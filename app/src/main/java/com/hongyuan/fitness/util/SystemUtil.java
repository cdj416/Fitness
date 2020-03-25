package com.hongyuan.fitness.util;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.os.Build;
import android.telephony.TelephonyManager;

import androidx.annotation.RequiresApi;

import java.util.Locale;

/**
 * 系统工具类
 * Created by zhuwentao on 2016-07-18.
 */
public class SystemUtil {

    /**
     * 获取当前手机系统语言。
     *
     * @return 返回当前系统语言。例如：当前设置的是“中文-中国”，则返回“zh-CN”
     */
    public static String getSystemLanguage() {
        return Locale.getDefault().getLanguage();
    }

    /**
     * 获取当前系统上的语言列表(Locale列表)
     *
     * @return  语言列表
     */
    public static Locale[] getSystemLanguageList() {
        return Locale.getAvailableLocales();
    }

    /**
     * 获取当前手机系统版本号
     *
     * @return  系统版本号
     */
    public static String getSystemVersion() {
        return android.os.Build.VERSION.RELEASE;
    }

    /**
     * 获取手机型号
     *
     * @return  手机型号
     */
    public static String getSystemModel() {
        return android.os.Build.MODEL;
    }

    /**
     * 获取手机厂商
     *
     * @return  手机厂商
     */
    public static String getDeviceBrand() {
        return android.os.Build.BRAND;
    }

    /**
     * 获取手机IMEI(需要“android.permission.READ_PHONE_STATE”权限)
     *
     * @return  手机IMEI
     */
    @SuppressLint("MissingPermission")
    public static String getIMEI(Context ctx) {
        TelephonyManager tm = (TelephonyManager) ctx.getSystemService(Activity.TELEPHONY_SERVICE);
        if (tm != null) {
            return tm.getDeviceId();
        }
        return null;
    }

    //检测当前的网络状态
    //API版本23以下时调用此方法进行检测
    //因为API23后getNetworkInfo(int networkType)方法被弃用
    public void checkState_23(Context mContext){
        //步骤1：通过Context.getSystemService(Context.CONNECTIVITY_SERVICE)获得ConnectivityManager对象
        ConnectivityManager connMgr = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);

        //步骤2：获取ConnectivityManager对象对应的NetworkInfo对象
        //NetworkInfo对象包含网络连接的所有信息
        //步骤3：根据需要取出网络连接信息
        //获取WIFI连接的信息
        NetworkInfo networkInfo = connMgr.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        Boolean isWifiConn = networkInfo.isConnected();

        //获取移动数据连接的信息
        networkInfo = connMgr.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        Boolean isMobileConn = networkInfo.isConnected();
    }

    // API 23及以上时调用此方法进行网络的检测
    // getAllNetworks() 在API 21后开始使用
    //步骤非常类似
    public static boolean checkState_23orNew(Context mContext){
        //是否有网络连接
        boolean iscon = false;

        //获得ConnectivityManager对象
        ConnectivityManager connMgr = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);

        //获取所有网络连接的信息
        Network[] networks = connMgr.getAllNetworks();
        //用于存放网络连接信息
        StringBuilder sb = new StringBuilder();
        //通过循环将网络信息逐个取出来
        for (int i=0; i < networks.length; i++){
            //获取ConnectivityManager对象对应的NetworkInfo对象
            NetworkInfo networkInfo = connMgr.getNetworkInfo(networks[i]);

            if(networkInfo.isConnected()){
                iscon = true;
            }
        }

        return iscon;
    }
}
