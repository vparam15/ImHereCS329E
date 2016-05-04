package com.example.thu.imhere;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {
    Button signup_button, login_button;
    EditText editTextUsername, editTextPass;
    DatabaseHelper LoginDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        login_button = (Button) findViewById(R.id.login_button);
        signup_button = (Button) findViewById(R.id.signup_button);
        editTextUsername = (EditText) findViewById(R.id.username);
        editTextPass = (EditText) findViewById(R.id.password);
        LoginDb = new DatabaseHelper(this);
        final SharedPreferences mSettings = getSharedPreferences("Settings", 0);

        // when user presses "Login"
        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = editTextUsername.getText().toString();
                String password = editTextPass.getText().toString();

                // retrieve stored password from database
                String storedPassword = LoginDb.checkLogin(username);

                if (password.equals(storedPassword)) {

                    SharedPreferences.Editor editor = mSettings.edit();
                    editor.putString("current_username", username);
                    editor.commit();

                    Toast.makeText(getApplicationContext(), "Redirecting...", Toast.LENGTH_SHORT).show();
                    Intent first_category = new Intent(Login.this, MainActivity.class);
                    startActivity(first_category);

                } else {
                    Toast.makeText(getApplicationContext(), "Wrong Credentials", Toast.LENGTH_SHORT).show();
                }
            }
        });
        // when user presses signup
        signup_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent signup_screen = new Intent(Login.this, Signup.class);
                startActivity(signup_screen);

            }
        });


    }


}
