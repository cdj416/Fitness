package com.hongyuan.fitness.ui.shop.sadapter;

import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hongyuan.fitness.R;
import com.hongyuan.fitness.ui.shop.sbeans.ShopAddressBeans;

public class ShopAddressAdapter extends BaseQuickAdapter<ShopAddressBeans.DataBean, BaseViewHolder> {

    public ShopAddressAdapter(){
        super(R.layout.item_shop_address);
    }

    @Override
    protected void convert(BaseViewHolder helper, ShopAddressBeans.DataBean item) {
        if(item.getIs_default() == 1){
            helper.getView(R.id.defaultMark).setVisibility(View.VISIBLE);
        }else{
            helper.getView(R.id.defaultMark).setVisibility(View.GONE);
        }
        helper.setText(R.id.telNum,item.getTel())
                .setText(R.id.address,item.getProvince()+" "+item.getCity()+" "+item.getDistrict()+" "+item.getAddress());

        helper.addOnClickListener(R.id.goModify).addOnClickListener(R.id.box);
    }
}
