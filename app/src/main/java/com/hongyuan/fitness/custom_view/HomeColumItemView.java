package com.hongyuan.fitness.custom_view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.base.SingleClick;
import com.hongyuan.fitness.ui.encyclopedia.EncyclopediaActivity;
import com.hongyuan.fitness.ui.heat.HeatActivity;
import com.hongyuan.fitness.ui.membership_card.MembershipCardActivity;

public class HomeColumItemView extends LinearLayout implements View.OnClickListener {

    private LinearLayout memberCardBox,encyclopediaBox,outdoorBox,heatBox;
    private CustomActivity mActivity;

    //点击的运动
    public final static int RUN_CLICK = 0X1;
    //点击的热量的
    public final static int RUN_HEAT = 0X2;

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
        heatBox = view.findViewById(R.id.heatBox);

        memberCardBox.setOnClickListener(this);
        encyclopediaBox.setOnClickListener(this);
        outdoorBox.setOnClickListener(this);
        heatBox.setOnClickListener(this);
    }



    @SingleClick(2000)
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.memberCardBox:
                mActivity.startActivity(MembershipCardActivity.class);
                break;
            case R.id.encyclopediaBox:
                mActivity.startActivity(EncyclopediaActivity.class);
                break;
            case R.id.outdoorBox:
                if(clickReturn != null){
                    clickReturn.itemClick(RUN_CLICK);
                }
                break;
            case R.id.heatBox:
                if(clickReturn != null){
                    clickReturn.itemClick(RUN_HEAT);
                }
                break;
        }
    }
}
