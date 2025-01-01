package ai.yuvi.auth.services;

import ai.yuvi.auth.models.Session;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface SessionService {
    // Session CRUD operations
    Session createSession(Long userId, HttpServletRequest request);
    Optional<Session> getSession(String token);
    void updateSession(Session session);
    void deleteSession(String token);

    // Session validation and management
    boolean isValidSession(String token);
    boolean isExpiredSession(Session session);
    void extendSession(String token);
    void touchSession(String token);

    // User session management
    List<Session> getUserActiveSessions(Long userId);
    void invalidateUserSessions(Long userId);
    void invalidateUserSessionsExcept(Long userId, String currentSessionToken);
    boolean hasActiveSession(Long userId);

    // Session cleanup
    void cleanupExpiredSessions();
    void cleanupInactiveSessions(LocalDateTime lastAccessedBefore);
    void cleanupAllSessionsForUser(Long userId);

    // Session information
    String generateSessionToken();
    String extractSessionToken(HttpServletRequest request);
    Map<String, Object> getSessionMetadata(String token);

    // Session security
    boolean validateSessionOrigin(String token, HttpServletRequest request);
    void updateSessionSecurity(String token, HttpServletRequest request);
    boolean isSessionCompromised(String token, HttpServletRequest request);

    // Session statistics
    int getActiveSessionCount();
    int getUserSessionCount(Long userId);
    Map<String, Object> getSessionStats(LocalDateTime startDate, LocalDateTime endDate);

    // Custom exceptions
    class SessionException extends Exception {
        public SessionException(String message) {
            super(message);
        }
    }

    class SessionValidationException extends Exception {
        public SessionValidationException(String message) {
            super(message);
        }
    }

    class SessionCreationException extends Exception {
        public SessionCreationException(String message) {
            super(message);
        }
    }

    // Helper methods
    String getClientIp(HttpServletRequest request);
    String getUserAgent(HttpServletRequest request);
    Map<String, Object> extractRequestMetadata(HttpServletRequest request);
}
