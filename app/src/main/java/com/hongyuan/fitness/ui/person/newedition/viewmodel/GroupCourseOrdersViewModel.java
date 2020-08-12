package com.hongyuan.fitness.ui.person.newedition.viewmodel;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.BaseBean;
import com.hongyuan.fitness.base.Constants;
import com.hongyuan.fitness.base.ConstantsCode;
import com.hongyuan.fitness.base.Controller;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.base.CustomViewModel;
import com.hongyuan.fitness.base.SingleClick;
import com.hongyuan.fitness.databinding.AcitivityGroupCourseOrdersBinding;
import com.hongyuan.fitness.ui.about_class.group_class.group_details.MissionDetailActivity;
import com.hongyuan.fitness.ui.main.main_about_class.group_class.MyGroupClassBean;
import com.hongyuan.fitness.ui.person.waiting_for_class.about_group_course.MyGroupCourseeCheckAdapter;
import com.hongyuan.fitness.ui.scan.ScanActivity;
import com.hongyuan.fitness.util.CustomDialog;
import com.hongyuan.fitness.util.TimeUtil;

import java.util.List;

public class GroupCourseOrdersViewModel extends CustomViewModel {

    private AcitivityGroupCourseOrdersBinding binding;
    private MyGroupCourseeCheckAdapter adapter;
    //订单信息
    private List<MyGroupClassBean.DataBean.ListBean> mList;

    //记录当前签到的是哪一项
    private int mPosition;

    public GroupCourseOrdersViewModel(CustomActivity mActivity, AcitivityGroupCourseOrdersBinding binding) {
        super(mActivity);
        this.binding = binding;
        initView();
    }

    @Override
    protected void initView() {
        setEnableRefresh(true);
        setEnableLoadMore(true);

        LinearLayoutManager manager = new LinearLayoutManager(mActivity);
        manager.setOrientation(RecyclerView.VERTICAL);
        binding.mRec.setLayoutManager(manager);
        adapter = new MyGroupCourseeCheckAdapter();
        binding.mRec.setAdapter(adapter);
        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @SingleClick
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                //签到
                if(view.getId() == R.id.checkIn && mList.get(position).getXy_qd_state() != 1){
                    mPosition = position;
                    mActivity.startActivity(ScanActivity.class,null);
                }

                //去课程详情
                if(view.getId() == R.id.goBuyBox){
                    Bundle bundle = new Bundle();
                    bundle.putString("cs_id",String.valueOf(mList.get(position).getCs_id()));
                    startActivity(MissionDetailActivity.class,bundle);
                }
            }
        });
    }


    @Override
    public void refreshData(){
        curPage = FIRST_PAGE;
        lazyLoad();
    }

    @Override
    protected void loadMoreData() {
        lazyLoad();
    }
    @Override
    protected void lazyLoad() {
        mActivity.showLoading();
        clearParams();
        Controller.myRequest(Constants.GET_MEMBER_SIGN_UP_COURSE_SUPER_LIST,Controller.TYPE_POST,getParams(), MyGroupClassBean.class,this);
    }

    /*
     * 扫码回调签到
     * */
    public void courseQD(){

        if(mList.get(mPosition).getXy_qd_state() != 1){
            clearParams().setParams("ocs_id",String.valueOf(mList.get(mPosition).getOcs_id())).setParams("type","qd");
            Controller.myRequest(ConstantsCode.SUPER_COURSE_XY_QD,Constants.SUPER_COURSE_XY_QD,Controller.TYPE_POST,getParams(), BaseBean.class,this);
        }
    }


    @Override
    public void onSuccess(Object data) {
        mActivity.closeLoading();
        if(data instanceof MyGroupClassBean){
            List<MyGroupClassBean.DataBean.ListBean> list = ((MyGroupClassBean)data).getData().getList();
            if(curPage == FIRST_PAGE){
                mList = list;
            }else{
                if(list != null && list.size() > 0){
                    mList.addAll(list);
                }else{
                    mActivity.refresh.finishLoadMoreWithNoMoreData();
                }
            }

            if(mList != null && mList.size() > 0){
                adapter.setNewData(mList);
                mActivity.setPromtView(mActivity.SHOW_DATA);
            }else{
                mActivity.setPromtView(mActivity.SHOW_EMPTY);
            }
        }
    }

    @Override
    public void onSuccess(int code, Object data) {
        if(code == ConstantsCode.SUPER_COURSE_XY_QD){
           mList.get(mPosition).setXy_qd_state(1);
            adapter.setNewData(mList);
            CustomDialog.groupCoursePunchSuccess(mActivity, TimeUtil.formatDataMsec(TimeUtil.dateFormatDotMD,System.currentTimeMillis()),
                    TimeUtil.getWeek());
        }
    }
}
