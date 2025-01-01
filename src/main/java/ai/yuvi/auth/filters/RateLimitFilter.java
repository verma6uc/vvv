package ai.yuvi.auth.filters;

import ai.yuvi.auth.services.AuditService;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

@WebFilter("/*")
public class RateLimitFilter implements Filter {
    private final AuditService auditService;

    // Rate limit configurations
    private static final int WINDOW_MINUTES = 1;
    private static final int MAX_REQUESTS_PER_IP = 60; // 60 requests per minute
    private static final int MAX_AUTH_ATTEMPTS = 5; // 5 auth attempts per minute
    private static final int MAX_REQUESTS_PER_USER = 120; // 120 requests per minute for authenticated users

    // Cache to store request counts per IP
    private LoadingCache<String, AtomicInteger> ipRequestCountCache;

    // Cache to store auth attempt counts per IP
    private LoadingCache<String, AtomicInteger> authAttemptCountCache;

    // Cache to store request counts per user
    private LoadingCache<Long, AtomicInteger> userRequestCountCache;

    // Paths that require stricter rate limiting (auth endpoints)
    private static final Set<String> AUTH_PATHS = new HashSet<>(Arrays.asList(
        "/login",
        "/signup",
        "/forgot-password",
        "/reset-password",
        "/verify-email"
    ));

    public RateLimitFilter(AuditService auditService) {
        this.auditService = auditService;
        initializeCaches();
    }

    private void initializeCaches() {
        // Initialize IP request count cache
        ipRequestCountCache = CacheBuilder.newBuilder()
            .expireAfterWrite(WINDOW_MINUTES, TimeUnit.MINUTES)
            .build(new CacheLoader<String, AtomicInteger>() {
                @Override
                public AtomicInteger load(String key) {
                    return new AtomicInteger(0);
                }
            });

        // Initialize auth attempt count cache
        authAttemptCountCache = CacheBuilder.newBuilder()
            .expireAfterWrite(WINDOW_MINUTES, TimeUnit.MINUTES)
            .build(new CacheLoader<String, AtomicInteger>() {
                @Override
                public AtomicInteger load(String key) {
                    return new AtomicInteger(0);
                }
            });

        // Initialize user request count cache
        userRequestCountCache = CacheBuilder.newBuilder()
            .expireAfterWrite(WINDOW_MINUTES, TimeUnit.MINUTES)
            .build(new CacheLoader<Long, AtomicInteger>() {
                @Override
                public AtomicInteger load(Long key) {
                    return new AtomicInteger(0);
                }
            });
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

        String clientIp = getClientIp(httpRequest);
        String path = httpRequest.getRequestURI().substring(
            httpRequest.getContextPath().length());

        try {
            // Check IP-based rate limit
            AtomicInteger ipRequestCount = ipRequestCountCache.get(clientIp);
            if (ipRequestCount.incrementAndGet() > MAX_REQUESTS_PER_IP) {
                handleRateLimitExceeded(httpRequest, httpResponse, "IP-based rate limit exceeded");
                return;
            }

            // Check auth endpoint rate limit
            if (isAuthPath(path)) {
                AtomicInteger authAttemptCount = authAttemptCountCache.get(clientIp);
                if (authAttemptCount.incrementAndGet() > MAX_AUTH_ATTEMPTS) {
                    handleRateLimitExceeded(httpRequest, httpResponse, 
                        "Authentication attempt rate limit exceeded");
                    return;
                }
            }

            // Check user-based rate limit for authenticated users
            Object userId = httpRequest.getAttribute("currentUser.id");
            if (userId != null) {
                AtomicInteger userRequestCount = userRequestCountCache.get((Long) userId);
                if (userRequestCount.incrementAndGet() > MAX_REQUESTS_PER_USER) {
                    handleRateLimitExceeded(httpRequest, httpResponse, 
                        "User-based rate limit exceeded");
                    return;
                }
            }

            // Add rate limit headers
            addRateLimitHeaders(httpResponse, clientIp, userId);

            // Continue with the request
            chain.doFilter(request, response);

        } catch (ExecutionException e) {
            // Log error and continue without rate limiting
            e.printStackTrace();
            chain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {
        // Cleanup code if needed
    }

    private boolean isAuthPath(String path) {
        return AUTH_PATHS.stream().anyMatch(path::startsWith);
    }

    private void handleRateLimitExceeded(HttpServletRequest request, 
                                       HttpServletResponse response,
                                       String reason) throws IOException {
        // Log rate limit violation
        auditService.logSecurityEvent(null, "RATE_LIMIT_EXCEEDED",
            reason + " for IP: " + getClientIp(request), request);

        // Set rate limit headers
        response.setHeader("Retry-After", String.valueOf(WINDOW_MINUTES * 60));

        if (isAjaxRequest(request)) {
            response.setStatus(HttpServletResponse.SC_TOO_MANY_REQUESTS);
            response.getWriter().write("{\"error\":\"" + reason + "\"}");
        } else {
            response.sendError(HttpServletResponse.SC_TOO_MANY_REQUESTS, reason);
        }
    }

    private void addRateLimitHeaders(HttpServletResponse response, String clientIp,
                                   Object userId) throws ExecutionException {
        // Add IP-based rate limit headers
        AtomicInteger ipRequestCount = ipRequestCountCache.get(clientIp);
        response.setHeader("X-RateLimit-Limit", String.valueOf(MAX_REQUESTS_PER_IP));
        response.setHeader("X-RateLimit-Remaining", 
            String.valueOf(Math.max(0, MAX_REQUESTS_PER_IP - ipRequestCount.get())));
        response.setHeader("X-RateLimit-Reset", 
            String.valueOf(System.currentTimeMillis() / 1000 + WINDOW_MINUTES * 60));

        // Add user-based rate limit headers if authenticated
        if (userId != null) {
            AtomicInteger userRequestCount = userRequestCountCache.get((Long) userId);
            response.setHeader("X-UserRateLimit-Limit", 
                String.valueOf(MAX_REQUESTS_PER_USER));
            response.setHeader("X-UserRateLimit-Remaining",
                String.valueOf(Math.max(0, MAX_REQUESTS_PER_USER - userRequestCount.get())));
        }
    }

    private String getClientIp(HttpServletRequest request) {
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
        // If multiple IPs, take the first one (client IP)
        if (ip != null && ip.contains(",")) {
            ip = ip.split(",")[0].trim();
        }
        return ip;
    }

    private boolean isAjaxRequest(HttpServletRequest request) {
        return "XMLHttpRequest".equals(request.getHeader("X-Requested-With")) ||
               request.getHeader("Accept") != null &&
               request.getHeader("Accept").contains("application/json");
    }
}
