package com.example.j2se.serializable;

import java.io.Serializable;

public class SDemo implements Serializable {
    private static final long serialVersionUID = 1L;

    public String mId;
    public int mAge;

    @Override
    public String toString() {
        return "SDemo{" +
                "mId='" + mId + '\'' +
                ", mAge=" + mAge +
                '}';
    }
}
