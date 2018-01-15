package com.example.liuj.liujdemo.module.service.process_binderpool;

import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

import com.example.liuj.R;
import com.example.liuj.liujdemo.base.BaseActivity;
import com.example.liuj.liujdemo.module.service.process_binderpool.binder.AnimalBinderFac;
import com.example.liuj.liujdemo.module.service.process_binderpool.binder.BinderPool;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by liuj on 2018/1/15.
 */
public class BinderPoolAct extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.button1)
    Button mBtnAll;
    @BindView(R.id.button2)
    Button mBtnFish;
    @BindView(R.id.button3)
    Button mBtnBird;
    @BindView(R.id.button4)
    Button mBtnTaidi;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_processservice_binderpool_act);
        ButterKnife.bind(this);

        mBtnAll.setOnClickListener(this);
        mBtnFish.setOnClickListener(this);
        mBtnBird.setOnClickListener(this);
        mBtnTaidi.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == mBtnAll) {
            allAnimall();
        } else if (v == mBtnBird) {
            bird();
        } else if (v == mBtnFish) {
            fish();
        } else if (v == mBtnTaidi) {
            taidi();
        }
    }

    void allAnimall() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                IBinder fishBinder = BinderPool.getInstance(BinderPoolAct.this).queryAnimal(AnimalBinderFac.ANIMAL_CODE_FISH);
                IFish fish = IFish.Stub.asInterface(fishBinder);

                IBinder birdBinder = BinderPool.getInstance(BinderPoolAct.this).queryAnimal(AnimalBinderFac.ANIMAL_CODE_BIRD);
                IBird bird = IBird.Stub.asInterface(birdBinder);

                IBinder monkeyBinder = BinderPool.getInstance(BinderPoolAct.this).queryAnimal(AnimalBinderFac.ANIMAL_CODE_TAIDI);
                ITaiDi taidi = ITaiDi.Stub.asInterface(monkeyBinder);

                try {
                    taidi.fuckAir();
                    bird.fly();
                    fish.swim();
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    void fish() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                IBinder fishBinder = BinderPool.getInstance(BinderPoolAct.this).queryAnimal(AnimalBinderFac.ANIMAL_CODE_FISH);
                IFish fish = IFish.Stub.asInterface(fishBinder);
                try {
                    fish.swim();
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    void bird() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                IBinder birdBinder = BinderPool.getInstance(BinderPoolAct.this).queryAnimal(AnimalBinderFac.ANIMAL_CODE_BIRD);
                IBird bird = IBird.Stub.asInterface(birdBinder);
                try {
                    bird.fly();
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }

    void taidi() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                IBinder monkeyBinder = BinderPool.getInstance(BinderPoolAct.this).queryAnimal(AnimalBinderFac.ANIMAL_CODE_TAIDI);
                ITaiDi taidi = ITaiDi.Stub.asInterface(monkeyBinder);
                try {
                    taidi.fuckAir();
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }


}