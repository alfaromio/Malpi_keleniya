/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.institute_management.payment_mgt;

import com.institute_management.DataSourse.DataSource;

import com.institute_management.course_mgt.CourseDbConnection;
import static com.institute_management.user_mgt.UI.NewLogin2.logger;
import com.institute_management.util.CommonMethods;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author acer
 */
public class PaymentDbConnection {

    
    CommonMethods cm = new CommonMethods();

  

    public HashMap getLastTwoPaymentStatus(String stuID, String courseID) throws SQLException, Exception {
        PreparedStatement stmt=null;
        ResultSet result=null;
        DataSource d=null;
        Connection con=null;
        HashMap<String, String> paymentDetails = new HashMap<String, String>();
        try {
             d = new DataSource();
             con = d.getConnection();
            Date date = new Date();
            String year = Integer.toString(Calendar.getInstance().get(Calendar.YEAR));
            String tableName = "payments_" + year + "_" + courseID;

            String query = "select * from `" + tableName + "` where student_id = ?";
            stmt = con.prepareStatement(query);
            stmt.setString(1, stuID);
            String months[] = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
            result = stmt.executeQuery();
            while (result.next()) {
                String currentMonth;
                String payment;
                for (int i = 11; i >= 0; i--) {
                    currentMonth = months[i];
                    payment = result.getString(currentMonth);
                    if (payment.equals("YES")) {
                        paymentDetails.put(currentMonth, "YES");
                        if (i > 0) {
                            paymentDetails.put(months[i - 1], result.getString(months[i - 1]));
                        } else {
                            paymentDetails.put("--", "--");
                        }
                        break;
                    }
                }
            }
           
        } catch (Exception ex) {

            cm.closeConnection(con);
            cm.closeResult(result);
            cm.closeStatements(stmt);
            //------------------------log Start--------------------------------------------
            logger.error(PaymentDbConnection.class+".insertLecPayment("+stuID+","+courseID+") ->error"+ex);        
            //-------------------------log End---------------------------------------------
            throw ex;
        } finally {
            cm.closeConnection(con);
            cm.closeResult(result);
            cm.closeStatements(stmt);
             return paymentDetails;
        }
    }

    public int makeBatchPayment(String courseID, String studentID, ArrayList<String> a) throws Exception {
        Date date = new Date();
        String year = Integer.toString(Calendar.getInstance().get(Calendar.YEAR));
        String tableName = "payments_" + year + "_" + courseID;
        PreparedStatement stmt=null;
        ResultSet result=null;
        DataSource d=null;
        Connection con=null;
        int success=0;

        try {
             d = new DataSource();
             con = d.getConnection();
            String query = "select * from `" + tableName + "` where student_id = ?";
            stmt = con.prepareStatement(query);
            stmt.setString(1, studentID);
            result = stmt.executeQuery();
            if (result.next()) {
                String updateQuery = "";
                for (int i = 0; i < a.size(); i++) {
                    updateQuery = updateQuery + a.get(i) + " = 'YES' ,";
                }
                //remove last ,
                updateQuery = updateQuery.substring(0, updateQuery.length() - 1);
                query = "update `" + tableName + "` set " + updateQuery + " where student_id = ?";
                stmt = con.prepareStatement(query);
                stmt.setString(1, studentID);
                success = stmt.executeUpdate();
            } else {
                String field = "student_id ,";
                String value = studentID + " ,";
                for (int i = 0; i < a.size(); i++) {
                    field = field + a.get(i) + " ,";
                    value = value + "'YES' ,";
                }
                field = field.substring(0, field.length() - 1);
                value = value.substring(0, value.length() - 1);

                query = "Insert into `" + tableName + "` ( " + field + " ) values ( " + value + " )";
                stmt = con.prepareStatement(query);
                success = stmt.executeUpdate();
            }
            
         } catch (Exception ex) {

            cm.closeConnection(con);
            cm.closeResult(result);
            cm.closeStatements(stmt);
            //------------------------log Start--------------------------------------------
            logger.error(PaymentDbConnection.class+".makeBatchPayment("+courseID+","+studentID+","+a+") ->error"+ex);        
            //-------------------------log End---------------------------------------------
            throw ex;
        } finally {
            cm.closeConnection(con);
            cm.closeResult(result);
            cm.closeStatements(stmt);
            return success;
        }
    }

    public int checkPaymentYear(String TableName) throws Exception {
       PreparedStatement stmt=null;
        ResultSet result=null;
        DataSource d=null;
        Connection con=null;
        int success = 0;
        try {
             d = new DataSource();
             con = d.getConnection();
            String query = "SELECT count(*) as count FROM information_schema.TABLES WHERE "
                    + "(TABLE_SCHEMA = 'institute_management') AND (TABLE_NAME = '"+TableName+"')";
            stmt = con.prepareStatement(query);
            
            result = stmt.executeQuery();
            while(result.next()){
                success = result.getInt("count");
            }
            
            
       } catch (Exception ex) {

            cm.closeConnection(con);
            cm.closeResult(result);
            cm.closeStatements(stmt);
            //------------------------log Start--------------------------------------------
            logger.error(PaymentDbConnection.class+".checkPaymentYear("+TableName+") ->error"+ex);        
            //-------------------------log End---------------------------------------------
            throw ex;
        } finally {
            cm.closeConnection(con);
            cm.closeResult(result);
            cm.closeStatements(stmt);
            return success;
        }
        
    }
}
