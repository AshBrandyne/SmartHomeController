package com.algonquin.cst2335.smarthomecontroller;


import android.graphics.Color;
import android.graphics.SurfaceTexture;
import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutCompat;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import java.io.IOException;

/**
 * Class to set lights in the Kitchen
 *
 * Use this tutorial for how to change background with seekbar: http://android-er.blogspot.ca/2009/08/change-background-color-by-seekbar.html
 */
public class KLightsActivity extends AppCompatActivity {
    SeekBar dimmer;
    LinearLayout lightsOn;
    LinearLayout lightsOff;
    LinearLayout rootLayout;
    TextView onText, offText;
    private int seekR, seekG, seekB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_klights);

        rootLayout = (LinearLayout) findViewById(R.id.activity_klights);
        lightsOn = (LinearLayout) findViewById(R.id.lightsOnKitchen);
        lightsOff = (LinearLayout) findViewById(R.id.lightsOffKitchen);
        dimmer = (SeekBar) findViewById(R.id.kitchenSeekBar);
        //B stays at 0, both R & G go up at the same time to slide from black to yellow
        seekB=0;
        seekR=0;
        seekG=0;

        onText = (TextView) findViewById(R.id.lights_on_text);
        offText = (TextView) findViewById(R.id.lights_off_text);

        lightsOn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setActivityBackgroundColor(Color.YELLOW);
                onText.setTextColor(Color.parseColor("#000000"));
                offText.setTextColor(Color.parseColor("#000000"));
            }
        });



        lightsOff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setActivityBackgroundColor(Color.BLACK);
                onText.setTextColor(Color.parseColor("#FFFFFF"));
                offText.setTextColor(Color.parseColor("#FFFFFF"));
            }
        });

        dimmer.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                setBackgroundSeekBar();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });



    }

    private void setBackgroundSeekBar() {
        seekR = (dimmer.getProgress() * 0x10000);
        seekG = (dimmer.getProgress() * 0x100);

        setActivityBackgroundColor(0xff000000 + seekR + seekG + seekB);
    }

    public void setActivityBackgroundColor(int color) {
        View view = this.getWindow().getDecorView();
        view.setBackgroundColor(color);
    }
}
