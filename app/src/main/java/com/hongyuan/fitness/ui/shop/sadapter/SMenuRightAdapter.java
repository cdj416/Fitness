package com.hongyuan.fitness.ui.shop.sadapter;

import android.os.Bundle;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.ui.shop.sactivity.ShopSearchActivity;
import com.hongyuan.fitness.ui.shop.sbeans.ShopMenuBeans;
public class SMenuRightAdapter extends BaseQuickAdapter<ShopMenuBeans.DataBean, BaseViewHolder> {

    public SMenuRightAdapter(){
        super(R.layout.item_shop_menu_right);
    }

    @Override
    protected void convert(BaseViewHolder helper, ShopMenuBeans.DataBean item) {
        helper.setText(R.id.firstName,item.getCategory_name());

        RecyclerView childRec = helper.getView(R.id.childRec);
        GridLayoutManager rihtManager = new GridLayoutManager(mContext, 3);
        rihtManager.setOrientation(RecyclerView.VERTICAL);
        childRec.setLayoutManager(rihtManager);
        SMenuRightChildAdapter childAdapter = new SMenuRightChildAdapter();
        childRec.setAdapter(childAdapter);
        childAdapter.setNewData(item.getSecond());
        childAdapter.setOnItemChildClickListener((adapter, view, position) -> {
            Bundle bundle = new Bundle();
            bundle.putString("first_category_id",String.valueOf(item.getSecond().get(position).getCategory_id()));
            bundle.putString("third_category_id",String.valueOf(item.getSecond().get(position).getCategory_id()));
            bundle.putString("showText",item.getSecond().get(position).getCategory_name());
            ((CustomActivity)mContext).startActivity(ShopSearchActivity.class,bundle);
        });
    }
}
