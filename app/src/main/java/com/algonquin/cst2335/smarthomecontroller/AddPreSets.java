package com.algonquin.cst2335.smarthomecontroller;

import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.ArrayList;

import static android.R.attr.data;
import static android.R.id.list;
import static com.algonquin.cst2335.smarthomecontroller.R.id.chatWindow;

public class AddPreSets extends AppCompatActivity {
    View result;
    private EditText chatInput;
    ChatAdapter messageAdapter;
    ArrayList<String> chat = new ArrayList<>();
    String input;
    Button send;
    Button delete;
    Button edit;
    private SQLiteDatabase datasource;
    final String ACTIVITY_NAME = "ChatWindow";
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    public void onDestroy() {

        super.onDestroy();
        datasource.close();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_pre_sets);
        ListView presets = (ListView) findViewById(R.id.chatWindow);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        send = (Button) findViewById(R.id.send);
        //ListView presetWindow = (ListView) findViewById(chatWindow);
        //setSupportActionBar(toolbar);
        messageAdapter = new ChatAdapter(this);

        chatInput = (EditText) findViewById(R.id.chatInput);
        input = chatInput.getText().toString();

        datasource = new PreSetDataBaseHelper(this).getWritableDatabase();
        presets.setAdapter(messageAdapter);
        Cursor cur = datasource.query(PreSetDataBaseHelper.chatTable, new String[]{PreSetDataBaseHelper.KEY_ID, PreSetDataBaseHelper.KEY_MESSAGE}, null, null, null, null, null, null);

        cur.moveToFirst();


        while (!cur.isAfterLast()) {
            String message = cur.getString(cur.getColumnIndex(PreSetDataBaseHelper.KEY_MESSAGE));
            chat.add(message);
            Log.i(ACTIVITY_NAME, "SQL MESSAGE:" + cur.getString(cur.getColumnIndex(PreSetDataBaseHelper.KEY_MESSAGE)));

            cur.moveToNext();
        }

        for (int i = 0; i < cur.getColumnCount(); i++) {
            Log.i(ACTIVITY_NAME, "Cursor’s  column count =" + cur.getColumnCount());

        }

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                input = chatInput.getText().toString();
                chat.add(input);

                messageAdapter.notifyDataSetChanged();
                chatInput.setText("");

                ContentValues contentValues = new ContentValues();
                contentValues.put(PreSetDataBaseHelper.KEY_MESSAGE, input);
                datasource.insert(PreSetDataBaseHelper.chatTable, "Null Place Holder", contentValues);
            }

        });
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Add Presets") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }

    private class ChatAdapter extends ArrayAdapter<String> {

        public ChatAdapter(Context ctx) {
            super(ctx, 0);
        }

        public int getCount() {
            return chat.size();
        }

        public String getItem(int position) {
            return chat.get(position);
        }

        public View getView(int position, View convertView, ViewGroup parent) {

            LayoutInflater inflater = AddPreSets.this.getLayoutInflater();
            result = inflater.inflate(R.layout.presetlayout, null);

            TextView message_text = (TextView) result.findViewById(R.id.message_text);
            message_text.setText(getItem(position));
            edit = (Button) result.findViewById(R.id.editButton);
            delete = (Button) result.findViewById(R.id.deleteButton);
            edit.setBackgroundColor(Color.BLUE);
            delete.setBackgroundColor(Color.RED);
            final int positionToRemove = position;
            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //messageAdapter.remove(chat.get(positionToRemove));
                    chat.remove(messageAdapter.getItem(positionToRemove));

                    messageAdapter.notifyDataSetChanged();
                }
            });

            edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    chatInput.setText(chat.get(positionToRemove));
                    chat.remove(messageAdapter.getItem(positionToRemove));
                    messageAdapter.notifyDataSetChanged();

                }
            });
            return result;

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

            builder.setTitle("Set Temperature Activity");
            builder.setMessage("Activity made by Tyler Woyiwada: " +
                    " Click the blue button to edit the preset, the red one to delete a preset, and Save Preset to save a preset.");
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


}
