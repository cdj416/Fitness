package com.hongyuan.fitness.ui.main.main_person;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.ui.membership_card.MembershipCardActivity;
import com.hongyuan.fitness.ui.person.person_message.PersonMessageActivity;
import com.hongyuan.fitness.ui.store.store_page_list.StoreActivity;
import com.makeramen.roundedimageview.RoundedImageView;

public class PersonHeaderView extends LinearLayout {

    private RoundedImageView headImg;
    private TextView userName,statusText,buttonText;

    public PersonHeaderView(Context context) {
        super(context);
        initLayoutView();
    }

    public PersonHeaderView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initLayoutView();
    }

    public PersonHeaderView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initLayoutView();
    }

    public void initLayoutView(){
        View view = View.inflate(getContext(), R.layout.view_person_header, this);
        headImg = view.findViewById(R.id.headImg);
        userName = view.findViewById(R.id.userName);
        statusText = view.findViewById(R.id.statusText);
        buttonText = view.findViewById(R.id.buttonText);

        headImg.setOnClickListener(v -> {
            ((CustomActivity)getContext()).startActivity(PersonMessageActivity.class);
            //upHeadimg();
        });
    }

    public void setHeadImg(PersonBean.DataBean.InfoBean bean){
        RequestOptions options = new RequestOptions().placeholder(R.mipmap.default_head_img).error(R.mipmap.default_head_img).centerCrop();
        Glide.with(getContext()).load(bean.getMi_head()).apply(options).into(headImg);
        userName.setText(bean.getM_name());
        if(bean.getCard_count() > 0){
            buttonText.setText("查看");
            statusText.setText(bean.getCard_count()+"张会籍卡");
        }else{
            buttonText.setText("去查看");
            statusText.setText("暂无会籍卡");
        }

        buttonText.setOnClickListener(v -> {
            if(bean.getCard_count() > 0){
                ((CustomActivity)getContext()).startActivity(MembershipCardActivity.class);
            }else{
                ((CustomActivity)getContext()).startActivity(StoreActivity.class);
            }

        });
    }
}
