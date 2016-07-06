package com.nanda.rxandroidexample.models.entity;


import com.nanda.rxandroidexample.view.baseclass.BaseActivity;

public class ActivityListItem {

    private Class<? extends BaseActivity> activityClass;
    private String activityName;

    public ActivityListItem(Class<? extends BaseActivity> activityClass, String activityName) {
        this.activityClass = activityClass;
        this.activityName = activityName;
    }

    public Class<? extends BaseActivity> getActivityClass() {
        return activityClass;
    }

    public void setActivityClass(Class<? extends BaseActivity> activityClass) {
        this.activityClass = activityClass;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }
}
