package ai.yuvi.auth.config;

import ai.yuvi.auth.services.AuthService;
import ai.yuvi.auth.services.SessionService;
import ai.yuvi.auth.services.AuditService;
import ai.yuvi.auth.services.impl.AuthServiceImpl;
import ai.yuvi.auth.services.impl.SessionServiceImpl;
import ai.yuvi.auth.services.impl.AuditServiceImpl;
import ai.yuvi.auth.filters.AuthenticationFilter;
import ai.yuvi.auth.filters.CsrfFilter;
import ai.yuvi.auth.filters.SecurityHeadersFilter;
import ai.yuvi.auth.filters.RateLimitFilter;
import ai.yuvi.auth.servlets.*;
import ai.yuvi.db.utilities.DatabaseUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletRegistration;
import javax.servlet.annotation.WebListener;

@WebListener
public class AuthConfig implements ServletContextListener {
    private AuthService authService;
    private SessionService sessionService;
    private AuditService auditService;
    private DatabaseUtils databaseUtils;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext context = sce.getServletContext();

        // Initialize database utilities
        initializeDatabaseUtils();

        // Initialize services
        initializeServices();

        // Register servlets
        registerServlets(context);

        // Register filters
        registerFilters(context);

        // Add services to servlet context for use in JSPs
        context.setAttribute("authService", authService);
        context.setAttribute("sessionService", sessionService);
        context.setAttribute("auditService", auditService);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        // Cleanup resources
        if (databaseUtils != null) {
            databaseUtils.close();
        }
    }

    private void initializeDatabaseUtils() {
        databaseUtils = new DatabaseUtils();
    }

    private void initializeServices() {
        // Initialize services with dependencies
        sessionService = new SessionServiceImpl(databaseUtils);
        auditService = new AuditServiceImpl(databaseUtils);
        authService = new AuthServiceImpl(databaseUtils, sessionService, auditService);
    }

    private void registerServlets(ServletContext context) {
        // Create servlet instances with dependencies
        LoginServlet loginServlet = new LoginServlet(authService, sessionService, auditService);
        SignupServlet signupServlet = new SignupServlet(authService, sessionService, auditService);
        ForgotPasswordServlet forgotPasswordServlet = new ForgotPasswordServlet(authService, sessionService, auditService);
        EmailVerificationServlet emailVerificationServlet = new EmailVerificationServlet(authService, sessionService, auditService);

        // Register servlets
        ServletRegistration.Dynamic loginReg = context.addServlet("loginServlet", loginServlet);
        loginReg.setLoadOnStartup(1);
        loginReg.addMapping("/login");

        ServletRegistration.Dynamic signupReg = context.addServlet("signupServlet", signupServlet);
        signupReg.setLoadOnStartup(1);
        signupReg.addMapping("/signup");

        ServletRegistration.Dynamic forgotPasswordReg = context.addServlet("forgotPasswordServlet", forgotPasswordServlet);
        forgotPasswordReg.setLoadOnStartup(1);
        forgotPasswordReg.addMapping("/forgot-password", "/reset-password");

        ServletRegistration.Dynamic emailVerificationReg = context.addServlet("emailVerificationServlet", emailVerificationServlet);
        emailVerificationReg.setLoadOnStartup(1);
        emailVerificationReg.addMapping("/verify-email");
    }

    private void registerFilters(ServletContext context) {
        // Register authentication filter
        context.addFilter("authenticationFilter", 
            new AuthenticationFilter(authService, sessionService, auditService))
            .addMappingForUrlPatterns(null, true, "/*");

        // Register CSRF filter
        context.addFilter("csrfFilter", 
            new CsrfFilter(sessionService, auditService))
            .addMappingForUrlPatterns(null, true, "/*");

        // Register security headers filter
        context.addFilter("securityHeadersFilter", 
            new SecurityHeadersFilter())
            .addMappingForUrlPatterns(null, true, "/*");

        // Register rate limit filter
        context.addFilter("rateLimitFilter", 
            new RateLimitFilter(auditService))
            .addMappingForUrlPatterns(null, true, "/*");
    }

    // Helper method to get database configuration
    private String getDatabaseUrl() {
        String dbUrl = System.getenv("DB_URL");
        if (dbUrl == null || dbUrl.isEmpty()) {
            dbUrl = "jdbc:postgresql://localhost:5432/yuvi";
        }
        return dbUrl;
    }

    private String getDatabaseUsername() {
        String username = System.getenv("DB_USERNAME");
        if (username == null || username.isEmpty()) {
            username = "postgres";
        }
        return username;
    }

    private String getDatabasePassword() {
        String password = System.getenv("DB_PASSWORD");
        if (password == null || password.isEmpty()) {
            password = "postgres";
        }
        return password;
    }

    // Helper method to get email configuration
    private String getSmtpHost() {
        String host = System.getenv("SMTP_HOST");
        if (host == null || host.isEmpty()) {
            host = "smtp.gmail.com";
        }
        return host;
    }

    private int getSmtpPort() {
        String port = System.getenv("SMTP_PORT");
        if (port == null || port.isEmpty()) {
            return 587;
        }
        return Integer.parseInt(port);
    }

    private String getSmtpUsername() {
        String username = System.getenv("SMTP_USERNAME");
        if (username == null || username.isEmpty()) {
            throw new IllegalStateException("SMTP_USERNAME environment variable is required");
        }
        return username;
    }

    private String getSmtpPassword() {
        String password = System.getenv("SMTP_PASSWORD");
        if (password == null || password.isEmpty()) {
            throw new IllegalStateException("SMTP_PASSWORD environment variable is required");
        }
        return password;
    }

    // Helper method to get security configuration
    private String getJwtSecret() {
        String secret = System.getenv("JWT_SECRET");
        if (secret == null || secret.isEmpty()) {
            throw new IllegalStateException("JWT_SECRET environment variable is required");
        }
        return secret;
    }

    private int getSessionTimeout() {
        String timeout = System.getenv("SESSION_TIMEOUT");
        if (timeout == null || timeout.isEmpty()) {
            return 30; // 30 minutes default
        }
        return Integer.parseInt(timeout);
    }

    private boolean isHttpsOnly() {
        String httpsOnly = System.getenv("HTTPS_ONLY");
        return httpsOnly != null && httpsOnly.equalsIgnoreCase("true");
    }
}
