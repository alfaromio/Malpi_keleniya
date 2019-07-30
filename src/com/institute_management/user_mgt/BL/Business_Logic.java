/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.institute_management.user_mgt.BL;

import com.institute_management.main.Main;
import com.institute_management.user_mgt.DB.UserDbConnection;
import com.institute_management.user_mgt.bean.userBean;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Business_Logic {

    public HashMap<Integer, Object[]> loadPrivilagesAssignTableData(HashMap<Integer, String> allPages, ArrayList<Integer> accessGrantedPageList) {
        int count = 0;
        HashMap<Integer, Object[]> tableData =new HashMap<Integer, Object[]>();
        
         for (Map.Entry entry : allPages.entrySet()) {
            Object[] rowData = new Object[2];
            count++;
            int key = (Integer) entry.getKey();
            String value = (String) entry.getValue();

            if (accessGrantedPageList.contains(key)) {
                rowData[0] = (boolean)true;
                rowData[1] = value;
            } else {
                rowData[0] = (boolean)false;
                rowData[1] = value;
            }
            
            tableData.put(count, rowData);

        }
        return  tableData;
    }

}
