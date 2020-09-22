package com.hongyuan.fitness.ui.shop.sfragment;

import android.view.View;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.BaseBean;
import com.hongyuan.fitness.base.Constants;
import com.hongyuan.fitness.base.ConstantsCode;
import com.hongyuan.fitness.base.Controller;
import com.hongyuan.fitness.base.CustomFragment;
import com.hongyuan.fitness.custom_view.CustomRecyclerView;
import com.hongyuan.fitness.ui.shop.sadapter.SDMimgAdapter;
import com.hongyuan.fitness.ui.shop.sadapter.SstoreGouponAdapter;
import com.hongyuan.fitness.ui.shop.sbeans.StoreCouponBeans;
import com.hongyuan.fitness.util.BaseUtil;
import com.hongyuan.fitness.util.DensityUtil;
import java.util.Arrays;
import java.util.List;

public class SstoreMainFragment extends CustomFragment {

    private RecyclerView mRec;
    private CustomRecyclerView couponRec;
    private SstoreGouponAdapter topAdapter;
    private SDMimgAdapter gAdapter;

    private List<StoreCouponBeans.DataBean> mList;

    //记录index
    private int mPosition;
    @Override
    public int getLayoutId() {
        return R.layout.fragment_shop_store_main;
    }

    @Override
    public void initView(View mView) {
        couponRec = mView.findViewById(R.id.couponRec);
        mRec = mView.findViewById(R.id.mRec);

        LinearLayoutManager topManager = new LinearLayoutManager(getContext());
        topManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        couponRec.setLayoutManager(topManager);
        topAdapter = new SstoreGouponAdapter();
        couponRec.setAdapter(topAdapter);
        topAdapter.setOnItemChildClickListener((adapter, view, position) -> {
            mPosition = position;
            getReceive(String.valueOf(mList.get(position).getCoupon_id()));
        });

        LinearLayoutManager imgManager = new LinearLayoutManager(mActivity);
        imgManager.setOrientation(RecyclerView.VERTICAL);
        mRec.setLayoutManager(imgManager);
        gAdapter = new SDMimgAdapter(DensityUtil.getScreensWith(mActivity));
        mRec.setAdapter(gAdapter);
    }

    /*
    * 设置店铺首页图片
    * */
    public void setImgs(String imgs){
        if(BaseUtil.isValue(imgs)){
            if(imgs.contains(",")){
                String[] imgAry = imgs.split(",");
                List<String> imgList = Arrays.asList(imgAry);
                gAdapter.setNewData(imgList);
            }
            if(imgs.contains(";")){
                String[] imgAry = imgs.split(";");
                List<String> imgList = Arrays.asList(imgAry);
                gAdapter.setNewData(imgList);
            }
        }
    }

    @Override
    protected void lazyLoad() {
        clearParams().setParams("store_id",mActivity.getBundle().getString("store_id"));
        Controller.myRequest(Constants.GET_STORE_COUPON_LIST,Controller.TYPE_POST,getParams(), StoreCouponBeans.class,this);
    }

    /*
     * 领取优惠券
     * */
    private void getReceive(String couponId){
        mActivity.showLoading();
        clearParams().setParams("coupon_id",couponId);
        Controller.myRequest(ConstantsCode.GET_COUPON,Constants.GET_COUPON,Controller.TYPE_POST,getParams(), BaseBean.class,this);
    }


    @Override
    public void onSuccess(Object data) {
        if(data instanceof StoreCouponBeans){
            mList = ((StoreCouponBeans)data).getData();

            if(mList == null || mList.size() <= 0){
                couponRec.setVisibility(View.GONE);
            }else{
                topAdapter.setNewData(mList);
            }

        }
    }

    @Override
    public void onSuccess(int code, Object data) {
        super.onSuccess(code,data);

        mActivity.closeLoading();
        if(code == ConstantsCode.GET_COUPON){
            mList.remove(mPosition);
            topAdapter.setNewData(mList);
            showSuccess("领取成功！");
        }
    }
}
