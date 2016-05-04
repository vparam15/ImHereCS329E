package com.example.thu.imhere;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.telephony.PhoneNumberUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Signup extends AppCompatActivity {

    // Create instances of all variables used to sign up
    EditText editTextUsername, editTextPhone, editTextPass, editTextConfirmPass;
    Button btnSubmit;
    DatabaseHelper LoginDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        // Get an instance of the database helper class
        LoginDb = new DatabaseHelper(this);

        // Get references to Android UI widgets
        editTextUsername = (EditText)findViewById(R.id.first_name_field);
        editTextPhone = (EditText)findViewById(R.id.phone_number_field);
        editTextPass = (EditText)findViewById(R.id.password_field);
        editTextConfirmPass = (EditText)findViewById(R.id.confirm_password_field);

        btnSubmit = (Button)findViewById(R.id.submit);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 String username = editTextUsername.getText().toString();
                 String phone = editTextPhone.getText().toString();
                 String password = editTextPass.getText().toString();
                 String confirmPassword = editTextConfirmPass.getText().toString();

                 // check if any of the fields are vacant
                 if (username.equals("")||phone.equals("")||password.equals("")||confirmPassword.equals(""))
                 {
                     Toast.makeText(getApplicationContext(), "Please fill all fields", Toast.LENGTH_LONG).show();
                     return;
                 }
                 // check if both passwords match
                 if(!password.equals(confirmPassword))
                 {
                     Toast.makeText(getApplicationContext(), "Password does not match", Toast.LENGTH_LONG).show();
                     return;
                 }
                 else
                 {
                     // Save the Data in Database
                     LoginDb.insertData(username, phone, password);
                     Toast.makeText(getApplicationContext(), "Account Successfully Created ", Toast.LENGTH_LONG).show();
                 }
             }
         }
        );

    }

}