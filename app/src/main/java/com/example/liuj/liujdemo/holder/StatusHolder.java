package com.example.liuj.liujdemo.holder;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.liuj.R;
import com.example.liuj.liujdemo.model.StatusModel;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by liuj on 2017/12/21.
 */
public class StatusHolder extends BaseHolder<StatusModel> {

    @BindView(R.id.tv_text)
    TextView mTextView;

    int colorNormal;
    int colorStart;
    int colorStop;

    public static StatusHolder newInstance(Context context, ViewGroup viewGroup) {
        return new StatusHolder(LayoutInflater.from(context).inflate(R.layout.rv_item_status_layout, viewGroup, false), context);
    }

    public StatusHolder(View itemView, Context context) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        colorNormal = ContextCompat.getColor(context, R.color.none_received);
        colorStart = ContextCompat.getColor(context, R.color.start_received);
        colorStop = ContextCompat.getColor(context, R.color.stop_received);
    }

    @Override
    public void bindData(StatusModel stateModel) {
        mTextView.setText(stateModel.msg);
        if (stateModel.status == StatusModel.STATUS_SCHEDULE_JOB) {
            mTextView.setBackgroundColor(colorNormal);
        } else if (stateModel.status == StatusModel.STATUS_START_TASK) {
            mTextView.setBackgroundColor(colorStart);
        } else if (stateModel.status == StatusModel.STATUS_STOP_STASK) {
            mTextView.setBackgroundColor(colorStop);
        }
    }

}