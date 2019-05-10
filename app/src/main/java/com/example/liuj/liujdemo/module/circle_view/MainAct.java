package com.example.liuj.liujdemo.module.circle_view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;

import com.example.liuj.R;
import com.example.liuj.liujdemo.base.BaseActivity;
import com.example.liuj.liujdemo.base.BaseNativePageAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by jliu on 2018/4/11.
 */
public class MainAct extends BaseActivity {

    @BindView(R.id.circle_vp1)
    ViewPager mViewPager;

    private BaseNativePageAdapter mAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.circle_main_act);
        ButterKnife.bind(this);

        init();
    }

    private void init() {
        mAdapter = new BaseNativePageAdapter(this);
        List<Integer> list = new ArrayList<>();
        list.add(R.drawable.img1);
        list.add(R.drawable.img2);
        list.add(R.drawable.img1);
        list.add(R.drawable.img2);
        list.add(R.drawable.img1);
        list.add(R.drawable.img2);
        list.add(R.drawable.img1);
        list.add(R.drawable.img2);
        list.add(R.drawable.img1);
        list.add(R.drawable.img2);
        list.add(R.drawable.img1);
        list.add(R.drawable.img2);
        mAdapter.reset(list);

        mViewPager.setAdapter(mAdapter);
    }

}