package com.com.com.task;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/** Jum 28-09-18 094321
    http://www.androiddeft.com/2017/09/10/json-parsing-android-http/*/

public class MainEmployee extends AppCompatActivity {

    private static final String KEY_SUCCESS = "success";
    private static final String KEY_DATA = "data";
    private static final String KEY_EMPLOYEE_ID = "employee_id";
    private static final String KEY_NAME = "name";
    private static final String KEY_DOB = "dob";
    private static final String KEY_DESIGNATION = "designation";
    private static final String KEY_CONTACT_NUMBER = "contact_number";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_SALARY = "salary";
    private ProgressDialog pDialog;
    private int success;
    private EmployeeAdapter adapter;

    private String url = "http://api.androiddeft.com/json/employee.php";

    private Serializable serializable = MainEmployee.this.getClass();
    private String TAG = serializable.toString();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main2);
        //Call the AsyncTask
        new FetchEmployeeDetails().execute();
    }

    private class FetchEmployeeDetails extends AsyncTask<String, String, String> {
        JSONObject response;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Log.v(TAG, "\"onPreExecute\" JUST INFO HERE. NOW DOWNLOADING.");

            //Display progress bar
            pDialog = new ProgressDialog(MainEmployee.this);
            pDialog.setMessage("Loading Data.. Please wait...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(false);
            pDialog.show();
        }

        @Override
        protected String doInBackground(String... params) {
            HTTPJsonParser jsonParser = new HTTPJsonParser();
            response = jsonParser.makeHttpRequest(url,"GET",null);
            Log.v(TAG, "\"doInBackground\" :\n" + response + " : PRINTED ABOVE IS RESULT FROM REQUEST \"GET\" FROM API URL\n");
            try {
                success = response.getInt(KEY_SUCCESS);
                Log.v(TAG, "\"doInBackground\" :::: response.getInt(KEY_SUCCESS) ? (1 IS YES) " + success);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        protected void onPostExecute(String result) {
            pDialog.dismiss();

            runOnUiThread(new Runnable() {
                public void run() {
                    ListView listView = findViewById(R.id.employeeList);
                    if (success == 1) {
                        try {
                            JSONArray employeeArray =  response.getJSONArray(KEY_DATA);
                            List<EmployeeDetails> employeeList = new ArrayList<>();
                            //Populate the EmployeeDetails list from response
                            for (int i = 0; i<employeeArray.length();i++){
                                EmployeeDetails employeeDetails = new EmployeeDetails();
                                JSONObject employeeObj = employeeArray.getJSONObject(i);
                                employeeDetails.setEmployeeId(employeeObj.getInt(KEY_EMPLOYEE_ID));
                                employeeDetails.setName(employeeObj.getString(KEY_NAME));
                                employeeDetails.setDob(employeeObj.getString(KEY_DOB));
                                employeeDetails.setDesignation(employeeObj.getString(KEY_DESIGNATION));
                                employeeDetails.setContactNumber(employeeObj.getString(KEY_CONTACT_NUMBER));
                                employeeDetails.setEmail(employeeObj.getString(KEY_EMAIL));
                                employeeDetails.setSalary(employeeObj.getString(KEY_SALARY));
                                employeeList.add(employeeDetails);
                            }
                            //Create an adapter with the EmployeeDetails List and set it to the LstView
//                            adapter = new EmployeeAdapter(employeeList,getApplicationContext());
                            adapter = new EmployeeAdapter(employeeList,getApplicationContext());
                            listView.setAdapter(adapter);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    } else {
                        Toast.makeText(MainEmployee.this, "Some error occurred while loading data", Toast.LENGTH_LONG).show();
                    }
                }
            });
        }
    }

}
