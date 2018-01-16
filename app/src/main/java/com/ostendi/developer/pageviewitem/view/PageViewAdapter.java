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
    PagedList<Item> itemPagedList;

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
        /**
         *  itemPagedList.get(position) method has null pointer exception so itemPagedList need to set  in constructor
         *  for that find a way to pass the "liveObservedItem" from MainActivity class as parameter of pageViewAdapter
         */
        //item = itemPagedList.get(position);


        /**
         * call the method getItem(position) in PagedListAdapter to get item on position based but
         * it select strange item position when i use checkbox so need to use itemPagedList.get(position)
         */
        item = getItem(position);
        if (item != null) {
            holder.lineTextView.setText(String.valueOf(item));
        }

        //Remove a previously setOnCheckedChangeListener. In some cases, it will prevent unwanted situations
        holder.checkBox.setOnCheckedChangeListener(null);

        holder.checkBox.setOnCheckedChangeListener((compoundButton, isChecked) -> {
            if (compoundButton.isChecked()) {
                item.setSelected(isChecked);
            } else {
                item.setSelected(false);
            }
            if (item.getSelected()) {
                holder.checkBox.setChecked(true);
                /**
                 * Here checkbox select strange item position. Example: when i click on item 1 , it select item 5.
                 */
                Log.e(TAG, "item at correponding checked value :" + String.valueOf(item));
                PageDataSource.saveSelectedItemInList(item);
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

