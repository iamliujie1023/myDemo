package com.example.liuj.liujdemo.module.bitmap;

import com.example.liuj.liujdemo.base.BaseMainAct;
import com.example.liuj.liujdemo.model.BaseModel;
import com.example.liuj.liujdemo.model.ModelMapAatModel;
import com.example.liuj.liujdemo.module.bitmap.region_decoder.LargeImageViewSimpleBakAct;
import com.example.liuj.liujdemo.module.bitmap.region_decoder.RegionDecoderAct;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liuj on 2018/1/15.
 */

public class MainAct extends BaseMainAct {

    @Override
    protected List<BaseModel> initCell() {
        List<BaseModel> list = new ArrayList<>();
        list.add(new ModelMapAatModel(LargeImageViewSimpleBakAct.class, "Android 高清加载巨图方案 拒绝压缩图片-simple"));
        list.add(new ModelMapAatModel(RegionDecoderAct.class, "Android 高清加载巨图方案 拒绝压缩图片"));

        return list;
    }

}
