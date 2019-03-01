/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

/**
 *
 * @author baonguyen
 */

import java.sql.Connection;
import javax.swing.table.DefaultTableModel;
import Connect.DBConnection;
import Model.Category;
import Model.Manufacturer;
import java.awt.HeadlessException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.JOptionPane;

public class JPanelBicycle extends javax.swing.JPanel {
    
    // Private Properties
    private DefaultTableModel dtm = new DefaultTableModel(); // For Table
    Connection connect = DBConnection.getConnection();
    Statement statement;
    private int selectedId = -1;
    
    // List of all categories
    private ArrayList<Category> categories = new ArrayList<>();
    
    // List of all Manufacturers;
    private ArrayList<Manufacturer> manufacturers =  new ArrayList<>();
    
    /**
     * Creates new form JPanelBicycle
     */
    public JPanelBicycle() {
        initComponents();
        
        try {
            statement = connect.createStatement();
        } catch (SQLException e) {
            
        }
        
        loadData();
    }
    
    
    // Load the Data for the frame
    private void loadData() {
        loadTable();
        loadComboBox();
        
    }
    
    
     // Load Headers and row for Bicycle Table
    private void loadTable(){
        // Headers
        String headers[] = {"Id", "Model", "Price", "Stock", "Manufacturer", "Category"};
        
        // Set # of row to 0
        dtm.setRowCount(0);
        dtm.setColumnIdentifiers(headers);
        
        this.jTableBicycle.setModel(dtm);
        try {
            ResultSet rs = statement.executeQuery("select * from Bicycle b, Manufacturer m, "
                    + "      Category c where b.manufacturer_id = m.mid and b.category_id = c.cid");
            while (rs.next()) {
                dtm.addRow(new Object[]{rs.getInt(1), rs.getString(2), rs.getDouble("price"), 
                        rs.getInt("stock"), rs.getString("m.name"), rs.getString("c.name")});
            }
        } catch (SQLException e) {
            
        } 
    }
    
    // Load the comboBox of Category and Manufacturer
    private void loadComboBox() {
        try {
            ResultSet rs = statement.executeQuery("select * from Manufacturer");
            while (rs.next()) {
                manufacturers.add(new Manufacturer(rs.getInt(1), rs.getString(2)));         
            }
            rs = statement.executeQuery("select * from Category");
            while (rs.next()) {
                
                categories.add(new Category(rs.getInt(1), rs.getString(2)));         
            }
             
            this.jComboBoxCategory.removeAllItems();
            this.jComboBoxManufacturer.removeAllItems();
            
            for(int i = 0; i < manufacturers.size(); i++) {
                //System.out.println(manufacturers.get(i).getName());
                this.jComboBoxManufacturer.addItem(manufacturers.get(i).getName());
            }
            
            for(int i = 0; i < categories.size(); i++) {
                //System.out.println(categories.get(i).getName());
                this.jComboBoxCategory.addItem(categories.get(i).getName());
            }
                
            
        } catch (SQLException e) {
            
        } 
    }
    
    // Reset the textfiled and load table
    private void reset(){
        this.jTextFieldId.setText("");
        this.jTextFieldIdModel.setText("");
        this.jTextFieldIdPrice.setText("");
        this.jTextFieldIdStock.setText("");
        loadTable();
    }
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelBicycleTable = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableBicycle = new javax.swing.JTable();
        jPanelBicycleInfo = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTextFieldId = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jTextFieldIdModel = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jTextFieldIdPrice = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jTextFieldIdStock = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jComboBoxManufacturer = new javax.swing.JComboBox<>();
        jComboBoxCategory = new javax.swing.JComboBox<>();
        jButtonUpdate = new javax.swing.JButton();
        jButtonDelete = new javax.swing.JButton();
        jButtonAdd = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));

        jPanelBicycleTable.setBackground(new java.awt.Color(255, 255, 255));
        jPanelBicycleTable.setBorder(javax.swing.BorderFactory.createTitledBorder("Bicycle List"));

        jTableBicycle.setModel(new javax.swing.table.DefaultTableModel(
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
        jTableBicycle.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableBicycleMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTableBicycle);

        javax.swing.GroupLayout jPanelBicycleTableLayout = new javax.swing.GroupLayout(jPanelBicycleTable);
        jPanelBicycleTable.setLayout(jPanelBicycleTableLayout);
        jPanelBicycleTableLayout.setHorizontalGroup(
            jPanelBicycleTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelBicycleTableLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        jPanelBicycleTableLayout.setVerticalGroup(
            jPanelBicycleTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelBicycleTableLayout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 291, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 9, Short.MAX_VALUE))
        );

        jPanelBicycleInfo.setBackground(new java.awt.Color(255, 255, 255));
        jPanelBicycleInfo.setBorder(javax.swing.BorderFactory.createTitledBorder("Bicycle Detail"));

        jLabel1.setText("Id");

        jTextFieldId.setEnabled(false);
        jTextFieldId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldIdActionPerformed(evt);
            }
        });

        jLabel2.setText("Model");

        jTextFieldIdModel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldIdModelActionPerformed(evt);
            }
        });

        jLabel3.setText("Price");

        jTextFieldIdPrice.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldIdPriceActionPerformed(evt);
            }
        });

        jLabel4.setText("Stock");

        jTextFieldIdStock.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldIdStockActionPerformed(evt);
            }
        });

        jLabel5.setText("Manufacturer");

        jLabel6.setText("Category");

        jButtonUpdate.setText("Update");
        jButtonUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonUpdateActionPerformed(evt);
            }
        });

        jButtonDelete.setText("Delete");
        jButtonDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDeleteActionPerformed(evt);
            }
        });

        jButtonAdd.setText("Add");
        jButtonAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAddActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelBicycleInfoLayout = new javax.swing.GroupLayout(jPanelBicycleInfo);
        jPanelBicycleInfo.setLayout(jPanelBicycleInfoLayout);
        jPanelBicycleInfoLayout.setHorizontalGroup(
            jPanelBicycleInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelBicycleInfoLayout.createSequentialGroup()
                .addGap(32, 187, Short.MAX_VALUE)
                .addGroup(jPanelBicycleInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelBicycleInfoLayout.createSequentialGroup()
                        .addGroup(jPanelBicycleInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel5)
                            .addComponent(jButtonAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(16, 16, 16)
                        .addGroup(jPanelBicycleInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanelBicycleInfoLayout.createSequentialGroup()
                                .addComponent(jComboBoxManufacturer, javax.swing.GroupLayout.PREFERRED_SIZE, 379, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanelBicycleInfoLayout.createSequentialGroup()
                                .addGroup(jPanelBicycleInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jComboBoxCategory, javax.swing.GroupLayout.PREFERRED_SIZE, 379, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanelBicycleInfoLayout.createSequentialGroup()
                                        .addGap(46, 46, 46)
                                        .addComponent(jButtonUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jButtonDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(jPanelBicycleInfoLayout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanelBicycleInfoLayout.createSequentialGroup()
                        .addGroup(jPanelBicycleInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextFieldIdStock, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 380, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanelBicycleInfoLayout.createSequentialGroup()
                                .addGroup(jPanelBicycleInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel3))
                                .addGap(99, 99, 99)
                                .addGroup(jPanelBicycleInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextFieldIdModel, javax.swing.GroupLayout.PREFERRED_SIZE, 380, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextFieldIdPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 380, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextFieldId, javax.swing.GroupLayout.PREFERRED_SIZE, 380, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(194, 194, 194))))
        );
        jPanelBicycleInfoLayout.setVerticalGroup(
            jPanelBicycleInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelBicycleInfoLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanelBicycleInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextFieldId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelBicycleInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldIdModel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelBicycleInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jTextFieldIdPrice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelBicycleInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jTextFieldIdStock, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelBicycleInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jComboBoxManufacturer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelBicycleInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jComboBoxCategory, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanelBicycleInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 22, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanelBicycleInfo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanelBicycleTable, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jPanelBicycleTable, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanelBicycleInfo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jTextFieldIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldIdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldIdActionPerformed

    private void jTextFieldIdModelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldIdModelActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldIdModelActionPerformed

    private void jTextFieldIdPriceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldIdPriceActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldIdPriceActionPerformed

    private void jTextFieldIdStockActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldIdStockActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldIdStockActionPerformed

    private void jButtonUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonUpdateActionPerformed
        // TODO add your handling code here:
        // TODO add your handling code here:
        if(this.jTableBicycle.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(null, "Choose a bicycle to moldify");
        } else {
            try {
                int manuIndex = manufacturers.get(this.jComboBoxManufacturer.getSelectedIndex()).getId();
                int cateIndex = categories.get(this.jComboBoxCategory.getSelectedIndex()).getId();
                String updateString = "update Bicycle set model = ?, price = ?," +
"                        stock = ?, manufacturer_id = ?, category_id = ? where" +
"                        bid = ?";
                PreparedStatement updateBicycle = connect.prepareStatement(updateString);
                updateBicycle.setString(1, this.jTextFieldIdModel.getText());
                updateBicycle.setDouble(2, Double.valueOf(this.jTextFieldIdPrice.getText()));
                updateBicycle.setInt(3, Integer.valueOf(this.jTextFieldIdStock.getText()));
                updateBicycle.setInt(4, manuIndex);
                updateBicycle.setInt(5, cateIndex);
                updateBicycle.setInt(6, selectedId);
                updateBicycle.executeUpdate();
                JOptionPane.showMessageDialog(null, "Update Bicycle Succeded");
                reset();
            } catch (HeadlessException | NumberFormatException | SQLException e) {
                //String updateString = "upadte Manufacturer set name = ? where mid = ?";
                System.out.println(Arrays.toString(e.getStackTrace()));
                JOptionPane.showMessageDialog(null, "Update Failed");
                
                
            }
        }
        
    }//GEN-LAST:event_jButtonUpdateActionPerformed

    private void jButtonDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDeleteActionPerformed
        // TODO add your handling code here:
         if(this.jTableBicycle.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(null, "Choose a bicycle to delete");
        } else {
            try {               
                statement.executeUpdate("delete from Bicycle where bid = " + selectedId);
               
                JOptionPane.showMessageDialog(null, "Delete Succeded");
                reset();
            } catch (SQLException e) {
                //String updateString = "upadte Manufacturer set name = ? where mid = ?";
                System.out.println(e.getErrorCode());
                JOptionPane.showMessageDialog(null, "Detele Failed");
                
                
            }
        }
    }//GEN-LAST:event_jButtonDeleteActionPerformed

    private void jButtonAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAddActionPerformed
        // TODO add your handling code here:
         try {
                int manuIndex = manufacturers.get(this.jComboBoxManufacturer.getSelectedIndex()).getId();
                int cateIndex = categories.get(this.jComboBoxCategory.getSelectedIndex()).getId();
                String addString = "insert into Bicycle(model, price, stock, "
                        + "manufacturer_id, category_id) values(?, ?, ?, ?, ?)";
                PreparedStatement addBicycle = connect.prepareStatement(addString);
                addBicycle.setString(1, this.jTextFieldIdModel.getText());
                addBicycle.setDouble(2, Double.valueOf(this.jTextFieldIdPrice.getText()));
                addBicycle.setInt(3, Integer.valueOf(this.jTextFieldIdStock.getText()));
                addBicycle.setInt(4, manuIndex);
                addBicycle.setInt(5, cateIndex);
                addBicycle.executeUpdate();
                JOptionPane.showMessageDialog(null, "Add Bicycle Succeded");
                reset();
            } catch (HeadlessException | NumberFormatException | SQLException e) {
                //String updateString = "upadte Manufacturer set name = ? where mid = ?";
                System.out.println(Arrays.toString(e.getStackTrace()));
                JOptionPane.showMessageDialog(null, "Add Bicycle Failed");             
            }
    }//GEN-LAST:event_jButtonAddActionPerformed

    private void jTableBicycleMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableBicycleMouseClicked
        // TODO add your handling code here:
        int row = this.jTableBicycle.getSelectedRow();
        
        //Get current Id
        selectedId = (Integer) this.jTableBicycle.getValueAt(row, 0);
        
        this.jTextFieldId.setText(this.jTableBicycle.getValueAt(row, 0).toString());
        this.jTextFieldIdModel.setText(this.jTableBicycle.getValueAt(row, 1).toString());
        this.jTextFieldIdPrice.setText(this.jTableBicycle.getValueAt(row, 2).toString());
        this.jTextFieldIdStock.setText(this.jTableBicycle.getValueAt(row, 3).toString());
        this.jComboBoxManufacturer.setSelectedItem(this.jTableBicycle.getValueAt(row, 4).toString());
        this.jComboBoxCategory.setSelectedItem(this.jTableBicycle.getValueAt(row, 5).toString());
        
    }//GEN-LAST:event_jTableBicycleMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAdd;
    private javax.swing.JButton jButtonDelete;
    private javax.swing.JButton jButtonUpdate;
    private javax.swing.JComboBox<String> jComboBoxCategory;
    private javax.swing.JComboBox<String> jComboBoxManufacturer;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanelBicycleInfo;
    private javax.swing.JPanel jPanelBicycleTable;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableBicycle;
    private javax.swing.JTextField jTextFieldId;
    private javax.swing.JTextField jTextFieldIdModel;
    private javax.swing.JTextField jTextFieldIdPrice;
    private javax.swing.JTextField jTextFieldIdStock;
    // End of variables declaration//GEN-END:variables
}