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
import com.hongyuan.fitness.databinding.AcitivityRefundGoodsBinding;
import com.hongyuan.fitness.ui.find.circle.edit_post.MoreImgBean;
import com.hongyuan.fitness.ui.shop.sactivity.ReturnPriceDetailsActivity;
import com.hongyuan.fitness.ui.shop.sbeans.AftersaleOrderBeans;
import com.hongyuan.fitness.ui.shop.sbeans.ReturnPriceBeans;
import com.hongyuan.fitness.ui.shop.sbeans.ReturnResionBeans;
import com.hongyuan.fitness.util.BaseUtil;
import com.hongyuan.fitness.util.CustomDialog;

import java.util.List;

public class RefundGoodsViewModel extends CustomViewModel {

    private AcitivityRefundGoodsBinding binding;

    private AftersaleOrderBeans.DataBean.InfoBean.GoodsListBean item;

    //是否退货1.是，2.否。
    private int is_return_goods;

    //退款理由选择工具类
    private UnitBeanUtils rUtils;
    private List<ReturnResionBeans.DataBean.ListBean> rList;

    //退款最大金额数据
    private ReturnPriceBeans.DataBean rpBeans;

    public RefundGoodsViewModel(CustomActivity mActivity, AcitivityRefundGoodsBinding binding) {
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

        is_return_goods = getBundle().getInt("is_return_goods");
        item = (AftersaleOrderBeans.DataBean.InfoBean.GoodsListBean) getBundle().getSerializable("item");

        RequestOptions options = new RequestOptions().placeholder(R.color.color_f2).error(R.color.color_f2);
        Glide.with(mActivity).load(item.getG_img()).apply(options).into(binding.goodsImg);
        binding.goodName.setText(item.getG_name());
        binding.goodPrice.setText(BaseUtil.getNoZoon(item.getGp_price()));
        binding.goodNum.setText("x"+item.getBuy_num());
        binding.returnPrice.setText(BaseUtil.getNoZoon(item.getGp_price()));

        if(BaseUtil.isValue(item.getSku_names())){
            binding.goodSku.setText(item.getSku_names());
        }else{
            binding.goodSku.setVisibility(View.INVISIBLE);
        }

        if(is_return_goods != 1){
            mActivity.getMainTitle().setCentreText("我要退款");
            binding.returnType.setVisibility(View.GONE);
        }else{
            mActivity.getMainTitle().setCentreText("我要退货退款");
            binding.returnType.setVisibility(View.VISIBLE);
        }

        binding.resion.setOnClickListener(v -> {
            CustomDialog.scroller(mActivity, rUtils.getUnitList(rList), "请选择退款理由", (v1, message) -> {
                binding.resion.setRightText(message);
                binding.resion.setUseValue(rUtils.getUseId(message));
            });
        });
    }

    /*
    * 申请退款
    * */
    public void applyReturn(String imgs){
        mActivity.showLoading();
        clearParams().setParams("opg_id",String.valueOf(item.getOpg_id()))
                .setParams("refund_money",BaseUtil.isValue(binding.returnPrice.getText().toString()) ? binding.returnPrice.getText().toString() : String.valueOf(rpBeans.getMax_refund()))
                .setParams("refund_reason_id",binding.resion.getRightText())
                .setParams("is_return_goods",String.valueOf(is_return_goods))
                .setParams("refund_note",binding.notes.getRightText());
        if(BaseUtil.isValue(imgs)){
            setParams("refund_voucher",imgs);
        }

        //1为从新申请-1为编辑退款，其他为第一次申请
        if(getBundle().getInt("is_refund") == 1){
            Controller.myRequest(ConstantsCode.NEW_APPLY_REFUND,Constants.NEW_APPLY_REFUND,Controller.TYPE_POST,getParams(), BaseBean.class,this);
        }else if(getBundle().getInt("is_refund") == -1){
            Controller.myRequest(ConstantsCode.EDIT_REFUND,Constants.EDIT_REFUND,Controller.TYPE_POST,getParams(), BaseBean.class,this);
        }else{
            Controller.myRequest(ConstantsCode.APPLY_REFUND,Constants.APPLY_REFUND,Controller.TYPE_POST,getParams(), BaseBean.class,this);
        }
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
        clearParams();
        Controller.myRequest(Constants.GET_REFUND_REASON,Controller.TYPE_POST,getParams(), ReturnResionBeans.class,this);

        //获取退款金额
        clearParams().setParams("opg_id",String.valueOf(item.getOpg_id()));
        Controller.myRequest(Constants.GET_MAX_REFUND,Controller.TYPE_POST,getParams(), ReturnPriceBeans.class,this);


    }

    @Override
    public void onSuccess(Object data) {
        if(data instanceof ReturnResionBeans){
            rList = ((ReturnResionBeans)data).getData().getList();

            rUtils = new UnitBeanUtils<ReturnResionBeans.DataBean.ListBean>() {
                @Override
                public String unit(ReturnResionBeans.DataBean.ListBean listBean) {
                    return String.valueOf(listBean.getReason_id());
                }

                @Override
                public String unit_cn(ReturnResionBeans.DataBean.ListBean listBean) {
                    return listBean.getReason_name();
                }
            };

            binding.resion.setRightText(rList.get(0).getReason_name());
            binding.resion.setUseValue(String.valueOf(rList.get(0).getReason_id()));
        }

        if(data instanceof ReturnPriceBeans){
            rpBeans = ((ReturnPriceBeans)data).getData();
            binding.returnPrice.setText(BaseUtil.getNoZoon(rpBeans.getMax_refund()));
        }

        if(data instanceof MoreImgBean && isSuccess(data)){
            MoreImgBean imgBeans = (MoreImgBean)data;

            String imgUrls = "";

            for(MoreImgBean.DataBean.FileUrlBean imgBean : imgBeans.getData().getFile_url()){
                imgUrls+=","+imgBean.getOss_url();
            }
            applyReturn(imgUrls.substring(1));
        }
    }

    @Override
    public void onSuccess(int code, Object data) {
        mActivity.closeLoading();
        if(code == ConstantsCode.APPLY_REFUND){
            Bundle bundle = new Bundle();
            bundle.putString("opg_id",String.valueOf(item.getOpg_id()));
            startActivity(ReturnPriceDetailsActivity.class,bundle);
        }

        if(code == ConstantsCode.NEW_APPLY_REFUND){
            Bundle bundle = new Bundle();
            bundle.putString("opg_id",String.valueOf(item.getOpg_id()));
            startActivity(ReturnPriceDetailsActivity.class,bundle);
        }

        if(code == ConstantsCode.EDIT_REFUND){
            Bundle bundle = new Bundle();
            bundle.putString("opg_id",String.valueOf(item.getOpg_id()));
            startActivity(ReturnPriceDetailsActivity.class,bundle);
        }
    }
}
