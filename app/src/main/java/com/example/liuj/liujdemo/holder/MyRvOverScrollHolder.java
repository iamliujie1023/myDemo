package com.example.liuj.liujdemo.holder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.liuj.R;
import com.example.liuj.liujdemo.model.NormalModel;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by liuj on 2017/12/13.
 */

public class MyRvOverScrollHolder extends BaseHolder<NormalModel> {

    @BindView(R.id.tv_text)
    TextView mTextView;

    public static MyRvOverScrollHolder newInstance(Context context, ViewGroup viewGroup) {
        return new MyRvOverScrollHolder(LayoutInflater.from(context).inflate(R.layout.rv_item_normal_layout, viewGroup, false));
    }

    public MyRvOverScrollHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void bindData(NormalModel normalModel) {
        mTextView.setText(normalModel.text);
    }

}