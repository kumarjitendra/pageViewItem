package com.ostendi.developer.pageviewitem.view;

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
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ostendi.developer.pageviewitem.R;
import com.ostendi.developer.pageviewitem.model.Item;

public class PageViewAdapter extends PagedListAdapter<Item, PageViewAdapter.PageViewHolder> {
    private final Context context;
    SharedPreferences preferences;
    Item item;

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
        item = getItem(position);


        if (item != null) {
            holder.lineTextView.setText(String.valueOf(item));
        }
        //Remove a previously setOnCheckedChangeListener.in some cases, it will prevent unwanted situations
        holder.checkBox.setOnCheckedChangeListener(null);

        // setTag:Sets the tag associated with this view. which is used to store data within a view without resorting to another data structure.
        holder.checkBox.setTag(holder);

        if (holder.checkBox.isChecked()) {
            // Boolean value = preferences.getBoolean("checkedState", false);
            //  holder.checkBox.setChecked(value); //setChecked:Changes the checked state of selected checkbox.
        }

        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (holder.checkBox.isChecked()) {
                     Log.e("pAdapter", "isChecked  " + String.valueOf(holder.checkBox.isChecked()));
                    // preferences.edit().putBoolean("checkedState", isChecked).commit();

                    item.setSelected(isChecked);
                    holder.checkBox.setChecked(item.isSelected()); //setChecked:Changes the checked state of selected checkbox.
                    Log.e("pAdapter", "setChecked  " + String.valueOf(holder.checkBox.isChecked()));
                    Log.e("pAdapter", "setCheckedValue  " + String.valueOf(item.isSelected()));

                }
            }
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

