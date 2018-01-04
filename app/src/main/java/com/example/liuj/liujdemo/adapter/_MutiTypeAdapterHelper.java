package com.example.liuj.liujdemo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.example.liuj.liujdemo.holder.BaseHolder;
import com.example.liuj.liujdemo.holder.ModelMapActHolder;
import com.example.liuj.liujdemo.holder.NormalHolder;
import com.example.liuj.liujdemo.holder.StatusHolder;
import com.example.liuj.liujdemo.model.ModelMapAatModel;
import com.example.liuj.liujdemo.model.BaseModel;
import com.example.liuj.liujdemo.model.NormalModel;
import com.example.liuj.liujdemo.model.StatusModel;

import java.lang.reflect.Type;
import java.util.HashMap;

/**
 * Created by liuj on 2017/12/29.
 */
class _MutiTypeAdapterHelper {

    public static final int TYPE_NORMALL = 1 << 20;
    public static final int TYPE_STATUS = TYPE_NORMALL + 1;
    public static final int TYPE_MODEL_MAP_ACT = TYPE_NORMALL + 2;

    private HashMap<Type, Integer> mTypeMapType;

    public _MutiTypeAdapterHelper() {
        mTypeMapType = new HashMap<>();
        mTypeMapType.put(NormalModel.class, TYPE_NORMALL);
        mTypeMapType.put(StatusModel.class, TYPE_STATUS);
        mTypeMapType.put(ModelMapAatModel.class, TYPE_MODEL_MAP_ACT);
    }

    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType, Context context) {
        switch (viewType) {
            case TYPE_NORMALL :
                return NormalHolder.newInstance(parent, context);
            case TYPE_STATUS:
                return StatusHolder.newInstance(parent, context);
            case TYPE_MODEL_MAP_ACT:
                return ModelMapActHolder.newInstance(parent, context);
            default:
                return NormalHolder.newInstance(parent, context);
        }
    }

    public void onBindViewHolder(RecyclerView.ViewHolder holder, BaseModel baseModel, int position) {
        ((BaseHolder) holder).bindData(baseModel);
    }

    public final int getItemViewType(BaseModel baseModel) {
        return mTypeMapType.get(baseModel.getClass());
    }


}