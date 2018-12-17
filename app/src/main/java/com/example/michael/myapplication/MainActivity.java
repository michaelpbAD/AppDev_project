package com.example.michael.myapplication;

import android.content.Intent;
import android.content.res.Configuration;
import android.database.Cursor;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.example.michael.myapplication.utilities.DatabaseFiller;

import static com.example.michael.myapplication.data.MyDataContract.MyData.CONTENT_URI;

public class MainActivity extends AppCompatActivity implements MyDataAdapter.ListItemClickListener{

    RecyclerView _MyDataRv;
    MyDataAdapter _MyDataAdapter;
    public static boolean _MasterDetail = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        _MyDataRv = findViewById(R.id.rv_myData);

//        DatabaseFiller dbFiller = new DatabaseFiller(this);
//
//        Cursor testResult = getContentResolver().query(CONTENT_URI, null, null, null, null);
//        if(testResult.getCount()==0){
//            dbFiller.AddValues();
//        }

        if(findViewById(R.id.ll_twopane) != null) {
            _MasterDetail = true;
        }else{
            _MasterDetail = false;
            Log.d("I", "MainActivity ==> _MasterDetail: " + MainActivity._MasterDetail );
        }

/*
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        _MyDataRv.setLayoutManager(linearLayoutManager);
        _MyDataRv.setHasFixedSize(true);

        String projection[] = {"_ID", "firstname", "lastname"};
        Cursor MyDataData = getContentResolver().query(CONTENT_URI, projection, null, null, null);
        Toast.makeText(this, "MyDataData: " + MyDataData.getCount(), Toast.LENGTH_LONG).show();
        _MyDataAdapter = new MyDataAdapter(MyDataData, this);
        _MyDataRv.setAdapter(_MyDataAdapter);
*/
    }

    @Override
    public void onListItemClick(int itemPosition, String id) {
//        Intent detailIntent = new Intent(MainActivity.this, DetailActivity.class);
//        detailIntent.putExtra("lectorID", id);
//        startActivity(detailIntent);

    }

}
