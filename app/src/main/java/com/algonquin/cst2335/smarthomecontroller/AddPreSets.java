package com.algonquin.cst2335.smarthomecontroller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.ArrayList;

public class AddPreSets extends AppCompatActivity {
    private EditText chatInput;
    private ListView chatWindow;
    ArrayList<String> chat = new ArrayList<>();
    String input;
    Button send;
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
        ListView presets = (ListView) findViewById(R.id.home_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        send = (Button) findViewById(R.id.send);

        setSupportActionBar(toolbar);
        final ChatAdapter messageAdapter = new ChatAdapter(this);
        chatWindow.setAdapter(messageAdapter);
        chatInput = (EditText) findViewById(R.id.chatInput);
        input = chatInput.getText().toString();

        datasource = new PreSetDataBaseHelper(this).getWritableDatabase();

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
                .setName("ChatWindow Page") // TODO: Define a title for the content shown.
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

//        public View getView(int position, View convertView, ViewGroup parent) {
//
//            LayoutInflater inflater = ChatWindow.this.getLayoutInflater();
//            View result = null;
//            if (position % 2 == 0) {
//                result = inflater.inflate(R.layout.chat_row_incoming, null);
//            } else {
//                result = inflater.inflate(R.layout.chat_row_outgoing, null);
//            }
//            TextView message_text = (TextView) result.findViewById(R.id.message_text);
//            message_text.setText(getItem(position));
//            return result;
//
//        }
    }


}