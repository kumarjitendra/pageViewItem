package com.ostendi.developer.pageviewitem.model;

import android.arch.paging.PositionalDataSource;
import android.support.annotation.NonNull;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;


public class PageDataSource extends PositionalDataSource<Item> {
    int COUNT = 265656565;

    private List<Item> createNewItemsMatchingRequestedSize(int startPosition, int pagecount) {
        List<Item> newItems = new ArrayList<>();
        // actual load code here
        for (int i = 0; i < pagecount; i++) {
            newItems.add(new Item("Item " + i + " " + "startPosition(" + startPosition + ")"));
        }
        return newItems;
    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams params, @NonNull LoadInitialCallback<Item> callback) {
        //  int totalCount = computeCount();
        //  int position = computeInitialLoadPosition(params, totalCount);
        // int loadSize = computeInitialLoadSize(params, position, totalCount);
        callback.onResult(createNewItemsMatchingRequestedSize(params.requestedStartPosition, params.pageSize), params.requestedStartPosition, COUNT);
    }

    @Override
    public void loadRange(@NonNull LoadRangeParams params, @NonNull LoadRangeCallback<Item> callback) {
        List<Item> newLoadedItems = createNewItemsMatchingRequestedSize(params.startPosition, params.loadSize);
        callback.onResult(newLoadedItems);
    }


    public static void saveSelectedItemInList( Item selected) {
        List<Item> selectedLineNumber = new ArrayList<Item>();
        selectedLineNumber.add(selected);
        Log.e("pageDataSource", selected + "saved in the list ");
    }
}
