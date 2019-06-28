package com.example.liuj.sdk.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import android.widget.FrameLayout;


import java.util.List;

/**
 * Created by liuj on 2018/1/15.
 */
public abstract class BaseMainAct extends BaseActivity {

    protected abstract List<BaseModel> initCell();

    protected MutiTypeAdapter mAdapter;

    protected RecyclerView mRecyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAdapter = new MutiTypeAdapter(this);
        List<BaseModel> modelMapAatModels = initCell();
        mAdapter.reset(modelMapAatModels);

        mRecyclerView = new RecyclerView(this);
        mRecyclerView.setLayoutParams(
                new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mAdapter);
        setContentView(mRecyclerView);
    }
}
