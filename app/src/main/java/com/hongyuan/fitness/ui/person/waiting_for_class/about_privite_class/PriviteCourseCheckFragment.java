package com.hongyuan.fitness.ui.person.waiting_for_class.about_privite_class;

import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.BaseBean;
import com.hongyuan.fitness.base.Constants;
import com.hongyuan.fitness.base.ConstantsCode;
import com.hongyuan.fitness.base.Controller;
import com.hongyuan.fitness.base.CustomFragment;
import com.hongyuan.fitness.base.SingleClick;
import com.hongyuan.fitness.ui.about_class.privite_class.course_details.CourseDetailsActivity;
import com.hongyuan.fitness.ui.person.waiting_for_class.about_privite_class.privite_checkin_details.PriviteCourseCheckDetails;
import com.hongyuan.fitness.util.CustomDialog;
import com.hongyuan.fitness.util.TimeUtil;

public class PriviteCourseCheckFragment extends CustomFragment {

    private RecyclerView mRecycler;
    private MyPriviteCourseCheckAdapter adapter;

    private PriviteCourseCheckBeans courseBeans;

    //记录当前签到的是哪一项
    private int mPosition;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_about_privite_class;
    }

    @Override
    public void initView(View mView) {
        setEnableRefresh(true);
        setEnableLoadMore(true);

        mRecycler = mView.findViewById(R.id.mRecycler);

        LinearLayoutManager manager = new LinearLayoutManager(mActivity);
        manager.setOrientation(RecyclerView.VERTICAL);
        mRecycler.setLayoutManager(manager);
        adapter = new MyPriviteCourseCheckAdapter();
        mRecycler.setAdapter(adapter);

        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @SingleClick(2000)
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                //签到
                if(view.getId() == R.id.qdBox && courseBeans.getData().getList().get(position).getXy_qd_state() != 1){
                    mPosition = position;
                    courseQD(String.valueOf(courseBeans.getData().getList().get(position).getCpa_id()));
                }

                //去课程详情和签到详情
                if(view.getId() == R.id.goBuyBox){
                    if(!(Boolean) view.getTag()){
                        Bundle bundle = new Bundle();
                        bundle.putString("cp_id",String.valueOf(courseBeans.getData().getList().get(position).getCp_id()));
                        startActivity(CourseDetailsActivity.class,bundle);
                    }else{
                        Bundle bundle = new Bundle();
                        bundle.putString("cp_id",String.valueOf(courseBeans.getData().getList().get(position).getCp_id()));
                        bundle.putString("cpa_id",String.valueOf(courseBeans.getData().getList().get(position).getCpa_id()));
                        bundle.putString("showTime",courseBeans.getData().getList().get(position).getStart_time());
                        startActivity(PriviteCourseCheckDetails.class,bundle);
                    }

                }
            }
        });
    }

    /*
     * 加载更多
     * */
    @Override
    public void loadMoreData() {
        loadData();
    }

    /*
    * 返回页面时刷新数据
    * */
    @Override
    public void refreshData() {
        lazyLoad();
    }

    @Override
    protected void lazyLoad() {
        courseBeans = null;
        loadData();
    }

    /*
    * 加载课程信息
    * */
    private void loadData(){
        mActivity.showLoading();
        clearParams().setParams("state_str","1");
        Controller.myRequest(Constants.GET_MEMBER_APPOINTMENT_COURSE_PRIVITE_LIST,Controller.TYPE_POST,getParams(), PriviteCourseCheckBeans.class,this);
    }

    /*
    * 签到签退
    * */
    private void courseQD(String cpa_id){
        clearParams().setParams("cpa_id",cpa_id).setParams("mtype","xy").setParams("type","qd");
        Controller.myRequest(ConstantsCode.PRIVITE_COURSE_QD,Constants.PRIVITE_COURSE_QD,Controller.TYPE_POST,getParams(), BaseBean.class,this);
    }

    @Override
    public void onSuccess(Object data) {
        if(data instanceof PriviteCourseCheckBeans){
            mActivity.closeLoading();
            PriviteCourseCheckBeans pageData = (PriviteCourseCheckBeans)data;
            if(curPage == FIRST_PAGE){
                if(pageData.getData().getList() != null && pageData.getData().getList().size() > 0){
                    courseBeans = pageData;
                }
            }else{
                if(pageData.getData().getList() != null && pageData.getData().getList().size() > 0){
                    courseBeans.getData().getList().addAll(pageData.getData().getList());
                }
            }

            if(courseBeans != null && courseBeans.getData() != null &&
                    courseBeans.getData().getList() != null &&
                    courseBeans.getData().getList().size() > 0){
                adapter.setNewData(courseBeans.getData().getList());
                setPromtView(mActivity.SHOW_DATA);
            }else{
                setPromtView( mActivity.SHOW_EMPTY);
            }
        }
    }

    @Override
    public void onSuccess(int code, Object data) {
        if(code == ConstantsCode.PRIVITE_COURSE_QD){
            courseBeans.getData().getList().get(mPosition).setXy_qd_state(1);
            adapter.setNewData(courseBeans.getData().getList());
            CustomDialog.priviteCoursePunchSuccess(mActivity,TimeUtil.formatDataMsec(TimeUtil.dateFormatDotMD,System.currentTimeMillis()),
                    TimeUtil.getWeek());
        }
    }
}
