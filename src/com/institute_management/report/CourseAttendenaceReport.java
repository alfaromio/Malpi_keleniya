/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.institute_management.report;

import com.institute_management.DataSourse.DataSource;
import com.institute_management.configurations.Config;

import com.institute_management.course_mgt.CourseDbConnection;
import com.institute_management.main.NewJFrame;

import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import com.institute_management.lecture_mgt.LectureDbConnection;
import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author tharindu_m
 */
public class CourseAttendenaceReport extends javax.swing.JFrame {

    /**
     * Creates new form AttendenaceReport
     */
    public CourseAttendenaceReport() {
        initComponents();
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("images.png")));
        try {
            DataSource d = new DataSource();
            Connection con = d.getConnection();
            String query1 = "SELECT `name` FROM `lecturer` ";

            PreparedStatement stmt1 = con.prepareStatement(query1);
            ResultSet result1 = stmt1.executeQuery();
            cmbLecName.addItem("ALL");
            while (result1.next()) {
                String role = result1.getString("name");
                cmbLecName.addItem(role);
            }

            result1.close();
            stmt1.close();

        } catch (Exception ex) {
            System.out.println(ex);
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

        jPanel1 = new javax.swing.JPanel();
        lblvrification = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        lblTotalCourses = new javax.swing.JLabel();
        cmbCourseID = new javax.swing.JComboBox();
        from = new com.toedter.calendar.JDateChooser();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        to = new com.toedter.calendar.JDateChooser();
        jButton1 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        cmbLecName = new javax.swing.JComboBox();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        lblvrification.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        lblvrification.setForeground(new java.awt.Color(0, 0, 255));

        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel8.setText("Select the Course");

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel19.setText(":");

        lblTotalCourses.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);

        cmbCourseID.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cmbCourseIDMouseClicked(evt);
            }
        });
        cmbCourseID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbCourseIDActionPerformed(evt);
            }
        });

        jLabel1.setText("From");

        jLabel2.setText("To");

        jButton1.setText("View Detail Report");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 51, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Course Attendence Report");

        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel20.setText(":");

        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel9.setText("Lecturer Name");

        cmbLecName.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cmbLecNameMouseClicked(evt);
            }
        });
        cmbLecName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbLecNameActionPerformed(evt);
            }
        });

        jButton2.setText("View Summery Report");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(195, 195, 195)
                        .addComponent(lblTotalCourses, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(32, 32, 32)
                        .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(332, 332, 332)
                        .addComponent(lblvrification, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(from, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(53, 53, 53)
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(to, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jButton2)
                                .addGap(31, 31, 31)
                                .addComponent(jButton1))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(32, 32, 32)
                                .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cmbLecName, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cmbCourseID, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(163, 163, 163)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {from, to});

        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(jLabel20)
                            .addComponent(cmbLecName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(3, 3, 3))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblvrification, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(lblTotalCourses, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                            .addGap(2, 2, 2)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel8)
                                .addComponent(jLabel19))))
                    .addComponent(cmbCourseID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(from, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(to, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(40, 40, 40)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addContainerGap(51, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 510, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 2, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cmbCourseIDMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbCourseIDMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbCourseIDMouseClicked

    private void cmbCourseIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbCourseIDActionPerformed

    }//GEN-LAST:event_cmbCourseIDActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {

            if (from.getDate() == null) {
                JOptionPane.showMessageDialog(new JFrame(), "Please Select FromDate");
            } else if (to.getDate() == null) {
                JOptionPane.showMessageDialog(new JFrame(), "Please Select ToDate");
            } else {

                Date dateTo = to.getDate();
                Date dateFrom = from.getDate();

                DateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd");
                String to = dateFormat.format(dateTo);
                String from = dateFormat.format(dateFrom);

                // SELECT * FROM `attendence` WHERE id>=(select min(id) from attendence where year >= 2017 and month >=5 and date >=25) and id<=(select max(id) from attendence where year <= 2017 and month <=5 and date <=25  )
                HashMap a = new HashMap();

                a.put("fromDate", from);
                a.put("toDate", to);
                a.put("c_id", cmbCourseID.getSelectedItem().toString());
                a.put("l_name", cmbLecName.getSelectedItem().toString());

                try {
                    ////temp code
                    Config config = new Config();
                    reportGen rg;
                    try {
                        if (cmbCourseID.getSelectedItem().toString().equals("ALL")) {
                            rg = new reportGen(config.report_path + "courseAttandanceDetailsReportForAllCourses.jasper", a);
                        } else {
                            rg = new reportGen(config.report_path + "CourseAttendanceReport.jasper", a);
                        }
                        rg.setVisible(true);

                    } catch (Exception ex) {
                        Logger.getLogger(NewJFrame.class
                                .getName()).log(Level.SEVERE, null, ex);
                    }
                } catch (Exception ex) {

                }
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(new JFrame(), ex);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void cmbLecNameMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbLecNameMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbLecNameMouseClicked

    private void cmbLecNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbLecNameActionPerformed
        PreparedStatement stmt1 = null;
        ResultSet result1 = null;

        try {
            DataSource d = new DataSource();
            Connection con = d.getConnection();

            String query1 = "SELECT `course_id` FROM `course` where lecturer_id = (select id from lecturer where name='" + cmbLecName.getSelectedItem().toString() + "')";
            cmbCourseID.removeAllItems();
            if (cmbLecName.getSelectedItem().toString().equals("ALL")) {
                cmbCourseID.addItem("ALL");
            } else {
                stmt1 = con.prepareStatement(query1);
                result1 = stmt1.executeQuery();
                int count = 0;
                while (result1.next()) {
                    count++;
                    if (count == 1) {

                    }
                    String role = result1.getString("course_id");
                    cmbCourseID.addItem(role);
                }
            }

            result1.close();
            stmt1.close();

        } catch (Exception ex) {
            System.out.println(ex);
        }
    }//GEN-LAST:event_cmbLecNameActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        try {

            if (from.getDate() == null) {
                JOptionPane.showMessageDialog(new JFrame(), "Please Select FromDate");
            } else if (to.getDate() == null) {
                JOptionPane.showMessageDialog(new JFrame(), "Please Select ToDate");
            } else {

                Date dateTo = to.getDate();
                Date dateFrom = from.getDate();

                DateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd");
                String to = dateFormat.format(dateTo);
                String from = dateFormat.format(dateFrom);

                // SELECT * FROM `attendence` WHERE id>=(select min(id) from attendence where year >= 2017 and month >=5 and date >=25) and id<=(select max(id) from attendence where year <= 2017 and month <=5 and date <=25  )
                HashMap a = new HashMap();

                a.put("fromDate", from);
                a.put("toDate", to);

                try {
                    ////temp code
                    Config config = new Config();
                    reportGen rg;
                    try {
                        rg = new reportGen(config.report_path + "courseAttandanceSummeryReport.jasper", a);
                        rg.setVisible(true);

                    } catch (Exception ex) {
                        Logger.getLogger(NewJFrame.class
                                .getName()).log(Level.SEVERE, null, ex);
                    }
                } catch (Exception ex) {

                }
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(new JFrame(), ex);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

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
            java.util.logging.Logger.getLogger(CourseAttendenaceReport.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CourseAttendenaceReport.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CourseAttendenaceReport.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CourseAttendenaceReport.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CourseAttendenaceReport().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox cmbCourseID;
    private javax.swing.JComboBox cmbLecName;
    private com.toedter.calendar.JDateChooser from;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblTotalCourses;
    private static javax.swing.JLabel lblvrification;
    private com.toedter.calendar.JDateChooser to;
    // End of variables declaration//GEN-END:variables
}
