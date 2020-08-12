package com.hongyuan.fitness.custom_view;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.util.BaseUtil;
import com.hongyuan.fitness.util.CustomDialog;
import com.hongyuan.fitness.util.SkinConstants;

public class ItemCloumView extends LinearLayout {

    private CustomActivity mContext;

    private String leftText,rightText;
    private boolean isShow,icShowCopy;
    private int rightColor,LeftColor;

    private TextView titleName,content,copyTv;
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
        icShowCopy = a.getBoolean(R.styleable.ItemCloumView_icShowCopy,false);
        rightColor = a.getColor(R.styleable.ItemCloumView_icRightColor,getResources().getColor(R.color.color_FF333333));
        LeftColor = a.getColor(R.styleable.ItemCloumView_icLeftColor,getResources().getColor(R.color.color_FF999999));

        if(context instanceof CustomActivity){
            mContext = (CustomActivity) context;
        }

        initView();
    }


    private void initView(){
        View view = View.inflate(getContext(), R.layout.view_item_cloum, this);
        titleName = view.findViewById(R.id.titleName);
        content = view.findViewById(R.id.content);
        copyTv = view.findViewById(R.id.copyTv);
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
        if(BaseUtil.isValue(LeftColor)){
            titleName.setTextColor(LeftColor);
        }
        if(icShowCopy){
            copyTv.setVisibility(VISIBLE);
        }

        copyTv.setOnClickListener(v -> {
            //获取剪贴板管理器：
            ClipboardManager cm = (ClipboardManager) getContext().getSystemService(Context.CLIPBOARD_SERVICE);
            // 创建普通字符型ClipData
            ClipData mClipData = ClipData.newPlainText("Label", content.getText().toString());
            // 将ClipData内容放到系统剪贴板里。
            cm.setPrimaryClip(mClipData);
            CustomDialog.showMessage(getContext(),"复制成功！");
        });

        if(mContext.skin.equals(SkinConstants.SKIN_NAME.DEFAULT)){
            content.setTextColor(mContext.getResources().getColor(R.color.theme_color3));
        }
        if(mContext.skin.equals(SkinConstants.SKIN_NAME.BLACK)){
            content.setTextColor(mContext.getResources().getColor(R.color.theme_color1));
        }
    }

    public void setRightText(String contentText){
        content.setText(contentText);
    }

}
