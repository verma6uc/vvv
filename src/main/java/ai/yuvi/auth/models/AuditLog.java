package ai.yuvi.auth.models;

import java.time.LocalDateTime;

public class AuditLog {
    private Long id;
    private Long userId;
    private String action;
    private String entityType;
    private Long entityId;
    private String details;
    private String ipAddress;
    private String userAgent;
    private LocalDateTime createdAt;

    // Action types
    public static final String ACTION_LOGIN = "LOGIN";
    public static final String ACTION_LOGOUT = "LOGOUT";
    public static final String ACTION_SIGNUP = "SIGNUP";
    public static final String ACTION_PASSWORD_RESET = "PASSWORD_RESET";
    public static final String ACTION_EMAIL_VERIFICATION = "EMAIL_VERIFICATION";
    public static final String ACTION_PROFILE_UPDATE = "PROFILE_UPDATE";
    public static final String ACTION_ROLE_CHANGE = "ROLE_CHANGE";

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

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
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

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    // Builder pattern for easy creation
    public static class Builder {
        private AuditLog log;

        public Builder() {
            log = new AuditLog();
            log.setCreatedAt(LocalDateTime.now());
        }

        public Builder userId(Long userId) {
            log.setUserId(userId);
            return this;
        }

        public Builder action(String action) {
            log.setAction(action);
            return this;
        }

        public Builder entityType(String entityType) {
            log.setEntityType(entityType);
            return this;
        }

        public Builder entityId(Long entityId) {
            log.setEntityId(entityId);
            return this;
        }

        public Builder details(String details) {
            log.setDetails(details);
            return this;
        }

        public Builder ipAddress(String ipAddress) {
            log.setIpAddress(ipAddress);
            return this;
        }

        public Builder userAgent(String userAgent) {
            log.setUserAgent(userAgent);
            return this;
        }

        public AuditLog build() {
            return log;
        }
    }
}
