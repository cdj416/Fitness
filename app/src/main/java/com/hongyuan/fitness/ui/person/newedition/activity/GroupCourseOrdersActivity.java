package com.hongyuan.fitness.ui.person.newedition.activity;

import android.os.Bundle;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.ConstantsCode;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.databinding.AcitivityGroupCourseOrdersBinding;
import com.hongyuan.fitness.ui.person.newedition.viewmodel.GroupCourseOrdersViewModel;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

public class GroupCourseOrdersActivity extends CustomActivity {

    private GroupCourseOrdersViewModel viewModel;

    @Override
    protected int getLayoutId() {
        return R.layout.acitivity_group_course_orders;
    }

    @Override
    protected void initView() {
        setTitleBar(TYPE_BAR5,R.drawable.shape_soid_ffffff,"我报名的团课");

        AcitivityGroupCourseOrdersBinding binding = AcitivityGroupCourseOrdersBinding.bind(mView);
        viewModel = new GroupCourseOrdersViewModel(this,binding);
        binding.setViewModel(viewModel);
    }

    /*
     * 刷新数据
     * */
    @Subscribe(id = ConstantsCode.EB_SUPER_COURSE_XY_QD)
    public void refresh(String message) {
        viewModel.courseQD();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
