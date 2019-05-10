package com.example.liuj.liujdemo.module.pulltofresh.smart;

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
                new ModelMapAatModel(SimpleAct.class, "简单的实现下拉刷新")
        );
        return list;
    }

}
