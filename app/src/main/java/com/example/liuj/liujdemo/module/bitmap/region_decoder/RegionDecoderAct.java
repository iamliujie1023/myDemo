package com.example.liuj.liujdemo.module.bitmap.region_decoder;

import android.os.Bundle;

import com.example.liuj.R;
import com.example.liuj.liujdemo.base.BaseActivity;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by liuj on 2018/1/15.
 *  http://blog.csdn.net/lmj623565791/article/details/49300989
 */
public class RegionDecoderAct extends BaseActivity {

    private LargeImageView mLargeImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.large_image_view_act);

        mLargeImageView = (LargeImageView) findViewById(R.id.largetImageview);
        try {
            InputStream inputStream = getAssets().open("qm.jpg");
            mLargeImageView.setInputStream(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
