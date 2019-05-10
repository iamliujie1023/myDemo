package com.example.liuj.liujdemo.module.glide;

import java.util.Random;

public class Images {

    public static String getRomdanImgUrl() {
        Random random = new Random();
        int pos = random.nextInt(imageThumbUrls.length - 1);
        return imageThumbUrls[pos];
    }

    public final static String[] imageThumbUrls = new String[]{
            "http://img3.imgtn.bdimg.com/it/u=4271053251,2424464488&fm=23&gp=0.jpg",
            "https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=1588849600,2826220087&fm=200&gp=0.jpg",
            "https://img-bss.csdn.net/201806271145205598.jpg",
            "https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=3532261106,3235149118&fm=27&gp=0.jpg",
            "https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=1350989714,1426510945&fm=200&gp=0.jpg",
            "https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=3493211203,1072925001&fm=200&gp=0.jpg"

    };
}
