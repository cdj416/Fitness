package com.hongyuan.fitness.ui.shop.sadapter;

import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hongyuan.fitness.R;
import com.hongyuan.fitness.ui.shop.sbeans.PointTaskBeans;

public class NewPoitionTaskAdapter extends BaseQuickAdapter<PointTaskBeans.DataBean.NewsListBean, BaseViewHolder> {

    public NewPoitionTaskAdapter(){
        super(R.layout.item_task_poition);
    }

    @Override
    protected void convert(BaseViewHolder helper, PointTaskBeans.DataBean.NewsListBean item) {

        RequestOptions options = new RequestOptions().placeholder(R.color.color_f2).error(R.color.color_f2);
        Glide.with(mContext).load(item.getPt_img()).apply(options).into((ImageView) helper.getView(R.id.img));

        if(helper.getAdapterPosition() == 0){
            helper.getView(R.id.leftLine).setVisibility(View.VISIBLE);
            helper.getView(R.id.rightLine).setVisibility(View.GONE);
        }else if(helper.getAdapterPosition() == (getData().size() - 1)){
            helper.getView(R.id.leftLine).setVisibility(View.GONE);
            helper.getView(R.id.rightLine).setVisibility(View.VISIBLE);
        }else{
            helper.getView(R.id.leftLine).setVisibility(View.GONE);
            helper.getView(R.id.rightLine).setVisibility(View.GONE);
        }

        helper.addOnClickListener(R.id.img);
    }
}
