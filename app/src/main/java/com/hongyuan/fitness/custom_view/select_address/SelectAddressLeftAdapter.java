package com.hongyuan.fitness.custom_view.select_address;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hongyuan.fitness.R;

public class SelectAddressLeftAdapter extends BaseQuickAdapter<SelectAddressLeftBeans.DataBean, BaseViewHolder> {

    public SelectAddressLeftAdapter(){
        super(R.layout.item_address_left);
    }
    @Override
    protected void convert(BaseViewHolder helper, SelectAddressLeftBeans.DataBean item) {
        if(item.isSelect()){
            helper.setText(R.id.provinceName,item.getRegion_name())
                    .setTextColor(R.id.provinceName,mContext.getResources().getColor(R.color.color_EF5B48))
                    .setBackgroundColor(R.id.provinceName,mContext.getResources().getColor(R.color.color_FFFFFF));
        }else{
            helper.setText(R.id.provinceName,item.getRegion_name())
                    .setTextColor(R.id.provinceName,mContext.getResources().getColor(R.color.color_FF333333))
                    .setBackgroundColor(R.id.provinceName,mContext.getResources().getColor(R.color.color_F5F6FB));
        }
        helper.addOnClickListener(R.id.provinceName);
    }

}
