package com.example.liuj.liujdemo.module.customview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.liuj.R;
import com.example.liuj.liujdemo.adapter.MutiTypeAdapter;
import com.example.liuj.liujdemo.base.BaseAct;
import com.example.liuj.liujdemo.model.ModelMapAatModel;
import com.example.liuj.liujdemo.model.BaseModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by liuj on 2017/12/29.
 */
public class Main extends BaseAct {

    MutiTypeAdapter mAdapter;

    @BindView(R.id.main_rl)
    RecyclerView mRecyclerView;

    private List<BaseModel> list = new ArrayList<>();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_act);
        ButterKnife.bind(this);

        initView();
    }

    private void initView() {
        mAdapter = new MutiTypeAdapter(this);
        ModelMapAatModel model = new ModelMapAatModel(Demo1_12.class);
        list.add(model);
        mAdapter.reset(list);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mAdapter);
    }


}