package com.algonquin.cst2335.smarthomecontroller;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

/**
 * Author: Jessica Stratton
 * LRHome is the menu to access all the other activities in the Living Room Saga
 */
public class LRHome extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lrhome);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        Button remote = (Button) findViewById(R.id.remoteButt);
        Button blinds = (Button)findViewById(R.id.blindsButt);
        Button lights = (Button) findViewById(R.id.lampButt);

        remote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LRHome.this, RemoteActivity.class);
                startActivity(intent);
            }
        });

        blinds.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LRHome.this, LRBlindsActivity.class);
                startActivity(intent);
            }
        });

        lights.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LRHome.this, LRLampActivity.class);
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

            builder.setTitle("LRHome Settings");
            builder.setMessage((Html.fromHtml("")));
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
