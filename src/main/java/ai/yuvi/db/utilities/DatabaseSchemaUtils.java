package ai.yuvi.db.utilities;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Utility class for database schema operations aligned with Yuvi's data model
 */
public class DatabaseSchemaUtils {
    private static final Logger LOGGER = Logger.getLogger(DatabaseSchemaUtils.class.getName());

    /**
     * Agent Types as defined in agent_type_enum
     */
    public enum AgentType {
        PLANNING,
        CONVERSATION,
        MEMORY_CONTEXT,
        RESEARCH,
        IMPACT_ANALYSIS,
        DATA_MODELING,
        QUERY_ENDPOINT,
        HTML_TEMPLATING,
        JS_LOGIC,
        ACTION_EFFECTS,
        API_INTEGRATION,
        DATA_DISCOVERY,
        OTHER
    }

    /**
     * Application phases as defined in application_phase_enum
     */
    public enum ApplicationPhase {
        MEMORY,
        BLUEPRINT,
        VISUAL_PRD,
        DEVELOPMENT,
        MAINTENANCE
    }

    /**
     * Memory block types as defined in memory_block_type_enum
     */
    public enum MemoryBlockType {
        IDEA,
        FEATURE_CONCEPT,
        PERSONA,
        CONSTRAINT,
        RESEARCH_NOTES,
        OTHER
    }

    /**
     * Task statuses as defined in task_status_enum
     */
    public enum TaskStatus {
        OPEN,
        IN_PROGRESS,
        DONE,
        BLOCKED,
        CANCELLED
    }

    /**
     * Get all agents of a specific type
     */
    public static List<String> getAgentsByType(AgentType type) throws SQLException {
        String sql = "SELECT name FROM agents WHERE type = ?::agent_type_enum";
        return DatabaseUtils.executeQuery(sql, rs -> {
            List<String> agents = new ArrayList<>();
            while (rs.next()) {
                agents.add(rs.getString("name"));
            }
            return agents;
        }, type.name());
    }

    /**
     * Get memory blocks for an application phase
     */
    public static List<String> getMemoryBlocks(String applicationId, MemoryBlockType type) throws SQLException {
        String sql = "SELECT title FROM memory_blocks WHERE application_id = ? AND type = ?::memory_block_type_enum";
        return DatabaseUtils.executeQuery(sql, rs -> {
            List<String> blocks = new ArrayList<>();
            while (rs.next()) {
                blocks.add(rs.getString("title"));
            }
            return blocks;
        }, applicationId, type.name());
    }

    /**
     * Get tasks by status
     */
    public static List<String> getTasksByStatus(String applicationId, TaskStatus status) throws SQLException {
        String sql = "SELECT title FROM tasks WHERE application_id = ? AND status = ?::task_status_enum";
        return DatabaseUtils.executeQuery(sql, rs -> {
            List<String> tasks = new ArrayList<>();
            while (rs.next()) {
                tasks.add(rs.getString("title"));
            }
            return tasks;
        }, applicationId, status.name());
    }

    /**
     * Get application phase
     */
    public static ApplicationPhase getApplicationPhase(String applicationId) throws SQLException {
        String sql = "SELECT phase FROM applications WHERE id = ?";
        return DatabaseUtils.executeQuery(sql, rs -> {
            if (rs.next()) {
                return ApplicationPhase.valueOf(rs.getString("phase"));
            }
            throw new SQLException("Application not found: " + applicationId);
        }, applicationId);
    }

    /**
     * Update application phase
     */
    public static void updateApplicationPhase(String applicationId, ApplicationPhase phase) throws SQLException {
        String sql = "UPDATE applications SET phase = ?::application_phase_enum WHERE id = ?";
        DatabaseUtils.executeUpdate(sql, phase.name(), applicationId);
    }

    /**
     * Create a new memory block
     */
    public static void createMemoryBlock(String applicationId, String title, MemoryBlockType type, String content) throws SQLException {
        String sql = "INSERT INTO memory_blocks (application_id, title, type, content) VALUES (?, ?, ?::memory_block_type_enum, ?)";
        DatabaseUtils.executeUpdate(sql, applicationId, title, type.name(), content);
    }

    /**
     * Create a new task
     */
    public static void createTask(String applicationId, String title, TaskStatus status) throws SQLException {
        String sql = "INSERT INTO tasks (application_id, title, status) VALUES (?, ?, ?::task_status_enum)";
        DatabaseUtils.executeUpdate(sql, applicationId, title, status.name());
    }

    /**
     * Update task status
     */
    public static void updateTaskStatus(String taskId, TaskStatus status) throws SQLException {
        String sql = "UPDATE tasks SET status = ?::task_status_enum WHERE id = ?";
        DatabaseUtils.executeUpdate(sql, status.name(), taskId);
    }

    /**
     * Get agent task types
     */
    public static List<String> getAgentTaskTypes(String agentId) throws SQLException {
        String sql = "SELECT task_type FROM agent_task_types WHERE agent_id = ?";
        return DatabaseUtils.executeQuery(sql, rs -> {
            List<String> types = new ArrayList<>();
            while (rs.next()) {
                types.add(rs.getString("task_type"));
            }
            return types;
        }, agentId);
    }

    /**
     * Check if table exists
     */
    public static boolean tableExists(String tableName) throws SQLException {
        return DatabaseUtils.executeQuery(
            "SELECT EXISTS (SELECT 1 FROM information_schema.tables WHERE table_name = ?)",
            rs -> {
                rs.next();
                return rs.getBoolean(1);
            },
            tableName.toLowerCase()
        );
    }

    /**
     * Initialize schema if needed
     */
    public static void initializeSchemaIfNeeded() {
        try {
            // Check if key tables exist
            if (!tableExists("applications")) {
                LOGGER.info("Initializing database schema...");
                // Schema initialization would go here
                // In practice, you'd probably want to use a migration tool like Flyway
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Failed to initialize schema", e);
        }
    }
}
