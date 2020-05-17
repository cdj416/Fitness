package com.hongyuan.fitness.ui.main;

import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hongyuan.fitness.base.Constants;
import com.hongyuan.fitness.base.Controller;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.base.CustomViewModel;
import com.hongyuan.fitness.databinding.AcitivityAllCitysBinding;
import com.hongyuan.fitness.util.LocationBean;

public class AllCitysViwModel extends CustomViewModel {

    private AcitivityAllCitysBinding binding;

    private AllCitysAdapter adapter;

    private OpenCitysBeans.DataBean dataBean;

    public AllCitysViwModel(CustomActivity mActivity, AcitivityAllCitysBinding binding) {
        super(mActivity);
        this.binding = binding;

        initView();
        lazyLoad();
    }

    @Override
    protected void initView() {
        binding.locationCity.setText(LocationBean.getInstance().getCityName());

        LinearLayoutManager manager = new LinearLayoutManager(mActivity);
        manager.setOrientation(RecyclerView.VERTICAL);
        binding.mRec.setLayoutManager(manager);
        adapter = new AllCitysAdapter();
        binding.mRec.setAdapter(adapter);
        adapter.setFooterView(mActivity.getFooterHeight(binding.mRec));

        adapter.setOnItemChildClickListener((adapter, view, position) -> {
            LocationBean.getInstance().setCityName(dataBean.getList().get(position).getRegion_name());
            mActivity.finish();
        });
    }

    @Override
    protected void lazyLoad() {
        mActivity.showLoading();
        clearParams();
        Controller.myRequest(Constants.GET_OPEN_CITYS, Controller.TYPE_POST,getParams(), OpenCitysBeans.class,this);
    }

    @Override
    public void onSuccess(Object data) {
        mActivity.closeLoading();

        if(data instanceof OpenCitysBeans){
            dataBean = ((OpenCitysBeans)data).getData();
            adapter.setNewData(dataBean.getList());
        }
    }
}
