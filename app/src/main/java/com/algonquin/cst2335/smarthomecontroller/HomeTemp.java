package com.algonquin.cst2335.smarthomecontroller;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import static com.algonquin.cst2335.smarthomecontroller.R.id.angry_btn2;
import static com.algonquin.cst2335.smarthomecontroller.R.id.homeColdButton;
import static com.algonquin.cst2335.smarthomecontroller.R.id.homeJustRightButton;

public class HomeTemp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_temp);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        Button hotButton = (Button) findViewById(angry_btn2);
        Button coldButton = (Button) findViewById(homeColdButton);
        Button perfectButton = (Button) findViewById(homeJustRightButton);

        hotButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent intent = new Intent(HomeTemp.this, ThermostatControl.class);
                startActivity(intent);
            }

        });
        coldButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent intent = new Intent(HomeTemp.this, ThermostatControl.class);
                startActivity(intent);
            }

        });

        perfectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent intent = new Intent(HomeTemp.this, AddPreSets.class);
                startActivity(intent);
            }

        });
    }
        //Thermometer image credit:  https://pixabay.com/p-159386/?no_redirect
        @Override
        public boolean onCreateOptionsMenu(final Menu menu) {
            getMenuInflater().inflate(R.menu.toolbar_menu, menu);

            return true;
        }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        //if it's help, show the help screen!
        if (menuItem.getItemId() ==  R.id.action_help) {
            final android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(this);

            builder.setTitle("HomeTemp Activity");
            builder.setMessage("Activity made by Tyler Woyiwada" +
                    ": Choose a temperature setting");
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

