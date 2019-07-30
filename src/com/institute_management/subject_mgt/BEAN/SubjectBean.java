/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.institute_management.subject_mgt.BEAN;

/**
 *
 * @author mihiran_p
 * @date Feb 22, 2017
 */
public class SubjectBean {
    private String SubjectCode;
    private String SubjectId;
    private String SubjectName;
    private String SubjectMedium;

    public String getSubjectId() {
        return SubjectId;
    }

    public void setSubjectId(String SubjectId) {
        this.SubjectId = SubjectId;
    }

    public String getSubjectName() {
        return SubjectName;
    }

    public void setSubjectName(String SubjectName) {
        this.SubjectName = SubjectName;
    }

    public String getSubjectMedium() {
        return SubjectMedium;
    }

    public void setSubjectMedium(String SubjectMedium) {
        this.SubjectMedium = SubjectMedium;
    }

    public String getSubjectCode() {
        return SubjectCode;
    }

    public void setSubjectCode(String SubjectCode) {
        this.SubjectCode = SubjectCode;
    }
    
}
