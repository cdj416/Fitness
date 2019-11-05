package com.hongyuan.fitness.ui.heat.add_food;


import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hongyuan.fitness.R;
import com.hongyuan.fitness.ui.heat.HeatBean;
import com.makeramen.roundedimageview.RoundedImageView;

public class AddFoodAdapter extends BaseQuickAdapter<AddFoodBean.DataBean.ListBean, BaseViewHolder> {

    public AddFoodAdapter() {
        super(R.layout.item_heat_child);
    }

    @Override
    protected void convert(BaseViewHolder helper, AddFoodBean.DataBean.ListBean item) {

        RequestOptions options = new RequestOptions().placeholder(R.mipmap.zhengfangxing_default_img).error(R.mipmap.zhengfangxing_default_img).centerCrop();
        Glide.with(mContext).load(item.getF_img()).apply(options).into((RoundedImageView)helper.getView(R.id.footImg));

        helper.setText(R.id.footName,item.getF_name()).setText(R.id.heatNum,((int)(Float.valueOf(item.getF_cal())*100))+"千卡");
        if(item.getF_type() == 1){
            helper.setText(R.id.quantity,100+"毫升");
        }else{
            helper.setText(R.id.quantity,100+"克");
        }
        helper.addOnClickListener(R.id.itemBox);
    }
}
