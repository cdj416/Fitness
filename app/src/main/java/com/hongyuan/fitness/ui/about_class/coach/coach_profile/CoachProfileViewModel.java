package com.hongyuan.fitness.ui.about_class.coach.coach_profile;

import android.graphics.Color;
import android.view.ViewTreeObserver;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.Constants;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.base.CustomViewModel;
import com.hongyuan.fitness.custom_view.StickyScrollView;
import com.hongyuan.fitness.databinding.ActivityCoachProfileBinding;
import com.hongyuan.fitness.ui.about_class.coach.coach_homepage.CoachHomeBean;
import com.hongyuan.fitness.util.DividerItemDecoration;
import com.hongyuan.fitness.util.ViewChangeUtil;

public class CoachProfileViewModel extends CustomViewModel implements StickyScrollView.ScrollViewListener {

    private ActivityCoachProfileBinding binding;
    private CoachHomeBean.DataBean coachBean;

    //渐变高度
    private int height;


    public CoachProfileViewModel(CustomActivity mActivity, ActivityCoachProfileBinding binding) {
        super(mActivity);
        this.binding = binding;
        initView();
    }

    @Override
    protected void initView() {
        coachBean = (CoachHomeBean.DataBean)mActivity.getIntentData("coach");

        if(coachBean == null){
            mActivity.finish();
            return;
        }

        String headUrl;
        if(coachBean.getInfo().getCoach_head().contains("http")){
            headUrl = coachBean.getInfo().getCoach_head();
        }else{
            headUrl = Constants.ADRESS + coachBean.getInfo().getCoach_head();
        }
        RequestOptions options = new RequestOptions().placeholder(R.mipmap.default_head_img).error(R.mipmap.default_head_img).centerCrop();
        Glide.with(mActivity).load(headUrl).apply(options).into(binding.headImg);
        binding.coachName.setText(coachBean.getInfo().getCoach_nickname());
        binding.coachGrade.setText("私教专业P"+coachBean.getInfo().getCoach_level());
        binding.experienceText.setText(coachBean.getInfo().getCoach_experience());
        binding.goodAt.setText(coachBean.getInfo().getCoach_skill());
        binding.certificationNUm.setText(coachBean.getCerts().size()+"项");

        if(coachBean.getInfo().getMi_sex() == 1){
            ViewChangeUtil.changeRightDrawable(mActivity,binding.coachName,R.mipmap.person_boby_mark_img);
        }else{
            ViewChangeUtil.changeRightDrawable(mActivity,binding.coachName,R.mipmap.person_girl_mark_img);
        }

        //认证项
        LinearLayoutManager layoutManager = new LinearLayoutManager(mActivity);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        binding.mRecycler.setLayoutManager(layoutManager);
        binding.mRecycler.addItemDecoration(new DividerItemDecoration(
                mActivity, DividerItemDecoration.HORIZONTAL_LIST,30,mActivity.getResources().getColor(R.color.transparent)));
        CerterAdapter mtAdapter = new CerterAdapter();
        binding.mRecycler.setAdapter(mtAdapter);
        mtAdapter.setNewData(coachBean.getCerts());

        initListeners();
    }

    /*
     * 获取头部图片的高度
     * */
    private void initListeners() {
        ViewTreeObserver vto = binding.bgImg.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                binding.titleBox.getViewTreeObserver().removeGlobalOnLayoutListener(
                        this);
                height = binding.bgImg.getHeight();

                binding.nScroll.setScrollViewListener(CoachProfileViewModel.this);
            }
        });
    }

    @Override
    public void onScrollChanged(StickyScrollView scrollView, int x, int y, int oldx, int oldy) {
        if (y <= 0) {   //设置标题的背景颜色
            binding.titleBox.setBackgroundColor(Color.argb((int) 0, 99,130,236));
        } else if (y > 0 && y <= height) {
            float scale = (float) y / height;
            float alpha = (255 * scale);
            //binding.titleBox.setTextColor(Color.argb((int) alpha, 255,255,255));
            binding.titleBox.setBackgroundColor(Color.argb((int) alpha, 99,130,236));
        } else {
            binding.titleBox.setBackgroundColor(Color.argb( 255, 99,130,236));
        }
    }

    @Override
    public void onSuccess(Object data) {

    }
}
