package com.example.liuj.liujdemo.module.diyview.timeslot;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.liuj.R;
import com.example.liuj.liujdemo.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by jliu on 2018/4/3.
 */
public class TimeSlotAct extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.rssv)
    TSScrollView mView;

    @BindView(R.id.move)
    TextView mTvMove;

    @BindView(R.id.add)
    TextView mTvAdd;

    @BindView(R.id.edit_target_pos)
    EditText mEditText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.diyview_ts_act);
        ButterKnife.bind(this);

        mTvMove.setOnClickListener(this);
        mTvAdd.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == mTvMove) {
            move();
        } else if (v == mTvAdd) {
            add();
        }
    }

    private void add() {
        List<TLModel> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(new TLModel("title" + (i+1), "desc" + (i+1)));
        }
        mView.resetData(list);
    }

    private void move() {
        int pos = Integer.valueOf(mEditText.getText().toString());
        mView.scrollToPos(pos);
    }

}