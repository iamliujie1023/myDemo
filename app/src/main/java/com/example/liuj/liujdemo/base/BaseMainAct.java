package com.example.liuj.liujdemo.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.liuj.R;
import com.example.liuj.liujdemo.adapter.MutiTypeAdapter;
import com.example.liuj.liujdemo.model.BaseModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by liuj on 2018/1/15.
 */
public abstract class BaseMainAct extends BaseActivity {

    protected abstract List<BaseModel> initCell();

    MutiTypeAdapter mAdapter;

    @BindView(R.id.main_rl)
    RecyclerView mRecyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_act);
        ButterKnife.bind(this);

        initView();
    }

    private void initView() {
        mAdapter = new MutiTypeAdapter(this);
        List<BaseModel> modelMapAatModels = initCell();
        mAdapter.reset(modelMapAatModels);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mAdapter);
    }


}
