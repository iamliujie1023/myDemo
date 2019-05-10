package com.example.j2se.serializable;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * 情境：服务器端给客户端发送序列化对象数据，对象中有一些数据是敏感的，比如密码字符串等，
 * 希望对该密码字段在序列化时，进行加密，而客户端如果拥有解密的密钥，
 * 只有在客户端进行反序列化时，才可以对密码进行读取，这样可以一定程度保证序列化对象的数据安全。
 * <p>
 * 解决：在序列化过程中，虚拟机会试图调用对象类里的 writeObject 和 readObject 方法，
 * 进行用户自定义的序列化和反序列化，如果没有这样的方法，
 * 则默认调用是 ObjectOutputStream 的 defaultWriteObject 方法以及 ObjectInputStream 的 defaultReadObject 方法。
 * 用户自定义的 writeObject 和 readObject 方法可以允许用户控制序列化的过程，
 * 比如可以在序列化的过程中动态改变序列化的数值。基于这个原理，可以在实际应用中得到使用，
 * 用于敏感字段的加密工作
 */
public class Main3 implements Serializable {

    private static final long serialVersionUID = 1L;

    private String password = "pass";

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    private void writeObject(ObjectOutputStream out) {
        try {
            ObjectOutputStream.PutField putFields = out.putFields();
            System.out.println("原密码:" + password);
            password = "encryption";//假装加密
            putFields.put("password", password);
            System.out.println("加密后的密码" + password);
            out.writeFields();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void readObject(ObjectInputStream in) {
        try {
            ObjectInputStream.GetField readFields = in.readFields();
            Object object = readFields.get("password", "");
            System.out.println("要解密的字符串:" + object.toString());
            password = "pass";//假装解密,需要获得本地的密钥
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        try {
            ObjectOutputStream out = new ObjectOutputStream(
                    new FileOutputStream("password_serializable_demo.obj"));
            out.writeObject(new Main3());
            out.close();

            ObjectInputStream oin = new ObjectInputStream(new FileInputStream(
                    "password_serializable_demo.obj"));
            Main3 t = (Main3) oin.readObject();
            System.out.println("解密后的字符串:" + t.getPassword());
            oin.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


}