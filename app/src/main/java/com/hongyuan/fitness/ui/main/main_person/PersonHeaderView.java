package com.hongyuan.fitness.ui.main.main_person;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.ui.find.friends.FriendsActivity;
import com.hongyuan.fitness.ui.membership_card.MembershipCardActivity;
import com.hongyuan.fitness.ui.person.daily_punch.DailyPunchActivity;
import com.hongyuan.fitness.ui.person.my_fan.MyFansActivity;
import com.hongyuan.fitness.ui.person.person_message.PersonMessageActivity;
import com.hongyuan.fitness.ui.store.store_page_list.StoreActivity;
import com.hongyuan.fitness.util.BaseUtil;
import com.makeramen.roundedimageview.RoundedImageView;

public class PersonHeaderView extends LinearLayout {

    private CustomActivity mActivity;
    private RoundedImageView headImg;
    private TextView userName,statusText,buttonText,miSign,balance,attentionNum,fansNum,pointNum,goSignIn;
    private RelativeLayout headBox;
    private LinearLayout myFans,myAttention,myDynamic,myPoint;

    public PersonHeaderView(Context context) {
        super(context);

        if(context instanceof CustomActivity){
            mActivity = (CustomActivity) context;
        }
        initLayoutView();
    }

    public PersonHeaderView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        if(context instanceof CustomActivity){
            mActivity = (CustomActivity) context;
        }
        initLayoutView();
    }

    public PersonHeaderView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        if(context instanceof CustomActivity){
            mActivity = (CustomActivity) context;
        }
        initLayoutView();
    }

    public void initLayoutView(){
        View view = View.inflate(getContext(), R.layout.view_person_header, this);
        headBox = view.findViewById(R.id.headBox);
        headImg = view.findViewById(R.id.headImg);
        userName = view.findViewById(R.id.userName);
        statusText = view.findViewById(R.id.statusText);
        buttonText = view.findViewById(R.id.buttonText);
        miSign = view.findViewById(R.id.miSign);
        myFans = view.findViewById(R.id.myFans);
        myAttention = view.findViewById(R.id.myAttention);
        myDynamic = view.findViewById(R.id.myDynamic);
        myPoint = view.findViewById(R.id.myPoint);
        balance = view.findViewById(R.id.balance);
        fansNum = view.findViewById(R.id.fansNum);
        attentionNum = view.findViewById(R.id.attentionNum);
        //dynamicNum = view.findViewById(R.id.dynamicNum);
        pointNum = view.findViewById(R.id.pointNum);
        goSignIn = view.findViewById(R.id.goSignIn);

        headBox.setOnClickListener(v -> {
            ((CustomActivity)getContext()).startActivity(PersonMessageActivity.class);
            //upHeadimg();
        });

        myPoint.setOnClickListener(v -> mActivity.startActivity(DailyPunchActivity.class,null));
        myFans.setOnClickListener(v -> mActivity.startActivity(MyFansActivity.class,null));
        myAttention.setOnClickListener(v -> mActivity.startActivity(FriendsActivity.class,null));
        myDynamic.setOnClickListener(v -> mActivity.startActivity(PersonMessageActivity.class,null));

        goSignIn.setOnClickListener(v -> mActivity.startActivity(DailyPunchActivity.class,null));
    }

    public void setHeadImg(PersonBean.DataBean.InfoBean bean){
        RequestOptions options = new RequestOptions().placeholder(R.mipmap.default_head_img).error(R.mipmap.default_head_img).centerCrop();
        Glide.with(getContext()).load(bean.getMi_head()).apply(options).into(headImg);
        userName.setText(bean.getM_name());
        miSign.setText(bean.getMi_sign());
        balance.setText(BaseUtil.getNoZoon(bean.getMi_money()));
        attentionNum.setText(String.valueOf(bean.getGz_num()));
        fansNum.setText(String.valueOf(bean.getFans_num()));
        pointNum.setText(String.valueOf(bean.getMi_point()));
        if(bean.getCard_count() > 0){
            buttonText.setText("查看");
            statusText.setText(bean.getCard_count()+"张会籍卡");
        }else{
            buttonText.setText("去购买");
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
