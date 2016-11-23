package com.algonquin.cst2335.smarthomecontroller;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutCompat;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;

public class KLightsActivity extends AppCompatActivity {
    SeekBar dimmer;
    LinearLayout lightsOn;
    LinearLayout lightsOff;
    LinearLayout rootLayout;

    private int seekR, seekG, seekB;
    //use Async Task here?

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_klights);
        setActivityBackgroundColor(Color.parseColor("#090F0F"));

        rootLayout = (LinearLayout) findViewById(R.id.activity_klights);
        lightsOn = (LinearLayout) findViewById(R.id.lightsOnKitchen);
        lightsOff = (LinearLayout) findViewById(R.id.lightsOffKitchen);
        dimmer = (SeekBar) findViewById(R.id.kitchenSeekBar);
        //B stays at 0, both R & G go up at the same time to slide from black to yellow
        seekB=0;
        seekR=0;
        seekG=0;

        lightsOn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setActivityBackgroundColor(Color.YELLOW);
            }
        });



        lightsOff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setActivityBackgroundColor(Color.BLACK);
            }
        });



    }

    public void setActivityBackgroundColor(int color) {
        View view = this.getWindow().getDecorView();
        view.setBackgroundColor(color);
    }
}
