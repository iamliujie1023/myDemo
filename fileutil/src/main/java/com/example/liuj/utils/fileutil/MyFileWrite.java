package com.example.liuj.utils.fileutil;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

/**
 * Created by liuj on 2017/12/6.
 */
public class MyFileWrite {

    public static boolean writeStreamToFile(InputStream in, String dir, String fileName) {
        if (in == null || dir == null || fileName == null) {
            return false;
        }

        File directory = new File(dir);

        if (!MyFileUtils.mkdirs(directory))
            return false;

        File target = new File(dir + File.separator + fileName);

        try {
            if (!target.exists()) {
                target.createNewFile();
            }
            FileOutputStream out = new FileOutputStream(target);

            int b;
            do {
                b = in.read();
                if (b != -1) {
                    out.write(b);
                }
            } while (b != -1);
            in.close();
            out.flush();
            out.close();
            out = null;
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            try {
                target.delete();
            } catch (Exception ex) {
            }
            return false;
        }

    }

    public static boolean writeStringToFile(String str, String dir, String fileName) {
        return writeStringToFile(str, "UTF-8", dir, fileName);
    }

    public static boolean writeStringToFile(String str, String enc, String dir, String fileName) {
        if (str == null || dir == null || fileName == null)
            return false;

        File directory = new File(dir);

        if (!MyFileUtils.mkdirs(directory))
            return false;

        File target = new File(dir + File.separator + fileName);

        try {
            if (!target.exists()) {
                target.createNewFile();
            }
            FileOutputStream out = new FileOutputStream(target);
            byte[] bytes = str.getBytes(enc);
            out.write(bytes);
            out.flush();
            out.close();
            out = null;
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            try {
                target.delete();
            } catch (Exception ex) {
            }
            return false;
        }

    }
}
