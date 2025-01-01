package ai.yuvi.auth.servlets;

import ai.yuvi.auth.services.AuthService;
import ai.yuvi.auth.services.SessionService;
import ai.yuvi.auth.services.AuditService;
import ai.yuvi.auth.config.AuthConfig;
import ai.yuvi.auth.utilities.SecurityUtils;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/forgot-password")
public class ForgotPasswordServlet extends BaseAuthServlet {
    
    private AuthService authService;
    private SessionService sessionService;
    private AuditService auditService;
    private SecurityUtils securityUtils;

    @Override
    public void init() throws ServletException {
        super.init();
        // Get service instances from AuthConfig
        AuthConfig config = AuthConfig.getInstance();
        this.authService = config.getAuthService();
        this.sessionService = config.getSessionService();
        this.auditService = config.getAuditService();
        this.securityUtils = config.getSecurityUtils();
    }

    @Override
    protected String getJspPath() {
        return "/forgot-password.jsp";
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        // If user is already authenticated, redirect to home
        if (sessionService.isAuthenticated(request)) {
            response.sendRedirect(request.getContextPath() + "/");
            return;
        }

        String token = request.getParameter("token");
        if (token != null) {
            // Validate token format before checking database
            if (!securityUtils.isValidResetToken(token)) {
                auditService.logSecurityEvent(request, "INVALID_RESET_TOKEN", 
                    "Invalid reset token format detected");
                request.setAttribute("error", "Invalid password reset link. Please try again.");
                request.getRequestDispatcher(getJspPath()).forward(request, response);
                return;
            }

            // If token is provided, validate it
            if (authService.validatePasswordResetToken(token)) {
                // Token is valid, show reset password form
                request.setAttribute("token", token);
                request.getRequestDispatcher("/reset-password.jsp").forward(request, response);
                return;
            } else {
                // Invalid or expired token
                auditService.logSecurityEvent(request, "EXPIRED_RESET_TOKEN", 
                    "Expired reset token attempted to be used");
                request.setAttribute("error", "This password reset link has expired. Please request a new one.");
            }
        }

        // Show forgot password form
        request.getRequestDispatcher(getJspPath()).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String email = request.getParameter("email");
        String token = request.getParameter("token");
        String newPassword = request.getParameter("new_password");
        String confirmPassword = request.getParameter("confirm_password");

        try {
            if (token != null) {
                handlePasswordReset(request, response, token, newPassword, confirmPassword);
            } else {
                handleForgotPassword(request, response, email);
            }
        } catch (Exception e) {
            handleException(request, response, e);
        }
    }

    private void handlePasswordReset(HttpServletRequest request, HttpServletResponse response,
                                   String token, String newPassword, String confirmPassword) 
            throws ServletException, IOException {
        // Validate token format
        if (!securityUtils.isValidResetToken(token)) {
            auditService.logSecurityEvent(request, "INVALID_RESET_TOKEN", 
                "Invalid reset token format detected during reset");
            request.setAttribute("error", "Invalid password reset link. Please try again.");
            request.getRequestDispatcher(getJspPath()).forward(request, response);
            return;
        }

        // Validate password
        if (newPassword == null || newPassword.trim().isEmpty()) {
            request.setAttribute("error", "New password is required");
            request.setAttribute("token", token);
            request.getRequestDispatcher("/reset-password.jsp").forward(request, response);
            return;
        }

        if (!newPassword.equals(confirmPassword)) {
            request.setAttribute("error", "Passwords do not match");
            request.setAttribute("token", token);
            request.getRequestDispatcher("/reset-password.jsp").forward(request, response);
            return;
        }

        if (!authService.isPasswordStrong(newPassword)) {
            request.setAttribute("error", 
                "Password must be at least 8 characters long and contain at least " +
                "one uppercase letter, one lowercase letter, one number, and one special character");
            request.setAttribute("token", token);
            request.getRequestDispatcher("/reset-password.jsp").forward(request, response);
            return;
        }

        try {
            // Reset password
            authService.resetPassword(token, newPassword);

            // Log password reset
            auditService.logSecurityEvent(request, "PASSWORD_RESET_SUCCESS", 
                "Password reset successful");

            // Redirect to login with success message
            request.getSession().setAttribute("success", 
                "Your password has been reset successfully. Please log in with your new password.");
            response.sendRedirect(request.getContextPath() + "/login");

        } catch (AuthService.PasswordResetException e) {
            handlePasswordResetError(request, response, token, e);
        }
    }

    private void handleForgotPassword(HttpServletRequest request, HttpServletResponse response, 
                                    String email) 
            throws ServletException, IOException {
        if (email == null || email.trim().isEmpty()) {
            request.setAttribute("error", "Email is required");
            request.getRequestDispatcher(getJspPath()).forward(request, response);
            return;
        }

        try {
            // Rate limit check for password reset requests
            if (!securityUtils.checkRateLimit(request, "password_reset", email)) {
                auditService.logSecurityEvent(request, "RATE_LIMIT_EXCEEDED", 
                    "Too many password reset attempts for: " + email);
                request.setAttribute("error", 
                    "Too many password reset attempts. Please try again later.");
                request.getRequestDispatcher(getJspPath()).forward(request, response);
                return;
            }

            // Initiate password reset
            authService.initiatePasswordReset(email);

            // Log password reset request
            auditService.logSecurityEvent(request, "PASSWORD_RESET_REQUESTED", 
                "Password reset requested");

            // Show success message (don't confirm email exists)
            request.setAttribute("success", 
                "If an account exists for this email, you will receive password reset instructions shortly.");
            request.getRequestDispatcher(getJspPath()).forward(request, response);

        } catch (AuthService.EmailException e) {
            handleEmailError(request, response, email, e);
        }
    }

    private void handlePasswordResetError(HttpServletRequest request, HttpServletResponse response,
                                        String token, Exception e) 
            throws ServletException, IOException {
        auditService.logSecurityEvent(request, "PASSWORD_RESET_FAILED", 
            "Password reset failed: " + e.getMessage());
        request.setAttribute("error", "Unable to reset password. Please try again.");
        request.setAttribute("token", token);
        request.getRequestDispatcher("/reset-password.jsp").forward(request, response);
    }

    private void handleEmailError(HttpServletRequest request, HttpServletResponse response,
                                String email, Exception e) 
            throws ServletException, IOException {
        auditService.logSecurityEvent(request, "PASSWORD_RESET_EMAIL_FAILED", 
            "Failed to send password reset email: " + e.getMessage());
        request.setAttribute("error", 
            "Unable to process your request. Please try again later.");
        request.setAttribute("email", email);
        request.getRequestDispatcher(getJspPath()).forward(request, response);
    }

    private void handleException(HttpServletRequest request, HttpServletResponse response, 
                               Exception e) 
            throws ServletException, IOException {
        auditService.logSecurityEvent(request, "ERROR", 
            "Error during password reset flow: " + e.getMessage());
        throw new ServletException("Password reset error", e);
    }
}
