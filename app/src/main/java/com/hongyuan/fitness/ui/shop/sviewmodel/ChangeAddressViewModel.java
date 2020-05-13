package com.hongyuan.fitness.ui.shop.sviewmodel;

import android.graphics.Color;
import android.view.View;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.BaseBean;
import com.hongyuan.fitness.base.Constants;
import com.hongyuan.fitness.base.ConstantsCode;
import com.hongyuan.fitness.base.Controller;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.base.CustomViewModel;
import com.hongyuan.fitness.custom_view.time_selecter.OptionsPickerView;
import com.hongyuan.fitness.custom_view.time_selecter.get_address.JsonDataBean;
import com.hongyuan.fitness.custom_view.time_selecter.get_address.OptionsPickerBuilder;
import com.hongyuan.fitness.databinding.AcitivityChangeAddressBinding;
import com.hongyuan.fitness.ui.shop.sbeans.AddressInfoBeans;
import com.hongyuan.fitness.util.BaseUtil;
import com.hongyuan.fitness.util.CustomDialog;

public class ChangeAddressViewModel extends CustomViewModel {
    public static final int ADD = 0X01;
    public static final int MODIFY = 0X02;

    public int show_type = ADD;

    private AcitivityChangeAddressBinding binding;

    //地址数据
    private JsonDataBean useAddress;
    //详情数据
    private AddressInfoBeans.DataBean infoData;

    public ChangeAddressViewModel(CustomActivity mActivity,AcitivityChangeAddressBinding binding) {
        super(mActivity);
        this.binding = binding;

        initView();
    }

    public void setAddressList(JsonDataBean useAddress){
        this.useAddress = useAddress;
        //去尝试设置一次
        setShowAddress();
    }

    @Override
    protected void initView() {
        show_type = getBundle().getInt("type");

        if(show_type == ADD){
            binding.deleteBox.setVisibility(View.GONE);
            mActivity.getMainTitle().setCentreText("添加收获地址")
                    .setRightTextColor("添加",mActivity.getResources().getColor(R.color.color_FF333333))
                    .setOnClickListener(v -> {
                        addAddress();
            });
        }else{
            binding.deleteBox.setVisibility(View.VISIBLE);
            mActivity.getMainTitle().setCentreText("修改收获地址")
                    .setRightTextColor("修改",mActivity.getResources().getColor(R.color.color_FF333333))
                    .setOnClickListener(v -> {
                        addAddress();
            });
        }

        //显示地址弹框
        binding.selectAddress.setOnClickListener(v -> {
            if(BaseUtil.isValue(useAddress)){
                showAddress(useAddress);
            }else{
                CustomDialog.showMessage(mActivity,"获取地址数据失败！");
            }
        });

        //初始化为0
        binding.isDefault.setUseValues("","0");
        binding.isDefault.setSwicthListener(flag -> {
            if(flag){
                binding.isDefault.setUseValues("","1");
            }else{
                binding.isDefault.setUseValues("","0");
            }
        });
        //删除地址
        binding.delete.setOnClickListener(v -> {
            deleteAddress();
        });

        if(show_type == MODIFY){
            lazyLoad();
        }
    }

    /*
     * 显示地址数据
     * */
    public void showAddress(JsonDataBean useAddress){
        OptionsPickerView pickerView = new OptionsPickerBuilder(mActivity, (options1, options2, options3, v) -> {
            String showAddress = useAddress.getProvinceList().get(options1).getRegion_name()+" "
                    +useAddress.getCityList().get(options1).get(options2).getRegion_name();

            if(useAddress.getAreaList().get(options1).get(options2).size()>0){
                showAddress += " "+useAddress.getAreaList().get(options1).get(options2).get(options3).getRegion_name();
            }
            binding.selectAddress.setUseValues(showAddress,showAddress);

            useAddress.setUsePro(useAddress.getProvinceList().get(options1).getRegion_name());
            useAddress.setUseProCode(useAddress.getProvinceList().get(options1).getRegion_code());
            if(BaseUtil.isValue(useAddress.getCityList().get(options1).get(options2).getRegion_name())){
                useAddress.setUseCity(useAddress.getCityList().get(options1).get(options2).getRegion_name());
                useAddress.setUsecityCode(useAddress.getCityList().get(options1).get(options2).getRegion_code());
            }else{
                useAddress.setUseCity(useAddress.getProvinceList().get(options1).getRegion_name());
                useAddress.setUsecityCode(useAddress.getProvinceList().get(options1).getRegion_code());
            }
            if(useAddress.getAreaList().get(options1).get(options2).size() > 0){
                useAddress.setUseArea(useAddress.getAreaList().get(options1).get(options2).get(options3).getRegion_name());
                useAddress.setUseAreaCode(useAddress.getAreaList().get(options1).get(options2).get(options3).getRegion_code());
            }else{
                useAddress.setUseArea(useAddress.getProvinceList().get(options1).getRegion_name());
                useAddress.setUseAreaCode(useAddress.getProvinceList().get(options1).getRegion_code());
            }
        }).setTitleText("城市选择")
                .setDividerColor(Color.BLACK)
                .setTextColorCenter(Color.BLACK) //设置选中项文字颜色
                .setContentTextSize(20)
                .build();

        pickerView.setPicker(useAddress.getProvinceList(), useAddress.getCityList(), useAddress.getAreaList());//三级选择器
        pickerView.show();
    }

    /*
    * 添加修改地址
    * */
    private void addAddress(){
        if(!BaseUtil.isValue(binding.name.getText().toString())){
            CustomDialog.showMessage(mActivity,"请填写收货人名！");
            return;
        }
        if(!BaseUtil.isValue(binding.telNum.getText().toString())){
            CustomDialog.showMessage(mActivity,"请填写电话号码！");
            return;
        }
        if(useAddress != null && !BaseUtil.isValue(useAddress.getUsePro())){
            CustomDialog.showMessage(mActivity,"请选择地址！");
            return;
        }
        if(!BaseUtil.isValue(binding.details.getText().toString())){
            CustomDialog.showMessage(mActivity,"请填写详情地址！");
            return;
        }

        mActivity.showLoading();
        clearParams().setParams("name",binding.name.getText().toString())
                .setParams("tel",binding.telNum.getText().toString())
                .setParams("address",binding.details.getText().toString())
                .setParams("is_default",binding.isDefault.getUseValues());

        if(useAddress != null && BaseUtil.isValue(useAddress.getUsePro())){
            setParams("pid",useAddress.getUseProCode())
                    .setParams("cid",useAddress.getUsecityCode())
                    .setParams("did",useAddress.getUseAreaCode());
        }

        if(show_type == MODIFY){
            setParams("address_id",String.valueOf(infoData.getAddress_id()));
            Controller.myRequest(ConstantsCode.EDIT_RECEIVING_ADDRESS,Constants.EDIT_RECEIVING_ADDRESS,Controller.TYPE_POST,getParams(), BaseBean.class,this);
        }else{
            Controller.myRequest(ConstantsCode.ADD_RECEIVING_ADDRESS,Constants.ADD_RECEIVING_ADDRESS,Controller.TYPE_POST,getParams(), BaseBean.class,this);
        }

    }

    /*
    * 删除收货地址
    * */
    private void deleteAddress(){
        mActivity.showLoading();
        clearParams().setParams("address_id",String.valueOf(infoData.getAddress_id()));
        Controller.myRequest(ConstantsCode.DETL_RECEIVING_ADDRESS,Constants.DETL_RECEIVING_ADDRESS,Controller.TYPE_POST,getParams(), BaseBean.class,this);
    }

    @Override
    protected void lazyLoad() {
        mActivity.showLoading();
        clearParams().setParams("address_id",getBundle().getString("address_id"));
        Controller.myRequest(Constants.GET_RECEIVING_ADDRESS_INFO,Controller.TYPE_POST,getParams(), AddressInfoBeans.class,this);
    }

    @Override
    public void onSuccess(Object data) {
        mActivity.closeLoading();
        if(data instanceof AddressInfoBeans){
            infoData = ((AddressInfoBeans)data).getData();
            binding.name.setText(infoData.getName());
            binding.telNum.setText(infoData.getTel());
            binding.details.setText(infoData.getAddress());
            binding.isDefault.setUseValues(String.valueOf(infoData.getIs_default()),String.valueOf(infoData.getIs_default()));

            //去尝试设置一次
            setShowAddress();
        }
    }

    /*
    * 设置地址信息
    * */
    private void setShowAddress(){
        if(infoData != null && useAddress != null){
            binding.selectAddress.setUseValues(infoData.getProvince()+" "+infoData.getCity()+" "+infoData.getDistrict(),infoData.getProvince()+" "+infoData.getCity()+" "+infoData.getDistrict());
            useAddress.setUsePro(infoData.getProvince());
            useAddress.setUseProCode(String.valueOf(infoData.getPid()));
            useAddress.setUseCity(infoData.getCity());
            useAddress.setUsecityCode(String.valueOf(infoData.getCid()));
            useAddress.setUseArea(infoData.getDistrict());
            useAddress.setUseAreaCode(String.valueOf(infoData.getDid()));
        }
    }

    @Override
    public void onSuccess(int code, Object data) {
        mActivity.closeLoading();
        if(code == ConstantsCode.ADD_RECEIVING_ADDRESS){
            mActivity.showSuccess("添加成功！");
        }
        if(code == ConstantsCode.EDIT_RECEIVING_ADDRESS){
            mActivity.showSuccess("修改成功！");
        }

        if(code == ConstantsCode.DETL_RECEIVING_ADDRESS){
            mActivity.showSuccess("删除成功！");
        }

    }
}
