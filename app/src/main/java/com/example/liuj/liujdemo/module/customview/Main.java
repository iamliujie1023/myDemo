package com.example.liuj.liujdemo.module.customview;

import com.example.liuj.liujdemo.base.BaseMainAct;
import com.example.liuj.liujdemo.model.BaseModel;
import com.example.liuj.liujdemo.model.ModelMapAatModel;
import com.example.liuj.liujdemo.module.customview.base_demo.ViewDemo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liuj on 2017/12/29.
 */
public class Main extends BaseMainAct {


    @Override
    protected List<BaseModel> initCell() {
        List<BaseModel> list = new ArrayList<>();
        list.add(new ModelMapAatModel(Demo1_12.class, "Demo1_12"));
        list.add(new ModelMapAatModel(ViewDemo.class, "ViewDemo"));
        return list;
    }


}