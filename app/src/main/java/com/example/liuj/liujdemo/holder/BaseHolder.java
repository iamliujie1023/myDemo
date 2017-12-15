package com.example.liuj.liujdemo.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by liuj on 2017/12/13.
 */
public abstract class BaseHolder<T> extends RecyclerView.ViewHolder {

    public BaseHolder(View itemView) {
        super(itemView);
    }

    public abstract void bindData(T t);

}