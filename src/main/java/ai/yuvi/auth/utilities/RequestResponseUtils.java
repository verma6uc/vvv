package ai.yuvi.auth.utilities;

import ai.yuvi.auth.models.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class RequestResponseUtils {
    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static final String SESSION_COOKIE_NAME = "YUVI_SESSION";
    private static final int SESSION_COOKIE_MAX_AGE = 24 * 60 * 60; // 24 hours
    private static final String CSRF_TOKEN_HEADER = "X-CSRF-TOKEN";
    private static final String CSRF_TOKEN_FORM_FIELD = "_csrf";

    // Request parsing methods
    public static Map<String, String> parseJsonRequest(HttpServletRequest request) 
            throws IOException {
        return objectMapper.readValue(request.getInputStream(), Map.class);
    }

    public static String getRequiredParameter(HttpServletRequest request, String paramName) 
            throws IllegalArgumentException {
        String value = request.getParameter(paramName);
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException(paramName + " is required");
        }
        return SecurityUtils.sanitizeInput(value.trim());
    }

    public static Optional<String> getOptionalParameter(HttpServletRequest request, 
                                                      String paramName) {
        String value = request.getParameter(paramName);
        return value == null || value.trim().isEmpty() ? 
               Optional.empty() : 
               Optional.of(SecurityUtils.sanitizeInput(value.trim()));
    }

    public static String getClientIp(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    public static String getUserAgent(HttpServletRequest request) {
        String userAgent = request.getHeader("User-Agent");
        return userAgent != null ? SecurityUtils.sanitizeInput(userAgent) : "Unknown";
    }

    public static String getBaseUrl(HttpServletRequest request) {
        String scheme = request.getScheme();
        String serverName = request.getServerName();
        int serverPort = request.getServerPort();
        String contextPath = request.getContextPath();

        StringBuilder url = new StringBuilder();
        url.append(scheme).append("://").append(serverName);

        if (serverPort != 80 && serverPort != 443) {
            url.append(":").append(serverPort);
        }

        url.append(contextPath);
        return url.toString();
    }

    // Response methods
    public static void sendJsonResponse(HttpServletResponse response, Object data) 
            throws IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        objectMapper.writeValue(response.getWriter(), data);
    }

    public static void sendError(HttpServletResponse response, String message, int status) 
            throws IOException {
        response.setStatus(status);
        Map<String, String> error = new HashMap<>();
        error.put("error", message);
        sendJsonResponse(response, error);
    }

    public static void sendSuccess(HttpServletResponse response, String message) 
            throws IOException {
        Map<String, String> success = new HashMap<>();
        success.put("success", message);
        sendJsonResponse(response, success);
    }

    public static void sendUserResponse(HttpServletResponse response, User user) 
            throws IOException {
        Map<String, Object> userResponse = new HashMap<>();
        userResponse.put("id", user.getId());
        userResponse.put("email", user.getEmail());
        userResponse.put("firstName", user.getFirstName());
        userResponse.put("lastName", user.getLastName());
        userResponse.put("emailVerified", user.isEmailVerified());
        userResponse.put("systemRole", user.getSystemRole());
        sendJsonResponse(response, userResponse);
    }

    // Cookie management
    public static void setSessionCookie(HttpServletResponse response, String token, 
                                      boolean rememberMe) {
        Cookie cookie = new Cookie(SESSION_COOKIE_NAME, token);
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        cookie.setSecure(true); // Requires HTTPS
        
        if (rememberMe) {
            cookie.setMaxAge(SESSION_COOKIE_MAX_AGE);
        } else {
            cookie.setMaxAge(-1); // Session cookie
        }
        
        response.addCookie(cookie);
    }

    public static void clearSessionCookie(HttpServletResponse response) {
        Cookie cookie = new Cookie(SESSION_COOKIE_NAME, "");
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        cookie.setSecure(true);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
    }

    public static Optional<String> getSessionToken(HttpServletRequest request) {
        // First check header
        String token = request.getHeader("X-Session-Token");
        if (token != null && !token.isEmpty()) {
            return Optional.of(token);
        }

        // Then check cookies
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (SESSION_COOKIE_NAME.equals(cookie.getName())) {
                    return Optional.of(cookie.getValue());
                }
            }
        }

        // Finally check request parameter
        token = request.getParameter("sessionToken");
        return token != null && !token.isEmpty() ? Optional.of(token) : Optional.empty();
    }

    // CSRF protection
    public static void setCsrfToken(HttpServletRequest request, 
                                  HttpServletResponse response) {
        String token = SecurityUtils.generateCsrfToken();
        request.setAttribute(CSRF_TOKEN_FORM_FIELD, token);
        response.setHeader(CSRF_TOKEN_HEADER, token);
    }

    public static Optional<String> getCsrfToken(HttpServletRequest request) {
        // First check header
        String token = request.getHeader(CSRF_TOKEN_HEADER);
        if (token != null && !token.isEmpty()) {
            return Optional.of(token);
        }

        // Then check form parameter
        token = request.getParameter(CSRF_TOKEN_FORM_FIELD);
        return token != null && !token.isEmpty() ? Optional.of(token) : Optional.empty();
    }

    public static boolean isAjaxRequest(HttpServletRequest request) {
        return "XMLHttpRequest".equals(request.getHeader("X-Requested-With")) ||
               request.getHeader("Accept") != null &&
               request.getHeader("Accept").contains("application/json");
    }
}
