package com.hongyuan.fitness.ui.person.fix;

import android.view.View;

import com.hongyuan.fitness.base.BaseBean;
import com.hongyuan.fitness.base.Constants;
import com.hongyuan.fitness.base.ConstantsCode;
import com.hongyuan.fitness.base.Controller;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.base.CustomViewModel;
import com.hongyuan.fitness.custom_view.scllor_view.UnitBeanUtils;
import com.hongyuan.fitness.databinding.AcitivityCancelCourseBinding;
import com.hongyuan.fitness.util.CustomDialog;

import java.util.List;

public class CanncelCourseViewModel extends CustomViewModel {

    private AcitivityCancelCourseBinding binding;

    //配送方式选择工具类
    private UnitBeanUtils rUtils;
    private List<CancelCourseResionBeans.DataBean.ListBean> rList;

    public CanncelCourseViewModel(CustomActivity mActivity,AcitivityCancelCourseBinding binding) {
        super(mActivity);
        this.binding = binding;

        initView();
        lazyLoad();
    }

    @Override
    protected void initView() {

        binding.selectRec.setOnClickListener(v -> {
            CustomDialog.scroller(mActivity, rUtils.getUnitList(rList), "选择取消原因", (v1, message) -> {
                binding.selectRec.setRightText(message);
                binding.selectRec.setUseValue(rUtils.getUseId(message));
            });
        });

        binding.callTel.setOnClickListener(v -> {
            /*CustomDialog.callTel(mActivity, getBundle().getString("tel"), v12 -> {
                //callTel(getBundle().getString("tel"));
            });*/
        });

        binding.cancel.setOnClickListener(v -> {
            cancelRese();
        });
    }

    /*
     * 取消私教课预约
     * */
    private void cancelRese(){
        clearParams().setParams("cpa_id",getBundle().getString("cpa_id"))
                .setParams("cancel_reason_id",binding.selectRec.getRightText())
                .setParams("cancel_note",binding.notes.getText().toString());
        Controller.myRequest(ConstantsCode.CANCEL_COURSE_PRIVITE_APPOINTMENT_NEW,Constants.CANCEL_COURSE_PRIVITE_APPOINTMENT_NEW,Controller.TYPE_POST,getParams(), BaseBean.class,this);
    }

    @Override
    protected void lazyLoad() {
        mActivity.showLoading();
        clearParams();
        Controller.myRequest(Constants.GET_CANCEL_REASON,Controller.TYPE_POST,getParams(), CancelCourseResionBeans.class,this);
    }

    @Override
    public void onSuccess(Object data) {
        mActivity.closeLoading();

        if(data instanceof CancelCourseResionBeans){
            rList = ((CancelCourseResionBeans)data).getData().getList();

            binding.selectRec.setRightText(rList.get(0).getName());
            binding.selectRec.setUseValue(String.valueOf(rList.get(0).getReason_id()));
            rUtils = new UnitBeanUtils<CancelCourseResionBeans.DataBean.ListBean>() {
                @Override
                public String unit(CancelCourseResionBeans.DataBean.ListBean o) {
                    return String.valueOf(o.getReason_id());
                }

                @Override
                public String unit_cn(CancelCourseResionBeans.DataBean.ListBean o) {
                    return o.getName();
                }
            };
        }

    }

    @Override
    public void onSuccess(int code, Object data) {
        if(code == ConstantsCode.CANCEL_COURSE_PRIVITE_APPOINTMENT_NEW){
            mActivity.showSuccess("已发起申请！");
        }
    }
}
