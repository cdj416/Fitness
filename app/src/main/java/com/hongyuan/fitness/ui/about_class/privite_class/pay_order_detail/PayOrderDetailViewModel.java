package com.hongyuan.fitness.ui.about_class.privite_class.pay_order_detail;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.BaseBean;
import com.hongyuan.fitness.base.Constants;
import com.hongyuan.fitness.base.Controller;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.base.CustomViewModel;
import com.hongyuan.fitness.databinding.ActivityPayOrderDetailsBinding;
import com.hongyuan.fitness.ui.about_class.check_payment_method.CheckPayMethodActivity;
import com.hongyuan.fitness.ui.about_class.check_payment_method.OrderBean;
import com.hongyuan.fitness.ui.about_class.privite_class.course_details.CourseDetailsBean;
import com.hongyuan.fitness.ui.about_class.privite_class.course_details.CourseDetailsPriceAdapter;
import com.hongyuan.fitness.ui.about_class.privite_class.course_details.PriceDataBeans;
import com.hongyuan.fitness.ui.mall.good_order_details.PointBean;
import com.hongyuan.fitness.ui.mall.good_order_details.SubmitOrderBean;
import com.hongyuan.fitness.ui.mall.good_pay.GoodsPayActivity;
import com.hongyuan.fitness.ui.mall.good_pay.PayDataBean;
import com.hongyuan.fitness.ui.promt_success.V3SuccessBeans;
import com.hongyuan.fitness.util.BaseUtil;
import com.hongyuan.fitness.util.BigDecimalUtils;
import com.hongyuan.fitness.util.CustomDialog;

import java.util.ArrayList;
import java.util.List;

import me.goldze.mvvmhabit.binding.command.BindingAction;
import me.goldze.mvvmhabit.binding.command.BindingCommand;

public class PayOrderDetailViewModel extends CustomViewModel {

    private ActivityPayOrderDetailsBinding binding;
    private CourseDetailsBean.DataBean courseDetail;
    private PayOrderDetailPriceAdapter priceAdapter;

    private PointBean pointBean;
    private PriceDataBeans priceDataBeans;

    public PayOrderDetailViewModel(CustomActivity mActivity, ActivityPayOrderDetailsBinding binding) {
        super(mActivity);
        this.binding = binding;
        initView();
        lazyLoad();
    }

    //点击减少数量
    public BindingCommand subNum = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            try {
                int classNum;
                try {
                    classNum = Integer.valueOf(binding.classNum.getText().toString());
                }catch (Exception e){
                    e.printStackTrace();
                    classNum = 1;
                }
                classNum--;
                if(classNum < 1){
                    classNum = 1;
                }
                binding.classNum.setText(String.valueOf(classNum));

            }catch (Exception e){
                e.printStackTrace();
            }
        }
    });
    //点击添加数量
    public BindingCommand addNum = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            int classNum;
            try {
                classNum = Integer.valueOf(binding.classNum.getText().toString());
            }catch (Exception e){
                e.printStackTrace();
                classNum = 0;
            }

            classNum++;
            binding.classNum.setText(String.valueOf(classNum));
        }
    });
    //去支付
    public BindingCommand goPay = new BindingCommand(() -> {
        if(courseDetail != null){
            getPriviteOrder();
        }
    });

    /*
    * 生成私教课订单
    * */
    private void getPriviteOrder(){
        if(!BaseUtil.isValue(binding.classNum.getText().toString())){
            CustomDialog.showMessage(mActivity,"请选择数量！");
            return;
        }

        clearParams().setParams("jl_mid",String.valueOf(courseDetail.getM_id()))
                .setParams("ocp_num",binding.classNum.getText().toString())
                .setParams("cp_id",String.valueOf(courseDetail.getCp_id()));
        Controller.myRequest(Constants.BUY_PRIVITE_COURSE,Controller.TYPE_POST,getParams(), SubmitOrderBean.class,this);
    }

    @Override
    protected void initView() {
        //获取教练信息
        courseDetail = (CourseDetailsBean.DataBean)getBundle().getSerializable("courseDetail");

        LinearLayoutManager priceManager = new LinearLayoutManager(mActivity);
        priceManager.setOrientation(RecyclerView.VERTICAL);
        binding.mRecycler.setLayoutManager(priceManager);
        priceAdapter = new PayOrderDetailPriceAdapter();
        binding.mRecycler.setAdapter(priceAdapter);
        priceAdapter.setNewData(courseDetail.getPrice_list());

        //通知子view可以去干自己的事了
        binding.selectTime.setData(String.valueOf(courseDetail.getM_id()),this,0);
        RequestOptions options = new RequestOptions().placeholder(R.mipmap.a_test2).error(R.mipmap.a_test2).centerCrop();
        Glide.with(mActivity).load(courseDetail.getCp_img()).apply(options).into(binding.courseImg);
        binding.coursePrice.setText(String.valueOf(courseDetail.getCp_price()));
        binding.classNum.setText(String.valueOf(courseDetail.getCp_num()));
        binding.courseAddress.setText(courseDetail.getOs_name());
        binding.coachName.setText(courseDetail.getMi_realname());
        binding.courseTypeTwo.setText(courseDetail.getFt_name()+" / 一对一私教课");
        binding.courseType.setText(courseDetail.getFt_name());
        binding.allPrice.setText(BigDecimalUtils.mul(courseDetail.getCp_price(),String.valueOf(courseDetail.getCp_num()),2));


        priceAdapter.setOnItemChildClickListener((adapter, view, position) -> {
            for (CourseDetailsBean.DataBean.PriceListBean priceListBean : courseDetail.getPrice_list()){
                priceListBean.setSelect(false);
            }
            courseDetail.getPrice_list().get(position).setSelect(true);
            priceAdapter.setNewData(courseDetail.getPrice_list());
            binding.classNum.setText(String.valueOf(courseDetail.getPrice_list().get(position).getMin_num()));
        });

        binding.classNum.addTextChangedListener(watcher);
    }

    TextWatcher watcher = new TextWatcher() {

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            //只要编辑框内容有变化就会调用该方法，s为编辑框变化后的内容
            if (s.toString().contains(" ")) {
                String[] str = s.toString().split(" ");
                String str1 = "";
                for (int i = 0; i < str.length; i++) {
                    str1 += str[i];
                }
                binding.classNum.setText(str1);
                binding.classNum.setSelection(start);
            }
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            //编辑框内容变化之前会调用该方法，s为编辑框内容变化之前的内容
            Log.i("beforeTextChanged", s.toString());
        }

        @Override
        public void afterTextChanged(Editable s) {
            //编辑框内容变化之后会调用该方法，s为编辑框内容变化后的内容
            if(s.length() > 0){
                try {
                    int snum = Integer.valueOf(s.toString());
                    if(snum > 0){
                        getPriceData(s.toString());
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }

            }

        }
    };

    @Override
    protected void lazyLoad() {
        Controller.myRequest(Constants.GET_MEMBER_POINT,Controller.TYPE_POST,getParams(), PointBean.class,this);
    }

    /*
    * 获取价格数据
    * */
    private void getPriceData(String num){
        setParams("cp_id",String.valueOf(courseDetail.getCp_id()))
                .setParams("num",num);
        Controller.myRequest(Constants.GET_PRICE_BY_NUM,Controller.TYPE_POST,getParams(), PriceDataBeans.class,this);
    }

    @Override
    public void onSuccess(Object data) {
        if(data instanceof PointBean){
            pointBean = (PointBean)data;
        }
        if(data instanceof SubmitOrderBean){
            SubmitOrderBean submitOrderBean = (SubmitOrderBean)data;
            PayDataBean payDataBean = new PayDataBean();
            payDataBean.setO_id(submitOrderBean.getData().getO_id());
            payDataBean.setShowPoint("0");
            payDataBean.setShowPrice(binding.allPrice.getText().toString());
            payDataBean.setLavePoint(String.valueOf(pointBean.getData().getPoint()));

            if(isValue(binding.selectTime.getStartTime())){
                PayDataBean.ReservationData reservationData = new PayDataBean.ReservationData();
                reservationData.setStart_date(binding.selectTime.getStartTime());
                reservationData.setEnd_date(binding.selectTime.getEndTime());
                reservationData.setJl_mid(String.valueOf(courseDetail.getM_id()));
                reservationData.setCp_id(String.valueOf(courseDetail.getCp_id()));

                payDataBean.setReservationData(reservationData);
            }

            V3SuccessBeans beans = new V3SuccessBeans();
            beans.setTitleText("订单");
            beans.setShowText("购买成功");
            beans.setBtn2Text("完成");
            List<V3SuccessBeans.ItemConten> list = new ArrayList<>();

            V3SuccessBeans.ItemConten itemConten = new V3SuccessBeans.ItemConten();
            itemConten.setContent(courseDetail.getFt_name()+" / 一对一私教课");
            itemConten.setItemTitle("课程类型:");
            list.add(itemConten);

            itemConten = new V3SuccessBeans.ItemConten();
            itemConten.setContent("¥"+ BaseUtil.getNoZoon(binding.coursePrice.getText().toString())+"/节");
            itemConten.setItemTitle("单价:");
            list.add(itemConten);

            itemConten = new V3SuccessBeans.ItemConten();
            itemConten.setContent(courseDetail.getHave_num()+"节");
            itemConten.setItemTitle("数量:");
            list.add(itemConten);

            itemConten = new V3SuccessBeans.ItemConten();
            itemConten.setContent("¥"+BaseUtil.getNoZoon(binding.allPrice.getText().toString()));
            itemConten.setItemTitle("总价:");
            list.add(itemConten);

            itemConten = new V3SuccessBeans.ItemConten();
            itemConten.setContent(binding.selectTime.getStartTime());
            itemConten.setItemTitle("首次上课时间:");
            list.add(itemConten);

            beans.setItemContens(list);

            Bundle bundle = new Bundle();
            bundle.putSerializable("payDataBean",payDataBean);
            bundle.putSerializable("successBeans",beans);
            startActivity(GoodsPayActivity.class,bundle);
        }

        if(data instanceof PriceDataBeans){
            priceDataBeans = (PriceDataBeans)data;

            try {
                int classNum = Integer.valueOf(binding.classNum.getText().toString());
                String showPrice = BigDecimalUtils.mul(priceDataBeans.getData().getPrice(),String.valueOf(classNum),2);
                binding.allPrice.setText(BaseUtil.getNoZoon(showPrice));
                binding.coursePrice.setText(BaseUtil.getNoZoon(priceDataBeans.getData().getPrice()));
            }catch (Exception e){
                e.printStackTrace();
            }

        }
    }
}
