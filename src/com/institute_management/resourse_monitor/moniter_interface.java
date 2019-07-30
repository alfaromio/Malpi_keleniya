/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.institute_management.resourse_monitor;

import com.institute_management.DataSourse.DataSource;
import com.institute_management.attendance_new.initial_page;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Timer;

/**
 *
 * @author malindad
 */
public class moniter_interface extends javax.swing.JFrame {

    HashMap< Integer, javax.swing.JLabel> a = new HashMap<Integer, javax.swing.JLabel>();

    int status[] = new int[10];
    int count = 0;
    int max_initial_pool=0;
    int max_busy_connection=0;
   
    DataSource aa;
    Timer SimpleTimer = new Timer(1000, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {

              //  status = aa.getStatus();
            } catch (Exception ex) {
                Logger.getLogger(initial_page.class.getName()).log(Level.SEVERE, null, ex);
            }
            if(status[0]>max_initial_pool){
                max_initial_pool=status[0];
            }
            if(status[3]>max_busy_connection){
                max_busy_connection = status[3];
            }
            initial_pool_size.setText(status[0] + "");
            max_pool_size.setText(status[1] + "");
            busy_connections.setText(status[3] + "");
            free_connections.setText(status[2] + "");
            
            initial_pool_size1.setText(max_initial_pool+"");
            busy_connections1.setText(max_busy_connection+"");
            
            maxPooolSize();
            activeConnections();
            busyConnections();
            maxConnectionsGreenBorder();
            
            count++;
            if (count == 30) {
                maxConnectionsWhite();
                count = 0;

            }

        }
    });

    public moniter_interface() {

        initComponents();
        SimpleTimer.start();
        a.put(1, i);
        a.put(2, ii);
        a.put(3, iii);
        a.put(4, iv);
        a.put(5, v);
        a.put(6, i2);
        a.put(7, ii2);
        a.put(8, iii2);
        a.put(9, iv2);
        a.put(10, v2);
        a.put(11, i3);
        a.put(12, ii3);
        a.put(13, iii3);
        a.put(14, iv3);
        a.put(15, v3);
        a.put(16, i4);
        a.put(17, ii4);
        a.put(18, iii4);
        a.put(19, iv4);
        a.put(20, v4);
        a.put(21, i5);
        a.put(22, ii5);
        a.put(23, iii5);
        a.put(24, iv5);
        a.put(25, v5);
        a.put(26, i6);
        a.put(27, ii6);
        a.put(28, iii6);
        a.put(29, iv6);
        a.put(30, v6);

    }

    public void activeConnections() {
        for (int i = 0; i < status[0]; i++) {
            a.get(i + 1).setOpaque(true);
            a.get(i + 1).setBackground(Color.blue);
        }
    }

    public void busyConnections() {
        for (int i = 0; i < status[3]; i++) {
            a.get(i + 1).setOpaque(true);
            a.get(i + 1).setBackground(Color.red);
        }
    }

    public void maxConnectionsGreenBorder() {
        for (int i = 0; i < count; i++) {
            a.get(i + 1).setOpaque(true);
            a.get(i + 1).setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 204, 0)));
        }
    }

    public void maxConnectionsWhite() {
        for (int i = 0; i < 30; i++) {
            a.get(i + 1).setOpaque(true);
            a.get(i + 1).setBorder(javax.swing.BorderFactory.createLineBorder(Color.WHITE));
        }
    }
    public void resetConnections() {
        for (int i = 0; i < 30; i++) {
            a.get(i + 1).setOpaque(true);
            a.get(i + 1).setBackground(Color.WHITE);
        }
    }
    public void maxPooolSize() {
        for (int i = 0; i <status[1] ; i++) {
            a.get(i + 1).setOpaque(true);
            a.get(i + 1).setBackground(new java.awt.Color(153,255,153));
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        i = new javax.swing.JLabel();
        ii = new javax.swing.JLabel();
        iii = new javax.swing.JLabel();
        iv = new javax.swing.JLabel();
        v = new javax.swing.JLabel();
        i2 = new javax.swing.JLabel();
        ii2 = new javax.swing.JLabel();
        iii2 = new javax.swing.JLabel();
        iv2 = new javax.swing.JLabel();
        v2 = new javax.swing.JLabel();
        iv4 = new javax.swing.JLabel();
        v4 = new javax.swing.JLabel();
        iii4 = new javax.swing.JLabel();
        ii4 = new javax.swing.JLabel();
        i4 = new javax.swing.JLabel();
        v3 = new javax.swing.JLabel();
        iv3 = new javax.swing.JLabel();
        iii3 = new javax.swing.JLabel();
        ii3 = new javax.swing.JLabel();
        i3 = new javax.swing.JLabel();
        i5 = new javax.swing.JLabel();
        iv6 = new javax.swing.JLabel();
        v5 = new javax.swing.JLabel();
        iii5 = new javax.swing.JLabel();
        iv5 = new javax.swing.JLabel();
        v6 = new javax.swing.JLabel();
        iii6 = new javax.swing.JLabel();
        ii6 = new javax.swing.JLabel();
        i6 = new javax.swing.JLabel();
        ii5 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        initial_pool_size = new javax.swing.JLabel();
        max_pool_size = new javax.swing.JLabel();
        busy_connections = new javax.swing.JLabel();
        free_connections = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel37 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        busy_connections1 = new javax.swing.JLabel();
        initial_pool_size1 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        i.setBackground(new java.awt.Color(255, 255, 255));
        i.setOpaque(true);

        ii.setBackground(new java.awt.Color(255, 255, 255));
        ii.setAlignmentY(0.0F);

        iii.setBackground(new java.awt.Color(255, 255, 255));
        iii.setAlignmentY(0.0F);

        iv.setBackground(new java.awt.Color(255, 255, 255));
        iv.setAlignmentY(0.0F);

        v.setBackground(new java.awt.Color(255, 255, 255));
        v.setAlignmentY(0.0F);

        i2.setBackground(new java.awt.Color(255, 255, 255));

        ii2.setBackground(new java.awt.Color(255, 255, 255));
        ii2.setAlignmentY(0.0F);

        iii2.setBackground(new java.awt.Color(255, 255, 255));
        iii2.setAlignmentY(0.0F);

        iv2.setBackground(new java.awt.Color(255, 255, 255));
        iv2.setAlignmentY(0.0F);

        v2.setBackground(new java.awt.Color(255, 255, 255));
        v2.setAlignmentY(0.0F);

        iv4.setBackground(new java.awt.Color(255, 255, 255));
        iv4.setAlignmentY(0.0F);

        v4.setBackground(new java.awt.Color(255, 255, 255));
        v4.setAlignmentY(0.0F);

        iii4.setBackground(new java.awt.Color(255, 255, 255));
        iii4.setAlignmentY(0.0F);

        ii4.setBackground(new java.awt.Color(255, 255, 255));
        ii4.setAlignmentY(0.0F);

        i4.setBackground(new java.awt.Color(255, 255, 255));

        v3.setBackground(new java.awt.Color(255, 255, 255));
        v3.setAlignmentY(0.0F);

        iv3.setBackground(new java.awt.Color(255, 255, 255));
        iv3.setAlignmentY(0.0F);

        iii3.setBackground(new java.awt.Color(255, 255, 255));
        iii3.setAlignmentY(0.0F);

        ii3.setBackground(new java.awt.Color(255, 255, 255));
        ii3.setAlignmentY(0.0F);

        i3.setBackground(new java.awt.Color(255, 255, 255));

        i5.setBackground(new java.awt.Color(255, 255, 255));

        iv6.setBackground(new java.awt.Color(255, 255, 255));
        iv6.setAlignmentY(0.0F);

        v5.setBackground(new java.awt.Color(255, 255, 255));
        v5.setAlignmentY(0.0F);

        iii5.setBackground(new java.awt.Color(255, 255, 255));
        iii5.setAlignmentY(0.0F);

        iv5.setBackground(new java.awt.Color(255, 255, 255));
        iv5.setAlignmentY(0.0F);

        v6.setBackground(new java.awt.Color(255, 255, 255));
        v6.setAlignmentY(0.0F);

        iii6.setBackground(new java.awt.Color(255, 255, 255));
        iii6.setAlignmentY(0.0F);

        ii6.setBackground(new java.awt.Color(255, 255, 255));
        ii6.setAlignmentY(0.0F);

        i6.setBackground(new java.awt.Color(255, 255, 255));

        ii5.setBackground(new java.awt.Color(255, 255, 255));
        ii5.setAlignmentY(0.0F);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(i, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ii, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(iii, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(iv, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(v, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(i2, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ii2, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(iii2, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(iv2, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(v2, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(i3, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ii3, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(iii3, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(iv3, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(v3, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(i4, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ii4, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(iii4, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(iv4, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(v4, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(i5, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ii5, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(iii5, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(iv5, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(v5, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(i6, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ii6, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(iii6, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(iv6, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(v6, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(i, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ii, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(iii, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(iv, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(v, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(i2, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ii2, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(iii2, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(iv2, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(v2, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(i3, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ii3, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(iii3, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(iv3, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(v3, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(i4, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ii4, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(iii4, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(iv4, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(v4, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(i5, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ii5, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(iii5, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(iv5, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(v5, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(i6, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ii6, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(iii6, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(iv6, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(v6, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(2, 2, 2))
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setForeground(new java.awt.Color(51, 51, 255));

        jLabel32.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel32.setText("IMS Status Monitor");

        jLabel33.setText("Initial Pool Size");

        jLabel34.setText("Max Pool Size");

        jLabel35.setText("Busy Connection Count");

        jLabel36.setText("Free Connection Count");

        jButton1.setBackground(new java.awt.Color(255, 51, 51));
        jButton1.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Rest All Connections");
        jButton1.setBorder(null);
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel37.setText("Inital Pool Size");

        jLabel38.setText("Busy Connection Count");

        jLabel40.setText("Current Status");

        jLabel41.setText("Maximum resourse Used");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel32, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel33, javax.swing.GroupLayout.DEFAULT_SIZE, 112, Short.MAX_VALUE)
                        .addComponent(jLabel34, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jLabel35, javax.swing.GroupLayout.DEFAULT_SIZE, 137, Short.MAX_VALUE)
                    .addComponent(jLabel36, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(busy_connections, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(max_pool_size, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(initial_pool_size, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(free_connections, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(200, 200, 200)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel37, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel38, javax.swing.GroupLayout.DEFAULT_SIZE, 132, Short.MAX_VALUE))
                .addGap(35, 35, 35)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(initial_pool_size1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(busy_connections1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(43, 43, 43))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(75, 75, 75)
                .addComponent(jLabel40, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel41)
                .addGap(89, 89, 89))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel32)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 39, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel40)
                    .addComponent(jLabel41, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel33, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(initial_pool_size, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel34, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(max_pool_size, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(busy_connections, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel35)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel37, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(initial_pool_size1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(busy_connections1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel38))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel36, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(free_connections, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(69, 69, 69)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
//            aa.resetConnection();
            resetConnections();
            maxConnectionsWhite();
            count=0;
        } catch (Exception ex) {

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
            java.util.logging.Logger.getLogger(moniter_interface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(moniter_interface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(moniter_interface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(moniter_interface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new moniter_interface().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel busy_connections;
    private javax.swing.JLabel busy_connections1;
    private javax.swing.JLabel free_connections;
    private javax.swing.JLabel i;
    private javax.swing.JLabel i2;
    private javax.swing.JLabel i3;
    private javax.swing.JLabel i4;
    private javax.swing.JLabel i5;
    private javax.swing.JLabel i6;
    private javax.swing.JLabel ii;
    private javax.swing.JLabel ii2;
    private javax.swing.JLabel ii3;
    private javax.swing.JLabel ii4;
    private javax.swing.JLabel ii5;
    private javax.swing.JLabel ii6;
    private javax.swing.JLabel iii;
    private javax.swing.JLabel iii2;
    private javax.swing.JLabel iii3;
    private javax.swing.JLabel iii4;
    private javax.swing.JLabel iii5;
    private javax.swing.JLabel iii6;
    private javax.swing.JLabel initial_pool_size;
    private javax.swing.JLabel initial_pool_size1;
    private javax.swing.JLabel iv;
    private javax.swing.JLabel iv2;
    private javax.swing.JLabel iv3;
    private javax.swing.JLabel iv4;
    private javax.swing.JLabel iv5;
    private javax.swing.JLabel iv6;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel max_pool_size;
    private javax.swing.JLabel v;
    private javax.swing.JLabel v2;
    private javax.swing.JLabel v3;
    private javax.swing.JLabel v4;
    private javax.swing.JLabel v5;
    private javax.swing.JLabel v6;
    // End of variables declaration//GEN-END:variables
}
