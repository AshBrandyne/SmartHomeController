package com.algonquin.cst2335.smarthomecontroller;


import android.app.ListActivity;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.algonquin.cst2335.smarthomecontroller.R;

/**
 * Created by tyyyl on 2016-12-03.
 */

public class HomeSubMenuLstFragment extends ListFragment implements AdapterView.OnItemClickListener {
    String[] datasource={"Garage", "Home Thermostat", "Outside Weather"};
    String[] details={"Control your garage door.  Turn the lights on or off.  All at the flick of a finger!",
            "Adjust the temperature around you with barely more than a passing thought like a deity!  Also set a schedule for temperature changes.",
            "Like a telepathic wizard, instantly observethe current weather and temperature outside in real time!"};
    //String message = "testTestTest";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState){
        //Inflate the fragment layout file
        ViewGroup rootView=(ViewGroup)inflater.inflate(R.layout.homesubmenufragment, container, false);
        //Create datasource

        //Bind adapter to listFragment
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(getActivity(), R.layout.rowlayout,R.id.txtitem, datasource);
        setListAdapter(adapter);
        setRetainInstance(true);
        return rootView;
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ArrayAdapter adapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.Options, android.R.layout.simple_list_item_1);
        setListAdapter(adapter);
        getListView().setOnItemClickListener(this);
    }


    //Handling item click
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id){

        Toast.makeText(getActivity(), details[position], Toast.LENGTH_LONG).show();
        if(position == 1)
        {
            Intent intent = new Intent(getActivity() , HomeTemp.class);
            startActivity(intent);
        }
        if(position == 0)
        {
           Intent intent = new Intent(getActivity() , GarageSettings.class);
           startActivity(intent);
        }
    }
   }
    //muscleman.png image credit: http://appointmentschedulingnews.com/wp-content/uploads/2016/03/schedulingsystempoweruser.png

