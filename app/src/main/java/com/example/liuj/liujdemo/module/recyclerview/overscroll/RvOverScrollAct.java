package com.example.liuj.liujdemo.module.recyclerview.overscroll;

import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;

import com.example.liuj.R;
import com.example.liuj.liujdemo.base.BaseAct;
import com.example.liuj.liujdemo.holder.NormalHolder;
import com.example.liuj.liujdemo.model.NormalModel;
import com.example.liuj.liujdemo.tools.ListDataUtil;
import com.example.liuj.sdk.LogUtils;

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

        mTransX = (int) mRecyclerView.getTranslationX();
        mTouchSlop = ViewConfiguration.get(this).getScaledTouchSlop();
        mRecyclerView.setOnTouchListener(mOnTouchListener);

        initView();

        LogUtils.i("mTouchSlop=" + mTouchSlop);
        LogUtils.i("adapter Count =" + mRecyclerView.getAdapter().getItemCount());
    }

    private void initView() {
        linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);

        MyAdapter myAdapter = new MyAdapter(this);
        myAdapter.reset(ListDataUtil.getDemoColors());

        mRecyclerView.setAdapter(myAdapter);
        mRecyclerView.setLayoutManager(linearLayoutManager);


        MyAdapter myAdapter2 = new MyAdapter(this);
        myAdapter2.reset(ListDataUtil.getDemoColors());
        mMyView.setAdapter(myAdapter);


        MyAdapter myAdapter3 = new MyAdapter(this);
        myAdapter3.reset(ListDataUtil.getDemoColors());
        mMyView2.setAdapter(myAdapter3);
    }

    int mTouchSlop;
    int mTransX;
    float unCanScrollX = 0;
//    float mDire;// >0 forward <0 backwards

    float mLastX;
    private View.OnTouchListener mOnTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                mLastX = event.getX();
            } else if (event.getAction() == MotionEvent.ACTION_UP) {
                unCanScrollX = 0;
                mLastX = 0;

                float curTranX = v.getTranslationX();

                ObjectAnimator animatorTranslateX = ObjectAnimator.ofFloat(v, "translationX", curTranX, mTransX);
                animatorTranslateX.start();
                return false;
            } else if (event.getAction() == MotionEvent.ACTION_MOVE) {

                if (mLastX == 0) {
                    mLastX = event.getX();
                    return false;
                }

                float dire = event.getX() - mLastX; // >0 forward <0 backwards
                int diff = (int) Math.abs(dire);
//                Log.i("liujie", "diff= " + diff + ", lastX = " + (int) mLastX + ", curX=" + (int) event.getX());

                boolean isFirstPosVisible = linearLayoutManager.findFirstCompletelyVisibleItemPosition() == 0;
                boolean isLastPosVisible = linearLayoutManager.findLastCompletelyVisibleItemPosition() == ((RecyclerView) v).getAdapter().getItemCount() - 1;

                LogUtils.i("!(-1) = " + !v.canScrollHorizontally(-1));
                LogUtils.i("!(1) = " + !v.canScrollHorizontally(1));


                if (dire > 0 && !v.canScrollHorizontally(-1)
                        && isFirstPosVisible) {

                    v.setTranslationX(v.getTranslationX() + diff / 2);
                    mLastX = event.getX();
                    return true;
                } else if (dire < 0 && !v.canScrollHorizontally(1)
                        && isLastPosVisible) {

                    v.setTranslationX(v.getTranslationX() - diff / 2);
                    mLastX = event.getX();
                    return true;
                } else if (dire < 0 && isFirstPosVisible && (int) v.getTranslationX() != mTransX) {

                    v.setTranslationX(v.getTranslationX() - diff / 2);
                    mLastX = event.getX();
                    return true;
                }

                mLastX = event.getX();
                return false;
            }

            return false;
        }
    };

    public boolean inteceptMove(MotionEvent event) {

        return true;
    }


    private static class MyAdapter extends RecyclerView.Adapter<NormalHolder> {

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
