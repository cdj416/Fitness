package com.hongyuan.fitness.ui.shop.sviewmodel;


import android.os.Bundle;
import android.view.View;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.BaseBean;
import com.hongyuan.fitness.base.Constants;
import com.hongyuan.fitness.base.ConstantsCode;
import com.hongyuan.fitness.base.Controller;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.base.CustomViewModel;
import com.hongyuan.fitness.custom_view.scllor_view.UnitBeanUtils;
import com.hongyuan.fitness.databinding.ActivityEditextLogisticsBinding;
import com.hongyuan.fitness.ui.find.circle.edit_post.MoreImgBean;
import com.hongyuan.fitness.ui.shop.sactivity.ReturnPriceDetailsActivity;
import com.hongyuan.fitness.ui.shop.sbeans.AftersaleOrderBeans;
import com.hongyuan.fitness.ui.shop.sbeans.LogisticsCommpenyBeans;
import com.hongyuan.fitness.util.BaseUtil;
import com.hongyuan.fitness.util.CustomDialog;

import java.util.List;

public class EdLogisticsViewModel extends CustomViewModel {

    private ActivityEditextLogisticsBinding binding;

    //退款理由选择工具类
    private UnitBeanUtils rUtils;
    private List<LogisticsCommpenyBeans.DataBean.ListBean> rList;

    private AftersaleOrderBeans.DataBean.InfoBean.GoodsListBean item;

    public EdLogisticsViewModel(CustomActivity mActivity, ActivityEditextLogisticsBinding binding) {
        super(mActivity);
        this.binding = binding;

        initView();
        lazyLoad();
    }

    @Override
    protected void initView() {
        setEnableOverScrollDrag(true);

        //设置智能选择图片
        binding.imgVideo.flag = true;

        item = (AftersaleOrderBeans.DataBean.InfoBean.GoodsListBean) getBundle().getSerializable("item");
        RequestOptions options = new RequestOptions().placeholder(R.color.color_f2).error(R.color.color_f2);
        Glide.with(mActivity).load(item.getG_img()).apply(options).into(binding.goodsImg);
        binding.goodName.setText(item.getG_name());
        binding.goodPrice.setText(BaseUtil.getNoZoon(item.getGp_price()));
        binding.goodNum.setText("x"+item.getBuy_num());


        binding.companyName.setOnClickListener(v -> {
            CustomDialog.scroller(mActivity, rUtils.getUnitList(rList), "请选择物流公司", (v1, message) -> {
                binding.companyName.setRightText(message);
                binding.companyName.setUseValue(rUtils.getUseId(message));
            });
        });
    }

    /*
    * 退回商品
    * */
    public void delGoods(String imgs){
        if(!BaseUtil.isValue(binding.delNum.getRightText())){
            CustomDialog.showMessage(mActivity,"请填写物流单号！");
            return;
        }
        mActivity.showLoading();
        clearParams().setParams("deliver_company",rUtils.getMessage(binding.companyName.getRightText()))
                .setParams("deliver_company_code",binding.companyName.getRightText())
                .setParams("deliver_num",binding.delNum.getRightText())
                .setParams("opg_id",getBundle().getString("opg_id"));

        if(BaseUtil.isValue(imgs)){
            setParams("refund_voucher",imgs);
        }

        Controller.myRequest(ConstantsCode.DELIVER_REFUND,Constants.DELIVER_REFUND,Controller.TYPE_POST,getParams(), BaseBean.class,this);
    }

    /*
     * 开始上传文件
     * */
    public void updataFile(){
        if(binding.imgVideo.getImgList() != null && binding.imgVideo.getImgList().size() > 0){
            mActivity.showLoading();
            Controller.myRequest(Constants.UPFILE_OSS_MORE,Controller.TYPE_POST,getKeyValueList(binding.imgVideo.getImgList()), MoreImgBean.class,this);
        }
    }

    /*
     * 是否需要上传凭证
     * */
    public boolean isUpdateImg(){
        if(binding.imgVideo.getImgList() != null && binding.imgVideo.getImgList().size() > 0){
            return true;
        }
        return false;
    }

    @Override
    protected void lazyLoad() {
        mActivity.showLoading();
        clearParams();
        Controller.myRequest(Constants.GET_DELIVER_COMPANY,Controller.TYPE_POST,getParams(), LogisticsCommpenyBeans.class,this);
    }

    @Override
    public void onSuccess(Object data) {
        mActivity.closeLoading();

        if(data instanceof LogisticsCommpenyBeans){
            rList = ((LogisticsCommpenyBeans)data).getData().getList();

            rUtils = new UnitBeanUtils<LogisticsCommpenyBeans.DataBean.ListBean>() {
                @Override
                public String unit(LogisticsCommpenyBeans.DataBean.ListBean listBean) {
                    return String.valueOf(listBean.getCode());
                }

                @Override
                public String unit_cn(LogisticsCommpenyBeans.DataBean.ListBean listBean) {
                    return listBean.getName();
                }
            };
            rUtils.getUnitList(rList);
            binding.companyName.setRightText(rList.get(0).getName());
            binding.companyName.setUseValue(String.valueOf(rList.get(0).getCode()));
        }

        if(data instanceof MoreImgBean && isSuccess(data)){
            MoreImgBean imgBeans = (MoreImgBean)data;

            String imgUrls = "";

            for(MoreImgBean.DataBean.FileUrlBean imgBean : imgBeans.getData().getFile_url()){
                imgUrls+=","+imgBean.getOss_url();
            }
            delGoods(imgUrls.substring(1));
        }
    }

    @Override
    public void onSuccess(int code, Object data) {
        super.onSuccess(code,data);

        mActivity.closeLoading();
        if(code == ConstantsCode.DELIVER_REFUND){
            Bundle bundle = new Bundle();
            bundle.putString("opg_id",getBundle().getString("opg_id"));
            startActivity(ReturnPriceDetailsActivity.class,bundle);
        }
    }
}
