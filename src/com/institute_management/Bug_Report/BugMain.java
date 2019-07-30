/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.institute_management.Bug_Report;

import com.institute_management.configurations.Config;
import com.institute_management.util.Configurations;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author malindad
 */
public class BugMain extends javax.swing.JFrame {
    Config config = new Config();
    
     int x, y, z = 0;
    Timer SimpleTimer = new Timer(10, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            designe();
        }
    });

    public BugMain() {
        initComponents();
        SimpleTimer.start();

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        cmbLevel = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        cmbModule = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtDes = new javax.swing.JTextPane();
        jButton1 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        cmbInstitute = new javax.swing.JComboBox();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setText("Bug Level");

        cmbLevel.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-Select Level-", "Only Backup", "Low Level", "Medium Level", "High Level" }));

        jLabel2.setText("Module");

        cmbModule.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-Select Module-", "Backup All", "Payment", "Attendance", "Report", "Student", "Course", "Lecturer", "Other" }));

        jLabel3.setText("Discription");

        jScrollPane1.setViewportView(txtDes);

        jButton1.setText("Report Bug");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel4.setText("Institute");

        cmbInstitute.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Malpi Kelaniya" }));

        jLabel8.setBackground(new java.awt.Color(255, 51, 102));
        jLabel8.setForeground(new java.awt.Color(0, 0, 204));

        jLabel5.setText("Create DB Backup");

        jLabel6.setText("Connect mail server");

        jLabel7.setText("Report Bug");

        jLabel12.setFont(new java.awt.Font("Cambria", 1, 24)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 0, 0));
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("Error Reporting Page");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 92, Short.MAX_VALUE)
                        .addGap(457, 457, 457))
                    .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(46, 46, 46)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel4))
                                .addGap(58, 58, 58)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(cmbInstitute, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(cmbLevel, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(cmbModule, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jButton1)
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 372, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 62, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(cmbInstitute, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(cmbLevel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(cmbModule, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addGap(9, 9, 9)
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
public void designe() {
        if (x == 1) {
            jLabel8.setBackground(Color.YELLOW);
        } else if (x == 2) {
            jLabel8.setBackground(Color.GREEN);
        } else if (x == 3) {
            jLabel8.setBackground(Color.RED);
        }
        if (x == 1) {
            jLabel9.setBackground(Color.YELLOW);
        } else if (x == 2) {
            jLabel9.setBackground(Color.GREEN);
        } else if (x == 3) {
            jLabel9.setBackground(Color.RED);
        }
        if (x == 1) {
            jLabel10.setBackground(Color.YELLOW);
        } else if (x == 2) {
            jLabel10.setBackground(Color.GREEN);
        } else if (x == 3) {
            jLabel10.setBackground(Color.RED);
        }
    }
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if(!cmbLevel.getSelectedItem().toString().equals("-Select Level-")){
            if(!cmbModule.getSelectedItem().toString().equals("-Select Module-")){
                 sw.execute();
            }else{
                JOptionPane.showMessageDialog(new JFrame(), "Select Module");
            }
        }else{
            JOptionPane.showMessageDialog(new JFrame(), "Select Level");
        }
       
    }//GEN-LAST:event_jButton1ActionPerformed
    SwingWorker sw = new SwingWorker() {
        @Override
        protected Object doInBackground() throws Exception {
            Process p = null;
            int processComplete = 10;
            String s = null;
            final String user = "zeogen.bug@gmail.com";
            final String pass = "Zeogen@123";
            jLabel8.setOpaque(true);
            jLabel9.setOpaque(true);
            jLabel10.setOpaque(true);
            jLabel8.setBackground(Color.YELLOW);
            
            try {
                Format formatter = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
                s = formatter.format(new Date());
                s = config.sqlBackupPath + s + ".sql";

                Runtime runtime = Runtime.getRuntime();
                p = runtime.exec("C:\\xampp\\mysql\\bin\\mysqldump.exe -uroot -ppassword --add-drop-database -B institute_management_malpi_kelaniya -r" + s);
                //C:\\xampp\\mysql\\bin\\mysqldump.exe -uroot -pmypass --add-drop-database -B institute_management_test -r"+path
                processComplete = p.waitFor();
                Thread.sleep(1000);
                if (processComplete == 0) {
                    jLabel9.setBackground(Color.YELLOW);
                    jLabel8.setBackground(Color.GREEN);
                    Thread.sleep(2000);
                    //send email
                    Properties props = new Properties();

                    props.put("mail.smtp.user", "username");
                    props.put("mail.smtp.host", "smtp.gmail.com");
                    props.put("mail.smtp.port", "25");
                    props.put("mail.debug", "true");
                    props.put("mail.smtp.auth", "true");
                    props.put("mail.smtp.starttls.enable", "true");
                    props.put("mail.smtp.EnableSSL.enable", "true");

                    props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
                    props.setProperty("mail.smtp.socketFactory.fallback", "false");
                    props.setProperty("mail.smtp.port", "465");
                    props.setProperty("mail.smtp.socketFactory.port", "465");

                    Session session = Session.getInstance(props, new javax.mail.Authenticator() {

                        protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
                            return new javax.mail.PasswordAuthentication(user, pass);
                        }
                    });

                    try {
                        Message message = new MimeMessage(session);
                        message.setFrom(new InternetAddress("zeogen.bug@gmail.com"));
                        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("rtharindumalinda@gmail.com"));
                        message.setSubject("BUG REPORT - " + cmbInstitute.getSelectedItem().toString());
                        String htmlText = "<H3>BlueWolfInc(Pvt)Ltd.</H3><h4>Mr.Mihiran</h4>Here is the login details of your account of IMS(Pvt)Ltd.<br> " + "<h4>User_ID: Test</h4>" + "<h4>Password: Test</h4>Admin.<br>Institute Management System System.<br>IMS(Pvt)Ltd.";

                        // creating first MimeBodyPart object 
                        BodyPart messageBodyPart1 = new MimeBodyPart();
                        messageBodyPart1.setText(cmbInstitute.getSelectedItem().toString() + System.lineSeparator() + cmbLevel.getSelectedItem().toString() + System.lineSeparator() + cmbModule.getSelectedItem().toString() + System.lineSeparator()+txtDes.getText().toString());

                        // creating second MimeBodyPart object 
                        BodyPart messageBodyPart2 = new MimeBodyPart();
                        String filename = s;
                        DataSource source = new FileDataSource(filename);
                        messageBodyPart2.setDataHandler(new DataHandler(source));
                        messageBodyPart2.setFileName(filename);

                        // creating MultiPart object 
                        Multipart multipartObject = new MimeMultipart();
                        multipartObject.addBodyPart(messageBodyPart1);
                        multipartObject.addBodyPart(messageBodyPart2);

                        //message.setContent(htmlText, "text/html");
                        message.setContent(multipartObject);
                        Transport.send(message);

                        System.out.println("message sent");
                        jLabel9.setBackground(Color.GREEN);
                        jLabel10.setBackground(Color.YELLOW);
                        Thread.sleep(3000);
                        jLabel10.setBackground(Color.GREEN);
                         x= 2;y=2;z=2;
                    } catch (Exception e) {
                        jLabel9.setBackground(Color.RED);
                        jLabel10.setBackground(Color.RED);
                        System.out.println(e);
                    }
                } else {
                    jLabel8.setBackground(Color.RED);
                    jLabel9.setBackground(Color.RED);
                    jLabel10.setBackground(Color.RED);
                }
            } catch (Exception ex) {
                System.out.println(ex);
            }
            return true;
        }

        @Override
        public void done() {
            if (x == 2 && y == 2 && z == 2) {
                jLabel11.setText("Bug Successfully Reported");
                jLabel11.setForeground(Color.BLUE);
            } else {
                jLabel11.setText("Error in Bug Reporting Process");
                jLabel11.setForeground(Color.RED);
                jLabel13.setText("Please Check your Internet Connection.If its okay, Call Zeogen Support");
                jLabel13.setForeground(Color.RED);
                
            }
        }
    };

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) throws ClassNotFoundException {

        try {
            /* Set the Nimbus look and feel */
            //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
            /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
             * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
             */

            //</editor-fold>
            //UIManager.setLookAndFeel("com.jtattoo.plaf.acryl.AcrylLookAndFeel");
            // UIManager.setLookAndFeel("com.jtattoo.plaf.texture.TextureLookAndFeel");
            //  UIManager.setLookAndFeel("com.jtattoo.plaf.smart.SmartLookAndFeel");
            UIManager.setLookAndFeel("com.jtattoo.plaf.noire.NoireLookAndFeel");
            // UIManager.setLookAndFeel("com.jtattoo.plaf.acryl.AcrylLookAndFeel");
            // UIManager.setLookAndFeel("com.jtattoo.plaf.aero.AeroLookAndFeel");
            //UIManager.setLookAndFeel("com.jtattoo.plaf.aluminium.AluminiumLookAndFeel");
            //  UIManager.setLookAndFeel("com.jtattoo.plaf.bernstein.BernsteinLookAndFeel");
            //  UIManager.setLookAndFeel("com.jtattoo.plaf.fast.FastLookAndFeel");
            //  UIManager.setLookAndFeel("com.jtattoo.plaf.graphite.GraphiteLookAndFeel");
            //  UIManager.setLookAndFeel("com.jtattoo.plaf.hifi.HiFiLookAndFeel");
            // UIManager.setLookAndFeel("com.jtattoo.plaf.luna.LunaLookAndFeel");
            //  UIManager.setLookAndFeel("com.jtattoo.plaf.mcwin.McWinLookAndFeel");
            //  UIManager.setLookAndFeel("com.jtattoo.plaf.mint.MintLookAndFeel");
            /* Create and display the form */
            java.awt.EventQueue.invokeLater(new Runnable() {
                public void run() {
                    new BugMain().setVisible(true);
                }
            });
        } catch (InstantiationException ex) {
            Logger.getLogger(BugMain.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(BugMain.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(BugMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox cmbInstitute;
    private javax.swing.JComboBox cmbLevel;
    private javax.swing.JComboBox cmbModule;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextPane txtDes;
    // End of variables declaration//GEN-END:variables
}
