package com.hongyuan.fitness.ui.shop.sfragment;

import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.ConstantsCode;
import com.hongyuan.fitness.base.CustomFragment;
import com.hongyuan.fitness.custom_view.time_selecter.get_address.JsonFileBean;
import com.hongyuan.fitness.ui.shop.sadapter.SearchProvinceAdapter;
import com.hongyuan.fitness.ui.shop.sbeans.GoodsFilterBeans;
import com.hongyuan.fitness.util.LocationBean;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

public class SearchDrawerFragment extends CustomFragment {

    private EditText startPrice,endPrice;
    private TextView locationPro,locationCity,cancel,submit;
    private RecyclerView mRec;

    private SearchProvinceAdapter adapter;
    private List<JsonFileBean.DataBean> mList;

    private int mPosition = -1;
    @Override
    public int getLayoutId() {
        return R.layout.fragment_search_right_drawer;
    }

    @Override
    public void initView(View mView) {

        startPrice = mView.findViewById(R.id.startPrice);
        endPrice = mView.findViewById(R.id.endPrice);
        locationPro = mView.findViewById(R.id.locationPro);
        locationCity = mView.findViewById(R.id.locationCity);
        cancel = mView.findViewById(R.id.cancel);
        submit = mView.findViewById(R.id.submit);
        mRec = mView.findViewById(R.id.mRec);

        GridLayoutManager layoutManager =
                new GridLayoutManager(mActivity,4);
        mRec.setLayoutManager(layoutManager);
        adapter = new SearchProvinceAdapter();
        mRec.setAdapter(adapter);
        adapter.setOnItemChildClickListener((adapter, view, position) -> {
            mPosition = position;
            for (JsonFileBean.DataBean bean : mList){
                bean.setSelect(false);
            }
            mList.get(position).setSelect(true);
            adapter.setNewData(mList);
        });


        //设置定位初始值
        locationPro.setText(LocationBean.getInstance().getProName());
        locationCity.setText(LocationBean.getInstance().getCityName());

        submit.setOnClickListener(v -> {
            GoodsFilterBeans filterBeans = new GoodsFilterBeans();
            filterBeans.setFirst_price(startPrice.getText().toString());
            filterBeans.setEnd_price(endPrice.getText().toString());
            if(mPosition != -1){
                filterBeans.setDeliver_code(mList.get(mPosition).getRegion_code());
            }
            //通知列表刷新筛选的数据
            EventBus.getDefault().post(ConstantsCode.GET_GOODS_LIST_SIX,filterBeans);
        });
        //取消所选内容
        cancel.setOnClickListener(v -> {
            mPosition = -1;
            for (JsonFileBean.DataBean bean : mList){
                bean.setSelect(false);
            }
            adapter.setNewData(mList);
            startPrice.setText("");
            endPrice.setText("");
        });
    }

   /*
    * 设置相关地址数据
    * */
    public void setProData(List<JsonFileBean.DataBean> mList){
        this.mList = mList;
        adapter.setNewData(mList);
    }

    @Override
    public void onSuccess(Object data) {

    }
}
