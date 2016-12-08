package com.algonquin.cst2335.smarthomecontroller;

import android.content.DialogInterface;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class AutomobileRadioActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_automobile_radio);

        addRadio = (Button) findViewById(R.id.addRadio);
        addRadio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(AutomobileRadioActivity.this);

                LayoutInflater inflater = AutomobileRadioActivity.this.getLayoutInflater();
                final View view = inflater.inflate(R.layout.add_radio_dialog, null);

                final Spinner radioSpinner = (Spinner) view.findViewById(R.id.radioSpinner);
                ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(view.getContext(),
                        R.array.fmRadio, android.R.layout.simple_spinner_item);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                radioSpinner.setAdapter(adapter);
                radioSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        stationAdded = parent.getItemAtPosition(position).toString();
                        radioAdded = Snackbar.make(findViewById(R.id.activity_automobile_radio), "Added station " + stationAdded, Snackbar.LENGTH_LONG)
                                .setAction("Action", null);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {
                        stationAdded = "none";
                        radioAdded = Snackbar.make(findViewById(R.id.activity_automobile_radio), "Added station " + stationAdded, Snackbar.LENGTH_LONG)
                                .setAction("Action", null);
                    }
                });

                builder.setView(view)
                        .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener()
                        {
                            @Override
                            public void onClick(DialogInterface dialog, int id)
                            {
                                radioAdded.show();
                            }
                        })
                        .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener()
                        {
                            public void onClick(DialogInterface dialog, int id)
                            {
                                //User clicked cancel button
                            }
                        })
                        .create()
                        .show();

            }
        });
    }

    private Button addRadio;
    private Snackbar radioAdded;
    private String stationAdded;
}
