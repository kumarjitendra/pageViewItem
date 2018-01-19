package com.ostendi.developer.pageviewitem.viewmodel;


import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.paging.LivePagedListBuilder;
import android.arch.paging.PagedList;

import com.ostendi.developer.pageviewitem.model.Item;
import com.ostendi.developer.pageviewitem.model.MyDatSource;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class MyViewModel extends ViewModel {

    private static final int INITIAL_Load_Size = 50;
    private static final int PAGE_SIZE = 25;
    private static final Boolean Enable_Place_holders = true;
    private static final int PREFETCH_DISTANCE = 20;//the paged list will attempt to load 10 items in advance of data that's already been accessed.
    private static final int INITIAL_LOAD_KEY = 0;
    Executor backgroundThreadexecuter;
    PagedList.BoundaryCallback<Item> boundaryCallback;
    MyDatSource dataSource;

    //LiveData:Data holder class that keeps a value(here Item) and allows this value to be observed
    public LiveData<PagedList<Item>> livePagedListData;

    public MyViewModel() {

        //newFixedThreadPool:Creates a thread pool that reuses a fixed number of threads operating off a shared unbounded queue
        // If additional tasks are submitted when all threads are active, they will wait in the queue until a thread is available.
        backgroundThreadexecuter = Executors.newFixedThreadPool(5);
        getpagedListLiveData();
    }

    private final android.arch.paging.DataSource.Factory<Integer, Item> dataSourceFactory =
            new android.arch.paging.DataSource.Factory<Integer, Item>() {
                @Override
                public android.arch.paging.DataSource create() {
                    dataSource = new MyDatSource();
                    return dataSource;
                }
            };
    private final PagedList.Config pagedListConfig =
            new PagedList.Config.Builder()
                    .setPrefetchDistance(PREFETCH_DISTANCE)//Distance the PagedList should prefetch.If not set, defaults to page size.
                    .setInitialLoadSizeHint(INITIAL_Load_Size)//Defines how many items to load when first load occurs.
                    .setPageSize(PAGE_SIZE)//Defines the number of items loaded at once from the MyDatSource.
                    .setEnablePlaceholders(Enable_Place_holders)
                    .build();


    private void getpagedListLiveData() {
        //LivePagedListBuilder:Builder for LiveData<PagedList>
        livePagedListData = new LivePagedListBuilder<>(dataSourceFactory, pagedListConfig)
                //setBoundaryCallback:Sets a PagedList.BoundaryCallback on each PagedList created, typically used to load
                // additional data from network when paging from local storage.
                //Pass a BoundaryCallback to listen to when the PagedList runs out of data to load
                .setBoundaryCallback(boundaryCallback)
                .setBackgroundThreadExecutor(backgroundThreadexecuter) //Sets backgroundThreadexecuter which will be used for background loading of pages.
                .setInitialLoadKey(INITIAL_LOAD_KEY)//When a new PagedList/MyDatSource pair is created after the first, it acquires a load key from the previous generation so that data is loaded around the position already being observed.
                .build();
    }

    //Expose data to observe by Activity and display on UI
    public LiveData<PagedList<Item>> getLivePagedListData() {
        return livePagedListData;
    }

}
