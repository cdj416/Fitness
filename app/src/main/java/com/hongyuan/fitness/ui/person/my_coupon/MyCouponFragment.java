package com.hongyuan.fitness.ui.person.my_coupon;
import android.os.Bundle;
import android.view.View;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.Constants;
import com.hongyuan.fitness.base.ConstantsCode;
import com.hongyuan.fitness.base.Controller;
import com.hongyuan.fitness.base.CustomFragment;
import com.hongyuan.fitness.base.SingleClick;
import com.hongyuan.fitness.ui.main.MainActivity;
import com.hongyuan.fitness.ui.mall.GoodActivity;
import com.hongyuan.fitness.ui.membership_card.MembershipCardActivity;
import com.hongyuan.fitness.ui.store.more_store.MoreStoreActivity;
import com.hongyuan.fitness.ui.store.store_page_list.StoreActivity;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

public class MyCouponFragment extends CustomFragment {


    private RecyclerView mRecycler;

    //优惠券适配器
    private CouponAdapter adapter;
    private List<CouponListBeans.DataBean.ListBean> mList;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_coupon;
    }

    @Override
    public void initView(View mView) {
        setEnableLoadMore(true);
        setEnableRefresh(true);

        mRecycler = mView.findViewById(R.id.mRecycler);

        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        manager.setOrientation(RecyclerView.VERTICAL);
        mRecycler.setLayoutManager(manager);
        adapter = new CouponAdapter();
        mRecycler.setAdapter(adapter);

        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @SingleClick
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                goOther(mList.get(position).getCoupon_for());
            }
        });

    }

    /*
    * 跳转约定
    * */
    private void goOther(String couponFor){
        if(couponFor.contains(",")){
            mActivity.startActivity(StoreActivity.class,null);
        }else{
            switch (couponFor){
                case "2":
                    mActivity.startActivity(MembershipCardActivity.class);
                    break;
                case "3":
                    //通过EventBus去通知MainActivity显示第三页
                    EventBus.getDefault().post(ConstantsCode.EB_START_COURSE,"2");
                    mActivity.startActivity(MainActivity.class);
                    break;
                case "4":
                    Bundle bundle = new Bundle();
                    bundle.putInt("position",0);
                    startActivity(GoodActivity.class,bundle);
                    break;
                    default:
                        mActivity.startActivity(MembershipCardActivity.class);
                        break;
            }
        }
    }

    @Override
    protected void lazyLoad() {
        getData();
    }

    @Override
    public void loadMoreData() {
        getData();
    }

    /*
    * 请求我的优惠券
    * */
    private void getData(){
        mActivity.showLoading();
        clearParams();
        if("0".equals(getFragType())){
            setParams("is_use","2");
        }
        if("1".equals(getFragType())){
            setParams("is_use","1");
        }
        if("2".equals(getFragType())){
            setParams("is_exp","1");
        }

        Controller.myRequest(Constants.MY_COUPON_LIST,Controller.TYPE_POST,getParams(), CouponListBeans.class,this);
    }

    @Override
    public void onSuccess(Object data) {
        mActivity.closeLoading();
        if(data instanceof CouponListBeans){
            List<CouponListBeans.DataBean.ListBean> list = ((CouponListBeans)data).getData().getList();
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
                setPromtView(SHOW_DATA);
            }else{
                setPromtView(SHOW_EMPTY);
            }
        }
    }
}
