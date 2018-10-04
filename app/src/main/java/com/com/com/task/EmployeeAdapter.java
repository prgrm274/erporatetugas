package com.com.com.task;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/** Jum 28-09-18 093458
    http://www.androiddeft.com/2017/09/10/json-parsing-android-http/*/

public class EmployeeAdapter extends ArrayAdapter<EmployeeDetails> {
    // for description, not necessarily
//    private static final String KEY_EMPLOYEE_ID = "Employee Id: ";
//    private static final String KEY_NAME = "Name: ";
//    private static final String KEY_DOB = "Date of Birth: ";
//    private static final String KEY_DESIGNATION = "Designation: ";
//    private static final String KEY_CONTACT_NUMBER = "Contact Number: ";
//    private static final String KEY_EMAIL = "Email: ";
//    private static final String KEY_SALARY = "Salary: ";

    private List<EmployeeDetails> dataSet;

    public EmployeeAdapter(List<EmployeeDetails> dataSet, Context mContext) {
        super(mContext, R.layout.main2_items, dataSet);
        this.dataSet = dataSet;
    }


    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.main2_items, null);
        }
        EmployeeDetails employee = dataSet.get(position);
        if (employee != null) {
            //Text View references
            TextView id = v.findViewById(R.id.employeeId);
            TextView name = (TextView) v.findViewById(R.id.name);
            TextView dob = v.findViewById(R.id.dob);
            TextView designation = (TextView) v.findViewById(R.id.designation);
            TextView contactNumber = (TextView) v.findViewById(R.id.contact_number);
            TextView email = v.findViewById(R.id.email);
            TextView salary = v.findViewById(R.id.salary);

            //Updating the text views
//            employeeId.setText(KEY_EMPLOYEE_ID + employee.getEmployeeId());
//            name.setText(KEY_NAME + employee.getName());
//            dob.setText(KEY_DOB + employee.getDob());
//            designation.setText(KEY_DESIGNATION + employee.getDesignation());
//            contactNumber.setText(KEY_CONTACT_NUMBER + employee.getContactNumber());
//            email.setText(KEY_EMAIL + employee.getEmail());
//            salary.setText(KEY_SALARY + employee.getSalary().toString());

            id.setText(String.valueOf(employee.getEmployeeId()));
            name.setText(employee.getName());
            dob.setText(employee.getDob());
            designation.setText(employee.getDesignation());
            contactNumber.setText(employee.getContactNumber());
            email.setText(employee.getEmail());
            salary.setText(employee.getSalary());
        }

        return v;
    }
}
