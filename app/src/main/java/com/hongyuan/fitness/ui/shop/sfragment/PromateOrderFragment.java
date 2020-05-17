package com.hongyuan.fitness.ui.shop.sfragment;

import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.Constants;
import com.hongyuan.fitness.base.Controller;
import com.hongyuan.fitness.base.CustomFragment;
import com.hongyuan.fitness.ui.shop.sadapter.PromateOrderAdapter;
import com.hongyuan.fitness.ui.shop.sbeans.PromateOrderBeans;
import com.hongyuan.fitness.ui.shop.sbeans.SnewOrderBeans;
import com.hongyuan.fitness.util.BaseUtil;

import java.util.ArrayList;
import java.util.List;

public class PromateOrderFragment extends CustomFragment {

    private RecyclerView mRec;
    private PromateOrderAdapter adapter;

    //数据集
    private List<MultiItemEntity> mList;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_base_recylerview;
    }

    @Override
    public void initView(View mView) {
        mRec = mView.findViewById(R.id.mRec);

        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        mRec.setLayoutManager(manager);
        adapter = new PromateOrderAdapter(new ArrayList<>());
        mRec.setAdapter(adapter);
    }

    @Override
    protected void lazyLoad() {
        mActivity.showLoading();
        if(BaseUtil.isValue(getFragType())){
            clearParams().setParams("o_state",getFragType());
        }
        Controller.myRequest(Constants.GET_INCOME_ORDER_LIST,Controller.TYPE_POST,getParams(), PromateOrderBeans.class,this);

    }

    @Override
    public void onSuccess(Object data) {
        mActivity.closeLoading();

        if(data instanceof PromateOrderBeans){
            PromateOrderBeans.DataBean dataBean = ((PromateOrderBeans)data).getData();

            if(curPage == FIRST_PAGE){
                mList = dataBean.getmList();
            }else{
                if(dataBean.getmList() != null && dataBean.getmList().size() > 0){
                    mList.addAll(dataBean.getmList());
                }else{
                    refresh.finishLoadMoreWithNoMoreData();
                }
            }

            if(mList != null && mList.size() > 0){
                adapter.setNewData(mList);
                //打开所有子项列表
                adapter.expandAll();

                setPromtView(SHOW_DATA);
            }else{
                setPromtView(SHOW_EMPTY);
            }
        }
    }
}
