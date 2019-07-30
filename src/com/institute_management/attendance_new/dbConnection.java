/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.institute_management.attendance_new;

import com.institute_management.DataSourse.DataSource;
import com.institute_management.configurations.Config;
import com.institute_management.main.NewJFrame;
import com.institute_management.payment_mgt.paymentBean;
import com.institute_management.report.reportGen;
import static com.institute_management.user_mgt.UI.NewLogin2.logger;
import com.institute_management.util.CommonMethods;
import java.awt.Component;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author malindad
 */
public class dbConnection {

    Config config = new Config();
    CommonMethods cm = new CommonMethods();

    public String[] get_relavant_Course(String s_id) {

        PreparedStatement stmt = null;
        ResultSet result = null;
        DataSource d = null;
        Connection con = null;

        int selected_index = -1;
        ArrayList<String[]> classes = new ArrayList<String[]>();
        try {
            d = new DataSource();
            con = d.getConnection();
            DateFormat dateFormat = new SimpleDateFormat("EEEE");
            Date date = new Date();
            String day = dateFormat.format(date).toLowerCase();
            String query = "select distinct cd.course_id as course_id,cd." + day + " FROM  `courses_dates` cd inner join `student-course` sc on sc.course_id = cd.course_id  WHERE  cd." + day + "<> '' and sc.s_id = ? and cd.course_id in (select course_id from course where status = 'ACT')";
            stmt = con.prepareStatement(query);
            stmt.setString(1, s_id);
            result = stmt.executeQuery();
            while (result.next()) {
                String[] classArray = new String[3];
                // if there is only one class. those value should return. otherwise should re calculate;
                classArray[0] = result.getString("course_id");
                System.out.println(result.getString("course_id"));
                classArray[1] = result.getString(day);
                classArray[2] = "Normal Class";// class type
                classes.add(classArray);
            }
            // get Extra class details;
            query = "SELECT courseID,starttime,endtime FROM `extraclasses` WHERE STR_TO_DATE(date, '%m/%d/%Y')=CURDATE() and extraclasses.courseID in (select course.course_id from course where status = 'ACT')";
            stmt = con.prepareStatement(query);
            result = stmt.executeQuery();
            while (result.next()) {
                String[] classArray = new String[3];
                classArray[0] = result.getString("courseid");
                classArray[1] = result.getString("starttime") + "-" + result.getString("endtime");
                classArray[2] = "Extra Class";
                classes.add(classArray);
            }

            //has only one class
            if (classes.size() == 1) {
                selected_index = 0;
                return classes.get(selected_index);
            } // has several classes on today. So have to select correct class
            else {
                long dif = 0;
                int count = 0;

                for (int i = 0; i < classes.size(); i++) {
                    String course_id = classes.get(i)[0];
                    String c_time = classes.get(i)[1].split("-")[0];

                    SimpleDateFormat time_format = new SimpleDateFormat("HH:mm");
                    Date date1 = time_format.parse(c_time);
                    Date date2 = time_format.parse(time_format.format(new Date()));
                    long current_dif = (date1.getTime() - date2.getTime());
                    if (current_dif < 0) {
                        current_dif = current_dif * (-1);
                    }
                    // set the value for dif bcz first time there is no value
                    if (count == 0) {
                        dif = current_dif;
                    }
                    if (current_dif <= dif) {
                        dif = current_dif;
                        selected_index = i;

                    }
                    count++;

                }

            }

        } catch (Exception ex) {

            cm.closeConnection(con);
            cm.closeResult(result);
            cm.closeStatements(stmt);
            //------------------------log Start--------------------------------------------
            logger.error(dbConnection.class + ".get_relavant_Course(" + s_id + ") ->error" + ex);
            //-------------------------log End---------------------------------------------
            throw ex;

        } finally {
            cm.closeConnection(con);
            cm.closeResult(result);
            cm.closeStatements(stmt);
            if (selected_index > -1) {
                return classes.get(selected_index);
            } else {
                return new String[3];// return empty array
            }
        }
    }
    
    public boolean IS_First_Entrence(String sid) throws Exception {
        boolean count = false;
        PreparedStatement pst = null;
        ResultSet rs = null;
        DataSource d = null;
        Connection connection = null;
        String query = null;
        DateFormat dateFormat2 = new SimpleDateFormat("YYYY-MM-dd");        
        String dateattendence2 = dateFormat2.format(new Date());

        System.out.println(dateattendence2);
        query = "select s_id from `attendence` where `attendence`= ? and `completeDate`= ? and `s_id` =?";
        

        try {
            d = new DataSource();
            connection = d.getConnection();
            pst = connection.prepareStatement(query);
            pst.setInt(1, 1);
            pst.setString(2, dateattendence2);
            pst.setString(3, sid);
            
            rs = pst.executeQuery();
            if (!rs.isBeforeFirst()) {
                count = true;
            } else {
                count = false;
            }
        } catch (Exception ex) {

            cm.closeConnection(connection);
            cm.closeResult(rs);
            cm.closeStatements(pst);
            //------------------------log Start--------------------------------------------
            logger.error(dbConnection.class + ".markAttendence() ->error" + ex);
            //-------------------------log End---------------------------------------------
            throw ex;
        } finally {
            cm.closeConnection(connection);
            cm.closeResult(rs);
            cm.closeStatements(pst);
            return count;
        }

    }

    public int insert_payment_SMS(String body,String mobile,String s_id) {
       
        int x = 0;
        
        PreparedStatement pst = null;
        ResultSet rs = null;
        DataSource d = null;
        Connection connection = null;
        String query = null;
        body = body.replace(" ","+");
        try {
            d = new DataSource();
            connection = d.getConnection();
            query = "insert into sms_details (s_id,body,mobile,created_date,retry_attempts,send_date) values (?,?,?,curdate(),0,'') ";

            pst = connection.prepareStatement(query);
            pst.setString(1, s_id);
            pst.setString(2, body);
            pst.setString(3, mobile);
            x = pst.executeUpdate();
        } catch (Exception ex) {

            cm.closeConnection(connection);
            cm.closeResult(rs);
            cm.closeStatements(pst);
            //------------------------log Start--------------------------------------------
            logger.error(dbConnection.class + ".insert_SMS() ->error" + ex);
            //-------------------------log End---------------------------------------------
            throw ex;
        } finally {
            cm.closeConnection(connection);
            cm.closeResult(rs);
            cm.closeStatements(pst);
            return x;
        }
    }
    
    public int insert_arrive_SMS(String title, String Name, String time, String mobile,String s_id) {
        String new_body = config.SMS_Genaral_attendance;
        int x = 0;
        time = time.replace(":","%3A");
        new_body = new_body.replace("<TITLE>", title);
        new_body = new_body.replace("<NAME>", Name);
        new_body = new_body.replace("<TIME>", time);
        

        PreparedStatement pst = null;
        ResultSet rs = null;
        DataSource d = null;
        Connection connection = null;
        String query = null;
        try {
            d = new DataSource();
            connection = d.getConnection();
            query = "insert into sms_details (s_id,body,mobile,created_date,retry_attempts,send_date) values (?,?,?,curdate(),0,'') ";

            pst = connection.prepareStatement(query);
            pst.setString(1, s_id);
            pst.setString(2, new_body);
            pst.setString(3, mobile);
            x = pst.executeUpdate();
        } catch (Exception ex) {

            cm.closeConnection(connection);
            cm.closeResult(rs);
            cm.closeStatements(pst);
            //------------------------log Start--------------------------------------------
            logger.error(dbConnection.class + ".insert_SMS() ->error" + ex);
            //-------------------------log End---------------------------------------------
            throw ex;
        } finally {
            cm.closeConnection(connection);
            cm.closeResult(rs);
            cm.closeStatements(pst);
            return x;
        }
    }
    
    
    
    
    public int insert_attend_SMS(String Name,String course, String in_time,String out_time,String mobile,String s_id) {
        String new_body = config.SMS_course_attendance;
        int x = 0;
        in_time = in_time.replace(":","%3A");
        out_time = out_time.replace(":","%3A");
        new_body = new_body.replace("<NAME>", Name);
        new_body = new_body.replace("<COURSE>", course);
        new_body = new_body.replace("<IN_TIME>", in_time);
        new_body = new_body.replace("<OUT_TIME>", out_time);

        PreparedStatement pst = null;
        ResultSet rs = null;
        DataSource d = null;
        Connection connection = null;
        String query = null;
        try {
            d = new DataSource();
            connection = d.getConnection();
            query = "insert into sms_details (s_id,body,mobile,created_date,retry_attempts,send_date) values (?,?,?,curdate(),0,'') ";

            pst = connection.prepareStatement(query);
            pst.setString(1, s_id);
            pst.setString(2, new_body);
            pst.setString(3, mobile);
            x = pst.executeUpdate();
        } catch (Exception ex) {

            cm.closeConnection(connection);
            cm.closeResult(rs);
            cm.closeStatements(pst);
            //------------------------log Start--------------------------------------------
            logger.error(dbConnection.class + ".insert_SMS() ->error" + ex);
            //-------------------------log End---------------------------------------------
            throw ex;
        } finally {
            cm.closeConnection(connection);
            cm.closeResult(rs);
            cm.closeStatements(pst);
            return x;
        }
    }
    
    public int insert_not_attend_SMS(String Name,String course,String mobile,String s_id) {
        String new_body = config.SMS_Genaral_not_attendance;
        int x = 0;
       
        new_body = new_body.replace("<NAME>", Name);
        new_body = new_body.replace("<COURSE>", course);
        

        PreparedStatement pst = null;
        ResultSet rs = null;
        DataSource d = null;
        Connection connection = null;
        String query = null;
        try {
            d = new DataSource();
            connection = d.getConnection();
            query = "insert into sms_details (s_id,body,mobile,created_date,retry_attempts,send_date) values (?,?,?,curdate(),0,'') ";

            pst = connection.prepareStatement(query);
            pst.setString(1, s_id);
            pst.setString(2, new_body);
            pst.setString(3, mobile);
            x = pst.executeUpdate();
        } catch (Exception ex) {

            cm.closeConnection(connection);
            cm.closeResult(rs);
            cm.closeStatements(pst);
            //------------------------log Start--------------------------------------------
            logger.error(dbConnection.class + ".insert_SMS() ->error" + ex);
            //-------------------------log End---------------------------------------------
            throw ex;
        } finally {
            cm.closeConnection(connection);
            cm.closeResult(rs);
            cm.closeStatements(pst);
            return x;
        }
    }
    
    public int deleteSMS(String id) {
        
        int x = 0;
       

        PreparedStatement pst = null;
        ResultSet rs = null;
        DataSource d = null;
        Connection connection = null;
        String query = null;
        try {
            d = new DataSource();
            connection = d.getConnection();
            query = "delete from sms_details where id = ? ";

            pst = connection.prepareStatement(query);
            pst.setString(1, id);
            x = pst.executeUpdate();
        } catch (Exception ex) {

            cm.closeConnection(connection);
            cm.closeResult(rs);
            cm.closeStatements(pst);
            //------------------------log Start--------------------------------------------
            logger.error(dbConnection.class + ".deleteSMS() ->error" + ex);
            //-------------------------log End---------------------------------------------
            throw ex;
        } finally {
            cm.closeConnection(connection);
            cm.closeResult(rs);
            cm.closeStatements(pst);
            return x;
        }
    }

    public HashMap<Integer, Object[]> getPendingSMS(String s_id, String date) throws Exception {
        int count = 0;
         int count2 = 1;

        PreparedStatement stmt = null;
        ResultSet result = null;
        DataSource d = null;
        Connection con = null;
        int success;
        String query1 = null;
        String query2 = null;
        String query3 = null;
        String query4 = null;
        HashMap<Integer, Object[]> tableData = new HashMap<Integer, Object[]>();

        try {
            d = new DataSource();
            con = d.getConnection();
            if (s_id.equals("")) {
                if (date == null) {
                    count = 1;
                } else {
                    count = 2;
                }
            } else {
                if (date == null) {
                    count = 3;
                } else {
                    count = 4;
                }
            }

            if (count == 1) {
                query1 = "select * from sms_details where `status`=0 ";
                stmt = con.prepareStatement(query1);
                
            } else if (count == 2) {
                query2 = "select * from sms_details where `status`=0 and  `created_date`=?";
                stmt = con.prepareStatement(query2);
                stmt.setString(1, date);
            } else if (count == 3) {
                query3 = "select * from sms_details where `status`=0 and  `s_id` = ?";
                stmt = con.prepareStatement(query3);
                stmt.setString(1, s_id);
            } else if (count == 4) {
                query4 = "select * from sms_details where `status`=0 and  `created_date`=? and `s_id` = ?";
                stmt = con.prepareStatement(query4);
                stmt.setString(1, date);
                stmt.setString(2, s_id);
            }

            result = stmt.executeQuery();
            while (result.next()) {
                Object[] rowData = new Object[5];

                rowData[0] = result.getString("id");
                rowData[1] = result.getString("s_id");
                rowData[2] = result.getString("body").replace("+"," ");
                rowData[3] = result.getString("created_date");
                rowData[4] = (boolean) false;
                tableData.put(count2, rowData);
                count2++;
            }
        } catch (Exception ex) {
            cm.closeConnection(con);
            cm.closeResult(result);
            cm.closeStatements(stmt);
            //------------------------log Start--------------------------------------------
            logger.error(dbConnection.class + ".getPendingSMS() ->error" + ex);
            //-------------------------log End---------------------------------------------
            throw ex;
        } finally {
            cm.closeConnection(con);
            cm.closeResult(result);
            cm.closeStatements(stmt);
            return tableData;
        }

    }

    public String updateSMStable(String id) {

        PreparedStatement pst = null;
        ResultSet rs = null;
        DataSource d = null;
        Connection connection = null;
        String query = null;
        String x = null;
        try {
            d = new DataSource();
            connection = d.getConnection();
            query = "update sms_details set status = 1 where id = " + id;

            pst = connection.prepareStatement(query);

            pst.executeUpdate();

        } catch (Exception ex) {

            cm.closeConnection(connection);
            cm.closeResult(rs);
            cm.closeStatements(pst);
            //------------------------log Start--------------------------------------------
            logger.error(dbConnection.class + ".updateSMStable() ->error" + ex);
            //-------------------------log End---------------------------------------------
            throw ex;
        } finally {
            cm.closeConnection(connection);
            cm.closeResult(rs);
            cm.closeStatements(pst);
            return x;
        }
    }

    public String Check_pending_SMS() {

        PreparedStatement pst = null;
        ResultSet rs = null;
        DataSource d = null;
        Connection connection = null;
        String query = null;
        String x = null;
        try {
            d = new DataSource();
            connection = d.getConnection();
            query = "select count(*) a from sms_details where status = 0";

            pst = connection.prepareStatement(query);

            rs = pst.executeQuery();

            while (rs.next()) {
                x = rs.getString("a");
            }
        } catch (Exception ex) {

            cm.closeConnection(connection);
            cm.closeResult(rs);
            cm.closeStatements(pst);
            //------------------------log Start--------------------------------------------
            logger.error(dbConnection.class + ".Check_pending_SMS() ->error" + ex);
            //-------------------------log End---------------------------------------------
            throw ex;
        } finally {
            cm.closeConnection(connection);
            cm.closeResult(rs);
            cm.closeStatements(pst);
            return x;
        }
    }

    public int get_weekNumber() throws SQLException {

        PreparedStatement stmt = null;
        ResultSet result = null;
        DataSource d = null;
        Connection con = null;
        int weeknumber = 0;
        try {
            d = new DataSource();
            con = d.getConnection();
            String query = "SELECT FLOOR( (DAYOFMONTH( CURRENT_DATE( )) -1 ) /7) +1 AS week_of_month";
            stmt = con.prepareStatement(query);

            result = stmt.executeQuery();
            while (result.next()) {
                weeknumber = result.getInt("week_of_month") + 2;
            }

        } catch (Exception ex) {

            cm.closeConnection(con);
            cm.closeResult(result);
            cm.closeStatements(stmt);
            //------------------------log Start--------------------------------------------
            logger.error(dbConnection.class + ".get_sid_from_card_number( ) ->error" + ex);
            //-------------------------log End---------------------------------------------
            throw ex;
        } finally {
            cm.closeConnection(con);
            cm.closeResult(result);
            cm.closeStatements(stmt);
            return weeknumber;
        }
    }

    public String get_SNameFromID(String ID) throws SQLException {
        String S_name = null;
        PreparedStatement stmt = null;
        ResultSet result = null;
        DataSource d = null;
        Connection con = null;
        try {
            d = new DataSource();
            con = d.getConnection();
            String query = "select s_name from student where s_id = ?";
            stmt = con.prepareStatement(query);
            stmt.setString(1, ID);
            result = stmt.executeQuery();
            while (result.next()) {
                S_name = result.getString("s_name");
            }

        } catch (Exception ex) {

            cm.closeConnection(con);
            cm.closeResult(result);
            cm.closeStatements(stmt);
            //------------------------log Start--------------------------------------------
            logger.error(dbConnection.class + ".get_sid_from_card_number(" + S_name + ") ->error" + ex);
            //-------------------------log End---------------------------------------------
            throw ex;
        } finally {
            cm.closeConnection(con);
            cm.closeResult(result);
            cm.closeStatements(stmt);
            return S_name;
        }
    }
    
    public String get_stdnt_SMS_contact(String ID) throws SQLException {
        String contact = null;
        PreparedStatement stmt = null;
        ResultSet result = null;
        DataSource d = null;
        Connection con = null;
        try {
            d = new DataSource();
            con = d.getConnection();
            String query = "select S_PARENT_CONTACT_NO,S_TELEPHONE from student where s_id = ?";
            stmt = con.prepareStatement(query);
            stmt.setString(1, ID);
            result = stmt.executeQuery();
            while (result.next()) {
                contact = result.getString("S_PARENT_CONTACT_NO")+","+result.getString("S_TELEPHONE");
            }

        } catch (Exception ex) {

            cm.closeConnection(con);
            cm.closeResult(result);
            cm.closeStatements(stmt);
            //------------------------log Start--------------------------------------------
            logger.error(dbConnection.class + ".get_stdnt_SMS_contact() ->error" + ex);
            //-------------------------log End---------------------------------------------
            throw ex;
        } finally {
            cm.closeConnection(con);
            cm.closeResult(result);
            cm.closeStatements(stmt);
            return contact;
        }
    }

    public boolean makePayment(paymentBean pb, Connection con) throws Exception {
        PreparedStatement stmt = null;
        ResultSet result = null;

        boolean success = false;
        String s_id = pb.getS_id();
        String courseId = pb.getCourseID();
        String date = pb.getDate();
        ArrayList<String> months = pb.getMonth();
        String year = pb.getYear();
        double amount = pb.getAmount();
        String cardType = pb.getCardType() + "";
        SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd");
        String payDate = sdf.format(new Date());

        Calendar cal = Calendar.getInstance();
        String payment_res_month = new SimpleDateFormat("MMMM").format(cal.getTime());
        String payment_res_year = new SimpleDateFormat("YYYY").format(cal.getTime());

        try {

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
                    query = "INSERT INTO `payments`( `year`, `month`, `date`, `course_id`, `s_id`, `amount`,`payment_issue_date`,`payment_issue_month`,`payment_issue_year`,card_type) VALUES (?,?,?,?,?,?,?,?,?,?)";

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
                    stmt.setString(10, cardType);
                    int x = stmt.executeUpdate();
                    

                    if (x == 1) {
                        query = "update payments_activation_details set status = 'COMPLETED' where course_id=? and s_id=? and payment_month = ? and payment_year=? ";
                        stmt = con.prepareStatement(query);
                        stmt.setString(1, courseId);
                        stmt.setString(2, s_id);
                        stmt.setString(3, months.get(i));
                        stmt.setString(4, year);
                        int y = stmt.executeUpdate();

                        if (y == 1) {
                            con.commit();
                            success = true;
                        }

                    }
                } else {
                    JOptionPane.showMessageDialog(new JFrame(), "Already Paid For " + months.get(i) + " of " + year);
                }
            }

        } catch (Exception ex) {
            con.rollback();
            cm.closeResult(result);
            cm.closeStatements(stmt);
            //------------------------log Start--------------------------------------------
            // logger.error(NewPaymentDbConnection.class+".makePayment("+pb+") ->error"+ex);        
            //-------------------------log End---------------------------------------------
            throw ex;
        } finally {

            cm.closeResult(result);
            cm.closeStatements(stmt);
            return success;
        }
    }

    public HashMap<Integer, Object[]> get_given_class_due_payments(String last_Pmonth, int last_Pyear, String curMonth, int curYear, HashMap<Integer, String> monthMap, boolean isFirstMonth) throws SQLException {
        HashMap<Integer, Object[]> tableData = new HashMap<Integer, Object[]>();
        int count = 1;
        int from = 0;
        int to = 0;

        Calendar currentMonth = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMMM");
        currentMonth.add(Calendar.MONTH, 1);
        String nextMonth = dateFormat.format(currentMonth.getTime()).toLowerCase();
        try {

            if (isFirstMonth) {
                for (Map.Entry<Integer, String> entry : monthMap.entrySet()) {
                    if (entry.getValue().equals(last_Pmonth)) {
                        from = entry.getKey();
                    }
                    if (entry.getValue().equals(nextMonth)) {
                        to = entry.getKey();
                    }
                }
            } else {
                for (Map.Entry<Integer, String> entry : monthMap.entrySet()) {
                    if (entry.getValue().equals(last_Pmonth)) {
                        from = entry.getKey() + 1;
                    }
                    if (entry.getValue().equals(curMonth)) {
                        to = entry.getKey();
                    }
                }
            }

            if (last_Pyear == curYear) {
                for (int i = from; i <= to; i++) {
                    Object[] rowData = new Object[3];
                    rowData[0] = monthMap.get(i) + "-" + curYear;
                    rowData[1] = false;
                    rowData[2] = false;
                    tableData.put(count, rowData);
                    count++;
                }
            } else {
                for (int i = from; i <= 12; i++) {
                    Object[] rowData = new Object[3];
                    rowData[0] = monthMap.get(i) + "-" + last_Pyear;
                    rowData[1] = false;
                    rowData[2] = false;
                    tableData.put(count, rowData);
                    count++;
                }
                for (int i = 1; i <= to; i++) {
                    Object[] rowData = new Object[3];
                    rowData[0] = monthMap.get(i) + "-" + curYear;
                    rowData[1] = false;
                    rowData[2] = false;
                    tableData.put(count, rowData);
                    count++;
                }
            }

            //rowData[3]=true;
        } catch (Exception ex) {

            //------------------------log Start--------------------------------------------
            logger.error(dbConnection.class + ".get_given_class_due_payments ->error" + ex);
            //-------------------------log End---------------------------------------------
        } finally {

            return tableData;
        }
    }

    public String getStringMonthByIntMonth(String charDate) {
        String[] x = charDate.split("-");
        if (x[1].equals("01")) {
            return "january";
        } else if (x[1].equals("02")) {
            return "february";
        } else if (x[1].equals("03")) {
            return "march";
        } else if (x[1].equals("04")) {
            return "april";
        } else if (x[1].equals("05")) {
            return "may";
        } else if (x[1].equals("06")) {
            return "june";
        } else if (x[1].equals("07")) {
            return "july";
        } else if (x[1].equals("08")) {
            return "august";
        } else if (x[1].equals("09")) {
            return "september";
        } else if (x[1].equals("10")) {
            return "october";
        } else if (x[1].equals("11")) {
            return "november";
        } else if (x[1].equals("12")) {
            return "december";
        }
        return null;
    }

    public String getCourseRegDate(String s_id, String course_id) {
        PreparedStatement stmt = null;
        ResultSet result = null;
        DataSource d = null;
        Connection con = null;
        String regDate = null;
        try {
            d = new DataSource();
            con = d.getConnection();
            DateFormat dateFormat = new SimpleDateFormat("EEEE");
            Date date = new Date();
            String day = dateFormat.format(date).toLowerCase();

            String query = "select `registation_date`  FROM  `student-course`  WHERE  course_id = ? and s_id = ?";
            stmt = con.prepareStatement(query);
            stmt.setString(1, course_id);
            stmt.setString(2, s_id);
            result = stmt.executeQuery();
            while (result.next()) {
                regDate = result.getString("registation_date");
            }
        } catch (Exception ex) {

            cm.closeConnection(con);
            cm.closeResult(result);
            cm.closeStatements(stmt);
            //------------------------log Start--------------------------------------------
            logger.error(dbConnection.class + ".getCourseRegDate(" + s_id + ") ->error" + ex);
            //-------------------------log End---------------------------------------------
            throw ex;
        } finally {
            cm.closeConnection(con);
            cm.closeResult(result);
            cm.closeStatements(stmt);
            return regDate;
        }

    }
    
    public String getCourseDesc(String course_id) {
        PreparedStatement stmt = null;
        ResultSet result = null;
        DataSource d = null;
        Connection con = null;
        String c_desc = null;
        try {
            d = new DataSource();
            con = d.getConnection();

            String query = "select `course_description`  FROM  `course`  WHERE  course_id = ?";
            stmt = con.prepareStatement(query);
            stmt.setString(1, course_id);
            result = stmt.executeQuery();
            while (result.next()) {
                c_desc = result.getString("course_description");
            }
        } catch (Exception ex) {

            cm.closeConnection(con);
            cm.closeResult(result);
            cm.closeStatements(stmt);
            //------------------------log Start--------------------------------------------
            logger.error(dbConnection.class + ".getCourseDesc() ->error" + ex);
            //-------------------------log End---------------------------------------------
            throw ex;
        } finally {
            cm.closeConnection(con);
            cm.closeResult(result);
            cm.closeStatements(stmt);
            return c_desc;
        }

    }

    public ArrayList<String[]> get_all_Courses(String s_id) {
        String[] classArray = new String[3];
        PreparedStatement stmt = null;
        ResultSet result = null;
        DataSource d = null;
        Connection con = null;
        ArrayList<String[]> classes = new ArrayList<String[]>();
        try {
            d = new DataSource();
            con = d.getConnection();
            DateFormat dateFormat = new SimpleDateFormat("EEEE");
            Date date = new Date();
            String day = dateFormat.format(date).toLowerCase();

            String query = "select cd.course_id as course_id,cd." + day + " FROM  `courses_dates` cd inner join `student-course` sc on sc.course_id = cd.course_id  WHERE  cd." + day + "<> '' and sc.s_id = ? and cd.course_id in (select course_id from course where status = 'ACT')";
            stmt = con.prepareStatement(query);
            stmt.setString(1, s_id);
            result = stmt.executeQuery();
            while (result.next()) {
                // if there is only one class. those value should return. otherwise should re calculate;
                classArray[0] = result.getString("course_id");
                classArray[1] = result.getString(day);
                classArray[2] = "Normal Class";// class type
                classes.add(classArray);
            }
            // get Extra class details;
            query = "SELECT courseID,starttime,endtime FROM `extraclasses` WHERE STR_TO_DATE(date, '%m/%d/%Y')=CURDATE() and courseID in (select course_id from course where status = 'ACT')";
            stmt = con.prepareStatement(query);
            result = stmt.executeQuery();
            while (result.next()) {
                classArray[0] = result.getString("courseid");
                classArray[1] = result.getString("starttime") + "-" + result.getString("endtime");
                classArray[2] = "Extra Class";
                classes.add(classArray);
            }

            //has only one class
        } catch (Exception ex) {

            cm.closeConnection(con);
            cm.closeResult(result);
            cm.closeStatements(stmt);
            //------------------------log Start--------------------------------------------
            logger.error(dbConnection.class + ".get_all_Courses(" + s_id + ") ->error" + ex);
            //-------------------------log End---------------------------------------------
            throw ex;
        } finally {
            cm.closeConnection(con);
            cm.closeResult(result);
            cm.closeStatements(stmt);
            return classes;
        }

    }

    public SelectedCourseStudentDetails get_student_details(String s_id, SelectedCourseStudentDetails temp) throws SQLException {

        PreparedStatement stmt = null;
        ResultSet result = null;
        DataSource d = null;
        Connection con = null;
        try {
            d = new DataSource();
            con = d.getConnection();
            String query = "select * from student where s_id = ?";
            stmt = con.prepareStatement(query);
            stmt.setString(1, s_id);
            result = stmt.executeQuery();
            while (result.next()) {
                temp.setS_address(result.getString("S_ADDRESS"));
                temp.setS_contact(result.getString("S_TELEPHONE"));
                temp.setS_id(s_id);
                temp.setS_name(result.getString("S_NAME"));
                temp.setS_reg_date(result.getString("S_YOR"));
                temp.setS_school(result.getString("S_SCHOOL"));
                temp.setSms_contact1(result.getString("S_PARENT_CONTACT_NO"));
                temp.setSms_contact2(result.getString("S_TELEPHONE"));
                if (result.getString("S_GENDER").equals("Female")) {
                    temp.setS_gender("Daughter");
                } else {
                    temp.setS_gender("Son");
                }
            }

        } catch (Exception ex) {
            cm.closeConnection(con);
            cm.closeResult(result);
            cm.closeStatements(stmt);
            //------------------------log Start--------------------------------------------
            logger.error(dbConnection.class + ".get_student_details(" + s_id + "," + temp + ") ->error" + ex);
            //-------------------------log End---------------------------------------------
            throw ex;
        } finally {
            cm.closeConnection(con);
            cm.closeResult(result);
            cm.closeStatements(stmt);
            return temp;
        }
    }

    public String get_sid_from_card_number(String cardNumber) throws SQLException {
        String s_id = null;
        PreparedStatement stmt = null;
        ResultSet result = null;
        DataSource d = null;
        Connection con = null;
        try {
            d = new DataSource();
            con = d.getConnection();
            String query = "select s_id from student where card_number = ?";
            stmt = con.prepareStatement(query);
            stmt.setString(1, cardNumber);
            result = stmt.executeQuery();
            while (result.next()) {
                s_id = result.getString("s_id");
            }

        } catch (Exception ex) {

            cm.closeConnection(con);
            cm.closeResult(result);
            cm.closeStatements(stmt);
            //------------------------log Start--------------------------------------------
            logger.error(dbConnection.class + ".get_sid_from_card_number(" + cardNumber + ") ->error" + ex);
            //-------------------------log End---------------------------------------------
            throw ex;
        } finally {
            cm.closeConnection(con);
            cm.closeResult(result);
            cm.closeStatements(stmt);
            return s_id;
        }
    }

    public SelectedCourseStudentDetails get_lecturer_Course_details(String course_id, String s_id, SelectedCourseStudentDetails temp) throws SQLException {

        PreparedStatement stmt = null;
        ResultSet result = null;
        DataSource d = null;
        Connection con = null;
        try {
            d = new DataSource();
            con = d.getConnection();
            String query = "select le.id l_id,le.name l_name,co.monthly_fee,co.class_type,st.s_name,st.s_gender,st.S_PARENT_CONTACT_NO,S_TELEPHONE , (select cardtype from `student-course` where s_id=? and course_id=?)card_type, (select month from payments where s_id=? and course_id=? order by id desc limit 1)as last_payment_month  from lecturer le ,course co,student st where co.course_id = ? and  co.lecturer_id = le.id and st.s_id = ?";
            stmt = con.prepareStatement(query);
            stmt.setString(1, s_id);
            stmt.setString(2, course_id);
            stmt.setString(3, s_id);
            stmt.setString(4, course_id);
            stmt.setString(5, course_id);
            stmt.setString(6, s_id);
            result = stmt.executeQuery();
            while (result.next()) {
                temp.setL_id(result.getString("l_id"));
                temp.setL_name(result.getString("l_name"));
                temp.setC_fee(result.getString("monthly_fee"));
                temp.setC_last_payment(result.getString("last_payment_month"));
                temp.setC_type_r_or_t(result.getString("class_type"));
                temp.setC_card_type(result.getString("card_type"));

                temp.setSms_contact1(result.getString("S_PARENT_CONTACT_NO"));
                temp.setSms_contact2(result.getString("S_TELEPHONE"));
                if (result.getString("S_GENDER").equals("Female")) {
                    temp.setS_gender("Daughter");
                } else {
                    temp.setS_gender("Son");
                }
                temp.setS_name(result.getString("s_name"));

            }

        } catch (Exception ex) {
            cm.closeConnection(con);
            cm.closeResult(result);
            cm.closeStatements(stmt);
            //------------------------log Start--------------------------------------------
            logger.error(dbConnection.class + ".get_lecturer_Course_details(" + course_id + "," + s_id + "," + temp + ") ->error" + ex);
            //-------------------------log End---------------------------------------------
            throw ex;
        } finally {
            cm.closeConnection(con);
            cm.closeResult(result);
            cm.closeStatements(stmt);

            return temp;
        }
    }

    public ArrayList<String> get_attendance_history(String s_id, String c_id) throws SQLException {
        ArrayList<String> attandance_history = new ArrayList<String>();
        PreparedStatement stmt = null;
        ResultSet result = null;
        DataSource d = null;
        Connection con = null;
        try {
            d = new DataSource();
            con = d.getConnection();
            String query = "select completeDate,attendence from attendence where c_id = ? and s_id = ? order by completeDate DESC LIMIT 12";
            stmt = con.prepareStatement(query);
            stmt.setString(1, c_id);
            stmt.setString(2, s_id);
            result = stmt.executeQuery();
            while (result.next()) {
                String datee = result.getString("completeDate");
                int Status = result.getInt("attendence");
                String Statuss;
                if (Status == 1) {
                    Statuss = "attend";
                } else {
                    Statuss = "not attend";
                }
                attandance_history.add(datee + "     - " + Statuss);// 5 spaces before - and 1 space after -

            }

        } catch (Exception ex) {
            cm.closeConnection(con);
            cm.closeResult(result);
            cm.closeStatements(stmt);
            //------------------------log Start--------------------------------------------
            logger.error(dbConnection.class + ".get_attendance_history(" + s_id + "," + c_id + ") ->error" + ex);
            //-------------------------log End---------------------------------------------
            throw ex;
        } finally {
            cm.closeConnection(con);
            cm.closeResult(result);
            cm.closeStatements(stmt);

            return attandance_history;
        }
    }

    public ArrayList<String> getPaymentDetails(String courseId, String studntID, int noOfMonths) throws Exception {
        PreparedStatement stmt = null;
        ResultSet result = null;
        DataSource d = null;
        Connection con = null;
        String regMonth = null;
        ArrayList<String> paymentDetails = new ArrayList<String>();
        ArrayList<String> paymentMap = new ArrayList<String>();
        int x = 0;
        try {
            d = new DataSource();
            con = d.getConnection();

            String query = "select payment_year,payment_month,status from payments_activation_details where course_id = ? and s_id=? order by id desc";

            stmt = con.prepareStatement(query);
            stmt.setString(1, courseId);
            stmt.setString(2, studntID);

            result = stmt.executeQuery();
            while (result.next()) {
                String month = result.getString("payment_month");
                String year = result.getString("payment_year");
                paymentMap.add(year + " " + month + new String(new char[12 - month.length()]).replace("\0", " ") + "- " + result.getString("status"));
            }
        } catch (Exception ex) {
            cm.closeConnection(con);
            cm.closeResult(result);
            cm.closeStatements(stmt);
            //------------------------log Start--------------------------------------------
            logger.error(dbConnection.class + ".getPaymentDetails(" + courseId + "," + studntID + "," + noOfMonths + ") ->error" + ex);
            //-------------------------log End---------------------------------------------
            throw ex;
        } finally {
            cm.closeConnection(con);
            cm.closeResult(result);
            cm.closeStatements(stmt);
            return paymentMap;
        }

    }

    public HashMap<Integer, Object[]> get_all_class_payment_history(String s_id) throws SQLException {
        HashMap<Integer, Object[]> tableData = new HashMap<Integer, Object[]>();
        PreparedStatement stmt = null;
        ResultSet result = null;
        DataSource d = null;
        Connection con = null;
        int count = 1;
        try {
            d = new DataSource();
            con = d.getConnection();
            String query = "select *,IFNULL(ELT(FIELD(card_type,0,1,2),'Free','Half','Normal'),'error')as cardType from payments_activation_details where s_id = ? and status = 'PENDING' order by id";
            stmt = con.prepareStatement(query);
            stmt.setString(1, s_id);

            result = stmt.executeQuery();
            double fee_factor = 1;
            while (result.next()) {
                String crdType = result.getString("cardType");
                Object[] rowData = new Object[4];
                rowData[0] = result.getString("course_id");
                rowData[1] = result.getDouble("elegible_fee") * fee_factor;;
                rowData[2] = result.getString("cardType");
                rowData[3] = result.getString("payment_month") + "-" + result.getString("payment_year");
                //rowData[3]=true;
                tableData.put(count, rowData);
                count++;
            }
        } catch (Exception ex) {
            cm.closeConnection(con);
            cm.closeResult(result);
            cm.closeStatements(stmt);
            //------------------------log Start--------------------------------------------
            logger.error(dbConnection.class + ".get_all_class_payment_history(" + s_id + ") ->error" + ex);
            //-------------------------log End---------------------------------------------
        } finally {
            cm.closeConnection(con);
            cm.closeResult(result);
            cm.closeStatements(stmt);
            return tableData;
        }
    }

    public void printBill(String billID) {
        HashMap a = new HashMap();
        a.put("bill_id", billID);

        try {
            ////temp code
            reportGen rg;
            try {
                rg = new reportGen(config.report_path + "LatestPaymentRecipt.jasper", a);
                rg.setVisible(true);
                logger.debug("Bill Print Status : Success");

            } catch (Exception ex) {
                Logger.getLogger(NewJFrame.class.getName()).log(Level.SEVERE, null, ex);
                logger.debug("Bill Print Status : Error " + ex);
            }
        } catch (Exception ex) {
            logger.debug("Bill Print Status : Error " + ex);
        }

    }

    public String insert_to_bill_details(ArrayList<paymentBean> paymentArray, Connection con, String IssuedBy) throws SQLException, Exception {
        PreparedStatement stmt = null;
        ResultSet result = null;
        String bill_id = null;

        bill_id = paymentArray.get(0).getS_id() + new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());

        try {

            String query = "INSERT INTO `payment_bill_details`(`bill_id`, `course_id`, `bill_amount`, `payment_month`, `comments`, `s_id`, `card_type`,`issued_by`) VALUES "
                    + " (?,?,?,?,?,?,?,?)";
            for (int i = 0; i < paymentArray.size(); i++) {
                paymentBean a = new paymentBean();
                a = paymentArray.get(i);
                int CardTpe = a.getCardType();
                String cType = CardTpe == 0 ? "Free Card" : (CardTpe == 1 ? "Half Card" : (CardTpe == 2 ? "Normal Card" : "20% OFF Card"));
                stmt = con.prepareStatement(query);
                stmt.setString(1, bill_id);
                stmt.setString(2, a.getCourseID());
                stmt.setDouble(3, a.getAmount());
                stmt.setString(4, a.getMonthYear());
                stmt.setString(5, a.getComment());
                stmt.setString(6, a.getS_id());
                stmt.setString(7, cType);
                stmt.setString(8, IssuedBy);
                stmt.executeUpdate();
            }

        } catch (Exception ex) {
            con.rollback();
            bill_id = "0";
            cm.closeResult(result);
            cm.closeStatements(stmt);
            //------------------------log Start--------------------------------------------
            logger.error(dbConnection.class + ".get_attendance_history(" + bill_id + ") ->error" + ex);
            //-------------------------log End---------------------------------------------
            throw ex;
        } finally {

            cm.closeResult(result);
            cm.closeStatements(stmt);
            return bill_id;
        }
    }
    
    public int okcancel(String theMessage) {
        int result = JOptionPane.showConfirmDialog((Component) null, theMessage,
                "alert", JOptionPane.OK_CANCEL_OPTION);
        return result;
    }

}
