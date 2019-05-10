package com.example.j2se.serializable;

import com.example.j2se.LogUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Main {

    public static void main(String[] args) {

        try {
            serializeModel();

//            SerializeableDemoModel model2 = deserialize();
//            if(null != model2) {
//                LogUtil.sysopl(model2.toString());
//            }
        } catch (IOException e) {
            e.printStackTrace();
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
        }

    }

    public static void serializeModel() throws IOException {
        SerializeableDemoModel model1 = new SerializeableDemoModel("zhang", 100);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(new File("s_deme")));
        objectOutputStream.writeObject(model1);
        LogUtil.sysopl("SerializeableDemoModel 对象序列化成功！");
        objectOutputStream.close();
    }

    public static SerializeableDemoModel deserialize() throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File("s_deme")));
        SerializeableDemoModel person = (SerializeableDemoModel) ois.readObject();
        LogUtil.sysopl("SerializeableDemoModel 对象反序列化成功！");
        return person;
    }

}