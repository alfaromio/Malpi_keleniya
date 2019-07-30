/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.institute_management.report;

import com.institute_management.DataSourse.DataSource;

import java.awt.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.HashMap;
import javax.swing.JFrame;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.swing.JRViewer;

/**
 *
 * @author tharindu_m
 */
public class reportGen extends JFrame {

    public reportGen(String filename, HashMap parameters) throws Exception {

        
        try {
            DataSource d = new DataSource();
            Connection conn = d.getConnection();
            JasperPrint print = JasperFillManager.fillReport(filename, parameters, conn);
            JRViewer viewer = new JRViewer(print);
            Container c = getContentPane();
            c.add(viewer);
            
            setBounds(10, 10, 1300, 600);
            setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    public void save(String filename, HashMap parameters) {
        
        try {
            DataSource d = new DataSource();
            Connection conn = d.getConnection();
            JasperPrint print = JasperFillManager.fillReport(filename, parameters, conn);
            OutputStream output = null;
            String path = "D:\\EOD\\";
            path = path + "hello.pdf";
            File outFile = new File(path);

            outFile.getParentFile().mkdirs();
            outFile.createNewFile();
            output = new FileOutputStream(new File(path));

            JasperExportManager.exportReportToPdfStream(print, output);
        } catch (Exception ex) {

        }
    }

    public static void main(String args[]) throws Exception {
//        HashMap param = new HashMap();
//        reportGen rg = new reportGen("G:\\new version\\Institute_mgt_System\\src\\com\\institute_management\\report\\AttendanceReport.jasper", param);
//        rg.setVisible(true);
//        
//        rg.save("G:\\new version\\Institute_mgt_System\\src\\com\\institute_management\\report\\report2.jasper", param);
    }
}
