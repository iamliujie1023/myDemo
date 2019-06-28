package com.example.liuj2019q3;

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
    protected List<BaseModel> initCell() {
        List list = new ArrayList();
        list.add(new ModelMapAatModel(com.example.liuj2019q3.view.MainAct.class, "View相关回顾"));
        return list;
    }
}
