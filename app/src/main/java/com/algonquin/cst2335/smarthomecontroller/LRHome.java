package com.algonquin.cst2335.smarthomecontroller;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

/**
 * Author: Jessica Stratton
 * LRHome is the menu to access all the other activities in the Living Room Saga
 */
public class LRHome extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lrhome);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        Button remote = (Button) findViewById(R.id.remoteButt);
        Button blinds = (Button)findViewById(R.id.blindsButt);
        Button lights = (Button) findViewById(R.id.lampButt);

        remote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LRHome.this, RemoteActivity.class);
                startActivity(intent);
            }
        });

        blinds.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LRHome.this, LRBlindsActivity.class);
                startActivity(intent);
            }
        });

        lights.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LRHome.this, LRLampActivity.class);
                startActivity(intent);
            }
        });
        setSupportActionBar(toolbar);


    }

}
