package com.example.j2se.serializable;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 *  静态变量序列化问题代码
 */
public class Main2 implements Serializable {

    private static final long serialVersionUID = 1L;
    public static int staticVar = 5;

    public static void main(String[] args) {
        try {
            //初始时staticVar为5
            ObjectOutputStream out = new ObjectOutputStream(
                    new FileOutputStream("static_serializable_demo.obj"));
            out.writeObject(new Main2());
            out.close();

            //序列化后修改为10
            Main2.staticVar = 10;

            ObjectInputStream oin = new ObjectInputStream(new FileInputStream(
                    "static_serializable_demo.obj"));
            Main2 t = (Main2) oin.readObject();
            oin.close();

            //再读取，通过t.staticVar打印新的值
            System.out.println(t.staticVar);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}