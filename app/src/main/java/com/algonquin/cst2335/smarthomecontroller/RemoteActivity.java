package com.algonquin.cst2335.smarthomecontroller;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import static com.algonquin.cst2335.smarthomecontroller.R.id.favButton;

public class RemoteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remote);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        Button playButton = (Button) findViewById(favButton);
        Button sleep = (Button)findViewById(R.id.button);
        Button pause = (Button) findViewById(R.id.guideButton);
        Button rewind = (Button) findViewById(R.id.liveButton);
        Button last = (Button) findViewById(R.id.lastButton);

        SeekBar seek =(SeekBar) findViewById(R.id.seekBar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                CharSequence text = "Here we go!";
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(RemoteActivity.this, text, duration);
                toast.show();
            }
        });

        seek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){
            int progressChanged = 0;

            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser){
                progressChanged = progress;}

            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }

            public void onStopTrackingTouch(SeekBar seekBar) {
                Toast.makeText(RemoteActivity.this,"Volume:"+ progressChanged,
                        Toast.LENGTH_SHORT).show();
            }
        });


        sleep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RemoteActivity.this, LRBlindsActivity.class);
                startActivity(intent);
            }
        });




    }

}
