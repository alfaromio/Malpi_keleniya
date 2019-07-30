/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.institute_management.student.BL;

import com.institute_management.student.BEAN.Student;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author NIPUN
 */
public class StudentDetails extends AbstractTableModel {

    private static final String[] column = {"Name",
        "Registration No",    
        "Card Number",
        "Gender",
        "Year of Registration",
        "Parents Telephone",
        "School"};
    private static ArrayList<Student> list;

    public StudentDetails(ArrayList<Student> stList) {
        this.list = stList;
    }

    @Override
    public int getRowCount() {
        return list.size();
    }

    @Override
    public int getColumnCount() {
        return column.length;
    }
    
    @Override
     public String getColumnName(int col) {
      return column[col];
    }


    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return list.get(rowIndex).getName();
            case 1:
                return list.get(rowIndex).getStudentID();
            case 2:
                return list.get(rowIndex).getCardNumber();           
            case 3:
                return list.get(rowIndex).getGender();
            case 4:
                return list.get(rowIndex).getYearOfReg();
            case 5:
                return list.get(rowIndex).getpContactNo();
            case 6:
                return list.get(rowIndex).getSchool();
            default:
                return "Error";

        }

    }

}
