package com.hongyuan.fitness.ui.main.main_home.recommend;

import android.app.Dialog;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.Constants;
import com.hongyuan.fitness.base.Controller;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.base.CustomFragment;
import com.hongyuan.fitness.base.RetrofitListener;
import com.hongyuan.fitness.ui.membership_card.v4_mycard_list.V4QRImgBean;
import com.hongyuan.fitness.ui.membership_card.v4_mycard_list.V4ScanSuccess;
import com.hongyuan.fitness.ui.scan.ScanActivity;
import com.hongyuan.fitness.ui.store.punch.PunchBeans;
import com.hongyuan.fitness.util.CustomDialog;
import com.hongyuan.fitness.util.HourMeterUtil;

public class HomeRightView extends LinearLayout implements RetrofitListener,HourMeterUtil.TimeCallBack {

    private TextView showQr,scan;
    private CustomActivity mActivity;
    private CustomFragment viewModel;

    //获取拥有的卡数据
    private HomeRightBeans.DataBean rightBeans;

    //获取进店离店状态数据
    private PunchBeans.DataBean punchBeans;

    //当前时间code
    private String is_code;
    //当前点击的对象position
    private int mPosition;

    //弹框对象
    private Dialog dialog;
    //计时对象
    private HourMeterUtil hourMeterUtil;

    @Override
    public void onSuccess(Object data) {
        mActivity.closeLoading();
        if(data instanceof HomeRightBeans){
            rightBeans = ((HomeRightBeans)data).getData();

            if(rightBeans != null && rightBeans.getList()!= null && rightBeans.getList().size() > 1){

                CustomDialog.showCards(mActivity, rightBeans.getList(), (v, position, adapter) -> {
                    mPosition = position;
                    getInStore(rightBeans.getList().get(position).getOs_id());
                });
            }else if(rightBeans != null && rightBeans.getList()!= null && rightBeans.getList().size() == 1){
                mPosition = 0;
                getInStore(rightBeans.getList().get(0).getOs_id());
            }else{
                CustomDialog.showMessage(mActivity,"暂无可使用的卡！");
            }
        }

        if(data instanceof PunchBeans){
            punchBeans = ((PunchBeans)data).getData();
            //去获取二维码图片路径
            generateQrImg();
        }

        if(data instanceof V4QRImgBean){
            V4QRImgBean qrImgBean = (V4QRImgBean)data;
            dialog = CustomDialog.showQRScan(mActivity,qrImgBean.getData().getImg(),rightBeans.getList().get(mPosition).getCard_type_name(),punchBeans.getIs_in());

            //开启计时请求
            hourMeterUtil.startCount();
        }

        if(data instanceof V4ScanSuccess){
            V4ScanSuccess.DataBean scanSuccess = ((V4ScanSuccess)data).getData();
            if(scanSuccess.getIs_ok() != 0){
                dialog.dismiss();
                hourMeterUtil.onDestory();
            }
        }
    }

    @Override
    public void onSuccess(int code, Object data) {

    }

    @Override
    public void onError(int error_code, String description) {
        if(description.contains("登录")){
            CustomDialog.showMessage(mActivity,"请先登录！");
        }
    }

    @Override
    public void closeRefresh() {

    }

    public HomeRightView(Context context, CustomFragment viewModel) {
        super(context);
        this.viewModel = viewModel;
        if(context instanceof CustomActivity){
            mActivity = (CustomActivity) context;
        }
        initLayoutView();
    }

    public HomeRightView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        if(context instanceof CustomActivity){
            mActivity = (CustomActivity) context;
        }
        initLayoutView();
    }

    public HomeRightView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        if(context instanceof CustomActivity){
            mActivity = (CustomActivity) context;
        }
        initLayoutView();
    }

    public void initLayoutView() {
        View view = View.inflate(getContext(), R.layout.view_home_right, this);
        showQr = view.findViewById(R.id.showQr);
        scan = view.findViewById(R.id.scan);

        scan.setOnClickListener(v -> {
            mActivity.startActivity(ScanActivity.class,null);
        });

        showQr.setOnClickListener(v -> {
            getComCard();
        });

        //初始化计时状态
        hourMeterUtil = new HourMeterUtil();
        hourMeterUtil.setTimeCallBack(this);
    }


    /*
     * 得到进店打卡的会籍卡列表
     * */
    private void getComCard(){
        viewModel.clearParams();
        Controller.myRequest(Constants.GET_COME_CARD, Controller.TYPE_POST,viewModel.getParams(), HomeRightBeans.class,this);
    }

    /*
     * 检查是进店还是离店
     * */
    private void getInStore(String os_id){
        mActivity.showLoading();
        viewModel.clearParams().setParams("os_id",os_id);
        Controller.myRequest(Constants.CHECK_COME_OR_OFF_STORE,Controller.TYPE_POST,viewModel.getParams(), PunchBeans.class,this);
    }

    /*
     * 生成二维码（进店离店）
     * */
    private void generateQrImg(){
        mActivity.showLoading();
        is_code = String.valueOf(System.currentTimeMillis());

        if(punchBeans != null){
            viewModel.clearParams().setParams("my_card_id",String.valueOf(rightBeans.getList().get(mPosition).getMy_card_id()))
                    .setParams("is_type",punchBeans.getIs_in().equals("0") ? "1" : "2")
                    .setParams("is_code",is_code);

            Controller.myRequest(Constants.SHOW_CODE,Controller.TYPE_POST,viewModel.getParams(), V4QRImgBean.class,this);
        }
    }

    /*
     * 去请求检查二维码扫码成功没
     * */
    private void checkSuccessScan(){
        viewModel.clearParams().setParams("os_id",rightBeans.getList().get(mPosition).getOs_id()).setParams("is_code",is_code)
                .setParams("is_type",punchBeans.getIs_in().equals("0") ? "1" : "2");
        Controller.myRequest(Constants.check_do_card,Controller.TYPE_POST,viewModel.getParams(), V4ScanSuccess.class,this);
    }

    @Override
    public void onTime(int passedTime) {

        if(dialog.isShowing()){
            checkSuccessScan();
        }else{
            hourMeterUtil.onDestory();
        }
    }
}
