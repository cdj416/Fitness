package com.hongyuan.fitness.ui.person.my_coupon.newcoupon;

import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.hongyuan.fitness.base.Constants;
import com.hongyuan.fitness.base.ConstantsCode;
import com.hongyuan.fitness.base.Controller;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.base.CustomViewModel;
import com.hongyuan.fitness.base.SingleClick;
import com.hongyuan.fitness.databinding.ActivityNewMycouponBinding;
import com.hongyuan.fitness.ui.main.MainActivity;
import com.hongyuan.fitness.ui.main.TokenSingleBean;
import com.hongyuan.fitness.ui.membership_card.MembershipCardActivity;
import com.hongyuan.fitness.ui.person.my_coupon.CouponAdapter;
import com.hongyuan.fitness.ui.person.my_coupon.CouponListBeans;
import com.hongyuan.fitness.ui.person.my_coupon.newcoupon.adapter.CouponDropMenuAdapter;
import com.hongyuan.fitness.ui.shop.sactivity.CollectCouponsActivity;
import com.hongyuan.fitness.ui.shop.sactivity.SstoreActivity;
import com.hongyuan.fitness.ui.store.store_page_list.StoreActivity;
import com.hongyuan.fitness.ui.webview.WebViewActivity;
import com.scwang.smartrefresh.header.MaterialHeader;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.BallPulseFooter;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

public class NewCouponViewModel extends CustomViewModel implements CouponDropMenuAdapter.OnFilterDoneListener, CouponDropMenuAdapter.OnFilterContentListener {

    private ActivityNewMycouponBinding binding;

    private CouponAdapter adapter;
    private List<CouponListBeans.DataBean.ListBean> mList;

    //优惠价类型默认商城优惠劵
    private String coupon_way = "1";
    //是否使用默认表示未使用，1表示已使用,2表示未使用
    private String is_use = "2";
    //是否过期表示未过期，1表示已过期
    private String is_exp = "0";

    public NewCouponViewModel(CustomActivity mActivity, ActivityNewMycouponBinding binding) {
        super(mActivity);
        this.binding = binding;

        initView();
        lazyLoad();
    }

    @Override
    protected void initView() {
        setOnRefresh();

        String[] titleList = new String[] { "商城优惠劵", "未使用" };
        binding.dropDownMenu.setMenuAdapter(new CouponDropMenuAdapter(mActivity, titleList, this,this));

        LinearLayoutManager manager = new LinearLayoutManager(mActivity);
        manager.setOrientation(RecyclerView.VERTICAL);
        binding.mRecycler.setLayoutManager(manager);
        adapter = new CouponAdapter();
        binding.mRecycler.setAdapter(adapter);
        adapter.setFooterView(mActivity.getFooterHeight(binding.mRecycler));
        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @SingleClick
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                goOther(mList.get(position).getCoupon_for(),String.valueOf(mList.get(position).getStore_id()));
            }
        });

        binding.goRecCoupon.setOnClickListener(v -> {
            startActivity(CollectCouponsActivity.class,null);
        });
    }

    /*
     * 跳转约定
     * */
    private void goOther(String couponFor,String store_id){
        Bundle bundle = new Bundle();
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
                    bundle.putString("store_id",store_id);
                    startActivity(SstoreActivity.class,bundle);
                    break;
                case "5":
                case "6":
                    bundle.putString("url", Constants.WEB_ADDRESS+ TokenSingleBean.getInstance().getWebAllParams(""));
                    bundle.putString("title","场馆首页");
                    mActivity.startActivity(WebViewActivity.class,bundle);
                    break;
            }
        }
    }

    /*
     * 初始化加载控件各个设置
     * */
    private void setOnRefresh(){
        //关闭滚动到底部自动加载
        binding.refresh.setEnableAutoLoadMore(false);
        //设置主题颜色
        binding.refresh.setPrimaryColors(0xFFF2F2F2);
        //初始刷新动画
        binding.refresh.setRefreshHeader(new MaterialHeader(mActivity).setShowBezierWave(true));
        //初始化加载动画
        binding.refresh.setRefreshFooter(new BallPulseFooter(mActivity).setSpinnerStyle(SpinnerStyle.Scale));
        //关闭上拉加载更多
        binding.refresh.setEnableLoadMore(true);
        //是否开启自动刷新
        binding.refresh.setEnableRefresh(false);
        //是否开启刷新功能
        binding.refresh.setEnableRefresh(true);

        isLoadMore = true;
        binding.refresh.setOnRefreshListener(onRefresh());
        binding.refresh.setOnLoadMoreListener(onLoadMore());
    }

    /*
     * 下拉刷新监听
     * */
    private OnRefreshListener onRefresh() {
        return refreshLayout -> {
            curPage = 1;
            refreshData();
        };
    }

    /*
     * 上啦加载更多监听
     * */
    private OnLoadMoreListener onLoadMore(){
        return refreshLayout -> {
            curPage++;
            loadMoreData();
        };
    }

    @Override
    public void refreshData(){
        //starCoachBean = null;
        lazyLoad();
    }

    @Override
    protected void loadMoreData() {
        lazyLoad();
    }


    @Override
    protected void lazyLoad() {
        mActivity.showLoading();
        clearParams().setParams("coupon_way",coupon_way);
        if(!"1".equals(is_exp)){
            setParams("is_use",is_use);
        }else{
            setParams("is_exp",is_exp);
        }

        Controller.myRequest(Constants.MY_COUPON_LIST,Controller.TYPE_POST,getParams(), CouponListBeans.class,this);
    }

    @Override
    public void onSuccess(Object data) {
        mActivity.closeLoading();
        if(data instanceof CouponListBeans) {
            List<CouponListBeans.DataBean.ListBean> list = ((CouponListBeans) data).getData().getList();
            if (curPage == FIRST_PAGE) {
                mList = list;
            } else {
                if (list != null && list.size() > 0) {
                    mList.addAll(list);
                } else {
                    binding.refresh.finishLoadMoreWithNoMoreData();
                }
            }

            if (mList != null && mList.size() > 0) {
                adapter.setNewData(mList);
                binding.mRecycler.setVisibility(View.VISIBLE);
                binding.loadBox.setVisibility(View.GONE);
            } else {
                binding.mRecycler.setVisibility(View.GONE);
                binding.loadBox.setVisibility(View.VISIBLE);
            }
        }
    }

    @Override
    public void closeRefresh() {
        binding.refresh.finishRefresh();
        binding.refresh.finishLoadMore();
    }


    @Override
    public void onFilterContent(int position, String changeText) {
        binding.dropDownMenu.close();
        binding.dropDownMenu.setPositionIndicatorText(position,changeText);
    }

    @Override
    public void onFilterDone(String coupon_way, String is_use, String is_exp) {
        this.coupon_way = coupon_way;
        this.is_use = is_use;
        this.is_exp = is_exp;

        if(coupon_way.equals("1")){
            binding.goRecCoupon.setVisibility(View.VISIBLE);
        }else{
            binding.goRecCoupon.setVisibility(View.GONE);
        }

        //重置为第一页
        curPage = FIRST_PAGE;
        lazyLoad();
    }
}
