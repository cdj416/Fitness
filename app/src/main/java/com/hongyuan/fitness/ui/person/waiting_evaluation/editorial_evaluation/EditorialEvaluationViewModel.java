package com.hongyuan.fitness.ui.person.waiting_evaluation.editorial_evaluation;
import android.view.View;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.BaseBean;
import com.hongyuan.fitness.base.Constants;
import com.hongyuan.fitness.base.Controller;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.base.CustomViewModel;
import com.hongyuan.fitness.base.SingleClick;
import com.hongyuan.fitness.databinding.ActivityEditorialEvaluationBinding;
import com.hongyuan.fitness.ui.find.circle.edit_post.ChooseImgOrVideoView;
import com.hongyuan.fitness.ui.find.circle.edit_post.MoreImgBean;
import com.hongyuan.fitness.ui.person.waiting_for_class.about_privite_class.PriviteCourseCheckBeans;
import com.hongyuan.fitness.util.CustomDialog;

import me.goldze.mvvmhabit.binding.command.BindingAction;
import me.goldze.mvvmhabit.binding.command.BindingCommand;

public class EditorialEvaluationViewModel extends CustomViewModel {

    private ActivityEditorialEvaluationBinding binding;
    private boolean isSelect = false;

    private PriviteCourseCheckBeans.DataBean.ListBean courseBeans;

    //一下变量是发帖需要的参数
    private String crfile = "";//已上传的文件地址拼接字符串

    public EditorialEvaluationViewModel(CustomActivity mActivity, ActivityEditorialEvaluationBinding binding) {
        super(mActivity);
        this.binding = binding;
        initView();
    }

    //选择图片
    public BindingCommand chooseImg = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            binding.imgOrVideo.selectContent(ChooseImgOrVideoView.SHOW_IMG);
        }
    });
    //选中匿名
    public BindingCommand chooseSelect = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            if(!isSelect){
                binding.selectImg.setImageResource(R.mipmap.pay_chengse_select_img);
                isSelect = true;
            }else{
                binding.selectImg.setImageResource(R.mipmap.pay_huise_no_select_img);
                isSelect = false;
            }
        }
    });

    @Override
    protected void initView() {
        courseBeans = (PriviteCourseCheckBeans.DataBean.ListBean)getBundle().getSerializable("courseBeans");
        mActivity.getMainTitle().setRightText("发表");
        mActivity.getMainTitle().getRightView().setOnClickListener(new View.OnClickListener() {
            @SingleClick(2000)
            @Override
            public void onClick(View v) {
                sendPinLun();
            }
        });

        RequestOptions options = new RequestOptions().placeholder(R.mipmap.zhengfangxing_default_img).error(R.mipmap.zhengfangxing_default_img).centerCrop();
        Glide.with(mActivity).load(courseBeans.getCp_img()).apply(options).into(binding.courseImg);

       /* binding.myRat.setOnRatingBarChangeListener((ratingBar, rating, fromUser) -> {
            if(rating <= 1){
                binding.showText.setText("不满意");
            }
            if(rating > 1 && rating <=2){
                binding.showText.setText("一般");
            }
            if(rating > 2 && rating <=3){
                binding.showText.setText("满意");
            }
            if(rating > 3 && rating <=4){
                binding.showText.setText("非常满意");
            }
            if(rating >= 5){
                binding.showText.setText("超出预期");
            }
        });*/
    }

    /*
    * 发布评论
    * */
    private void sendPinLun(){
        if(!isValue(binding.etContent.getText().toString())){
            CustomDialog.showMessage(mActivity,"请输入评论内容！");
            return;
        }

        onLoading("正在努力的上传数据，请耐心等待！");
        if(binding.imgOrVideo.getImgList() != null &&
                binding.imgOrVideo.getImgList().size() > 0){
            Controller.myRequest(Constants.UPFILE_OSS_MORE,Controller.TYPE_POST,getKeyValueList(binding.imgOrVideo.getImgList()), MoreImgBean.class,this);
        }else{
            //去请求
            submitData();
        }
    }

    private void submitData(){
        clearParams().setParams("cp_id",String.valueOf(courseBeans.getCp_id()))
                .setParams("cr_content",binding.etContent.getText().toString())
                .setParams("coach_s", String.valueOf(5))
                .setParams("cpa_id",String.valueOf(courseBeans.getCpa_id()));
        if(isValue(crfile)){
            setParams("crfile",crfile);
        }
        Controller.myRequest(Constants.ADD_COACH_REVIEW,Controller.TYPE_POST,getParams(), EvaluationBeans.class,this);
    }

    @Override
    public void onSuccess(Object data) {
        if(data instanceof MoreImgBean && isSuccess(data)){
            MoreImgBean imgBeans = (MoreImgBean)data;
            //去区分
            setReqPar(imgBeans);
            //去请求
            submitData();
        }

        if(data instanceof EvaluationBeans){
            mActivity.showSuccess("评论成功！");
        }
    }

    /*
     * 拼接circlefile参数
     * */
    public void setReqPar(MoreImgBean imgBeans){
        if(binding.imgOrVideo.nowType == ChooseImgOrVideoView.SHOW_IMG){
            for (int i = 0 ; i < imgBeans.getData().getFile_url().size() ; i++){
                if(isValue(imgBeans.getData().getFile_url().get(i).getOss_url())){
                    if(i ==  (imgBeans.getData().getFile_url().size() -1)){
                        crfile += imgBeans.getData().getFile_url().get(i).getOss_url();
                    }else{
                        crfile += imgBeans.getData().getFile_url().get(i).getOss_url() + ",";
                    }

                }
            }
        }
    }
}
