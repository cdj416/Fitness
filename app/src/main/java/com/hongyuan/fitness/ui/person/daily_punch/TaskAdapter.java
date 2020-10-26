package com.hongyuan.fitness.ui.person.daily_punch;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hongyuan.fitness.R;
import com.hongyuan.fitness.ui.shop.sbeans.PointTaskBeans;

public class TaskAdapter extends BaseQuickAdapter<PointTaskBeans.DataBean.DayListBean, BaseViewHolder> {

    public TaskAdapter(){
        super(R.layout.item_task_list);
    }
    @Override
    protected void convert(BaseViewHolder helper, PointTaskBeans.DataBean.DayListBean item) {
        helper.setText(R.id.taskName,item.getPt_name()).setText(R.id.taskDes,item.getPt_rule())
                .setText(R.id.taskPoint,"+"+item.getPoint_num())
                .setText(R.id.taskSuccessNum,""+item.getState_num())
                .setText(R.id.taskLeverAll,"/"+item.getState_all_num());

        if(item.getPoint_num_up() <= 0){
            helper.setVisible(R.id.showNumsBox,false);
        }else{
            helper.setVisible(R.id.showNumsBox,true);
        }

        if(item.getIs_state() == 1){
            helper.setText(R.id.taskGo,"已完成")
                    .setTextColor(R.id.taskGo,mContext.getResources().getColor(R.color.color_FF1BC177))
                    .setBackgroundColor(R.id.taskGo,mContext.getResources().getColor(R.color.transparent));
            helper.getView(R.id.taskGo).setClickable(false);
        }else{
            helper.setText(R.id.taskGo,"去完成")
                    .setTextColor(R.id.taskGo,mContext.getResources().getColor(R.color.color_EF5B48))
                    .setBackgroundRes(R.id.taskGo,R.drawable.shape_radius16_00000000_stroke_ef5b48);
            helper.getView(R.id.taskGo).setClickable(true);
            helper.addOnClickListener(R.id.taskGo);
        }
        if(item.getIs_state()== 1){
            helper.setTextColor(R.id.taskSuccessNum,mContext.getResources().getColor(R.color.color_FF999999));
        }else{
            helper.setTextColor(R.id.taskSuccessNum,mContext.getResources().getColor(R.color.color_EF5B48));
        }
    }
}
