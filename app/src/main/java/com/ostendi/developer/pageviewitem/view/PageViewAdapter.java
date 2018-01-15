package com.ostendi.developer.pageviewitem.view;

import android.arch.paging.PagedList;
import android.arch.paging.PagedListAdapter;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.ostendi.developer.pageviewitem.R;
import com.ostendi.developer.pageviewitem.model.Item;

public class PageViewAdapter extends PagedListAdapter<Item, PageViewAdapter.PageViewHolder> {
    private final Context context;
    SharedPreferences preferences;
    Item item;
    PagedList<Item> pagedList;

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
        preferences = PreferenceManager.getDefaultSharedPreferences(context);
        Log.e("pAdapter", "onBindViewHolderCalled  ");
        item = getItem(position);
        if (item != null) {
            holder.lineTextView.setText(String.valueOf(item));
        }
        // setTag:Sets the tag associated with this view. which is used to store data within a view without resorting to another data structure.
        holder.checkBox.setTag(position);

        //Remove a previously setOnCheckedChangeListener.in some cases, it will prevent unwanted situations
        holder.checkBox.setOnCheckedChangeListener(null);
        if (holder.checkBox.isChecked()) {
            holder.checkBox.setChecked(item.getSelected()); //setChecked:Changes the checked state of selected checkbox.
        }
        holder.checkBox.setOnCheckedChangeListener((compoundButton, isChecked) -> {
            if (compoundButton.isPressed()) {
                item.setSelected(isChecked);
            } else item.setSelected(false);
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

