package com.algonquin.cst2335.smarthomecontroller;

import android.net.Uri;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.ListFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

public class HomeSubMenu extends AppCompatActivity {

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_sub_menu);
        HomeSubMenuLstFragment lstFragment = (HomeSubMenuLstFragment)getSupportFragmentManager().findFragmentByTag("lstfragment");
        if(lstFragment==null){
            lstFragment=new HomeSubMenuLstFragment();
            FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();
            transaction.add(android.R.id.content,lstFragment,"lstfragment");
            transaction.commit();
        }
    }


}
