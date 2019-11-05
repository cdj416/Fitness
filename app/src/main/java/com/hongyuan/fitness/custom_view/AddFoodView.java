package com.hongyuan.fitness.custom_view;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.material.tabs.TabLayout;
import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.SingleClick;
import com.hongyuan.fitness.custom_view.ruler_selector.RulerView;
import com.hongyuan.fitness.ui.heat.add_food.AddFoodBean;
import com.hongyuan.fitness.util.BaseUtil;
import com.makeramen.roundedimageview.RoundedImageView;

public class AddFoodView extends LinearLayout implements View.OnClickListener {
    private RulerView rulerOne,rulerTwo;
    private TabLayout layoutMenu;
    private RoundedImageView foodImg;
    private TextView foodName,foodNum,foodCal,selectCal,selectNum,submit;
    private AddFoodBean.DataBean.ListBean data;

    private Dialog dialog;

    private String fuId = "0";
    private String fe_num = "100";
    private String selNum = "0.0";

    /*
    * 选择结果回调
    * */
    public interface SelectData{
        void retrunEatingStr(String f_id,String fu_id,String fe_num,String selNum);
    }
    private SelectData selectData;

    public AddFoodView(Context context) {
        super(context);
        initLayoutView();
    }

    public AddFoodView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initLayoutView();
    }

    public AddFoodView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
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

        submit.setOnClickListener(this);
    }

    /*
    * 设置数据
    * */
    public void setData(AddFoodBean.DataBean.ListBean data,SelectData selectData,Dialog dialog){
        this.selectData = selectData;
        this.data = data;
        this.dialog = dialog;

        RequestOptions options = new RequestOptions().placeholder(R.mipmap.zhengfangxing_default_img).error(R.mipmap.zhengfangxing_default_img).centerCrop();
        Glide.with(getContext()).load(data.getF_img()).apply(options).into(foodImg);
        foodName.setText(data.getF_name());
        foodNum.setText(100+(data.getF_type() == 1 ? "毫升" :"克"));
        foodCal.setText((int) (100*Float.valueOf(data.getF_cal()))+"千卡");
        selectCal.setText((int) (100*Float.valueOf(data.getF_cal()))+"千卡");
        selectNum.setText(100+(data.getF_type() == 1 ? "毫升" :"克"));

        setStyleType(1,data.getF_cal(),data.getF_type(),0);

        layoutMenu.addTab(layoutMenu.newTab().setText(data.getF_type() == 1 ? "克" :"毫升"));
        for(AddFoodBean.DataBean.ListBean.FuBean fuBean : data.getFu()){
            layoutMenu.addTab(layoutMenu.newTab().setText(fuBean.getFu_name()));
        }
        //tablayout的回调
        layoutMenu.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if(!"克".equals(tab.getText()) && !"毫升".equals(tab.getText())){

                    for(AddFoodBean.DataBean.ListBean.FuBean fuBean : data.getFu()){
                        if(fuBean.getFu_name().equals(tab.getText())){
                            try {
                                int fuNum = (int) Math.floor(Double.valueOf(fuBean.getFu_num()));
                                setStyleType(2,data.getF_cal(),data.getF_type(),fuNum);
                                fuId = String.valueOf(fuBean.getFu_id());
                            }catch (Exception e){
                                e.printStackTrace();
                            }

                        }
                    }
                }else{
                    fuId = "0";
                    setStyleType(1,data.getF_cal(),data.getF_type(),0);
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    /*
    * 尺码选择器的样式
    * styleType 1表示，克，毫升，单位 2表示，碗，瓶，等为单位
    * */
    @SuppressLint("SetTextI18n")
    private void setStyleType(int styleType,String F_cal,int F_type, int Fu_num){
        if(styleType == 1){
            rulerTwo.setVisibility(GONE);
            rulerOne.setVisibility(VISIBLE);

            selNum = "0.0";
            fe_num = BaseUtil.getNoZoon(100);
            //设置尺码选择器的基础值
            rulerOne.setValue(100, 0, 1000, 1);
            selectCal.setText((int) (100 * Float.valueOf(F_cal)) + "千卡");
            selectNum.setText(100+(F_type == 1 ? "毫升" :"克"));
            //尺码选择器的回调
            rulerOne.setOnValueChangeListener(value -> {
                fe_num = BaseUtil.getNoZoon(value);
                selectCal.setText((int) (value * Float.valueOf(F_cal)) + "千卡");
                selectNum.setText(BaseUtil.getNoZoon(value)+(F_type == 1 ? "毫升" :"克"));
            });
        }else{
            rulerOne.setVisibility(GONE);
            rulerTwo.setVisibility(VISIBLE);

            selNum = "1";
            fe_num = BaseUtil.getNoZoon(Fu_num);
            rulerTwo.setValue(1, 0, 100, 1);
            selectCal.setText((int) (1 * Fu_num * Float.valueOf(F_cal)) + "千卡");
            selectNum.setText(1 * Fu_num +(F_type == 1 ? "毫升" :"克"));
            //尺码选择器的回调
            rulerTwo.setOnValueChangeListener(value -> {
                selNum = String.valueOf((int) value);
                fe_num = BaseUtil.getNoZoon(value * Fu_num);
                selectCal.setText((int) (value * Fu_num * Float.valueOf(F_cal)) + "千卡");
                selectNum.setText(BaseUtil.getNoZoon(value * Fu_num) +(F_type == 1 ? "毫升" :"克"));
            });
        }
    }


    @SingleClick(2000)
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.submit:
                dialog.dismiss();
                if(selectData != null){
                    selectData.retrunEatingStr(String.valueOf(data.getF_id()),fuId,fe_num,selNum);
                }
                break;
        }
    }
}
