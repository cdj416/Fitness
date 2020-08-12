package com.hongyuan.fitness.custom_view.select_address;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hongyuan.fitness.R;
import com.hongyuan.fitness.util.SkinConstants;

public class SelectAddressLeftAdapter extends BaseQuickAdapter<SelectAddressLeftBeans.DataBean, BaseViewHolder> {

    private String skin;

    public SelectAddressLeftAdapter(String skin){
        super(R.layout.item_address_left);
        this.skin = skin;
    }
    @Override
    protected void convert(BaseViewHolder helper, SelectAddressLeftBeans.DataBean item) {
        if(item.isSelect()){
            helper.setText(R.id.provinceName,item.getRegion_name())
                    .setTextColor(R.id.provinceName,mContext.getResources().getColor(R.color.color_EF5B48));
            if(SkinConstants.SKIN_NAME.DEFAULT.equals(skin)){
                helper.setBackgroundColor(R.id.provinceName,mContext.getResources().getColor(R.color.color_FFFFFF));
            }

            if(SkinConstants.SKIN_NAME.BLACK.equals(skin)){
                helper.setBackgroundColor(R.id.provinceName,mContext.getResources().getColor(R.color.theme_color3));
            }

        }else{
            helper.setText(R.id.provinceName,item.getRegion_name());
            if(SkinConstants.SKIN_NAME.DEFAULT.equals(skin)){
                helper.setBackgroundColor(R.id.provinceName,mContext.getResources().getColor(R.color.color_FFFFFF))
                        .setTextColor(R.id.provinceName,mContext.getResources().getColor(R.color.color_FF333333));
            }

            if(SkinConstants.SKIN_NAME.BLACK.equals(skin)){
                helper.setBackgroundColor(R.id.provinceName,mContext.getResources().getColor(R.color.theme_background_black))
                        .setTextColor(R.id.provinceName,mContext.getResources().getColor(R.color.color_FFFFFF));
            }
        }
        helper.addOnClickListener(R.id.provinceName);
    }

}
