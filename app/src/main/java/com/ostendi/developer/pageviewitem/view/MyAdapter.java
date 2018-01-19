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
import com.ostendi.developer.pageviewitem.model.MyDatSource;

public class MyAdapter extends PagedListAdapter<Item, MyAdapter.ViewHolder> {
    private static String TAG = "MyAdapter";
    private MyDatSource dataSource = new MyDatSource();
    Item item;

    MyAdapter() {
        super(Item.DIFF_CALLBACK);

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Create a new View
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.line_view, parent, false);
        ViewHolder viewholder = new ViewHolder(layoutView);

        //setOnCheckedChangeListener():Register a callback to be invoked when the checked state of this button changes.
        //compoundButton:A button with two states, checked and unchecked. When the button is pressed or clicked, the state changes automatically.
        viewholder.checkBox.setOnCheckedChangeListener((compoundButton, isChecked) -> {
            if (compoundButton.isPressed()) {
                viewholder.checkBox.setChecked(isChecked);
                dataSource.saveSelectedItemInList(viewholder.getAdapterPosition());
            }

        });
        return viewholder;
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