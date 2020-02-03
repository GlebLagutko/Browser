import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    public static ConnectionFactory factory;
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DATABASE_URL = "jdbc:postgresql://localhost:5432/postgres";
    static final String USER = "postgres";
    static final String PASSWORD = "gleb1899";
    public static synchronized ConnectionFactory getInstance() {
        if (factory == null) {
            try {
                Class.forName("org.postgresql.Driver");
                factory = new ConnectionFactory();
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
        return factory;
    }

    public Connection getConnection()throws SQLException {
        return DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);
    }
}
