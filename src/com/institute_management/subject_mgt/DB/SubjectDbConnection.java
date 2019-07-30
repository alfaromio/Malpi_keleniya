/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.institute_management.subject_mgt.DB;

import com.institute_management.DataSourse.DataSource;


import com.institute_management.student.BEAN.Student;
import com.institute_management.subject_mgt.BEAN.SubjectBean;
import static com.institute_management.user_mgt.UI.NewLogin2.logger;
import com.institute_management.util.CommonMethods;
import static com.institute_management.util.CommonMethods.addSchoolsToStaticList;
import java.awt.Component;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
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
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

/**
 *
 * @author mihiran_p
 * @date Feb 22, 2017
 */
public class SubjectDbConnection {

    CommonMethods cm = new CommonMethods();

    /**
     *
     * @param sb
     * @return
     * @throws Exception
     */
    public int insertIntoSubject(SubjectBean sb) throws Exception {
        int count = 0;
        PreparedStatement pst = null;

        DataSource d = null;
        Connection connection = null;
        String query = null;
        try {
            d = new DataSource();
            connection = d.getConnection();
            query = "insert into subject(SUBJECT_NAME,SUBJECT_CODE) values (?,?)";
            pst = connection.prepareStatement(query);
            pst.setString(1, sb.getSubjectName());
            pst.setString(2, sb.getSubjectCode());
            count = pst.executeUpdate();

        } catch (Exception ex) {

            cm.closeConnection(connection);
            cm.closeStatements(pst);
            //------------------------log Start--------------------------------------------
            logger.error(SubjectDbConnection.class + ".insertIntoSubject(" + sb + ") ->error" + ex);
            //-------------------------log End---------------------------------------------
            throw ex;
        } finally {
            cm.closeConnection(connection);
            cm.closeStatements(pst);
            return count;
        }

    }
    
    
    public String getCardTypeBySID_CID(String S_ID,String C_ID) {
        String type =null;
        PreparedStatement pst = null;
        ResultSet result=null;
        DataSource d = null;
        Connection connection = null;
        String query = null;
        try {
            d = new DataSource();
            connection = d.getConnection();
            query = "Select cardType from `student-course` where s_id = ? and course_id = ?";
            pst = connection.prepareStatement(query);
            pst.setString(1, S_ID);
            pst.setString(2, C_ID);
            result = pst.executeQuery();
            while(result.next()){
                type = result.getString("cardType");
            }

        } catch (Exception ex) {

            cm.closeConnection(connection);
            cm.closeStatements(pst);
            //------------------------log Start--------------------------------------------
            logger.error(SubjectDbConnection.class + ".getCardTypeBySID_CID(" + type + ") ->error" + ex);
            //-------------------------log End---------------------------------------------
            throw ex;
        } finally {
            cm.closeConnection(connection);
            cm.closeStatements(pst);
            return type;
        }

    }
    
    public int UpdateCardType(String S_ID,String C_ID,String CardType) {
        String type =null;
        PreparedStatement pst = null;
        int result=0;
        DataSource d = null;
        Connection connection = null;
        String query = null;
        try {
            d = new DataSource();
            connection = d.getConnection();
            query = "Update `student-course` set cardType =? where s_id = ? and course_id = ?";
            pst = connection.prepareStatement(query);
            pst.setString(1, CardType);
            pst.setString(2, S_ID);
            pst.setString(3, C_ID);
            result = pst.executeUpdate();

        } catch (Exception ex) {

            cm.closeConnection(connection);
            cm.closeStatements(pst);
            //------------------------log Start--------------------------------------------
            logger.error(SubjectDbConnection.class + ".getCardTypeBySID_CID(" + type + ") ->error" + ex);
            //-------------------------log End---------------------------------------------
            throw ex;
        } finally {
            cm.closeConnection(connection);
            cm.closeStatements(pst);
            return result;
        }

    }

    public boolean checkSubjectExist(String name) throws Exception {
        int count = 0;
        PreparedStatement pst = null;

        DataSource d = null;
        Connection connection = null;
        String query = null;
        ResultSet rs = null;
        boolean isExist = false;
        try {
            d = new DataSource();
            connection = d.getConnection();
            query = "select SUBJECT_NAME from subject where SUBJECT_NAME = ?";
            pst = connection.prepareStatement(query);
            pst.setString(1, name);
            rs = pst.executeQuery();
            while (rs.next()) {
                isExist = true;
            }

        } catch (Exception ex) {

            cm.closeConnection(connection);
            cm.closeStatements(pst);
            cm.closeResult(rs);
            //------------------------log Start--------------------------------------------
            logger.error(SubjectDbConnection.class + ".checkexistancy(" + name + ") ->error" + ex);
            //-------------------------log End---------------------------------------------
            throw ex;
        } finally {
            cm.closeConnection(connection);
            cm.closeStatements(pst);
            cm.closeResult(rs);
            return isExist;
        }

    }

    public int deleteSubjectClasses(String code) throws Exception {
        PreparedStatement stmt = null;
        ResultSet result = null;
        DataSource d = null;
        Connection connection = null;
        int success = 0;
        try {
            d = new DataSource();
            connection = d.getConnection();
            String query = "delete from subject where SUBJECT_CODE = ? ";

            stmt = connection.prepareStatement(query);
            stmt.setString(1, code);

            success = stmt.executeUpdate();

        } catch (Exception ex) {

            cm.closeConnection(connection);
            cm.closeResult(result);
            cm.closeStatements(stmt);
            //------------------------log Start--------------------------------------------
            logger.error(SubjectDbConnection.class + ".deleteSubjectClasses(" + code + ") ->error" + ex);
            //-------------------------log End---------------------------------------------
            throw ex;
        } finally {
            cm.closeConnection(connection);
            cm.closeResult(result);
            cm.closeStatements(stmt);
            return success;
        }
    }

    /**
     *
     * @return @throws Exception
     */
    public ArrayList<Object[]> selectAllSubject() throws Exception {
        ArrayList<Object[]> subjectList = new ArrayList<Object[]>();
        Object[] tableData = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        DataSource d = null;
        Connection connection = null;
        String query = null;
        try {
            d = new DataSource();
            connection = d.getConnection();
            query = "SELECT SUBJECT_NAME,SUBJECT_CODE FROM subject";

            pst = connection.prepareStatement(query);

            rs = pst.executeQuery();
            while (rs.next()) {
                tableData = new Object[3];
                // tableData[0] = false;
                tableData[0] = rs.getString("SUBJECT_NAME");
                tableData[1] = rs.getString("SUBJECT_CODE");

                subjectList.add(tableData);
            }

        } catch (Exception ex) {

            cm.closeConnection(connection);
            cm.closeResult(rs);
            cm.closeStatements(pst);
            //------------------------log Start--------------------------------------------
            logger.error(SubjectDbConnection.class + ".selectAllSubject() ->error" + ex);
            //-------------------------log End---------------------------------------------
            throw ex;
        } finally {
            cm.closeConnection(connection);
            cm.closeResult(rs);
            cm.closeStatements(pst);
            return subjectList;
        }

    }

    public int insertStudent(Student st) throws Exception {
        int count = 0;
        PreparedStatement pst = null;
        ResultSet rs = null;
        DataSource d = null;
        Connection connection = null;
        String query = null;

        try {
            d = new DataSource();
            connection = d.getConnection();
            query = "INSERT INTO student(S_NAME, S_DOB, S_ADDRESS,S_ADDRESS2,S_ADDRESS3,S_EMAIL, S_GENDER, S_YOR, S_TELEPHONE, S_SCHOOL,S_ID,S_PARENT_CONTACT_NO,landline_number,S_FIRSTNAME) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            pst = connection.prepareStatement(query);
            pst.setString(1, st.getName());
            pst.setString(2, st.getDob());
            pst.setString(3, st.getAddress());
            pst.setString(4, st.getAddress1());
            pst.setString(5, st.getAddress2());
            pst.setString(6, st.getEmail());
            pst.setString(7, st.getGender());
            pst.setString(8, st.getYearOfReg());
            pst.setString(9, st.getTelephn());
            pst.setString(10, st.getSchool());
            pst.setInt(11, Integer.parseInt(st.getStudentID()));
            pst.setString(12, st.getpContactNo());
            pst.setString(13, st.getLandline_no());
            pst.setString(14, st.getFname());
            count = pst.executeUpdate();

        } catch (Exception ex) {

            cm.closeConnection(connection);
            cm.closeResult(rs);
            cm.closeStatements(pst);
            //------------------------log Start--------------------------------------------
            logger.error(SubjectDbConnection.class + ".deleteSubjectClasses(" + st + ") ->error" + ex);
            //-------------------------log End---------------------------------------------
            throw ex;
        } finally {
            cm.closeConnection(connection);
            cm.closeResult(rs);
            cm.closeStatements(pst);
            return count;
        }

    }

    public ArrayList<Student> selectAllStudent() throws Exception {
        ArrayList<Student> studentList = new ArrayList<Student>();
        Object[] tableData = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        DataSource d = null;
        Connection connection = null;
        String query = null;
        try {
            d = new DataSource();
            connection = d.getConnection();
            query = "SELECT  S_NAME,S_ID,card_number,S_GENDER,S_YOR,S_PARENT_CONTACT_NO,S_SCHOOL FROM student order by S_ID desc";

            pst = connection.prepareStatement(query);

            rs = pst.executeQuery();
            while (rs.next()) {
                Student st = new Student();
                //tableData[0] = false;
                st.setName(rs.getString("S_NAME"));
                st.setStudentID(rs.getString("S_ID"));
                st.setCardNumber(rs.getString("card_number"));
//                tableData[2] = rs.getString("S_ADDRESS");
//                tableData[3] = rs.getString("S_EMAIL");
//                tableData[4] = rs.getString("S_GENDER");
//                tableData[5] = rs.getString("S_YOR");
//                tableData[6] = rs.getString("S_TELEPHONE");
//                tableData[7] = rs.getString("S_SCHOOL");    
                st.setGender(rs.getString("S_GENDER"));
                st.setSchool(rs.getString("S_SCHOOL"));
                st.setYearOfReg(rs.getString("S_YOR"));
                st.setpContactNo(rs.getString("S_PARENT_CONTACT_NO"));

                studentList.add(st);
            }

        } catch (Exception ex) {

            cm.closeConnection(connection);
            cm.closeResult(rs);
            cm.closeStatements(pst);
            //------------------------log Start--------------------------------------------
            logger.error(SubjectDbConnection.class + ".selectAllStudent() ->error" + ex);
            //-------------------------log End---------------------------------------------
            throw ex;
        } finally {
            cm.closeConnection(connection);
            cm.closeResult(rs);
            cm.closeStatements(pst);
            return studentList;
        }

    }

    public int updateStudent(Student st) throws Exception {
        int count = 0;
        PreparedStatement pst = null;
        ResultSet rs = null;
        DataSource d = null;
        Connection connection = null;
        String query = null;

        try {
            d = new DataSource();
            connection = d.getConnection();

            query = "UPDATE student SET S_NAME=?,S_DOB=?,"
                    + "S_ADDRESS=?,S_ADDRESS2=?,S_ADDRESS3=?,S_EMAIL=?,S_GENDER=?,"
                    + "S_YOR=?,S_TELEPHONE=?,S_SCHOOL=?,S_FIRSTNAME=?,S_PARENT_EMAIL=?,landline_number=?,S_PARENT_CONTACT_NO=?,card_number=? WHERE S_ID=?";
            /* 
             query = "UPDATE student SET S_NAME=?,"
             + "S_ADDRESS=?,S_ADDRESS2=?,S_ADDRESS3=?,S_EMAIL=?"
             + ",S_TELEPHONE=?,S_SCHOOL=?,S_FIRSTNAME=?,card_number=? WHERE S_ID=?";
             */
            pst = connection.prepareStatement(query);
            pst.setString(1, st.getName());
            pst.setString(2, st.getDob());
            pst.setString(3, st.getAddress());
            pst.setString(4, st.getAddress1());
            pst.setString(5, st.getAddress2());
            pst.setString(6, st.getEmail());
            pst.setString(7, st.getGender());
            pst.setString(8, st.getYearOfReg());
            pst.setString(9, st.getTelephn());
            pst.setString(10, st.getSchool());
            pst.setString(11, st.getFname());
            pst.setString(12, st.getEmail());
            pst.setString(13, st.getLandline_no());
            pst.setString(14, st.getpContactNo());
            pst.setString(15, st.getCardNumber());
            pst.setString(16, st.getStudentID());
            count = pst.executeUpdate();

        } catch (Exception ex) {

            cm.closeConnection(connection);
            cm.closeResult(rs);
            cm.closeStatements(pst);
            //------------------------log Start--------------------------------------------
            logger.error(SubjectDbConnection.class + ".updateStudent(" + st + ") ->error" + ex);
            //-------------------------log End---------------------------------------------
            throw ex;
        } finally {
            cm.closeConnection(connection);
            cm.closeResult(rs);
            cm.closeStatements(pst);
            return count;
        }
    }

    public int updateParents(Student st) throws Exception {
        int count = 0;
        PreparedStatement pst = null;
        ResultSet rs = null;
        DataSource d = null;
        Connection connection = null;
        String query = null;

        try {
            d = new DataSource();
            connection = d.getConnection();
            /*
             query = "UPDATE student SET S_NAME=?,S_DOB=?,"
             + "S_ADDRESS=?,S_ADDRESS2=?,S_ADDRESS3=?,S_EMAIL=?,S_GENDER=?,"
             + "S_YOR=?,S_TELEPHONE=?,S_SCHOOL=?,S_FIRSTNAME=?,S_PARENT_EMAIL=?,S_PARENT_NAME=?,S_PARENT_CONTACT_NO=?,card_number=? WHERE S_ID=?";
             */
            query = "UPDATE student SET S_PARENT_EMAIL=?,S_PARENT_NAME=?,S_PARENT_CONTACT_NO=?,landline_number=? WHERE S_ID=?";
            pst = connection.prepareStatement(query);
            pst.setString(1, st.getParentEmail());
            pst.setString(2, st.getParentName());
            pst.setString(3, st.getpContactNo());
            pst.setString(4, st.getLandline_no());
            pst.setString(5, st.getStudentID());

            count = pst.executeUpdate();

        } catch (Exception ex) {

            cm.closeConnection(connection);
            cm.closeResult(rs);
            cm.closeStatements(pst);
            //------------------------log Start--------------------------------------------
            logger.error(SubjectDbConnection.class + ".updateParents(" + st + ") ->error" + ex);
            //-------------------------log End---------------------------------------------
            throw ex;
        } finally {
            cm.closeConnection(connection);
            cm.closeResult(rs);
            cm.closeStatements(pst);
            return count;
        }
    }

    public int deleteStudent(Student st) throws Exception {
        int count = 0;
        PreparedStatement pst = null;
        ResultSet rs = null;
        DataSource d = null;
        Connection connection = null;
        String query = null;

        try {
            d = new DataSource();
            connection = d.getConnection();
            query = "DELETE FROM `student` WHERE S_NAME=?";
            pst = connection.prepareStatement(query);
            pst.setString(1, st.getName());

            count = pst.executeUpdate();

        } catch (Exception ex) {

            cm.closeConnection(connection);
            cm.closeResult(rs);
            cm.closeStatements(pst);
            //------------------------log Start--------------------------------------------
            logger.error(SubjectDbConnection.class + ".deleteStudent(" + st + ") ->error" + ex);
            //-------------------------log End---------------------------------------------
            throw ex;
        } finally {
            cm.closeConnection(connection);
            cm.closeResult(rs);
            cm.closeStatements(pst);
            return count;
        }

    }

    public Student selectStudent(String regId) throws Exception {
        Student student = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        DataSource d = null;
        Connection connection = null;
        String query = null;
        try {
            d = new DataSource();
            connection = d.getConnection();
            query = "select payment_date,payment_amount, S_PARENT_EMAIL,S_PARENT_NAME,S_NAME,S_DOB,S_ADDRESS,S_ADDRESS2,S_ADDRESS3,S_EMAIL,S_GENDER,S_YOR,S_TELEPHONE,S_SCHOOL,student.S_ID,S_PARENT_CONTACT_NO,landline_number,S_FIRSTNAME,card_number from student left join admission on admission.s_id = student.s_id where student.S_ID=?";
            pst = connection.prepareStatement(query);
            pst.setString(1, regId);

            rs = pst.executeQuery();

            while (rs.next()) {
                student = new Student();

                student.setDob(rs.getString("S_DOB"));
                student.setEmail(rs.getString("S_EMAIL"));
                student.setGender(rs.getString("S_GENDER"));
                student.setName(rs.getString("S_NAME"));
                student.setSchool(rs.getString("S_SCHOOL"));
                student.setStudentID(rs.getString("S_ID"));
                student.setTelephn(rs.getString("S_TELEPHONE"));
                student.setYearOfReg(rs.getString("S_YOR"));
                student.setpContactNo(rs.getString("S_PARENT_CONTACT_NO"));
                student.setAddress(rs.getString("S_ADDRESS"));
                student.setParentEmail(rs.getString("S_PARENT_EMAIL"));
                student.setParentName(rs.getString("S_PARENT_NAME"));
                student.setFname(rs.getString("S_FIRSTNAME"));
                student.setAddress1(rs.getString("S_ADDRESS2"));
                student.setAddress2(rs.getString("S_ADDRESS3"));
                student.setLandline_no(rs.getString("landline_number"));
                if (rs.getString("payment_date") == null) {
                    student.setAdmissionStatuse("NOT PAID");

                } else {
                    student.setAdmissionStatuse("PAID");
                    student.setAdmissionDate((rs.getString("payment_date")));
                }
                student.setCardNumber(rs.getString("card_number"));

            }

        } catch (Exception e) {
            //------------------------log Start--------------------------------------------
            logger.error(SubjectDbConnection.class + ".deleteStudent(" + regId + ") ->error" + e);
            //-------------------------log End---------------------------------------------
            throw e;
        } finally {
            try {
                if (pst != null) {
                    try {
                        pst.close();
                    } catch (SQLException e) {
                        throw e;
                    }
                }
            } catch (Exception ex) {

                cm.closeConnection(connection);
                cm.closeResult(rs);
                cm.closeStatements(pst);
                //------------------------log Start--------------------------------------------
                logger.error(SubjectDbConnection.class + ".deleteStudent(" + regId + ") ->error" + ex);
                //-------------------------log End---------------------------------------------
                throw ex;
            } finally {
                cm.closeConnection(connection);
                cm.closeResult(rs);
                cm.closeStatements(pst);
                return student;
            }
        }

    }

    public ArrayList<String> getStudentList() throws Exception {
        ArrayList<String> studentList = new ArrayList<String>();
        PreparedStatement pst = null;
        ResultSet rs = null;
        DataSource d = null;
        Connection connection = null;
        String query = null;
        try {
            d = new DataSource();
            connection = d.getConnection();
            query = "SELECT S_ID FROM student";

            pst = connection.prepareStatement(query);
            rs = pst.executeQuery();
            studentList.add("--");

            while (rs.next()) {

                studentList.add(rs.getString("S_ID"));
            }

        } catch (Exception ex) {

            cm.closeConnection(connection);
            cm.closeResult(rs);
            cm.closeStatements(pst);
            //------------------------log Start--------------------------------------------
            logger.error(SubjectDbConnection.class + ".getStudentList() ->error" + ex);
            //-------------------------log End---------------------------------------------
            throw ex;
        } finally {
            cm.closeConnection(connection);
            cm.closeResult(rs);
            cm.closeStatements(pst);
            return studentList;
        }

    }

    public String getStudentNameOnId(String studentId) throws Exception {
        String studentName = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        DataSource d = null;
        Connection connection = null;
        String query = null;
        try {
            d = new DataSource();
            connection = d.getConnection();
            query = "SELECT S_NAME FROM student where S_ID=?";

            pst = connection.prepareStatement(query);
            pst.setString(1, studentId);
            rs = pst.executeQuery();

            while (rs.next()) {
                studentName = rs.getString("S_NAME");
            }

        } catch (Exception ex) {

            cm.closeConnection(connection);
            cm.closeResult(rs);
            cm.closeStatements(pst);
            //------------------------log Start--------------------------------------------
            logger.error(SubjectDbConnection.class + ".getStudentNameOnId(" + studentId + ") ->error" + ex);
            //-------------------------log End---------------------------------------------
            throw ex;
        } finally {
            cm.closeConnection(connection);
            cm.closeResult(rs);
            cm.closeStatements(pst);
            return studentName;
        }
    }

    public ArrayList<Student> getStudentsByName(String stdName) throws Exception {
        ArrayList<Student> studentList = new ArrayList<Student>();
        Object[] tableData = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        DataSource d = null;
        Connection connection = null;
        String query = null;
        try {
            d = new DataSource();
            connection = d.getConnection();
            query = "SELECT  S_NAME,S_ID,card_number,S_GENDER,S_YOR,S_PARENT_CONTACT_NO,S_SCHOOL FROM student where S_NAME LIKE ? or s_id like ? order by S_ID desc";

            pst = connection.prepareStatement(query);
            pst.setString(1, "%" + stdName + "%");
            pst.setString(2, "%" + stdName + "%");

            rs = pst.executeQuery();
            while (rs.next()) {
                Student st = new Student();
                //tableData[0] = false;
                st.setName(rs.getString("S_NAME"));
                st.setStudentID(rs.getString("S_ID"));
                st.setCardNumber(rs.getString("card_number"));
//                tableData[2] = rs.getString("S_ADDRESS");
//                tableData[3] = rs.getString("S_EMAIL");
//                tableData[4] = rs.getString("S_GENDER");
//                tableData[5] = rs.getString("S_YOR");
//                tableData[6] = rs.getString("S_TELEPHONE");
//                tableData[7] = rs.getString("S_SCHOOL");    
                st.setGender(rs.getString("S_GENDER"));
                st.setSchool(rs.getString("S_SCHOOL"));
                st.setYearOfReg(rs.getString("S_YOR"));
                st.setpContactNo(rs.getString("S_PARENT_CONTACT_NO"));

                studentList.add(st);
            }

        } catch (Exception ex) {

            cm.closeConnection(connection);
            cm.closeResult(rs);
            cm.closeStatements(pst);
            //------------------------log Start--------------------------------------------
            logger.error(SubjectDbConnection.class + ".getStudentsByName(" + stdName + ") ->error" + ex);
            //-------------------------log End---------------------------------------------
            throw ex;
        } finally {
            cm.closeConnection(connection);
            cm.closeResult(rs);
            cm.closeStatements(pst);
            return studentList;
        }

    }

    public BufferedImage getImageById(String id) {
        PreparedStatement pst = null;
        ResultSet rs = null;
        DataSource d = null;
        Connection connection = null;

        String query = "select S_IMAGE from student where S_ID = ?";
        BufferedImage img = null;
        try {
            d = new DataSource();
            connection = d.getConnection();
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, id);
            ResultSet result = stmt.executeQuery();
            byte[] bytes = null;
            if (result.next()) {
                bytes = result.getBytes(1);
            }
            if (bytes != null) {
                img = ImageIO.read(new ByteArrayInputStream(bytes));
            }

        } catch (Exception ex) {

            cm.closeConnection(connection);
            cm.closeResult(rs);
            cm.closeStatements(pst);
            //------------------------log Start--------------------------------------------
            logger.error(SubjectDbConnection.class + ".getImageById(" + id + ") ->error" + ex);
            //-------------------------log End---------------------------------------------
            throw ex;
        } finally {
            cm.closeConnection(connection);
            cm.closeResult(rs);
            cm.closeStatements(pst);
            return img;
        }

    }

    public ArrayList<String> getCourseList(String grade, String Subject) throws Exception {
        ArrayList<String> courseList = new ArrayList<String>();
        PreparedStatement pst = null;
        ResultSet rs = null;
        DataSource d = null;
        Connection connection = null;
        String query = null;
        try {
            d = new DataSource();
            connection = d.getConnection();
            query = "SELECT course_id FROM course where grade like '%" + grade + "%' and subject_id in (select subject_id from subject where subject_name like '%" + Subject + "%')";

            pst = connection.prepareStatement(query);
            rs = pst.executeQuery();
            courseList.add("--");

            while (rs.next()) {

                courseList.add(rs.getString("course_id"));
            }

        } catch (Exception ex) {

            cm.closeConnection(connection);
            cm.closeResult(rs);
            cm.closeStatements(pst);
            //------------------------log Start--------------------------------------------
            logger.error(SubjectDbConnection.class + ".getCourseList(" + grade + "," + Subject + ") ->error" + ex);
            //-------------------------log End---------------------------------------------
            throw ex;
        } finally {
            cm.closeConnection(connection);
            cm.closeResult(rs);
            cm.closeStatements(pst);
            return courseList;
        }

    }

    public ArrayList<String> getSubjectList() throws Exception {
        ArrayList<String> subjectList = new ArrayList<String>();
        PreparedStatement pst = null;
        ResultSet rs = null;
        DataSource d = null;
        Connection connection = null;
        String query = null;
        try {
            d = new DataSource();
            connection = d.getConnection();
            query = "SELECT subject_name FROM subject";

            pst = connection.prepareStatement(query);
            rs = pst.executeQuery();
            subjectList.add("--");

            while (rs.next()) {

                subjectList.add(rs.getString("subject_name"));
            }

        } catch (Exception ex) {

            cm.closeConnection(connection);
            cm.closeResult(rs);
            cm.closeStatements(pst);
            //------------------------log Start--------------------------------------------
            logger.error(SubjectDbConnection.class + ".getSubjectList() ->error" + ex);
            //-------------------------log End---------------------------------------------
            throw ex;
        } finally {
            cm.closeConnection(connection);
            cm.closeResult(rs);
            cm.closeStatements(pst);
            return subjectList;
        }

    }

    public ArrayList<String> getGradeList() throws Exception {
        ArrayList<String> subjectList = new ArrayList<String>();
        PreparedStatement pst = null;
        ResultSet rs = null;
        DataSource d = null;
        Connection connection = null;
        String query = null;
        try {
            d = new DataSource();
            connection = d.getConnection();
            query = "SELECT subject_name FROM subject";

            pst = connection.prepareStatement(query);
            rs = pst.executeQuery();
            subjectList.add("--");

            while (rs.next()) {

                subjectList.add(rs.getString("subject_name"));
            }

        } catch (Exception ex) {

            cm.closeConnection(connection);
            cm.closeResult(rs);
            cm.closeStatements(pst);
            //------------------------log Start--------------------------------------------
            logger.error(SubjectDbConnection.class + ".getGradeList() ->error" + ex);
            //-------------------------log End---------------------------------------------
            throw ex;
        } finally {
            cm.closeConnection(connection);
            cm.closeResult(rs);
            cm.closeStatements(pst);
            return subjectList;
        }

    }

    public String getCourseNameOnId(String corseID) throws Exception {
        String courseName = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        DataSource d = null;
        Connection connection = null;
        String query = null;
        try {
            d = new DataSource();
            connection = d.getConnection();
            query = "SELECT course_description FROM course where course_id=?";

            pst = connection.prepareStatement(query);
            pst.setString(1, corseID);
            rs = pst.executeQuery();

            while (rs.next()) {
                courseName = rs.getString("course_description");
            }
        } catch (Exception ex) {

            cm.closeConnection(connection);
            cm.closeResult(rs);
            cm.closeStatements(pst);
            //------------------------log Start--------------------------------------------
            logger.error(SubjectDbConnection.class + ".getCourseNameOnId(" + corseID + ") ->error" + ex);
            //-------------------------log End---------------------------------------------
            throw ex;
        } finally {
            cm.closeConnection(connection);
            cm.closeResult(rs);
            cm.closeStatements(pst);
            return courseName;
        }

    }

    public String getCourseFeeOnId(String corseID) throws Exception {
        String courseFee = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        DataSource d = null;
        Connection connection = null;
        String query = null;
        try {
            d = new DataSource();
            connection = d.getConnection();
            query = "SELECT monthly_fee FROM course where course_id=?";

            pst = connection.prepareStatement(query);
            pst.setString(1, corseID);
            rs = pst.executeQuery();

            while (rs.next()) {
                courseFee = rs.getString("monthly_fee");
            }
        } catch (Exception ex) {

            cm.closeConnection(connection);
            cm.closeResult(rs);
            cm.closeStatements(pst);
            //------------------------log Start--------------------------------------------
            logger.error(SubjectDbConnection.class + ".getCourseFeeOnId(" + corseID + ") ->error" + ex);
            //-------------------------log End---------------------------------------------
            throw ex;
        } finally {
            cm.closeConnection(connection);
            cm.closeResult(rs);
            cm.closeStatements(pst);
            return courseFee;
        }

    }

    public HashMap<Integer, Object[]> getCourseOnStudent(String studentID) throws Exception {
        int count = 1;

        PreparedStatement stmt = null;
        ResultSet result = null;
        DataSource d = null;
        Connection connection = null;
        String query = null;
        int success;
        HashMap<Integer, Object[]> tableData = new HashMap<Integer, Object[]>();

        try {
            d = new DataSource();
            connection = d.getConnection();
            query = "select `course_id` FROM `student-course` WHERE `S_ID`=?";

            stmt = connection.prepareStatement(query);
            stmt.setString(1, studentID);

            result = stmt.executeQuery();
            while (result.next()) {
                Object[] rowData = new Object[1];

                rowData[0] = result.getString("course_id");
                //   rowData[1] = (boolean) false;
                tableData.put(count, rowData);
                count++;
            }
        } catch (Exception ex) {

            cm.closeConnection(connection);
            cm.closeResult(result);
            cm.closeStatements(stmt);
            //------------------------log Start--------------------------------------------
            logger.error(SubjectDbConnection.class + ".getCourseOnStudent(" + studentID + ") ->error" + ex);
            //-------------------------log End---------------------------------------------
            throw ex;
        } finally {
            cm.closeConnection(connection);
            cm.closeResult(result);
            cm.closeStatements(stmt);
            return tableData;
        }

    }

    public int addStudentToACourse(Object selectedItem, String studentID, int cardType) throws Exception {
        int count = 0;
        PreparedStatement pst = null;
        ResultSet rs = null;
        DataSource d = null;
        Connection connection = null;
        String query = null;
        try {
            d = new DataSource();
            connection = d.getConnection();
            query = "INSERT INTO student-course(course_id, student_id, card_type) VALUES (?,?,?)";
            pst = connection.prepareStatement(query);
            pst.setString(1, selectedItem.toString());
            pst.setString(2, studentID);
            pst.setInt(3, cardType);

            count = pst.executeUpdate();

        } catch (Exception ex) {

            cm.closeConnection(connection);
            cm.closeResult(rs);
            cm.closeStatements(pst);
            //------------------------log Start--------------------------------------------
            logger.error(SubjectDbConnection.class + ".addStudentToACourse(" + selectedItem + "," + studentID + "," + cardType + ") ->error" + ex);
            //-------------------------log End---------------------------------------------
            throw ex;
        } finally {
            cm.closeConnection(connection);
            cm.closeResult(rs);
            cm.closeStatements(pst);
            return count;
        }

    }

    public HashMap<Integer, Object[]> getCourseDetailsOnStudent(String studentID) throws Exception {
        int count = 1;

        PreparedStatement stmt = null;
        ResultSet result = null;
        DataSource d = null;
        Connection connection = null;
        String query = null;
        int success;
        HashMap<Integer, Object[]> tableData = new HashMap<Integer, Object[]>();

        try {
            d = new DataSource();
            connection = d.getConnection();
            query = "select `course_id` FROM `student-course` WHERE `S_ID`=?";

            stmt = connection.prepareStatement(query);
            stmt.setString(1, studentID);

            result = stmt.executeQuery();
            while (result.next()) {
                Object[] rowData = new Object[1];

                rowData[0] = result.getString("course_id");
                //   rowData[1] = (boolean) false;
                tableData.put(count, rowData);
                count++;
            }
        } catch (Exception ex) {

            cm.closeConnection(connection);
            cm.closeResult(result);
            cm.closeStatements(stmt);
            //------------------------log Start--------------------------------------------
            logger.error(SubjectDbConnection.class + ".getCourseDetailsOnStudent(" + studentID + ") ->error" + ex);
            //-------------------------log End---------------------------------------------
            throw ex;
        } finally {
            cm.closeConnection(connection);
            cm.closeResult(result);
            cm.closeStatements(stmt);
            return tableData;
        }

    }

    public HashMap<Integer, Object[]> getCourseDetailsOnStudent(String sid, String date) throws Exception {
        int count = 1;

        PreparedStatement stmt = null;
        ResultSet result = null;
        DataSource d = null;
        Connection connection = null;
        String query = null;
        int success;
        HashMap<Integer, Object[]> tableData = new HashMap<Integer, Object[]>();

        try {
            d = new DataSource();
            connection = d.getConnection();
            query = "select cd.course_id as course_id,cd." + date + " as date  FROM `student-course` sc left join `courses_dates` cd on cd.course_id=sc.course_id  WHERE sc.S_ID = ? AND cd." + date + "<> ''";

            stmt = connection.prepareStatement(query);
            stmt.setString(1, sid);

            System.out.println(query);

            result = stmt.executeQuery();
            while (result.next()) {
                Object[] rowData = new Object[2];

                rowData[0] = result.getString("course_id");
                rowData[1] = result.getString("date");
                tableData.put(count, rowData);
                count++;
            }
        } catch (Exception ex) {

            cm.closeConnection(connection);
            cm.closeResult(result);
            cm.closeStatements(stmt);
            //------------------------log Start--------------------------------------------
            logger.error(SubjectDbConnection.class + ".getCourseDetailsOnStudent(" + sid + "," + date + ") ->error" + ex);
            //-------------------------log End---------------------------------------------
            throw ex;
        } finally {
            cm.closeConnection(connection);
            cm.closeResult(result);
            cm.closeStatements(stmt);
            return tableData;
        }

    }

    public ArrayList<String[]> getCourseDetailsOnDay(String day) throws Exception {
        ArrayList<String[]> subList = new ArrayList<String[]>();
        PreparedStatement stmt = null;
        ResultSet result = null;
        DataSource d = null;
        Connection connection = null;
        try {
            d = new DataSource();
            connection = d.getConnection();
            String query = "select cd.course_id as course_id,cd." + day + " as time  FROM  `courses_dates` cd   WHERE  cd." + day + "<> ''";

            stmt = connection.prepareStatement(query);
            //stmt.setString(1, sid);

            System.out.println(query);

            result = stmt.executeQuery();
            while (result.next()) {
                String[] rowData = new String[2];

                rowData[0] = result.getString("course_id");
                rowData[1] = result.getString("time");
//                tableData.put(count, rowData);
//                count++;

                subList.add(rowData);
            }
        } catch (Exception ex) {

            cm.closeConnection(connection);
            cm.closeResult(result);
            cm.closeStatements(stmt);
            //------------------------log Start--------------------------------------------
            logger.error(SubjectDbConnection.class + ".getCourseDetailsOnDay(" + day + ") ->error" + ex);
            //-------------------------log End---------------------------------------------
            throw ex;
        } finally {
            cm.closeConnection(connection);
            cm.closeResult(result);
            cm.closeStatements(stmt);
            return subList;
        }

    }

    public ArrayList<String> getCourseListOnStudent(String studentID) throws Exception {
        ArrayList<String> courseList = new ArrayList<String>();
        PreparedStatement pst = null;
        ResultSet rs = null;
        DataSource d = null;
        Connection connection = null;
        String query = null;
        try {
            d = new DataSource();
            connection = d.getConnection();
            query = "SELECT `course_id` FROM `student-course` WHERE `S_ID`=? AND `status` IN ('ACT');";

            pst = connection.prepareStatement(query);
            pst.setString(1, studentID);
            rs = pst.executeQuery();
            courseList.add("--");

            while (rs.next()) {

                courseList.add(rs.getString("course_id"));
            }

        } catch (Exception ex) {

            cm.closeConnection(connection);
            cm.closeResult(rs);
            cm.closeStatements(pst);
            //------------------------log Start--------------------------------------------
            logger.error(SubjectDbConnection.class + ".getCourseListOnStudent(" + studentID + ") ->error" + ex);
            //-------------------------log End---------------------------------------------
            throw ex;
        } finally {
            cm.closeConnection(connection);
            cm.closeResult(rs);
            cm.closeStatements(pst);
            return courseList;
        }

    }

    public int markAttendence(String selectedCourseId, String sid, Date date, int i) throws Exception {
        int count = 0;
        PreparedStatement pst = null;
        ResultSet rs = null;
        DataSource d = null;
        Connection connection = null;
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
        query1 = "select s_id from `attendence` where `attendence`= ? and `completeDate`= ? and `s_id` =? and `c_id`=?";
        query2 = "INSERT INTO `attendence`(`s_id`, `c_id`, `year`, `month`, `date`, `day`, `time`, `attendence`,`completeDate`) VALUES (?,?,?,?,?,?,?,?,?)";

        try {
            d = new DataSource();
            connection = d.getConnection();

            pst = connection.prepareStatement(query1);

            pst.setInt(1, i);
            pst.setString(2, dateattendence2);
            pst.setString(3, sid);
            pst.setString(4, selectedCourseId);

            rs = pst.executeQuery();
            if (!rs.isBeforeFirst()) {

                pst = connection.prepareStatement(query2);
                pst.setString(1, sid);
                pst.setString(2, selectedCourseId);
                pst.setString(3, s[2]);
                pst.setString(4, s[1]);
                pst.setString(5, s[0]);
                pst.setString(6, dateattendence1);
                pst.setString(7, s[3]);
                pst.setInt(8, i);
                pst.setString(9, dateattendence2);

                count = pst.executeUpdate();
            } else {

                count = -1;

            }
            
           

        } catch (Exception ex) {

            cm.closeConnection(connection);
            cm.closeResult(rs);
            cm.closeStatements(pst);
            //------------------------log Start--------------------------------------------
            logger.error(SubjectDbConnection.class + ".markAttendence(" + selectedCourseId + "," + selectedCourseId + "," + sid + "," + date + "," + i + ") ->error" + ex);
            //-------------------------log End---------------------------------------------
            throw ex;
        } finally {
            cm.closeConnection(connection);
            cm.closeResult(rs);
            cm.closeStatements(pst);
            return count;
        }

    }
    
    
    

    public String getCardType(String sid, String selectedCourseId) throws Exception {
        PreparedStatement pst = null;
        ResultSet rs = null;
        DataSource d = null;
        Connection connection = null;
        String query = null;
        String cardType = null;
        try {
            d = new DataSource();
            connection = d.getConnection();
            query = "SELECT cardType from `student-course`  WHERE `s_id` in(?) AND `course_id` in(?)";

            pst = connection.prepareStatement(query);
            pst.setString(1, sid);
            pst.setString(2, selectedCourseId);
            rs = pst.executeQuery();

            while (rs.next()) {

                if (rs.getString("cardType").equals("2")) {
                    cardType = "Normal Card";
                } else if (rs.getString("cardType").equals("1")) {
                    cardType = "Half Card";
                } else if (rs.getString("cardType").equals("0")) {
                    cardType = "Free Card";
                }

            }

        } catch (Exception ex) {

            cm.closeConnection(connection);
            cm.closeResult(rs);
            cm.closeStatements(pst);
            //------------------------log Start--------------------------------------------
            logger.error(SubjectDbConnection.class + ".getCardType(" + sid + "," + selectedCourseId + ") ->error" + ex);
            //-------------------------log End---------------------------------------------
            throw ex;
        } finally {
            cm.closeConnection(connection);
            cm.closeResult(rs);
            cm.closeStatements(pst);
            return cardType;
        }

    }

    public void insertSchool(String school) throws Exception {
        int count = 0;
        PreparedStatement pst = null;
        ResultSet rs = null;
        DataSource d = null;
        Connection connection = null;
        String query = null;
        try {
            d = new DataSource();
            connection = d.getConnection();
            query = "SELECT  `school` FROM `school` WHERE `school` in (?);";
            pst = connection.prepareStatement(query);
            pst.setString(1, school);
//            pst.setString(2, studentID);
//            pst.setInt(3, cardType);

            rs = pst.executeQuery();

            while (rs.next()) {
                return;
            }

            query = "INSERT INTO `school`(`school`) VALUES (?)";
            pst = connection.prepareStatement(query);
            pst.setString(1, school);
            count = pst.executeUpdate();
            addSchoolsToStaticList();

        } catch (Exception ex) {

            cm.closeConnection(connection);
            cm.closeResult(rs);
            cm.closeStatements(pst);
            //------------------------log Start--------------------------------------------
            logger.error(SubjectDbConnection.class + ".insertSchool(" + school + ") ->error" + ex);
            //-------------------------log End---------------------------------------------
            throw ex;
        } finally {
            cm.closeConnection(connection);
            cm.closeResult(rs);
            cm.closeStatements(pst);

        }
        // return count;
    }

    public int GenerateSID() throws Exception {
        int s_id = 0;
        PreparedStatement pst = null;
        ResultSet rs = null;
        DataSource d = null;
        Connection connection = null;
        String query = null;
        try {
            d = new DataSource();
            connection = d.getConnection();
            
            query = "SELECT  ifnull(max(s_id)+1,20001)s_id FROM student ;";
            

            pst = connection.prepareStatement(query);
            rs = pst.executeQuery();

            while (rs.next()) {
                s_id = rs.getInt("s_id");
            }

        } catch (Exception ex) {

            cm.closeConnection(connection);
            cm.closeResult(rs);
            cm.closeStatements(pst);
            //------------------------log Start--------------------------------------------
            logger.error(SubjectDbConnection.class + ".GenerateSID() ->error" + ex);
            //-------------------------log End---------------------------------------------
            throw ex;
        } finally {
            cm.closeConnection(connection);
            cm.closeResult(rs);
            cm.closeStatements(pst);
            return s_id;
        }
        // return count;
    }

    public int getLastsid() throws Exception {
        int count = 0;
        PreparedStatement pst = null;
        ResultSet rs = null;
        DataSource d = null;
        Connection connection = null;
        String query = null;
        try {
            d = new DataSource();
            connection = d.getConnection();
            query = "SELECT MAX(`S_ID`) as id FROM `student`";

            pst = connection.prepareStatement(query);
//            pst.setString(1, studentID);
            rs = pst.executeQuery();
            //  courseList.add("--");

            while (rs.next()) {

                count = rs.getInt("id");
            }

        } catch (Exception ex) {

            cm.closeConnection(connection);
            cm.closeResult(rs);
            cm.closeStatements(pst);
            //------------------------log Start--------------------------------------------
            logger.error(SubjectDbConnection.class + ".getLastsid() ->error" + ex);
            //-------------------------log End---------------------------------------------
            throw ex;
        } finally {
            cm.closeConnection(connection);
            cm.closeResult(rs);
            cm.closeStatements(pst);
            return count;
        }

    }

    public String getParentContactNo(Integer slist1) throws Exception {
        String conNo = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        DataSource d = null;
        Connection connection = null;
        String query = null;
        try {
            d = new DataSource();
            connection = d.getConnection();
            query = "SELECT `S_PARENT_CONTACT_NO` FROM `student` WHERE `S_ID`=?";

            pst = connection.prepareStatement(query);
            pst.setInt(1, slist1);

            rs = pst.executeQuery();

            while (rs.next()) {

                conNo = rs.getString("S_PARENT_CONTACT_NO");
            }

        } catch (Exception ex) {

            cm.closeConnection(connection);
            cm.closeResult(rs);
            cm.closeStatements(pst);
            //------------------------log Start--------------------------------------------
            logger.error(SubjectDbConnection.class + ".getParentContactNo(" + slist1 + ") ->error" + ex);
            //-------------------------log End---------------------------------------------
            throw ex;
        } finally {
            cm.closeConnection(connection);
            cm.closeResult(rs);
            cm.closeStatements(pst);
            return conNo;
        }

    }

    public ArrayList<Integer> getNotAttendSList(String text, Date date) throws Exception {
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-YYYY-HH:mm:ss");
        DateFormat dateFormat1 = new SimpleDateFormat("EEEE");
        String dateattendence = dateFormat.format(date);
        PreparedStatement pst = null;
        ResultSet rs = null;
        DataSource d = null;
        Connection connection = null;
        String query = null;
        //  System.out.println(dateattendence1);
        String[] s = dateattendence.split("\\-");

        ArrayList<Integer> slist = new ArrayList<Integer>();
        try {
            d = new DataSource();
            connection = d.getConnection();
            query = "SELECT S_ID FROM `student-course` WHERE `course_id`=? and `S_ID` NOT IN(SELECT `s_id` FROM `attendence` WHERE `c_id`=? and `year`=? and `month`=? AND `date`=? )";

            pst = connection.prepareStatement(query);
            pst.setString(1, text);
            pst.setString(2, text);
            pst.setString(3, s[2]);
            pst.setString(4, s[1]);
            pst.setString(5, s[0]);
            rs = pst.executeQuery();

            while (rs.next()) {

                slist.add(rs.getInt("S_ID"));
            }

        } catch (Exception ex) {

            cm.closeConnection(connection);
            cm.closeResult(rs);
            cm.closeStatements(pst);
            //------------------------log Start--------------------------------------------
            logger.error(SubjectDbConnection.class + ".getNotAttendSList(" + text + "," + date + ") ->error" + ex);
            //-------------------------log End---------------------------------------------
            throw ex;
        } finally {
            cm.closeConnection(connection);
            cm.closeResult(rs);
            cm.closeStatements(pst);
            return slist;
        }

    }

    public ArrayList<Integer> getStudentListOnCourseID(String courseId) throws Exception {
        PreparedStatement pst = null;
        ResultSet rs = null;
        DataSource d = null;
        Connection connection = null;
        String query = null;
        ArrayList<Integer> slist = new ArrayList<Integer>();
        try {
            d = new DataSource();
            connection = d.getConnection();
            query = "SELECT S_ID FROM `student-course` WHERE `course_id`=?";

            pst = connection.prepareStatement(query);
            pst.setString(1, courseId);

            rs = pst.executeQuery();

            while (rs.next()) {

                slist.add(rs.getInt("S_ID"));
            }

        } catch (Exception ex) {

            cm.closeConnection(connection);
            cm.closeResult(rs);
            cm.closeStatements(pst);
            //------------------------log Start--------------------------------------------
            logger.error(SubjectDbConnection.class + ".getStudentListOnCourseID(" + courseId + ") ->error" + ex);
            //-------------------------log End---------------------------------------------
            throw ex;
        } finally {
            cm.closeConnection(connection);
            cm.closeResult(rs);
            cm.closeStatements(pst);
            return slist;
        }

    }

    public int makeAdmission(String studentID, double admissionAmount) throws Exception {
        int count = 0;
        PreparedStatement pst = null;
        ResultSet rs = null;
        DataSource dd = null;
        Connection connection = null;
        String query = null;
        DateFormat dateFormat2 = new SimpleDateFormat("YYYY-MM-dd");
        Date d = new Date();
        try {
            dd = new DataSource();
            connection = dd.getConnection();
            query = "INSERT INTO `admission`(`s_id`, `payment_date`, `payment_amount`) VALUES (?,?,?);";
            pst = connection.prepareStatement(query);
            pst.setString(1, studentID);
            pst.setString(2, dateFormat2.format(d));
            pst.setDouble(3, admissionAmount);

            count = pst.executeUpdate();

        } catch (Exception ex) {

            cm.closeConnection(connection);
            cm.closeResult(rs);
            cm.closeStatements(pst);
            //------------------------log Start--------------------------------------------
            logger.error(SubjectDbConnection.class + ".makeAdmission(" + studentID + "," + admissionAmount + ") ->error" + ex);
            //-------------------------log End---------------------------------------------
            throw ex;
        } finally {
            cm.closeConnection(connection);
            cm.closeResult(rs);
            cm.closeStatements(pst);
            return count;
        }

    }

    public int checkAdmission(String stid) throws Exception {
        int count = 0;
        PreparedStatement pst = null;
        ResultSet rs = null;
        DataSource dd = null;
        Connection connection = null;
        String query = null;

        DateFormat dateFormat2 = new SimpleDateFormat("dd-MM-YY");
        Date d = new Date();
        try {
            dd = new DataSource();
            connection = dd.getConnection();
            query = "select * from admission where `s_id` = ?;";
            pst = connection.prepareStatement(query);
            pst.setString(1, stid);
            rs = pst.executeQuery();
            while (rs.next()) {
                count = 1;
            }

        } catch (Exception ex) {

            cm.closeConnection(connection);
            cm.closeResult(rs);
            cm.closeStatements(pst);
            //------------------------log Start--------------------------------------------
            logger.error(SubjectDbConnection.class + ".checkAdmission(" + stid + ") ->error" + ex);
            //-------------------------log End---------------------------------------------
            throw ex;
        } finally {
            cm.closeConnection(connection);
            cm.closeResult(rs);
            cm.closeStatements(pst);
            return count;
        }

    }

    public int okcancel(String theMessage) {
        int result = JOptionPane.showConfirmDialog((Component) null, theMessage,
                "alert", JOptionPane.OK_CANCEL_OPTION);
        return result;
    }

    public ArrayList<Integer> getAttendSList(String text, Date date) throws Exception {
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-YYYY-HH:mm:ss");
        DateFormat dateFormat1 = new SimpleDateFormat("EEEE");
        String dateattendence = dateFormat.format(date);

        //  System.out.println(dateattendence1);
        String[] s = dateattendence.split("\\-");

        ArrayList<Integer> slist = new ArrayList<Integer>();
        PreparedStatement pst = null;
        ResultSet rs = null;
        DataSource d = null;
        Connection connection = null;
        String query = null;
        try {
            d = new DataSource();
            connection = d.getConnection();
            query = "SELECT S_ID FROM `student-course` WHERE `course_id`=? and `S_ID`  IN(SELECT `s_id` FROM `attendence` WHERE `c_id`=? and `year`=? and `month`=? AND `date`=?  AND attendence=1)";

            pst = connection.prepareStatement(query);
            pst.setString(1, text);
            pst.setString(2, text);
            pst.setString(3, s[2]);
            pst.setString(4, s[1]);
            pst.setString(5, s[0]);
            rs = pst.executeQuery();

            while (rs.next()) {

                slist.add(rs.getInt("S_ID"));
            }

        } catch (Exception ex) {

            cm.closeConnection(connection);
            cm.closeResult(rs);
            cm.closeStatements(pst);
            //------------------------log Start--------------------------------------------
            logger.error(SubjectDbConnection.class + ".getAttendSList(" + text + "," + date + ") ->error" + ex);
            //-------------------------log End---------------------------------------------
            throw ex;
        } finally {
            cm.closeConnection(connection);
            cm.closeResult(rs);
            cm.closeStatements(pst);
            return slist;
        }

    }

    public String getStudentFirstNam(Integer slist1) throws Exception {
        String name = "--";
        PreparedStatement pst = null;
        ResultSet rs = null;
        DataSource d = null;
        Connection connection = null;
        String query = null;
        try {
            d = new DataSource();
            connection = d.getConnection();
            query = "SELECT S_FIRSTNAME FROM student where S_ID=?";

            pst = connection.prepareStatement(query);
            pst.setString(1, Integer.toString(slist1));
            rs = pst.executeQuery();

            while (rs.next()) {
                name = rs.getString("S_FIRSTNAME");
            }

        } catch (Exception ex) {

            cm.closeConnection(connection);
            cm.closeResult(rs);
            cm.closeStatements(pst);
            //------------------------log Start--------------------------------------------
            logger.error(SubjectDbConnection.class + ".getStudentFirstNam(" + slist1 + ") ->error" + ex);
            //-------------------------log End---------------------------------------------
            throw ex;
        } finally {
            cm.closeConnection(connection);
            cm.closeResult(rs);
            cm.closeStatements(pst);
            return name;
        }

    }
}
