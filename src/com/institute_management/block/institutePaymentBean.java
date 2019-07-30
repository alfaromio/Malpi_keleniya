/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.institute_management.block;

/**
 *
 * @author malindad
 */
public class institutePaymentBean {

    private String NextBlockDate ;
    private double total_bill;
    private double due_amount;
    private double paymentAmount;

    /**
     * @return the NextBlockDate
     */
    public String getNextBlockDate() {
        return NextBlockDate;
    }

    /**
     * @param NextBlockDate the NextBlockDate to set
     */
    public void setNextBlockDate(String NextBlockDate) {
        this.NextBlockDate = NextBlockDate;
    }

    /**
     * @return the total_bill
     */
    public double getTotal_bill() {
        return total_bill;
    }

    /**
     * @param total_bill the total_bill to set
     */
    public void setTotal_bill(double total_bill) {
        this.total_bill = total_bill;
    }

    /**
     * @return the due_amount
     */
    public double getDue_amount() {
        return due_amount;
    }

    /**
     * @param due_amount the due_amount to set
     */
    public void setDue_amount(double due_amount) {
        this.due_amount = due_amount;
    }

    /**
     * @return the paymentAmount
     */
    public double getPaymentAmount() {
        return paymentAmount;
    }

    /**
     * @param paymentAmount the paymentAmount to set
     */
    public void setPaymentAmount(double paymentAmount) {
        this.paymentAmount = paymentAmount;
    }
}
