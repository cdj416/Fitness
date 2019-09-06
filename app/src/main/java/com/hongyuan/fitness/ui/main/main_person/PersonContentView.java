package com.hongyuan.fitness.ui.main.main_person;

import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.SingleClick;
import com.hongyuan.fitness.ui.about_class.privite_class.my_privite_course.MyPriviteCourseActivity;
import com.hongyuan.fitness.ui.mall.mine.mine_order.MineOrderActivity;
import com.hongyuan.fitness.ui.person.about_us.AboutUsActivity;
import com.hongyuan.fitness.ui.person.mine_message.MineMessageActivity;
import com.hongyuan.fitness.ui.person.mine_point.MinePointActivity;
import com.hongyuan.fitness.ui.person.setting.SettingActivity;
import com.hongyuan.fitness.ui.person.waiting_evaluation.WaitingEvaluationActivity;
import com.hongyuan.fitness.ui.person.waiting_for_class.WaitingForClassActivity;

public class PersonContentView extends LinearLayout implements View.OnClickListener {

    private TextView titleName,rightText,tv1,tv2,tv3;
    private ImageView iv1,iv2,iv3;
    private LinearLayout box1,box2,box3,box4,box5,secondBox;

    //显示类型
    private int showType;
    private final int MY_COURSE = 0;
    private final int MY_SERVICE = 1;

    public PersonContentView(Context context) {
        super(context);
        initLayoutView();
    }

    public PersonContentView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.PersonContentView);
        showType = a.getInt(R.styleable.PersonContentView_person_show_type,0);

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
        secondBox = view.findViewById(R.id.secondBox);

        box1.setOnClickListener(this);
        box2.setOnClickListener(this);
        box3.setOnClickListener(this);
        box4.setOnClickListener(this);
        box5.setOnClickListener(this);
        setShowView(showType);
    }

    /*
    * 初始化显示类型
    * */
    private void setShowView(int showType){
        if(showType == MY_COURSE){
            secondBox.setVisibility(GONE);
            titleName.setText("我的课程");
        }

        if(showType == MY_SERVICE){
            titleName.setText("我的服务");
            rightText.setVisibility(GONE);
            tv1.setText("我的订单");
            tv2.setText("我的消息");
            tv3.setText("设置");
            iv1.setImageResource(R.mipmap.wodedingdan_img);
            iv2.setImageResource(R.mipmap.xiaoxi_img);
            iv3.setImageResource(R.mipmap.shezhi_img);
            secondBox.setVisibility(VISIBLE);
        }
    }

    @SingleClick(2000)
    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()){

            case R.id.box1:
                if(showType == MY_SERVICE){
                    intent.setClass(getContext(), MineOrderActivity.class);
                    getContext().startActivity(intent);
                }else{
                    intent.setClass(getContext(), MyPriviteCourseActivity.class);
                    getContext().startActivity(intent);
                }
                break;

            case R.id.box2:
                if(showType == MY_SERVICE){
                    intent.setClass(getContext(), MineMessageActivity.class);
                    getContext().startActivity(intent);
                }else{
                    intent.setClass(getContext(), WaitingForClassActivity.class);
                    getContext().startActivity(intent);
                }

                break;

            case R.id.box3:
                if(showType == MY_COURSE) {
                    intent.setClass(getContext(), WaitingEvaluationActivity.class);
                    getContext().startActivity(intent);
                }else{
                    intent.setClass(getContext(), SettingActivity.class);
                    getContext().startActivity(intent);
                }
                break;

            case R.id.box4:
                intent.setClass(getContext(), MinePointActivity.class);
                getContext().startActivity(intent);
                break;
            case R.id.box5:
                intent.setClass(getContext(), AboutUsActivity.class);
                getContext().startActivity(intent);
                break;
        }
    }
}
