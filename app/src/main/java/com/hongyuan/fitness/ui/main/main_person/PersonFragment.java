package com.hongyuan.fitness.ui.main.main_person;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.Constants;
import com.hongyuan.fitness.base.Controller;
import com.hongyuan.fitness.base.CustomFragment;
import com.hongyuan.fitness.base.MessageEvent;
import com.hongyuan.fitness.custom_view.TitleView;
import com.hongyuan.fitness.ui.person.daily_punch.DailyPunchActivity;
import com.hongyuan.fitness.ui.person.mine_message.MineMessageActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class PersonFragment extends CustomFragment{

    private PersonHeaderView headView;
    private TitleView myTitle;
    private ImageView pushMark,messageMark;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_person;
    }

    @Override
    public void initView(View mView) {

        headView = mView.findViewById(R.id.headView);
        myTitle = mView.findViewById(R.id.myTitle);
        pushMark = mView.findViewById(R.id.pushMark);
        messageMark = mView.findViewById(R.id.messageMark);
        myTitle.getRightView().setOnClickListener(v -> startActivity(DailyPunchActivity.class,null));
        pushMark.setOnClickListener(v -> mActivity.startActivity(DailyPunchActivity.class,null));
        messageMark.setOnClickListener(v -> mActivity.startActivity(MineMessageActivity.class,null));
    }

    @Override
    protected void lazyLoad() {
        if(mActivity.userToken.getM_id() != null){
            //读取个人信息
            clearParams();
            Controller.myRequest(Constants.GET_MEMBER_INDEX_INFO,Controller.TYPE_POST,getParams(),PersonBean.class,this);
        }
    }


    /*
     * 执行刷新
     * */
    @Override
    public void refreshData() {
        if(mActivity.userToken.getM_id() != null){
            //读取个人信息
            clearParams();
            Controller.myRequest(Constants.GET_MEMBER_INDEX_INFO,Controller.TYPE_POST,getParams(),PersonBean.class,this);
        }
    }

    @Override
    public void onSuccess(Object data) {
        if(data instanceof PersonBean){
            PersonBean personBean = (PersonBean)data;
            headView.setHeadImg(personBean.getData().getInfo());
        }
    }



    /*
     * 登录成功去刷新数据
     * */
    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void onEvent(MessageEvent message) {
        if(message.getDataBean() == null){//登录成功之后需要去改变的数据
            //请求下数据
            onFragmentFirstVisible();
        }
    }
    //EventBus.getDefault().postSticky(new MessageEvent(null));

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        EventBus.getDefault().register(this);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(this);
    }
}
