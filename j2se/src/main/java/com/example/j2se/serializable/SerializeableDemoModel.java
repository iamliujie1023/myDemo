package com.example.j2se.serializable;

import java.io.Serializable;

public class SerializeableDemoModel implements Serializable {
    
    private static final long serialVersionUID = 1L;

    public String name;

    public int age;

    public SerializeableDemoModel(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "SerializeableDemoModel{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

}