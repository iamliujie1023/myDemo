package com.example.liuj.liujdemo.module.android_base;

import com.example.liuj.liujdemo.base.BaseMainAct;
import com.example.liuj.liujdemo.model.BaseModel;
import com.example.liuj.liujdemo.model.ModelMapAatModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jliu on 2018/3/6.
 */
public class MainAct extends BaseMainAct {
    @Override
    protected List<BaseModel> initCell() {

        List<BaseModel> baseModels = new ArrayList<>();
        baseModels.add(new ModelMapAatModel(NormallCaseActA.class, "正常情况下生命周期"));
        baseModels.add(new ModelMapAatModel(ConfigChangeCaseAct.class, "配置改变异常情况下生命周期"));
        baseModels.add(new ModelMapAatModel(ExceptionCaseAct.class, "Act被系统回收异常情况下生命周期"));

        return baseModels;
    }
}
