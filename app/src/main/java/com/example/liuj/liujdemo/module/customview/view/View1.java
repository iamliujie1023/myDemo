package com.example.liuj.liujdemo.module.customview.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.example.liuj.liujdemo.view.CommonUtil;
import com.example.liuj.sdk.LogUtils;

/**
 * Created by liuj on 2018/1/4.
 */
public class View1 extends View implements Runnable {

    private Paint mPaint;
    private int mRaduis;

    public View1(Context context) {
        this(context, null);
    }

    public View1(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        // 实例化画笔并打开抗锯齿
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

        /*
         * 设置画笔样式为描边，圆环嘛……当然不能填充不然就么意思了
         *
         * 画笔样式分三种：
         * 1.Paint.Style.STROKE：描边
         * 2.Paint.Style.FILL_AND_STROKE：描边并填充
         * 3.Paint.Style.FILL：填充
         */
        mPaint.setStyle(Paint.Style.STROKE);

        // 设置画笔颜色为浅灰色
        mPaint.setColor(Color.LTGRAY);

            /*
            * 设置描边的粗细，单位：像素px
            * 注意：当setStrokeWidth(0)的时候描边宽度并不为0而是只占一个像素
            */
        mPaint.setStrokeWidth(10);

        mRaduis = 100;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // 绘制圆环
        canvas.drawCircle(CommonUtil.getWidth(getContext()) / 2, CommonUtil.getHeight(getContext()) / 2, mRaduis, mPaint);
    }

    @Override
    public void run() {

        while (true) {
            LogUtils.i(" run mRaduis= " + mRaduis);
            if (mRaduis >= 0) {
                mRaduis -= 5;
            } else {
                mRaduis = 100;
            }

            postInvalidate();

            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

    }

}