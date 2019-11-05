package com.hongyuan.fitness.ui.main.main_mall;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hongyuan.fitness.R;

public class MallMenuAdapter extends BaseQuickAdapter<MallMenuBeans.DataBean.ListBean, BaseViewHolder> {

    public MallMenuAdapter(){
        super(R.layout.item_mall_menu);
    }
    @Override
    protected void convert(BaseViewHolder helper, MallMenuBeans.DataBean.ListBean item) {
        RequestOptions options = new RequestOptions().placeholder(R.mipmap.zhengfangxing_default_img).error(R.mipmap.zhengfangxing_default_img);
        Glide.with(mContext).load(item.getCategory_img()).apply(options).into((ImageView) helper.getView(R.id.menuImg));

        helper.setText(R.id.menuText,item.getCategory_name());
        helper.addOnClickListener(R.id.jumpBox);
    }
}
