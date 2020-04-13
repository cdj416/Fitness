package com.hongyuan.fitness.ui.shop.sfragment;

import android.view.View;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.BaseBean;
import com.hongyuan.fitness.base.CustomFragment;
import com.hongyuan.fitness.custom_view.CustomRecyclerView;
import com.hongyuan.fitness.ui.shop.sactivity.SgoodsDetailActivity;
import com.hongyuan.fitness.ui.shop.sadapter.SMGoodsAdapter;

import java.util.ArrayList;
import java.util.List;

public class SstoreGoodsFragment extends CustomFragment {

    private RecyclerView mRec;
    private SMGoodsAdapter gAdapter;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_shop_store_goods;
    }

    @Override
    public void initView(View mView) {
        mRec = mView.findViewById(R.id.mRec);

        GridLayoutManager layoutManager =
                new GridLayoutManager(mActivity,2);
        mRec.setLayoutManager(layoutManager);
        gAdapter = new SMGoodsAdapter();
        mRec.setAdapter(gAdapter);
        gAdapter.setNewData(getList());
        gAdapter.setOnItemChildClickListener((adapter, view, position) -> {
            startActivity(SgoodsDetailActivity.class,null);
        });
    }

    /*
     * 组装假数据
     * */
    private List<BaseBean> getList(){
        List<BaseBean> mList = new ArrayList<>();
        for(int i = 0 ; i < 10 ; i++){
            mList.add(new BaseBean());
        }
        return mList;
    }

    @Override
    public void onSuccess(Object data) {

    }
}
