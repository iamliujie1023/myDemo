package com.example.j2se.javareflection;

/**
 * Created by liuj on 2017/11/27.
 */

public class Person {

    public Person() {
        LogUtil.sysopl("private 构造方法");
    }

    private Person(String name, int age) {
        LogUtil.sysopl(String.format("private 构造方法 name:%s, age:%s", name, age));
        this.name = name;
        this.age = age;
    }

    public Person(String name) {
        this.name = name;
        LogUtil.sysopl("public 构造方法 参数String:" + name);
    }

    private String name;
    private int age;
    private String msg = "private msg field";

    public String msg2 = "public field name(msg2)";

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getMsg() {
        return msg;
    }

    public void printInfo(String name, int age) {
        LogUtil.sysopl(String.format("name:%s, age:%s", name, age));
    }

    private void printMsg(String msg) {
        LogUtil.sysopl(String.format("msg:%s", msg));
    }

}
