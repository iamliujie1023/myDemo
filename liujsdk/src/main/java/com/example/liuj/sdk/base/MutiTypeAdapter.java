package com.example.liuj.sdk.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liuj on 2017/12/14.
 */

public class MutiTypeAdapter extends RecyclerView.Adapter {

    private _MutiTypeAdapterHelper mMutiTypeAdapterHelper;

    private List<BaseModel> mData;

    private Context mContext;

    public MutiTypeAdapter(Context context) {
        mMutiTypeAdapterHelper = new _MutiTypeAdapterHelper();
        mData = new ArrayList<>();
        mContext = context;
    }

    public void clear() {
        mData.clear();
    }

    public void reset(List<? extends BaseModel> list) {
        mData.clear();
        mData.addAll(list);
        notifyDataSetChanged();
    }

    public void addAll(List<? extends BaseModel> list) {
        int start = mData.size();
        mData.addAll(list);
        notifyItemRangeChanged(start, mData.size());
    }

    public void addItem(BaseModel model) {
        mData.add(model);
        notifyItemChanged(mData.size());
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return mMutiTypeAdapterHelper.onCreateViewHolder(parent, viewType, mContext);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        mMutiTypeAdapterHelper.onBindViewHolder(holder, mData.get(position), position);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    @Override
    public int getItemViewType(int position) {
        BaseModel baseModel = mData.get(position);
        return mMutiTypeAdapterHelper.getItemViewType(baseModel);
    }
}