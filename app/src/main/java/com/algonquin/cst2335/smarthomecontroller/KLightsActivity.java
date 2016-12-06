package com.algonquin.cst2335.smarthomecontroller;


import android.graphics.Color;
import android.graphics.SurfaceTexture;
import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

/**
 * Class to set lights in the Kitchen
 *
 * Use this tutorial for how to change background with seekbar: http://android-er.blogspot.ca/2009/08/change-background-color-by-seekbar.html
 */
public class KLightsActivity extends Fragment {
    SeekBar dimmer;
    LinearLayout lightsOn;
    LinearLayout lightsOff;
    LinearLayout rootLayout;
    TextView onText, offText;
    private int seekR, seekG, seekB;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_klights, container, false);
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        rootLayout = (LinearLayout) getView().findViewById(R.id.activity_klights);
        rootLayout.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        lightsOn = (LinearLayout) getView().findViewById(R.id.lightsOnKitchen);
        lightsOff = (LinearLayout) getView().findViewById(R.id.lightsOffKitchen);
        dimmer = (SeekBar) getView().findViewById(R.id.kitchenSeekBar);
        //B stays at 0, both R & G go up at the same time to slide from black to yellow
        seekB=0;
        seekR=0;
        seekG=0;

        onText = (TextView) getView().findViewById(R.id.lights_on_text);
        offText = (TextView) getView().findViewById(R.id.lights_off_text);

        lightsOn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setActivityBackgroundColor(Color.YELLOW);
                onText.setTextColor(Color.parseColor("#000000"));
                offText.setTextColor(Color.parseColor("#000000"));
                Toast.makeText(getContext().getApplicationContext(), "Lights are on", Toast.LENGTH_SHORT).show();
            }
        });



        lightsOff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setActivityBackgroundColor(getResources().getColor(R.color.colorPrimary));
                onText.setTextColor(Color.parseColor("#FFFFFF"));
                offText.setTextColor(Color.parseColor("#FFFFFF"));
                Toast.makeText(getContext().getApplicationContext(), "Lights are off", Toast.LENGTH_SHORT).show();
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
        rootLayout.setBackgroundColor(color);
    }
}
