/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.institute_management.util;

import com.institute_management.DataSourse.DataSource;
import com.institute_management.configurations.Config;
import com.institute_management.payment_mgt.NewPaymentDbConnection;
import static com.institute_management.util.Configurations.UserBean;
import static com.institute_management.util.Configurations.schoolList;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import org.apache.commons.lang.time.DateUtils;

/**
 *
 * @author mihiran_p
 * @date Feb 22, 2017
 */
public class CommonMethods {

    public static HashMap<Integer, String> monthMap = new HashMap<Integer, String>();

    public static void setMonthMap() {
        monthMap.put(1, "january");
        monthMap.put(2, "february");
        monthMap.put(3, "march");
        monthMap.put(4, "april");
        monthMap.put(5, "may");
        monthMap.put(6, "june");
        monthMap.put(7, "july");
        monthMap.put(8, "august");
        monthMap.put(9, "september");
        monthMap.put(10, "october");
        monthMap.put(11, "november");
        monthMap.put(12, "december");
    }
    public static boolean isNull(String text) throws Exception {
        boolean flag = true;

        if (text != null) {
            flag = false;
        }
        return flag;
    }

    public static boolean isEmpty(String text) throws Exception {
        boolean flag = true;

        if (text.trim() != "") {
            flag = false;
        }
        return flag;
    }

    /*
     * @author malinda_r
     * @date Feb 22, 2017
     */
    public static boolean check_user_access(int pageID) throws Exception {
        boolean accessStatus = false;
        ArrayList<Integer> pageAccessList = UserBean.getPrivilegeGrantedPageList();
        if (pageAccessList.contains(pageID)) {
            accessStatus = true;
        }
        return accessStatus;
    }
    
    public static void addSchoolsToStaticList() throws Exception {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Connection conn = null;
        try {
            DataSource d = new DataSource();
            conn = d.getConnection();
            String str1 = "";
            String str = "";

            
                str = "SELECT `school` FROM `school` order by school ASC";
                stmt = conn.prepareStatement(str);
                rs = stmt.executeQuery(str);
                schoolList.clear();
                while (rs.next()) {
                    str1 = rs.getString("school");
                    schoolList.add(str1);
                }
         } catch (Exception ex) {
            System.out.println("error" + ex);

            closeConnection(conn);
            closeResult(rs);
            closeStatements(stmt);
            throw ex;
        } finally {
            closeConnection(conn);
            closeResult(rs);
            closeStatements(stmt);
          
        }
    }

    public static int sendsms(String reciever, String text) throws MalformedURLException, IOException {
        int count = 0;
        //URL textit = new URL("http://textit.biz/sendmsg/index.php?id=94774271409pw=1737&to=" + reciever + "&text=" + text);
        
        
        URL textit = new URL("http://textit.biz/sendmsg/index.php?id=94774271409&pw=1737&to="+reciever+"&text="+text);
        BufferedReader in = new BufferedReader(
                new InputStreamReader(textit.openStream()));

        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            //  System.out.println(inputLine);
            count = 1;
        }
        in.close();
        return count;
    }
    public static String FormatStringSMS(String sms){
        String x = sms.replaceAll("\\s+","+");
        //System.out.println(x);
        return x;
    }
    
    
    public static ArrayList<String> sortOnTime(ArrayList<String[]> subjectList) throws Exception {

        String time;
        String subName;
        Date startTime;
        Date endTime;
        Date currentTime;
        DateFormat df = new SimpleDateFormat("HH:mm");
        String dateobj = df.format(new Date());

        Date date1 = df.parse(dateobj);

        //SimpleDateFormat format = new SimpleDateFormat("hh:mm");
        ArrayList<Date> beforeSort = new ArrayList<>();
        ArrayList<String> subList = new ArrayList<>();
        // ArrayList<String> subListAlready = new ArrayList<>();
        LinkedHashMap<String, ArrayList<String>> sub = new LinkedHashMap<>();
        int addMinuteTime = 45;
       
        //   String[][] words = new String[2][subjectList.size()];
        for (String[] strings : subjectList) {
            time = strings[1];

            subName = strings[0];
            String[] parts = time.split("-");
            startTime = (java.util.Date) df.parse(parts[0]);
            endTime = (java.util.Date) df.parse(parts[1]);
            endTime = DateUtils.addMinutes(endTime, addMinuteTime); //add minute

            if (endTime.before(date1)) {
                continue;
            }
            String startTime_string = startTime.toString();

            if (sub.containsKey(startTime_string)) {
                sub.get(startTime_string).add(subName);
            } else {
                ArrayList<String> subject = new ArrayList<>();
                subject.add(subName);
                sub.put(startTime_string, subject);
            }
            // sub.put(startTime, subName);
            beforeSort.add(startTime);

        }

        Collections.sort(beforeSort);
//
//        for (Date string : beforeSort) {
//            String string_date = string.toString();//convert date to string 
//            for (int j = 0; j < words[0].length; j++) {
//                if (words[0][j].equals(string_date)) {
//                    subList.add(words[1][j]);
//                }
//            }
//
//        }

        for (Map.Entry<String, ArrayList<String>> entry : sub.entrySet()) {
            String key = entry.getKey();
            ArrayList<String> value = entry.getValue();
            for (String string : value) {
                subList.add(string);
            }

        }
        return subList;
    }
    
     public static void closeConnection(Connection con)  {
        try{
         if(con!=null){
            con.close();
        }
        }catch(Exception ex){
            JOptionPane.showMessageDialog(new JFrame(), "Error Occured when closing connections. If this happens regulary, Plz Contatc ZEOGEN Help DESK");
        }
    }
     public static void closeStatements(PreparedStatement stmt)  {
        try{
         if(stmt!=null){
            stmt.close();
        }
        }catch(Exception ex){
            JOptionPane.showMessageDialog(new JFrame(), "Error Occured when closing Statements. If this happens regulary, Plz Contatc ZEOGEN Help DESK");
        }
    }
     public static void closeResult(ResultSet rs)  {
        try{
         if(rs!=null){
            rs.close();
        }
        }catch(Exception ex){
            JOptionPane.showMessageDialog(new JFrame(), "Error Occured when closing Statements. If this happens regulary, Plz Contatc ZEOGEN Help DESK");
        }
    }
    
    public static void debugSlownessIssueLog(String msg){
        //log//
        Config a = new Config();
        Format formatter = new SimpleDateFormat("yyyy-MM-dd");
        String s = formatter.format(new Date());
        
            try (FileWriter fw = new FileWriter(a.slownesDebuglog+s+".txt", true);
                BufferedWriter bw = new BufferedWriter(fw);
                    PrintWriter out = new PrintWriter(bw))
            {
            out.println(msg);
            } catch (IOException eee) {    }
    }
        
    
    
    public static boolean validateMobileNo(String parentContactNo) {
        boolean status = false;
        if(parentContactNo.length() == 9){
            parentContactNo = "0"+parentContactNo;
        }
        if (parentContactNo.matches("[0-9]+") && parentContactNo.length() == 10) {
            String prefix = parentContactNo.trim().substring(0,3);
            if(prefix.equals("070") || prefix.equals("071")||prefix.equals("072")||prefix.equals("075")||prefix.equals("076")||prefix.equals("077")||prefix.equals("078")){
                status = true;
            }
            
        }
        return status;
    }
    
    public static boolean validateLandNo(String parentContactNo) {
        boolean status = false;
        if (parentContactNo.matches("[0-9]+") && parentContactNo.length() == 10) {
           
            
                status = true;
            
            
        }
        return status;
    }

}
