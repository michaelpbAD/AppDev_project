package com.example.michael.myapplication.utilities;

import android.content.ContentValues;
import android.content.Context;

import static com.example.michael.myapplication.data.MyDataContract.MyData.COLUMN_COMMENT;
import static com.example.michael.myapplication.data.MyDataContract.MyData.COLUMN_FIRST_NAME;
import static com.example.michael.myapplication.data.MyDataContract.MyData.COLUMN_LAST_NAME;
import static com.example.michael.myapplication.data.MyDataContract.MyData.COLUMN_STATUS;
import static com.example.michael.myapplication.data.MyDataContract.MyData.CONTENT_URI;

/**
 * Created by michael on 5/11/2018.
 */

public class DatabaseFiller {

    ContentValues myDataValues;
    Context _Context;

    public DatabaseFiller(Context context) {
        _Context = context;
    }

    public void AddValues() {
        myDataValues = new ContentValues();
        myDataValues.put(COLUMN_FIRST_NAME, "Lars");
        myDataValues.put(COLUMN_LAST_NAME, "Struyf");
        myDataValues.put(COLUMN_STATUS, "docent");
        myDataValues.put(COLUMN_COMMENT, "C++, Android, Parallel Processing");
        _Context.getContentResolver().insert(CONTENT_URI, myDataValues);
        myDataValues.clear();

        myDataValues = new ContentValues();
        myDataValues.put(COLUMN_FIRST_NAME, "Wim");
        myDataValues.put(COLUMN_LAST_NAME, "Dams");
        myDataValues.put(COLUMN_STATUS, "docent");
        myDataValues.put(COLUMN_COMMENT, "Qt, Cross Development");
        _Context.getContentResolver().insert(CONTENT_URI, myDataValues);
        myDataValues.clear();

        myDataValues = new ContentValues();
        myDataValues.put(COLUMN_FIRST_NAME, "Sofie");
        myDataValues.put(COLUMN_LAST_NAME, "Beerens");
        myDataValues.put(COLUMN_STATUS, "docent");
        myDataValues.put(COLUMN_COMMENT, "C, Webdevelopment, Angular");
        _Context.getContentResolver().insert(CONTENT_URI, myDataValues);
        myDataValues.clear();

        myDataValues = new ContentValues();
        myDataValues.put(COLUMN_FIRST_NAME, "Phaedra");
        myDataValues.put(COLUMN_LAST_NAME, "Degreef");
        myDataValues.put(COLUMN_STATUS, "docent");
        myDataValues.put(COLUMN_COMMENT, "OS, Netwerken");
        _Context.getContentResolver().insert(CONTENT_URI, myDataValues);
        myDataValues.clear();

        myDataValues = new ContentValues();
        myDataValues.put(COLUMN_FIRST_NAME, "Johan");
        myDataValues.put(COLUMN_LAST_NAME, "Van Bauwel");
        myDataValues.put(COLUMN_STATUS, "docent");
        myDataValues.put(COLUMN_COMMENT, "Python");
        _Context.getContentResolver().insert(CONTENT_URI, myDataValues);
        myDataValues.clear();


    }
}
