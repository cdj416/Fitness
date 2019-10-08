package com.hongyuan.fitness.ui.about_class.course_search;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hongyuan.fitness.base.Constants;
import com.hongyuan.fitness.base.Controller;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.base.CustomViewModel;
import com.hongyuan.fitness.databinding.ActivityV3SearchCourseBinding;
import com.hongyuan.fitness.ui.main.main_about_class.group_class.vtwo_group_class.VtwoGroupClassBeans;
import com.hongyuan.fitness.ui.main.main_about_class.private_lessons.vtwo_private_lessons.VtwoPrivateLessonsAdapter;
import com.hongyuan.fitness.ui.main.main_about_class.private_lessons.vtwo_private_lessons.VtwoPrivateLessonsBeans;

public class CourseSearchViewModel extends CustomViewModel {

    private ActivityV3SearchCourseBinding binding;

    private VtwoPrivateLessonsAdapter privateAdapter;
    private VtwoPrivateLessonsBeans privateLessonsBeans;

    public CourseSearchViewModel(CustomActivity mActivity, ActivityV3SearchCourseBinding binding) {
        super(mActivity);
        this.binding = binding;
        initView();
    }

    @Override
    protected void initView() {
        binding.clearText.setOnClickListener(v -> binding.searchContent.setText(""));
        binding.finishText.setOnClickListener(v -> mActivity.finish());

        LinearLayoutManager manager = new LinearLayoutManager(mActivity);
        manager.setOrientation(RecyclerView.VERTICAL);
        binding.priviteCourse.setLayoutManager(manager);
        privateAdapter = new VtwoPrivateLessonsAdapter();
        binding.priviteCourse.setAdapter(privateAdapter);
    }

    private void searchPriviteCourse(String search){
        clearParams().setParams("search_name",search);
        //请求私教课
        Controller.myRequest(Constants.GET_COURSE_PRIVITE_LIST,Controller.TYPE_POST,getParams(), VtwoPrivateLessonsBeans.class,this);
    }

    private void searchGroupCourse(String search){
        clearParams().setParams("search_name",search);
        //请求团课
        Controller.myRequest(Constants.GET_TWO_COURSE_SUPER_LIST,Controller.TYPE_POST,getParams(), VtwoGroupClassBeans.class,this);
    }

    @Override
    public void onSuccess(Object data) {
        if(data instanceof  VtwoPrivateLessonsBeans){
            privateLessonsBeans = (VtwoPrivateLessonsBeans)data;
            privateAdapter.setNewData(privateLessonsBeans.getData().getList());
        }
    }
}
