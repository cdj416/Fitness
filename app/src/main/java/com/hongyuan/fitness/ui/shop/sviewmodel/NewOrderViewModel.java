package com.hongyuan.fitness.ui.shop.sviewmodel;

import android.util.Log;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.base.CustomViewModel;
import com.hongyuan.fitness.databinding.ActivityNewOrderBinding;
import com.hongyuan.fitness.ui.mall.mine.mine_order.mine_order_list.MineOrderFragment;
import com.hongyuan.fitness.ui.person.waiting_for_class.about_group_course.GroupCourseCheckFragment;

public class NewOrderViewModel extends CustomViewModel {

    private ActivityNewOrderBinding binding;

    //声明本次使用到的java类
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;

    private MineOrderFragment orderFragment;
    private GroupCourseCheckFragment groupCourseCheckFragment;

    public NewOrderViewModel(CustomActivity mActivity, ActivityNewOrderBinding binding) {
        super(mActivity);
        this.binding = binding;
        initView();
    }

    @Override
    protected void initView() {
        mActivity.getMainTitle().setCentreText(getBundle().getString("title"));

        fragmentManager = mActivity.getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();

        orderFragment = new MineOrderFragment();
        groupCourseCheckFragment = new GroupCourseCheckFragment();
        fragmentTransaction.add(R.id.contentBox,orderFragment).add(R.id.contentBox,groupCourseCheckFragment);

        if("group".equals(getBundle().getString("tyep"))){
            //通过添加（事务处理的方式）将fragment加到对应的布局中
            fragmentTransaction.hide(orderFragment).show(groupCourseCheckFragment).commit();
        }else{
            Log.e("cdj","==========这里又是啥========"+getBundle().getString("type"));
            orderFragment.setArguments(getBundle().getString("type"));
            //通过添加（事务处理的方式）将fragment加到对应的布局中
            fragmentTransaction.hide(groupCourseCheckFragment).show(orderFragment).commit();
        }




    }

    @Override
    public void onSuccess(Object data) {

    }
}
