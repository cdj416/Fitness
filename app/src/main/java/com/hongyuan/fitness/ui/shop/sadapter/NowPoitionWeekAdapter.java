package com.hongyuan.fitness.ui.shop.sadapter;

import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.BaseBean;
import com.hongyuan.fitness.ui.shop.sbeans.NewPoitionBeans;
import com.hongyuan.fitness.util.TimeUtil;

public class NowPoitionWeekAdapter extends BaseQuickAdapter<NewPoitionBeans.DataBean.QdWeekBean, BaseViewHolder> {

    public NowPoitionWeekAdapter(){
        super(R.layout.item_week_datas);
    }

    @Override
    protected void convert(BaseViewHolder helper, NewPoitionBeans.DataBean.QdWeekBean item) {

        if(helper.getAdapterPosition() == 6){
            helper.getView(R.id.line).setVisibility(View.GONE);
        }else{
            helper.getView(R.id.line).setVisibility(View.VISIBLE);
        }

        if(item.getIs_qd() == 1){
            helper.setBackgroundRes(R.id.pointText,R.drawable.shape_gradient_oval_f92468_f9b449)
                    .setText(R.id.pointText,"+"+item.getPoint());
        }else{
            helper.setBackgroundRes(R.id.pointText,R.drawable.shape_oval_cccccc)
                    .setText(R.id.pointText,"+"+item.getPoint());
        }

        helper.setText(R.id.dataText, TimeUtil.isToday(item.getQd_date(),TimeUtil.dateFormatYMD) ?
                "今天" : TimeUtil.formatDate(item.getQd_date(),TimeUtil.dateFormatYMD,TimeUtil.dateFormatDotMD));
    }


}
