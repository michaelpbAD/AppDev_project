package com.example.michael.myapplication.data;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by michael on 5/11/2018.
 */

public class MyDataContract {
    public static final String AUTHORITY = "com.example.michael.myapplication";
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + AUTHORITY);
    public static final String MYDATA_PATH = "myData";

    private MyDataContract() {

    }

    public static class MyData implements BaseColumns {
        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(MYDATA_PATH).build();
        public static final String TABLE_NAME = "profile";
        public static final String COLUMN_FIRST_NAME = "firstname";
        public static final String COLUMN_LAST_NAME = "lastname";
        public static final String COLUMN_STATUS = "status";
        public static final String COLUMN_COMMENT = "comment";


    }
}
