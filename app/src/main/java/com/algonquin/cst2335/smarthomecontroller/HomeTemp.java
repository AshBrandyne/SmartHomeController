package com.algonquin.cst2335.smarthomecontroller;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import static com.algonquin.cst2335.smarthomecontroller.R.id.angry_btn2;
import static com.algonquin.cst2335.smarthomecontroller.R.id.homeColdButton;

public class HomeTemp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_temp);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        Button hotButton = (Button) findViewById(angry_btn2);
        Button coldButton = (Button) findViewById(homeColdButton);

        hotButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent intent = new Intent(HomeTemp.this, ThermostatControl.class);
                startActivity(intent);
            }

        });
        coldButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent intent = new Intent(HomeTemp.this, ThermostatControl.class);
                startActivity(intent);
            }

        });


    }
        // setSupportActionBar(toolbar);


    }

