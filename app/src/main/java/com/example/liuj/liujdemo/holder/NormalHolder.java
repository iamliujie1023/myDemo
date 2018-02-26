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
 * Created by liuj on 2017/12/29.
 */

public class NormalHolder extends BaseHolder<NormalModel> {

    @BindView(R.id.tv_text)
    TextView mTvTest;

    public static NormalHolder newInstance(ViewGroup parent, Context context) {
        return new NormalHolder(LayoutInflater.from(context).inflate(R.layout.rv_item_normal_layout, parent, false));
    }

    public NormalHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void bindData(NormalModel normalModel) {
        mTvTest.setText(normalModel.text);
    }

}