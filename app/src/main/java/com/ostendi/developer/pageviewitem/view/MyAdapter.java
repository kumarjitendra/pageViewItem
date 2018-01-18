package com.ostendi.developer.pageviewitem.view;


import android.arch.paging.PagedListAdapter;

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

public class MyAdapter extends PagedListAdapter<Item, MyAdapter.ViewHolder> {
    private static String TAG = "MyAdapter";
    Item item;
    private DataSource dataSource;

    MyAdapter() {
        super(Item.DIFF_CALLBACK);

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Create a new View
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.line_view, parent, false);
        ViewHolder viewHolder = new ViewHolder(layoutView);
        dataSource = new DataSource();
        viewHolder.checkBox.setOnCheckedChangeListener((compoundButton, isChecked) -> {
            if(compoundButton.isChecked())
            item = getItem(viewHolder.getAdapterPosition());
            Log.d(TAG, "item at position " + viewHolder.getAdapterPosition() + " :" + item);
            viewHolder.checkBox.setChecked(isChecked);
            dataSource.saveSelectedItemInList(viewHolder.getAdapterPosition());
        });
        return viewHolder;

    }

    //Data is bound to views
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
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

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView lineTextView;
        public CheckBox checkBox;

        public ViewHolder(View layoutView) {
            super(layoutView);
            lineTextView = layoutView.findViewById(R.id.line);
            checkBox = layoutView.findViewById(R.id.checkbox);
        }
    }
}