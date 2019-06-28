package com.example.liuj.sdk.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;


import java.lang.reflect.Type;
import java.util.HashMap;

/**
 * Created by liuj on 2017/12/29.
 */
class _MutiTypeAdapterHelper {

    public static final int TYPE_NORMALL = 1 << 20;
    public static final int TYPE_STATUS = TYPE_NORMALL + 1;
    public static final int TYPE_MODEL_MAP_ACT = TYPE_NORMALL + 2;
    public static final int TYPE_BANNER_DEMO_ITEM = TYPE_NORMALL + 3;

    private HashMap<Type, Integer> mTypeMapType;

    public _MutiTypeAdapterHelper() {
        mTypeMapType = new HashMap<>();
        mTypeMapType.put(ModelMapAatModel.class, TYPE_MODEL_MAP_ACT);
    }

    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType, Context context) {
        switch (viewType) {
            case TYPE_MODEL_MAP_ACT:
                return ModelMapActHolder.newInstance(parent, context);
            default:
                return ModelMapActHolder.newInstance(parent, context);
        }
    }

    public void onBindViewHolder(RecyclerView.ViewHolder holder, BaseModel baseModel, int position) {
        ((BaseHolder) holder).bindData(baseModel);
    }

    public final int getItemViewType(BaseModel baseModel) {
        return mTypeMapType.get(baseModel.getClass());
    }


}