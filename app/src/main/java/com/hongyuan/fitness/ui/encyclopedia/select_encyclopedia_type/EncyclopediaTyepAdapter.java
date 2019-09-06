package com.hongyuan.fitness.ui.encyclopedia.select_encyclopedia_type;


import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hongyuan.fitness.R;
import com.hongyuan.fitness.ui.main.main_about_class.private_lessons.MenuPrivateLessonsBean;

public class EncyclopediaTyepAdapter extends BaseQuickAdapter<MenuPrivateLessonsBean.DataBean, BaseViewHolder> {

    public EncyclopediaTyepAdapter() {
        super(R.layout.item_encyclopedia_type);
    }

    @Override
    protected void convert(BaseViewHolder helper, MenuPrivateLessonsBean.DataBean item) {
        helper.setText(R.id.typeName,item.getFt_name());
        helper.addOnClickListener(R.id.typeName);
    }
}
