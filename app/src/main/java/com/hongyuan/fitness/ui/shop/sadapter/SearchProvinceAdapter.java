package com.hongyuan.fitness.ui.shop.sadapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hongyuan.fitness.R;
import com.hongyuan.fitness.custom_view.time_selecter.get_address.JsonFileBean;
import com.hongyuan.fitness.util.SkinConstants;

public class SearchProvinceAdapter extends BaseQuickAdapter<JsonFileBean.DataBean, BaseViewHolder> {

    private String skin;
    public SearchProvinceAdapter(String skin){
        super(R.layout.item_sereach_province);
        this.skin = skin;
    }

    @Override
    protected void convert(BaseViewHolder helper, JsonFileBean.DataBean item) {
        helper.setText(R.id.proText,item.getRegion_name())
                .addOnClickListener(R.id.proText);

        if(item.isSelect()){
            helper.setBackgroundColor(R.id.proText,mContext.getResources().getColor(R.color.color_EF5B48))
                    .setTextColor(R.id.proText,mContext.getResources().getColor(R.color.color_FFFFFF));
        }else{

            if(SkinConstants.SKIN_NAME.DEFAULT.equals(skin)){
                helper.setBackgroundColor(R.id.proText,mContext.getResources().getColor(R.color.color_f2))
                        .setTextColor(R.id.proText,mContext.getResources().getColor(R.color.color_FF333333));
            }
            if(SkinConstants.SKIN_NAME.BLACK.equals(skin)){
                helper.setBackgroundColor(R.id.proText,mContext.getResources().getColor(R.color.color_f2_black))
                        .setTextColor(R.id.proText,mContext.getResources().getColor(R.color.color_FFFFFF));
            }
        }
    }
}
