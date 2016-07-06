package com.nanda.rxandroidexample.app;

import android.support.multidex.MultiDexApplication;

import com.nanda.rxandroidexample.network.NetworkCalls;

public class RxExampleApplication extends MultiDexApplication {

    private static RxExampleApplication appInstance;
    private NetworkCalls networkCalls;

    public static RxExampleApplication getInstance() {
        return appInstance;
    }

    public NetworkCalls getNetworkCall() {
        if (networkCalls == null)
            networkCalls = new NetworkCalls();
        return networkCalls;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        appInstance = this;
    }
}
