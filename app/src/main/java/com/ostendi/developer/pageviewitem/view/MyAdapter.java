package com.ostendi.developer.pageviewitem.view;


import android.arch.paging.PagedList;
import android.arch.paging.PagedListAdapter;
import android.content.Context;
import android.content.SharedPreferences;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.ostendi.developer.pageviewitem.R;
import com.ostendi.developer.pageviewitem.model.Item;
import com.ostendi.developer.pageviewitem.model.DataSource;

public class MyAdapter extends PagedListAdapter<Item, MyAdapter.MyViewHolder> {
    // sparse boolean array for checking the state of the items
    private static String TAG = "MyAdapter";
    private final Context context;
    Item item;
    
    MyAdapter(Context context) {
        super(Item.DIFF_CALLBACK);
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Create a new View
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.line_view, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(layoutView);

        myViewHolder.checkBox.setOnCheckedChangeListener((compoundButton, isChecked) -> {
            item = getItem(myViewHolder.getAdapterPosition());
            Log.d(TAG, "item at position " + myViewHolder.getAdapterPosition() + " :" + item);
            myViewHolder.checkBox.setChecked(isChecked);
            DataSource.saveSelectedItemInList(myViewHolder.getAdapterPosition());
        });
        return myViewHolder;

    }

    //Data is bound to views
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        /**
         * call the method getItem(position) in PagedListAdapter to get item on position based
         */
        item = getItem(position);
        if (item != null) {
            holder.lineTextView.setText(String.valueOf(item));
        }
        item = getItem(holder.getAdapterPosition());
        holder.lineTextView.setText(String.valueOf(item));
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView lineTextView;
        public CheckBox checkBox;

        public MyViewHolder(View layoutView) {
            super(layoutView);
            lineTextView = layoutView.findViewById(R.id.line);
            checkBox = layoutView.findViewById(R.id.checkbox);
        }
    }
}

