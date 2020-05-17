package com.hongyuan.fitness.ui.main;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hongyuan.fitness.R;

public class AllCitysAdapter extends BaseQuickAdapter<OpenCitysBeans.DataBean.ListBean, BaseViewHolder> {

    public AllCitysAdapter(){
        super(R.layout.item_all_citys);
    }
    @Override
    protected void convert(BaseViewHolder helper, OpenCitysBeans.DataBean.ListBean item) {
        helper.setText(R.id.cityName,item.getRegion_name());

        helper.addOnClickListener(R.id.cityName);
    }
}
