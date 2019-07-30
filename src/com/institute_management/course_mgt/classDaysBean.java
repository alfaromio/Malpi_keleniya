/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.institute_management.course_mgt;

import java.sql.Time;
import java.util.Date;

/**
 *
 * @author acer
 */
public class classDaysBean {
    private String Day;
    private Date startTime;
    private Date endTime;

    /**
     * @return the Day
     */
    public String getDay() {
        return Day;
    }

    /**
     * @param Day the Day to set
     */
    public void setDay(String Day) {
        this.Day = Day;
    }

    /**
     * @return the startTime
     */
    public Date getStartTime() {
        return startTime;
    }

    /**
     * @param startTime the startTime to set
     */
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    /**
     * @return the endTime
     */
    public Date getEndTime() {
        return endTime;
    }

    /**
     * @param endTime the endTime to set
     */
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

}