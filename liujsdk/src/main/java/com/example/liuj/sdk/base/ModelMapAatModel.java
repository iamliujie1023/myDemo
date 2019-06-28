package com.example.liuj.sdk.base;

/**
 * Created by liuj on 2018/1/4.
 */

public class ModelMapAatModel extends BaseModel{

    public ModelMapAatModel(Class<?> clz) {
        this.clz = clz;
        this.text = clz.getSimpleName();
    }

    public ModelMapAatModel(Class<?> clz, String text) {
        this.clz = clz;
        this.text = text;
    }

    public Class<?> clz;

    public String text;

}
