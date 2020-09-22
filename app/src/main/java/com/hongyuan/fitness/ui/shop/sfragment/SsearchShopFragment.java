package com.hongyuan.fitness.ui.shop.sfragment;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.Constants;
import com.hongyuan.fitness.base.ConstantsCode;
import com.hongyuan.fitness.base.Controller;
import com.hongyuan.fitness.base.CustomFragment;
import com.hongyuan.fitness.ui.shop.sactivity.SgoodsDetailActivity;
import com.hongyuan.fitness.ui.shop.sactivity.SstoreActivity;
import com.hongyuan.fitness.ui.shop.sadapter.SsearchShopAdapter;
import com.hongyuan.fitness.ui.shop.sbeans.GoodsFilterBeans;
import com.hongyuan.fitness.ui.shop.sbeans.ShopsBeans;
import com.hongyuan.fitness.util.BaseUtil;
import com.hongyuan.fitness.util.SkinConstants;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

public class SsearchShopFragment extends CustomFragment {

    private RecyclerView mRec;
    private SsearchShopAdapter adapter;
    private List<ShopsBeans.DataBean.ListBean> mList;

    private LinearLayout salesFilter;
    private TextView salesText;

    //销量筛选条件
    private String order;
    //条件赛选集
    private GoodsFilterBeans filterBeans;
    /*
     * 控制跳转到指定页面
     * */
    @Subscribe(id = ConstantsCode.GET_GOODS_LIST_SIX)
    public void search(GoodsFilterBeans message) {
        filterBeans.setFirst_price(message.getFirst_price());
        filterBeans.setEnd_price(message.getEnd_price());
        filterBeans.setDeliver_code(message.getDeliver_code());

        //lazyLoad();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    protected int getHeadLayoutId() {
        return R.layout.fragment_headview_searchshops;
    }

    @Override
    protected void initHeadView(View headLayt) {
        filterBeans = new GoodsFilterBeans();
        filterBeans.setSearch_name(mActivity.getBundle().getString("showText"));

        salesFilter = headLayt.findViewById(R.id.salesFilter);
        salesText = headLayt.findViewById(R.id.salesText);

        salesFilter.setOnClickListener(v -> {
            if(!Boolean.valueOf(salesText.getTag().toString())){
                salesText.setTextColor(getResources().getColor(R.color.color_EF5B48));
                salesText.setTag("true");
                order = "sale";
            }else{
                if(SkinConstants.SKIN_NAME.DEFAULT.equals(mActivity.skin))
                    salesText.setTextColor(getResources().getColor(R.color.color_FF999999));
                if(SkinConstants.SKIN_NAME.BLACK.equals(mActivity.skin))
                    salesText.setTextColor(getResources().getColor(R.color.color_FF999999));
                salesText.setTag("false");
                order = null;
            }
            lazyLoad();

        });
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_base_recylerview;
    }

    @Override
    public void initView(View mView) {
        setEnableLoadMore(true);
        setEnableRefresh(true);

        mRec = mView.findViewById(R.id.mRec);

        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        mRec.setLayoutManager(manager);
        adapter = new SsearchShopAdapter();
        mRec.setAdapter(adapter);

        adapter.setOnItemChildClickListener((adapter, view, position) -> {
            if(view.getId() == R.id.goStore){
                Bundle bundle = new Bundle();
                bundle.putString("store_id",String.valueOf(mList.get(position).getStore_id()));
                startActivity(SstoreActivity.class,bundle);
            }
            if(view.getId() == R.id.img1){
                Bundle bundle = new Bundle();
                bundle.putString("g_id",String.valueOf(mList.get(position).getGoods_list().get(0).getG_id()));
                startActivity(SgoodsDetailActivity.class,bundle);
            }
            if(view.getId() == R.id.img2){
                Bundle bundle = new Bundle();
                bundle.putString("g_id",String.valueOf(mList.get(position).getGoods_list().get(1).getG_id()));
                startActivity(SgoodsDetailActivity.class,bundle);
            }
            if(view.getId() == R.id.img3){
                Bundle bundle = new Bundle();
                bundle.putString("g_id",String.valueOf(mList.get(position).getGoods_list().get(2).getG_id()));
                startActivity(SgoodsDetailActivity.class,bundle);
            }
        });

    }

    /*
     * 加载更多
     * */
    @Override
    public void loadMoreData() {
        lazyLoad();
    }

    @Override
    protected void lazyLoad() {
        mActivity.showLoading();
        clearParams();
        if(BaseUtil.isValue(order)){
            setParams("order",order);
        }
        if(BaseUtil.isValue(filterBeans.getSearch_name())){
            setParams("search_name",filterBeans.getSearch_name());
        }
        if(BaseUtil.isValue(filterBeans.getDeliver_code())){
            setParams("deliver_code",filterBeans.getDeliver_code());
        }
        Controller.myRequest(Constants.GET_STORE_LIST,Controller.TYPE_POST,getParams(), ShopsBeans.class,this);
    }


    @Override
    public void onSuccess(Object data) {
        super.onSuccess(data);

        mActivity.closeLoading();
        if(data instanceof ShopsBeans){
            List<ShopsBeans.DataBean.ListBean> list = ((ShopsBeans)data).getData().getList();
            if(curPage == FIRST_PAGE){
                mList = list;
            }else{
                if(list != null && list.size() > 0){
                    mList.addAll(list);
                }else{
                    refresh.finishLoadMoreWithNoMoreData();
                }
            }

            if(mList != null && mList.size() > 0){
                adapter.setNewData(mList);
                setPromtView(mActivity.SHOW_DATA);
            }else{
                setPromtView(mActivity.SHOW_EMPTY);
            }
        }
    }
}
