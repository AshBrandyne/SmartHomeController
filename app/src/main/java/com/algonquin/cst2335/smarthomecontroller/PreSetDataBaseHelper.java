package com.algonquin.cst2335.smarthomecontroller;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by tyyyl on 2016-10-16.
 */
public class PreSetDataBaseHelper extends SQLiteOpenHelper {


    static final String DATABASE_NAME = "Chats.db";
    static final String chatTable = "Message_Table";
    static final String KEY_ID = "Message_ID";
    static final String KEY_MESSAGE = "Message";


    static int VERSION_NUM = 3;
    static int oldVer = VERSION_NUM;
    static int newVer = (oldVer + 1);

    public PreSetDataBaseHelper(Context ctx) {
        super(ctx, DATABASE_NAME, null, VERSION_NUM);
    }





    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub



        db.execSQL("create table "+chatTable+
                "("+KEY_ID + " integer primary key autoincrement, "
                +KEY_MESSAGE + " text not null"+");" );



        //Inserts pre-defined departments
        // InsertDepts(db);
        Log.i("ChatDatabaseHelper", "Calling onCreate");
    }




    public void onUpgrade(SQLiteDatabase db, int oldversion, int newversion){

        db.execSQL("DROP TABLE IF EXISTS " + chatTable);
        onCreate(db);
        Log.i("ChatDatabaseHelper", "Calling onUpgrade, oldVersion=" + oldVer + " newVersion=" + newVer);
    }
}
