/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.institute_management.report;

import com.institute_management.main.NewJFrame;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Malinda Ranabahu
 */
public class reportDash extends javax.swing.JFrame {

    /**
     * Creates new form reportDash
     */
    public reportDash() {
        initComponents();
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
        dailyIncome = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        studentDetails = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        lblCourseAttndance = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        admission = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        dailyIncome.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        dailyIncome.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/institute_management/resources/report image/lecturer attendance 1.jpg"))); // NOI18N
        dailyIncome.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        dailyIncome.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        dailyIncome.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dailyIncomeMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                dailyIncomeMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                dailyIncomeMouseExited(evt);
            }
        });
        jPanel1.add(dailyIncome, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 58, 119, 100));

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/institute_management/resources/report image/lecturer payment 1.jpg"))); // NOI18N
        jLabel2.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jLabel2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel2MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel2MouseExited(evt);
            }
        });
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(365, 58, 119, 100));

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/institute_management/resources/report image/student details 1.jpg"))); // NOI18N
        jLabel3.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jLabel3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel3MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel3MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel3MouseExited(evt);
            }
        });
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(365, 277, 119, 110));

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/institute_management/resources/report image/student payment 1.jpg"))); // NOI18N
        jLabel5.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jLabel5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel5MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel5MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel5MouseExited(evt);
            }
        });
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(537, 58, 119, 110));

        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/institute_management/resources/report image/student attendace 1.jpg"))); // NOI18N
        jLabel6.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jLabel6.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel6MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel6MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel6MouseExited(evt);
            }
        });
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(197, 277, 128, 110));

        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/institute_management/resources/report image/lecture_icon.png"))); // NOI18N
        jLabel7.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jLabel7.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel7MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel7MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel7MouseExited(evt);
            }
        });
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(27, 58, 119, -1));

        jLabel4.setFont(new java.awt.Font("Calibri", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 153));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Course Payment Status ");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(27, 31, -1, -1));

        jLabel8.setFont(new java.awt.Font("Calibri", 1, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 0, 153));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("Total Daily Income");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(197, 31, -1, -1));

        jLabel9.setFont(new java.awt.Font("Calibri", 1, 12)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 0, 153));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("Lecturer Monthly Payments");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(365, 31, -1, -1));

        jLabel10.setFont(new java.awt.Font("Calibri", 1, 12)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(0, 0, 153));
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("Student Details");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 243, 119, -1));

        jLabel11.setFont(new java.awt.Font("Calibri", 1, 12)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(0, 0, 153));
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("Student Attendance");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(197, 243, 134, -1));

        jLabel12.setFont(new java.awt.Font("Calibri", 1, 12)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(0, 0, 153));
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("Student Payments");
        jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(537, 31, 136, -1));

        studentDetails.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        studentDetails.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/institute_management/resources/report image/student details 1.jpg"))); // NOI18N
        studentDetails.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        studentDetails.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        studentDetails.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                studentDetailsMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                studentDetailsMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                studentDetailsMouseExited(evt);
            }
        });
        jPanel1.add(studentDetails, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 277, 119, 110));

        jLabel14.setFont(new java.awt.Font("Calibri", 1, 12)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(0, 0, 153));
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setText("Student Count In a Class");
        jPanel1.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(365, 243, 119, -1));

        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/institute_management/resources/report image/student attendace 1.jpg"))); // NOI18N
        jLabel15.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jLabel15.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel15.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel15MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel15MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel15MouseExited(evt);
            }
        });
        jPanel1.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(746, 277, 128, 110));

        jLabel16.setFont(new java.awt.Font("Calibri", 1, 12)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(0, 0, 153));
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel16.setText("Student enrollment in multiple Course ");
        jPanel1.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(718, 243, -1, -1));

        lblCourseAttndance.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCourseAttndance.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/institute_management/resources/report image/student attendace 1.jpg"))); // NOI18N
        lblCourseAttndance.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        lblCourseAttndance.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblCourseAttndance.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblCourseAttndanceMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblCourseAttndanceMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblCourseAttndanceMouseExited(evt);
            }
        });
        jPanel1.add(lblCourseAttndance, new org.netbeans.lib.awtextra.AbsoluteConstraints(549, 277, 128, 112));

        jLabel17.setFont(new java.awt.Font("Calibri", 1, 12)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(0, 0, 153));
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel17.setText("Course Attendance");
        jPanel1.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(543, 243, 134, -1));

        admission.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        admission.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/institute_management/resources/report image/lecturer payment 1.jpg"))); // NOI18N
        admission.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        admission.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        admission.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                admissionMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                admissionMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                admissionMouseExited(evt);
            }
        });
        jPanel1.add(admission, new org.netbeans.lib.awtextra.AbsoluteConstraints(755, 61, 119, 107));

        jLabel19.setFont(new java.awt.Font("Calibri", 1, 12)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(0, 0, 153));
        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel19.setText("Admission Income");
        jPanel1.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(771, 31, -1, -1));

        jButton1.setBackground(new java.awt.Color(102, 255, 102));
        jButton1.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        jButton1.setForeground(new java.awt.Color(0, 0, 204));
        jButton1.setText("Back To Home");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 410, 890, -1));

        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/institute_management/resources/report image/lecturer payment 1.jpg"))); // NOI18N
        jLabel13.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jLabel13.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel13.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel13MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel13MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel13MouseExited(evt);
            }
        });
        jPanel1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 70, 119, 100));

        jLabel18.setFont(new java.awt.Font("Calibri", 1, 12)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(0, 0, 153));
        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel18.setText("Lecturer Monthly Payments");
        jPanel1.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 30, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1157, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 453, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel7MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel7MouseEntered
        jLabel7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 255)));
    }//GEN-LAST:event_jLabel7MouseEntered

    private void jLabel7MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel7MouseExited
      jLabel7.setBorder(null);
    }//GEN-LAST:event_jLabel7MouseExited

    private void dailyIncomeMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dailyIncomeMouseEntered
         dailyIncome.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 255)));
    }//GEN-LAST:event_dailyIncomeMouseEntered

    private void dailyIncomeMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dailyIncomeMouseExited
         dailyIncome.setBorder(null);
    }//GEN-LAST:event_dailyIncomeMouseExited

    private void jLabel2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseEntered
         jLabel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 255)));
    }//GEN-LAST:event_jLabel2MouseEntered

    private void jLabel2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseExited
         jLabel2.setBorder(null);
    }//GEN-LAST:event_jLabel2MouseExited

    private void jLabel3MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseEntered
         jLabel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 255)));
    }//GEN-LAST:event_jLabel3MouseEntered

    private void jLabel3MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseExited
         jLabel3.setBorder(null);
    }//GEN-LAST:event_jLabel3MouseExited

    private void jLabel6MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel6MouseEntered
         jLabel6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 255)));
    }//GEN-LAST:event_jLabel6MouseEntered

    private void jLabel6MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel6MouseExited
         jLabel6.setBorder(null);
    }//GEN-LAST:event_jLabel6MouseExited

    private void jLabel5MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseEntered
         jLabel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 255)));
    }//GEN-LAST:event_jLabel5MouseEntered

    private void jLabel5MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseExited
         jLabel5.setBorder(null);
    }//GEN-LAST:event_jLabel5MouseExited

    private void jLabel7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel7MouseClicked
        //LecturerDetails a = new LecturerDetails();
        coursePaymentStatus a = new coursePaymentStatus();
        a.setVisible(true);
    }//GEN-LAST:event_jLabel7MouseClicked

    private void dailyIncomeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dailyIncomeMouseClicked
        //lecturerAttendance a = new lecturerAttendance();
        daylyIncome a = new daylyIncome();
        a.setVisible(true);
    }//GEN-LAST:event_dailyIncomeMouseClicked

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
        lecturerMonthlyIncome a = new lecturerMonthlyIncome();
        
        a.setVisible(true);
    }//GEN-LAST:event_jLabel2MouseClicked

    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked
       // StudentReport a = new StudentReport();
        totalStudentsInACourse a = new totalStudentsInACourse();
        a.setVisible(true);
    }//GEN-LAST:event_jLabel3MouseClicked

    private void jLabel6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel6MouseClicked
         AttendenaceReport a = new AttendenaceReport();
        a.setVisible(true);
    }//GEN-LAST:event_jLabel6MouseClicked

    private void jLabel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseClicked
        StudentPayments a = new StudentPayments();
        a.setVisible(true);
    }//GEN-LAST:event_jLabel5MouseClicked

    private void studentDetailsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_studentDetailsMouseClicked
      StudentReport a = new StudentReport();
      a.setVisible(true);
    }//GEN-LAST:event_studentDetailsMouseClicked

    private void studentDetailsMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_studentDetailsMouseEntered
      studentDetails.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 255)));
    }//GEN-LAST:event_studentDetailsMouseEntered

    private void studentDetailsMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_studentDetailsMouseExited
         studentDetails.setBorder(null);
    }//GEN-LAST:event_studentDetailsMouseExited

    private void jLabel15MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel15MouseClicked
//        NumberOfCoursesWithStudents a = new NumberOfCoursesWithStudents();
//        a.setVisible(true);
        JOptionPane.showMessageDialog(new JFrame(), "This Report is Not Configured Yet");
    }//GEN-LAST:event_jLabel15MouseClicked

    private void jLabel15MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel15MouseEntered
        jLabel15.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 255)));
    }//GEN-LAST:event_jLabel15MouseEntered

    private void jLabel15MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel15MouseExited
        jLabel15.setBorder(null);
    }//GEN-LAST:event_jLabel15MouseExited

    private void lblCourseAttndanceMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblCourseAttndanceMouseClicked
                CourseAttendenaceReport a = new CourseAttendenaceReport();
                a.setVisible(true);
    }//GEN-LAST:event_lblCourseAttndanceMouseClicked

    private void lblCourseAttndanceMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblCourseAttndanceMouseEntered
       lblCourseAttndance.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 255)));
    }//GEN-LAST:event_lblCourseAttndanceMouseEntered

    private void lblCourseAttndanceMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblCourseAttndanceMouseExited
        lblCourseAttndance.setBorder(null);
    }//GEN-LAST:event_lblCourseAttndanceMouseExited

    private void admissionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_admissionMouseClicked
       admissionReport a = new admissionReport();
       a.setVisible(true);
    }//GEN-LAST:event_admissionMouseClicked

    private void admissionMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_admissionMouseEntered
        admission.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 255)));
    }//GEN-LAST:event_admissionMouseEntered

    private void admissionMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_admissionMouseExited
        admission.setBorder(null);
    }//GEN-LAST:event_admissionMouseExited

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
       this.dispose();;
        NewJFrame a;
        try {
            a = new NewJFrame();
              a.setVisible(true);
        } catch (Exception ex) {
            Logger.getLogger(reportDash.class.getName()).log(Level.SEVERE, null, ex);
        }
      
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jLabel13MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel13MouseClicked
       
    }//GEN-LAST:event_jLabel13MouseClicked

    private void jLabel13MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel13MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel13MouseEntered

    private void jLabel13MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel13MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel13MouseExited

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
            java.util.logging.Logger.getLogger(reportDash.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(reportDash.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(reportDash.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(reportDash.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new reportDash().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel admission;
    private javax.swing.JLabel dailyIncome;
    private javax.swing.JButton jButton1;
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
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblCourseAttndance;
    private javax.swing.JLabel studentDetails;
    // End of variables declaration//GEN-END:variables
}
