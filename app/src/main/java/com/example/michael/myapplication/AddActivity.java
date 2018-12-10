package com.example.michael.myapplication;

import android.content.ContentValues;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import static com.example.michael.myapplication.data.MyDataContract.MyData.COLUMN_COMMENT;
import static com.example.michael.myapplication.data.MyDataContract.MyData.COLUMN_FIRST_NAME;
import static com.example.michael.myapplication.data.MyDataContract.MyData.COLUMN_LAST_NAME;
import static com.example.michael.myapplication.data.MyDataContract.MyData.COLUMN_STATUS;
import static com.example.michael.myapplication.data.MyDataContract.MyData.CONTENT_URI;

public class AddActivity extends AppCompatActivity {

    EditText _firstname;
    EditText _lastname;
    EditText _status;
    EditText _comment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        _firstname=this.findViewById(R.id.et_firstname);
        _lastname=this.findViewById(R.id.et_lastname);
        _status=this.findViewById(R.id.et_status);
        _comment=this.findViewById(R.id.et_comment);

        Button pbSave = this.findViewById(R.id.pb_save);
        pbSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ContentValues myDataValues = new ContentValues();
                myDataValues.put(COLUMN_FIRST_NAME, _firstname.getText().toString());
                myDataValues.put(COLUMN_LAST_NAME, _lastname.getText().toString());
                myDataValues.put(COLUMN_STATUS, _status.getText().toString());
                myDataValues.put(COLUMN_COMMENT, _comment.getText().toString());
                getContentResolver().insert(CONTENT_URI, myDataValues);
                finish();
            }
        });
    }
}
