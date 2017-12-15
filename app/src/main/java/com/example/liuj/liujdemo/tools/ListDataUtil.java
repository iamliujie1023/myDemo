package com.example.liuj.liujdemo.tools;

import com.example.liuj.liujdemo.model.NormalModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liuj on 2017/12/13.
 */

public class ListDataUtil {

    public static final List<NormalModel> getDemoColors() {
        List<NormalModel> list = new ArrayList<>();
        list.add(new NormalModel("Red"));
        list.add(new NormalModel("Light Red"));
        list.add(new NormalModel("Dark Red"));
        list.add(new NormalModel("Yellow"));
        list.add(new NormalModel("Purple"));
        list.add(new NormalModel("Oragne"));
        list.add(new NormalModel("Green"));
        list.add(new NormalModel("Gray"));
        list.add(new NormalModel("Black"));
        list.add(new NormalModel("White"));
        list.add(new NormalModel("Blue"));
        list.add(new NormalModel("Light Blue"));
        list.add(new NormalModel("Dark Blue"));
        return list;
    }

}
