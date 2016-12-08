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
import android.widget.CompoundButton;
import android.widget.Toast;
import android.widget.ToggleButton;

import static com.algonquin.cst2335.smarthomecontroller.R.id.homeJustRightButton;

public class GarageSettings extends AppCompatActivity {
ToggleButton door;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_garage_settings);
        Button done = (Button) findViewById(homeJustRightButton);
        final MediaPlayer doorSound = MediaPlayer.create(this, R.raw.garagedoor);
        door = (ToggleButton) this.findViewById(R.id.garageDoorButton);
        final ToggleButton lights = (ToggleButton) this.findViewById(R.id.garageLightsButton);
        setActivityBackgroundColor(Color.BLACK);
        door.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) {
                    doorSound.start();
                    lights.toggle();
                    Toast.makeText(GarageSettings.this,"Opening garage door", Toast.LENGTH_LONG).show();
                    // The toggle is enabled
                } else {
                    Toast.makeText(GarageSettings.this,"Closing garage door", Toast.LENGTH_LONG).show();
                    if (!lights.isChecked()) lights.toggle();
                    // The toggle is disabled
                }
            }




        });
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(GarageSettings.this, HomeSubMenu.class);
                startActivity(intent);
            }

        });
        lights.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) {

                    Toast.makeText(GarageSettings.this, "Activating Lightswitch", Toast.LENGTH_LONG).show();
                    setActivityBackgroundColor(Color.YELLOW);

                } else {
                    Toast.makeText(GarageSettings.this, "Activating Lightswitch", Toast.LENGTH_LONG).show();
                    setActivityBackgroundColor(Color.BLACK);

                }
            }
        });}


    public void setActivityBackgroundColor(int color) {
        View view = this.getWindow().getDecorView();
        view.setBackgroundColor(color);
    }
}
