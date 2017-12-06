package com.example.liuj.sdk;

import android.os.Environment;

import java.io.File;

/**
 * Created by liuj on 2017/12/6.
 */

public class FileUtils {

    public static boolean isSDCardAvailableNow() {
        String sdStatus = Environment.getExternalStorageState();
        return sdStatus.equals(Environment.MEDIA_MOUNTED);
    }

    public static String getAppSdcardPath() {
        File file = null;
        if (isSDCardAvailableNow()) {
            file = new File(Consts.DEFAULT_APK_DIR);
            if (!file.exists()) {
                if (!file.mkdirs()) {
                    LogUtils.e(String.format("%s file create failed", Consts.DEFAULT_APK_DIR));
                }
            }
        }
        return file == null ? "" : file.getAbsolutePath();
    }
}
