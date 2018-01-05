package com.ostendi.developer.pageviewitem.view;


import android.arch.paging.PagedListAdapter;
import android.content.ContentValues;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ostendi.developer.pageviewitem.R;
import com.ostendi.developer.pageviewitem.model.Item;

public class PageViewAdapter extends PagedListAdapter<Item, PageViewAdapter.PageViewHolder> {
    PageViewAdapter() {
        super(Item.DIFF_CALLBACK);
    }

    @Override
    public PageViewAdapter.PageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Create a new View
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.line_view, parent, false);
        PageViewHolder pageViewHolder = new PageViewHolder(layoutView);
        return pageViewHolder;
    }

    @Override
    public void onBindViewHolder(PageViewAdapter.PageViewHolder holder, int position) {
        holder.bind(holder, position);
    }

    public class PageViewHolder extends RecyclerView.ViewHolder {
        public TextView lineTextView;
        public LinearLayout container;
        public CheckBox checkBox;
        boolean checked = false;
        Item item;

        public PageViewHolder(View layoutView) {
            super(layoutView);
            lineTextView = (TextView) layoutView.findViewById(R.id.line);
            container = (LinearLayout) layoutView.findViewById(R.id.container);
            checkBox = (CheckBox) layoutView.findViewById(R.id.checkbox);
        }

        private void bind(PageViewHolder holder, int position) {
            item = getItem(position);
            PageViewHolder pageViewHolder = (PageViewHolder) holder;
            if (item != null) {
                pageViewHolder.lineTextView.setText(String.valueOf(item));
            }
            pageViewHolder.itemView.setTag(position);
            checkBox.setChecked(checked); //By default make it unchecked

            //getting the checkbox value whenever clicked
            //CompoundButton: A button with two states, checked and unchecked. When the button is pressed or clicked,the state changes automatically.
            checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    //saving the checkbox value in database
                    //ContentValues:store a set of values that the ContentResolver can process.
                    ContentValues cv = new ContentValues();
                    //put :Adds a value to the set.
                    cv.put(String.valueOf(item), isChecked);
                    Log.e("checkboxvalue", String.valueOf(cv));

                    // Save the checked line into datasource so that when it is resumed after closing the application it should remember the checked line
                    // db.update(Contract.TABLE_TODO.TABLE_NAME, cv, Contract.TABLE_TODO._ID + "=" + id, null);
                }
            });
        }
    }
}

