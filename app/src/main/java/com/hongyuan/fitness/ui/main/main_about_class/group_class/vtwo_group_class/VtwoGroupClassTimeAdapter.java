package com.hongyuan.fitness.ui.main.main_about_class.group_class.vtwo_group_class;


import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hongyuan.fitness.R;
import com.hongyuan.fitness.ui.about_class.privite_class.pay_order_detail.select_time.TabTimeBean;
import com.hongyuan.fitness.util.TimeUtil;

public class VtwoGroupClassTimeAdapter extends BaseQuickAdapter<TabTimeBean.DataBean, BaseViewHolder> {

    public VtwoGroupClassTimeAdapter(){
        super(R.layout.item_vtwo_group_class_time);
    }
    @Override
    protected void convert(BaseViewHolder helper, TabTimeBean.DataBean item) {

        if(item.isSelect()){
            helper.setVisible(R.id.selectImg,true)
                    .setTextColor(R.id.weekText,mContext.getResources().getColor(R.color.color_FFFFFF))
                    .setTextColor(R.id.date,mContext.getResources().getColor(R.color.color_FFFFFF))
                    .setText(R.id.weekText,getShowWeek(item.getIs_cur_date(),item.getWeek()))
                    .setText(R.id.date, TimeUtil.formatDate(item.getNow_day(),TimeUtil.dateFormatYMD,TimeUtil.dateFormatMD));
        }else{
            helper.setVisible(R.id.selectImg,false)
                    .setTextColor(R.id.weekText,mContext.getResources().getColor(R.color.color_FF999999))
                    .setTextColor(R.id.date,mContext.getResources().getColor(R.color.color_FF999999))
                    .setText(R.id.weekText,getShowWeek(item.getIs_cur_date(),item.getWeek()))
                    .setText(R.id.date, TimeUtil.formatDate(item.getNow_day(),TimeUtil.dateFormatYMD,TimeUtil.dateFormatMD));
        }
        helper.addOnClickListener(R.id.tabBox);

    }

    /*
    * 获取星期数
    * */
    private String getShowWeek(int is_cur_date,String week){
        if(is_cur_date == 1){
            return "今日";
        }
        return "周"+week;
    }
}
