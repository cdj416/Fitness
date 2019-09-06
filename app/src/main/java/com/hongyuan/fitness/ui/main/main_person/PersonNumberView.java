package com.hongyuan.fitness.ui.main.main_person;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.base.SingleClick;
import com.hongyuan.fitness.ui.membership_card.MembershipCardActivity;
import com.hongyuan.fitness.util.CustomDialog;

public class PersonNumberView extends LinearLayout implements View.OnClickListener {

    private TextView cardNum,balance,coupon;
    private LinearLayout cardBox,couponBox;

    public PersonNumberView(Context context) {
        super(context);
        initLayoutView();
    }

    public PersonNumberView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initLayoutView();
    }

    public PersonNumberView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initLayoutView();
    }

    public void initLayoutView(){
        View view = View.inflate(getContext(), R.layout.view_person_number, this);
        cardNum = view.findViewById(R.id.cardNum);
        balance = view.findViewById(R.id.balance);
        coupon = view.findViewById(R.id.coupon);
        cardBox = view.findViewById(R.id.cardBox);
        couponBox = view.findViewById(R.id.couponBox);

        cardBox.setOnClickListener(this);
        couponBox.setOnClickListener(this);
    }

    public void setData(PersonBean.DataBean.InfoBean infoBean){
        cardNum.setText(String.valueOf(infoBean.getCard_count()));
    }

    @SingleClick(2000)
    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.cardBox){
            ((CustomActivity)getContext()).startActivity(MembershipCardActivity.class);
        }
        if(v.getId() == R.id.couponBox){
            CustomDialog.showMessage(getContext(),"暂无可用优惠券");
        }
    }
}
