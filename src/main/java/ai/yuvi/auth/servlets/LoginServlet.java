package ai.yuvi.auth.servlets;

import ai.yuvi.auth.models.Session;
import ai.yuvi.auth.services.AuthService;
import ai.yuvi.auth.services.SessionService;
import ai.yuvi.auth.services.AuditService;
import ai.yuvi.auth.config.AuthConfig;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends BaseAuthServlet {
    
    private final AuthService authService;
    private final SessionService sessionService;
    private final AuditService auditService;

    @Override
    public void init() throws ServletException {
        super.init();
        // Get service instances from AuthConfig
        AuthConfig config = AuthConfig.getInstance();
        this.authService = config.getAuthService();
        this.sessionService = config.getSessionService();
        this.auditService = config.getAuditService();
    }

    @Override
    protected String getJspPath() {
        return "/login.jsp";
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        // If user is already authenticated, redirect to home
        if (sessionService.isAuthenticated(request)) {
            response.sendRedirect(request.getContextPath() + "/");
            return;
        }

        // Forward to login page
        super.doGet(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        boolean rememberMe = "on".equals(request.getParameter("remember"));

        try {
            // Attempt login
            Session session = authService.login(email, password, request);

            // Set session cookie
            if (rememberMe) {
                // Extend session timeout for remember me
                sessionService.extendSession(session.getToken());
            }
            sessionService.setSessionCookie(response, session);

            // Track successful login
            auditService.trackUserAction(request, "LOGIN");
            auditService.logSecurityEvent(request, "LOGIN_SUCCESS", 
                "User logged in successfully: " + email);

            // Redirect to requested page or home
            String redirectUrl = sessionService.getRedirectUrl(request);
            response.sendRedirect(redirectUrl);

        } catch (AuthService.AuthenticationException e) {
            // Log failed login attempt
            auditService.logSecurityEvent(request, "LOGIN_FAILED", 
                "Failed login attempt for email: " + email + " - " + e.getMessage());

            // Add error message and forward back to login page
            request.setAttribute("error", e.getMessage());
            request.setAttribute("email", email); // Preserve email for form
            request.getRequestDispatcher(getJspPath()).forward(request, response);
        } catch (Exception e) {
            handleException(request, response, e);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        try {
            // Get current session token
            String sessionToken = sessionService.extractSessionToken(request);
            if (sessionToken != null) {
                // Log the logout action before invalidating the session
                auditService.trackUserAction(request, "LOGOUT");
                auditService.logSecurityEvent(request, "LOGOUT", "User logged out successfully");

                // Logout and invalidate session
                authService.logout(sessionToken);
                sessionService.clearSessionCookie(response);
            }

            sendSuccess(response, "Logged out successfully");
        } catch (Exception e) {
            handleException(request, response, e);
        }
    }

    private void sendSuccess(HttpServletResponse response, String message) throws IOException {
        response.setContentType("application/json");
        response.getWriter().write("{\"message\":\"" + message + "\"}");
    }

    private void handleException(HttpServletRequest request, HttpServletResponse response, Exception e) 
            throws ServletException, IOException {
        auditService.logSecurityEvent(request, "ERROR", 
            "Error during authentication: " + e.getMessage());
        throw new ServletException("Authentication error", e);
    }
}
