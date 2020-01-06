package com.hongyuan.fitness.ui.person.physical_data.silhouette;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hongyuan.fitness.R;

public class SihouetteChildAdapter extends BaseQuickAdapter<SihouetteBeans.DataBean.ListBean.ImgsBean, BaseViewHolder> {

    public SihouetteChildAdapter(){
        super(R.layout.item_sihouette_child);
    }

    @Override
    protected void convert(BaseViewHolder helper, SihouetteBeans.DataBean.ListBean.ImgsBean item) {
        RequestOptions options = new RequestOptions().placeholder(R.mipmap.zhengfangxing_default_img).error(R.mipmap.zhengfangxing_default_img);
        Glide.with(mContext).load(item.getImg()).apply(options).into((ImageView) helper.getView(R.id.img));

        helper.addOnClickListener(R.id.img);

    }


}
