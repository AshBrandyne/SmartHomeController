package com.algonquin.cst2335.smarthomecontroller;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * An activity representing a list of Automobiles. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 */
public class AutomobileListActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_automobile_list);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getTitle());

        activities = new ArrayList<String>();
        activities.add("Temperature Settings");
        activities.add("Fuel Level");
        activities.add("Radio");
        activities.add("GPS");
        activities.add("Lights");
        activities.add("Odometer");
        activities.add("Drive");

        View recyclerView = findViewById(R.id.activity_list);
        assert recyclerView != null;
        setupRecyclerView((RecyclerView) recyclerView);
    }

    private void setupRecyclerView(@NonNull RecyclerView recyclerView)
    {
        recyclerView.setAdapter(new SimpleItemRecyclerViewAdapter(activities));
    }

    public class SimpleItemRecyclerViewAdapter
            extends RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder>
    {

        private final List<String> mValues;

        public SimpleItemRecyclerViewAdapter(List<String> items)
        {
            mValues = items;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
        {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.automobile_list_content, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, int position)
        {
            holder.mItem = mValues.get(position);
            holder.mIdView.setText(mValues.get(position));
            holder.mContentView.setText(mValues.get(position));

            final int activity = position;
            holder.mView.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                Context context = v.getContext();

                switch(activity) {
                    case 0:
                        Intent intent1 = new Intent(context, AutomobileTempActivity.class);
                        context.startActivity(intent1);
                        break;
                    case 1:
                        Intent intent2 = new Intent(context, AutomobileFuelActivity.class);
                        context.startActivity(intent2);
                        break;
                    case 2:
                        Intent intent3 = new Intent(context, AutomobileRadioActivity.class);
                        context.startActivity(intent3);
                        break;
                    case 4:
                        Intent intent5 = new Intent(context, AutomobileLightsActivity.class);
                        context.startActivity(intent5);
                        break;
                    case 5:
                        Intent intent6 = new Intent(context, AutomobileOdometerActivity.class);
                        context.startActivity(intent6);
                        break;
                    case 6:
                        Intent intent7 = new Intent(context, AutomobileDriveActivity.class);
                        context.startActivity(intent7);
                        break;
                    default:
                        break;
                }

                }
            });
        }

        @Override
        public int getItemCount()
        {
            return mValues.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder
        {
            public final View mView;
            public final TextView mIdView;
            public final TextView mContentView;
            public String mItem;

            public ViewHolder(View view)
            {
                super(view);
                mView = view;
                mIdView = (TextView) view.findViewById(R.id.id);
                mContentView = (TextView) view.findViewById(R.id.content);
            }

            @Override
            public String toString()
            {
                return super.toString() + " '" + mContentView.getText() + "'";
            }
        }
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

            builder.setTitle("Microwave Settings");
            builder.setMessage((Html.fromHtml("Enter a time and press start" +
                    "<p>Press Stop to pause the timer" +
                    "<p>Press Reset to reset the timer" +
                    "<p>Try pressing '6047' and Start for a surprise" +
                    "<p>This Activity by Ash-Lee Hommy for CST 2335")));
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


    ArrayList<String> activities;
}
