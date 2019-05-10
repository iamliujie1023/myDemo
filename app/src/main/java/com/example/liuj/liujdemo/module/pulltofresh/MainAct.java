package com.example.liuj.liujdemo.module.pulltofresh;

import com.example.liuj.liujdemo.base.BaseMainAct;
import com.example.liuj.liujdemo.model.BaseModel;
import com.example.liuj.liujdemo.model.ModelMapAatModel;

import java.util.ArrayList;
import java.util.List;

public class MainAct extends BaseMainAct {
    @Override
    protected List<BaseModel> initCell() {
        List<BaseModel> list = new ArrayList<>();
        list.add(
                new ModelMapAatModel(com.example.liuj.liujdemo.module.pulltofresh.smart.MainAct.class, "smart 控件的")
        );
        return list;
    }
}
