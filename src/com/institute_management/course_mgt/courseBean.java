/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.institute_management.course_mgt;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import javax.print.attribute.standard.DateTimeAtCompleted;


public class courseBean {
    private String courseID;
    private String courseDescription;
    private String lecturerName;
    private String courseMedium;
    private String subject;
    private double totalCourseFee;
    private double monthlyFee;
    private String grade;
    private int courseDuration;
    private String courseType;// theory/revision/paperclass
    private String LectureHole;
    private HashMap<String,classDaysBean> classDaysMap;
    private int batchNumber;
    private double paymentPrecentage;
    private String lecturerFirstName;

    /**
     * @return the courseDescription
     */
    public String getCourseDescription() {
        return courseDescription;
    }

    /**
     * @param courseDescription the courseDescription to set
     */
    public void setCourseDescription(String courseDescription) {
        this.courseDescription = courseDescription;
    }

    /**
     * @return the lecturerName
     */
    public String getLecturerName() {
        return lecturerName;
    }

    /**
     * @param lecturerName the lecturerName to set
     */
    public void setLecturerName(String lecturerName) {
        this.lecturerName = lecturerName;
    }

    /**
     * @return the courseMedium
     */
    public String getCourseMedium() {
        return courseMedium;
    }

    /**
     * @param courseMedium the courseMedium to set
     */
    public void setCourseMedium(String courseMedium) {
        this.courseMedium = courseMedium;
    }

    /**
     * @return the subject
     */
    public String getSubject() {
        return subject;
    }

    /**
     * @param subject the subject to set
     */
    public void setSubject(String subject) {
        this.subject = subject;
    }

    /**
     * @return the totalCourseFee
     */
    public double getTotalCourseFee() {
        return totalCourseFee;
    }

    /**
     * @param totalCourseFee the totalCourseFee to set
     */
    public void setTotalCourseFee(double totalCourseFee) {
        this.totalCourseFee = totalCourseFee;
    }

    /**
     * @return the monthlyFee
     */
    public double getMonthlyFee() {
        return monthlyFee;
    }

    /**
     * @param monthlyFee the monthlyFee to set
     */
    public void setMonthlyFee(double monthlyFee) {
        this.monthlyFee = monthlyFee;
    }

    /**
     * @return the grade
     */
    public String getGrade() {
        return grade;
    }

    /**
     * @param grade the grade to set
     */
    public void setGrade(String grade) {
        this.grade = grade;
    }

    /**
     * @return the courseDuration
     */
    public int getCourseDuration() {
        return courseDuration;
    }

    /**
     * @param courseDuration the courseDuration to set
     */
    public void setCourseDuration(int courseDuration) {
        this.courseDuration = courseDuration;
    }

    /**
     * @return the courseType
     */
    public String getCourseType() {
        return courseType;
    }

    /**
     * @param courseType the courseType to set
     */
    public void setCourseType(String courseType) {
        this.courseType = courseType;
    }

    /**
     * @return the LectureHole
     */
    public String getLectureHole() {
        return LectureHole;
    }

    /**
     * @param LectureHole the LectureHole to set
     */
    public void setLectureHole(String LectureHole) {
        this.LectureHole = LectureHole;
    }

    /**
     * @return the classDaysList
     */
    

    /**
     * @return the courseID
     */
    public String getCourseID() {
        return courseID;
    }

    /**
     * @param courseID the courseID to set
     */
    public void setCourseID(String courseID) {
        this.courseID = courseID;
    }

    /**
     * @return the batchNumber
     */
    public int getBatchNumber() {
        return batchNumber;
    }

    /**
     * @param batchNumber the batchNumber to set
     */
    public void setBatchNumber(int batchNumber) {
        this.batchNumber = batchNumber;
    }

    /**
     * @return the classDaysMap
     */
    public HashMap<String,classDaysBean> getClassDaysMap() {
        return classDaysMap;
    }

    /**
     * @param classDaysMap the classDaysMap to set
     */
    public void setClassDaysMap(HashMap<String,classDaysBean> classDaysMap) {
        this.classDaysMap = classDaysMap;
    }

    /**
     * @return the paymentPrecentage
     */
    public double getPaymentPrecentage() {
        return paymentPrecentage;
    }

    /**
     * @param paymentPrecentage the paymentPrecentage to set
     */
    public void setPaymentPrecentage(double paymentPrecentage) {
        this.paymentPrecentage = paymentPrecentage;
    }

    /**
     * @return the lecturerFirstName
     */
    public String getLecturerFirstName() {
        return lecturerFirstName;
    }

    /**
     * @param lecturerFirstName the lecturerFirstName to set
     */
    public void setLecturerFirstName(String lecturerFirstName) {
        this.lecturerFirstName = lecturerFirstName;
    }

  
}
