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
 * @author baonguyen
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
        
       
        dtmHighestSale.setColumnIdentifiers(highestSaleHeaders);
        dtmHighestBikes.setColumnIdentifiers(highestBikeHeaders);
        dtmAllCate.setColumnIdentifiers(allCateHeaders);
        dtmAllCateAndManu.setColumnIdentifiers(allCateAndManuHeaders);
        
        
        this.jTableHighestSales.setModel(dtmHighestSale);
        this.jTableHighestBike.setModel(dtmHighestBikes);
        this.jTableAllCategory.setModel(dtmAllCate);
        this.jTableAllCateAndManu.setModel(dtmAllCateAndManu);
        
        try {
            ResultSet rs = statement.executeQuery("select c.cid, c.name, " +
                       "c.email, c.phone, count(*) orderNum, sum(o.price) as "+
                       "totalAmount from Order_info o, Customer c " +
                        "where c.cid = o.customer_id and o.status_id = 2 " +
                        "group by c.cid, c.name, c.email, c.phone " +
                        "order by totalAmount desc " +
                        "limit 10");
            while (rs.next()) {
                dtmHighestSale.addRow(new Object[]{rs.getInt(1), rs.getString(2),
                                rs.getString(3), rs.getString(4), rs.getInt(5),
                                rs.getDouble(6)});
            }
            
            rs = statement.executeQuery("select c.cid, c.name, c.email, " + 
                    "c.phone, sum(i.quantity) as totalBikes " +
                    "from Order_info o, Customer c, Item_order i " +
                    "where c.cid = o.customer_id and o.id = i.order_id " + 
                    "and o.status_id = 2 " +
                    "group by c.cid, c.name, c.email, c.phone " +
                    "order by totalBikes desc " +
                    "limit 10");
            while (rs.next()) {
                dtmHighestBikes.addRow(new Object[]{rs.getInt(1), rs.getString(2),
                                rs.getString(3), rs.getString(4), rs.getInt(5)});
            }
            
            rs = statement.executeQuery("select c.cid, c.name, c.email, c.phone\n" +
                    "from Customer c where not exists (select * from " +
                    "Category cc where not exists (select * from " + 
                    "Order_info o, Item_order i, Bicycle b " +
                    "where o.status_id = 2 and c.cid = o.customer_id and " + 
                    "o.id = i.order_id and i.bicycle_id = b.bid and " + 
                    "b.category_id = cc.cid))");
            while (rs.next()) {
                dtmAllCate.addRow(new Object[]{rs.getInt(1), rs.getString(2),
                                rs.getString(3), rs.getString(4)});
            }
            
            
              rs = statement.executeQuery("select * from ( select c.cid, " + 
                      "c.name, c.email, c.phone from Customer c where not exists " + 
                      "(select * from Category cc where not exists (select * " + 
                      "from Order_info o, Item_order i, Bicycle b " +
                      "where o.status_id = 2 and c.cid = o.customer_id and " + 
                      "o.id = i.order_id and i.bicycle_id = b.bid and " + 
                      "b.category_id = cc.cid))) as T1, (select c.cid, " + 
                      "c.name, c.email from Customer c where not exists " + 
                      "(select * from Manufacturer m where not exists " + 
                      "(select * from Order_info o, Item_order i, Bicycle b " +
                      "where o.status_id = 2 and c.cid = o.customer_id and " + 
                      "o.id = i.order_id and i.bicycle_id = b.bid and " + 
                      "b.manufacturer_id = m.mid))) as T2 where T1.cid = " + 
                      "T2.cid and T1.email = T2.email and T1.name = T2.name");
            while (rs.next()) {
                dtmAllCateAndManu.addRow(new Object[]{rs.getInt(1), rs.getString(2),
                                rs.getString(3), rs.getString(4)});
            }
            
            
            
            
        } catch (SQLException e) {
            
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
        jTableHighestSales.setSize(new java.awt.Dimension(0, 0));
        jScrollPane1.setViewportView(jTableHighestSales);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 464, Short.MAX_VALUE)
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
                        .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, 470, Short.MAX_VALUE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 476, Short.MAX_VALUE))
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
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.getAccessibleContext().setAccessibleDescription("");

        jTabbedPane1.addTab("Customer Report", jPanelCustomerReport);

        javax.swing.GroupLayout jTabbedPanel2Layout = new javax.swing.GroupLayout(jTabbedPanel2);
        jTabbedPanel2.setLayout(jTabbedPanel2Layout);
        jTabbedPanel2Layout.setHorizontalGroup(
            jTabbedPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 932, Short.MAX_VALUE)
        );
        jTabbedPanel2Layout.setVerticalGroup(
            jTabbedPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 526, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Sale by Month", jTabbedPanel2);

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
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 558, Short.MAX_VALUE)
                .addContainerGap(236, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanelCustomerReport;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JPanel jTabbedPanel2;
    private javax.swing.JTable jTableAllCateAndManu;
    private javax.swing.JTable jTableAllCategory;
    private javax.swing.JTable jTableHighestBike;
    private javax.swing.JTable jTableHighestSales;
    // End of variables declaration//GEN-END:variables
}
