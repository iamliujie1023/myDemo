package com.example.liuj.liujdemo.module.dialogfragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

import com.example.liuj.R;
import com.example.liuj.liujdemo.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainAct extends BaseActivity {

    @BindView(R.id.button1)
    Button mButton;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialogfragment_main_act);
        ButterKnife.bind(this);

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragmentDemo1 dialogFragmentDemo1 = new DialogFragmentDemo1();
                dialogFragmentDemo1.show(getFragmentManager(), "A");
            }
        });

    }
}
