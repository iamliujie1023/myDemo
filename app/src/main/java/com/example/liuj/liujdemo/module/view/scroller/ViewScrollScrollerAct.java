package com.example.liuj.liujdemo.module.view.scroller;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.liuj.R;
import com.example.liuj.liujdemo.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by liuj on 2017/11/9.
 */
public class ViewScrollScrollerAct extends BaseActivity implements View.OnClickListener, MyScrollerTestView.ICallback{

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

    @BindView(R.id.viewscroll_tv_info)
    TextView mTvInfo;

    private int mRawX, mRawY;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewscroll_scrollxy_act);
        ButterKnife.bind(this);

        mBtnMove.setOnClickListener(this);
        mBtnReset.setOnClickListener(this);

        mMyScrollerTestView.setCallback(this);

        mRawX = (int) mMyScrollerTestView.getX();
        mRawY = (int) mMyScrollerTestView.getY();

    }

    @Override
    public void onClick(View v) {
        if (v == mBtnMove) {
            onMoveClick();
        } else if (v == mBtnReset) {
            mMyScrollerTestView.smoothScrollTo(mRawX, mRawY);
        }
    }

    private void onMoveClick() {
        String strX = mEdX.getText().toString();
        String strY = mEdY.getText().toString();
        int targetX = TextUtils.isEmpty(strX) ? 0 : Integer.valueOf(strX);
        int targetY = TextUtils.isEmpty(strY) ? 0 : Integer.valueOf(strY);
        mMyScrollerTestView.smoothScrollTo(targetX, targetY);
    }

    private void setTvScrollInfo() {
        String str = String.format("mScroll_X:%s, mScroll_Y:%s", mMyScrollerTestView.getScrollX(), mMyScrollerTestView.getScrollY());
        mTvInfo.setText(str);
    }

    @Override
    public void onSmoothFinish() {
        setTvScrollInfo();
    }
}
