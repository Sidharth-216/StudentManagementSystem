import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * ServerConnector class to manage database connections
 * Provides centralized server connection management
 */
public class ServerConnector {
    
    // Database credentials and URL
    private static final String MYSQL_DRIVER = "com.mysql.jdbc.Driver";
    
    // Server configurations
    private static String URL;
    private static String USER;
    private static String PASSWORD;
    
    // Connection pool
    private static Connection connection;
    
    /**
     * Initialize server connector with custom database configuration
     * @param url Database URL
     * @param user Database user
     * @param password Database password
     */
    public static void init(String url, String user, String password) {
        URL = url;
        USER = user;
        PASSWORD = password;
    }
    
    /**
     * Get database connection for College database
     * @return Connection object
     * @throws SQLException if connection fails
     */
    public static Connection getCollegeConnection() throws SQLException {
        return getConnection("jdbc:mysql://localhost:3306/college");
    }
    
    /**
     * Get database connection for Bank database
     * @return Connection object
     * @throws SQLException if connection fails
     */
    public static Connection getBankConnection() throws SQLException {
        return getConnection("jdbc:mysql://localhost:3306/Bank");
    }
    
    /**
     * Get database connection for custom database
     * @param url Database URL
     * @return Connection object
     * @throws SQLException if connection fails
     */
    public static Connection getConnection(String url) throws SQLException {
        try {
            Class.forName(MYSQL_DRIVER);
            return DriverManager.getConnection(url, "root", "sidu21605");
        } catch (ClassNotFoundException e) {
            throw new SQLException("MySQL Driver not found: " + e.getMessage());
        } catch (SQLException e) {
            throw new SQLException("Failed to connect to database: " + e.getMessage());
        }
    }
    
    /**
     * Get database connection using initialized credentials
     * @return Connection object
     * @throws SQLException if connection fails
     */
    public static Connection getConnection() throws SQLException {
        if (URL == null || USER == null || PASSWORD == null) {
            throw new SQLException("Server connector not initialized. Call init() first.");
        }
        
        try {
            Class.forName(MYSQL_DRIVER);
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            throw new SQLException("MySQL Driver not found: " + e.getMessage());
        } catch (SQLException e) {
            throw new SQLException("Failed to connect to database: " + e.getMessage());
        }
    }
    
    /**
     * Close database connection
     * @param conn Connection to close
     */
    public static void closeConnection(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                System.err.println("Error closing connection: " + e.getMessage());
            }
        }
    }
    
    /**
     * Test the database connection
     * @return true if connection is successful, false otherwise
     */
    public static boolean testConnection(String url, String user, String password) {
        try {
            Class.forName(MYSQL_DRIVER);
            Connection testConn = DriverManager.getConnection(url, user, password);
            if (testConn != null) {
                System.out.println("✓ Connection successful to: " + url);
                testConn.close();
                return true;
            }
        } catch (ClassNotFoundException e) {
            System.err.println("✗ MySQL Driver not found: " + e.getMessage());
        } catch (SQLException e) {
            System.err.println("✗ Connection failed: " + e.getMessage());
        }
        return false;
    }
}
