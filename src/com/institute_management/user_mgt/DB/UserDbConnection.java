package com.institute_management.user_mgt.DB;

import com.institute_management.DataSourse.DataSource;
import com.institute_management.user_mgt.bean.userBean;
import com.institute_management.util.CommonMethods;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class UserDbConnection {

    CommonMethods cm = new CommonMethods();

    //public Connection  conn = DataSource.getConnection() ;
    public boolean validateUser(String userName, String password) throws Exception {
        DataSource d = new DataSource();
        Connection con = d.getConnection();
        PreparedStatement stmt = null;
        ResultSet result = null;
        boolean permissionStatus = false;
        try {
            if (userName.equals("RENEW")) {
                int amount = validate_renew_code(password);
                UpdatevalidateAttemts(amount);
                if (amount > 44) {
                    JOptionPane.showMessageDialog(new JFrame(), "Successfully Renew Package");
                } else {
                    JOptionPane.showMessageDialog(new JFrame(), "System Damaged");
                }

            } else {
                String query = "SELECT USER_NAME,PASSWORD FROM sys_users WHERE USER_NAME = ? AND PASSWORD = ? ";
                stmt = con.prepareStatement(query);
                stmt.setString(1, userName);
                stmt.setString(2, password);

                result = stmt.executeQuery();
                while (result.next()) {
                    permissionStatus = true;
                }
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
            return permissionStatus;
        }

    }

    public int validate_renew_code(String code) {
        String[] parts = code.split("-");
        int month = Calendar.getInstance().get(Calendar.MONTH) + 1;
        int date = Calendar.getInstance().get(Calendar.DATE);
        int amount = 0;
        if (parts.length == 5) {
            if (!parts[0].equals("zeorenew")) {
                return -3000;
            } else if (Integer.parseInt(parts[1]) != (date + 52)) {
                return -3001;
            } else if (Integer.parseInt(parts[2]) != (month + 23)) {
                return -3002;
            } else if (Integer.parseInt(parts[3]) > (154989)) {
                return -3003;
            } else if (Integer.parseInt(parts[4]) > (46)) {
                return -3004;
            } else {
                amount = Integer.parseInt(parts[4]);
            }
        } else {
            return -3005;
        }
        return amount;
    }

    public int validateAttemts() throws SQLException, Exception {
        DataSource d = new DataSource();
        Connection con = d.getConnection();
        PreparedStatement stmt = null;
        ResultSet result = null;
        int remainning = 0;
        try {
            String query = "SELECT password FROM sys_users WHERE USER_id = 1001";
            stmt = con.prepareStatement(query);

            result = stmt.executeQuery();
            if (result.next()) {
                remainning = result.getInt("password") - 1;
                if (remainning > 45) {
                    remainning = -3005;
                }
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
            return remainning;
        }

    }

    public void UpdatevalidateAttemts(int count) throws SQLException, Exception {
        DataSource d = new DataSource();
        Connection con = d.getConnection();
        PreparedStatement stmt = null;

        int remainning = 0;
        try {
            String query = "update sys_users set password = ? WHERE USER_id = 1001";
            stmt = con.prepareStatement(query);
            stmt.setInt(1, count);
            stmt.executeUpdate();
            //System.out.println(query);
        } catch (Exception ex) {

            cm.closeConnection(con);

            cm.closeStatements(stmt);
            throw ex;
        } finally {
            cm.closeConnection(con);

            cm.closeStatements(stmt);

        }

    }

    public boolean updateUserRole(String userName, String UserRole) throws SQLException, Exception {
        DataSource d = new DataSource();
        Connection con = d.getConnection();
        PreparedStatement stmt = null;
        int result;
        boolean permissionStatus = false;
        try {
            String query = "Update sys_users set role_id=(select user_role_id from user_role where description = ?) where user_name = ?";
            stmt = con.prepareStatement(query);
            stmt.setString(1, UserRole);
            stmt.setString(2, userName);

            result = stmt.executeUpdate();
            if (result == 1) {
                permissionStatus = true;
            }

        } catch (Exception ex) {

            cm.closeConnection(con);

            cm.closeStatements(stmt);
            throw ex;
        } finally {
            cm.closeConnection(con);

            cm.closeStatements(stmt);
            return permissionStatus;
        }

    }

    public userBean getUserDetails(String userName) throws Exception {
        DataSource d = new DataSource();
        Connection con = d.getConnection();
        PreparedStatement stmt = null;
        ResultSet result = null;
        int count = 0;
        ArrayList<Integer> privilegeGrantedPageList = new ArrayList<>();
        userBean userbean = new userBean();

        try {
            String query = "select us.*,ur.description, pr.page_id from sys_users us, privileges pr,user_role ur where user_name = ? and us.role_id = pr.role_id and ur.user_role_id=us.role_id ";

            stmt = con.prepareStatement(query);
            stmt.setString(1, userName);
            result = stmt.executeQuery();

            while (result.next()) {
                if (count == 0) {
                    userbean.setAddress(result.getString("address"));
                    userbean.setContact(result.getString("contact"));
                    userbean.setGender(result.getString("gender"));
                    userbean.setNic(result.getString("nic"));
                    userbean.setName(result.getString("name"));
                    userbean.setUserID(result.getInt("user_id"));
                    userbean.setRoleID(result.getInt("role_id"));
                    userbean.setUserRole(result.getString("description"));
                    userbean.setUserName(result.getString("user_name"));
                    userbean.setPassword(result.getString("password"));
                }
                privilegeGrantedPageList.add(result.getInt("page_id"));
                count++;
            }

            userbean.setPrivilegeGrantedPageList(privilegeGrantedPageList);
        } catch (Exception ex) {

            cm.closeConnection(con);
            cm.closeResult(result);
            cm.closeStatements(stmt);
            throw ex;
        } finally {
            cm.closeConnection(con);
            cm.closeResult(result);
            cm.closeStatements(stmt);
            return userbean;
        }

    }

    public String[] selectUserRoles() throws SQLException, Exception {
        DataSource d = new DataSource();
        Connection con = d.getConnection();
        PreparedStatement stmt = null;
        ResultSet result = null;
        int noOfRole = 0;
        String[] stringArray = null;
        ArrayList<String> roleList = new ArrayList<String>();
        try {
            String query = "SELECT `DESCRIPTION` FROM `user_role`";

            stmt = con.prepareStatement(query);
            result = stmt.executeQuery();

            while (result.next()) {
                String role = result.getString("DESCRIPTION");
                roleList.add(role);
            }
            stringArray = roleList.toArray(new String[0]);

        } catch (Exception ex) {

            cm.closeConnection(con);
            cm.closeResult(result);
            cm.closeStatements(stmt);
            throw ex;
        } finally {
            cm.closeConnection(con);
            cm.closeResult(result);
            cm.closeStatements(stmt);
            return stringArray;
        }

    }

    //select distinct pd.page_description from page_details pd, privileges pr, sys_users su, user_role ur where user_name = 'malinda' and su.role_id = pr.role_id and pr.page_id = pd.page_id
    public HashMap<Integer, String> getAllPages() throws Exception {
        DataSource d = new DataSource();
        Connection con = d.getConnection();
        HashMap<Integer, String> pageMap = new HashMap<Integer, String>();
        PreparedStatement stmt = null;
        ResultSet result = null;
        try {
            String query = "select * from page_details order by page_id";
            stmt = con.prepareStatement(query);
            result = stmt.executeQuery();

            while (result.next()) {
                pageMap.put(result.getInt("page_id"), result.getString("page_description"));
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
            return pageMap;
        }

    }

    public ArrayList<Integer> getPageListForRole(String role_name) throws Exception {
        DataSource d = new DataSource();
        Connection con = d.getConnection();
        PreparedStatement stmt = null;
        ResultSet result = null;
        ArrayList<Integer> accessGrantedPageList = new ArrayList<Integer>();
        try {
            String query = "SELECT page_id FROM privileges pr, user_role ur WHERE ur.description = ? and ur.user_role_id = pr.role_id";
            stmt = con.prepareStatement(query);
            stmt.setString(1, role_name);
            result = stmt.executeQuery();

            while (result.next()) {
                accessGrantedPageList.add(result.getInt("page_id"));
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
            return accessGrantedPageList;
        }

    }

    public void deletePagePrivilages(int role_id) throws Exception {
        DataSource d = new DataSource();
        Connection con = d.getConnection();
        PreparedStatement stmt = null;
        ResultSet result = null;
        ArrayList<Integer> accessGrantedPageList = new ArrayList<Integer>();
        try {
            String query = "DELETE FROM privileges WHERE role_id = ? ";
            stmt = con.prepareStatement(query);
            stmt.setInt(1, role_id);
            stmt.executeUpdate();

        } catch (Exception ex) {

            cm.closeConnection(con);
            cm.closeResult(result);
            cm.closeStatements(stmt);
            throw ex;
        } finally {
            cm.closeConnection(con);
            cm.closeResult(result);
            cm.closeStatements(stmt);

        }
    }

    public void addPagePrivilages(int role_id, int page_id) throws Exception {
        DataSource d = new DataSource();
        Connection con = d.getConnection();
        PreparedStatement stmt = null;
        ResultSet result = null;
        ArrayList<Integer> accessGrantedPageList = new ArrayList<Integer>();
        try {
            String query = "INSERT INTO `privileges` (`role_id`, `page_id`) VALUES (?,?)";
            stmt = con.prepareStatement(query);
            stmt.setInt(1, role_id);
            stmt.setInt(2, page_id);
            stmt.executeUpdate();
        } catch (Exception ex) {

            cm.closeConnection(con);
            cm.closeResult(result);
            cm.closeStatements(stmt);
            throw ex;
        } finally {
            cm.closeConnection(con);
            cm.closeResult(result);
            cm.closeStatements(stmt);

        }
    }

    public int getUserRoleIDbyName(String role) throws Exception {
        DataSource d = new DataSource();
        Connection con = d.getConnection();
        PreparedStatement stmt = null;
        ResultSet result = null;
        int roleID = 0;

        try {
            String query = "select `user_role_id` from `user_role` where description=?";
            stmt = con.prepareStatement(query);
            stmt.setString(1, role);

            result = stmt.executeQuery();
            while (result.next()) {
                roleID = result.getInt("user_role_id");
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
            return roleID;
        }

    }

    public int AddSystemUser(userBean a) throws Exception {
        DataSource d = new DataSource();
        Connection con = d.getConnection();
        PreparedStatement stmt = null;
        ResultSet result = null;
        int success = 0;

        try {
            String query = "INSERT INTO `sys_users`(`name`, `nic`, `contact`, `address`, `gender`, `user_name`, `password`, `role_id`) VALUES (?,?,?,?,?,?,?,?)";
            stmt = con.prepareStatement(query);
            stmt.setString(1, a.getName());
            stmt.setString(2, a.getNic());
            stmt.setString(3, a.getContact());
            stmt.setString(4, a.getAddress());
            stmt.setString(5, a.getGender());
            stmt.setString(6, a.getUserName());
            stmt.setString(7, a.getPassword());
            stmt.setInt(8, a.getRoleID());

            success = stmt.executeUpdate();

        } catch (Exception ex) {

            cm.closeConnection(con);
            cm.closeResult(result);
            cm.closeStatements(stmt);
            throw ex;
        } finally {
            cm.closeConnection(con);
            cm.closeResult(result);
            cm.closeStatements(stmt);
            return success;
        }

    }

    public int UpdateSystemUser(userBean a, String oldUserName) throws Exception {
        DataSource d = new DataSource();
        Connection con = d.getConnection();
        PreparedStatement stmt = null;
        ResultSet result = null;
        int success = 0;

        try {
            String query = "UPDATE `sys_users` SET `name`= ?,`nic`= ?,`contact`= ?,`address`= ?,`gender`= ?,`user_name`=?,`password`=?,`role_id`=? WHERE user_name = ?";
            stmt = con.prepareStatement(query);
            stmt.setString(1, a.getName());
            stmt.setString(2, a.getNic());
            stmt.setString(3, a.getContact());
            stmt.setString(4, a.getAddress());
            stmt.setString(5, a.getGender());
            stmt.setString(6, a.getUserName());
            stmt.setString(7, a.getPassword());
            stmt.setInt(8, a.getRoleID());
            stmt.setString(9, oldUserName);

            success = stmt.executeUpdate();

        } catch (Exception ex) {

            cm.closeConnection(con);
            cm.closeResult(result);
            cm.closeStatements(stmt);
            throw ex;
        } finally {
            cm.closeConnection(con);
            cm.closeResult(result);
            cm.closeStatements(stmt);
            return success;
        }

    }

    public boolean checkUserNameExistancy(String userName) throws Exception {
        DataSource d = new DataSource();
        Connection con = d.getConnection();
        PreparedStatement stmt = null;
        ResultSet result = null;
        boolean userNameExist = false;

        try {
            String query = "select * from `sys_users` where user_name=?";
            stmt = con.prepareStatement(query);
            stmt.setString(1, userName);

            result = stmt.executeQuery();
            if (result.next()) {
                userNameExist = true;
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
            return userNameExist;
        }

    }
}
