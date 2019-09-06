package com.hongyuan.fitness.ui.about_class.coach.coach_homepage;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.hongyuan.fitness.R;
import com.hongyuan.fitness.util.BaseUtil;
import com.hongyuan.fitness.util.CustomDialog;
import com.makeramen.roundedimageview.RoundedImageView;

public class CoachHomePageView extends RelativeLayout{

    private RoundedImageView headImg;
    private TextView coachName,coachGrade,shangKeShu,evaluationNumber;
    private RatingBar myRat;
    private ImageView callTel;
    private CoachHomeBean.DataBean.InfoBean infoBean;

    public CoachHomePageView(Context context) {
        super(context);
        initLayoutView();
    }

    public CoachHomePageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initLayoutView();
    }

    public CoachHomePageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initLayoutView();
    }

    public void initLayoutView(){
        View view = View.inflate(getContext(), R.layout.view_coach_home_page_header, this);
        headImg = view.findViewById(R.id.headImg);
        coachName = view.findViewById(R.id.coachName);
        coachGrade = view.findViewById(R.id.coachGrade);
        shangKeShu = view.findViewById(R.id.shangKeShu);
        evaluationNumber = view.findViewById(R.id.evaluationNumber);
        myRat = view.findViewById(R.id.myRat);
        callTel = view.findViewById(R.id.callTel);

        callTel.setOnClickListener(v -> CustomDialog.callTel(getContext(), infoBean.getMi_contract_mobile(), v1 -> {
            Intent intent=new Intent(Intent.ACTION_CALL, Uri.parse("tel:"+infoBean.getMi_contract_mobile()));
            getContext().startActivity(intent);
        }));
    }


    /*
    * 设置数据
    * */
    public void setData(CoachHomeBean.DataBean.InfoBean item){
        this.infoBean = item;

        RequestOptions options = new RequestOptions().placeholder(R.mipmap.a_test2).error(R.mipmap.a_test2).centerCrop();
        Glide.with(getContext()).load(item.getCoach_head()).apply(options).into(headImg);
        coachName.setText(item.getCoach_nickname());
        coachGrade.setText("私教专业P"+item.getCoach_level());
        shangKeShu.setText(BaseUtil.getNoZoon(item.getCoach_review_count()));
        evaluationNumber.setText(BaseUtil.getNoZoon(item.getCoach_s()));
        myRat.setRating(Float.valueOf(item.getCoach_s()));
    }
}
