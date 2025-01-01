package ai.yuvi.auth.servlets;

import ai.yuvi.auth.models.User;
import ai.yuvi.auth.models.Session;
import ai.yuvi.auth.services.AuthService;
import ai.yuvi.auth.services.SessionService;
import ai.yuvi.auth.services.AuditService;
import ai.yuvi.db.utilities.DatabaseUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Cookie;
import java.io.IOException;
import java.util.Optional;

public abstract class BaseAuthServlet extends HttpServlet {
    protected final AuthService authService;
    protected final SessionService sessionService;
    protected final AuditService auditService;
    protected static final String SESSION_COOKIE_NAME = "YUVI_SESSION";
    protected static final int SESSION_COOKIE_MAX_AGE = 24 * 60 * 60; // 24 hours

    public BaseAuthServlet(AuthService authService, SessionService sessionService, 
                          AuditService auditService) {
        this.authService = authService;
        this.sessionService = sessionService;
        this.auditService = auditService;
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Track page view for all requests
        String sessionToken = sessionService.extractSessionToken(request);
        if (sessionToken != null) {
            Optional<User> currentUser = authService.getCurrentUser(sessionToken);
            if (currentUser.isPresent()) {
                auditService.trackPageView(currentUser.get().getId(), sessionToken,
                    request.getRequestURI(), request.getHeader("Referer"), request);
            }
        }
        
        super.service(request, response);
    }

    protected Optional<User> getCurrentUser(HttpServletRequest request) {
        String sessionToken = sessionService.extractSessionToken(request);
        if (sessionToken == null) {
            return Optional.empty();
        }
        return authService.getCurrentUser(sessionToken);
    }

    protected boolean isAuthenticated(HttpServletRequest request) {
        String sessionToken = sessionService.extractSessionToken(request);
        return sessionToken != null && authService.validateSession(sessionToken);
    }

    protected void requireAuthentication(HttpServletRequest request, 
                                      HttpServletResponse response) 
            throws IOException {
        if (!isAuthenticated(request)) {
            String targetUrl = request.getRequestURI();
            if (request.getQueryString() != null) {
                targetUrl += "?" + request.getQueryString();
            }
            response.sendRedirect(request.getContextPath() + 
                "/login?redirect=" + targetUrl);
        }
    }

    protected void setSessionCookie(HttpServletResponse response, Session session) {
        Cookie cookie = new Cookie(SESSION_COOKIE_NAME, session.getToken());
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        cookie.setSecure(true); // Requires HTTPS
        cookie.setMaxAge(SESSION_COOKIE_MAX_AGE);
        response.addCookie(cookie);
    }

    protected void clearSessionCookie(HttpServletResponse response) {
        Cookie cookie = new Cookie(SESSION_COOKIE_NAME, "");
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        cookie.setSecure(true);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
    }

    protected void sendError(HttpServletResponse response, String message) 
            throws IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write("{\"error\":\"" + message + "\"}");
    }

    protected void sendSuccess(HttpServletResponse response, String message) 
            throws IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write("{\"success\":\"" + message + "\"}");
    }

    protected void sendJson(HttpServletResponse response, Object data) 
            throws IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(
            new com.fasterxml.jackson.databind.ObjectMapper()
                .writeValueAsString(data)
        );
    }

    protected void trackUserAction(HttpServletRequest request, String action) {
        Optional<User> currentUser = getCurrentUser(request);
        if (currentUser.isPresent()) {
            String sessionToken = sessionService.extractSessionToken(request);
            auditService.trackEvent(currentUser.get().getId(), sessionToken,
                action, null, "ACTION", action, request);
        }
    }

    protected void logSecurityEvent(HttpServletRequest request, String action, 
                                  String details) {
        Optional<User> currentUser = getCurrentUser(request);
        auditService.logSecurityEvent(
            currentUser.map(User::getId).orElse(null),
            action,
            details,
            request
        );
    }

    protected String getRedirectUrl(HttpServletRequest request) {
        String redirectUrl = request.getParameter("redirect");
        if (redirectUrl == null || redirectUrl.isEmpty()) {
            redirectUrl = request.getContextPath() + "/";
        }
        // Validate redirect URL to prevent open redirect vulnerability
        if (!redirectUrl.startsWith("/") && 
            !redirectUrl.startsWith(request.getContextPath())) {
            redirectUrl = request.getContextPath() + "/";
        }
        return redirectUrl;
    }

    protected void handleException(HttpServletRequest request,
                                 HttpServletResponse response,
                                 Exception e) throws IOException {
        logSecurityEvent(request, "ERROR",
            "Error in " + request.getRequestURI() + ": " + e.getMessage());
        sendError(response, "An error occurred. Please try again later.");
    }
}
