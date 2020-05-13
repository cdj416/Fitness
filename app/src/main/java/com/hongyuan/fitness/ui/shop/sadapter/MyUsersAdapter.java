package com.hongyuan.fitness.ui.shop.sadapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.BaseBean;

public class MyUsersAdapter extends BaseQuickAdapter<BaseBean, BaseViewHolder> {

    public MyUsersAdapter(){
        super(R.layout.item_my_users);
    }

    @Override
    protected void convert(BaseViewHolder helper, BaseBean item) {

    }
}
