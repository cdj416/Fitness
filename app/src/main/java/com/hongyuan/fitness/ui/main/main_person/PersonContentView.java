package com.hongyuan.fitness.ui.main.main_person;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.Constants;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.ui.main.TokenSingleBean;
import com.hongyuan.fitness.ui.person.fix.SixPriviteCourseActivity;
import com.hongyuan.fitness.ui.person.newedition.activity.GroupCourseOrdersActivity;
import com.hongyuan.fitness.ui.person.newedition.activity.MemberCardOrdersActivity;
import com.hongyuan.fitness.ui.person.newedition.activity.PriviteCourseOrdersActivity;
import com.hongyuan.fitness.ui.shop.sactivity.ShopNewOrderAcitivity;
import com.hongyuan.fitness.ui.webview.WebViewActivity;

public class PersonContentView extends LinearLayout {

    private CustomActivity mActivity;

    private TextView titleName,rightText,tv1,tv2,tv3,tv6,tv5,tv4;
    private ImageView iv1,iv2,iv3,iv6,iv5,iv4;
    private LinearLayout box1,box2,box3,box4,box5,box6,secondBox;

    //显示类型
    private int showType;
    private final int ORDER = 0;
    private final int RUN = 1;
    private final int RACE = 2;

    public PersonContentView(Context context) {
        super(context);
        initLayoutView();
    }

    public PersonContentView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        if(context instanceof CustomActivity){
            mActivity = (CustomActivity)context;
        }
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.PersonContentView);
        showType = a.getInt(R.styleable.PersonContentView_pcvType,0);

        initLayoutView();
    }

    public PersonContentView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initLayoutView();
    }

    public void initLayoutView(){
        View view = View.inflate(getContext(), R.layout.view_person_content, this);
        titleName = view.findViewById(R.id.titleName);
        rightText = findViewById(R.id.rightText);
        tv1 = view.findViewById(R.id.tv1);
        tv2 = view.findViewById(R.id.tv2);
        tv3 = view.findViewById(R.id.tv3);
        iv1 = view.findViewById(R.id.iv1);
        iv2 = view.findViewById(R.id.iv2);
        iv3 = view.findViewById(R.id.iv3);
        box1 = view.findViewById(R.id.box1);
        box2 = view.findViewById(R.id.box2);
        box3 = view.findViewById(R.id.box3);
        box4 = view.findViewById(R.id.box4);
        box5 = view.findViewById(R.id.box5);
        tv6 = view.findViewById(R.id.tv6);
        tv5 = view.findViewById(R.id.tv5);
        iv4 = view.findViewById(R.id.iv4);
        iv6 = view.findViewById(R.id.iv6);
        iv5 = view.findViewById(R.id.iv5);
        tv4 = view.findViewById(R.id.tv4);
        box6 = view.findViewById(R.id.box6);
        secondBox = view.findViewById(R.id.secondBox);

        setShowView(showType);
    }

    /*
    * 初始化显示类型
    * */
    private void setShowView(int showType){
        if(showType == ORDER){
            titleName.setText("订单服务");
            tv1.setText("会员卡订单");
            iv1.setImageResource(R.mipmap.member_order_mark);
            tv2.setText("私教课订单");
            iv2.setImageResource(R.mipmap.pcourse_mark);
            tv3.setText("场馆订单");
            iv3.setImageResource(R.mipmap.venue_order_mark);
            tv4.setText("培训课订单");
            iv4.setImageResource(R.mipmap.training_class_mark);
            tv5.setText("商城订单");
            iv5.setImageResource(R.mipmap.shop_mark);
            box6.setVisibility(INVISIBLE);
            box1.setOnClickListener(v -> {
                mActivity.startActivity(MemberCardOrdersActivity.class,null);
            });
            box2.setOnClickListener(v -> {
                //mActivity.startActivity(MineOrderActivity.class,null);
                mActivity.startActivity(PriviteCourseOrdersActivity.class,null);
            });
            box3.setOnClickListener(v -> {
                Bundle bundle = new Bundle();
                bundle.putString("url", Constants.WEB_ADDRESS+"/practice_order"+TokenSingleBean.getInstance().getWebAllParams(""));
                bundle.putString("title","场馆订单");
                mActivity.startActivity(WebViewActivity.class,bundle);
            });
            box4.setOnClickListener(v -> {
                Bundle bundle = new Bundle();
                bundle.putString("url", Constants.WEB_ADDRESS+"/train_orderList"+TokenSingleBean.getInstance().getWebAllParams(""));
                bundle.putString("title","培训课订单");
                mActivity.startActivity(WebViewActivity.class,bundle);
            });
            box5.setOnClickListener(v -> {
                mActivity.startActivity(ShopNewOrderAcitivity.class,null);
            });
        }

        if(showType == RUN){
            titleName.setText("运动服务");
            rightText.setVisibility(GONE);
            tv1.setText("我的私教课");
            iv1.setImageResource(R.mipmap.my_pcourse_order_mark);
            tv2.setText("我报名的团课");
            iv2.setImageResource(R.mipmap.my_group_courser_mark);
            tv3.setText("我的约运动");
            iv3.setImageResource(R.mipmap.my_yuyue_yundong_mark);
            secondBox.setVisibility(GONE);
            box1.setOnClickListener(v -> {
                //mActivity.startActivity(MyPriviteCourseActivity.class,null);
                mActivity.startActivity(SixPriviteCourseActivity.class,null);
            });
            box2.setOnClickListener(v -> {
                //mActivity.startActivity(WaitingForClassActivity.class,null);
                mActivity.startActivity(GroupCourseOrdersActivity.class,null);
            });
            box3.setOnClickListener(v -> {
                Bundle bundle = new Bundle();
                bundle.putString("url", Constants.WEB_ADDRESS+"/my_sportList"+TokenSingleBean.getInstance().getWebAllParams(""));
                bundle.putString("title","我的约运动");
                mActivity.startActivity(WebViewActivity.class,bundle);
            });

        }

        if(showType == RACE){
            secondBox.setVisibility(GONE);
            titleName.setText("赛事服务");
            rightText.setVisibility(GONE);
            tv1.setText("我是运动达人");
            iv1.setImageResource(R.mipmap.my_yundongdaren_marik);
            tv2.setText("我的队伍");
            iv2.setImageResource(R.mipmap.my_duiwu_marik);
            tv3.setText("我的赛事");
            iv3.setImageResource(R.mipmap.my_saishi_mark);
            box1.setOnClickListener(v -> {
                Bundle bundle = new Bundle();
                bundle.putString("url", Constants.WEB_ADDRESS+"/sports_talent"+TokenSingleBean.getInstance().getWebAllParams(""));
                bundle.putString("title","我是运动达人");
                mActivity.startActivity(WebViewActivity.class,bundle);
            });
            box2.setOnClickListener(v -> {
                Bundle bundle = new Bundle();
                bundle.putString("url", Constants.WEB_ADDRESS+"/mygroup"+TokenSingleBean.getInstance().getWebAllParams(""));
                //bundle.putString("url", Constants.WEB_ADDRESS+"/mygroup?m_id="+ TokenSingleBean.getInstance().getM_id()+"&m_mobile="+TokenSingleBean.getInstance().getM_mobile());
                bundle.putString("title","我的队伍");
                mActivity.startActivity(WebViewActivity.class,bundle);
            });
            box3.setOnClickListener(v -> {
                Bundle bundle = new Bundle();
                bundle.putString("url", Constants.WEB_ADDRESS+"/myevent"+TokenSingleBean.getInstance().getWebAllParams(""));
                bundle.putString("title","我的赛事");
                mActivity.startActivity(WebViewActivity.class,bundle);

            });
        }
    }
}
