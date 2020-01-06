package com.hongyuan.fitness.ui.only_equipment.smart_historical_data;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hongyuan.fitness.R;
import com.hongyuan.fitness.util.TimeUtil;

public class SmartHistoricalDataAdapter extends BaseQuickAdapter<SmartBodyFatBeans.DataBean.ListBean, BaseViewHolder> {

    public SmartHistoricalDataAdapter(){
        super(R.layout.item_smart_historical_data);
    }
    @Override
    protected void convert(BaseViewHolder helper, SmartBodyFatBeans.DataBean.ListBean item) {
        helper.setText(R.id.dataText, TimeUtil.getStringByFormat((item.getAdd_time()*1000),TimeUtil.dateFormatYMD))
                .setText(R.id.weightNum,item.getWeight())
                .setText(R.id.bodyFatRate,item.getBody_fat_per());

        if(helper.getPosition() == 0){
            helper.setVisible(R.id.topLine,false);
        }else{
            helper.setVisible(R.id.topLine,true);
        }
        if(helper.getPosition() == (getData().size() - 1)){
            helper.setVisible(R.id.bottomLine,false);
        }else{
            helper.setVisible(R.id.bottomLine,true);
        }


        helper.addOnClickListener(R.id.delete);
    }
}
