package com.example.liuj.liujdemo.module.view.scroller;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.liuj.liujdemo.R;
import com.example.liuj.liujdemo.base.BaseAct;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by liuj on 2017/11/9.
 */
public class ViewScrollScrollerAct extends BaseAct implements View.OnClickListener {

    @BindView(R.id.mview_scroller_text_view)
    MyScrollerTestView mMyScrollerTestView;

    @BindView(R.id.viewscroll_btn1)
    Button mBtnMove;

    @BindView(R.id.viewscroll_btn2)
    Button mBtnReset;

    @BindView(R.id.viewscroll_etx)
    EditText mEdX;

    @BindView(R.id.viewscroll_ety)
    EditText mEdY;

    @BindView(R.id.viewscroll_iv_target)
    ImageView mIvTarget;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewscroll_scrollxy_act);
        ButterKnife.bind(this);

        mBtnMove.setOnClickListener(this);
        mBtnReset.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == mBtnMove) {
            int targetX = Integer.valueOf(mEdX.getText().toString()) == null ? 0 : Integer.valueOf(mEdX.getText().toString());
            mMyScrollerTestView.smoothScrollTo(targetX, 0);
        } else if (v == mBtnReset) {

        }
    }


}
