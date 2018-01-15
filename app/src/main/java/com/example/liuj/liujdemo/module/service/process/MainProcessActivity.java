package com.example.liuj.liujdemo.module.service.process;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.liuj.R;
import com.example.liuj.liujdemo.base.BaseActivity;
import com.example.liuj.liujdemo.module.service.ILiujAidlInterface;
import com.example.liuj.liujdemo.module.service.ILiujCallback;
import com.example.liuj.sdk.LogUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by liuj on 2018/1/15.
 */
public class MainProcessActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.button1)
    Button btn_bind;
    @BindView(R.id.button2)
    Button btn_get;
    @BindView(R.id.tv_1)
    TextView tv;

    public ILiujAidlInterface mService;

    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            mService = ILiujAidlInterface.Stub.asInterface(iBinder);
            try {
                if(null != mService) {
                    mService.registerCallback(mCallback);
                }
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {

        }
    };

    private ILiujCallback.Stub mCallback = new ILiujCallback.Stub() {
        @Override
        public void onRespond(String str) throws RemoteException {
            LogUtils.i("receive msg from Service: " + str);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_processservice_act);
        ButterKnife.bind(this);

        btn_bind.setOnClickListener(this);
        btn_get.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == btn_bind) {
            Intent intent = new Intent(MainProcessActivity.this, MyProcessService.class);
            bindService(intent, mConnection, BIND_AUTO_CREATE);
        } else if (v == btn_get) {
            try {
                mService.testMethod("hi, " + MyProcessService.class.getSimpleName());
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
//        if (serviceConnection != null) {
//            unbindService(connection);
//        }

        try {
            mService.unRegisterCallback(mCallback);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }


}