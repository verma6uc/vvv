package ai.yuvi.db.utilities;

import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionPool {
    private static final BasicDataSource dataSource;
    private static final Properties properties = new Properties();

    static {
        try (InputStream input = ConnectionPool.class.getClassLoader().getResourceAsStream("db.properties")) {
            properties.load(input);
            dataSource = setupDataSource();
        } catch (IOException e) {
            throw new RuntimeException("Failed to load database properties", e);
        }
    }

    private static BasicDataSource setupDataSource() {
        BasicDataSource ds = new BasicDataSource();

        // Basic Connection Properties
        ds.setDriverClassName(properties.getProperty("db.driver"));
        ds.setUrl(properties.getProperty("db.url"));
        ds.setUsername(properties.getProperty("db.username"));
        ds.setPassword(properties.getProperty("db.password"));

        // Pool Configuration
        ds.setInitialSize(Integer.parseInt(properties.getProperty("pool.initialSize")));
        ds.setMaxTotal(Integer.parseInt(properties.getProperty("pool.maxActive")));
        ds.setMaxIdle(Integer.parseInt(properties.getProperty("pool.maxIdle")));
        ds.setMinIdle(Integer.parseInt(properties.getProperty("pool.minIdle")));
        ds.setMaxWaitMillis(Long.parseLong(properties.getProperty("pool.maxWait")));
        ds.setRemoveAbandonedOnBorrow(Boolean.parseBoolean(properties.getProperty("pool.removeAbandoned")));
        ds.setRemoveAbandonedTimeout(Integer.parseInt(properties.getProperty("pool.removeAbandonedTimeout")));

        // Connection Validation
        ds.setTestOnBorrow(Boolean.parseBoolean(properties.getProperty("pool.testOnBorrow")));
        ds.setValidationQuery(properties.getProperty("pool.validationQuery"));
        ds.setValidationQueryTimeout(Integer.parseInt(properties.getProperty("pool.validationInterval")));

        // Connection Pool Maintenance
        ds.setTimeBetweenEvictionRunsMillis(Long.parseLong(properties.getProperty("pool.timeBetweenEvictionRunsMillis")));
        ds.setMinEvictableIdleTimeMillis(Long.parseLong(properties.getProperty("pool.minEvictableIdleTimeMillis")));

        // Performance Settings
        ds.setDefaultAutoCommit(Boolean.parseBoolean(properties.getProperty("pool.defaultAutoCommit")));
        ds.setDefaultTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
        ds.setTestWhileIdle(Boolean.parseBoolean(properties.getProperty("pool.testWhileIdle")));

        // Prepared Statement Caching
        ds.setPoolPreparedStatements(Boolean.parseBoolean(properties.getProperty("pool.poolPreparedStatements")));
        ds.setMaxOpenPreparedStatements(Integer.parseInt(properties.getProperty("pool.maxOpenPreparedStatements")));

        return ds;
    }

    /**
     * Get a connection from the pool
     * @return A database connection
     * @throws SQLException if a database access error occurs
     */
    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    /**
     * Get the underlying data source
     * @return The configured DataSource instance
     */
    public static DataSource getDataSource() {
        return dataSource;
    }

    /**
     * Close the connection pool
     */
    public static void closePool() {
        try {
            dataSource.close();
        } catch (SQLException e) {
            throw new RuntimeException("Error closing connection pool", e);
        }
    }

    /**
     * Get current pool statistics
     * @return A string containing pool statistics
     */
    public static String getPoolStats() {
        return String.format(
            "Pool Stats - Active: %d, Idle: %d, Total: %d",
            dataSource.getNumActive(),
            dataSource.getNumIdle(),
            dataSource.getMaxTotal()
        );
    }
}
