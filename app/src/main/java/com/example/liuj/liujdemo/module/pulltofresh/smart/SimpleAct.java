package com.example.liuj.liujdemo.module.pulltofresh.smart;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.liuj.R;
import com.example.liuj.liujdemo.adapter.MutiTypeAdapter;
import com.example.liuj.liujdemo.base.BaseActivity;
import com.example.liuj.liujdemo.model.BannerModel;
import com.example.liuj.liujdemo.tools.ListDataUtil;
import com.example.liuj.sdk.LogUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SimpleAct extends BaseActivity {

    @BindView(R.id.rv_list)
    RecyclerView mRv;
    MutiTypeAdapter myAdapter;
    @BindView(R.id.refreshlayout)
    SmartRefreshLayout mRefreshLayout;
    private ClassicsHeader mClassicsHeader;

    int mCount = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pulltorefresh_smart_simple_act);
        ButterKnife.bind(this);

        initView();
    }

    private void initView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        mRv.setLayoutManager(linearLayoutManager);

        myAdapter = new MutiTypeAdapter(this);
        mRv.setAdapter(myAdapter);
        mClassicsHeader = (ClassicsHeader) mRefreshLayout.getRefreshHeader();
        mClassicsHeader.setSpinnerStyle(SpinnerStyle.FixedBehind);

        mRefreshLayout.setEnableFooterFollowWhenLoadFinished(true);
        mRefreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshLayout) {
                myAdapter.addAll(ListDataUtil.getDemoColors());
                mCount = mCount + 1;

                if (mCount == 2) {
                    myAdapter.addItem(new BannerModel());
                }

                if (mCount >= 5) {
                    refreshLayout.finishLoadMoreWithNoMoreData();//设置之后，将不会再触发加载事件
                } else {
                    refreshLayout.finishLoadMore();
                }
            }

            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                LogUtils.i( "simpleAct onRefresh");
                mCount = 0;
                myAdapter.clear();

                myAdapter.addItem(new BannerModel());
                myAdapter.addAll(ListDataUtil.getDemoColors());

                refreshLayout.finishRefresh();
                refreshLayout.setNoMoreData(false);//恢复上拉状态
            }
        });

        mRefreshLayout.autoRefresh();
    }


}
