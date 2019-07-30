/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.institute_management.expences;

import com.institute_management.DataSourse.DataSource;

import com.institute_management.util.CommonMethods;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

/**
 *
 * @author malindad
 */
public class dbConnections {

    CommonMethods cm = new CommonMethods();

    public int insertExpences(ExpenceBean ep) throws Exception {
        int count = 0;
        String query;
        PreparedStatement stmt=null;
        
        DataSource d=null;
        Connection con=null;
        try {
             d = new DataSource();
             con = d.getConnection();
            query = "INSERT INTO `expences`(`amount`, `expence_date`, `expence_type`, `remark`, `inserted_user`, `creation_date`) VALUES (?,?,?,?,?,now())";
            stmt = con.prepareStatement(query);
            stmt.setDouble(1, ep.getAmount());
            stmt.setString(2, ep.getExpenceDate());
            stmt.setString(3, ep.getType());
            stmt.setString(4, ep.getRemark());
            stmt.setString(5, ep.getInsertedUser());

            count = stmt.executeUpdate();

       } catch (Exception ex) {

            cm.closeConnection(con);            
            cm.closeStatements(stmt);
            throw ex;
        } finally {
            cm.closeConnection(con);
            cm.closeStatements(stmt);
            return count;
        }
        
    }
    
    public int insertExpenceType(String expence) throws Exception {
        int count = 0;
        String query;
        PreparedStatement stmt=null;
        ResultSet result=null;
        DataSource d=null;
        Connection con=null;
        try {
             d = new DataSource();
             con = d.getConnection();
            query = "SELECT  `expence_type` FROM `expences_types` WHERE `expence_type` = (?);";
            stmt = con.prepareStatement(query);
            stmt.setString(1, expence);
//            pst.setString(2, studentID);
//            pst.setInt(3, cardType);

            result = stmt.executeQuery();

            while (result.next()) {
                return 0;
            }

            query = "INSERT INTO `expences_types`(`expence_type`) VALUES (?)";
            stmt = con.prepareStatement(query);
            stmt.setString(1, expence);
            count = stmt.executeUpdate();

        } catch (Exception ex) {

            cm.closeConnection(con);
            cm.closeResult(result);
            cm.closeStatements(stmt);
            throw ex;
        } finally {
            cm.closeConnection(con);
            cm.closeResult(result);
            cm.closeStatements(stmt);
             return count;
        }
        
    }
    
    public HashMap<Integer, Object[]> getExpencesDetails() throws Exception {
        int count = 1;

        PreparedStatement stmt=null;
        ResultSet result=null;
        DataSource d=null;
        Connection con=null;
        int success;
        HashMap<Integer, Object[]> tableData = new HashMap<Integer, Object[]>();

        try {
             d = new DataSource();
             con = d.getConnection();
            String query = "select * from expences order by id desc";

            stmt = con.prepareStatement(query);

            result = stmt.executeQuery();
            while (result.next()) {
                Object[] rowData = new Object[5];

                rowData[0] = result.getString("expence_type");
                rowData[1] = result.getString("amount");
                rowData[2] = result.getString("expence_date");
                rowData[3] = result.getString("remark");
                rowData[4] = result.getString("inserted_user");
                tableData.put(count, rowData);
                count++;
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
            return tableData;
        }

        
    }

}
