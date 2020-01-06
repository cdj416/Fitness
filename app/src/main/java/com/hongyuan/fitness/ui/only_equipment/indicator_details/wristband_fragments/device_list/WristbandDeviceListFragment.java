package com.hongyuan.fitness.ui.only_equipment.indicator_details.wristband_fragments.device_list;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.base.CustomFragment;
import com.hongyuan.fitness.base.SingleClick;
import com.hongyuan.fitness.ui.only_equipment.indicator_details.wristband_fragments.WristbandStautsUtils;
import com.yolanda.health.qnblesdk.out.QNBleDevice;

import java.util.ArrayList;
import java.util.List;

public class WristbandDeviceListFragment extends CustomFragment {
    private RecyclerView mRecycler;
    private TextView button;
    private WristbandDeviceListAdapter adapter;

    //数据集
    private List<QNBleDevice> mList;
    //扫描连接绑定工具类容
    private WristbandStautsUtils stautsUtils;
    @Override
    public int getLayoutId() {
        return R.layout.fragment_recyclerview;
    }

    @Override
    public int getBottomLayoutId() {
        return R.layout.view_bottom_one;
    }

    @Override
    public void initView(View mView) {
        setEnableOverScrollDrag(true);
        stautsUtils = WristbandStautsUtils.getInstance(mActivity);

        mRecycler = mView.findViewById(R.id.mRecycler);
        button = mView.findViewById(R.id.button);

        LinearLayoutManager manager = new LinearLayoutManager(mActivity);
        manager.setOrientation(RecyclerView.VERTICAL);
        mRecycler.setLayoutManager(manager);
        adapter = new WristbandDeviceListAdapter();
        mRecycler.setAdapter(adapter);
        adapter.setOnItemChildClickListener((adapter, view, position) -> {
            //链接设备
            stautsUtils.goConnect(mList.get(position));
        });


        button.setOnClickListener(new View.OnClickListener() {
            @SingleClick(5000)
            @Override
            public void onClick(View v) {
                mList.clear();
                adapter.notifyDataSetChanged();
                //扫描设备
                stautsUtils.goSearch();
            }
        });
    }

    /*
    * 刷新数据
    * */
    public void changeData(QNBleDevice addDevice){
        if(mList == null){
            mList = new ArrayList<>();
        }
        mList.add(addDevice);
        if(adapter != null){
            adapter.setNewData(mList);
        }
    }

    public List<QNBleDevice> getmList() {
        return mList;
    }

    @Override
    public void onSuccess(Object data) {

    }
}
