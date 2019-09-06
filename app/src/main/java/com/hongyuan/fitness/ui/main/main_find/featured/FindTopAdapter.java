package com.hongyuan.fitness.ui.main.main_find.featured;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hongyuan.fitness.R;
import com.hongyuan.fitness.ui.main.main_about_class.group_class.GroupClassBean;

public class FindTopAdapter extends BaseQuickAdapter<GroupClassBean, BaseViewHolder> {

    public FindTopAdapter(){
        super(R.layout.item_find_top);
    }

    @Override
    protected void convert(BaseViewHolder helper, GroupClassBean item) {
        helper.addOnClickListener(R.id.jumpBox);
    }
}
