package com.hongyuan.fitness.ui.shop.sviewmodel;

import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.base.CustomViewModel;
import com.hongyuan.fitness.custom_view.InputOrSlectView;
import com.hongyuan.fitness.databinding.ActivitySOrderDetailsBinding;
import com.hongyuan.fitness.ui.shop.sadapter.SCartAdapter;
import com.hongyuan.fitness.ui.shop.sbeans.CartBeans;
import com.hongyuan.fitness.ui.shop.sbeans.TestBean;
import com.hongyuan.fitness.util.CustomDialog;

import java.util.ArrayList;
import java.util.List;

public class SorderDetailViewModel extends CustomViewModel {

    private ActivitySOrderDetailsBinding binding;
    private InputOrSlectView myCoupon;

    private SCartAdapter adapter;

    public SorderDetailViewModel(CustomActivity mActivity, ActivitySOrderDetailsBinding binding) {
        super(mActivity);
        this.binding = binding;
        initView();
    }

    @Override
    protected void initView() {
        LinearLayoutManager manager = new LinearLayoutManager(mActivity);
        manager.setOrientation(RecyclerView.VERTICAL);
        binding.mRec.setLayoutManager(manager);
        adapter = new SCartAdapter(new ArrayList<>());
        binding.mRec.setAdapter(adapter);
        adapter.setFooterView(getFooter());
        adapter.setNewData(getData());
        //打开所有子项列表
        adapter.expandAll();
    }

    /*
    * 获取底部视图
    * */
    private View getFooter(){
        View footerView = mActivity.getLayoutInflater().inflate(R.layout.view_footer_sorder_details,null);
        myCoupon = footerView.findViewById(R.id.myCoupon);
        myCoupon.setOnClickListener(v -> {
            CustomDialog.showSOUseCoupon(mActivity);
        });
        return footerView;
    }

    /*
     * 组装假数据
     * */
    private List<MultiItemEntity> getData(){
        List<MultiItemEntity> mList = new ArrayList<>();
        for(int i = 0 ; i < 2 ; i++){
            CartBeans beans = new CartBeans();
            beans.setStoreName("测试标题"+i);
            for(int j = 0 ; j < (i+1) ; j++){
                TestBean testBean = new TestBean();
                beans.addSubItem(testBean);
            }
            mList.add(beans);
        }

        return mList;
    }

    @Override
    public void onSuccess(Object data) {

    }
}
