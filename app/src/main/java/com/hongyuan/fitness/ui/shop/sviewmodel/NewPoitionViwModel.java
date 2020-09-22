package com.hongyuan.fitness.ui.shop.sviewmodel;

import android.os.Bundle;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.BaseBean;
import com.hongyuan.fitness.base.Constants;
import com.hongyuan.fitness.base.ConstantsCode;
import com.hongyuan.fitness.base.Controller;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.base.CustomViewModel;
import com.hongyuan.fitness.databinding.ActivityNewPoitionBinding;
import com.hongyuan.fitness.ui.main.TokenSingleBean;
import com.hongyuan.fitness.ui.person.daily_punch.TaskAdapter;
import com.hongyuan.fitness.ui.person.push_share.PushShareActivity;
import com.hongyuan.fitness.ui.shop.sactivity.IntegralGoodsActivity;
import com.hongyuan.fitness.ui.shop.sactivity.IntegralGoodsDetailsActivity;
import com.hongyuan.fitness.ui.shop.sactivity.NewPoitionTaskDetailsActivity;
import com.hongyuan.fitness.ui.shop.sactivity.PoitionDetailsActivity;
import com.hongyuan.fitness.ui.shop.sadapter.NewPoitionGoodsAdapter;
import com.hongyuan.fitness.ui.shop.sadapter.NewPoitionTaskAdapter;
import com.hongyuan.fitness.ui.shop.sadapter.NowPoitionWeekAdapter;
import com.hongyuan.fitness.ui.shop.sbeans.NewPoitionBeans;
import com.hongyuan.fitness.ui.shop.sbeans.PointTaskBeans;
import com.hongyuan.fitness.ui.webview.WebViewActivity;
import com.hongyuan.fitness.util.BaseUtil;
import com.hongyuan.fitness.util.BigDecimalUtils;
import com.hongyuan.fitness.util.CustomDialog;
import com.hongyuan.fitness.util.JumpUtils;
import java.util.ArrayList;

public class NewPoitionViwModel extends CustomViewModel {

    private ActivityNewPoitionBinding binding;

    private NowPoitionWeekAdapter weekAdapter;
    private NewPoitionGoodsAdapter goodsAdapter;

    private NewPoitionBeans.DataBean poitionBeans;
    //新手任务数据
    private PointTaskBeans.DataBean pointTask;
    private NewPoitionTaskAdapter newTaskAdapter;
    //任务列表
    private TaskAdapter taskAdapter;

    public NewPoitionViwModel(CustomActivity mActivity, ActivityNewPoitionBinding binding) {
        super(mActivity);
        this.binding = binding;

        initView();
        //lazyLoad();
    }

    @Override
    protected void initView() {

        GridLayoutManager weekManager = new GridLayoutManager(mActivity, 7);
        weekManager.setOrientation(RecyclerView.VERTICAL);
        binding.weekRec.setLayoutManager(weekManager);
        weekAdapter = new NowPoitionWeekAdapter();
        binding.weekRec.setAdapter(weekAdapter);

        GridLayoutManager goodManager = new GridLayoutManager(mActivity, 4);
        goodsAdapter = new NewPoitionGoodsAdapter(new ArrayList<>());
        binding.goodsRes.setAdapter(goodsAdapter);
        goodManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                if(position == 6){
                    return 2;
                }else{
                    return 1;
                }
            }
        });
        binding.goodsRes.setLayoutManager(goodManager);
        goodsAdapter.setOnItemChildClickListener((adapter, view, position) -> {
            Bundle bundle = new Bundle();
            bundle.putString("g_id",String.valueOf(poitionBeans.getGoods().get(position).getG_id()));
            startActivity(IntegralGoodsDetailsActivity.class,bundle);
        });
        binding.goIntegralGoods.setOnClickListener(v -> {
            startActivity(IntegralGoodsActivity.class,null);
        });
        binding.goShop.setOnClickListener(v -> {
            startActivity(IntegralGoodsActivity.class,null);
        });

        LinearLayoutManager noviceTaskManager = new LinearLayoutManager(mActivity);
        noviceTaskManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        binding.noviceTask.setLayoutManager(noviceTaskManager);
        newTaskAdapter = new NewPoitionTaskAdapter();
        binding.noviceTask.setAdapter(newTaskAdapter);
        newTaskAdapter.setOnItemChildClickListener((adapter, view, position) -> {
            JumpUtils.goAtherPage(mActivity,pointTask.getNews_list().get(position).getPt_code());
        });


        LinearLayoutManager taskManager = new LinearLayoutManager(mActivity);
        taskManager.setOrientation(RecyclerView.VERTICAL);
        binding.taskRec.setLayoutManager(taskManager);
        taskAdapter = new TaskAdapter();
        binding.taskRec.setAdapter(taskAdapter);
        taskAdapter.setFooterView(mActivity.getFooter16Height(binding.taskRec));
        taskAdapter.setOnItemChildClickListener((adapter, view, position) -> {
            JumpUtils.goAtherPage(mActivity,pointTask.getDay_list().get(position).getPt_code());
        });

        binding.goDetails.setOnClickListener(v -> {
            startActivity(NewPoitionTaskDetailsActivity.class,null);
        });

        //签到
        binding.signIn.setOnClickListener(v -> checkIn());

        //每日打卡分享
        binding.goShare.setOnClickListener(v -> {
            startActivity(PushShareActivity.class,null);
        });

        //去H5活动页面
        binding.goActivity.setOnClickListener(v -> {
            if(BaseUtil.isValue(poitionBeans.getActive())){
                Bundle bundle = new Bundle();
                bundle.putString("url", poitionBeans.getActive()+TokenSingleBean.getInstance().getWebAllParams(poitionBeans.getActive()));
                bundle.putString("title","活动");
                mActivity.startActivity(WebViewActivity.class,bundle);
            }else{
                CustomDialog.showMessage(mActivity,"暂无活动！");
            }
        });

        binding.goDetail.setOnClickListener(v -> {
            startActivity(PoitionDetailsActivity.class,null);
        });
    }

    @Override
    protected void lazyLoad() {
        mActivity.showLoading();
        Controller.myRequest(Constants.MY_POINT,Controller.TYPE_POST,getParams(), NewPoitionBeans.class,this);

        //新手任务
        Controller.myRequest(Constants.POINT_TASK,Controller.TYPE_POST,getParams(), PointTaskBeans.class,this);
    }

    @Override
    public void refreshData() {
        lazyLoad();
    }

    /*
     * 会员签到
     * */
    private void checkIn(){
        mActivity.showLoading();
        clearParams();
        Controller.myRequest(ConstantsCode.ADD_QD,Constants.ADD_QD,Controller.TYPE_POST,getParams(), BaseBean.class,this);
    }

    /*
     * 更改是否可签到样式
     * */
    private void changeQD(int type){
        if(type == 1){
            binding.signIn.setText("今日已签到");
            binding.signIn.setClickable(false);
            binding.signIn.setBackgroundResource(R.drawable.shape_radius24_cccccc);
            /*if(SkinConstants.SKIN_NAME.BLACK.equals(mActivity.skin)){
                binding.signIn.setBackgroundResource(R.drawable.shape_radius16_00000000_stroke_cccccc);
            }*/
        }else{
            binding.signIn.setClickable(true);
            binding.signIn.setBackgroundResource(R.drawable.shape_radius16_gradient_ef8041_ef5b48);
        }
    }


    @Override
    public void onSuccess(Object data) {
        mActivity.closeLoading();

        if(data instanceof NewPoitionBeans){
            poitionBeans = ((NewPoitionBeans)data).getData();

            weekAdapter.setNewData(poitionBeans.getQd_week());

            if(poitionBeans.getGoods().size() >= 7){
                poitionBeans.getGoods().get(6).setItemType(NewPoitionGoodsAdapter.TYPE_TWO);
            }
            goodsAdapter.setNewData(poitionBeans.getGoods());

            binding.maxPro.setText(String.valueOf(poitionBeans.getG_point()));
            binding.myPoint.setText(String.valueOf(poitionBeans.getMy_point()));
            binding.gPoint.setProgressWithAnim(Float.valueOf(BigDecimalUtils.div(String.valueOf(poitionBeans.getMy_point()),String.valueOf(poitionBeans.getG_point()),5)),poitionBeans.getG_point());
            binding.lxSignDays.setText("已连续签到"+poitionBeans.getQd_info().getLx_day()+"天");

            //签到状态
            changeQD(poitionBeans.getQd_info().getIs_today_qd());
        }

        if(data instanceof PointTaskBeans){
            pointTask = ((PointTaskBeans)data).getData();

            newTaskAdapter.setNewData(pointTask.getNews_list());
            taskAdapter.setNewData(pointTask.getDay_list());
        }
    }

    @Override
    public void onSuccess(int code, Object data) {
        super.onSuccess(code,data);

        mActivity.closeLoading();
        if(code == ConstantsCode.ADD_QD){
            refreshData();
        }
    }
}
