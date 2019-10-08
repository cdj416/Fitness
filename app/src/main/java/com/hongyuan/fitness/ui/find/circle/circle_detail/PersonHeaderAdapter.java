package com.hongyuan.fitness.ui.find.circle.circle_detail;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hongyuan.fitness.R;
import com.hongyuan.fitness.util.BaseUtil;
import com.makeramen.roundedimageview.RoundedImageView;

public class PersonHeaderAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    public PersonHeaderAdapter(){
        super(R.layout.item_applyperson_num);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {

        if(BaseUtil.isValue(item)){
            RequestOptions options = new RequestOptions().placeholder(R.mipmap.default_head_img).error(R.mipmap.default_head_img);
            Glide.with(mContext).load(item).apply(options).into((RoundedImageView)helper.getView(R.id.headImg));
        }else{
            helper.setImageResource(R.id.headImg,R.mipmap.black_dot_more_mark);
        }
        helper.addOnClickListener(R.id.headImg);
    }
}
