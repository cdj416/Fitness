package com.hongyuan.fitness.custom_view;

import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.Constants;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.base.SingleClick;
import com.hongyuan.fitness.ui.encyclopedia.EncyclopediaActivity;
import com.hongyuan.fitness.ui.main.TokenSingleBean;
import com.hongyuan.fitness.ui.membership_card.MembershipCardActivity;
import com.hongyuan.fitness.ui.only_equipment.indicator_details.IndicatorDetailsActivity;
import com.hongyuan.fitness.ui.person.daily_punch.DailyPunchActivity;
import com.hongyuan.fitness.ui.person.daily_punch.DailyPunchCheckBean;
import com.hongyuan.fitness.ui.person.push_share.PushShareActivity;
import com.hongyuan.fitness.ui.store.store_page_list.StoreActivity;
import com.hongyuan.fitness.ui.training_plan.TrainingPlanActivity;
import com.hongyuan.fitness.ui.webview.WebViewActivity;

public class HomeColumItemView extends LinearLayout implements View.OnClickListener {

    private LinearLayout memberCardBox,encyclopediaBox,outdoorBox,heatBox
            ,shareDailyBox,pointsCenterBox,goVue,activityCenterBox,trainingPlanBox;
    private CustomActivity mActivity;

    //分享需要的数据
    private DailyPunchCheckBean.DataBean dailyPunchCheckBean;

    //点击的运动
    public final static int RUN_CLICK = 0X1;
    //点击的热量的
    public final static int RUN_HEAT = 0X2;
    //点击了计划
    public final static int TRAINING_PLAN = 0X3;

    public interface ClickReturn{
        void itemClick(int clockType);
    }
    private ClickReturn clickReturn;
    public void setClickReturn(ClickReturn clickReturn){
        this.clickReturn = clickReturn;
    }

    public HomeColumItemView(Context context) {
        super(context);
        initLayoutView();
    }

    public HomeColumItemView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initLayoutView();
    }

    public HomeColumItemView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initLayoutView();
    }

    public void initLayoutView() {
        mActivity = (CustomActivity)getContext();
        View view = View.inflate(getContext(), R.layout.view_home_colum_item, this);
        memberCardBox = view.findViewById(R.id.memberCardBox);
        encyclopediaBox = view.findViewById(R.id.encyclopediaBox);
        outdoorBox = view.findViewById(R.id.outdoorBox);
        //heatBox = view.findViewById(R.id.heatBox);
        //shareDailyBox = view.findViewById(R.id.shareDailyBox);
        pointsCenterBox = view.findViewById(R.id.pointsCenterBox);
        //activityCenterBox = view.findViewById(R.id.activityCenterBox);
        goVue = view.findViewById(R.id.goVue);
        //trainingPlanBox = view.findViewById(R.id.trainingPlanBox);

        memberCardBox.setOnClickListener(this);
        encyclopediaBox.setOnClickListener(this);
        outdoorBox.setOnClickListener(this);
        //heatBox.setOnClickListener(this);
        //shareDailyBox.setOnClickListener(this);
        pointsCenterBox.setOnClickListener(this);
        goVue.setOnClickListener(this);
        //trainingPlanBox.setOnClickListener(this);

    }

    @SingleClick(2000)
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.memberCardBox:
                mActivity.startActivity(StoreActivity.class,null);
                //mActivity.startActivity(MembershipCardActivity.class);
                break;

            case R.id.encyclopediaBox:
                mActivity.startActivity(EncyclopediaActivity.class);
                break;

            case R.id.outdoorBox:
                if(clickReturn != null){
                    clickReturn.itemClick(RUN_CLICK);
                }
                break;

           /* case R.id.heatBox:
                if(clickReturn != null){
                    clickReturn.itemClick(RUN_HEAT);
                }
                break;*/

            /*case R.id.shareDailyBox:
                mActivity.startActivity(PushShareActivity.class,null);
                break;*/

            case R.id.pointsCenterBox:
                mActivity.startActivity(DailyPunchActivity.class);
                break;

            case R.id.goVue:
                Bundle bundle = new Bundle();
                bundle.putString("url", Constants.WEB_ADDRESS+"/?m_id="+ TokenSingleBean.getInstance().getM_id()+"&m_mobile="+TokenSingleBean.getInstance().getM_mobile());
                bundle.putString("title","场馆首页");
                mActivity.startActivity(WebViewActivity.class,bundle);
                break;

           /* case R.id.trainingPlanBox:
                if(clickReturn != null){
                    clickReturn.itemClick(TRAINING_PLAN);
                }
                break;*/


        }
    }
}
