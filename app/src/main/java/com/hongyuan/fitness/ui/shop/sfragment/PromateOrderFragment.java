package com.hongyuan.fitness.ui.shop.sfragment;

import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.CustomFragment;
import com.hongyuan.fitness.ui.shop.sadapter.PromateOrderAdapter;
import com.hongyuan.fitness.ui.shop.sbeans.TestNewOrdersBean;

import java.util.ArrayList;
import java.util.List;

public class PromateOrderFragment extends CustomFragment {

    private RecyclerView mRec;
    private PromateOrderAdapter adapter;

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
        adapter.setNewData(getList());
    }

    /*
     * 组装假数据
     * */
    private List<TestNewOrdersBean> getList(){
        List<TestNewOrdersBean> mList = new ArrayList<>();
        mList.add(new TestNewOrdersBean(PromateOrderAdapter.TYPE_ONE));
        mList.add(new TestNewOrdersBean(PromateOrderAdapter.TYPE_TWO));
        mList.add(new TestNewOrdersBean(PromateOrderAdapter.TYPE_TWO));

        mList.add(new TestNewOrdersBean(PromateOrderAdapter.TYPE_ONE));
        mList.add(new TestNewOrdersBean(PromateOrderAdapter.TYPE_TWO));

        mList.add(new TestNewOrdersBean(PromateOrderAdapter.TYPE_ONE));
        mList.add(new TestNewOrdersBean(PromateOrderAdapter.TYPE_TWO));
        mList.add(new TestNewOrdersBean(PromateOrderAdapter.TYPE_TWO));
        mList.add(new TestNewOrdersBean(PromateOrderAdapter.TYPE_TWO));

        mList.add(new TestNewOrdersBean(PromateOrderAdapter.TYPE_ONE));
        mList.add(new TestNewOrdersBean(PromateOrderAdapter.TYPE_TWO));
        mList.add(new TestNewOrdersBean(PromateOrderAdapter.TYPE_TWO));
        mList.add(new TestNewOrdersBean(PromateOrderAdapter.TYPE_TWO));
        mList.add(new TestNewOrdersBean(PromateOrderAdapter.TYPE_TWO));
        mList.add(new TestNewOrdersBean(PromateOrderAdapter.TYPE_TWO));
        return mList;
    }

    @Override
    public void onSuccess(Object data) {

    }
}
