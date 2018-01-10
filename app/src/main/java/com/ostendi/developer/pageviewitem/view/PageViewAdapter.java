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
    private final MainActivity context;
    Item item;
    SharedPreferences preferences;

    PageViewAdapter(MainActivity mainActivity) {
        super(Item.DIFF_CALLBACK);
        this.context = mainActivity;
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
        preferences = PreferenceManager.getDefaultSharedPreferences(context);
        item = getItem(position);
        PageViewHolder pageViewHolder = holder;
        if (item != null) {
            pageViewHolder.lineTextView.setText(String.valueOf(item));
        }

        //  pageViewHolder.checkboxId.setOnCheckedChangeListener(null);

        // setTag:Sets the tag associated with this view. which is used to store data within a view without resorting to another data structure.
        pageViewHolder.checkboxId.setTag(position);
        if (preferences != null) {
            Log.e("isPrefNull", "preferences is not null");
            //getBoolean:Retrieve a boolean value from the preferences.
            pageViewHolder.checkboxId.setChecked(preferences.getBoolean("checkedStateValue", false));

            Log.e("stateAftergetFromPref", String.valueOf(preferences.getBoolean("checkedStateValue", false)));
            Log.e("check2", String.valueOf(preferences.contains("checkedState")));
        }

        //Getting the checkbox value whenever clicked
        //CompoundButton: A button with two states, checked and unchecked. When the button is pressed or clicked,the state changes automatically.
        pageViewHolder.checkboxId.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    /**edit():
                     * Create a new Editor for these preferences, through which you can make
                     * modifications to the data in the preferences and atomically commit those
                     * changes back to the SharedPreferences object.
                     *
                     * <p>Note that you <em>must</em> call {@link SharedPreferences.Editor#commit} to have any
                     * changes you perform in the Editor actually show up in the
                     * SharedPreferences.
                     *
                     * @return Returns a new instance of the {@link SharedPreferences.Editor} interface, allowing
                     * you to modify the values in this SharedPreferences object.
                     */

                    /**putBoolean:
                     * Set a boolean value in the preferences editor, to be written back
                     * once {@link #commit} or {@link #apply} are called.
                     *
                     * @param key(checkedState) The name of the preference to modify.
                     * @param value(isChecked) The new value for the preference.
                     *
                     * @return Returns a reference to the same Editor object, so you can
                     * chain put calls together.
                     */
                    preferences.edit().putBoolean("checkedState", isChecked).commit();

                    /**preferences.contains :
                     * Checks whether the preferences contains a preference.
                     * @param key(checkedState) The name of the preference to check.
                     * @return Returns true if the preference exists in the preferences,
                     *         otherwise false.
                     */
                    Log.e("check1", String.valueOf(preferences.contains("checkedState")));
                    Log.e("isChecked", String.valueOf(isChecked));
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
            lineTextView = layoutView.findViewById(R.id.line);
            container = layoutView.findViewById(R.id.container);
            checkboxId = layoutView.findViewById(R.id.checkbox);
        }
    }
}

