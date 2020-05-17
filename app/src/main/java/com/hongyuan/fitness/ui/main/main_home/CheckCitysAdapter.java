package com.hongyuan.fitness.ui.main.main_home;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hongyuan.fitness.R;
import com.hongyuan.fitness.ui.main.OpenCitysBeans;

public class CheckCitysAdapter extends BaseQuickAdapter<OpenCitysBeans.DataBean.ListBean, BaseViewHolder> {

    public CheckCitysAdapter(){
        super(R.layout.item_check_ctitys);
    }
    @Override
    protected void convert(BaseViewHolder helper, OpenCitysBeans.DataBean.ListBean item) {
        helper.setText(R.id.cityName,item.getRegion_name());
    }
}
