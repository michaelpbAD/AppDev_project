package com.example.michael.myapplication;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.michael.myapplication.utilities.DatabaseFiller;

import static com.example.michael.myapplication.data.MyDataContract.MyData.CONTENT_URI;


public class MainFragment extends Fragment implements MyDataAdapter.ListItemClickListener {
    RecyclerView _dataRv;
    MyDataAdapter _dataAdapter;

    FragmentManager _fragmentManager;
    DetailFragment _detailFragment;


    public MainFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        _dataRv = rootView.findViewById(R.id.rv_myData);

        FloatingActionButton FPbAdd = rootView.findViewById(R.id.FPbAdd);
        FPbAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(getContext(), "add buton klikt", Toast.LENGTH_LONG).show();
                Intent addIntent = new Intent(getContext(), AddActivity.class);
                startActivity(addIntent);
            }
        });

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        _dataRv.setLayoutManager(linearLayoutManager);
        _dataRv.setHasFixedSize(true);

        DatabaseFiller dbFiller = new DatabaseFiller(getActivity());

        Cursor testResult = getActivity().getContentResolver().query(CONTENT_URI, null, null, null, null);
        if(testResult.getCount()==0){
            dbFiller.AddValues();
        }
        testResult.close();

        String projection[] = {"_ID", "firstname", "lastname"};
        Cursor myData = getActivity().getContentResolver().query(CONTENT_URI, projection, null, null, null);
//        Toast.makeText(getContext(), "myData: " + myData.getCount(), Toast.LENGTH_LONG).show();
        _dataAdapter = new MyDataAdapter(myData, this);
        _dataRv.setAdapter(_dataAdapter);



        return rootView;
    }
    @Override
    public void onResume() {

        super.onResume();
        //Toast.makeText(getContext(), "onResume ", Toast.LENGTH_LONG).show();
        String projection[] = {"_ID", "firstname", "lastname"};
        Cursor myData = getActivity().getContentResolver().query(CONTENT_URI, projection, null, null, null);
        _dataAdapter.setMyDataData(myData);


    }

    @Override
    public void onListItemClick(int itemPosition, String id) {
        Log.d("I", "onListItemClick ==> _MasterDetail: " + MainActivity._MasterDetail );
        if(!MainActivity._MasterDetail) {
            Intent detailIntent = new Intent(getContext(), DetailActivity.class);
            detailIntent.putExtra("ID", id);
            startActivity(detailIntent);
        } else {
            _fragmentManager = getActivity().getSupportFragmentManager();
            _detailFragment = new DetailFragment();

            String selection = "_ID = ?";
            String selectionArgs[] = { id };
            Uri selectedData = CONTENT_URI.buildUpon().appendPath(id).build();
//            Toast.makeText(getContext(), selectedData.toString(), Toast.LENGTH_LONG).show();
            Cursor Data = getActivity().getContentResolver().query(selectedData, null, selection, selectionArgs, null);
            _detailFragment.setData(Data);
            _fragmentManager.beginTransaction()
                    .replace(R.id.fr_details, _detailFragment)
                    .commit();
        }
    }
//    @Override
//    public void onConfigurationChanged(Configuration newConfig) {
//        super.onConfigurationChanged(newConfig);
//
//        // Checks the orientation of the screen
//        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
//            Toast.makeText(getContext(), "landscape", Toast.LENGTH_SHORT).show();
//            setContentView(R.layout.activity_main);
//        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){
//            Toast.makeText(getContext(), "portrait", Toast.LENGTH_SHORT).show();
//            _fragmentManager.beginTransaction()
//                    .remove(_detailFragment)
//                    .commit();
//        }
//    }

}
