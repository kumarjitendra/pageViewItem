package com.ostendi.developer.pageviewitem.viewmodel;


import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.paging.DataSource;
import android.arch.paging.LivePagedListBuilder;
import android.arch.paging.PagedList;

import com.ostendi.developer.pageviewitem.model.Item;
import com.ostendi.developer.pageviewitem.model.PageDataSource;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

public class MyViewModel extends ViewModel {

    private static final int INITIAL_Load_Size = 100;
    private static final int PAGE_SIZE = 50;
    private static final Boolean Enable_Place_holders = false;
    private static final int PREFETCH_DISTANCE = 50;//the paged list will attempt to load 50 items in advance of data that's already been accessed.

    //LiveData:Data holder class that keeps a value(here Item) and allows this value to be observed
    public LiveData<PagedList<Item>> pagedListLiveData;

    public MyViewModel() {
        getpagedListLiveData();
    }

    private final DataSource.Factory<Integer, Item> dataSourceFactory =
            () -> {
                PageDataSource pageDataSource = new PageDataSource();
                return pageDataSource;
            };

    private void getpagedListLiveData() {
        pagedListLiveData = new LivePagedListBuilder<>(dataSourceFactory, new PagedList.Config.Builder()
                .setPrefetchDistance(PREFETCH_DISTANCE)//Distance the PagedList should prefetch.If not set, defaults to page size.
                .setInitialLoadSizeHint(INITIAL_Load_Size)//Defines how many items to load when first load occurs.
                .setPageSize(PAGE_SIZE)//Defines the number of items loaded at once from the DataSource.
                .setEnablePlaceholders(Enable_Place_holders)
                .build()).build();
    }
}
