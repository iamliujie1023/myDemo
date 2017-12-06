package com.example.liuj.sdk;

import android.os.Environment;

import java.io.File;

/**
 * Created by liuj on 2017/12/6.
 */

public class Consts {
    public static String PACKAGE_NAME = SDKConfig.Package_Name;
    public static final String TEMP_IMG_DIR = Environment.getExternalStorageDirectory() + "/Android/data/" + PACKAGE_NAME + File.separator + "tmp" + File.separator;
    public static final String DEFAULT_IMG_DIR = Environment.getExternalStorageDirectory() + "/Android/data/" + PACKAGE_NAME + File.separator + "default_tmp" + File.separator;
    public static final String DEFAULT_TOPBAR_ADS_DIR = Environment.getExternalStorageDirectory() + "/Android/data/" + PACKAGE_NAME + File.separator + "default_ads_tmp" + File.separator;
    public static final String DEFAULT_APK_DIR = Environment.getExternalStorageDirectory() + "/Android/data/" + PACKAGE_NAME + File.separator + "apk" + File.separator;
    public static final String CAMERA_IMG_DIR = Environment.getExternalStorageDirectory() + "/Android/data/" + PACKAGE_NAME + File.separator + "camera" + File.separator;

}
