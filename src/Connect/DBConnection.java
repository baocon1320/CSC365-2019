package Connect;

/**
 *
 * @author baonguyen
 * This File set up the connection to mySQL Database
 * 
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static Connection connect;
    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connect = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/bicycleInventory?user=root&password=password");
        } catch (ClassNotFoundException | SQLException e) {
        }
    }
    
    public static Connection getConnection() {
        return connect;
    }
    
}
