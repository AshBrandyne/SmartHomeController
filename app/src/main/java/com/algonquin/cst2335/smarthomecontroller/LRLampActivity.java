package com.algonquin.cst2335.smarthomecontroller;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
/**
 * Lamp Activity for the Living Room. Lets you set the mood for whatever activity you need.
 * Author: Jessica Stratton
 */

public class LRLampActivity extends AppCompatActivity {
    ImageButton left;
    ImageButton middle;
    ImageButton right;
    ImageView rawlings;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lrlamp);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        SeekBar seek = (SeekBar) findViewById(R.id.seekBar2);
        Button marvin = (Button) findViewById(R.id.relaxButton);
        Button read = (Button) findViewById(R.id.readingButton);
        Button baseball = (Button) findViewById(R.id.baseballButton);
        left = (ImageButton) findViewById(R.id.leftLightButton);
        middle = (ImageButton) findViewById(R.id.middleLightButton);
        right = (ImageButton) findViewById(R.id.rightLightButton);
        rawlings = (ImageView) findViewById(R.id.baseballTwo);
        setSupportActionBar(toolbar);
        boolean check = false;


        marvin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               left.setBackgroundColor(Color.RED);
               middle.setBackgroundColor(Color.BLACK);
                right.setBackgroundColor(Color.MAGENTA);
            }
        });
        read.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                left.setBackgroundColor(Color.YELLOW);
                middle.setBackgroundColor(Color.YELLOW);
                right.setBackgroundColor(Color.YELLOW);
            }
        });
        baseball.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                left.setBackgroundColor(Color.BLUE);
                middle.setBackgroundColor(Color.RED);
                right.setBackgroundColor(Color.WHITE);


                rawlings.setVisibility(View.VISIBLE);
                Animation wow = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.blink);
                rawlings.startAnimation(wow);
        // Image from http://www.mlbbettingcentral.com/wp-content/uploads/2013/01/mlb-baseball.jpg
                wow.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        rawlings.setVisibility(View.INVISIBLE);

                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);

        return true;
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        //if it's help, show the help screen!
        if (menuItem.getItemId() ==  R.id.action_help) {
            final AlertDialog.Builder builder = new AlertDialog.Builder(this);

            builder.setTitle("Lamp Settings");
            builder.setMessage((Html.fromHtml("Activity by Jessica Stratton" +
                    "<p>Choose a light setting for whatever mood you might be in")));
            builder .setCancelable(false)
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });
            final AlertDialog alert = builder.create();
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
