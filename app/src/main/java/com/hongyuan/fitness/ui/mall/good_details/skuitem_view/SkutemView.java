package com.hongyuan.fitness.ui.mall.good_details.skuitem_view;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.custom_view.SubAddNumView;
import com.hongyuan.fitness.ui.mall.good_details.GoodDetailsBean;
import com.hongyuan.fitness.ui.mall.good_order_details.CreateOrderDetailsBean;
import com.hongyuan.fitness.ui.mall.good_order_details.GoodOrderDetailsActivity;
import com.hongyuan.fitness.util.BaseUtil;
import com.hongyuan.fitness.util.DividerItemDecoration;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.ArrayList;
import java.util.List;

public class SkutemView extends RelativeLayout implements View.OnClickListener, SkuAdapter.ChangeData {

    private Dialog skuDialog;

    //dialog里面的控件
    private TextView goodSku,titleName,goodPrice,goodStock,goodNum,priceMark,addMark,pointText,goodPoint;
    private ImageView closeImg,subNum,addNum;
    private RoundedImageView goodImg;
    private RecyclerView skuRec;
    private Button submit;
    private SubAddNumView subAddView;

    //数据
    private GoodDetailsBean.DataBean.InfoBean dataBeans;
    //选中的sku
    List<GoodDetailsBean.DataBean.InfoBean.SkuBean> selectList = new ArrayList<>();

    //规格未选完时的提示
    private String errText = "请选择";

    //规格选择完成后返回的gp_id
    private String gp_id;
    //选择取货门店之后返回的op_quhuo_osid（提货门店id）
    private String op_quhuo_osid;
    //取货门店名字
    private String quhuoStoreName;
    //选取的规格字符串
    private String norm = "";
    //规格里面的库存
    private int skustock = 0;

    //规格选择的回调
    public interface ClickSku{
        void getClickData(List<GoodDetailsBean.DataBean.InfoBean.SkuBean> selectList);
    }
    private ClickSku clickSku;

    private SubAddNumView.NumChange numChange;

    public SkutemView(Context context) {
        super(context);
        initLayoutView();
    }

    public SkutemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initLayoutView();
    }

    public SkutemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initLayoutView();
    }

    public void initLayoutView(){
        View view = View.inflate(getContext(), R.layout.view_goods_sku, this);
        goodSku = view.findViewById(R.id.goodSku);
        subAddView = view.findViewById(R.id.subAddView);
        goodSku.setOnClickListener(this);
    }

    /*
    * 规格弹框
    * */
    private void showDialog(GoodDetailsBean.DataBean.InfoBean dataBeans){
        if(skuDialog == null){
            skuDialog = new Dialog(getContext(), R.style.DialogTheme);
            View view = View.inflate(getContext(), R.layout.dialog_select_sku,null);
            skuDialog.setContentView(view);
            Window window = skuDialog.getWindow();
            window.setGravity(Gravity.BOTTOM);
            window.setWindowAnimations(R.style.bottom_in_out);
            window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);

            titleName = view.findViewById(R.id.titleName);
            goodPrice = view.findViewById(R.id.goodPrice);
            goodStock = view.findViewById(R.id.goodStock);
            closeImg = view.findViewById(R.id.closeImg);
            goodImg = view.findViewById(R.id.goodImg);
            skuRec = view.findViewById(R.id.skuRec);
            submit = view.findViewById(R.id.submit);
            priceMark = view.findViewById(R.id.priceMark);
            addMark = view.findViewById(R.id.addMark);
            pointText = view.findViewById(R.id.pointText);
            goodPoint = view.findViewById(R.id.goodPoint);

            //关闭弹框
            closeImg.setOnClickListener(v -> skuDialog.dismiss());

            //设置规格
            LinearLayoutManager manager = new LinearLayoutManager(getContext());
            manager.setOrientation(RecyclerView.VERTICAL);
            skuRec.addItemDecoration(new DividerItemDecoration(
                    getContext(), DividerItemDecoration.HORIZONTAL_LIST,1,0xEEEEEEEE));
            skuRec.setLayoutManager(manager);
            SkuAdapter skuAdapter = new SkuAdapter(this);
            skuRec.setAdapter(skuAdapter);
            skuAdapter.addFooterView(getFooter(skuRec));

            RequestOptions options = new RequestOptions().placeholder(R.mipmap.zhengfangxing_default_img).error(R.mipmap.zhengfangxing_default_img);
            Glide.with(getContext()).load(dataBeans.getG_img()).apply(options).into(goodImg);

            if(dataBeans.getSku() != null && dataBeans.getSku().size() > 0){
                skuAdapter.setNewData(dataBeans.getSku());
                titleName.setText(getTitleText());
            }
            //去购买
            submit.setOnClickListener(v -> {
                if(getCensor()){
                    Bundle bundle = new Bundle();
                    //传递需要请求的参数集
                    bundle.putSerializable("paramsBean",getParamsBean());
                    //传递需要显示的参数集
                    bundle.putSerializable("InfoBean",dataBeans);
                    ((CustomActivity)getContext()).startActivity(GoodOrderDetailsActivity.class,bundle);
                }
            });
            //初始化数量显示为1
            showText();
            //设置显示数据
            setShowData(dataBeans.getG_stock(),dataBeans.getG_price(),dataBeans.getG_point());

            //默认不可点击
            if(dataBeans.getSku() != null && dataBeans.getSku().size() > 0){
                setBuy(false);
            }else{
                setBuy(true);
            }
        }

        skuDialog.show();
    }

    /*
    * 数量加减
    * */
    private View getFooter(RecyclerView v){
        View convertView = LayoutInflater
                .from(getContext())
                .inflate(R.layout.item_sku_footer, (ViewGroup) v.getParent(), false);
        subNum = convertView.findViewById(R.id.subNum);
        addNum = convertView.findViewById(R.id.addNum);
        goodNum = convertView.findViewById(R.id.goodNum);

        subNum.setOnClickListener(v1 -> {
            int num = Integer.valueOf(goodNum.getText().toString());
            num--;
            if(num < 1){
                num = 1;
            }
            goodNum.setText(String.valueOf(num));
            //改变规格显示内容
            showText();
            //更改activity中总价的显示
            if(numChange != null){
                numChange.changeNum(goodNum.getText().toString());
            }
        });
        addNum.setOnClickListener(v1 -> {
            int num = Integer.valueOf(goodNum.getText().toString());
            num++;
            if(num > dataBeans.getG_stock()){
                num = dataBeans.getG_stock();
            }
            goodNum.setText(String.valueOf(num));
            //改变规格显示内容
            showText();
            //更改activity中总价的显示
            if(numChange != null){
                numChange.changeNum(goodNum.getText().toString());
            }
        });
        return convertView;
    }

    /*
    * 设置数据
    * */
    public void setShowData(int stock,String price,int point){
        this.skustock = stock;
        if(stock > 0){
            goodStock.setText("库存"+stock+"件");
            //可购买
            setBuy(true);
        }else{
            goodStock.setText("库存不足");
            //不可购买
            setBuy(false);
        }

        if(Double.valueOf(price) > 0 && point <= 0){
            priceMark.setVisibility(VISIBLE);
            goodPrice.setVisibility(VISIBLE);
            addMark.setVisibility(GONE);
            goodPoint.setVisibility(GONE);
            pointText.setVisibility(GONE);
            goodPrice.setText(BaseUtil.getNoZoon(price));
        }
        if(Double.valueOf(price) > 0 && point > 0){
            priceMark.setVisibility(VISIBLE);
            goodPrice.setVisibility(VISIBLE);
            addMark.setVisibility(VISIBLE);
            goodPoint.setVisibility(VISIBLE);
            pointText.setVisibility(VISIBLE);
            goodPrice.setText(BaseUtil.getNoZoon(price));
            goodPoint.setText(BaseUtil.getNoZoon(point));
        }
        if(Double.valueOf(price) <= 0 && point > 0){
            priceMark.setVisibility(GONE);
            goodPrice.setVisibility(GONE);
            addMark.setVisibility(GONE);
            goodPoint.setVisibility(VISIBLE);
            pointText.setVisibility(VISIBLE);
            goodPoint.setText(BaseUtil.getNoZoon(point));
        }
    }

    /*
    * 设置按钮是否可购买
    * */
    private void setBuy(boolean flag){
        if(flag){
            submit.setBackgroundResource(R.drawable.shape_gradient_v_radiu5_login);
            submit.setClickable(true);
        }else{
            submit.setBackgroundResource(R.drawable.shape_radius5_cccccc);
            submit.setClickable(false);
        }
    }

    /*
    * 初始化数据
    * */
    public void setSkuData(GoodDetailsBean.DataBean.InfoBean dataBeans, ClickSku clickSku, SubAddNumView.NumChange numChange){
        this.dataBeans = dataBeans;
        this.clickSku = clickSku;
        this.numChange = numChange;

        if(dataBeans.getSku() == null ||dataBeans.getSku().size() == 0) {
            goodSku.setVisibility(GONE);
            subAddView.setVisibility(VISIBLE);
            if(dataBeans.getG_stock() > 0){
                subAddView.setMiMaNum(1,dataBeans.getG_stock(),numChange);
            }else{
                subAddView.setMiMaNum(0,0,numChange);
            }

        }else{
            goodSku.setVisibility(VISIBLE);
            subAddView.setVisibility(GONE);
        }
        //初始化选中项
        for(int i = 0 ; i < dataBeans.getSku().size() ; i++){
            selectList.add(null);
        }
    }

    /*
    * 获取弹框标题
    * */
    private String getTitleText(){
        String showTitle = "请选择";
        for (GoodDetailsBean.DataBean.InfoBean.SkuBean skuBean : dataBeans.getSku()){
            showTitle += ","+skuBean.getSku_type_name();
        }
        return showTitle;
    }

    @Override
    public void onClick(View v) {
        showDialog(dataBeans);
    }

    /*
    * sku点击事件的回调(组装选中的sku)
    * */
    @Override
    public void dataChange(List<GoodDetailsBean.DataBean.InfoBean.SkuBean> changeList,int partPosition,int childPosition) {
        //初始化一个选中对象
        GoodDetailsBean.DataBean.InfoBean.SkuBean selectBean = new GoodDetailsBean.DataBean.InfoBean.SkuBean();
        selectBean.setSku_type_id(changeList.get(partPosition).getSku_type_id());
        selectBean.setSku_type_name(changeList.get(partPosition).getSku_type_name());
        //选中的sku_name
        selectBean.setSelectChildName(changeList.get(partPosition).getItem().get(childPosition).getSku_name());
        selectList.set(partPosition,selectBean);


        String noSelectStr = "请选择";
        String isSelectStr = "已选择";
        for(int i = 0 ; i < selectList.size() ; i++){
            if(selectList.get(i) == null){
                noSelectStr += ","+changeList.get(i).getSku_type_name();
            }else{
                isSelectStr += ","+selectList.get(i).getSku_type_name();
            }
        }

        if(!noSelectStr.equals("请选择")){
            titleName.setText(noSelectStr);
        }else{
            titleName.setText(isSelectStr);
            //当所有的规格都选择了之后，去调取规格数据
            clickSku.getClickData(selectList);
        }

        //改变规格显示内容
        showText();
    }

    /*
    * 判断规格是否已选择
    * */
    public boolean getCensor(){
        errText = "请选择";
        if(selectList.size() <= 0){
            return true;
        }
        for(int i = 0 ; i < selectList.size() ; i++){
            if(selectList.get(i) == null){
                errText += ","+dataBeans.getSku().get(i).getSku_type_name();
            }
            if(i == (selectList.size()-1) && errText.length() <= 3){
                return true;
            }
        }
        return false;
    }

    /*
    * 判断是否有规格
    * */
    public boolean isHaveGuiGe(){
        if(selectList.size() > 0){
            return true;
        }
        return false;
    }

    /*
    * 是否可用支付
    * */
    public boolean isHaveStock(){
        if(isHaveGuiGe()){
            if(skustock > 0){
                return true;
            }
        }else{
            if(dataBeans.getG_stock() > 0){
                return true;
            }
        }
        return false;
    }

    /*
    * 规格显示的变动
    * */
    private void showText(){
        //组装显示内容
        String showText = "";
        String norm = "";
        for(int i = 0 ; i < selectList.size() ; i++){
            if(selectList.get(i) != null){
                showText += selectList.get(i).getSku_type_name()+":"
                        +selectList.get(i).getSelectChildName()+" ";
            }
        }
        showText = showText+"数量:"+goodNum.getText().toString();
        norm = showText;
        goodSku.setText(showText);
    }

    /*
    * 设置gp_id
    * */
    public void setGp_id(String gp_id){
        this.gp_id = gp_id;
    }

    /*
    * 设置提货门店id
    * */
    public void setOp_quhuo_osid(String op_quhuo_osid){
        this.op_quhuo_osid = op_quhuo_osid;
    }
    /*
    * 设置提货名店名
    * */
    public void setQuhuoStoreName(String storeName){
        this.quhuoStoreName = storeName;
    }

    /*
    * 获取检索规格的选取结果
    * */
    public String getErrText(){
        return this.errText;
    }

    /*
    * 获取下个页面请求需要的参数实体类
    * */
    public CreateOrderDetailsBean getParamsBean(){
        CreateOrderDetailsBean paramsBean = new CreateOrderDetailsBean();
        paramsBean.setStore_id(String.valueOf(dataBeans.getStore_id()));
        paramsBean.setNorm(norm);
        paramsBean.setOp_quhuo_osid(op_quhuo_osid);
        paramsBean.setQuhuostoreName(quhuoStoreName);
        if(!isHaveGuiGe()){
            //没有规格
            paramsBean.setOp_num(String.valueOf(subAddView.getSelectNum()));
        }else{
            //有规格
            if(goodNum == null){
                paramsBean.setOp_num("1");
            }else{
                paramsBean.setOp_num(goodNum.getText().toString());
            }
        }
        if(selectList.size() > 0){
            paramsBean.setGp_id(gp_id);
        }else{
            paramsBean.setGp_id(String.valueOf(dataBeans.getGp().get(0).getGp_id()));
        }

        return paramsBean;
    }

}
