package com.example.liuj.liujdemo.module.recyclerview.overscroll;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.example.liuj.R;
import com.example.liuj.liujdemo.base.BaseAct;
import com.example.liuj.liujdemo.holder.MyRvOverScrollHolder;
import com.example.liuj.liujdemo.model.NormalModel;
import com.example.liuj.liujdemo.tools.ListDataUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by liuj on 2017/12/14.
 */
public class RvOverScrollAct extends BaseAct {

    @BindView(R.id.rv)
    RecyclerView mRecyclerView;

    LinearLayoutManager linearLayoutManager;

    @BindView(R.id.rv2)
    MyView mMyView;

    @BindView(R.id.rv3)
    MyView2 mMyView2;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rv_overscroll_act);
        ButterKnife.bind(this);

        initView();
    }

    private void initView() {
        linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);

        MyAdapter myAdapter = new MyAdapter(this);
        myAdapter.reset(ListDataUtil.getDemoColors());

        mRecyclerView.setAdapter(myAdapter);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        RvHorOverScrollHelper.attach(mRecyclerView);


        MyAdapter myAdapter2 = new MyAdapter(this);
        myAdapter2.reset(ListDataUtil.getDemoColors());
        mMyView.setAdapter(myAdapter);


        MyAdapter myAdapter3 = new MyAdapter(this);
        myAdapter3.reset(ListDataUtil.getDemoColors());
        mMyView2.setAdapter(myAdapter3);
    }

    private static class MyAdapter extends RecyclerView.Adapter<MyRvOverScrollHolder> {

        private List<NormalModel> mData;
        private Context mContext;

        public MyAdapter(Context context) {
            mData = new ArrayList<>();
            mContext = context;
        }

        public void reset(List<NormalModel> data) {
            mData.clear();
            mData.addAll(data);
        }

        @Override
        public MyRvOverScrollHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return MyRvOverScrollHolder.newInstance(mContext, parent);
        }

        @Override
        public void onBindViewHolder(MyRvOverScrollHolder holder, int position) {
            NormalModel model = mData.get(position);
            holder.bindData(model);
        }

        @Override
        public int getItemCount() {
            return mData.size();
        }
    }
}
