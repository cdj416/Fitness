package com.hongyuan.fitness.ui.store.store_list;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.Constants;
import com.hongyuan.fitness.base.Controller;
import com.hongyuan.fitness.base.CustomFragment;
import com.hongyuan.fitness.base.SingleClick;
import com.hongyuan.fitness.ui.membership_card.card_detail.CardDetailsActivity;
import com.hongyuan.fitness.ui.membership_card.renewal_card.RenewalCardAdapter;
import com.hongyuan.fitness.ui.membership_card.renewal_card.RenewalCardBeans;
import com.hongyuan.fitness.ui.store.StoreDetailBean;
import com.hongyuan.fitness.util.DividerItemDecoration;
import com.makeramen.roundedimageview.RoundedImageView;

public class StoreListCardFragment extends CustomFragment {

    private RecyclerView mRecycler;
    private RenewalCardAdapter adapter;
    private TextView cardTypeText,commonCardText;
    private ImageView storeCardImgSelect,commonCardImgSelect;
    private RoundedImageView storeCardImg,commonCardImg;
    private RelativeLayout load_box;

    private RenewalCardBeans cardBeans;

    private String os_id = "";
    private String _type = "";
    //门店数据
    private StoreDetailBean.DataBean detailBean;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_storelist_card;
    }

    @Override
    public void initView(View mView) {
        os_id = getFragType("os_id");
        _type = getFragType("_type");

        mRecycler = mView.findViewById(R.id.mRecycler);
        cardTypeText = mView.findViewById(R.id.cardTypeText);
        commonCardImgSelect = mView.findViewById(R.id.commonCardImgSelect);
        storeCardImgSelect = mView.findViewById(R.id.storeCardImgSelect);
        commonCardText = mView.findViewById(R.id.commonCardText);
        commonCardImg = mView.findViewById(R.id.commonCardImg);
        storeCardImg = mView.findViewById(R.id.storeCardImg);
        load_box = mView.findViewById(R.id.load_box);

        GridLayoutManager manager = new GridLayoutManager(mActivity, 3);
        mRecycler.setLayoutManager(manager);
        mRecycler.addItemDecoration(new DividerItemDecoration(
                mActivity, DividerItemDecoration.VERTICAL_LIST,24,0x00000000));
        adapter = new RenewalCardAdapter();
        mRecycler.setAdapter(adapter);
        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @SingleClick(2000)
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                Bundle bundle = new Bundle();
                bundle.putString("card_id",String.valueOf(cardBeans.getData().getList().get(position).getCard_id()));
                startActivity(CardDetailsActivity.class,bundle);
            }
        });

        storeCardImg.setOnClickListener(new View.OnClickListener() {
            @SingleClick
            @Override
            public void onClick(View v) {
                storeCardImgSelect.setImageResource(R.mipmap.store_card_list_select_img);
                commonCardImgSelect.setImageResource(R.mipmap.store_card_list_no_select_img);
                getCardList("1",os_id);
            }
        });
        commonCardImg.setOnClickListener(new View.OnClickListener() {
            @SingleClick
            @Override
            public void onClick(View v) {
                storeCardImgSelect.setImageResource(R.mipmap.store_card_list_no_select_img);
                commonCardImgSelect.setImageResource(R.mipmap.store_card_list_select_img);
                getCardList("2",String.valueOf(detailBean.getOsl_id()));
            }
        });
    }

    @Override
    protected void lazyLoad() {
        //读取门店详情
        clearParams().setParams("os_id",os_id);
        Controller.myRequest(Constants.GET_OFFLINE_STORE_INFO,Controller.TYPE_POST,getParams(), StoreDetailBean.class,this);

        getCardList("1",os_id);
    }

    /*
    * 读取门店通卡
    * */
    private void getCardList(String my_card_type,String id){
        //读取门店详情
        clearParams().setParams("my_card_type",my_card_type).setParams("id",id);
        Controller.myRequest(Constants.GET_XU_CARD_LIST,Controller.TYPE_POST,getParams(), RenewalCardBeans.class,this);
    }

    @Override
    public void onSuccess(Object data) {
        if(data instanceof StoreDetailBean){
            detailBean = ((StoreDetailBean)data).getData();
            cardTypeText.setText(detailBean.getOs_name());

            if(detailBean.getOsl_id() == 1){
                commonCardImg.setImageResource(R.mipmap.commont_card);
                commonCardText.setText("普通店通卡");
            }else if(detailBean.getOsl_id() == 2){
                commonCardImg.setImageResource(R.mipmap.store_shenji_card);
                commonCardText.setText("升级店通卡");
            }else if(detailBean.getOsl_id() == 3){
                commonCardImg.setImageResource(R.mipmap.zhuanshi_card);
                commonCardText.setText("砖石店通卡");
            }
        }

        if(data instanceof RenewalCardBeans){
            cardBeans = (RenewalCardBeans)data;
            if(cardBeans.getData() != null && cardBeans.getData().getList() != null &&
                    cardBeans.getData().getList().size() > 0){
                adapter.setNewData(cardBeans.getData().getList());
                load_box.setVisibility(View.GONE);
                mRecycler.setVisibility(View.VISIBLE);
            }else{
                load_box.setVisibility(View.VISIBLE);
                mRecycler.setVisibility(View.GONE);
            }
        }
    }
}
