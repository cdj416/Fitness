package com.hongyuan.fitness.ui.shop.sviewmodel;

import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.BaseBean;
import com.hongyuan.fitness.base.Constants;
import com.hongyuan.fitness.base.ConstantsCode;
import com.hongyuan.fitness.base.Controller;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.base.CustomViewModel;
import com.hongyuan.fitness.custom_view.FlowLayoutManager;
import com.hongyuan.fitness.databinding.ActivityMainSearchBinding;
import com.hongyuan.fitness.ui.shop.sactivity.ShopSearchActivity;
import com.hongyuan.fitness.ui.shop.sadapter.MhsRecAdapter;
import com.hongyuan.fitness.ui.shop.sbeans.AtherSearchBeans;
import com.hongyuan.fitness.ui.shop.sbeans.MainSearchBeans;
import com.hongyuan.fitness.util.BaseUtil;
import com.hongyuan.fitness.util.CustomDialog;

import org.greenrobot.eventbus.EventBus;

public class MainSearchViewModel extends CustomViewModel {

    private ActivityMainSearchBinding binding;

    private MhsRecAdapter oAdapter;
    private MhsRecAdapter tAdapter;

    //我搜索的历史数据
    private MainSearchBeans.DataBean searchBeans;
    //大家搜索的历史数据
    private AtherSearchBeans.DataBean otherBeans;

    public MainSearchViewModel(CustomActivity mActivity, ActivityMainSearchBinding binding) {
        super(mActivity);
        this.binding = binding;
        initView();
        lazyLoad();
    }

    @Override
    protected void initView() {

        binding.etSearch.setText((getBundle() != null && BaseUtil.isValue(getBundle().getString("showText"))) ? getBundle().getString("showText") : "");

        FlowLayoutManager oManager = new FlowLayoutManager();
        binding.hsRec.setLayoutManager(oManager);
        oAdapter = new MhsRecAdapter<MainSearchBeans.DataBean.ListBean>() {
            @Override
            public String getName(MainSearchBeans.DataBean.ListBean item) {
                return item.getHabit_name();
            }
        };
        binding.hsRec.setAdapter(oAdapter);
        oAdapter.setOnItemChildClickListener((adapter, view, position) -> {
            Bundle bundle = new Bundle();
            bundle.putString("showText",searchBeans.getList().get(position).getHabit_name());
            startActivity(ShopSearchActivity.class,bundle);
            //通知关闭多余的页面
            EventBus.getDefault().post(ConstantsCode.EB_CLOSE_SERARCH,"");
            mActivity.finish();
        });

        FlowLayoutManager tManager = new FlowLayoutManager();
        binding.dsRec.setLayoutManager(tManager);
        tAdapter = new MhsRecAdapter<AtherSearchBeans.DataBean.ListBean>() {
            @Override
            public String getName(AtherSearchBeans.DataBean.ListBean item) {
                return item.getWord_name();
            }
        };
        binding.dsRec.setAdapter(tAdapter);
        tAdapter.setOnItemChildClickListener((adapter, view, position) -> {
            Bundle bundle = new Bundle();
            bundle.putString("showText",otherBeans.getList().get(position).getWord_name());
            startActivity(ShopSearchActivity.class,bundle);
            //通知关闭多余的页面
            EventBus.getDefault().post(ConstantsCode.EB_CLOSE_SERARCH,"");
            mActivity.finish();
        });

        binding.etSearch.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_SEARCH){
                Bundle bundle = new Bundle();
                bundle.putString("showText",binding.etSearch.getText().toString());
                startActivity(ShopSearchActivity.class,bundle);
                //通知关闭多余的页面
                EventBus.getDefault().post(ConstantsCode.EB_CLOSE_SERARCH,"");
                mActivity.finish();
                return true;
            }
            return false;
        });

        binding.clearImg.setOnClickListener(v -> {
            CustomDialog.promptDialog(mActivity, "是否清空搜索记录？", "确定清空", "在想想", false, v1 -> {
                if(v1.getId() == R.id.isOk){
                    clearHistory();
                }
            });
        });
        binding.changeAgin.setOnClickListener(v -> {
            getOther();
        });
        binding.cancelText.setOnClickListener(v -> mActivity.finish());
    }

    @Override
    protected void lazyLoad() {
        mActivity.showLoading();
        clearParams();
        //搜索历史记录
        Controller.myRequest(Constants.GET_VISIT_HABIT,Controller.TYPE_POST,getParams(), MainSearchBeans.class,this);

        getOther();
    }

    /*
    * 读取大家搜索的记录
    * */
    private void getOther(){
        mActivity.showLoading();
        clearParams();
        //大家都在搜索的记录
        Controller.myRequest(Constants.GET_HOT_WORD,Controller.TYPE_POST,getParams(), AtherSearchBeans.class,this);
    }

    /*
    * 情况历史记录
    * */
    private void clearHistory(){
        mActivity.showLoading();
        clearParams();
        Controller.myRequest(ConstantsCode.DEL_VISIT_HABIT,Constants.DEL_VISIT_HABIT,Controller.TYPE_POST,getParams(), BaseBean.class,this);
    }

    @Override
    public void onSuccess(Object data) {
        mActivity.closeLoading();
        if(data instanceof MainSearchBeans){
            searchBeans =  ((MainSearchBeans)data).getData();
            if(searchBeans.getList() == null || searchBeans.getList().size() <= 0){
                binding.historyBox.setVisibility(View.GONE);
            }else{
                binding.historyBox.setVisibility(View.VISIBLE);
                oAdapter.setNewData(searchBeans.getList());
            }

        }

        if(data instanceof AtherSearchBeans){
            otherBeans = ((AtherSearchBeans)data).getData();
            if(otherBeans.getList() == null || otherBeans.getList().size() <= 0){
                binding.otherHistoryBox.setVisibility(View.GONE);
            }else{
                binding.otherHistoryBox.setVisibility(View.VISIBLE);
                tAdapter.setNewData(otherBeans.getList());
            }
        }
    }

    @Override
    public void onSuccess(int code, Object data) {
        mActivity.closeLoading();
        if(code == ConstantsCode.DEL_VISIT_HABIT){
            binding.historyBox.setVisibility(View.GONE);
        }
    }
}
