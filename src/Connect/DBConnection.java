package Connect;

/**
 *
 * @author baonguyen
 * This File set up the connection to mySQL Database
 * 
 */

import static Connect.DBCreating.DB_URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static Connection connect;
    static final String DB_URL = "jdbc:mysql://localhost:3306/";
    static final String DB_Name = "bicycleInventory";

    // For User Credentials
    static final String username = "root";
    static final String password = "password";
    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connect = DriverManager.getConnection(DB_URL + DB_Name, username, password);
        } catch (ClassNotFoundException | SQLException e) {
        }
    }
    
    public static Connection getConnection() {
        return connect;
    }
    
}
