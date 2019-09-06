package com.hongyuan.fitness.custom_view.select_address;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hongyuan.fitness.R;

public class SelectAddressRightAdapter extends BaseQuickAdapter<SelectAddressLeftBeans.DataBean, BaseViewHolder> {

    public SelectAddressRightAdapter(){
        super(R.layout.item_address_right);
    }

    @Override
    protected void convert(BaseViewHolder helper, SelectAddressLeftBeans.DataBean item) {
        if(item.isSelect()){
            helper.setImageResource(R.id.statusImg,R.mipmap.filter_select_img)
                    .setText(R.id.showText,item.getRegion_name());
        }else{
            helper.setImageResource(R.id.statusImg,R.mipmap.filter_no_select)
                    .setText(R.id.showText,item.getRegion_name());
        }
        helper.addOnClickListener(R.id.itemBox);
    }

}
