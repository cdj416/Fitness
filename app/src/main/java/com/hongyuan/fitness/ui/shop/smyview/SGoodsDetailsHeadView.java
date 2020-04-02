package com.hongyuan.fitness.ui.shop.smyview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.annotation.Nullable;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.util.CustomDialog;

public class SGoodsDetailsHeadView extends LinearLayout {

    private RelativeLayout parameterBox;

    public SGoodsDetailsHeadView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initLayoutView();
    }

    public void initLayoutView() {
        View view = View.inflate(getContext(), R.layout.view_s_detail_header, this);

        parameterBox = view.findViewById(R.id.parameterBox);


        parameterBox.setOnClickListener(v -> {
            CustomDialog.showGoodsParameter(getContext());
        });
    }
}
