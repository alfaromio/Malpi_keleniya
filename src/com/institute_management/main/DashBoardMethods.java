/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.institute_management.main;

import com.institute_management.DataSourse.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

/**
 *
 * @author Malinda Ranabahu
 */
public class DashBoardMethods {

    public HashMap<String, Double> getTodayClassListAttendance() throws Exception {
        DataSource d = new DataSource();
        Connection con = d.getConnection();
        HashMap<String, Double> classList = new HashMap<String, Double>();
        PreparedStatement stmt;
        PreparedStatement stmt1 = null;
        ResultSet result;
        ResultSet result1 = null;
        DateFormat dateFormat = new SimpleDateFormat("EEEE");
        Date date = new Date();
        String day = dateFormat.format(date).toLowerCase();

        DateFormat dateFormat2 = new SimpleDateFormat("YYYY-MM-dd");
        String dateattendence2 = dateFormat2.format(new Date());

        try {
            String query = "select cd.course_id as course_id FROM  `courses_dates` cd   WHERE  cd." + day + "<> ''";
            stmt = con.prepareStatement(query);
            result = stmt.executeQuery();
            while (result.next()) {
                String Course_id = result.getString("course_id");

                String query1 = "select count(*) from `attendence` where `attendence`= 1 and `completeDate`= ?  and `c_id`=?";
                stmt1 = con.prepareStatement(query1);
                stmt1.setString(1, dateattendence2);
                stmt1.setString(2, Course_id);

                result1 = stmt1.executeQuery();
                result1.next();
                int current_attendance = result1.getInt("count(*)");

                query1 = "select count(*)as hello from `student-course` where course_id = '" + Course_id + "'";
                stmt1 = con.prepareStatement(query1);
                result1 = stmt1.executeQuery();
                result1.next();
                int total_student = result1.getInt("hello");
                total_student = (total_student == 0 ? 1 : total_student);
                double x = (current_attendance / total_student) * 10;
                classList.put(Course_id, x);

            }

            stmt.close();
            stmt1.close();
            result.close();
            result1.close();
            con.close();

        } catch (Exception ex) {
            System.out.println("Error " + ex);
            throw ex;
        }
        return classList;
    }

    public int getTotalCourses() throws Exception {
        DataSource d = new DataSource();
        Connection con = d.getConnection();
        int totalCourses = 0;
        PreparedStatement stmt;
        ResultSet result;
        try {

            String query = "SELECT count(course_id) as count FROM `course`";
            stmt = con.prepareStatement(query);
            result = stmt.executeQuery();
            while (result.next()) {
                totalCourses = result.getInt("count");
            }
            stmt.close();
            result.close();
            con.close();
        } catch (Exception ex) {
            throw ex;
        }
        return totalCourses;
    }

    public int getTotalLecturers() throws Exception {
        DataSource d = new DataSource();
        Connection con = d.getConnection();
        int totallecturers = 0;
        PreparedStatement stmt;
        ResultSet result;
        try {

            String query = "SELECT count(id) as count FROM `lecturer`";
            stmt = con.prepareStatement(query);
            result = stmt.executeQuery();
            while (result.next()) {
                totallecturers = result.getInt("count");
            }
            stmt.close();
            result.close();
            con.close();
        } catch (Exception ex) {
            throw ex;
        }
        return totallecturers;
    }

    public int getTotalStudents() throws Exception {
        DataSource d = new DataSource();
        Connection con = d.getConnection();
        int totalStudent = 0;
        PreparedStatement stmt;
        ResultSet result;
        try {

            String query = "SELECT count(s_id) as count FROM `student`";
            stmt = con.prepareStatement(query);
            result = stmt.executeQuery();
            while (result.next()) {
                totalStudent = result.getInt("count");
            }
            stmt.close();
            result.close();
            con.close();
        } catch (Exception ex) {
            throw ex;
        }
        return totalStudent;
    }

    public double getTotalIncome() throws Exception {

        DataSource d = new DataSource();
        Connection con = d.getConnection();
        int totalStudent = 0;
        PreparedStatement stmt;
        ResultSet result;
        PreparedStatement stmt1;
        ResultSet result1;
        int paymentcount = 0;
        double amount = 0;
        String tName;
        Calendar c = Calendar.getInstance();

        String thisMonth = c.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.ENGLISH).toLowerCase();
        String curYear = (new SimpleDateFormat("yyyy").format(c.getTime())).toLowerCase();
        try {

            String query = "select amount from payments where month = " + thisMonth + " and year = " + curYear;

            stmt = con.prepareStatement(query);
            result = stmt.executeQuery();
            while (result.next()) {
                amount = amount + result.getInt("amount");

            }
            stmt.close();
            result.close();
            con.close();
        } catch (Exception ex) {
            throw ex;
        }
        return amount;
    }

    public double getCourseFee(String CourseID) throws Exception {
        DataSource d = new DataSource();
        Connection con = d.getConnection();
        double totallecturers = 0;
        PreparedStatement stmt;
        ResultSet result;
        try {

            String query = "SELECT monthly_fee FROM `course` where course_id = '" + CourseID + "'";
            stmt = con.prepareStatement(query);
            result = stmt.executeQuery();
            while (result.next()) {
                totallecturers = result.getInt("monthly_fee");
            }
            stmt.close();
            result.close();
            con.close();
        } catch (Exception ex) {
            throw ex;
        }
        return totallecturers;
    }

//    public void selectTodayClasses(){
//        double totallecturers = 0;
//        PreparedStatement stmt;
//        ResultSet result;
//        try {
//
//            String query = "SELECT course_id FROM `course` where course_id = '" + CourseID + "'";
//            stmt = con.prepareStatement(query);
//            result = stmt.executeQuery();
//            while (result.next()) {
//                totallecturers = result.getInt("monthly_fee");
//            }
//        } catch (Exception ex) {
//            throw ex;
//        }
//    }
}
