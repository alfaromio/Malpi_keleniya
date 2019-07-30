/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.institute_management.configurations;

/**
 *
 * @author Malinda Ranabahu
 */
public class Config {
    
    //desktop paths
    public String report_path = "F:\\ZEOGEN\\IMS\\REPORT\\";
    public String student_image_path = "F:\\ZEOGEN\\IMS\\IMAGES\\S_IMAGES\\";
    public String lecturer_image_path = "F:\\ZEOGEN\\IMS\\IMAGES\\L_IMAGES\\";
    public String image_uploader_default_path = "C:\\Users\\Nasa\\Desktop\\";
    
    public String slownesDebuglog = "F:\\ZEOGEN\\IMS\\debug\\";
    public String instituteID = "10";
    public boolean check_lecturer_attendance=false;
    
    //those two variables used in new attendance moule-->init page(to auto rederect to the main page it will check the input length)
    public  int student_RegID_length = 5;
    public  int card_number_length = 10;
    
    public String db = "institute_management_malpi_kelaniya";
    public String pass = "password";
    public String sqlBackupPath = "F:\\ZEOGEN\\IMS\\SQLBACKUP\\";
    public String audioFilePath = "F:\\ZEOGEN\\IMS\\NOT_PAID_SOUND\\test2.wav";
    public String audioFilePath_success = "F:\\ZEOGEN\\IMS\\NOT_PAID_SOUND\\test3.wav";
    //this sound uses no match found and errors
    public String audioFilePath_error = "F:\\ZEOGEN\\IMS\\NOT_PAID_SOUND\\test4.wav";
    public String audioFilePath_already= "F:\\ZEOGEN\\IMS\\NOT_PAID_SOUND\\test5.wav";
    
    
    public String SMS_Genaral_attendance =  "Malpi+Vidupiyasa+SMS+Alert-NLC-Dear+Parent,-NLC-Your+<TITLE>+<NAME>+safly+entered+mulpi+premises+at+<TIME>.";
    public String SMS_course_attendance =  "Malpi+Vidupiyasa+SMS+Alert-NLC-Name+:+<NAME>-NLC-Course+:+<COURSE>-NLC-Status+:+Attend-NLC-In+Time+:+<IN_TIME>-NLC-Out+Time+:+<OUT_TIME>.";
    public String SMS_Genaral_not_attendance = "Malpi+Vidupiyasa+SMS+Alert-NLC-Name+:+<NAME>-NLC-Course+:+<COURSE>-NLC-Status+:+NOT+Attend."; 
    /*
    //laptop paths
    
    public String report_path = "G:\\ZEOGEN\\IMS\\REPORT\\";
    public String student_image_path = "G:\\ZEOGEN\\IMS\\IMAGES\\S_IMAGES\\";
    public String lecturer_image_path = "G:\\ZEOGEN\\IMS\\IMAGES\\L_IMAGES\\";
    public String image_uploader_default_path = "C:\\Users\\UComputers\\Desktop\\";
    
    public String instituteID = "10";
    public boolean check_lecturer_attendance=false;
    
    //those two variables used in new attendance moule-->init page(to auto rederect to the main page it will check the input length)
    public  int student_RegID_length = 5;
    public  int card_number_length = 8;
    
    public String db = "institute_management_nasa";
    public String pass = "password";
    public String sqlBackupPath = "G:\\ZEOGEN\\IMS\\SQLBACKUP\\";
     public String audioFilePath = "G:\\ZEOGEN\\IMS\\NOT_PAID_SOUND\\test2.wav";
     public String audioFilePath_success = "G:\\ZEOGEN\\IMS\\NOT_PAID_SOUND\\test3.wav";
     //this sound uses no match found and errors
     public String audioFilePath_error = "G:\\ZEOGEN\\IMS\\NOT_PAID_SOUND\\test4.wav";
     public String audioFilePath_already= "G:\\ZEOGEN\\IMS\\NOT_PAID_SOUND\\test5.wav";
*/
}
