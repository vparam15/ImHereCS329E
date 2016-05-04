package com.example.thu.imhere;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class ViewGroup extends AppCompatActivity {
    Button addGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_group);

        addGroup = (Button)findViewById(R.id.add_group);
        addGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent addgroup_screen = new Intent(ViewGroup.this, Category.class);
                startActivity(addgroup_screen);

            }
        });

    }

}
