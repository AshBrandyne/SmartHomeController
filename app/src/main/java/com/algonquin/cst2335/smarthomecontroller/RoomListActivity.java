package com.algonquin.cst2335.smarthomecontroller;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class RoomListActivity extends AppCompatActivity {
    ArrayList<String> rooms;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_list);

        ListView roomsList = (ListView) findViewById(R.id.roomListView);
        rooms = new ArrayList<>();
        rooms.add(getResources().getString(R.string.roomHome));
        rooms.add(getResources().getString(R.string.roomLiving));
        rooms.add(getResources().getString(R.string.roomKitchen));
        rooms.add(getResources().getString(R.string.roomAuto));
        final MediaPlayer doorBellSound = MediaPlayer.create(this, R.raw.doorbell);

        roomsList.setAdapter(new ArrayAdapter<>(
                this, R.layout.list_item, rooms
        ));

        roomsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View itemClicked, int position, long id) {
                TextView textView = (TextView) itemClicked;
                String selectedItem = textView.getText().toString();
                //if strTest = name of menu item string, launch that activity
                if (selectedItem.equalsIgnoreCase(getResources().getString(R.string.roomHome))){
                    doorBellSound.start();
                    startActivity(new Intent(RoomListActivity.this, HomeSubMenu.class));
                } else if (selectedItem.equalsIgnoreCase(getResources().getString(R.string.roomLiving))){
                    startActivity(new Intent(RoomListActivity.this, LRHome.class));
                } else if (selectedItem.equalsIgnoreCase(getResources().getString(R.string.roomKitchen))){
                    startActivity(new Intent(RoomListActivity.this, KitchenListActivity.class));
                } else if (selectedItem.equalsIgnoreCase(getResources().getString(R.string.roomAuto))){
                    startActivity(new Intent(RoomListActivity.this, AutomobileListActivity.class));
                }
            }
        });
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

            builder.setTitle("SmartHomeController for CST2335");
            builder.setMessage((Html.fromHtml("Choose Home to adjust home settings" +
                    "<p>Choose LivingRoom to adjust Living Room settings" +
                    "<p>Choose Kitchen to adjust Kitchen settings" +
                    "<p>Choose Automobile to adjust Automobile settings" +
                    "<p>This Activity was designed by Ash-Lee Hommy, Jessica Stratton, James Jarrett and Tyler Woyiwada for CST 2335")));
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
}
