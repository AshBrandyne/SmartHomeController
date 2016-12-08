package com.algonquin.cst2335.smarthomecontroller;

import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class AutomobileRadioActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_automobile_radio);

        dataList = new ArrayList<String>();

        radioPresets = (Spinner) findViewById(R.id.presetStations);
        loadData();

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

                presetNumSpinner = (Spinner) view.findViewById(R.id.presetNumSpinner);
                ArrayAdapter<CharSequence> presetAdapter = ArrayAdapter.createFromResource(view.getContext(),
                        R.array.presetNums, android.R.layout.simple_spinner_item);
                presetAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                presetNumSpinner.setAdapter(presetAdapter);
                presetNumSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        presetID = Integer.parseInt(parent.getItemAtPosition(position).toString());
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });

                builder.setView(view)
                        .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener()
                        {
                            @Override
                            public void onClick(DialogInterface dialog, int id)
                            {
                                ContentValues contentValues = new ContentValues();
                                contentValues.put(AutomobileDatabaseHelper.KEY_STATION, stationAdded);
                                automobileDatabase.update(AutomobileDatabaseHelper.TABLE_NAME, contentValues, "ID = " + presetID, null);

                                loadData();
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

    private class RadioAdapter extends ArrayAdapter<String>
    {
        public RadioAdapter(Context ctx)
        {
            super(ctx, 0);
        }

        public int getCount()
        {
            return 6;
        }

        public String getItem(int position)
        {
            return radioPresets.getItemAtPosition(position).toString();
        }

        public View getView(int position, View convertView, ViewGroup parent)
        {
            return findViewById(R.id.presetStations);
        }
    }

    private void loadData()
    {
        dataList = new ArrayList<>();
        automobileDatabase = new AutomobileDatabaseHelper(this).getWritableDatabase();
        Cursor cursor = automobileDatabase.rawQuery("SELECT * FROM " + AutomobileDatabaseHelper.TABLE_NAME, new String[]{});

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            String station = cursor.getString(
                    cursor.getColumnIndex(AutomobileDatabaseHelper.KEY_STATION));
            dataList.add(station);
            cursor.moveToNext();
        }

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, dataList);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        radioPresets.setAdapter(dataAdapter);
    }

    private Button addRadio;
    private Snackbar radioAdded;
    private String stationAdded;
    private int presetID = 1;
    private SQLiteDatabase automobileDatabase;
    private Spinner radioPresets;
    private Spinner presetNumSpinner;
    private ArrayList<String> dataList;
}
