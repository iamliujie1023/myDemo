package com.example.j2se.serializable;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Main5 implements Serializable {
    public int i;
    public String mString;
    public static String fileName = "main5_serializable_demo.obj";

    public static void main(String[] args) {
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName));
            Main5 test = new Main5();
            test.i = 1;
            test.mString = "liu";
            out.writeObject(test);
            out.flush();
            test.i = 2;
            test.mString = "zhang";
            out.writeObject(test);
            out.close();
            ObjectInputStream oin = new ObjectInputStream(new FileInputStream(fileName));
            Main5 t1 = (Main5) oin.readObject();
            Main5 t2 = (Main5) oin.readObject();
            System.out.println(t1.toString());
            System.out.println(t2.toString());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    @Override
    public String toString() {
        return "Main5{" +
                "i=" + i +
                ", mString='" + mString + '\'' +
                '}';
    }
}
