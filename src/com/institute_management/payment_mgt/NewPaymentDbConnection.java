/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.institute_management.payment_mgt;

import com.institute_management.DataSourse.DataSource;
import static com.institute_management.user_mgt.UI.NewLogin2.logger;

import com.institute_management.util.CommonMethods;
import java.awt.Component;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author tharindu_m
 */
public class NewPaymentDbConnection {

    CommonMethods cm = new CommonMethods();

    public HashMap<String, Integer> makePayment(paymentBean pb) throws Exception {
        PreparedStatement stmt = null;
        ResultSet result = null;
        DataSource d = null;
        Connection con = null;
        HashMap<String, Integer> success = new HashMap<String, Integer>();
        String s_id = pb.getS_id();
        String courseId = pb.getCourseID();
        String date = pb.getDate();
        ArrayList<String> months = pb.getMonth();
        String year = pb.getYear();
        double amount = pb.getAmount();
        SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd");
        String payDate = sdf.format(new Date());

        Calendar cal = Calendar.getInstance();
        String payment_res_month = new SimpleDateFormat("MMMM").format(cal.getTime());
        String payment_res_year = new SimpleDateFormat("YYYY").format(cal.getTime());
        if (pb.getCardType() == 0) {
            amount = 0.00;
        } else if (pb.getCardType() == 1) {
            amount = amount / 2;
        }
        int x;
        try {
             d = new DataSource();
             con = d.getConnection();
            for (int i = 0; i < months.size(); i++) {

                boolean isInsert = false;
                String query = "Select * from payments where course_id = ? and s_id=? and year=? and month = ?";
                stmt = con.prepareStatement(query);
                stmt.setString(1, courseId);
                stmt.setString(2, s_id);
                stmt.setString(3, year);
                stmt.setString(4, months.get(i));

                result = stmt.executeQuery();
                while (result.next()) {
                    isInsert = true;
                }
                if (!isInsert) {
                    query = "INSERT INTO `payments`( `year`, `month`, `date`, `course_id`, `s_id`, `amount`,`payment_issue_date`,`payment_issue_month`,`payment_issue_year`) VALUES (?,?,?,?,?,?,?,?,?)";

                    stmt = con.prepareStatement(query);
                    stmt.setString(1, year);
                    stmt.setString(2, months.get(i));
                    stmt.setString(3, date);
                    stmt.setString(4, courseId);
                    stmt.setString(5, s_id);
                    stmt.setDouble(6, amount);
                    stmt.setString(7, payDate);
                    stmt.setString(8, payment_res_month.toLowerCase());
                    stmt.setString(9, payment_res_year);
                    x = stmt.executeUpdate();
                    success.put(months.get(i), x);
                } else {
                    JOptionPane.showMessageDialog(new JFrame(), "Already Paid For " + months.get(i) + " of " + year);
                }
            }

        } catch (Exception ex) {

            cm.closeConnection(con);
            cm.closeResult(result);
            cm.closeStatements(stmt);
            //------------------------log Start--------------------------------------------
            logger.error(NewPaymentDbConnection.class+".makePayment("+pb+") ->error"+ex);        
            //-------------------------log End---------------------------------------------
            throw ex;
        } finally {
            cm.closeConnection(con);
            cm.closeResult(result);
            cm.closeStatements(stmt);
            return success;
        }
    }

    public int okcancel(String theMessage) {
        int result = JOptionPane.showConfirmDialog((Component) null, theMessage,
                "alert", JOptionPane.OK_CANCEL_OPTION);
        return result;
    }

    public int getCourseFee(String courseId) throws Exception {
        PreparedStatement stmt = null;
        ResultSet result = null;
        DataSource d = null;
        Connection con = null;
        int x = 0;
        try {
             d = new DataSource();
             con = d.getConnection();
            String query = "select monthly_fee from course where course_id = ?";

            stmt = con.prepareStatement(query);
            stmt.setString(1, courseId);

            result = stmt.executeQuery();
            while (result.next()) {
                x = result.getInt("monthly_fee");
            }

        } catch (Exception ex) {

            cm.closeConnection(con);
            cm.closeResult(result);
            cm.closeStatements(stmt);
            //------------------------log Start--------------------------------------------
            logger.error(NewPaymentDbConnection.class+".getCourseFee("+courseId+") ->error"+ex);        
            //-------------------------log End---------------------------------------------
            throw ex;
        } finally {
            cm.closeConnection(con);
            cm.closeResult(result);
            cm.closeStatements(stmt);
            return x;
        }

    }

    public LinkedHashMap<String, String> getPaymentDetails(String courseId, String studntID, int noOfMonths) throws Exception {
        PreparedStatement stmt = null;
        ResultSet result = null;
        DataSource d = null;
        Connection con = null;
        ArrayList<String> paymentDetails = new ArrayList<String>();
        LinkedHashMap<String, String> paymentMap = new LinkedHashMap<String, String>();
        int x = 0;
        try {
             d = new DataSource();
             con = d.getConnection();
            String query = "select month from payments where course_id = ? and s_id=? order by id LIMIT 11";

            stmt = con.prepareStatement(query);
            stmt.setString(1, courseId);
            stmt.setString(2, studntID);

            result = stmt.executeQuery();
            while (result.next()) {
                paymentDetails.add(result.getString("month"));
            }
            if (noOfMonths > 11) {
                noOfMonths = paymentDetails.size();
            }

            for (int i = 0; i < noOfMonths; i++) {
                Calendar cal = Calendar.getInstance();
                cal.add(Calendar.MONTH, -i);
                String Month = new SimpleDateFormat("MMMM").format(cal.getTime()).toLowerCase();
                if (paymentDetails.contains(Month)) {
                    paymentMap.put(Month, "Payment Completed");
                } else {
                    paymentMap.put(Month, "Payment not Completed");
                }
            }

        } catch (Exception ex) {

            cm.closeConnection(con);
            cm.closeResult(result);
            cm.closeStatements(stmt);
            //------------------------log Start--------------------------------------------
            logger.error(NewPaymentDbConnection.class+".getPaymentDetails("+courseId+","+studntID+","+noOfMonths+") ->error"+ex);        
            //-------------------------log End---------------------------------------------
            throw ex;
        } finally {
            cm.closeConnection(con);
            cm.closeResult(result);
            cm.closeStatements(stmt);
            return paymentMap;
        }

    }

    public Boolean getPaymentStatus(String courseId, String sid, Date date) throws Exception {
        boolean status = false;
        PreparedStatement stmt = null;
        ResultSet result = null;
        DataSource d = null;
        Connection con = null;

        String payment_res_month = new SimpleDateFormat("MMMM").format(date);
        String payment_res_year = new SimpleDateFormat("YYYY").format(date);

        try {
             d = new DataSource();
             con = d.getConnection();
            String query = "Select * from payments where course_id = ? and s_id=? and year=? and month = ?";
            stmt = con.prepareStatement(query);
            stmt.setString(1, courseId);
            stmt.setString(2, sid);
            stmt.setString(3, payment_res_year);
            stmt.setString(4, payment_res_month.toLowerCase());

            result = stmt.executeQuery();
            while (result.next()) {
                status = true;
            }
        } catch (Exception ex) {

            cm.closeConnection(con);
            cm.closeResult(result);
            cm.closeStatements(stmt);
            //------------------------log Start--------------------------------------------
            logger.error(NewPaymentDbConnection.class+".getPaymentDetails("+courseId+","+sid+","+date+") ->error"+ex);        
            //-------------------------log End---------------------------------------------
            throw ex;
        } finally {
            cm.closeConnection(con);
            cm.closeResult(result);
            cm.closeStatements(stmt);
            return status;
        }

    }

}
