package com.hongyuan.fitness.ui.shop.sfragment;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.Constants;
import com.hongyuan.fitness.base.ConstantsCode;
import com.hongyuan.fitness.base.Controller;
import com.hongyuan.fitness.base.CustomFragment;
import com.hongyuan.fitness.ui.shop.sactivity.SgoodsDetailActivity;
import com.hongyuan.fitness.ui.shop.sadapter.SsearchGoodsAdapter;
import com.hongyuan.fitness.ui.shop.sbeans.GoodsBeans;
import com.hongyuan.fitness.ui.shop.sbeans.GoodsFilterBeans;
import com.hongyuan.fitness.ui.shop.sinterface.SearchOCDrawerListener;
import com.hongyuan.fitness.util.BaseUtil;
import com.hongyuan.fitness.util.DividerItemDecoration;
import com.hongyuan.fitness.util.HiddenAnimUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

public class SsearchGoodsFragment extends CustomFragment {

    private RecyclerView mRec;
    private SsearchGoodsAdapter adapter;
    private List<GoodsBeans.DataBean.ListBean> mList;

    //头部控件
    private LinearLayout openFilter,sumUpFilter,salesFilter,priceFilter;
    private TextView sumUpText,salesText,priceText,openText;
    private ImageView sumUpImg,priceImg;

    //内容控件
    private LinearLayout sumUpBox;
    private RelativeLayout zhFilterBox;
    private View mengCheng;
    private TextView first,second;

    //筛选栏的打开操作
    private SearchOCDrawerListener drawerListener;
    //展开收缩动画工具类
    private HiddenAnimUtils animUtils;

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

        //关闭抽屉菜单
        drawerListener.openOrClose(false);

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

    public SsearchGoodsFragment(SearchOCDrawerListener drawerListener){
        this.drawerListener = drawerListener;
    }

    @Override
    protected int getHeadLayoutId() {
        return R.layout.fragment_headview_searchgoods;
    }

    @Override
    protected void initHeadView(View headLayt) {
        openFilter = headLayt.findViewById(R.id.openFilter);
        sumUpFilter = headLayt.findViewById(R.id.sumUpFilter);
        salesFilter = headLayt.findViewById(R.id.salesFilter);
        priceFilter = headLayt.findViewById(R.id.priceFilter);

        sumUpText = headLayt.findViewById(R.id.sumUpText);
        salesText = headLayt.findViewById(R.id.salesText);
        priceText = headLayt.findViewById(R.id.priceText);
        openText = headLayt.findViewById(R.id.openText);

        sumUpImg = headLayt.findViewById(R.id.sumUpImg);
        priceImg = headLayt.findViewById(R.id.priceImg);

        openFilter.setOnClickListener(v -> drawerListener.openOrClose(true));
        sumUpFilter.setOnClickListener(v -> {
            if(zhFilterBox.getVisibility() == View.GONE){
                zhFilterBox.setVisibility(View.VISIBLE);
            }else{
                zhFilterBox.setVisibility(View.GONE);
            }
            animUtils.toggle();

        });

        salesFilter.setOnClickListener(v -> {
            if(!Boolean.valueOf(salesText.getTag().toString())){
                salesText.setTextColor(getResources().getColor(R.color.color_EF5B48));
                salesText.setTag("true");
                filterBeans.setOrder1("sale");
            }else{
                salesText.setTextColor(getResources().getColor(R.color.color_FF333333));
                salesText.setTag("false");
                filterBeans.setOrder1("");
            }
            lazyLoad();

        });
        priceFilter.setOnClickListener(v -> {
            priceText.setTextColor(getResources().getColor(R.color.color_EF5B48));
            if("".equals(priceText.getTag())){
                priceText.setTag("price_d");
                filterBeans.setOrder2("price_d");
                priceImg.setImageResource(R.mipmap.price_down_mark);
                priceText.setTextColor(getResources().getColor(R.color.color_EF5B48));
            }else if("price_d".equals(priceText.getTag())){
                priceText.setTag("price_a");
                filterBeans.setOrder2("price_a");
                priceImg.setImageResource(R.mipmap.price_up_mark);
                priceText.setTextColor(getResources().getColor(R.color.color_EF5B48));
            }else if("price_a".equals(priceText.getTag())){
                priceText.setTag("");
                filterBeans.setOrder2("");
                priceImg.setImageResource(R.mipmap.price_default);
                priceText.setTextColor(getResources().getColor(R.color.color_FF333333));
            }
            lazyLoad();
        });
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_search_goods;
    }

    @Override
    public void initView(View mView) {
        setEnableLoadMore(true);
        setEnableRefresh(true);

        filterBeans = new GoodsFilterBeans();
        filterBeans.setSearch_name(mActivity.getBundle().getString("showText"));

        if(mActivity.getBundle().getString("first_category_id") != null){
            filterBeans.setFirst_category_id(mActivity.getBundle().getString("first_category_id"));
        }
        if(mActivity.getBundle().getString("third_category_id") != null){
            filterBeans.setThird_category_id(mActivity.getBundle().getString("third_category_id"));
        }

        mRec = mView.findViewById(R.id.mRec);
        sumUpBox = mView.findViewById(R.id.sumUpBox);
        zhFilterBox = mView.findViewById(R.id.zhFilterBox);
        mengCheng = mView.findViewById(R.id.mengCheng);
        first = mView.findViewById(R.id.first);
        second = mView.findViewById(R.id.second);

        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        mRec.setLayoutManager(manager);
        mRec.addItemDecoration(new DividerItemDecoration(
                getContext(), DividerItemDecoration.HORIZONTAL_LIST,2,getResources().getColor(R.color.color_EEEEEE)));
        adapter = new SsearchGoodsAdapter();
        mRec.setAdapter(adapter);
        adapter.setOnItemChildClickListener((adapter, view, position) -> {
            Bundle bundle = new Bundle();
            bundle.putString("g_id",String.valueOf(mList.get(position).getG_id()));
            startActivity(SgoodsDetailActivity.class,bundle);
        });

        //初始化动画对象
        animUtils = HiddenAnimUtils.newInstance(mActivity,sumUpBox,sumUpImg,89,0,false);

        mengCheng.setOnClickListener(v -> {
            zhFilterBox.setVisibility(View.GONE);
            animUtils.toggle();
        });
        first.setOnClickListener(v -> {
            first.setTextColor(getResources().getColor(R.color.color_EF5B48));
            second.setTextColor(getResources().getColor(R.color.color_FF999999));
            zhFilterBox.setVisibility(View.GONE);
            animUtils.toggle();
            filterBeans.setOrder3("");
            lazyLoad();
        });
        second.setOnClickListener(v -> {
            first.setTextColor(getResources().getColor(R.color.color_FF999999));
            second.setTextColor(getResources().getColor(R.color.color_EF5B48));
            zhFilterBox.setVisibility(View.GONE);
            animUtils.toggle();
            filterBeans.setOrder3("evaluation");
            lazyLoad();
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
        clearParams().setParams("is_point","0");
        if(BaseUtil.isValue(filterBeans.getFirst_category_id())){
            setParams("first_category_id",filterBeans.getFirst_category_id());
        }
        if(BaseUtil.isValue(filterBeans.getThird_category_id())){
            setParams("third_category_id",filterBeans.getThird_category_id());
        }
        if(BaseUtil.isValue(filterBeans.getSearch_name())){
            setParams("search_name",filterBeans.getSearch_name());
        }
        if(BaseUtil.isValue(filterBeans.getOrder1())){
            setParams("order1",filterBeans.getOrder1());
        }
        if(BaseUtil.isValue(filterBeans.getOrder2())){
            setParams("order2",filterBeans.getOrder2());
        }
        if(BaseUtil.isValue(filterBeans.getOrder3())){
            setParams("order3",filterBeans.getOrder3());
        }
        if(BaseUtil.isValue(filterBeans.getFirst_price())){
            setParams("first_price",filterBeans.getFirst_price());
        }
        if(BaseUtil.isValue(filterBeans.getFirst_price())){
            setParams("end_price",filterBeans.getEnd_price());
        }
        if(BaseUtil.isValue(filterBeans.getDeliver_code())){
            setParams("deliver_code",filterBeans.getDeliver_code());
        }
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
