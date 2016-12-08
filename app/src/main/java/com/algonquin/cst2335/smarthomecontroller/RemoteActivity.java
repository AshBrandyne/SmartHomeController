package com.algonquin.cst2335.smarthomecontroller;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import static com.algonquin.cst2335.smarthomecontroller.R.id.favButton;

/**
 * Author: Jessica Stratton
 * RemoteActivity is the activity to do remote functions. There is a volume seekbar at the bottom
 */
public class RemoteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remote);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        Button playButton = (Button) findViewById(favButton);
        Button sleep = (Button)findViewById(R.id.button);
        Button pause = (Button) findViewById(R.id.guideButton);
        Button rewind = (Button) findViewById(R.id.liveButton);
        Button last = (Button) findViewById(R.id.lastButton);

        SeekBar seek =(SeekBar) findViewById(R.id.seekBar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                CharSequence text = "Here we go!";
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(RemoteActivity.this, text, duration);
                toast.show();
            }
        });

        seek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){
            int progressChanged = 0;

            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser){
                progressChanged = progress;}

            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }

            public void onStopTrackingTouch(SeekBar seekBar) {
                Toast.makeText(RemoteActivity.this,"Volume:"+ progressChanged,
                        Toast.LENGTH_SHORT).show();
            }
        });


        sleep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RemoteActivity.this, LRBlindsActivity.class);
                startActivity(intent);
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

            builder.setTitle("Microwave Settings");
            builder.setMessage((Html.fromHtml("Living Room Remote Activity by Jessica Stratton")));
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
