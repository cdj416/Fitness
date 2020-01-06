package com.hongyuan.fitness.ui.only_equipment.indicator_details.wristband_fragments.wristband_inface;

import com.yolanda.health.qnblesdk.out.QNBleDevice;

public interface WristbandStatusListener {

    int PROMPT = 0X01;
    int SEREACH_LIST = 0X02;
    int WRISTBAND_DATA = 0X03;

    void statusListener(int code, QNBleDevice qnBleDevice);
}
