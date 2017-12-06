package com.example.liuj.utils.fileutil;

import java.io.File;

public class MyFileUtils {

    public static boolean isFileExists(String dir, String fileName) {
        File file = new File(dir + File.separator + fileName);
        return file.exists();
    }

    public static void deleteFile(String dir, String fileName) {
        File file = new File(dir + File.separator + fileName);
        if (file.exists()) {
            file.delete();
        }
    }

    /**
     * @param dir
     * @return
     */
    static boolean mkdirs(File dir) {
        if (dir != null && (!dir.exists() || !dir.isDirectory())) {
            return dir.mkdirs();
        }
        return true;
    }

    /**
     * @param fileName
     * @return
     */
    public static String getFileExtension(String fileName) {
        if (fileName == null || fileName.trim().equals(""))
            return null;
        int pos = fileName.lastIndexOf(".");

        if (pos > -1 && pos < fileName.length()) {
            return fileName.substring(pos + 1);
        } else {
            return "";
        }
    }

    /**
     * @param url
     * @return
     */
    public static String getFileName(String url) {
        if (url == null || url.trim().equals(""))
            return null;
        int pos = url.lastIndexOf("/");

        if (pos > -1 && pos < url.length()) {
            return url.substring(pos + 1);
        } else {
            return "";
        }
    }

    /**
     * 清空文件夹
     *
     * @param strPath
     */
    public static void clearFile(String strPath) {

        File file = new File(strPath);
        if (file.exists() && file.isDirectory()) {
            if (file.listFiles().length == 0) {
                file.delete();
            } else {
                File[] ff = file.listFiles();
                for (int i = 0; i < ff.length; i++) {
                    if (ff[i].isDirectory()) {
                        clearFile(strPath);
                    }
                    ff[i].delete();
                }
            }
        }

    }

    public static void removeFile(String strPath) {
        File file = new File(strPath);
        if (file.exists()) {
            file.delete();
        }
    }

}
