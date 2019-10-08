package com.hongyuan.fitness.ui.about_class.privite_class.course_details;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hongyuan.fitness.R;

public class SuccessCaseImgAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    public SuccessCaseImgAdapter(){
        super(R.layout.item_success_case_img);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        RequestOptions options = new RequestOptions().placeholder(R.mipmap.defaul_no_img).error(R.mipmap.defaul_no_img).centerCrop();
        Glide.with(mContext).load(item).apply(options).into((ImageView) helper.getView(R.id.img));
    }
}
