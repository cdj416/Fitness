package com.hongyuan.fitness.custom_view;

import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.base.SingleClick;
import com.hongyuan.fitness.ui.person.my_coupon.CouponListBeans;
import com.hongyuan.fitness.ui.person.my_coupon.receive_coupon_list.ReceiveCouponListActivity;
import com.hongyuan.fitness.ui.person.my_coupon.store_receive_coupon.StoreReceiveCouponAdapter;
import com.hongyuan.fitness.util.DividerItemDecoration;
import java.util.List;

public class HomeRecyclerItemView extends LinearLayout {

    private TextView name,more;
    private CustomRecyclerView mRecycler;
    private String os_id;

    public interface IntReceiveCoupon{
        void goReceive(String couponId,int position);
    }

    public HomeRecyclerItemView(Context context) {
        super(context);
        initLayoutView();
    }

    public HomeRecyclerItemView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initLayoutView();
    }

    public HomeRecyclerItemView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initLayoutView();
    }

    public void initLayoutView() {
        View view = View.inflate(getContext(), R.layout.view_home_recycleview_item, this);
        name = view.findViewById(R.id.name);
        more = view.findViewById(R.id.more);
        mRecycler = view.findViewById(R.id.mRecycler);

        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRecycler.addItemDecoration(new DividerItemDecoration(
                getContext(), DividerItemDecoration.VERTICAL_LIST,24,0x00000000));
        mRecycler.setLayoutManager(manager);

    }

    /*
    * 优惠券领取---门店使用
    * */
    public void setStoreCoupon(String os_id,List<CouponListBeans.DataBean.ListBean> mList,IntReceiveCoupon receiveCoupon){
        name.setText("优惠券");

        StoreReceiveCouponAdapter adapter = new StoreReceiveCouponAdapter();
        mRecycler.setAdapter(adapter);
        adapter.setNewData(mList);
        adapter.setOnItemChildClickListener(getItemChildClick());

        more.setOnClickListener(v ->{
            Bundle bundle = new Bundle();
            bundle.putString("os_id",os_id);
            ((CustomActivity)getContext()).startActivity(ReceiveCouponListActivity.class,bundle);} );

        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @SingleClick(2000)
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                receiveCoupon.goReceive(String.valueOf(mList.get(position).getCoupon_id()),position);
            }
        });
    }


    /*
     * 子项点击事件
     * */
    private BaseQuickAdapter.OnItemChildClickListener getItemChildClick(){
        return (adapter, view, position) -> {

        };
    }

}
