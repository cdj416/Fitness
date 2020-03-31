package com.hongyuan.fitness.ui.person.physical_data;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.recyclerview.widget.GridLayoutManager;
import com.hongyuan.fitness.base.Constants;
import com.hongyuan.fitness.base.ConstantsCode;
import com.hongyuan.fitness.base.Controller;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.base.CustomViewModel;
import com.hongyuan.fitness.databinding.ActivityPersonPhysicalDataBinding;
import com.hongyuan.fitness.ui.find.circle.edit_post.FileBean;
import com.hongyuan.fitness.ui.find.circle.edit_post.MoreImgBean;
import com.hongyuan.fitness.ui.person.physical_data.add_physical.AddPhysicaldataActivity;
import com.hongyuan.fitness.ui.person.physical_data.silhouette.SihouetteActivity;
import com.hongyuan.fitness.util.BaseUtil;
import com.hongyuan.fitness.util.DividerItemDecoration;
import com.hongyuan.fitness.util.SelectImgUtils;

import java.util.List;

public class PhysicalDataViewModel extends CustomViewModel implements SelectImgUtils.SelectionDoneListener {

    private ActivityPersonPhysicalDataBinding binding;

    private PhysicalBeans.DataBean.InfoBean infoBean;
    private PhysicaldataAdapter adapter;
    private SelectImgUtils imgUtils;
    private String creatTime;

    public PhysicalDataViewModel(CustomActivity mActivity, ActivityPersonPhysicalDataBinding binding) {
        super(mActivity);
        this.binding = binding;
        initView();
        getNewImgs();
    }

    @Override
    protected void initView() {
        setEnableOverScrollDrag(true);
        imgUtils = new SelectImgUtils();
        imgUtils.setDoneListener(this);
        binding.addImg.setOnClickListener(v -> imgUtils.selectImg(mActivity,1));
        binding.noImgBox.setOnClickListener(v -> imgUtils.selectImg(mActivity,1));

        GridLayoutManager layoutManager = new GridLayoutManager(mActivity, 4);
        binding.mRecycler.setLayoutManager(layoutManager);
        binding.mRecycler.addItemDecoration(new DividerItemDecoration(
                mActivity, DividerItemDecoration.VERTICAL_LIST,24,0x00000000));
        binding.mRecycler.setLayoutManager(layoutManager);
        adapter = new PhysicaldataAdapter();
        binding.mRecycler.setAdapter(adapter);
        adapter.setOnItemChildClickListener((adapter, view, position) -> {
            if(position == (adapter.getData().size() - 1)){
                startActivity(SihouetteActivity.class,null);
            }
        });



        binding.bodyWeight.setGoClick(() -> {
            Bundle bundle = new Bundle();
            bundle.putInt("position",0);
            startActivity(AddPhysicaldataActivity.class,bundle);
        });
        binding.height.setGoClick(() -> {
            Bundle bundle = new Bundle();
            bundle.putInt("position",1);
            startActivity(AddPhysicaldataActivity.class,bundle);
        });
        binding.bmi.setGoClick(() -> {
            Bundle bundle = new Bundle();
            bundle.putInt("position",2);
            startActivity(AddPhysicaldataActivity.class,bundle);
        });
        binding.bust.setGoClick(() -> {
            Bundle bundle = new Bundle();
            bundle.putInt("position",3);
            startActivity(AddPhysicaldataActivity.class,bundle);
        });
        binding.waistline.setGoClick(() -> {
            Bundle bundle = new Bundle();
            bundle.putInt("position",4);
            startActivity(AddPhysicaldataActivity.class,bundle);
        });
        binding.hipCircumference.setGoClick(() -> {
            Bundle bundle = new Bundle();
            bundle.putInt("position",5);
            startActivity(AddPhysicaldataActivity.class,bundle);
        });
        binding.thighCircumference.setGoClick(() -> {
            Bundle bundle = new Bundle();
            bundle.putInt("position",6);
            startActivity(AddPhysicaldataActivity.class,bundle);
        });
        binding.calfCircumference.setGoClick(() -> {
            Bundle bundle = new Bundle();
            bundle.putInt("position",7);
            startActivity(AddPhysicaldataActivity.class,bundle);
        });
        binding.armCircumference.setGoClick(() -> {
            Bundle bundle = new Bundle();
            bundle.putInt("position",8);
            startActivity(AddPhysicaldataActivity.class,bundle);
        });
        binding.bodyFatRate.setGoClick(() -> {
            Bundle bundle = new Bundle();
            bundle.putInt("position",9);
            startActivity(AddPhysicaldataActivity.class,bundle);
        });
    }

    public void onActivityResult(int requestCode, int resultCode,Intent data){
        imgUtils.onActivityResult(requestCode,resultCode,data);
    }

    @Override
    protected void onResume() {
        lazyLoad();
    }

    @Override
    protected void lazyLoad() {
        mActivity.showLoading();
        clearParams();
        Controller.myRequest(Constants.GET_BODY_DATA_INFO,Controller.TYPE_POST,getParams(), PhysicalBeans.class,this);

    }

    /*
    * 获取最新图片剪影
    * */
    private void getNewImgs(){
        clearParams();
        //获取剪影图片
        Controller.myRequest(Constants.GET_BODY_IMG,Controller.TYPE_POST,getParams(), PhsicaldataSilhouetteDataBeans.class,this);
    }

    @Override
    protected void setData() {
        if(BaseUtil.isValue(infoBean.getWeight())){
            binding.bodyWeight.setRightText(infoBean.getWeight()+"kg");
        }else{
            binding.bodyWeight.setRightText("--");
        }

        if(BaseUtil.isValue(infoBean.getHeight())){
            binding.height.setRightText(infoBean.getHeight()+"cm");
        }else{
            binding.height.setRightText("--");
        }

        if(BaseUtil.isValue(infoBean.getBmi())){
            binding.bmi.setRightText(infoBean.getBmi()+"");
        }else{
            binding.bmi.setRightText("--");
        }

        if(BaseUtil.isValue(infoBean.getXw())){
            binding.bust.setRightText(infoBean.getXw()+"cm");
        }else{
            binding.bust.setRightText("--");
        }

        if(BaseUtil.isValue(infoBean.getYw())){
            binding.waistline.setRightText(infoBean.getYw()+"cm");
        }else{
            binding.waistline.setRightText("--");
        }

        if(BaseUtil.isValue(infoBean.getTw())){
            binding.hipCircumference.setRightText(infoBean.getTw()+"cm");
        }else{
            binding.hipCircumference.setRightText("--");
        }

        if(BaseUtil.isValue(infoBean.getDtw())){
            binding.thighCircumference.setRightText(infoBean.getDtw()+"cm");
        }else{
            binding.thighCircumference.setRightText("--");
        }

        if(BaseUtil.isValue(infoBean.getXtw())){
            binding.calfCircumference.setRightText(infoBean.getXtw()+"cm");
        }else{
            binding.calfCircumference.setRightText("--");
        }

        if(BaseUtil.isValue(infoBean.getSbw())){
            binding.armCircumference.setRightText(infoBean.getSbw()+"cm");
        }else{
            binding.armCircumference.setRightText("--");
        }

        if(BaseUtil.isValue(infoBean.getBfp())){
            binding.bodyFatRate.setRightText(infoBean.getBfp()+"%");
        }else{
            binding.bodyFatRate.setRightText("--");
        }
    }

    @Override
    public void onSuccess(Object data) {
        mActivity.closeLoading();
        if(data instanceof PhysicalBeans){
            infoBean = ((PhysicalBeans)data).getData().getInfo();

            setData();
        }

        if(data instanceof PhsicaldataSilhouetteDataBeans){
            List<PhsicaldataSilhouetteDataBeans.DataBean.ListBean.ImgBean> imgList = ((PhsicaldataSilhouetteDataBeans)data).getData().getList().getImg();
            if(imgList.size() > 0){
                adapter.setNewData(imgList);
                binding.haveImgBox.setVisibility(View.VISIBLE);
                binding.noImgBox.setVisibility(View.GONE);
            }else{
                binding.haveImgBox.setVisibility(View.GONE);
                binding.noImgBox.setVisibility(View.VISIBLE);
            }

        }

        if(data instanceof MoreImgBean && isSuccess(data)){
            MoreImgBean imgBeans = (MoreImgBean)data;
            updataImg(imgBeans.getData().getFile_url().get(0).getOss_url());
        }
    }

    @Override
    public void onSuccess(int code, Object data) {
        if(code == ConstantsCode.ADD_BODY_IMG){
            getNewImgs();
        }
    }

    /*
    * 添加图片剪影
    * */
    private void updataImg(String img){
        clearParams().setParams("img",img).setParams("img_time",creatTime);
        Controller.myRequest(ConstantsCode.ADD_BODY_IMG,Constants.ADD_BODY_IMG,Controller.TYPE_POST,getParams(), MoreImgBean.class,this);
    }

    /*
    * 图片集上传
    * */
    @Override
    public void doneListener(List<FileBean> mList,String creatTime) {
        this.creatTime = creatTime;
        mActivity.showLoading();
        Controller.myRequest(Constants.UPFILE_OSS_MORE,Controller.TYPE_POST,getKeyValueList(mList), MoreImgBean.class,this);
    }
}
