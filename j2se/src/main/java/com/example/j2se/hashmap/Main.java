package com.example.j2se.hashmap;

import com.example.j2se.LogUtil;

import java.util.HashMap;

/**
 * Created by liuj on 2017/12/27.
 */

public class Main {

    public static void main(String[] args) {
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("demo", 1);

        int i = 1 << 30;
        LogUtil.sysopl(i + "");

        int a = 9 & 16;

        LogUtil.sysopl(a + "");

    }

}