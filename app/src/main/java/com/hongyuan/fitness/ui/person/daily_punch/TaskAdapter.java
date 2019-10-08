package com.hongyuan.fitness.ui.person.daily_punch;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hongyuan.fitness.R;

public class TaskAdapter extends BaseQuickAdapter<TaskBeans.DataBean.ListBeanX.ListBean, BaseViewHolder> {

    public TaskAdapter(){
        super(R.layout.item_task_list);
    }
    @Override
    protected void convert(BaseViewHolder helper, TaskBeans.DataBean.ListBeanX.ListBean item) {
        helper.setText(R.id.taskName,item.getTask_name()).setText(R.id.taskDes,item.getTask_note())
                .setText(R.id.taskPoint,"+"+item.getOne_point())
                .setText(R.id.taskSuccessNum,""+item.getLevel())
                .setText(R.id.taskLeverAll,"/"+item.getLevel_all());

        if(item.getLevel() == Integer.valueOf(item.getLevel_all())){
            helper.setText(R.id.taskGo,"已完成")
                    .setTextColor(R.id.taskGo,mContext.getResources().getColor(R.color.color_FF1BC177))
                    .setBackgroundColor(R.id.taskGo,mContext.getResources().getColor(R.color.color_FFFFFF));
            helper.getView(R.id.taskGo).setClickable(false);
        }else{
            helper.setText(R.id.taskGo,"去完成")
                    .setTextColor(R.id.taskGo,mContext.getResources().getColor(R.color.color_EF5B48))
                    .setBackgroundRes(R.id.taskGo,R.drawable.shape_radius16_ffffff_stroke_ef5b48);
            helper.getView(R.id.taskGo).setClickable(true);
            helper.addOnClickListener(R.id.taskGo);
        }
        if(item.getLevel() == 0){
            helper.setTextColor(R.id.taskSuccessNum,mContext.getResources().getColor(R.color.color_FF999999));
        }else{
            helper.setTextColor(R.id.taskSuccessNum,mContext.getResources().getColor(R.color.color_EF5B48));
        }
    }
}
