package com.nanda.rxandroidexample.utils;

import android.content.Context;
import android.widget.Toast;

import com.nanda.rxandroidexample.models.entity.ActivityListItem;
import com.nanda.rxandroidexample.view.activity.ViewClickActivity;

import java.util.List;

public class MethodUtils {

    public static void showToast(Context context, String input) {
        Toast.makeText(context, input, Toast.LENGTH_SHORT).show();
    }

}
