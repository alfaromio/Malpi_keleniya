/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.institute_management.lecture_mgt;

import com.institute_management.student.BL.*;
import com.institute_management.student.BEAN.Student;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author NIPUN
 */
public class LecturerDetailsReport extends AbstractTableModel {

    private static final String[] column = {"Lecturer ID",
        "Lecturer Name",
        "NIC",
        "Gender",
        "Contact"};
    private static ArrayList<lectureBean> list;

    public LecturerDetailsReport(ArrayList<lectureBean> leList) {
        this.list = leList;
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
                return list.get(rowIndex).getLecID();
            case 1:
                return list.get(rowIndex).getName();
            case 2:
                return list.get(rowIndex).getNic();
            case 3:
                return list.get(rowIndex).getGender();
            case 4:
                return list.get(rowIndex).getContact();
            default:
                return "Error";

        }

    }

}
