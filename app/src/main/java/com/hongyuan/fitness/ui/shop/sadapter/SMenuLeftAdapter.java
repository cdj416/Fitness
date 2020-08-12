package com.hongyuan.fitness.ui.shop.sadapter;

import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.BaseBean;
import com.hongyuan.fitness.ui.shop.sbeans.FirstCategoryBeans;
import com.hongyuan.fitness.util.SkinConstants;

public class SMenuLeftAdapter extends BaseQuickAdapter<FirstCategoryBeans.DataBean.ListBean, BaseViewHolder> {

    private String skin;
    public SMenuLeftAdapter(String skin){
        super(R.layout.item_shop_menu_left);
        this.skin = skin;
    }

    @Override
    protected void convert(BaseViewHolder helper, FirstCategoryBeans.DataBean.ListBean item) {

        helper.setText(R.id.name,item.getCategory_name());

        if(item.isSelect()){
            helper.getView(R.id.line).setVisibility(View.VISIBLE);

            if(SkinConstants.SKIN_NAME.DEFAULT.equals(skin)){
                helper.setBackgroundColor(R.id.box,mContext.getResources().getColor(R.color.color_FFE8EAF5));
                helper.setTextColor(R.id.name,mContext.getResources().getColor(R.color.color_FF333333));
            }
            if(SkinConstants.SKIN_NAME.BLACK.equals(skin)){
                helper.setBackgroundColor(R.id.box,mContext.getResources().getColor(R.color.color_FF333333));
                helper.setTextColor(R.id.name,mContext.getResources().getColor(R.color.color_EF5B48));
            }
        }else{
            helper.getView(R.id.line).setVisibility(View.GONE);

            if(SkinConstants.SKIN_NAME.DEFAULT.equals(skin)){
                helper.setBackgroundColor(R.id.box,mContext.getResources().getColor(R.color.color_F5F6FB));
                helper.setTextColor(R.id.name,mContext.getResources().getColor(R.color.color_777777));
            }
            if(SkinConstants.SKIN_NAME.BLACK.equals(skin)){
                helper.setBackgroundColor(R.id.box,mContext.getResources().getColor(R.color.theme_background_black));
                helper.setTextColor(R.id.name,mContext.getResources().getColor(R.color.color_FFFFFF));
            }
        }

        helper.addOnClickListener(R.id.box);
    }
}
