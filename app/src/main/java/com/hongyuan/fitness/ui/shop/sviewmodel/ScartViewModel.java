package com.hongyuan.fitness.ui.shop.sviewmodel;

import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.base.CustomViewModel;
import com.hongyuan.fitness.databinding.ActivityScartBinding;
import com.hongyuan.fitness.ui.shop.sadapter.SCartAdapter;
import com.hongyuan.fitness.ui.shop.sbeans.CartBeans;
import com.hongyuan.fitness.ui.shop.sbeans.TestBean;
import com.hongyuan.fitness.ui.shop.sinterface.MyClickListener;
import com.hongyuan.fitness.ui.shop.smyview.SCartTitleRightView;
import com.hongyuan.fitness.ui.shop.sviewmodel.bottomviewmodel.ScartBottomViewModel;

import java.util.ArrayList;
import java.util.List;

public class ScartViewModel extends CustomViewModel implements MyClickListener {

    private ActivityScartBinding binding;
    private ScartBottomViewModel bModel;

    //标题栏右边视图
    private SCartTitleRightView rightView;
    private SCartAdapter adapter;

    //当前可操作模式false为购买商品模式，true为管理商品模式
    private boolean mode = false;

    public ScartViewModel(CustomActivity mActivity, ActivityScartBinding binding, ScartBottomViewModel bModel) {
        super(mActivity);
        this.binding = binding;
        this.bModel = bModel;

        initView();
    }

    @Override
    protected void initView() {

        //设置右边显示视图
        rightView = new SCartTitleRightView(mActivity, this);
        mActivity.getMainTitle().addRightContentView(rightView);

        LinearLayoutManager manager = new LinearLayoutManager(mActivity);
        manager.setOrientation(RecyclerView.VERTICAL);
        binding.mRec.setLayoutManager(manager);
        adapter = new SCartAdapter(new ArrayList<>());
        binding.mRec.setAdapter(adapter);
        adapter.setFooterView(mActivity.getFooterHeight(binding.mRec));
        adapter.setNewData(getData());
        //打开所有子项列表
        adapter.expandAll();
    }

    /*
    * 组装假数据
    * */
    private List<MultiItemEntity> getData(){
        List<MultiItemEntity> mList = new ArrayList<>();
        for(int i = 0 ; i < 10 ; i++){
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
    public void onMyClick(View v) {
        switch (v.getId()){
            case R.id.operatText:
                bModel.changeModel(mode);
                if(!mode){
                    rightView.setOperatText("完成");
                    mode = true;
                }else{
                    rightView.setOperatText("管理");
                    mode = false;
                }
                break;
            case R.id.message:
                break;
        }
    }

    @Override
    public void onSuccess(Object data) {

    }
}
