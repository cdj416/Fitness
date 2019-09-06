package com.hongyuan.fitness.ui.mall.good_list;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.Constants;
import com.hongyuan.fitness.base.ConstantsCode;
import com.hongyuan.fitness.base.Controller;
import com.hongyuan.fitness.base.CustomFragment;
import com.hongyuan.fitness.ui.main.main_mall.MallAdapter;
import com.hongyuan.fitness.ui.main.main_mall.MallBeans;
import com.hongyuan.fitness.ui.mall.good_details.GoodDetailsActivity;
import com.hongyuan.fitness.util.DividerItemDecoration;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

public class GoodListFragment extends CustomFragment {

    private RecyclerView goodMenuRec,goodRec;
    private MallAdapter adapter;
    private GoodSecondMenuAdapter menuAdapter;
    private RelativeLayout load_box;

    private GoodListMeanBean meanBean;
    private MallBeans mallBeans;

    //商品查询条件
    private String seachText = "";

    //一级菜单id
    private String first_category_id;

    //二级菜单id
    private String second_category_id;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_good_list;
    }

    @Override
    public void initView(View mView) {
        //开启刷新
        setEnableRefresh(true);

        goodMenuRec = mView.findViewById(R.id.goodMenuRec);
        goodRec = mView.findViewById(R.id.goodRec);
        load_box = mView.findViewById(R.id.load_box);

        LinearLayoutManager menuManager = new LinearLayoutManager(getContext());
        menuManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        goodMenuRec.addItemDecoration(new DividerItemDecoration(
                getContext(), DividerItemDecoration.VERTICAL_LIST,50,0x00000000));
        goodMenuRec.setLayoutManager(menuManager);
        menuAdapter = new GoodSecondMenuAdapter();
        goodMenuRec.setAdapter(menuAdapter);
        menuAdapter.setOnItemChildClickListener((adapter, view, position) -> {
            for(GoodListMeanBean.DataBean.ListBean bean : meanBean.getData().getList()){
                bean.setSelect(false);
            }
            meanBean.getData().getList().get(position).setSelect(true);
            menuAdapter.setNewData(meanBean.getData().getList());

            //去查询商品
            first_category_id = String.valueOf(meanBean.getData().getList().get(position).getParent_id());
            second_category_id = String.valueOf(meanBean.getData().getList().get(position).getCategory_id());
            getGoodS();
        });


        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 2);
        goodRec.setLayoutManager(layoutManager);
        /*goodRec.addItemDecoration(new DividerItemDecoration(
                getContext(), DividerItemDecoration.HORIZONTAL_LIST,24,0x00000000));*/
        goodRec.addItemDecoration(new DividerItemDecoration(
                getContext(), DividerItemDecoration.VERTICAL_LIST,24,0x00000000));
        goodRec.setLayoutManager(layoutManager);
        adapter = new MallAdapter();
        goodRec.setAdapter(adapter);
        adapter.setOnItemChildClickListener((adapter, view, position) -> {
            Bundle bundle = new Bundle();
            bundle.putString("g_id",String.valueOf(mallBeans.getData().getList().get(position).getG_id()));
            startActivity(GoodDetailsActivity.class,bundle);
        });
    }

    @Override
    protected void lazyOnceLoad() {
        clearParams().setParams("category_id",getFragType());
        Controller.myRequest(Constants.GET_SECOND_CATEGORY,Controller.TYPE_POST,getParams(), GoodListMeanBean.class,this);
    }

    @Override
    protected void lazyLoad() {
        if(isValue(first_category_id)){
            getGoodS();
        }else{
            closeRefresh();
        }
    }

    /*
    * 请求商品列表
    * */
    private void getGoodS(){
        clearParams().setParams("first_category_id",first_category_id).setParams("second_category_id",second_category_id);
        if(isValue(seachText)){
            setParams("search_name",seachText);
        }
        Controller.myRequest(Constants.GET_GOODS_LIST,Controller.TYPE_POST,getParams(), MallBeans.class,this);
    }

    @Override
    public void onSuccess(Object data) {
        if(data instanceof GoodListMeanBean){
            meanBean = (GoodListMeanBean)data;
            //添加一个全部类型的子项
            GoodListMeanBean.DataBean.ListBean firstBean = new GoodListMeanBean.DataBean.ListBean();
            firstBean.setSelect(true);
            firstBean.setCategory_id(0);
            firstBean.setCategory_name("全部");
            firstBean.setLevel(2);
            firstBean.setParent_id(Integer.valueOf(getFragType()));
            meanBean.getData().getList().add(0,firstBean);
            menuAdapter.setNewData(meanBean.getData().getList());

            //去请求商品
            first_category_id = String.valueOf(meanBean.getData().getList().get(0).getParent_id());
            second_category_id = String.valueOf(meanBean.getData().getList().get(0).getCategory_id());
            getGoodS();
        }

        if(data instanceof MallBeans){
            mallBeans = (MallBeans)data;
            if(mallBeans.getData().getList() != null && mallBeans.getData().getList().size() > 0){
                adapter.setNewData(mallBeans.getData().getList());
                goodRec.setVisibility(View.VISIBLE);
                load_box.setVisibility(View.GONE);
            }else{
                load_box.setVisibility(View.VISIBLE);
                goodRec.setVisibility(View.GONE);
            }

        }
    }

    /*
     * 搜索
     * */
    @Subscribe(id = ConstantsCode.EB_SEARCH_GOODS)
    public void search(String message) {
        //并且把数据清空
        //addFoodBean = null;
        Log.e("cdj","=========没来吗？====="+message);
        //每次搜索初始化页数为1
        curPage = FIRST_PAGE;
        seachText = message;
        lazyLoad();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        EventBus.getDefault().register(this);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(this);
    }
}
