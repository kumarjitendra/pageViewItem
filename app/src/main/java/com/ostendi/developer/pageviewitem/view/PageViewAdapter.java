package com.ostendi.developer.pageviewitem.view;


import android.arch.paging.PagedListAdapter;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ostendi.developer.pageviewitem.R;
import com.ostendi.developer.pageviewitem.model.Item;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PageViewAdapter extends PagedListAdapter<Item, PageViewAdapter.PageViewHolder> {
    Item item;
    List<Boolean> checkedCases = new ArrayList<>(Arrays.asList(false, false, false));

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
        item = getItem(position);
        PageViewHolder pageViewHolder = (PageViewHolder) holder;
        if (item != null) {
            pageViewHolder.lineTextView.setText(String.valueOf(item));
        }

        pageViewHolder.checkBoxSelection.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (buttonView.isChecked())
                    checkedCases.set(0, true);
                else
                    checkedCases.set(0, false);
            }
        });
    }

    public static class PageViewHolder extends RecyclerView.ViewHolder implements CompoundButton.OnCheckedChangeListener {
        public TextView lineTextView;
        public LinearLayout container;
        public CheckBox checkBoxSelection;

        public PageViewHolder(View layoutView) {
            super(layoutView);
            lineTextView = (TextView) layoutView.findViewById(R.id.line);
            container = (LinearLayout) layoutView.findViewById(R.id.container);
            checkBoxSelection = (CheckBox) layoutView.findViewById(R.id.checkbox);
            //setOnCheckedChangeListener:Register a callback to be invoked when the checked state of this button changes.
            checkBoxSelection.setOnCheckedChangeListener(this);
        }

        //onCheckedChanged :Called when the checked state of a compound button has changed.
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

        }

    }
}

