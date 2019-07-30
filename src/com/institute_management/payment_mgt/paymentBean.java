/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.institute_management.payment_mgt;

import java.util.ArrayList;

/**
 *
 * @author tharindu_m
 */
public class paymentBean {

    private String courseID;
    private String s_id;
    private String Year;
    private ArrayList<String> month;
    private double amount;
    private String date;
    private int cardType;
    private String l_name;
    private String s_name;
    private String comment;
    private String monthYear;
    private String payment_initiate_interface;

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
     * @return the s_id
     */
    public String getS_id() {
        return s_id;
    }

    /**
     * @param s_id the s_id to set
     */
    public void setS_id(String s_id) {
        this.s_id = s_id;
    }

    /**
     * @return the Year
     */
    public String getYear() {
        return Year;
    }

    /**
     * @param Year the Year to set
     */
    public void setYear(String Year) {
        this.Year = Year;
    }

    /**
     * @return the month
     */
    /**
     * @return the amount
     */
    public double getAmount() {
        return amount;
    }

    /**
     * @param amount the amount to set
     */
    public void setAmount(double amount) {
        this.amount = amount;
    }

    /**
     * @return the date
     */
    public String getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * @return the month
     */
    public ArrayList<String> getMonth() {
        return month;
    }

    /**
     * @param month the month to set
     */
    public void setMonth(ArrayList<String> month) {
        this.month = month;
    }

    /**
     * @return the cardType
     */
    public int getCardType() {
        return cardType;
    }

    /**
     * @param cardType the cardType to set
     */
    public void setCardType(String cardTypeS) {

        if (cardTypeS.equals("Half")) {
            this.cardType = 1;
        } else if (cardTypeS.equals("Free")) {
            this.cardType = 0;
        } else {
            this.cardType = 2;
        }
    }

    /**
     * @return the l_name
     */
    public String getL_name() {
        return l_name;
    }
    /**
     * @param l_name the l_name to set
     */
    public void setL_name(String l_name) {
        this.l_name = l_name;
    }

    /**
     * @return the s_name
     */
    public String getS_name() {
        return s_name;
    }

    /**
     * @param s_name the s_name to set
     */
    public void setS_name(String s_name) {
        this.s_name = s_name;
    }

    /**
     * @return the comment
     */
    public String getComment() {
        return comment;
    }

    /**
     * @param comment the comment to set
     */
    public void setComment(String comment) {
        this.comment = comment;
    }

    /**
     * @return the monthYear
     */
    public String getMonthYear() {
        return monthYear;
    }

    /**
     * @param monthYear the monthYear to set
     */
    public void setMonthYear(String monthYear) {
        this.monthYear = monthYear;
    }

    /**
     * @return the payment_initiate_interface
     */
    public String getPayment_initiate_interface() {
        return payment_initiate_interface;
    }

    /**
     * @param payment_initiate_interface the payment_initiate_interface to set
     */
    public void setPayment_initiate_interface(String payment_initiate_interface) {
        this.payment_initiate_interface = payment_initiate_interface;
    }

}
