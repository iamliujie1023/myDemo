package com.example.liuj.liujdemo.module.service.process_binderpool.binder;

import android.os.IBinder;
import android.os.RemoteException;

import com.example.liuj.liujdemo.module.service.process_binderpool.IAnimal;

/**
 * Created by liuj on 2018/1/15.
 */
public class AnimalBinderFac extends IAnimal.Stub {

    public static final int NO_ANIMAL = 0;
    public static final int ANIMAL_CODE_BIRD = 1;
    public static final int ANIMAL_CODE_FISH = 2;
    public static final int ANIMAL_CODE_TAIDI = 3;

    @Override
    public IBinder queryAnimal(int animalCode) throws RemoteException {
        IBinder binder = null;
        switch (animalCode) {
            case ANIMAL_CODE_BIRD:
                binder = new BirdBinder();
                break;
            case ANIMAL_CODE_FISH:
                binder = new FishBinder();
                break;
            case ANIMAL_CODE_TAIDI:
                binder = new TaiDiBinder();
                break;
            default:
                break;
        }
        return binder;
    }


}