package com.hongyuan.fitness.ui.shop.sadapter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hongyuan.fitness.R;
import com.hongyuan.fitness.ui.shop.sbeans.NewPoitionBeans;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.List;

public class NewPoitionGoodsAdapter extends BaseMultiItemQuickAdapter<NewPoitionBeans.DataBean.GoodsBean, BaseViewHolder> {

    public static final int TYPE_ONE = 0;
    public static final int TYPE_TWO = 1;

    public NewPoitionGoodsAdapter(List<NewPoitionBeans.DataBean.GoodsBean> data){
        super(data);
        addItemType(TYPE_ONE, R.layout.item_newpoition_goods);
        addItemType(TYPE_TWO, R.layout.item_newpoition_goods_two);
    }

    @Override
    protected void convert(BaseViewHolder helper,  NewPoitionBeans.DataBean.GoodsBean item) {
        RequestOptions options = new RequestOptions().placeholder(R.color.color_f2).error(R.color.color_f2);
        Glide.with(mContext).load(item.getG_img()).apply(options).into((RoundedImageView)helper.getView(R.id.goodImg));

        helper.setText(R.id.needPoition,item.getG_point()+"积分");

        if(item.getItemType() == TYPE_TWO){
            helper.setText(R.id.goodName,item.getG_name());
        }

        helper.addOnClickListener(R.id.goGoodDetails).addOnClickListener(R.id.goGoodTwoDetails);
    }
}
