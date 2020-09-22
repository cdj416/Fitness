package com.hongyuan.fitness.ui.shop.sviewmodel;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.BaseBean;
import com.hongyuan.fitness.base.Constants;
import com.hongyuan.fitness.base.ConstantsCode;
import com.hongyuan.fitness.base.Controller;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.base.CustomViewModel;
import com.hongyuan.fitness.databinding.ActivityScartBinding;
import com.hongyuan.fitness.ui.shop.sactivity.SgoodsDetailActivity;
import com.hongyuan.fitness.ui.shop.sactivity.ShopMessageActivity;
import com.hongyuan.fitness.ui.shop.sactivity.SstoreActivity;
import com.hongyuan.fitness.ui.shop.sadapter.SCartAdapter;
import com.hongyuan.fitness.ui.shop.sbeans.CartJsonBeans;
import com.hongyuan.fitness.ui.shop.sbeans.ScartBeans;
import com.hongyuan.fitness.ui.shop.sinterface.MyClickListener;
import com.hongyuan.fitness.ui.shop.smyview.SCartTitleRightView;
import com.hongyuan.fitness.ui.shop.sviewmodel.bottomviewmodel.ScartBottomViewModel;
import com.hongyuan.fitness.util.BaseUtil;
import com.hongyuan.fitness.util.BigDecimalUtils;
import com.hongyuan.fitness.util.CustomDialog;
import com.hongyuan.fitness.util.GsonUtil;

import java.util.ArrayList;
import java.util.List;

public class ScartViewModel extends CustomViewModel implements MyClickListener {

    private ActivityScartBinding binding;
    private ScartBottomViewModel bModel;

    //标题栏右边视图
    private SCartTitleRightView rightView;
    private SCartAdapter adapter;

    //购物车使用的数据集
    private List<MultiItemEntity> mList;

    //当前可操作模式false为购买商品模式，true为管理商品模式
    private boolean mode = false;

    public ScartViewModel(CustomActivity mActivity, ActivityScartBinding binding, ScartBottomViewModel bModel) {
        super(mActivity);
        this.binding = binding;
        this.bModel = bModel;
        //初始化对象
        bModel.setManViewModel(this);
        initView();
    }

    @Override
    protected void initView() {
        setEnableOverScrollDrag(true);

        //设置右边显示视图
        rightView = new SCartTitleRightView(mActivity, this);
        mActivity.getMainTitle().addRightContentView(rightView);

        LinearLayoutManager manager = new LinearLayoutManager(mActivity);
        manager.setOrientation(RecyclerView.VERTICAL);
        binding.mRec.setLayoutManager(manager);
        adapter = new SCartAdapter(new ArrayList<>(),this::onMyClick);
        binding.mRec.setAdapter(adapter);
        adapter.setFooterView(mActivity.getFooterHeight(binding.mRec));

        adapter.setOnItemChildClickListener((adapter, view, position) -> {
            if(view.getId() == R.id.selectImg){
                ScartBeans.DataBean.ListBean oneBean = (ScartBeans.DataBean.ListBean) adapter.getData().get(position);
                if(oneBean.isSelectAll()){
                    oneBean.setSelectAll(false);
                    for(ScartBeans.DataBean.ListBean.GoodsListBean listBean : oneBean.getSubItems()){
                        listBean.setSelect(false);
                    }
                }else{
                    oneBean.setSelectAll(true);
                    for(ScartBeans.DataBean.ListBean.GoodsListBean listBean : oneBean.getSubItems()){
                        listBean.setSelect(true);
                    }
                }

                adapter.notifyDataSetChanged();
                //设置需要显示总价格
                bModel.setAllPric(countPrice());
            }

            //不想写注释了，心情不好，求安慰（˙︿˙，˙︿˙，˙︿˙）
            if(view.getId() == R.id.childSelectImg){
                ScartBeans.DataBean.ListBean.GoodsListBean twoBean = (ScartBeans.DataBean.ListBean.GoodsListBean) adapter.getData().get(position);
                if(twoBean.isSelect()){
                    twoBean.setSelect(false);
                }else{
                    twoBean.setSelect(true);
                }

                loop:
                for(MultiItemEntity partBean : mList){
                    if(partBean instanceof ScartBeans.DataBean.ListBean){
                        ScartBeans.DataBean.ListBean useBean = (ScartBeans.DataBean.ListBean) partBean;
                        if(useBean.contains(twoBean)){
                            for(int i = 0 ; i <  useBean.getSubItems().size() ; i++ ){
                                if(!useBean.getSubItems().get(i).isSelect()){
                                    useBean.setSelectAll(false);
                                    break loop;
                                }
                            }
                            useBean.setSelectAll(true);
                            break;
                        }
                    }
                }
                adapter.notifyDataSetChanged();
                //设置需要显示总价格
                bModel.setAllPric(countPrice());
            }

            if(view.getId() == R.id.storeName){
                ScartBeans.DataBean.ListBean oneBean = (ScartBeans.DataBean.ListBean) adapter.getData().get(position);
                Bundle bundle = new Bundle();
                bundle.putString("store_id",String.valueOf(oneBean.getStore_id()));
                startActivity(SstoreActivity.class,bundle);
                return;
            }

            if(view.getId() == R.id.goodImg || view.getId() == R.id.goDetail){
                ScartBeans.DataBean.ListBean.GoodsListBean twoBean = (ScartBeans.DataBean.ListBean.GoodsListBean) adapter.getData().get(position);
                Bundle bundle = new Bundle();
                bundle.putString("g_id",String.valueOf(twoBean.getG_id()));
                startActivity(SgoodsDetailActivity.class,bundle);
                return;
            }

            //遍历识别是否全选
            for(MultiItemEntity partBean : mList){
                if(partBean instanceof ScartBeans.DataBean.ListBean){
                    ScartBeans.DataBean.ListBean useBean = (ScartBeans.DataBean.ListBean) partBean;
                    for(int i = 0 ; i <  useBean.getSubItems().size() ; i++ ){
                        if(!useBean.getSubItems().get(i).isSelect()){
                            bModel.changeAllSelect(false);
                            return;
                        }
                    }
                }
            }

            bModel.changeAllSelect(true);
        });

    }

    /*
    * 获取订单详情页面需要的json参数
    * */
    public String getCartJson(){
        if(mList == null) return "";
        List<CartJsonBeans> jsonList = new ArrayList<>();
        //遍历需要购买的商品
        for(MultiItemEntity partBean : mList){
            if(partBean instanceof ScartBeans.DataBean.ListBean){
                ScartBeans.DataBean.ListBean useBean = (ScartBeans.DataBean.ListBean) partBean;

                CartJsonBeans jsonBeans = new CartJsonBeans();
                jsonBeans.setStore_id(useBean.getStore_id());
                List<CartJsonBeans.GoodsListBean> jsonGoods = new ArrayList<>();

                for(ScartBeans.DataBean.ListBean.GoodsListBean goodsListBean : useBean.getSubItems()){
                    if(goodsListBean.isSelect()){
                        CartJsonBeans.GoodsListBean listBean = new CartJsonBeans.GoodsListBean();
                        listBean.setBuy_num(goodsListBean.getBuy_num());
                        listBean.setCart_id(goodsListBean.getCart_id());
                        listBean.setGp_id(goodsListBean.getGp_id());
                        jsonGoods.add(listBean);
                    }
                }

                if(jsonGoods.size() > 0){
                    jsonBeans.setGoods_list(jsonGoods);
                    jsonList.add(jsonBeans);
                }
            }
        }

        if(jsonList.size() <= 0){
            return null;
        }
        return GsonUtil.toJsonStr(jsonList);
    }

    /*
    * 计算底部显示的价格
    * */
    private String countPrice(){
        String allPric = "0";
        for(MultiItemEntity partBean : mList){
            if(partBean instanceof ScartBeans.DataBean.ListBean){
                ScartBeans.DataBean.ListBean useBean = (ScartBeans.DataBean.ListBean) partBean;
                for(ScartBeans.DataBean.ListBean.GoodsListBean childBean : useBean.getSubItems()){
                    if(childBean.isSelect()){
                        allPric = BigDecimalUtils.add(allPric,BigDecimalUtils.mul(childBean.getGp_price(),String.valueOf(childBean.getBuy_num()),2),2);
                    }
                }
            }
        }

        return BaseUtil.getNoZoon(allPric);
    }

    /*
    * 全选，取消切换
    * */
    public void changAllCheck(boolean flag){
        if(mList == null) return;

        for(MultiItemEntity partBean : mList){
            if(partBean instanceof ScartBeans.DataBean.ListBean){
                ScartBeans.DataBean.ListBean useBean = (ScartBeans.DataBean.ListBean) partBean;
                useBean.setSelectAll(flag);
                for(ScartBeans.DataBean.ListBean.GoodsListBean childBean : useBean.getSubItems()){
                    childBean.setSelect(flag);
                }
            }
        }
        adapter.notifyDataSetChanged();

        //设置需要显示总价格
        bModel.setAllPric(countPrice());
    }

    /*
    * 判断是否有数据
    * */
    public boolean isData(){
        if(mList != null && mList.size() > 0){
            return true;
        }
        return false;
    }

    /*
    * 删除商品
    * flag:是否全部删除
    * */
    public void deletGoods(boolean flag){
        clearParams();
        if(flag){
            setParams("del_all","1");
        }else{
            String ids = getDeleteIds();
            if(!BaseUtil.isValue(ids)){
                CustomDialog.showMessage(mActivity,"请选择删除的商品！");
                return;
            }
            setParams("cart_ids",ids);
        }

        CustomDialog.promptDialog(mActivity, "确定要删除选中的商品？", "确定", "在想想", false, v -> {
            if(v.getId() == R.id.isOk){
                mActivity.showLoading();
                Controller.myRequest(ConstantsCode.DEL_SHOPPING_CART,Constants.DEL_SHOPPING_CART,Controller.TYPE_POST,getParams(), BaseBean.class,this);
            }
        });
    }

    /*
    * 获取要删除的商品ids
    * */
    private String getDeleteIds(){
        if(!isData()){
            return null;
        }

        String ids = "";
        for(MultiItemEntity partBean : mList){
            if(partBean instanceof ScartBeans.DataBean.ListBean){
                ScartBeans.DataBean.ListBean useBean = (ScartBeans.DataBean.ListBean) partBean;
                for(ScartBeans.DataBean.ListBean.GoodsListBean childBean : useBean.getSubItems()){
                    if(childBean.isSelect()){
                        ids += "," + childBean.getCart_id();
                    }
                }
            }
        }
        if(BaseUtil.isValue(ids)){
            return ids.substring(1);
        }
        return null;
    }


    @Override
    public void refreshData() {
        mActivity.showLoading();
        clearParams();
        Controller.myRequest(ConstantsCode.GET_SHOPPING_CART,Constants.GET_SHOPPING_CART,Controller.TYPE_POST,getParams(), ScartBeans.class,this);
    }

    @Override
    public void onMyClick(View v) {
        switch (v.getId()){
            case R.id.operatText:
                bModel.changeModel(mode);
                if(!mode){
                    rightView.setOperatText("完成");
                    mode = true;
                }else{
                    rightView.setOperatText("管理");
                    mode = false;
                }
                break;
            case R.id.message:
                startActivity(ShopMessageActivity.class,null);
                break;

            case R.id.sub:
            case R.id.add:
                //设置需要显示总价格
                bModel.setAllPric(countPrice());
                break;
        }
    }

    @Override
    public void onSuccess(Object data) {
        super.onSuccess(data);
    }

    @Override
    public void onSuccess(int code, Object data) {
        super.onSuccess(code,data);

        mActivity.closeLoading();

        if(code == ConstantsCode.GET_SHOPPING_CART){
            //每次重新请求时初始化全选状态
            bModel.changeAllSelect(false);

            ScartBeans.DataBean scartBeans = ((ScartBeans)data).getData();

            if(scartBeans.getList() != null && scartBeans.getList().size() > 0){
                mList = scartBeans.getmList();
                if(mList != null && mList.size() > 0){
                    adapter.setNewData(mList);
                    //打开所有子项列表
                    adapter.expandAll();
                    mActivity.setPromtView(mActivity.SHOW_DATA);
                }else{
                    mActivity.setPromtView(mActivity.SHOW_EMPTY);
                }
            }else{
                mList = null;
                mActivity.setPromtView(mActivity.SHOW_EMPTY);
            }
        }

        if(code == ConstantsCode.DEL_SHOPPING_CART){
            showSuccess("删除成功！");
            //删除成功之后去重新获取下数据
            refreshData();
        }
    }
}
