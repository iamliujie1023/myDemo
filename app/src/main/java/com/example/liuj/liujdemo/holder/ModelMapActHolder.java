package com.example.liuj.liujdemo.holder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.liuj.R;
import com.example.liuj.liujdemo.model.ModelMapAatModel;
import com.example.liuj.sdk.IntentUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by liuj on 2018/1/4.
 */
public class ModelMapActHolder extends BaseHolder<ModelMapAatModel>{

    @BindView(R.id.tv_text)
    TextView mTextView;

    Context mContext;

    public static ModelMapActHolder newInstance(ViewGroup group, Context context) {
        return new ModelMapActHolder(LayoutInflater.from(context).inflate(R.layout.rv_item_model_map_act_layout, group, false), context);
    }

    public ModelMapActHolder(View itemView, Context context) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        mContext = context;
    }

    @Override
    public void bindData(final ModelMapAatModel actMapModelModel) {
        mTextView.setText(actMapModelModel.text);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentUtil.goToTargetAct(mContext, actMapModelModel.clz);
            }
        });
    }

}
