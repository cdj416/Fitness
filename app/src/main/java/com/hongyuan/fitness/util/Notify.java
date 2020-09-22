package com.hongyuan.fitness.util;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.ConstantsCode;
import com.hongyuan.fitness.ui.shop.sactivity.ShopMessageActivity;

import org.greenrobot.eventbus.EventBus;

public class Notify {

    private NotificationManager manager;
    private Notification.Builder builder;
    private Context context;
    private Notification notification;
    private String TAG = "notify";

    public Notify(Context context){
        this.context = context;
    }

    @SuppressLint("WrongConstant")
    @TargetApi(26)
    public void setNotification(String title, String desc){

        manager = (NotificationManager)context.getSystemService(context.NOTIFICATION_SERVICE);

        // set channel  chanenel的创建
        String CHANNEL_ONE_ID = "com.hongyuan.fitness";
        String CHANNEL_ONE_NAME = "Channel One";
        NotificationChannel notificationChannel;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            notificationChannel = new NotificationChannel(CHANNEL_ONE_ID,
                    CHANNEL_ONE_NAME, NotificationManager.IMPORTANCE_HIGH);
            /*notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.RED);
            notificationChannel.setShowBadge(true);
            notificationChannel.setLockscreenVisibility(Notification.VISIBILITY_PUBLIC);*/
            NotificationManager nm = context.getSystemService(NotificationManager.class);
            nm.createNotificationChannel(notificationChannel);
        }

        EventBus.getDefault().post(ConstantsCode.EB_CHANGE_PERSON,"");

        //Intent intent = new Intent(context, MineMessageActivity.class);
        Intent intent = new Intent(context, ShopMessageActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);

        builder = new Notification.Builder(context).setContentTitle(title)
                .setChannelId(CHANNEL_ONE_ID)
                .setContentText(desc)
                .setContentIntent(pendingIntent)
                .setWhen(System.currentTimeMillis())
                .setAutoCancel(true)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setVibrate(new long[]{0, 1000, 1000, 1000}) //通知栏消息震动
                .setLights(Color.GREEN, 1000, 2000) //通知栏消息闪灯(亮一秒间隔两秒再亮)
                .setDefaults(NotificationCompat.DEFAULT_ALL)
                .setSmallIcon(R.mipmap.ic_launcher);
        Log.i(TAG,"after build a builder");
        //manager.notify(1, builder.getNotification());
        NotificationManagerCompat new_nm = NotificationManagerCompat.from(context);
        new_nm.notify(1, builder.build());  // 第一个参数1具体实现时 需要修改 用于显示不同消息。
        //return builder.getNotification();
    }
}
