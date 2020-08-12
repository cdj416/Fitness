package com.hongyuan.fitness.ui.person.my_coupon.newcoupon.adapter;

import android.content.Context;
import android.view.View;
import android.widget.FrameLayout;

import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.custom_view.filter_view.adapter.MenuAdapter;
import com.hongyuan.fitness.custom_view.filter_view.adapter.SingleReclerAdapter;
import com.hongyuan.fitness.custom_view.filter_view.typeview.SingleReclerView;
import com.hongyuan.fitness.custom_view.filter_view.util.UIUtil;
import com.hongyuan.fitness.ui.person.my_coupon.newcoupon.cbeans.CouponStatusBeans;
import com.hongyuan.fitness.ui.person.my_coupon.newcoupon.cbeans.CouponTypesBeans;

import java.util.ArrayList;
import java.util.List;

public class CouponDropMenuAdapter implements MenuAdapter{

    private final int POSITION_ONE = 0;
    private final int POSITION_TWO = 1;

    private CustomActivity mContext;
    private OnFilterDoneListener onFilterDoneListener;
    private OnFilterContentListener contentListener;
    private String[] titles;

    //优惠价类型默认商城优惠劵
    private String coupon_way = "1";
    //是否使用默认2表示未使用，1表示已使用
    private String is_use = "2";
    //是否过期表示未过期，1表示已过期
    private String is_exp = "0";

    public interface OnFilterDoneListener {
        void onFilterDone(String coupon_way, String is_use,String is_exp);
    }

    public interface OnFilterContentListener {
        void onFilterContent(int position, String changeText);
    }

    public CouponDropMenuAdapter(CustomActivity context, String[] titles, OnFilterDoneListener onFilterDoneListener,OnFilterContentListener contentListener){
        this.mContext = context;
        this.titles = titles;
        this.onFilterDoneListener = onFilterDoneListener;
        this.contentListener = contentListener;
    }

    @Override
    public int getMenuCount() {
        return titles.length;
    }

    @Override
    public String getMenuTitle(int position) {
        return titles[position];
    }

    @Override
    public int getBottomMargin(int position) {
        if (position == 3) {
            return 0;
        }

        return UIUtil.dp(mContext, 140);
    }

    @Override
    public View getView(int position, FrameLayout parentContainer) {
        View view = parentContainer.getChildAt(position);
        switch (position){
            case 0:
                view = createCouponTpeView();
                break;
            case 1:
                view = createStatusView();
                break;
        }
        return view;
    }

    /*
     * 设置单例的视图
     * */
    private View createCouponTpeView() {
        List<CouponTypesBeans> mList = getCouponType();
        SingleReclerView<CouponTypesBeans> singleTimeListView = new SingleReclerView<CouponTypesBeans>(mContext)
                .setMyAdapter(new SingleReclerAdapter<CouponTypesBeans>(mContext.skin) {
                    @Override
                    public String provideText(CouponTypesBeans t) {

                        return t.getCouponTypeName();
                    }

                    @Override
                    public boolean isSelect(CouponTypesBeans t) {
                       return t.isSelect();
                    }
                }).setItemClickListener((position,adapter) -> {
                    for(CouponTypesBeans beans : mList){
                        beans.setSelect(false);
                    }
                    mList.get(position).setSelect(true);
                    adapter.setNewData(mList);

                    coupon_way = mList.get(position).getCouponType();

                    //去更新数据
                    onFilterDone(coupon_way,is_use,is_exp);

                    //去更新显示类容
                    onFilterText(POSITION_ONE, mList.get(position).getCouponTypeName());
                });

        singleTimeListView.setList(mList);

        return singleTimeListView;
    }
    /*
     * 设置单例的视图
     * */
    private View createStatusView() {
        List<CouponStatusBeans> mList = getCouponStatus();
        SingleReclerView<CouponStatusBeans> singleTimeListView = new SingleReclerView<CouponStatusBeans>(mContext)
                .setMyAdapter(new SingleReclerAdapter<CouponStatusBeans>(mContext.skin) {
                    @Override
                    public String provideText(CouponStatusBeans t) {
                        return t.getStatusName();
                    }

                    @Override
                    public boolean isSelect(CouponStatusBeans t) {
                       return t.isSelect();
                    }
                }).setItemClickListener((position,adapter) -> {
                    for(CouponStatusBeans beans : mList){
                        beans.setSelect(false);
                    }
                    mList.get(position).setSelect(true);
                    adapter.setNewData(mList);

                    is_use = mList.get(position).getStatusName().equals("已使用") ? "1" : "2";
                    is_exp = mList.get(position).getStatusName().equals("已过期") ? "1" : "0";

                    //去更新数据
                    onFilterDone(coupon_way,is_use,is_exp);

                    //去更新显示类容
                    onFilterText(POSITION_TWO, mList.get(position).getStatusName());
                });

        singleTimeListView.setList(mList);

        return singleTimeListView;
    }

    /*
     * 组装类型数据
     * */
    private List<CouponTypesBeans> getCouponType(){
        List<CouponTypesBeans> list = new ArrayList<>();

        list.add(new CouponTypesBeans("1","商场优惠券",true));
        list.add(new CouponTypesBeans("2","门店优惠券",false));

        return list;
    }
    /*
     * 组装状态数据
     * */
    private List<CouponStatusBeans> getCouponStatus(){
        List<CouponStatusBeans> list = new ArrayList<>();

        list.add(new CouponStatusBeans("2","未使用",true));
        list.add(new CouponStatusBeans("1","已使用",false));
        list.add(new CouponStatusBeans("0","已过期",false));

        return list;
    }

    /*
     * 选中信息回调
     * */
    private void onFilterDone(String coupon_way, String is_use,String is_exp) {
        if (onFilterDoneListener != null) {
            onFilterDoneListener.onFilterDone(coupon_way,is_use,is_exp);
        }
    }
    /*
     * 选中信息菜单栏显示类容的回调
     * */
    private void onFilterText(int position,String changeText) {
        if (contentListener != null) {
            contentListener.onFilterContent(position, changeText);
        }
    }
}
