package com.example.liuj.liujdemo.tools;

import android.graphics.Color;

import java.util.Random;

public class ColorUtils {

    public static int parseColor(String color) {
        int result = Color.WHITE;
        try {
            result = Color.parseColor(color);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static int getRandomColor() {
        int colors[] = {
                Color.TRANSPARENT, Color.BLACK, Color.BLUE,
                Color.GREEN, Color.CYAN, Color.GRAY,
                Color.RED, Color.BLUE, Color.YELLOW,
                Color.TRANSPARENT, Color.DKGRAY, Color.WHITE};

        Random random = new Random();
        int r = random.nextInt(colors.length - 1);
        int color = colors[r];
        return color;
    }

}
