package com.hongyuan.fitness.ui.mall.mine.mine_order.mine_order_list;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.Constants;
import com.hongyuan.fitness.base.Controller;
import com.hongyuan.fitness.base.CustomFragment;
import com.hongyuan.fitness.ui.mall.mine.mine_order.order_details.MineOrderDetailsActivity;
import com.hongyuan.fitness.util.DividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

public class MineOrderFragment extends CustomFragment {

    private RecyclerView typeRec,mRecycler;
    private TypeAdapter typeAdapter;
    private MineOrderAdapter adapter;
    private LinearLayout topBox;
    private RelativeLayout load_box;

    //状态栏
    private List<TypeBeans> mList = new ArrayList<>();
    //订单信息
    private MineOrderBeans mineOrderBeans;

    //支付状态
    private String o_pay_state = "0";

    @Override
    public int getLayoutId() {
        return R.layout.fragment_mine_order;
    }

    @Override
    public void initView(View mView) {
        //开启下拉刷新
        setEnableRefresh(true);
        //开启上啦加载更多
        setEnableLoadMore(true);

        typeRec = mView.findViewById(R.id.typeRec);
        mRecycler = mView.findViewById(R.id.mRecycler);
        topBox = mView.findViewById(R.id.topBox);
        load_box = mView.findViewById(R.id.load_box);

        //初始化状态栏数据
        getStatusList();

        LinearLayoutManager menuManager = new LinearLayoutManager(getContext());
        menuManager.setOrientation(RecyclerView.HORIZONTAL);
        typeRec.setLayoutManager(menuManager);
        typeAdapter = new TypeAdapter();
        typeRec.setAdapter(typeAdapter);
        typeAdapter.setNewData(mList);
        typeRec.addItemDecoration(new DividerItemDecoration(
                getContext(), DividerItemDecoration.VERTICAL_LIST,50,0x00000000));
        typeAdapter.setOnItemChildClickListener((adapter, view, position) -> {
            for (TypeBeans beans : mList){
                beans.setSelect(false);
            }
            mList.get(position).setSelect(true);
            typeAdapter.setNewData(mList);
            //赋值支付状态
            o_pay_state = mList.get(position).getTypeId();
            mineOrderBeans = null;
            getData();
        });

        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        manager.setOrientation(RecyclerView.VERTICAL);
        mRecycler.setLayoutManager(manager);
        adapter = new MineOrderAdapter();
        mRecycler.setAdapter(adapter);
        adapter.setOnItemChildClickListener((adapter, view, position) -> {
            Bundle bundle = new Bundle();
            bundle.putString("o_id",String.valueOf(mineOrderBeans.getData().getList().get(position).getO_id()));
            startActivity(MineOrderDetailsActivity.class,bundle);
        });

        if(getFragType().equals("o_goods")){
            topBox.setVisibility(View.VISIBLE);
        }else{
            topBox.setVisibility(View.GONE);
        }
    }

    /*
    * 组装状态栏数据
    * */
    private void getStatusList(){
        TypeBeans typeBeans1 = new TypeBeans("全部","0",true);
        TypeBeans typeBeans2 = new TypeBeans("已支付","1",false);
        TypeBeans typeBeans3 = new TypeBeans("未支付","2",false);
        TypeBeans typeBeans4 = new TypeBeans("已收货","5",false);
        mList.add(typeBeans1);
        mList.add(typeBeans2);
        mList.add(typeBeans3);
        mList.add(typeBeans4);
    }

    /*
    * 上啦加载更多
    * */

    @Override
    public void loadMoreData() {
        getData();
    }

    @Override
    protected void lazyLoad() {
        mineOrderBeans = null;
        getData();
    }

    /*
    * 请求数据
    * */
    private void getData(){
        clearParams().setParams("o_pay_state",o_pay_state).setParams("o_type_code",getFragType());
        Controller.myRequest(Constants.GET_ORDER_LIST,Controller.TYPE_POST,getParams(), MineOrderBeans.class,this);
    }

    @Override
    public void onSuccess(Object data) {
        if(data instanceof MineOrderBeans){
            MineOrderBeans pageData = (MineOrderBeans)data;
            if(curPage == FIRST_PAGE){
                if(pageData.getData().getList() != null && pageData.getData().getList().size() > 0){
                    mineOrderBeans = pageData;
                }
            }else{
                if(pageData.getData().getList() != null && pageData.getData().getList().size() > 0){
                    mineOrderBeans.getData().getList().addAll(pageData.getData().getList());
                }
            }

            if(mineOrderBeans != null && mineOrderBeans.getData() != null &&
                    mineOrderBeans.getData().getList() != null &&
                    mineOrderBeans.getData().getList().size() > 0){
                adapter.setNewData(mineOrderBeans.getData().getList());
                /*if("o_goods".equals(getFragType())){
                    mRecycler.setVisibility(View.VISIBLE);
                    load_box.setVisibility(View.GONE);
                }else{
                    setPromtView(SHOW_DATA);
                }*/
                mRecycler.setVisibility(View.VISIBLE);
                load_box.setVisibility(View.GONE);
            }else{
                mRecycler.setVisibility(View.GONE);
                load_box.setVisibility(View.VISIBLE);
                /*if("o_goods".equals(getFragType())){
                    mRecycler.setVisibility(View.GONE);
                    load_box.setVisibility(View.VISIBLE);
                }else{
                    setPromtView(SHOW_EMPTY);
                }*/
            }
        }
    }
}
