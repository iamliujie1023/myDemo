package com.example.liuj.liujdemo.module.recyclerview;

import android.content.Context;
import android.graphics.Canvas;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.ViewGroup;

import com.example.liuj.R;
import com.example.liuj.liujdemo.base.BaseAct;
import com.example.liuj.liujdemo.holder.NormalHolder;
import com.example.liuj.liujdemo.model.NormalModel;
import com.example.liuj.liujdemo.tools.ListDataUtil;
import com.example.liuj.liujdemo.view.HorItemDecoration;
import com.example.liuj.sdk.LogUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by liuj on 2017/12/4.
 */
public class ItemTouchHelperDemoAct extends BaseAct {

    @BindView(R.id.rv)
    RecyclerView mRecyclerView;

    @BindView(R.id.rv2)
    RecyclerView mRvVer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rv_itemtouch_act);
        ButterKnife.bind(this);

        initView();

    }


    private class A extends LinearLayoutManager implements ItemTouchHelper.ViewDropHandler {
        public A(Context context) {
            super(context);
            setOrientation(HORIZONTAL);
        }
    }


    private void initView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);

        MyAdapter myAdapter = new MyAdapter(this);
        myAdapter.reset(ListDataUtil.getDemoColors());

        mRecyclerView.setAdapter(myAdapter);
        mRecyclerView.addItemDecoration(new HorItemDecoration(new HorItemDecoration.ItemBound(0, 0, 2, 2, 0, 2, 0, 2)));
        mRecyclerView.setLayoutManager(linearLayoutManager);

        ItemTouchHelper.Callback callback = new MyCallback();
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(callback);
        itemTouchHelper.attachToRecyclerView(mRecyclerView);

    }

    private class MyCallback extends ItemTouchHelper.Callback {

        @Override
        public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
            super.onSelectedChanged(viewHolder, actionState);
            LogUtils.i("actionState = " + actionState);
        }

        @Override
        public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
            LogUtils.i(String.format("%s getMovementFlags", PAGE_TAG));
            return 0;
        }

        @Override
        public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
            LogUtils.i(String.format("%s onMove", PAGE_TAG));
            return false;
        }

        @Override
        public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
            LogUtils.i(String.format("%s onSwiped", PAGE_TAG));
        }

        /**
         * 是否可滑动
         * @return
         */
        @Override
        public boolean isItemViewSwipeEnabled() {
            return false;
//            return super.isItemViewSwipeEnabled();
        }

    };

    ItemTouchHelper.SimpleCallback mSimpleCallback = new ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP | ItemTouchHelper.DOWN, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
        @Override
        public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
            LogUtils.i("move");
            return false;
        }

        @Override
        public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
            LogUtils.i("onSwiped");
        }

    };



    private static class MyAdapter extends RecyclerView.Adapter<NormalHolder>  {

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
        public NormalHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return NormalHolder.newInstance(mContext, parent);
        }

        @Override
        public void onBindViewHolder(NormalHolder holder, int position) {
            NormalModel model = mData.get(position);
            holder.bindData(model);
        }

        @Override
        public int getItemCount() {
            return mData.size();
        }
    }

}
