package com.example.michael.myapplication;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.michael.myapplication.data.MyDataContract;

import static com.example.michael.myapplication.data.MyDataContract.MyData.CONTENT_URI;

public class DetailFragment extends Fragment {

    TextView _firstname;
    TextView _lastname;
    TextView _status;
    TextView _comment;

    public Cursor _Data;

    public DetailFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d("I", "DetailFragment ==> onCreateView" );
        View rootView = inflater.inflate(R.layout.fragment_detail, container, false);

        _firstname = rootView.findViewById(R.id.tv_firstname);
        _lastname = rootView.findViewById(R.id.tv_lastname);
        _status = rootView.findViewById(R.id.tv_status);
        _comment = rootView.findViewById(R.id.tv_comment);

        if(!MainActivity._MasterDetail) {

            Intent receivedIntent = getActivity().getIntent();
            String id = receivedIntent.getStringExtra("ID");
            if(id != null){
                String selection = "_ID = ?";
                String selectionArgs[] = {id};
                Uri selectedData = CONTENT_URI.buildUpon().appendPath(id).build();
//            Toast.makeText(getContext(), selectedData.toString(), Toast.LENGTH_LONG).show();
                _Data = getActivity().getContentResolver().query(selectedData, null, selection, selectionArgs, null);

            }
        }
        if(_Data!= null){
            _Data.moveToFirst();
            _firstname.setText(_Data.getString(_Data.getColumnIndex(MyDataContract.MyData.COLUMN_FIRST_NAME)));
            _lastname.setText(_Data.getString(_Data.getColumnIndex(MyDataContract.MyData.COLUMN_LAST_NAME)));
            _status.setText(_Data.getString(_Data.getColumnIndex(MyDataContract.MyData.COLUMN_STATUS)));
            _comment.setText(_Data.getString(_Data.getColumnIndex(MyDataContract.MyData.COLUMN_COMMENT)));
        }


        return rootView;
    }
    public void setData(Cursor lData) {
        _Data = lData;

    }

}
