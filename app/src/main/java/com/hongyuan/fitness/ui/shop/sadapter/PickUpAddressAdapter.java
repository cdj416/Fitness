package com.hongyuan.fitness.ui.shop.sadapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hongyuan.fitness.R;
import com.hongyuan.fitness.ui.shop.sbeans.PickUpAddress;
import com.hongyuan.fitness.util.BigDecimalUtils;

public class PickUpAddressAdapter extends BaseQuickAdapter<PickUpAddress.DataBean.ListBean, BaseViewHolder> {

    public PickUpAddressAdapter(){
        super(R.layout.item_pickup_address);
    }

    @Override
    protected void convert(BaseViewHolder helper, PickUpAddress.DataBean.ListBean item) {

        helper.setText(R.id.address,item.getPname()+" "
                +item.getCname()+" "
                +item.getDname()+" "
                +item.getAddress())
                .setText(R.id.distance,getDistan(item.getDistance_um()));

        if(item.isSelect()){
            helper.setImageResource(R.id.img,R.mipmap.checkmark_circle);
        }else{
            helper.setImageResource(R.id.img,R.mipmap.yuanquanquan_no_select_img);
        }

        helper.addOnClickListener(R.id.box);
    }

    //距离显示
    private String getDistan(long distan){
        String showDist = "";
        if(distan > 1000){
            showDist = BigDecimalUtils.div(String.valueOf(distan),"1000",2) + "km";
        }else{
            showDist = distan + "m";
        }
        return showDist;
    }
}
