package com.example.liuj.utils.fileutil;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by liuj on 2017/12/6.
 */

public class MyFileRead {

    /**
     * @param dir      文件的目录
     * @param fileName 文件的名称
     * @return
     */
    public static FileInputStream openInputStream(String dir, String fileName) {
        return openInputStream(dir + File.separator + fileName);
    }

    /**
     * @param filePath 文件的绝对路径
     * @return
     */
    public static FileInputStream openInputStream(String filePath) {
        File f = new File(filePath);
        if (f.exists()) {
            try {
                return new FileInputStream(f);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * @param dir
     * @param fileName
     */
    public static String getStringFromFile(String dir, String fileName) {
        return getStringFromFile(dir, fileName, "UTF-8");
    }

    /**
     * @param dir
     * @param fileName
     * @param enc
     */
    public static String getStringFromFile(String dir, String fileName, String enc) {
        return getStringInputStream(openInputStream(dir, fileName), enc);
    }

    public static String getStringInputStream(InputStream in) {
        return getStringInputStream(in, "UTF-8");
    }

    public static String getStringInputStream(InputStream in, String enc) {
        return getStringInputStream(in, enc, false);
    }

    /**
     * @param in            InputStream
     * @param enc           编码格式 默认为UTF-8
     * @param appendNewLine 是否追加换行符,默认为false
     */
    public static String getStringInputStream(InputStream in, String enc, boolean appendNewLine) {
        String result = null;
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(in, enc));
            final StringBuilder str = new StringBuilder();
            String temp;
            do {
                temp = br.readLine();
                if (temp != null) {
                    str.append(temp);
                    if (appendNewLine) {
                        str.append("\n");
                    }
                }
            } while (temp != null);
            result = str.toString();

            br.close();
            in = null;
            br = null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
