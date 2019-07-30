/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.institute_management.attendance_new;

import com.institute_management.DataSourse.DataSource;
import com.institute_management.configurations.Config;
import com.institute_management.course_mgt.CourseDbConnection;
import com.institute_management.course_mgt.selectCourse;
import com.institute_management.notification.notifyMain;
import com.institute_management.payment_mgt.NewPaymentDbConnection;

import com.institute_management.payment_mgt.paymentBean;
import com.institute_management.payment_mgt.paymentINIT;
import com.institute_management.report.reportDash;
import com.institute_management.student.UI.selectStudent;
import com.institute_management.student.UI.studentUpdate;
import com.institute_management.subject_mgt.DB.SubjectDbConnection;
import static com.institute_management.user_mgt.UI.NewLogin2.logger;
import com.institute_management.util.CommonMethods;
import static com.institute_management.util.CommonMethods.validateMobileNo;
import com.institute_management.util.Configurations;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextPane;
import javax.swing.SwingWorker;
import javax.swing.Timer;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.text.AttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;

/**
 *
 * @author malindad
 */
public class MainPage extends javax.swing.JFrame {

    boolean IS_First_attend = false;
    Config config = new Config();
    int globle_x = 0;
    Timer SimpleTimer = new Timer(100, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            globle_x = globle_x + 1;
            designe(globle_x);

        }
    });

    dbConnection a = new dbConnection();
    NewPaymentDbConnection conn = new NewPaymentDbConnection();
    SelectedCourseStudentDetails detailBean = new SelectedCourseStudentDetails();
    Calendar cal = Calendar.getInstance();
    String curMonth = new SimpleDateFormat("MMMM").format(cal.getTime()).toLowerCase();
    String curYear = (new SimpleDateFormat("yyyy").format(cal.getTime())).toLowerCase();
    HashMap<Integer, String> monthMap_local = new HashMap<Integer, String>();
    //payment array: this chage for LIT institute as they asked all payments in one bill
    public ArrayList<paymentBean> paymentArray = new ArrayList<paymentBean>();
    String s_id = null;
    public CommonMethods cmMethds = new CommonMethods();

    public MainPage() {

    }

    public MainPage(String[] class_details, HashMap<Integer, String> monthMap) throws SQLException, Exception {
        //log
        //cmMethds.debugSlownessIssueLog(new Date()+ "Start initComponents in main page" );

        initComponents();
        tbl1.getModel().addTableModelListener(new CheckBoxModelListener());
        monthMap_local = monthMap;
        textTotalFee.setText("0.00");
        SimpleTimer.start();
        s_id = initial_page.bean_sid.getS_id();
        IS_First_attend = a.IS_First_Entrence(s_id);

        //log
        //cmMethds.debugSlownessIssueLog(new Date()+ "Start load Image" );
        loadStudentImage();

        //cmMethds.debugSlownessIssueLog(new Date()+ "End load Image" );
        //System.out.println(s_id + "get correct id");
        if (class_details[0] == null && class_details[1] == null && class_details[0] == null) {
            JOptionPane.showMessageDialog(new JFrame(), "NO Match Found");

        } else if (class_details[0] != null || class_details[1] != null || class_details[0] != null) {
            detailBean.setC_id(class_details[0]);
            detailBean.setC_time(class_details[1]);
            detailBean.setC_type(class_details[2]);

            //log
            //cmMethds.debugSlownessIssueLog(new Date()+ "Start get_all_Courses" ); 
            ArrayList<String[]> all_classes = a.get_all_Courses(s_id);
            //cmMethds.debugSlownessIssueLog(new Date()+ "END get_all_Courses and start get_student_details" ); 
            //set student details to detail bean
            detailBean = a.get_student_details(s_id, detailBean);
            //cmMethds.debugSlownessIssueLog(new Date()+ "End get_student_details" ); 

            //set lecture details to detail bean
            if (class_details[0] != null) {
                //cmMethds.debugSlownessIssueLog(new Date()+ "start get_lecturer_Course_details" ); 
                detailBean = a.get_lecturer_Course_details(class_details[0], s_id, detailBean);
                //cmMethds.debugSlownessIssueLog(new Date()+ "end get_lecturer_Course_details" ); 
            }
            //detailBean.setC_last_payment("march");
            // set details on interface;
            //cmMethds.debugSlownessIssueLog(new Date()+ "start load initial data into labels" ); 
            lbls_name.setText(detailBean.getS_name());
            lblreg_id.setText(detailBean.getS_id());
            lblschool.setText(detailBean.getS_school());
            lblregDate.setText(detailBean.getS_reg_date());
            lbl_cardtype.setText(detailBean.getC_card_type().equals("2") ? "Normal Card" : (detailBean.getC_card_type().equals("1") ? "Half Card" : "Free Card"));
            if (detailBean.getC_last_payment() != null) {
                lblLastPayment.setText(detailBean.getC_last_payment().equals(curMonth) ? "YES" : "NO");
            } else {
                lblLastPayment.setText("No Last Payment Records");
            }
            lblLname.setText(detailBean.getL_name());
            lblclassType.setText(detailBean.getC_type_r_or_t());
            lbltime.setText(detailBean.getC_time());
            lblcourse_id.setText(detailBean.getC_id());
            lblExtraorNormal.setText(detailBean.getC_type());
            //cmMethds.debugSlownessIssueLog(new Date()+ "END" );

            //load all class payment status
            //cmMethds.debugSlownessIssueLog(new Date()+ "Start get_all_class_payment_history" );
            HashMap<Integer, Object[]> tblData = a.get_all_class_payment_history(s_id);
            //cmMethds.debugSlownessIssueLog(new Date()+ "END" );

            //cmMethds.debugSlownessIssueLog(new Date()+ "Start load payment data to table" );
            DefaultTableModel model = (DefaultTableModel) tbl1.getModel();
            model.setRowCount(0);
            for (int i = 1; i <= tblData.size(); i++) {
                model.addRow(tblData.get(i));
            }
            //cmMethds.debugSlownessIssueLog(new Date()+ "End load payment data to table" );

            //load payment
            if (class_details[0] != null) {
                ArrayList<String> payment_history;
                try {
                    //cmMethds.debugSlownessIssueLog(new Date()+ "Start getPaymentDetails" );
                    payment_history = a.getPaymentDetails(class_details[0], s_id, 11);
                    //cmMethds.debugSlownessIssueLog(new Date()+ "END getPaymentDetails" );
                    //cmMethds.debugSlownessIssueLog(new Date()+ "Start load payment history" );
                    for (int i = 0; i < payment_history.size(); i++) {
                        appendToPane(payment_history_pane, payment_history.get(i));
                        //jTextPane1.append(attendance_history.get(i));                   
                        //attendaence_history_pane.append(System.lineSeparator());
                    }
                    //cmMethds.debugSlownessIssueLog(new Date()+ "End load payment history" );
                } catch (Exception ex) {
                    Logger.getLogger(MainPage.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        }
        //cmMethds.debugSlownessIssueLog(new Date()+ "Start load lec image" );
        loadLectureImage();
        //cmMethds.debugSlownessIssueLog(new Date()+ "End load lec image" );
        //mark student attendance

        //cmMethds.debugSlownessIssueLog(new Date()+ "Start Mark Attendance" );
        String student_id = initial_page.bean_sid.getS_id().toString().trim();
        int count = 0;

        if (student_id.equals("")) {
            JOptionPane.showMessageDialog(new JFrame(), "Please Select a Student first");
        } else {
            // TODO add your handling code here:
            try {

                boolean x = new CourseDbConnection().checkstudentinCourse(student_id, detailBean.getC_id());
                //cmMethds.debugSlownessIssueLog(new Date()+ "check student existancy" );
                if (x) {
                    count = new SubjectDbConnection().markAttendence(detailBean.getC_id(), student_id, new Date(), 1);
                    //cmMethds.debugSlownessIssueLog(new Date()+ "mark attendance" );
                    if (count == -1) {
                        JOptionPane.showMessageDialog(new JFrame(), "Student is already marked today attendence to this course");
                    } else {
                        //JOptionPane.showMessageDialog(new JFrame(), "Successfully Marked the attendance");
                        //reload the attandace interface
                        attendaence_history_pane.setText(null);
                        ArrayList<String> attendance_history = a.get_attendance_history(student_id, detailBean.getC_id());
//                        for (int i = 0; i < attendance_history.size(); i++) {
//                            appendToPane(attendaence_history_pane, attendance_history.get(i));
//                        }
                        if (IS_First_attend) {
                            //validate sms data
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy HH:mm");
                            Date date = new Date();
                            String mob1 = detailBean.getSms_contact1();
                            String mob2 = detailBean.getSms_contact2();
                            String f_mob = null;

                            boolean xx = validateMobileNo(mob1);
                            if (xx) {
                                f_mob = mob1;
                            } else {
                                boolean xxx = validateMobileNo(mob2);
                                if (xxx) {
                                    f_mob = mob2;
                                }
                            }

                            //insert  sms
                            if (f_mob != null) {
                                String s_n = detailBean.getS_name().replace(" ", "+");
                                a.insert_arrive_SMS(detailBean.getS_gender(), s_n, formatter.format(date).replace(" ", "+"), f_mob, detailBean.getS_id());
                            } else {
                                JOptionPane.showMessageDialog(new JFrame(), "Invalid Mobile Number.SMS not sent");
                            }
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(new JFrame(), "Student have not enroll to this course");
                }
                // this.loadAttendLabel(attendList);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(new JFrame(), "Error In Attendence Marking" + e);
            }
            //cmMethds.debugSlownessIssueLog(new Date()+ "end Mark Attendance" );
        }

        //load attendance history
        //cmMethds.debugSlownessIssueLog(new Date()+ "Start load attendance" );
        if (class_details[0] != null) {
            ArrayList<String> attendance_history = a.get_attendance_history(s_id, class_details[0]);

            for (int i = 0; i < attendance_history.size(); i++) {
                appendToPane(attendaence_history_pane, attendance_history.get(i));
                //jTextPane1.append(attendance_history.get(i));                   
                //attendaence_history_pane.append(System.lineSeparator());
            }

        }
        //cmMethds.debugSlownessIssueLog(new Date()+ "End load attendance" );
        //sound Play
        //cmMethds.debugSlownessIssueLog(new Date()+ "Start sound play attendance" );
        sw.execute();
        //cmMethds.debugSlownessIssueLog(new Date()+ "End sound play attendance" );
    }

    SwingWorker sw = new SwingWorker() {
        @Override
        protected Object doInBackground() throws Exception {
            try {
                int weekNumber = a.get_weekNumber();
                if (weekNumber > 1) {
                    if (!lblLastPayment.getText().toString().equals("YES")) {
                        String audioFilePath = config.audioFilePath;
                        AudioPlayerExample1 player = new AudioPlayerExample1();
                        player.play(audioFilePath);
                    } else {
                        String audioFilePath = config.audioFilePath_success;
                        AudioPlayerExample1 player = new AudioPlayerExample1();
                        player.play(audioFilePath);
                    }
                } else {
                    String audioFilePath = config.audioFilePath_success;
                    AudioPlayerExample1 player = new AudioPlayerExample1();
                    player.play(audioFilePath);
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(new JFrame(), "Error In Alert System" + ex);
            }
            return null;
        }

        @Override
        public void done() {

        }
    };

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        lblImage = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        lbls_name = new javax.swing.JLabel();
        lblreg_id = new javax.swing.JLabel();
        lblschool = new javax.swing.JLabel();
        lblregDate = new javax.swing.JLabel();
        lbl_cardtype = new javax.swing.JLabel();
        lblLastPayment = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        lblLimage = new javax.swing.JLabel();
        lblLname = new javax.swing.JLabel();
        lblclassType = new javax.swing.JLabel();
        lbltime = new javax.swing.JLabel();
        lblcourse_id = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        lblExtraorNormal = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl1 = new javax.swing.JTable();
        jLabel16 = new javax.swing.JLabel();
        textTotalFee = new javax.swing.JTextField();
        txtCashIn = new javax.swing.JTextField();
        txtBalance = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        attendaence_history_pane = new javax.swing.JTextPane();
        jPanel6 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        payment_history_pane = new javax.swing.JTextPane();
        l1 = new javax.swing.JLabel();
        l2 = new javax.swing.JLabel();
        l3 = new javax.swing.JLabel();
        l4 = new javax.swing.JLabel();
        l5 = new javax.swing.JLabel();
        l6 = new javax.swing.JLabel();
        l7 = new javax.swing.JLabel();
        l8 = new javax.swing.JLabel();
        l9 = new javax.swing.JLabel();
        l10 = new javax.swing.JLabel();
        l11 = new javax.swing.JLabel();
        l12 = new javax.swing.JLabel();
        l13 = new javax.swing.JLabel();
        l14 = new javax.swing.JLabel();
        l15 = new javax.swing.JLabel();
        l16 = new javax.swing.JLabel();
        l17 = new javax.swing.JLabel();
        l18 = new javax.swing.JLabel();
        l19 = new javax.swing.JLabel();
        l20 = new javax.swing.JLabel();
        l21 = new javax.swing.JLabel();
        l22 = new javax.swing.JLabel();
        l23 = new javax.swing.JLabel();
        l24 = new javax.swing.JLabel();
        l25 = new javax.swing.JLabel();
        l26 = new javax.swing.JLabel();
        l27 = new javax.swing.JLabel();
        l28 = new javax.swing.JLabel();
        l29 = new javax.swing.JLabel();
        l30 = new javax.swing.JLabel();
        l31 = new javax.swing.JLabel();
        l32 = new javax.swing.JLabel();
        l33 = new javax.swing.JLabel();
        l34 = new javax.swing.JLabel();
        l35 = new javax.swing.JLabel();
        l36 = new javax.swing.JLabel();
        l37 = new javax.swing.JLabel();
        l38 = new javax.swing.JLabel();
        l39 = new javax.swing.JLabel();
        l40 = new javax.swing.JLabel();
        l41 = new javax.swing.JLabel();
        l42 = new javax.swing.JLabel();
        l43 = new javax.swing.JLabel();
        l44 = new javax.swing.JLabel();
        l45 = new javax.swing.JLabel();
        l46 = new javax.swing.JLabel();
        l47 = new javax.swing.JLabel();
        l48 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        current_bill_pane = new javax.swing.JTextPane();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 255)));

        jLabel2.setText("Name");

        jLabel3.setText("Reg ID");

        jLabel4.setText("School");

        jLabel5.setText("Reg Date");

        jLabel7.setText("Payments");

        jLabel8.setText("Card Type");

        lblLastPayment.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(lblImage, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jLabel4)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbls_name, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblreg_id, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblschool, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblregDate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbl_cardtype, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblLastPayment, javax.swing.GroupLayout.DEFAULT_SIZE, 303, Short.MAX_VALUE))
                .addGap(10, 10, 10))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(lblImage, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbls_name, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblreg_id, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblschool, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblregDate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbl_cardtype, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblLastPayment, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 255)));

        jLabel9.setText("Lecturer Name");

        jLabel10.setText("Course ID");

        jLabel11.setText("Class Type");

        jLabel15.setText("Time");

        lblLimage.setIcon(new ImageIcon(new ImageIcon("E:\\Image\\Supratim-Chakraborty_passport-size-photo.jpg").getImage().getScaledInstance(168, 184, Image.SCALE_DEFAULT)));

        lblcourse_id.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N

        jLabel19.setText("Extara | Normal");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(lblLimage, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addComponent(jLabel11)
                            .addComponent(jLabel15)
                            .addComponent(jLabel10))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblLname, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblclassType, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbltime, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblcourse_id, javax.swing.GroupLayout.DEFAULT_SIZE, 256, Short.MAX_VALUE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel19)
                        .addGap(18, 18, 18)
                        .addComponent(lblExtraorNormal, javax.swing.GroupLayout.DEFAULT_SIZE, 251, Short.MAX_VALUE)))
                .addGap(0, 23, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblLname, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblclassType, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbltime, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(lblcourse_id, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(lblExtraorNormal, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(lblLimage, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 255), 2));

        tbl1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 255)));
        tbl1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Course ID", "Fee", "CardType", "Last Paymnet", ""
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbl1.setGridColor(new java.awt.Color(51, 102, 255));
        tbl1.setRowHeight(25);
        tbl1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl1MouseClicked(evt);
            }
        });
        tbl1.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                tbl1PropertyChange(evt);
            }
        });
        jScrollPane1.setViewportView(tbl1);
        if (tbl1.getColumnModel().getColumnCount() > 0) {
            tbl1.getColumnModel().getColumn(0).setPreferredWidth(300);
            tbl1.getColumnModel().getColumn(1).setPreferredWidth(50);
            tbl1.getColumnModel().getColumn(2).setMinWidth(70);
            tbl1.getColumnModel().getColumn(2).setPreferredWidth(70);
            tbl1.getColumnModel().getColumn(2).setMaxWidth(70);
            tbl1.getColumnModel().getColumn(3).setPreferredWidth(100);
            tbl1.getColumnModel().getColumn(4).setPreferredWidth(20);
            tbl1.getColumnModel().getColumn(4).setMaxWidth(20);
        }

        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel16.setText("Total Fee");

        textTotalFee.setEditable(false);
        textTotalFee.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        textTotalFee.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        textTotalFee.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textTotalFeeActionPerformed(evt);
            }
        });

        txtCashIn.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        txtCashIn.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCashIn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCashInActionPerformed(evt);
            }
        });
        txtCashIn.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCashInKeyReleased(evt);
            }
        });

        txtBalance.setEditable(false);
        txtBalance.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        txtBalance.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel17.setText("Balance");

        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel18.setText("Cash IN");

        jButton1.setBackground(new java.awt.Color(102, 153, 255));
        jButton1.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jButton1.setForeground(new java.awt.Color(51, 51, 255));
        jButton1.setText("PAY SELECTED COURSES");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(255, 51, 51));
        jButton2.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 0, 51));
        jButton2.setText("PAY SELECTED COURSES WITH DISCOUNT");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(textTotalFee)
                                    .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, 217, Short.MAX_VALUE))
                                .addGap(33, 33, 33)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtCashIn, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(37, 37, 37)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel17, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtBalance, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 747, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton1)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textTotalFee)
                    .addComponent(txtCashIn)
                    .addComponent(txtBalance, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel16)
                        .addComponent(jLabel18))
                    .addComponent(jLabel17)))
        );

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        jLabel13.setFont(new java.awt.Font("Bell MT", 1, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(0, 153, 0));
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setText("Attendance History");

        attendaence_history_pane.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 255)));
        attendaence_history_pane.setOpaque(false);
        jScrollPane2.setViewportView(attendaence_history_pane);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 268, Short.MAX_VALUE)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2)
                .addContainerGap())
        );

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));

        jLabel12.setFont(new java.awt.Font("Bell MT", 1, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(51, 153, 0));
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("Payment History");

        payment_history_pane.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 255)));
        jScrollPane3.setViewportView(payment_history_pane);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(15, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3)
                .addContainerGap())
        );

        l1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 255)));

        l2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 255)));

        l3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 255)));

        l4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 255)));

        l5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 255)));

        l6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 255)));

        l7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 255)));

        l8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 255)));

        l9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 255)));

        l10.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 255)));

        l11.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 255)));

        l12.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 255)));

        l13.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 255)));

        l14.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 255)));

        l15.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 255)));

        l16.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 255)));

        l17.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 255)));

        l18.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 255)));

        l19.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 255)));

        l20.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 255)));

        l21.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 255)));

        l22.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 255)));

        l23.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 255)));

        l24.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 255)));

        l25.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 255)));

        l26.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 255)));

        l27.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 255)));

        l28.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 255)));

        l29.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 255)));

        l30.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 255)));

        l31.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 255)));

        l32.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 255)));

        l33.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 255)));

        l34.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 255)));

        l35.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 255)));

        l36.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 255)));

        l37.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 255)));

        l38.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 255)));

        l39.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 255)));

        l40.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 255)));

        l41.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 255)));

        l42.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 255)));

        l43.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 255)));

        l44.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 255)));

        l45.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 255)));

        l46.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 255)));

        l47.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 255)));

        l48.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 255)));

        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/institute_management/resources/gif/attend.gif"))); // NOI18N
        jLabel14.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(0, 0, 204), new java.awt.Color(102, 153, 255)));
        jLabel14.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel14.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel14MouseClicked(evt);
            }
        });

        jLabel20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/institute_management/resources/gif/payment.gif"))); // NOI18N
        jLabel20.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(0, 0, 204), new java.awt.Color(102, 153, 255)));
        jLabel20.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel20.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel20MouseClicked(evt);
            }
        });

        jLabel21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/institute_management/resources/gif/report.gif"))); // NOI18N
        jLabel21.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(0, 0, 204), new java.awt.Color(102, 153, 255)));
        jLabel21.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel21.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel21MouseClicked(evt);
            }
        });

        jLabel22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/institute_management/resources/gif/sms.gif"))); // NOI18N
        jLabel22.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(0, 0, 204), new java.awt.Color(102, 153, 255)));
        jLabel22.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel22.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel22MouseClicked(evt);
            }
        });

        jLabel23.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/institute_management/resources/gif/student.gif"))); // NOI18N
        jLabel23.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(0, 0, 204), new java.awt.Color(102, 153, 255)));
        jLabel23.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel23.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel23MouseClicked(evt);
            }
        });

        jLabel24.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/institute_management/resources/gif/corse.gif"))); // NOI18N
        jLabel24.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(0, 0, 204), new java.awt.Color(102, 153, 255)));
        jLabel24.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel24.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel24MouseClicked(evt);
            }
        });

        jLabel25.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(0, 153, 0));
        jLabel25.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel25.setText("Attendance");

        jLabel26.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(0, 153, 0));
        jLabel26.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel26.setText("Payments");

        jLabel27.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(0, 153, 0));
        jLabel27.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel27.setText("Report");

        jLabel29.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(0, 153, 0));
        jLabel29.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel29.setText("Course");

        jLabel30.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(0, 153, 0));
        jLabel30.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel30.setText("Student");

        jScrollPane4.setViewportView(current_bill_pane);

        jLabel31.setFont(new java.awt.Font("Bell MT", 1, 14)); // NOI18N
        jLabel31.setForeground(new java.awt.Color(0, 153, 0));
        jLabel31.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel31.setText("Current Bill Pane");

        jLabel32.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        jLabel32.setForeground(new java.awt.Color(0, 153, 0));
        jLabel32.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel32.setText("Discount");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(45, 45, 45)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(44, 44, 44)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(43, 43, 43)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(43, 43, 43)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(31, 31, 31)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(35, 35, 35)
                                .addComponent(jLabel31, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel22)
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane4)))))
                .addGap(29, 29, 29))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(l1, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(l2, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(l3, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(l4, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(l5, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(l6, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(l7, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(l8, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(l9, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(l10, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(l11, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(l12, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(l13, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(l14, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(l15, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(l16, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(l17, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(l18, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(l19, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(l20, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(l21, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(l22, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(l23, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(l24, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(l25, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(l26, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(l27, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(l28, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(l29, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(l30, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(l31, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(l32, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(l33, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(l34, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(l35, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 764, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(l36, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(l37, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(l38, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(l39, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(l40, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(l41, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(l42, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(l43, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(l44, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(l45, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(l46, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(l47, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(l48, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(37, 37, 37)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel25)
                    .addComponent(jLabel26)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel27)
                        .addComponent(jLabel29)
                        .addComponent(jLabel30)
                        .addComponent(jLabel32))
                    .addComponent(jLabel31))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, 97, Short.MAX_VALUE)
                                    .addComponent(jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, 97, Short.MAX_VALUE)
                                    .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, 97, Short.MAX_VALUE)
                                    .addComponent(jLabel23, javax.swing.GroupLayout.DEFAULT_SIZE, 97, Short.MAX_VALUE)
                                    .addComponent(jLabel24, javax.swing.GroupLayout.DEFAULT_SIZE, 97, Short.MAX_VALUE))
                                .addGap(27, 27, 27))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(2, 2, 2)))))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(l1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(l2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(l3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(l4, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(l5, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(l6, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(l7, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(l8, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(l9, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(l10, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(l11, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(l12, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(l13, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(l14, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(l15, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(l16, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(l17, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(l18, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(l19, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(l20, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(l21, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(l22, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(l23, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(l24, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(l25, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(l26, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(l27, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(l28, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(l29, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(l30, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(l31, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(l32, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(l33, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(l34, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(l35, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(l36, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(l37, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(l38, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(l39, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(l40, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(l41, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(l42, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(l43, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(l44, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(l45, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(l46, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(l47, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(l48, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void textTotalFeeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textTotalFeeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textTotalFeeActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        //cmMethds.debugSlownessIssueLog(new Date()+ "Start payment process" );
        int x = 0;
        HashMap<String, Integer> successStatus = new HashMap<String, Integer>();
        int count = 0;
        Calendar cal = Calendar.getInstance();
        String curMonth = new SimpleDateFormat("MMMM").format(cal.getTime()).toLowerCase();
        Calendar cal_prev = Calendar.getInstance();
        cal_prev.add(Calendar.MONTH, -1);
        String curMonth_prev = new SimpleDateFormat("MMMM").format(cal.getTime()).toLowerCase();

        // check select already paid month
        String msg = "Payment Status !" + System.lineSeparator() + System.lineSeparator();

        //cmMethds.debugSlownessIssueLog(new Date()+ "initialize variables" );
        try {

            Connection con = new DataSource().getConnection();
            con.setAutoCommit(false);
            //insert to bill table
            String bill_id = a.insert_to_bill_details(paymentArray, con, Configurations.UserBean.getUserName());
            //cmMethds.debugSlownessIssueLog(new Date()+ "insrted to bill table" );
            //insert to payment table
            String Payment_SMS = lbls_name.getText().toString() + " is paid for following class(es) on " + new SimpleDateFormat("YYYY-MM-dd").format(new Date()) + "-NLC-";
            if (!bill_id.equals("0")) {
                boolean haspaymentSMSbody = false;
                for (int i = 0; i < paymentArray.size(); i++) {
                    paymentBean pbean = new paymentBean();
                    pbean = paymentArray.get(i);
                    ArrayList<String> months = new ArrayList<String>();
                    months.add(paymentArray.get(i).getMonthYear().toString().split("-")[0]);
                    pbean.setMonth(months);
                    pbean.setYear(paymentArray.get(i).getMonthYear().toString().split("-")[1]);

                    boolean value = a.makePayment(pbean, con);
                    if (value) {
                        String co_des = a.getCourseDesc(pbean.getCourseID());
                        Payment_SMS = Payment_SMS + "Rs " + pbean.getAmount() + " for " + co_des + " Month of " + pbean.getMonth().get(0) + "-NLC-";
                        haspaymentSMSbody = true;
                    }
                    //cmMethds.debugSlownessIssueLog(new Date()+ "done payment" );
                }
                con.commit();
                //cmMethds.debugSlownessIssueLog(new Date()+ "End payment process" );
                //insert payment sms
                String contact_array[] = a.get_stdnt_SMS_contact(s_id).split(",");
                String f_mob = null;

                boolean xx = validateMobileNo(contact_array[0]);
                if (xx) {
                    f_mob = contact_array[0];
                } else {
                    boolean xxx = validateMobileNo(contact_array[1]);
                    if (xxx) {
                        f_mob = contact_array[1];
                    }
                }
                if (f_mob != null) {
                    if (haspaymentSMSbody) {
                        a.insert_payment_SMS(Payment_SMS, f_mob, s_id);
                    }
                } else {
                    JOptionPane.showMessageDialog(new JFrame(), lbls_name.getText().toString() + "(" + s_id + ") has Invalid Mobile Number.SMS not sent");
                }
            }

            con.close();
            if (!bill_id.equals("0")) {
                a.printBill(bill_id);
            } else {
                JOptionPane.showMessageDialog(new JFrame(), "Error when generate Bill_ID");
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(new JFrame(), "Error Occured");
        }

        //reload payment table
        try {
            //cmMethds.debugSlownessIssueLog(new Date()+ "Start reload payment table" );
            HashMap<Integer, Object[]> tblData = a.get_all_class_payment_history(initial_page.bean_sid.getS_id());
            DefaultTableModel model = (DefaultTableModel) tbl1.getModel();
            model.setRowCount(0);
            for (int i = 1; i <= tblData.size(); i++) {
                model.addRow(tblData.get(i));
            }
            //cmMethds.debugSlownessIssueLog(new Date()+ "End reload payment table" );

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(new JFrame(), "error");
        }
        paymentArray.clear();
        current_bill_pane.setText(null);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void txtCashInActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCashInActionPerformed

    }//GEN-LAST:event_txtCashInActionPerformed

    private void txtCashInKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCashInKeyReleased
        txtBalance.setText((Double.parseDouble(txtCashIn.getText())) - (Double.parseDouble(textTotalFee.getText())) + "0");

    }//GEN-LAST:event_txtCashInKeyReleased

    private void tbl1PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_tbl1PropertyChange

    }//GEN-LAST:event_tbl1PropertyChange

    private void tbl1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl1MouseClicked
        int x = 0;
        int count = 0;
        double fee = 0;
        //cmMethds.debugSlownessIssueLog(new Date()+ "Start reload payment table on table click" );
        //load payment details
        try {
            ArrayList<String> payment_history = a.getPaymentDetails(tbl1.getValueAt(tbl1.getSelectedRow(), 0).toString(), detailBean.getS_id(), 11);
            payment_history_pane.setText("");
            for (int i = 0; i < payment_history.size(); i++) {
                appendToPane(payment_history_pane, payment_history.get(i));
                //jTextPane1.append(attendance_history.get(i));                   
                //attendaence_history_pane.append(System.lineSeparator());
            }
        } catch (Exception ex) {
            Logger.getLogger(MainPage.class.getName()).log(Level.SEVERE, null, ex);
        }
        //cmMethds.debugSlownessIssueLog(new Date()+ "End reload payment table on table click" );

        //cmMethds.debugSlownessIssueLog(new Date()+ "Start reload attendance table on table click" );
        //load attendance details
        try {
            ArrayList<String> attendance_history = a.get_attendance_history(detailBean.getS_id(), tbl1.getValueAt(tbl1.getSelectedRow(), 0).toString());
            attendaence_history_pane.setText("");
            for (int i = 0; i < attendance_history.size(); i++) {
                appendToPane(attendaence_history_pane, attendance_history.get(i));
                //jTextPane1.append(attendance_history.get(i));                   
                //attendaence_history_pane.append(System.lineSeparator());
            }
        } catch (Exception ex) {
            Logger.getLogger(MainPage.class.getName()).log(Level.SEVERE, null, ex);
        }
        //cmMethds.debugSlownessIssueLog(new Date()+ "End reload attendance table on table click" );
    }//GEN-LAST:event_tbl1MouseClicked

    private void jLabel20MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel20MouseClicked
        paymentINIT spp = new paymentINIT();
        spp.setVisible(true);
    }//GEN-LAST:event_jLabel20MouseClicked

    private void jLabel21MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel21MouseClicked
        reportDash a = new reportDash();
        a.setVisible(true);
    }//GEN-LAST:event_jLabel21MouseClicked

    private void jLabel22MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel22MouseClicked
        notifyMain a = new notifyMain();
        a.setVisible(true);
    }//GEN-LAST:event_jLabel22MouseClicked

    private void jLabel24MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel24MouseClicked
        selectCourse a = new selectCourse();
        a.setVisible(true);
    }//GEN-LAST:event_jLabel24MouseClicked

    private void jLabel23MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel23MouseClicked
        selectStudent a = new selectStudent();
        a.setVisible(true);
    }//GEN-LAST:event_jLabel23MouseClicked
    public class CheckBoxModelListener implements TableModelListener {

        private static final int BOOLEAN_COLUMN = 4;

        public void tableChanged(TableModelEvent e) {
            boolean isFirstMonth = false;
            String[] last_payment;
            int last_payment_year = 0;
            String last_payment_month = null;
            try {
                int row = tbl1.getSelectedRow();
                int column = e.getColumn();
                if (column == BOOLEAN_COLUMN) {
                    TableModel model = (TableModel) e.getSource();
                    String columnName = model.getColumnName(column);
                    String course_name = model.getValueAt(row, 0).toString();

                    String cardType = model.getValueAt(row, 2).toString();
                    Double classFee = Double.parseDouble(model.getValueAt(row, 1).toString());
                    Boolean checked = (Boolean) model.getValueAt(row, column);
                    String monthYear = model.getValueAt(row, 3).toString();
                    if (checked) {
                        paymentBean pBean = new paymentBean();
                        pBean.setAmount(classFee);
                        pBean.setCardType(cardType);
                        pBean.setCourseID(course_name);
                        pBean.setComment("Full Payment");// this is not actual card type. they should have to get the half payments also
                        pBean.setMonthYear(monthYear);
                        pBean.setS_id(s_id);
                        pBean.setDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime()).toLowerCase());
                        pBean.setPayment_initiate_interface("Attendance_interface");
                        paymentArray.add(pBean);

                        //load current bill pane
                        current_bill_pane.setText(null);
                        double x = 0.0;
                        for (int i = 0; i < paymentArray.size(); i++) {
                            appendToPane(current_bill_pane, paymentArray.get(i).getCourseID() + " --> " + paymentArray.get(i).getMonthYear() + " --> " + paymentArray.get(i).getComment());
                            x = x + paymentArray.get(i).getAmount();
                        }
                        textTotalFee.setText(x + "");

                    } else {
                        //this else part is used to reove beans from arraylist.which means, once someone check curses the if he uncheck,then those beans should remove from arraylist
                        for (int i = 0; i < paymentArray.size(); i++) {
                            if (course_name.equals(paymentArray.get(i).getCourseID()) && monthYear.equals(paymentArray.get(i).getMonthYear())) {
                                paymentArray.remove(i);
                                i--;
                            }
                        }
                        //load current bill pane
                        current_bill_pane.setText(null);
                        double x = 0.0;
                        for (int i = 0; i < paymentArray.size(); i++) {
                            appendToPane(current_bill_pane, paymentArray.get(i).getCourseID() + " --> " + paymentArray.get(i).getMonthYear() + " --> " + paymentArray.get(i).getComment());
                            x = x + paymentArray.get(i).getAmount();
                        }
                        textTotalFee.setText(x + "");
                    }
                }
            } catch (Exception ex) {
                System.out.println(ex);
            }
        }
    }

    private void jLabel14MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel14MouseClicked
        String student_id = initial_page.bean_sid.getS_id().toString().trim();
        int count = 0;

        if (student_id.equals("")) {
            JOptionPane.showMessageDialog(new JFrame(), "Please Select a Student first");
        } else {
            // TODO add your handling code here:
            try {

                boolean x = new CourseDbConnection().checkstudentinCourse(student_id, detailBean.getC_id());
                if (x) {
                    count = new SubjectDbConnection().markAttendence(detailBean.getC_id(), student_id, new Date(), 1);
                    if (count == -1) {
                        JOptionPane.showMessageDialog(new JFrame(), "Student is already marked today attendence to this course");
                    } else {
                        JOptionPane.showMessageDialog(new JFrame(), "Successfully Marked the attendance");
                        //reload the attandace interface
                        attendaence_history_pane.setText(null);
                        ArrayList<String> attendance_history = a.get_attendance_history(student_id, detailBean.getC_id());
                        for (int i = 0; i < attendance_history.size(); i++) {
                            appendToPane(attendaence_history_pane, attendance_history.get(i));
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(new JFrame(), "Student have not enroll to this course");
                }
                // this.loadAttendLabel(attendList);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(new JFrame(), "Error In Attendence Marking" + e);
            }
        }
    }//GEN-LAST:event_jLabel14MouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        //cmMethds.debugSlownessIssueLog(new Date()+ "Start payment process" );
        int j = a.okcancel("This Discount can not reverse.Are you sure ?");
        if (j == 0) {
            int x = 0;
            double discount_amount = 500;
            HashMap<String, Integer> successStatus = new HashMap<String, Integer>();
            int count = 0;
            Calendar cal = Calendar.getInstance();
            String curMonth = new SimpleDateFormat("MMMM").format(cal.getTime()).toLowerCase();
            Calendar cal_prev = Calendar.getInstance();
            cal_prev.add(Calendar.MONTH, -1);
            String curMonth_prev = new SimpleDateFormat("MMMM").format(cal.getTime()).toLowerCase();

            // check select already paid month
            String msg = "Payment Status !" + System.lineSeparator() + System.lineSeparator();

            //cmMethds.debugSlownessIssueLog(new Date()+ "initialize variables" );
            try {

                //apply discount amount to payment array,
                int pay_arraySize = paymentArray.size();
                double discount_for_one_course = discount_amount / pay_arraySize;

                for (int i = 0; i < paymentArray.size(); i++) {
                    paymentBean pbean1 = new paymentBean();
                    pbean1 = paymentArray.get(i);
                    pbean1.setAmount(discount_for_one_course);
                    pbean1.setComment("Full Amount with Discount");
                }

                Connection con = new DataSource().getConnection();
                con.setAutoCommit(false);
                //insert to bill table
                String bill_id = a.insert_to_bill_details(paymentArray, con, Configurations.UserBean.getUserName());
                //cmMethds.debugSlownessIssueLog(new Date()+ "insrted to bill table" );
                //insert to payment table
                String Payment_SMS = lbls_name.getText().toString() + " is paid for following class(es) on " + new SimpleDateFormat("YYYY-MM-dd").format(new Date()) + "-NLC-";
                if (!bill_id.equals("0")) {
                    boolean haspaymentSMSbody = false;
                    for (int i = 0; i < paymentArray.size(); i++) {
                        paymentBean pbean = new paymentBean();
                        pbean = paymentArray.get(i);
                        ArrayList<String> months = new ArrayList<String>();
                        months.add(paymentArray.get(i).getMonthYear().toString().split("-")[0]);
                        pbean.setMonth(months);
                        pbean.setYear(paymentArray.get(i).getMonthYear().toString().split("-")[1]);

                        boolean value = a.makePayment(pbean, con);
                        if (value) {
                            String co_des = a.getCourseDesc(pbean.getCourseID());
                            Payment_SMS = Payment_SMS + "Rs " + pbean.getAmount() + " for " + co_des + " Month of " + pbean.getMonth().get(0) + "-NLC-";
                            haspaymentSMSbody = true;
                        }
                        //cmMethds.debugSlownessIssueLog(new Date()+ "done payment" );
                    }
                    con.commit();
                    //cmMethds.debugSlownessIssueLog(new Date()+ "End payment process" );
                    //insert payment sms
                    String contact_array[] = a.get_stdnt_SMS_contact(s_id).split(",");
                    String f_mob = null;

                    boolean xx = validateMobileNo(contact_array[0]);
                    if (xx) {
                        f_mob = contact_array[0];
                    } else {
                        boolean xxx = validateMobileNo(contact_array[1]);
                        if (xxx) {
                            f_mob = contact_array[1];
                        }
                    }
                    if (f_mob != null) {
                        if (haspaymentSMSbody) {
                            a.insert_payment_SMS(Payment_SMS, f_mob, s_id);
                        }
                    } else {
                        JOptionPane.showMessageDialog(new JFrame(), lbls_name.getText().toString() + "(" + s_id + ") has Invalid Mobile Number.SMS not sent");
                    }
                }

                con.close();
                if (!bill_id.equals("0")) {
                    a.printBill(bill_id);
                } else {
                    JOptionPane.showMessageDialog(new JFrame(), "Error when generate Bill_ID");
                }

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(new JFrame(), "Error Occured");
            }

            //reload payment table
            try {
                //cmMethds.debugSlownessIssueLog(new Date()+ "Start reload payment table" );
                HashMap<Integer, Object[]> tblData = a.get_all_class_payment_history(initial_page.bean_sid.getS_id());
                DefaultTableModel model = (DefaultTableModel) tbl1.getModel();
                model.setRowCount(0);
                for (int i = 1; i <= tblData.size(); i++) {
                    model.addRow(tblData.get(i));
                }
                //cmMethds.debugSlownessIssueLog(new Date()+ "End reload payment table" );

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(new JFrame(), "error");
            }
            paymentArray.clear();
            current_bill_pane.setText(null);

        } else {
            JOptionPane.showMessageDialog(new JFrame(), "Action Terminated");
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
            java.util.logging.Logger.getLogger(MainPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {

                    new MainPage().setVisible(true);

                } catch (Exception ex) {
                    Logger.getLogger(MainPage.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    private void loadStudentImage() {

        try {
            File f = new File(config.student_image_path + initial_page.bean_sid.getS_id() + ".jpg");
            ImageIcon image;
            if (f.exists() && !f.isDirectory()) {
                image = new ImageIcon(config.student_image_path + initial_page.bean_sid.getS_id() + ".jpg");
            } else {
                image = new ImageIcon(config.student_image_path + "error.jpg");
            }

            Image im_l = image.getImage();
            Image myImg_l = im_l.getScaledInstance(lblImage.getWidth(), lblImage.getHeight(), Image.SCALE_SMOOTH);
            Icon newImage_l = new ImageIcon(myImg_l);
            lblImage.setIcon(newImage_l);

        } catch (Exception e) {

            //------------------------log Start--------------------------------------------
            logger.error(studentUpdate.class + ".loadImage() ->error" + e);
            //-------------------------log End---------------------------------------------

        }

    }

    private void loadLectureImage() {

        try {
            File f = new File(config.lecturer_image_path + detailBean.getL_id() + ".jpg");
            ImageIcon image_l;
            if (f.exists() && !f.isDirectory()) {
                image_l = new ImageIcon(config.lecturer_image_path + detailBean.getL_id() + ".jpg");
            } else {
                image_l = new ImageIcon(config.lecturer_image_path + "error.jpg");
            }

            Image im_l = image_l.getImage();
            Image myImg_l = im_l.getScaledInstance(lblLimage.getWidth(), lblLimage.getHeight(), Image.SCALE_SMOOTH);
            Icon newImage_l = new ImageIcon(myImg_l);
            lblLimage.setIcon(newImage_l);

        } catch (Exception e) {

            //------------------------log Start--------------------------------------------
            logger.error(studentUpdate.class + ".loadImage() ->error" + e);
            //-------------------------log End---------------------------------------------

        }

    }

    private void appendToPane(JTextPane tp, String msg) {

        Color c;
        if (msg.contains("PENDING") || msg.contains("not")) {
            c = Color.RED;
        } else {
            c = Color.BLUE;
        }
        StyleContext sc = StyleContext.getDefaultStyleContext();
        AttributeSet aset = sc.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.Foreground, c);

        aset = sc.addAttribute(aset, StyleConstants.FontFamily, "Lucida Console");
        aset = sc.addAttribute(aset, StyleConstants.Alignment, StyleConstants.ALIGN_JUSTIFIED);

        int len = tp.getDocument().getLength();
        tp.setCaretPosition(len);
        tp.setCharacterAttributes(aset, false);
        tp.replaceSelection(msg + System.lineSeparator());
    }

    public void designe(int x) {
        if (x > 0) {
            l1.setOpaque(true);
            l1.setBackground(Color.BLUE);
        }
        if (x > 1) {
            l2.setOpaque(true);
            l2.setBackground(Color.BLUE);
        }
        if (x > 2) {
            l3.setOpaque(true);
            l3.setBackground(Color.BLUE);
        }
        if (x > 3) {
            l4.setOpaque(true);
            l4.setBackground(Color.BLUE);
        }
        if (x > 4) {
            l5.setOpaque(true);
            l5.setBackground(Color.BLUE);
        }
        if (x > 5) {
            l6.setOpaque(true);
            l6.setBackground(Color.BLUE);
        }
        if (x > 6) {
            l7.setOpaque(true);
            l7.setBackground(Color.BLUE);
        }
        if (x > 7) {
            l8.setOpaque(true);
            l8.setBackground(Color.BLUE);
        }
        if (x > 8) {
            l9.setOpaque(true);
            l9.setBackground(Color.BLUE);
        }
        if (x > 9) {
            l10.setOpaque(true);
            l10.setBackground(Color.BLUE);
        }
        if (x > 10) {
            l11.setOpaque(true);
            l11.setBackground(Color.BLUE);
        }
        if (x > 11) {
            l12.setOpaque(true);
            l12.setBackground(Color.BLUE);
        }
        if (x > 12) {
            l13.setOpaque(true);
            l13.setBackground(Color.BLUE);
        }
        if (x > 13) {
            l14.setOpaque(true);
            l14.setBackground(Color.BLUE);
        }
        if (x > 14) {
            l15.setOpaque(true);
            l15.setBackground(Color.BLUE);
        }
        if (x > 15) {
            l16.setOpaque(true);
            l16.setBackground(Color.BLUE);
        }
        if (x > 16) {
            l17.setOpaque(true);
            l17.setBackground(Color.BLUE);
        }
        if (x > 17) {
            l18.setOpaque(true);
            l18.setBackground(Color.BLUE);
        }
        if (x > 18) {
            l19.setOpaque(true);
            l19.setBackground(Color.BLUE);
        }
        if (x > 19) {
            l20.setOpaque(true);
            l20.setBackground(Color.BLUE);
        }
        if (x > 20) {
            l21.setOpaque(true);
            l21.setBackground(Color.BLUE);
        }
        if (x > 21) {
            l22.setOpaque(true);
            l22.setBackground(Color.BLUE);
        }
        if (x > 22) {
            l23.setOpaque(true);
            l23.setBackground(Color.BLUE);
        }
        if (x > 23) {
            l24.setOpaque(true);
            l24.setBackground(Color.BLUE);
        }
        if (x > 24) {
            l25.setOpaque(true);
            l25.setBackground(Color.BLUE);
        }
        if (x > 25) {
            l26.setOpaque(true);
            l26.setBackground(Color.BLUE);
        }
        if (x > 26) {
            l27.setOpaque(true);
            l27.setBackground(Color.BLUE);
        }
        if (x > 27) {
            l28.setOpaque(true);
            l28.setBackground(Color.BLUE);
        }
        if (x > 28) {
            l29.setOpaque(true);
            l29.setBackground(Color.BLUE);
        }
        if (x > 29) {
            l30.setOpaque(true);
            l30.setBackground(Color.BLUE);
        }
        if (x > 30) {
            l31.setOpaque(true);
            l31.setBackground(Color.BLUE);
        }
        if (x > 31) {
            l32.setOpaque(true);
            l32.setBackground(Color.BLUE);
        }
        if (x > 32) {
            l33.setOpaque(true);
            l33.setBackground(Color.BLUE);
        }
        if (x > 33) {
            l34.setOpaque(true);
            l34.setBackground(Color.BLUE);
        }
        if (x > 34) {
            l35.setOpaque(true);
            l35.setBackground(Color.BLUE);
        }
        if (x > 35) {
            l36.setOpaque(true);
            l36.setBackground(Color.BLUE);
        }
        if (x > 36) {
            l37.setOpaque(true);
            l37.setBackground(Color.BLUE);
        }
        if (x > 37) {
            l38.setOpaque(true);
            l38.setBackground(Color.BLUE);
        }
        if (x > 38) {
            l39.setOpaque(true);
            l39.setBackground(Color.BLUE);
        }
        if (x > 39) {
            l40.setOpaque(true);
            l40.setBackground(Color.BLUE);
        }
        if (x > 40) {
            l41.setOpaque(true);
            l41.setBackground(Color.BLUE);
        }
        if (x > 41) {
            l42.setOpaque(true);
            l42.setBackground(Color.BLUE);
        }
        if (x > 42) {
            l43.setOpaque(true);
            l43.setBackground(Color.BLUE);
        }
        if (x > 43) {
            l44.setOpaque(true);
            l44.setBackground(Color.BLUE);
        }
        if (x > 44) {
            l45.setOpaque(true);
            l45.setBackground(Color.BLUE);
        }
        if (x > 45) {
            l46.setOpaque(true);
            l46.setBackground(Color.BLUE);
        }
        if (x > 46) {
            l47.setOpaque(true);
            l47.setBackground(Color.BLUE);
        }
        if (x > 47) {
            l48.setOpaque(true);
            l48.setBackground(Color.BLUE);
        }

        if (x > 48) {
            globle_x = 0;
            l1.setBackground(Color.WHITE);
            l2.setBackground(Color.WHITE);
            l3.setBackground(Color.WHITE);
            l4.setBackground(Color.WHITE);
            l5.setBackground(Color.WHITE);
            l6.setBackground(Color.WHITE);
            l7.setBackground(Color.WHITE);
            l8.setBackground(Color.WHITE);
            l9.setBackground(Color.WHITE);
            l10.setBackground(Color.WHITE);
            l11.setBackground(Color.WHITE);
            l12.setBackground(Color.WHITE);
            l13.setBackground(Color.WHITE);
            l14.setBackground(Color.WHITE);
            l15.setBackground(Color.WHITE);
            l16.setBackground(Color.WHITE);
            l17.setBackground(Color.WHITE);
            l18.setBackground(Color.WHITE);
            l19.setBackground(Color.WHITE);
            l20.setBackground(Color.WHITE);
            l21.setBackground(Color.WHITE);
            l22.setBackground(Color.WHITE);
            l23.setBackground(Color.WHITE);
            l24.setBackground(Color.WHITE);
            l25.setBackground(Color.WHITE);
            l26.setBackground(Color.WHITE);
            l27.setBackground(Color.WHITE);
            l28.setBackground(Color.WHITE);
            l29.setBackground(Color.WHITE);
            l30.setBackground(Color.WHITE);
            l31.setBackground(Color.WHITE);
            l32.setBackground(Color.WHITE);
            l33.setBackground(Color.WHITE);
            l34.setBackground(Color.WHITE);
            l35.setBackground(Color.WHITE);
            l36.setBackground(Color.WHITE);
            l37.setBackground(Color.WHITE);
            l38.setBackground(Color.WHITE);
            l39.setBackground(Color.WHITE);
            l40.setBackground(Color.WHITE);
            l41.setBackground(Color.WHITE);
            l42.setBackground(Color.WHITE);
            l43.setBackground(Color.WHITE);
            l44.setBackground(Color.WHITE);
            l45.setBackground(Color.WHITE);
            l46.setBackground(Color.WHITE);
            l47.setBackground(Color.WHITE);
            l48.setBackground(Color.WHITE);
        }

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextPane attendaence_history_pane;
    private javax.swing.JTextPane current_bill_pane;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JLabel l1;
    private javax.swing.JLabel l10;
    private javax.swing.JLabel l11;
    private javax.swing.JLabel l12;
    private javax.swing.JLabel l13;
    private javax.swing.JLabel l14;
    private javax.swing.JLabel l15;
    private javax.swing.JLabel l16;
    private javax.swing.JLabel l17;
    private javax.swing.JLabel l18;
    private javax.swing.JLabel l19;
    private javax.swing.JLabel l2;
    private javax.swing.JLabel l20;
    private javax.swing.JLabel l21;
    private javax.swing.JLabel l22;
    private javax.swing.JLabel l23;
    private javax.swing.JLabel l24;
    private javax.swing.JLabel l25;
    private javax.swing.JLabel l26;
    private javax.swing.JLabel l27;
    private javax.swing.JLabel l28;
    private javax.swing.JLabel l29;
    private javax.swing.JLabel l3;
    private javax.swing.JLabel l30;
    private javax.swing.JLabel l31;
    private javax.swing.JLabel l32;
    private javax.swing.JLabel l33;
    private javax.swing.JLabel l34;
    private javax.swing.JLabel l35;
    private javax.swing.JLabel l36;
    private javax.swing.JLabel l37;
    private javax.swing.JLabel l38;
    private javax.swing.JLabel l39;
    private javax.swing.JLabel l4;
    private javax.swing.JLabel l40;
    private javax.swing.JLabel l41;
    private javax.swing.JLabel l42;
    private javax.swing.JLabel l43;
    private javax.swing.JLabel l44;
    private javax.swing.JLabel l45;
    private javax.swing.JLabel l46;
    private javax.swing.JLabel l47;
    private javax.swing.JLabel l48;
    private javax.swing.JLabel l5;
    private javax.swing.JLabel l6;
    private javax.swing.JLabel l7;
    private javax.swing.JLabel l8;
    private javax.swing.JLabel l9;
    private javax.swing.JLabel lblExtraorNormal;
    private javax.swing.JLabel lblImage;
    private javax.swing.JLabel lblLastPayment;
    private javax.swing.JLabel lblLimage;
    private javax.swing.JLabel lblLname;
    private javax.swing.JLabel lbl_cardtype;
    private javax.swing.JLabel lblclassType;
    private javax.swing.JLabel lblcourse_id;
    private javax.swing.JLabel lblregDate;
    private javax.swing.JLabel lblreg_id;
    private javax.swing.JLabel lbls_name;
    private javax.swing.JLabel lblschool;
    private javax.swing.JLabel lbltime;
    private javax.swing.JTextPane payment_history_pane;
    private javax.swing.JTable tbl1;
    private javax.swing.JTextField textTotalFee;
    private javax.swing.JTextField txtBalance;
    private javax.swing.JTextField txtCashIn;
    // End of variables declaration//GEN-END:variables
}
