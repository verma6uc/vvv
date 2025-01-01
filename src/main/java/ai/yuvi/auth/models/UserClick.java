package ai.yuvi.auth.models;

import java.time.LocalDateTime;

public class UserClick {
    private Long id;
    private Long userId;
    private String pageUrl;
    private String elementId;
    private String elementType;
    private String elementText;
    private String ipAddress;
    private String userAgent;
    private LocalDateTime createdAt;
    private String sessionId;
    private String referrer;
    private String eventType;

    // Common event types
    public static final String EVENT_CLICK = "CLICK";
    public static final String EVENT_SUBMIT = "SUBMIT";
    public static final String EVENT_NAVIGATION = "NAVIGATION";
    public static final String EVENT_SEARCH = "SEARCH";
    public static final String EVENT_FILTER = "FILTER";

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

    public String getPageUrl() {
        return pageUrl;
    }

    public void setPageUrl(String pageUrl) {
        this.pageUrl = pageUrl;
    }

    public String getElementId() {
        return elementId;
    }

    public void setElementId(String elementId) {
        this.elementId = elementId;
    }

    public String getElementType() {
        return elementType;
    }

    public void setElementType(String elementType) {
        this.elementType = elementType;
    }

    public String getElementText() {
        return elementText;
    }

    public void setElementText(String elementText) {
        this.elementText = elementText;
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

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getReferrer() {
        return referrer;
    }

    public void setReferrer(String referrer) {
        this.referrer = referrer;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    // Builder pattern for easy creation
    public static class Builder {
        private UserClick click;

        public Builder() {
            click = new UserClick();
            click.setCreatedAt(LocalDateTime.now());
        }

        public Builder userId(Long userId) {
            click.setUserId(userId);
            return this;
        }

        public Builder pageUrl(String pageUrl) {
            click.setPageUrl(pageUrl);
            return this;
        }

        public Builder element(String id, String type, String text) {
            click.setElementId(id);
            click.setElementType(type);
            click.setElementText(text);
            return this;
        }

        public Builder clientInfo(String ipAddress, String userAgent) {
            click.setIpAddress(ipAddress);
            click.setUserAgent(userAgent);
            return this;
        }

        public Builder sessionId(String sessionId) {
            click.setSessionId(sessionId);
            return this;
        }

        public Builder referrer(String referrer) {
            click.setReferrer(referrer);
            return this;
        }

        public Builder eventType(String eventType) {
            click.setEventType(eventType);
            return this;
        }

        public UserClick build() {
            return click;
        }
    }
}
