/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Connect.DBCreating;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;

/**
 *
 * @author baonguyen
 * Main Frame of Application
 */
public class JFrameMain extends javax.swing.JFrame {

    /**
     * Creates new form NewJFrame
     */
    
    
    public JFrameMain() throws MalformedURLException, IOException {
           initComponents();          
           // Check if DB exist, if not create one
           DBCreating.DBCreating();
           
           ImageIcon icon = new ImageIcon("src/Image/background.jpg");
           this.jLabel3.setIcon(icon);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelMenu = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jToolBarManufacturer = new javax.swing.JToolBar();
        jButtonManufacturer = new javax.swing.JButton();
        jToolBarCategory = new javax.swing.JToolBar();
        jButtonCategory = new javax.swing.JButton();
        jToolBarBicycle = new javax.swing.JToolBar();
        jButtonBicycle = new javax.swing.JButton();
        jToolBarCustomer = new javax.swing.JToolBar();
        jButtonCustomer = new javax.swing.JButton();
        jToolBarOrder = new javax.swing.JToolBar();
        jButtonBicycleOrder = new javax.swing.JButton();
        jToolBarSaleReport = new javax.swing.JToolBar();
        jButtonBicycleSaleReport = new javax.swing.JButton();
        jToolBarOrder1 = new javax.swing.JToolBar();
        jButtonBicycleOrderAdd = new javax.swing.JButton();
        jTabbedPaneMain = new javax.swing.JTabbedPane();
        jPanelMain = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanelMenu.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("American Typewriter", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 0, 0));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("MENU");

        jToolBarManufacturer.setBackground(new java.awt.Color(255, 255, 255));
        jToolBarManufacturer.setFloatable(false);

        jButtonManufacturer.setBackground(new java.awt.Color(255, 255, 255));
        jButtonManufacturer.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        jButtonManufacturer.setText("Manufacturer");
        jButtonManufacturer.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jButtonManufacturer.setBorderPainted(false);
        jButtonManufacturer.setContentAreaFilled(false);
        jButtonManufacturer.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButtonManufacturer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonManufacturerActionPerformed(evt);
            }
        });
        jToolBarManufacturer.add(jButtonManufacturer);

        jToolBarCategory.setBackground(new java.awt.Color(255, 255, 255));
        jToolBarCategory.setFloatable(false);

        jButtonCategory.setBackground(new java.awt.Color(255, 255, 255));
        jButtonCategory.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        jButtonCategory.setText("Category");
        jButtonCategory.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jButtonCategory.setBorderPainted(false);
        jButtonCategory.setContentAreaFilled(false);
        jButtonCategory.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButtonCategory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCategoryActionPerformed(evt);
            }
        });
        jToolBarCategory.add(jButtonCategory);

        jToolBarBicycle.setBackground(new java.awt.Color(255, 255, 255));
        jToolBarBicycle.setFloatable(false);

        jButtonBicycle.setBackground(new java.awt.Color(255, 255, 255));
        jButtonBicycle.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        jButtonBicycle.setText("Bicycle");
        jButtonBicycle.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jButtonBicycle.setBorderPainted(false);
        jButtonBicycle.setContentAreaFilled(false);
        jButtonBicycle.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButtonBicycle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonBicycleActionPerformed(evt);
            }
        });
        jToolBarBicycle.add(jButtonBicycle);

        jToolBarCustomer.setBackground(new java.awt.Color(255, 255, 255));
        jToolBarCustomer.setFloatable(false);

        jButtonCustomer.setBackground(new java.awt.Color(255, 255, 255));
        jButtonCustomer.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        jButtonCustomer.setText("Customer");
        jButtonCustomer.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jButtonCustomer.setBorderPainted(false);
        jButtonCustomer.setContentAreaFilled(false);
        jButtonCustomer.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButtonCustomer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCustomerActionPerformed(evt);
            }
        });
        jToolBarCustomer.add(jButtonCustomer);

        jToolBarOrder.setBackground(new java.awt.Color(255, 255, 255));
        jToolBarOrder.setFloatable(false);

        jButtonBicycleOrder.setBackground(new java.awt.Color(255, 255, 255));
        jButtonBicycleOrder.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        jButtonBicycleOrder.setText("Order");
        jButtonBicycleOrder.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jButtonBicycleOrder.setBorderPainted(false);
        jButtonBicycleOrder.setContentAreaFilled(false);
        jButtonBicycleOrder.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButtonBicycleOrder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonBicycleOrderActionPerformed(evt);
            }
        });
        jToolBarOrder.add(jButtonBicycleOrder);

        jToolBarSaleReport.setBackground(new java.awt.Color(255, 255, 255));
        jToolBarSaleReport.setFloatable(false);

        jButtonBicycleSaleReport.setBackground(new java.awt.Color(255, 255, 255));
        jButtonBicycleSaleReport.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        jButtonBicycleSaleReport.setText("Sale Report");
        jButtonBicycleSaleReport.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jButtonBicycleSaleReport.setBorderPainted(false);
        jButtonBicycleSaleReport.setContentAreaFilled(false);
        jButtonBicycleSaleReport.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButtonBicycleSaleReport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonBicycleSaleReportActionPerformed(evt);
            }
        });
        jToolBarSaleReport.add(jButtonBicycleSaleReport);

        jToolBarOrder1.setBackground(new java.awt.Color(255, 255, 255));
        jToolBarOrder1.setFloatable(false);

        jButtonBicycleOrderAdd.setBackground(new java.awt.Color(255, 255, 255));
        jButtonBicycleOrderAdd.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        jButtonBicycleOrderAdd.setText("Add Order");
        jButtonBicycleOrderAdd.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jButtonBicycleOrderAdd.setBorderPainted(false);
        jButtonBicycleOrderAdd.setContentAreaFilled(false);
        jButtonBicycleOrderAdd.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButtonBicycleOrderAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonBicycleOrderAddActionPerformed(evt);
            }
        });
        jToolBarOrder1.add(jButtonBicycleOrderAdd);

        javax.swing.GroupLayout jPanelMenuLayout = new javax.swing.GroupLayout(jPanelMenu);
        jPanelMenu.setLayout(jPanelMenuLayout);
        jPanelMenuLayout.setHorizontalGroup(
            jPanelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(jPanelMenuLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelMenuLayout.createSequentialGroup()
                .addGroup(jPanelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jToolBarCategory, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jToolBarManufacturer, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jToolBarBicycle, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jToolBarCustomer, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jToolBarOrder, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jToolBarSaleReport, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jToolBarOrder1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanelMenuLayout.setVerticalGroup(
            jPanelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelMenuLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jToolBarManufacturer, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jToolBarCategory, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jToolBarBicycle, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jToolBarCustomer, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jToolBarOrder, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jToolBarOrder1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jToolBarSaleReport, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPaneMain.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanelMainLayout = new javax.swing.GroupLayout(jPanelMain);
        jPanelMain.setLayout(jPanelMainLayout);
        jPanelMainLayout.setHorizontalGroup(
            jPanelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelMainLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 691, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelMainLayout.setVerticalGroup(
            jPanelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelMainLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 506, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPaneMain.addTab("tab1", jPanelMain);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelMenu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTabbedPaneMain)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanelMenu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTabbedPaneMain))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    // Link to Manufacture manager, Manufacture Button handle
    private void jButtonManufacturerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonManufacturerActionPerformed
        // TODO add your handling code here:
        JPanelManufacturer manufacturerManager = new JPanelManufacturer();
        this.jTabbedPaneMain.removeAll();
        this.jTabbedPaneMain.add("Manufacturer Manager", manufacturerManager);
    }//GEN-LAST:event_jButtonManufacturerActionPerformed

    // Link to Category manager, Category Button handle
    private void jButtonCategoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCategoryActionPerformed
        // TODO add your handling code here:
        JPanelCategory categoryManager = new JPanelCategory();
        this.jTabbedPaneMain.removeAll();
        this.jTabbedPaneMain.add("Category Manager", categoryManager);
    }//GEN-LAST:event_jButtonCategoryActionPerformed

    private void jButtonBicycleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonBicycleActionPerformed
        // TODO add your handling code here:
        JPanelBicycle bicycleManager = new JPanelBicycle();
        this.jTabbedPaneMain.removeAll();
        this.jTabbedPaneMain.add("Bicycle Manager", bicycleManager);
    }//GEN-LAST:event_jButtonBicycleActionPerformed

    private void jButtonCustomerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCustomerActionPerformed
        // TODO add your handling code here:
        JPanelCustomer customerManager = new JPanelCustomer();
        this.jTabbedPaneMain.removeAll();
        this.jTabbedPaneMain.add("Customer Manager", customerManager);
    }//GEN-LAST:event_jButtonCustomerActionPerformed

    private void jButtonBicycleOrderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonBicycleOrderActionPerformed
        // TODO add your handling code here:
        JPanelOrder orderManager = new JPanelOrder();
        this.jTabbedPaneMain.removeAll();
        this.jTabbedPaneMain.add("Order Manager", orderManager);
    }//GEN-LAST:event_jButtonBicycleOrderActionPerformed

    private void jButtonBicycleSaleReportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonBicycleSaleReportActionPerformed
        // TODO add your handling code here:
        JPanelSaleReport saleReportManager = new JPanelSaleReport();
        this.jTabbedPaneMain.removeAll();
        this.jTabbedPaneMain.add("Sale Report Manager", saleReportManager);
    }//GEN-LAST:event_jButtonBicycleSaleReportActionPerformed

    private void jButtonBicycleOrderAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonBicycleOrderAddActionPerformed
        // TODO add your handling code here:
        JPanelAddOrder addOrderManager = new JPanelAddOrder();
        this.jTabbedPaneMain.removeAll();
        this.jTabbedPaneMain.add("Add New Order", addOrderManager);
    }//GEN-LAST:event_jButtonBicycleOrderAddActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(JFrameMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JFrameMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JFrameMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JFrameMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new JFrameMain().setVisible(true);
                } catch (IOException ex) {
                    Logger.getLogger(JFrameMain.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonBicycle;
    private javax.swing.JButton jButtonBicycleOrder;
    private javax.swing.JButton jButtonBicycleOrderAdd;
    private javax.swing.JButton jButtonBicycleSaleReport;
    private javax.swing.JButton jButtonCategory;
    private javax.swing.JButton jButtonCustomer;
    private javax.swing.JButton jButtonManufacturer;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanelMain;
    private javax.swing.JPanel jPanelMenu;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTabbedPane jTabbedPaneMain;
    private javax.swing.JToolBar jToolBarBicycle;
    private javax.swing.JToolBar jToolBarCategory;
    private javax.swing.JToolBar jToolBarCustomer;
    private javax.swing.JToolBar jToolBarManufacturer;
    private javax.swing.JToolBar jToolBarOrder;
    private javax.swing.JToolBar jToolBarOrder1;
    private javax.swing.JToolBar jToolBarSaleReport;
    // End of variables declaration//GEN-END:variables
}
