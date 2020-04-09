package com.hongyuan.fitness.ui.shop.sadapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.BaseBean;
import com.hongyuan.fitness.custom_view.FlowLayoutManager;
import com.hongyuan.fitness.custom_view.NestedRecyclerView;

import java.util.ArrayList;
import java.util.List;

public class SGDspecificationAdapter extends BaseQuickAdapter<BaseBean, BaseViewHolder> {

    public SGDspecificationAdapter(){
        super(R.layout.item_sgdetails_specification);
    }

    @Override
    protected void convert(BaseViewHolder helper, BaseBean item) {


        //子类item
        NestedRecyclerView childRec = helper.getView(R.id.childRec);
        FlowLayoutManager flowLayoutManager = new FlowLayoutManager();
        childRec.setLayoutManager(flowLayoutManager);
        SGDspecificationChildAdapter childAdapter = new SGDspecificationChildAdapter();
        childRec.setAdapter(childAdapter);

        childAdapter.setNewData(getList());
    }

    /*
    * 假数据
    * */
    private  List<BaseBean> getList(){
        List<BaseBean> mList = new ArrayList<>();
        for(int i = 0 ; i < 10 ; i++){
            BaseBean bean = new BaseBean();
            mList.add(bean);
        }
        return mList;
    }
}
