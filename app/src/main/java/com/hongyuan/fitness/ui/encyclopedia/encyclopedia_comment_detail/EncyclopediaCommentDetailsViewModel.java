package com.hongyuan.fitness.ui.encyclopedia.encyclopedia_comment_detail;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.Constants;
import com.hongyuan.fitness.base.Controller;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.base.CustomViewModel;
import com.hongyuan.fitness.base.SingleClick;
import com.hongyuan.fitness.custom_view.KeyboardLayout;
import com.hongyuan.fitness.databinding.ActivityEncyclopediaCommentDetailsBinding;
import com.hongyuan.fitness.ui.encyclopedia.encyclopedia_detail.EncyclopediaDetailCommentBean;
import com.hongyuan.fitness.ui.encyclopedia.encyclopedia_detail.EncyclopediaDetailSendMeassageBean;
import com.hongyuan.fitness.util.DividerItemDecoration;
import com.hongyuan.fitness.util.TimeUtil;
import com.hongyuan.fitness.util.ViewChangeUtil;

import me.goldze.mvvmhabit.binding.command.BindingAction;
import me.goldze.mvvmhabit.binding.command.BindingCommand;

import static me.goldze.mvvmhabit.utils.Utils.getContext;

public class EncyclopediaCommentDetailsViewModel extends CustomViewModel implements KeyboardLayout.onKeyboaddsChangeListener {

    private ActivityEncyclopediaCommentDetailsBinding binding;
    private EncyclopediaDetailCommentBean.DataBean.ListBean brBin;
    private EncyclopediaCommentDetailsAdapter adapter;
    private EncyclopediaCommentDetailsBean dataBeans;

    private String br_id_father = "0";//当前帖子的id
    private String first_br_id = "0";//当前帖子的父类id

    public EncyclopediaCommentDetailsViewModel(CustomActivity mActivity, ActivityEncyclopediaCommentDetailsBinding binding) {
        super(mActivity);
        this.binding = binding;
        initView();
        lazyLoad();
    }

    @Override
    protected void initView() {
        //开启刷新
        setEnableRefresh(true);
        //开启加载更多
        setEnableLoadMore(true);

        brBin = (EncyclopediaDetailCommentBean.DataBean.ListBean)mActivity.getIntent().getExtras().getSerializable("brBin");
        br_id_father = String.valueOf(brBin.getBr_id());
        first_br_id = String.valueOf(brBin.getBr_id());

        LinearLayoutManager manager = new LinearLayoutManager(mActivity);
        manager.setOrientation(RecyclerView.VERTICAL);
        binding.mRecycler.addItemDecoration(new DividerItemDecoration(
                getContext(), DividerItemDecoration.HORIZONTAL_LIST,1,0xFFEEEEEE));
        binding.mRecycler.setLayoutManager(manager);
        adapter = new EncyclopediaCommentDetailsAdapter();
        binding.mRecycler.setAdapter(adapter);

        RequestOptions options = new RequestOptions().placeholder(R.mipmap.default_head_img).error(R.mipmap.default_head_img);
        Glide.with(mActivity).load(brBin.getMi_head()).apply(options).into(binding.headImg);
        binding.fName.setText(brBin.getM_name());
        binding.postContent.setText(brBin.getBr_content());
        binding.attention.setText(String.valueOf(brBin.getPraise_num()));
        binding.timeAfter.setText(TimeUtil.friendly_time(brBin.getAdd_date()));

        binding.outBox.setOnkbdStateListener(this);

        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @SingleClick(2000)
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                if(view.getId() == R.id.attention){
                    sendLike(dataBeans.getData().getList().get(position).getIs_praise(),dataBeans.getData().getList().get(position).getBr_id());
                }else{
                    br_id_father = String.valueOf(dataBeans.getData().getList().get(position).getBr_id());
                    first_br_id = String.valueOf(brBin.getBr_id());
                    showEditext();
                }
            }
        });
    }

    @Override
    protected void lazyLoad() {
        //读取圈子帖子评论列表
        clearParams().setParams("br_id",String.valueOf(brBin.getBr_id()));
        Controller.myRequest(Constants.GET_BAIKE_REVIEWLIST_SON,Controller.TYPE_POST,getParams(), EncyclopediaCommentDetailsBean.class,this);
    }
    @Override
    public void refreshData(){
        dataBeans = null;
        lazyLoad();
    }

    @Override
    protected void loadMoreData() {
        lazyLoad();
    }


    //打开软键盘输入评论
    public BindingCommand showEditext = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            br_id_father = String.valueOf(brBin.getBr_id());
            first_br_id = String.valueOf(brBin.getBr_id());
            showEditext();
        }
    });
    //关闭软键盘输入
    public BindingCommand closeEidt = new BindingCommand(() -> hideEditext());
    //提交评论
    public BindingCommand sendMessage = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            sendComment();
        }
    });
    //点赞
    public BindingCommand likeClick = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            //下面模拟点赞，有可能会有问题
            if(brBin.getIs_praise() == 0){
                ViewChangeUtil.changeLeftDrawable(mActivity,binding.attention,R.mipmap.like_chengse_img);
                brBin.setIs_praise(1);
                brBin.setPraise_num(brBin.getPraise_num()+1);
                binding.attention.setText(String.valueOf(brBin.getPraise_num()));
            }else{
                ViewChangeUtil.changeLeftDrawable(mActivity,binding.attention,R.mipmap.like_huise_img);
                brBin.setIs_praise(0);
                brBin.setPraise_num(brBin.getPraise_num()-1);
                binding.attention.setText(String.valueOf(brBin.getPraise_num()));
            }
            sendLike(brBin.getIs_praise(),brBin.getBr_id());
        }
    });

    /*
     * 提交评论信息
     * */
    private void sendComment(){
        if(!isValue(binding.editText.getText().toString())){
            onError(1,"请求输入评论内容！");
            return;
        }

        clearParams().setParams("baike_id",String.valueOf(brBin.getBaike_id())).setParams("br_id_father",br_id_father)
                .setParams("br_content",binding.editText.getText().toString())
                .setParams("first_br_id",first_br_id);
        Controller.myRequest(Constants.ADD_BAIKE_REVIEW,Controller.TYPE_POST,getParams(), EncyclopediaDetailSendMeassageBean.class,this);
    }

    /*
    * 点赞/取消点赞
    * */
    private void sendLike(int is_praise,int br_id){
        clearParams().setParams("br_id",String.valueOf(br_id));
        if(is_praise == 0){
            Controller.myRequest(Constants.ADD_BAIKE_REVIEW_PRAISE,Controller.TYPE_POST,getParams(), EncyclopediaLikeBean.class,this);
        }else{
            Controller.myRequest(Constants.CANCEL_BAIKE_REVIEW_PRAISE,Controller.TYPE_POST,getParams(), EncyclopediaLikeBean.class,this);
        }
    }

    /*
     * 显示软键盘
     * */
    private void showEditext(){
        InputMethodManager imm = (InputMethodManager) binding.editText.getContext()
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            binding.editText.requestFocus();
            imm.showSoftInput(binding.editText, 0);
        }
    }

    /*
     *隐藏软键盘
     * */
    private void hideEditext(){
        binding.editText.setText("");
        br_id_father = String.valueOf(brBin.getBr_id());
        first_br_id = String.valueOf(brBin.getBr_id());
        InputMethodManager imm = (InputMethodManager) binding.editText.getContext()
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.hideSoftInputFromWindow(binding.editText.getWindowToken(),0);
        }
    }

    @Override
    public void onKeyBoardStateChange(int state) {
        switch (state) {
            case KeyboardLayout.KEYBOARD_STATE_HIDE:
                binding.mengCheng.setVisibility(View.GONE);
                break;
            case KeyboardLayout.KEYBOARD_STATE_SHOW:
                binding.mengCheng.setVisibility(View.VISIBLE);
                break;
        }
    }

    @Override
    public void onSuccess(Object data) {
        if(data instanceof EncyclopediaCommentDetailsBean && isSuccess(data)){
            EncyclopediaCommentDetailsBean pageData = (EncyclopediaCommentDetailsBean)data;
            if(curPage == FIRST_PAGE){
                if(pageData.getData().getList() != null && pageData.getData().getList().size() > 0){
                    dataBeans = pageData;
                }
            }else{
                if(pageData.getData().getList() != null && pageData.getData().getList().size() > 0){
                    dataBeans.getData().getList().addAll(pageData.getData().getList());
                }
            }

            if(dataBeans != null && dataBeans.getData() != null &&
                    dataBeans.getData().getList() != null &&
                    dataBeans.getData().getList().size() > 0){
                adapter.setNewData(dataBeans.getData().getList());
                //mActivity.setPromtView(mActivity.SHOW_DATA);
            }else{
                //mActivity.setPromtView( mActivity.SHOW_EMPTY);
            }
        }

        if(data instanceof EncyclopediaDetailSendMeassageBean && isSuccess(data)){
            //提交成功隐藏软键盘
            hideEditext();
            //刷新评论数据
            lazyLoad();
        }

        if(data instanceof EncyclopediaLikeBean && isSuccess(data)){
            //刷新评论数据
            lazyLoad();
        }
    }
}
