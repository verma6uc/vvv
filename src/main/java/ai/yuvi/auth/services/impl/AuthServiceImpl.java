package ai.yuvi.auth.services.impl;

import ai.yuvi.auth.models.User;
import ai.yuvi.auth.models.Session;
import ai.yuvi.auth.services.AuthService;
import ai.yuvi.auth.services.SessionService;
import ai.yuvi.auth.services.AuditService;
import ai.yuvi.db.utilities.DatabaseUtils;

import javax.servlet.http.HttpServletRequest;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.Optional;
import java.util.regex.Pattern;

public class AuthServiceImpl implements AuthService {
    private final DatabaseUtils dbUtils;
    private final SessionService sessionService;
    private final AuditService auditService;
    private static final int MAX_LOGIN_ATTEMPTS = 5;
    private static final int LOCKOUT_DURATION_MINUTES = 30;
    private static final Pattern PASSWORD_PATTERN = Pattern.compile(
        "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$"
    );

    public AuthServiceImpl(DatabaseUtils dbUtils, SessionService sessionService, AuditService auditService) {
        this.dbUtils = dbUtils;
        this.sessionService = sessionService;
        this.auditService = auditService;
    }

    @Override
    public Session login(String email, String password, HttpServletRequest request) throws AuthenticationException {
        try {
            if (isAccountLocked(email)) {
                auditService.logSecurityEvent(null, "LOGIN_ATTEMPT_LOCKED", 
                    "Login attempt on locked account: " + email, request);
                throw new AuthenticationException("Account is temporarily locked. Please try again later.");
            }

            Optional<User> userOpt = getUserByEmail(email);
            if (!userOpt.isPresent()) {
                trackLoginAttempt(email, false, request);
                throw new AuthenticationException("Invalid email or password");
            }

            User user = userOpt.get();
            if (!verifyPassword(password, user.getPasswordHash())) {
                trackLoginAttempt(email, false, request);
                throw new AuthenticationException("Invalid email or password");
            }

            if (!user.isEmailVerified()) {
                throw new AuthenticationException("Please verify your email before logging in");
            }

            // Create new session
            Session session = sessionService.createSession(user.getId(), request);
            
            // Update last login
            user.setLastLoginAt(LocalDateTime.now());
            updateUser(user);

            // Log successful login
            trackLoginAttempt(email, true, request);
            auditService.logAction(user.getId(), "LOGIN", "USER", user.getId(), 
                "User logged in successfully", request);

            return session;
        } catch (Exception e) {
            throw new AuthenticationException("Login failed: " + e.getMessage());
        }
    }

    @Override
    public void logout(String sessionToken) throws AuthenticationException {
        try {
            Optional<Session> sessionOpt = sessionService.getSession(sessionToken);
            if (!sessionOpt.isPresent()) {
                throw new AuthenticationException("Invalid session");
            }

            Session session = sessionOpt.get();
            sessionService.deleteSession(sessionToken);
            auditService.logAction(session.getUserId(), "LOGOUT", "USER", 
                session.getUserId(), "User logged out", null);
        } catch (Exception e) {
            throw new AuthenticationException("Logout failed: " + e.getMessage());
        }
    }

    @Override
    public Optional<User> getCurrentUser(String sessionToken) {
        try {
            Optional<Session> sessionOpt = sessionService.getSession(sessionToken);
            if (!sessionOpt.isPresent() || !sessionService.isValidSession(sessionToken)) {
                return Optional.empty();
            }

            return getUserById(sessionOpt.get().getUserId());
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public boolean validateSession(String sessionToken) {
        return sessionService.isValidSession(sessionToken);
    }

    @Override
    public User register(String email, String password, String firstName, String lastName, 
                        HttpServletRequest request) throws RegistrationException {
        try {
            // Validate input
            if (!isEmailAvailable(email)) {
                throw new RegistrationException("Email is already registered");
            }
            if (!isPasswordStrong(password)) {
                throw new RegistrationException("Password does not meet security requirements");
            }

            // Create user
            User user = new User();
            user.setEmail(email);
            user.setPasswordHash(hashPassword(password));
            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setEmailVerified(false);
            user.setCreatedAt(LocalDateTime.now());
            user.setUpdatedAt(LocalDateTime.now());

            // Save user
            Long userId = createUser(user);
            user.setId(userId);

            // Send verification email
            sendVerificationEmail(user);

            // Log registration
            auditService.logAction(userId, "REGISTER", "USER", userId, 
                "New user registration", request);

            return user;
        } catch (Exception e) {
            throw new RegistrationException("Registration failed: " + e.getMessage());
        }
    }

    @Override
    public void sendVerificationEmail(User user) throws EmailException {
        // Implementation would handle email sending logic
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public boolean verifyEmail(String token) throws VerificationException {
        // Implementation would handle email verification logic
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public void initiatePasswordReset(String email) throws EmailException {
        // Implementation would handle password reset initiation logic
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public boolean validatePasswordResetToken(String token) {
        // Implementation would handle password reset token validation logic
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public void resetPassword(String token, String newPassword) throws PasswordResetException {
        // Implementation would handle password reset logic
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public void changePassword(Long userId, String oldPassword, String newPassword) 
            throws PasswordChangeException {
        // Implementation would handle password change logic
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public void invalidateAllSessions(Long userId) {
        sessionService.invalidateUserSessions(userId);
    }

    @Override
    public void cleanupExpiredSessions() {
        sessionService.cleanupExpiredSessions();
    }

    @Override
    public boolean isEmailAvailable(String email) {
        String sql = "SELECT COUNT(*) FROM users WHERE email = ?";
        return dbUtils.queryForObject(sql, new Object[]{email}, Integer.class) == 0;
    }

    @Override
    public boolean isPasswordStrong(String password) {
        return PASSWORD_PATTERN.matcher(password).matches();
    }

    @Override
    public void trackLoginAttempt(String email, boolean success, HttpServletRequest request) {
        String sql = "INSERT INTO user_login_attempts (email, success, ip_address, user_agent, created_at) " +
                    "VALUES (?, ?, ?, ?, ?)";
        dbUtils.update(sql, email, success, getClientIp(request), 
            request.getHeader("User-Agent"), LocalDateTime.now());
    }

    @Override
    public boolean isAccountLocked(String email) {
        String sql = "SELECT COUNT(*) FROM user_login_attempts " +
                    "WHERE email = ? AND success = false AND " +
                    "created_at > ? AND created_at <= ?";
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime lockoutStart = now.minusMinutes(LOCKOUT_DURATION_MINUTES);
        int attempts = dbUtils.queryForObject(sql, 
            new Object[]{email, lockoutStart, now}, Integer.class);
        return attempts >= MAX_LOGIN_ATTEMPTS;
    }

    // Helper methods
    private Optional<User> getUserByEmail(String email) {
        String sql = "SELECT * FROM users WHERE email = ?";
        return dbUtils.queryForObject(sql, new Object[]{email}, this::mapUser);
    }

    private Optional<User> getUserById(Long id) {
        String sql = "SELECT * FROM users WHERE id = ?";
        return dbUtils.queryForObject(sql, new Object[]{id}, this::mapUser);
    }

    private Long createUser(User user) {
        String sql = "INSERT INTO users (email, password_hash, first_name, last_name, " +
                    "email_verified, created_at, updated_at) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?) RETURNING id";
        return dbUtils.queryForObject(sql, 
            new Object[]{
                user.getEmail(), user.getPasswordHash(), user.getFirstName(),
                user.getLastName(), user.isEmailVerified(), user.getCreatedAt(),
                user.getUpdatedAt()
            }, Long.class);
    }

    private void updateUser(User user) {
        String sql = "UPDATE users SET email = ?, password_hash = ?, first_name = ?, " +
                    "last_name = ?, email_verified = ?, last_login_at = ?, " +
                    "updated_at = ? WHERE id = ?";
        dbUtils.update(sql, 
            user.getEmail(), user.getPasswordHash(), user.getFirstName(),
            user.getLastName(), user.isEmailVerified(), user.getLastLoginAt(),
            LocalDateTime.now(), user.getId());
    }

    private User mapUser(java.sql.ResultSet rs, int rowNum) throws java.sql.SQLException {
        User user = new User();
        user.setId(rs.getLong("id"));
        user.setEmail(rs.getString("email"));
        user.setPasswordHash(rs.getString("password_hash"));
        user.setFirstName(rs.getString("first_name"));
        user.setLastName(rs.getString("last_name"));
        user.setEmailVerified(rs.getBoolean("email_verified"));
        user.setLastLoginAt(rs.getTimestamp("last_login_at").toLocalDateTime());
        user.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
        user.setUpdatedAt(rs.getTimestamp("updated_at").toLocalDateTime());
        return user;
    }

    private String hashPassword(String password) {
        try {
            byte[] salt = new byte[16];
            new SecureRandom().nextBytes(salt);
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            md.update(salt);
            byte[] hashedPassword = md.digest(password.getBytes());
            return Base64.getEncoder().encodeToString(hashedPassword) + ":" +
                   Base64.getEncoder().encodeToString(salt);
        } catch (Exception e) {
            throw new RuntimeException("Error hashing password", e);
        }
    }

    private boolean verifyPassword(String password, String storedHash) {
        try {
            String[] parts = storedHash.split(":");
            byte[] hash = Base64.getDecoder().decode(parts[0]);
            byte[] salt = Base64.getDecoder().decode(parts[1]);
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            md.update(salt);
            byte[] testHash = md.digest(password.getBytes());
            return MessageDigest.isEqual(hash, testHash);
        } catch (Exception e) {
            throw new RuntimeException("Error verifying password", e);
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
        return ip;
    }
}
