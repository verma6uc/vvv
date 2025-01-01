package ai.yuvi.auth.models;

import java.time.LocalDateTime;
import java.util.Map;

public class ChangeLog {
    private Long id;
    private Long userId;
    private String entityType;
    private Long entityId;
    private String action;
    private Map<String, Object> oldValues;
    private Map<String, Object> newValues;
    private String reason;
    private LocalDateTime createdAt;
    private String ipAddress;
    private String userAgent;

    // Common action types
    public static final String ACTION_CREATE = "CREATE";
    public static final String ACTION_UPDATE = "UPDATE";
    public static final String ACTION_DELETE = "DELETE";
    public static final String ACTION_RESTORE = "RESTORE";
    public static final String ACTION_ARCHIVE = "ARCHIVE";

    // Common entity types
    public static final String ENTITY_USER = "USER";
    public static final String ENTITY_COMPANY = "COMPANY";
    public static final String ENTITY_ROLE = "ROLE";
    public static final String ENTITY_PERMISSION = "PERMISSION";
    public static final String ENTITY_SESSION = "SESSION";

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getEntityType() {
        return entityType;
    }

    public void setEntityType(String entityType) {
        this.entityType = entityType;
    }

    public Long getEntityId() {
        return entityId;
    }

    public void setEntityId(Long entityId) {
        this.entityId = entityId;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public Map<String, Object> getOldValues() {
        return oldValues;
    }

    public void setOldValues(Map<String, Object> oldValues) {
        this.oldValues = oldValues;
    }

    public Map<String, Object> getNewValues() {
        return newValues;
    }

    public void setNewValues(Map<String, Object> newValues) {
        this.newValues = newValues;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    // Builder pattern for easy creation
    public static class Builder {
        private ChangeLog log;

        public Builder() {
            log = new ChangeLog();
            log.setCreatedAt(LocalDateTime.now());
        }

        public Builder userId(Long userId) {
            log.setUserId(userId);
            return this;
        }

        public Builder entity(String type, Long id) {
            log.setEntityType(type);
            log.setEntityId(id);
            return this;
        }

        public Builder action(String action) {
            log.setAction(action);
            return this;
        }

        public Builder changes(Map<String, Object> oldValues, Map<String, Object> newValues) {
            log.setOldValues(oldValues);
            log.setNewValues(newValues);
            return this;
        }

        public Builder reason(String reason) {
            log.setReason(reason);
            return this;
        }

        public Builder clientInfo(String ipAddress, String userAgent) {
            log.setIpAddress(ipAddress);
            log.setUserAgent(userAgent);
            return this;
        }

        public ChangeLog build() {
            return log;
        }
    }
}
