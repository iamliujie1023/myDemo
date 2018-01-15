// ILiujAidlInterface.aidl
package com.example.liuj.liujdemo.module.service;

import com.example.liuj.liujdemo.module.service.ILiujCallback;
// Declare any non-default types here with import statements

interface ILiujAidlInterface {
//    /**
//     * Demonstrates some basic types that you can use as parameters
//     * and return values in AIDL.
//     */
//    void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat,
//            double aDouble, String aString);

      void testMethod(String str);

      void registerCallback(ILiujCallback callback);

      void unRegisterCallback(ILiujCallback callback);
}