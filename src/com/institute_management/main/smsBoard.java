/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.institute_management.main;

import com.institute_management.DataSourse.DataSource;
import com.institute_management.attendance_new.dbConnection;
import static com.institute_management.user_mgt.UI.NewLogin2.logger;
import com.institute_management.util.CommonMethods;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author malindad
 */
public class smsBoard extends javax.swing.JFrame {

    dbConnection a = new dbConnection();
    CommonMethods cm = new CommonMethods();

    public smsBoard() {
        initComponents();
        String x = a.Check_pending_SMS();
        jLabel9.setText(x);

        try {
            String to = null;
            DateFormat dateFormat = null;
            Date dateFrom = from.getDate();
            if (dateFrom != null) {
                dateFormat = new SimpleDateFormat("YYYY-MM-dd");
                to = dateFormat.format(dateFrom);
            }

            HashMap<Integer, Object[]> tblData = a.getPendingSMS(jTextField1.getText(), to);
            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
            model.setRowCount(0);
            for (int i = 1; i <= tblData.size(); i++) {
                model.addRow(tblData.get(i));
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        from = new com.toedter.calendar.JDateChooser();
        jButton1 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(0, 0, 255), null));

        jLabel5.setText("Fail Count");

        jLabel4.setText("Success Count");

        jButton2.setText("STEP 02 - SEND PENDING SMS");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel2.setText("Pending SMS Count");

        jButton5.setText("STEP 01 - Check Pending SMS");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(245, Short.MAX_VALUE))
            .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jButton5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jButton5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36)
                .addComponent(jButton2)
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel5)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(115, Short.MAX_VALUE)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(171, 171, 171))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(229, 229, 229)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(44, 44, 44))
        );

        jTabbedPane1.addTab("Pending SMS", jPanel1);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ID", "Student ID", "Message Body", "Date", "Delete"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                true, false, false, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(0).setMinWidth(50);
            jTable1.getColumnModel().getColumn(0).setPreferredWidth(50);
            jTable1.getColumnModel().getColumn(0).setMaxWidth(50);
            jTable1.getColumnModel().getColumn(1).setMinWidth(100);
            jTable1.getColumnModel().getColumn(1).setPreferredWidth(100);
            jTable1.getColumnModel().getColumn(1).setMaxWidth(100);
            jTable1.getColumnModel().getColumn(2).setMinWidth(350);
            jTable1.getColumnModel().getColumn(2).setPreferredWidth(350);
            jTable1.getColumnModel().getColumn(2).setMaxWidth(350);
            jTable1.getColumnModel().getColumn(3).setMinWidth(100);
            jTable1.getColumnModel().getColumn(3).setPreferredWidth(100);
            jTable1.getColumnModel().getColumn(3).setMaxWidth(100);
            jTable1.getColumnModel().getColumn(4).setMinWidth(60);
            jTable1.getColumnModel().getColumn(4).setPreferredWidth(60);
            jTable1.getColumnModel().getColumn(4).setMaxWidth(60);
        }

        jLabel3.setText("Student ID");

        jLabel10.setText("Send Date");

        jButton1.setText("Filter");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton4.setText("Delete Selected Messages");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 662, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(18, 18, 18)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(59, 59, 59)
                        .addComponent(jLabel10)
                        .addGap(28, 28, 28)
                        .addComponent(from, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(0, 475, Short.MAX_VALUE)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 39, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel10))
                    .addComponent(from, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addComponent(jButton4))
        );

        jTabbedPane1.addTab("Delete Pending SMS", jPanel2);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(51, 51, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("SMS Dashboard");

        jButton3.setText("Filter");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTabbedPane1, javax.swing.GroupLayout.Alignment.TRAILING)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(299, 299, 299)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(318, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 353, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(205, 205, 205)
                    .addComponent(jButton3)
                    .addContainerGap(164, Short.MAX_VALUE)))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        if (jLabel9.getText().toString().equals("0")) {
            JOptionPane.showMessageDialog(new JFrame(), "No Message To Send");
        } else {
            PreparedStatement pst = null;
            ResultSet rs = null;
            DataSource d = null;
            Connection connection = null;
            String query = null;
            String mobile = null;
            String msg = null;
            String id = "";
            int suscount = 0;
            try {
                d = new DataSource();
                connection = d.getConnection();
                query = "select *  from sms_details where status = 0";

                pst = connection.prepareStatement(query);

                rs = pst.executeQuery();

                while (rs.next()) {
                    mobile = rs.getString("mobile");
                    msg = rs.getString("body");
                    id = rs.getString("id");

                    URL textit = new URL("http://textit.biz/sendmsg/index.php?id=94717856356&pw=1073&to=" + mobile + "&text=" + URLEncoder.encode(msg, "UTF-8"));
                    BufferedReader in = new BufferedReader(new InputStreamReader(textit.openStream()));
                    String inputLine;
                    while ((inputLine = in.readLine()) != null) {
                        String out[] = inputLine.split(":");
                        if (out[0].equals("OK")) {
                            a.updateSMStable(id);
                            suscount++;
                        }
                    }

                }
                jLabel6.setText(suscount + "");
                jLabel8.setText("0");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(new JFrame(), "Error In SMS Sending.");
                cm.closeConnection(connection);
                cm.closeResult(rs);
                cm.closeStatements(pst);
                //------------------------log Start--------------------------------------------
                logger.error(dbConnection.class + ".insert_SMS() ->error" + ex);
                //-------------------------log End---------------------------------------------

            } finally {
                cm.closeConnection(connection);
                cm.closeResult(rs);
                cm.closeStatements(pst);

            }
        }
        //load pending sms
        try {
            String to = null;
            DateFormat dateFormat = null;
            Date dateFrom = from.getDate();
            if (dateFrom != null) {
                dateFormat = new SimpleDateFormat("YYYY-MM-dd");
                to = dateFormat.format(dateFrom);
            }

            HashMap<Integer, Object[]> tblData = a.getPendingSMS(jTextField1.getText(), to);
            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
            model.setRowCount(0);
            for (int i = 1; i <= tblData.size(); i++) {
                model.addRow(tblData.get(i));
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }

    }//GEN-LAST:event_jButton2ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        int Roww = jTable1.getSelectedRow();
        TableModel a = jTable1.getModel();
        String reg_id = a.getValueAt(Roww, 2).toString();
        smsBodypopup ss = new smsBodypopup(reg_id);
        ss.setVisible(true);
    }//GEN-LAST:event_jTable1MouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            String to = null;
            DateFormat dateFormat = null;
            Date dateFrom = from.getDate();
            if (dateFrom != null) {
                dateFormat = new SimpleDateFormat("YYYY-MM-dd");
                to = dateFormat.format(dateFrom);
            }

            HashMap<Integer, Object[]> tblData = a.getPendingSMS(jTextField1.getText(), to);
            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
            model.setRowCount(0);
            for (int i = 1; i <= tblData.size(); i++) {
                model.addRow(tblData.get(i));
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        int x = 0;
        int count = 0;
        for (int i = 0; i < jTable1.getRowCount(); i++) {
            Boolean isChecked = (boolean) jTable1.getValueAt(i, 4);

            if (isChecked) {
                String id = jTable1.getValueAt(i, 0).toString();

                try {
                    x = a.deleteSMS(id);
                } catch (Exception ex) {
                    break;
                }
                count++;
                if (x != 1) {
                    break;
                }
            }
        }
        if (x == 1) {
            JOptionPane.showMessageDialog(new JFrame(), count + " Messege(s) Successfully Deleted");
        } else {
            JOptionPane.showMessageDialog(new JFrame(), "error Occured. Try again");
        }

        //reload table
        try {
            String to = null;
            DateFormat dateFormat = null;
            Date dateFrom = from.getDate();
            if (dateFrom != null) {
                dateFormat = new SimpleDateFormat("YYYY-MM-dd");
                to = dateFormat.format(dateFrom);
            }

            HashMap<Integer, Object[]> tblData = a.getPendingSMS(jTextField1.getText(), to);
            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
            model.setRowCount(0);
            for (int i = 1; i <= tblData.size(); i++) {
                model.addRow(tblData.get(i));
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        String x = a.Check_pending_SMS();
        jLabel9.setText(x);
    }//GEN-LAST:event_jButton5ActionPerformed

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
            java.util.logging.Logger.getLogger(smsBoard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(smsBoard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(smsBoard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(smsBoard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new smsBoard().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.toedter.calendar.JDateChooser from;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
