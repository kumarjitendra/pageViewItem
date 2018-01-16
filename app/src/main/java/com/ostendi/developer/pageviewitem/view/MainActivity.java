package com.ostendi.developer.pageviewitem.view;

import android.app.Activity;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.arch.paging.PagedList;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.RelativeLayout;

import com.ostendi.developer.pageviewitem.R;
import com.ostendi.developer.pageviewitem.model.Item;
import com.ostendi.developer.pageviewitem.viewmodel.MyViewModel;

public class MainActivity extends AppCompatActivity {

    public RecyclerView recyclerView;
    private GridLayoutManager gridLayoutManager;
    private PageViewAdapter pageViewAdapter;
    int x =200;//x = Numbers of max rows which will be cached when scrolling.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pageViewAdapter = new PageViewAdapter(this);
        RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(false); //setHasFixedSize(true) means the RecyclerView has children (items) that has fixed width and height.
        recyclerView.setItemViewCacheSize(x);

        relativeLayout.removeView(recyclerView);
        relativeLayout.addView(recyclerView);
        MyViewModel viewModel = ViewModelProviders.of(this).get(MyViewModel.class);
        viewModel.getLivePagedListData().observe(this, new Observer<PagedList<Item>>() {
            @Override
            public void onChanged(@Nullable PagedList<Item> liveObservedItem) {
                pageViewAdapter.setList(liveObservedItem);
            }
        });

        //Attach the pageViewAdapter to recyclerview
        recyclerView.setAdapter(pageViewAdapter);
        int numberOfColumns = 1;
        gridLayoutManager = new GridLayoutManager(this.getApplicationContext(), numberOfColumns);
        gridLayoutManager.setOrientation(GridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(gridLayoutManager);
    }
}
