package ai.yuvi.auth.services;

import ai.yuvi.auth.models.User;
import ai.yuvi.auth.models.Session;
import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

public interface AuthService {
    // User authentication
    Session login(String email, String password, HttpServletRequest request) throws AuthenticationException;
    void logout(String sessionToken) throws AuthenticationException;
    Optional<User> getCurrentUser(String sessionToken);
    boolean validateSession(String sessionToken);

    // User registration
    User register(String email, String password, String firstName, String lastName, HttpServletRequest request) throws RegistrationException;
    void sendVerificationEmail(User user) throws EmailException;
    boolean verifyEmail(String token) throws VerificationException;

    // Password management
    void initiatePasswordReset(String email) throws EmailException;
    boolean validatePasswordResetToken(String token);
    void resetPassword(String token, String newPassword) throws PasswordResetException;
    void changePassword(Long userId, String oldPassword, String newPassword) throws PasswordChangeException;

    // Session management
    void invalidateAllSessions(Long userId);
    void cleanupExpiredSessions();

    // Security checks
    boolean isEmailAvailable(String email);
    boolean isPasswordStrong(String password);
    void trackLoginAttempt(String email, boolean success, HttpServletRequest request);
    boolean isAccountLocked(String email);

    // Custom exceptions
    class AuthenticationException extends Exception {
        public AuthenticationException(String message) {
            super(message);
        }
    }

    class RegistrationException extends Exception {
        public RegistrationException(String message) {
            super(message);
        }
    }

    class EmailException extends Exception {
        public EmailException(String message) {
            super(message);
        }
    }

    class VerificationException extends Exception {
        public VerificationException(String message) {
            super(message);
        }
    }

    class PasswordResetException extends Exception {
        public PasswordResetException(String message) {
            super(message);
        }
    }

    class PasswordChangeException extends Exception {
        public PasswordChangeException(String message) {
            super(message);
        }
    }
}
