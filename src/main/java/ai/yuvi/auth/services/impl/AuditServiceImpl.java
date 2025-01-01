package ai.yuvi.auth.services.impl;

import ai.yuvi.auth.models.AuditLog;
import ai.yuvi.auth.models.ChangeLog;
import ai.yuvi.auth.models.UserClick;
import ai.yuvi.auth.services.AuditService;
import ai.yuvi.db.utilities.DatabaseUtils;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.*;

public class AuditServiceImpl implements AuditService {
    private final DatabaseUtils dbUtils;
    private static final int MAX_DETAILS_LENGTH = 4000;

    public AuditServiceImpl(DatabaseUtils dbUtils) {
        this.dbUtils = dbUtils;
    }

    @Override
    public void logAction(Long userId, String action, String entityType, Long entityId, 
                         String details, HttpServletRequest request) {
        String sql = "INSERT INTO audit_logs (user_id, action, entity_type, entity_id, " +
                    "details, ip_address, user_agent, created_at) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        
        dbUtils.update(sql, userId, action, entityType, entityId,
            truncateDetails(details), getClientIp(request),
            request.getHeader("User-Agent"), LocalDateTime.now());
    }

    @Override
    public void logSecurityEvent(Long userId, String action, String details, 
                               HttpServletRequest request) {
        logAction(userId, action, "SECURITY", null, details, request);
    }

    @Override
    public List<AuditLog> getUserAuditLogs(Long userId, LocalDateTime startDate, 
                                         LocalDateTime endDate) {
        String sql = "SELECT * FROM audit_logs WHERE user_id = ? " +
                    "AND created_at BETWEEN ? AND ? ORDER BY created_at DESC";
        return dbUtils.queryForList(sql, 
            new Object[]{userId, startDate, endDate}, this::mapAuditLog);
    }

    @Override
    public List<AuditLog> getEntityAuditLogs(String entityType, Long entityId) {
        String sql = "SELECT * FROM audit_logs WHERE entity_type = ? " +
                    "AND entity_id = ? ORDER BY created_at DESC";
        return dbUtils.queryForList(sql, 
            new Object[]{entityType, entityId}, this::mapAuditLog);
    }

    @Override
    public void logChange(Long userId, String entityType, Long entityId, String action,
                         Map<String, Object> oldValues, Map<String, Object> newValues,
                         String reason, HttpServletRequest request) {
        String sql = "INSERT INTO change_logs (user_id, entity_type, entity_id, action, " +
                    "old_values, new_values, reason, ip_address, user_agent, created_at) " +
                    "VALUES (?, ?, ?, ?, ?::jsonb, ?::jsonb, ?, ?, ?, ?)";
        
        dbUtils.update(sql, userId, entityType, entityId, action,
            toJsonString(oldValues), toJsonString(newValues), reason,
            getClientIp(request), request.getHeader("User-Agent"),
            LocalDateTime.now());
    }

    @Override
    public List<ChangeLog> getEntityChangeLogs(String entityType, Long entityId) {
        String sql = "SELECT * FROM change_logs WHERE entity_type = ? " +
                    "AND entity_id = ? ORDER BY created_at DESC";
        return dbUtils.queryForList(sql, 
            new Object[]{entityType, entityId}, this::mapChangeLog);
    }

    @Override
    public List<ChangeLog> getUserChangeLogs(Long userId, LocalDateTime startDate,
                                           LocalDateTime endDate) {
        String sql = "SELECT * FROM change_logs WHERE user_id = ? " +
                    "AND created_at BETWEEN ? AND ? ORDER BY created_at DESC";
        return dbUtils.queryForList(sql, 
            new Object[]{userId, startDate, endDate}, this::mapChangeLog);
    }

    @Override
    public Map<String, Object> compareVersions(String entityType, Long entityId,
                                             Long version1, Long version2) {
        String sql = "SELECT * FROM change_logs WHERE entity_type = ? " +
                    "AND entity_id = ? AND id IN (?, ?) ORDER BY created_at ASC";
        List<ChangeLog> changes = dbUtils.queryForList(sql,
            new Object[]{entityType, entityId, version1, version2},
            this::mapChangeLog);

        if (changes.size() != 2) {
            return Collections.emptyMap();
        }

        Map<String, Object> comparison = new HashMap<>();
        comparison.put("oldVersion", changes.get(0));
        comparison.put("newVersion", changes.get(1));
        comparison.put("differences", compareValues(
            changes.get(0).getNewValues(),
            changes.get(1).getNewValues()
        ));

        return comparison;
    }

    @Override
    public void trackClick(Long userId, String sessionId, HttpServletRequest request) {
        String sql = "INSERT INTO user_clicks (user_id, session_id, page_url, " +
                    "ip_address, user_agent, created_at) " +
                    "VALUES (?, ?, ?, ?, ?, ?)";
        
        dbUtils.update(sql, userId, sessionId, request.getRequestURI(),
            getClientIp(request), request.getHeader("User-Agent"),
            LocalDateTime.now());
    }

    @Override
    public void trackPageView(Long userId, String sessionId, String pageUrl,
                            String referrer, HttpServletRequest request) {
        String sql = "INSERT INTO user_clicks (user_id, session_id, page_url, " +
                    "referrer, event_type, ip_address, user_agent, created_at) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        
        dbUtils.update(sql, userId, sessionId, pageUrl, referrer,
            UserClick.EVENT_NAVIGATION, getClientIp(request),
            request.getHeader("User-Agent"), LocalDateTime.now());
    }

    @Override
    public void trackEvent(Long userId, String sessionId, String eventType,
                         String elementId, String elementType, String elementText,
                         HttpServletRequest request) {
        String sql = "INSERT INTO user_clicks (user_id, session_id, page_url, " +
                    "element_id, element_type, element_text, event_type, " +
                    "ip_address, user_agent, created_at) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        dbUtils.update(sql, userId, sessionId, request.getRequestURI(),
            elementId, elementType, elementText, eventType,
            getClientIp(request), request.getHeader("User-Agent"),
            LocalDateTime.now());
    }

    @Override
    public List<UserClick> getUserClicks(Long userId, LocalDateTime startDate,
                                       LocalDateTime endDate) {
        String sql = "SELECT * FROM user_clicks WHERE user_id = ? " +
                    "AND created_at BETWEEN ? AND ? ORDER BY created_at DESC";
        return dbUtils.queryForList(sql,
            new Object[]{userId, startDate, endDate}, this::mapUserClick);
    }

    @Override
    public List<UserClick> getSessionClicks(String sessionId) {
        String sql = "SELECT * FROM user_clicks WHERE session_id = ? " +
                    "ORDER BY created_at ASC";
        return dbUtils.queryForList(sql,
            new Object[]{sessionId}, this::mapUserClick);
    }

    @Override
    public Map<String, Long> getPopularPages(LocalDateTime startDate,
                                           LocalDateTime endDate) {
        String sql = "SELECT page_url, COUNT(*) as count FROM user_clicks " +
                    "WHERE created_at BETWEEN ? AND ? " +
                    "GROUP BY page_url ORDER BY count DESC";
        List<Map<String, Object>> results = dbUtils.queryForList(sql,
            new Object[]{startDate, endDate});

        Map<String, Long> popularPages = new HashMap<>();
        for (Map<String, Object> row : results) {
            popularPages.put(
                (String) row.get("page_url"),
                ((Number) row.get("count")).longValue()
            );
        }
        return popularPages;
    }

    @Override
    public Map<String, Long> getPopularEvents(LocalDateTime startDate,
                                            LocalDateTime endDate) {
        String sql = "SELECT event_type, COUNT(*) as count FROM user_clicks " +
                    "WHERE created_at BETWEEN ? AND ? AND event_type IS NOT NULL " +
                    "GROUP BY event_type ORDER BY count DESC";
        List<Map<String, Object>> results = dbUtils.queryForList(sql,
            new Object[]{startDate, endDate});

        Map<String, Long> popularEvents = new HashMap<>();
        for (Map<String, Object> row : results) {
            popularEvents.put(
                (String) row.get("event_type"),
                ((Number) row.get("count")).longValue()
            );
        }
        return popularEvents;
    }

    @Override
    public Map<String, Map<String, Long>> getUserActivitySummary(Long userId,
                                                               LocalDateTime startDate,
                                                               LocalDateTime endDate) {
        Map<String, Map<String, Long>> summary = new HashMap<>();

        // Page views
        summary.put("pageViews", getPopularPagesForUser(userId, startDate, endDate));

        // Events
        summary.put("events", getPopularEventsForUser(userId, startDate, endDate));

        // Actions
        summary.put("actions", getPopularActionsForUser(userId, startDate, endDate));

        return summary;
    }

    @Override
    public void cleanupOldAuditLogs(LocalDateTime before) {
        String sql = "DELETE FROM audit_logs WHERE created_at < ?";
        dbUtils.update(sql, before);
    }

    @Override
    public void cleanupOldChangeLogs(LocalDateTime before) {
        String sql = "DELETE FROM change_logs WHERE created_at < ?";
        dbUtils.update(sql, before);
    }

    @Override
    public void cleanupOldClickLogs(LocalDateTime before) {
        String sql = "DELETE FROM user_clicks WHERE created_at < ?";
        dbUtils.update(sql, before);
    }

    // Helper methods
    private String truncateDetails(String details) {
        if (details == null) {
            return null;
        }
        return details.length() > MAX_DETAILS_LENGTH
            ? details.substring(0, MAX_DETAILS_LENGTH)
            : details;
    }

    private String toJsonString(Map<String, Object> map) {
        if (map == null) {
            return null;
        }
        try {
            return new com.fasterxml.jackson.databind.ObjectMapper()
                .writeValueAsString(map);
        } catch (Exception e) {
            throw new RuntimeException("Error converting map to JSON", e);
        }
    }

    private Map<String, Object> fromJsonString(String json) {
        if (json == null) {
            return Collections.emptyMap();
        }
        try {
            return new com.fasterxml.jackson.databind.ObjectMapper()
                .readValue(json, Map.class);
        } catch (Exception e) {
            throw new RuntimeException("Error parsing JSON", e);
        }
    }

    private Map<String, Object> compareValues(Map<String, Object> oldValues,
                                            Map<String, Object> newValues) {
        Map<String, Object> differences = new HashMap<>();
        Set<String> allKeys = new HashSet<>();
        allKeys.addAll(oldValues.keySet());
        allKeys.addAll(newValues.keySet());

        for (String key : allKeys) {
            Object oldValue = oldValues.get(key);
            Object newValue = newValues.get(key);
            if (!Objects.equals(oldValue, newValue)) {
                Map<String, Object> diff = new HashMap<>();
                diff.put("old", oldValue);
                diff.put("new", newValue);
                differences.put(key, diff);
            }
        }
        return differences;
    }

    private Map<String, Long> getPopularPagesForUser(Long userId,
                                                    LocalDateTime startDate,
                                                    LocalDateTime endDate) {
        String sql = "SELECT page_url, COUNT(*) as count FROM user_clicks " +
                    "WHERE user_id = ? AND created_at BETWEEN ? AND ? " +
                    "GROUP BY page_url ORDER BY count DESC";
        List<Map<String, Object>> results = dbUtils.queryForList(sql,
            new Object[]{userId, startDate, endDate});

        Map<String, Long> popularPages = new HashMap<>();
        for (Map<String, Object> row : results) {
            popularPages.put(
                (String) row.get("page_url"),
                ((Number) row.get("count")).longValue()
            );
        }
        return popularPages;
    }

    private Map<String, Long> getPopularEventsForUser(Long userId,
                                                     LocalDateTime startDate,
                                                     LocalDateTime endDate) {
        String sql = "SELECT event_type, COUNT(*) as count FROM user_clicks " +
                    "WHERE user_id = ? AND created_at BETWEEN ? AND ? " +
                    "AND event_type IS NOT NULL " +
                    "GROUP BY event_type ORDER BY count DESC";
        List<Map<String, Object>> results = dbUtils.queryForList(sql,
            new Object[]{userId, startDate, endDate});

        Map<String, Long> popularEvents = new HashMap<>();
        for (Map<String, Object> row : results) {
            popularEvents.put(
                (String) row.get("event_type"),
                ((Number) row.get("count")).longValue()
            );
        }
        return popularEvents;
    }

    private Map<String, Long> getPopularActionsForUser(Long userId,
                                                      LocalDateTime startDate,
                                                      LocalDateTime endDate) {
        String sql = "SELECT action, COUNT(*) as count FROM audit_logs " +
                    "WHERE user_id = ? AND created_at BETWEEN ? AND ? " +
                    "GROUP BY action ORDER BY count DESC";
        List<Map<String, Object>> results = dbUtils.queryForList(sql,
            new Object[]{userId, startDate, endDate});

        Map<String, Long> popularActions = new HashMap<>();
        for (Map<String, Object> row : results) {
            popularActions.put(
                (String) row.get("action"),
                ((Number) row.get("count")).longValue()
            );
        }
        return popularActions;
    }

    private AuditLog mapAuditLog(java.sql.ResultSet rs, int rowNum)
            throws java.sql.SQLException {
        AuditLog log = new AuditLog();
        log.setId(rs.getLong("id"));
        log.setUserId(rs.getLong("user_id"));
        log.setAction(rs.getString("action"));
        log.setEntityType(rs.getString("entity_type"));
        log.setEntityId(rs.getLong("entity_id"));
        log.setDetails(rs.getString("details"));
        log.setIpAddress(rs.getString("ip_address"));
        log.setUserAgent(rs.getString("user_agent"));
        log.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
        return log;
    }

    private ChangeLog mapChangeLog(java.sql.ResultSet rs, int rowNum)
            throws java.sql.SQLException {
        ChangeLog log = new ChangeLog();
        log.setId(rs.getLong("id"));
        log.setUserId(rs.getLong("user_id"));
        log.setEntityType(rs.getString("entity_type"));
        log.setEntityId(rs.getLong("entity_id"));
        log.setAction(rs.getString("action"));
        log.setOldValues(fromJsonString(rs.getString("old_values")));
        log.setNewValues(fromJsonString(rs.getString("new_values")));
        log.setReason(rs.getString("reason"));
        log.setIpAddress(rs.getString("ip_address"));
        log.setUserAgent(rs.getString("user_agent"));
        log.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
        return log;
    }

    private UserClick mapUserClick(java.sql.ResultSet rs, int rowNum)
            throws java.sql.SQLException {
        UserClick click = new UserClick();
        click.setId(rs.getLong("id"));
        click.setUserId(rs.getLong("user_id"));
        click.setSessionId(rs.getString("session_id"));
        click.setPageUrl(rs.getString("page_url"));
        click.setElementId(rs.getString("element_id"));
        click.setElementType(rs.getString("element_type"));
        click.setElementText(rs.getString("element_text"));
        click.setEventType(rs.getString("event_type"));
        click.setReferrer(rs.getString("referrer"));
        click.setIpAddress(rs.getString("ip_address"));
        click.setUserAgent(rs.getString("user_agent"));
        click.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
        return click;
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
