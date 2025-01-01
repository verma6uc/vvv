package ai.yuvi.auth.services.impl;

import ai.yuvi.auth.models.Session;
import ai.yuvi.auth.services.SessionService;
import ai.yuvi.db.utilities.DatabaseUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Cookie;
import java.time.LocalDateTime;
import java.util.*;
import java.security.SecureRandom;

public class SessionServiceImpl implements SessionService {
    private final DatabaseUtils dbUtils;
    private static final int SESSION_TIMEOUT_HOURS = 24;
    private static final int SESSION_TOKEN_LENGTH = 32;
    private static final String SESSION_COOKIE_NAME = "YUVI_SESSION";
    private static final SecureRandom SECURE_RANDOM = new SecureRandom();

    public SessionServiceImpl(DatabaseUtils dbUtils) {
        this.dbUtils = dbUtils;
    }

    @Override
    public Session createSession(Long userId, HttpServletRequest request) {
        Session session = new Session();
        session.setUserId(userId);
        session.setToken(generateSessionToken());
        session.setIpAddress(getClientIp(request));
        session.setUserAgent(request.getHeader("User-Agent"));
        session.setCreatedAt(LocalDateTime.now());
        session.setLastAccessedAt(LocalDateTime.now());
        session.setExpiresAt(LocalDateTime.now().plusHours(SESSION_TIMEOUT_HOURS));
        session.setActive(true);

        String sql = "INSERT INTO sessions (user_id, token, ip_address, user_agent, " +
                    "created_at, last_accessed_at, expires_at, is_active) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?) RETURNING id";
        
        Long sessionId = dbUtils.queryForObject(sql,
            new Object[]{
                session.getUserId(), session.getToken(), session.getIpAddress(),
                session.getUserAgent(), session.getCreatedAt(), session.getLastAccessedAt(),
                session.getExpiresAt(), session.isActive()
            }, Long.class);
        
        session.setId(sessionId);
        return session;
    }

    @Override
    public Optional<Session> getSession(String token) {
        String sql = "SELECT * FROM sessions WHERE token = ?";
        return dbUtils.queryForObject(sql, new Object[]{token}, this::mapSession);
    }

    @Override
    public void updateSession(Session session) {
        String sql = "UPDATE sessions SET user_id = ?, ip_address = ?, user_agent = ?, " +
                    "last_accessed_at = ?, expires_at = ?, is_active = ? " +
                    "WHERE token = ?";
        dbUtils.update(sql,
            session.getUserId(), session.getIpAddress(), session.getUserAgent(),
            session.getLastAccessedAt(), session.getExpiresAt(), session.isActive(),
            session.getToken());
    }

    @Override
    public void deleteSession(String token) {
        String sql = "UPDATE sessions SET is_active = false WHERE token = ?";
        dbUtils.update(sql, token);
    }

    @Override
    public boolean isValidSession(String token) {
        Optional<Session> sessionOpt = getSession(token);
        if (!sessionOpt.isPresent()) {
            return false;
        }

        Session session = sessionOpt.get();
        return session.isActive() && !isExpiredSession(session);
    }

    @Override
    public boolean isExpiredSession(Session session) {
        return LocalDateTime.now().isAfter(session.getExpiresAt());
    }

    @Override
    public void extendSession(String token) {
        Optional<Session> sessionOpt = getSession(token);
        if (sessionOpt.isPresent()) {
            Session session = sessionOpt.get();
            session.setExpiresAt(LocalDateTime.now().plusHours(SESSION_TIMEOUT_HOURS));
            updateSession(session);
        }
    }

    @Override
    public void touchSession(String token) {
        String sql = "UPDATE sessions SET last_accessed_at = ? WHERE token = ?";
        dbUtils.update(sql, LocalDateTime.now(), token);
    }

    @Override
    public List<Session> getUserActiveSessions(Long userId) {
        String sql = "SELECT * FROM sessions WHERE user_id = ? AND is_active = true " +
                    "AND expires_at > ? ORDER BY last_accessed_at DESC";
        return dbUtils.queryForList(sql, 
            new Object[]{userId, LocalDateTime.now()}, this::mapSession);
    }

    @Override
    public void invalidateUserSessions(Long userId) {
        String sql = "UPDATE sessions SET is_active = false WHERE user_id = ?";
        dbUtils.update(sql, userId);
    }

    @Override
    public void invalidateUserSessionsExcept(Long userId, String currentSessionToken) {
        String sql = "UPDATE sessions SET is_active = false " +
                    "WHERE user_id = ? AND token != ?";
        dbUtils.update(sql, userId, currentSessionToken);
    }

    @Override
    public boolean hasActiveSession(Long userId) {
        String sql = "SELECT COUNT(*) FROM sessions WHERE user_id = ? " +
                    "AND is_active = true AND expires_at > ?";
        int count = dbUtils.queryForObject(sql, 
            new Object[]{userId, LocalDateTime.now()}, Integer.class);
        return count > 0;
    }

    @Override
    public void cleanupExpiredSessions() {
        String sql = "UPDATE sessions SET is_active = false " +
                    "WHERE expires_at <= ? OR (is_active = true AND last_accessed_at <= ?)";
        LocalDateTime now = LocalDateTime.now();
        dbUtils.update(sql, now, now.minusHours(SESSION_TIMEOUT_HOURS));
    }

    @Override
    public void cleanupInactiveSessions(LocalDateTime lastAccessedBefore) {
        String sql = "DELETE FROM sessions WHERE is_active = false " +
                    "AND last_accessed_at <= ?";
        dbUtils.update(sql, lastAccessedBefore);
    }

    @Override
    public void cleanupAllSessionsForUser(Long userId) {
        String sql = "DELETE FROM sessions WHERE user_id = ?";
        dbUtils.update(sql, userId);
    }

    @Override
    public String generateSessionToken() {
        byte[] randomBytes = new byte[SESSION_TOKEN_LENGTH];
        SECURE_RANDOM.nextBytes(randomBytes);
        return Base64.getUrlEncoder().withoutPadding().encodeToString(randomBytes);
    }

    @Override
    public String extractSessionToken(HttpServletRequest request) {
        // First check header
        String token = request.getHeader("X-Session-Token");
        if (token != null && !token.isEmpty()) {
            return token;
        }

        // Then check cookies
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (SESSION_COOKIE_NAME.equals(cookie.getName())) {
                    return cookie.getValue();
                }
            }
        }

        // Finally check request parameter
        return request.getParameter("sessionToken");
    }

    @Override
    public Map<String, Object> getSessionMetadata(String token) {
        Optional<Session> sessionOpt = getSession(token);
        if (!sessionOpt.isPresent()) {
            return Collections.emptyMap();
        }

        Session session = sessionOpt.get();
        Map<String, Object> metadata = new HashMap<>();
        metadata.put("userId", session.getUserId());
        metadata.put("createdAt", session.getCreatedAt());
        metadata.put("lastAccessedAt", session.getLastAccessedAt());
        metadata.put("expiresAt", session.getExpiresAt());
        metadata.put("ipAddress", session.getIpAddress());
        metadata.put("userAgent", session.getUserAgent());
        metadata.put("isActive", session.isActive());
        metadata.put("isExpired", isExpiredSession(session));
        return metadata;
    }

    @Override
    public boolean validateSessionOrigin(String token, HttpServletRequest request) {
        Optional<Session> sessionOpt = getSession(token);
        if (!sessionOpt.isPresent()) {
            return false;
        }

        Session session = sessionOpt.get();
        String currentIp = getClientIp(request);
        String currentUserAgent = request.getHeader("User-Agent");

        return session.getIpAddress().equals(currentIp) &&
               session.getUserAgent().equals(currentUserAgent);
    }

    @Override
    public void updateSessionSecurity(String token, HttpServletRequest request) {
        String sql = "UPDATE sessions SET ip_address = ?, user_agent = ? WHERE token = ?";
        dbUtils.update(sql, getClientIp(request), 
            request.getHeader("User-Agent"), token);
    }

    @Override
    public boolean isSessionCompromised(String token, HttpServletRequest request) {
        Optional<Session> sessionOpt = getSession(token);
        if (!sessionOpt.isPresent()) {
            return true;
        }

        Session session = sessionOpt.get();
        String currentIp = getClientIp(request);
        String currentUserAgent = request.getHeader("User-Agent");

        return !session.isActive() ||
               isExpiredSession(session) ||
               !session.getIpAddress().equals(currentIp) ||
               !session.getUserAgent().equals(currentUserAgent);
    }

    @Override
    public int getActiveSessionCount() {
        String sql = "SELECT COUNT(*) FROM sessions WHERE is_active = true " +
                    "AND expires_at > ?";
        return dbUtils.queryForObject(sql, 
            new Object[]{LocalDateTime.now()}, Integer.class);
    }

    @Override
    public int getUserSessionCount(Long userId) {
        String sql = "SELECT COUNT(*) FROM sessions WHERE user_id = ? " +
                    "AND is_active = true AND expires_at > ?";
        return dbUtils.queryForObject(sql, 
            new Object[]{userId, LocalDateTime.now()}, Integer.class);
    }

    @Override
    public Map<String, Object> getSessionStats(LocalDateTime startDate, LocalDateTime endDate) {
        Map<String, Object> stats = new HashMap<>();

        // Total sessions created
        String sql1 = "SELECT COUNT(*) FROM sessions WHERE created_at BETWEEN ? AND ?";
        stats.put("totalSessions", dbUtils.queryForObject(sql1, 
            new Object[]{startDate, endDate}, Integer.class));

        // Active sessions
        String sql2 = "SELECT COUNT(*) FROM sessions WHERE is_active = true " +
                     "AND expires_at > ? AND created_at BETWEEN ? AND ?";
        stats.put("activeSessions", dbUtils.queryForObject(sql2, 
            new Object[]{LocalDateTime.now(), startDate, endDate}, Integer.class));

        // Average session duration
        String sql3 = "SELECT AVG(EXTRACT(EPOCH FROM (last_accessed_at - created_at))) " +
                     "FROM sessions WHERE created_at BETWEEN ? AND ?";
        Double avgDuration = dbUtils.queryForObject(sql3, 
            new Object[]{startDate, endDate}, Double.class);
        stats.put("averageSessionDuration", avgDuration != null ? avgDuration : 0);

        // Unique users with sessions
        String sql4 = "SELECT COUNT(DISTINCT user_id) FROM sessions " +
                     "WHERE created_at BETWEEN ? AND ?";
        stats.put("uniqueUsers", dbUtils.queryForObject(sql4, 
            new Object[]{startDate, endDate}, Integer.class));

        return stats;
    }

    private Session mapSession(java.sql.ResultSet rs, int rowNum) throws java.sql.SQLException {
        Session session = new Session();
        session.setId(rs.getLong("id"));
        session.setUserId(rs.getLong("user_id"));
        session.setToken(rs.getString("token"));
        session.setIpAddress(rs.getString("ip_address"));
        session.setUserAgent(rs.getString("user_agent"));
        session.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
        session.setLastAccessedAt(rs.getTimestamp("last_accessed_at").toLocalDateTime());
        session.setExpiresAt(rs.getTimestamp("expires_at").toLocalDateTime());
        session.setActive(rs.getBoolean("is_active"));
        return session;
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
