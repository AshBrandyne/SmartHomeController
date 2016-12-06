package com.algonquin.cst2335.smarthomecontroller;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    protected static final String ACTIVITY_NAME = "LoginActivity";
    protected SharedPreferences sharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        Button lButton = (Button) findViewById(R.id.loginButton);
        final EditText loginField = (EditText) findViewById(R.id.loginField);
        ImageView logo = (ImageView) findViewById(R.id.logoLogin);

        loginField.setText(sharedPref.getString("DefaultEmail", "email@domain.com"));

        lButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putString("DefaultEmail", loginField.getText().toString());
                editor.commit();

                Intent intent = new Intent(LoginActivity.this, RoomListActivity.class);
                startActivity(intent);
            }

        });

        logo.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        Toast.makeText(getApplicationContext(), "Welcome home!", Toast.LENGTH_LONG).show();
                                    }
                                }
        );

        Log.i(ACTIVITY_NAME, "In onCreate()");
    }

}
