package com.algonquin.cst2335.smarthomecontroller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

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
                fuelInTank.setText(FUEL_CAPACITY);
                updateDistanceEstimate();
            }
        });
    }

    private void updateDistanceEstimate()
    {
        String fuelString = fuelInTank.getText().toString();
        double fuel = Double.parseDouble(fuelString);
        double distance = fuel*100/FUEL_EFFICIENCY;

        distanceEstimate.setText(String.valueOf(distance));
    }

    private TextView fuelInTank;
    private TextView distanceEstimate;
    private Button fillTank;
    private static String FUEL_CAPACITY = "46.9";
    private static double FUEL_EFFICIENCY = 7.0;
}
