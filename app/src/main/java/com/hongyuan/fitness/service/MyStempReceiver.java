package com.hongyuan.fitness.service;

import android.content.Context;
import android.content.Intent;

import com.hongyuan.fitness.base.MyApplication;
import com.hongyuan.fitness.ui.out_door.RunActivity;
import com.today.step.lib.BaseClickBroadcast;

public class MyStempReceiver extends BaseClickBroadcast {
    private static final String TAG = "MyStempReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        MyApplication tsApplication = (MyApplication) context.getApplicationContext();
        if (!tsApplication.isForeground()) {
            Intent mainIntent = new Intent(context, RunActivity.class);
            mainIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(mainIntent);
        }
    }
}
