package com.example.j2se.serializable;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * 序列化存储规则
 * </p>
 * 清单 3 中对同一对象两次写入文件，打印出写入一次对象后的存储大小和写入两次后的存储大小，
 * 然后从文件中反序列化出两个对象，比较这两个对象是否为同一对象。一般的思维是，两次写入对象，
 * 文件大小会变为两倍的大小，反序列化时，由于从文件读取，生成了两个对象，
 * 判断相等时应该是输入 false 才对，
 * </p>
 * 但是最后结果输出:
 * 56
 * 61
 * true
 */
public class Main4 implements Serializable {

    public static void main(String[] args) {
        try {
            ObjectOutputStream out = new ObjectOutputStream(
                    new FileOutputStream("rule_serializable_demo.obj"));
            Main4 test = new Main4();
            //试图将对象两次写入文件
            out.writeObject(test);
            out.flush();
            System.out.println(new File("rule_serializable_demo.obj").length());
            out.writeObject(test);
            out.close();
            System.out.println(new File("rule_serializable_demo.obj").length());

            ObjectInputStream oin = new ObjectInputStream(new FileInputStream(
                    "rule_serializable_demo.obj"));
            //从文件依次读出两个文件
            Main4 t1 = (Main4) oin.readObject();
            Main4 t2 = (Main4) oin.readObject();
            oin.close();

            //判断两个引用是否指向同一个对象
            System.out.println(t1 == t2);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
