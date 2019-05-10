package com.example.liuj.liujdemo.module.webview;

import com.example.liuj.liujdemo.base.BaseMainAct;
import com.example.liuj.liujdemo.model.BaseModel;
import com.example.liuj.liujdemo.model.ModelMapAatModel;
import com.example.liuj.liujdemo.module.webview.action.base.MyActionWebViewAct;
import com.example.liuj.liujdemo.module.webview.base.MyHyBridBaseAct;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jliu on 2018/3/12.
 */

public class MainAct extends BaseMainAct {

    @Override
    protected List<BaseModel> initCell() {
        List<BaseModel> list = new ArrayList<>();
        list.add(new ModelMapAatModel(MyHyBridBaseAct.class, "基本的实现"));
        list.add(new ModelMapAatModel(MyActionWebViewAct.class, "action实现"));
        return list;
    }

}