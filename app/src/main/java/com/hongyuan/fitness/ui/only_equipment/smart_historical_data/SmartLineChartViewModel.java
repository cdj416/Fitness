package com.hongyuan.fitness.ui.only_equipment.smart_historical_data;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.hongyuan.fitness.base.BaseBean;
import com.hongyuan.fitness.base.Constants;
import com.hongyuan.fitness.base.ConstantsCode;
import com.hongyuan.fitness.base.Controller;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.base.CustomViewModel;
import com.hongyuan.fitness.custom_view.SwipeItemLayout;
import com.hongyuan.fitness.databinding.ActivitySmartHistoricalDataBinding;

import java.util.List;

public class SmartLineChartViewModel extends CustomViewModel {

    private ActivitySmartHistoricalDataBinding binding;
    private SmartLineChartPagerAdapter meunAdapter;

    private List<SmartBodyFatBeans.DataBean.ListBean> mList;
    private SmartHistoricalDataAdapter adapter;
    private int mPosition;//记录要删除数据的坐标

    public SmartLineChartViewModel(CustomActivity mActivity, ActivitySmartHistoricalDataBinding binding) {
        super(mActivity);
        this.binding = binding;
        initView();
        lazyLoad();
    }

    @Override
    protected void initView() {
        //setEnableOverScrollDrag(true);
        meunAdapter = new SmartLineChartPagerAdapter(mActivity.getSupportFragmentManager());
        binding.mViewPager.setAdapter(meunAdapter);
        binding.layoutMenu.setupWithViewPager(binding.mViewPager);
        binding.mViewPager.setOffscreenPageLimit(13);

        LinearLayoutManager manager = new LinearLayoutManager(mActivity);
        manager.setOrientation(RecyclerView.VERTICAL);
        binding.mRecycler.setLayoutManager(manager);
        adapter = new SmartHistoricalDataAdapter();
        binding.mRecycler.setAdapter(adapter);
        adapter.setOnItemChildClickListener((adapter, view, position) -> {
            mPosition = position;
            deleteData(String.valueOf(mList.get(position).getBf_id()));
        });

        binding.mRecycler.addOnItemTouchListener(new SwipeItemLayout.OnSwipeItemTouchListener(mActivity));
        //禁止滑动以及优化卡顿问题
        binding.mRecycler.setHasFixedSize(true);
        binding.mRecycler.setNestedScrollingEnabled(false);
        binding.mRecycler.setLayoutManager(new LinearLayoutManager(mActivity){
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        });
    }

    @Override
    protected void lazyLoad() {
        mActivity.showLoading();
        clearParams();
        Controller.myRequest(Constants.GET_BODY_FAT_LIST,Controller.TYPE_POST,getParams(), SmartBodyFatBeans.class,this);
        //体脂--读取最新的十条数据
        clearParams();
        Controller.myRequest(Constants.GET_BODY_FAT_LIST_NEW,Controller.TYPE_POST,getParams(), BodyFatListNewBeans.class,this);
    }

    /*
    * 删除记录
    * */
    private void deleteData(String bf_id){
        mActivity.showLoading();
        clearParams().setParams("bf_id",bf_id);
        Controller.myRequest(ConstantsCode.DEL_BODY_FAT,Constants.DEL_BODY_FAT,Controller.TYPE_POST,getParams(), BaseBean.class,this);
    }

    @Override
    public void onSuccess(Object data) {
        mActivity.closeLoading();
        if(data instanceof SmartBodyFatBeans){
            List<SmartBodyFatBeans.DataBean.ListBean> list = ((SmartBodyFatBeans)data).getData().getList();
            if(curPage == FIRST_PAGE){
                mList = list;
            }else{
                if(list != null && list.size() > 0){
                    mList.addAll(list);
                }else{
                    //refresh.finishLoadMoreWithNoMoreData();
                }
            }

            if(mList != null && mList.size() > 0){
                adapter.setNewData(mList);
                //setPromtView(SHOW_DATA);
            }else{
                //setPromtView(SHOW_EMPTY);
            }
        }

        if(data instanceof BodyFatListNewBeans){
            List<BodyFatListNewBeans.DataBean.ListBean> newBeans = ((BodyFatListNewBeans)data).getData().getList();
            meunAdapter.setData(newBeans);
        }
    }

    @Override
    public void onSuccess(int code, Object data) {
        super.onSuccess(code,data);

        mActivity.closeLoading();
        if(code == ConstantsCode.DEL_BODY_FAT){
            showSuccess("删除成功！");
            mList.remove(mPosition);
            adapter.notifyDataSetChanged();
        }
    }
}
