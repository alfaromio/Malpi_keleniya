/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.institute_management.attendence;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author mihiran_p
 * @date May 25, 2017
 */
public class test {
    public static void main(String[] args) {
         int count = 0;
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-YYYY-HH:mm:ss");       
         DateFormat dateFormat1 = new SimpleDateFormat("EEEE");       
        String dateattendence=dateFormat.format(new Date());
         String dateattendence1=dateFormat1.format(new Date()).toLowerCase();
        System.out.println(dateattendence1);
        
        String[] s=dateattendence.split("\\-");
        System.out.println("helo");
    }

}
