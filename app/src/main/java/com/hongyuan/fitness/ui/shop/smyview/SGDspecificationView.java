package com.hongyuan.fitness.ui.shop.smyview;

import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.BaseBean;
import com.hongyuan.fitness.base.Constants;
import com.hongyuan.fitness.base.ConstantsCode;
import com.hongyuan.fitness.base.Controller;
import com.hongyuan.fitness.base.CustomFragment;
import com.hongyuan.fitness.base.CustomViewModel;
import com.hongyuan.fitness.base.RetrofitListener;
import com.hongyuan.fitness.ui.shop.sactivity.SCartActivity;
import com.hongyuan.fitness.ui.shop.sactivity.SorderDetailsActivity;
import com.hongyuan.fitness.ui.shop.sadapter.SGDspecificationAdapter;
import com.hongyuan.fitness.ui.shop.sbeans.CartJsonBeans;
import com.hongyuan.fitness.ui.shop.sbeans.SgoodsDetailBeans;
import com.hongyuan.fitness.ui.shop.sbeans.SgoodselectSkunBeans;
import com.hongyuan.fitness.util.BaseUtil;
import com.hongyuan.fitness.util.CustomDialog;
import com.hongyuan.fitness.util.GsonUtil;
import com.makeramen.roundedimageview.RoundedImageView;

import net.lemonsoft.lemonbubble.LemonBubble;

import java.util.ArrayList;
import java.util.List;

public class SGDspecificationView extends LinearLayout implements RetrofitListener,SGDspecificationAdapter.ChangeData {

    private CustomFragment mFragment;
    private CustomViewModel model;

    private RecyclerView mRec;
    private  SGDspecificationAdapter adapter;

    private RoundedImageView goodImg;
    private TextView goodPrice,goodStock,changeNorm,addMark,goodPoint,pointText,numText,goBuy;
    private LinearLayout addCart,buyBox,exchangeBox,pointBox;

    //数据源
    private SgoodsDetailBeans.DataBean.InfoBean infoBean;
    //规格选择之后的数据源
    private SgoodselectSkunBeans.DataBean.ItemBean itemBean;

    //当前可购买的最大数量
    private int maxNum = 99999;

    //是否立即购买
    private boolean isBuyNow = false;

    //选中的sku
    List<SgoodsDetailBeans.DataBean.InfoBean.SkuBean> selectList = new ArrayList<>();

    public SGDspecificationView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initLayoutView();
    }

    public void initLayoutView() {
        View view = View.inflate(getContext(), R.layout.view_sgd_specification, this);

        mRec = view.findViewById(R.id.mRec);
        goodImg = view.findViewById(R.id.goodImg);
        goodPrice = view.findViewById(R.id.goodPrice);
        goodStock = view.findViewById(R.id.goodStock);
        changeNorm = view.findViewById(R.id.changeNorm);
        goBuy = view.findViewById(R.id.goBuy);
        addCart = view.findViewById(R.id.addCart);
        addMark = view.findViewById(R.id.addMark);
        goodPoint = view.findViewById(R.id.goodPoint);
        pointText = view.findViewById(R.id.pointText);
        buyBox = view.findViewById(R.id.buyBox);
        exchangeBox = view.findViewById(R.id.exchangeBox);
        pointBox = view.findViewById(R.id.pointBox);


        LinearLayoutManager comManager = new LinearLayoutManager(getContext());
        comManager.setOrientation(RecyclerView.VERTICAL);
        mRec.setLayoutManager(comManager);
        adapter = new SGDspecificationAdapter(this);
        mRec.setAdapter(adapter);

        adapter.setFooterView(getFooter());

        addCart.setOnClickListener(v -> {
            isBuyNow = false;
            addCart();
        });

        goBuy.setOnClickListener(v -> {
            isBuyNow = true;
            goDetails();
        });

        //直接去到订单确认页面
        exchangeBox.setOnClickListener(v -> {

            goDetails();

        });
    }

    private void goDetails(){
        String gp_id;
        if(infoBean.getSku() != null && infoBean.getSku().size() > 0){
            if(itemBean == null || itemBean.getGp_stock() <=0 ){
                CustomDialog.showMessage(getContext(),"库存不足！");
                return;
            }else{
                gp_id = String.valueOf(itemBean.getGp_id());
            }
        }else{
            if(infoBean.getGp() != null && infoBean.getGp().size() > 0 && infoBean.getGp().get(0).getGp_stock() > 0){
                gp_id = String.valueOf(infoBean.getGp().get(0).getGp_id());
            }else{
                CustomDialog.showMessage(getContext(),"库存不足！");
                return;
            }
        }

        if(Integer.valueOf(numText.getText().toString()) <= 0){
            CustomDialog.showMessage(getContext(),"购买数量不能少于1");
            return;
        }

        Bundle bundle = new Bundle();
        bundle.putString("cartJson",getJson(Integer.parseInt(gp_id)));
        if(model != null){
            model.startActivity(SorderDetailsActivity.class,bundle);
        }else if(mFragment != null){
            mFragment.startActivity(SorderDetailsActivity.class,bundle);
        }
    }

    private View getFooter(){
        //加载数量加减试图
        View footer = LayoutInflater.from(getContext())
                .inflate(R.layout.view_footer_add_num, (ViewGroup) mRec.getParent(), false);
        TextView addNum = footer.findViewById(R.id.addNum);
        numText = footer.findViewById(R.id.numText);
        TextView subNum = footer.findViewById(R.id.subNum);
        addNum.setOnClickListener(v -> {
            int showNum = Integer.valueOf(numText.getText().toString());
            showNum++;
            if(showNum >= maxNum){
                showNum = maxNum;
            }
            numText.setText(String.valueOf(showNum));
        });
        subNum.setOnClickListener(v -> {
            int showNum = Integer.valueOf(numText.getText().toString());
            if(showNum > 1){
                numText.setText(String.valueOf(showNum-1));
            }else{
                if(maxNum == 0){
                    numText.setText("0");
                }else{
                    numText.setText("1");
                }
            }
        });

        return footer;
    }

    /*
    * 积分兑换需要组装的json数据
    * */
    private String getJson(int gp_id){
        List<CartJsonBeans> jsonList = new ArrayList<>();
        CartJsonBeans jsonBeans = new CartJsonBeans();
        jsonBeans.setStore_id(infoBean.getStore_id());

        List<CartJsonBeans.GoodsListBean> goodsList = new ArrayList<>();
        CartJsonBeans.GoodsListBean goodsListBean = new CartJsonBeans.GoodsListBean();

        goodsListBean.setCart_id(0);
        goodsListBean.setBuy_num(Integer.parseInt(numText.getText().toString()));
        goodsListBean.setGp_id(gp_id);
        goodsList.add(goodsListBean);

        jsonBeans.setGoods_list(goodsList);
        jsonList.add(jsonBeans);

        return GsonUtil.toJsonStr(jsonList);

    }

    /*
    * 设置规格相关数据
    * */
    public void setDatas(SgoodsDetailBeans.DataBean.InfoBean infoBean,CustomFragment mFragment,CustomViewModel model){
        this.mFragment = mFragment;
        this.model = model;
        this.infoBean = infoBean;

        if(mFragment != null){
            exchangeBox.setVisibility(GONE);
            buyBox.setVisibility(VISIBLE);
        }else if(model != null){
            exchangeBox.setVisibility(VISIBLE);
            buyBox.setVisibility(GONE);
        }

        maxNum = infoBean.getG_stock();
        Glide.with(getContext()).load(infoBean.getG_img()).into(goodImg);
        goodPrice.setText(BaseUtil.getNoZoon(infoBean.getG_price()));
        goodStock.setText("库存"+infoBean.getG_stock());

        adapter.setNewData(infoBean.getSku());

        String noSelectStr = "请选择";
        //初始化选中项
        for(int i = 0 ; i < infoBean.getSku().size() ; i++){
            selectList.add(null);
            noSelectStr += " "+infoBean.getSku().get(i).getSku_type_name();
        }
        changeNorm.setText(noSelectStr);

        if(infoBean.getG_point() > 0){
            pointBox.setVisibility(VISIBLE);
            goodPoint.setText(String.valueOf(infoBean.getG_point()));
        }else{
            pointBox.setVisibility(GONE);
        }
    }

    /*
    * 规格选取后的回调
    * */
    @Override
    public void dataChange(List<SgoodsDetailBeans.DataBean.InfoBean.SkuBean> changeList, int partPosition, int childPosition) {
        //初始化一个选中对象
        SgoodsDetailBeans.DataBean.InfoBean.SkuBean selectBean = new SgoodsDetailBeans.DataBean.InfoBean.SkuBean();
        selectBean.setSku_type_id(changeList.get(partPosition).getSku_type_id());
        selectBean.setSku_type_name(changeList.get(partPosition).getSku_type_name());
        //选中的sku_name
        selectBean.setSelectChildName(changeList.get(partPosition).getItem().get(childPosition).getSku_name());
        selectList.set(partPosition,selectBean);

        String noSelectStr = "请选择";
        String isSelectStr = "已选择";
        for(int i = 0 ; i < selectList.size() ; i++){
            if(selectList.get(i) == null){
                noSelectStr += " "+changeList.get(i).getSku_type_name();
            }else{
                isSelectStr += " "+selectList.get(i).getSelectChildName();
            }
        }

        if(!noSelectStr.equals("请选择")){
            changeNorm.setText(noSelectStr);
        }else{
            changeNorm.setText(isSelectStr);
            //当所有的规格都选择了之后，去调取规格数据
            getSelectSkun(selectList);
        }
    }

    /*********************************************数据请求******************************************/

    /*
     * 请求获取全部规格选择后的数据
     * */
    private void getSelectSkun(List<SgoodsDetailBeans.DataBean.InfoBean.SkuBean> selectList){


        if(model != null){
            model.clearParams().setParams("g_id",String.valueOf(infoBean.getG_id()))
                    .setParams("third_category_id",String.valueOf(infoBean.getThird_category_id()));
        }else if(mFragment != null){
            mFragment.clearParams().setParams("g_id",String.valueOf(infoBean.getG_id()))
                    .setParams("third_category_id",String.valueOf(infoBean.getThird_category_id()));
        }

        for(SgoodsDetailBeans.DataBean.InfoBean.SkuBean skuBean: selectList){
            if(skuBean.getSelectChildName() != null){
                if(model != null){
                    model.setParams("sku_name_"+skuBean.getSku_type_id(),skuBean.getSelectChildName());
                }else if(mFragment != null){
                    mFragment.setParams("sku_name_"+skuBean.getSku_type_id(),skuBean.getSelectChildName());
                }

            }
        }
        Controller.myRequest(Constants.SELECT_SKU,Controller.TYPE_POST,mFragment != null ? mFragment.getParams() : model.getParams(), SgoodselectSkunBeans.class,this);
    }

    /*
    * 请求添加到购物车
    * */
    private void addCart(){
        String gp_id;
        if(infoBean.getSku() != null && infoBean.getSku().size() > 0){
            if(itemBean == null || itemBean.getGp_stock() <=0 ){
                CustomDialog.showMessage(getContext(),"库存不足！");
                return;
            }else{
                gp_id = String.valueOf(itemBean.getGp_id());
            }
        }else{
            if(infoBean.getGp() != null && infoBean.getGp().size() > 0 && infoBean.getGp().get(0).getGp_stock() > 0){
                gp_id = String.valueOf(infoBean.getGp().get(0).getGp_id());
            }else{
                CustomDialog.showMessage(getContext(),"库存不足！");
                return;
            }
        }

        if(Integer.valueOf(numText.getText().toString()) <= 0){
            CustomDialog.showMessage(getContext(),"购买数量不能少于1");
            return;
        }

        if(model != null){
            model.clearParams().setParams("gp_id",gp_id)
                    .setParams("buy_num",numText.getText().toString());
        }else if(mFragment != null){
            mFragment.clearParams().setParams("gp_id",gp_id)
                    .setParams("buy_num",numText.getText().toString());
        }


        Controller.myRequest(ConstantsCode.ADD_SHOPPING_CART,Constants.ADD_SHOPPING_CART,Controller.TYPE_POST,mFragment != null ? mFragment.getParams() : model.getParams(), BaseBean.class,this);
    }

    /**********************************************数据回调*****************************************/


    @Override
    public void onSuccess(Object data) {
        if(data instanceof SgoodselectSkunBeans){
            itemBean = ((SgoodselectSkunBeans)data).getData().getItem();
            //初始化可购买数量

            if(itemBean != null){
                maxNum = itemBean.getGp_stock();
                goodStock.setText("库存"+itemBean.getGp_stock());
                goodPrice.setText(BaseUtil.getNoZoon(itemBean.getGp_price()));

                if(infoBean.getG_point() > 0){
                    pointBox.setVisibility(VISIBLE);

                    goodPoint.setText(String.valueOf(infoBean.getG_point()));
                }else{
                    pointBox.setVisibility(GONE);
                }
            }else{
                maxNum = 0;
                goodStock.setText("库存"+0);

                pointBox.setVisibility(GONE);
            }
        }
    }

    @Override
    public void onSuccess(int code, Object data) {
        if(code == ConstantsCode.ADD_SHOPPING_CART){
            if(isBuyNow){
                if(mFragment != null){
                    mFragment.startActivity(SCartActivity.class,null);
                }else if(model != null){
                    model.startActivity(SCartActivity.class,null);
                }
            }else{
                if(mFragment != null){
                    mFragment.showSuccess("已加入购物车");
                }else if(model != null){
                    model.showSuccess("已加入购物车");
                }
            }

        }
    }

    @Override
    public void onError(int error_code, String description) {
        LemonBubble.showError(getContext(), description, 2000);
    }

    @Override
    public void closeRefresh() {

    }
}
