package com.example.liuj.liujdemo.module.view.event;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import com.example.liuj.R;
import com.example.liuj.liujdemo.base.BaseActivity;
import com.example.liuj.sdk.LogUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EventMainAct extends BaseActivity {

    @BindView(R.id.view_event_root)
    ViewGroup mViewGroup;

    @BindView(R.id.view_event_view_child)
    View mChlid;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_event_main_act);

        ButterKnife.bind(this);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        LogUtils.i("EventMainAct dispatchTouchEvent");
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        LogUtils.i("EventMainAct onTouchEvent");
        return super.onTouchEvent(event);
    }
}
