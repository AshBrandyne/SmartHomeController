package com.algonquin.cst2335.smarthomecontroller;

import java.text.DecimalFormat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

    private TextView fuelInTank;
    private TextView distanceEstimate;
    private Button fillTank;
    private static String FUEL_CAPACITY = "46.9";
    private static double FUEL_EFFICIENCY = 7.0;
}
