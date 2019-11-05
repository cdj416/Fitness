package com.hongyuan.fitness.ui.membership_card.card_detail.add_person;

import android.os.Bundle;
import android.view.View;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.base.CustomViewModel;
import com.hongyuan.fitness.databinding.ActivityCardAddPersonBinding;
import com.hongyuan.fitness.ui.membership_card.card_detail.CardAddPersonBeans;
import com.hongyuan.fitness.util.BaseUtil;
import com.hongyuan.fitness.util.BasisTimesUtils;
import com.hongyuan.fitness.util.CustomDialog;

public class CardAddPersonViewModel extends CustomViewModel {

    public static final int PERSON = 0X01;
    public static final int CHILD = 0X02;
    private int type;

    private ActivityCardAddPersonBinding binding;

    //生日使用的年月日
    private int birthYear = 1991;
    private int birthMonth = 12;
    private int birthDay = 12;

    //修改传递过来的对象
    private CardAddPersonBeans updataBeans;
    //对象id
    private String beansId;

    public CardAddPersonViewModel(CustomActivity mActivity,ActivityCardAddPersonBinding binding) {
        super(mActivity);
        this.binding = binding;
        initView();
    }

    @Override
    protected void initView() {
        beansId = getBundle("bundle").getString("beansId");
        type = getBundle("bundle").getInt("showType");
        updataBeans = (CardAddPersonBeans) getBundle("bundle").getSerializable("beans");
        mActivity.getMainTitle().setLeftImage(R.mipmap.close_heise_img);
        mActivity.getMainTitle().setRightText("完成");
        if( type == PERSON){
            if(updataBeans != null){
                mActivity.getMainTitle().setCentreText("修改信息");
                binding.etName.setText(updataBeans.getName());
                binding.etPhoneNum.setText(updataBeans.getPhoneOrDays());
            }else{
                mActivity.getMainTitle().setCentreText("添加成人");
            }
            binding.phoneBox.setVisibility(View.VISIBLE);
        }else{
            if(updataBeans != null){
                mActivity.getMainTitle().setCentreText("修改信息");
                binding.etName.setText(updataBeans.getName());
                binding.tvDays.setText(updataBeans.getPhoneOrDays().substring(5));
            }else{
                mActivity.getMainTitle().setCentreText("添加儿童");
            }
            binding.daysBox.setVisibility(View.VISIBLE);
        }

        binding.tvDays.setOnClickListener(v -> {
            BasisTimesUtils.showDatePickerDialog(mActivity, BasisTimesUtils.THEME_HOLO_DARK, "请选择年月日", birthYear, birthMonth, birthDay, new BasisTimesUtils.OnDatePickerListener() {

                @Override
                public void onConfirm(int year, int month, int dayOfMonth) {
                    birthYear = year;
                    birthMonth = month;
                    birthDay = dayOfMonth;

                    binding.tvDays.setText(birthYear+"-"+birthMonth+"-"+birthDay);
                }

                @Override
                public void onCancel() {

                }
            });
        });

        mActivity.getMainTitle().getRightView().setOnClickListener(v -> {
            if(!BaseUtil.isValue(binding.etName.getText().toString())){
                CustomDialog.showMessage(mActivity,"请输入姓名！");
                return;
            }
            if(type == PERSON && !BaseUtil.isValue(binding.etPhoneNum.getText().toString())){
                CustomDialog.showMessage(mActivity,"请输入手机号码！");
                return;
            }

            if(type == CHILD && !BaseUtil.isValue(binding.tvDays.getText().toString())){
                CustomDialog.showMessage(mActivity,"请选择出生日期！");
                return;
            }

            CardAddPersonBeans addBeans = new CardAddPersonBeans();
            addBeans.setBeansId(beansId);
            addBeans.setName(binding.etName.getText().toString());
            if(type == PERSON){
                addBeans.setPhoneOrDays(binding.etPhoneNum.getText().toString());
            }else{
                addBeans.setPhoneOrDays("出生日期："+binding.tvDays.getText().toString());
            }
            addBeans.setType(type);

            Bundle bundle = new Bundle();
            bundle.putSerializable("addPerson",addBeans);
            setResult(bundle);
        });
    }

    @Override
    public void onSuccess(Object data) {

    }
}
