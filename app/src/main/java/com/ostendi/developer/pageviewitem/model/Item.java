package com.ostendi.developer.pageviewitem.model;

import android.support.annotation.NonNull;
import android.support.v7.recyclerview.extensions.DiffCallback;
import android.util.Log;
import android.widget.Checkable;

public class Item {

    private String value;
    boolean selected;

    public Item(String value) {
        this.value = value;
    }

    public boolean getSelected() {
        //Log.e("Item", "SelectedValueReturned " + String.valueOf(selected));
        return this.selected;
    }

    public void setSelected(boolean selected) {
        //Log.e("Item", "SelectedValueIsSet " + String.valueOf(selected));
        this.selected = selected;
    }


    public final static DiffCallback<Item> DIFF_CALLBACK = new DiffCallback<Item>() {
        @Override
        public boolean areItemsTheSame(@NonNull Item oldItem, @NonNull Item newItem) {
            return oldItem.value.equals(newItem.value);
        }

        @Override
        public boolean areContentsTheSame(@NonNull Item oldItem, @NonNull Item newItem) {
            return oldItem.equals(newItem);
        }
    };

    @Override
    public String toString() {
        return value.toString();
    }

}


