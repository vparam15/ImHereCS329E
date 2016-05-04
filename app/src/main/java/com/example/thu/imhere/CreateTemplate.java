package com.example.thu.imhere;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CreateTemplate extends AppCompatActivity {
    Button ViewTemplate;
    EditText editTextOpening, editTextEnding, editTextTemplateName;
    TemplateDb templateDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_template);

        // Get an instance of the database helper class
        templateDB = new TemplateDb(this);

        ViewTemplate = (Button)findViewById(R.id.add_message_button) ;
        editTextOpening = (EditText)findViewById(R.id.message_opening_field);
        editTextEnding = (EditText)findViewById(R.id.message_ending_field);
        editTextTemplateName = (EditText)findViewById(R.id.template_name_field);
        final SharedPreferences mSettings = getSharedPreferences("Settings", 0);

        ViewTemplate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String opening = editTextOpening.getText().toString();
                String ending = editTextEnding.getText().toString();
                String template_name = editTextTemplateName.getText().toString();
                String username = mSettings.getString("current_username", "missing");

                // check if any of the fields are vacant
                if (opening.equals("")||ending.equals("")||template_name.equals(""))
                {
                    Toast.makeText(getApplicationContext(), "Please fill all fields", Toast.LENGTH_LONG).show();
                    return;
                }
                else {
                    // insert template data into template database
                    templateDB.insertData(template_name, opening, ending, username);

                    // go back to template View
                    Intent template_screen = new Intent(CreateTemplate.this, Templates.class);
                    startActivity(template_screen);
                }
            }

        });
    }

}
