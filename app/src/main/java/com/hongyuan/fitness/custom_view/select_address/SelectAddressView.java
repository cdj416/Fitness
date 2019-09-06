package com.hongyuan.fitness.custom_view.select_address;

import android.app.Dialog;
import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.BaseBean;
import com.hongyuan.fitness.base.Constants;
import com.hongyuan.fitness.base.ConstantsCode;
import com.hongyuan.fitness.base.Controller;
import com.hongyuan.fitness.base.CustomViewModel;
import com.hongyuan.fitness.base.RetrofitListener;
import com.hongyuan.fitness.base.SingleClick;
import com.hongyuan.fitness.util.BaseUtil;
import com.hongyuan.fitness.util.CustomDialog;
import com.hongyuan.fitness.util.DividerItemDecoration;

public class SelectAddressView extends LinearLayout implements RetrofitListener {

    private Dialog dialog;
    private RelativeLayout addressBox;

    private RecyclerView province,city;
    private Button userSexSubmit;
    private TextView showAddress;

    private SelectAddressLeftAdapter leftAdapter;
    private SelectAddressRightAdapter rightAdapter;

    private CustomViewModel viewModel;
    private SelectAddressLeftBeans leftBeans;
    private SelectAddressLeftBeans righBeans;

    //省份code
    private String pid;
    //城市code
    private String cid;
    //省份position
    private int leftPosition;
    //城市position
    private int rightPosition;

    public SelectAddressView(Context context) {
        super(context);
        initLayoutView();
    }

    public SelectAddressView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initLayoutView();
    }

    public SelectAddressView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initLayoutView();
    }

    public void initLayoutView(){
        View view = View.inflate(getContext(), R.layout.view_select_address, this);
        addressBox = view.findViewById(R.id.addressBox);
        showAddress = view.findViewById(R.id.showAddress);

        addressBox.setOnClickListener(v -> {
            selectLocation(getContext());
        });
    }

    /*
    * 设置地址的显示
    * */
    public void setShowAddress(CustomViewModel viewModel,String address){
        this.viewModel = viewModel;
        showAddress.setText(address);
    }

    /*
     * 选择定位地址弹框
     * */
    public void selectLocation(Context mContext){
        if(dialog == null){
            dialog = new Dialog(mContext, R.style.DialogTheme);
            View view = View.inflate(mContext, R.layout.dialog_select_address,null);
            dialog.setContentView(view);
            Window window = dialog.getWindow();
            window.setGravity(Gravity.BOTTOM);
            window.setWindowAnimations(R.style.bottom_in_out);
            window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);

            province = view.findViewById(R.id.province);
            city = view.findViewById(R.id.city);
            userSexSubmit = view.findViewById(R.id.userSexSubmit);

            LinearLayoutManager LeftManager = new LinearLayoutManager(getContext());
            LeftManager.setOrientation(RecyclerView.VERTICAL);
            province.addItemDecoration(new DividerItemDecoration(
                    getContext(), DividerItemDecoration.HORIZONTAL_LIST,1,getContext().getResources().getColor(R.color.color_EEEEEE)));
            province.setLayoutManager(LeftManager);
            leftAdapter = new SelectAddressLeftAdapter();
            province.setAdapter(leftAdapter);
            leftAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
                @SingleClick
                @Override
                public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                    for (SelectAddressLeftBeans.DataBean bean : leftBeans.getData()){
                        bean.setSelect(false);
                    }
                    leftBeans.getData().get(position).setSelect(true);
                    leftAdapter.setNewData(leftBeans.getData());
                    getCity(leftBeans.getData().get(position).getRegion_code());

                    leftPosition = position;
                    pid = leftBeans.getData().get(position).getRegion_code();
                }
            });

            LinearLayoutManager rightManager = new LinearLayoutManager(getContext());
            rightManager.setOrientation(RecyclerView.VERTICAL);
            city.addItemDecoration(new DividerItemDecoration(
                    getContext(), DividerItemDecoration.HORIZONTAL_LIST,1,getContext().getResources().getColor(R.color.color_EEEEEE)));
            city.setLayoutManager(rightManager);
            rightAdapter = new SelectAddressRightAdapter();
            city.setAdapter(rightAdapter);
            rightAdapter.setOnItemChildClickListener((adapter, view1, position) -> {
                for (SelectAddressLeftBeans.DataBean bean : righBeans.getData()){
                    bean.setSelect(false);
                }
                righBeans.getData().get(position).setSelect(true);
                rightAdapter.setNewData(righBeans.getData());

                rightPosition = position;
                cid = righBeans.getData().get(position).getRegion_code();
            });

            userSexSubmit.setOnClickListener(v -> {
                updateAddress();
            });
        }

        if(leftBeans == null){
            getProvince("0");
        }
        dialog.show();
    }

    /*
    * 请求省份数据
    * */
    private void getProvince(String father_id){
        //去请求教练某天的时间安排
        viewModel.clearParams().setParams("father_id",father_id);
        Controller.myRequest(Constants.GET_ADDRESS_LIST,Controller.TYPE_POST,viewModel.getParams(), SelectAddressLeftBeans.class,this);
    }

    /*
    * 请求市数据
    * */
    private void getCity(String father_id){
        //去请求教练某天的时间安排
        viewModel.clearParams().setParams("father_id",father_id);
        Controller.myRequest(ConstantsCode.GET_ADDRESS_LIST,Constants.GET_ADDRESS_LIST,Controller.TYPE_POST,viewModel.getParams(), SelectAddressLeftBeans.class,this);
    }

    /*
    * 修改地址
    * */
    private void updateAddress(){
        if(!BaseUtil.isValue(pid)){
            CustomDialog.showMessage(getContext(),"请选择省份！");
            return;
        }
        if(!BaseUtil.isValue(cid)){
            CustomDialog.showMessage(getContext(),"请选择城市！");
            return;
        }
        viewModel.clearParams().setParams("pid",pid).setParams("cid",cid);
        Controller.myRequest(ConstantsCode.UPDATE_MEMBER_AREA,Constants.UPDATE_MEMBER_AREA,Controller.TYPE_POST,viewModel.getParams(), BaseBean.class,this);
    }


    @Override
    public void onSuccess(Object data) {
        if(data instanceof SelectAddressLeftBeans){
            leftBeans = (SelectAddressLeftBeans)data;
            leftBeans.getData().get(0).setSelect(true);
            leftAdapter.setNewData(leftBeans.getData());
            pid = leftBeans.getData().get(0).getRegion_code();

            getCity(leftBeans.getData().get(0).getRegion_code());

        }
    }

    @Override
    public void onSuccess(int code, Object data) {
        if(code == ConstantsCode.GET_ADDRESS_LIST){
            righBeans = (SelectAddressLeftBeans)data;
            rightAdapter.setNewData(righBeans.getData());
        }
        if(code == ConstantsCode.UPDATE_MEMBER_AREA){
            dialog.dismiss();
            viewModel.showSuccess("地区修改成功！");
            showAddress.setText(leftBeans.getData().get(leftPosition).getRegion_name()+"-"+righBeans.getData().get(rightPosition).getRegion_name());
        }
    }

    @Override
    public void onError(int error_code, String description) {

    }

    @Override
    public void closeRefresh() {

    }
}
