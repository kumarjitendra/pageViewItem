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
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ostendi.developer.pageviewitem.R;
import com.ostendi.developer.pageviewitem.model.Item;

public class PageViewAdapter extends PagedListAdapter<Item, PageViewAdapter.PageViewHolder> {
    private final Context context;
    SharedPreferences preferences;

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

    //data is bound to views
    @Override
    public void onBindViewHolder(PageViewAdapter.PageViewHolder holder, int position) {
        preferences = PreferenceManager.getDefaultSharedPreferences(context);
        final Item item = getItem(position);
        PageViewHolder pageViewHolder = holder;
        if (item != null) {
            pageViewHolder.lineTextView.setText(String.valueOf(item));
           // Boolean value = preferences.getBoolean("checkedState", false);
           // pageViewHolder.checkBox.setChecked(preferences.contains("checkedState"));
        }

        //Remove a previously setOnCheckedChangeListener.in some cases, it will prevent unwanted situations
        pageViewHolder.checkBox.setOnCheckedChangeListener(null);

        // setTag:Sets the tag associated with this view. which is used to store data within a view without resorting to another data structure.
        pageViewHolder.checkBox.setTag(item);
        pageViewHolder.checkBox.setSelected(item.isSelected());
        pageViewHolder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                  //  preferences.edit().putBoolean("checkedState", isChecked).commit();
                  //  Log.e("isChecked", String.valueOf(isChecked));

                    item.setSelected(true);
                }
                else {item.setSelected(false);}
            }
        });

        pageViewHolder.checkBox.setChecked(item.isSelected());

    }


        public class PageViewHolder extends RecyclerView.ViewHolder  {
        public TextView lineTextView;
        public LinearLayout container;
        public CheckBox checkBox;


        public PageViewHolder(View layoutView) {
            super(layoutView);
            lineTextView = layoutView.findViewById(R.id.line);
           // container = layoutView.findViewById(R.id.container);
            checkBox = layoutView.findViewById(R.id.checkbox);
        }
    }
}

