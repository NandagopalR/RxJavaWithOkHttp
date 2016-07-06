package com.nanda.rxandroidexample.view.activity;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringDef;
import android.util.Log;
import android.widget.TextView;

import com.nanda.rxandroidexample.R;
import com.nanda.rxandroidexample.app.RxExampleApplication;
import com.nanda.rxandroidexample.databinding.ViewByNwCallBinding;
import com.nanda.rxandroidexample.utils.MethodUtils;
import com.nanda.rxandroidexample.utils.RestClient;
import com.nanda.rxandroidexample.view.baseclass.BaseActivity;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.IOException;
import java.util.List;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func0;
import rx.schedulers.Schedulers;

public class ViewByNetworkCallActivity extends BaseActivity {

    private static final String url = "http://api.goounj.com/polls/v1/poll/1";
    private ViewByNwCallBinding viewByNwCallBinding;
    private TextView mView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewByNwCallBinding = DataBindingUtil.setContentView(this, R.layout.view_by_nw_call);
        mView = viewByNwCallBinding.viewName;

//        getDataFromNw(url);
//        getDataWithObserver(url);
        getData(mActivity, url);
    }


    public void getDataFromNw(String url) {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(url)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                MethodUtils.showToast(mActivity, "" + e.getMessage());
            }

            @Override
            public void onResponse(Response response) throws IOException {
                try {
                    if (response.isSuccessful()) {
                        JSONObject jsonObject = new JSONObject(response.body().string());
                        JSONArray jsonObject1 = jsonObject.optJSONArray("questionList");
                        Log.e("TAG", "onResponse: " + jsonObject1.length());
                        getDataAsObservable("" + jsonObject1.length());
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }


    private void getDataAsObservable(String data) {
        Observable.just(data)
                .subscribe(new Observer<String>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(String s) {
                        mView.setText("" + s);
                        MethodUtils.showToast(mActivity, s);
                    }
                });
    }

    private void getDataWithObserver(final String url) {
        Observable.create(new Observable.OnSubscribe<String>() {

            @Override
            public void call(Subscriber<? super String> subscriber) {
                OkHttpClient client = new OkHttpClient();
                try {
                    Response response = client.newCall(new Request.Builder().url(url).build()).execute();
                    if (!response.isSuccessful()) {
                        subscriber.onError(new Exception("error"));
                        return;
                    }
                    subscriber.onNext(response.body().string());
                    subscriber.onCompleted();
                } catch (IOException e) {
                    subscriber.onError(e);
                }
            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray jsonObject1 = jsonObject.optJSONArray("questionList");
                            Log.e("TAG", "onResponse: " + jsonObject1.length());
                            mView.setText("" + jsonObject1.length());
                            MethodUtils.showToast(mActivity, "" + jsonObject1.length());
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

    private void getData(final String url) {
        final OkHttpClient okHttpClient = new OkHttpClient();
        Observable.defer(new Func0<Observable<Response>>() {
            @Override
            public Observable<Response> call() {

                try {
                    Response response = okHttpClient.newCall(new Request.Builder().url(url).build()).execute();
                    return Observable.just(response);
                } catch (IOException e) {
                    Observable.error(e);
                }

                return null;
            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Response>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        MethodUtils.showToast(ViewByNetworkCallActivity.this, "" + e.getMessage());
                    }

                    @Override
                    public void onNext(Response response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response.body().string());
                            JSONArray jsonObject1 = jsonObject.optJSONArray("questionList");
                            Log.e("TAG", "onResponse: " + jsonObject1.length());
                            mView.setText("" + jsonObject1.length());
                            MethodUtils.showToast(mActivity, "" + jsonObject1.length());
                        } catch (JSONException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

    private void getData(Context context, final String url) {
        Observable.fromCallable(new Func0<String>() {
            @Override
            public String call() {
                OkHttpClient okHttpClient = new OkHttpClient();
                try {
                    Response response = okHttpClient.newCall(new Request.Builder().url(url).build()).execute();
                    if (response.isSuccessful()) {
                        JSONObject jsonObject = new JSONObject(response.body().string());
                        JSONArray jsonObject1 = jsonObject.optJSONArray("questionList");
                        return String.valueOf(jsonObject1.length());
                    }
                } catch (JSONException e) {
                    Observable.error(e);
                } catch (IOException e) {
                    Observable.error(e);
                }
                return null;
            }
        })
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(String s) {
                        mView.setText(s);
                        MethodUtils.showToast(mActivity, s);
                    }
                });
    }

    private List<Object> getWorkOrder() {
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }


}
