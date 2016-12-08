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
        db.execSQL("CREATE TABLE " + TABLE_NAME + " (" +
                KEY_ID + " INTEGER(1), " +
                KEY_STATION + " CHAR(7), CONSTRAINT RadioStations_PK PRIMARY KEY (" +
                KEY_ID + "));"
        );

        db.execSQL("INSERT INTO " + TABLE_NAME + " VALUES (1, 'None');");
        db.execSQL("INSERT INTO " + TABLE_NAME + " VALUES (2, 'None');");
        db.execSQL("INSERT INTO " + TABLE_NAME + " VALUES (3, 'None');");
        db.execSQL("INSERT INTO " + TABLE_NAME + " VALUES (4, 'None');");
        db.execSQL("INSERT INTO " + TABLE_NAME + " VALUES (5, 'None');");
        db.execSQL("INSERT INTO " + TABLE_NAME + " VALUES (6, 'None');");
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
    private static String DATABASE_NAME = "Automobile Database";
    private static int VERSION_NUM = 1;
    protected static String TABLE_NAME = "RadioStations";
    protected static String KEY_ID = "ID";
    protected static String KEY_STATION = "Station";
}
