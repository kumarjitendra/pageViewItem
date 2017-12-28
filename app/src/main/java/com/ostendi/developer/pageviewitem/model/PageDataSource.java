package com.ostendi.developer.pageviewitem.model;

import android.arch.paging.PositionalDataSource;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

public class PageDataSource extends PositionalDataSource<Item> {
    List<Item> listitem = new ArrayList<>();

    private int computeCount() {
        return listitem.size();
    }

    private List<Item> loadRangeInternal(int startPosition, int pagecount) {
        List<Item> newItems = new ArrayList<>();
        // actual load code here
        for (int i = 0; i < pagecount; i++) {
            newItems.add(new Item("Item " + i));
        }
        listitem.addAll(newItems);
        return newItems;
    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams params, @NonNull LoadInitialCallback<Item> callback) {
      //  int totalCount = computeCount();
      //  int position = computeInitialLoadPosition(params, totalCount);
       // int loadSize = computeInitialLoadSize(params, position, totalCount);
        callback.onResult(loadRangeInternal(params.requestedStartPosition, params.pageSize), params.requestedStartPosition);
    }

    @Override
    public void loadRange(@NonNull LoadRangeParams params, @NonNull LoadRangeCallback<Item> callback) {
        callback.onResult(loadRangeInternal(params.startPosition, params.loadSize));
    }
}
