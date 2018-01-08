package com.ostendi.developer.pageviewitem.view;

import android.arch.paging.PagedListAdapter;
import android.content.Context;
import android.content.SharedPreferences;
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
    Item item;
    boolean checked = false;
    private Context context;
    
    PageViewAdapter(Context context) {
        super(Item.DIFF_CALLBACK);
        this.context = context;
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
        // holder.bind(holder, position);
        item = getItem(position);
        PageViewHolder pageViewHolder = (PageViewHolder) holder;
        if (item != null) {
            pageViewHolder.lineTextView.setText(String.valueOf(item));
        }
        SharedPreferences preferences = context.getSharedPreferences(
                "MyPrefs", context.MODE_PRIVATE);

        // We need an editor object to make changes
        final SharedPreferences.Editor editor = preferences.edit();
        // pageViewHolder.itemView.setTag(position);
        pageViewHolder.checkboxId.setTag(position);
        pageViewHolder.checkboxId.setChecked(preferences.getBoolean("checked", checked));


        //getting the checkbox value whenever clicked
        //CompoundButton: A button with two states, checked and unchecked. When the button is pressed or clicked,the state changes automatically.
        pageViewHolder.checkboxId.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // save the state of  checkboxes (checked or unchecked) when user exits the application
                // so that it can reload the state when the application restarts
                if (isChecked) {
                    editor.putBoolean("checkBoxValue", isChecked);
                    editor.commit();
                    Log.e("checkboxvalue", String.valueOf(isChecked));
                }
            }
        });
    }

    public class PageViewHolder extends RecyclerView.ViewHolder {
        public TextView lineTextView;
        public LinearLayout container;
        public CheckBox checkboxId;


        public PageViewHolder(View layoutView) {
            super(layoutView);
            lineTextView = (TextView) layoutView.findViewById(R.id.line);
            container = (LinearLayout) layoutView.findViewById(R.id.container);
            checkboxId = (CheckBox) layoutView.findViewById(R.id.checkbox);
        }
    }
}

