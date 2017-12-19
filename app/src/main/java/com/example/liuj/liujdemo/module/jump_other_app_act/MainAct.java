package com.example.liuj.liujdemo.module.jump_other_app_act;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.liuj.R;
import com.example.liuj.liujdemo.base.BaseAct;
import com.example.liuj.sdk.ToastHelper;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by liuj on 2017/12/19.
 */
public class MainAct extends BaseAct implements View.OnClickListener {

    @BindView(R.id.button1)
    Button mButton1;

    @BindView(R.id.button2)
    Button mButton2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.jump_other_app_act);
        ButterKnife.bind(this);

        initView();
    }

    private void initView() {
        mButton1.setOnClickListener(this);
        mButton2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.button1) {
            Intent intent = new Intent(Intent.ACTION_MAIN);
            //知道要跳转应用的包名、类名
            ComponentName componentName = new ComponentName("com.example.liuj.router", "com.example.liuj.router.MainActivity");
            intent.setComponent(componentName);
            Bundle bundle = new Bundle();
            bundle.putString("HELLO", "HELLO_ROUTER");
            intent.putExtras(bundle);
            startActivity(intent);
        } else if (id == R.id.button2) {
            try {
                Intent intent = new Intent();
                ComponentName componentName = new ComponentName("com.example.liuj.router", "com.example.liuj.router.ActA");
                intent.setComponent(componentName);
                startActivity(intent);
            } catch (Exception e) {
                e.printStackTrace();
                ToastHelper.toastLong(this, e.toString());
            }
        }

    }


}