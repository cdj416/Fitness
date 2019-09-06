package com.hongyuan.fitness.ui.mall.good_details.skuitem_view;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hongyuan.fitness.R;
import com.hongyuan.fitness.custom_view.FlowLayoutManager;
import com.hongyuan.fitness.custom_view.NestedRecyclerView;
import com.hongyuan.fitness.ui.mall.good_details.GoodDetailsBean;

import java.util.List;

public class SkuAdapter extends BaseQuickAdapter<GoodDetailsBean.DataBean.InfoBean.SkuBean, BaseViewHolder> {

    public interface ChangeData{
        void dataChange(List<GoodDetailsBean.DataBean.InfoBean.SkuBean> changeList,int partPosition,int childPosition);
    }
    private ChangeData changeData;

    public SkuAdapter(ChangeData changeData){
        super(R.layout.item_dialog_sku);
        this.changeData = changeData;
    }
    @Override
    protected void convert(BaseViewHolder helper, GoodDetailsBean.DataBean.InfoBean.SkuBean item) {
        helper.setText(R.id.skuTitle,item.getSku_type_name());

        NestedRecyclerView childRec = helper.getView(R.id.des);
        FlowLayoutManager flowLayoutManager = new FlowLayoutManager();
        childRec.setLayoutManager(flowLayoutManager);
        SkuChildAdapter adapter = new SkuChildAdapter();
        adapter.setNewData(item.getItem());
        childRec.setAdapter(adapter);

        //规格的选择
        adapter.setOnItemChildClickListener((adapter1, view, position) -> {
            adapter.setNewData(changClick(item.getItem(),position));
            if(changeData != null){
                //这里需要传的是父类的position
                changeData.dataChange(getData(),helper.getPosition(),position);
            }
        });
    }

    /*
    * position:子项的position
    * */
    private List<GoodDetailsBean.DataBean.InfoBean.SkuBean.ItemBean> changClick(List<GoodDetailsBean.DataBean.InfoBean.SkuBean.ItemBean> mList,int position){
        for(int i = 0 ; i < mList.size() ; i++){
            if(i == position){
                mList.get(i).setSelect(true);
            }else{
                mList.get(i).setSelect(false);
            }
        }

        return mList;
    }
}
