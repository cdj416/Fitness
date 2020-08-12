package com.hongyuan.fitness.ui.shop.sviewmodel;

import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.Constants;
import com.hongyuan.fitness.base.Controller;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.base.CustomViewModel;
import com.hongyuan.fitness.databinding.AcitivityShopAddressBinding;
import com.hongyuan.fitness.ui.shop.sactivity.ChangeAddressActivity;
import com.hongyuan.fitness.ui.shop.sadapter.ShopAddressAdapter;
import com.hongyuan.fitness.ui.shop.sbeans.ShopAddressBeans;
import java.util.List;

public class ShopAddressViewModel extends CustomViewModel {

    private AcitivityShopAddressBinding binding;

    private ShopAddressAdapter adapter;
    private List<ShopAddressBeans.DataBean> mList;

    public ShopAddressViewModel(CustomActivity mActivity, AcitivityShopAddressBinding binding) {
        super(mActivity);
        this.binding = binding;
        initView();
    }

    @Override
    protected void initView() {


        mActivity.getMainTitle().getRightView().setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            bundle.putInt("type",ChangeAddressViewModel.ADD);
            startActivity(ChangeAddressActivity.class,bundle);
        });

        LinearLayoutManager manager = new LinearLayoutManager(mActivity);
        manager.setOrientation(RecyclerView.VERTICAL);
        binding.mRec.setLayoutManager(manager);
        adapter = new ShopAddressAdapter();
        binding.mRec.setAdapter(adapter);
        adapter.setFooterView(mActivity.getFooterHeight(binding.mRec));

        adapter.setOnItemChildClickListener((adapter, view, position) -> {
            if(view.getId() == R.id.box){
                Bundle bundle = new Bundle();
                bundle.putSerializable("select",mList.get(position));
                setResult(bundle);
            }else{
                Bundle bundle = new Bundle();
                bundle.putInt("type",ChangeAddressViewModel.MODIFY);
                bundle.putString("address_id",String.valueOf(mList.get(position).getAddress_id()));
                startActivity(ChangeAddressActivity.class,bundle);
            }

        });
    }


    @Override
    public void refreshData() {
        clearParams();
        Controller.myRequest(Constants.GET_RECEIVING_ADDRESS_LIST,Controller.TYPE_POST,getParams(), ShopAddressBeans.class,this);
    }

    @Override
    public void onSuccess(Object data) {
        if(data instanceof ShopAddressBeans){
            List<ShopAddressBeans.DataBean> list = ((ShopAddressBeans)data).getData();
            if(curPage == FIRST_PAGE){
                mList = list;
            }else{
                if(list != null && list.size() > 0){
                    mList.addAll(list);
                }else{
                    mActivity.refresh.finishLoadMoreWithNoMoreData();
                }
            }

            if(mList != null && mList.size() > 0){
                adapter.setNewData(mList);
                mActivity.setPromtView(mActivity.SHOW_DATA);
            }else{
                mActivity.setPromtView(mActivity.SHOW_EMPTY);
            }
        }
    }
}
