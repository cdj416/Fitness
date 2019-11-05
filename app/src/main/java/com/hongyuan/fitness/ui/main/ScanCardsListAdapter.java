package com.hongyuan.fitness.ui.main;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hongyuan.fitness.R;
import com.hongyuan.fitness.ui.main.main_home.recommend.HomeRightBeans;

public class ScanCardsListAdapter extends BaseQuickAdapter<HomeRightBeans.DataBean.ListBean, BaseViewHolder> {

    public ScanCardsListAdapter(){
        super(R.layout.item_scan_cards_list);
    }
    @Override
    protected void convert(BaseViewHolder helper, HomeRightBeans.DataBean.ListBean item) {
        helper.setText(R.id.titleName,item.getCard_type_name())
                .setText(R.id.storeName,item.getOs_names());

        helper.addOnClickListener(R.id.show);
    }
}
