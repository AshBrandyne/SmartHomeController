package com.algonquin.cst2335.smarthomecontroller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

    private TextView frontTemp;
    private ImageButton frontTempUp;
    private ImageButton frontTempDown;
    private Spinner frontFan;
    private TextView backTemp;
    private ImageButton backTempUp;
    private ImageButton backTempDown;
    private Spinner backFan;
}


