/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.institute_management.CreatePaymentMetaData;

import com.institute_management.DataSourse.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;

/**
 *
 * @author malindad
 */
public class Main_int extends javax.swing.JFrame {

    /**
     * Creates new form Main
     */
    public Main_int() {
        initComponents();
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        lblmonth = new javax.swing.JLabel();
        lbltotal = new javax.swing.JLabel();
        lblsuccess = new javax.swing.JLabel();
        lblerror = new javax.swing.JLabel();
        lblstatus = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(51, 51, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Create Payment Meta Data For New Month");

        jButton1.setText("START PROCESS");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel2.setText("Selected Month");

        jLabel3.setText("Total Target Records ");

        jLabel4.setText("Success Records ");

        jLabel5.setText("Error Records ");

        jLabel6.setText("Status");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
            .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(45, 45, 45)
                        .addComponent(lblmonth, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblstatus, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblerror, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblsuccess, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbltotal, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(49, 49, 49)
                                        .addComponent(jButton1)
                                        .addGap(36, 36, 36)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel2)
                                            .addComponent(lblmonth, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel3))
                                    .addComponent(lbltotal, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel4))
                            .addComponent(lblsuccess, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel5))
                    .addComponent(lblerror, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblstatus, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        sw.execute();
    }//GEN-LAST:event_jButton1ActionPerformed
    SwingWorker sw = new SwingWorker() {
        @Override
        protected Object doInBackground() throws Exception {
            Process p = null;
            int processComplete = 10;
            String error_msg = null;
            int success_count = 0;
            int error_count = 0;
            
            //check eligibility of selected month
            Calendar cal = Calendar.getInstance();
            String curMonth = new SimpleDateFormat("MMMM").format(cal.getTime()).toLowerCase();
            String curYear = (new SimpleDateFormat("yyyy").format(cal.getTime())).toLowerCase();
            
            lblmonth.setText(curMonth);
            lblsuccess.setText("0");
            lblerror.setText("0");
            lblstatus.setText("Process Running..");
            
            PreparedStatement stmt = null;
            ResultSet result = null;
            DataSource d = null;
            Connection con = null;
            try {
                d = new DataSource();
                con = d.getConnection();
                con.setAutoCommit(false);
                
                String query ="select * from `payments_activation_details` where payment_year = ? and payment_month = ? ";
                stmt = con.prepareStatement(query);
                stmt.setString(1, curYear);
                stmt.setString(2, curMonth);
                result = stmt.executeQuery();
                
                if(result.next()){
                    error_msg = "Already generated payment details for "+curMonth +" "+curYear;
                    lblstatus.setText("ERROR");
                    JOptionPane.showMessageDialog(new JFrame(), error_msg);
                }else{
                    // select * data from student_course and insert to the payment details table
                    query ="select sc.*,c.monthly_fee,case when sc.cardtype = '2' then c.monthly_fee when sc.cardtype = '1' then c.monthly_fee/2 when sc.cardtype = '0' then 0 end elegible_fee from `student-course` sc,`course` c where sc.status = 'ACT' and sc.course_id = c.course_id order by course_id";
                    stmt = con.prepareStatement(query);
                    result = stmt.executeQuery();
                    while(result.next()){
                        PreparedStatement stmt2 = null;
                        ResultSet result2 = null;
                        String query2 = "INSERT INTO `payments_activation_details`(`s_id`, `course_id`, `payment_month`, `payment_year`, `card_type`,`class_fee`,`elegible_fee`,`status`) VALUES (?,?,?,?,?,?,?,?)";
                        stmt2 = con.prepareStatement(query2);
                        stmt2.setString(1, result.getString("s_id"));
                        stmt2.setString(2,  result.getString("course_id"));
                        stmt2.setString(3, curMonth);
                        stmt2.setString(4, curYear);
                        stmt2.setString(5, result.getString("cardType"));
                        stmt2.setDouble(6, result.getDouble("monthly_fee"));
                        stmt2.setDouble(7, result.getDouble("elegible_fee"));
                        stmt2.setString(8, "PENDING");
                        int status = stmt2.executeUpdate();
                        if(status==1){
                            success_count = success_count+1;
                            lblsuccess.setText(success_count+"");
                            
                            stmt2.close();
                            
                        }else{
                            error_count = error_count+1;
                            lblerror.setText(error_count+"");
                            lblstatus.setText("ERROR");
                            con.rollback();
                            con.close();
                            break;
                        }
                        
                    }if(error_count==0){
                        con.commit();
                        lblstatus.setText("SUCCESS");
                        stmt.close();
                        result.close();
                        con.close();
                    }else{
                        error_count = error_count+1;
                            lblerror.setText(error_count+"");
                            lblstatus.setText("ERROR");
                            con.rollback();
                            con.close();
                    }
                    
                }
            
            }catch(Exception ex){
                lblstatus.setText("ERROR");
            }
            
         return 0;       
        }

        @Override
        public void done() {
            
        }
    };
    
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
            java.util.logging.Logger.getLogger(Main_int.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main_int.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main_int.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main_int.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main_int().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblerror;
    private javax.swing.JLabel lblmonth;
    private javax.swing.JLabel lblstatus;
    private javax.swing.JLabel lblsuccess;
    private javax.swing.JLabel lbltotal;
    // End of variables declaration//GEN-END:variables
}
