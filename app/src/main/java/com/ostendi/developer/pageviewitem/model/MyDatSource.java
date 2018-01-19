package com.ostendi.developer.pageviewitem.model;

import android.arch.paging.PositionalDataSource;
import android.support.annotation.NonNull;
import android.util.Log;

import com.ostendi.developer.pageviewitem.view.MyAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class MyDatSource extends PositionalDataSource<Item> {

    private static String TAG = "MyDatSource";
    int COUNT = 265656565;
    private List<Integer> listOfPosition = new ArrayList<>((Arrays.asList(4,7,10,90)));

    private List<Item> createNewItemsMatchingRequestedSize(int startPosition, int pagecount) {
        List<Item> newItems = new ArrayList<>();
        // actual load code here
        for (int i = 0; i < pagecount; i++) {
            Item item = new Item("Item " + i + " " + "startPosition(" + startPosition + ")");
            //listOfPosition.contains(startPosition + i) is boolean to check wheck it's true or not
            if (listOfPosition.contains(startPosition + i)) {
                Log.e(TAG, "listOfPosition to be preSelected  " + listOfPosition);
                item.setSelected(true);
                Log.e(TAG, "preSelected item is : " + item  );
            }
            newItems.add(item);
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

    public void saveSelectedItemInList(int position) {
        listOfPosition.add(position);
        Log.e(TAG, "checkbox state(true/false) at position " + position + " has been updated in the list of MyDatSource");
        Log.e(TAG, "Updated listOfPosition in MyDatSource " + listOfPosition);
    }
}
