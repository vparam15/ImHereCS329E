package com.example.thu.imhere;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

public class MainActivity extends AppCompatActivity {
    Button Groups, Templates, Message;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Groups = (Button)findViewById(R.id.contacts);
        Templates = (Button)findViewById(R.id.templates);
        Message = (Button)findViewById(R.id.compose_message);

        Groups.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent groups_screen = new Intent(MainActivity.this, ViewGroup.class);
                startActivity(groups_screen);
            }
        });

        Templates.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent templates_screen = new Intent(MainActivity.this, Templates.class);
                startActivity(templates_screen);
            }
        });

        Message.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent message_screen = new Intent(MainActivity.this, Message.class);
                startActivity(message_screen);
            }
        });

    }

}
