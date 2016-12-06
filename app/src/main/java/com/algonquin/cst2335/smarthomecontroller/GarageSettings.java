package com.algonquin.cst2335.smarthomecontroller;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Toast;
import android.widget.ToggleButton;

import static com.algonquin.cst2335.smarthomecontroller.R.id.homeJustRightButton;

public class GarageSettings extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_garage_settings);
        Button done = (Button) findViewById(homeJustRightButton);
        final MediaPlayer doorSound = MediaPlayer.create(this, R.raw.garagedoor);
        final ToggleButton door = (ToggleButton) this.findViewById(R.id.garageDoorButton);
        final ToggleButton lights = (ToggleButton) this.findViewById(R.id.garageLightsButton);

        door.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                doorSound.start();
                lights.toggle();
                Toast.makeText(GarageSettings.this,"Activating garage door opener", Toast.LENGTH_LONG).show();

            }
        });
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(GarageSettings.this, HomeSubMenu.class);
                startActivity(intent);
            }

        });
        lights.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Toast.makeText(GarageSettings.this,"Activating Lightswitch", Toast.LENGTH_LONG).show();
                setActivityBackgroundColor(Color.YELLOW);

            }
        });


    }
    public void setActivityBackgroundColor(int color) {
        View view = this.getWindow().getDecorView();
        view.setBackgroundColor(color);
    }
}
