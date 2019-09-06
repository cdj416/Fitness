package com.hongyuan.fitness.ui.membership_card.my_card_detail;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.BaseBean;
import com.hongyuan.fitness.base.Constants;
import com.hongyuan.fitness.base.Controller;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.base.CustomViewModel;
import com.hongyuan.fitness.base.SingleClick;
import com.hongyuan.fitness.databinding.ActivityMyCardDetailsBinding;
import com.hongyuan.fitness.ui.membership_card.renewal_card.RenewalCardActivity;
import com.hongyuan.fitness.ui.membership_card.vtwo_my_card_list.VtwoMycardStoreList;
import com.hongyuan.fitness.ui.store.StoreDetailActivity;
import com.hongyuan.fitness.util.BaseUtil;
import com.hongyuan.fitness.util.TimeUtil;

import java.util.ArrayList;
import java.util.List;

import me.goldze.mvvmhabit.binding.command.BindingCommand;

public class MyCardDetailsViewModel extends CustomViewModel {

    private ActivityMyCardDetailsBinding binding;
    private MyCardDetailsBeans.DataBean.InfoBean detailsBean;
    private MyCardDetailsAdapter adapter;

    private List<VtwoMycardStoreList.DataBean.ListBean> storeList;

    public MyCardDetailsViewModel(CustomActivity mActivity, ActivityMyCardDetailsBinding binding) {
        super(mActivity);
        this.binding = binding;
        initView();
        lazyLoad();
    }

    @Override
    protected void initView() {
        LinearLayoutManager menuManager = new LinearLayoutManager(mActivity);
        menuManager.setOrientation(RecyclerView.VERTICAL);
        binding.useStoreList.setLayoutManager(menuManager);
        adapter = new MyCardDetailsAdapter();
        binding.useStoreList.setAdapter(adapter);
        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @SingleClick
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                Bundle bundle = new Bundle();
                bundle.putString("os_id",String.valueOf(storeList.get(position).getOs_id()));
                startActivity(StoreDetailActivity.class,bundle);
            }
        });

        binding.xuKaButton.setOnClickListener(new View.OnClickListener() {
            @SingleClick
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("my_card_type",String.valueOf(detailsBean.getMy_card_type()));
                if(detailsBean.getMy_card_type() != 1){
                    bundle.putString("id",String.valueOf(detailsBean.getOsl_id()));
                }else{
                    bundle.putString("id",String.valueOf(detailsBean.getOs_id()));
                }

                bundle.putString("storeName",detailsBean.getOs_name());
                startActivity(RenewalCardActivity.class,bundle);
            }
        });
        binding.closeImg.setOnClickListener(v -> mActivity.finish());
    }

    @Override
    protected void setData() {
        if(detailsBean.getMy_card_type() == 1){
            binding.cardBg.setImageResource(R.mipmap.store_dan_card);
            binding.cardTypeText.setText(detailsBean.getOs_name());
            binding.cardUseTime.setText(TimeUtil.formatDate(detailsBean.getLast_date(),TimeUtil.dateFormatYMDHMS,TimeUtil.dateFormatDotYMD)+"到期");
        }else{
            if(detailsBean.getOsl_id() == 1){
                binding.cardBg.setImageResource(R.mipmap.commont_card);
                binding.cardTypeText.setText("普通店通卡");
            }else if(detailsBean.getOsl_id() == 2){
                binding.cardBg.setImageResource(R.mipmap.store_shenji_card);
                binding.cardTypeText.setText("升级店通卡");
            }else if(detailsBean.getOsl_id() == 3){
                binding.cardBg.setImageResource(R.mipmap.zhuanshi_card);
                binding.cardTypeText.setText("砖石店通卡");
            }
            if(BaseUtil.isValue(detailsBean.getLast_date())){
                binding.cardUseTime.setText(TimeUtil.formatDate(detailsBean.getLast_date(),TimeUtil.dateFormatYMDHMS,TimeUtil.dateFormatDotYMD)+"到期");
            }else{
                binding.cardUseTime.setText("未开通");
            }
        }
    }

    //去门店
    public BindingCommand goStore = new BindingCommand(() -> {
        Bundle bundle = new Bundle();
        bundle.putString("os_id",String.valueOf(detailsBean.getOs_id()));
        startActivity(StoreDetailActivity.class,bundle);
    });

    @Override
    protected void lazyLoad() {
        clearParams().setParams("my_card_id",getBundle().getString("my_card_id"));
        Controller.myRequest(Constants.GET_MY_CARD_INFO,Controller.TYPE_POST,getParams(), MyCardDetailsBeans.class,this);

    }

    /*
    * 读取卡适用的门店列表
    * */
    private void getStoreList(String osl_id){
        clearParams().setParams("osl_id",osl_id).setParams("city_name","湖州市");
        Controller.myRequest(Constants.GET_SUIT_OS_LIST,Controller.TYPE_POST,getParams(), VtwoMycardStoreList.class,this);
    }

    @Override
    public void onSuccess(Object data) {
        if(data instanceof MyCardDetailsBeans){
            detailsBean = ((MyCardDetailsBeans)data).getData().getInfo();
            //去请求数据
            if(detailsBean.getMy_card_type() != 1){
                getStoreList(String.valueOf(detailsBean.getOsl_id()));
            }else{
                storeList = new ArrayList<>();
                VtwoMycardStoreList.DataBean.ListBean listBean = new VtwoMycardStoreList.DataBean.ListBean();
                listBean.setOs_id(detailsBean.getOs_id());
                listBean.setOs_name(detailsBean.getOs_name());
                storeList.add(listBean);
                adapter.setNewData(storeList);
            }

            //去设置数据
            setData();
        }

        if(data instanceof VtwoMycardStoreList){
            storeList = ((VtwoMycardStoreList)data).getData().getList();
            adapter.setNewData(storeList);
        }

    }


}
