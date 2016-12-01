package com.algonquin.cst2335.smarthomecontroller;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;

public class ThermostatControl extends AppCompatActivity {

    RelativeLayout screen;
    public int progress = 20;
    private int backColor;
    SeekBar tempBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        screen = (RelativeLayout) findViewById(R.id.content_thermostat_control);
        setContentView(R.layout.activity_thermostat_control);
        int maxProgress = 40;

        final float[] hsvColor = {0, 1, 1};
        hsvColor[0] = 360f * progress / maxProgress;
        tempBar = (SeekBar) findViewById(R.id.homeTempBar);
        setActivityBackgroundColor();
        tempBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {


            @Override
            public void onProgressChanged(SeekBar tempBar, int progress, boolean fromUser) {
                setActivityBackgroundColor();
            }

            @Override
            public void onStartTrackingTouch(SeekBar tempBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar tempBar) {

            }
        });
    }
    private void setActivityBackgroundColor() {
        backColor = tempBar.getProgress();

        screen.setBackgroundColor(
                0xff000000
                + backColor
        );


    }
}
/**
 * Comfy chair image credit-  https://openclipart.org/image/2400px/svg_to_png/181804/comfychair.png

 */