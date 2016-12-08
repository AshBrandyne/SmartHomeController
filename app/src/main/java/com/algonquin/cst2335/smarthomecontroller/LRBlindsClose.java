package com.algonquin.cst2335.smarthomecontroller;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.algonquin.cst2335.smarthomecontroller.R;

/**
 * Author:Jessica Stratton
 * LRBlindsClose is the closed blinds activity
 */
public class LRBlindsClose extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lrblinds_close);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        Button close = (Button) findViewById(R.id.openButton);
        setSupportActionBar(toolbar);


        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LRBlindsClose.this, LRBlindsActivity.class);
                startActivity(intent);
            }
        });

    }

}
