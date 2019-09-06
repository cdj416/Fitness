package com.hongyuan.fitness.ui.about_class.privite_class.my_privite_course;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.hongyuan.fitness.base.Constants;
import com.hongyuan.fitness.base.Controller;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.base.CustomViewModel;
import com.hongyuan.fitness.base.SingleClick;
import com.hongyuan.fitness.databinding.ActivityMyPriviteCourseBinding;
import com.hongyuan.fitness.ui.about_class.privite_class.course_details.CourseDetailsActivity;
import com.hongyuan.fitness.ui.about_class.privite_class.preservation_course.ReservationDetailsActivity;
import com.hongyuan.fitness.util.DividerItemDecoration;
import com.hongyuan.fitness.util.HiddenAnimUtils;


public class MyPriviteCourseViewModel extends CustomViewModel {

    private ActivityMyPriviteCourseBinding binding;
    private MyPriviteCourseAdapter adapter;

    private SelectCoachAdapter coachAdapter;
    private MyPriviteCourseBeans courseBeans;
    private MyCoachBeans coachBeans;

    //需要筛选的教练id
    private String jl_mid = "";

    public MyPriviteCourseViewModel(CustomActivity mActivity, ActivityMyPriviteCourseBinding binding) {
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
        binding.mRecycler.setLayoutManager(manager);
        adapter = new MyPriviteCourseAdapter();
        binding.mRecycler.setAdapter(adapter);
        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @SingleClick(2000)
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                TextView textView = (TextView)view;
                if("再次购买".equals(textView.getText().toString())){
                    Bundle bundle = new Bundle();
                    bundle.putString("cp_id",String.valueOf(courseBeans.getData().getList().get(position).getCp_id()));
                    startActivity(CourseDetailsActivity.class,bundle);
                }
                if("预约".equals(textView.getText().toString())){
                    Bundle bundle = new Bundle();
                    bundle.putString("cp_id",String.valueOf(courseBeans.getData().getList().get(position).getCp_id()));
                    startActivity(ReservationDetailsActivity.class,bundle);
                }
            }
        });

        LinearLayoutManager topManager = new LinearLayoutManager(mActivity);
        topManager.setOrientation(RecyclerView.HORIZONTAL);
        binding.coachRec.addItemDecoration(new DividerItemDecoration(
                mActivity, DividerItemDecoration.VERTICAL_LIST,40,0x00000000));
        binding.coachRec.setLayoutManager(topManager);
        coachAdapter = new SelectCoachAdapter();
        binding.coachRec.setAdapter(coachAdapter);
        coachAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @SingleClick(2000)
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                for(MyCoachBeans.DataBean.ListBean bean : coachBeans.getData().getList()){
                    bean.setSelect(false);
                }
                coachBeans.getData().getList().get(position).setSelect(true);
                coachAdapter.setNewData(coachBeans.getData().getList());

                //每次筛选初始页面为1
                curPage = FIRST_PAGE;
                jl_mid = String.valueOf(coachBeans.getData().getList().get(position).getM_id());
                //初始化
                courseBeans = null;
                getCourse();
            }
        });


        binding.selectCoach.setOnClickListener(v -> HiddenAnimUtils.newInstance(mActivity,binding.coachBox,binding.downImg,108).toggle());
    }

    /*
     * 加载更多
     * */
    @Override
    public void loadMoreData() {
        getCourse();
    }

    //刷新数据
    @Override
    public void refreshData() {
        courseBeans = null;
        lazyLoad();
    }

    @Override
    protected void lazyLoad() {
        //查询已买私教课教练列表
        Controller.myRequest(Constants.GET_MY_COURSE_PRIVITE_COACH,Controller.TYPE_POST,getParams(), MyCoachBeans.class,this);

        getCourse();
    }

    private void getCourse(){
        //查询已买私教课并可以预约的列表
        if(isValue(jl_mid)){
            clearParams().setParams("jl_mid",jl_mid);
        }
        Controller.myRequest(Constants.GET_MY_COURSE_PRIVITE_LIST,Controller.TYPE_POST,getParams(), MyPriviteCourseBeans.class,this);
    }

    @Override
    public void onSuccess(Object data) {

        if(data instanceof MyCoachBeans){
            coachBeans = (MyCoachBeans)data;
            coachAdapter.setNewData(coachBeans.getData().getList());
        }

        if(data instanceof MyPriviteCourseBeans){
            MyPriviteCourseBeans pageData = (MyPriviteCourseBeans)data;
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
                mActivity.setPromtView(mActivity.SHOW_DATA);
            }else{
                mActivity.setPromtView( mActivity.SHOW_EMPTY);
            }
        }
    }
}
