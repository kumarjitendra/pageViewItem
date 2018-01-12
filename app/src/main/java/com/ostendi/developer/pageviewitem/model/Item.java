package com.ostendi.developer.pageviewitem.model;

import android.support.annotation.NonNull;
import android.support.v7.recyclerview.extensions.DiffCallback;
import android.util.Log;
import android.widget.Checkable;

public class Item {

    private String value;
    // private boolean selected =false;
    private boolean selected ;

    public Item(String value) {
        this.value = value;
    }

    public void setSelected(boolean selected) {
        Log.e("Item","setSelectedValue "+ String.valueOf(selected));
        this.selected = selected;
    }

    public boolean isSelected() {
        Log.e("Item", "ReturnSelectedValue "+ String.valueOf(selected));
        return selected;

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


