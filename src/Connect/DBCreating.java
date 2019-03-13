/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Connect;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author baonguyen
 */
public class DBCreating {

    // For Connection
    static final String JBDC_Driver = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://ambari-head.csc.calpoly.edu:3306/";  //enter your DB_URL here
    static final String DB_Name = "bnguy123"; // enter your DB name if you want create one

    // For User Credentials

    static final String username = "bnguy123"; // username of DB server
    static final String password = "014508308"; // password of DB server

    private Statement st;

    // Check if DB exist, if not creat one
    public static void DBCreating() {
        Connection con;
        ArrayList<String> listDB = new ArrayList<>();
        DatabaseMetaData dbm;
        Statement st;
        ResultSet rs;

        try {
            Class.forName(JBDC_Driver);
            con = DriverManager.getConnection(DB_URL, username, password);
            dbm = con.getMetaData();
            st = con.createStatement();
            rs = dbm.getCatalogs();

            // Add all DB name to list
            while (rs.next()) {
                listDB.add(rs.getString("TABLE_CAT"));
                //System.out.println(rs.getString("TABLE_CAT"));
            }

            // Check if DB does not exist create one
            if (!(listDB.contains(DB_Name))) {

                st.executeUpdate("create database " + DB_Name);
                String newURL = DB_URL + DB_Name;
                con = DriverManager.getConnection(newURL, username, password);
                st = con.createStatement();

                // Create table Manufacturer
                String manufacturerCreate = "create table Manufacturer ("
                        + "mid integer auto_increment primary key,"
                        + "name varchar(50) not null unique)";
                st.executeUpdate(manufacturerCreate);

                // Create table Category
                String categoryCreate = "create table Category ("
                        + "cid integer auto_increment primary key,"
                        + "name varchar(50) not null unique)";
                st.executeUpdate(categoryCreate);

                // Create table Bicycle
                String bicycleCreate = "create table Bicycle ("
                        + "bid integer auto_increment primary key,"
                        + "model varchar(50) not null,"
                        + "price double,"
                        + "stock integer not null,"
                        + "manufacturer_id integer,"
                        + "category_id integer,"
                        + "foreign key (manufacturer_id) references Manufacturer(mid)"
                        + " on delete set null on update cascade,"
                        + "foreign key (category_id) references Category(cid)"
                        + " on delete set null on update cascade)";
                st.executeUpdate(bicycleCreate);

                // Create table State
                String stateCreate = "create table State ("
                        + "sid integer auto_increment primary key,"
                        + "name varchar(50) not null unique)";
                st.executeUpdate(stateCreate);

                // Insert all states to table State
                String[] states = {"California,", "Alabama,", "Arkansas,", "Arizona,",
                    "Alaska,", "Colorado,", "Connecticut,", "Delaware,", "Florida,",
                    "Georgia,", "Hawaii,", "Idaho,", "Illinois,", "Indiana,", "Iowa,",
                    "Kansas,", "Kentucky,", "Louisiana,", "Maine,", "Maryland,",
                    "Massachusetts,", "Michigan,", "Minnesota,", "Mississippi,",
                    "Missouri,", "Montana,", "Nebraska,", "Nevada,", "New Hampshire,",
                    "New Jersey,", "New Mexico,", "New York,", "North Carolina,",
                    "North Dakota,", "Ohio,", "Oklahoma,", "Oregon,", "Pennsylvania,",
                    "Rhode Island,", "South Carolina,", "South Dakota,", "Tennessee,",
                    "Texas,", "Utah,", "Vermont,", "Virginia,", "Washington,",
                    "West Virginia,", "Wisconsin,", "Wyoming"};

                // State names in alphabetical order
                Arrays.sort(states);

                for (int i = 0; i < states.length; i++) {
                    st.executeUpdate("insert into State(name) values('"
                            + states[i] + "')");
                }

                // Create table Address
                String addressCreate = "create table Address ("
                        + "aid integer auto_increment primary key,"
                        + "address varchar(100),"
                        + "city varchar(50),"
                        + "state_id integer,"
                        + "zipcode varchar(5),"
                        + "foreign key (state_id) references State(sid))";
                st.executeUpdate(addressCreate);

                // Create table Customer
                String customerCreate = "create table Customer ("
                        + "cid integer auto_increment primary key,"
                        + "name varchar(50) not null,"
                        + "email varchar(50) not null unique,"
                        + "phone varchar(10) not null,"
                        + "address_id integer,"
                        + "foreign key (address_id) references Address(aid)"
                        + " on delete set null on update cascade)";
                st.executeUpdate(customerCreate);

                // Create table Order_status
                String orderStatusCreate = "create table  Order_status ("
                        + "sid integer auto_increment primary key,"
                        + "description varchar(20) not null unique)";
                st.executeUpdate(orderStatusCreate);
                
                // Insert 3 status to order status table
                String[] orderStatus = {"Processing", "Done", "Cancel"};
                for(int i = 0; i < orderStatus.length; i++) {
                    st.executeUpdate    ("insert into Order_status(description)"
                            + " values('" + orderStatus[i] + "')");
                }
                

                // Create table Order
                String orderCreate = "create table Order_info ("
                        + "id integer auto_increment primary key,"
                        + "customer_id integer,"
                        + "price double not null, "
                        + "date_order date not  null,"
                        + "status_id integer not null,"
                        + "foreign key (customer_id) references Customer(cid)"
                        + " on delete set null on update cascade,"
                        + "foreign key (status_id) references Order_status(sid))";
                st.executeUpdate(orderCreate);

                // Create table Item_order
                String item_orderCreate = "create table Item_order ("
                        + "bicycle_id integer,"
                        + "order_id integer,"
                        + "price double,"
                        + "quantity integer,"
                        + "primary key (bicycle_id, order_id),"
                        + "foreign key (bicycle_id) references Bicycle(bid) "
                        + "on delete cascade on update cascade,"
                        + "foreign key (order_id) references Order_info(id) "
                        + "on delete cascade on update cascade)";
                st.executeUpdate(item_orderCreate);

            }

        } catch (ClassNotFoundException | SQLException e) {
            System.out.println ( "DBCreating: " + e.getMessage () );
        }
    }

}
