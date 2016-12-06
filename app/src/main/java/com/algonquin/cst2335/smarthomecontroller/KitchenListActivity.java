package com.algonquin.cst2335.smarthomecontroller;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;


import com.algonquin.cst2335.smarthomecontroller.dummy.DummyContent;

import java.util.ArrayList;
import java.util.List;

/**
 * Ash-Lee's Kitchen Branch
 *
 * Icons from Icons8.com
 * <a href="https://icons8.com">Icon pack by Icons8</a>
 *
 * An activity representing a list of Kitchens. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a {@link KitchenDetailActivity} representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 *
 * Design: Populate the List in order of how many times clicked
 * Depending on device, use fragment or detail activity
 * Have a button to add new Appliance, it will let you add a new one to the list
 *
 */
public class KitchenListActivity extends AppCompatActivity {
    protected final static String ACTIVITY_NAME = "ChatWindow";
    ArrayList<String> kitchenItems; //arrayList to add to the menu
    EditText newItem; //editText for if user wants to add new item, this will change to an Alert box
    ItemAdapter itemAdapter;
    SQLiteDatabase database;
    Cursor cursor;
    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private boolean mTwoPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kitchen_list);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getTitle());

        kitchenItems = new ArrayList<>();
        kitchenItems.add("Lights");
        kitchenItems.add("Microwave");
        kitchenItems.add("Refrigerator");

        DatabaseConnector databaseConnector = new DatabaseConnector();
        databaseConnector.execute();

        itemAdapter = new ItemAdapter(this);
        ListView theItems = (ListView) findViewById(R.id.kitchenItemsListView);
        Button okButton = (Button) findViewById(R.id.newItemKButton);
        theItems.setAdapter(itemAdapter);





        if (findViewById(R.id.kitchen_detail_container) != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-w900dp).
            // If this view is present, then the
            // activity should be in two-pane mode.
            mTwoPane = true;
        }
    }

    private class DatabaseConnector extends AsyncTask<String, Integer, String> {
        protected String doInBackground(String... args) {


            return "";
        }

        protected void onProgressUpdate(Integer... value) {

        }

        protected void onPostExecute(String result) {

        }
    }

    private class ItemAdapter extends ArrayAdapter<String> {
        /**
         * Passes the context param to the parent constructor. Passing 0 as the resource param
         * because we will not be using the default layout
         * @param context
         */
        public ItemAdapter(Context context) {
            super(context, 0);
        }

        @Override
        public int getCount() {
            //returns the number of rows that will be in my listView
            //= number of strings in the array list object
            return kitchenItems.size();
        }

        @Override
        public String getItem(int position) {
            //returns the item to show in the list at the specified position
            return kitchenItems.get(position);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            //returns layout that will be positioned at the specified position in the list

            //this recreates View made in resource file
            LayoutInflater inflater = KitchenListActivity.this.getLayoutInflater();

            View result = inflater.inflate(R.layout.kitchen_list_content, null);

            TextView message = (TextView) result.findViewById(R.id.message_text);
            ImageView icon = (ImageView) result.findViewById(R.id.message_pic);

            final String itemText = getItem(position); //this is what is sent in the bundle
            message.setText(itemText);

            if (itemText.contains("Light")) {
                icon.setBackgroundResource(R.drawable.light_settings);
            } else if (itemText.contains("Microwave")) {
                icon.setBackgroundResource(R.drawable.microwave);
            } else if (itemText.contains("Refrigerator")) {
                icon.setBackgroundResource(R.drawable.fridge);
            }

            result.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (mTwoPane) {
                        Bundle arguments = new Bundle();
                        arguments.putString(KitchenDetailFragment.ARG_ITEM_ID, itemText);
                        KitchenDetailFragment fragment = new KitchenDetailFragment();
                        fragment.setArguments(arguments);
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.kitchen_detail_container, fragment)
                                .commit();
                    } else {
                        Context context = v.getContext();
                        Intent intent = new Intent(context, KitchenDetailActivity.class);
                        intent.putExtra(KitchenDetailFragment.ARG_ITEM_ID, itemText);

                        context.startActivity(intent);
                    }
                }
            });
            return result;
        }


    }

    protected void onDestroy() {
        super.onDestroy();

    }
}
