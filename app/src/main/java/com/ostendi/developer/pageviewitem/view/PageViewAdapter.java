package com.ostendi.developer.pageviewitem.view;


import android.arch.paging.PagedListAdapter;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ostendi.developer.pageviewitem.R;
import com.ostendi.developer.pageviewitem.model.Item;

public class PageViewAdapter extends PagedListAdapter<Item, PageViewAdapter.PageViewHolder> {

    PageViewAdapter() {
        super(Item.DIFF_CALLBACK);
    }

    public static class PageViewHolder extends RecyclerView.ViewHolder {
        public final TextView itemTextView;
        public final LinearLayout itemContainer;

        public PageViewHolder(View itemView) {
            super(itemView);
            itemTextView = (TextView) itemView.findViewById(R.id.item);
            itemContainer = (LinearLayout) itemView.findViewById(R.id.item_container);
        }
    }

    @Override
    public PageViewAdapter.PageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Create a new View
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.gird_view, parent, false);
        PageViewHolder pageViewHolder = new PageViewHolder(layoutView);
        return pageViewHolder;
    }

    @Override
    public void onBindViewHolder(PageViewAdapter.PageViewHolder holder, int position) {
        Item item = getItem(position);
        PageViewHolder pageViewHolder = (PageViewHolder)holder;
        if (item != null) {
            pageViewHolder.itemTextView.setText(String.valueOf(item));
        }
    }


}
