package com.hongyuan.fitness.ui.person.waiting_for_class.about_group_course;

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
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.base.CustomFragment;
import com.hongyuan.fitness.base.SingleClick;
import com.hongyuan.fitness.ui.about_class.group_class.group_details.MissionDetailActivity;
import com.hongyuan.fitness.ui.main.main_about_class.group_class.MyGroupClassBean;
import com.hongyuan.fitness.ui.person.waiting_for_class.about_group_course.group_checkin_details.GroupCourseCheckDetailsActivity;
import com.hongyuan.fitness.util.CustomDialog;
import com.hongyuan.fitness.util.TimeUtil;

public class GroupCourseCheckFragment extends CustomFragment {

    private RecyclerView mRecycler;
    private MyGroupCourseeCheckAdapter adapter;

    private MyGroupClassBean courseBeans;

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
        adapter = new MyGroupCourseeCheckAdapter();
        mRecycler.setAdapter(adapter);

        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @SingleClick(2000)
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                //签到
                if(view.getId() == R.id.checkIn && courseBeans.getData().getList().get(position).getXy_qd_state() != 1){
                    mPosition = position;
                    courseQD(String.valueOf(courseBeans.getData().getList().get(position).getOcs_id()));
                }

                //去课程详情
                if(view.getId() == R.id.goBuyBox){
                    if((Boolean) view.getTag()){
                        Bundle bundle = new Bundle();
                        bundle.putString("cs_id",String.valueOf(courseBeans.getData().getList().get(position).getCs_id()));
                        startActivity(GroupCourseCheckDetailsActivity.class,bundle);
                    }else{
                        Bundle bundle = new Bundle();
                        bundle.putString("cs_id",String.valueOf(courseBeans.getData().getList().get(position).getCs_id()));
                        startActivity(MissionDetailActivity.class,bundle);
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

    @Override
    protected void lazyLoad() {
        courseBeans = null;
        loadData();
    }

    @Override
    public void refreshData() {
        lazyLoad();
    }

    /*
    * 加载课程信息
    * */
    private void loadData(){
        clearParams();
        Controller.myRequest(Constants.GET_MEMBER_SIGN_UP_COURSE_SUPER_LIST,Controller.TYPE_POST,getParams(), MyGroupClassBean.class,this);
    }

    /*
    * 签到签退
    * */
    private void courseQD(String ocs_id){
        clearParams().setParams("ocs_id",ocs_id).setParams("type","qd");
        Controller.myRequest(ConstantsCode.SUPER_COURSE_XY_QD,Constants.SUPER_COURSE_XY_QD,Controller.TYPE_POST,getParams(), BaseBean.class,this);
    }

    @Override
    public void onSuccess(Object data) {
        if(data instanceof MyGroupClassBean){
            MyGroupClassBean pageData = (MyGroupClassBean)data;
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
        if(code == ConstantsCode.SUPER_COURSE_XY_QD){
            courseBeans.getData().getList().get(mPosition).setXy_qd_state(1);
            adapter.setNewData(courseBeans.getData().getList());
            CustomDialog.groupCoursePunchSuccess(mActivity, TimeUtil.formatDataMsec(TimeUtil.dateFormatDotMD,System.currentTimeMillis()),
                    TimeUtil.getWeek());
        }
    }
}
