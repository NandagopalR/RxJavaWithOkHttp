package com.nanda.rxandroidexample.view.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.nanda.rxandroidexample.R;
import com.nanda.rxandroidexample.databinding.ListByNwCallBinding;
import com.nanda.rxandroidexample.view.baseclass.BaseActivity;

public class ListByNwCall extends BaseActivity {

    private ListByNwCallBinding listByNwCallBinding;
    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        listByNwCallBinding = DataBindingUtil.setContentView(this, R.layout.list_by_nw_call);
        mRecyclerView = listByNwCallBinding.recyclerviewNwCall;

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
