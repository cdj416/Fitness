package com.hongyuan.fitness.ui.heat.heat_detail;


import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hongyuan.fitness.R;
import com.hongyuan.fitness.util.BaseUtil;

public class HeatDetailAdapter extends BaseQuickAdapter<HeatDetailBean.DataBean.FuBean, BaseViewHolder> {

    private double f_cal;
    private String unit;
    public void setBili(double f_cal,int f_type) {
        if(f_type == 1){
            unit = "毫升";
        }else{
            unit = "克";
        }
        this.f_cal = f_cal;
    }
    public HeatDetailAdapter() {
        super(R.layout.item_heat_detail);
    }

    @Override
    protected void convert(BaseViewHolder helper, HeatDetailBean.DataBean.FuBean item) {

        helper.setText(R.id.fuName,item.getFu_name())
                .setText(R.id.fuCal, BaseUtil.getNoZoon(Double.valueOf(item.getFu_num())*f_cal)+"千卡")
                .setText(R.id.fuNum,item.getFu_num()+unit+"，可食用部分"+item.getFu_num()+unit);


    }
}
