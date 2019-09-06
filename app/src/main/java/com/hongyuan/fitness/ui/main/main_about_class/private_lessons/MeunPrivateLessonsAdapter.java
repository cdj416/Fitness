package com.hongyuan.fitness.ui.main.main_about_class.private_lessons;

import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hongyuan.fitness.R;

public class MeunPrivateLessonsAdapter extends BaseQuickAdapter<MenuPrivateLessonsBean.DataBean, BaseViewHolder> {

    public MeunPrivateLessonsAdapter(){
        super(R.layout.item_menu_private_lessons);
    }
    @Override
    protected void convert(BaseViewHolder helper, MenuPrivateLessonsBean.DataBean item) {
        helper.setText(R.id.name,item.getFt_name());

        if(item.isSelect()){
            ((TextView)helper.getView(R.id.name)).setTextColor(0xffffffff);
            ((TextView)helper.getView(R.id.number)).setTextColor(0xffffffff);
            helper.getView(R.id.box).setBackgroundResource(R.drawable.shape_radius16_ef5b48);
        }else{
            ((TextView)helper.getView(R.id.name)).setTextColor(0xff5F5F5F);
            ((TextView)helper.getView(R.id.number)).setTextColor(0xffEF5B48);
            helper.getView(R.id.box).setBackgroundResource(R.drawable.shape_radius16_ffffff_stroke_979797);
        }
        helper.addOnClickListener(R.id.box);
    }
}
