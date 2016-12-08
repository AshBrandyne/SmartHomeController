package com.algonquin.cst2335.smarthomecontroller;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextClock;
/**
 * Author:Jessica Stratton
 * LRBlindsActivity is the open blinds activity
 */
public class LRBlindsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lrblinds);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        TextClock clock = (TextClock) findViewById(R.id.textClock);
        Button open = (Button) findViewById(R.id.closeButton);
        Button next = (Button) findViewById(R.id.lampButton);
        setSupportActionBar(toolbar);

        open.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LRBlindsActivity.this, LRBlindsClose.class);
                startActivity(intent);
            }
        });

        next.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(LRBlindsActivity.this, LRLampActivity.class);
                startActivity(intent);
            }
        });


    }

}
