package com.hongyuan.fitness.ui.find.circle.edit_post;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;

import com.hongyuan.fitness.base.Constants;
import com.hongyuan.fitness.base.Controller;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.base.CustomViewModel;
import com.hongyuan.fitness.base.SingleClick;
import com.hongyuan.fitness.custom_view.add_imgorvideo.AddImageOrVideoView;
import com.hongyuan.fitness.databinding.ActivityEditPostBinding;
import com.hongyuan.fitness.ui.find.circle.get_nearby_location.GetNearyLocationActivity;
import com.hongyuan.fitness.ui.find.circle.get_nearby_location.NearLocationBeans;
import com.hongyuan.fitness.ui.find.topic.SelectTopicActivity;
import com.hongyuan.fitness.ui.find.topic.SlectTopicRighttBeans;
import com.hongyuan.fitness.util.LocationBean;
import me.goldze.mvvmhabit.binding.command.BindingCommand;

public class EditPostViewModel extends CustomViewModel {

    //一下变量是发帖需要的参数
    private String circle_img;//视频第一帧以上传的图片地址
    private String circlefile = "";//已上传的文件地址拼接字符串
    private String circle_type;//上传的文件类型

    //话题选择的数据
    private SlectTopicRighttBeans.DataBean.ListBean topicBean;
    //地址的选择
    private NearLocationBeans nearLocationBeans;

    //选择话题类型
    public static final int TOPIC = 0X01;
    //位置
    public static final int ADDRESS = 0X02;

    private ActivityEditPostBinding binding;
    public EditPostViewModel(CustomActivity mActivity, ActivityEditPostBinding binding) {
        super(mActivity);
        this.binding = binding;
        initView();
    }

    @Override
    protected void initView() {
        mActivity.getMainTitle().setRightText("发布");
        //发布圈子
        mActivity.getMainTitle().getRightView().setOnClickListener(new View.OnClickListener() {
            @SingleClick(2000)
            @Override
            public void onClick(View v) {
                //先把图片上传了
                submitFiles();
            }
        });

        if(getBundle() != null && getBundle().getSerializable("topBeans") != null){
            topicBean = (SlectTopicRighttBeans.DataBean.ListBean) getBundle().getSerializable("topBeans");
            binding.topMark.setVisibility(View.VISIBLE);
            binding.topicName.setText(topicBean.getCategory_name());
        }

        if(getBundle() != null && getBundle().getString("shareFile") != null){
            String shareFile = getBundle().getString("shareFile");
            binding.imgVideo.changeShow(shareFile);
        }
    }

    //跳转到选择圈子页面
    public BindingCommand goChooseCircle = new BindingCommand(() -> startActivityForResult(SelectTopicActivity.class,null));

    //选择位置
    public BindingCommand chooseLocation = new BindingCommand(() -> startActivityForResult(GetNearyLocationActivity.class,null));

    /*
    * 上传图片
    * */
    @SuppressLint("CheckResult")
    public void submitFiles(){
        if(!isValue(binding.editContent.getText().toString())){
            onError(1,"请输入你要分享的文字内容哦？");
            return;
        }

        if(binding.imgVideo.getImgList() == null || binding.imgVideo.getImgList().size() <= 0){
            onError(1,"请选择图片或者视频？");
            return;
        }

        if(topicBean == null){
            onError(1,"请选择话题！");
            return;
        }
        updataFile();
    }

    /*
    * 开始上传文件
    * */
    private void updataFile(){
        if(binding.imgVideo.getImgList() != null && binding.imgVideo.getImgList().size() > 0){
            onLoading("正在努力的上传数据，请耐心等待！");
            Controller.myRequest(Constants.UPFILE_OSS_MORE,Controller.TYPE_POST,getKeyValueList(binding.imgVideo.getImgList()),MoreImgBean.class,this);
        }
    }

    /*
    * 发布圈子
    * */
    public void submitData(){
        clearParams().setParams("circle_categoryid",String.valueOf(topicBean.getCategory_id())).setParams("circle_name",topicBean.getCategory_name())
                .setParams("circle_content",binding.editContent.getText().toString())
                .setParams("lat", LocationBean.getInstance().getLat())
                .setParams("lng",LocationBean.getInstance().getLng())
                .setParams("os_id",String.valueOf(LocationBean.getInstance().getOs_id()))
                .setParams("circlefile",circlefile)
                .setParams("circle_type",circle_type);
        if(isValue(circle_img)){
            setParams("circle_img",circle_img);
        }
        Controller.myRequest(Constants.ADD_CIRCLE,Controller.TYPE_POST,getParams(), UpdataSueccse.class,this);
    }

    @Override
    public void onSuccess(Object data) {
        super.onSuccess(data);

        if(data instanceof MoreImgBean && isSuccess(data)){
            MoreImgBean imgBeans = (MoreImgBean)data;
            //去区分
            setReqPar(imgBeans);
            //去请求
            submitData();
        }

        if(data instanceof UpdataSueccse && isSuccess(data)){
            mActivity.showSuccess("发布成功！");
        }
    }

    /*
    * 拼接circlefile参数
    * */
    public void setReqPar(MoreImgBean imgBeans){
        if(binding.imgVideo.nowType == AddImageOrVideoView.SHOW_IMG){
            circle_img = "";
            circle_type = "1";
            for (int i = 0 ; i < imgBeans.getData().getFile_url().size() ; i++){
                if(isValue(imgBeans.getData().getFile_url().get(i).getOss_url())){
                    if(i ==  (imgBeans.getData().getFile_url().size() -1)){
                        circlefile += imgBeans.getData().getFile_url().get(i).getOss_url();
                    }else{
                        circlefile += imgBeans.getData().getFile_url().get(i).getOss_url() + ",";
                    }

                }
            }
        }else{
            circle_type = "2";
            for(int i = 0 ; i < imgBeans.getData().getFile_url().size() ; i++){
                if(isVideoUrl(imgBeans.getData().getFile_url().get(i).getOss_url())){
                    circlefile = imgBeans.getData().getFile_url().get(i).getOss_url();
                }else{
                    circle_img = imgBeans.getData().getFile_url().get(i).getOss_url();
                }
            }
        }

    }

    /*
    * 判断是否是视频路径
    * */
    public boolean isVideoUrl(String url){
        if(url.contains(".mp4") || url.contains(".avi") || url.contains(".flv") || url.contains(".rmvb") || url.contains(".swf")){
            return true;
        }
        return false;
    }

    @Override
    public void forResult(Bundle bundle) {
        int type = bundle.getInt("type");
        if(type == TOPIC){
            topicBean = (SlectTopicRighttBeans.DataBean.ListBean)bundle.getSerializable("topicItemBean");
            if(topicBean != null){
                binding.topMark.setVisibility(View.VISIBLE);
                binding.topicName.setText(topicBean.getCategory_name());
            }
        }else{
            nearLocationBeans = (NearLocationBeans) bundle.getSerializable("NearLocationBeans");
            if(nearLocationBeans != null){
                binding.address.setText(nearLocationBeans.getTitle());
            }else{
                binding.address.setText("你在哪里？");
            }
        }

    }
}
