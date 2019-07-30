

package com.institute_management.attendance_new;

import java.util.ArrayList;

/**
 *
 * @author malindad
 */
public class SelectedCourseStudentDetails {

    /**
     * @return the sms_contact1
     */
    public String getSms_contact1() {
        return sms_contact1;
    }

    /**
     * @param sms_contact1 the sms_contact1 to set
     */
    public void setSms_contact1(String sms_contact1) {
        this.sms_contact1 = sms_contact1;
    }

    /**
     * @return the sms_contact2
     */
    public String getSms_contact2() {
        return sms_contact2;
    }

    /**
     * @param sms_contact2 the sms_contact2 to set
     */
    public void setSms_contact2(String sms_contact2) {
        this.sms_contact2 = sms_contact2;
    }

    /**
     * @return the s_firstName
     */
    public String getS_firstName() {
        return s_firstName;
    }

    /**
     * @param s_firstName the s_firstName to set
     */
    public void setS_firstName(String s_firstName) {
        this.s_firstName = s_firstName;
    }

    /**
     * @return the s_gender
     */
    public String getS_gender() {
        return s_gender;
    }

    /**
     * @param s_gender the s_gender to set
     */
    public void setS_gender(String s_gender) {
        this.s_gender = s_gender;
    }
    private String s_name;
    private String s_firstName;
    private String s_gender;
    private String s_id;
    private String s_contact;
    private String s_address;
    private String s_reg_date;
    private String s_school;
    private String sms_contact1;
    private String sms_contact2;
    
    private String l_name;
    private String l_id;
    
    private String c_card_type;
    private String c_id;
    private String c_type;//extra class or not
    private String c_time;
    private String c_last_payment;
    private String c_fee;
    private String c_type_r_or_t;// class type revision or theory
    private ArrayList<String> c_all_courses;

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
     * @return the s_contact
     */
    public String getS_contact() {
        return s_contact;
    }

    /**
     * @param s_contact the s_contact to set
     */
    public void setS_contact(String s_contact) {
        this.s_contact = s_contact;
    }

    /**
     * @return the s_address
     */
    public String getS_address() {
        return s_address;
    }

    /**
     * @param s_address the s_address to set
     */
    public void setS_address(String s_address) {
        this.s_address = s_address;
    }

    /**
     * @return the s_reg_date
     */
    public String getS_reg_date() {
        return s_reg_date;
    }

    /**
     * @param s_reg_date the s_reg_date to set
     */
    public void setS_reg_date(String s_reg_date) {
        this.s_reg_date = s_reg_date;
    }

    /**
     * @return the s_card_type
     */
  

    /**
     * @return the s_school
     */
    public String getS_school() {
        return s_school;
    }

    /**
     * @param s_school the s_school to set
     */
    public void setS_school(String s_school) {
        this.s_school = s_school;
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
     * @return the l_id
     */
    public String getL_id() {
        return l_id;
    }

    /**
     * @param l_id the l_id to set
     */
    public void setL_id(String l_id) {
        this.l_id = l_id;
    }

    /**
     * @return the c_id
     */
    public String getC_id() {
        return c_id;
    }

    /**
     * @param c_id the c_id to set
     */
    public void setC_id(String c_id) {
        this.c_id = c_id;
    }

    /**
     * @return the c_type
     */
    public String getC_type() {
        return c_type;
    }

    /**
     * @param c_type the c_type to set
     */
    public void setC_type(String c_type) {
        this.c_type = c_type;
    }

    /**
     * @return the c_time
     */
    public String getC_time() {
        return c_time;
    }

    /**
     * @param c_time the c_time to set
     */
    public void setC_time(String c_time) {
        this.c_time = c_time;
    }

    /**
     * @return the c_last_payment
     */
    public String getC_last_payment() {
        return c_last_payment;
    }

    /**
     * @param c_last_payment the c_last_payment to set
     */
    public void setC_last_payment(String c_last_payment) {
        this.c_last_payment = c_last_payment;
    }

    /**
     * @return the c_all_courses
     */
    public ArrayList<String> getC_all_courses() {
        return c_all_courses;
    }

    /**
     * @param c_all_courses the c_all_courses to set
     */
    public void setC_all_courses(ArrayList<String> c_all_courses) {
        this.c_all_courses = c_all_courses;
    }

    /**
     * @return the c_fee
     */
    public String getC_fee() {
        return c_fee;
    }

    /**
     * @param c_fee the c_fee to set
     */
    public void setC_fee(String c_fee) {
        this.c_fee = c_fee;
    }

    /**
     * @return the c_type_r_or_t
     */
    public String getC_type_r_or_t() {
        return c_type_r_or_t;
    }

    /**
     * @param c_type_r_or_t the c_type_r_or_t to set
     */
    public void setC_type_r_or_t(String c_type_r_or_t) {
        this.c_type_r_or_t = c_type_r_or_t;
    }

    /**
     * @return the c_card_type
     */
    public String getC_card_type() {
        return c_card_type;
    }

    /**
     * @param c_card_type the c_card_type to set
     */
    public void setC_card_type(String c_card_type) {
        this.c_card_type = c_card_type;
    }
}
