package com.hongyuan.fitness.ui.main.main_person;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.Constants;
import com.hongyuan.fitness.base.Controller;
import com.hongyuan.fitness.base.CustomFragment;
import com.hongyuan.fitness.base.MessageEvent;
import com.hongyuan.fitness.custom_view.TitleView;
import com.hongyuan.fitness.custom_view.share_view.ShareUtil;
import com.hongyuan.fitness.ui.main.TokenSingleBean;
import com.hongyuan.fitness.ui.only_equipment.indicator_details.IndicatorDetailsActivity;
import com.hongyuan.fitness.ui.person.about_us.AboutUsActivity;
import com.hongyuan.fitness.ui.person.daily_punch.DailyPunchActivity;
import com.hongyuan.fitness.ui.person.exercise_data.ExeriseDataActivity;
import com.hongyuan.fitness.ui.person.mine_message.MineMessageActivity;
import com.hongyuan.fitness.ui.person.my_collection.MyCollectionActivity;
import com.hongyuan.fitness.ui.person.my_coupon.MyCouponActivity;
import com.hongyuan.fitness.ui.person.my_coupon.newcoupon.NewCouponActivity;
import com.hongyuan.fitness.ui.person.my_promote.PromotionCodeActivity;
import com.hongyuan.fitness.ui.person.physical_data.PhysicalDataActivity;
import com.hongyuan.fitness.ui.person.setting.SettingActivity;
import com.hongyuan.fitness.ui.shop.sactivity.CheckInMeActivity;
import com.hongyuan.fitness.ui.shop.sactivity.CustomServerActivity;
import com.hongyuan.fitness.ui.shop.sactivity.IncomeMangeActivity;
import com.hongyuan.fitness.ui.shop.sactivity.ShopCollectActivity;
import com.hongyuan.fitness.util.TimeUtil;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class PersonFragment extends CustomFragment{

    private PersonHeaderView pHeadView;
    private TitleView myTitle;
    private TextView weightNum,weightDate,calories,exerciseDays;
    private ImageView messageMark;

    private LinearLayout personCouponBox,personCollectionBox,personShareBox,personSettingBox
            ,personAboutUsBox,physicalDataBox,exerciseDataBox,promotionCodeBox,shopCollectBox
            ,checkInMeBox,goIncomeBox,goSheBei,goCustomServer;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_person;
    }

    @Override
    public void initView(View mView) {

        pHeadView = mView.findViewById(R.id.pHeadView);
        myTitle = mView.findViewById(R.id.myTitle);
        messageMark = mView.findViewById(R.id.messageMark);
        myTitle.getRightView().setOnClickListener(v -> startActivity(DailyPunchActivity.class,null));
        messageMark.setOnClickListener(v -> mActivity.startActivity(MineMessageActivity.class,null));

        personCouponBox = mView.findViewById(R.id.personCouponBox);
        personCollectionBox = mView.findViewById(R.id.personCollectionBox);
        personShareBox = mView.findViewById(R.id.personShareBox);
        personSettingBox = mView.findViewById(R.id.personSettingBox);
        personAboutUsBox = mView.findViewById(R.id.personAboutUsBox);
        promotionCodeBox = mView.findViewById(R.id.promotionCodeBox);
        shopCollectBox = mView.findViewById(R.id.shopCollectBox);
        checkInMeBox = mView.findViewById(R.id.checkInMeBox);
        goIncomeBox = mView.findViewById(R.id.goIncomeBox);
        goSheBei = mView.findViewById(R.id.goSheBei);
        goCustomServer = mView.findViewById(R.id.goCustomServer);

        physicalDataBox = mView.findViewById(R.id.physicalDataBox);
        exerciseDataBox = mView.findViewById(R.id.exerciseDataBox);

        weightNum = mView.findViewById(R.id.weightNum);
        weightDate = mView.findViewById(R.id.weightDate);
        calories = mView.findViewById(R.id.calories);
        exerciseDays = mView.findViewById(R.id.exerciseDays);

        personCouponBox.setOnClickListener(v -> {
            //startActivity(MyCouponActivity.class,null);
            startActivity(NewCouponActivity.class,null);
        });
        personCollectionBox.setOnClickListener(v -> startActivity(MyCollectionActivity.class,null));
        personShareBox.setOnClickListener(v -> ShareUtil.showShare(mActivity));
        personSettingBox.setOnClickListener(v -> startActivity(SettingActivity.class,null));
        personAboutUsBox.setOnClickListener(v -> startActivity(AboutUsActivity.class,null));
        promotionCodeBox.setOnClickListener(v -> startActivity(PromotionCodeActivity.class,null));
        shopCollectBox.setOnClickListener(v -> startActivity(ShopCollectActivity.class,null));
        goSheBei.setOnClickListener(v -> {
            Bundle bundle2 = new Bundle();
            bundle2.putInt("showPosition",0);
            mActivity.startActivity(IndicatorDetailsActivity.class,bundle2);
        });

        physicalDataBox.setOnClickListener(v -> startActivity(PhysicalDataActivity.class,null));
        exerciseDataBox.setOnClickListener(v -> startActivity(ExeriseDataActivity.class,null));
        checkInMeBox.setOnClickListener(v -> startActivity(CheckInMeActivity.class,null));
        goIncomeBox.setOnClickListener(v -> startActivity(IncomeMangeActivity.class,null));
        goCustomServer.setOnClickListener(v -> startActivity(CustomServerActivity.class,null));

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
            pHeadView.setHeadImg(personBean.getData().getInfo());
            TokenSingleBean.getInstance().setHeadUrl(personBean.getData().getInfo().getMi_head());

            if("暂未数据".equals(personBean.getData().getInfo().getWeight())){
                weightNum.setText("0.00");
            }else{
                weightNum.setText(personBean.getData().getInfo().getWeight());
            }
            if("暂未数据".equals(personBean.getData().getInfo().getWeight_date())){
                weightDate.setText("未记录");
            }else{
                weightDate.setText("上次记录"+TimeUtil.friendly_time(personBean.getData().getInfo().getWeight_date()));
            }

            calories.setText(String.valueOf(personBean.getData().getInfo().getCalories()));
            exerciseDays.setText("总训练天数"+personBean.getData().getInfo().getExercise_days()+"天");
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
