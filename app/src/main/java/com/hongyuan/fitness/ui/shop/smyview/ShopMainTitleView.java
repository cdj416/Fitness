package com.hongyuan.fitness.ui.shop.smyview;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import com.hongyuan.fitness.R;
import com.hongyuan.fitness.util.BaseUtil;

public class ShopMainTitleView extends LinearLayout {

    private TextView titleView;
    private String titleName;
    public ShopMainTitleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.ShopMainTitleView);
        titleName = a.getString(R.styleable.ShopMainTitleView_smTitleName);
        initLayoutView();
    }

    public void initLayoutView() {
        View view = View.inflate(getContext(), R.layout.view_shop_main_title, this);
        titleView = view.findViewById(R.id.titleName);
        if(BaseUtil.isValue(titleName)){
            titleView.setText(titleName);
        }
    }
}
