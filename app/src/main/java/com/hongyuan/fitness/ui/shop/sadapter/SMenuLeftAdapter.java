package com.hongyuan.fitness.ui.shop.sadapter;

import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.BaseBean;
import com.hongyuan.fitness.ui.shop.sbeans.FirstCategoryBeans;

public class SMenuLeftAdapter extends BaseQuickAdapter<FirstCategoryBeans.DataBean.ListBean, BaseViewHolder> {

    public SMenuLeftAdapter(){
        super(R.layout.item_shop_menu_left);
    }

    @Override
    protected void convert(BaseViewHolder helper, FirstCategoryBeans.DataBean.ListBean item) {

        helper.setText(R.id.name,item.getCategory_name());

        if(item.isSelect()){
            helper.setBackgroundColor(R.id.box,mContext.getResources().getColor(R.color.color_FFE8EAF5));
            helper.getView(R.id.line).setVisibility(View.VISIBLE);
            helper.setTextColor(R.id.name,mContext.getResources().getColor(R.color.color_FF333333));
        }else{
            helper.setBackgroundColor(R.id.box,mContext.getResources().getColor(R.color.color_F5F6FB));
            helper.getView(R.id.line).setVisibility(View.GONE);
            helper.setTextColor(R.id.name,mContext.getResources().getColor(R.color.color_777777));
        }

        helper.addOnClickListener(R.id.box);
    }
}
