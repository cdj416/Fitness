package com.hongyuan.fitness.ui.about_class.course_search;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.Constants;
import com.hongyuan.fitness.base.Controller;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.base.CustomViewModel;
import com.hongyuan.fitness.base.SingleClick;
import com.hongyuan.fitness.databinding.ActivityV3SearchCourseBinding;
import com.hongyuan.fitness.ui.about_class.group_class.group_details.MissionDetailActivity;
import com.hongyuan.fitness.ui.about_class.privite_class.course_details.CourseDetailsActivity;
import com.hongyuan.fitness.ui.main.main_about_class.group_class.vtwo_group_class.VtwoGroupClassAdapter;
import com.hongyuan.fitness.ui.main.main_about_class.group_class.vtwo_group_class.VtwoGroupClassBeans;
import com.hongyuan.fitness.ui.main.main_about_class.private_lessons.vtwo_private_lessons.VtwoPrivateLessonsAdapter;
import com.hongyuan.fitness.ui.main.main_about_class.private_lessons.vtwo_private_lessons.VtwoPrivateLessonsBeans;
import com.hongyuan.fitness.util.BaseUtil;

import java.util.List;

public class CourseSearchViewModel extends CustomViewModel {

    private final int PRIVITE_TYPE = 0X01;
    private final int GROUP_TYPE = 0X02;
    private int check_type = PRIVITE_TYPE;

    private ActivityV3SearchCourseBinding binding;

    private VtwoPrivateLessonsAdapter privateAdapter;
    private List<VtwoPrivateLessonsBeans.DataBean.ListBean> privateList;

    private VtwoGroupClassAdapter groupAdapter;
    private List<VtwoGroupClassBeans.DataBean.ListBean> groupList;

    //搜索条件
    private String search;

    public CourseSearchViewModel(CustomActivity mActivity, ActivityV3SearchCourseBinding binding) {
        super(mActivity);
        this.binding = binding;
        initView();
        searchPriviteCourse();
    }

    @Override
    protected void initView() {
        setEnableLoadMore(true);

        binding.clearText.setOnClickListener(v -> {
            binding.searchContent.setText("");
            search = "";
            if(check_type == PRIVITE_TYPE){
                searchPriviteCourse();
            }else{
                searchGroupCourse();
            }
        });
        binding.finishText.setOnClickListener(v -> mActivity.finish());

        binding.priviteBox.setOnClickListener(v -> {
            check_type = PRIVITE_TYPE;
            binding.priviteText.setTextColor(mActivity.getResources().getColor(R.color.color_EF5B48));
            binding.oBottomLine.setVisibility(View.VISIBLE);
            binding.groupText.setTextColor(mActivity.getResources().getColor(R.color.color_FF999999));
            binding.tBottomLine.setVisibility(View.GONE);

            binding.priviteCourse.setVisibility(View.VISIBLE);
            binding.groupCourse.setVisibility(View.GONE);

            mActivity.refresh.setEnableLoadMore(true);
            if(privateList!=null){
                privateList.clear();
            }
            searchPriviteCourse();
        });
        binding.groupBox.setOnClickListener(v -> {
            check_type = GROUP_TYPE;
            binding.priviteText.setTextColor(mActivity.getResources().getColor(R.color.color_FF999999));
            binding.oBottomLine.setVisibility(View.GONE);
            binding.groupText.setTextColor(mActivity.getResources().getColor(R.color.color_EF5B48));
            binding.tBottomLine.setVisibility(View.VISIBLE);

            binding.priviteCourse.setVisibility(View.GONE);
            binding.groupCourse.setVisibility(View.VISIBLE);

            mActivity.refresh.setEnableLoadMore(true);
            if(groupList != null){
                groupList.clear();
            }
            searchGroupCourse();
        });

        LinearLayoutManager priviteManager = new LinearLayoutManager(mActivity);
        priviteManager.setOrientation(RecyclerView.VERTICAL);
        binding.priviteCourse.setLayoutManager(priviteManager);
        privateAdapter = new VtwoPrivateLessonsAdapter();
        binding.priviteCourse.setAdapter(privateAdapter);
        privateAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @SingleClick
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                Bundle bundle = new Bundle();
                bundle.putString("cp_id",String.valueOf(privateList.get(position).getCp_id()));
                startActivity(CourseDetailsActivity.class,bundle);
            }
        });


        LinearLayoutManager groupManager = new LinearLayoutManager(mActivity);
        groupManager.setOrientation(RecyclerView.VERTICAL);
        binding.groupCourse.setLayoutManager(groupManager);
        groupAdapter = new VtwoGroupClassAdapter();
        binding.groupCourse.setAdapter(groupAdapter);
        groupAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @SingleClick
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                Bundle bundle = new Bundle();
                bundle.putString("cs_id",String.valueOf(groupList.get(position).getCs_id()));
                startActivity(MissionDetailActivity.class,bundle);
            }
        });

        binding.searchContent.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(BaseUtil.isValue(s.toString())){
                    search = s.toString();
                    if(check_type == PRIVITE_TYPE){
                        searchPriviteCourse();
                    }else{
                        searchGroupCourse();
                    }
                }
            }
        });

    }

    @Override
    protected void loadMoreData() {
        if(check_type == PRIVITE_TYPE){
            searchPriviteCourse();
        }else{
            searchGroupCourse();
        }
    }

    private void searchPriviteCourse(){
        clearParams();
        if(BaseUtil.isValue(search)){
            setParams("search_name",search);
        }
        //请求私教课
        Controller.myRequest(Constants.GET_COURSE_PRIVITE_LIST,Controller.TYPE_POST,getParams(), VtwoPrivateLessonsBeans.class,this);
    }

    private void searchGroupCourse(){
        clearParams().setParams("search_name",search);
        //请求团课
        Controller.myRequest(Constants.GET_TWO_COURSE_SUPER_LIST,Controller.TYPE_POST,getParams(), VtwoGroupClassBeans.class,this);
    }

    @Override
    public void onSuccess(Object data) {
        if(data instanceof  VtwoPrivateLessonsBeans){
            List<VtwoPrivateLessonsBeans.DataBean.ListBean> list = ((VtwoPrivateLessonsBeans)data).getData().getList();
            if(curPage == FIRST_PAGE){
                privateList = list;
            }else{
                if(list != null && list.size() > 0){
                    privateList.addAll(list);
                }else{
                    mActivity.refresh.finishLoadMoreWithNoMoreData();
                }
            }

            if(privateList != null && privateList.size() > 0){
                privateAdapter.setNewData(privateList);
                binding.priviteCourse.setVisibility(View.VISIBLE);
                binding.loadBox.setVisibility(View.GONE);
            }else{
                binding.loadBox.setVisibility(View.VISIBLE);
                binding.priviteCourse.setVisibility(View.GONE);
            }
        }

        if(data instanceof  VtwoGroupClassBeans){
            List<VtwoGroupClassBeans.DataBean.ListBean> list = ((VtwoGroupClassBeans)data).getData().getList();
            if(curPage == FIRST_PAGE){
                groupList = list;
            }else{
                if(list != null && list.size() > 0){
                    groupList.addAll(list);
                }else{
                    mActivity.refresh.finishLoadMoreWithNoMoreData();
                }
            }

            if(groupList != null && groupList.size() > 0){
                groupAdapter.setNewData(groupList);
                binding.groupCourse.setVisibility(View.VISIBLE);
                binding.loadBox.setVisibility(View.GONE);
            }else{
                binding.loadBox.setVisibility(View.VISIBLE);
                binding.groupCourse.setVisibility(View.GONE);
            }
        }
    }
}
