package com.hongyuan.fitness.ui.membership_card.renewal_card;

import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.GridLayoutManager;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.Constants;
import com.hongyuan.fitness.base.Controller;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.base.CustomViewModel;
import com.hongyuan.fitness.base.SingleClick;
import com.hongyuan.fitness.databinding.ActivityRenewalCardBinding;
import com.hongyuan.fitness.ui.membership_card.card_detail.CardDetailsActivity;
import com.hongyuan.fitness.util.DividerItemDecoration;

public class RenewalCardViewModel extends CustomViewModel {

    private ActivityRenewalCardBinding binding;
    private RenewalCardAdapter adapter;
    private RenewalCardBeans cardBeans;

    //卡的类型
    private String my_card_type;
    //如果是门店卡，id为门店id 如果是通用卡，id为osl_id
    private String id;
    //门店名
    private String storeName;


    public RenewalCardViewModel(CustomActivity mActivity, ActivityRenewalCardBinding binding) {
        super(mActivity);
        this.binding = binding;
        initView();
        lazyLoad();
    }

    @Override
    protected void initView() {
        my_card_type = getBundle().getString("my_card_type");
        id = getBundle().getString("id");
        storeName = getBundle().getString("storeName");

        if("1".equals(my_card_type)){
            binding.cardBg.setImageResource(R.mipmap.store_dan_card);
            binding.cardTypeText.setText(storeName);
        }else{
            if("1".equals(id)){
                binding.cardBg.setImageResource(R.mipmap.commont_card);
                binding.cardTypeText.setText("普通店通卡");
            }else if("2".equals(id)){
                binding.cardBg.setImageResource(R.mipmap.store_shenji_card);
                binding.cardTypeText.setText("升级店通卡");
            }else if("3".equals(id)){
                binding.cardBg.setImageResource(R.mipmap.zhuanshi_card);
                binding.cardTypeText.setText("砖石店通卡");
            }
        }

        GridLayoutManager manager = new GridLayoutManager(mActivity, 3);
        binding.mRecycler.setLayoutManager(manager);
        binding.mRecycler.addItemDecoration(new DividerItemDecoration(
                mActivity, DividerItemDecoration.VERTICAL_LIST,24,0x00000000));
        adapter = new RenewalCardAdapter();
        binding.mRecycler.setAdapter(adapter);
        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @SingleClick(2000)
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                Bundle bundle = new Bundle();
                bundle.putString("card_id",String.valueOf(cardBeans.getData().getList().get(position).getCard_id()));
                startActivity(CardDetailsActivity.class,bundle);
            }
        });
    }

    @Override
    protected void lazyLoad() {
        //读取门店详情
        clearParams().setParams("my_card_type",my_card_type).setParams("id",id);
        Controller.myRequest(Constants.GET_XU_CARD_LIST,Controller.TYPE_POST,getParams(), RenewalCardBeans.class,this);
    }

    @Override
    public void onSuccess(Object data) {
        if(data instanceof RenewalCardBeans){
            cardBeans = (RenewalCardBeans)data;
            if(cardBeans.getData() != null && cardBeans.getData().getList() != null &&
                    cardBeans.getData().getList().size() > 0){
                adapter.setNewData(cardBeans.getData().getList());
            }
        }
    }

}
