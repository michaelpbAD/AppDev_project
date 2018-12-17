package com.example.michael.myapplication;

import android.content.Context;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import static android.provider.BaseColumns._ID;
import static com.example.michael.myapplication.data.MyDataContract.MyData.COLUMN_FIRST_NAME;
import static com.example.michael.myapplication.data.MyDataContract.MyData.COLUMN_LAST_NAME;

/**
 * Created by michael on 5/11/2018.
 */

public class MyDataAdapter extends RecyclerView.Adapter<MyDataAdapter.MyDataViewHolder> {

    private Cursor MyDataData;
    private final ListItemClickListener _OnListItemClick;

    public interface ListItemClickListener {
        void onListItemClick(int itemPosition, String id);
    }

    public MyDataAdapter(Cursor MyDataCursor, ListItemClickListener listItemClickListener) {
        MyDataData = MyDataCursor;
        _OnListItemClick = listItemClickListener;
    }

    @NonNull
    @Override
    public MyDataViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();

        int layoutIdForListItem = R.layout.mydata_item;
        LayoutInflater inflater = LayoutInflater.from(context);

        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(layoutIdForListItem, parent, shouldAttachToParentImmediately);
        MyDataViewHolder viewHolder = new MyDataViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyDataViewHolder holder, int position) {
        MyDataData.moveToPosition(position);
        holder.firstName.setText(MyDataData.getString(MyDataData.getColumnIndex(COLUMN_FIRST_NAME)));
        holder.lastName.setText(MyDataData.getString(MyDataData.getColumnIndex(COLUMN_LAST_NAME)));
        holder.lastName.setTag(MyDataData.getInt(MyDataData.getColumnIndex(_ID)));
    }

    @Override
    public int getItemCount() {
        return MyDataData.getCount();
    }

    class MyDataViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView firstName;
        TextView lastName;

        public MyDataViewHolder(View itemView) {
            super(itemView);

            firstName = itemView.findViewById(R.id.tv_firstname);
            lastName = itemView.findViewById(R.id.tv_lastname);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            String id = v.findViewById(R.id.tv_lastname).getTag().toString();
            _OnListItemClick.onListItemClick(position, id);
        }
    }

    public void setMyDataData(Cursor cursor){
        MyDataData.close();
        MyDataData = cursor;
         notifyDataSetChanged();
     }
}
