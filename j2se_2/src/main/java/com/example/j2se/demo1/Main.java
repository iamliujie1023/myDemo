package com.example.j2se.demo1;

import com.example.j2se.serializable.SDemo;
import com.example.j2se.serializable.SerializeableDemoModel;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class Main {

    public static void main(String[] args) {
        try {
            System.out.println("update1");

            System.out.println("update2");

            System.out.println("update3");

            System.out.println("update4");


            System.out.println("update by dev_1");
            System.out.println("update by dev");
//            SerializeableDemoModel model = deserialize();
//            System.out.println(model);


            System.out.println("在另一个工程下反序列化");
            SDemo model = deserialize2();
            System.out.println(model);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

//    public static SerializeableDemoModel deserialize() throws IOException, ClassNotFoundException {
//        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File("s_deme")));
//        SerializeableDemoModel person = (SerializeableDemoModel) ois.readObject();
//        System.out.println("SerializeableDemoModel 对象反序列化成功！");
//        return person;
//    }

    public static SDemo deserialize2() throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File("s_deme2")));
        SDemo person = (SDemo) ois.readObject();
        System.out.println("SDemo 对象反序列化成功！");
        return person;
    }

}
