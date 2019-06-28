package com.example.liuj2019q3.view;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.liuj.sdk.base.BaseMainAct;
import com.example.liuj.sdk.base.BaseModel;
import com.example.liuj.sdk.base.ModelMapAatModel;

import java.util.ArrayList;
import java.util.List;

/**
 * @author liuj
 * @time 19-6-28
 */
public class MainAct extends BaseMainAct {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("View相关");
    }

    @Override
    protected List<BaseModel> initCell() {
        List list = new ArrayList();
        list.add(new ModelMapAatModel(PointAct.class, "基础坐标"));
        return list;
    }

}
