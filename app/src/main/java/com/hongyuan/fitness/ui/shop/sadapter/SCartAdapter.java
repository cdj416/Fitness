package com.hongyuan.fitness.ui.shop.sadapter;

import android.view.View;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.hongyuan.fitness.R;
import com.hongyuan.fitness.ui.shop.sbeans.CartBeans;
import com.hongyuan.fitness.ui.shop.sbeans.TestBean;

import java.util.List;
public class SCartAdapter extends BaseMultiItemQuickAdapter<MultiItemEntity, BaseViewHolder> {
    public static final int TYPE_ONE = 0;
    public static final int TYPE_TWO = 1;

    public SCartAdapter(List<MultiItemEntity> data){
        super(data);
        addItemType(TYPE_ONE, R.layout.item_shop_cart_title);
        addItemType(TYPE_TWO, R.layout.item_shop_cart_goods);
    }

    @Override
    protected void convert(BaseViewHolder helper, MultiItemEntity item) {
        switch (helper.getItemViewType()) {
            case TYPE_ONE:
                final CartBeans oneBean = (CartBeans) item;
                helper.setText(R.id.storeName, oneBean.getStoreName());
                break;
            case TYPE_TWO:
                final TestBean twoBean = (TestBean) item;
                //helper.setText(R.id.content, twoBean.getContent());
                helper.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                    }
                });
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
