package com.hongyuan.fitness.ui.shop.sadapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.custom_view.FlowLayoutManager;
import com.hongyuan.fitness.custom_view.NestedRecyclerView;
import com.hongyuan.fitness.ui.shop.sbeans.SgoodsDetailBeans;

import java.util.List;

public class SGDspecificationAdapter extends BaseQuickAdapter<SgoodsDetailBeans.DataBean.InfoBean.SkuBean, BaseViewHolder> {

    public interface ChangeData{
        void dataChange(List<SgoodsDetailBeans.DataBean.InfoBean.SkuBean> changeList, int partPosition, int childPosition);
    }
    private ChangeData changeData;

    private CustomActivity mContext;

    public SGDspecificationAdapter(ChangeData changeData, CustomActivity mContext){
        super(R.layout.item_sgdetails_specification);
        this.changeData = changeData;
        this.mContext = mContext;
    }

    @Override
    protected void convert(BaseViewHolder helper, SgoodsDetailBeans.DataBean.InfoBean.SkuBean item) {

        helper.setText(R.id.titleName,item.getSku_type_name());

        //子类item
        NestedRecyclerView childRec = helper.getView(R.id.childRec);
        FlowLayoutManager flowLayoutManager = new FlowLayoutManager();
        childRec.setLayoutManager(flowLayoutManager);
        SGDspecificationChildAdapter childAdapter = new SGDspecificationChildAdapter(mContext);
        childRec.setAdapter(childAdapter);
        childAdapter.setOnItemChildClickListener((adapter, view, position) -> {
            for(SgoodsDetailBeans.DataBean.InfoBean.SkuBean.ItemBean itemBean : item.getItem()){
                itemBean.setSelect(false);
            }
            item.getItem().get(position).setSelect(true);
            adapter.notifyDataSetChanged();
            //子项规格点击之后的回调
            changeData.dataChange(getData(),helper.getAdapterPosition(),position);
        });

        childAdapter.setNewData(item.getItem());
    }

}
