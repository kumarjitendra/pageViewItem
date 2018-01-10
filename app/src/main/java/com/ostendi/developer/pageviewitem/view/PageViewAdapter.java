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
    private static Item line;

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
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        //  SharedPreferences preferences = context.getSharedPreferences(preferenceValues, 0);// 0 = Private Mode
        line = getItem(position);
        PageViewHolder pageViewHolder = holder;
        if (line != null) {
            pageViewHolder.lineTextView.setText(String.valueOf(line));
        }
        pageViewHolder.checkBox.setOnCheckedChangeListener(null);//Remove a previously setOnCheckedChangeListener

        // setTag:Sets the tag associated with this view. which is used to store data within a view without resorting to another data structure.
        pageViewHolder.checkBox.setTag(position);

        Boolean checkedvalue = preferences.getBoolean("checked", false);
        Log.e("checkedvalue", String.valueOf(checkedvalue));
        if (checkedvalue == true) {
            pageViewHolder.checkBox.setChecked(true);
        } else {
            pageViewHolder.checkBox.setChecked(false);
        }

        pageViewHolder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (pageViewHolder.checkBox.isChecked()) {
                    preferences.edit().putBoolean("checked", true).commit();
                } else {
                    preferences.edit().putBoolean("checked", false).commit();
                }

            }
        });


        /**
         holder.checkBox.setOnClickListener(new View.OnClickListener() {
        @Override public void onClick(View v) {
        if (holder.checkBox.isChecked()) {
        preferences.edit().putBoolean("checked", true).commit();
        // holder.checkBox.setChecked(true);
        }
        }
        });
         Boolean checkedvalue = preferences.getBoolean("checked", false);
         Log.e("checkedvalue", String.valueOf(checkedvalue));
         holder.checkBox.setChecked(checkedvalue);
         **/
    }

    public class PageViewHolder extends RecyclerView.ViewHolder {
        public TextView lineTextView;
        public LinearLayout container;
        public CheckBox checkBox;


        public PageViewHolder(View layoutView) {
            super(layoutView);
            lineTextView = layoutView.findViewById(R.id.line);
            container = layoutView.findViewById(R.id.container);
            checkBox = layoutView.findViewById(R.id.checkbox);
        }
    }
}

