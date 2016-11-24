package com.algonquin.cst2335.smarthomecontroller;

/**
 * Created by James on 24/11/2016.
 */

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class AutomobileDatabaseHelper extends SQLiteOpenHelper
{
    public AutomobileDatabaseHelper(Context ctx)
    {
        super(ctx, DATABASE_NAME, null, VERSION_NUM);
    }

    public void onCreate(SQLiteDatabase db)
    {
        Log.i(ACTIVITY_NAME, "Calling onCreate");
        db.execSQL( "CREATE TABLE " + TABLE_NAME + " (" +
                KEY_ID + " INT AUTO_INCREMENT, " +
                KEY_MESSAGE + " VARCHAR(200), CONSTRAINT MESSAGELIST_PK PRIMARY KEY (" +
                KEY_ID + "));"
        );
    }

    public void onUpgrade(SQLiteDatabase db, int oldVer, int newVer)
    {
        Log.i(ACTIVITY_NAME, "Calling onUpgrade, oldVersion = " + oldVer
                + " newVersion = " + newVer);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVer, int newVer)
    {
        Log.i(ACTIVITY_NAME, "Calling onDowngrade, oldVersion = " + oldVer
                + " newVersion = " + newVer);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }



    private static final String ACTIVITY_NAME = "AutomobileDatabase";
    private static String DATABASE_NAME = "Database 1";
    private static int VERSION_NUM = 1;
    protected static String TABLE_NAME = "MESSAGE_LIST";
    private static String KEY_ID = "ID";
    protected static String KEY_MESSAGE = "MESSAGE";
}
