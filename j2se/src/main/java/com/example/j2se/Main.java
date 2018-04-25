package com.example.j2se;

/**
 * Created by liuj on 2017/11/30.
 */

public class Main {

    public static void main(String[] args) {

        String x = null;
        String str = String.format("%s%s%s%s", "A", "B", "C", x);
        LogUtil.sysop(str);

    }
}
