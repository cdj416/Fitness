package com.hongyuan.fitness.custom_view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.util.BaseUtil;

public class ItemCloumView extends LinearLayout {

    private String leftText,rightText;
    private boolean isShow;
    private int rightColor;

    private TextView titleName,content;
    private View line;

    public ItemCloumView(Context context) {
        super(context);
    }

    public ItemCloumView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.ItemCloumView);
        leftText = a.getString(R.styleable.ItemCloumView_icLeftText);
        rightText = a.getString(R.styleable.ItemCloumView_icRightText);
        isShow = a.getBoolean(R.styleable.ItemCloumView_icLine,true);
        rightColor = a.getColor(R.styleable.ItemCloumView_icRightColor,getResources().getColor(R.color.color_FF333333));

        initView();
    }


    private void initView(){
        View view = View.inflate(getContext(), R.layout.view_item_cloum, this);
        titleName = view.findViewById(R.id.titleName);
        content = view.findViewById(R.id.content);
        line = view.findViewById(R.id.line);

        if(BaseUtil.isValue(leftText)){
            titleName.setText(leftText);
        }
        if(BaseUtil.isValue(rightText)){
            content.setText(rightText);
        }
        if(isShow){
            line.setVisibility(VISIBLE);
        }else{
            line.setVisibility(GONE);
        }
        if(BaseUtil.isValue(rightColor)){
            content.setTextColor(rightColor);
        }
    }

}
