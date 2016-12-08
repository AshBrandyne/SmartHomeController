package com.algonquin.cst2335.smarthomecontroller;

/**
 * @author Ash-Lee Hommy 040840815
 */

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class KitchenDatabaseHelper extends SQLiteOpenHelper {
    private static final String ACTIVITY_NAME = "KitchenDatabase";
    private static String DATABASE_NAME = "Kitchen";
    private static int VERSION_NUM = 1;
    protected static String TABLE_NAME = "DEVICE_LIST";
    private static String KEY_ID = "ID";
    protected static String KEY_TEMP = "TEMP";

    public KitchenDatabaseHelper(Context ctx)
    {
        super(ctx, DATABASE_NAME, null, VERSION_NUM);
    }

    public void onCreate(SQLiteDatabase db)
    {
        Log.i(ACTIVITY_NAME, "Calling onCreate");
        db.execSQL( "CREATE TABLE " + TABLE_NAME + " (" +
                KEY_ID + " INT AUTO_INCREMENT, " +
                KEY_TEMP + " VARCHAR(200), CONSTRAINT DEVICELIST_PK PRIMARY KEY (" +
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




}
