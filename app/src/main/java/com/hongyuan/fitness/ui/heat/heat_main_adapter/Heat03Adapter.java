package com.hongyuan.fitness.ui.heat.heat_main_adapter;


import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hongyuan.fitness.R;
import com.hongyuan.fitness.ui.heat.HeatBean;
import com.hongyuan.fitness.util.BaseUtil;
import com.makeramen.roundedimageview.RoundedImageView;

public class Heat03Adapter extends BaseQuickAdapter<HeatBean.DataBean.ListBeanXXXXXX._$3Bean.ListBeanXX, BaseViewHolder> {

    public Heat03Adapter() {
        super(R.layout.item_heat_child);
    }

    @Override
    protected void convert(BaseViewHolder helper, HeatBean.DataBean.ListBeanXXXXXX._$3Bean.ListBeanXX item) {

        RequestOptions options = new RequestOptions().placeholder(R.mipmap.a_test2).error(R.mipmap.a_test2).centerCrop();
        Glide.with(mContext).load(item.getF_img()).apply(options).into((RoundedImageView)helper.getView(R.id.footImg));

        helper.setText(R.id.footName,item.getF_name()).setText(R.id.heatNum,BaseUtil.getNoZoon(item.getFe_cal())+"千卡");
        if(item.getF_type() == 1){
            helper.setText(R.id.quantity,BaseUtil.getNoZoon(item.getFe_num())+"毫升");
        }else{
            helper.setText(R.id.quantity, BaseUtil.getNoZoon(item.getFe_num())+"克");
        }
        helper.addOnClickListener(R.id.itemBox);
    }
}
