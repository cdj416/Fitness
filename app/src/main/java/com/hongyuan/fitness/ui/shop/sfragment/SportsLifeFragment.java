package com.hongyuan.fitness.ui.shop.sfragment;

import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.Constants;
import com.hongyuan.fitness.base.Controller;
import com.hongyuan.fitness.base.CustomFragment;
import com.hongyuan.fitness.ui.shop.sactivity.SgoodsDetailActivity;
import com.hongyuan.fitness.ui.shop.sadapter.SMGoodsAdapter;
import com.hongyuan.fitness.ui.shop.sbeans.GoodsBeans;
import com.hongyuan.fitness.ui.shop.sbeans.SporsLisfeBeans;
import com.hongyuan.fitness.ui.shop.sbeans.SportsLifeMensBean;

import java.util.List;

public class SportsLifeFragment extends CustomFragment {

    private RecyclerView mRec;
    private SMGoodsAdapter gAdapter;

    private SportsLifeMensBean.DataBean.ListBean item;

    private List<GoodsBeans.DataBean.ListBean> mList;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_base_recylerview;
    }

    @Override
    public void initView(View mView) {
        setEnableLoadMore(true);
        setEnableRefresh(true);

        item = (SportsLifeMensBean.DataBean.ListBean) getSerializableBeans("item");

        mRec = mView.findViewById(R.id.mRec);

        GridLayoutManager rihtManager = new GridLayoutManager(mActivity, 2);
        rihtManager.setOrientation(RecyclerView.VERTICAL);
        mRec.setLayoutManager(rihtManager);
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
        mRec.setAdapter(gAdapter);

        gAdapter.setOnItemChildClickListener((adapter, view, position) -> {
            Bundle bundle = new Bundle();
            bundle.putString("g_id",String.valueOf(mList.get(position).getG_id()));
            startActivity(SgoodsDetailActivity.class,bundle);
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
        mActivity.showLoading();;
        //请求推荐商品
        clearParams().setParams("second_category_id",String.valueOf(item.getCategory_id()));
        Controller.myRequest(Constants.GET_GOODS_LIST_SIX,Controller.TYPE_POST,getParams(), GoodsBeans.class,this);
    }

    @Override
    public void onSuccess(Object data) {
        mActivity.closeLoading();
        if(data instanceof GoodsBeans) {
            List<GoodsBeans.DataBean.ListBean> list = ((GoodsBeans) data).getData().getList();
            if (curPage == FIRST_PAGE) {
                mList = list;
            } else {
                if (list != null && list.size() > 0) {
                    mList.addAll(list);
                } else {
                    refresh.finishLoadMoreWithNoMoreData();
                }
            }

            if (mList != null && mList.size() > 0) {
                gAdapter.setNewData(mList);
                setPromtView(SHOW_DATA);
            } else {
                setPromtView(SHOW_EMPTY);
            }
        }
    }
}
