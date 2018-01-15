package com.example.j2se.threadlocal;

import com.example.j2se.LogUtil;

/**
 * Created by liuj on 2018/1/9.
 */

public class main {

    public static void main(String[] args) {
        final ThreadLocal<String> tl_1 = new ThreadLocal<>();
        tl_1.set("TL_1 def value");

        LogUtil.sysopl("thread_main value = " + tl_1.get());

        new Thread("thread_1"){
            @Override
            public void run() {
                super.run();
                tl_1.set("thread_1_value");
                LogUtil.sysopl("thread_1 TL_1 value = " + tl_1.get());
            }
        }.start();

        new Thread("thread_1"){
            @Override
            public void run() {
                super.run();
                LogUtil.sysopl("thread_2 TL_1 value = " + tl_1.get());
            }
        }.start();

    }

}