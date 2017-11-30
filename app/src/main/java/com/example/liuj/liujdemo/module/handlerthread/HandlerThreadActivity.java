package com.example.liuj.liujdemo.module.handlerthread;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.widget.ImageView;

import com.example.liuj.liujdemo.R;
import com.example.liuj.liujdemo.base.BaseAct;
import com.example.liuj.liujdemo.tools.LogUtils;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by liuj on 2017/11/16.
 */
public class HandlerThreadActivity extends BaseAct {
    /**
     * 图片地址集合
     */
    private String url[] = {
            "http://img.blog.csdn.net/20160903083245762",
            "http://img.blog.csdn.net/20160903083252184",
            "http://img.blog.csdn.net/20160903083257871",
            "http://img.blog.csdn.net/20160903083257871",
            "http://img.blog.csdn.net/20160903083311972",
            "http://img.blog.csdn.net/20160903083319668",
            "http://img.blog.csdn.net/20160903083326871"
    };
    @BindView(R.id.image)
    ImageView imageView;
    private Handler mUIHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            LogUtils.i("次数:" + msg.what);
            ImageModel model = (ImageModel) msg.obj;
            imageView.setImageBitmap(model.bitmap);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.handler_thread_act);
        ButterKnife.bind(this);

        //创建异步HandlerThread
        HandlerThread handlerThread = new HandlerThread("downloadImage");
        //必须先开启线程
        handlerThread.start();
        //子线程Handler
        Handler childHandler = new Handler(handlerThread.getLooper(), new ChildCallback());
        for (int i = 0; i < 7; i++) {
            //每个1秒去更新图片
            childHandler.sendEmptyMessageDelayed(i, 1000 * i);
        }
    }

    /**
     * 该callback运行于子线程
     */
    class ChildCallback implements Handler.Callback {
        @Override
        public boolean handleMessage(Message msg) {
            //在子线程中进行网络请求
            Bitmap bitmap = downloadUrlBitmap(url[msg.what]);
            ImageModel imageModel = new ImageModel();
            imageModel.bitmap = bitmap;
            imageModel.url = url[msg.what];
            Message msg1 = new Message();
            msg1.what = msg.what;
            msg1.obj = imageModel;
            //通知主线程去更新UI
            mUIHandler.sendMessage(msg1);
            return false;
        }
    }

    private Bitmap downloadUrlBitmap(String urlString) {
        HttpURLConnection urlConnection = null;
        BufferedInputStream in = null;
        Bitmap bitmap = null;
        try {
            final URL url = new URL(urlString);
            urlConnection = (HttpURLConnection) url.openConnection();
            in = new BufferedInputStream(urlConnection.getInputStream(), 8 * 1024);
            bitmap = BitmapFactory.decodeStream(in);
        } catch (final IOException e) {
            e.printStackTrace();
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            try {
                if (in != null) {
                    in.close();
                }
            } catch (final IOException e) {
                e.printStackTrace();
            }
        }
        return bitmap;
    }

    public class ImageModel {
        public String url;
        public Bitmap bitmap;
    }
}