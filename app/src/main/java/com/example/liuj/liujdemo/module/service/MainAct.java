package com.example.liuj.liujdemo.module.service;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.liuj.R;
import com.example.liuj.liujdemo.adapter.MutiTypeAdapter;
import com.example.liuj.liujdemo.base.BaseActivity;
import com.example.liuj.liujdemo.model.BaseModel;
import com.example.liuj.liujdemo.model.ModelMapAatModel;
import com.example.liuj.liujdemo.module.service.process_binderpool.BinderPoolAct;
import com.example.liuj.liujdemo.module.service.process.MainProcessActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by liuj on 2018/1/15.
 */
public class MainAct extends BaseActivity {

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
        initCell();

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mAdapter);
    }

    private void initCell() {
        ModelMapAatModel model1 = new ModelMapAatModel(DemoAct.class, "单进程通讯");
        ModelMapAatModel model2 = new ModelMapAatModel(MainProcessActivity.class, "跨进程 通讯");
        ModelMapAatModel model3 = new ModelMapAatModel(BinderPoolAct.class, "binder 连接池");

        list.add(model1);
        list.add(model2);
        list.add(model3);

        mAdapter.reset(list);
    }

}