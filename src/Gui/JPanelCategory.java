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
public class JPanelCategory extends javax.swing.JPanel {

    /**
     * Creates new form JPanelManufacturer
     */
    
    // Private Properties
    private DefaultTableModel dtm = new DefaultTableModel(); // For Table
    Connection connect = DBConnection.getConnection();
    Statement statement;
    private int selectedId = -1;
    
    public JPanelCategory(){
        initComponents();
        try {
            statement = connect.createStatement();
        } catch (SQLException e) {
            
        }
        //statement = connect.createStatement();
        loadTable();
    }
    
    // Load Headers and row for Manufacturer Table
    private void loadTable(){
        // Headers
        String headers[] = {"Id", "Category Name"};
        
        // Set # of row to 0
        dtm.setRowCount(0);
        dtm.setColumnIdentifiers(headers);
        this.jTableCategory.setModel(dtm);
        try {
            ResultSet rs = statement.executeQuery("Select * from Category");
            while (rs.next()) {
                dtm.addRow(new Object[]{rs.getInt(1), rs.getString(2)});
            }
        } catch (SQLException e) {
            
        } 
    }
    
    // Reset the textfiled and load table
    private void reset(){
        this.jTextFieldId.setText("");
        this.jTextFieldCategoryName.setText("");
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

        jPanelCategoryList = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableCategory = new javax.swing.JTable();
        jPanelCategory = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jTextFieldId = new javax.swing.JTextField();
        jTextFieldCategoryName = new javax.swing.JTextField();
        jButtonDelete = new javax.swing.JButton();
        jButtonAdd = new javax.swing.JButton();
        jButtonUpdate = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));

        jPanelCategoryList.setBackground(new java.awt.Color(255, 255, 255));
        jPanelCategoryList.setBorder(javax.swing.BorderFactory.createTitledBorder("Category List"));
        jPanelCategoryList.setToolTipText("Category List");
        jPanelCategoryList.setName("Manufacturer List"); // NOI18N

        jTableCategory.setModel(new javax.swing.table.DefaultTableModel(
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
        jTableCategory.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableCategoryMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTableCategory);

        javax.swing.GroupLayout jPanelCategoryListLayout = new javax.swing.GroupLayout(jPanelCategoryList);
        jPanelCategoryList.setLayout(jPanelCategoryListLayout);
        jPanelCategoryListLayout.setHorizontalGroup(
            jPanelCategoryListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelCategoryListLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        jPanelCategoryListLayout.setVerticalGroup(
            jPanelCategoryListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelCategoryListLayout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 29, Short.MAX_VALUE))
        );

        jPanelCategory.setBackground(new java.awt.Color(255, 255, 255));
        jPanelCategory.setBorder(javax.swing.BorderFactory.createTitledBorder("Category Detail"));
        jPanelCategory.setToolTipText("Category Detail");

        jLabel1.setText("Id");

        jLabel2.setText("Category Name");

        jTextFieldId.setEnabled(false);
        jTextFieldId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldIdActionPerformed(evt);
            }
        });

        jTextFieldCategoryName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldCategoryNameActionPerformed(evt);
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

        jButtonUpdate.setText("Update");
        jButtonUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonUpdateActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelCategoryLayout = new javax.swing.GroupLayout(jPanelCategory);
        jPanelCategory.setLayout(jPanelCategoryLayout);
        jPanelCategoryLayout.setHorizontalGroup(
            jPanelCategoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelCategoryLayout.createSequentialGroup()
                .addGroup(jPanelCategoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelCategoryLayout.createSequentialGroup()
                        .addGap(58, 58, 58)
                        .addGroup(jPanelCategoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)))
                    .addGroup(jPanelCategoryLayout.createSequentialGroup()
                        .addGap(87, 87, 87)
                        .addComponent(jButtonAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(46, 46, 46)
                .addGroup(jPanelCategoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelCategoryLayout.createSequentialGroup()
                        .addComponent(jButtonUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(50, 50, 50)
                        .addComponent(jButtonDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelCategoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jTextFieldId)
                        .addComponent(jTextFieldCategoryName, javax.swing.GroupLayout.DEFAULT_SIZE, 227, Short.MAX_VALUE)))
                .addContainerGap(95, Short.MAX_VALUE))
        );
        jPanelCategoryLayout.setVerticalGroup(
            jPanelCategoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelCategoryLayout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(jPanelCategoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextFieldId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanelCategoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTextFieldCategoryName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 59, Short.MAX_VALUE)
                .addGroup(jPanelCategoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(37, 37, 37))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanelCategory, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanelCategoryList, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jPanelCategoryList, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanelCategory, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanelCategoryList.getAccessibleContext().setAccessibleName("Category List");
    }// </editor-fold>//GEN-END:initComponents

    private void jTextFieldCategoryNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldCategoryNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldCategoryNameActionPerformed
    
    // Handle event when user click on table
    private void jTableCategoryMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableCategoryMouseClicked
        // TODO add your handling code here:
        int row = this.jTableCategory.getSelectedRow();
        selectedId = (Integer) this.jTableCategory.getValueAt(row, 0);
        this.jTextFieldId.setText(this.jTableCategory.getValueAt(row, 0).toString());
        this.jTextFieldCategoryName.setText(this.jTableCategory.getValueAt(row, 1).toString());
        
    }//GEN-LAST:event_jTableCategoryMouseClicked

    private void jTextFieldIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldIdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldIdActionPerformed
    
    
    // Update Category
    private void jButtonUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonUpdateActionPerformed
        // TODO add your handling code here:
        if(this.jTableCategory.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(null, "Choose a category to moldify");
        } else {
            try {
                statement.executeUpdate("update Category set name = '" +
                        this.jTextFieldCategoryName.getText() + "' where cid = " + selectedId);
               
                JOptionPane.showMessageDialog(null, "Update Succeded");
                reset();
            } catch (SQLException e) {
                //String updateString = "upadte Manufacturer set name = ? where mid = ?";
                System.out.println(e.getErrorCode());
                JOptionPane.showMessageDialog(null, "Update Failed");
                
                
            }
        }
    }//GEN-LAST:event_jButtonUpdateActionPerformed

    //Delete Button
    private void jButtonDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDeleteActionPerformed
        // TODO add your handling code here:
         if(this.jTableCategory.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(null, "Choose a category to delete");
        } else {
            try {
                
                statement.executeUpdate("delete from Category where cid = " + selectedId);
               
                JOptionPane.showMessageDialog(null, "Delete Succeded");
                reset();
            } catch (SQLException e) {
                //String updateString = "upadte Manufacturer set name = ? where mid = ?";
                System.out.println(e.getErrorCode());
                JOptionPane.showMessageDialog(null, "Detele Failed");
                
                
            }
        }
    }//GEN-LAST:event_jButtonDeleteActionPerformed

    // Add Button
    private void jButtonAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAddActionPerformed
        // TODO add your handling code here:
        try {
                
                statement.executeUpdate("insert into Category(name) values ('" + 
                        this.jTextFieldCategoryName.getText() + "')");
               
                JOptionPane.showMessageDialog(null, "Add Category Succeded");
                reset();
            } catch (SQLException e) {
                //String updateString = "upadte Manufacturer set name = ? where mid = ?";
                System.out.println(e.getErrorCode());
                JOptionPane.showMessageDialog(null, "Add Category Failed");             
            }
    }//GEN-LAST:event_jButtonAddActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAdd;
    private javax.swing.JButton jButtonDelete;
    private javax.swing.JButton jButtonUpdate;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanelCategory;
    private javax.swing.JPanel jPanelCategoryList;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableCategory;
    private javax.swing.JTextField jTextFieldCategoryName;
    private javax.swing.JTextField jTextFieldId;
    // End of variables declaration//GEN-END:variables
}
