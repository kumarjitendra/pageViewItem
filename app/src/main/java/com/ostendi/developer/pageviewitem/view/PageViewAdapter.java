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
import com.ostendi.developer.pageviewitem.model.PageDataSource;

public class PageViewAdapter extends PagedListAdapter<Item, PageViewAdapter.PageViewHolder> {
    private static String TAG = "PageViewAdapter";
    private final Context context;
    SharedPreferences preferences;
    Item item;
    PagedList<Item> items;

    PageViewAdapter(Context context) {
        super(Item.DIFF_CALLBACK);
        this.context = context;
    }

    @Override
    public PageViewAdapter.PageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Create a new View
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.line_view, parent, false);
        PageViewHolder pageViewHolder = new PageViewHolder(layoutView);
        layoutView.setTag(pageViewHolder);
        return pageViewHolder;
    }

    //Data is bound to views
    @Override
    public void onBindViewHolder(PageViewAdapter.PageViewHolder holder, int position) {
        //call the method getItem() in PagedListAdapter to get item on position based
        //item = getItem(holder.getLayoutPosition());
        // item = getItem(holder.getAdapterPosition());
        item = getItem(position);
        Log.e(TAG, "item at position " + position + " : " + String.valueOf(item));
        Log.e(TAG, "layoutPosition " + holder.getLayoutPosition());

        if (item != null) {
            holder.lineTextView.setText(String.valueOf(item));
        }
        //Remove a previously setOnCheckedChangeListener.in some cases, it will prevent unwanted situations
        // holder.checkBox.setOnCheckedChangeListener(null);

        holder.checkBox.setOnCheckedChangeListener((compoundButton, isChecked) -> {
            if (compoundButton.isPressed()) {
                item.setSelected(isChecked);
            } else {
                item.setSelected(false);
            }
            holder.checkBox.setChecked(item.getSelected());
            Log.e(TAG, "setChecked value " + item.getSelected());
            Log.e(TAG, "item at correponding checked value :" + String.valueOf(item));
            PageDataSource.saveSelectedItemInList(item);
        });
    }

    public class PageViewHolder extends RecyclerView.ViewHolder {
        public TextView lineTextView;
        public CheckBox checkBox;

        public PageViewHolder(View layoutView) {
            super(layoutView);
            lineTextView = layoutView.findViewById(R.id.line);
            checkBox = layoutView.findViewById(R.id.checkbox);
        }
    }
}

