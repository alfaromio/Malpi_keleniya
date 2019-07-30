/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.institute_management.DataSourse;

import com.institute_management.configurations.Config;
import static com.institute_management.user_mgt.UI.NewLogin2.logger;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Malinda Ranabahu
 */
public class DataSource {
    Config config = new Config();
    public Connection getConnection() throws Exception {
        Connection conn=null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/"+config.db, "root", config.pass);//
           //remote
          // conn = (Connection) DriverManager.getConnection("jdbc:mysql://192.168.8.102:3306/"+config.db, "root", config.pass);
        } catch (Exception ex) {
            //------------------------log Start--------------------------------------------
            logger.error(DataSource.class+".getConnection() ->error"+ex);        
            //-------------------------log End---------------------------------------------
            throw ex;
        }
        return conn;
    }
    
     public Connection getOnlineConnection() throws Exception {
        Connection conn=null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = (Connection) DriverManager.getConnection("jdbc:mysql://remotemysql.com:3306/OjHYCLlWRF","OjHYCLlWRF","SGLW4AG2Y2");//
           //conn = (Connection) DriverManager.getConnection("jdbc:mysql://166.62.28.97:3306/Initial_data_fetching_base", "zeogen", "zeogen@123");
        } catch (Exception ex) {
            //------------------------log Start--------------------------------------------
            logger.error(DataSource.class+".getConnection() ->error"+ex);        
            //-------------------------log End---------------------------------------------
            throw ex;
        }
        return conn;
    }
}
