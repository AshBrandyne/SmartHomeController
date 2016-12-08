package com.algonquin.cst2335.smarthomecontroller;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import org.w3c.dom.Text;

public class AutomobileTempActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_automobile_temp_settings);

        frontTemp = (TextView) findViewById(R.id.frontTemp);

        frontTempUp = (ImageButton) findViewById(R.id.frontTempUp);
        frontTempUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tempString = frontTemp.getText().toString();
                Integer temp = Integer.parseInt(tempString);

                if (temp < 25)
                {
                    temp++;
                }

                frontTemp.setText(temp.toString());
            }
        });

        frontTempDown = (ImageButton) findViewById(R.id.frontTempDown);
        frontTempDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tempString = frontTemp.getText().toString();
                Integer temp = Integer.parseInt(tempString);

                if (temp > 16)
                {
                    temp--;
                }

                frontTemp.setText(temp.toString());
            }
        });

        backTemp = (TextView) findViewById(R.id.backTemp);

        backTempUp = (ImageButton) findViewById(R.id.backTempUp);
        backTempUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tempString = backTemp.getText().toString();
                Integer temp = Integer.parseInt(tempString);

                if (temp < 25)
                {
                    temp++;
                }

                backTemp.setText(temp.toString());
            }
        });

        backTempDown = (ImageButton) findViewById(R.id.backTempDown);
        backTempDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tempString = backTemp.getText().toString();
                Integer temp = Integer.parseInt(tempString);

                if (temp > 16)
                {
                    temp--;
                }

                backTemp.setText(temp.toString());
            }
        });

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.fanSettings, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        frontFan = (Spinner) findViewById(R.id.frontFan);
        frontFan.setAdapter(adapter);
        backFan = (Spinner) findViewById(R.id.backFan);
        backFan.setAdapter(adapter);
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


    private TextView frontTemp;
    private ImageButton frontTempUp;
    private ImageButton frontTempDown;
    private Spinner frontFan;
    private TextView backTemp;
    private ImageButton backTempUp;
    private ImageButton backTempDown;
    private Spinner backFan;

}


