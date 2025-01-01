package ai.yuvi.db.utilities;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DatabaseUtils {
    private static final Logger LOGGER = Logger.getLogger(DatabaseUtils.class.getName());

    /**
     * Quietly close database resources
     */
    public static void closeQuietly(AutoCloseable... resources) {
        for (AutoCloseable resource : resources) {
            if (resource != null) {
                try {
                    resource.close();
                } catch (Exception e) {
                    LOGGER.log(Level.WARNING, "Error closing resource", e);
                }
            }
        }
    }

    /**
     * Execute a query with parameters and process results with a ResultSetHandler
     */
    public static <T> T executeQuery(String sql, ResultSetHandler<T> handler, Object... params) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = ConnectionPool.getConnection();
            stmt = conn.prepareStatement(sql);
            setParameters(stmt, params);
            rs = stmt.executeQuery();
            return handler.handle(rs);
        } finally {
            closeQuietly(rs, stmt, conn);
        }
    }

    /**
     * Execute an update query with parameters
     */
    public static int executeUpdate(String sql, Object... params) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = ConnectionPool.getConnection();
            stmt = conn.prepareStatement(sql);
            setParameters(stmt, params);
            return stmt.executeUpdate();
        } finally {
            closeQuietly(stmt, conn);
        }
    }

    /**
     * Execute a batch update
     */
    public static int[] executeBatch(String sql, BatchPreparedStatementSetter setter) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = ConnectionPool.getConnection();
            conn.setAutoCommit(false);
            stmt = conn.prepareStatement(sql);

            int batchSize = setter.getBatchSize();
            for (int i = 0; i < batchSize; i++) {
                setter.setValues(stmt, i);
                stmt.addBatch();
            }

            int[] results = stmt.executeBatch();
            conn.commit();
            return results;
        } catch (SQLException e) {
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException ex) {
                    LOGGER.log(Level.SEVERE, "Error rolling back transaction", ex);
                }
            }
            throw e;
        } finally {
            if (conn != null) {
                try {
                    conn.setAutoCommit(true);
                } catch (SQLException e) {
                    LOGGER.log(Level.WARNING, "Error resetting auto-commit", e);
                }
            }
            closeQuietly(stmt, conn);
        }
    }

    /**
     * Set parameters for a prepared statement
     */
    private static void setParameters(PreparedStatement stmt, Object... params) throws SQLException {
        if (params != null) {
            for (int i = 0; i < params.length; i++) {
                stmt.setObject(i + 1, params[i]);
            }
        }
    }

    /**
     * Interface for handling ResultSet processing
     */
    @FunctionalInterface
    public interface ResultSetHandler<T> {
        T handle(ResultSet rs) throws SQLException;
    }

    /**
     * Interface for setting batch parameters
     */
    public interface BatchPreparedStatementSetter {
        void setValues(PreparedStatement ps, int i) throws SQLException;
        int getBatchSize();
    }

    /**
     * Execute in a transaction
     */
    public static <T> T executeInTransaction(TransactionCallback<T> callback) throws SQLException {
        Connection conn = null;
        try {
            conn = ConnectionPool.getConnection();
            conn.setAutoCommit(false);
            
            T result = callback.doInTransaction(conn);
            
            conn.commit();
            return result;
        } catch (SQLException e) {
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException ex) {
                    LOGGER.log(Level.SEVERE, "Error rolling back transaction", ex);
                }
            }
            throw e;
        } finally {
            if (conn != null) {
                try {
                    conn.setAutoCommit(true);
                } catch (SQLException e) {
                    LOGGER.log(Level.WARNING, "Error resetting auto-commit", e);
                }
            }
            closeQuietly(conn);
        }
    }

    /**
     * Interface for transaction callbacks
     */
    @FunctionalInterface
    public interface TransactionCallback<T> {
        T doInTransaction(Connection conn) throws SQLException;
    }

    /**
     * Check if a table exists
     */
    public static boolean tableExists(String tableName) throws SQLException {
        return executeQuery(
            "SELECT COUNT(*) FROM information_schema.tables WHERE table_name = ?",
            rs -> {
                rs.next();
                return rs.getInt(1) > 0;
            },
            tableName.toLowerCase()
        );
    }

    /**
     * Get the current database version
     */
    public static String getDatabaseVersion() throws SQLException {
        return executeQuery(
            "SELECT version()",
            rs -> {
                rs.next();
                return rs.getString(1);
            }
        );
    }

    /**
     * Test database connectivity
     */
    public static boolean testConnection() {
        try (Connection conn = ConnectionPool.getConnection()) {
            return conn.isValid(5);
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Database connection test failed", e);
            return false;
        }
    }
}
