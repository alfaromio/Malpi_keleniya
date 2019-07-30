/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.institute_management.payment_mgt;

import com.institute_management.DataSourse.DataSource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author Malinda Ranabahu
 */
public class freeCardPaymentHandle {

    

    public ArrayList<freeCArdHoldersBean> getAllFreeCardHolders() throws Exception {
        PreparedStatement stmt;
        ResultSet result;
        int success = 0;
        ArrayList<freeCArdHoldersBean> details = new ArrayList<freeCArdHoldersBean>();
        try {
            DataSource d = new DataSource();
            Connection con = d.getConnection();
            String query = "Select * from `student-course` where status = ? and cardType = ?";
            stmt = con.prepareStatement(query);
            stmt.setString(1, "ACT");
            stmt.setInt(2, 0);
            result = stmt.executeQuery();
            while (result.next()) {
                freeCArdHoldersBean a = new freeCArdHoldersBean();
                a.setCourseId(result.getString("course_id"));
                a.setStudentId(result.getInt("S_ID"));
                details.add(a);
            }
        } catch (Exception ex) {
            throw ex;
        }
        return details;
    }
    
    
    public ArrayList<freeCArdHoldersBean> checkFreeCardInsertStatus(freeCArdHoldersBean freeCardbean) throws Exception {
        PreparedStatement stmt;
        ResultSet result;
        boolean isInsert = false;
        ArrayList<freeCArdHoldersBean> details = new ArrayList<freeCArdHoldersBean>();
        Calendar cal = Calendar.getInstance();
        String month = new SimpleDateFormat("MMMM").format(cal.getTime()).toLowerCase();
        String year = new SimpleDateFormat("YYYY").format(cal.getTime());
        SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd");
        String payDate = sdf.format(new Date());
        
        try {
            DataSource d = new DataSource();
            Connection con = d.getConnection();
            String c_id = freeCardbean.getCourseId();
            int s_id = freeCardbean.getStudentId();
            String query = "Select * from payments where course_id = ? and s_id=? and year=? and month = ?";
            stmt = con.prepareStatement(query);
            stmt.setString(1, freeCardbean.getCourseId());
            stmt.setInt(2, freeCardbean.getStudentId());
            stmt.setString(3, year);
            stmt.setString(4, month);
            
            result = stmt.executeQuery();
            while (result.next()) {
                isInsert = true;
            }
            if(!isInsert){
                query = "INSERT INTO `payments` "
                        + "(`s_id`, `course_id`, `year`, `month`, `date`, `amount`, "
                        + "`paid_to_lecture`, `payment_issue_date`, `payment_issue_month`, `payment_issue_year`) "
                        + "VALUES (?,?,?,?,?,?,?,?,?,?)";
            stmt = con.prepareStatement(query);
            stmt.setString(1,freeCardbean.getStudentId()+"");
            stmt.setString(2,freeCardbean.getCourseId());
            stmt.setString(3,year);
            stmt.setString(4,month);
            stmt.setString(5,payDate);
            stmt.setDouble(6,0.00);
            stmt.setInt(7,1);
            stmt.setString(8,payDate);
            stmt.setString(9,month);
            stmt.setString(10,year);
            }
        } catch (Exception ex) {
            throw ex;
        }
        return details;
    }
    
    
}
