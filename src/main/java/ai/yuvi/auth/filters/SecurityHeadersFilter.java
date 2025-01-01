package ai.yuvi.auth.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/*")
public class SecurityHeadersFilter implements Filter {
    // CSP directives
    private static final String CONTENT_SECURITY_POLICY =
        "default-src 'self'; " +
        "script-src 'self' 'unsafe-inline' 'unsafe-eval'; " + // For inline scripts and eval
        "style-src 'self' 'unsafe-inline'; " + // For inline styles
        "img-src 'self' data: https:; " + // Allow data: URLs for images and HTTPS
        "font-src 'self' data: https:; " + // Allow data: URLs for fonts and HTTPS
        "connect-src 'self' https:; " + // Allow HTTPS connections
        "frame-ancestors 'none'; " + // Prevent clickjacking
        "form-action 'self'; " + // Restrict form submissions to same origin
        "base-uri 'self'"; // Restrict base URI to same origin

    // Cache control directives for different content types
    private static final String CACHE_CONTROL_STATIC = 
        "public, max-age=31536000, immutable"; // 1 year for static assets
    private static final String CACHE_CONTROL_DYNAMIC = 
        "no-store, no-cache, must-revalidate, proxy-revalidate";

    // Static file extensions that can be cached
    private static final Set<String> STATIC_EXTENSIONS = new HashSet<>(Arrays.asList(
        ".css", ".js", ".jpg", ".jpeg", ".png", ".gif", ".ico", ".svg", 
        ".woff", ".woff2", ".ttf", ".eot"
    ));

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Initialization code if needed
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, 
                        FilterChain chain) throws IOException, ServletException {
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        // Security headers
        httpResponse.setHeader("X-Content-Type-Options", "nosniff");
        httpResponse.setHeader("X-Frame-Options", "DENY");
        httpResponse.setHeader("X-XSS-Protection", "1; mode=block");
        httpResponse.setHeader("Referrer-Policy", "strict-origin-when-cross-origin");
        httpResponse.setHeader("Content-Security-Policy", CONTENT_SECURITY_POLICY);
        httpResponse.setHeader("Permissions-Policy", 
            "geolocation=(), " +
            "microphone=(), " +
            "camera=(), " +
            "payment=(), " +
            "usb=(), " +
            "magnetometer=(), " +
            "accelerometer=()");

        // HSTS - Enable only if site is HTTPS only
        if (request.isSecure()) {
            httpResponse.setHeader("Strict-Transport-Security", 
                "max-age=31536000; includeSubDomains; preload");
        }

        // Cache control
        String path = ((HttpServletRequest) request).getRequestURI();
        if (isStaticResource(path)) {
            httpResponse.setHeader("Cache-Control", CACHE_CONTROL_STATIC);
            // Add ETag for static resources
            String eTag = generateETag(path);
            httpResponse.setHeader("ETag", eTag);
        } else {
            httpResponse.setHeader("Cache-Control", CACHE_CONTROL_DYNAMIC);
            httpResponse.setHeader("Pragma", "no-cache");
            httpResponse.setHeader("Expires", "0");
        }

        // Continue with the request
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        // Cleanup code if needed
    }

    private boolean isStaticResource(String path) {
        return STATIC_EXTENSIONS.stream()
            .anyMatch(ext -> path.toLowerCase().endsWith(ext));
    }

    private String generateETag(String path) {
        try {
            // Get the file's last modified time
            ServletContext context = filterConfig.getServletContext();
            String realPath = context.getRealPath(path);
            if (realPath != null) {
                File file = new File(realPath);
                if (file.exists()) {
                    long lastModified = file.lastModified();
                    // Generate hash of file path and last modified time
                    String input = path + lastModified;
                    MessageDigest md = MessageDigest.getInstance("MD5");
                    byte[] hash = md.digest(input.getBytes(StandardCharsets.UTF_8));
                    return "\"" + Base64.getEncoder().encodeToString(hash) + "\"";
                }
            }
        } catch (Exception e) {
            // Log error but continue without ETag
            e.printStackTrace();
        }
        return null;
    }

    private FilterConfig filterConfig;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
    }
}
