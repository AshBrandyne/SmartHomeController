package com.algonquin.cst2335.smarthomecontroller;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;

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
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

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

}
