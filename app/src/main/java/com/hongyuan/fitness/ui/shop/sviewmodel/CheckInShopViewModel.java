package com.hongyuan.fitness.ui.shop.sviewmodel;

import android.graphics.Color;
import android.view.View;
import android.view.ViewTreeObserver;

import com.hongyuan.fitness.base.BaseBean;
import com.hongyuan.fitness.base.Constants;
import com.hongyuan.fitness.base.ConstantsCode;
import com.hongyuan.fitness.base.Controller;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.base.CustomViewModel;
import com.hongyuan.fitness.custom_view.scllor_view.UnitBean;
import com.hongyuan.fitness.custom_view.scllor_view.UnitBeanUtils;
import com.hongyuan.fitness.custom_view.time_selecter.OptionsPickerView;
import com.hongyuan.fitness.custom_view.time_selecter.get_address.JsonDataBean;
import com.hongyuan.fitness.custom_view.time_selecter.get_address.OptionsPickerBuilder;
import com.hongyuan.fitness.custom_view.time_selecter.use_time.GetTimeData;
import com.hongyuan.fitness.databinding.AcitivityCheckInShopBinding;
import com.hongyuan.fitness.ui.main.main_mall.MallMenuBeans;
import com.hongyuan.fitness.ui.shop.sbeans.AdminNumUse;
import com.hongyuan.fitness.ui.shop.sbeans.CheckinDetailsBean;
import com.hongyuan.fitness.util.BaseUtil;
import com.hongyuan.fitness.util.CustomDialog;
import com.hongyuan.fitness.util.HiddenAnimUtils;
import com.hongyuan.fitness.util.TimeUtil;

import java.util.ArrayList;
import java.util.List;

public class CheckInShopViewModel extends CustomViewModel {

    public static final int SHOW_SHOP = 1;
    public static final int SHOW_GYM = 2;
    public static final int SHOW_VENUE = 3;
    private int showType = SHOW_SHOP;
    //是否提交过1.提交，0.未提交
    private int submitStatus;

    private AcitivityCheckInShopBinding binding;

    //主营类目数据集
    private List<MallMenuBeans.DataBean.ListBean> mallList;
    //地址数据
    private JsonDataBean useAddress;
    //已提交过之后请求的回显数据
    private CheckinDetailsBean.DataBean dataBean;

    //主营类目转换工具
    private UnitBeanUtils storeUtils;

    //展开收缩动画工具类
    private HiddenAnimUtils animUtils;
    //顶部提示消息展开收缩动画工具类
    private HiddenAnimUtils topAnimUtils;
    //展开收缩高度
    private int detailBoxHeight;
    //营业期限时间悬着器
    private GetTimeData licenseGetTime;
    //组织机构代码证有效期悬着器
    private GetTimeData tissueDataGetTime;

    public CheckInShopViewModel(CustomActivity mActivity, AcitivityCheckInShopBinding binding) {
        super(mActivity);
        this.binding = binding;

        showType();
    }

    public void setAddressList(JsonDataBean useAddress){
        this.useAddress = useAddress;
    }

    /*
    * 区分显示类型
    * */
    private void showType(){
        submitStatus = getBundle().getInt("state");
        showType = getBundle().getInt("type");

        if(showType == SHOW_SHOP){
            mActivity.getMainTitle().setCentreText("商城入驻");
            binding.category.setVisibility(View.VISIBLE);
            binding.shopTypeBox.setVisibility(View.VISIBLE);
            binding.backstageNumBox.setVisibility(View.GONE);
        }

        if(showType == SHOW_VENUE){
            mActivity.getMainTitle().setCentreText("场馆入驻");
            binding.category.setVisibility(View.GONE);
            binding.shopTypeBox.setVisibility(View.GONE);
            binding.backstageNumBox.setVisibility(View.VISIBLE);
        }

        if(showType == SHOW_GYM){
            mActivity.getMainTitle().setCentreText("健身房入驻");
            binding.category.setVisibility(View.GONE);
            binding.shopTypeBox.setVisibility(View.GONE);
            binding.backstageNumBox.setVisibility(View.VISIBLE);
        }

        initView();
        lazyLoad();
    }

    @Override
    protected void initView() {
        setEnableOverScrollDrag(true);

        //初始化头部消息动画对象
        topAnimUtils = HiddenAnimUtils.newInstance(mActivity,binding.failureBox,binding.closeMessage,72,0,false);
        binding.closeMessage.setOnClickListener(v -> topAnimUtils.toggle());

        //设置需要上传图片的viewmodel
        binding.license.setViewModel(this);
        binding.cardImg.setViewModel(this);
        binding.tissueImg.setViewModel(this);
        binding.taxypayerImg.setViewModel(this);

        //初始化为0
        binding.switchBt.setUseValues("","0");
        binding.switchBt.setSwicthListener(flag -> {
            if(flag){
                binding.switchBt.setUseValues("","1");
            }else{
                binding.switchBt.setUseValues("","0");
            }
            //隐藏展开布局
            animUtils.toggle();
        });

        binding.category.setOnClickListener(view -> {
            if(storeUtils == null){
                CustomDialog.showMessage(mActivity,"抱歉，未查到数据！");
                return;
            }
            CustomDialog.scroller(mActivity, storeUtils.getUnitList(mallList), "主营类目", (v, message) -> {
                binding.category.setUseValues(message,storeUtils.getUseId(message));
            });
        });

        //显示地址弹框
        binding.showAddress.setOnClickListener(v -> {
            if(BaseUtil.isValue(useAddress)){
                showAddress(useAddress);
            }else{
                CustomDialog.showMessage(mActivity,"获取地址数据失败！");
            }
        });

        //监听设值之后的高度，用于显示隐藏
        ViewTreeObserver vto = binding.noUnityBox.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                binding.noUnityBox.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                detailBoxHeight = binding.noUnityBox.getHeight();
                //初始化动画对象
                animUtils = HiddenAnimUtils.newInstance(mActivity,binding.noUnityBox,null,detailBoxHeight,false);
            }
        });

        //营业期限日期选择
        licenseGetTime = new GetTimeData(mActivity, (date, v) -> {
            binding.licenseData.setUseValues(TimeUtil.getStringByFormat(date,TimeUtil.dateFormatYMD),TimeUtil.getStringByFormat(date,TimeUtil.dateFormatYMD));
        },TimeUtil.getCurrentDateFormat(),TimeUtil.getDateByFormat("3333-03-03",TimeUtil.dateFormatYMD),GetTimeData.YMD);
        binding.licenseData.setOnClickListener(v -> {
            licenseGetTime.showTime();
        });

        //营业期限日期选择
        tissueDataGetTime = new GetTimeData(mActivity, (date, v) -> {
            binding.tissueData.setUseValues(TimeUtil.getStringByFormat(date,TimeUtil.dateFormatYMD),TimeUtil.getStringByFormat(date,TimeUtil.dateFormatYMD));
        },TimeUtil.getCurrentDateFormat(),TimeUtil.getDateByFormat("3333-03-03",TimeUtil.dateFormatYMD),GetTimeData.YMD);
        binding.tissueData.setOnClickListener(v -> {
            tissueDataGetTime.showTime();
        });

        //营业期限处理
        setLicenseData();
    }

    /*
    * 非三合一处理营业期限类型
    * */
    private void setLicenseData(){
        List<UnitBean> useList = new ArrayList<>();
        UnitBean unitBean = new UnitBean();
        unitBean.unit = "2";
        unitBean.unit_cn = "固定期限有效";
        useList.add(unitBean);

        UnitBean unitBean1 = new UnitBean();
        unitBean1.unit = "1";
        unitBean1.unit_cn = "长期有效";
        useList.add(unitBean1);

        //初始化显示效果
        binding.licenseDataType.setUseValues("固定期限有效","2");
        binding.licenseDataType.setOnClickListener(view -> {
            CustomDialog.scroller(mActivity, useList, "营业期限类型", (v, message) -> {
                String useType;
                if("固定期限有效".equals(message)){
                    useType = "2";
                    binding.licenseData.setVisibility(View.VISIBLE);
                }else{
                    useType = "1";
                    binding.licenseData.setVisibility(View.GONE);
                }
                binding.licenseDataType.setUseValues(message,useType);
            });
        });

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
            binding.showAddress.setUseValues(showAddress,showAddress);

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

    @Override
    protected void lazyLoad() {
        //获取商家分类
        mActivity.showLoading();
        clearParams();
        Controller.myRequest(Constants.GET_FIRST_CATEGORY,Controller.TYPE_POST,getParams(), MallMenuBeans.class,this);
    }

    /*
    * 回显数据的请求
    * */
    private void getHuiXianData(){
        clearParams().setParams("apply_type",String.valueOf(showType));
        Controller.myRequest(Constants.GET_APPLY_INFO,Controller.TYPE_POST,getParams(), CheckinDetailsBean.class,this);
    }

    /*
    * 检查超级管理员是否可用
    * */
    public void checkAdmin(){

        if(showType == SHOW_SHOP){
            uploadData();
        }else{
            if(!BaseUtil.isValue(binding.adminNum.getUseValues())){
                CustomDialog.showMessage(mActivity,"请输入账号");
                return;
            }
            clearParams().setParams("admin_name",binding.adminNum.getUseValues());
            Controller.myRequest(Constants.CHECK_ADMIN_NAME,Controller.TYPE_POST,getParams(), AdminNumUse.class,this);
        }

    }

    /*
    * 上传申请数据
    * */
    private void uploadData(){
        if(showType == SHOW_SHOP){
            if(!BaseUtil.isValue(binding.category.getUseValues())){
                CustomDialog.showMessage(mActivity,"请选择分类数据");
                return;
            }
            if(!BaseUtil.isValue(binding.shopName.getUseValues())){
                CustomDialog.showMessage(mActivity,"请填写店铺名");
                return;
            }
        }else{
            if(!BaseUtil.isValue(binding.adminNum.getUseValues())){
                CustomDialog.showMessage(mActivity,"请输入账号");
                return;
            }
            if(!BaseUtil.isValue(binding.passwordNum.getUseValues())){
                CustomDialog.showMessage(mActivity,"请输入密码");
                return;
            }
            if(!BaseUtil.isValue(binding.passwordNumAgin.getUseValues())){
                CustomDialog.showMessage(mActivity,"请确认密码");
                return;
            }

            if(!binding.passwordNumAgin.getUseValues().equals(binding.passwordNum.getUseValues())){
                CustomDialog.showMessage(mActivity,"两次密码不一致");
                return;
            }
        }

        if(!BaseUtil.isValue(binding.license.getImgUrl1())){
            CustomDialog.showMessage(mActivity,"请上传营业执照");
            return;
        }
        if(!BaseUtil.isValue(binding.creditUnion.getUseValues())){
            CustomDialog.showMessage(mActivity,"请填写信用社代码");
            return;
        }
        if(!BaseUtil.isValue(binding.companyName.getUseValues())){
            CustomDialog.showMessage(mActivity,"请填写公司名");
            return;
        }
        if(!BaseUtil.isValue(binding.showAddress.getUseValues())){
            CustomDialog.showMessage(mActivity,"请选择公司地址");
            return;
        }
        if(!BaseUtil.isValue(binding.specificAddress.getText().toString())){
            CustomDialog.showMessage(mActivity,"请填写具体地址");
            return;
        }
        if(!BaseUtil.isValue(binding.actualName.getUseValues())){
            CustomDialog.showMessage(mActivity,"请填写真实姓名");
            return;
        }
        if(!BaseUtil.isValue(binding.idCode.getUseValues())){
            CustomDialog.showMessage(mActivity,"请填写身份证");
            return;
        }
        if(!BaseUtil.isValue(binding.phoneNum.getUseValues())){
            CustomDialog.showMessage(mActivity,"请填写法人手机号");
            return;
        }
        if(!BaseUtil.isValue(binding.cardImg.getImgUrl1())){
            CustomDialog.showMessage(mActivity,"请上传正面身份证");
            return;
        }
        if(!BaseUtil.isValue(binding.cardImg.getImgUrl2())){
            CustomDialog.showMessage(mActivity,"请上传反面身份证");
            return;
        }

        if("0".equals(binding.switchBt.getUseValues())){
            if(!BaseUtil.isValue(binding.licenseNum.getUseValues())){
                CustomDialog.showMessage(mActivity,"请填写营业执照注册号");
                return;
            }
            if("2".equals(binding.licenseDataType.getUseValues()) && !BaseUtil.isValue(binding.licenseData.getUseValues())){
                CustomDialog.showMessage(mActivity,"请选择营业期限");
                return;
            }
            if(!BaseUtil.isValue(binding.tissueNum.getUseValues())){
                CustomDialog.showMessage(mActivity,"请填写组织机构代码");
                return;
            }
            if(!BaseUtil.isValue(binding.taxpayerNum.getUseValues())){
                CustomDialog.showMessage(mActivity,"请填写纳税人识别码");
                return;
            }
            if(!BaseUtil.isValue(binding.tissueData.getUseValues())){
                CustomDialog.showMessage(mActivity,"请选择机构代码证有效期");
                return;
            }
            if(!BaseUtil.isValue(binding.tissueImg.getImgUrl1())){
                CustomDialog.showMessage(mActivity,"请上传机构代码证");
                return;
            }
            if(!BaseUtil.isValue(binding.taxypayerImg.getImgUrl1())){
                CustomDialog.showMessage(mActivity,"请上传税务登记证");
                return;
            }
        }

        //获取商家分类
        mActivity.showLoading();
        clearParams().setParams("apply_type",String.valueOf(showType))
                .setParams("is_san",binding.switchBt.getUseValues())
                .setParams("license",binding.license.getImgUrl1())
                .setParams("credit",binding.creditUnion.getUseValues())
                .setParams("company",binding.companyName.getUseValues())
                .setParams("pid",useAddress.getUseProCode())
                .setParams("cid",useAddress.getUsecityCode())
                .setParams("did",useAddress.getUseAreaCode())
                .setParams("pname",useAddress.getUsePro())
                .setParams("cname",useAddress.getUseCity())
                .setParams("dname",useAddress.getUseArea())
                .setParams("address",binding.specificAddress.getText().toString())
                .setParams("real_name",binding.actualName.getUseValues())
                .setParams("id_card",binding.idCode.getUseValues())
                .setParams("mobile",binding.phoneNum.getUseValues())
                .setParams("mobile",binding.phoneNum.getUseValues())
                .setParams("id_img1",binding.cardImg.getImgUrl1())
                .setParams("id_img2",binding.cardImg.getImgUrl2());

        if("0".equals(binding.switchBt.getUseValues())){
            setParams("register_num",binding.licenseNum.getUseValues())
                    .setParams("business_time_type",binding.licenseDataType.getUseValues())
                    .setParams("jigou_num",binding.tissueNum.getUseValues())
                    .setParams("shui_num",binding.taxpayerNum.getUseValues())
                    .setParams("jigou_time",binding.tissueData.getUseValues())
                    .setParams("jigou_img",binding.tissueImg.getImgUrl1())
                    .setParams("shui_img",binding.taxypayerImg.getImgUrl1());
            if("2".equals(binding.licenseDataType.getUseValues())){
                setParams("business_time",binding.licenseData.getUseValues());
            }
        }
        if(showType == SHOW_SHOP){
            setParams("store_name",binding.shopName.getUseValues())
                    .setParams("category_id",binding.category.getUseValues());
        }else{
            setParams("admin_name",binding.adminNum.getUseValues());
            setParams("admin_pwd",binding.passwordNum.getUseValues());
            setParams("comfirm_pwd",binding.passwordNumAgin.getUseValues());
        }

        mActivity.showLoading();
        if(submitStatus == 1){
            setParams("apply_id",String.valueOf(dataBean.getApply_id()));
            Controller.myRequest(ConstantsCode.EDIT_APPLY,Constants.EDIT_APPLY,Controller.TYPE_POST,getParams(), BaseBean.class,this);
        }else{
            Controller.myRequest(ConstantsCode.APPLY_IN,Constants.APPLY_IN,Controller.TYPE_POST,getParams(), BaseBean.class,this);
        }
    }

    @Override
    public void onSuccess(Object data) {
        if(data instanceof MallMenuBeans){
            mallList = ((MallMenuBeans)data).getData().getList();

            if(mallList != null && mallList.size() > 0){
                //初始化实体类转换工具
                storeUtils = new UnitBeanUtils<MallMenuBeans.DataBean.ListBean>() {
                    @Override
                    public String unit(MallMenuBeans.DataBean.ListBean t) {
                        return String.valueOf(t.getCategory_id());
                    }

                    @Override
                    public String unit_cn(MallMenuBeans.DataBean.ListBean t) {
                        return t.getCategory_name();
                    }
                };
            }

            //已提交过数据，需要回显
            if(submitStatus == 1){

                getHuiXianData();
            }else{
                mActivity.closeLoading();
            }
        }

        if(data instanceof AdminNumUse){
            AdminNumUse.DataBean numUse = ((AdminNumUse)data).getData();
            if(numUse.getState() == 1){
                uploadData();
            }else{
                CustomDialog.showMessage(mActivity,numUse.getMsg());
            }
        }

        if(data instanceof CheckinDetailsBean){
            mActivity.closeLoading();
            dataBean = ((CheckinDetailsBean)data).getData();

            binding.switchBt.setUseValues("",String.valueOf(dataBean.getIs_san()));
            binding.license.setShowImg1(dataBean.getLicense());
            binding.creditUnion.setUseValues(dataBean.getCredit(),dataBean.getCredit());
            binding.companyName.setUseValues(dataBean.getCompany(),dataBean.getCompany());
            binding.showAddress.setUseValues(dataBean.getPname()+" "+dataBean.getCname()+" "+dataBean.getDname(),dataBean.getPname()+" "+dataBean.getCname()+" "+dataBean.getDname());
            useAddress.setUseProCode(String.valueOf(dataBean.getPid()));
            useAddress.setUsecityCode(String.valueOf(dataBean.getCid()));
            useAddress.setUseAreaCode(String.valueOf(dataBean.getDid()));
            useAddress.setUsePro(dataBean.getPname());
            useAddress.setUseCity(dataBean.getCname());
            useAddress.setUseArea(dataBean.getDname());
            binding.specificAddress.setText(dataBean.getAddress());
            binding.actualName.setUseValues(dataBean.getReal_name(),dataBean.getReal_name());
            binding.idCode.setUseValues(dataBean.getId_card(),dataBean.getId_card());
            binding.phoneNum.setUseValues(dataBean.getMobile(),dataBean.getMobile());
            binding.cardImg.setShowImg1(dataBean.getId_img1());
            binding.cardImg.setShowImg2(dataBean.getId_img2());

            if(BaseUtil.isValue(dataBean.getRegister_num()))
            binding.licenseNum.setUseValues(dataBean.getRegister_num(),dataBean.getRegister_num());
            if(BaseUtil.isValue(dataBean.getBusiness_time_type()))
                binding.licenseDataType.setUseValues(dataBean.getBusiness_time_type() == 1?"长期有效":"固定期限有效",String.valueOf(dataBean.getBusiness_time_type()));
            if(BaseUtil.isValue(dataBean.getBusiness_time()))
                binding.licenseData.setUseValues(dataBean.getBusiness_date(),dataBean.getBusiness_date());
            if(BaseUtil.isValue(dataBean.getJigou_num()))
                binding.tissueNum.setUseValues(dataBean.getJigou_num(),dataBean.getJigou_num());
            if(BaseUtil.isValue(dataBean.getShui_num()))
                binding.taxpayerNum.setUseValues(dataBean.getShui_num(),dataBean.getShui_num());
            if(BaseUtil.isValue(dataBean.getJigou_date()))
                binding.tissueData.setUseValues(dataBean.getJigou_date(),dataBean.getJigou_date());
            if(BaseUtil.isValue(dataBean.getJigou_img()))
                binding.tissueImg.setShowImg1(dataBean.getJigou_img());
            if(BaseUtil.isValue(dataBean.getShui_img()))
                binding.taxypayerImg.setShowImg1(dataBean.getShui_img());

            if(dataBean.getIs_san() == 1 && animUtils != null)animUtils.toggle();

            if(showType == SHOW_SHOP){
                binding.category.setUseValues(dataBean.getCategory_name(),String.valueOf(dataBean.getCategory_id()));
                binding.shopName.setUseValues(dataBean.getStore_name(),dataBean.getStore_name());
            }else{
                binding.adminNum.setUseValues(dataBean.getAdmin_name(),dataBean.getAdmin_name());
                binding.passwordNum.setUseValues(dataBean.getAdmin_pwd(),dataBean.getAdmin_pwd());
                binding.passwordNumAgin.setUseValues(dataBean.getAdmin_pwd(),dataBean.getAdmin_pwd());
            }


            if(BaseUtil.isValue(dataBean.getApply_note())){
                binding.failureMessage.setText("失败原因："+dataBean.getApply_note());
                topAnimUtils.toggle();
            }
        }
    }

    @Override
    public void onSuccess(int code, Object data) {
        mActivity.closeLoading();
        if(code == ConstantsCode.APPLY_IN){
            mActivity.showSuccess("提交成功！");
        }
        if(code == ConstantsCode.EDIT_APPLY){
            mActivity.showSuccess("修改成功！");
        }
    }
}
