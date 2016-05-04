package com.example.thu.imhere;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Templates extends AppCompatActivity {
    Button addTemplate;
    ListView myList;
    TemplateDb templateDB;
    private ArrayAdapter<String> listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_templates);
        addTemplate = (Button) findViewById(R.id.add_template);
        myList = (ListView) findViewById (R.id.template_listView);
        final SharedPreferences mSettings = getSharedPreferences("Settings", 0);
        String username = mSettings.getString("current_username", "missing");

        // Get an instance of the database helper class
        templateDB = new TemplateDb(this);

        Cursor cursor = templateDB.getAllData();
        ArrayList<String> TemplateList = new ArrayList<String>();

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                TemplateList.add(cursor.getString(1) + " " + cursor.getString(4));
            } while (cursor.moveToNext());
        }

        ArrayAdapter<String> listAdapter = new ArrayAdapter<String>(this, R.layout.template_row, TemplateList);
        myList.setAdapter(listAdapter);

        // when user presses addTemplate
        addTemplate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "We're getting there!", Toast.LENGTH_SHORT).show();
                Intent create_template = new Intent(Templates.this, CreateTemplate.class);
                startActivity(create_template);
            }
        });
    }

    private void populateListView() {
        Cursor cursor = templateDB.getAllData();
        String[] fromFieldNames = new String[] {TemplateDb.COL_2};
        int[] toViewIds = new int[] {R.id.rowTextView};
        SimpleCursorAdapter myCursorAdapter;
        myCursorAdapter = new SimpleCursorAdapter(getBaseContext(), R.layout.template_row, cursor, fromFieldNames, toViewIds, 0);
        ListView myList = (ListView) findViewById(R.id.template_listView);
        myList.setAdapter(myCursorAdapter);
    }
}





