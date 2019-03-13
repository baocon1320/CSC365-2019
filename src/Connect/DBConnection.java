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
    // For Connection
    static final String JBDC_Driver = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://ambari-head.csc.calpoly.edu:3306/";  //enter your DB_URL here
    static final String DB_Name = "bnguy123"; // enter your DB name if you want create one

    // For User Credentials

    static final String username = "bnguy123"; // username of DB server
    static final String password = "014508308"; // password of DB server
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
