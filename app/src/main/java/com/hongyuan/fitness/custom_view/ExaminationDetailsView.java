package com.hongyuan.fitness.custom_view;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.ui.only_equipment.indicator_details.ExaminationDetailsUseData;
import com.hongyuan.fitness.util.BaseUtil;
import com.hongyuan.fitness.util.HiddenAnimUtils;

public class ExaminationDetailsView extends LinearLayout implements View.OnClickListener {

    private RelativeLayout titleBox,gradeLineBox;
    private LinearLayout detailBox,gradeTitleBox,boundaryValueBox;
    private TextView normTitle,normText,levelText,gradeTitle1,gradeTitle2,gradeTitle3,gradeTitle4,
            gradeTitle5,firstBoundaryValue,secondBoundaryValue,firthBoundaryValue,fourthBoundaryValue,des;
    private ImageView upDownImg;
    private View mobileView,gradeLine1,gradeLine2,gradeLine3,gradeLine4,gradeLine5;

    //展开收缩动画工具类
    private HiddenAnimUtils animUtils;
    //详情box的高度
    private int detailBoxHeight;

    public ExaminationDetailsView(Context context) {
        super(context);
        initLayoutView();
    }

    public ExaminationDetailsView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initLayoutView();
    }

    public ExaminationDetailsView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initLayoutView();
    }

    public void initLayoutView(){

        View view = View.inflate(getContext(), R.layout.view_examination_details, this);

        titleBox = view.findViewById(R.id.titleBox);
        gradeLineBox = view.findViewById(R.id.gradeLineBox);
        gradeTitleBox = view.findViewById(R.id.gradeTitleBox);
        boundaryValueBox = view.findViewById(R.id.boundaryValueBox);
        detailBox = view.findViewById(R.id.detailBox);
        normTitle = view.findViewById(R.id.normTitle);
        normText = view.findViewById(R.id.normText);
        levelText = view.findViewById(R.id.levelText);
        firstBoundaryValue = view.findViewById(R.id.firstBoundaryValue);
        secondBoundaryValue = view.findViewById(R.id.secondBoundaryValue);
        firthBoundaryValue = view.findViewById(R.id.firthBoundaryValue);
        fourthBoundaryValue = view.findViewById(R.id.fourthBoundaryValue);
        des = view.findViewById(R.id.des);
        upDownImg = view.findViewById(R.id.upDownImg);
        mobileView = view.findViewById(R.id.mobileView);
        gradeTitle1 = view.findViewById(R.id.gradeTitle1);
        gradeTitle2 = view.findViewById(R.id.gradeTitle2);
        gradeTitle3 = view.findViewById(R.id.gradeTitle3);
        gradeTitle4 = view.findViewById(R.id.gradeTitle4);
        gradeTitle5 = view.findViewById(R.id.gradeTitle5);
        gradeLine1 = view.findViewById(R.id.gradeLine1);
        gradeLine2 = view.findViewById(R.id.gradeLine2);
        gradeLine3 = view.findViewById(R.id.gradeLine3);
        gradeLine4 = view.findViewById(R.id.gradeLine4);
        gradeLine5 = view.findViewById(R.id.gradeLine5);

        titleBox.setOnClickListener(this);
    }

    /*
    * 设置初始值
    * */
    public void setContent(ExaminationDetailsUseData data){

        normTitle.setText(data.getNormTitle());
        normText.setText(data.getNormText());
        levelText.setText(data.getLevelText());
        setDrawBgColor(levelText,data.getLevelColor());
        if(BaseUtil.isValue(data.getGradeTitle1())){
            gradeTitle1.setText(data.getGradeTitle1());
        }
        if(BaseUtil.isValue(data.getGradeTitle2())){
            gradeTitle2.setText(data.getGradeTitle2());
        }
        if(BaseUtil.isValue(data.getGradeTitle3())){
            gradeTitle3.setText(data.getGradeTitle3());
        }
        if(BaseUtil.isValue(data.getGradeTitle4())){
            gradeTitle4.setText(data.getGradeTitle4());
        }
        if(BaseUtil.isValue(data.getGradeTitle5())){
            gradeTitle5.setText(data.getGradeTitle5());
        }

        if(BaseUtil.isValue(data.getFirstBoundaryValue())){
            firstBoundaryValue.setText(data.getFirstBoundaryValue());
        }
        if(BaseUtil.isValue(data.getSecondBoundaryValue())){
            secondBoundaryValue.setText(data.getSecondBoundaryValue());
        }
        if(BaseUtil.isValue(data.getFirthBoundaryValue())){
            firthBoundaryValue.setText(data.getFirthBoundaryValue());
        }
        if(BaseUtil.isValue(data.getFourthBoundaryValue())){
            fourthBoundaryValue.setText(data.getFourthBoundaryValue());
        }

        if(!BaseUtil.isValue(data.getGradeTitle1())){
            gradeTitle1.setVisibility(GONE);
            gradeTitle2.setVisibility(GONE);
            gradeTitle3.setVisibility(GONE);
            gradeTitle4.setVisibility(GONE);
            gradeTitle5.setVisibility(GONE);
            gradeLine1.setVisibility(GONE);
            gradeLine2.setVisibility(GONE);
            gradeLine3.setVisibility(GONE);
            gradeLine4.setVisibility(GONE);
            gradeLine5.setVisibility(GONE);
            firstBoundaryValue.setVisibility(GONE);
            secondBoundaryValue.setVisibility(GONE);
            firthBoundaryValue.setVisibility(GONE);
            fourthBoundaryValue.setVisibility(GONE);
        }else{
            gradeLineBox.setVisibility(VISIBLE);
            gradeTitleBox.setVisibility(VISIBLE);
            boundaryValueBox.setVisibility(VISIBLE);
        }

        if(!BaseUtil.isValue(data.getGradeTitle3()) && BaseUtil.isValue(data.getGradeTitle2())){
            gradeTitle1.setVisibility(VISIBLE);
            gradeTitle2.setVisibility(VISIBLE);
            gradeTitle3.setVisibility(GONE);
            gradeTitle4.setVisibility(GONE);
            gradeTitle5.setVisibility(GONE);
            gradeLine1.setVisibility(VISIBLE);
            gradeLine2.setVisibility(GONE);
            gradeLine3.setVisibility(GONE);
            gradeLine4.setVisibility(GONE);
            gradeLine5.setVisibility(VISIBLE);
            firstBoundaryValue.setVisibility(VISIBLE);
            secondBoundaryValue.setVisibility(GONE);
            firthBoundaryValue.setVisibility(GONE);
            fourthBoundaryValue.setVisibility(GONE);

            setDrawBgColor(gradeLine1,getColor(data.getGradeTitle1()));
            setDrawBgColor(gradeLine5,getColor(data.getGradeTitle2()));
        }

        if(!BaseUtil.isValue(data.getGradeTitle4()) && BaseUtil.isValue(data.getGradeTitle3())){
            gradeTitle1.setVisibility(VISIBLE);
            gradeTitle2.setVisibility(VISIBLE);
            gradeTitle3.setVisibility(VISIBLE);
            gradeTitle4.setVisibility(GONE);
            gradeTitle5.setVisibility(GONE);
            gradeLine1.setVisibility(VISIBLE);
            gradeLine2.setVisibility(VISIBLE);
            gradeLine3.setVisibility(GONE);
            gradeLine4.setVisibility(GONE);
            gradeLine5.setVisibility(VISIBLE);
            firstBoundaryValue.setVisibility(VISIBLE);
            secondBoundaryValue.setVisibility(VISIBLE);
            firthBoundaryValue.setVisibility(GONE);
            fourthBoundaryValue.setVisibility(GONE);

            setDrawBgColor(gradeLine1,getColor(data.getGradeTitle1()));
            setBgColor(gradeLine2,getColor(data.getGradeTitle2()));
            setDrawBgColor(gradeLine5,getColor(data.getGradeTitle3()));
        }

        if(!BaseUtil.isValue(data.getGradeTitle5()) && BaseUtil.isValue(data.getGradeTitle4())){
            gradeTitle1.setVisibility(VISIBLE);
            gradeTitle2.setVisibility(VISIBLE);
            gradeTitle3.setVisibility(VISIBLE);
            gradeTitle4.setVisibility(VISIBLE);
            gradeTitle5.setVisibility(GONE);
            gradeLine1.setVisibility(VISIBLE);
            gradeLine2.setVisibility(VISIBLE);
            gradeLine3.setVisibility(VISIBLE);
            gradeLine4.setVisibility(GONE);
            gradeLine5.setVisibility(VISIBLE);
            firstBoundaryValue.setVisibility(VISIBLE);
            secondBoundaryValue.setVisibility(VISIBLE);
            firthBoundaryValue.setVisibility(VISIBLE);
            fourthBoundaryValue.setVisibility(GONE);

            setDrawBgColor(gradeLine1,getColor(data.getGradeTitle1()));
            setBgColor(gradeLine2,getColor(data.getGradeTitle2()));
            setBgColor(gradeLine3,getColor(data.getGradeTitle3()));
            setDrawBgColor(gradeLine5,getColor(data.getGradeTitle4()));
        }

        if(BaseUtil.isValue(data.getGradeTitle5())){
            gradeTitle1.setVisibility(VISIBLE);
            gradeTitle2.setVisibility(VISIBLE);
            gradeTitle3.setVisibility(VISIBLE);
            gradeTitle4.setVisibility(VISIBLE);
            gradeTitle5.setVisibility(VISIBLE);
            gradeLine1.setVisibility(VISIBLE);
            gradeLine2.setVisibility(VISIBLE);
            gradeLine3.setVisibility(VISIBLE);
            gradeLine4.setVisibility(VISIBLE);
            gradeLine5.setVisibility(VISIBLE);
            firstBoundaryValue.setVisibility(VISIBLE);
            secondBoundaryValue.setVisibility(VISIBLE);
            firthBoundaryValue.setVisibility(VISIBLE);
            fourthBoundaryValue.setVisibility(VISIBLE);

            setDrawBgColor(gradeLine1,getColor(data.getGradeTitle1()));
            setBgColor(gradeLine2,getColor(data.getGradeTitle2()));
            setBgColor(gradeLine3,getColor(data.getGradeTitle3()));
            setBgColor(gradeLine4,getColor(data.getGradeTitle4()));
            setDrawBgColor(gradeLine5,getColor(data.getGradeTitle5()));
        }

        if(BaseUtil.isValue(data.getDes())){
            des.setText(data.getDes());
            des.setVisibility(VISIBLE);
        }

        //监听设值之后的高度，用于显示隐藏
        ViewTreeObserver vto = detailBox.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                detailBox.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                detailBoxHeight = detailBox.getHeight();
                //初始化动画对象
                animUtils = HiddenAnimUtils.newInstance(getContext(),detailBox,upDownImg,detailBoxHeight,false);
                //隐藏布局
                animUtils.toggle();
            }
        });
    }

    /*
    * 设置控件背景颜色
    * */
    private void setDrawBgColor(View view,String solidColor){
        if(view == null){
            return;
        }
        GradientDrawable gradientDrawable = (GradientDrawable) view.getBackground();
        gradientDrawable.setColor(Color.parseColor(solidColor));
    }

    /*
    * 单独设置控件背景颜色
    * */
    private void setBgColor(View view,String solidColor){
        view.setBackgroundColor(Color.parseColor(solidColor));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.titleBox:
                animUtils.toggle();
                break;
        }
    }

    /*
    * 匹配颜色值
    * */
    private String getColor(String showText){
        switch (showText){
            case "严重偏低":
                return "#3E9FFF";
            case "偏低":
                return "#0DC3D1";
            case "标准":
                return "#1bc050";
            case "偏高": case "充足": case "中度肥胖": case "轻度肥胖":case "超重":
                return "#FFAD3E";
            case "严重偏高": case "严重肥胖":
                return "#E13F14";
        }
        return "#1bc050";
    }
}
