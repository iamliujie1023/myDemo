package com.example.j2se;

import java.util.UUID;

/**
 * Created by liuj on 2017/11/30.
 */

public class Main {

    public static void main(String[] args) {

//        String x = null;
//        String str = String.format("%s%s%s%s", "A", "B", "C", x);
//        LogUtil.sysop(str);


//        HashCodeDemo hashCodeDemo1 = new HashCodeDemo("A");
//        HashCodeDemo hashCodeDemo2 = new HashCodeDemo("A");
//
//        LogUtil.sysopl("hashCodeDemo1 hashCode = " + hashCodeDemo1.getId());
//        LogUtil.sysopl("hashCodeDemo2 hashCode = " + hashCodeDemo2.getId());
//
//        hashCodeDemo2.mNext = hashCodeDemo1;
//        LogUtil.sysopl("hashCodeDemo2 hashCode = " + hashCodeDemo2.getId());

        for (int i = 0; i < 1000; i++) {
            LogUtil.sysopl(UUID.randomUUID().toString().replace("-", ""));
        }

    }

//    static class HashCodeDemo {
//        public String m;
//        public HashCodeDemo mNext;
//
//        public HashCodeDemo(String m) {
//            this.m = m;
//        }
//
//        public String getId() {
//            return this.hashCode() + "";
//        }
//    }

}
