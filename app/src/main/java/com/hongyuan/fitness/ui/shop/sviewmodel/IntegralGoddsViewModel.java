package com.hongyuan.fitness.ui.shop.sviewmodel;

import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.hongyuan.fitness.base.BaseBean;
import com.hongyuan.fitness.base.Constants;
import com.hongyuan.fitness.base.Controller;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.base.CustomViewModel;
import com.hongyuan.fitness.databinding.ActivityIntegralGoodsBinding;
import com.hongyuan.fitness.ui.mall.good_order_details.PointBean;
import com.hongyuan.fitness.ui.shop.sactivity.IntegralGoodsDetailsActivity;
import com.hongyuan.fitness.ui.shop.sactivity.SgoodsDetailActivity;
import com.hongyuan.fitness.ui.shop.sadapter.SMGoodsAdapter;
import com.hongyuan.fitness.ui.shop.sbeans.GoodsBeans;
import java.util.List;

public class IntegralGoddsViewModel extends CustomViewModel {

    private ActivityIntegralGoodsBinding binding;
    private SMGoodsAdapter gAdapter;

    //商品数据
    private List<GoodsBeans.DataBean.ListBean> mList;

    public IntegralGoddsViewModel(CustomActivity mActivity, ActivityIntegralGoodsBinding binding) {
        super(mActivity);
        this.binding = binding;
        initView();
        lazyLoad();
        getPoint();
    }

    @Override
    protected void initView() {
        GridLayoutManager rihtManager = new GridLayoutManager(mActivity, 2);
        rihtManager.setOrientation(RecyclerView.VERTICAL);
        binding.mRec.setLayoutManager(rihtManager);
        gAdapter = new SMGoodsAdapter<GoodsBeans.DataBean.ListBean>() {
            @Override
            public String getImg(GoodsBeans.DataBean.ListBean item) {
                return item.getG_img();
            }

            @Override
            public String getName(GoodsBeans.DataBean.ListBean item) {
                return item.getG_name();
            }

            @Override
            public String getPrice(GoodsBeans.DataBean.ListBean item) {
                return item.getG_price();
            }
        };
        binding.mRec.setAdapter(gAdapter);

        gAdapter.setOnItemChildClickListener((adapter, view, position) ->{
            Bundle bundle = new Bundle();
            bundle.putString("g_id",String.valueOf(mList.get(position).getG_id()));
            startActivity(IntegralGoodsDetailsActivity.class,bundle);
        });
    }

    /*
     * 查询积分
     * */
    private void getPoint(){
        Controller.myRequest(Constants.GET_MEMBER_POINT,Controller.TYPE_POST,getParams(), PointBean.class,this);
    }

    @Override
    protected void loadMoreData() {
        lazyLoad();
    }

    @Override
    protected void lazyLoad() {
        mActivity.closeLoading();
        clearParams().setParams("is_point","1");
        Controller.myRequest(Constants.GET_GOODS_LIST_SIX,Controller.TYPE_POST,getParams(), GoodsBeans.class,this);
    }

    @Override
    public void onSuccess(Object data) {
        mActivity.closeLoading();
        if(data instanceof GoodsBeans){
            List<GoodsBeans.DataBean.ListBean> list = ((GoodsBeans)data).getData().getList();
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
                gAdapter.setNewData(mList);
                binding.childEmpty.setVisibility(View.GONE);
                binding.mRec.setVisibility(View.VISIBLE);
            }else{
                binding.childEmpty.setVisibility(View.VISIBLE);
                binding.mRec.setVisibility(View.GONE);
            }
        }

        if(data instanceof PointBean){
            PointBean pointBean = (PointBean)data;

            binding.pointNum.setText(String.valueOf(pointBean.getData().getPoint()));
        }
    }
}
