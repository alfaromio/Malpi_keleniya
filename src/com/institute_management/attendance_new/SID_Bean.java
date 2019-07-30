/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.institute_management.attendance_new;

/**
 *
 * @author malindad
 */
public class SID_Bean {
    
    private String s_id;
    private String card_number;
    private boolean manual_mode;

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
     * @return the card_number
     */
    public String getCard_number() {
        return card_number;
    }

    /**
     * @param card_number the card_number to set
     */
    public void setCard_number(String card_number) {
        this.card_number = card_number;
    }

    /**
     * @return the manual_mode
     */
    public boolean isManual_mode() {
        return manual_mode;
    }

    /**
     * @param manual_mode the manual_mode to set
     */
    public void setManual_mode(boolean manual_mode) {
        this.manual_mode = manual_mode;
    }
}
