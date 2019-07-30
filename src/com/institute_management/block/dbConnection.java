/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.institute_management.block;

import com.institute_management.DataSourse.DataSource;
import com.institute_management.configurations.Config;
import static com.institute_management.user_mgt.UI.NewLogin2.logger;
import com.institute_management.util.CommonMethods;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author malindad
 */
public class dbConnection {

    Config config = new Config();
    CommonMethods cm = new CommonMethods();
    String instituteID = new Config().instituteID;
    public boolean updateBackendBlockStatus(Connection Online_conn) throws SQLException {
        PreparedStatement stmt = null;
        ResultSet result = null;
        DataSource d = null;
        Connection Local_con = null;
        String Status = null;
        String NextBlockDate = null;

        SimpleDateFormat time_format = new SimpleDateFormat("yyyy-MM-dd");
        String curDate = time_format.format(new Date());

        boolean processStatus = false;

        try {
            d = new DataSource();
            Local_con = d.getConnection();

            Online_conn.setAutoCommit(false);
            Local_con.setAutoCommit(false);

            String query = "select * from block where ID = "+instituteID;
            stmt = Online_conn.prepareStatement(query);
            result = stmt.executeQuery();
            while (result.next()) {
                NextBlockDate = result.getString("NextBlockDate");
                Status = result.getString("status");
            }
            if (NextBlockDate == null) {

            } else {
                query = "update block set nextBlockDate = ?, status = ? where ID = "+instituteID;
                stmt = Local_con.prepareStatement(query);
                stmt.setString(1, NextBlockDate);
                stmt.setString(2, Status);
                int result2 = stmt.executeUpdate();
                if (result2 == 1) {
                    query = "update block set lastSyncDate = ?  where ID = "+instituteID;
                    stmt = Online_conn.prepareStatement(query);
                     stmt.setString(1, curDate);
                    int result3 = stmt.executeUpdate();
                    if (result3 == 1) {
                        Online_conn.commit();
                        Local_con.commit();
                        processStatus = true;
                    } else {
                        Online_conn.rollback();
                        Local_con.rollback();
                    }
                } else {
                    Online_conn.rollback();
                    Local_con.rollback();
                }
            }

        } catch (Exception ex) {

            cm.closeConnection(Local_con);
            cm.closeResult(result);
            cm.closeStatements(stmt);
            //------------------------log Start--------------------------------------------
            logger.error(dbConnection.class + ".updateBackendBlockStatus() ->error" + ex);
            //-------------------------log End---------------------------------------------
            throw ex;
        } finally {
            cm.closeConnection(Local_con);
            cm.closeResult(result);
            cm.closeStatements(stmt);
            Online_conn.setAutoCommit(true);
            return processStatus;
        }
    }

    public boolean compairBlockEligibility() {
        boolean eligible = false;

        PreparedStatement stmt = null;
        ResultSet result = null;
        DataSource d = null;
        Connection Local_con = null;
        String NextBlockDate = null;

        try {
            
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            String curDate = format.format(new Date());

            d = new DataSource();
            Local_con = d.getConnection();

            String query = "select * from block where ID = "+instituteID;
            stmt = Local_con.prepareStatement(query);
            result = stmt.executeQuery();
            while (result.next()) {
                NextBlockDate = result.getString("NextBlockDate");
            }
            Date dbDate = format.parse(NextBlockDate);
            Date date1 = format.parse(curDate);
            if (date1.compareTo(dbDate) >= 0) {
                eligible = true;
            }
        } catch (Exception ex) {

            cm.closeConnection(Local_con);
            cm.closeResult(result);
            cm.closeStatements(stmt);
            //------------------------log Start--------------------------------------------
            logger.error(dbConnection.class + ".compairBlockEligibility() ->error" + ex);
            //-------------------------log End---------------------------------------------
            throw ex;
        } finally {
            cm.closeConnection(Local_con);
            cm.closeResult(result);
            cm.closeStatements(stmt);
            return eligible;
        }
        
    }
    
    
    public String getBackendSystemStatus() {
        boolean eligible = false;

        PreparedStatement stmt = null;
        ResultSet result = null;
        DataSource d = null;
        Connection Local_con = null;
        String NextBlockDate = null;

        try {
            
            d = new DataSource();
            Local_con = d.getConnection();

            String query = "select * from block where ID = "+instituteID;
            stmt = Local_con.prepareStatement(query);
            result = stmt.executeQuery();
            while (result.next()) {
                NextBlockDate = result.getString("status");
            }
            
        } catch (Exception ex) {

            cm.closeConnection(Local_con);
            cm.closeResult(result);
            cm.closeStatements(stmt);
            //------------------------log Start--------------------------------------------
            logger.error(dbConnection.class + ".compairBlockEligibility() ->error" + ex);
            //-------------------------log End---------------------------------------------
            throw ex;
        } finally {
            cm.closeConnection(Local_con);
            cm.closeResult(result);
            cm.closeStatements(stmt);
            return NextBlockDate;
        }
        
    }
    
    public institutePaymentBean getCurrentBillDetails(Connection onlineConnection) {
        
        boolean eligible = false;
        PreparedStatement stmt = null;
        ResultSet result = null;
        DataSource d = null;
        institutePaymentBean bean = new institutePaymentBean();
        
       

        try {
            
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            String curDate = format.format(new Date());

            String query = "select * from institute_paymants where institute_ID = "+instituteID+" order by id desc limit 1";
            stmt = onlineConnection.prepareStatement(query);
            result = stmt.executeQuery();
            while (result.next()) {
                bean.setNextBlockDate(result.getString("due_date")); 
                bean.setTotal_bill(result.getDouble("total_bill"));
                bean.setPaymentAmount(result.getDouble("paymentAmount"));
                bean.setDue_amount(result.getDouble("due_amount"));
                
            }
            
        } catch (Exception ex) {
            cm.closeResult(result);
            cm.closeStatements(stmt);
            //------------------------log Start--------------------------------------------
            logger.error(dbConnection.class + ".compairBlockEligibility() ->error" + ex);
            //-------------------------log End---------------------------------------------
            throw ex;
        } finally {
            cm.closeResult(result);
            cm.closeStatements(stmt);
            return bean;
        }
        
    }
}
