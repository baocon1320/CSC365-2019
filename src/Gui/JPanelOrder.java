/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Connect.DBConnection;
import Model.OrderStatus;
import jdk.nashorn.internal.scripts.JO;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

/**
 * @author baonguyen and wesley benica
 */
public class JPanelOrder extends javax.swing.JPanel {

    // For Table Order
    private DefaultTableModel dtmOrder = new DefaultTableModel ( );

    //For Table Item-Order
    private DefaultTableModel dtmItemOrder = new DefaultTableModel ( );

    // List of all orderStatus
    Connection connect = DBConnection.getConnection ( );
    Statement  statement;

    /**
     * Creates new form JPanelOrder
     */
    public JPanelOrder ( ) {

        initComponents ( );
        try {
            statement = connect.createStatement ( );
        } catch ( SQLException e ) {

        }
        loadData ( );
    }

    //Load Data for jPanel
    private void loadData ( ) {

        loadTableHeaders ( );
        loadOrderStatus ( );
        loadOrderTable ( );
//        loadItemOrderTable ( );
    }

    // Load headers of 2 tables
    private void loadTableHeaders ( ) {

        // Headers of Order table
        String[] orderHeaders = {"Id", "Customer Name", "Customer Email",
                "Total Amount", "Date Purchased", "Order Status"};

        // Headers of Item-Order table
        String[] itemOrderHeaders = {"Order ID", "Model", "Unit Price", "Quantity", "Total"};
        dtmOrder.setColumnIdentifiers ( orderHeaders );
        dtmItemOrder.setColumnIdentifiers ( itemOrderHeaders );

        this.jTableOrder.setModel ( dtmOrder );
        this.jTableItemOrderDetail.setModel ( dtmItemOrder );
    }

    private void clearItemOrderTable ( ) {

        dtmItemOrder.setRowCount ( 0 );
    }

    private void loadItemOrderTable ( ) {

        loadItemOrderTable ( 0 );
    }

    private void loadItemOrderTable ( int order_id ) {

        dtmItemOrder.setRowCount ( 0 );
        try {
            StringBuilder sb = new StringBuilder ( "SELECT O.id, B.model, I.price, I.quantity, ( I.price * I" +
                    ".quantity ) AS total\n" +
                    "FROM Order_info O JOIN Item_order I ON O.id = I.order_id JOIN bicycle B ON I.bicycle_id = B" +
                    ".bid\n" );
            if ( order_id > 0 ) {
                sb.append ( "WHERE O.id = " );
                sb.append ( order_id );
            }
            ResultSet rs = statement.executeQuery ( sb.toString ( ) );
            while ( rs.next ( ) ) {
                dtmItemOrder.addRow ( new Object[] {rs.getInt ( "id" ), rs.getString ( "model" ),
                        roundPrice ( rs.getDouble ( "price" ) ), rs.getInt ( "quantity" ),
                        roundPrice ( rs.getDouble ( "total" ) )} );
            }
        } catch ( SQLException e ) {
            System.out.println ( e.getMessage ( ) );
            System.exit ( 1 );
        }
    }

    // Load Headers and row for Bicycle Table
    private void loadOrderTable ( ) {
        // Set # of row to 0
        String[] orderHeaders = {"Id", "Customer Name", "Customer Email",
                "Total Amount", "Date Purchased", "Order Status"};
        dtmOrder.setRowCount ( 0 );
        dtmOrder.setColumnIdentifiers ( orderHeaders );
        // Load data for table from database
        //"Id", "Customer Name", "Customer Email", "Total Amount", "Date Purchased", "Order Status"
        try {
            ResultSet rs1 = statement.executeQuery ( "SELECT O.id, C.name, C.email, SUM(I.price * I.quantity)" +
                    " AS total, O.date_order, S.description\n" +
                    "FROM Order_info O JOIN Customer C ON O.customer_id = C.cid\n" +
                    "\tJOIN Order_status S ON O.status_id = S.sid\n" +
                    "JOIN Item_order I ON I.order_id = O.id\n" +
                    "GROUP BY O.id\n" +
                    "ORDER BY O.date_order DESC, total DESC;" );
            while ( rs1.next ( ) ) {
                dtmOrder.addRow ( new Object[] {rs1.getInt ( "id" ), rs1.getString ( "name" ), rs1.getString ( "email"
                ), roundPrice ( rs1.getDouble ( "total" ) ),
                        rs1.getDate ( "date_order" ), rs1.getString ( "description" )} );
            }
        } catch ( SQLException e ) {
            System.out.println ( e.getMessage ( ) );
            System.exit ( 1 );
        }

    }

    private double roundPrice ( double amount ) {

        return ( double ) Math.round ( amount * 100 ) / 100;
    }

    // Load the orderStatus to orderStatus ArrayList and load to 
    // JComboBoxOrderStatus
    private void loadOrderStatus ( ) {
        // Need to implement
        try {
            ResultSet rs = statement.executeQuery ( "Select * from Order_status" );
            jComboBoxOrderStatus.addItem ( "" );
            while ( rs.next ( ) ) {
                jComboBoxOrderStatus.addItem ( rs.getString ( 2 ) );
            }
        } catch ( SQLException e ) {
            System.out.println ( e.getMessage ( ) );
            System.exit ( 1 );
        }
    }

    private int getCustomerID ( ) {

        try {
            ResultSet rs = statement.executeQuery ( "SELECT cid FROM Customer where" +
                    " Customer.name =" + this.jTextFieldCustomerName +
                    " AND Customer.email =" + this.jTextFieldCustomerEmail );
            rs.next ( );
            return rs.getInt ( 1 );
        } catch ( SQLException e ) {

        }
        return -1;
    }

    private int getOrderStatus ( ) {

        try {
            ResultSet rs = statement.executeQuery ( "SELECT sid FROM Order_info where" +
                    " Order_info.description =" + this.jComboBoxOrderStatus.getSelectedItem ( ) );
        } catch ( SQLException e ) {

        }
        return -1;
    }

    private void jButtonAdd ( java.awt.event.ActionEvent evt ) {
        // TODO add your handling code here:
        try {
            int sid = getCustomerID ( );
            if ( sid == -1 ) {
                JOptionPane.showMessageDialog ( null, "Customer does not exist" );
                return;
            }
            int order_status = getOrderStatus ( );

            statement.executeUpdate ( "insert into Order_info(id,customer_id,price,date_order,status_id) values ('"
                    + this.jTextFieldId.getText ( ) + "', '"
                    + sid + ", "
                    + this.jTextFieldTotalAmount.getText ( ) + " , "
                    + this.jTextFieldDatePurchased.getText ( ) + ", "
                    + order_status + " )" );

            JOptionPane.showMessageDialog ( null, "Add Customer Succeded" );
        } catch ( SQLException e ) {
            System.out.println ( "Error " + e.getErrorCode ( ) );
            JOptionPane.showMessageDialog ( null, "Add Order Failed" );
        }
    }

    private void jTableOrderRowSelectionChanged ( ) {//GEN-FIRST
        // :event_jTableOrderMouseClicked
        if ( jTableOrder.getSelectedRow ( ) != -1 ) {
            int     row        = this.jTableOrder.getSelectedRow ( );
            Integer selectedId = ( Integer ) this.jTableOrder.getValueAt ( row, 0 );
            this.jTextFieldId.setText ( this.jTableOrder.getValueAt ( row, 0 ).toString ( ) );
            this.jTextFieldCustomerName.setText ( this.jTableOrder.getValueAt ( row, 1 ).toString ( ) );
            this.jTextFieldCustomerEmail.setText ( this.jTableOrder.getValueAt ( row, 2 ).toString ( ) );
            this.jTextFieldTotalAmount.setText ( this.jTableOrder.getValueAt ( row, 3 ).toString ( ) );
            this.jTextFieldDatePurchased.setText ( this.jTableOrder.getValueAt ( row, 4 ).toString ( ) );
            this.jComboBoxOrderStatus.setSelectedItem ( this.jTableOrder.getValueAt ( row, 5 ).toString ( ) );
            loadItemOrderTable ( selectedId );
        }
    }//GEN-LAST:event_jTableCustomerMouseClicked

    private void jButtonUpdateActionPerformed ( ) {

        int selectedRow = this.jTableOrder.getSelectedRow ( );
        if ( selectedRow == -1 ) {
            JOptionPane.showMessageDialog ( null, "Choose an order to modify" );
        } else {
            try {
                String prevOrderStatus = this.jTableOrder.getValueAt ( selectedRow, 5 ).toString ( );
                if ( !prevOrderStatus.equals ( "Processing" ) ) {
                    JOptionPane.showMessageDialog ( null, "Can only change processing order" );
                } else if ( jComboBoxOrderStatus.getSelectedItem ( ).equals ( prevOrderStatus ) ) {
                    JOptionPane.showMessageDialog ( null, "Selected status is the same as previous status" );
                } else if ( jComboBoxOrderStatus.getSelectedIndex ( ) == 0 ) {
                    JOptionPane.showMessageDialog ( null, "Please select a status" );
                } else {
                    int orderId = ( int ) jTableOrder.getValueAt ( selectedRow, 0 );
                    if ( jComboBoxOrderStatus.getSelectedItem ( ).toString ( ).equals ( "Cancel" ) ) {
                        returnToInventory ( );
                    }
                    String query = "UPDATE Order_info O\n" +
                            "SET O.status_id = " + ( jComboBoxOrderStatus.getSelectedIndex ( ) ) +
                            "\nWHERE O.id = " + orderId;
                    statement.execute ( query );
                    loadOrderTable ( );
                    jTableOrder.setRowSelectionInterval ( selectedRow, selectedRow );

                }
            } catch ( SQLException e ) {
                System.out.println ( e.getErrorCode ( ) );
                JOptionPane.showMessageDialog ( null, "Update Failed" );
            }
        }
    }

    private void returnToInventory ( ) {

        for ( int row = 0; row < jTableItemOrderDetail.getRowCount ( ); row++ ) {
            String bikeModel = ( String ) jTableItemOrderDetail.getValueAt ( row, 1 );
            int    quantity  = ( int ) jTableItemOrderDetail.getValueAt ( row, 3 );
            String query = "UPDATE Bicycle\n" +
                    "SET stock = stock + " + quantity +
                    "\nWHERE bicycle.model = '" + bikeModel + "'";
            try {
                statement.execute ( query );
            } catch ( SQLException e ) {
                System.out.println ( e.getMessage ( ) );
                JOptionPane.showMessageDialog ( null, "Delete failed\n" + e.getMessage ( ) );
            }
        }
    }

    private void jButtonDeleteActionPerformed ( ) {//GEN-FIRST
        // :event_jButtonDeleteActionPerformed

        int selectedRow = this.jTableOrder.getSelectedRow ( );
        int selectedId  = ( int ) this.jTableOrder.getValueAt ( selectedRow, 0 );
        if ( selectedRow == -1 ) {
            JOptionPane.showMessageDialog ( null, "Choose an order to delete" );
        } else {
            Object selectedStatus = jTableOrder.getValueAt ( selectedRow, 5 );
            if ( selectedStatus.equals ( "Done" ) ) {
                JOptionPane.showMessageDialog ( null, "Can't delete a completed order" );
            } else {
                try {
                    if ( selectedStatus.equals ( "Processing" ) ) {
                        JOptionPane.showMessageDialog ( null, "Deleting" );
                        returnToInventory ( );
                    }
                    statement.executeUpdate ( "delete from order_info where id = " + selectedId );
                    JOptionPane.showMessageDialog ( null, "Delete Succeded" );
                    reset ( );
                } catch ( SQLException e ) {
                    System.out.println ( e.getErrorCode ( ) );
                    JOptionPane.showMessageDialog ( null, "Delete Failed" );
                }
            }
        }

        reset ( );
    }//GEN-LAST:event_jButtonDeleteActionPerformed

    private void reset ( ) {

        loadOrderTable ( );
        jTextFieldId.setText ( "" );
        jTextFieldCustomerName.setText ( "" );
        jTextFieldCustomerEmail.setText ( "" );
        jTextFieldTotalAmount.setText ( "" );
        jTextFieldDatePurchased.setText ( "" );
        jComboBoxOrderStatus.setSelectedIndex ( 0 );
        clearItemOrderTable ( );
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents ( ) {

        jPanelOrderList = new javax.swing.JPanel ( );
        jScrollPane1 = new javax.swing.JScrollPane ( );
        jTableOrder = new javax.swing.JTable ( );
        jPanelDetail = new javax.swing.JPanel ( );
        jLabel1 = new javax.swing.JLabel ( );
        jLabel2 = new javax.swing.JLabel ( );
        jLabel3 = new javax.swing.JLabel ( );
        jLabel4 = new javax.swing.JLabel ( );
        jLabel5 = new javax.swing.JLabel ( );
        jTextFieldId = new javax.swing.JTextField ( );
        jTextFieldCustomerName = new javax.swing.JTextField ( );
        jTextFieldCustomerEmail = new javax.swing.JTextField ( );
        jTextFieldTotalAmount = new javax.swing.JTextField ( );
        jTextFieldDatePurchased = new javax.swing.JTextField ( );
        jLabel6 = new javax.swing.JLabel ( );
        jComboBoxOrderStatus = new javax.swing.JComboBox<> ( );
        jPanelItem_Order_Detail = new javax.swing.JPanel ( );
        jScrollPane2 = new javax.swing.JScrollPane ( );
        jTableItemOrderDetail = new javax.swing.JTable ( );
        jButtonUpdate = new javax.swing.JButton ( );
        jButtonDelete = new javax.swing.JButton ( );

        setBackground ( new java.awt.Color ( 255, 255, 255 ) );

        jPanelOrderList.setBackground ( new java.awt.Color ( 255, 255, 255 ) );
        jPanelOrderList.setBorder ( javax.swing.BorderFactory.createTitledBorder ( "Order List" ) );

        jTableOrder.setModel ( new javax.swing.table.DefaultTableModel (
                new Object[][] {
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null}
                },
                new String[] {
                        "Title 1", "Title 2", "Title 3", "Title 4"
                }
        ) );

        jTableOrder.getSelectionModel ( ).addListSelectionListener ( new ListSelectionListener ( ) {
            public void valueChanged ( ListSelectionEvent event ) {

                jTableOrderRowSelectionChanged ( );
            }
        } );

        jScrollPane1.setViewportView ( jTableOrder );

        javax.swing.GroupLayout jPanelOrderListLayout = new javax.swing.GroupLayout ( jPanelOrderList );
        jPanelOrderList.setLayout ( jPanelOrderListLayout );
        jPanelOrderListLayout.setHorizontalGroup (
                jPanelOrderListLayout.createParallelGroup ( javax.swing.GroupLayout.Alignment.LEADING )
                        .addGroup ( jPanelOrderListLayout.createSequentialGroup ( )
                                .addContainerGap ( )
                                .addComponent ( jScrollPane1 )
                                .addContainerGap ( ) )
        );
        jPanelOrderListLayout.setVerticalGroup (
                jPanelOrderListLayout.createParallelGroup ( javax.swing.GroupLayout.Alignment.LEADING )
                        .addGroup ( jPanelOrderListLayout.createSequentialGroup ( )
                                .addContainerGap ( )
                                .addComponent ( jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 195,
                                        Short.MAX_VALUE )
                                .addContainerGap ( ) )
        );

        jPanelDetail.setBackground ( new java.awt.Color ( 255, 255, 255 ) );
        jPanelDetail.setBorder ( javax.swing.BorderFactory.createTitledBorder ( "Order Detail" ) );

        jLabel1.setText ( "Id" );

        jLabel2.setText ( "Customer Name" );

        jLabel3.setText ( "Customer Email" );

        jLabel4.setText ( "Total Amount" );

        jLabel5.setText ( "Date Purchased" );

        jTextFieldId.setDragEnabled ( false );
        jTextFieldId.setEditable ( false );
        jTextFieldCustomerName.setEditable ( false );
        jTextFieldCustomerEmail.setEditable ( false );
        jTextFieldDatePurchased.setEditable ( false );
        jTextFieldTotalAmount.setEditable ( false );

        jLabel6.setText ( "Order Status" );

        jPanelItem_Order_Detail.setBackground ( new java.awt.Color ( 255, 255, 255 ) );
        jPanelItem_Order_Detail.setBorder ( javax.swing.BorderFactory.createTitledBorder ( "Item-Order Detail" ) );

        jTableItemOrderDetail.setModel ( new javax.swing.table.DefaultTableModel (
                new Object[][] {
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null}
                },
                new String[] {
                        "Title 1", "Title 2", "Title 3", "Title 4"
                }
        ) );
        jScrollPane2.setViewportView ( jTableItemOrderDetail );

        javax.swing.GroupLayout jPanelItem_Order_DetailLayout = new javax.swing.GroupLayout ( jPanelItem_Order_Detail );
        jPanelItem_Order_Detail.setLayout ( jPanelItem_Order_DetailLayout );
        jPanelItem_Order_DetailLayout.setHorizontalGroup (
                jPanelItem_Order_DetailLayout.createParallelGroup ( javax.swing.GroupLayout.Alignment.LEADING )
                        .addGroup ( jPanelItem_Order_DetailLayout.createSequentialGroup ( )
                                .addContainerGap ( )
                                .addComponent ( jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 708,
                                        Short.MAX_VALUE )
                                .addContainerGap ( ) )
        );
        jPanelItem_Order_DetailLayout.setVerticalGroup (
                jPanelItem_Order_DetailLayout.createParallelGroup ( javax.swing.GroupLayout.Alignment.LEADING )
                        .addGroup ( javax.swing.GroupLayout.Alignment.TRAILING,
                                jPanelItem_Order_DetailLayout.createSequentialGroup ( )
                                        .addContainerGap ( javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE )
                                        .addComponent ( jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 199,
                                                javax.swing.GroupLayout.PREFERRED_SIZE )
                                        .addGap ( 227, 227, 227 ) )
        );

        jButtonUpdate.setText ( "Update" );

        jButtonUpdate.addActionListener ( new ActionListener ( ) {
            public void actionPerformed ( ActionEvent evt ) {

                jButtonUpdateActionPerformed ( );
            }
        } );

        jButtonDelete.setText ( "Delete" );
        jButtonDelete.addActionListener ( new ActionListener ( ) {
            @Override
            public void actionPerformed ( ActionEvent e ) {

                jButtonDeleteActionPerformed ( );
            }
        } );
        javax.swing.GroupLayout jPanelDetailLayout = new javax.swing.GroupLayout ( jPanelDetail );
        jPanelDetail.setLayout ( jPanelDetailLayout );
        jPanelDetailLayout.setHorizontalGroup (
                jPanelDetailLayout.createParallelGroup ( javax.swing.GroupLayout.Alignment.LEADING )
                        .addGroup ( jPanelDetailLayout.createSequentialGroup ( )
                                .addGroup ( jPanelDetailLayout.createParallelGroup ( javax.swing.GroupLayout.Alignment.LEADING )
                                        .addGroup ( jPanelDetailLayout.createSequentialGroup ( )
                                                .addGap ( 71, 71, 71 )
                                                .addGroup ( jPanelDetailLayout.createParallelGroup ( javax.swing.GroupLayout.Alignment.LEADING )
                                                        .addComponent ( jPanelItem_Order_Detail,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE )
                                                        .addGroup ( jPanelDetailLayout.createSequentialGroup ( )
                                                                .addGroup ( jPanelDetailLayout.createParallelGroup ( javax.swing.GroupLayout.Alignment.LEADING )
                                                                        .addComponent ( jLabel1 )
                                                                        .addComponent ( jLabel2 )
                                                                        .addComponent ( jLabel3 )
                                                                        .addComponent ( jLabel4 )
                                                                        .addComponent ( jLabel6 ) )
                                                                .addGap ( 58, 58, 58 )
                                                                .addGroup ( jPanelDetailLayout.createParallelGroup ( javax.swing.GroupLayout.Alignment.LEADING, false )
                                                                        .addComponent ( jTextFieldId )
                                                                        .addComponent ( jTextFieldCustomerName )
                                                                        .addComponent ( jTextFieldCustomerEmail )
                                                                        .addGroup ( jPanelDetailLayout.createSequentialGroup ( )
                                                                                .addComponent ( jTextFieldTotalAmount
                                                                                        ,
                                                                                        javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE )
                                                                                .addGap ( 27, 27, 27 )
                                                                                .addComponent ( jLabel5 )
                                                                                .addGap ( 18, 18, 18 )
                                                                                .addComponent ( jTextFieldDatePurchased, javax.swing.GroupLayout.DEFAULT_SIZE, 191, Short.MAX_VALUE ) )
                                                                        .addComponent ( jComboBoxOrderStatus, 0,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                Short.MAX_VALUE ) ) ) ) )
                                        .addGroup ( jPanelDetailLayout.createSequentialGroup ( )
                                                .addGap ( 253, 253, 253 )
                                                .addComponent ( jButtonUpdate, javax.swing.GroupLayout.PREFERRED_SIZE
                                                        , 109, javax.swing.GroupLayout.PREFERRED_SIZE )
                                                .addGap ( 116, 116, 116 )
                                                .addComponent ( jButtonDelete, javax.swing.GroupLayout.PREFERRED_SIZE
                                                        , 109, javax.swing.GroupLayout.PREFERRED_SIZE ) ) )
                                .addContainerGap ( 83, Short.MAX_VALUE ) )
        );
        jPanelDetailLayout.setVerticalGroup (
                jPanelDetailLayout.createParallelGroup ( javax.swing.GroupLayout.Alignment.LEADING )
                        .addGroup ( jPanelDetailLayout.createSequentialGroup ( )
                                .addContainerGap ( )
                                .addGroup ( jPanelDetailLayout.createParallelGroup ( javax.swing.GroupLayout.Alignment.BASELINE )
                                        .addComponent ( jLabel1 )
                                        .addComponent ( jTextFieldId, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.PREFERRED_SIZE ) )
                                .addPreferredGap ( javax.swing.LayoutStyle.ComponentPlacement.RELATED )
                                .addGroup ( jPanelDetailLayout.createParallelGroup ( javax.swing.GroupLayout.Alignment.BASELINE )
                                        .addComponent ( jLabel2 )
                                        .addComponent ( jTextFieldCustomerName,
                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.PREFERRED_SIZE ) )
                                .addPreferredGap ( javax.swing.LayoutStyle.ComponentPlacement.RELATED )
                                .addGroup ( jPanelDetailLayout.createParallelGroup ( javax.swing.GroupLayout.Alignment.BASELINE )
                                        .addComponent ( jLabel3 )
                                        .addComponent ( jTextFieldCustomerEmail,
                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.PREFERRED_SIZE ) )
                                .addPreferredGap ( javax.swing.LayoutStyle.ComponentPlacement.RELATED )
                                .addGroup ( jPanelDetailLayout.createParallelGroup ( javax.swing.GroupLayout.Alignment.BASELINE )
                                        .addComponent ( jLabel4 )
                                        .addComponent ( jLabel5 )
                                        .addComponent ( jTextFieldTotalAmount, javax.swing.GroupLayout.PREFERRED_SIZE
                                                , javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.PREFERRED_SIZE )
                                        .addComponent ( jTextFieldDatePurchased,
                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.PREFERRED_SIZE ) )
                                .addPreferredGap ( javax.swing.LayoutStyle.ComponentPlacement.RELATED )
                                .addGroup ( jPanelDetailLayout.createParallelGroup ( javax.swing.GroupLayout.Alignment.LEADING )
                                        .addComponent ( jLabel6 )
                                        .addComponent ( jComboBoxOrderStatus, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.PREFERRED_SIZE ) )
                                .addGap ( 18, 18, 18 )
                                .addComponent ( jPanelItem_Order_Detail, javax.swing.GroupLayout.PREFERRED_SIZE, 247,
                                        javax.swing.GroupLayout.PREFERRED_SIZE )
                                .addPreferredGap ( javax.swing.LayoutStyle.ComponentPlacement.RELATED )
                                .addGroup ( jPanelDetailLayout.createParallelGroup ( javax.swing.GroupLayout.Alignment.BASELINE )
                                        .addComponent ( jButtonUpdate, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE )
                                        .addComponent ( jButtonDelete, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE ) )
                                .addContainerGap ( ) )
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout ( this );
        this.setLayout ( layout );
        layout.setHorizontalGroup (
                layout.createParallelGroup ( javax.swing.GroupLayout.Alignment.LEADING )
                        .addGroup ( layout.createSequentialGroup ( )
                                .addContainerGap ( )
                                .addGroup ( layout.createParallelGroup ( javax.swing.GroupLayout.Alignment.LEADING )
                                        .addComponent ( jPanelOrderList, javax.swing.GroupLayout.Alignment.TRAILING,
                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE )
                                        .addComponent ( jPanelDetail, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE ) )
                                .addContainerGap ( ) )
        );
        layout.setVerticalGroup (
                layout.createParallelGroup ( javax.swing.GroupLayout.Alignment.LEADING )
                        .addGroup ( layout.createSequentialGroup ( )
                                .addContainerGap ( )
                                .addComponent ( jPanelOrderList, javax.swing.GroupLayout.PREFERRED_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE )
                                .addPreferredGap ( javax.swing.LayoutStyle.ComponentPlacement.UNRELATED )
                                .addComponent ( jPanelDetail, javax.swing.GroupLayout.DEFAULT_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE )
                                .addContainerGap ( ) )
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton           jButtonDelete;
    private javax.swing.JButton           jButtonUpdate;
    private javax.swing.JComboBox<String> jComboBoxOrderStatus;
    private javax.swing.JLabel            jLabel1;
    private javax.swing.JLabel            jLabel2;
    private javax.swing.JLabel            jLabel3;
    private javax.swing.JLabel            jLabel4;
    private javax.swing.JLabel            jLabel5;
    private javax.swing.JLabel            jLabel6;
    private javax.swing.JPanel            jPanelDetail;
    private javax.swing.JPanel            jPanelItem_Order_Detail;
    private javax.swing.JPanel            jPanelOrderList;
    private javax.swing.JScrollPane       jScrollPane1;
    private javax.swing.JScrollPane       jScrollPane2;
    private javax.swing.JTable            jTableItemOrderDetail;
    private javax.swing.JTable            jTableOrder;
    private javax.swing.JTextField        jTextFieldCustomerEmail;
    private javax.swing.JTextField        jTextFieldCustomerName;
    private javax.swing.JTextField        jTextFieldDatePurchased;
    private javax.swing.JTextField        jTextFieldId;
    private javax.swing.JTextField        jTextFieldTotalAmount;
    // End of variables declaration//GEN-END:variables
}
