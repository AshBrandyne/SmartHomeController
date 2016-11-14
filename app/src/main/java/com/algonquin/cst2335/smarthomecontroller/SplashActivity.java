package com.algonquin.cst2335.smarthomecontroller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

/** Splash Activity welcome animation
 * Created by Ash-Lee on 2016-11-08.
 */

public class SplashActivity extends AppCompatActivity {
    ImageView theLogo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        theLogo = (ImageView)findViewById(R.id.splashImage);
        Animation anim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.splash_animation);
        theLogo.setAnimation(anim);

        anim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                //nothing needed here
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                finish();
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                //nothing needed here
            }
        });
    }
}
