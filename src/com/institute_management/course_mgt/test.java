/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.institute_management.course_mgt;

import static com.institute_management.course_mgt.selectCourse.OnlyForCourseEditBean;
import com.institute_management.student.BEAN.Student;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Malinda Ranabahu
 */
public class test extends javax.swing.JFrame {

    CourseDbConnection conn = new CourseDbConnection();
    courseBean a = OnlyForCourseEditBean;
    public static Student st = new Student();
    public static int StaticCardType = 0;
    //this variable is used for makepayment tab
    public static String oneTimeStudentID = "";
    public static HashMap searchResult = new HashMap();
    courseBean editCourseBean = new courseBean();

    public static String studentVerificationStatus = "--";
    public static String studentVerificationStatusDel = "--";

    //to identify which search button pressed
    public static int AddSearchPress = 0;
    public static int DelSearchPress = 0;

    public static String name = "";
    public static String regID = "";
    public static String NIC = "";
    public static String mobile = "";
    public static int confirmORrejectPress = 0;

    public static String nameDel = "";
    public static String regIDDel = "";
    public static String NICDel = "";
    public static String mobileDel = "";
    public static int confirmORrejectPressDel = 0;

    public test() {
        //load data to class details update tab
        try {
            editCourseBean = conn.classDetailsUpdate(a.getCourseID());
            initComponents();
            this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("images.png")));

            Timer SimpleTimer = new Timer(1000, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (confirmORrejectPress == 1) {
                        cmbStdMobReg.setSelectedItem(mobile);
                        cmbStdNICReg.setSelectedItem(NIC);
                        cmbStdIDReg.setSelectedItem(regID);
                        cmbStdNameReg.setSelectedItem(name);
                        lblvrification.setText(studentVerificationStatus);
                        confirmORrejectPress = 0;
                    }
                    if (confirmORrejectPressDel == 1) {
                        cmbStdMobRem.setSelectedItem(mobileDel);
                        cmbStdNICRem.setSelectedItem(NICDel);
                        cmbStdIDRem.setSelectedItem(regIDDel);
                        cmbStdNameRem.setSelectedItem(nameDel);
                        lblvrificationDel.setText(studentVerificationStatusDel);
                        confirmORrejectPressDel = 0;
                    }

                }
            });
            SimpleTimer.start();
            //a.setCourseID("Grade1/Sinhala/T/Samitha/B1");

            int array[] = conn.totalFreeCards(a.getCourseID());//index--> 0-free/1-half/2-normal/3-total

            //load data in header
            lblName.setText(":   " + a.getLecturerName());
            lblBatchNo.setText(":   " + Integer.toString(a.getBatchNumber()));
            lblGrade.setText(":   " + a.getGrade());
            lblHalf.setText(":   " + Integer.toString(array[1]));
            lblMonthlyFee.setText(":   " + Double.toString(a.getMonthlyFee()));
            lblSubject.setText(":   " + a.getSubject());
            lblTotalFee.setText(":   " + Double.toString(a.getTotalCourseFee()));
            lblfree.setText(":   " + Integer.toString(array[0]));
            lbltotalStudent.setText(":   " + Integer.toString(array[3]));

//            txtClassType.setText(a.getCourseType());
//            txtClassType.disable();
//            txtGrade.setText(a.getGrade());
//            txtSubject.setText(a.getSubject());
            // load extra classes
            HashMap<Integer, Object[]> tblData = conn.getExtraClassDetails(a.getCourseID());
            DefaultTableModel model = (DefaultTableModel) tblExtra.getModel();
            model.setRowCount(0);
            for (int i = 1; i <= tblData.size(); i++) {
                model.addRow(tblData.get(i));
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(new JFrame(), "Error occured when loading initial data.");
        }
        rIssueFree.setSelected(true);
        rEditFree.setSelected(true);
        

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
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jTabbedPane3 = new javax.swing.JTabbedPane();
        jLocaleChooser1 = new com.toedter.components.JLocaleChooser();
        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        rNormal = new javax.swing.JRadioButton();
        rhalf = new javax.swing.JRadioButton();
        rFull = new javax.swing.JRadioButton();
        jLabel37 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel48 = new javax.swing.JLabel();
        jLabel49 = new javax.swing.JLabel();
        btnSearch = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        cmbStdNameReg = new javax.swing.JComboBox();
        cmbStdIDReg = new javax.swing.JComboBox();
        cmbStdNICReg = new javax.swing.JComboBox();
        cmbStdMobReg = new javax.swing.JComboBox();
        lblvrification = new javax.swing.JLabel();
        jLabel57 = new javax.swing.JLabel();
        jLabel58 = new javax.swing.JLabel();
        jButton19 = new javax.swing.JButton();
        jPanel9 = new javax.swing.JPanel();
        btnDelete = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jLabel50 = new javax.swing.JLabel();
        jLabel51 = new javax.swing.JLabel();
        jLabel52 = new javax.swing.JLabel();
        jLabel53 = new javax.swing.JLabel();
        jButton14 = new javax.swing.JButton();
        jButton21 = new javax.swing.JButton();
        jLabel54 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox();
        cmbStdIDRem = new javax.swing.JComboBox();
        cmbStdNICRem = new javax.swing.JComboBox();
        cmbStdNameRem = new javax.swing.JComboBox();
        cmbStdMobRem = new javax.swing.JComboBox();
        jLabel59 = new javax.swing.JLabel();
        jLabel60 = new javax.swing.JLabel();
        lblvrificationDel = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jPanel14 = new javax.swing.JPanel();
        ExtraEndTime = new javax.swing.JSpinner();
        jLabel7 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        extraStartTime = new javax.swing.JSpinner();
        jdate = new com.toedter.calendar.JDateChooser();
        jLabel5 = new javax.swing.JLabel();
        extraAdd = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jLabel33 = new javax.swing.JLabel();
        jPanel15 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblExtra = new javax.swing.JTable();
        jButton9 = new javax.swing.JButton();
        jLabel34 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jPanel16 = new javax.swing.JPanel();
        rIssueFree = new javax.swing.JRadioButton();
        rIssue50Free = new javax.swing.JRadioButton();
        btnIssue = new javax.swing.JButton();
        jButton13 = new javax.swing.JButton();
        jLabel30 = new javax.swing.JLabel();
        cmbStdIDReg1 = new javax.swing.JComboBox();
        jLabel14 = new javax.swing.JLabel();
        jLabel61 = new javax.swing.JLabel();
        cmbStdNICReg1 = new javax.swing.JComboBox();
        cmbStdNameReg1 = new javax.swing.JComboBox();
        jLabel62 = new javax.swing.JLabel();
        jLabel63 = new javax.swing.JLabel();
        cmbStdMobReg1 = new javax.swing.JComboBox();
        btnSearch1 = new javax.swing.JButton();
        jButton23 = new javax.swing.JButton();
        jLabel72 = new javax.swing.JLabel();
        jLabel73 = new javax.swing.JLabel();
        lblvrification1 = new javax.swing.JLabel();
        jPanel17 = new javax.swing.JPanel();
        rEditFree = new javax.swing.JRadioButton();
        rEdit50Free = new javax.swing.JRadioButton();
        btnEdit = new javax.swing.JButton();
        jButton15 = new javax.swing.JButton();
        jLabel32 = new javax.swing.JLabel();
        jLabel64 = new javax.swing.JLabel();
        jLabel65 = new javax.swing.JLabel();
        cmbStdNICReg2 = new javax.swing.JComboBox();
        cmbStdIDReg2 = new javax.swing.JComboBox();
        jLabel66 = new javax.swing.JLabel();
        jLabel67 = new javax.swing.JLabel();
        cmbStdMobReg2 = new javax.swing.JComboBox();
        cmbStdNameReg2 = new javax.swing.JComboBox();
        btnSearch2 = new javax.swing.JButton();
        jButton24 = new javax.swing.JButton();
        jLabel74 = new javax.swing.JLabel();
        jLabel75 = new javax.swing.JLabel();
        lblvrification2 = new javax.swing.JLabel();
        jPanel18 = new javax.swing.JPanel();
        jButton16 = new javax.swing.JButton();
        jButton17 = new javax.swing.JButton();
        jLabel29 = new javax.swing.JLabel();
        cmbStdIDReg3 = new javax.swing.JComboBox();
        jLabel68 = new javax.swing.JLabel();
        jLabel69 = new javax.swing.JLabel();
        cmbStdNICReg3 = new javax.swing.JComboBox();
        jLabel70 = new javax.swing.JLabel();
        jLabel71 = new javax.swing.JLabel();
        cmbStdNameReg3 = new javax.swing.JComboBox();
        cmbStdMobReg3 = new javax.swing.JComboBox();
        btnSearch3 = new javax.swing.JButton();
        jButton25 = new javax.swing.JButton();
        jLabel76 = new javax.swing.JLabel();
        jLabel77 = new javax.swing.JLabel();
        lblvrification3 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtMonthlyfee = new javax.swing.JTextField();
        txtTotalFee = new javax.swing.JTextField();
        test = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jLabel36 = new javax.swing.JLabel();
        jPanel12 = new javax.swing.JPanel();
        jLabel56 = new javax.swing.JLabel();
        txtPrecentage = new javax.swing.JTextField();
        test1 = new javax.swing.JButton();
        jButton22 = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jPanel13 = new javax.swing.JPanel();
        chkMonday = new javax.swing.JCheckBox();
        chkTuesday = new javax.swing.JCheckBox();
        chkWednesday = new javax.swing.JCheckBox();
        chkThursday = new javax.swing.JCheckBox();
        chkFriday = new javax.swing.JCheckBox();
        chkSaturday = new javax.swing.JCheckBox();
        chkSunday = new javax.swing.JCheckBox();
        monSt = new javax.swing.JSpinner();
        monEd = new javax.swing.JSpinner();
        tueSt = new javax.swing.JSpinner();
        tueEd = new javax.swing.JSpinner();
        wedSt = new javax.swing.JSpinner();
        wedEd = new javax.swing.JSpinner();
        thuSt = new javax.swing.JSpinner();
        thuEd = new javax.swing.JSpinner();
        friSt = new javax.swing.JSpinner();
        friEd = new javax.swing.JSpinner();
        satSt = new javax.swing.JSpinner();
        satEd = new javax.swing.JSpinner();
        sunSt = new javax.swing.JSpinner();
        sunEd = new javax.swing.JSpinner();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jLabel35 = new javax.swing.JLabel();
        jPanel19 = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        lblName = new javax.swing.JLabel();
        lblSubject = new javax.swing.JLabel();
        lblGrade = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        lblTotalFee = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        lblMonthlyFee = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        lblBatchNo = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        lbltotalStudent = new javax.swing.JLabel();
        lblfree = new javax.swing.JLabel();
        lblHalf = new javax.swing.JLabel();

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        jTabbedPane2.addTab("tab1", jTabbedPane3);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setMaximumSize(null);
        setMinimumSize(null);
        setResizable(false);

        jTabbedPane1.setBackground(new java.awt.Color(255, 255, 255));
        jTabbedPane1.setMinimumSize(new java.awt.Dimension(920, 548));
        jTabbedPane1.setPreferredSize(new java.awt.Dimension(920, 628));

        jPanel1.setMaximumSize(new java.awt.Dimension(920, 520));
        jPanel1.setMinimumSize(new java.awt.Dimension(920, 520));

        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder("Search Students"));
        jPanel8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanel8MouseEntered(evt);
            }
        });

        jButton1.setForeground(new java.awt.Color(51, 51, 255));
        jButton1.setText("A D D");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        rNormal.setForeground(new java.awt.Color(51, 51, 255));
        rNormal.setSelected(true);
        rNormal.setText("Normal Card");
        rNormal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rNormalActionPerformed(evt);
            }
        });

        rhalf.setForeground(new java.awt.Color(51, 51, 255));
        rhalf.setText("50% Free Card");
        rhalf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rhalfActionPerformed(evt);
            }
        });

        rFull.setForeground(new java.awt.Color(51, 51, 255));
        rFull.setText("Totally Free Card");
        rFull.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rFullActionPerformed(evt);
            }
        });

        jLabel37.setFont(new java.awt.Font("Calibri", 1, 11)); // NOI18N
        jLabel37.setForeground(new java.awt.Color(0, 0, 102));

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel1.setText("Reg ID");

        jLabel27.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel27.setText("Name");

        jLabel48.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel48.setText("NIC");

        jLabel49.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel49.setText("Mobile");

        btnSearch.setForeground(new java.awt.Color(0, 0, 204));
        btnSearch.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/institute_management/resources/images/icons/352091-32.png"))); // NOI18N
        btnSearch.setText("SEARCH");
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        jButton10.setForeground(new java.awt.Color(0, 0, 255));
        jButton10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/institute_management/resources/images/icons/896650-32.png"))); // NOI18N
        jButton10.setText("CLEAR");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        cmbStdNameReg.setEditable(true);
        cmbStdNameReg.setFont(new java.awt.Font("Calibri", 1, 12)); // NOI18N
        cmbStdNameReg.setForeground(new java.awt.Color(0, 102, 255));
        cmbStdNameReg.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cmbStdNameRegMouseClicked(evt);
            }
        });
        cmbStdNameReg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbStdNameRegActionPerformed(evt);
            }
        });

        cmbStdIDReg.setEditable(true);
        cmbStdIDReg.setFont(new java.awt.Font("Calibri", 1, 12)); // NOI18N
        cmbStdIDReg.setForeground(new java.awt.Color(0, 102, 255));
        cmbStdIDReg.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cmbStdIDRegMouseClicked(evt);
            }
        });
        cmbStdIDReg.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cmbStdIDRegKeyPressed(evt);
            }
        });

        cmbStdNICReg.setEditable(true);
        cmbStdNICReg.setFont(new java.awt.Font("Calibri", 1, 12)); // NOI18N
        cmbStdNICReg.setForeground(new java.awt.Color(0, 102, 255));
        cmbStdNICReg.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cmbStdNICRegMouseClicked(evt);
            }
        });

        cmbStdMobReg.setEditable(true);
        cmbStdMobReg.setFont(new java.awt.Font("Calibri", 1, 12)); // NOI18N
        cmbStdMobReg.setForeground(new java.awt.Color(0, 102, 255));
        cmbStdMobReg.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cmbStdMobRegMouseClicked(evt);
            }
        });

        lblvrification.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        lblvrification.setForeground(new java.awt.Color(0, 0, 255));

        jLabel57.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        jLabel57.setForeground(new java.awt.Color(0, 0, 255));
        jLabel57.setText("Student Verification Status");

        jLabel58.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel58.setForeground(new java.awt.Color(0, 0, 255));
        jLabel58.setText(":");

        jButton19.setForeground(new java.awt.Color(0, 0, 255));
        jButton19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/institute_management/resources/images/icons/896650-32.png"))); // NOI18N
        jButton19.setText("CLEAR");
        jButton19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton19ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel48, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(26, 26, 26)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cmbStdIDReg, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmbStdNICReg, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 74, Short.MAX_VALUE)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel27, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel49, javax.swing.GroupLayout.DEFAULT_SIZE, 47, Short.MAX_VALUE))
                        .addGap(19, 19, 19)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cmbStdMobReg, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addGap(30, 30, 30)
                                .addComponent(btnSearch)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton10))
                            .addComponent(cmbStdNameReg, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel57)
                        .addGap(13, 13, 13)
                        .addComponent(jLabel58, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lblvrification, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(209, 209, 209)))
                .addGap(24, 24, 24))
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addComponent(jLabel37)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(rNormal)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(rhalf)
                .addGap(156, 156, 156)
                .addComponent(rFull)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton19)
                .addGap(17, 17, 17))
        );

        jPanel8Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {rFull, rNormal, rhalf});

        jPanel8Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnSearch, jButton10});

        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addComponent(jLabel37, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel27)
                    .addComponent(cmbStdNameReg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbStdIDReg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel48)
                    .addComponent(jLabel49)
                    .addComponent(cmbStdNICReg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbStdMobReg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblvrification, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel57, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel58))
                .addGap(35, 35, 35)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rNormal)
                    .addComponent(rhalf)
                    .addComponent(rFull))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 53, Short.MAX_VALUE)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton19, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30))
        );

        jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder("Remove Students From Course"));
        jPanel9.setPreferredSize(new java.awt.Dimension(342, 232));

        btnDelete.setForeground(new java.awt.Color(51, 51, 255));
        btnDelete.setText("D E L E T E");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        jButton4.setForeground(new java.awt.Color(51, 51, 255));
        jButton4.setText("C L E A R");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jLabel50.setText("NIC");

        jLabel51.setText("Reg ID");

        jLabel52.setText("Name");

        jLabel53.setText("Mobile");

        jButton14.setText("Search");
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });

        jButton21.setText("Clear");
        jButton21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton21ActionPerformed(evt);
            }
        });

        jLabel54.setText("Reason To Remove");

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-- Select the Reason --", "Not Attend Any More", "Payment Issue", "Misbehaviour", " " }));
        jComboBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox2ActionPerformed(evt);
            }
        });

        cmbStdIDRem.setEditable(true);
        cmbStdIDRem.setFont(new java.awt.Font("Calibri", 1, 12)); // NOI18N
        cmbStdIDRem.setForeground(new java.awt.Color(0, 102, 255));
        cmbStdIDRem.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cmbStdIDRemMouseClicked(evt);
            }
        });
        cmbStdIDRem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbStdIDRemActionPerformed(evt);
            }
        });

        cmbStdNICRem.setEditable(true);
        cmbStdNICRem.setFont(new java.awt.Font("Calibri", 1, 12)); // NOI18N
        cmbStdNICRem.setForeground(new java.awt.Color(0, 102, 255));
        cmbStdNICRem.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cmbStdNICRemMouseClicked(evt);
            }
        });

        cmbStdNameRem.setEditable(true);
        cmbStdNameRem.setFont(new java.awt.Font("Calibri", 1, 12)); // NOI18N
        cmbStdNameRem.setForeground(new java.awt.Color(0, 102, 255));
        cmbStdNameRem.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cmbStdNameRemMouseClicked(evt);
            }
        });

        cmbStdMobRem.setEditable(true);
        cmbStdMobRem.setFont(new java.awt.Font("Calibri", 1, 12)); // NOI18N
        cmbStdMobRem.setForeground(new java.awt.Color(0, 102, 255));
        cmbStdMobRem.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cmbStdMobRemMouseClicked(evt);
            }
        });

        jLabel59.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        jLabel59.setForeground(new java.awt.Color(0, 0, 255));
        jLabel59.setText("Student Verification Status");

        jLabel60.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel60.setForeground(new java.awt.Color(0, 0, 255));
        jLabel60.setText(":");

        lblvrificationDel.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        lblvrificationDel.setForeground(new java.awt.Color(0, 0, 255));

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel59)
                .addGap(13, 13, 13)
                .addComponent(jLabel60, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblvrificationDel, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel51)
                    .addComponent(jLabel50))
                .addGap(18, 18, 18)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cmbStdIDRem, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbStdNICRem, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(jLabel53, javax.swing.GroupLayout.DEFAULT_SIZE, 53, Short.MAX_VALUE)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addGap(41, 41, 41)
                                .addComponent(jButton14)
                                .addGap(18, 18, 18)
                                .addComponent(jButton21))
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addGap(5, 5, 5)
                                .addComponent(cmbStdMobRem, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(26, 26, 26))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(jLabel52)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cmbStdNameRem, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(jLabel54)
                        .addGap(36, 36, 36)
                        .addComponent(jComboBox2, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 321, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton4)))
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel51)
                    .addComponent(jLabel52)
                    .addComponent(cmbStdIDRem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbStdNameRem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel50)
                    .addComponent(jLabel53)
                    .addComponent(cmbStdNICRem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbStdMobRem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton14, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton21))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblvrificationDel, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel59, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel60))
                .addGap(45, 45, 45)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel54))
                .addGap(30, 30, 30)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnDelete)
                    .addComponent(jButton4))
                .addGap(66, 66, 66))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, 546, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(32, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, 391, Short.MAX_VALUE))
                .addContainerGap(92, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Add | Delete Students", jPanel1);

        jPanel14.setBorder(javax.swing.BorderFactory.createTitledBorder("Add New Extra Class"));

        Date date = new Date();
        SpinnerDateModel smMonSt2 = new SpinnerDateModel(date, null,null, Calendar.HOUR_OF_DAY);
        ExtraEndTime = new javax.swing.JSpinner(smMonSt2);
        JSpinner.DateEditor deMonSt2 = new JSpinner.DateEditor(ExtraEndTime, "hh:mm a");
        ExtraEndTime.setEditor(deMonSt2);

        jLabel7.setForeground(new java.awt.Color(0, 51, 255));
        jLabel7.setText("End Time");

        jLabel6.setForeground(new java.awt.Color(0, 51, 255));
        jLabel6.setText("Start Time");

        date = new Date();
        SpinnerDateModel smMonSt1 = new SpinnerDateModel(date, null,null, Calendar.HOUR_OF_DAY);
        extraStartTime = new javax.swing.JSpinner(smMonSt1);
        JSpinner.DateEditor deMonSt1 = new JSpinner.DateEditor(extraStartTime, "hh:mm a");
        extraStartTime.setEditor(deMonSt1);

        jdate.setDateFormatString("yyyy-MMM-dd");

        jLabel5.setForeground(new java.awt.Color(0, 51, 255));
        jLabel5.setText("Select Date");

        extraAdd.setForeground(new java.awt.Color(0, 51, 255));
        extraAdd.setText("A D D");
        extraAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                extraAddActionPerformed(evt);
            }
        });

        jButton11.setForeground(new java.awt.Color(0, 51, 255));
        jButton11.setText("C L E A R");
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });

        jLabel33.setFont(new java.awt.Font("Calibri", 1, 11)); // NOI18N
        jLabel33.setForeground(new java.awt.Color(0, 0, 102));

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7))
                .addGap(25, 25, 25)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jdate, javax.swing.GroupLayout.DEFAULT_SIZE, 161, Short.MAX_VALUE)
                    .addComponent(extraStartTime)
                    .addComponent(ExtraEndTime))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap(121, Short.MAX_VALUE)
                .addComponent(extraAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton11, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel14Layout.createSequentialGroup()
                .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jdate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(18, 18, 18)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(extraStartTime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGap(18, 18, 18)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ExtraEndTime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(extraAdd)
                    .addComponent(jButton11))
                .addGap(20, 20, 20))
        );

        jPanel15.setBorder(javax.swing.BorderFactory.createTitledBorder("Remove Extra Classes"));

        tblExtra.setForeground(new java.awt.Color(0, 51, 255));
        tblExtra.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Class Date", "Start Time", "End Time", "Status", "Remove"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblExtra);
        if (tblExtra.getColumnModel().getColumnCount() > 0) {
            tblExtra.getColumnModel().getColumn(3).setPreferredWidth(50);
            tblExtra.getColumnModel().getColumn(4).setPreferredWidth(25);
        }

        jButton9.setForeground(new java.awt.Color(0, 51, 255));
        jButton9.setText("D E L E T E");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        jLabel34.setFont(new java.awt.Font("Calibri", 1, 11)); // NOI18N
        jLabel34.setForeground(new java.awt.Color(0, 0, 102));

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 698, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel15Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel15Layout.createSequentialGroup()
                .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton9))
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(130, 130, 130)
                .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(148, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(111, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Manage Extra Classes", jPanel5);

        jPanel3.setForeground(new java.awt.Color(0, 0, 255));
        jPanel3.setLayout(null);

        jPanel16.setBorder(javax.swing.BorderFactory.createTitledBorder("Issue New Free Card"));

        rIssueFree.setForeground(new java.awt.Color(0, 0, 255));
        rIssueFree.setText("Totally Free");
        rIssueFree.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rIssueFreeActionPerformed(evt);
            }
        });

        rIssue50Free.setForeground(new java.awt.Color(0, 0, 255));
        rIssue50Free.setText("50% Free");
        rIssue50Free.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rIssue50FreeActionPerformed(evt);
            }
        });

        btnIssue.setForeground(new java.awt.Color(0, 0, 255));
        btnIssue.setText("I S S U E");
        btnIssue.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIssueActionPerformed(evt);
            }
        });

        jButton13.setForeground(new java.awt.Color(0, 0, 255));
        jButton13.setText("C L E A R");
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });

        jLabel30.setFont(new java.awt.Font("Calibri", 1, 11)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(0, 0, 102));

        cmbStdIDReg1.setEditable(true);
        cmbStdIDReg1.setFont(new java.awt.Font("Calibri", 1, 12)); // NOI18N
        cmbStdIDReg1.setForeground(new java.awt.Color(0, 102, 255));
        cmbStdIDReg1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cmbStdIDReg1MouseClicked(evt);
            }
        });
        cmbStdIDReg1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cmbStdIDReg1KeyPressed(evt);
            }
        });

        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel14.setText("Reg ID");

        jLabel61.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel61.setText("NIC");

        cmbStdNICReg1.setEditable(true);
        cmbStdNICReg1.setFont(new java.awt.Font("Calibri", 1, 12)); // NOI18N
        cmbStdNICReg1.setForeground(new java.awt.Color(0, 102, 255));
        cmbStdNICReg1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cmbStdNICReg1MouseClicked(evt);
            }
        });

        cmbStdNameReg1.setEditable(true);
        cmbStdNameReg1.setFont(new java.awt.Font("Calibri", 1, 12)); // NOI18N
        cmbStdNameReg1.setForeground(new java.awt.Color(0, 102, 255));
        cmbStdNameReg1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cmbStdNameReg1MouseClicked(evt);
            }
        });
        cmbStdNameReg1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbStdNameReg1ActionPerformed(evt);
            }
        });

        jLabel62.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel62.setText("Name");

        jLabel63.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel63.setText("Mobile");

        cmbStdMobReg1.setEditable(true);
        cmbStdMobReg1.setFont(new java.awt.Font("Calibri", 1, 12)); // NOI18N
        cmbStdMobReg1.setForeground(new java.awt.Color(0, 102, 255));
        cmbStdMobReg1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cmbStdMobReg1MouseClicked(evt);
            }
        });

        btnSearch1.setText("Search");
        btnSearch1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearch1ActionPerformed(evt);
            }
        });

        jButton23.setText("Clear");
        jButton23.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton23ActionPerformed(evt);
            }
        });

        jLabel72.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        jLabel72.setForeground(new java.awt.Color(0, 0, 255));
        jLabel72.setText("Student Verification Status");

        jLabel73.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel73.setForeground(new java.awt.Color(0, 0, 255));
        jLabel73.setText(":");

        lblvrification1.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        lblvrification1.setForeground(new java.awt.Color(0, 0, 255));

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addComponent(rIssueFree)
                        .addGap(53, 53, 53)
                        .addComponent(rIssue50Free, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addComponent(jLabel72)
                        .addGap(13, 13, 13)
                        .addComponent(jLabel73, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lblvrification1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel16Layout.createSequentialGroup()
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel16Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(btnSearch1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton23))
                            .addGroup(jPanel16Layout.createSequentialGroup()
                                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel61, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel14))
                                .addGap(26, 26, 26)
                                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(cmbStdIDReg1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(cmbStdNICReg1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel62, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel63, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(19, 19, 19)
                                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(cmbStdNameReg1, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cmbStdMobReg1, 0, 1, Short.MAX_VALUE))))
                        .addGap(13, 13, 13))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel16Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnIssue, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton13, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)))
                .addGap(57, 57, 57))
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(jLabel62)
                    .addComponent(cmbStdNameReg1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbStdIDReg1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel61)
                    .addComponent(jLabel63)
                    .addComponent(cmbStdNICReg1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbStdMobReg1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnSearch1)
                    .addComponent(jButton23))
                .addGap(40, 40, 40)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblvrification1, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel72, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel73))
                .addGap(49, 49, 49)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rIssueFree)
                    .addComponent(rIssue50Free))
                .addGap(18, 18, 18)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnIssue)
                    .addComponent(jButton13))
                .addGap(32, 32, 32))
        );

        jPanel3.add(jPanel16);
        jPanel16.setBounds(30, 40, 410, 340);

        jPanel17.setBorder(javax.swing.BorderFactory.createTitledBorder("Update Free Card"));

        rEditFree.setForeground(new java.awt.Color(0, 0, 255));
        rEditFree.setText("Totally Free");
        rEditFree.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rEditFreeActionPerformed(evt);
            }
        });

        rEdit50Free.setForeground(new java.awt.Color(0, 0, 255));
        rEdit50Free.setText("50% Free");
        rEdit50Free.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rEdit50FreeActionPerformed(evt);
            }
        });

        btnEdit.setForeground(new java.awt.Color(0, 0, 255));
        btnEdit.setText("E D I T");
        btnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditActionPerformed(evt);
            }
        });

        jButton15.setForeground(new java.awt.Color(0, 0, 255));
        jButton15.setText("C L E A R");
        jButton15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton15ActionPerformed(evt);
            }
        });

        jLabel32.setFont(new java.awt.Font("Calibri", 1, 11)); // NOI18N
        jLabel32.setForeground(new java.awt.Color(0, 0, 102));

        jLabel64.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel64.setText("Reg ID");

        jLabel65.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel65.setText("NIC");

        cmbStdNICReg2.setEditable(true);
        cmbStdNICReg2.setFont(new java.awt.Font("Calibri", 1, 12)); // NOI18N
        cmbStdNICReg2.setForeground(new java.awt.Color(0, 102, 255));
        cmbStdNICReg2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cmbStdNICReg2MouseClicked(evt);
            }
        });

        cmbStdIDReg2.setEditable(true);
        cmbStdIDReg2.setFont(new java.awt.Font("Calibri", 1, 12)); // NOI18N
        cmbStdIDReg2.setForeground(new java.awt.Color(0, 102, 255));
        cmbStdIDReg2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cmbStdIDReg2MouseClicked(evt);
            }
        });
        cmbStdIDReg2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cmbStdIDReg2KeyPressed(evt);
            }
        });

        jLabel66.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel66.setText("Name");

        jLabel67.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel67.setText("Mobile");

        cmbStdMobReg2.setEditable(true);
        cmbStdMobReg2.setFont(new java.awt.Font("Calibri", 1, 12)); // NOI18N
        cmbStdMobReg2.setForeground(new java.awt.Color(0, 102, 255));
        cmbStdMobReg2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cmbStdMobReg2MouseClicked(evt);
            }
        });

        cmbStdNameReg2.setEditable(true);
        cmbStdNameReg2.setFont(new java.awt.Font("Calibri", 1, 12)); // NOI18N
        cmbStdNameReg2.setForeground(new java.awt.Color(0, 102, 255));
        cmbStdNameReg2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cmbStdNameReg2MouseClicked(evt);
            }
        });
        cmbStdNameReg2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbStdNameReg2ActionPerformed(evt);
            }
        });

        btnSearch2.setText("Search");
        btnSearch2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearch2ActionPerformed(evt);
            }
        });

        jButton24.setText("Clear");
        jButton24.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton24ActionPerformed(evt);
            }
        });

        jLabel74.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        jLabel74.setForeground(new java.awt.Color(0, 0, 255));
        jLabel74.setText("Student Verification Status");

        jLabel75.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel75.setForeground(new java.awt.Color(0, 0, 255));
        jLabel75.setText(":");

        lblvrification2.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        lblvrification2.setForeground(new java.awt.Color(0, 0, 255));

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel17Layout.createSequentialGroup()
                        .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel17Layout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnSearch2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton24))
                            .addGroup(jPanel17Layout.createSequentialGroup()
                                .addGap(8, 8, 8)
                                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel65, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel64))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(cmbStdIDReg2, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(cmbStdNICReg2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel66, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel67, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(19, 19, 19)
                                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(cmbStdNameReg2, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cmbStdMobReg2, 0, 1, Short.MAX_VALUE))))
                        .addGap(14, 14, 14))
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel17Layout.createSequentialGroup()
                                .addComponent(rEditFree)
                                .addGap(52, 52, 52)
                                .addComponent(rEdit50Free))
                            .addGroup(jPanel17Layout.createSequentialGroup()
                                .addComponent(jLabel74)
                                .addGap(13, 13, 13)
                                .addComponent(jLabel75, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(lblvrification2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel17Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton15, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(52, 52, 52))
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel66)
                            .addComponent(cmbStdNameReg2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmbStdIDReg2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel67)
                            .addComponent(cmbStdNICReg2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmbStdMobReg2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel65)))
                    .addComponent(jLabel64))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnSearch2)
                    .addComponent(jButton24))
                .addGap(40, 40, 40)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblvrification2, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel74, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel75)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rEditFree)
                    .addComponent(rEdit50Free))
                .addGap(26, 26, 26)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnEdit)
                    .addComponent(jButton15))
                .addGap(21, 21, 21))
        );

        jPanel3.add(jPanel17);
        jPanel17.setBounds(490, 40, 400, 340);

        jPanel18.setBorder(javax.swing.BorderFactory.createTitledBorder("Cancel Free Card"));

        jButton16.setForeground(new java.awt.Color(0, 0, 255));
        jButton16.setText("D E L E T E");
        jButton16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton16ActionPerformed(evt);
            }
        });

        jButton17.setForeground(new java.awt.Color(0, 0, 255));
        jButton17.setText("C L E A R");
        jButton17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton17ActionPerformed(evt);
            }
        });

        jLabel29.setFont(new java.awt.Font("Calibri", 1, 11)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(0, 0, 102));

        cmbStdIDReg3.setEditable(true);
        cmbStdIDReg3.setFont(new java.awt.Font("Calibri", 1, 12)); // NOI18N
        cmbStdIDReg3.setForeground(new java.awt.Color(0, 102, 255));
        cmbStdIDReg3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cmbStdIDReg3MouseClicked(evt);
            }
        });
        cmbStdIDReg3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cmbStdIDReg3KeyPressed(evt);
            }
        });

        jLabel68.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel68.setText("Reg ID");

        jLabel69.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel69.setText("NIC");

        cmbStdNICReg3.setEditable(true);
        cmbStdNICReg3.setFont(new java.awt.Font("Calibri", 1, 12)); // NOI18N
        cmbStdNICReg3.setForeground(new java.awt.Color(0, 102, 255));
        cmbStdNICReg3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cmbStdNICReg3MouseClicked(evt);
            }
        });

        jLabel70.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel70.setText("Mobile");

        jLabel71.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel71.setText("Name");

        cmbStdNameReg3.setEditable(true);
        cmbStdNameReg3.setFont(new java.awt.Font("Calibri", 1, 12)); // NOI18N
        cmbStdNameReg3.setForeground(new java.awt.Color(0, 102, 255));
        cmbStdNameReg3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cmbStdNameReg3MouseClicked(evt);
            }
        });
        cmbStdNameReg3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbStdNameReg3ActionPerformed(evt);
            }
        });

        cmbStdMobReg3.setEditable(true);
        cmbStdMobReg3.setFont(new java.awt.Font("Calibri", 1, 12)); // NOI18N
        cmbStdMobReg3.setForeground(new java.awt.Color(0, 102, 255));
        cmbStdMobReg3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cmbStdMobReg3MouseClicked(evt);
            }
        });

        btnSearch3.setText("Search");
        btnSearch3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearch3ActionPerformed(evt);
            }
        });

        jButton25.setText("Clear");
        jButton25.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton25ActionPerformed(evt);
            }
        });

        jLabel76.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        jLabel76.setForeground(new java.awt.Color(0, 0, 255));
        jLabel76.setText("Student Verification Status");

        jLabel77.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel77.setForeground(new java.awt.Color(0, 0, 255));
        jLabel77.setText(":");

        lblvrification3.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        lblvrification3.setForeground(new java.awt.Color(0, 0, 255));

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel76)
                        .addGap(13, 13, 13)
                        .addComponent(jLabel77, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lblvrification3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel18Layout.createSequentialGroup()
                                .addGap(8, 8, 8)
                                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel18Layout.createSequentialGroup()
                                        .addComponent(btnSearch3)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jButton25))
                                    .addGroup(jPanel18Layout.createSequentialGroup()
                                        .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jLabel69, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jLabel68))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(cmbStdIDReg3, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(cmbStdNICReg3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jLabel71, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jLabel70, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(19, 19, 19)
                                        .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(cmbStdNameReg3, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(cmbStdMobReg3, 0, 1, Short.MAX_VALUE))))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel18Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jButton16)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton17, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27))
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addComponent(jLabel29)
                .addGap(18, 18, 18)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addComponent(cmbStdIDReg3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cmbStdNICReg3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel69)))
                    .addComponent(jLabel68)
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel71)
                            .addComponent(cmbStdNameReg3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel70)
                            .addComponent(cmbStdMobReg3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnSearch3)
                    .addComponent(jButton25))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 50, Short.MAX_VALUE)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblvrification3, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel76, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel77))
                .addGap(91, 91, 91)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton16)
                    .addComponent(jButton17))
                .addGap(20, 20, 20))
        );

        jPanel3.add(jPanel18);
        jPanel18.setBounds(930, 40, 390, 340);

        jTabbedPane1.addTab("Free Cards", jPanel3);

        jPanel4.setMaximumSize(null);

        jPanel11.setBorder(javax.swing.BorderFactory.createTitledBorder("Update Class Fee "));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(51, 51, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Monthly Fee");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(51, 51, 255));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Total Course Fee");

        test.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        test.setForeground(new java.awt.Color(51, 51, 255));
        test.setText("U P D A T E");
        test.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                testActionPerformed(evt);
            }
        });

        jButton6.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton6.setForeground(new java.awt.Color(51, 51, 255));
        jButton6.setText("C L E A R");

        jLabel36.setFont(new java.awt.Font("Calibri", 1, 11)); // NOI18N
        jLabel36.setForeground(new java.awt.Color(0, 0, 102));

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addComponent(jLabel36)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtMonthlyfee)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 53, Short.MAX_VALUE)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtTotalFee, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(test)
                .addGap(18, 18, 18)
                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtMonthlyfee)
                            .addComponent(txtTotalFee, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(24, 24, 24)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(test)
                    .addComponent(jButton6))
                .addContainerGap())
        );

        jPanel12.setBorder(javax.swing.BorderFactory.createTitledBorder("Update Lecturer Payment Precentage"));

        jLabel56.setText("Precentage");

        test1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        test1.setForeground(new java.awt.Color(51, 51, 255));
        test1.setText("U P D A T E");
        test1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                test1ActionPerformed(evt);
            }
        });

        jButton22.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton22.setForeground(new java.awt.Color(51, 51, 255));
        jButton22.setText("C L E A R");

        jLabel9.setText("%");

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                .addContainerGap(90, Short.MAX_VALUE)
                .addComponent(test1)
                .addGap(18, 18, 18)
                .addComponent(jButton22, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel56)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtPrecentage, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel9)
                .addGap(105, 105, 105))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel56)
                    .addComponent(txtPrecentage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addGap(18, 18, Short.MAX_VALUE)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(test1)
                    .addComponent(jButton22))
                .addContainerGap())
        );

        jPanel13.setBorder(javax.swing.BorderFactory.createTitledBorder("Update Class Date and Time "));
        jPanel13.setMinimumSize(new java.awt.Dimension(890, 190));

        chkMonday.setForeground(new java.awt.Color(51, 51, 255));
        chkMonday.setText("Monday");

        chkTuesday.setForeground(new java.awt.Color(51, 51, 255));
        chkTuesday.setText("Tuesday");

        chkWednesday.setForeground(new java.awt.Color(51, 51, 255));
        chkWednesday.setText("Wednesday");

        chkThursday.setForeground(new java.awt.Color(51, 51, 255));
        chkThursday.setText("Thursday");

        chkFriday.setForeground(new java.awt.Color(51, 51, 255));
        chkFriday.setText("Friday");

        chkSaturday.setForeground(new java.awt.Color(51, 51, 255));
        chkSaturday.setText("Saturday");

        chkSunday.setForeground(new java.awt.Color(51, 51, 255));
        chkSunday.setText("Sunday");

        if (editCourseBean.getClassDaysMap() != null) {
            HashMap monday = editCourseBean.getClassDaysMap();
            if (monday.containsKey("monday")) {
                chkMonday.setSelected(true);
                date = ((classDaysBean) monday.get("monday")).getEndTime();
                SpinnerDateModel smMonSt = new SpinnerDateModel(date, null,null, Calendar.HOUR_OF_DAY);
                monSt = new javax.swing.JSpinner(smMonSt);
                JSpinner.DateEditor deMonSt = new JSpinner.DateEditor(monSt, "hh:mm a");
                monSt.setEditor(deMonSt);
            }

            else{
                date = new Date();

                SpinnerDateModel smMonSt = new SpinnerDateModel(date, null,null, Calendar.HOUR_OF_DAY);
                monSt = new javax.swing.JSpinner(smMonSt);
                JSpinner.DateEditor deMonSt = new JSpinner.DateEditor(monSt, "hh:mm a");
                monSt.setEditor(deMonSt);
            }
        }

        if (editCourseBean.getClassDaysMap() != null) {
            HashMap monday = editCourseBean.getClassDaysMap();
            if (monday.containsKey("monday")) {
                Date monEndTime = ((classDaysBean) monday.get("monday")).getEndTime();
                SpinnerDateModel smMonEd = new SpinnerDateModel(monEndTime, null, null, Calendar.HOUR_OF_DAY);
                monEd = new javax.swing.JSpinner(smMonEd);
                JSpinner.DateEditor deMonEd = new JSpinner.DateEditor(monEd, "hh:mm a");
                monEd.setEditor(deMonEd);
            }

            else{
                date = new Date();

                SpinnerDateModel smMonEd = new SpinnerDateModel(date, null, null, Calendar.HOUR_OF_DAY);
                monEd = new javax.swing.JSpinner(smMonEd);
                JSpinner.DateEditor deMonEd = new JSpinner.DateEditor(monEd, "hh:mm a");
                monEd.setEditor(deMonEd);
            }
        }

        if (editCourseBean.getClassDaysMap() != null) {
            HashMap tuesday = editCourseBean.getClassDaysMap();
            if (tuesday.containsKey("tuesday")) {
                chkTuesday.setSelected(true);
                Date tueStartTime = ((classDaysBean) tuesday.get("tuesday")).getStartTime();
                SpinnerDateModel smTueSt = new SpinnerDateModel(tueStartTime, null, null, Calendar.HOUR_OF_DAY);
                tueSt = new javax.swing.JSpinner(smTueSt);
                JSpinner.DateEditor deTueSt = new JSpinner.DateEditor(tueSt, "hh:mm a");
                tueSt.setEditor(deTueSt);
            }

            else{
                date = new Date();

                SpinnerDateModel smTueSt = new SpinnerDateModel(date, null, null, Calendar.HOUR_OF_DAY);
                tueSt = new javax.swing.JSpinner(smTueSt);
                JSpinner.DateEditor deTueSt = new JSpinner.DateEditor(tueSt, "hh:mm a");
                tueSt.setEditor(deTueSt);
            }
        }

        if (editCourseBean.getClassDaysMap() != null) {
            HashMap tuesday = editCourseBean.getClassDaysMap();
            if (tuesday.containsKey("tuesday")) {

                Date tueEndTime = ((classDaysBean) tuesday.get("tuesday")).getEndTime();
                SpinnerDateModel smTueEd = new SpinnerDateModel(tueEndTime, null, null, Calendar.HOUR_OF_DAY);
                tueEd = new javax.swing.JSpinner(smTueEd);
                JSpinner.DateEditor deTueEd = new JSpinner.DateEditor(tueEd, "hh:mm a");
                tueEd.setEditor(deTueEd);
            }

            else{
                date = new Date();

                SpinnerDateModel smTueEd = new SpinnerDateModel(date, null, null, Calendar.HOUR_OF_DAY);
                tueEd = new javax.swing.JSpinner(smTueEd);
                JSpinner.DateEditor deTueEd = new JSpinner.DateEditor(tueEd, "hh:mm a");
                tueEd.setEditor(deTueEd);
            }
        }

        if (editCourseBean.getClassDaysMap() != null) {
            HashMap wednesday = editCourseBean.getClassDaysMap();
            if (wednesday.containsKey("wednesday")) {
                chkWednesday.setSelected(true);
                Date wedStartTime = ((classDaysBean) wednesday.get("wednesday")).getStartTime();
                SpinnerDateModel smWedSt = new SpinnerDateModel(wedStartTime, null, null, Calendar.HOUR_OF_DAY);
                wedSt = new javax.swing.JSpinner(smWedSt);
                JSpinner.DateEditor deWedSt = new JSpinner.DateEditor(wedSt, "hh:mm a");
                wedSt.setEditor(deWedSt);
            }

            else{
                date = new Date();

                SpinnerDateModel smWedSt = new SpinnerDateModel(date, null, null, Calendar.HOUR_OF_DAY);
                wedSt = new javax.swing.JSpinner(smWedSt);
                JSpinner.DateEditor deWedSt = new JSpinner.DateEditor(wedSt, "hh:mm a");
                wedSt.setEditor(deWedSt);
            }
        }

        if (editCourseBean.getClassDaysMap() != null) {
            HashMap wednesday = editCourseBean.getClassDaysMap();
            if (wednesday.containsKey("wednesday")) {

                Date wedEndTime = ((classDaysBean) wednesday.get("wednesday")).getEndTime();
                SpinnerDateModel smWedEd = new SpinnerDateModel(wedEndTime, null, null, Calendar.HOUR_OF_DAY);
                wedEd = new javax.swing.JSpinner(smWedEd);
                JSpinner.DateEditor deWedEd = new JSpinner.DateEditor(wedEd, "hh:mm a");
                wedEd.setEditor(deWedEd);
            }

            else{
                date = new Date();

                SpinnerDateModel smWedEd = new SpinnerDateModel(date, null, null, Calendar.HOUR_OF_DAY);
                wedEd = new javax.swing.JSpinner(smWedEd);
                JSpinner.DateEditor deWedEd = new JSpinner.DateEditor(wedEd, "hh:mm a");
                wedEd.setEditor(deWedEd);
            }
        }

        if (editCourseBean.getClassDaysMap() != null) {
            HashMap thursday = editCourseBean.getClassDaysMap();
            if (thursday.containsKey("thursday")) {
                chkThursday.setSelected(true);
                Date thuStartTime = ((classDaysBean) thursday.get("thursday")).getStartTime();
                SpinnerDateModel smThuSt = new SpinnerDateModel(thuStartTime, null, null, Calendar.HOUR_OF_DAY);
                thuSt = new javax.swing.JSpinner(smThuSt);
                JSpinner.DateEditor deThuSt = new JSpinner.DateEditor(thuSt, "hh:mm a");
                thuSt.setEditor(deThuSt);
            }

            else{
                date = new Date();

                SpinnerDateModel smThuSt = new SpinnerDateModel(date, null, null, Calendar.HOUR_OF_DAY);
                thuSt = new javax.swing.JSpinner(smThuSt);
                JSpinner.DateEditor deThuSt = new JSpinner.DateEditor(thuSt, "hh:mm a");
                thuSt.setEditor(deThuSt);
            }
        }

        if (editCourseBean.getClassDaysMap() != null) {
            HashMap thursday = editCourseBean.getClassDaysMap();
            if (thursday.containsKey("thursday")) {

                Date thuEndTime = ((classDaysBean) thursday.get("thursday")).getEndTime();
                SpinnerDateModel smThuEd = new SpinnerDateModel(thuEndTime, null, null, Calendar.HOUR_OF_DAY);
                thuEd = new javax.swing.JSpinner(smThuEd);
                JSpinner.DateEditor deThuEd = new JSpinner.DateEditor(thuEd, "hh:mm a");
                thuEd.setEditor(deThuEd);
            }

            else{
                date = new Date();

                SpinnerDateModel smThuEd = new SpinnerDateModel(date, null, null, Calendar.HOUR_OF_DAY);
                thuEd = new javax.swing.JSpinner(smThuEd);
                JSpinner.DateEditor deThuEd = new JSpinner.DateEditor(thuEd, "hh:mm a");
                thuEd.setEditor(deThuEd);
            }
        }

        if (editCourseBean.getClassDaysMap() != null) {
            HashMap friday = editCourseBean.getClassDaysMap();
            if (friday.containsKey("friday")) {
                chkFriday.setSelected(true);
                Date friStartTime = ((classDaysBean) friday.get("friday")).getStartTime();
                SpinnerDateModel smFriSt = new SpinnerDateModel(friStartTime, null, null, Calendar.HOUR_OF_DAY);
                friSt = new javax.swing.JSpinner(smFriSt);
                JSpinner.DateEditor deFriSt = new JSpinner.DateEditor(friSt, "hh:mm a");
                friSt.setEditor(deFriSt);
            }

            else{
                date = new Date();

                SpinnerDateModel smFriSt = new SpinnerDateModel(date, null, null, Calendar.HOUR_OF_DAY);
                friSt = new javax.swing.JSpinner(smFriSt);
                JSpinner.DateEditor deFriSt = new JSpinner.DateEditor(friSt, "hh:mm a");
                friSt.setEditor(deFriSt);
            }
        }

        if (editCourseBean.getClassDaysMap() != null) {
            HashMap friday = editCourseBean.getClassDaysMap();
            if (friday.containsKey("friday")) {

                Date friEndTime = ((classDaysBean) friday.get("friday")).getEndTime();
                SpinnerDateModel smFriEd = new SpinnerDateModel(friEndTime, null, null, Calendar.HOUR_OF_DAY);
                friEd = new javax.swing.JSpinner(smFriEd);
                JSpinner.DateEditor deFriEd = new JSpinner.DateEditor(friEd, "hh:mm a");
                friEd.setEditor(deFriEd);
            }

            else{
                date = new Date();

                SpinnerDateModel smFriEd = new SpinnerDateModel(date, null, null, Calendar.HOUR_OF_DAY);
                friEd = new javax.swing.JSpinner(smFriEd);
                JSpinner.DateEditor deFriEd = new JSpinner.DateEditor(friEd, "hh:mm a");
                friEd.setEditor(deFriEd);
            }
        }

        if (editCourseBean.getClassDaysMap() != null) {
            HashMap saturday = editCourseBean.getClassDaysMap();
            if (saturday.containsKey("saturday")) {
                chkSaturday.setSelected(true);
                Date satStartTime = ((classDaysBean) saturday.get("saturday")).getStartTime();
                SpinnerDateModel smSatSt = new SpinnerDateModel(satStartTime, null, null, Calendar.HOUR_OF_DAY);
                satSt = new javax.swing.JSpinner(smSatSt);
                JSpinner.DateEditor deSatSt = new JSpinner.DateEditor(satSt, "hh:mm a");
                satSt.setEditor(deSatSt);
            }

            else{
                date = new Date();

                SpinnerDateModel smSatSt = new SpinnerDateModel(date, null, null, Calendar.HOUR_OF_DAY);
                satSt = new javax.swing.JSpinner(smSatSt);
                JSpinner.DateEditor deSatSt = new JSpinner.DateEditor(satSt, "hh:mm a");
                satSt.setEditor(deSatSt);
            }
        }

        if (editCourseBean.getClassDaysMap() != null) {
            HashMap saturday = editCourseBean.getClassDaysMap();
            if (saturday.containsKey("saturday")) {

                Date satEndTime = ((classDaysBean) saturday.get("saturday")).getEndTime();
                SpinnerDateModel smSatEd = new SpinnerDateModel(satEndTime, null, null, Calendar.HOUR_OF_DAY);
                satEd = new javax.swing.JSpinner(smSatEd);
                JSpinner.DateEditor deSatEd = new JSpinner.DateEditor(satEd, "hh:mm a");
                satEd.setEditor(deSatEd);
            }

            else{
                date = new Date();

                SpinnerDateModel smSatEd = new SpinnerDateModel(date, null, null, Calendar.HOUR_OF_DAY);
                satEd = new javax.swing.JSpinner(smSatEd);
                JSpinner.DateEditor deSatEd = new JSpinner.DateEditor(satEd, "HH:mm a");
                satEd.setEditor(deSatEd);
            }
        }

        if (editCourseBean.getClassDaysMap() != null) {
            HashMap sunday = editCourseBean.getClassDaysMap();
            if (sunday.containsKey("sunday")) {
                chkSunday.setSelected(true);
                Date sunStartTime = ((classDaysBean) sunday.get("sunday")).getStartTime();
                SpinnerDateModel smSunSt = new SpinnerDateModel(sunStartTime, null, null, Calendar.HOUR_OF_DAY);
                sunSt = new javax.swing.JSpinner(smSunSt);
                JSpinner.DateEditor deSunSt = new JSpinner.DateEditor(sunSt, "hh:mm a");
                sunSt.setEditor(deSunSt);
            }

            else{
                date = new Date();

                SpinnerDateModel smSunSt = new SpinnerDateModel(date, null, null, Calendar.HOUR_OF_DAY);
                sunSt = new javax.swing.JSpinner(smSunSt);
                JSpinner.DateEditor deSunSt = new JSpinner.DateEditor(sunSt, "hh:mm a");
                sunSt.setEditor(deSunSt);
            }
        }

        if (editCourseBean.getClassDaysMap() != null) {
            HashMap sunday = editCourseBean.getClassDaysMap();
            if (sunday.containsKey("sunday")) {
                chkSunday.setSelected(true);
                Date sunEndTime = ((classDaysBean) sunday.get("sunday")).getEndTime();
                SpinnerDateModel smSunEd = new SpinnerDateModel(sunEndTime, null, null, Calendar.HOUR_OF_DAY);
                sunEd = new javax.swing.JSpinner(smSunEd);
                JSpinner.DateEditor deSunEd = new JSpinner.DateEditor(sunEd, "hh:mm a");
                sunEd.setEditor(deSunEd);
            }

            else{
                date = new Date();

                SpinnerDateModel smSunEd = new SpinnerDateModel(date, null, null, Calendar.HOUR_OF_DAY);
                sunEd = new javax.swing.JSpinner(smSunEd);
                JSpinner.DateEditor deSunEd = new JSpinner.DateEditor(sunEd, "hh:mm a");
                sunEd.setEditor(deSunEd);
            }
        }

        jButton7.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton7.setForeground(new java.awt.Color(51, 51, 255));
        jButton7.setText("U P D A T E");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jButton8.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton8.setForeground(new java.awt.Color(51, 51, 255));
        jButton8.setText("C A N C E L");

        jLabel35.setFont(new java.awt.Font("Calibri", 1, 11)); // NOI18N
        jLabel35.setForeground(new java.awt.Color(0, 0, 102));

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addComponent(jLabel35)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(chkMonday, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(monSt, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(monEd, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 76, Short.MAX_VALUE)
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(chkTuesday)
                            .addComponent(tueSt, javax.swing.GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE)
                            .addComponent(tueEd))
                        .addGap(85, 85, 85)
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(chkWednesday, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(wedSt, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(wedEd, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(90, 90, 90)
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(chkThursday, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(thuSt)
                            .addComponent(thuEd))
                        .addGap(87, 87, 87)
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(chkFriday)
                            .addComponent(friSt, javax.swing.GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE)
                            .addComponent(friEd))
                        .addGap(84, 84, 84)
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(chkSaturday)
                            .addComponent(satSt, javax.swing.GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE)
                            .addComponent(satEd))
                        .addGap(74, 74, 74)
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(sunEd)
                            .addComponent(chkSunday)
                            .addComponent(sunSt, javax.swing.GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE)))
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton7)
                        .addGap(27, 27, 27)
                        .addComponent(jButton8)
                        .addGap(3, 3, 3)))
                .addGap(22, 22, 22))
        );

        jPanel13Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {friEd, friSt, monEd, satEd, satSt, sunEd, sunSt, thuEd, thuSt, tueEd, tueSt, wedEd, wedSt});

        jPanel13Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {chkFriday, chkMonday, chkSaturday, chkSunday, chkThursday, chkTuesday, chkWednesday});

        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                                .addComponent(chkWednesday)
                                .addGap(18, 18, 18)
                                .addComponent(wedSt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(wedEd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                                    .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(chkMonday)
                                        .addComponent(chkThursday, javax.swing.GroupLayout.Alignment.TRAILING))
                                    .addGap(18, 18, 18)
                                    .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel13Layout.createSequentialGroup()
                                            .addComponent(monSt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(monEd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPanel13Layout.createSequentialGroup()
                                            .addComponent(thuSt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(thuEd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                                    .addComponent(chkSaturday)
                                    .addGap(18, 18, 18)
                                    .addComponent(satSt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(satEd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                                    .addComponent(chkSunday)
                                    .addGap(18, 18, 18)
                                    .addComponent(sunSt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(sunEd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel13Layout.createSequentialGroup()
                                    .addComponent(chkTuesday)
                                    .addGap(18, 18, 18)
                                    .addComponent(tueSt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(tueEd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton7)
                            .addComponent(jButton8)))
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addComponent(chkFriday)
                        .addGap(18, 18, 18)
                        .addComponent(friSt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(friEd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(27, 27, 27))
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap(54, Short.MAX_VALUE)
                        .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(128, 128, 128)
                        .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(150, 150, 150))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(111, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Update Class Details", jPanel4);

        jPanel19.setBorder(javax.swing.BorderFactory.createTitledBorder("Course Details"));

        jLabel19.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        jLabel19.setText("Lecturer Name");

        jLabel23.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        jLabel23.setText("Subject");

        jLabel24.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        jLabel24.setText("Grade");

        lblName.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N

        lblSubject.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N

        lblGrade.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N

        jLabel31.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        jLabel31.setText("Total Course Fee");

        lblTotalFee.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N

        jLabel15.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        jLabel15.setText("Monthly Fee");

        lblMonthlyFee.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N

        jLabel17.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        jLabel17.setText("Batch Number");

        lblBatchNo.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N

        jLabel20.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        jLabel20.setText("Total Students");

        jLabel21.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        jLabel21.setText("Total Free Cards");

        jLabel26.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        jLabel26.setText("Total 50% fee Cards");

        lbltotalStudent.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N

        lblfree.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N

        lblHalf.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(54, 54, 54)
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblSubject, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblGrade, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblName, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 193, Short.MAX_VALUE)
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel31, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(44, 44, 44)
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblTotalFee, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblBatchNo, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblMonthlyFee, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(270, 270, 270)
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel19Layout.createSequentialGroup()
                        .addComponent(jLabel26)
                        .addGap(18, 18, 18)
                        .addComponent(lblHalf, javax.swing.GroupLayout.DEFAULT_SIZE, 72, Short.MAX_VALUE))
                    .addGroup(jPanel19Layout.createSequentialGroup()
                        .addComponent(jLabel21)
                        .addGap(23, 23, 23)
                        .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lbltotalStudent, javax.swing.GroupLayout.DEFAULT_SIZE, 87, Short.MAX_VALUE)
                            .addComponent(lblfree, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel19Layout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblName, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel19)))
                    .addGroup(jPanel19Layout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel31)
                            .addComponent(lblTotalFee, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbltotalStudent, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel20))))
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel19Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel23)
                            .addComponent(lblSubject, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel15)
                            .addComponent(lblMonthlyFee, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel21))
                        .addGap(29, 29, 29))
                    .addGroup(jPanel19Layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(lblfree, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)))
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel24)
                    .addComponent(lblGrade, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(lblBatchNo, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel17))
                    .addGroup(jPanel19Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel26)
                            .addComponent(lblHalf, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(29, 29, 29))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 71, Short.MAX_VALUE))
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 499, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        try {

            HashMap<String, classDaysBean> clzDaysMap = new HashMap<String, classDaysBean>();
            if (chkMonday.isSelected()) {
                classDaysBean clzBean = new classDaysBean();
                clzBean.setDay("monday");
                clzBean.setStartTime((Date) monSt.getValue());
                clzBean.setEndTime((Date) monEd.getValue());
                clzDaysMap.put("monday", clzBean);
            }
            if (chkTuesday.isSelected()) {
                classDaysBean clzBean = new classDaysBean();
                clzBean.setDay("tuesday");
                clzBean.setStartTime((Date) tueSt.getValue());
                clzBean.setEndTime((Date) tueEd.getValue());
                clzDaysMap.put("tuesday", clzBean);
            }
            if (chkWednesday.isSelected()) {
                classDaysBean clzBean = new classDaysBean();
                clzBean.setDay("wednesday");
                clzBean.setStartTime((Date) wedSt.getValue());
                clzBean.setEndTime((Date) wedEd.getValue());
                clzDaysMap.put("wednesday", clzBean);
            }
            if (chkThursday.isSelected()) {
                classDaysBean clzBean = new classDaysBean();
                clzBean.setDay("thursday");
                clzBean.setStartTime((Date) thuSt.getValue());
                clzBean.setEndTime((Date) thuEd.getValue());
                clzDaysMap.put("thursday", clzBean);
            }
            if (chkFriday.isSelected()) {
                classDaysBean clzBean = new classDaysBean();
                clzBean.setDay("friday");
                clzBean.setStartTime((Date) friSt.getValue());
                clzBean.setEndTime((Date) friEd.getValue());
                clzDaysMap.put("friday", clzBean);
            }
            if (chkSaturday.isSelected()) {
                classDaysBean clzBean = new classDaysBean();
                clzBean.setDay("saturday");
                clzBean.setStartTime((Date) satSt.getValue());
                clzBean.setEndTime((Date) satEd.getValue());
                clzDaysMap.put("saturday", clzBean);
            }
            if (chkSunday.isSelected()) {
                classDaysBean clzBean = new classDaysBean();
                clzBean.setDay("sunday");
                clzBean.setStartTime((Date) sunSt.getValue());
                clzBean.setEndTime((Date) sunEd.getValue());
                clzDaysMap.put("sunday", clzBean);
            }
            if (clzDaysMap.isEmpty()) {
                JOptionPane.showMessageDialog(new JFrame(), "Atleast One day should be select");
            } else {
                conn.updateClassDays(clzDaysMap, a.getCourseID());
                JOptionPane.showMessageDialog(new JFrame(), "Successfully Change the class Dates and Times");
            }

        } catch (Exception ex) {

        }
    }//GEN-LAST:event_jButton7ActionPerformed

    private void test1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_test1ActionPerformed
        try {
            int x = conn.updatepaymentprecentage(OnlyForCourseEditBean.getCourseID(), Double.parseDouble(txtPrecentage.getText().toString()));
            if (x == 1) {
                JOptionPane.showMessageDialog(new JFrame(), "Successfully Updated");
            } else {
                JOptionPane.showMessageDialog(new JFrame(), "Error Occured");
            }
        } catch (Exception ex) {
            Logger.getLogger(test.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_test1ActionPerformed

    private void testActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_testActionPerformed
        double monFee = 0.0;
        double totalFee = 0.00;
        if (txtMonthlyfee.getText() != "") {
            monFee = Double.parseDouble(txtMonthlyfee.getText());
        }
        if (!txtTotalFee.getText().isEmpty()) {
            totalFee = Double.parseDouble(txtTotalFee.getText());
        }
        try {
            int x = conn.updateCourseFee(a.getCourseID(), monFee, totalFee);
            if (x == 1) {
                JOptionPane.showMessageDialog(new JFrame(), "Successfully Updated");
            } else {
                JOptionPane.showMessageDialog(new JFrame(), "Error Ocuured. Try Again");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(new JFrame(), "Error Ocuured.Try Again");
        }
    }//GEN-LAST:event_testActionPerformed

    private void jButton25ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton25ActionPerformed
        cmbStdIDReg3.setSelectedItem(searchResult.get(""));
        cmbStdNameReg3.setSelectedItem(searchResult.get(""));
        cmbStdNICReg3.setSelectedItem(searchResult.get(""));
        cmbStdMobReg3.setSelectedItem(searchResult.get(""));
        lblvrification3.setText("--");
    }//GEN-LAST:event_jButton25ActionPerformed

    private void btnSearch3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearch3ActionPerformed
        String id = cmbStdIDReg3.getEditor().getItem().toString();
        String name = cmbStdNameReg3.getEditor().getItem().toString();
        String nic = cmbStdNICReg3.getEditor().getItem().toString();
        String mob = cmbStdMobReg3.getEditor().getItem().toString();
        try {
            searchResult = conn.SearchStudent(id, name, nic, mob);

            if (searchResult.size() > 0) {
                cmbStdIDReg3.setSelectedItem(searchResult.get("regID"));
                cmbStdNameReg3.setSelectedItem(searchResult.get("name"));
                cmbStdNICReg3.setSelectedItem(searchResult.get("nic"));
                cmbStdMobReg3.setSelectedItem(searchResult.get("mobile"));

                lblvrification3.setText("Verify");
            } else {
                cmbStdIDReg3.setSelectedItem(searchResult.get(""));
                cmbStdNameReg3.setSelectedItem(searchResult.get(""));
                cmbStdNICReg3.setSelectedItem(searchResult.get(""));
                cmbStdMobReg3.setSelectedItem(searchResult.get(""));
                lblvrification3.setText("Wrong Details");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(new JFrame(), "Fail to search");
        }
    }//GEN-LAST:event_btnSearch3ActionPerformed

    private void cmbStdMobReg3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbStdMobReg3MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbStdMobReg3MouseClicked

    private void cmbStdNameReg3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbStdNameReg3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbStdNameReg3ActionPerformed

    private void cmbStdNameReg3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbStdNameReg3MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbStdNameReg3MouseClicked

    private void cmbStdNICReg3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbStdNICReg3MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbStdNICReg3MouseClicked

    private void cmbStdIDReg3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbStdIDReg3KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbStdIDReg3KeyPressed

    private void cmbStdIDReg3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbStdIDReg3MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbStdIDReg3MouseClicked

    private void jButton17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton17ActionPerformed
        cmbStdIDReg3.getEditor().setItem("");
        cmbStdMobReg3.setSelectedItem("");
        cmbStdNICReg3.setSelectedItem("");
        cmbStdNameReg3.setSelectedItem("");
    }//GEN-LAST:event_jButton17ActionPerformed

    private void jButton16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton16ActionPerformed
        try {
            int cardType = 2;

            boolean studentExistancy = conn.checkstudentExistancy(cmbStdIDReg3.getEditor().getItem().toString());
            if (studentExistancy) {
                boolean x = conn.checkstudentinCourse(cmbStdIDReg3.getEditor().getItem().toString(), OnlyForCourseEditBean.getCourseID());
                if (x) {
                    int result = conn.updateStudentCardType(cmbStdIDReg3.getEditor().getItem().toString(), OnlyForCourseEditBean.getCourseID(), cardType);
                    if (result == 1) {
                        JOptionPane.showMessageDialog(new JFrame(), "Successfully Cancel Free Card");
                    } else {
                        JOptionPane.showMessageDialog(new JFrame(), "Something went wrong. Try again");
                    }
                } else {
                    JOptionPane.showMessageDialog(new JFrame(), "Student ID " + cmbStdIDReg3.getEditor().getItem().toString() + " is not registered for this course");
                }
            } else {
                JOptionPane.showMessageDialog(new JFrame(), "To countinue the prosses Student must be registered");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(new JFrame(), ex);
        }
    }//GEN-LAST:event_jButton16ActionPerformed

    private void jButton24ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton24ActionPerformed
        cmbStdIDReg2.setSelectedItem(searchResult.get(""));
        cmbStdNameReg2.setSelectedItem(searchResult.get(""));
        cmbStdNICReg2.setSelectedItem(searchResult.get(""));
        cmbStdMobReg2.setSelectedItem(searchResult.get(""));
        lblvrification2.setText("--");
    }//GEN-LAST:event_jButton24ActionPerformed

    private void btnSearch2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearch2ActionPerformed
        String id = cmbStdIDReg2.getEditor().getItem().toString();
        String name = cmbStdNameReg2.getEditor().getItem().toString();
        String nic = cmbStdNICReg2.getEditor().getItem().toString();
        String mob = cmbStdMobReg2.getEditor().getItem().toString();
        try {
            searchResult = conn.SearchStudent(id, name, nic, mob);

            if (searchResult.size() > 0) {
                cmbStdIDReg2.setSelectedItem(searchResult.get("regID"));
                cmbStdNameReg2.setSelectedItem(searchResult.get("name"));
                cmbStdNICReg2.setSelectedItem(searchResult.get("nic"));
                cmbStdMobReg2.setSelectedItem(searchResult.get("mobile"));

                lblvrification2.setText("Verify");
            } else {
                cmbStdIDReg2.setSelectedItem(searchResult.get(""));
                cmbStdNameReg2.setSelectedItem(searchResult.get(""));
                cmbStdNICReg2.setSelectedItem(searchResult.get(""));
                cmbStdMobReg2.setSelectedItem(searchResult.get(""));
                lblvrification2.setText("Wrong Details");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(new JFrame(), "Fail to search");
        }
    }//GEN-LAST:event_btnSearch2ActionPerformed

    private void cmbStdNameReg2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbStdNameReg2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbStdNameReg2ActionPerformed

    private void cmbStdNameReg2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbStdNameReg2MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbStdNameReg2MouseClicked

    private void cmbStdMobReg2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbStdMobReg2MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbStdMobReg2MouseClicked

    private void cmbStdIDReg2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbStdIDReg2KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbStdIDReg2KeyPressed

    private void cmbStdIDReg2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbStdIDReg2MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbStdIDReg2MouseClicked

    private void cmbStdNICReg2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbStdNICReg2MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbStdNICReg2MouseClicked

    private void jButton15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton15ActionPerformed
        cmbStdIDReg2.getEditor().setItem("");
        cmbStdMobReg2.setSelectedItem("");
        cmbStdNICReg2.setSelectedItem("");
        cmbStdNameReg2.setSelectedItem("");
    }//GEN-LAST:event_jButton15ActionPerformed

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        try {
            int cardType = 2;
            if (rEditFree.isSelected()) {
                cardType = 0;
            } else if (rEdit50Free.isSelected()) {
                cardType = 1;
            }
            boolean studentExistancy = conn.checkstudentExistancy(cmbStdIDReg2.getEditor().getItem().toString());
            if (studentExistancy) {
                boolean x = conn.checkstudentinCourse(cmbStdIDReg2.getEditor().getItem().toString(), OnlyForCourseEditBean.getCourseID());
                if (x) {
                    int result = conn.updateStudentCardType(cmbStdIDReg2.getEditor().getItem().toString(), OnlyForCourseEditBean.getCourseID(), cardType);
                    if (result == 1) {
                        JOptionPane.showMessageDialog(new JFrame(), "Successfully Updated requested Crad type");
                    } else {
                        JOptionPane.showMessageDialog(new JFrame(), "Something went wrong. Try again");
                    }
                } else {
                    JOptionPane.showMessageDialog(new JFrame(), "Student ID " + cmbStdIDReg2.getEditor().getItem().toString() + " is not registered for this course");
                }
            } else {
                JOptionPane.showMessageDialog(new JFrame(), "To countinue the prosses Student must be registered");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(new JFrame(), ex);
        }
    }//GEN-LAST:event_btnEditActionPerformed

    private void rEdit50FreeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rEdit50FreeActionPerformed
        rEditFree.setSelected(false);
    }//GEN-LAST:event_rEdit50FreeActionPerformed

    private void rEditFreeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rEditFreeActionPerformed
        rEdit50Free.setSelected(false);
    }//GEN-LAST:event_rEditFreeActionPerformed

    private void jButton23ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton23ActionPerformed
        cmbStdIDReg1.setSelectedItem(searchResult.get(""));
        cmbStdNameReg1.setSelectedItem(searchResult.get(""));
        cmbStdNICReg1.setSelectedItem(searchResult.get(""));
        cmbStdMobReg1.setSelectedItem(searchResult.get(""));
        lblvrification1.setText("--");
    }//GEN-LAST:event_jButton23ActionPerformed

    private void btnSearch1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearch1ActionPerformed
        String id = cmbStdIDReg1.getEditor().getItem().toString();
        String name = cmbStdNameReg1.getEditor().getItem().toString();
        String nic = cmbStdNICReg1.getEditor().getItem().toString();
        String mob = cmbStdMobReg1.getEditor().getItem().toString();
        try {
            searchResult = conn.SearchStudent(id, name, nic, mob);

            if (searchResult.size() > 0) {
                cmbStdIDReg1.setSelectedItem(searchResult.get("regID"));
                cmbStdNameReg1.setSelectedItem(searchResult.get("name"));
                cmbStdNICReg1.setSelectedItem(searchResult.get("nic"));
                cmbStdMobReg1.setSelectedItem(searchResult.get("mobile"));

                lblvrification1.setText("Verify");
            } else {
                cmbStdIDReg1.setSelectedItem(searchResult.get(""));
                cmbStdNameReg1.setSelectedItem(searchResult.get(""));
                cmbStdNICReg1.setSelectedItem(searchResult.get(""));
                cmbStdMobReg1.setSelectedItem(searchResult.get(""));
                lblvrification1.setText("Wrong Details");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(new JFrame(), "Fail to search");
        }
    }//GEN-LAST:event_btnSearch1ActionPerformed

    private void cmbStdMobReg1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbStdMobReg1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbStdMobReg1MouseClicked

    private void cmbStdNameReg1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbStdNameReg1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbStdNameReg1ActionPerformed

    private void cmbStdNameReg1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbStdNameReg1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbStdNameReg1MouseClicked

    private void cmbStdNICReg1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbStdNICReg1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbStdNICReg1MouseClicked

    private void cmbStdIDReg1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbStdIDReg1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbStdIDReg1KeyPressed

    private void cmbStdIDReg1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbStdIDReg1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbStdIDReg1MouseClicked

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
        cmbStdIDReg1.getEditor().setItem("");
        cmbStdMobReg1.setSelectedItem("");
        cmbStdNICReg1.setSelectedItem("");
        cmbStdNameReg1.setSelectedItem("");
    }//GEN-LAST:event_jButton13ActionPerformed

    private void btnIssueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIssueActionPerformed
        try {
            int cardType = 2;
            if (rIssueFree.isSelected()) {
                cardType = 0;
            } else if (rIssue50Free.isSelected()) {
                cardType = 1;
            }
            boolean studentExistancy = conn.checkstudentExistancy(cmbStdIDReg1.getEditor().getItem().toString());
            if (studentExistancy) {
                boolean x = conn.checkstudentinCourse(cmbStdIDReg1.getEditor().getItem().toString(), OnlyForCourseEditBean.getCourseID());
                if (x) {
                    int result = conn.updateStudentCardType(cmbStdIDReg1.getEditor().getItem().toString(), OnlyForCourseEditBean.getCourseID(), cardType);
                    if (result == 1) {
                        JOptionPane.showMessageDialog(new JFrame(), "Successfully Updated requested Crad type");
                    } else {
                        JOptionPane.showMessageDialog(new JFrame(), "Something went wrong. Try again");
                    }
                } else {
                    JOptionPane.showMessageDialog(new JFrame(), "Student ID " + cmbStdIDReg1.getEditor().getItem().toString() + " is not registered for this course");
                }
            } else {
                JOptionPane.showMessageDialog(new JFrame(), "To countinue the prosses Student must be registered");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(new JFrame(), ex);
        }
    }//GEN-LAST:event_btnIssueActionPerformed

    private void rIssue50FreeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rIssue50FreeActionPerformed
        rIssueFree.setSelected(false);
    }//GEN-LAST:event_rIssue50FreeActionPerformed

    private void rIssueFreeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rIssueFreeActionPerformed
        rIssue50Free.setSelected(false);
    }//GEN-LAST:event_rIssueFreeActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        int x = 0;
        int count = 0;
        for (int i = 0; i < tblExtra.getRowCount(); i++) {
            Boolean isChecked = (boolean)tblExtra.getValueAt(i, 4);

            if (isChecked) {
                String date = tblExtra.getValueAt(i, 0).toString();
                String time = tblExtra.getValueAt(i, 1).toString();

                try {
                    x = conn.deleteExtraClasses(a.getCourseID(), date, time);
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
            JOptionPane.showMessageDialog(new JFrame(), count + " Extra classes Delete Successfully");
        } else {
            JOptionPane.showMessageDialog(new JFrame(), "error Occured. Try again");
        }

        //load extra class table again
        try {
            HashMap<Integer, Object[]> tblData = conn.getExtraClassDetails(a.getCourseID());
            DefaultTableModel model = (DefaultTableModel) tblExtra.getModel();
            model.setRowCount(0);
            for (int i = 1; i <= tblData.size(); i++) {
                model.addRow(tblData.get(i));
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed

    }//GEN-LAST:event_jButton11ActionPerformed

    private void extraAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_extraAddActionPerformed
        SimpleDateFormat printFormat = new SimpleDateFormat("HH:mm");

        Date extraClassDate = jdate.getDate();
        String extraClassStartTime = printFormat.format((Date) extraStartTime.getValue());
        String extraClassEndTime = printFormat.format((Date) ExtraEndTime.getValue());
        try {
            int x = conn.addExtraClass(OnlyForCourseEditBean.getCourseID(), extraClassDate, extraClassStartTime, extraClassEndTime);
            if (x != 1) {
                JOptionPane.showMessageDialog(new JFrame(), "Something went wrong. try again");
            } else {
                JOptionPane.showMessageDialog(new JFrame(), "Extra class Successfully added");
            }

            // load data into extra class details table
            try {
                HashMap<Integer, Object[]> tblData = conn.getExtraClassDetails(a.getCourseID());
                DefaultTableModel model = (DefaultTableModel) tblExtra.getModel();
                model.setRowCount(0);
                for (int i = 1; i <= tblData.size(); i++) {
                    model.addRow(tblData.get(i));
                }
            } catch (Exception ex) {
                System.out.println(ex);
            }
            //end

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(new JFrame(), "Database connection fail");
        }
    }//GEN-LAST:event_extraAddActionPerformed

    private void cmbStdMobRemMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbStdMobRemMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbStdMobRemMouseClicked

    private void cmbStdNameRemMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbStdNameRemMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbStdNameRemMouseClicked

    private void cmbStdNICRemMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbStdNICRemMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbStdNICRemMouseClicked

    private void cmbStdIDRemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbStdIDRemActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbStdIDRemActionPerformed

    private void cmbStdIDRemMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbStdIDRemMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbStdIDRemMouseClicked

    private void jComboBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox2ActionPerformed

    private void jButton21ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton21ActionPerformed
        cmbStdIDRem.setSelectedItem("");
        cmbStdMobRem.setSelectedItem("");
        cmbStdNICRem.setSelectedItem("");
        cmbStdNameRem.setSelectedItem("");
    }//GEN-LAST:event_jButton21ActionPerformed

    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
        String id = cmbStdIDRem.getEditor().getItem().toString();
        String name = cmbStdNameRem.getEditor().getItem().toString();
        String nic = cmbStdNICRem.getEditor().getItem().toString();
        String mob = cmbStdMobRem.getEditor().getItem().toString();
        try {
            searchResult = conn.SearchStudent(id, name, nic, mob);
            studentDetailPopUpforSearch sdps = new studentDetailPopUpforSearch();
            sdps.setVisible(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(new JFrame(), "No Match Found");
            lblvrificationDel.setForeground(new java.awt.Color(0, 153, 51));
            lblvrificationDel.setText("No Match Found");
        }
        lblvrificationDel.setForeground(new java.awt.Color(0, 153, 51));
        lblvrificationDel.setText("Pending");
        DelSearchPress = 1;
    }//GEN-LAST:event_jButton14ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        cmbStdIDRem.setSelectedItem("");
        cmbStdMobRem.setSelectedItem("");
        cmbStdNICRem.setSelectedItem("");
        cmbStdNameRem.setSelectedItem("");
    }//GEN-LAST:event_jButton4ActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        try {
            boolean studentExistancy = conn.checkstudentExistancy(cmbStdIDRem.getEditor().getItem().toString());
            if (studentExistancy) {
                //check student already registered for this course or not
                boolean x = conn.checkstudentinCourse(cmbStdIDRem.getEditor().getItem().toString(), OnlyForCourseEditBean.getCourseID());
                if (x) {
                    st = conn.getStudentDetails(cmbStdIDRem.getEditor().getItem().toString());
                    try {

                        int xx = new CourseDbConnection().studentDeleteFromCourse(st.getStudentID(), OnlyForCourseEditBean.getCourseID());

                        if (xx != 1) {
                            JOptionPane.showMessageDialog(new JFrame(), "StudentID " + st.getStudentID() + " is not Registered with this course");

                        } else {
                            JOptionPane.showMessageDialog(new JFrame(), "StudentID " + st.getStudentID() + " Successfully Removed.");

                        }
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(new JFrame(), "Error Occured when deleting..");

                    }
                } else {
                    JOptionPane.showMessageDialog(new JFrame(), "Student ID " + cmbStdIDRem.getEditor().getItem().toString() + " is not registered with this course");

                    // show student deails to confirm
                }
            } else {
                JOptionPane.showMessageDialog(new JFrame(), "To countinue the prosses Student must be registered");
            }
        } catch (Exception ex) {

        }
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void jPanel8MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel8MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_jPanel8MouseEntered

    private void jButton19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton19ActionPerformed
        cmbStdIDReg.setSelectedItem("");
        cmbStdMobReg.setSelectedItem("");
        cmbStdNICReg.setSelectedItem("");
        cmbStdNameReg.setSelectedItem("");
    }//GEN-LAST:event_jButton19ActionPerformed

    private void cmbStdMobRegMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbStdMobRegMouseClicked

    }//GEN-LAST:event_cmbStdMobRegMouseClicked

    private void cmbStdNICRegMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbStdNICRegMouseClicked

    }//GEN-LAST:event_cmbStdNICRegMouseClicked

    private void cmbStdIDRegKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbStdIDRegKeyPressed
        lblvrification.setForeground(new java.awt.Color(255, 255, 0));
        lblvrification.setText("Vrified");
    }//GEN-LAST:event_cmbStdIDRegKeyPressed

    private void cmbStdIDRegMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbStdIDRegMouseClicked

    }//GEN-LAST:event_cmbStdIDRegMouseClicked

    private void cmbStdNameRegActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbStdNameRegActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbStdNameRegActionPerformed

    private void cmbStdNameRegMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbStdNameRegMouseClicked

    }//GEN-LAST:event_cmbStdNameRegMouseClicked

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        cmbStdIDReg.setSelectedItem("");
        cmbStdMobReg.setSelectedItem("");
        cmbStdNICReg.setSelectedItem("");
        cmbStdNameReg.setSelectedItem("");
    }//GEN-LAST:event_jButton10ActionPerformed

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        String id = cmbStdIDReg.getEditor().getItem().toString();
        String name = cmbStdNameReg.getEditor().getItem().toString();
        String nic = cmbStdNICReg.getEditor().getItem().toString();
        String mob = cmbStdMobReg.getEditor().getItem().toString();
        try {
            searchResult = conn.SearchStudent(id, name, nic, mob);
            studentDetailPopUpforSearch sdps = new studentDetailPopUpforSearch();
            sdps.setVisible(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(new JFrame(), "No Match Found");
            lblvrification.setForeground(new java.awt.Color(0, 153, 51));
            lblvrification.setText("No Match Found");
        }
        lblvrification.setForeground(new java.awt.Color(0, 153, 51));
        lblvrification.setText("Pending");
        AddSearchPress = 1;
    }//GEN-LAST:event_btnSearchActionPerformed

    private void rFullActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rFullActionPerformed
        rNormal.setSelected(false);
        rhalf.setSelected(false);
    }//GEN-LAST:event_rFullActionPerformed

    private void rhalfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rhalfActionPerformed
        rNormal.setSelected(false);
        rFull.setSelected(false);
    }//GEN-LAST:event_rhalfActionPerformed

    private void rNormalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rNormalActionPerformed
        rhalf.setSelected(false);
        rFull.setSelected(false);
    }//GEN-LAST:event_rNormalActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            
            String txtStdIDReg = cmbStdIDReg.getEditor().getItem().toString();
            boolean studentExistancy = conn.checkstudentExistancy(txtStdIDReg);

            if (rFull.isSelected()) {
                StaticCardType = 0;
            } else if (rhalf.isSelected()) {
                StaticCardType = 1;
            } else if (rNormal.isSelected()) {
                StaticCardType = 2;
            }

            if (studentExistancy) {
                //check student already registered for this course or not

                boolean x = conn.checkstudentinCourse(txtStdIDReg, OnlyForCourseEditBean.getCourseID());
                if (x) {
                    JOptionPane.showMessageDialog(new JFrame(), "Student ID " + txtStdIDReg + " already registered for this course");
                } else {
                    st = conn.getStudentDetails(txtStdIDReg);
                    // show student deails to confirm
                    try {

                        int xx = new CourseDbConnection().studentRegistrationForCourse(st.getStudentID(), OnlyForCourseEditBean.getCourseID(), StaticCardType,"");

                        if (xx != 1) {
                            JOptionPane.showMessageDialog(new JFrame(), "Student is already registered with this course");

                        } else if (xx == 1) {
                            JOptionPane.showMessageDialog(new JFrame(), "Student " + st.getStudentID() + " Successfully Redistered");

                        }
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(new JFrame(), "Error Occured when Inserting");

                    }

                }
            } else {
                JOptionPane.showMessageDialog(new JFrame(), "To countinue the prosses Student must be registered");

            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(new JFrame(), "Error Ocuured. Try Again");

        }
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(test.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(test.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(test.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(test.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                test t = new test();

                t.setVisible(true);
            }
        });

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JSpinner ExtraEndTime;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton btnIssue;
    private javax.swing.JButton btnSearch;
    private javax.swing.JButton btnSearch1;
    private javax.swing.JButton btnSearch2;
    private javax.swing.JButton btnSearch3;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JCheckBox chkFriday;
    private javax.swing.JCheckBox chkMonday;
    private javax.swing.JCheckBox chkSaturday;
    private javax.swing.JCheckBox chkSunday;
    private javax.swing.JCheckBox chkThursday;
    private javax.swing.JCheckBox chkTuesday;
    private javax.swing.JCheckBox chkWednesday;
    private javax.swing.JComboBox cmbStdIDReg;
    private javax.swing.JComboBox cmbStdIDReg1;
    private javax.swing.JComboBox cmbStdIDReg2;
    private javax.swing.JComboBox cmbStdIDReg3;
    private javax.swing.JComboBox cmbStdIDRem;
    private javax.swing.JComboBox cmbStdMobReg;
    private javax.swing.JComboBox cmbStdMobReg1;
    private javax.swing.JComboBox cmbStdMobReg2;
    private javax.swing.JComboBox cmbStdMobReg3;
    private javax.swing.JComboBox cmbStdMobRem;
    private javax.swing.JComboBox cmbStdNICReg;
    private javax.swing.JComboBox cmbStdNICReg1;
    private javax.swing.JComboBox cmbStdNICReg2;
    private javax.swing.JComboBox cmbStdNICReg3;
    private javax.swing.JComboBox cmbStdNICRem;
    private javax.swing.JComboBox cmbStdNameReg;
    private javax.swing.JComboBox cmbStdNameReg1;
    private javax.swing.JComboBox cmbStdNameReg2;
    private javax.swing.JComboBox cmbStdNameReg3;
    private javax.swing.JComboBox cmbStdNameRem;
    private javax.swing.JButton extraAdd;
    private javax.swing.JSpinner extraStartTime;
    private javax.swing.JSpinner friEd;
    private javax.swing.JSpinner friSt;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton16;
    private javax.swing.JButton jButton17;
    private javax.swing.JButton jButton19;
    private javax.swing.JButton jButton21;
    private javax.swing.JButton jButton22;
    private javax.swing.JButton jButton23;
    private javax.swing.JButton jButton24;
    private javax.swing.JButton jButton25;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JComboBox jComboBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JLabel jLabel69;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel70;
    private javax.swing.JLabel jLabel71;
    private javax.swing.JLabel jLabel72;
    private javax.swing.JLabel jLabel73;
    private javax.swing.JLabel jLabel74;
    private javax.swing.JLabel jLabel75;
    private javax.swing.JLabel jLabel76;
    private javax.swing.JLabel jLabel77;
    private javax.swing.JLabel jLabel9;
    private com.toedter.components.JLocaleChooser jLocaleChooser1;
    private static javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private static javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTabbedPane jTabbedPane3;
    private com.toedter.calendar.JDateChooser jdate;
    private javax.swing.JLabel lblBatchNo;
    private javax.swing.JLabel lblGrade;
    private javax.swing.JLabel lblHalf;
    private javax.swing.JLabel lblMonthlyFee;
    private javax.swing.JLabel lblName;
    private javax.swing.JLabel lblSubject;
    private javax.swing.JLabel lblTotalFee;
    private javax.swing.JLabel lblfree;
    private javax.swing.JLabel lbltotalStudent;
    private static javax.swing.JLabel lblvrification;
    private static javax.swing.JLabel lblvrification1;
    private static javax.swing.JLabel lblvrification2;
    private static javax.swing.JLabel lblvrification3;
    private static javax.swing.JLabel lblvrificationDel;
    private javax.swing.JSpinner monEd;
    private javax.swing.JSpinner monSt;
    private javax.swing.JRadioButton rEdit50Free;
    private javax.swing.JRadioButton rEditFree;
    private javax.swing.JRadioButton rFull;
    private javax.swing.JRadioButton rIssue50Free;
    private javax.swing.JRadioButton rIssueFree;
    private javax.swing.JRadioButton rNormal;
    private javax.swing.JRadioButton rhalf;
    private javax.swing.JSpinner satEd;
    private javax.swing.JSpinner satSt;
    private javax.swing.JSpinner sunEd;
    private javax.swing.JSpinner sunSt;
    private javax.swing.JTable tblExtra;
    private javax.swing.JButton test;
    private javax.swing.JButton test1;
    private javax.swing.JSpinner thuEd;
    private javax.swing.JSpinner thuSt;
    private javax.swing.JSpinner tueEd;
    private javax.swing.JSpinner tueSt;
    private javax.swing.JTextField txtMonthlyfee;
    private javax.swing.JTextField txtPrecentage;
    private javax.swing.JTextField txtTotalFee;
    private javax.swing.JSpinner wedEd;
    private javax.swing.JSpinner wedSt;
    // End of variables declaration//GEN-END:variables
}
