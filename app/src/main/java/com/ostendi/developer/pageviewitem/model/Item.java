package com.ostendi.developer.pageviewitem.model;

import android.support.annotation.NonNull;
import android.support.v7.recyclerview.extensions.DiffCallback;
import android.widget.Checkable;

public class Item implements Checkable{

    private String value;
    public boolean checked;

    public Item(String value) {
        this.value = value;
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

    @Override
    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    @Override
    public boolean isChecked() {
        return checked;
    }

    @Override
    public void toggle() {
        setChecked(!checked);
    }
}


