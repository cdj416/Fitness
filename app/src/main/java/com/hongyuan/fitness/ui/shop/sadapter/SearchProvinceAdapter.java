package com.hongyuan.fitness.ui.shop.sadapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hongyuan.fitness.R;
import com.hongyuan.fitness.custom_view.time_selecter.get_address.JsonFileBean;

public class SearchProvinceAdapter extends BaseQuickAdapter<JsonFileBean.DataBean, BaseViewHolder> {

    public SearchProvinceAdapter(){
        super(R.layout.item_sereach_province);
    }

    @Override
    protected void convert(BaseViewHolder helper, JsonFileBean.DataBean item) {
        helper.setText(R.id.proText,item.getRegion_name())
                .addOnClickListener(R.id.proText);

        if(item.isSelect()){
            helper.setBackgroundColor(R.id.proText,mContext.getResources().getColor(R.color.color_EF5B48))
                    .setTextColor(R.id.proText,mContext.getResources().getColor(R.color.color_FFFFFF));
        }else{
            helper.setBackgroundColor(R.id.proText,mContext.getResources().getColor(R.color.color_f2))
                    .setTextColor(R.id.proText,mContext.getResources().getColor(R.color.color_FF333333));
        }
    }
}
