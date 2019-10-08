package com.hongyuan.fitness.ui.encyclopedia.edit_encyclopedia;

import com.hongyuan.fitness.base.BaseBean;
import com.hongyuan.fitness.base.Constants;
import com.hongyuan.fitness.base.Controller;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.base.CustomViewModel;
import com.hongyuan.fitness.databinding.ActivityEditEncyclopediaBinding;
import com.hongyuan.fitness.ui.encyclopedia.select_encyclopedia_type.EncyclopediaTypeActivity;
import com.hongyuan.fitness.ui.find.circle.edit_post.ChooseImgOrVideoView;
import com.hongyuan.fitness.ui.find.circle.edit_post.FileBean;
import com.hongyuan.fitness.ui.find.circle.edit_post.MoreImgBean;
import com.hongyuan.fitness.ui.main.main_about_class.private_lessons.MenuPrivateLessonsBean;
import com.hongyuan.fitness.ui.main.main_person.RetrunImgBean;
import com.hongyuan.fitness.util.LocationBean;

import me.goldze.mvvmhabit.binding.command.BindingCommand;

public class EditEncyclopediaViewModel extends CustomViewModel {

    private ActivityEditEncyclopediaBinding binding;
    private MenuPrivateLessonsBean.DataBean typeBean;

    //一下变量是发帖需要的参数
    private String baiKeFile = "";//已上传的文件地址拼接字符串
    private String baiKe_type;//上传的文件类型
    private String baike_fengmian;//封面

    public EditEncyclopediaViewModel(CustomActivity mActivity, ActivityEditEncyclopediaBinding binding) {
        super(mActivity);
        this.binding = binding;
        initView();
    }

    @Override
    protected void initView() {
        mActivity.getMainTitle().setRightText("发布");
        mActivity.getMainTitle().getRightView().setOnClickListener(v -> {
            if(!isValue(binding.encyclopediaTitle.getText().toString())){
                onError(1,"请输入百科标题！");
                return;
            }
            if(typeBean == null){
                onError(1,"请选择百科分类！");
                return;
            }
            if(!isValue(binding.editContent.getText().toString())){
                onError(1,"请输入百科内容！");
                return;
            }
            if(binding.coverImg.getImgFile() == null){
                onError(1,"请上传百科封面！");
                return;
            }
            updataFile();
        });
    }

    //选择封面
    public BindingCommand chooseCover = new BindingCommand(() -> binding.coverImg.selectContent());
    //选择视频
    public BindingCommand chooseVideo = new BindingCommand(() -> binding.imgOrVideo.selectContent(ChooseImgOrVideoView.SHOW_VEDIO));
    //选择图片
    public BindingCommand chooseImg = new BindingCommand(() -> binding.imgOrVideo.selectContent(ChooseImgOrVideoView.SHOW_IMG));
    //选择分类
    public BindingCommand chooseType = new BindingCommand(() -> startActivity(EncyclopediaTypeActivity.class,null));

    /*
    * 设置所选类型
    * */
    public void setChooseType(MenuPrivateLessonsBean.DataBean typeBean){
        this.typeBean = typeBean;
        binding.typeName.setText(typeBean.getFt_name());
    }

    /*
     * 开始上传文件
     * */
    private void updataFile(){
        onLoading("正在努力的上传数据，请耐心等待！");

        if(binding.coverImg.getImgFile() != null){
            FileBean fileBean = new FileBean();
            fileBean.setFileKey("oss_file");
            fileBean.setmFile(binding.coverImg.getImgFile());
            clearParams().setParams("type","1");
            //上传单文件（封面）
            Controller.myRequest(Constants.UPFILE_OSS,Controller.TYPE_POST,getParams(),fileBean, RetrunImgBean.class,this);
        }

        if(binding.imgOrVideo.getImgList() != null && binding.imgOrVideo.getImgList().size() > 0){
            //上传多文件（选择的图片）
            Controller.myRequest(Constants.UPFILE_OSS_MORE,Controller.TYPE_POST,getKeyValueList(binding.imgOrVideo.getImgList()),MoreImgBean.class,this);
        }
    }

    /*
     * 发布圈子
     * */
    public void submitData(){
        clearParams().setParams("baike_categoryid","1")
                .setParams("baike_content",binding.editContent.getText().toString())
                .setParams("baike_name",binding.encyclopediaTitle.getText().toString())
                .setParams("ft_id",String.valueOf(typeBean.getFt_id()))
                .setParams("lat", LocationBean.getInstance().getLat())
                .setParams("lng",LocationBean.getInstance().getLng())
                .setParams("os_id",String.valueOf(LocationBean.getInstance().getOs_id()))
                .setParams("baikefile",baiKeFile)
                .setParams("baike_type",baiKe_type);
        if(isValue(baike_fengmian)){
            setParams("baike_img",baike_fengmian);
        }
        Controller.myRequest(Constants.ADD_BAIKE,Controller.TYPE_POST,getParams(), EditEncyclopediaBeans.class,this);
    }

    @Override
    public void onSuccess(Object data) {
        if(data instanceof RetrunImgBean){
            RetrunImgBean imgBean = (RetrunImgBean)data;
            baike_fengmian = imgBean.getData().getFile_url();
        }
        if(data instanceof MoreImgBean && isSuccess(data)){
            MoreImgBean imgBeans = (MoreImgBean)data;
            //去区分
            setReqPar(imgBeans);
            //去请求
            submitData();
        }

        if(data instanceof EditEncyclopediaBeans){
            mActivity.showSuccess("发布成功！");
        }
    }

    /*
     * 拼接circlefile参数
     * */
    public void setReqPar(MoreImgBean imgBeans){
        if(binding.imgOrVideo.nowType == ChooseImgOrVideoView.SHOW_IMG){
            baiKe_type = "1";
            for (int i = 0 ; i < imgBeans.getData().getFile_url().size() ; i++){
                if(isValue(imgBeans.getData().getFile_url().get(i).getOss_url())){
                    if(i ==  (imgBeans.getData().getFile_url().size() -1)){
                        baiKeFile += imgBeans.getData().getFile_url().get(i).getOss_url();
                    }else{
                        baiKeFile += imgBeans.getData().getFile_url().get(i).getOss_url() + ",";
                    }

                }
            }
        }else{
            baiKe_type = "2";
            for(int i = 0 ; i < imgBeans.getData().getFile_url().size() ; i++){
                if(isVideoUrl(imgBeans.getData().getFile_url().get(i).getOss_url())){
                    baiKeFile = imgBeans.getData().getFile_url().get(i).getOss_url();
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
}
