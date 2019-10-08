package com.hongyuan.fitness.ui.person.person_message;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewTreeObserver;

import androidx.recyclerview.widget.GridLayoutManager;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.Constants;
import com.hongyuan.fitness.base.ConstantsCode;
import com.hongyuan.fitness.base.Controller;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.base.CustomViewModel;
import com.hongyuan.fitness.base.SingleClick;
import com.hongyuan.fitness.custom_view.StickyScrollView;
import com.hongyuan.fitness.databinding.ActivityPersonMessageBinding;
import com.hongyuan.fitness.ui.find.circle.post_details.AttentionBean;
import com.hongyuan.fitness.ui.find.circle.post_details.PostDetailsActivity;
import com.hongyuan.fitness.ui.find.circle.post_details.PostDetailsLikeBean;
import com.hongyuan.fitness.ui.find.friends.FriendsActivity;
import com.hongyuan.fitness.ui.main.main_find.featured.FeatureBean;
import com.hongyuan.fitness.ui.main.main_find.featured.V2FindContentAdapter;
import com.hongyuan.fitness.ui.person.edit_information.EditInformationActivity;
import com.hongyuan.fitness.ui.person.my_fan.MyFansActivity;
import com.hongyuan.fitness.util.CustomDialog;
import com.hongyuan.fitness.util.ViewChangeUtil;

public class PersonMessageViewModel extends CustomViewModel implements StickyScrollView.ScrollViewListener{

    private ActivityPersonMessageBinding binding;
    private PersonMessageBeans.DataBean personMessageBeans;
    private V2FindContentAdapter adapter;
    private FeatureBean featureBean;

    //渐变高度
    private int height;
    //当前（点赞/取消点赞/关注/取消关注）等操作的数据位置
    private int mPosition;

    //别人的主页数据
    private PersonAttentionBeans attentionBeans;

    public PersonMessageViewModel(CustomActivity mActivity, ActivityPersonMessageBinding binding) {
        super(mActivity);
        this.binding = binding;
        initView();
        getPersonCircle();
    }

    @Override
    protected void initView() {
        setEnableRefresh(true);
        setEnableLoadMore(true);

        if(getBundle() != null && getBundle().getSerializable("otherPerson") != null){
            attentionBeans = (PersonAttentionBeans) getBundle().getSerializable("otherPerson");
            binding.myTitle.setCentreText(attentionBeans.getM_name()+"的主页");
            if(attentionBeans.getIs_friend() == 1){
                binding.myTitle.setRightText("取消关注");
            }else{
                binding.myTitle.setRightText("关注Ta");
            }

        }

        GridLayoutManager layoutManager = new GridLayoutManager(mActivity, 2);
        binding.mRecycler.setLayoutManager(layoutManager);
        adapter = new V2FindContentAdapter();
        binding.mRecycler.setAdapter(adapter);

        adapter.setOnItemChildClickListener((adapter, view, position) -> {
            if(view.getId() == R.id.jumpDetails){
                Bundle bundle = new Bundle();
                bundle.putString("circle_id",String.valueOf(featureBean.getData().getList().get(position).getCircle_id()));
                startActivity(PostDetailsActivity.class,bundle);
            }
            if(view.getId() == R.id.attention){
                //帖子点赞/取消点赞
                getBaikeLike(position);
            }
        });

        binding.myTitle.getRightView().setOnClickListener(new View.OnClickListener() {
            @SingleClick
            @Override
            public void onClick(View v) {
                if(attentionBeans == null){
                    startActivity(EditInformationActivity.class,null);
                }else{
                    if(attentionBeans.getIs_friend() == 1){
                        CustomDialog.promptDialog(mActivity, "确定要取消关注吗？", "暂不取消", "取消关注", false, v1 -> {
                            if(v1.getId() == R.id.isCannel){
                                sendAttention();
                            }
                        });
                    }else{
                        sendAttention();
                    }
                }
            }
        });

        binding.myGuanZhu.setOnClickListener(new View.OnClickListener() {
            @SingleClick
            @Override
            public void onClick(View v) {
                if(attentionBeans == null){
                    startActivity(FriendsActivity.class,null);
                }
            }
        });
        binding.fans.setOnClickListener(new View.OnClickListener() {
            @SingleClick
            @Override
            public void onClick(View v) {
                if(attentionBeans == null){
                    startActivity(MyFansActivity.class,null);
                }
            }
        });

        //设置滚动监听
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

                binding.mScroll.setScrollViewListener(PersonMessageViewModel.this);
            }
        });
    }

    @Override
    public void onScrollChanged(StickyScrollView scrollView, int x, int y, int oldx, int oldy) {
        if (y <= 0) {   //设置标题的背景颜色
            binding.titleBox.setBackgroundColor(Color.argb((int) 0, 239,91,72));
        } else if (y > 0 && y <= height) {
            float scale = (float) y / height;
            float alpha = (255 * scale);
            //binding.titleBox.setTextColor(Color.argb((int) alpha, 255,255,255));
            binding.titleBox.setBackgroundColor(Color.argb((int) alpha, 239,91,72));
        } else {
            binding.titleBox.setBackgroundColor(Color.argb( 255, 239,91,72));
        }
    }

    @Override
    protected void setData() {
        RequestOptions options = new RequestOptions().placeholder(R.mipmap.default_head_img).error(R.mipmap.default_head_img);
        Glide.with(mActivity).load(personMessageBeans.getMi_head()).apply(options).into(binding.headImg);
        binding.userName.setText(personMessageBeans.getM_name());
        binding.userAddress.setText(personMessageBeans.getArea());
        binding.userSignature.setText(personMessageBeans.getMi_sign());
        binding.attentionNum.setText(String.valueOf(personMessageBeans.getGz_num()));
        binding.fanNum.setText(String.valueOf(personMessageBeans.getFs_num()));

        if(personMessageBeans.getMi_sex() == 1){
            ViewChangeUtil.changeRightDrawable(mActivity,binding.userName,R.mipmap.person_boby_mark_img);
        }else{
            ViewChangeUtil.changeRightDrawable(mActivity,binding.userName,R.mipmap.person_girl_mark_img);
        }
    }

    @Override
    public void refreshData(){
        featureBean = null;
        getPersonCircle();
    }

    @Override
    protected void loadMoreData() {
        getPersonCircle();
    }

    @Override
    protected void lazyLoad() {
        if(attentionBeans != null){
            clearParams().setParams("other_m_id",String.valueOf(attentionBeans.getM_id()));
            Controller.myRequest(Constants.CIRCLE_OTHER_MEMBER_INDEX,Controller.TYPE_POST,getParams(), PersonMessageBeans.class,this);
        }else{
            clearParams();
            Controller.myRequest(Constants.CIRCLE_MEMBER_INDEX,Controller.TYPE_POST,getParams(), PersonMessageBeans.class,this);
        }
    }

    /*
    * 请求个人的帖子列表
    * */
    private void getPersonCircle(){
        if(attentionBeans != null){
            clearParams().setParams("other_m_id",String.valueOf(attentionBeans.getM_id()));
            Controller.myRequest(Constants.GET_OTHER_MEMBER_CIRCLE_LIST,Controller.TYPE_POST,getParams(), FeatureBean.class,this);
        }else{
            clearParams();
            Controller.myRequest(Constants.GET_MEMBER_CIRCLE_LIST,Controller.TYPE_POST,getParams(), FeatureBean.class,this);
        }

    }

    /*
     * 帖子点赞/取消
     * */
    private void getBaikeLike(int position){
        mActivity.showLoading();
        mPosition = position;
        clearParams().setParams("circle_id",String.valueOf(featureBean.getData().getList().get(position).getCircle_id()));
        if(featureBean.getData().getList().get(position).getIs_praise() == 0){
            Controller.myRequest(ConstantsCode.ADD_CIRCLE_PRAISE,Constants.ADD_CIRCLE_PRAISE,Controller.TYPE_POST,getParams(), PostDetailsLikeBean.class,this);
        }else{
            Controller.myRequest(ConstantsCode.CANCEL_CIRCLE_PRAISE,Constants.CANCEL_CIRCLE_PRAISE,Controller.TYPE_POST,getParams(), PostDetailsLikeBean.class,this);
        }
    }

    /*
     *关注、取消
     * */
    private void sendAttention(){
        mActivity.showLoading();
        clearParams().setParams("f_mid",String.valueOf(attentionBeans.getM_id()));
        if(attentionBeans.getIs_friend() == 1){
            setParams("f_type","reduce");
        }else{
            setParams("f_type","add");
        }
        Controller.myRequest(ConstantsCode.ADD_FRIEND,Constants.ADD_FRIEND,Controller.TYPE_POST,getParams(), AttentionBean.class,this);
    }

    @Override
    public void onSuccess(Object data) {
        if(data instanceof PersonMessageBeans){
            personMessageBeans = ((PersonMessageBeans)data).getData();
            setData();
        }

        if(data instanceof FeatureBean && isSuccess(data)){
            FeatureBean pageData = (FeatureBean)data;
            if(curPage == FIRST_PAGE){
                if(pageData.getData().getList() != null && pageData.getData().getList().size() > 0){
                    featureBean = pageData;
                }
            }else{
                if(pageData.getData().getList() != null && pageData.getData().getList().size() > 0){
                    featureBean.getData().getList().addAll(pageData.getData().getList());
                }
            }

            if(featureBean != null && featureBean.getData() != null &&
                    featureBean.getData().getList() != null &&
                    featureBean.getData().getList().size() > 0){
                adapter.setNewData(featureBean.getData().getList());
                binding.loadBox.setVisibility(View.GONE);
                binding.mRecycler.setVisibility(View.VISIBLE);
            }else{
                binding.loadBox.setVisibility(View.VISIBLE);
                binding.mRecycler.setVisibility(View.GONE);
            }

        }
    }

    /*
     * 需要做区分走这里
     * */
    @Override
    public void onSuccess(int code, Object data) {
        mActivity.closeLoading();

        if(code == ConstantsCode.ADD_CIRCLE_PRAISE){
            featureBean.getData().getList().get(mPosition).setIs_praise(1);
            featureBean.getData().getList().get(mPosition).setPraise_num(featureBean.getData().getList().get(mPosition).getPraise_num()+1);
            adapter.setNewData(featureBean.getData().getList());
            showSuccess("点赞成功！");
        }
        if(code == ConstantsCode.CANCEL_CIRCLE_PRAISE){
            featureBean.getData().getList().get(mPosition).setIs_praise(0);
            featureBean.getData().getList().get(mPosition).setPraise_num(featureBean.getData().getList().get(mPosition).getPraise_num()-1);
            adapter.setNewData(featureBean.getData().getList());
            showSuccess("已取消点赞！");
        }

        if(code == ConstantsCode.ADD_FRIEND){
            if(attentionBeans.getIs_friend() == 1){
                attentionBeans.setIs_friend(0);
                binding.myTitle.setRightText("关注Ta");
                showSuccess("取关成功！");
            }else{
                attentionBeans.setIs_friend(1);
                binding.myTitle.setRightText("取消关注");
                showSuccess("关注成功！");
            }
            adapter.notifyDataSetChanged();
        }
    }
}
