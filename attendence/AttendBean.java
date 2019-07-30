/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.institute_management.attendence;

/**
 *
 * @author mihiran_p
 * @date May 25, 2017
 */
public class AttendBean {

    private String day;
    private String time;
    private int attend;
    private int date;
    

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }


    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public int getAttend() {
        return attend;
    }

    public void setAttend(int attend) {
        this.attend = attend;
    }

}
