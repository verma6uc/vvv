package ai.yuvi.auth.filters;

import ai.yuvi.auth.services.AuditService;
import ai.yuvi.auth.services.SessionService;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.HashSet;
import java.util.Set;

@WebFilter("/*")
public class CsrfFilter implements Filter {
    private final SessionService sessionService;
    private final AuditService auditService;
    private static final SecureRandom SECURE_RANDOM = new SecureRandom();
    private static final String CSRF_TOKEN_HEADER = "X-CSRF-TOKEN";
    private static final String CSRF_TOKEN_FORM_FIELD = "_csrf";
    private static final String CSRF_TOKEN_ATTRIBUTE = "csrfToken";

    // Methods that require CSRF protection
    private static final Set<String> PROTECTED_METHODS = new HashSet<String>() {{
        add("POST");
        add("PUT");
        add("DELETE");
        add("PATCH");
    }};

    // Paths that are exempt from CSRF protection
    private static final Set<String> CSRF_EXEMPT_PATHS = new HashSet<String>() {{
        add("/api/"); // For API endpoints that use other authentication methods
        add("/webhook/"); // For webhook endpoints
    }};

    public CsrfFilter(SessionService sessionService, AuditService auditService) {
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

        // Skip CSRF check for exempt paths
        String path = httpRequest.getRequestURI().substring(
            httpRequest.getContextPath().length());
        if (isCsrfExemptPath(path)) {
            chain.doFilter(request, response);
            return;
        }

        // Generate or retrieve CSRF token
        String csrfToken = getCsrfToken(httpRequest);
        httpRequest.setAttribute(CSRF_TOKEN_ATTRIBUTE, csrfToken);

        // Add CSRF token to response headers for AJAX requests
        httpResponse.setHeader(CSRF_TOKEN_HEADER, csrfToken);

        // Check CSRF token for protected methods
        if (PROTECTED_METHODS.contains(httpRequest.getMethod())) {
            String requestToken = getRequestCsrfToken(httpRequest);
            
            if (!isValidCsrfToken(csrfToken, requestToken)) {
                handleInvalidCsrfToken(httpRequest, httpResponse);
                return;
            }
        }

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        // Cleanup code if needed
    }

    private String getCsrfToken(HttpServletRequest request) {
        // Try to get existing token from session
        String token = (String) request.getSession().getAttribute(CSRF_TOKEN_ATTRIBUTE);
        
        if (token == null) {
            // Generate new token
            byte[] randomBytes = new byte[32];
            SECURE_RANDOM.nextBytes(randomBytes);
            token = Base64.getUrlEncoder().withoutPadding().encodeToString(randomBytes);
            
            // Store in session
            request.getSession().setAttribute(CSRF_TOKEN_ATTRIBUTE, token);
        }
        
        return token;
    }

    private String getRequestCsrfToken(HttpServletRequest request) {
        // Check header first (for AJAX requests)
        String token = request.getHeader(CSRF_TOKEN_HEADER);
        
        // Then check form parameter
        if (token == null || token.isEmpty()) {
            token = request.getParameter(CSRF_TOKEN_FORM_FIELD);
        }
        
        return token;
    }

    private boolean isValidCsrfToken(String expectedToken, String actualToken) {
        if (expectedToken == null || actualToken == null) {
            return false;
        }
        
        // Use constant-time comparison to prevent timing attacks
        return MessageDigest.isEqual(
            expectedToken.getBytes(),
            actualToken.getBytes()
        );
    }

    private boolean isCsrfExemptPath(String path) {
        return CSRF_EXEMPT_PATHS.stream().anyMatch(path::startsWith);
    }

    private void handleInvalidCsrfToken(HttpServletRequest request, 
                                      HttpServletResponse response) 
            throws IOException {
        // Log security event
        auditService.logSecurityEvent(null, "CSRF_VIOLATION",
            "Invalid CSRF token for " + request.getMethod() + " " + 
            request.getRequestURI(), request);

        if (isAjaxRequest(request)) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            response.getWriter().write("{\"error\":\"Invalid CSRF token\"}");
        } else {
            response.sendError(HttpServletResponse.SC_FORBIDDEN, 
                "Invalid CSRF token");
        }
    }

    private boolean isAjaxRequest(HttpServletRequest request) {
        return "XMLHttpRequest".equals(request.getHeader("X-Requested-With")) ||
               request.getHeader("Accept") != null &&
               request.getHeader("Accept").contains("application/json");
    }
}
