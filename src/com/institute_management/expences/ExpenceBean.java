/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.institute_management.expences;

/**
 *
 * @author malindad
 */
public class ExpenceBean {
    
    private Double amount;
    private String expenceDate;
    private String remark;
    private String Type;
    private String InsertedUser;

    /**
     * @return the amount
     */
    public Double getAmount() {
        return amount;
    }

    /**
     * @param amount the amount to set
     */
    public void setAmount(Double amount) {
        this.amount = amount;
    }

    /**
     * @return the expenceDate
     */
    public String getExpenceDate() {
        return expenceDate;
    }

    /**
     * @param expenceDate the expenceDate to set
     */
    public void setExpenceDate(String expenceDate) {
        this.expenceDate = expenceDate;
    }

    /**
     * @return the remark
     */
    public String getRemark() {
        return remark;
    }

    /**
     * @param remark the remark to set
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * @return the Type
     */
    public String getType() {
        return Type;
    }

    /**
     * @param Type the Type to set
     */
    public void setType(String Type) {
        this.Type = Type;
    }

    /**
     * @return the InsertedUser
     */
    public String getInsertedUser() {
        return InsertedUser;
    }

    /**
     * @param InsertedUser the InsertedUser to set
     */
    public void setInsertedUser(String InsertedUser) {
        this.InsertedUser = InsertedUser;
    }
    
    
}
