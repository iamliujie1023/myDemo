package com.example.liuj.liujdemo.module.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.liuj.R;
import com.example.liuj.liujdemo.home.IItemClickCallback;
import com.example.liuj.liujdemo.module.view.position.ViewPositionAct;
import com.example.liuj.liujdemo.module.view.scroller.ViewScrollScrollerAct;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ViewMain extends AppCompatActivity implements IItemClickCallback {

    @BindView(R.id.main_rl)
    RecyclerView mRecyclerView;

    private MyAdapter mAdapter;

    private String[] strs = new String[]{
            ViewPositionAct.class.getSimpleName(),
            ViewScrollScrollerAct.class.getSimpleName()
    };
    private Class<?>[] clzs = new Class[]{
            ViewPositionAct.class,
            ViewScrollScrollerAct.class
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_act);
        ButterKnife.bind(this);

        initView();
    }

    private void initView() {
        mAdapter = new MyAdapter(this, this);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.reset(strs);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onItemClick(int pos) {
        Intent intent = new Intent();
        intent.setClass(ViewMain.this, clzs[pos]);
        startActivity(intent);
    }

    static class MyAdapter extends RecyclerView.Adapter {

        private String[] mDatas;

        private Context mContext;

        private IItemClickCallback mCallback;

        public MyAdapter(Context context, IItemClickCallback callback) {
            mContext = context;
            mCallback = callback;
        }

        public void reset(String[] strings) {
            mDatas = strings.clone();
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return MyHolder.obtain(mContext, parent);
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            ((MyHolder) holder).bindData(position, mDatas[position], mCallback);
        }

        @Override
        public int getItemCount() {
            return mDatas.length;
        }
    }

    static class MyHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.main_holder_tv)
        TextView mTv;

        public MyHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        static MyHolder obtain(Context context, ViewGroup viewGroup) {
            return new MyHolder(LayoutInflater.from(context).inflate(R.layout.home_holder_layout, viewGroup, false));
        }

        public void bindData(final int pos, String s, final IItemClickCallback callback) {
            mTv.setText(s);
            mTv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    callback.onItemClick(pos);
                }
            });
        }

    }
}
