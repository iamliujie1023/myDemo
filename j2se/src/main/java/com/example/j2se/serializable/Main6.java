package com.example.j2se.serializable;

import com.example.j2se.LogUtil;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class Main6 {

    public static void main(String[] args) {

        try {
            serializeModel();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void serializeModel() throws IOException {
        SDemo model1 = new SDemo();
        model1.mAge = 10;
        model1.mId = "id_15";
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(new File("s_deme2")));
        objectOutputStream.writeObject(model1);
        LogUtil.sysopl("SDemo 对象序列化成功！sDemo=" +model1.toString() );
        objectOutputStream.close();
    }


}
