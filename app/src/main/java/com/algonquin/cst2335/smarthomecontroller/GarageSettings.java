package com.algonquin.cst2335.smarthomecontroller;

import android.content.Context;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Toast;

public class GarageSettings extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_garage_settings);

        final MediaPlayer doorSound = MediaPlayer.create(this, R.raw.garagedoor);
        final Button door = (Button) this.findViewById(R.id.garageDoorButton);

        door.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                doorSound.start();
                Toast.makeText(GarageSettings.this,"Activating garage door opener", Toast.LENGTH_LONG).show();

            }
        });
    }
}
