package com.example.liuj.liujdemo.tools;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by liuj on 2018/1/13.
 */

public class TimeUtils {

    public static String getYYYYMMDDHHMMSS(long time) {
        SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return s.format(new Date(time));
    }

}
