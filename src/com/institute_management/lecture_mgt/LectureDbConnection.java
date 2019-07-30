/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.institute_management.lecture_mgt;

import com.institute_management.DataSourse.DataSource;

import com.institute_management.course_mgt.*;
import com.institute_management.course_mgt.courseBean;
import static com.institute_management.user_mgt.UI.NewLogin2.logger;
import com.institute_management.util.CommonMethods;
import java.awt.Component;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author tharindu_m
 */
public class LectureDbConnection {

    CommonMethods cm = new CommonMethods();

    public boolean addLecture(lectureBean lecbean) throws SQLException {

        PreparedStatement stmt = null;

        DataSource d = null;
        Connection con = null;
        boolean status = false;
        Date date = new Date();
        try {
            d = new DataSource();
            con = d.getConnection();
            String query = "INSERT INTO `lecturer`(`nic`, `name`, `gender`, `email`, `contact`, `address`,`address1`,`address2`,`RegDate`,`firstName`,`title`) VALUES (?,?,?,?,?,?,?,?,?,?,?) ";
            stmt = con.prepareStatement(query);
            stmt.setString(1, lecbean.getNic());
            stmt.setString(2, lecbean.getName());
            stmt.setString(3, lecbean.getGender());
            stmt.setString(4, lecbean.getEmail());
            stmt.setString(5, lecbean.getContact());
            stmt.setString(6, lecbean.getAddress());
            stmt.setString(7, lecbean.getAddress1());
            stmt.setString(8, lecbean.getAddress2());
            stmt.setString(9, new SimpleDateFormat("yyyy-MM-dd").format(date));
            stmt.setString(10, lecbean.getFirstlName());
            stmt.setString(11, lecbean.getTittle());

            int result = stmt.executeUpdate();
            if (result == 1) {
                status = true;
            }

        } catch (Exception ex) {

            cm.closeConnection(con);

            cm.closeStatements(stmt);
             //------------------------log Start--------------------------------------------
            logger.error(LectureDbConnection.class+".addLecture() ->error"+ex);        
            //-------------------------log End---------------------------------------------
            throw ex;
        } finally {
            cm.closeConnection(con);

            cm.closeStatements(stmt);
            return status;
        }

    }

    public HashMap<Integer, Object[]> getLecturerTableDetails() throws Exception {
        int count = 1;

        PreparedStatement stmt = null;
        ResultSet result = null;
        DataSource d = null;
        Connection con = null;
        int success;
        HashMap<Integer, Object[]> tableData = new HashMap<Integer, Object[]>();

        try {
            d = new DataSource();
            con = d.getConnection();
            String query = "select * from lecturer order by id desc;";

            stmt = con.prepareStatement(query);

            result = stmt.executeQuery();
            while (result.next()) {
                Object[] rowData = new Object[5];

                rowData[0] = result.getString("ID");
                rowData[1] = result.getString("title") + ". " + result.getString("Name");
                rowData[2] = result.getString("Gender");
                rowData[3] = result.getString("contact");
                rowData[4] = result.getString("nic");

                tableData.put(count, rowData);
                count++;
            }
        } catch (Exception ex) {

            cm.closeConnection(con);
            cm.closeResult(result);
            cm.closeStatements(stmt);
            //------------------------log Start--------------------------------------------
            logger.error(LectureDbConnection.class+".getLecturerTableDetails() ->error"+ex);        
            //-------------------------log End---------------------------------------------
            throw ex;
        } finally {
            cm.closeConnection(con);
            cm.closeResult(result);
            cm.closeStatements(stmt);
            return tableData;
        }

    }
    
    public String getNextLectureID() throws Exception {
        
    String lecID=null;
        PreparedStatement stmt = null;
        ResultSet result = null;
        DataSource d = null;
        Connection con = null;

        try {
            d = new DataSource();
            con = d.getConnection();
            String query = "select max(id)+1 as nextID from lecturer ";

            stmt = con.prepareStatement(query);

            result = stmt.executeQuery();
            while (result.next()) {
                lecID=result.getString("nextID");
                if(lecID==null){
                    lecID="1";
                }
            }
        } catch (Exception ex) {

            cm.closeConnection(con);
            cm.closeResult(result);
            cm.closeStatements(stmt);
            //------------------------log Start--------------------------------------------
            logger.error(LectureDbConnection.class+".getLecturerTableDetails() ->error"+ex);        
            //-------------------------log End---------------------------------------------
            throw ex;
        } finally {
            cm.closeConnection(con);
            cm.closeResult(result);
            cm.closeStatements(stmt);
            return lecID;
        }

    }
    
    
    public ArrayList<lectureBean> getLecturerByName(String stdName) throws Exception {
        ArrayList<lectureBean> lecturerList = new ArrayList<lectureBean>();
        Object[] tableData = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        DataSource d = null;
        Connection connection = null;
        String query = null;
        try {
             d = new DataSource();
             connection = d.getConnection();
            query = "SELECT  * FROM lecturer where name LIKE ? order by ID desc";

            pst = connection.prepareStatement(query);
            pst.setString(1, "%" + stdName + "%");

            rs = pst.executeQuery();
            while (rs.next()) {
                lectureBean le = new lectureBean();
                //tableData[0] = false;
                le.setLecID(rs.getString("ID"));
                le.setName(rs.getString("name"));
                le.setGender(rs.getString("gender"));
                le.setNic(rs.getString("nic"));
                le.setContact(rs.getString("contact"));
//                tableData[2] = rs.getString("S_ADDRESS");
//                tableData[3] = rs.getString("S_EMAIL");
//                tableData[4] = rs.getString("S_GENDER");
//                tableData[5] = rs.getString("S_YOR");
//                tableData[6] = rs.getString("S_TELEPHONE");
//                tableData[7] = rs.getString("S_SCHOOL");    
                

                lecturerList.add(le);
            }

       } catch (Exception ex) {

            cm.closeConnection(connection);
            cm.closeResult(rs);
            cm.closeStatements(pst);
            //------------------------log Start--------------------------------------------
            logger.error(LectureDbConnection.class+".getLecturerByName("+stdName+") ->error"+ex);        
            //-------------------------log End---------------------------------------------
            throw ex;
        } finally {
            cm.closeConnection(connection);
            cm.closeResult(rs);
            cm.closeStatements(pst);
            return lecturerList;
        }
       
    }
     public int okcancel(String theMessage) {
        int result = JOptionPane.showConfirmDialog((Component) null, theMessage,
                "alert", JOptionPane.OK_CANCEL_OPTION);
        return result;
    }
    public lectureBean getLecturerDetails(String id) throws Exception {
        PreparedStatement stmt = null;
        ResultSet result = null;
        DataSource d = null;
        Connection con = null;
        lectureBean lb = new lectureBean();
        boolean status = false;
        try {
            d = new DataSource();
            con = d.getConnection();
            String query = "SELECT le.* FROM lecturer le WHERE le.id= ? ";
            stmt = con.prepareStatement(query);
            stmt.setString(1, id);

            result = stmt.executeQuery();
            while (result.next()) {
                lb.setLecID(result.getString("id"));
                lb.setAddress(result.getString("address"));
                lb.setAddress1(result.getString("address1"));
                lb.setAddress2(result.getString("address2"));
                lb.setContact(result.getString("contact"));
                lb.setEmail(result.getString("email"));
                lb.setGender(result.getString("gender"));
                lb.setName(result.getString("name"));
                 lb.setFirstlName(result.getString("firstName"));
                lb.setNic(result.getString("nic"));      
                lb.setTittle(result.getString("title")); 
                lb.setRegDate(result.getString("RegDate"));
                lb.setLecID(result.getString("id"));

            }

        } catch (Exception ex) {

            cm.closeConnection(con);
            cm.closeResult(result);
            cm.closeStatements(stmt);
             //------------------------log Start--------------------------------------------
            logger.error(LectureDbConnection.class+".getLecturerDetails("+id+") ->error"+ex);        
            //-------------------------log End---------------------------------------------
            throw ex;
        } finally {
            cm.closeConnection(con);
            cm.closeResult(result);
            cm.closeStatements(stmt);
            return lb;
        }

    }

    public double caculateLecturerPayments(String courseID, String month, String year, double precentage) throws Exception {
        PreparedStatement stmt = null;
        ResultSet result = null;
        DataSource d = null;
        Connection con = null;
        String name = null;
        String query = null;
        double amount = 0;
        try {
            d = new DataSource();
            con = d.getConnection();
            if (courseID.equals("All")) {
                query = "SELECT sum(amount) as amount from payments WHERE year = ? and month = ? and paid_to_lecture = 0";
                stmt = con.prepareStatement(query);

                stmt.setString(1, year);
                stmt.setString(2, month.toLowerCase());
            } else {
                query = "SELECT sum(amount) as amount from payments WHERE course_id = ? and year = ? and month = ? and paid_to_lecture = 0";
                stmt = con.prepareStatement(query);
                stmt.setString(1, courseID);
                stmt.setString(2, year);
                stmt.setString(3, month.toLowerCase());
            }

            result = stmt.executeQuery();

            while (result.next()) {
                amount = result.getDouble("amount");
            }

        } catch (Exception ex) {

            cm.closeConnection(con);
            cm.closeResult(result);
            cm.closeStatements(stmt);
            //------------------------log Start--------------------------------------------
            logger.error(LectureDbConnection.class+".caculateLecturerPayments("+courseID+","+month+","+year+","+precentage+") ->error"+ex);        
            //-------------------------log End---------------------------------------------
            throw ex;
        } finally {
            cm.closeConnection(con);
            cm.closeResult(result);
            cm.closeStatements(stmt);
            return amount;
        }

    }

    public double calculateAdvancePayments(int lec_id) throws Exception {
        PreparedStatement stmt = null;
        ResultSet result = null;
        DataSource d = null;
        Connection con = null;
        String name = null;
        double amount = 0;

        try {
            d = new DataSource();
            con = d.getConnection();
            String query = "SELECT sum(amount) as amount from advance_payments WHERE lec_id = ? and status = 'ACTIVE'";
            stmt = con.prepareStatement(query);
            stmt.setInt(1, lec_id);
            result = stmt.executeQuery();

            while (result.next()) {
                amount = result.getDouble("amount");
            }

        } catch (Exception ex) {

            cm.closeConnection(con);
            cm.closeResult(result);
            cm.closeStatements(stmt);
            //------------------------log Start--------------------------------------------
            logger.error(LectureDbConnection.class+".calculateAdvancePayments("+lec_id+") ->error"+ex);        
            //-------------------------log End---------------------------------------------
            throw ex;
        } finally {
            cm.closeConnection(con);
            cm.closeResult(result);
            cm.closeStatements(stmt);
            return amount;
        }

    }

    public String getLecturerFirstName(String FullName) throws Exception {
        PreparedStatement stmt = null;
        ResultSet result = null;
        DataSource d = null;
        Connection con = null;
        String name = null;
        String Firstname = "";

        try {
            d = new DataSource();
            con = d.getConnection();
            String query = "SELECT firstName from lecturer WHERE name = ? ";
            stmt = con.prepareStatement(query);
            stmt.setString(1, FullName);
            result = stmt.executeQuery();

            while (result.next()) {
                Firstname = result.getString("firstName");
            }

        } catch (Exception ex) {

            cm.closeConnection(con);
            cm.closeResult(result);
            cm.closeStatements(stmt);
            //------------------------log Start--------------------------------------------
            logger.error(LectureDbConnection.class+".getLecturerFirstName("+FullName+") ->error"+ex);        
            //-------------------------log End---------------------------------------------
            throw ex;
        } finally {
            cm.closeConnection(con);
            cm.closeResult(result);
            cm.closeStatements(stmt);
            return Firstname;
        }

    }

    public void setAdvancedPayment(String lec_id, double amount) throws Exception {
        PreparedStatement stmt = null;
        ResultSet result = null;
        DataSource d = null;
        Connection con = null;
        String name = null;
        DateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd");
        String date = dateFormat.format(new Date());

        try {
            d = new DataSource();
            con = d.getConnection();
            String query = "INSERT INTO `advance_payments`(`lec_id`, `amount`, `date`, `status`) VALUES (?,?,?,?)";
            stmt = con.prepareStatement(query);
            stmt.setString(1, lec_id);
            stmt.setDouble(2, amount);
            stmt.setString(3, date);
            stmt.setString(4, "ACTIVE");

            stmt.executeUpdate();

        } catch (Exception ex) {

            cm.closeConnection(con);
            cm.closeResult(result);
            cm.closeStatements(stmt);
            //------------------------log Start--------------------------------------------
            logger.error(LectureDbConnection.class+".setAdvancedPayment("+lec_id+","+amount+") ->error"+ex);        
            //-------------------------log End---------------------------------------------
            throw ex;
        } finally {
            cm.closeConnection(con);
            cm.closeResult(result);
            cm.closeStatements(stmt);

        }

    }

    public void updatePaymentsAsPaid(String course_id, String month, int year) throws Exception {
        PreparedStatement stmt = null;
        ResultSet result = null;
        DataSource d = null;
        Connection con = null;
        String name = null;
        DateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd");
        String date = dateFormat.format(new Date());
        String query = null;
        try {
            d = new DataSource();
            con = d.getConnection();
            if (course_id.equals("All")) {
                query = "update `payments` set paid_to_lecture=?,payment_issue_date = ? where  month = ? and year = ? ";
                stmt = con.prepareStatement(query);

                stmt.setBoolean(1, true);
                stmt.setString(2, date);
                //stmt.setString(2, course_id);
                stmt.setString(3, month);
                stmt.setInt(4, year);

            } else {
                query = "update `payments` set paid_to_lecture=? ,payment_issue_date = ? where course_id = ? and month = ? and year = ? ";
                stmt = con.prepareStatement(query);

                stmt.setBoolean(1, true);
                stmt.setString(2, date);
                stmt.setString(3, course_id);
                stmt.setString(4, month);
                stmt.setInt(5, year);

            }

            stmt.executeUpdate();

        } catch (Exception ex) {

            cm.closeConnection(con);
            cm.closeResult(result);
            cm.closeStatements(stmt);
            //------------------------log Start--------------------------------------------
            logger.error(LectureDbConnection.class+".setAdvancedPayment("+course_id+","+month+","+year+") ->error"+ex);        
            //-------------------------log End---------------------------------------------
            throw ex;
        } finally {
            cm.closeConnection(con);
            cm.closeResult(result);
            cm.closeStatements(stmt);

        }

    }

    public void settleAdvancedPayment(String lec_id, String month, int year) throws Exception {
        PreparedStatement stmt = null;
        ResultSet result = null;
        DataSource d = null;
        Connection con = null;
        String name = null;
        DateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd");
        String date = dateFormat.format(new Date());

        try {
            d = new DataSource();
            con = d.getConnection();
            String query = "update `advance_payments` set status=?,settle_date = ?,settled_payment_month=?,settled_payment_year=? where lec_id = ?";
            stmt = con.prepareStatement(query);

            stmt.setString(1, "DEACTIVE");
            stmt.setString(2, date);
            stmt.setString(3, month);
            stmt.setInt(4, year);
            stmt.setString(5, lec_id);

            stmt.executeUpdate();

        } catch (Exception ex) {

            cm.closeConnection(con);
            cm.closeResult(result);
            cm.closeStatements(stmt);
            //------------------------log Start--------------------------------------------
            logger.error(LectureDbConnection.class+".settleAdvancedPayment("+lec_id+","+month+","+year+") ->error"+ex);        
            //-------------------------log End---------------------------------------------
            throw ex;
        } finally {
            cm.closeConnection(con);
            cm.closeResult(result);
            cm.closeStatements(stmt);

        }

    }

    public int[] totalFreeCards(String LecturerID) throws Exception {
        PreparedStatement stmt = null;
        ResultSet result = null;
        DataSource d = null;
        Connection con = null;
        int success;
        int array[] = new int[4];
        try {
            d = new DataSource();
            con = d.getConnection();
            String query = "SELECT DISTINCT (select count(cardType) from `student-course` where cardType=0 ) as free,(select count(cardType) from `student-course` where cardType=1 ) as half,(select count(cardType) from `student-course` where cardType=2 ) as normal, (select count(cardType) from `student-course`)as total FROM `student-course`,`Lecturer`,`course` where Lecturer.id = ? and course.lecturer_id =  Lecturer.id and course.course_id = `student-course`.course_id";

            stmt = con.prepareStatement(query);
            stmt.setString(1, LecturerID);

            result = stmt.executeQuery();
            while (result.next()) {
                array[0] = result.getInt("free");
                array[1] = result.getInt("half");
                array[2] = result.getInt("normal");
                array[3] = result.getInt("total");

            }

        } catch (Exception ex) {

            cm.closeConnection(con);
            cm.closeResult(result);
            cm.closeStatements(stmt);
            //------------------------log Start--------------------------------------------
            logger.error(LectureDbConnection.class+".totalFreeCards("+LecturerID+") ->error"+ex);        
            //-------------------------log End---------------------------------------------
            throw ex;
        } finally {
            cm.closeConnection(con);
            cm.closeResult(result);
            cm.closeStatements(stmt);
            return array;
        }
    }

    public double getPaymentPrecentage(String Course_id) throws Exception {
        PreparedStatement stmt = null;
        ResultSet result = null;
        DataSource d = null;
        Connection con = null;
        Double precentage = 0.00;

        try {
            d = new DataSource();
            con = d.getConnection();
            String query = "SELECT lecturer_payment_precentage from course where `course_id` = ?";

            stmt = con.prepareStatement(query);
            stmt.setString(1, Course_id);

            result = stmt.executeQuery();
            while (result.next()) {
                precentage = result.getDouble("lecturer_payment_precentage");

            }

        } catch (Exception ex) {

            cm.closeConnection(con);
            cm.closeResult(result);
            cm.closeStatements(stmt);
            //------------------------log Start--------------------------------------------
            logger.error(LectureDbConnection.class+".getPaymentPrecentage("+Course_id+") ->error"+ex);        
            //-------------------------log End---------------------------------------------
            throw ex;
        } finally {
            cm.closeConnection(con);
            cm.closeResult(result);
            cm.closeStatements(stmt);
            return precentage;
        }
    }

    public int courseCount(String lecID) throws Exception {

        PreparedStatement stmt = null;
        ResultSet result = null;
        DataSource d = null;
        Connection con = null;
        int count = 0;
        try {
            d = new DataSource();
            con = d.getConnection();
            String query = "SELECT count(*)as count FROM `course` WHERE lecturer_id = ?;";
            stmt = con.prepareStatement(query);
            stmt.setString(1, lecID);

            result = stmt.executeQuery();
            while (result.next()) {
                count = result.getInt("count");

            }

        } catch (Exception ex) {

            cm.closeConnection(con);
            cm.closeResult(result);
            cm.closeStatements(stmt);
             //------------------------log Start--------------------------------------------
            logger.error(LectureDbConnection.class+".courseCount("+lecID+") ->error"+ex);        
            //-------------------------log End---------------------------------------------
            throw ex;
        } finally {
            cm.closeConnection(con);
            cm.closeResult(result);
            cm.closeStatements(stmt);
            return count;
        }

    }

    public boolean updateLecture(lectureBean lecbean) throws SQLException, Exception {

        PreparedStatement stmt = null;

        DataSource d = null;
        Connection con = null;
        boolean status = false;
        try {
            d = new DataSource();
            con = d.getConnection();
            String query = "update `lecturer` set `title`=?,`nic`=?,`name`=?,`firstName`=?, `gender`=? , `email`=?, `contact`=? ,`address`=?,`address1`=?,`address2`=? where id = ?";
            stmt = con.prepareStatement(query);
            stmt.setString(1, lecbean.getTittle());
            stmt.setString(2, lecbean.getNic());
            stmt.setString(3, lecbean.getName());
            stmt.setString(4, lecbean.getFirstlName());
            stmt.setString(5, lecbean.getGender());
            stmt.setString(6, lecbean.getEmail());
            stmt.setString(7, lecbean.getContact());
            stmt.setString(8, lecbean.getAddress());
            stmt.setString(9, lecbean.getAddress1());
            stmt.setString(10, lecbean.getAddress2());
            stmt.setString(11, lecbean.getLecID());

            int result = stmt.executeUpdate();
            if (result == 1) {
                status = true;
            }

        } catch (Exception ex) {

            cm.closeConnection(con);

            cm.closeStatements(stmt);
            //------------------------log Start--------------------------------------------
            logger.error(LectureDbConnection.class+".updateLecture("+lecbean+") ->error"+ex);        
            //-------------------------log End---------------------------------------------
            throw ex;
        } finally {
            cm.closeConnection(con);

            cm.closeStatements(stmt);
            return status;
        }

    }

    public double calculateIncomePrecentage(courseBean cb) {
        try {
            int array[] = totalFreeCards(cb.getCourseID());
            if (cb.getMonthlyFee() > 0.00) {
                double totalNormalCardIncome = array[2] * cb.getMonthlyFee();
                double totalHalfCardIncome = array[1] * cb.getMonthlyFee();
                double precentage = (totalHalfCardIncome + totalNormalCardIncome);
            } else {
                double totalNormalCardIncome = array[2] * cb.getTotalCourseFee();
                double totalHalfCardIncome = array[1] * cb.getTotalCourseFee();
            }
        } catch (Exception ex) {
            
            //------------------------log Start--------------------------------------------
            logger.error(LectureDbConnection.class+".calculateIncomePrecentage("+cb+") ->error"+ex);        
            //-------------------------log End---------------------------------------------
        }
        return 0.00;
    }

    HashMap<Integer, Object[]> getPaymentDetailsOnLecturer(String lecID) throws Exception {
        int count = 1;

        PreparedStatement stmt = null;
        ResultSet result = null;
        DataSource d = null;
        Connection con = null;
        boolean success;
        HashMap<Integer, Object[]> tableData = new HashMap<Integer, Object[]>();

        try {
            d = new DataSource();
            con = d.getConnection();
            String query = "SELECT  `amount`, `date`, `advance_payment_status` FROM `lecturer_payments` WHERE `lecturer_id`=?;";

            stmt = con.prepareStatement(query);
            stmt.setString(1, lecID);

            result = stmt.executeQuery();
            while (result.next()) {
                Object[] rowData = new Object[3];

                rowData[0] = result.getString("date");
                rowData[1] = result.getDouble("amount");
                success = result.getBoolean("advance_payment_status");

                if (success == true) {
                    rowData[2] = "YES";
                } else {
                    rowData[2] = "NO";
                }

            }
        } catch (Exception ex) {

            cm.closeConnection(con);
            cm.closeResult(result);
            cm.closeStatements(stmt);
            //------------------------log Start--------------------------------------------
            logger.error(LectureDbConnection.class+".getPaymentDetailsOnLecturer("+lecID+") ->error"+ex);        
            //-------------------------log End---------------------------------------------
            throw ex;
        } finally {
            cm.closeConnection(con);
            cm.closeResult(result);
            cm.closeStatements(stmt);
            return tableData;
        }

    }

    int insertLecPayment(String lecID, String payDate, double amount, String paymntMonth, int paymntYear, String courseID) throws Exception {
        int count = 0;
        PreparedStatement stmt = null;

        DataSource d = null;
        Connection con = null;
        try {
            d = new DataSource();
            con = d.getConnection();
            String query = "INSERT INTO `lecturer_payments`(`lecturer_id`, `amount`, `date`, `payment_month`, `patment_year`,`course_id`) VALUES (?,?,?,?,?,?)";

            stmt = con.prepareStatement(query);
            stmt.setString(1, lecID);
            stmt.setString(3, payDate);
            stmt.setDouble(2, amount);
            stmt.setString(4, paymntMonth);
            stmt.setInt(5, paymntYear);
            stmt.setString(6, courseID);
            count = stmt.executeUpdate();

        } catch (Exception ex) {

            cm.closeConnection(con);

            cm.closeStatements(stmt);
            //------------------------log Start--------------------------------------------
            logger.error(LectureDbConnection.class+".insertLecPayment("+lecID+","+payDate+","+amount+","+paymntMonth+","+paymntYear+","+courseID+") ->error"+ex);        
            //-------------------------log End---------------------------------------------
            throw ex;
        } finally {
            cm.closeConnection(con);

            cm.closeStatements(stmt);
            return count;
        }

    }

}
