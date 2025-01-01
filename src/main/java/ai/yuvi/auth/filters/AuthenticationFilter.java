package ai.yuvi.auth.filters;

import ai.yuvi.auth.models.User;
import ai.yuvi.auth.services.AuthService;
import ai.yuvi.auth.services.SessionService;
import ai.yuvi.auth.services.AuditService;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@WebFilter("/*")
public class AuthenticationFilter implements Filter {
    private final AuthService authService;
    private final SessionService sessionService;
    private final AuditService auditService;

    // Paths that don't require authentication
    private static final Set<String> PUBLIC_PATHS = new HashSet<>(Arrays.asList(
        "/login",
        "/signup",
        "/forgot-password",
        "/reset-password",
        "/verify-email",
        "/assets",
        "/favicon.ico"
    ));

    // Paths that are always allowed (even for unverified users)
    private static final Set<String> ALWAYS_ALLOWED_PATHS = new HashSet<>(Arrays.asList(
        "/verify-email",
        "/logout",
        "/assets",
        "/favicon.ico"
    ));

    public AuthenticationFilter(AuthService authService, SessionService sessionService, 
                              AuditService auditService) {
        this.authService = authService;
        this.sessionService = sessionService;
        this.auditService = auditService;
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Initialization code if needed
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, 
                        FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        String path = httpRequest.getRequestURI().substring(
            httpRequest.getContextPath().length());

        // Skip authentication for public paths
        if (isPublicPath(path)) {
            chain.doFilter(request, response);
            return;
        }

        // Get session token
        String sessionToken = sessionService.extractSessionToken(httpRequest);
        if (sessionToken == null) {
            handleUnauthenticated(httpRequest, httpResponse);
            return;
        }

        // Validate session
        if (!authService.validateSession(sessionToken)) {
            handleInvalidSession(httpRequest, httpResponse, sessionToken);
            return;
        }

        // Get current user
        Optional<User> currentUser = authService.getCurrentUser(sessionToken);
        if (!currentUser.isPresent()) {
            handleInvalidSession(httpRequest, httpResponse, sessionToken);
            return;
        }

        User user = currentUser.get();

        // Check email verification requirement
        if (!user.isEmailVerified() && !isAlwaysAllowedPath(path)) {
            handleUnverifiedEmail(httpRequest, httpResponse, user);
            return;
        }

        // Track page view
        auditService.trackPageView(user.getId(), sessionToken,
            httpRequest.getRequestURI(), httpRequest.getHeader("Referer"),
            httpRequest);

        // Add user to request attributes
        httpRequest.setAttribute("currentUser", user);

        // Continue with the request
        chain.doFilter(request, response);

        // Track response status
        if (httpResponse.getStatus() >= 400) {
            auditService.logAction(user.getId(), "ERROR", "HTTP",
                null, "HTTP " + httpResponse.getStatus() + " on " + path,
                httpRequest);
        }
    }

    @Override
    public void destroy() {
        // Cleanup code if needed
    }

    private boolean isPublicPath(String path) {
        return PUBLIC_PATHS.stream().anyMatch(path::startsWith);
    }

    private boolean isAlwaysAllowedPath(String path) {
        return ALWAYS_ALLOWED_PATHS.stream().anyMatch(path::startsWith);
    }

    private void handleUnauthenticated(HttpServletRequest request, 
                                     HttpServletResponse response) 
            throws IOException {
        if (isAjaxRequest(request)) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("{\"error\":\"Authentication required\"}");
        } else {
            String targetUrl = request.getRequestURI();
            if (request.getQueryString() != null) {
                targetUrl += "?" + request.getQueryString();
            }
            response.sendRedirect(request.getContextPath() + 
                "/login?redirect=" + targetUrl);
        }
    }

    private void handleInvalidSession(HttpServletRequest request, 
                                    HttpServletResponse response,
                                    String sessionToken) throws IOException {
        // Log security event
        auditService.logSecurityEvent(null, "INVALID_SESSION",
            "Invalid session token: " + sessionToken, request);

        if (isAjaxRequest(request)) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("{\"error\":\"Session expired\"}");
        } else {
            String targetUrl = request.getRequestURI();
            if (request.getQueryString() != null) {
                targetUrl += "?" + request.getQueryString();
            }
            response.sendRedirect(request.getContextPath() + 
                "/login?redirect=" + targetUrl + "&expired=true");
        }
    }

    private void handleUnverifiedEmail(HttpServletRequest request, 
                                     HttpServletResponse response,
                                     User user) throws IOException {
        // Log security event
        auditService.logSecurityEvent(user.getId(), "UNVERIFIED_ACCESS_ATTEMPT",
            "Unverified user attempted to access: " + request.getRequestURI(),
            request);

        if (isAjaxRequest(request)) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            response.getWriter().write(
                "{\"error\":\"Please verify your email to access this resource\"}");
        } else {
            response.sendRedirect(request.getContextPath() + 
                "/verify-email?unverified=true");
        }
    }

    private boolean isAjaxRequest(HttpServletRequest request) {
        return "XMLHttpRequest".equals(request.getHeader("X-Requested-With")) ||
               request.getHeader("Accept") != null &&
               request.getHeader("Accept").contains("application/json");
    }
}
