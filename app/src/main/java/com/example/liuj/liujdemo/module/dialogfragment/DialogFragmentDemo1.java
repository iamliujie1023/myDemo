package com.example.liuj.liujdemo.module.dialogfragment;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.liuj.R;

public class DialogFragmentDemo1 extends DialogFragment {

//    public static void showDialog() {
//        DialogFragmentDemo1 demo1 = new DialogFragmentDemo1();
//    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_dialogfragment_demo1, container, false);
        return view;
    }

}