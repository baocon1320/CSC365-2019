/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import java.sql.Connection;
import javax.swing.table.DefaultTableModel;
import Connect.DBConnection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author baonguyen, Zoey Buelow, Austin Woo, Wesley Benica
 * Complex queries to get the significant info from data 
 */
public class JPanelSaleReport extends javax.swing.JPanel {

    /**
     * Creates new form JPanelSaleReport
     */
    // Private Properties
    private DefaultTableModel dtmHighestSale = new DefaultTableModel();
    private DefaultTableModel dtmHighestBikes = new DefaultTableModel();
    private DefaultTableModel dtmAllCate = new DefaultTableModel();
    private DefaultTableModel dtmAllCateAndManu = new DefaultTableModel();

    private DefaultTableModel dtmPopularBikes = new DefaultTableModel();
    private DefaultTableModel dtmHighestSalesBikes = new DefaultTableModel();
    private DefaultTableModel dtmPopularManufacturers = new DefaultTableModel();

    private DefaultTableModel dtmOrdersSixMonths = new DefaultTableModel();
    private DefaultTableModel dtmCustomerTwoOrders = new DefaultTableModel();
    private DefaultTableModel dtmCustomerOrderTwoYears = new DefaultTableModel();

    private DefaultTableModel dtmManuAllCate = new DefaultTableModel();
    private DefaultTableModel dtmBikesPerCate = new DefaultTableModel();
    private DefaultTableModel dtmBikesMostOrder = new DefaultTableModel();

    Connection connect = DBConnection.getConnection();
    Statement statement;

    public JPanelSaleReport() {
        initComponents();

        try {
            statement = connect.createStatement();
        } catch (SQLException e) {

        }
        loadData();

    }

    private void loadData() {
        // Headers
        String highestSaleHeaders[] = {"Id", "Name", "email", "phone", "Number of Order", "Total Amount"};
        String highestBikeHeaders[] = {"Id", "Name", "email", "phone", "Number of Bikes"};
        String allCateHeaders[] = {"Id", "Name", "email", "phone"};
        String allCateAndManuHeaders[] = {"Id", "Name", "email", "phone"};
        String popularBikeHeaders[] = {"Id", "Model", "Number of Bikes sold"};
        String highestSaleBikesHeaders[] = {"Id", "Model", "Total Sale Amount"};
        String popularManuHeaders[] = {"Id", "Manufacturers", "Current on Stock"};
        String orderSixMonthsHeaders[] = {"Order Id", "Customer Name", "Customer Email", "Total Price", "Dated Order"};
        String customerTwoOrdersHeaders[] = {"Customer Id", "Customer Name", "Customer Email", "Number of Open Orders"};
        String customerOrderTwoYearsHeaders[] = {"Customer Id", "Customer Name", "Customer Email", "Last Order Date"};
        String manuAllCateHeaders[] = {"Manufacturer Id", "Manufacturer Name"};
        String bikePerCateHeaders[] = {"Category Id", "Category Name", "Number of Bikes"};
        String bikeMostOrderHeaders[] = {"Bike Id", "Bike Name", "Number of Order"};

        dtmHighestSale.setColumnIdentifiers(highestSaleHeaders);
        dtmHighestBikes.setColumnIdentifiers(highestBikeHeaders);
        dtmAllCate.setColumnIdentifiers(allCateHeaders);
        dtmAllCateAndManu.setColumnIdentifiers(allCateAndManuHeaders);

        //Austin
        dtmPopularBikes.setColumnIdentifiers(popularBikeHeaders);
        dtmHighestSalesBikes.setColumnIdentifiers(highestSaleBikesHeaders);
        dtmPopularManufacturers.setColumnIdentifiers(popularManuHeaders);

        // Wesley
        dtmOrdersSixMonths.setColumnIdentifiers(orderSixMonthsHeaders);
        dtmCustomerTwoOrders.setColumnIdentifiers(customerTwoOrdersHeaders);
        dtmCustomerOrderTwoYears.setColumnIdentifiers(customerOrderTwoYearsHeaders);

        // Zoey
        dtmManuAllCate.setColumnIdentifiers(manuAllCateHeaders);
        dtmBikesPerCate.setColumnIdentifiers(bikePerCateHeaders);
        dtmBikesMostOrder.setColumnIdentifiers(bikeMostOrderHeaders);

        this.jTableHighestSales.setModel(dtmHighestSale);
        this.jTableHighestBike.setModel(dtmHighestBikes);
        this.jTableAllCategory.setModel(dtmAllCate);
        this.jTableAllCateAndManu.setModel(dtmAllCateAndManu);
        this.jTablePopularBikes.setModel(dtmPopularBikes);
        this.jTableHighestSalesBikes.setModel(dtmHighestSalesBikes);
        this.jTablePopularManufacturer.setModel(dtmPopularManufacturers);
        this.jTableOrderSixMonths.setModel(dtmOrdersSixMonths);
        this.jTableCustomerTwoOrders.setModel(dtmCustomerTwoOrders);
        this.jTableCustomerTwoOrders2Years.setModel(dtmCustomerOrderTwoYears);
        this.jTableManuAllCate.setModel(dtmManuAllCate);
        this.jTableBikesPerCate.setModel(dtmBikesPerCate);
        this.jTableBikesMostOrder.setModel(dtmBikesMostOrder);

        // Select top 10 customers with total order purchased and number of 
        // their orders which order already completed
        try {
            ResultSet rs = statement.executeQuery("select c.cid, c.name, "
                    + "c.email, c.phone, count(*) orderNum, sum(o.price) as "
                    + "totalAmount from Order_info o, Customer c "
                    + "where c.cid = o.customer_id and o.status_id = 2 "
                    + "group by c.cid, c.name, c.email, c.phone "
                    + "order by totalAmount desc "
                    + "limit 10");
            while (rs.next()) {
                dtmHighestSale.addRow(new Object[]{rs.getInt(1), rs.getString(2),
                    rs.getString(3), rs.getString(4), rs.getInt(5),
                    rs.getDouble(6)});
            }

            // Select top 10 customers who bought much bike and order already completed
            rs = statement.executeQuery("select c.cid, c.name, c.email, "
                    + "c.phone, sum(i.quantity) as totalBikes "
                    + "from Order_info o, Customer c, Item_order i "
                    + "where c.cid = o.customer_id and o.id = i.order_id "
                    + "and o.status_id = 2 "
                    + "group by c.cid, c.name, c.email, c.phone "
                    + "order by totalBikes desc "
                    + "limit 10");
            while (rs.next()) {
                dtmHighestBikes.addRow(new Object[]{rs.getInt(1), rs.getString(2),
                    rs.getString(3), rs.getString(4), rs.getInt(5)});
            }

            //#Customers who bought bikes in all category
            rs = statement.executeQuery("select c.cid, c.name, c.email, c.phone\n"
                    + "from Customer c where not exists (select * from "
                    + "Category cc where not exists (select * from "
                    + "Order_info o, Item_order i, Bicycle b "
                    + "where o.status_id = 2 and c.cid = o.customer_id and "
                    + "o.id = i.order_id and i.bicycle_id = b.bid and "
                    + "b.category_id = cc.cid))");
            while (rs.next()) {
                dtmAllCate.addRow(new Object[]{rs.getInt(1), rs.getString(2),
                    rs.getString(3), rs.getString(4)});
            }

            //#Customers who bought all category and all manufacturer
            rs = statement.executeQuery("select * from ( select c.cid, "
                    + "c.name, c.email, c.phone from Customer c where not exists "
                    + "(select * from Category cc where not exists (select * "
                    + "from Order_info o, Item_order i, Bicycle b "
                    + "where o.status_id = 2 and c.cid = o.customer_id and "
                    + "o.id = i.order_id and i.bicycle_id = b.bid and "
                    + "b.category_id = cc.cid))) as T1, (select c.cid, "
                    + "c.name, c.email from Customer c where not exists "
                    + "(select * from Manufacturer m where not exists "
                    + "(select * from Order_info o, Item_order i, Bicycle b "
                    + "where o.status_id = 2 and c.cid = o.customer_id and "
                    + "o.id = i.order_id and i.bicycle_id = b.bid and "
                    + "b.manufacturer_id = m.mid))) as T2 where T1.cid = "
                    + "T2.cid and T1.email = T2.email and T1.name = T2.name");
            while (rs.next()) {
                dtmAllCateAndManu.addRow(new Object[]{rs.getInt(1), rs.getString(2),
                    rs.getString(3), rs.getString(4)});
            }

            // Top 10 most popular bikes sold
            statement.executeUpdate("drop view if exists BikesGroupedByBID");

            statement.executeUpdate("CREATE VIEW BikesGroupedByBID(bikeID, num)\n"
                    + "AS SELECT I.bicycle_id,sum(I.quantity) as num\n"
                    + "FROM Item_order as I,Order_info as O\n"
                    + "WHERE O.id = I.order_id and O.status_id = 2\n"
                    + "GROUP BY I.bicycle_id\n"
                    + "ORDER BY num desc\n"
                    + "LIMIT 10");
            rs = statement.executeQuery("SELECT B.bid, B.model, G.num\n"
                    + "FROM BikesGroupedByBID G, Bicycle B\n"
                    + "WHERE G.bikeID = B.bid");
            while (rs.next()) {
                dtmPopularBikes.addRow(new Object[]{rs.getInt(1), rs.getString(2),
                    rs.getInt(3)});
            }

            //  Top 10 bike models that made the most money
            statement.executeUpdate("drop view if exists bikeByPrice");

            statement.executeUpdate("CREATE VIEW bikeByPrice(bikeID,price) AS\n"
                    + "SELECT bicycle_id, sum(price) as price\n"
                    + "FROM (select bicycle_id, (price * quantity) as price from Item_order) as T1\n"
                    + "GROUP BY bicycle_id\n"
                    + "ORDER BY price desc\n"
                    + "LIMIT 10");
            rs = statement.executeQuery("SELECT B.bid, B.model, P.price as amount\n"
                    + "FROM bikeByPrice P, Bicycle B\n"
                    + "WHERE P.bikeID = B.bid");
            while (rs.next()) {
                dtmHighestSalesBikes.addRow(new Object[]{rs.getInt(1), rs.getString(2),
                    rs.getDouble(3)});
            }

            //  Manufacturer that has the most bikes in stock
            statement.executeUpdate("drop view if exists Popularity");

            statement.executeUpdate("CREATE VIEW Popularity(manufacturer, stock)\n"
                    + "AS SELECT manufacturer_id, sum(stock) as stock\n"
                    + "FROM Bicycle \n"
                    + "GROUP BY manufacturer_id\n"
                    + "ORDER BY stock desc\n"
                    + "Limit 1");

            rs = statement.executeQuery("SELECT M.mid, M.name, stock\n"
                    + "FROM Manufacturer M, Popularity P\n"
                    + "WHERE M.mid = P.manufacturer");
            while (rs.next()) {
                dtmPopularManufacturers.addRow(new Object[]{rs.getInt(1), rs.getString(2),
                    rs.getInt(3)});
            }

            // Orders over 6 months old that are still processing
            rs = statement.executeQuery("SELECT O.id, C.name, C.email, O.price, O.date_order\n"
                    + "FROM Order_info O JOIN Customer C ON O.customer_id = C.cid \n"
                    + "JOIN Order_status S ON S.sid = O.status_id\n"
                    + "WHERE O.date_order <= NOW() - INTERVAL 6 MONTH\n"
                    + "AND S.description = \"Processing\"\n"
                    + "ORDER BY O.date_order");
            while (rs.next()) {
                dtmOrdersSixMonths.addRow(new Object[]{rs.getInt(1), rs.getString(2),
                    rs.getString(3), rs.getDouble(4), rs.getString(5)});
            }

            //  Customers with more than 2 open orders
            statement.executeUpdate("DROP VIEW IF EXISTS NumberOfProcOrdersByCust");

            statement.executeUpdate("CREATE VIEW NumberOfProcOrdersByCust AS\n"
                    + "SELECT C.cid, COUNT(*) AS open_orders\n"
                    + "FROM Customer C JOIN Order_info O ON C.cid = O.customer_id \n"
                    + "JOIN Order_status S ON O.status_id = S.sid\n"
                    + "WHERE S.description = \"Processing\"\n"
                    + "GROUP BY C.cid\n"
                    + "ORDER BY open_orders DESC");

            rs = statement.executeQuery("SELECT C.cid, C.name, C.email, N.open_orders\n"
                    + "FROM NumberOfProcOrdersByCust N NATURAL JOIN Customer C\n"
                    + "WHERE N.open_orders > 2");
            while (rs.next()) {
                dtmCustomerTwoOrders.addRow(new Object[]{rs.getInt(1), rs.getString(2),
                    rs.getString(3), rs.getInt(4)});
            }

            //  Customers who haven't ordered in last 24 months (2 years)
            rs = statement.executeQuery("SELECT C.cid, C.name, C.email, MAX(date_order) AS last_order\n"
                    + "FROM Customer C JOIN Order_info O ON C.cid = O.customer_id\n"
                    + "WHERE NOT EXISTS ( SELECT *\n"
                    + "FROM Customer C1 JOIN Order_info O1 ON C1.cid = O1.customer_id\n"
                    + "WHERE C.cid = O1.customer_id AND\n"
                    + "O1.date_order >= NOW() - INTERVAL 24 MONTH )\n"
                    + "GROUP BY C.cid, C.name, C.email, C.phone\n"
                    + "ORDER BY last_order DESC");
            while (rs.next()) {
                dtmCustomerOrderTwoYears.addRow(new Object[]{rs.getInt(1), rs.getString(2),
                    rs.getString(3), rs.getString(4)});
            }

            //  Manufacturers with bike in all categories
            rs = statement.executeQuery("select M.mid, M.name from Manufacturer M\n"
                    + "where not exists (select C.cid from Category C\n"
                    + "where not exists (select B.bid from Bicycle B\n"
                    + "where B.category_id = C.cid \n"
                    + "AND B.manufacturer_id = M.mid))");
            while (rs.next()) {
                dtmManuAllCate.addRow(new Object[]{rs.getInt(1), rs.getString(2)});
            }

            //  Number of bikes per category
            rs = statement.executeQuery("select C.cid, C.name, count(*) as numberOfModels\n"
                    + "from Category C, Bicycle B\n"
                    + "where C.cid = B.category_id\n"
                    + "group by C.cid, C.name");
            while (rs.next()) {
                dtmBikesPerCate.addRow(new Object[]{rs.getInt(1), rs.getString(2),
                    rs.getInt(3)});
            }

            //  Bike that appear in  most number of orders
            statement.executeUpdate("drop view if exists numOrders");

            statement.executeUpdate("create view numOrders AS\n"
                    + "select B.bid, B.model, count(*) as numberOfOrders\n"
                    + "from Bicycle B, Item_order I\n"
                    + "where B.bid = I.bicycle_id\n"
                    + "group by I.bicycle_id");

            rs = statement.executeQuery("select *\n"
                    + "from numOrders O\n"
                    + "where O.numberOfOrders in (select max(O.numberOfOrders) from numOrders O)");
            while (rs.next()) {
                dtmBikesMostOrder.addRow(new Object[]{rs.getInt(1), rs.getString(2),
                    rs.getInt(3)});
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanelCustomerReport = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableHighestSales = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTableAllCateAndManu = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableHighestBike = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTableAllCategory = new javax.swing.JTable();
        jTabbedPanel2 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTablePopularBikes = new javax.swing.JTable();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        jTableHighestSalesBikes = new javax.swing.JTable();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        jTablePopularManufacturer = new javax.swing.JTable();
        jPanel8 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jScrollPane8 = new javax.swing.JScrollPane();
        jTableOrderSixMonths = new javax.swing.JTable();
        jPanel10 = new javax.swing.JPanel();
        jScrollPane9 = new javax.swing.JScrollPane();
        jTableCustomerTwoOrders = new javax.swing.JTable();
        jPanel12 = new javax.swing.JPanel();
        jScrollPane11 = new javax.swing.JScrollPane();
        jTableCustomerTwoOrders2Years = new javax.swing.JTable();
        jPanel11 = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        jScrollPane10 = new javax.swing.JScrollPane();
        jTableManuAllCate = new javax.swing.JTable();
        jPanel14 = new javax.swing.JPanel();
        jScrollPane12 = new javax.swing.JScrollPane();
        jTableBikesPerCate = new javax.swing.JTable();
        jPanel15 = new javax.swing.JPanel();
        jScrollPane13 = new javax.swing.JScrollPane();
        jTableBikesMostOrder = new javax.swing.JTable();

        jTabbedPane1.setPreferredSize(new java.awt.Dimension(953, 600));

        jPanelCustomerReport.setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Top 10 Buyers with highest total Purchase"));
        jPanel1.setPreferredSize(new java.awt.Dimension(0, 0));

        jTableHighestSales.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTableHighestSales);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 470, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 226, Short.MAX_VALUE)
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Customers who bought bike in all categories and manufactueres"));

        jTableAllCateAndManu.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane4.setViewportView(jTableAllCateAndManu);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(19, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Top 10 Buyers who bought greatest number of bikes"));
        jPanel3.setPreferredSize(new java.awt.Dimension(0, 0));

        jTableHighestBike.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(jTableHighestBike);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 420, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Customers who bought bikes in all Categories"));
        jPanel4.setPreferredSize(new java.awt.Dimension(0, 0));

        jTableAllCategory.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane3.setViewportView(jTableAllCategory);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(76, 76, 76))
        );

        javax.swing.GroupLayout jPanelCustomerReportLayout = new javax.swing.GroupLayout(jPanelCustomerReport);
        jPanelCustomerReport.setLayout(jPanelCustomerReportLayout);
        jPanelCustomerReportLayout.setHorizontalGroup(
            jPanelCustomerReportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelCustomerReportLayout.createSequentialGroup()
                .addGroup(jPanelCustomerReportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelCustomerReportLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, 476, Short.MAX_VALUE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 482, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelCustomerReportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 444, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanelCustomerReportLayout.setVerticalGroup(
            jPanelCustomerReportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelCustomerReportLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelCustomerReportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelCustomerReportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 258, Short.MAX_VALUE))
                .addContainerGap(215, Short.MAX_VALUE))
        );

        jPanel2.getAccessibleContext().setAccessibleDescription("");

        jTabbedPane1.addTab("Customer Report", jPanelCustomerReport);

        jTabbedPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Top 10 most popular bikes sold"));

        jTablePopularBikes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane5.setViewportView(jTablePopularBikes);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane5)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 169, Short.MAX_VALUE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder("Top 10 bike models that made the most money"));

        jTableHighestSalesBikes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane6.setViewportView(jTableHighestSalesBikes);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 892, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 169, Short.MAX_VALUE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder("Manufacturer that has the most bikes in stock"));

        jTablePopularManufacturer.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane7.setViewportView(jTablePopularManufacturer);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 892, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 169, Short.MAX_VALUE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jTabbedPanel2Layout = new javax.swing.GroupLayout(jTabbedPanel2);
        jTabbedPanel2.setLayout(jTabbedPanel2Layout);
        jTabbedPanel2Layout.setHorizontalGroup(
            jTabbedPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jTabbedPanel2Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jTabbedPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jTabbedPanel2Layout.setVerticalGroup(
            jTabbedPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jTabbedPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(102, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Sales Report", jTabbedPanel2);

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));
        jPanel8.setToolTipText("Order Report");

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));
        jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder("Orders over 6 months old that are still processing"));

        jTableOrderSixMonths.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane8.setViewportView(jTableOrderSixMonths);

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jScrollPane8, javax.swing.GroupLayout.DEFAULT_SIZE, 888, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane8, javax.swing.GroupLayout.DEFAULT_SIZE, 156, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel10.setBackground(new java.awt.Color(255, 255, 255));
        jPanel10.setBorder(javax.swing.BorderFactory.createTitledBorder("Customers with more than 2 open orders"));

        jTableCustomerTwoOrders.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane9.setViewportView(jTableCustomerTwoOrders);

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jScrollPane9, javax.swing.GroupLayout.DEFAULT_SIZE, 888, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel12.setBackground(new java.awt.Color(255, 255, 255));
        jPanel12.setBorder(javax.swing.BorderFactory.createTitledBorder("Customers who haven't ordered in last 24 months (2 years)"));

        jTableCustomerTwoOrders2Years.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane11.setViewportView(jTableCustomerTwoOrders2Years);

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jScrollPane11, javax.swing.GroupLayout.DEFAULT_SIZE, 888, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(97, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Order Report", jPanel8);

        jPanel11.setBackground(new java.awt.Color(255, 255, 255));

        jPanel13.setBackground(new java.awt.Color(255, 255, 255));
        jPanel13.setBorder(javax.swing.BorderFactory.createTitledBorder("Manufacturers with bike in all categories"));

        jTableManuAllCate.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane10.setViewportView(jTableManuAllCate);

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane10, javax.swing.GroupLayout.DEFAULT_SIZE, 902, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addComponent(jScrollPane10, javax.swing.GroupLayout.DEFAULT_SIZE, 159, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel14.setBackground(new java.awt.Color(255, 255, 255));
        jPanel14.setBorder(javax.swing.BorderFactory.createTitledBorder("Number of bikes per category"));

        jTableBikesPerCate.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane12.setViewportView(jTableBikesPerCate);

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane12, javax.swing.GroupLayout.DEFAULT_SIZE, 902, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addComponent(jScrollPane12, javax.swing.GroupLayout.DEFAULT_SIZE, 159, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel15.setBackground(new java.awt.Color(255, 255, 255));
        jPanel15.setBorder(javax.swing.BorderFactory.createTitledBorder("Bike with most number of orders"));

        jTableBikesMostOrder.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane13.setViewportView(jTableBikesMostOrder);

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane13, javax.swing.GroupLayout.DEFAULT_SIZE, 902, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addComponent(jScrollPane13, javax.swing.GroupLayout.DEFAULT_SIZE, 159, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(150, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Bike Report", jPanel11);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 781, Short.MAX_VALUE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JPanel jPanelCustomerReport;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane13;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JPanel jTabbedPanel2;
    private javax.swing.JTable jTableAllCateAndManu;
    private javax.swing.JTable jTableAllCategory;
    private javax.swing.JTable jTableBikesMostOrder;
    private javax.swing.JTable jTableBikesPerCate;
    private javax.swing.JTable jTableCustomerTwoOrders;
    private javax.swing.JTable jTableCustomerTwoOrders2Years;
    private javax.swing.JTable jTableHighestBike;
    private javax.swing.JTable jTableHighestSales;
    private javax.swing.JTable jTableHighestSalesBikes;
    private javax.swing.JTable jTableManuAllCate;
    private javax.swing.JTable jTableOrderSixMonths;
    private javax.swing.JTable jTablePopularBikes;
    private javax.swing.JTable jTablePopularManufacturer;
    // End of variables declaration//GEN-END:variables
}
