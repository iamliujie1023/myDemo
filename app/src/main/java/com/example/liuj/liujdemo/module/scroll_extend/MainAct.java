package com.example.liuj.liujdemo.module.scroll_extend;

import com.example.liuj.liujdemo.base.BaseMainAct;
import com.example.liuj.liujdemo.model.BaseModel;
import com.example.liuj.liujdemo.model.ModelMapAatModel;
import com.example.liuj.liujdemo.module.scroll_extend.pagescroll.PageScrollAct;

import java.util.ArrayList;
import java.util.List;

public class MainAct extends BaseMainAct {

    @Override
    protected List<BaseModel> initCell() {
        List<BaseModel> list = new ArrayList<>();
        list.add(new ModelMapAatModel(PageScrollAct.class, "PageScrollAct"));
        return list;
    }

}
