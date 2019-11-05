package com.hongyuan.fitness.ui.membership_card;
import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.Constants;
import com.hongyuan.fitness.base.Controller;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.base.CustomViewModel;
import com.hongyuan.fitness.databinding.ActivityMembershipCardBinding;
import com.hongyuan.fitness.ui.membership_card.v4_mycard_detail.V4MyCardDetail;
import com.hongyuan.fitness.ui.membership_card.v4_mycard_detail.V4MyCardDetailViewModel;
import com.hongyuan.fitness.ui.membership_card.v4_mycard_list.V4MyCardListBeans;
import com.hongyuan.fitness.ui.membership_card.vtwo_my_card_list.VtwoMemberShipAdapter;
import com.hongyuan.fitness.ui.store.store_page_list.StoreActivity;

import java.util.List;

public class MembershipCardViewModel extends CustomViewModel{

    private ActivityMembershipCardBinding binding;
    private VtwoMemberShipAdapter adapter;
    private List<V4MyCardListBeans.DataBean.ListBean> mList;


    public MembershipCardViewModel(CustomActivity mActivity, ActivityMembershipCardBinding binding) {
        super(mActivity);
        this.binding = binding;
        initView();
        lazyLoad();
    }

    @Override
    protected void initView() {
        setEnableRefresh(true);

        LinearLayoutManager menuManager = new LinearLayoutManager(mActivity);
        menuManager.setOrientation(RecyclerView.VERTICAL);
        binding.mRecycler.setLayoutManager(menuManager);
        adapter = new VtwoMemberShipAdapter();
        binding.mRecycler.setAdapter(adapter);

        adapter.setOnItemChildClickListener((adapter, view, position) -> {
            if(view.getId() == R.id.memberQr){

               /* Bundle bundle = new Bundle();
                bundle.putString("my_card_type",String.valueOf(itemBean.getUseList().get(position).getMy_card_type()));
                if(itemBean.getUseList().get(position).getMy_card_type() != 1){
                    bundle.putString("id",String.valueOf(itemBean.getUseList().get(position).getOsl_id()));
                }else{
                    bundle.putString("id",String.valueOf(itemBean.getUseList().get(position).getOs_id()));
                }
                bundle.putString("storeName",itemBean.getUseList().get(position).getOs_name());
                startActivity(RenewalCardActivity.class,bundle);*/

            }else{
                /*Bundle bundle = new Bundle();
                bundle.putString("my_card_id",String.valueOf(itemBean.getUseList().get(position).getMy_card_id()));
                startActivity(MyCardDetailsActivity.class,bundle);*/

                Bundle bundle = new Bundle();
                bundle.putString("my_card_id",String.valueOf(mList.get(position).getMy_card_id()));
                bundle.putInt("aisle", V4MyCardDetailViewModel.MY_AISLE);
                startActivity(V4MyCardDetail.class,bundle);
            }
        });
        binding.goStore.setOnClickListener(v -> {
            startActivity(StoreActivity.class,null);
        });
    }

    @Override
    public void refreshData(){
        lazyLoad();
    }

    @Override
    protected void lazyLoad() {
        //clearParams().setParams("oc_pay_state","1");
        mActivity.showLoading();
        clearParams();
        Controller.myRequest(Constants.GET_MY_CARD_LIST,Controller.TYPE_POST,getParams(), V4MyCardListBeans.class,this);
    }


    @Override
    public void onSuccess(Object data) {
        mActivity.closeLoading();
        if(data instanceof V4MyCardListBeans){
            mList = ((V4MyCardListBeans)data).getData().getList();
            if(mList != null && mList.size() > 0){
                adapter.setNewData(mList);
                binding.noCardBox.setVisibility(View.GONE);
            }else{
                binding.noCardBox.setVisibility(View.VISIBLE);
            }
        }

    }

}
