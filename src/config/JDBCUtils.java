package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCUtils {

    private static final String jdbcURL = "jdbc:postgresql://localhost:5432/j2ee_library";
    private static final String jdbcUsername = "postgres";
    private static final String jdbcPassword = "postgres";

    public static Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (SQLException e) {
            System.out.println("Connection problems");
        }
        return connection;
    }
}
