package com.algonquin.cst2335.smarthomecontroller;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
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
        fritemp = "1.6 °C";
        freetemp= "-18 °C";

        //Set text of the temp based on the most recent value in the database

        //set listeners for Spinners
        fridgeSpinner.setOnItemSelectedListener(this);
        freezeSpinner.setOnItemSelectedListener(this);

        //add values to Spinners
        List<String> settings = new ArrayList<>();
        settings.add(getResources().getString(R.string.kitchTemp));
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
            Snackbar.make(view, getResources().getString(R.string.friTemp) + fritemp, Snackbar.LENGTH_SHORT)
                    .setAction("Action", null).show();
            fridge.setText(fritemp);

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
            Snackbar.make(view, getResources().getString(R.string.freeTemp) + freetemp, Snackbar.LENGTH_SHORT)
                    .setAction("Action", null).show();        }
        freezer.setText(freetemp);


    }
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
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

            builder.setTitle("Refrigerator Settings");
            builder.setMessage((Html.fromHtml("Adjust the temperature of the fridge and freezer" +
                    "<p>Activity created by Ash-Lee Hommy for CST 2335")));
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
                    break;
                case R.id.action_sofa:
                    intent = new Intent(this, LRHome.class);
                    break;
                case R.id.action_fridge:
                    intent = new Intent(this, KitchenListActivity.class);
                    break;
                case R.id.action_car:
                    intent = new Intent(this, AutomobileListActivity.class);
                    break;
            }
            startActivity(intent);
        }
        return true;
    }

    private class DatabaseConnector extends AsyncTask<String, Integer, String> {
        protected String doInBackground(String... args) {


            return "";
        }

        protected void onProgressUpdate(Integer... value) {

        }

        protected void onPostExecute(String result) {

        }
    }


}
