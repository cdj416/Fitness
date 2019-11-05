package com.hongyuan.fitness.ui.membership_card.v4_mycard_list;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.Constants;
import com.hongyuan.fitness.base.Controller;
import com.hongyuan.fitness.base.CustomFragment;
import com.hongyuan.fitness.base.SingleClick;
import com.hongyuan.fitness.custom_view.filter_view.DropDownMenu;
import com.hongyuan.fitness.ui.membership_card.v4_mycard_detail.V4MyCardDetail;
import com.hongyuan.fitness.ui.membership_card.v4_mycard_detail.V4MyCardDetailViewModel;
import com.hongyuan.fitness.util.BaseUtil;
import com.hongyuan.fitness.util.DividerItemDecoration;
import com.scwang.smartrefresh.header.MaterialHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.BallPulseFooter;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.List;

public class V4CardsListFragment extends CustomFragment implements V4FilterCardsListAdapter.OnFilterDoneListener,
        V4FilterCardsListAdapter.OnFilterContentListener {

    private DropDownMenu dropDownMenu;
    private SmartRefreshLayout mFilterContentView;
    private RecyclerView mRecycler;

    private V4CardsListAdapter adapter;
    private List<V4CardsListBeans.DataBean.ListBean> mList;

    //传递的数据
    private String os_id;
    private String _type;
    //筛选的参数
    private String ct_id, cc_id,osl_id;

    //错误信息控件
    private RelativeLayout load_box;
    private TextView isEmpty,isTvErr;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_v4_cardslist;
    }

    @Override
    public void initView(View mView) {
        os_id = getFragType("os_id");
        _type = getFragType("_type");
        osl_id = getFragType("osl_id");

        dropDownMenu = mView.findViewById(R.id.dropDownMenu);
        mFilterContentView = mView.findViewById(R.id.mFilterContentView);
        mRecycler = mView.findViewById(R.id.mRecycler);

        //加载错误页面信息
        initPrompt(mView);

        //初始化筛选菜单
        String[] titleList = new String[] { "有效期", "筛选卡种" };
        dropDownMenu.setMenuAdapter(new V4FilterCardsListAdapter(mActivity, titleList, this,this));

        //加载刷新控件
        setOnRefresh();

        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        manager.setOrientation(RecyclerView.VERTICAL);
        mRecycler.addItemDecoration(new DividerItemDecoration(
                mActivity, DividerItemDecoration.HORIZONTAL_LIST,1,mActivity.getResources().getColor(R.color.color_EEEEEE)));
        mRecycler.setLayoutManager(manager);
        adapter = new V4CardsListAdapter();
        mRecycler.setAdapter(adapter);
        adapter.addFooterView(getFooterHeight(mRecycler));

        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @SingleClick
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                Bundle bundle = new Bundle();
                bundle.putString("card_id",String.valueOf(mList.get(position).getCard_id()));
                bundle.putInt("aisle", V4MyCardDetailViewModel.BUY_AISLE);
                bundle.putString("os_id",os_id);
                startActivity(V4MyCardDetail.class,bundle);
            }
        });
    }

    /*
     * 初始化加载控件各个设置
     * */
    private void setOnRefresh(){
        //单独开启页数控制
        isLoadMore = true;
        //关闭滚动到底部自动加载
        mFilterContentView.setEnableAutoLoadMore(false);
        //设置刷新监听
        mFilterContentView.setOnRefreshListener(onRefresh());
        //加载更多监听
        mFilterContentView.setOnLoadMoreListener(onLoadMore());
        //设置主题颜色
        mFilterContentView.setPrimaryColors(getResources().getColor(R.color.color_F5F6FB));
        //初始刷新动画
        mFilterContentView.setRefreshHeader(new MaterialHeader(mActivity).setShowBezierWave(true));
        //初始化加载动画
        mFilterContentView.setRefreshFooter(new BallPulseFooter(mActivity).setSpinnerStyle(SpinnerStyle.Scale));
        //开启上拉加载更多
        mFilterContentView.setEnableLoadMore(true);
        //开启刷新功能
        mFilterContentView.setEnableRefresh(true);

    }

    /*
     * 加载页面显示效果
     * */
    private void initPrompt(View mView){
        load_box = mView.findViewById(R.id.load_box);
        isEmpty = mView.findViewById(R.id.isEmpty);
        isTvErr = mView.findViewById(R.id.isErr);
    }

    /*
     * 下拉刷新监听
     * */
    private OnRefreshListener onRefresh() {
        return refreshLayout -> {
            //刷新，初始化页数为1
            curPage = FIRST_PAGE;

            lazyLoad();
        };
    }

    /*
     * 上啦加载更多监听
     * */
    private OnLoadMoreListener onLoadMore(){
        return refreshLayout -> {
            curPage++;
            lazyLoad();
        };
    }

    @Override
    protected void lazyLoad() {
        mActivity.showLoading();
        if("2".equals(_type)){
            clearParams().setParams("card_type",_type).setParams("osl_id",osl_id);
        }else{
            clearParams().setParams("os_id",os_id).setParams("card_type",_type);
            if(BaseUtil.isValue(ct_id)){
                setParams("ct_id",ct_id);
            }
            if(BaseUtil.isValue(cc_id)){
                setParams("cc_id",cc_id);
            }
        }

        Controller.myRequest(Constants.GET_CARD_LIST,Controller.TYPE_POST,getParams(), V4CardsListBeans.class,this);
    }

    @Override
    public void onSuccess(Object data) {
        mActivity.closeLoading();
        if(data instanceof V4CardsListBeans){
            List<V4CardsListBeans.DataBean.ListBean> list = ((V4CardsListBeans)data).getData().getList();
            if(curPage == FIRST_PAGE){
                mList = list;
            }else{
                if(list != null && list.size() > 0){
                    mList.addAll(list);
                }else{
                    mFilterContentView.finishLoadMoreWithNoMoreData();
                }
            }

            if(mList != null && mList.size() > 0){
                adapter.setNewData(mList);
                load_box.setVisibility(View.GONE);
                mRecycler.setVisibility(View.VISIBLE);
            }else{
                load_box.setVisibility(View.VISIBLE);
                mRecycler.setVisibility(View.GONE);
            }
        }
    }

    @Override
    public void closeRefresh() {
        if(mFilterContentView != null){
            mFilterContentView.finishRefresh();
            mFilterContentView.finishLoadMore();
        }
    }

    @Override
    public void onFilterContent(int position, String changeText) {
        dropDownMenu.setPositionIndicatorText(position,changeText);
    }

    @Override
    public void onFilterDone(String ct_id,String cc_id) {
        dropDownMenu.close();
        this.cc_id = cc_id;
        this.ct_id = ct_id;
        lazyLoad();
    }
}
