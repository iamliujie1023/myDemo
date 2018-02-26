package com.example.liuj.liujdemo.module.NestedScrolling;

import com.example.liuj.liujdemo.base.BaseMainAct;
import com.example.liuj.liujdemo.model.BaseModel;
import com.example.liuj.liujdemo.model.ModelMapAatModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liuj on 2018/2/26.
 */

public class MainAct extends BaseMainAct{

    @Override
    protected List<BaseModel> initCell() {
        List<BaseModel> list = new ArrayList<>();
        list.add(new ModelMapAatModel(com.example.liuj.liujdemo.module.NestedScrolling.old.MainActivity.class, "以前传统方式"));
        list.add(new ModelMapAatModel(com.example.liuj.liujdemo.module.NestedScrolling.nestedscrollingparent.MainActivity.class, "现在的方式"));

        return list;
    }
}
