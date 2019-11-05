package com.hongyuan.fitness.ui.membership_card.v4_mycard_list;

import android.content.Context;
import android.view.View;
import android.widget.FrameLayout;
import com.hongyuan.fitness.base.Constants;
import com.hongyuan.fitness.base.Controller;
import com.hongyuan.fitness.base.RetrofitListener;
import com.hongyuan.fitness.custom_view.filter_view.adapter.MenuAdapter;
import com.hongyuan.fitness.custom_view.filter_view.adapter.SingleReclerAdapter;
import com.hongyuan.fitness.custom_view.filter_view.typeview.SingleReclerView;
import com.hongyuan.fitness.custom_view.filter_view.util.UIUtil;
import com.hongyuan.fitness.ui.main.TokenSingleBean;
import com.hongyuan.fitness.ui.membership_card.v4_mycard_list.filter.V4CardsListTypeBeans;
import com.hongyuan.fitness.ui.membership_card.v4_mycard_list.filter.V4CardsListValidityPeriodBeans;
import com.hongyuan.fitness.util.EncryptionUtil;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class V4FilterCardsListAdapter implements MenuAdapter, RetrofitListener {

    private final int POSITION_CARD_YEAR = 0;
    private final int POSITION_CARD_TYPE = 1;

    private final Context mContext;
    private OnFilterDoneListener onFilterDoneListener;
    private OnFilterContentListener contentListener;
    private String[] titles;

    private TokenSingleBean userToken;

    //有效期数据
    private SingleReclerView<V4CardsListValidityPeriodBeans.DataBean> validityPeriodListView;
    private List<V4CardsListValidityPeriodBeans.DataBean> validityList;

    //卡类型数据
    private  SingleReclerView<V4CardsListTypeBeans.DataBean> typeListView;
    private List<V4CardsListTypeBeans.DataBean> typeList;

    public interface OnFilterDoneListener {
        void onFilterDone(String cc_id,String ct_id);
    }

    public interface OnFilterContentListener {
        void onFilterContent(int position, String changeText);
    }


    public V4FilterCardsListAdapter(Context context, String[] titles, OnFilterDoneListener onFilterDoneListener, OnFilterContentListener contentListener){
        this.mContext = context;
        this.titles = titles;
        this.onFilterDoneListener = onFilterDoneListener;
        this.contentListener = contentListener;

        userToken = TokenSingleBean.getInstance();
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
                view = createValidityPeriodView();
                break;
            case 1:
                view = createCardsTypeListView();
                break;
        }
        return view;
    }

    /*
    * 设置单例的视图
    * */
    private View createValidityPeriodView() {
        validityPeriodListView = new SingleReclerView<V4CardsListValidityPeriodBeans.DataBean>(mContext)
                .setMyAdapter(new SingleReclerAdapter<V4CardsListValidityPeriodBeans.DataBean>() {
                    @Override
                    public String provideText(V4CardsListValidityPeriodBeans.DataBean t) {
                        return t.getCt_name();
                    }

                    @Override
                    public boolean isSelect(V4CardsListValidityPeriodBeans.DataBean t) {
                        if(t.isSelect()){
                            return true;
                        }else{
                            return false;
                        }
                    }
                }).setItemClickListener((position,adapter) -> {
                    for(V4CardsListValidityPeriodBeans.DataBean beans : validityList){
                        beans.setSelect(false);
                    }
                    validityList.get(position).setSelect(true);
                    adapter.setNewData(validityList);

                    selectText = validityList.get(position).getCt_name();
                    ct_id = String.valueOf(validityList.get(position).getCt_id());
                    //去更新数据
                    onFilterDone(ct_id,cc_id);

                    //去更新显示类容
                    onFilterText(POSITION_CARD_YEAR,selectText);
                });

        //获取卡年限
        getCardYears();

        return validityPeriodListView;
    }

    /*
    * 设置有效期筛选条件视图
    * */
    private View createCardsTypeListView(){
        typeListView  = new SingleReclerView<V4CardsListTypeBeans.DataBean>(mContext)
                .setMyAdapter(new SingleReclerAdapter<V4CardsListTypeBeans.DataBean>() {
                    @Override
                    public String provideText(V4CardsListTypeBeans.DataBean t) {
                        return t.getCc_name();
                    }

                    @Override
                    public boolean isSelect(V4CardsListTypeBeans.DataBean t) {
                        if(t.isSelect()){
                            return true;
                        }else{
                            return false;
                        }
                    }
                }).setItemClickListener((position,adapter) -> {
                    for(V4CardsListTypeBeans.DataBean beans : typeList){
                        beans.setSelect(false);
                    }
                    typeList.get(position).setSelect(true);
                    adapter.setNewData(typeList);

                    selectText = typeList.get(position).getCc_name();
                    cc_id = String.valueOf(typeList.get(position).getCc_id());
                    //去更新数据
                    onFilterDone(ct_id,cc_id);

                    //去更新显示类容
                    onFilterText(POSITION_CARD_TYPE,selectText);
                });


        //获取卡类型
        getCardType();

        return typeListView;
    }

    /*
    * 选中信息回调
    * */
    private void onFilterDone(String ct_id,String cc_id) {
        if (onFilterDoneListener != null) {
            onFilterDoneListener.onFilterDone(cc_id,ct_id);
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


    /***********************************************已下去请求获取数据*********************************/

    private Map<String,String> params;

    //分页需要的数据
    protected int curPage = 1;//当前页数
    private int pageSize = 10;//一页条目数
    private boolean isLoadMore = false;//是否开启了分页功能
    protected final int FIRST_PAGE = 1;//表示第一页

    //已下是所选参数的回调
    private String selectText = "";//多个或者单个门店id；

    //年限id
    private String ct_id = "";
    //类型id
    private String cc_id = "";

    /*
     * 组装参数
     * */
    public V4FilterCardsListAdapter setParams(String key, String value){
        if(params == null){
            params = new HashMap<>();
        }
        params.put(key,value);
        return this;
    }

    /*
     * 清空参数集
     * */
    public V4FilterCardsListAdapter clearParams(){
        if(params != null){
            params.clear();
        }
        return this;
    }

    /*
     * 获取参数集合
     * */
    public Map<String,String> getParams(){
        if(getBaseParams() != null){
            if(params == null){
                params = new HashMap<>();
            }
            params.putAll(getBaseParams());
        }
        return this.params;
    }

    /*
     * 组装基础请求参数
     * */
    private Map<String,String> getBaseParams(){
        if(userToken != null && userToken.getToken() != null){
            int randomNum = (int)(Math.random()*100);
            long timeSpan = System.currentTimeMillis();

            StringBuilder ntoken = new StringBuilder();
            ntoken.append(EncryptionUtil.md5Decode(userToken.getToken()));
            ntoken.append(EncryptionUtil.md5DecodeTwo(String.valueOf(randomNum)));
            ntoken.append(EncryptionUtil.md5DecodeTwo(String.valueOf(timeSpan)));

            Map<String,String> baseParams = new HashMap<>();
            baseParams.put("client","android");
            baseParams.put("token",userToken.getToken());
            baseParams.put("at_id",String.valueOf(userToken.getAt_id()));
            baseParams.put("randomnum",String.valueOf(randomNum));
            baseParams.put("timespan",String.valueOf(timeSpan));
            baseParams.put("ntoken",ntoken.toString());

            //是否开启分页
            if(isLoadMore){
                baseParams.put("curpage",String.valueOf(curPage));
                baseParams.put("page",String.valueOf(pageSize));
            }

            if(userToken.getM_id() != null){
                baseParams.put("m_id",userToken.getM_id());
                baseParams.put("m_mobile",userToken.getM_mobile());
            }

            return baseParams;
        }
        return null;
    }

    /*
     * 请求卡年限
     * */
    private void getCardYears(){
        //获取卡年限
        clearParams();
        Controller.myRequest(Constants.GET_CARD_TYPE_LIST,Controller.TYPE_POST,getParams(), V4CardsListValidityPeriodBeans.class,this);
    }

    /*
     * 请求卡类型
     * */
    private void getCardType(){
        //获取卡年限
        clearParams();
        Controller.myRequest(Constants.GET_CARD_CATEGORY_LIST,Controller.TYPE_POST,getParams(), V4CardsListTypeBeans.class,this);
    }

    @Override
    public void onSuccess(Object data) {
        if(data instanceof V4CardsListValidityPeriodBeans){
            validityList = ((V4CardsListValidityPeriodBeans)data).getData();
            validityList.add(0,new V4CardsListValidityPeriodBeans.DataBean(0,"全部",true));
            validityPeriodListView.setList(validityList);
        }

        if(data instanceof V4CardsListTypeBeans){
            typeList = ((V4CardsListTypeBeans)data).getData();
            typeList.add(0,new V4CardsListTypeBeans.DataBean(0,"全部","",0,0,"",true));
            typeListView.setList(typeList);
        }
    }

    @Override
    public void onSuccess(int code, Object data) {

    }

    @Override
    public void onError(int error_code, String description) {

    }

    @Override
    public void closeRefresh() {

    }
}
