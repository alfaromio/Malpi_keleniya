/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.institute_management.util;

import com.institute_management.user_mgt.bean.userBean;
import java.util.ArrayList;

/**
 *
 * @author Malinda Ranabahu
 */
public class Configurations {
    
    public static userBean UserBean = new userBean();
    

    //pagenames and IDs
    
    public static int Main_Page = 1;
    public static int User_Privileges_Assigning_Page = 2;
    public static int Subject_Management = 3;
    public static int Student_Management = 4;
    public static int Lecturer_Management = 5;
    public static int Attendance_Details = 6;
    public static int Course_Management = 7;
    public static int Payment_Management = 8;
    public static int Report_Module = 9;
    public static int Email_sms = 10;
    
    public static String userName;
    
    public static ArrayList<String> schoolList = new ArrayList<String>();
    
   
    

}
