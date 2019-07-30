/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.institute_management.course_mgt.course_stop;

import com.institute_management.DataSourse.DataSource;
import com.institute_management.util.CommonMethods;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

/**
 *
 * @author malindad
 */
public class C_StopDBClass {

    CommonMethods cm = new CommonMethods();

    public HashMap<Integer, Object[]> getStopEligibleClasses() throws Exception {
        HashMap<Integer, Object[]> classStop = new HashMap<Integer, Object[]>();
        ArrayList<String[]> classes = new ArrayList<String[]>();
        PreparedStatement stmt = null;
        ResultSet result = null;
        DataSource d = null;
        Connection con = null;
        DateFormat dateFormat = new SimpleDateFormat("EEEE");
        Date date = new Date();
        try {
            d = new DataSource();
            con = d.getConnection();
            String day = dateFormat.format(date).toLowerCase();
            String query = "select cd.course_id as course_id,cd." + day + " FROM  `courses_dates` cd WHERE  cd." + day + "<> '' "
                    + "and cd.course_id not in (select course_id from class_conduct_details where date = ?) ";
            stmt = con.prepareStatement(query);
            stmt.setString(1, new SimpleDateFormat("YYYY-MM-dd").format(new Date()));
            result = stmt.executeQuery();
            while (result.next()) {
                String[] classArray = new String[4];
                // if there is only one class. those value should return. otherwise should re calculate;
                classArray[0] = result.getString("course_id");
                System.out.println(result.getString("course_id"));
                classArray[1] = result.getString(day);
                classArray[3] = "Normal Class";// class type
                classes.add(classArray);
            }
            // get Extra class details;
            query = "SELECT courseID,starttime,endtime FROM `extraclasses` WHERE STR_TO_DATE(date, '%m/%d/%Y')=CURDATE() and "
                    + " courseID not in (select course_id from class_conduct_details where date = ?)";
            stmt = con.prepareStatement(query);
            stmt.setString(1, new SimpleDateFormat("YYYY-MM-dd").format(new Date()));
            result = stmt.executeQuery();
            while (result.next()) {
                String[] classArray = new String[4];
                classArray[0] = result.getString("courseid");
                classArray[1] = result.getString("starttime") + "-" + result.getString("endtime");
                classArray[3] = "Extra Class";
                classes.add(classArray);
            }

            long dif = 0;
            int count = 0;

            for (int i = 0; i < classes.size(); i++) {
                String course_id = classes.get(i)[0];
                String c_time = classes.get(i)[1].split("-")[0];

                SimpleDateFormat time_format = new SimpleDateFormat("HH:mm");
                Date date1 = time_format.parse(c_time);
                Date date2 = time_format.parse(time_format.format(new Date()));
                long current_dif = (date1.getTime() - date2.getTime()) / 60000;
                if (current_dif <= 30) {
                    String[] aa = classes.get(i);
                    aa[2] = time_format.format(new Date());
                    classStop.put(count, aa);
                    count++;
                } else {

                }
            }

        } catch (Exception ex) {

            cm.closeConnection(con);
            cm.closeResult(result);
            cm.closeStatements(stmt);
            //------------------------log Start--------------------------------------------
            //  logger.error(CourseDbConnection.class + ".SearchLecturer(" + id + "," + name + "," + nic + "," + mob + ") ->error" + ex);
            //-------------------------log End---------------------------------------------
            throw ex;
        } finally {
            cm.closeConnection(con);
            cm.closeResult(result);
            cm.closeStatements(stmt);
            return classStop;
        }

    }

    public int Insert_class_conduct_details(String courseID, String end_time,Connection con) throws Exception {
        PreparedStatement stmt = null;
        int result = 0;

        try {
            
            con.setAutoCommit(false);
            String query = "Insert into class_conduct_details (`course_id`,`start_time`,`end_time`,`date`) values (?,?,?,?)";
            stmt = con.prepareStatement(query);
            stmt.setString(1, courseID);
            stmt.setString(2, "");
            stmt.setString(3, end_time);
            stmt.setString(4, new SimpleDateFormat("YYYY-MM-dd").format(new Date()));
            result = stmt.executeUpdate();
        } catch (Exception ex) {
            cm.closeStatements(stmt);
            //------------------------log Start--------------------------------------------
            //logger.error(CourseDbConnection.class + ".insertClassDays(" + beanMap + "," + CourseId + ") ->error" + ex);
            //-------------------------log End---------------------------------------------
            throw ex;
        } finally {
            cm.closeStatements(stmt);
            return result;
        }
    }
    
    public boolean create_AbsenceRecords(String selectedCourseId,Connection con) throws Exception {
        int count = 0;
        PreparedStatement pst = null;
        PreparedStatement pst2 = null;
        ResultSet rs = null;
        DataSource d = null;
        String query = null;
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-YYYY-HH:mm:ss");
        DateFormat dateFormat1 = new SimpleDateFormat("EEEE");
        DateFormat dateFormat2 = new SimpleDateFormat("YYYY-MM-dd");
        String dateattendence = dateFormat.format(new Date());
        String dateattendence1 = dateFormat1.format(new Date()).toLowerCase();
        String dateattendence2 = dateFormat2.format(new Date());
        System.out.println(dateattendence1);
        String query1, query2;
        String[] s = dateattendence.split("\\-");
        System.out.println(dateattendence2);
        boolean output = false;
        query1 = "select sc.s_id from `student-course` sc where sc.s_id not in (select s_id from `attendence` where `completeDate`= ? and `c_id`=?) and sc.course_id =?";
        
        query2 = "INSERT INTO `attendence`(`s_id`, `c_id`, `year`, `month`, `date`, `day`, `time`, `attendence`,`completeDate`) VALUES (?,?,?,?,?,?,?,?,?)";

        try {
            d = new DataSource();
            pst = con.prepareStatement(query1);

            pst.setString(1, dateattendence2);
            pst.setString(2, selectedCourseId);
            pst.setString(3, selectedCourseId);

            rs = pst.executeQuery();
            while(rs.next()){
                String s_id = rs.getString("s_id");
                pst2 = con.prepareStatement(query2);
                pst2.setString(1, s_id);
                pst2.setString(2, selectedCourseId);
                pst2.setString(3, s[2]);
                pst2.setString(4, s[1]);
                pst2.setString(5, s[0]);
                pst2.setString(6, dateattendence1);
                pst2.setString(7, s[3]);
                pst2.setInt(8, 0);
                pst2.setString(9, dateattendence2);
                count = pst2.executeUpdate();
            } 
            output = true;
        } catch (Exception ex) {
            cm.closeResult(rs);
            cm.closeStatements(pst);
            //------------------------log Start--------------------------------------------
            //logger.error(SubjectDbConnection.class + ".markAttendence(" + selectedCourseId + "," + selectedCourseId + "," + sid + "," + date + "," + i + ") ->error" + ex);
            //-------------------------log End---------------------------------------------
            throw ex;
        } finally {
            cm.closeResult(rs);
            cm.closeStatements(pst);
            return output;
        }

    }

}
