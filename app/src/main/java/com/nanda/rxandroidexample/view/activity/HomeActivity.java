package com.nanda.rxandroidexample.view.activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;

import com.nanda.rxandroidexample.R;
import com.nanda.rxandroidexample.app.RxExampleApplication;
import com.nanda.rxandroidexample.databinding.HomeActivityBinding;
import com.nanda.rxandroidexample.models.entity.ActivityListItem;
import com.nanda.rxandroidexample.utils.MethodUtils;
import com.nanda.rxandroidexample.view.adapter.SimpleListAdapter;
import com.nanda.rxandroidexample.view.baseclass.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Observer;
import rx.functions.Func1;

public class HomeActivity extends BaseActivity implements AdapterView.OnItemClickListener {

    private HomeActivityBinding mHomeActivityBinding;
    private SimpleListAdapter mAdapter;
    private ImageView mNoData;
    private List<ActivityListItem> itemList = new ArrayList<>();
    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mHomeActivityBinding = DataBindingUtil.setContentView(this, R.layout.home_activity);
        mRecyclerView = mHomeActivityBinding.recyclerview;
        mAdapter = new SimpleListAdapter(this);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setAdapter(mAdapter);
        setObservableAdapter();
        mAdapter.setOnItemClickListener(this);

    }

    private void setObservableAdapter() {
        Observable.just(itemList)
                .map(new Func1<List<ActivityListItem>, List<ActivityListItem>>() {
                    @Override
                    public List<ActivityListItem> call(List<ActivityListItem> itemList) {
                        return getActivityList(itemList);
                    }
                })
                .subscribe(new Observer<List<ActivityListItem>>() {
                    @Override
                    public void onCompleted() {
                        MethodUtils.showToast(HomeActivity.this, "Completed");
                    }

                    @Override
                    public void onError(Throwable e) {
                        MethodUtils.showToast(HomeActivity.this, e.getMessage());
                    }

                    @Override
                    public void onNext(List<ActivityListItem> itemList) {
                        mAdapter.setListData(itemList);
                    }
                });

    }

    private List<ActivityListItem> getActivityList(List<ActivityListItem> itemList) {
        itemList.clear();
        itemList.add(new ActivityListItem(ViewClickActivity.class, "Button Activity One"));
        itemList.add(new ActivityListItem(ViewClickActivity.class, "Button Activity Two"));
        itemList.add(new ActivityListItem(ViewClickActivity.class, "Button Activity Three"));
        itemList.add(new ActivityListItem(ViewByNetworkCallActivity.class, "View By Network Call"));
        itemList.add(new ActivityListItem(ListByNwCall.class, "List By Network Call"));

        return itemList;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        startActivity(new Intent(this, itemList.get(position).getActivityClass()));
    }
}
