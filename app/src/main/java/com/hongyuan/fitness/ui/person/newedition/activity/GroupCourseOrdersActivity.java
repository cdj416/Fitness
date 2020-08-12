package com.hongyuan.fitness.ui.person.newedition.activity;

import android.os.Bundle;
import android.view.KeyEvent;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.ConstantsCode;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.databinding.AcitivityGroupCourseOrdersBinding;
import com.hongyuan.fitness.ui.main.MainActivity;
import com.hongyuan.fitness.ui.person.newedition.viewmodel.GroupCourseOrdersViewModel;
import com.hongyuan.fitness.util.SkinConstants;

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
        if(SkinConstants.SKIN_NAME.DEFAULT.equals(skin))
            setTitleBar(TYPE_BAR5,R.drawable.theme_shape_soid_ffffff,"我报名的团课");
        if(SkinConstants.SKIN_NAME.BLACK.equals(skin))
            setTitleBar(TYPE_BAR9,R.drawable.theme_shape_soid_ffffff_black,"我报名的团课");

        AcitivityGroupCourseOrdersBinding binding = AcitivityGroupCourseOrdersBinding.bind(mView);
        viewModel = new GroupCourseOrdersViewModel(this,binding);
        binding.setViewModel(viewModel);
    }

    @Override
    protected void onResume() {
        super.onResume();
        viewModel.refreshData();
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

    @Override
    public void finish() {
        EventBus.getDefault().post(ConstantsCode.EB_START_COURSE,"3");
        startActivity(MainActivity.class);
        super.finish();
    }

    //安卓重写返回键事件
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode==KeyEvent.KEYCODE_BACK){
            EventBus.getDefault().post(ConstantsCode.EB_START_COURSE,"3");
            startActivity(MainActivity.class);
            return false;
        }
        return true;
    }
}
