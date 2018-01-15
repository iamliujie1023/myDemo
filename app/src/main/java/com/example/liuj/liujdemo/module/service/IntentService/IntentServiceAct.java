package com.example.liuj.liujdemo.module.service.IntentService;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.example.liuj.R;
import com.example.liuj.liujdemo.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by liuj on 2018/1/14.
 */
public class IntentServiceAct extends BaseActivity {

    public static final String RESULT = "com.liuj.intent_service.result";

    @BindView(R.id.tv_1)
    TextView mTv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.intent_service_demo_act);
        ButterKnife.bind(this);

        mTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DemoIntentService.startService(IntentServiceAct.this, "tag_1");
                DemoIntentService.startService(IntentServiceAct.this, "tag_2");
                DemoIntentService.startService(IntentServiceAct.this, "tag_3");

            }
        });

    }

}
