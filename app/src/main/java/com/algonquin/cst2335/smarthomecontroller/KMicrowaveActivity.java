package com.algonquin.cst2335.smarthomecontroller;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * Animated Microwave gif from http://www.animated-gifs.eu/kitchen-microwaves
 *
 * Blinking animation tutorial from stack overflow: http://stackoverflow.com/questions/3450839/blinking-text-in-android-view/11991435
 */
public class KMicrowaveActivity extends AppCompatActivity implements View.OnClickListener {
    private CountDownTimer countDownTimer;
    private TextView clock;
    private Button b1, b2, b3, b4, b5, b6, b7, b8, b9, b0, bStop, bStart, bReset;
    private boolean isRunning = false;
    private boolean isPaused = false;
    List<Button> buttonList;
    String timeEntered;
    StringBuilder sb;
    int minutes, seconds;
    long resetTime;
    ImageView goat;
    GridLayout theButtons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kmicrowave);
        b1 = (Button) findViewById(R.id.btn1);
        b2 = (Button) findViewById(R.id.btn2);
        b3 = (Button) findViewById(R.id.btn3);
        b4 = (Button) findViewById(R.id.btn4);
        b5 = (Button) findViewById(R.id.btn5);
        b6 = (Button) findViewById(R.id.btn6);
        b7 = (Button) findViewById(R.id.btn7);
        b8 = (Button) findViewById(R.id.btn8);
        b9 = (Button) findViewById(R.id.btn9);
        b0 = (Button) findViewById(R.id.btn0);
        bStart = (Button) findViewById(R.id.btnStart);
        bStop = (Button) findViewById(R.id.btnStop);
        bReset = (Button) findViewById(R.id.btnReset);

        theButtons = (GridLayout)findViewById(R.id.microButtons);

        buttonList = Arrays.asList(b1, b2, b3, b4, b5, b6, b7, b8, b9, b0, bStart, bStop, bReset);

        /* add Click Listener for each button */
        for (Button button : buttonList) {
            button.setOnClickListener(this);
        }

        clock = (TextView) findViewById(R.id.microTimer);

        minutes = 00;
        seconds = 00;

        clock.setText("00:00");
        timeEntered = "0000";
        sb = new StringBuilder(timeEntered);
    }

    @Override
    public void onClick(View v) {

        boolean numDisabled = false;
        boolean startDisabled = false;

        //TODO: if it's running, disable some buttons.

        //if clock is 0, do nothing.

        switch (v.getId()) {
            case R.id.btn0:
                updateTimer("0");
                break;
            case R.id.btn1:
                updateTimer("1");
                break;
            case R.id.btn2:
                updateTimer("2");
                break;
            case R.id.btn3:
                updateTimer("3");
                break;
            case R.id.btn4:
                updateTimer("4");
                break;
            case R.id.btn5:
                updateTimer("5");
                break;
            case R.id.btn6:
                updateTimer("6");
                break;
            case R.id.btn7:
                updateTimer("7");
                break;
            case R.id.btn8:
                updateTimer("8");
                break;
            case R.id.btn9:
                updateTimer("9");
                break;
            case R.id.btnStart:

                if (minutes == 3600000 && seconds == 47000) {
                    goat = (ImageView)findViewById(R.id.goat);
                    goat.setVisibility(View.VISIBLE);
                    theButtons.setVisibility(View.INVISIBLE);
                    Animation anim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.splash_animation);
                    goat.setAnimation(anim);

                    anim.setAnimationListener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) {
                            //nothing needed here
                        }

                        @Override
                        public void onAnimationEnd(Animation animation) {
                            goat.setVisibility(View.INVISIBLE);
                            updateTimer("0000");
                            minutes=0;
                            seconds=0;
                            theButtons.setVisibility(View.VISIBLE);
                            //maybe also disable and reenable?
                        }

                        @Override
                        public void onAnimationRepeat(Animation animation) {
                            //nothing needed here
                        }
                    });
                }

                if (isPaused) {
                    countDownTimer.start();
                    isPaused = false;
                    break;
                }
                numDisabled = true;
                countDownTimer = new MyMicrowaveTimer(minutes+seconds, 1000);
                countDownTimer.start();
                isRunning = true; //clock has started
                //easter egg, if 6047 minutes are 60 and seconds are 47, flash goat
                break;
            case R.id.btnStop:
                isPaused = true; //timer is paused
                countDownTimer.cancel();
                countDownTimer = new MyMicrowaveTimer(resetTime, 1000);
                //get text and updateTimer?
                numDisabled = false;
                break;
            case R.id.btnReset:
                if (isRunning) { //if time has started cancel
                    countDownTimer.cancel();
                }

                numDisabled = false;
                isRunning = false;
                minutes=0;
                seconds=0;
                updateTimer("0000");
                clock.setText("00:00");
                clock.setTextColor(Color.GRAY);
                break;
        }


        if (numDisabled) {
            //disable the buttons
        } else {
            //enable the buttons
        }
        if (startDisabled) {
            //disable start
        } else {
            //enable the buttons
        }

    }

    /**
     * Updates the clock/timer
     * @param num the number the user pressed
     */
    private void updateTimer(String num) {
        sb.append(num);
        //get the last 4 with substring
        //then set 2 variables last 2 first 2

        String time = sb.substring(sb.length()-4);
        time.length();

        String mins = time.substring(0, 2);
        String secs = time.substring(time.length()-2);

        minutes = Integer.parseInt(mins) * 60000;
        seconds = Integer.parseInt(secs) * 1000;

        clock.setText(mins + ":" + secs); //set text to time with colon 00:00
    }



    private class MyMicrowaveTimer extends CountDownTimer {
        public MyMicrowaveTimer(long start, long interval) {
            super(start, interval);
        }

        @Override
        public void onFinish() {
            //Vibrate the phone! Flash numbers & turn them red?
            clock.setText("00:00");
            clock.setTextColor(Color.RED);

            Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
            v.vibrate(3000);

            Animation anim = new AlphaAnimation(0.0f, 2.0f);
            anim.setDuration(100);
            anim.setStartOffset(20);
            anim.setRepeatMode(Animation.REVERSE);
            anim.setRepeatCount(3);
            clock.startAnimation(anim);

        }

        @Override
        public void onTick(long millisUntilFinished) {
            resetTime = millisUntilFinished;

            long secs = millisUntilFinished / 1000;
            long mins = secs / 60;
            secs = secs % 60;
            clock.setText((String.format("%02d",mins))+":"+(String.format("%02d",secs)));
        }
    }


}


