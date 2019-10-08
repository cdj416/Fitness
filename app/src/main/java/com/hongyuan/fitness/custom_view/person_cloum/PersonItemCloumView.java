package com.hongyuan.fitness.custom_view.person_cloum;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.base.SingleClick;
import com.hongyuan.fitness.custom_view.share_view.ShareUtil;
import com.hongyuan.fitness.ui.mall.mine.mine_order.MineOrderActivity;
import com.hongyuan.fitness.ui.person.about_us.AboutUsActivity;
import com.hongyuan.fitness.ui.person.daily_punch.DailyPunchActivity;
import com.hongyuan.fitness.ui.person.my_collection.MyCollectionActivity;
import com.hongyuan.fitness.ui.person.push_share.PushShareActivity;
import com.hongyuan.fitness.ui.person.setting.SettingActivity;
import com.hongyuan.fitness.util.BaseUtil;

public class PersonItemCloumView extends LinearLayout {

    private CustomActivity mActivity;

    private TextView leftView,rightView;
    private Drawable leftImageDrawble;
    private Drawable rightImageDrawble;
    private String leftText;
    private String rightText;
    private int itemType;

    private RelativeLayout box;

    public PersonItemCloumView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        if(context instanceof CustomActivity){
            mActivity = (CustomActivity)context;
        }

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.PersonItem);
        leftImageDrawble = a.getDrawable(R.styleable.PersonItem_leftDrawable);
        rightImageDrawble = a.getDrawable(R.styleable.PersonItem_rightDrawable);
        leftText = a.getString(R.styleable.PersonItem_leftText);
        rightText = a.getString(R.styleable.PersonItem_rightText);
        itemType = a.getInt(R.styleable.PersonItem_itemType,0);

        View view = View.inflate(getContext(), R.layout.view_v3_person_cloum, this);
        leftView = view.findViewById(R.id.leftView);
        rightView = view.findViewById(R.id.rightView);
        box = view.findViewById(R.id.box);

        if(BaseUtil.isValue(leftText)){
            leftView.setText(leftText);
        }
        if(BaseUtil.isValue(rightText)){
            rightView.setText(rightText);
        }

        if(leftImageDrawble != null){
            leftImageDrawble.setBounds(0,0,leftImageDrawble.getMinimumWidth(),leftImageDrawble.getMinimumHeight());
            leftView.setCompoundDrawables(leftImageDrawble,null,null,null);
        }
        if(rightImageDrawble != null){
            rightImageDrawble.setBounds(0,0,rightImageDrawble.getMinimumWidth(),rightImageDrawble.getMinimumHeight());
            rightView.setCompoundDrawables(null,null,rightImageDrawble,null);
        }


        box.setOnClickListener(new OnClickListener() {
            @SingleClick
            @Override
            public void onClick(View v) {
                if(itemType == 0){
                    mActivity.startActivity(DailyPunchActivity.class,null);
                }
                if(itemType == 1){
                    mActivity.startActivity(MyCollectionActivity.class,null);
                }
                if(itemType == 2){
                    mActivity.startActivity(MineOrderActivity.class,null);
                }
                if(itemType == 3){
                    //mActivity.startActivity(MineOrderActivity.class,null);
                    ShareUtil.showShare(mActivity);
                }
                if(itemType == 4){
                    mActivity.startActivity(SettingActivity.class,null);
                }
                if(itemType == 5){
                    mActivity.startActivity(AboutUsActivity.class,null);
                }
            }
        });
    }

    /*
    * 设置右边文字
    * */
    public void setRightText(String text){
        rightView.setText(text);
    }
}
