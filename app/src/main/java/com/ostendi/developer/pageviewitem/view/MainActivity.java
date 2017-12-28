package com.ostendi.developer.pageviewitem.view;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.RelativeLayout;

import com.ostendi.developer.pageviewitem.R;
import com.ostendi.developer.pageviewitem.viewmodel.MyViewModel;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.activity_main);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        relativeLayout.removeView(recyclerView);
        relativeLayout.addView(recyclerView);

        final PageViewAdapter adapter = new PageViewAdapter();
        MyViewModel viewModel = ViewModelProviders.of(this).get(MyViewModel.class);
        viewModel.pagedListLiveData.observe(this, pagedList -> adapter.setList(pagedList));
        //Attach the adapter to recyclerview
        recyclerView.setAdapter(adapter);
        int numberOfColumns = 5;
        GridLayoutManager glm = new GridLayoutManager(this.getApplicationContext(),numberOfColumns);
         glm.setOrientation(GridLayoutManager.VERTICAL);
         //glm.setOrientation(GridLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(glm);
    }
}
