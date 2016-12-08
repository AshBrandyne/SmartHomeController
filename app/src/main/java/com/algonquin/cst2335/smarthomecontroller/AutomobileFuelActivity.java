package com.algonquin.cst2335.smarthomecontroller;

import java.text.DecimalFormat;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class AutomobileFuelActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_automobile_fuel);

        fuelInTank = (TextView) findViewById(R.id.fuelInTank);
        distanceEstimate = (TextView) findViewById(R.id.distanceEstimate);
        updateDistanceEstimate();

        fillTank = (Button) findViewById(R.id.fillTank);
        fillTank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(fuelInTank.getText().equals(FUEL_CAPACITY))
                {
                    Toast.makeText(AutomobileFuelActivity.this, "Tank is already full", Toast.LENGTH_LONG).show();

                }
                else {
                    fuelInTank.setText(FUEL_CAPACITY);
                    updateDistanceEstimate();
                    Toast.makeText(AutomobileFuelActivity.this, "Tank filled", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void updateDistanceEstimate()
    {
        DecimalFormat form = new DecimalFormat("#.0");
        String fuelString = fuelInTank.getText().toString();
        double fuel = Double.parseDouble(fuelString);
        double distance = fuel*100/FUEL_EFFICIENCY;

        distanceEstimate.setText(form.format(distance));
    }

    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);

        return true;
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        //if it's help, show the help screen!
        if (menuItem.getItemId() ==  R.id.action_help) {
            final android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(this);

            builder.setTitle("Automobile Activity");
            builder.setMessage("Activity made by James Jarrett. Select an option to view and change " +
                    "relevant settings.");
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


    private TextView fuelInTank;
    private TextView distanceEstimate;
    private Button fillTank;
    private static String FUEL_CAPACITY = "46.9";
    private static double FUEL_EFFICIENCY = 7.0;
}
