package com.hongyuan.fitness.ui.only_equipment.indicator_details.wristband_fragments.device_list;


import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hongyuan.fitness.R;
import com.yolanda.health.qnblesdk.out.QNBleDevice;

public class WristbandDeviceListAdapter extends BaseQuickAdapter<QNBleDevice, BaseViewHolder> {

    public WristbandDeviceListAdapter() {
        super(R.layout.item_wristband_device_list);
    }

    @Override
    protected void convert(BaseViewHolder helper, QNBleDevice item) {

        helper.setText(R.id.deviceName,item.getName())
                .setText(R.id.deviceMac,item.getMac());

        helper.addOnClickListener(R.id.connection);
    }
}
