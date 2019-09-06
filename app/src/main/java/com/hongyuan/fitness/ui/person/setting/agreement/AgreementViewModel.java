package com.hongyuan.fitness.ui.person.setting.agreement;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.Constants;
import com.hongyuan.fitness.base.Controller;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.base.CustomViewModel;
import com.hongyuan.fitness.databinding.ActivityAgreementBinding;

public class AgreementViewModel extends CustomViewModel {

    private ActivityAgreementBinding binding;

    public AgreementViewModel(CustomActivity mActivity, ActivityAgreementBinding binding) {
        super(mActivity);
        this.binding = binding;
        lazyLoad();
    }

    @Override
    protected void lazyLoad() {
        clearParams().setParams("img_code","app_xy").setParams("img_num","1");
        Controller.myRequest(Constants.GET_IMG_LIST,Controller.TYPE_POST,getParams(), AgreementBeans.class,this);
    }

    @Override
    public void onSuccess(Object data) {
        if(data instanceof AgreementBeans){
            AgreementBeans beans = (AgreementBeans)data;
            RequestOptions options = new RequestOptions().placeholder(R.mipmap.agreement_img).error(R.mipmap.agreement_img);
            Glide.with(mActivity).load(beans.getData().getList().get(0).getImg_src()).apply(options).into(binding.ivBig);
        }
    }
}
