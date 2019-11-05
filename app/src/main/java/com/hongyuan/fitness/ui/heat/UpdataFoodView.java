package com.hongyuan.fitness.ui.heat;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.material.tabs.TabLayout;
import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.custom_view.ruler_selector.RulerView;
import com.hongyuan.fitness.ui.heat.heat_detail.HeatDetailActivity;
import com.hongyuan.fitness.ui.heat.heat_detail.HeatDetailBean;
import com.hongyuan.fitness.util.BaseUtil;
import com.makeramen.roundedimageview.RoundedImageView;

public class UpdataFoodView extends LinearLayout implements View.OnClickListener {

    private RulerView rulerOne,rulerTwo;
    private TabLayout layoutMenu;
    private RoundedImageView foodImg;
    private TextView foodName,foodNum,foodCal,selectCal,selectNum,submit,tvDelet;
    private RelativeLayout detailBox;

    private ItemClikeBean showData;

    private Dialog dialog;

    private String fuId = "0";
    //修改后需要上传的克数或者毫升数
    private String fe_num = "100";
    private String selNum = "0.0";

    /*
     * 修改结果回调
     * */
    public interface UpdataData{
        void updataStr(String fe_id,String fe_num,String fe_ge,String fu_id);
        void detelFood(String fe_id);
    }
    private UpdataData updataData;

    public UpdataFoodView(Context context) {
        super(context);
        initLayoutView();
    }

    public UpdataFoodView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initLayoutView();
    }

    public UpdataFoodView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initLayoutView();
    }

    @SuppressLint("ClickableViewAccessibility")
    public void initLayoutView() {
        View view = View.inflate(getContext(), R.layout.view_add_food, this);
        rulerOne = view.findViewById(R.id.rulerOne);
        rulerTwo = view.findViewById(R.id.rulerTwo);
        layoutMenu = view.findViewById(R.id.layoutMenu);
        foodImg = view.findViewById(R.id.foodImg);
        foodName = view.findViewById(R.id.foodName);
        foodNum = view.findViewById(R.id.foodNum);
        foodCal = view.findViewById(R.id.foodCal);
        selectCal = view.findViewById(R.id.selectCal);
        selectNum = view.findViewById(R.id.selectNum);
        submit = view.findViewById(R.id.submit);
        tvDelet = view.findViewById(R.id.tvDelet);
        detailBox = view.findViewById(R.id.detailBox);

        tvDelet.setVisibility(VISIBLE);
        submit.setText("确认修改");
        submit.setOnClickListener(this);
        tvDelet.setOnClickListener(this);
        detailBox.setOnClickListener(this);
    }

    /*
     * 设置展示页传过来的
     * */
    @SuppressLint("SetTextI18n")
    public void setHeatData(ItemClikeBean setData, HeatDetailBean.DataBean detailBean, UpdataData updataData, Dialog dialog){
        this.updataData = updataData;
        this.dialog = dialog;
        this.showData = setData;

        //初始化一下
        fe_num = setData.getFe_num();
        selNum = String.valueOf(setData.getFe_ge());

        String F_cal = BaseUtil.getNoZoon(Double.valueOf(setData.getFe_cal())/Double.valueOf(setData.getFe_num()));

        RequestOptions options = new RequestOptions().placeholder(R.mipmap.zhengfangxing_default_img).error(R.mipmap.zhengfangxing_default_img).centerCrop();
        Glide.with(getContext()).load(setData.getF_img()).apply(options).into(foodImg);
        foodName.setText(setData.getF_name());
        foodNum.setText(setData.getFe_num()+(setData.getF_type() == 1 ? "毫升" :"克"));
        foodCal.setText(setData.getFe_cal()+"千卡");
        selectCal.setText(setData.getFe_cal()+"千卡");
        selectNum.setText(setData.getFe_num()+(setData.getF_type() == 1 ? "毫升" :"克"));

        double mmp = Double.valueOf(setData.getFe_num());
        //初始化去
        setStyleType(setData.getFe_ge() >0 ? 2 : 1,F_cal,setData.getF_type(),0,setData.getFe_ge(),(int)mmp);

        //加载一个默认项
        layoutMenu.addTab(layoutMenu.newTab().setText(setData.getF_type() == 1 ? "克" :"毫升"));
        int mySelectPosition = 0;
        for(int i = 0 ; i < detailBean.getFu().size() ; i++){
            layoutMenu.addTab(layoutMenu.newTab().setText(detailBean.getFu().get(i).getFu_name()));
            //当前单位所对应的fu_num数量
            int oneNum = Integer.valueOf(BaseUtil.getNoZoon(detailBean.getFu().get(i).getFu_num()));
            //计算当前加餐量除以个体数之后对应的数量
            int twoNum = 0;
            if(setData.getFe_ge() != 0)
            twoNum = Integer.valueOf(BaseUtil.getNoZoon(setData.getFe_num())) / setData.getFe_ge();
            //两个值想同，直接设为选中状态
            if(oneNum == twoNum){
                //因为已经有一项了，所有下标往后移一步
                mySelectPosition = i+1;
            }
        }

        //tablayout的回调
        layoutMenu.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if(!"克".equals(tab.getText()) && !"毫升".equals(tab.getText())){

                    for(HeatDetailBean.DataBean.FuBean fuBean : detailBean.getFu()){
                        if(fuBean.getFu_name().equals(tab.getText())){
                            try {
                                int fuNum = (int) Math.floor(Double.valueOf(fuBean.getFu_num()));
                                setStyleType(2,F_cal,setData.getF_type(),fuNum,setData.getFe_ge(),(int)mmp);
                                fuId = String.valueOf(fuBean.getFu_id());
                            }catch (Exception e){
                                e.printStackTrace();
                            }

                        }
                    }
                }else{
                    fuId = "0";
                    setStyleType(1,F_cal,setData.getF_type(),0,setData.getFe_ge(),(int)mmp);
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        layoutMenu.getTabAt(mySelectPosition).select();
    }

    /*
     * 尺码选择器的样式
     * styleType 1表示，克，毫升，单位 2表示，碗，瓶，等为单位
     * F_cal:热量倍数
     * F_type：固体还是液体
     * Fu_num：一碗，一瓶的数量
     * geShu：碗，瓶，等的数量
     * myFe_num：当前未改变的数量，也就是初始数量
     * */
    @SuppressLint("SetTextI18n")
    private void setStyleType(int styleType,String F_cal,int F_type, int Fu_num,int geShu,int myFe_num){
        if(styleType == 1){
            rulerTwo.setVisibility(GONE);
            rulerOne.setVisibility(VISIBLE);

            selNum = "0.0";
            fe_num = BaseUtil.getNoZoon(myFe_num);
            //设置尺码选择器的基础值
            rulerOne.setValue(myFe_num, 0, 1000, 1);
            selectCal.setText((int) (myFe_num * Float.valueOf(F_cal)) + "千卡");
            selectNum.setText(myFe_num+(F_type == 1 ? "毫升" :"克"));
            //尺码选择器的回调
            rulerOne.setOnValueChangeListener(value -> {
                fe_num = BaseUtil.getNoZoon(value);
                selectCal.setText((int) (value * Float.valueOf(F_cal)) + "千卡");
                selectNum.setText(BaseUtil.getNoZoon(value)+(F_type == 1 ? "毫升" :"克"));
            });
        }else{
            rulerOne.setVisibility(GONE);
            rulerTwo.setVisibility(VISIBLE);

            selNum = String.valueOf(geShu);
            fe_num = BaseUtil.getNoZoon(myFe_num);
            rulerTwo.setValue(geShu, 0, 100, 1);
            selectCal.setText((int) (geShu * Fu_num * Float.valueOf(F_cal)) + "千卡");
            selectNum.setText(geShu * Fu_num +(F_type == 1 ? "毫升" :"克"));
            //尺码选择器的回调
            rulerTwo.setOnValueChangeListener(value -> {
                selNum = String.valueOf((int) value);
                fe_num = BaseUtil.getNoZoon(value * Fu_num);
                selectCal.setText((int) (value * Fu_num * Float.valueOf(F_cal)) + "千卡");
                selectNum.setText(BaseUtil.getNoZoon(value * Fu_num) +(F_type == 1 ? "毫升" :"克"));
            });
        }
    }

    @Override
    public void onClick(View v) {
        dialog.dismiss();
        switch (v.getId()){
            case R.id.submit:
                if(updataData != null){
                    updataData.updataStr(String.valueOf(showData.getFe_id()),fe_num,selNum,String.valueOf(showData.getFu_id()));
                }
                break;
            case R.id.tvDelet:
                if(updataData != null){
                    updataData.detelFood(String.valueOf(showData.getFe_id()));
                }
                break;
            case R.id.detailBox:
                Bundle bundle = new Bundle();
                bundle.putString("f_id",String.valueOf(showData.getF_id()));
                ((CustomActivity)getContext()).startActivity(HeatDetailActivity.class,bundle);
                break;
        }
    }
}
