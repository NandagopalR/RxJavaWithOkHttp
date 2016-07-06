package com.nanda.rxandroidexample.view.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;

import com.nanda.rxandroidexample.R;
import com.nanda.rxandroidexample.databinding.ViewClickActivityBinding;
import com.nanda.rxandroidexample.view.baseclass.BaseActivity;

import rx.Observer;
import rx.subjects.PublishSubject;

public class ViewClickActivity extends BaseActivity implements View.OnClickListener {


    private ViewClickActivityBinding mHomeBinding;
    private int mCount = 0;
    private PublishSubject<Integer> mPublishSubject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mHomeBinding = DataBindingUtil.setContentView(this, R.layout.view_click_activity);

        if (mCount == 0)
            mHomeBinding.count.setText("No Values");

        setDataEmitting();

        mHomeBinding.increaseButton.setOnClickListener(this);
        mHomeBinding.decreaseButton.setOnClickListener(this);
    }

    private void onIncrementClick() {
        mCount++;
        mPublishSubject.onNext(mCount);
    }

    private void onDecrementClick() {
        mCount--;
        mPublishSubject.onNext(mCount);
    }

    private void setDataEmitting() {
        mPublishSubject = PublishSubject.create();
        mPublishSubject.subscribe(new Observer<Integer>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Integer integer) {
                mHomeBinding.count.setText(String.valueOf(integer));
            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.increase_button:
                onIncrementClick();
                break;
            case R.id.decrease_button:
                onDecrementClick();
                break;
        }
    }
}
