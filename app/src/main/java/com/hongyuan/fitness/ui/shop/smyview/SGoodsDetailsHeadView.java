package com.hongyuan.fitness.ui.shop.smyview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.annotation.Nullable;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.BaseBean;
import com.hongyuan.fitness.util.CustomDialog;

import java.util.ArrayList;
import java.util.List;

public class SGoodsDetailsHeadView extends LinearLayout {

    private RelativeLayout parameterBox,couponBox;
    private LinearLayout specificationBox;

    public SGoodsDetailsHeadView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initLayoutView();
    }

    public void initLayoutView() {
        View view = View.inflate(getContext(), R.layout.view_s_detail_header, this);

        couponBox = view.findViewById(R.id.couponBox);
        parameterBox = view.findViewById(R.id.parameterBox);
        specificationBox = view.findViewById(R.id.specificationBox);


        //优惠卷弹框
        couponBox.setOnClickListener(v -> {
            CustomDialog.showSGDCoupon(getContext());
        });
        //参数弹框
        parameterBox.setOnClickListener(v -> {
            CustomDialog.showGoodsParameter(getContext());
        });
        //规格弹框
        specificationBox.setOnClickListener(v -> {
            CustomDialog.showGoodsSpecification(getContext());
        });
    }

    private List<BaseBean> getList(){
        List<BaseBean> mList = new ArrayList<>();
        for(int i = 0 ; i < 2 ; i++){
            mList.add(new BaseBean());
        }
        return mList;
    }
}
