/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.institute_management.DeativateCourses;

import com.institute_management.block.*;
import com.institute_management.DataSourse.DataSource;
import com.institute_management.configurations.Config;
import com.institute_management.student.BEAN.Student;
import static com.institute_management.user_mgt.UI.NewLogin2.logger;
import com.institute_management.util.CommonMethods;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author malindad
 */
public class DactDbConnection {

    Config config = new Config();
    CommonMethods cm = new CommonMethods();

    public HashMap<Integer, Object[]> selectStudentsInACourse(String Course_id) {

        HashMap<Integer, Object[]> tableData = new HashMap<Integer, Object[]>();
        PreparedStatement pst = null;
        ResultSet rs = null;
        DataSource d = null;
        Connection connection = null;
        String query = null;
        try {
            d = new DataSource();
            connection = d.getConnection();
            query = "select * from student where S_ID in (select s_id from `student-course` where course_id = '" + Course_id + "')";

            pst = connection.prepareStatement(query);

            rs = pst.executeQuery();
            int count = 1;
            while (rs.next()) {
                Object[] rowData = new Object[6];

                rowData[0] = rs.getString("s_id");
                rowData[1] = rs.getString("s_name");
                rowData[2] = (boolean) false;
                tableData.put(count, rowData);
                count++;
            }

        } catch (Exception ex) {

            cm.closeConnection(connection);
            cm.closeResult(rs);
            cm.closeStatements(pst);
            //------------------------log Start--------------------------------------------
            logger.error(DactDbConnection.class + ".selectAllStudent() ->error" + ex);
            //-------------------------log End---------------------------------------------
            throw ex;
        } finally {
            cm.closeConnection(connection);
            cm.closeResult(rs);
            cm.closeStatements(pst);
            return tableData;
        }

    }

    public boolean InsertStudentsIntoNewCourse(String NewCourse_id, String OldCouse_id, ArrayList<String> sList, String regDate) {

        boolean isProcessSuccess = false;
        PreparedStatement pst = null;
        PreparedStatement pst1 = null;
        ResultSet rs = null;
        ResultSet rs1 = null;
        DataSource d = null;
        Connection connection = null;
        String query = null;
        String query1 = null;
        try {
            d = new DataSource();
            connection = d.getConnection();
            connection.setAutoCommit(false);
            query = "Select * from `student-course` where course_id = '" + OldCouse_id + "'";

            pst = connection.prepareStatement(query);

            rs = pst.executeQuery();
            int count = 0;
            while (rs.next()) {
                query1 = "INSERT INTO `student-course`(`S_ID`, `course_id`, `registation_date`, `cardType`, `status`, `Online_Sync`) VALUES "
                        + "(?,?,?,?,?,?) ";
                pst1 = connection.prepareStatement(query1);
                pst1.setString(1, rs.getString("s_id"));
                pst1.setString(2, NewCourse_id);
                pst1.setString(3, regDate);
                pst1.setString(4, rs.getString("cardType"));
                pst1.setString(5, rs.getString("status"));
                pst1.setString(6, rs.getString("Online_Sync"));
                pst1.executeUpdate();
                count++;
            }

            if (getStudentCountInaGivenCourse(OldCouse_id) == count) {
                query = "update course set status = 'DACT' where course_id = '" + OldCouse_id + "'";
                pst = connection.prepareStatement(query);
                int x = pst.executeUpdate();
                if (x == 1) {
                    connection.commit();
                    isProcessSuccess = true;
                    JOptionPane.showMessageDialog(new JFrame(), count + " number of students copy to new course..");
                }else{
                    JOptionPane.showMessageDialog(new JFrame(),"error occured when deactivating old Course..");
                }

            } else {
                connection.rollback();
                JOptionPane.showMessageDialog(new JFrame(), "recorde mismatch..Operation terminated");
            }

        } catch (Exception ex) {

            cm.closeConnection(connection);
            cm.closeResult(rs);
            
            cm.closeStatements(pst);
            cm.closeStatements(pst1);
            //------------------------log Start--------------------------------------------
            logger.error(DactDbConnection.class + ".selectAllStudent() ->error" + ex);
            //-------------------------log End---------------------------------------------
            throw ex;
        } finally {
            cm.closeConnection(connection);
            cm.closeResult(rs);
           
            cm.closeStatements(pst);
            cm.closeStatements(pst1);
            return isProcessSuccess;
        }

    }

    public int getStudentCountInaGivenCourse(String Course_id) {

        int rec_count = 0;
        PreparedStatement pst = null;
        ResultSet rs = null;
        DataSource d = null;
        Connection connection = null;
        String query = null;
        try {
            d = new DataSource();
            connection = d.getConnection();
            query = "select count(*) rec_count from `student-course` where course_id = '" + Course_id + "'";

            pst = connection.prepareStatement(query);

            rs = pst.executeQuery();
            while (rs.next()) {
                rec_count = rs.getInt("rec_count");
            }

        } catch (Exception ex) {

            cm.closeConnection(connection);
            cm.closeResult(rs);
            cm.closeStatements(pst);
            //------------------------log Start--------------------------------------------
            logger.error(DactDbConnection.class + ".selectAllStudent() ->error" + ex);
            //-------------------------log End---------------------------------------------
            throw ex;
        } finally {
            cm.closeConnection(connection);
            cm.closeResult(rs);
            cm.closeStatements(pst);
            return rec_count;
        }

    }
    
    
    

}
