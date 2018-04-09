package com.example.liuj.liujdemo.module.diyview.timeslot;

import com.example.liuj.liujdemo.model.BaseModel;

/**
 * Created by jliu on 2018/4/3.
 */

public class TLModel extends BaseModel {

    public String title;

    public String mSubTitle;

    public TLModel(String title, String subTitle) {
        this.title = title;
        mSubTitle = subTitle;
    }
}
