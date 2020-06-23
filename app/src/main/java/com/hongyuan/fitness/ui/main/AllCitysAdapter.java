package com.hongyuan.fitness.ui.main;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.hongyuan.fitness.ui.main.AllCitysBeans.DataBean.HeardBeans;
import com.hongyuan.fitness.ui.main.AllCitysBeans.DataBean.ListBean;
import com.hongyuan.fitness.R;

import java.util.ArrayList;
import java.util.HashMap;

public class AllCitysAdapter extends BaseMultiItemQuickAdapter<MultiItemEntity, BaseViewHolder> {

    public static final int TYPE_ONE = 0;
    public static final int TYPE_TWO = 1;

    public AllCitysAdapter(){
        super(new ArrayList<>());
        addItemType(TYPE_ONE, R.layout.item_citys_title);
        addItemType(TYPE_TWO, R.layout.item_all_citys);
    }

    @Override
    protected void convert(BaseViewHolder helper, MultiItemEntity item) {
        switch (helper.getItemViewType()) {
            case TYPE_ONE:
                HeardBeans oneBean = (HeardBeans) item;
                helper.setText(R.id.cityName,oneBean.getTitle());
                break;
            case TYPE_TWO:
                ListBean twoBean = (ListBean) item;
                helper.setText(R.id.cityName,twoBean.getRegion_name());

                helper.addOnClickListener(R.id.cityName);
                break;
        }
    }
}
