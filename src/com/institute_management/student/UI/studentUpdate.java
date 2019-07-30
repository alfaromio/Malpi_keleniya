/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.institute_management.student.UI;

import com.institute_management.attendance_new.dbConnection;
import com.institute_management.configurations.Config;
import com.institute_management.course_mgt.CourseDbConnection;
import com.institute_management.report.reportGen;
import com.institute_management.student.BEAN.Student;
import static com.institute_management.student.UI.selectStudent.studentDont;
import com.institute_management.subject_mgt.DB.SubjectDbConnection;
import static com.institute_management.user_mgt.UI.NewLogin2.logger;
import static com.institute_management.util.CommonMethods.addSchoolsToStaticList;
import com.institute_management.util.Configurations;
import static com.institute_management.util.Configurations.schoolList;
import com.institute_management.util.autoSuggestt;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Maduranga
 */
public class studentUpdate extends javax.swing.JFrame {

    /**
     * Creates new form studentUpdate
     */
    Student st = new Student();
    SubjectDbConnection dbConnn = new SubjectDbConnection();
    CourseDbConnection cdb = new CourseDbConnection();
    dbConnection a = new dbConnection();
    Config config = new Config();

    public studentUpdate() {
    }

    public studentUpdate(Student stt) {
        initComponents();
        st = stt;
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("images.png")));
        loadStudentUpdate();
        loadImage();
        loadSchool("");
        //  loadCourseComboBox();
        loadSubjectComboBox();
        loadTextBoxCourseName();
        loadCourseComboBoxOnStudent();

        loadtblStudentCourese();
        final JTextField textfieldIDSchool = (JTextField) txtSchoolUpdate.getEditor().getEditorComponent();
        textfieldIDSchool.addKeyListener(new KeyAdapter() {

            public void keyReleased(KeyEvent ke) {

                if (ke.getKeyCode() == 38 || ke.getKeyCode() == 40) {
                    //this is necessory. becouse without this if we cant select items in dropdown using downarrow key
                } else if (ke.getKeyCode() != KeyEvent.VK_ENTER) {
                    System.out.println(ke.getKeyCode());
                    SwingUtilities.invokeLater(new Runnable() {
                        public void run() {
                            try {
                                // new autoSuggestt().comboFilter(textfieldIDSchool.getText(), txtSchool, 5);
                                new autoSuggestt().comboInternalFilter(textfieldIDSchool.getText(), txtSchoolUpdate);
                            } catch (Exception ex) {
                                Logger.getLogger(addStudent.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    });
                }
            }
        });

    }

    void loadStudentUpdate() {
        try {
            // st = studentDont;

            txtAddrs.setText(st.getAddress() + "," + st.getAddress1() + "," + st.getAddress2());
            txtDob.setText(st.getDob());
            txtMail.setText(st.getEmail());
            txtName.setText(st.getName());
            txtRegNo.setText(st.getStudentID());
            txtSchool.setText(st.getSchool());
            txtTele.setText(st.getTelephn());
            txtYor.setText(st.getYearOfReg());
            txtpcontact.setText(st.getpContactNo());
            txtGender.setText(st.getGender());

            if ((st.getAdmissionStatuse().equals("PAID"))) {
                admissionPaymnetStatus.setText("PAID");
                admissionPaymanetDate.setText(st.getAdmissionDate());
            } else {
                admissionPaymnetStatus.setText("NOT PAID");
                admissionPaymanetDate.setText("--");
            }

            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            txtCardID.setText(st.getCardNumber());
            txtemail.setText(st.getEmail());//
            txtAddrs1.setText(st.getAddress());//
            txtAddrs3.setText(st.getAddress2());//
            txtAddrs2.setText(st.getAddress1());//
            txtName1.setText(st.getName());//
            txtpcontact1.setText(st.getpContactNo());//
            txtSchoolUpdate.setSelectedItem(st.getSchool());//
            txtfName.setText(st.getFname()); //      
            txtLandLine.setText(st.getLandline_no());//
            txtTele1.setText(st.getTelephn());//
            jDOB.setDate(sdf.parse(st.getDob()));
            jYOR.setDate(sdf.parse(st.getYearOfReg()));
            comboGender.setSelectedItem(st.getGender());
        } catch (ParseException ex) {
            //------------------------log Start--------------------------------------------
            logger.error(studentUpdate.class + ".updateStudent(" + st + ") ->error" + ex);
            //-------------------------log End---------------------------------------------
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
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtMail = new javax.swing.JTextField();
        txtAddrs = new javax.swing.JTextField();
        txtDob = new javax.swing.JTextField();
        txtName = new javax.swing.JTextField();
        txtRegNo = new javax.swing.JTextField();
        txtTele = new javax.swing.JTextField();
        txtSchool = new javax.swing.JTextField();
        txtpcontact = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txtYor = new javax.swing.JTextField();
        txtGender = new javax.swing.JTextField();
        lblImg = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel31 = new javax.swing.JLabel();
        txtSchoolUpdate = new javax.swing.JComboBox();
        jLabel32 = new javax.swing.JLabel();
        txtName1 = new javax.swing.JTextField();
        jLabel33 = new javax.swing.JLabel();
        jDOB = new com.toedter.calendar.JDateChooser();
        txtAddrs1 = new javax.swing.JTextField();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        txtAddrs3 = new javax.swing.JTextField();
        comboGender = new javax.swing.JComboBox();
        jLabel36 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        txtTele1 = new javax.swing.JTextField();
        jYOR = new com.toedter.calendar.JDateChooser();
        jLabel38 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        Update = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel40 = new javax.swing.JLabel();
        txtpcontact1 = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        txtfName = new javax.swing.JTextField();
        jLabel41 = new javax.swing.JLabel();
        txtCardID = new javax.swing.JTextField();
        txtemail = new javax.swing.JTextField();
        txtAddrs2 = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        txtLandLine = new javax.swing.JTextField();
        jButton5 = new javax.swing.JButton();
        jLabel42 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        txtCName = new javax.swing.JTextField();
        rNormal = new javax.swing.JRadioButton();
        rhalf = new javax.swing.JRadioButton();
        rFull = new javax.swing.JRadioButton();
        jButton3 = new javax.swing.JButton();
        comboCourseId = new javax.swing.JComboBox();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        subjectCombo = new javax.swing.JComboBox();
        cmbGrade = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();
        txtCName1 = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblStudentCourse = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jPanel11 = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        admissionPaymnetStatus = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        admissionPaymanetDate = new javax.swing.JLabel();
        txtAmount = new javax.swing.JTextField();
        jPanel6 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        CouserListChangeCard = new javax.swing.JComboBox();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox();
        jToggleButton1 = new javax.swing.JToggleButton();
        lblCardType = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        cmbMonth2 = new javax.swing.JComboBox();
        jLabel49 = new javax.swing.JLabel();
        jButton8 = new javax.swing.JButton();
        jLabel50 = new javax.swing.JLabel();
        cmbYear2 = new javax.swing.JComboBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Main details"));

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("Name");

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("Date of Birth ");

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel4.setText("Address");

        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel6.setText("Email");

        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel7.setText("Gender");

        txtMail.setEditable(false);
        txtMail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMailActionPerformed(evt);
            }
        });

        txtAddrs.setEditable(false);
        txtAddrs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtAddrsActionPerformed(evt);
            }
        });

        txtDob.setEditable(false);
        txtDob.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDobActionPerformed(evt);
            }
        });

        txtName.setEditable(false);
        txtName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNameActionPerformed(evt);
            }
        });

        txtRegNo.setEditable(false);
        txtRegNo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtRegNoActionPerformed(evt);
            }
        });

        txtTele.setEditable(false);

        txtSchool.setEditable(false);
        txtSchool.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSchoolActionPerformed(evt);
            }
        });

        txtpcontact.setEditable(false);
        txtpcontact.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtpcontactActionPerformed(evt);
            }
        });

        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel10.setText("Parent Contact NO");

        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel8.setText("School");

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel5.setText("Telephone");

        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel9.setText("Registration  Number");

        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel11.setText("Year of Registration");

        txtYor.setEditable(false);
        txtYor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtYorActionPerformed(evt);
            }
        });

        txtGender.setEditable(false);
        txtGender.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtGenderActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(72, 72, 72)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtAddrs, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtMail, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtGender, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtDob, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(68, 68, 68)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(68, 68, 68)
                        .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(56, 56, 56)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtRegNo, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTele, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSchool, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtpcontact, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtYor, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblImg, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(81, 81, 81))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblImg, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel2)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel9)
                                .addComponent(txtRegNo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtDob, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel3)
                                .addComponent(jLabel5)
                                .addComponent(txtTele, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtAddrs, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel8)
                                .addComponent(txtSchool, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtMail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel6)
                                .addComponent(jLabel10)
                                .addComponent(txtpcontact, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtYor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtGender, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel7)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 51, 255)));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel31.setFont(new java.awt.Font("Calibri Light", 1, 18)); // NOI18N
        jLabel31.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel31.setText("Update Student Details");
        jLabel31.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel3.add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 20, 390, 40));

        txtSchoolUpdate.setEditable(true);
        jPanel3.add(txtSchoolUpdate, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 170, 188, -1));

        jLabel32.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel32.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel32.setText("First Name");
        jPanel3.add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 110, 100, 22));

        txtName1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtName1ActionPerformed(evt);
            }
        });
        jPanel3.add(txtName1, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 110, 570, -1));

        jLabel33.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel33.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel33.setText("Date of Birth");
        jPanel3.add(jLabel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 200, 102, 22));

        jDOB.setDateFormatString("yyyy-MM-dd");
        jDOB.setPreferredSize(new java.awt.Dimension(6, 20));
        jPanel3.add(jDOB, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 200, 186, 25));

        txtAddrs1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtAddrs1ActionPerformed(evt);
            }
        });
        jPanel3.add(txtAddrs1, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 200, 186, -1));

        jLabel34.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel34.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel34.setText("Upload Image");
        jPanel3.add(jLabel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 320, 90, 20));

        jLabel35.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel35.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel35.setText("Parents Name");
        jPanel3.add(jLabel35, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 170, 110, 20));

        txtAddrs3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtAddrs3ActionPerformed(evt);
            }
        });
        jPanel3.add(txtAddrs3, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 260, 186, -1));

        comboGender.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Male", "Female" }));
        comboGender.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboGenderActionPerformed(evt);
            }
        });
        jPanel3.add(comboGender, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 170, 180, -1));

        jLabel36.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel36.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel36.setText("Gender");
        jPanel3.add(jLabel36, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 170, 102, 20));

        jLabel37.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel37.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel37.setText("School");
        jPanel3.add(jLabel37, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 170, 70, 20));
        jPanel3.add(txtTele1, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 260, 188, -1));

        jYOR.setDateFormatString("yyyy-MM-dd");
        jYOR.setPreferredSize(new java.awt.Dimension(6, 20));
        jPanel3.add(jYOR, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 230, 188, 25));

        jLabel38.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel38.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel38.setText("Year Of Reg");
        jPanel3.add(jLabel38, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 230, 110, 20));

        jLabel39.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel39.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel39.setText("Occupation");
        jPanel3.add(jLabel39, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 230, 140, -1));

        Update.setBackground(new java.awt.Color(255, 255, 255));
        Update.setText("Update");
        Update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UpdateActionPerformed(evt);
            }
        });
        jPanel3.add(Update, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 320, 83, -1));

        jButton2.setBackground(new java.awt.Color(255, 255, 255));
        jButton2.setText("Clear");
        jPanel3.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1010, 320, 80, -1));

        jLabel40.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel40.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel40.setText("Parent Mobile 1");
        jPanel3.add(jLabel40, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 200, 130, 20));

        txtpcontact1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtpcontact1ActionPerformed(evt);
            }
        });
        jPanel3.add(txtpcontact1, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 200, 188, -1));

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel12.setText("Full Name");
        jPanel3.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, 90, -1));

        txtfName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtfNameActionPerformed(evt);
            }
        });
        jPanel3.add(txtfName, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 110, 186, -1));

        jLabel41.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel41.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel41.setText("Card Number");
        jPanel3.add(jLabel41, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 260, 102, 20));

        txtCardID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCardIDActionPerformed(evt);
            }
        });
        jPanel3.add(txtCardID, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 260, 186, -1));

        txtemail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtemailActionPerformed(evt);
            }
        });
        jPanel3.add(txtemail, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 170, 186, -1));

        txtAddrs2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtAddrs2ActionPerformed(evt);
            }
        });
        jPanel3.add(txtAddrs2, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 230, 186, -1));

        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel20.setText("Parent Mobile 2");
        jPanel3.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 260, 140, -1));
        jPanel3.add(txtLandLine, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 230, 188, -1));

        jButton5.setText("-Select-");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 320, 190, -1));

        jLabel42.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel42.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel42.setText("Address");
        jPanel3.add(jLabel42, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 200, 90, 20));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 1269, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 364, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Update Details", jPanel2);

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));
        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder("Add Course"));

        jLabel18.setText("Course ID");

        jLabel19.setText("Course Fee");

        txtCName.setEditable(false);
        txtCName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCNameActionPerformed(evt);
            }
        });

        rNormal.setSelected(true);
        rNormal.setText("Normal Card");
        rNormal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rNormalActionPerformed(evt);
            }
        });

        rhalf.setText("50% Free Card");
        rhalf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rhalfActionPerformed(evt);
            }
        });

        rFull.setText("Totally Free Card");
        rFull.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rFullActionPerformed(evt);
            }
        });

        jButton3.setText("Add");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        comboCourseId.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comboCourseIdItemStateChanged(evt);
            }
        });
        comboCourseId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboCourseIdActionPerformed(evt);
            }
        });

        jLabel24.setText("Select Subject");

        jLabel25.setText("Select Grade");

        subjectCombo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                subjectComboActionPerformed(evt);
            }
        });

        int Examyear = Calendar.getInstance().get(Calendar.YEAR);
        cmbGrade.addItem("Grade 1");
        cmbGrade.addItem("Grade 2");
        cmbGrade.addItem("Grade 3");
        cmbGrade.addItem("Grade 4");
        cmbGrade.addItem("Grade 5");
        cmbGrade.addItem("Grade 6");
        cmbGrade.addItem("Grade 7");
        cmbGrade.addItem("Grade 8");
        cmbGrade.addItem("Grade 9");
        cmbGrade.addItem("Grade 10");
        cmbGrade.addItem("O/L");

        for(int i=3;i>=0;i--){
            String item = "A/L - "+ Integer.toString(Examyear);
            cmbGrade.addItem(item);
            Examyear++;
        }

        cmbGrade.addItem("Common");
        cmbGrade.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbGradeActionPerformed(evt);
            }
        });

        jLabel1.setText("Course Days");

        txtCName1.setEnabled(false);
        txtCName1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCName1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addGap(32, 269, Short.MAX_VALUE)
                                .addComponent(jButton3)
                                .addGap(132, 132, 132))
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addComponent(rNormal)
                                .addGap(18, 18, 18)
                                .addComponent(rhalf)
                                .addGap(18, 18, 18)
                                .addComponent(rFull))))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel24, javax.swing.GroupLayout.DEFAULT_SIZE, 86, Short.MAX_VALUE)
                            .addComponent(jLabel25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(comboCourseId, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtCName, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cmbGrade, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(subjectCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtCName1, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 122, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel24)
                    .addComponent(subjectCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel25)
                    .addComponent(cmbGrade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(comboCourseId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(txtCName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtCName1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rNormal)
                    .addComponent(rhalf)
                    .addComponent(rFull))
                .addGap(34, 34, 34)
                .addComponent(jButton3)
                .addContainerGap())
        );

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Enrolled Courses"));

        jScrollPane2.setBackground(new java.awt.Color(255, 255, 255));

        tblStudentCourse.setForeground(new java.awt.Color(51, 51, 255));
        tblStudentCourse.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Course Name", "Remove"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tblStudentCourse);
        if (tblStudentCourse.getColumnModel().getColumnCount() > 0) {
            tblStudentCourse.getColumnModel().getColumn(0).setMinWidth(500);
            tblStudentCourse.getColumnModel().getColumn(0).setPreferredWidth(500);
            tblStudentCourse.getColumnModel().getColumn(0).setMaxWidth(500);
            tblStudentCourse.getColumnModel().getColumn(1).setMinWidth(100);
            tblStudentCourse.getColumnModel().getColumn(1).setPreferredWidth(100);
            tblStudentCourse.getColumnModel().getColumn(1).setMaxWidth(100);
        }

        jButton1.setText("Remove");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(49, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 600, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addGap(27, 27, 27))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addGap(51, 51, 51))
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(111, 111, 111)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jTabbedPane1.addTab("Course Selection", jPanel4);

        jPanel11.setBackground(new java.awt.Color(255, 255, 255));

        jPanel12.setBackground(new java.awt.Color(255, 255, 255));
        jPanel12.setBorder(javax.swing.BorderFactory.createTitledBorder("Admission Payment"));

        jLabel26.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(51, 51, 255));
        jLabel26.setText("Payment Status");

        jLabel27.setText("Payment  Date         ");

        jButton4.setBackground(new java.awt.Color(255, 255, 255));
        jButton4.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        jButton4.setForeground(new java.awt.Color(0, 204, 0));
        jButton4.setText("Make Payment");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        admissionPaymnetStatus.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        admissionPaymnetStatus.setForeground(new java.awt.Color(51, 51, 255));

        jLabel28.setText("Payment  Amount      ");

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(admissionPaymnetStatus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel28)
                            .addComponent(jLabel27))
                        .addGap(22, 22, 22)
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel12Layout.createSequentialGroup()
                                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(admissionPaymanetDate, javax.swing.GroupLayout.DEFAULT_SIZE, 134, Short.MAX_VALUE)
                                    .addComponent(txtAmount))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 88, Short.MAX_VALUE))
                            .addGroup(jPanel12Layout.createSequentialGroup()
                                .addComponent(jButton4)
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap(38, Short.MAX_VALUE)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel26, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
                    .addComponent(admissionPaymnetStatus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(31, 31, 31)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel27)
                    .addComponent(admissionPaymanetDate, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel28)
                    .addComponent(txtAmount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(64, 64, 64)
                .addComponent(jButton4)
                .addGap(77, 77, 77))
        );

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Change Card Type", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(51, 51, 255))); // NOI18N
        jPanel6.setForeground(new java.awt.Color(51, 51, 255));

        jLabel13.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(51, 51, 255));
        jLabel13.setText("Select Course");

        CouserListChangeCard.setFont(new java.awt.Font("Calibri", 1, 11)); // NOI18N
        CouserListChangeCard.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CouserListChangeCardActionPerformed(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(51, 51, 255));
        jLabel14.setText("Old Card Type");

        jLabel16.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(51, 51, 255));
        jLabel16.setText("Select New Card Type");

        jComboBox1.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        jComboBox1.setForeground(new java.awt.Color(255, 0, 51));
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-Select-", "Free", "200 OFF", "Normal" }));

        jToggleButton1.setBackground(new java.awt.Color(255, 51, 51));
        jToggleButton1.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jToggleButton1.setText("Confirm");
        jToggleButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton1ActionPerformed(evt);
            }
        });

        lblCardType.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        lblCardType.setForeground(new java.awt.Color(0, 204, 51));

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addGap(54, 54, 54)
                        .addComponent(CouserListChangeCard, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel16)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jToggleButton1)
                        .addGroup(jPanel6Layout.createSequentialGroup()
                            .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(lblCardType, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(2, 2, 2)
                            .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)))))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(jLabel13))
                    .addComponent(CouserListChangeCard, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(41, 41, 41)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel15)
                    .addComponent(lblCardType, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE))
                .addGap(28, 28, 28)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(51, 51, 51)
                .addComponent(jToggleButton1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel10.setBackground(new java.awt.Color(255, 255, 255));
        jPanel10.setBorder(javax.swing.BorderFactory.createTitledBorder("Bill Reprint"));

        cmbMonth2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December" }));

        jLabel49.setText("Select Month");

        jButton8.setFont(new java.awt.Font("Cambria Math", 1, 24)); // NOI18N
        jButton8.setForeground(new java.awt.Color(0, 0, 204));
        jButton8.setText("Reprint Bill");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jLabel50.setText("Select Year");

        cmbYear2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "2018", "2019" }));

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(jButton8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(jLabel50, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel49, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addGap(0, 20, Short.MAX_VALUE)
                                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(cmbYear2, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cmbMonth2, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(20, 20, 20))))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(jLabel49)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cmbMonth2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel50)
                .addGap(3, 3, 3)
                .addComponent(cmbYear2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41)
                .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(67, 67, 67)
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(46, 46, 46)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(51, 51, 51)
                .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(24, 24, 24))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel10, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jTabbedPane1.addTab("Admission | Change Card | Bill Reprint", jPanel11);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jTabbedPane1)
                        .addContainerGap())
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
public void loadSchool(String name) {
        try {

            for (int i = 0; i < schoolList.size(); i++) {
                String role = schoolList.get(i);
                txtSchoolUpdate.addItem(role);
            }

        } catch (Exception ex) {
            //------------------------log Start--------------------------------------------
            logger.error(studentUpdate.class + ".loadSchool(" + name + ") ->error" + ex);
            //-------------------------log End---------------------------------------------
        }
    }
    private void txtCNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCNameActionPerformed

    }//GEN-LAST:event_txtCNameActionPerformed

    private void rNormalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rNormalActionPerformed
        rhalf.setSelected(false);
        rFull.setSelected(false);
    }//GEN-LAST:event_rNormalActionPerformed

    private void rhalfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rhalfActionPerformed
        rNormal.setSelected(false);
        rFull.setSelected(false);
    }//GEN-LAST:event_rhalfActionPerformed

    private void rFullActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rFullActionPerformed
        rNormal.setSelected(false);
        rhalf.setSelected(false);
    }//GEN-LAST:event_rFullActionPerformed

    private void comboCourseIdItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboCourseIdItemStateChanged
        // TODO add your handling code here:
        String courseId = comboCourseId.getSelectedItem().toString();
    }//GEN-LAST:event_comboCourseIdItemStateChanged

    private void comboCourseIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboCourseIdActionPerformed
        // TODO add your handling code here:
        loadTextBoxCourseName();
    }//GEN-LAST:event_comboCourseIdActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        int cardType = 2;
        if (rhalf.isSelected()) {
            cardType = 1;
        } else if (rFull.isSelected()) {
            cardType = 0;
        }
        try {
             if(comboCourseId.getSelectedItem()==null){
                JOptionPane.showMessageDialog(new JFrame(), "Select Course First");
            }else if(comboCourseId.getSelectedItem().toString().equals("--") ){
                JOptionPane.showMessageDialog(new JFrame(), "Select Course First");
            }else{
                cdb.studentRegistrationForCourse(st.getStudentID(), comboCourseId.getSelectedItem().toString(), cardType,txtCName.getText());
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(new JFrame(), "Error In Add course to Student" + e);
            //------------------------log Start--------------------------------------------
            logger.error(studentUpdate.class + ".jButton3ActionPerformed() ->error" + e);
            //-------------------------log End---------------------------------------------

        }
        loadtblStudentCourese();
        loadCourseComboBoxOnStudent();

    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        // TODO add your handling code here:
        int count = 0;
        double admissionAmount = Double.parseDouble(txtAmount.getText());
        try {
            int result = dbConnn.checkAdmission((st.getStudentID()));
            if (result == 1) {
                JOptionPane.showMessageDialog(new JFrame(), "Already Paid");
            } else {
                count = dbConnn.makeAdmission(st.getStudentID(), admissionAmount);

                if (count > 0) {
                    JOptionPane.showMessageDialog(new JFrame(), "Admission Payment success");
                    int i = dbConnn.okcancel("Do You Want To Print Bill ?");
                    if (i == 0) {
                        HashMap a = new HashMap();
                        double totalAmount = 0.00;
                        a.put("id", st.getStudentID());
                        a.put("regDate", st.getYearOfReg());
                        a.put("name", st.getName());
                        a.put("fee", txtAmount.getText());
                        a.put("userName", Configurations.UserBean.getUserName());

                        reportGen rg;

                        rg = new reportGen(config.report_path + "admissionPaymentrecipt.jasper", a);
                        rg.setVisible(true);
                    }
                }
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(new JFrame(), "Error in admission Payment" + e);
            //------------------------log Start--------------------------------------------
            logger.error(studentUpdate.class + ".jButton4ActionPerformed() ->error" + e);
            //-------------------------log End---------------------------------------------

        }

    }//GEN-LAST:event_jButton4ActionPerformed

    private void txtGenderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtGenderActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtGenderActionPerformed

    private void txtYorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtYorActionPerformed
        // TODO add your handling code here:
        //  st.setParentEmail(txtPnameUpdate.getText());
    }//GEN-LAST:event_txtYorActionPerformed

    private void txtpcontactActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtpcontactActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtpcontactActionPerformed

    private void txtSchoolActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSchoolActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSchoolActionPerformed

    private void txtRegNoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtRegNoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtRegNoActionPerformed

    private void txtNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNameActionPerformed

    private void txtDobActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDobActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDobActionPerformed

    private void txtAddrsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtAddrsActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAddrsActionPerformed

    private void txtMailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMailActionPerformed

    private void subjectComboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subjectComboActionPerformed
        String a = subjectCombo.getSelectedItem().toString();
        loadCourseComboBox("", a);
    }//GEN-LAST:event_subjectComboActionPerformed

    private void cmbGradeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbGradeActionPerformed
        String a = subjectCombo.getSelectedItem().toString();
        String aa = cmbGrade.getSelectedItem().toString();
        loadCourseComboBox(aa, a);
    }//GEN-LAST:event_cmbGradeActionPerformed

    private void txtCName1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCName1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCName1ActionPerformed

    private void txtName1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtName1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtName1ActionPerformed

    private void txtAddrs1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtAddrs1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAddrs1ActionPerformed

    private void txtAddrs3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtAddrs3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAddrs3ActionPerformed

    private void comboGenderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboGenderActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboGenderActionPerformed

    private void UpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UpdateActionPerformed
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        //------------------------log Start--------------------------------------------
        logger.info("***Student details Update(Student ID" + st.getStudentID() + ")");
        if (st.getName() == null || !st.getName().equals(txtName1.getText())) {
            logger.info("FullName changed " + "OLD :" + st.getName() + "| NEW :" + txtName1.getText());
        }
        if (st.getSchool() == null || !st.getSchool().equals(txtSchoolUpdate.getSelectedItem().toString())) {
            logger.info("School changed " + "OLD :" + st.getSchool() + "| NEW :" + txtSchoolUpdate.getSelectedItem().toString());
        }
        if (st.getAddress() == null || !st.getAddress().equals(txtAddrs1.getText())) {
            logger.info("AdressLine1 changed " + "OLD :" + st.getAddress() + "| NEW :" + txtAddrs1.getText());
        }
        if (st.getAddress1() == null || !st.getAddress1().equals(txtAddrs2.getText())) {
            logger.info("AdressLine2 changed " + "OLD :" + st.getAddress1() + "| NEW :" + txtAddrs2.getText());
        }
        if (st.getAddress2() == null || !st.getAddress2().equals(txtAddrs3.getText())) {
            logger.info("AdressLine3 changed " + "OLD :" + st.getAddress2() + "| NEW :" + txtAddrs3.getText());
        }
        if (st.getEmail() == null || !st.getEmail().equals(txtemail.getText())) {
            logger.info("Email changed " + "OLD :" + st.getEmail() + "| NEW :" + txtemail.getText());
        }
        if (st.getTelephn() == null || !st.getTelephn().equals(txtTele1.getText())) {
            logger.info("Student mobile changed " + "OLD :" + st.getTelephn() + "| NEW :" + txtTele1.getText());
        }
        if (st.getFname() == null || !st.getFname().equals(txtfName.getText())) {
            logger.info("FirstName changed " + "OLD :" + st.getFname() + "| NEW :" + txtfName.getText());
        }
        if (st.getCardNumber() == null || !st.getCardNumber().equals(txtCardID.getText())) {
            logger.info("CardNumber changed " + "OLD :" + st.getCardNumber() + "| NEW :" + txtCardID.getText());
        }
        if (st.getpContactNo() == null || !st.getpContactNo().equals(txtpcontact1.getText())) {
            logger.info("ParentsMobile changed " + "OLD :" + st.getpContactNo() + "| NEW :" + txtpcontact1.getText());
        }
        if (st.getLandline_no() == null || !st.getLandline_no().equals(txtLandLine.getText())) {
            logger.info("LandLine changed " + "OLD :" + st.getLandline_no() + "| NEW :" + txtLandLine.getText());
        }
        if (!st.getDob().equals(sdf.format(jDOB.getDate()))) {
            logger.info("DOB changed " + "OLD :" + st.getDob() + "| NEW :" + jDOB.getDate());
        }
        if (!st.getYearOfReg().equals(sdf.format(jYOR.getDate()))) {
            logger.info("Reg Date changed " + "OLD :" + st.getYearOfReg() + "| NEW :" + jYOR.getDate());
        }
        if (!st.getGender().equals(comboGender.getSelectedItem().toString())) {
            logger.info("Gender changed " + "OLD :" + st.getGender() + "| NEW :" + comboGender.getSelectedItem().toString());
        }
        //-------------------------log End---------------------------------------------
        try {

            st.setName(txtName1.getText());
            st.setSchool(txtSchoolUpdate.getSelectedItem().toString());
            st.setAddress(txtAddrs1.getText());
            st.setAddress1(txtAddrs2.getText());
            st.setAddress2(txtAddrs3.getText());
            st.setEmail(txtemail.getText());
            st.setTelephn(txtTele1.getText());
            st.setFname(txtfName.getText());
            st.setCardNumber(txtCardID.getText());
            st.setpContactNo(txtpcontact1.getText());
            st.setLandline_no(txtLandLine.getText());
            st.setDob(sdf.format(jDOB.getDate()));
            st.setYearOfReg(sdf.format(jYOR.getDate()));
            st.setGender(comboGender.getSelectedItem().toString());

            int count = dbConnn.updateStudent(st);
            if (count > 0) {
                dbConnn.insertSchool(st.getSchool());
                JOptionPane.showMessageDialog(new JFrame(), "Successfully Updated");
                logger.info("***Student details successfully Updated(Student ID" + st.getStudentID() + ")");
            }

            loadStudentUpdate();
            addSchoolsToStaticList();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(new JFrame(), "Error In student Update" + e);
            //------------------------log Start--------------------------------------------
            logger.error(studentUpdate.class + ".updateStudent(" + st.getStudentID() + ") ->error" + e);
            //-------------------------log End---------------------------------------------
        }
    }//GEN-LAST:event_UpdateActionPerformed

    private void txtpcontact1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtpcontact1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtpcontact1ActionPerformed

    private void txtfNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtfNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtfNameActionPerformed

    private void txtCardIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCardIDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCardIDActionPerformed

    private void txtemailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtemailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtemailActionPerformed

    private void txtAddrs2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtAddrs2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAddrs2ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed

        ImageUploader im = new ImageUploader(txtRegNo.getText().toString());
        im.setVisible(true);

        //this.disable();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        int x = 0;
        int count = 0;

        for (int i = 0; i < tblStudentCourse.getRowCount(); i++) {
            Object text = tblStudentCourse.getValueAt(i, 1);
            if (text != null) {
                try {

                    int j = cdb.okcancel("Please Double Check.This Action Can't Reverse.Confirm ?");
                    if (j == 0) {
                        x = new CourseDbConnection().studentDeleteFromCourse(st.getStudentID(), tblStudentCourse.getValueAt(i, 0).toString());
                    } else {
                        JOptionPane.showMessageDialog(new JFrame(), "Action Terminated");
                    }
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
            JOptionPane.showMessageDialog(new JFrame(), " Student Successfully Remove from the Course");
            loadtblStudentCourese();
        } else {
            JOptionPane.showMessageDialog(new JFrame(), "error Occured. Try again");
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void CouserListChangeCardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CouserListChangeCardActionPerformed

        if (CouserListChangeCard.getSelectedItem().toString().equals("--")) {
            lblCardType.setText(null);
        } else {
            String CardType = dbConnn.getCardTypeBySID_CID(st.getStudentID(), CouserListChangeCard.getSelectedItem().toString());
            CardType = CardType.equals("2") ? "Normal Card" : (CardType.equals("1") ? "Half Card" : "Free Card");
            lblCardType.setText(CardType);
        }

    }//GEN-LAST:event_CouserListChangeCardActionPerformed

    private void jToggleButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton1ActionPerformed
        String type = jComboBox1.getSelectedItem().toString();
        //-Select-, Free, Half, Normal
        if (type.equals("-Select-")) {
            JOptionPane.showMessageDialog(new JFrame(), "Select New Card Type, Then Try");
        } else {
            int j = cdb.okcancel("Please Double Check.This Action Can't Reverse.Confirm ?");
            if (j == 0) {
                type = type.equals("Free") ? "0" : (type.equals("Half") ? "1" : "2");
                int x = dbConnn.UpdateCardType(st.getStudentID(), CouserListChangeCard.getSelectedItem().toString(), type);
                if (x != 1) {
                    JOptionPane.showMessageDialog(new JFrame(), "Card Type not Changed.Try Again");
                } else {
                    JOptionPane.showMessageDialog(new JFrame(), "Successfully Update");
                    
                }
            }

        }
        lblCardType.setText(null);
        jComboBox1.setSelectedIndex(0);
        CouserListChangeCard.setSelectedIndex(0);

    }//GEN-LAST:event_jToggleButton1ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        try {
           String id = st.getStudentID() + cmbYear2.getSelectedItem().toString() + (((cmbMonth2.getSelectedIndex()+1)+"").length()==1?("0"+(cmbMonth2.getSelectedIndex()+1)):(cmbMonth2.getSelectedIndex()+1)+"");
            String ActualBillID = cdb.getActualBillID(id);

            if (!(ActualBillID==null)) {
                a.printBill(ActualBillID);
            } else {
                JOptionPane.showMessageDialog(new JFrame(), "No Past Bill Print");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(new JFrame(), "Error when printing");
        }
    }//GEN-LAST:event_jButton8ActionPerformed

    public String getCourseName(String CourseId) {

        return null;
    }

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
            java.util.logging.Logger.getLogger(studentUpdate.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(studentUpdate.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(studentUpdate.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(studentUpdate.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new studentUpdate().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox CouserListChangeCard;
    private javax.swing.JButton Update;
    private javax.swing.JLabel admissionPaymanetDate;
    private javax.swing.JLabel admissionPaymnetStatus;
    private javax.swing.JComboBox cmbGrade;
    private javax.swing.JComboBox cmbMonth2;
    private javax.swing.JComboBox cmbYear2;
    private javax.swing.JComboBox comboCourseId;
    private javax.swing.JComboBox comboGender;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton8;
    private javax.swing.JComboBox jComboBox1;
    private com.toedter.calendar.JDateChooser jDOB;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JToggleButton jToggleButton1;
    private com.toedter.calendar.JDateChooser jYOR;
    private javax.swing.JLabel lblCardType;
    private javax.swing.JLabel lblImg;
    private javax.swing.JRadioButton rFull;
    private javax.swing.JRadioButton rNormal;
    private javax.swing.JRadioButton rhalf;
    private javax.swing.JComboBox subjectCombo;
    private javax.swing.JTable tblStudentCourse;
    private javax.swing.JTextField txtAddrs;
    private javax.swing.JTextField txtAddrs1;
    private javax.swing.JTextField txtAddrs2;
    private javax.swing.JTextField txtAddrs3;
    private javax.swing.JTextField txtAmount;
    private javax.swing.JTextField txtCName;
    private javax.swing.JTextField txtCName1;
    private javax.swing.JTextField txtCardID;
    private javax.swing.JTextField txtDob;
    private javax.swing.JTextField txtGender;
    private javax.swing.JTextField txtLandLine;
    private javax.swing.JTextField txtMail;
    private javax.swing.JTextField txtName;
    private javax.swing.JTextField txtName1;
    private javax.swing.JTextField txtRegNo;
    private javax.swing.JTextField txtSchool;
    private javax.swing.JComboBox txtSchoolUpdate;
    private javax.swing.JTextField txtTele;
    private javax.swing.JTextField txtTele1;
    private javax.swing.JTextField txtYor;
    private javax.swing.JTextField txtemail;
    private javax.swing.JTextField txtfName;
    private javax.swing.JTextField txtpcontact;
    private javax.swing.JTextField txtpcontact1;
    // End of variables declaration//GEN-END:variables

    private void loadCourseComboBox(String grade, String Subject) {
        try {
            ArrayList<String> courselist = null;
            courselist = dbConnn.getCourseList(grade, Subject);
            if (courselist != null) {

                comboCourseId.setModel(new DefaultComboBoxModel(courselist.toArray()));
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(new JFrame(), "Error in selecting Course Id" + e);
            //------------------------log Start--------------------------------------------
            logger.error(studentUpdate.class + ".loadCourseComboBox(" + grade + "," + Subject + ") ->error" + e);
            //-------------------------log End---------------------------------------------
        }
    }

    private void loadSubjectComboBox() {
        try {
            ArrayList<String> subjectlist = null;
            subjectlist = dbConnn.getSubjectList();
            if (subjectlist != null) {

                subjectCombo.setModel(new DefaultComboBoxModel(subjectlist.toArray()));
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(new JFrame(), "Error in selecting Subject Id" + e);
            //------------------------log Start--------------------------------------------
            logger.error(studentUpdate.class + ".loadSubjectComboBox() ->error" + e);
            //-------------------------log End---------------------------------------------
        }
    }

    private void loadCourseComboBoxOnStudent() {
        try {
            ArrayList<String> courselist = null;
            courselist = dbConnn.getCourseListOnStudent(st.getStudentID());
            if (courselist != null) {
                CouserListChangeCard.setModel(new DefaultComboBoxModel(courselist.toArray()));
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(new JFrame(), "Error in selecting Course Id" + e);
            //------------------------log Start--------------------------------------------
            logger.error(studentUpdate.class + ".loadCourseComboBoxOnStudent() ->error" + e);
            //-------------------------log End---------------------------------------------
        }
    }

    private void loadTextBoxCourseName() {
        try {
            String corseID = comboCourseId.getSelectedItem() != null ? comboCourseId.getSelectedItem().toString() : null;
            if (corseID == null || corseID == "--") {
                txtCName.setText(null);
            } else {
                String courseFee = dbConnn.getCourseFeeOnId(corseID);
                txtCName.setText(courseFee);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(new JFrame(), "Error in loading Course Name" + e);
            //------------------------log Start--------------------------------------------
            logger.error(studentUpdate.class + ".loadTextBoxCourseName() ->error" + e);
            //-------------------------log End---------------------------------------------
        }
    }

    private void loadtblStudentCourese() {
        try {
            HashMap<Integer, Object[]> tblData = dbConnn.getCourseDetailsOnStudent(st.getStudentID());
            DefaultTableModel model = (DefaultTableModel) tblStudentCourse.getModel();
            model.setRowCount(0);
            for (int i = 1; i <= tblData.size(); i++) {
                model.addRow(tblData.get(i));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(new JFrame(), "Error in loading Course Name" + e);
            //------------------------log Start--------------------------------------------
            logger.error(studentUpdate.class + ".loadtblStudentCourese() ->error" + e);
            //-------------------------log End---------------------------------------------
        }
    }

    private void loadImage() {
        try {
            ImageIcon image = new ImageIcon(config.student_image_path + st.getStudentID() + ".jpg");
            Image im = image.getImage();
            Image myImg = im.getScaledInstance(lblImg.getWidth(), lblImg.getHeight(), Image.SCALE_SMOOTH);
            Icon newImage = new ImageIcon(myImg);
            lblImg.setIcon(newImage);
        } catch (Exception e) {

            ImageIcon image = new ImageIcon(config.student_image_path + "error.jpg");
            Image im = image.getImage();
            Image myImg = im.getScaledInstance(lblImg.getWidth(), lblImg.getHeight(), Image.SCALE_SMOOTH);
            Icon newImage = new ImageIcon(myImg);
            lblImg.setIcon(newImage);
            //------------------------log Start--------------------------------------------
            logger.error(studentUpdate.class + ".loadImage() ->error" + e);
            //-------------------------log End---------------------------------------------
        }

    }
}
