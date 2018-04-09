package com.example.liuj.liujdemo.module.diyview;

import com.example.liuj.liujdemo.base.BaseMainAct;
import com.example.liuj.liujdemo.model.BaseModel;
import com.example.liuj.liujdemo.model.ModelMapAatModel;
import com.example.liuj.liujdemo.module.diyview.timeslot.TimeSlotAct;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jliu on 2018/4/3.
 */
public class MainAct extends BaseMainAct {

    @Override
    protected List<BaseModel> initCell() {
        List<BaseModel> list = new ArrayList<>();
        list.add(new ModelMapAatModel(TimeSlotAct.class, "时间戳滑动控件"));
        return list;
    }

}