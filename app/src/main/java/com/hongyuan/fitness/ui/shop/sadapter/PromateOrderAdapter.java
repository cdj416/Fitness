package com.hongyuan.fitness.ui.shop.sadapter;
import android.view.View;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hongyuan.fitness.R;
import com.hongyuan.fitness.ui.shop.sbeans.TestNewOrdersBean;

import java.util.List;

public class PromateOrderAdapter extends BaseMultiItemQuickAdapter<TestNewOrdersBean, BaseViewHolder> {
    public static final int TYPE_ONE = 0;
    public static final int TYPE_TWO = 1;

    public PromateOrderAdapter(List<TestNewOrdersBean> data){
        super(data);
        addItemType(TYPE_ONE, R.layout.item_neworders_title);
        addItemType(TYPE_TWO, R.layout.item_promate_orders);
    }

    @Override
    protected void convert(BaseViewHolder helper, TestNewOrdersBean item) {
        switch (helper.getItemViewType()) {
            case TYPE_ONE:
                helper.itemView.setVisibility(View.VISIBLE);
                break;
            case TYPE_TWO:
                //helper.setText(R.id.content, twoBean.getContent());
                helper.itemView.setOnClickListener(view -> {

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
