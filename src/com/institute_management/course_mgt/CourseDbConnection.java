/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.institute_management.course_mgt;

import com.institute_management.DataSourse.DataSource;

import com.institute_management.student.BEAN.Student;
import static com.institute_management.user_mgt.UI.NewLogin2.logger;
import com.institute_management.util.CommonMethods;
import java.awt.Component;
import java.lang.reflect.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author acer
 */
public class CourseDbConnection {

    CommonMethods cm = new CommonMethods();

    public HashMap SearchLecturer(String id, String name, String nic, String mob) throws Exception {
        PreparedStatement stmt = null;
        ResultSet result = null;
        DataSource d = null;
        Connection con = null;
        HashMap map = new HashMap();
        id = id.equals("--") ? "" : id;
        name = name.equals("--") ? "" : name;
        nic = nic.equals("--") ? "" : nic;
        mob = mob.equals("--") ? "" : mob;
        String query = null;//query = "SELECT * from student where S_ID like '%" + id + "%' AND S_NAME like '%" + name + "%' AND S_NIC like '%" + nic + "%' AND S_TELEPHONE like '%" + mob + "%'";
        try {
            d = new DataSource();
            con = d.getConnection();
            if (!id.equals("")) {
                query = "SELECT * from lecturer where ID = " + id;
            } else if (!name.equals("")) {
                query = "SELECT * from lecturer where NAME = '" + name + "'";
            } else if (!nic.equals("")) {
                query = "SELECT * from lecturer where NIC = '" + nic + "'";
            } else if (!mob.equals("")) {
                query = "SELECT * from lecturer where contact = '" + mob + "'";
            }
            stmt = con.prepareStatement(query);

            result = stmt.executeQuery();
            while (result.next()) {
                map.put("name", (result.getString("name") == null) ? "--" : result.getString("NAME"));
                map.put("id", (result.getString("id") == null) ? "--" : result.getString("id"));
                map.put("contact", (result.getString("contact") == null) ? "--" : result.getString("contact"));
                map.put("nic", (result.getString("NIC") == null) ? "--" : result.getString("NIC"));

            }

        } catch (Exception ex) {

            cm.closeConnection(con);
            cm.closeResult(result);
            cm.closeStatements(stmt);
            //------------------------log Start--------------------------------------------
            logger.error(CourseDbConnection.class + ".SearchLecturer(" + id + "," + name + "," + nic + "," + mob + ") ->error" + ex);
            //-------------------------log End---------------------------------------------
            throw ex;
        } finally {
            cm.closeConnection(con);
            cm.closeResult(result);
            cm.closeStatements(stmt);
            return map;
        }

    }

    public String getActualBillID(String BillID) throws Exception {
        PreparedStatement stmt = null;
        ResultSet result = null;
        DataSource d = null;
        Connection con = null;
        String ID = null;
        int x = 0;
        try {
            d = new DataSource();
            con = d.getConnection();
            String query = "select bill_id from payment_bill_details where bill_id like '%" + BillID + "%' limit 1";
            stmt = con.prepareStatement(query);
            result = stmt.executeQuery();
            while (result.next()) {
                ID = result.getString("bill_id");

            }

        } catch (Exception ex) {
            cm.closeConnection(con);
            cm.closeResult(result);
            cm.closeStatements(stmt);
            //------------------------log Start--------------------------------------------

            //-------------------------log End---------------------------------------------
            throw ex;
        } finally {
            cm.closeConnection(con);
            cm.closeResult(result);
            cm.closeStatements(stmt);
            return ID;
        }

    }

    public int okcancel(String theMessage) {
        int result = JOptionPane.showConfirmDialog((Component) null, theMessage,
                "alert", JOptionPane.OK_CANCEL_OPTION);
        return result;
    }

    public void insertCourseData(courseBean bean) throws Exception {
        PreparedStatement stmt = null;
        ResultSet result = null;
        DataSource d = null;
        Connection con = null;

        try {
            d = new DataSource();
            con = d.getConnection();
            String query = "INSERT INTO `course`(`course_id`,`course_description`,subject_id,lecturer_id, "
                    + " `total_course_fee`, `monthly_fee`, `course_duration`, `grade`, `class_type`, `medium`,`batch_number`,`lecturer_payment_precentage`) VALUES "
                    + " (?,?,(Select SUBJECT_ID from subject where SUBJECT_NAME=?),(SELECT ID FROM LECTURER WHERE NAME = ?),?,?,?,?,?,?,?,?)";

            stmt = con.prepareStatement(query);
            stmt.setString(1, bean.getCourseID());
            stmt.setString(2, bean.getCourseDescription());
            stmt.setString(3, bean.getSubject());
            stmt.setString(4, bean.getLecturerName());
            stmt.setDouble(5, bean.getTotalCourseFee());
            stmt.setDouble(6, bean.getMonthlyFee());
            stmt.setInt(7, bean.getCourseDuration());
            stmt.setString(8, bean.getGrade());
            stmt.setString(9, bean.getCourseType());
            stmt.setString(10, bean.getCourseMedium());
            stmt.setInt(11, bean.getBatchNumber());
            stmt.setDouble(12, bean.getPaymentPrecentage());
            System.out.println(stmt);
            stmt.executeUpdate();

        } catch (Exception ex) {

            cm.closeConnection(con);
            cm.closeResult(result);
            cm.closeStatements(stmt);
            //------------------------log Start--------------------------------------------
            logger.error(CourseDbConnection.class + ".insertCourseData(" + bean + ") ->error" + ex);
            //-------------------------log End---------------------------------------------
            throw ex;
        } finally {
            cm.closeConnection(con);
            cm.closeResult(result);
            cm.closeStatements(stmt);

        }
    }

    public void insertClassDays(HashMap<String, classDaysBean> beanMap, String CourseId) throws Exception {
        PreparedStatement stmt = null;
        ResultSet result = null;
        DataSource d = null;
        Connection con = null;
        classDaysBean bean = new classDaysBean();
        SimpleDateFormat printFormat = new SimpleDateFormat("HH:mm");
        String values = "";//       VALUES (?,?,?,?,?,?,?,?)
        int count = 0;
        try {
            d = new DataSource();
            con = d.getConnection();
            Map<String, classDaysBean> map = beanMap;
            for (Map.Entry<String, classDaysBean> entry : map.entrySet()) {
                //System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
                count++;
                if (count == 1) {
                    bean = entry.getValue();
                    String StartEndTime = printFormat.format(bean.getStartTime()) + "-" + printFormat.format(bean.getEndTime());
                    String query = "Insert into courses_dates (`course_id`," + bean.getDay() + ") values (?,?)";
                    stmt = con.prepareStatement(query);
                    stmt.setString(1, CourseId);
                    stmt.setString(2, StartEndTime);
                    stmt.executeUpdate();
                } else if (count > 1) {
                    if (beanMap.size() > 1) {

                        bean = entry.getValue();
                        String StartEndTime = printFormat.format(bean.getStartTime()) + "-" + printFormat.format(bean.getEndTime());
                        String query = "update courses_dates set " + bean.getDay() + " = ? where `course_id` = ? ";
                        stmt = con.prepareStatement(query);
                        stmt.setString(1, StartEndTime);
                        stmt.setString(2, CourseId);
                        stmt.executeUpdate();

                    }
                }
            }
        } catch (Exception ex) {

            cm.closeConnection(con);
            cm.closeResult(result);
            cm.closeStatements(stmt);
            //------------------------log Start--------------------------------------------
            logger.error(CourseDbConnection.class + ".insertClassDays(" + beanMap + "," + CourseId + ") ->error" + ex);
            //-------------------------log End---------------------------------------------
            throw ex;
        } finally {
            cm.closeConnection(con);
            cm.closeResult(result);
            cm.closeStatements(stmt);

        }
    }

    public String GenerateCourseID(String Grade, String subject, String ClassType, String medium, String Lecturer, int batchNo, boolean nextYear) {
        String CourseID = null;
        try {
            int year = Calendar.getInstance().get(Calendar.YEAR);
            if (nextYear) {
                year = year + 1;

            } else {

            }

            String lecName = Lecturer.contains(" ") ? Lecturer.substring(0, Lecturer.indexOf(" ")) : Lecturer;
            CourseID = Grade.replace(" ", "") + "/" + subject + "/" + ClassType.charAt(0) + "/" + medium.charAt(0) + "/" + lecName + "/B" + (Integer.toString(batchNo)) + "(" + year + ")";
        } catch (Exception ex) {
            //------------------------log Start--------------------------------------------
            logger.error(CourseDbConnection.class + ".GenerateCourseID(" + Grade + "," + subject + "," + ClassType + "," + medium + "," + Lecturer + "," + batchNo + ") ->error" + ex);
            //-------------------------log End---------------------------------------------
        }
        return CourseID;
    }

    public boolean checkDuplicateCourseID(String CourseID) throws Exception {
        PreparedStatement stmt = null;
        ResultSet result = null;
        DataSource d = null;
        Connection con = null;
        boolean isDuplicateID = false;

        try {
            d = new DataSource();
            con = d.getConnection();
            String query = "SELECT * FROM COURSE WHERE COURSE_ID = ?";

            stmt = con.prepareStatement(query);
            stmt.setString(1, CourseID);
            result = stmt.executeQuery();
            if (result.next()) {
                isDuplicateID = true;
            }

        } catch (Exception ex) {

            cm.closeConnection(con);
            cm.closeResult(result);
            cm.closeStatements(stmt);
            //------------------------log Start--------------------------------------------
            logger.error(CourseDbConnection.class + ".checkDuplicateCourseID(" + CourseID + ") ->error" + ex);
            //-------------------------log End---------------------------------------------
            throw ex;
        } finally {
            cm.closeConnection(con);
            cm.closeResult(result);
            cm.closeStatements(stmt);
            return isDuplicateID;
        }

    }

    public int updatepaymentprecentage(String CourseID, double precentage) throws Exception {
        PreparedStatement stmt = null;
        ResultSet result = null;
        DataSource d = null;
        Connection con = null;
        int successe = 0;

        try {
            d = new DataSource();
            con = d.getConnection();
            String query = "UPDATE `course` SET `lecturer_payment_precentage`= ? WHERE course_id = ?";

            stmt = con.prepareStatement(query);
            stmt.setDouble(1, precentage);
            stmt.setString(2, CourseID);
            successe = stmt.executeUpdate();

        } catch (Exception ex) {

            cm.closeConnection(con);
            cm.closeResult(result);
            cm.closeStatements(stmt);
            //------------------------log Start--------------------------------------------
            logger.error(CourseDbConnection.class + ".updatepaymentprecentage(" + CourseID + "," + precentage + ") ->error" + ex);
            //-------------------------log End---------------------------------------------
            throw ex;
        } finally {
            cm.closeConnection(con);
            cm.closeResult(result);
            cm.closeStatements(stmt);
            return successe;
        }

    }

    public boolean checkstudentExistancy(String studentID) throws Exception {
        PreparedStatement stmt = null;
        ResultSet result = null;
        DataSource d = null;
        Connection con = null;
        boolean isExist = false;

        try {
            d = new DataSource();
            con = d.getConnection();
            String query = "SELECT * FROM student WHERE s_id = ?";

            stmt = con.prepareStatement(query);
            stmt.setString(1, studentID);
            result = stmt.executeQuery();
            if (result.next()) {
                isExist = true;
            }

        } catch (Exception ex) {

            cm.closeConnection(con);
            cm.closeResult(result);
            cm.closeStatements(stmt);
            //------------------------log Start--------------------------------------------
            logger.error(CourseDbConnection.class + ".checkstudentExistancy(" + studentID + ") ->error" + ex);
            //-------------------------log End---------------------------------------------
            throw ex;
        } finally {
            cm.closeConnection(con);
            cm.closeResult(result);
            cm.closeStatements(stmt);
            return isExist;
        }

    }

    public Student getStudentDetails(String studentID) throws Exception {
        PreparedStatement stmt = null;
        ResultSet result = null;
        DataSource d = null;
        Connection con = null;
        Student stdn = new Student();

        try {
            d = new DataSource();
            con = d.getConnection();
            String query = "SELECT * FROM student WHERE s_id = ?";

            stmt = con.prepareStatement(query);
            stmt.setString(1, studentID);
            result = stmt.executeQuery();
            if (result.next()) {
                stdn.setAddress(result.getString("S_ADDRESS"));
                //stdn.setDob(result.getString(""));
                // stdn.setEmail(result.getString(""));
                stdn.setGender(result.getString("S_GENDER"));
                stdn.setName(result.getString("S_NAME"));
                stdn.setSchool(result.getString("S_school"));
                stdn.setTelephn(result.getString("S_TELEPHONE"));
                stdn.setYearOfReg(result.getString("S_YOR"));
                stdn.setpContactNo(result.getString("S_PARENT_CONTACT_NO"));
                stdn.setStudentID(studentID);

            }

        } catch (Exception ex) {

            cm.closeConnection(con);
            cm.closeResult(result);
            cm.closeStatements(stmt);
            //------------------------log Start--------------------------------------------
            logger.error(CourseDbConnection.class + ".getStudentDetails(" + studentID + ") ->error" + ex);
            //-------------------------log End---------------------------------------------
            throw ex;
        } finally {
            cm.closeConnection(con);
            cm.closeResult(result);
            cm.closeStatements(stmt);
            return stdn;
        }

    }

    public boolean checkstudentinCourse(String studentID, String courseID) throws Exception {
        PreparedStatement stmt = null;
        ResultSet result = null;
        DataSource d = null;
        Connection con = null;
        boolean isExist = false;

        try {
            d = new DataSource();
            con = d.getConnection();
            String query = "SELECT * FROM `student-course` WHERE s_id = ?";

            stmt = con.prepareStatement(query);
            stmt.setString(1, studentID);
            result = stmt.executeQuery();
            while (result.next()) {
                String cID = result.getString("course_id");
                String status = result.getString("status");

                if (cID.equals(courseID) && status.equals("ACT")) {
                    isExist = true;
                    return isExist;
                }
            }

        } catch (Exception ex) {

            cm.closeConnection(con);
            cm.closeResult(result);
            cm.closeStatements(stmt);
            //------------------------log Start--------------------------------------------
            logger.error(CourseDbConnection.class + ".checkstudentinCourse(" + studentID + "," + courseID + ") ->error" + ex);
            //-------------------------log End---------------------------------------------
            throw ex;
        } finally {
            cm.closeConnection(con);
            cm.closeResult(result);
            cm.closeStatements(stmt);
            return isExist;
        }

    }

    public int studentRegistrationForCourse(String studentID, String courseID, int cardType,String fee) throws Exception {
        PreparedStatement stmt = null;
        ResultSet result = null;
        DataSource d = null;
        Connection con = null;
        
        int success = 0;

        try {
            d = new DataSource();
            con = d.getConnection();
            con.setAutoCommit(false);
            String query = "INSERT INTO `student-course`(`s_id`, `course_id`, `registation_date`,`cardType`, `status`) VALUES (?,?,CURDATE(),?,?)";

            stmt = con.prepareStatement(query);
            stmt.setString(1, studentID);
            stmt.setString(2, courseID);
            stmt.setInt(3, cardType);
            stmt.setString(4, "ACT");

            success = stmt.executeUpdate();
            if(success==1){
                
                int su = InserToPaymentActivationTable(studentID,courseID,cardType,Double.parseDouble(fee),con);
                if(su==1){
                    con.commit();
                }else{
                    con.rollback();
                }
            }

        } catch (Exception ex) {
            con.rollback();
            cm.closeConnection(con);
            cm.closeResult(result);
            cm.closeStatements(stmt);
            //------------------------log Start--------------------------------------------
            logger.error(CourseDbConnection.class + ".studentRegistrationForCourse(" + studentID + "," + courseID + "," + cardType + ") ->error" + ex);
            //-------------------------log End---------------------------------------------
            throw ex;
        } finally {
            cm.closeConnection(con);
            cm.closeResult(result);
            cm.closeStatements(stmt);
            return success;
        }

    }

    public int InserToPaymentActivationTable(String studentID, String courseID, int cardType,double fee,Connection con) throws Exception {
        PreparedStatement stmt = null;
        ResultSet result = null;
        DataSource d = null;
        int success = 0;
        Calendar cal = Calendar.getInstance();
        String curMonth = new SimpleDateFormat("MMMM").format(cal.getTime()).toLowerCase();
        String curYear = (new SimpleDateFormat("yyyy").format(cal.getTime())).toLowerCase();

        try {
            String query2 = "INSERT INTO `payments_activation_details`(`s_id`, `course_id`, `payment_month`, `payment_year`, `card_type`,`class_fee`,`elegible_fee`,`status`) VALUES (?,?,?,?,?,?,?,?)";
            stmt = con.prepareStatement(query2);
            stmt.setString(1, studentID);
            stmt.setString(2, courseID);
            stmt.setString(3, curMonth);
            stmt.setString(4, curYear);
            stmt.setString(5, cardType+"");
            stmt.setDouble(6, fee);
            stmt.setDouble(7, cardType==2?fee:(cardType==1?fee/2:(cardType==0?0:-1)));
            stmt.setString(8, "PENDING");
            success = stmt.executeUpdate();

        } catch (Exception ex) {

            
            cm.closeResult(result);
            cm.closeStatements(stmt);
            //------------------------log Start--------------------------------------------
            logger.error(CourseDbConnection.class + ".studentRegistrationForCourse(" + studentID + "," + courseID + "," + cardType + ") ->error" + ex);
            //-------------------------log End---------------------------------------------
            throw ex;
        } finally {
          
            cm.closeResult(result);
            cm.closeStatements(stmt);
            return success;
        }

    }

    public int studentDeleteFromCourse(String studentID, String courseID) throws Exception {
        PreparedStatement stmt = null;
        ResultSet result = null;
        DataSource d = null;
        Connection con = null;
        int success = 0;

        try {
            d = new DataSource();
            con = d.getConnection();
            String query = "DELETE FROM `student-course`  WHERE `s_id`=? and `course_id`=? and `status`='ACT'";

            stmt = con.prepareStatement(query);
            stmt.setString(1, studentID);
            stmt.setString(2, courseID);

            success = stmt.executeUpdate();

        } catch (Exception ex) {
            cm.closeConnection(con);
            cm.closeResult(result);
            cm.closeStatements(stmt);
            //------------------------log Start--------------------------------------------
            logger.error(CourseDbConnection.class + ".studentDeleteFromCourse(" + studentID + "," + courseID + ") ->error" + ex);
            //-------------------------log End---------------------------------------------
            throw ex;
        } finally {
            cm.closeConnection(con);
            cm.closeResult(result);
            cm.closeStatements(stmt);
            return success;
        }

    }

    public int updateStudentCardType(String studentID, String courseID, int cardType) throws Exception {
        PreparedStatement stmt = null;
        ResultSet result = null;
        DataSource d = null;
        Connection con = null;
        int success;

        try {
            d = new DataSource();
            con = d.getConnection();
            String query = "UPDATE `student-course` SET `cardType`=? WHERE `s_id`=? and  `status`=? and `course_id`=? ";

            stmt = con.prepareStatement(query);
            stmt.setInt(1, cardType);
            stmt.setString(2, studentID);
            stmt.setString(3, "ACT");
            stmt.setString(4, courseID);

            success = stmt.executeUpdate();

        } catch (Exception ex) {
            cm.closeConnection(con);
            cm.closeResult(result);
            cm.closeStatements(stmt);
            //------------------------log Start--------------------------------------------
            logger.error(CourseDbConnection.class + ".updateStudentCardType(" + studentID + "," + courseID + "," + cardType + ") ->error" + ex);
            //-------------------------log End---------------------------------------------
            throw ex;
        } finally {
            cm.closeConnection(con);
            cm.closeResult(result);
            cm.closeStatements(stmt);
            return success = 0;
        }

    }

    public courseBean classDetailsUpdate(String courseID) throws Exception {
        // SimpleDateFormat format = new SimpleDateFormat("dd/MMM/yy hh.mm aa");
        courseBean cb = new courseBean();
        HashMap<String, classDaysBean> map = new HashMap<String, classDaysBean>();
        PreparedStatement stmt = null;
        ResultSet result = null;
        DataSource d = null;
        Connection con = null;
        int success;
        SimpleDateFormat format = new SimpleDateFormat("HH:mm");
        try {
            d = new DataSource();
            con = d.getConnection();
            String query = "select * from courses_dates where course_id = ?";

            stmt = con.prepareStatement(query);
            stmt.setString(1, courseID);
            result = stmt.executeQuery();
            while (result.next()) {
                if (result.getString("monday") != null) {
                    classDaysBean cdays = new classDaysBean();
                    String temp = result.getString("monday");
                    String[] parts = temp.split("-");
                    cdays.setStartTime((java.util.Date) format.parse(parts[0]));
                    cdays.setEndTime((java.util.Date) format.parse(parts[1]));
                    cdays.setDay("monday");
                    map.put("monday", cdays);
                }
                if (result.getString("tuesday") != null) {
                    classDaysBean cdays = new classDaysBean();
                    String temp = result.getString("tuesday");
                    String[] parts = temp.split("-");
                    cdays.setStartTime((java.util.Date) format.parse(parts[0]));
                    cdays.setEndTime((java.util.Date) format.parse(parts[1]));
                    cdays.setDay("tuesday");
                    map.put("tuesday", cdays);
                }
                if (result.getString("wednesday") != null) {
                    classDaysBean cdays = new classDaysBean();
                    String temp = result.getString("wednesday");
                    String[] parts = temp.split("-");
                    cdays.setStartTime((java.util.Date) format.parse(parts[0]));
                    cdays.setEndTime((java.util.Date) format.parse(parts[1]));
                    cdays.setDay("wednesday");
                    map.put("wednesday", cdays);
                }
                if (result.getString("thursday") != null) {
                    classDaysBean cdays = new classDaysBean();
                    String temp = result.getString("thursday");
                    String[] parts = temp.split("-");
                    cdays.setStartTime((java.util.Date) format.parse(parts[0]));
                    cdays.setEndTime((java.util.Date) format.parse(parts[1]));
                    cdays.setDay("thursday");
                    map.put("thursday", cdays);
                }
                if (result.getString("friday") != null) {
                    classDaysBean cdays = new classDaysBean();
                    String temp = result.getString("friday");
                    String[] parts = temp.split("-");
                    cdays.setStartTime((java.util.Date) format.parse(parts[0]));
                    cdays.setEndTime((java.util.Date) format.parse(parts[1]));
                    cdays.setDay("friday");
                    map.put("friday", cdays);
                }
                if (result.getString("saturday") != null) {
                    classDaysBean cdays = new classDaysBean();
                    String temp = result.getString("saturday");
                    String[] parts = temp.split("-");
                    cdays.setStartTime(format.parse(parts[0]));
                    cdays.setEndTime((java.util.Date) format.parse(parts[1]));
                    cdays.setDay("saturday");
                    map.put("saturday", cdays);
                }
                if (result.getString("sunday") != null) {
                    classDaysBean cdays = new classDaysBean();
                    String temp = result.getString("sunday");
                    String[] parts = temp.split("-");
                    cdays.setStartTime((java.util.Date) format.parse(parts[0]));
                    cdays.setEndTime((java.util.Date) format.parse(parts[1]));
                    cdays.setDay("sunday");
                    map.put("sunday", cdays);
                }
                cb.setClassDaysMap(map);

            }
            PreparedStatement stmt1;
            ResultSet result1;
            String query1 = "Select total_course_fee,monthly_fee from course where course_id=?";
            stmt1 = con.prepareStatement(query1);
            stmt1.setString(1, courseID);
            result1 = stmt1.executeQuery();
            while (result1.next()) {
                cb.setCourseID(courseID);
                cb.setTotalCourseFee(result1.getDouble("total_course_fee"));
                cb.setMonthlyFee(result1.getDouble("monthly_fee"));
            }

        } catch (Exception ex) {
            cm.closeConnection(con);
            cm.closeResult(result);
            cm.closeStatements(stmt);
            //------------------------log Start--------------------------------------------
            logger.error(CourseDbConnection.class + ".classDetailsUpdate(" + courseID + ") ->error" + ex);
            //-------------------------log End---------------------------------------------
            throw ex;
        } finally {
            cm.closeConnection(con);
            cm.closeResult(result);
            cm.closeStatements(stmt);
            return cb;
        }
    }

    public int addExtraClass(String courseID, Date clzDate, String StartTime, String EndTime) throws Exception {
        PreparedStatement stmt = null;
        ResultSet result = null;
        DataSource d = null;
        Connection con = null;
        int success = 0;
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        String reportDate = df.format(clzDate);

        try {
            d = new DataSource();
            con = d.getConnection();
            String query = "INSERT INTO `extraclasses`(`courseID`, `date`, `startTime`, `endTime`, `status`) VALUES (?,?,?,?,?) ";

            stmt = con.prepareStatement(query);
            stmt.setString(1, courseID);
            stmt.setString(2, reportDate);
            stmt.setString(3, StartTime);
            stmt.setString(4, EndTime);
            stmt.setString(5, "ACT");

            success = stmt.executeUpdate();

        } catch (Exception ex) {
            cm.closeConnection(con);
            cm.closeResult(result);
            cm.closeStatements(stmt);
            //------------------------log Start--------------------------------------------
            logger.error(CourseDbConnection.class + ".addExtraClass(" + courseID + "," + clzDate + "," + StartTime + "," + EndTime + ") ->error" + ex);
            //-------------------------log End---------------------------------------------
            throw ex;
        } finally {
            cm.closeConnection(con);
            cm.closeResult(result);
            cm.closeStatements(stmt);
            return success;
        }

    }

    public HashMap<Integer, Object[]> getExtraClassDetails(String courseID) throws Exception {
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
            String query = "select * from extraclasses where `courseID`=? ";

            stmt = con.prepareStatement(query);
            stmt.setString(1, courseID);

            result = stmt.executeQuery();
            while (result.next()) {
                Object[] rowData = new Object[5];

                rowData[0] = result.getString("date");
                rowData[1] = result.getString("startTime");
                rowData[2] = result.getString("endTime");
                rowData[3] = result.getString("status");
                rowData[4] = (boolean) false;
                tableData.put(count, rowData);
                count++;
            }
        } catch (Exception ex) {
            cm.closeConnection(con);
            cm.closeResult(result);
            cm.closeStatements(stmt);
            //------------------------log Start--------------------------------------------
            logger.error(CourseDbConnection.class + ".getExtraClassDetails(" + courseID + ") ->error" + ex);
            //-------------------------log End---------------------------------------------
            throw ex;
        } finally {
            cm.closeConnection(con);
            cm.closeResult(result);
            cm.closeStatements(stmt);
            return tableData;
        }

    }

    public HashMap<Integer, Object[]> getCourseDetailsForTableView() throws Exception {
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
            String query = "select cs.course_id,cs.course_description,cs.class_type,cs.medium,(select name from lecturer where id = cs.lecturer_id)as lecturer,(select subject_name from subject where subject_id = cs.subject_id)as subject from course cs ";

            stmt = con.prepareStatement(query);

            result = stmt.executeQuery();
            while (result.next()) {
                Object[] rowData = new Object[6];

                rowData[0] = result.getString("course_id");
                rowData[1] = result.getString("course_description");
                rowData[2] = result.getString("subject");
                rowData[3] = result.getString("lecturer");
                rowData[4] = result.getString("class_type");
                rowData[5] = result.getString("medium");
                tableData.put(count, rowData);
                count++;
            }
        } catch (Exception ex) {
            cm.closeConnection(con);
            cm.closeResult(result);
            cm.closeStatements(stmt);
            //------------------------log Start--------------------------------------------
            logger.error(CourseDbConnection.class + ".getCourseDetailsForTableView() ->error" + ex);
            //-------------------------log End---------------------------------------------
            throw ex;
        } finally {
            cm.closeConnection(con);
            cm.closeResult(result);
            cm.closeStatements(stmt);
            return tableData;
        }

    }

    public void updateClassDays(HashMap<String, classDaysBean> beanMap, String CourseId) throws Exception {
        PreparedStatement stmt = null;
        ResultSet result = null;
        DataSource d = null;
        Connection con = null;

        try {
            d = new DataSource();
            con = d.getConnection();
            String query = "Delete from courses_dates where Course_id=?";
            stmt = con.prepareStatement(query);
            stmt.setString(1, CourseId);
            stmt.executeUpdate();

            insertClassDays(beanMap, CourseId);

        } catch (Exception ex) {
            cm.closeConnection(con);
            cm.closeResult(result);
            cm.closeStatements(stmt);
            //------------------------log Start--------------------------------------------
            logger.error(CourseDbConnection.class + ".validate_renew_code(" + beanMap + "," + CourseId + ") ->error" + ex);
            //-------------------------log End---------------------------------------------
            throw ex;
        } finally {
            cm.closeConnection(con);
            cm.closeResult(result);
            cm.closeStatements(stmt);

        }
    }

    public int updateCourseFee(String course_id, double monthlyFee, double totalFee) throws Exception {

        PreparedStatement stmt = null;
        ResultSet result = null;
        DataSource d = null;
        Connection con = null;
        int success = 0;

        try {
            d = new DataSource();
            con = d.getConnection();
            String query = " UPDATE `course` SET `total_course_fee`= ? ,`monthly_fee`= ? WHERE `course_id` = ?";

            stmt = con.prepareStatement(query);
            stmt.setDouble(1, totalFee);
            stmt.setDouble(2, monthlyFee);
            stmt.setString(3, course_id);

            success = stmt.executeUpdate();

        } catch (Exception ex) {
            cm.closeConnection(con);
            cm.closeResult(result);
            cm.closeStatements(stmt);
            //------------------------log Start--------------------------------------------
            logger.error(CourseDbConnection.class + ".updateCourseFee(" + course_id + "," + monthlyFee + "," + totalFee + ") ->error" + ex);
            //-------------------------log End---------------------------------------------
            throw ex;
        } finally {
            cm.closeConnection(con);
            cm.closeResult(result);
            cm.closeStatements(stmt);
            return success;
        }

    }

    public int deleteExtraClasses(String courseId, String date, String startTime) throws Exception {
        PreparedStatement stmt = null;
        ResultSet result = null;
        DataSource d = null;
        Connection con = null;
        int success = 0;
        try {
            d = new DataSource();
            con = d.getConnection();
            String query = "delete from extraclasses where courseid = ? and date = ? and startTime = ?";

            stmt = con.prepareStatement(query);
            stmt.setString(1, courseId);
            stmt.setString(2, date);
            stmt.setString(3, startTime);

            success = stmt.executeUpdate();

        } catch (Exception ex) {
            cm.closeConnection(con);
            cm.closeResult(result);
            cm.closeStatements(stmt);
            //------------------------log Start--------------------------------------------
            logger.error(CourseDbConnection.class + ".deleteExtraClasses(" + courseId + "," + date + "," + startTime + ") ->error" + ex);
            //-------------------------log End---------------------------------------------
            throw ex;
        } finally {
            cm.closeConnection(con);
            cm.closeResult(result);
            cm.closeStatements(stmt);
            return success;
        }
    }

    public int[] totalFreeCards(String courseID) throws Exception {
        PreparedStatement stmt = null;
        ResultSet result = null;
        DataSource d = null;
        Connection con = null;
        int success;
        int array[] = new int[4];
        try {
            d = new DataSource();
            con = d.getConnection();
            String query = "SELECT (select count(cardType) from `student-course` where cardType=0 and  course_id = ?) as free,(select count(cardType) from `student-course` where cardType=1 and  course_id = ? ) as half,(select count(cardType) from `student-course` where cardType=2  and  course_id =?) as normal, (select count(cardType) from `student-course` where  course_id =?)as total FROM `student-course` where course_id = ?";

            stmt = con.prepareStatement(query);
            stmt.setString(1, courseID);
            stmt.setString(2, courseID);
            stmt.setString(3, courseID);
            stmt.setString(4, courseID);
            stmt.setString(5, courseID);

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
            logger.error(CourseDbConnection.class + ".totalFreeCards(" + courseID + ") ->error" + ex);
            //-------------------------log End---------------------------------------------
            throw ex;
        } finally {
            cm.closeConnection(con);
            cm.closeResult(result);
            cm.closeStatements(stmt);
            return array;
        }
    }

    public courseBean getCourseDetails(String CourseID) throws Exception {

        PreparedStatement stmt = null;
        ResultSet result = null;
        DataSource d = null;
        Connection con = null;
        courseBean cb = new courseBean();

        try {
            d = new DataSource();
            con = d.getConnection();
            String query = "select co.*,su.subject_name,le.name from course co inner join subject su on co.subject_id = su.subject_id inner join lecturer le on co.lecturer_id = le.id where co.course_id ='" + CourseID + "'";

            stmt = con.prepareStatement(query);
            result = stmt.executeQuery();

            while (result.next()) {
                cb.setLecturerName(result.getString("name"));
                cb.setSubject(result.getString("subject_name"));
                cb.setGrade(result.getString("grade"));
                cb.setCourseType(result.getString("class_type"));
                cb.setCourseDescription(result.getString("course_description"));
                cb.setCourseMedium(result.getString("medium"));
                cb.setBatchNumber(result.getInt("batch_number"));
                cb.setMonthlyFee(result.getInt("monthly_fee"));
                cb.setTotalCourseFee(result.getInt("total_course_fee"));
                cb.setCourseID(CourseID);
            }

        } catch (Exception ex) {
            cm.closeConnection(con);
            cm.closeResult(result);
            cm.closeStatements(stmt);
            //------------------------log Start--------------------------------------------
            logger.error(CourseDbConnection.class + ".getCourseDetails(" + CourseID + ") ->error" + ex);
            //-------------------------log End---------------------------------------------
            throw ex;
        } finally {
            cm.closeConnection(con);
            cm.closeResult(result);
            cm.closeStatements(stmt);
            return cb;
        }
    }

    public String[] getStudentData(String StudentID, String CourseID) throws Exception {
        PreparedStatement stmt = null;
        ResultSet result = null;
        DataSource d = null;
        Connection con = null;
        String array[] = new String[2];

        try {
            d = new DataSource();
            con = d.getConnection();
            String query = "SELECT cardType,(select s_name from student where s_id = ?)as name FROM `student-course` WHERE s_ID = ? and course_id = ?";

            stmt = con.prepareStatement(query);
            stmt.setString(1, StudentID);
            stmt.setString(2, StudentID);
            stmt.setString(3, CourseID);
            result = stmt.executeQuery();

            while (result.next()) {
                if (result.getString("cardType").equals("0")) {
                    array[1] = "Free Card";
                } else if (result.getString("cardType").equals("1")) {
                    array[1] = "Half Card";
                } else if (result.getString("cardType").equals("2")) {
                    array[1] = "Normal Card";
                }
                array[0] = result.getString("name");
            }

        } catch (Exception ex) {
            cm.closeConnection(con);
            cm.closeResult(result);
            cm.closeStatements(stmt);
            //------------------------log Start--------------------------------------------
            logger.error(CourseDbConnection.class + ".getStudentData(" + StudentID + "," + CourseID + ") ->error" + ex);
            //-------------------------log End---------------------------------------------
            throw ex;
        } finally {
            cm.closeConnection(con);
            cm.closeResult(result);
            cm.closeStatements(stmt);
            return array;
        }
    }

    public HashMap SearchStudent(String id, String name, String nic, String mob) throws Exception {
        PreparedStatement stmt = null;
        ResultSet result = null;
        DataSource d = null;
        Connection con = null;
        HashMap map = new HashMap();
        id = id.equals("--") ? "" : id;
        name = name.equals("--") ? "" : name;
        nic = nic.equals("--") ? "" : nic;
        mob = mob.equals("--") ? "" : mob;
        String query = null;//query = "SELECT * from student where S_ID like '%" + id + "%' AND S_NAME like '%" + name + "%' AND S_NIC like '%" + nic + "%' AND S_TELEPHONE like '%" + mob + "%'";
        try {
            d = new DataSource();
            con = d.getConnection();
            if (!id.equals("")) {
                query = "SELECT * from student where S_ID = " + id;
            } else if (!name.equals("")) {
                query = "SELECT * from student where S_NAME = '" + name + "'";
            } else if (!nic.equals("")) {
                query = "SELECT * from student where S_NIC = '" + nic + "'";
            } else if (!mob.equals("")) {
                query = "SELECT * from student where S_TELEPHONE = '" + mob + "'";
            }
            stmt = con.prepareStatement(query);

            result = stmt.executeQuery();
            while (result.next()) {
                map.put("name", (result.getString("S_NAME") == null) ? "--" : result.getString("S_NAME"));
                map.put("address", (result.getString("S_ADDRESS") == null) ? "--" : result.getString("S_ADDRESS"));
                map.put("email", (result.getString("S_EMAIL") == null) ? "--" : result.getString("S_EMAIL"));
                map.put("gender", (result.getString("S_GENDER") == null) ? "--" : result.getString("S_GENDER"));
                map.put("mobile", (result.getString("S_TELEPHONE") == null) ? "--" : result.getString("S_TELEPHONE"));
                map.put("school", (result.getString("S_school") == null) ? "--" : result.getString("S_school"));
                map.put("nic", (result.getString("S_NIC") == null) ? "--" : result.getString("S_NIC"));
                map.put("regID", (result.getString("S_ID") == null) ? "--" : result.getString("S_ID"));
                map.put("pContact", (result.getString("S_PARENT_CONTACT_NO") == null) ? "--" : result.getString("S_PARENT_CONTACT_NO"));
                map.put("regDate", (result.getString("S_YOR") == null) ? "--" : result.getString("S_YOR"));

            }

        } catch (Exception ex) {
            cm.closeConnection(con);
            cm.closeResult(result);
            cm.closeStatements(stmt);
            //------------------------log Start--------------------------------------------
            logger.error(CourseDbConnection.class + ".totalFreeCards(" + id + "," + name + "," + nic + "," + mob + ") ->error" + ex);
            //-------------------------log End---------------------------------------------
            throw ex;
        } finally {
            cm.closeConnection(con);
            cm.closeResult(result);
            cm.closeStatements(stmt);
            return map;
        }

    }

    public ArrayList<String> getAllCourse(String StudentID) throws Exception {
        PreparedStatement stmt = null;
        ResultSet result = null;
        DataSource d = null;
        Connection con = null;
        ArrayList<String> list = new ArrayList<String>();

        try {
            d = new DataSource();
            con = d.getConnection();
            String query = "SELECT course_id from `student-course` where s_id = ? ";

            stmt = con.prepareStatement(query);
            stmt.setString(1, StudentID);

            result = stmt.executeQuery();

            while (result.next()) {
                list.add(result.getString("course_id"));
            }

        } catch (Exception ex) {
            cm.closeConnection(con);
            cm.closeResult(result);
            cm.closeStatements(stmt);
            //------------------------log Start--------------------------------------------
            logger.error(CourseDbConnection.class + ".getStudentData(" + StudentID + ") ->error" + ex);
            //-------------------------log End---------------------------------------------
            throw ex;
        } finally {
            cm.closeConnection(con);
            cm.closeResult(result);
            cm.closeStatements(stmt);
            return list;
        }
    }
}
