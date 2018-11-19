package com.example.michael.myapplication.data;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import static com.example.michael.myapplication.data.MyDataContract.AUTHORITY;
import static com.example.michael.myapplication.data.MyDataContract.MYDATA_PATH;

/**
 * Created by michael on 5/11/2018.
 */

public class MyDataProvider extends ContentProvider {

    private static final UriMatcher sUriMatcher = buildUriMatcher();
    private MyDataDbHelper _MyDataDbHelper;
    private static final int ALL_MYDATA = 100;
    private static final int MYDATA_ID = 101;

    private static UriMatcher buildUriMatcher() {
        UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        // Uri for complete myData table
        uriMatcher.addURI(AUTHORITY, MYDATA_PATH, ALL_MYDATA);
        // Uri for one row in myData table
        uriMatcher.addURI(AUTHORITY, MYDATA_PATH + "/#", MYDATA_ID);

        return uriMatcher;
    }

    @Override
    public boolean onCreate() {
        Context context = getContext();
        _MyDataDbHelper = new MyDataDbHelper(context);

        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        final SQLiteDatabase db = _MyDataDbHelper.getReadableDatabase();

        Cursor MyDataData;
        int match = sUriMatcher.match(uri);

        switch (match) {
            case ALL_MYDATA:
                MyDataData = db.query(MyDataContract.MyData.TABLE_NAME, projection, null, null, null, null, null);
                break;
            case MYDATA_ID:
                MyDataData = db.query(MyDataContract.MyData.TABLE_NAME, projection, selection, selectionArgs, null, null, null);
                break;
            default:
                throw new UnsupportedOperationException("Unknown Uri: " + uri);
        }
        return MyDataData;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        final SQLiteDatabase db = _MyDataDbHelper.getWritableDatabase();

        int match = sUriMatcher.match(uri);
        Uri returnUri = null;

        switch (match) {
            case ALL_MYDATA:
                long id = db.insert(MyDataContract.MyData.TABLE_NAME, null, values);
                if (id > 0) {
                    returnUri = ContentUris.withAppendedId(MyDataContract.MyData.CONTENT_URI, id);
                } else {
                    throw new SQLException("Failed to insert row into " + uri);
                }
                break;
            default:
                throw new UnsupportedOperationException("Unknown Uri: " + uri);
        }

        getContext().getContentResolver().notifyChange(uri, null);

        return returnUri;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        final SQLiteDatabase db = _MyDataDbHelper.getWritableDatabase();
        int deleted = 0;

        int match = sUriMatcher.match(uri);

        switch(match) {
            case ALL_MYDATA:
                break;
            case MYDATA_ID:
                deleted = db.delete(MyDataContract.MyData.TABLE_NAME, selection, selectionArgs);
                break;
            default:
                throw new UnsupportedOperationException("Unknown Uri " + uri);
        }

        return deleted;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }
}
