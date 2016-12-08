package com.algonquin.cst2335.smarthomecontroller;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;

import static com.algonquin.cst2335.smarthomecontroller.R.id.homeJustRightButton;

public class ThermostatControl extends AppCompatActivity {

    RelativeLayout screen;
    public int progress = 20;
    private int backColor;
    SeekBar tempBar;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        screen = (RelativeLayout) findViewById(R.id.content_thermostat_control);
        setContentView(R.layout.activity_thermostat_control);
        int maxProgress = 40;
        Button done = (Button)findViewById(homeJustRightButton);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getTitle());

//        final float[] hsvColor = {0, 1, 1};
//        hsvColor[0] = 360f * progress / maxProgress;
        tempBar = (SeekBar) findViewById(R.id.homeTempBar);
//        setActivityBackgroundColor();
        tempBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {


            @Override
            public void onProgressChanged(SeekBar tempBar, int progress, boolean fromUser) {
                //setBackgroundSeekBar();
            }

            @Override
            public void onStartTrackingTouch(SeekBar tempBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar tempBar) {

            }
        });
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent intent = new Intent(ThermostatControl.this, AddPreSets.class);
                startActivity(intent);
            }

        });
    }

//    private void setBackgroundSeekBar() {
//        backColor = (tempBar.getProgress() * 10000);
//
//        setActivityBackGroundColor(0xff000000 + backColor);
//
//
//    }

//    public void setActivityBackGroundColor(int color) {
//        View view = this.getWindow().getDecorView();
//        view.setBackgroundColor(color);
//    }
@Override
public boolean onCreateOptionsMenu(final Menu menu) {
    getMenuInflater().inflate(R.menu.toolbar_menu, menu);

    return true;
}

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        //if it's help, show the help screen!
        if (menuItem.getItemId() ==  R.id.action_help) {
            final android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(this);

            builder.setTitle("Home Activity");
            builder.setMessage("Activity made by Tyler Woyiwada" +
                    " Control the thermostat");
            builder .setCancelable(false)
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });
            final android.app.AlertDialog alert = builder.create();
            alert.show();

        } else {
            Intent intent = new Intent();
            switch (menuItem.getItemId()) {
                case R.id.action_home:
                    intent = new Intent(this, HomeSubMenu.class);
                    startActivity(intent);
                    break;
                case R.id.action_sofa:
                    intent = new Intent(this, LRHome.class);
                    startActivity(intent);
                    break;
                case R.id.action_fridge:
                    intent = new Intent(this, KitchenListActivity.class);
                    startActivity(intent);
                    break;
                case R.id.action_car:
                    intent = new Intent(this, AutomobileListActivity.class);
                    startActivity(intent);
                    break;
            }

        }
        return true;
    }

}


/**
 * Comfy chair image credit-  https://openclipart.org/image/2400px/svg_to_png/181804/comfychair.png

 */