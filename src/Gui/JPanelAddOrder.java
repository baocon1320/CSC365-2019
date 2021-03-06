/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Connect.DBConnection;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.sql.Date;
import java.time.format.DateTimeFormatter;  
import java.time.LocalDateTime;  
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author austinwoo
 * Add New order
 */
public class JPanelAddOrder extends javax.swing.JPanel {
    private DefaultTableModel dtmCustomers = new DefaultTableModel();
    private DefaultTableModel dtmBicycles = new DefaultTableModel();// For Table
    //private DefaultTableModel dtmOrderHistory = new DefaultTableModel();
    private DefaultTableModel dtmSelectedBicycles = new DefaultTableModel();
    Connection connect = DBConnection.getConnection();
    Statement statement;
    private int selectedId = -1;
    private int selectedCustomer = -1;
    private Set<Integer> bikeSet = new HashSet<>();
    /**
     * Creates new form JPanelAddOrder
     */
    public JPanelAddOrder() {
        initComponents();
        try {
            statement = connect.createStatement();
        } catch (SQLException e) {
            
        }
        loadData();
    }
    
    private void loadData(){
        loadCustomers();
        loadBicycles();
        loadSelectedBikes();
    }
    
    private void loadSelectedBikes() {
        String headers[] = {"Bike Id", "Model", "Price", "Quantity"};
        dtmSelectedBicycles.setRowCount(0);
        dtmSelectedBicycles.setColumnIdentifiers(headers);
        this.jTableBikeslist.setModel(dtmSelectedBicycles);
        
    }
    private void loadCustomers(){
        String headers[] = {"Id", "Name", "Email", "Phone  Number"};
        
        // Set # of row to 0
        dtmCustomers.setRowCount(0);
        dtmCustomers.setColumnIdentifiers(headers);
        
        this.jTableCustomers.setModel(dtmCustomers);
        try{
            ResultSet rs = statement.executeQuery("SELECT * FROM Customer");
            while(rs.next()){
                dtmCustomers.addRow(new Object[]{rs.getInt(1), rs.getString(2),
                            rs.getString(3), rs.getString(4)});
            }
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
            System.exit(1);
        }
        
    }
    private void loadBicycles(){
        String headers[] = {"Id", "Model", "Price", "Stock", "Manufacturer", "Category"};
        // Set # of row to 0
        dtmBicycles.setRowCount(0);
        dtmBicycles.setColumnIdentifiers(headers);
        this.jTableBicycles.setModel(dtmBicycles);
        try{
            ResultSet rs = statement.executeQuery("SELECT * FROM Bicycle");
            while(rs.next()){
                dtmBicycles.addRow(new Object[]{rs.getInt(1), rs.getString(2), rs.getDouble(3), 
                        rs.getInt(4), rs.getInt(5),rs.getInt(6)});
            }
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
            System.exit(1);
        }
    }
    
    private void reset() {
        this.dtmSelectedBicycles.setRowCount(0);
        this.jTextFieldCustomerId.setText("");
        this.jTextFieldCustomerName.setText("");
        this.jTextFieldCustomerEmail.setText("");
        this.jTextFieldPhone.setText("");
        this.jTextFieldTotalAmount.setText("");
    }
    private void updateTotalAmountTextfiled(){
        double sum = 0;
        for(int i = 0; i < jTableBikeslist.getRowCount(); i++){
            sum += Double.parseDouble(this.jTableBikeslist.getValueAt(i, 2).toString()) * 
                    (Integer) this.jTableBikeslist.getValueAt(i, 3);
        }
        //return sum;
        this.jTextFieldTotalAmount.setText(Double.toString(sum));
    }
    
    private void updateCustomerInfo(){
        int custRow = jTableCustomers.getSelectedRow();
        selectedCustomer = (Integer) this.jTableCustomers.getValueAt(custRow, 0);
        this.jTextFieldCustomerId.setText(this.jTableCustomers.getValueAt(custRow, 0).toString());
        this.jTextFieldCustomerName.setText(this.jTableCustomers.getValueAt(custRow, 1).toString());
        this.jTextFieldCustomerEmail.setText(this.jTableCustomers.getValueAt(custRow, 2).toString());
        this.jTextFieldPhone.setText(this.jTableCustomers.getValueAt(custRow, 3).toString());
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTableCustomers = new javax.swing.JTable();
        jButtonChooseCustomer = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTableBicycles = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        QuantityTextField = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jTextFieldCustomerId = new javax.swing.JTextField();
        jTextFieldCustomerName = new javax.swing.JTextField();
        jTextFieldCustomerEmail = new javax.swing.JTextField();
        jTextFieldPhone = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jTextFieldTotalAmount = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableBikeslist = new javax.swing.JTable();
        jButtonDeleteBicycleRow = new javax.swing.JButton();
        jButtonAddOrder = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));
        setPreferredSize(new java.awt.Dimension(1440, 663));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Choose a Customer"));
        jPanel2.setToolTipText("");
        jPanel2.setPreferredSize(new java.awt.Dimension(570, 350));

        jTableCustomers.setModel(new javax.swing.table.DefaultTableModel(
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
        jTableCustomers.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableCustomersMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(jTableCustomers);

        jButtonChooseCustomer.setText("Choose Customer");
        jButtonChooseCustomer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonChooseCustomerActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 540, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(211, 211, 211)
                        .addComponent(jButtonChooseCustomer)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButtonChooseCustomer)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Add Bicycles"));
        jPanel3.setPreferredSize(new java.awt.Dimension(570, 350));

        jTableBicycles.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane4.setViewportView(jTableBicycles);

        jLabel2.setText("Quantity");

        jButton1.setBackground(new java.awt.Color(51, 153, 255));
        jButton1.setText("Add");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 540, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(177, 177, 177)
                        .addComponent(jLabel2)
                        .addGap(84, 84, 84)
                        .addComponent(QuantityTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addGap(228, 228, 228))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel2)
                        .addGap(6, 6, 6))
                    .addComponent(QuantityTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Add New Order"));

        jLabel1.setText("Customer Name: ");

        jLabel3.setText("Customer Email:");

        jLabel4.setText("Customer Phone: ");

        jLabel5.setText("Customer Id:");

        jTextFieldCustomerId.setEnabled(false);

        jTextFieldCustomerName.setEnabled(false);

        jTextFieldCustomerEmail.setEnabled(false);

        jTextFieldPhone.setEnabled(false);

        jLabel6.setText("Total Amount:");

        jTextFieldTotalAmount.setText("0.0");
        jTextFieldTotalAmount.setEnabled(false);
        jTextFieldTotalAmount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldTotalAmountActionPerformed(evt);
            }
        });

        jTableBikeslist.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(jTableBikeslist);

        jButtonDeleteBicycleRow.setText("Remove a Model");
        jButtonDeleteBicycleRow.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDeleteBicycleRowActionPerformed(evt);
            }
        });

        jButtonAddOrder.setText("Add Order");
        jButtonAddOrder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAddOrderActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(33, 33, 33)
                        .addComponent(jTextFieldCustomerId))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextFieldCustomerEmail))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldCustomerName))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextFieldPhone)
                            .addComponent(jTextFieldTotalAmount, javax.swing.GroupLayout.DEFAULT_SIZE, 327, Short.MAX_VALUE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 514, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(186, 186, 186)
                .addComponent(jButtonAddOrder, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButtonDeleteBicycleRow)
                .addGap(223, 223, 223))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jTextFieldCustomerId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(7, 7, 7)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextFieldCustomerName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jTextFieldCustomerEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jTextFieldPhone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jTextFieldTotalAmount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonDeleteBicycleRow)
                    .addComponent(jButtonAddOrder, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 53, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 564, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 558, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(294, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 282, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 282, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(44, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jTableCustomersMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableCustomersMouseClicked
        // TODO add your handling code here:
         
    }//GEN-LAST:event_jTableCustomersMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
         // TODO add your handling code here:
        
         
        int bikeRow = jTableBicycles.getSelectedRow();
        int quantity = -1;
        try {
            quantity = Integer.parseInt(QuantityTextField.getText());
        } catch (Exception e) {
            JOptionPane.showMessageDialog ( null, "Quantity is not correct" );
            return;
        }
         
        if(bikeRow == -1 || quantity <= 0){
            JOptionPane.showMessageDialog ( null, "Select a Bicycle or correct number of quantity" );
            return;
        }
        int bikeId = (Integer) jTableBicycles.getValueAt(bikeRow, 0);
        int stock = (Integer) jTableBicycles.getValueAt(bikeRow, 3);
        if(quantity > stock){
            JOptionPane.showMessageDialog ( null, "Over number in stock" );
            return;
        }
        if(bikeSet.contains(bikeId)) {
            JOptionPane.showMessageDialog ( null, "This Bike is already in" );
            return;
        } 
        bikeSet.add(bikeId);
        dtmSelectedBicycles.addRow(new Object[]{jTableBicycles.getValueAt(bikeRow, 0).toString(), 
            jTableBicycles.getValueAt(bikeRow, 1).toString(), jTableBicycles.getValueAt(bikeRow, 2).toString(),
        quantity});
        this.QuantityTextField.setText("");
        updateTotalAmountTextfiled();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTextFieldTotalAmountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldTotalAmountActionPerformed
       
    }//GEN-LAST:event_jTextFieldTotalAmountActionPerformed

    private void jButtonChooseCustomerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonChooseCustomerActionPerformed
        // TODO add your handling code here:
        int custRow = jTableCustomers.getSelectedRow();
        if(custRow == -1){
            JOptionPane.showMessageDialog ( null, "Select a Customer" );
            return;
        }
        updateCustomerInfo();
    }//GEN-LAST:event_jButtonChooseCustomerActionPerformed

    private void jButtonAddOrderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAddOrderActionPerformed
        // TODO add your handling code here:
        // TODO add your handling code here:
        
        if(selectedCustomer == -1) {
            JOptionPane.showMessageDialog ( null, "Select a Customer to add order" );
            return;
        }
        if(jTextFieldTotalAmount.getText().equals("0.0")) {
            JOptionPane.showMessageDialog ( null, "Select bikes to add order" );
            return;
        }
        
        double amount  = Double.parseDouble(jTextFieldTotalAmount.getText());
        long millis=System.currentTimeMillis();
        Date date= new java.sql.Date(millis);
        int order_id = -1;
        String bicycle_id = "";
        String quantity = "";
        String price = "";
        try{
            statement.executeUpdate("insert into Order_info(customer_id, price, date_order, status_id) values ("+selectedCustomer+ ", "
                +amount+", "
                +"'"+date+"'"+", "
                +1+");");
            
            ResultSet rs = statement.executeQuery("select last_insert_id()");
            
            while(rs.next()){
                order_id = rs.getInt(1);
            }
            
           
            for(int i = 0; i < jTableBikeslist.getRowCount(); i++){
                bicycle_id = jTableBikeslist.getValueAt(i, 0).toString();
                price = jTableBikeslist.getValueAt(i, 2).toString();
                quantity = jTableBikeslist.getValueAt(i, 3).toString();
                addItemOrder(order_id,bicycle_id,price,quantity);
            }
            reset();
            loadBicycles();
            JOptionPane.showMessageDialog(null, "Add Order Succeeded");
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(null, "Add Order failure");
            //System.exit(1);
        }
    }//GEN-LAST:event_jButtonAddOrderActionPerformed

    private void jButtonDeleteBicycleRowActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDeleteBicycleRowActionPerformed
        // TODO add your handling code here:
        int bikeRow = jTableBikeslist.getSelectedRow();     
        if(bikeRow == -1){
            JOptionPane.showMessageDialog ( null, "Select a model to remove" );
            return;
        }
        dtmSelectedBicycles.removeRow(bikeRow);
        updateTotalAmountTextfiled();
    }//GEN-LAST:event_jButtonDeleteBicycleRowActionPerformed

    private void addItemOrder(int orderId,String bikeId,String price,String quantity){
        try{
           
            statement.executeUpdate("insert into Item_order values("+
                    bikeId+" ,"+
                    orderId+", "+
                    price+", "+
                    quantity+")");
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(null, "Add Order failure");
            //System.exit(1);
        }
        
    }    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField QuantityTextField;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButtonAddOrder;
    private javax.swing.JButton jButtonChooseCustomer;
    private javax.swing.JButton jButtonDeleteBicycleRow;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTable jTableBicycles;
    private javax.swing.JTable jTableBikeslist;
    private javax.swing.JTable jTableCustomers;
    private javax.swing.JTextField jTextFieldCustomerEmail;
    private javax.swing.JTextField jTextFieldCustomerId;
    private javax.swing.JTextField jTextFieldCustomerName;
    private javax.swing.JTextField jTextFieldPhone;
    private javax.swing.JTextField jTextFieldTotalAmount;
    // End of variables declaration//GEN-END:variables
}
