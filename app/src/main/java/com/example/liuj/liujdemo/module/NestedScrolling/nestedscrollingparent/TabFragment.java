package com.example.liuj.liujdemo.module.NestedScrolling.nestedscrollingparent;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.liuj.R;
import com.example.liuj.liujdemo.adapter.MutiTypeAdapter;
import com.example.liuj.liujdemo.model.BaseModel;
import com.example.liuj.liujdemo.model.NormalModel;

import java.util.ArrayList;
import java.util.List;

@SuppressLint("NeedExtendsBaseActivityOrBaseFragment")
public class TabFragment extends Fragment {
    public static final String TITLE = "title";
    private String mTitle = "Defaut Value";
    private RecyclerView mRecyclerView;
    private MutiTypeAdapter mAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mTitle = getArguments().getString(TITLE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.nested_scrolling_parent_fragment_tab, container, false);
        mRecyclerView = (RecyclerView) view
                .findViewById(R.id.id_stickynavlayout_innerscrollview);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        List<BaseModel> mData = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            mData.add(new NormalModel("item -> " + (i + 1)));
        }

        mAdapter = new MutiTypeAdapter(getContext());
        mAdapter.reset(mData);

        mRecyclerView.setAdapter(mAdapter);
        return view;
    }

    public static TabFragment newInstance(String title) {
        TabFragment tabFragment = new TabFragment();
        Bundle bundle = new Bundle();
        bundle.putString(TITLE, title);
        tabFragment.setArguments(bundle);
        return tabFragment;
    }


}