package com.example.michael.myapplication.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by michael on 5/11/2018.
 */

public class MyDataDbHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "myData.db";
    public static final int DB_VERSION = 1;

    public MyDataDbHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String CREATE_MYDATA_DB = "CREATE TABLE IF NOT EXISTS " +
                MyDataContract.MyData.TABLE_NAME + " (" +
                MyDataContract.MyData._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                MyDataContract.MyData.COLUMN_FIRST_NAME + " VARCHAR(20), " +
                MyDataContract.MyData.COLUMN_LAST_NAME + " VARCHAR(20), " +
                MyDataContract.MyData.COLUMN_STATUS + " VARCHAR(20), " +
                MyDataContract.MyData.COLUMN_COMMENT + " VARCHAR(100) " +
                ")";

        db.execSQL(CREATE_MYDATA_DB);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + MyDataContract.MyData.TABLE_NAME);
        onCreate(db);
    }
}
