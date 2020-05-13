package com.hongyuan.fitness.ui.shop.sadapter;

import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.hongyuan.fitness.R;
import com.hongyuan.fitness.ui.shop.sbeans.ScartBeans.DataBean.ListBean;
import com.hongyuan.fitness.ui.shop.sbeans.ScartBeans.DataBean.ListBean.GoodsListBean;
import com.hongyuan.fitness.ui.shop.sinterface.MyClickListener;
import com.hongyuan.fitness.util.BaseUtil;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.List;

public class SCartAdapter extends BaseMultiItemQuickAdapter<MultiItemEntity, BaseViewHolder> {
    public static final int TYPE_ONE = 0;
    public static final int TYPE_TWO = 1;

    //用于回调数量的变化而变化显示的价格
    private MyClickListener clickListener;

    public SCartAdapter(List<MultiItemEntity> data, MyClickListener clickListener){
        super(data);
        this.clickListener = clickListener;
        addItemType(TYPE_ONE, R.layout.item_shop_cart_title);
        addItemType(TYPE_TWO, R.layout.item_shop_cart_goods);
    }

    @Override
    protected void convert(BaseViewHolder helper, MultiItemEntity item) {
        switch (helper.getItemViewType()) {
            case TYPE_ONE:
                final ListBean oneBean = (ListBean) item;
                helper.setText(R.id.storeName, oneBean.getStore_name());
                if(oneBean.isSelectAll()){
                    helper.setBackgroundRes(R.id.selectImg,R.mipmap.checkmark_circle);
                }else{
                    helper.setBackgroundRes(R.id.selectImg,R.mipmap.yuanquanquan_no_select_img);
                }

                helper.addOnClickListener(R.id.selectImg).addOnClickListener(R.id.storeName);
                break;
            case TYPE_TWO:
                final GoodsListBean twoBean = (GoodsListBean) item;
                RequestOptions options = new RequestOptions().placeholder(R.color.color_f2).error(R.color.color_f2);
                Glide.with(mContext).load(twoBean.getG_img()).apply(options).into((RoundedImageView)helper.getView(R.id.goodImg));

                helper.setText(R.id.goodName,twoBean.getG_name())
                        .setText(R.id.goodPrice, BaseUtil.getNoZoon(twoBean.getGp_price()))
                        .setText(R.id.goodNum,String.valueOf(twoBean.getBuy_num()));

                if(BaseUtil.isValue(twoBean.getSku_names())){
                    helper.setVisible(R.id.goodType,true).setText(R.id.goodType,twoBean.getSku_names());
                }else{
                    helper.setVisible(R.id.goodType,false);
                }

                if(twoBean.isSelect()){
                    helper.setBackgroundRes(R.id.childSelectImg,R.mipmap.checkmark_circle);
                }else{
                    helper.setBackgroundRes(R.id.childSelectImg,R.mipmap.yuanquanquan_no_select_img);
                }

                helper.getView(R.id.sub).setOnClickListener(v -> {
                    TextView tvNum = helper.getView(R.id.goodNum);
                    int num = Integer.parseInt(tvNum.getText().toString())-1;
                    if(num <= 0){
                        num = 1;
                    }
                    tvNum.setText(String.valueOf(num));
                    twoBean.setBuy_num(num);
                    if(clickListener != null){
                        clickListener.onMyClick(v);
                    }
                });
                helper.getView(R.id.add).setOnClickListener(v -> {
                    TextView tvNum = helper.getView(R.id.goodNum);
                    int num = Integer.parseInt(tvNum.getText().toString())+1;
                    if(num > twoBean.getGp_stock()){
                        num = twoBean.getGp_stock();
                    }
                    tvNum.setText(String.valueOf(num));
                    twoBean.setBuy_num(num);
                    if(clickListener != null){
                        clickListener.onMyClick(v);
                    }
                });

                helper.addOnClickListener(R.id.childSelectImg).addOnClickListener(R.id.goodImg)
                        .addOnClickListener(R.id.goDetail);
                break;
        }
    }


    /*helper.itemView.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            int pos = helper.getAdapterPosition();
            if (oneBean.isExpanded()) {
                //收起
                collapse(pos);
            } else {
                //打开
                expand(pos);
            }
        }
    });*/

}
