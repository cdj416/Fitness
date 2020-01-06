package com.hongyuan.fitness.service;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class BluetoothStateBroadcastReceive extends BroadcastReceiver {

    public interface BluetoothStateListener{
        void bluetoothState(int code);
    }

    private BluetoothStateListener stateListener;

    public void setStateListener(BluetoothStateListener stateListener){
        this.stateListener = stateListener;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
        switch (action){
            case BluetoothDevice.ACTION_ACL_CONNECTED://设备已连接
                break;
            case BluetoothDevice.ACTION_ACL_DISCONNECTED://设备已断开
                break;
            case BluetoothAdapter.ACTION_STATE_CHANGED:
                int blueState = intent.getIntExtra(BluetoothAdapter.EXTRA_STATE, 0);
                switch (blueState){
                    case BluetoothAdapter.STATE_OFF://蓝牙已关闭
                        if(stateListener != null){
                            stateListener.bluetoothState(BluetoothAdapter.STATE_OFF);
                        }
                        break;
                    case BluetoothAdapter.STATE_ON://蓝牙已打开
                        if(stateListener != null){
                            stateListener.bluetoothState(BluetoothAdapter.STATE_ON);
                        }
                        break;
                }
                break;
        }
    }
}
