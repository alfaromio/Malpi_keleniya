/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.institute_management.report;

import com.institute_management.DataSourse.DataSource;

import com.institute_management.util.CommonMethods;
import com.institute_management.util.Configurations;
import java.awt.Container;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRTextExporter;
import net.sf.jasperreports.engine.export.JRTextExporterParameter;

/**
 *
 * @author Malinda Ranabahu
 */
public class reportDbConnection {

     CommonMethods cm = new CommonMethods();

    public HashMap getStudentDetails(String studentID) throws Exception {
       PreparedStatement stmt=null;
        ResultSet result=null;
        DataSource d=null;
        Connection con=null;
        HashMap map = new HashMap();

        try {
             d = new DataSource();
             con = d.getConnection();
            String query = "SELECT DISTINCT su.*,(select count(course_id) from `student-course` where s_id =? and cardType = 0 ) as freeCards,(select count(course_id) from `student-course` where s_id =? and cardType = 1 ) as HalfFree,(select count(course_id) from `student-course` where s_id =? and cardType = 2 )as normalCards,(select count(course_id) from `student-course` where s_id =? ) as TotalCourses FROM student su,`student-course` stuc WHERE su.s_id = ? and su.s_id = stuc.s_id";

            stmt = con.prepareStatement(query);
            stmt.setString(1, studentID);
            stmt.setString(2, studentID);
            stmt.setString(3, studentID);
            stmt.setString(4, studentID);
            stmt.setString(5, studentID);
            result = stmt.executeQuery();
            while (result.next()) {
                map.put("student_name", result.getString("S_NAME")==null ?"--":  result.getString("S_NAME"));
                map.put("nic", result.getString("S_NIC")==null ?"--":  result.getString("S_NIC"));
                map.put("address", result.getString("S_ADDRESS")==null ?"--":  result.getString("S_ADDRESS"));
                map.put("personal_contact", result.getString("S_TELEPHONE")==null ?"--":  result.getString("S_TELEPHONE"));
                map.put("regDate", result.getString("S_YOR")==null ?"--":  result.getString("S_YOR"));
                map.put("school", result.getString("S_school")==null ?"--":  result.getString("S_school"));
                map.put("parents_mobile", result.getString("S_PARENT_CONTACT_NO")==null ?"--":  result.getString("S_PARENT_CONTACT_NO"));
                map.put("gender", result.getString("S_GENDER")==null ?"--":  result.getString("S_GENDER"));
                map.put("freeCards", result.getString("freeCards")==null ?"--":  result.getString("freeCards"));
                map.put("HalfFree", result.getString("HalfFree")==null ?"--":  result.getString("HalfFree"));
                map.put("NormalCards", result.getString("normalCards")==null ?"--":  result.getString("normalCards"));
                map.put("totalCourses", result.getString("TotalCourses")==null ?"--":  result.getString("TotalCourses"));

            }

           
       } catch (Exception ex) {

            cm.closeConnection(con);
            cm.closeResult(result);
            cm.closeStatements(stmt);
            throw ex;
        } finally {
            cm.closeConnection(con);
            cm.closeResult(result);
            cm.closeStatements(stmt);
             return map;
        }
    }
    
     public double[] getCourseFee(String courseID) throws Exception {
        PreparedStatement stmt=null;
        ResultSet result=null;
        DataSource d=null;
        Connection con=null;
        double array[] = new double[2];
        

        try {
             d = new DataSource();
             con = d.getConnection();
            String query = "select monthly_fee,lecturer_payment_precentage from course where course_id = ?";

            stmt = con.prepareStatement(query);
            stmt.setString(1, courseID);
           
            result = stmt.executeQuery();
            while (result.next()) {
               array[0]=Double.parseDouble(result.getString("monthly_fee"));
               array[1]=Double.parseDouble(result.getString("lecturer_payment_precentage"));

            }

            
         } catch (Exception ex) {

            cm.closeConnection(con);
            cm.closeResult(result);
            cm.closeStatements(stmt);
            throw ex;
        } finally {
            cm.closeConnection(con);
            cm.closeResult(result);
            cm.closeStatements(stmt);
            return array;
        }
    }
     
      public int getLecturerID(String name) throws Exception {
       PreparedStatement stmt=null;
        ResultSet result=null;
        DataSource d=null;
        Connection con=null;
        int lectId=0;
        

        try {
             d = new DataSource();
             con = d.getConnection();
            String query = "select id from lecturer where name = ?";

            stmt = con.prepareStatement(query);
            stmt.setString(1, name);
           
            result = stmt.executeQuery();
            while (result.next()) {
               lectId=Integer.parseInt(result.getString("id"));
            }

            
        } catch (Exception ex) {

            cm.closeConnection(con);
            cm.closeResult(result);
            cm.closeStatements(stmt);
            throw ex;
        } finally {
            cm.closeConnection(con);
            cm.closeResult(result);
            cm.closeStatements(stmt);
            return lectId;
        }
    }
     public double getTotalLEcturerIncome(String courseID,String year, String month) throws Exception {
        PreparedStatement stmt=null;
        ResultSet result=null;
        DataSource d=null;
        Connection con=null;
        double amount;
        double precentage;
        double lecturerIncome=0;
        
        

        try {
             d = new DataSource();
             con = d.getConnection();
            String query = "select IFNULL(sum(ps.amount),0.00) as amount,co.lecturer_payment_precentage from payments ps join course co on co.course_id = ps.course_id  where ps.course_id like ? and ps.payment_issue_year=? and ps.payment_issue_month =?";

            stmt = con.prepareStatement(query);
            stmt.setString(1, "%"+courseID+"%");
            stmt.setString(2, year);
            stmt.setString(3, month);
           
            result = stmt.executeQuery();
            while (result.next()) {
               
                amount = result.getDouble("amount");
                precentage = result.getDouble("lecturer_payment_precentage");
                lecturerIncome = (amount*precentage)/100;
            }

            
        } catch (Exception ex) {

            cm.closeConnection(con);
            cm.closeResult(result);
            cm.closeStatements(stmt);
            throw ex;
        } finally {
            cm.closeConnection(con);
            cm.closeResult(result);
            cm.closeStatements(stmt);
           return lecturerIncome;
        }
    }
     
     
     
     
}
