package com.hongyuan.fitness.ui.about_class.coach.coach_profile;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hongyuan.fitness.R;
import com.hongyuan.fitness.ui.store.StoreDetailBean;

public class CerterAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    public CerterAdapter(){
        super(R.layout.item_mark_text);
    }
    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.setText(R.id.showName,item);
    }
}
