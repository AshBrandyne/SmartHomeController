package com.algonquin.cst2335.smarthomecontroller;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**KFridgeActivity
 *
 * @author Ash-Lee Hommy 040840815
 *
 * References:
 * Tutorial for spinner: https://www.tutorialspoint.com/android/android_spinner_control.htm
 *
 */
public class KFridgeActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    TextView fridge, freezer;
    String freetemp, fritemp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kfridge);

        Spinner fridgeSpinner = (Spinner) findViewById(R.id.fridgeSpinner);
        Spinner freezeSpinner = (Spinner) findViewById(R.id.freezerSpinner);
        fridge = (TextView) findViewById(R.id.fridgeTemp2);
        freezer = (TextView) findViewById(R.id.freezerTemp2);
        fritemp = "0 °C";
        freetemp= "0 °C";

        //Set text of the temp based on the most recent value in the database

        //set listeners for Spinners
        fridgeSpinner.setOnItemSelectedListener(this);
        freezeSpinner.setOnItemSelectedListener(this);

        //add values to Spinners
        List<String> settings = new ArrayList<>();
        settings.add("Set Temperature");
        settings.add("1"); // 3.6 C
        settings.add("2"); // 2.6 C
        settings.add("3"); //Fridge 1.6 C
        settings.add("4"); // 0.6 C
        settings.add("5"); // 0 C

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, settings);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        fridgeSpinner.setAdapter(adapter);
        freezeSpinner.setAdapter(adapter);

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String item = parent.getItemAtPosition(position).toString();
        String whichSpinner = parent.toString();
        int itemInt = 0;
        if (item.contains("1") || item.contains("2") || item.contains("3") ||
                item.contains("4") || item.contains("5")){
            itemInt = Integer.parseInt(item);
        }

        if (whichSpinner.contains("fridge")) {
            switch (itemInt) {
                case 1:
                    fritemp = "3.6 °C";
                    break;
                case 2:
                    fritemp = "2.6 °C";
                    break;
                case 3:
                    fritemp = "1.6 °C";
                    break;
                case 4:
                    fritemp = "0.6 °C";
                    break;
                case 5:
                    fritemp = "0 °C";
                    break;
            }
            Snackbar.make(view, "Fridge Temperature: " + fritemp, Snackbar.LENGTH_SHORT)
                    .setAction("Action", null).show();

        } else if (whichSpinner.contains("freezer")) {
            switch (itemInt) {
                case 1:
                    freetemp = "-1 °C";
                    break;
                case 2:
                    freetemp = "-10 °C";
                    break;
                case 3:
                    freetemp = "-18 °C";
                    break;
                case 4:
                    freetemp = "-22 °C";
                    break;
                case 5:
                    freetemp = "-25 °C";
                    break;
            }
            Snackbar.make(view, "Freezer Temperature: " + freetemp, Snackbar.LENGTH_SHORT)
                    .setAction("Action", null).show();        }


    }
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }


}
