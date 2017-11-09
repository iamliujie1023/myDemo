package com.example.liuj.liujdemo.module.view.position;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.liuj.liujdemo.R;
import com.example.liuj.liujdemo.base.BaseAct;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by liuj on 2017/11/9.
 */

public class ViewPositionAct extends BaseAct {

    @BindView(R.id.viewpos_tv)
    TextView mTvDisplay;

    @BindView(R.id.viewpos_btn1)
    Button mBtnUp;

    @BindView(R.id.viewpos_btn2)
    Button mBtnDown;

    @BindView(R.id.viewpos_btn3)
    Button mBtnLeft;

    @BindView(R.id.viewpos_btn4)
    Button mBtnRight;

    @BindView(R.id.viewpos_btn5)
    Button mBtnReset;

    @BindView(R.id.viewpos_iv)
    ImageView mImgTarget;

    private float mRowTransX, mRowTransY;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewposition_demo_act);
        ButterKnife.bind(this);

        mBtnUp.setOnClickListener(mOnClickListener);
        mBtnDown.setOnClickListener(mOnClickListener);
        mBtnLeft.setOnClickListener(mOnClickListener);
        mBtnRight.setOnClickListener(mOnClickListener);
        mBtnReset.setOnClickListener(mOnClickListener);

        mRowTransX = mImgTarget.getTranslationX();
        mRowTransY = mImgTarget.getTranslationY();


        mImgTarget.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener(){

            @Override
            public void onGlobalLayout() {
                showTargetViewInfo(mImgTarget);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    mImgTarget.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                } else {
                    mImgTarget.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                }
            }
        });

    }

    private View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int id = v.getId();
            float curTransX = mImgTarget.getTranslationX();
            float curTransY = mImgTarget.getTranslationY();
            switch (id) {
                case R.id.viewpos_btn1:
                    mImgTarget.setTranslationY(curTransY - 10);
                    break;
                case R.id.viewpos_btn2:
                    mImgTarget.setTranslationY(curTransY + 10);
                    break;
                case R.id.viewpos_btn3:
                    mImgTarget.setTranslationX(curTransX - 10);
                    break;
                case R.id.viewpos_btn4:
                    mImgTarget.setTranslationX(curTransX + 10);
                    break;
                case R.id.viewpos_btn5:
                    mImgTarget.setTranslationX(mRowTransX);
                    mImgTarget.setTranslationY(mRowTransY);
                    break;
            }

            showTargetViewInfo(mImgTarget);
        }
    };

    private void showTargetViewInfo(ImageView imageView) {
        String str = String.format("left:%s top:%s right:%s botton:%s \ntransX:%s transY:%s X:%s Y:%s",
                imageView.getLeft(), imageView.getTop(), imageView.getRight(), imageView.getBottom(),
                imageView.getTranslationX(), imageView.getTranslationY(),
                imageView.getX(), imageView.getY());

        mTvDisplay.setText(str);
    }

}
