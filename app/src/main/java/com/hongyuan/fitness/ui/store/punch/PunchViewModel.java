package com.hongyuan.fitness.ui.store.punch;

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
import com.hongyuan.fitness.base.SingleClick;
import com.hongyuan.fitness.databinding.ActivityStoreSignInBinding;

public class PunchViewModel extends CustomViewModel {

    private ActivityStoreSignInBinding binding;
    private PunchBeans.DataBean punchBeans;

    public PunchViewModel(CustomActivity mActivity, ActivityStoreSignInBinding binding) {
        super(mActivity);
        this.binding = binding;
        initView();
        lazyLoad();
    }

    @Override
    protected void initView() {
        RequestOptions options = new RequestOptions().placeholder(R.mipmap.defaul_no_img).error(R.mipmap.defaul_no_img);
        Glide.with(mActivity).load(getBundle().getString("bg_img")).apply(options).into(binding.bgImg);

        binding.punchBut.setOnClickListener(new View.OnClickListener() {
            @SingleClick(2000)
            @Override
            public void onClick(View v) {
                clickPunch();
            }
        });
    }

    @Override
    protected void setData() {
        binding.cardDay.setText(String.valueOf(punchBeans.getItem().getCard_days()));
        binding.rentDay.setText(String.valueOf(0));
        binding.priviteCourseNum.setText(String.valueOf(punchBeans.getItem().getCp_num()));
        binding.toStoreDay.setText(String.valueOf(punchBeans.getItem().getIn_os_days()));
        binding.groupCoursNum.setText(String.valueOf(punchBeans.getItem().getCs_num()));
        binding.punchNum.setText(String.valueOf(punchBeans.getItem().getIn_os_days_week()));
        binding.storePeople.setText(String.valueOf(punchBeans.getItem().getIn_num()));

        if("0".equals(punchBeans.getIs_in())){
            binding.punchBut.setText("进店打卡");
        }else{
            binding.punchBut.setText("离店打卡");
        }
    }

    /*
    * 打卡
    * */
    private void clickPunch(){
        clearParams().setParams("os_id",getBundle().getString("os_id"));
        if("0".equals(punchBeans.getIs_in())){
            //进店打卡
            Controller.myRequest(ConstantsCode.MEMBER_COME_STORE_DO_CARD,Constants.MEMBER_COME_STORE_DO_CARD,Controller.TYPE_POST,getParams(), BaseBean.class,this);
        }else{
            //离店打卡
            Controller.myRequest(ConstantsCode.MEMBER_OFF_STORE_DO_CARD,Constants.MEMBER_OFF_STORE_DO_CARD,Controller.TYPE_POST,getParams(), BaseBean.class,this);
        }


    }

    @Override
    protected void lazyLoad() {
        //读取默认门店或者购买门店信息
        clearParams().setParams("os_id",getBundle().getString("os_id"));
        Controller.myRequest(Constants.CHECK_COME_OR_OFF_STORE,Controller.TYPE_POST,getParams(), PunchBeans.class,this);
    }

    @Override
    public void onSuccess(Object data) {
        if(data instanceof PunchBeans){
            punchBeans = ((PunchBeans)data).getData();
            setData();
        }
    }

    @Override
    public void onSuccess(int code, Object data) {
        super.onSuccess(code,data);

        if(code == ConstantsCode.MEMBER_COME_STORE_DO_CARD){
            punchBeans.setIs_in("1");
            showSuccess("进店打卡成功！");
            binding.punchBut.setText("离店打卡");
        }
        if(code == ConstantsCode.MEMBER_OFF_STORE_DO_CARD){
            punchBeans.setIs_in("0");
            showSuccess("离店店打卡成功！");
            binding.punchBut.setText("进店打卡");
        }
        lazyLoad();
    }
}
