package com.hongyuan.fitness.ui.find.circle.get_nearby_location;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.BaseBean;

public class GetNearyLocationAdapter extends BaseQuickAdapter<NearLocationBeans, BaseViewHolder> {

    public GetNearyLocationAdapter(){
        super(R.layout.item_get_nearby_location);
    }

    @Override
    protected void convert(final BaseViewHolder helper, NearLocationBeans item) {
        helper.setText(R.id.titleName,item.getTitle()).setText(R.id.address,item.getSnippet());
        helper.addOnClickListener(R.id.box);
    }

}
