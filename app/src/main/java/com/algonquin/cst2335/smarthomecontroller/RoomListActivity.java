package com.algonquin.cst2335.smarthomecontroller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
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
                    startActivity(new Intent(RoomListActivity.this, HomeSubMenu.class));
                } else if (selectedItem.equalsIgnoreCase(getResources().getString(R.string.roomLiving))){
                    startActivity(new Intent(RoomListActivity.this, LivingRoomListActivity.class));
                } else if (selectedItem.equalsIgnoreCase(getResources().getString(R.string.roomKitchen))){
                    startActivity(new Intent(RoomListActivity.this, KitchenListActivity.class));
                } else if (selectedItem.equalsIgnoreCase(getResources().getString(R.string.roomAuto))){
                    startActivity(new Intent(RoomListActivity.this, AutomobileListActivity.class));
                }
            }
        });
    }
}
