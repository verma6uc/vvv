package ai.yuvi.auth.services;

import ai.yuvi.auth.models.AuditLog;
import ai.yuvi.auth.models.ChangeLog;
import ai.yuvi.auth.models.UserClick;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public interface AuditService {
    // Audit logging
    void logAction(Long userId, String action, String entityType, Long entityId, String details, HttpServletRequest request);
    void logSecurityEvent(Long userId, String action, String details, HttpServletRequest request);
    List<AuditLog> getUserAuditLogs(Long userId, LocalDateTime startDate, LocalDateTime endDate);
    List<AuditLog> getEntityAuditLogs(String entityType, Long entityId);

    // Change tracking
    void logChange(Long userId, String entityType, Long entityId, String action, 
                  Map<String, Object> oldValues, Map<String, Object> newValues, 
                  String reason, HttpServletRequest request);
    List<ChangeLog> getEntityChangeLogs(String entityType, Long entityId);
    List<ChangeLog> getUserChangeLogs(Long userId, LocalDateTime startDate, LocalDateTime endDate);
    Map<String, Object> compareVersions(String entityType, Long entityId, Long version1, Long version2);

    // User click tracking
    void trackClick(Long userId, String sessionId, HttpServletRequest request);
    void trackPageView(Long userId, String sessionId, String pageUrl, String referrer, HttpServletRequest request);
    void trackEvent(Long userId, String sessionId, String eventType, String elementId, 
                   String elementType, String elementText, HttpServletRequest request);
    List<UserClick> getUserClicks(Long userId, LocalDateTime startDate, LocalDateTime endDate);
    List<UserClick> getSessionClicks(String sessionId);

    // Analytics
    Map<String, Long> getPopularPages(LocalDateTime startDate, LocalDateTime endDate);
    Map<String, Long> getPopularEvents(LocalDateTime startDate, LocalDateTime endDate);
    Map<String, Map<String, Long>> getUserActivitySummary(Long userId, LocalDateTime startDate, LocalDateTime endDate);

    // Data retention
    void cleanupOldAuditLogs(LocalDateTime before);
    void cleanupOldChangeLogs(LocalDateTime before);
    void cleanupOldClickLogs(LocalDateTime before);

    // Custom exceptions
    class AuditException extends Exception {
        public AuditException(String message) {
            super(message);
        }
    }

    class ChangeTrackingException extends Exception {
        public ChangeTrackingException(String message) {
            super(message);
        }
    }

    class ClickTrackingException extends Exception {
        public ClickTrackingException(String message) {
            super(message);
        }
    }

    // Helper methods
    String getClientIp(HttpServletRequest request);
    String getUserAgent(HttpServletRequest request);
    Map<String, Object> extractRequestMetadata(HttpServletRequest request);
}
