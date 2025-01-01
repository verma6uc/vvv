package ai.yuvi.auth.servlets;

import ai.yuvi.auth.models.User;
import ai.yuvi.auth.services.AuthService;
import ai.yuvi.auth.services.SessionService;
import ai.yuvi.auth.services.AuditService;
import ai.yuvi.auth.config.AuthConfig;
import ai.yuvi.auth.utilities.SecurityUtils;
import ai.yuvi.auth.utilities.RequestResponseUtils;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@WebServlet("/verify-email")
public class EmailVerificationServlet extends BaseAuthServlet {
    
    private AuthService authService;
    private SessionService sessionService;
    private AuditService auditService;
    private SecurityUtils securityUtils;
    private RequestResponseUtils requestUtils;

    @Override
    public void init() throws ServletException {
        super.init();
        // Get service instances from AuthConfig
        AuthConfig config = AuthConfig.getInstance();
        this.authService = config.getAuthService();
        this.sessionService = config.getSessionService();
        this.auditService = config.getAuditService();
        this.securityUtils = config.getSecurityUtils();
        this.requestUtils = config.getRequestResponseUtils();
    }

    @Override
    protected String getJspPath() {
        return "/login.jsp"; // Email verification redirects to login page
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String token = request.getParameter("token");
        
        if (token == null || token.trim().isEmpty()) {
            handleInvalidToken(request, response, "Missing verification token");
            return;
        }

        // Validate token format before checking database
        if (!securityUtils.isValidVerificationToken(token)) {
            handleInvalidToken(request, response, "Invalid verification token format");
            return;
        }

        try {
            // Verify email
            boolean verified = authService.verifyEmail(token);

            if (verified) {
                handleSuccessfulVerification(request, response, token);
            } else {
                handleFailedVerification(request, response, token);
            }

        } catch (AuthService.VerificationException e) {
            handleVerificationError(request, response, e);
        } catch (Exception e) {
            handleException(request, response, e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String email = request.getParameter("email");

        if (email == null || email.trim().isEmpty()) {
            requestUtils.sendJsonError(response, "Email is required");
            return;
        }

        try {
            // Rate limit check
            if (!securityUtils.checkRateLimit(request, "verification_email", email)) {
                auditService.logSecurityEvent(request, "RATE_LIMIT_EXCEEDED", 
                    "Too many verification email requests");
                requestUtils.sendJsonError(response, 
                    "Too many requests. Please try again later.");
                return;
            }

            // Verify email ownership if user is logged in
            Optional<User> currentUser = sessionService.getCurrentUser(request);
            if (currentUser.isPresent() && !email.equals(currentUser.get().getEmail())) {
                handleUnauthorizedRequest(request, response, email);
                return;
            }

            // Send verification email
            authService.sendVerificationEmail(email);
            handleSuccessfulEmailSent(request, response, email);

        } catch (AuthService.EmailException e) {
            handleEmailError(request, response, email, e);
        } catch (Exception e) {
            handleException(request, response, e);
        }
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        if (!sessionService.isAuthenticated(request)) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        try {
            Optional<User> user = sessionService.getCurrentUser(request);
            if (user.isEmpty()) {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
                return;
            }

            // Rate limit check
            if (!securityUtils.checkRateLimit(request, "verification_email", user.get().getEmail())) {
                auditService.logSecurityEvent(request, "RATE_LIMIT_EXCEEDED", 
                    "Too many verification email requests");
                requestUtils.sendJsonError(response, 
                    "Too many requests. Please try again later.");
                return;
            }

            // Send new verification email
            authService.sendVerificationEmail(user.get());
            handleSuccessfulEmailSent(request, response, user.get().getEmail());

        } catch (Exception e) {
            handleException(request, response, e);
        }
    }

    private void handleInvalidToken(HttpServletRequest request, HttpServletResponse response, 
                                  String reason) 
            throws ServletException, IOException {
        auditService.logSecurityEvent(request, "INVALID_VERIFICATION_TOKEN", reason);
        request.setAttribute("error", "Invalid verification link. Please request a new one.");
        request.getRequestDispatcher(getJspPath()).forward(request, response);
    }

    private void handleSuccessfulVerification(HttpServletRequest request, 
                                            HttpServletResponse response, String token) 
            throws ServletException, IOException {
        auditService.logSecurityEvent(request, "EMAIL_VERIFICATION_SUCCESS", 
            "Email verified successfully");
        request.setAttribute("success", 
            "Your email has been verified successfully. You can now log in.");
        request.getRequestDispatcher(getJspPath()).forward(request, response);
    }

    private void handleFailedVerification(HttpServletRequest request, 
                                        HttpServletResponse response, String token) 
            throws ServletException, IOException {
        auditService.logSecurityEvent(request, "EMAIL_VERIFICATION_FAILED", 
            "Invalid or expired verification token");
        request.setAttribute("error", 
            "This verification link has expired. Please request a new one.");
        request.getRequestDispatcher(getJspPath()).forward(request, response);
    }

    private void handleVerificationError(HttpServletRequest request, 
                                       HttpServletResponse response, Exception e) 
            throws ServletException, IOException {
        auditService.logSecurityEvent(request, "EMAIL_VERIFICATION_ERROR", 
            "Error verifying email: " + e.getMessage());
        request.setAttribute("error", 
            "An error occurred while verifying your email. Please try again later.");
        request.getRequestDispatcher(getJspPath()).forward(request, response);
    }

    private void handleUnauthorizedRequest(HttpServletRequest request, 
                                         HttpServletResponse response, String email) 
            throws IOException {
        auditService.logSecurityEvent(request, "UNAUTHORIZED_VERIFICATION_REQUEST", 
            "Attempted to request verification email for different email: " + email);
        requestUtils.sendJsonError(response, "Unauthorized request");
    }

    private void handleSuccessfulEmailSent(HttpServletRequest request, 
                                         HttpServletResponse response, String email) 
            throws IOException {
        auditService.logSecurityEvent(request, "VERIFICATION_EMAIL_SENT", 
            "Verification email sent");
        requestUtils.sendJsonSuccess(response, 
            "Verification email sent. Please check your inbox and spam folder.");
    }

    private void handleEmailError(HttpServletRequest request, HttpServletResponse response, 
                                String email, Exception e) 
            throws IOException {
        auditService.logSecurityEvent(request, "VERIFICATION_EMAIL_FAILED", 
            "Failed to send verification email: " + e.getMessage());
        requestUtils.sendJsonError(response, 
            "Unable to send verification email. Please try again later.");
    }

    private void handleException(HttpServletRequest request, HttpServletResponse response, 
                               Exception e) 
            throws ServletException, IOException {
        auditService.logSecurityEvent(request, "ERROR", 
            "Error during email verification flow: " + e.getMessage());
        throw new ServletException("Email verification error", e);
    }
}
