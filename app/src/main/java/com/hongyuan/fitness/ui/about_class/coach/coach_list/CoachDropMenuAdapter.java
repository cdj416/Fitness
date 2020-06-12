package com.hongyuan.fitness.ui.about_class.coach.coach_list;

import android.content.Context;
import android.view.View;
import android.widget.FrameLayout;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.Constants;
import com.hongyuan.fitness.base.Controller;
import com.hongyuan.fitness.base.RetrofitListener;
import com.hongyuan.fitness.custom_view.filter_view.adapter.DoubleListAdapter;
import com.hongyuan.fitness.custom_view.filter_view.adapter.MenuAdapter;
import com.hongyuan.fitness.custom_view.filter_view.adapter.SimpleTextAdapter;
import com.hongyuan.fitness.custom_view.filter_view.typeview.DoubleListView;
import com.hongyuan.fitness.custom_view.filter_view.util.CommonUtil;
import com.hongyuan.fitness.custom_view.filter_view.util.UIUtil;
import com.hongyuan.fitness.custom_view.filter_view.view.FilterCheckedTextView;
import com.hongyuan.fitness.ui.main.TokenSingleBean;
import com.hongyuan.fitness.ui.main.main_about_class.private_lessons.MenuPrivateLessonsBean;
import com.hongyuan.fitness.util.BaseUtil;
import com.hongyuan.fitness.util.EncryptionUtil;

import net.lemonsoft.lemonbubble.LemonBubble;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CoachDropMenuAdapter implements MenuAdapter, RetrofitListener {

    private final int POSITION_STORE = 0;
    private final int POSITION_TYPE = 1;

    private final Context mContext;
    private OnFilterDoneListener onFilterDoneListener;
    private OnFilterContentListener contentListener;
    private String[] titles;

    private TokenSingleBean userToken;

    //区县门店列表
    private DoubleListView<FilterAreaBeans.DataBean.ListBean.SonListBean, FilterStoreBeans.DataBean.ListBean> doubleFilter;
    //男女课程类型筛选
    private DoubleListView<FilterBGbeans, MenuPrivateLessonsBean.DataBean> typeFilter;

    public interface OnFilterDoneListener {
        void onFilterDone(String region_code, String filterCode, String os_ids,String ft_ids);
    }

    public interface OnFilterContentListener {
        void onFilterContent(int position, String changeText);
    }

    public CoachDropMenuAdapter(Context context, String[] titles, OnFilterDoneListener onFilterDoneListener,OnFilterContentListener contentListener){
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
                view = createDoubleListView();
                break;
            case 1:
                view = creatTypeListView();
                break;
        }
        return view;
    }

    private View createDoubleListView(){
        doubleFilter = new DoubleListView<FilterAreaBeans.DataBean.ListBean.SonListBean, FilterStoreBeans.DataBean.ListBean>(mContext)
                .leftAdapter(new SimpleTextAdapter<FilterAreaBeans.DataBean.ListBean.SonListBean>(null, mContext) {
                    @Override
                    public String provideText(FilterAreaBeans.DataBean.ListBean.SonListBean filterType) {
                        return filterType.getRegion_name();
                    }

                    @Override
                    protected void initCheckedTextView(FilterCheckedTextView checkedTextView) {
                        checkedTextView.setPadding(0, UIUtil.dp(mContext, 15), 0, UIUtil.dp(mContext, 15));
                    }
                })
                .rightAdapter(new DoubleListAdapter<FilterStoreBeans.DataBean.ListBean>(null, mContext) {
                    @Override
                    public String provideText(FilterStoreBeans.DataBean.ListBean filterRigthBeans) {
                        return filterRigthBeans.getOs_name();
                    }

                    @Override
                    public boolean isSelect(FilterStoreBeans.DataBean.ListBean filterRigthBeans) {
                        return filterRigthBeans.isSelect();
                    }
                })
                .onLeftItemClickListener((item, position) -> {
                    region_code = item.getRegion_code();
                    //去请求右边所显示的门店
                    getFilterStore();

                    String showStoreText;
                    if(position != 0){
                        showStoreText = item.getRegion_name();
                    }else{
                        showStoreText = "湖州全城";
                    }
                    //去更新显示类容
                    onFilterText(POSITION_STORE,showStoreText);

                }).onRightItemClickListener((item, childItem) -> {
                    if (BaseUtil.isValue(childItem)) {
                        if (childItem.isSelect()) {
                            childItem.setSelect(false);
                        } else {
                            childItem.setSelect(true);
                        }
                    }
                }).onRestClickListener((rightList, listView) -> {
                    if (!CommonUtil.isEmpty(rightList)) {
                        for (FilterStoreBeans.DataBean.ListBean beans : rightList) {
                            beans.setSelect(false);
                        }
                        listView.notifyDataChanged();
                    }
                }).onInquireClickListener(rightList -> {
                    if (!CommonUtil.isEmpty(rightList)) {
                        os_ids = "";
                        for (FilterStoreBeans.DataBean.ListBean beans : rightList) {
                            if (beans.isSelect()) {
                                if(!BaseUtil.isValue(os_ids)){
                                    os_ids += beans.getOs_id();
                                }else{
                                    os_ids += ","+beans.getOs_id();
                                }
                            }
                        }
                        onFilterDone(region_code,filterCode,os_ids,ft_ids);
                    }else{
                        LemonBubble.showError(mContext, "很抱歉，该地区没有相应的教练！", 2000);
                    }
                });

        doubleFilter.getLeftListView().setBackgroundColor(mContext.getResources().getColor(R.color.color_F5F6FB));
        //获取左边的区县数据
        getArea();

        return doubleFilter;
    }

    /*
    * 设置私教筛选
    * */
    private View creatTypeListView() {
        typeFilter = new DoubleListView<FilterBGbeans, MenuPrivateLessonsBean.DataBean>(mContext)
                .leftAdapter(new SimpleTextAdapter<FilterBGbeans>(null, mContext) {
                    @Override
                    public String provideText(FilterBGbeans filterType) {
                        return filterType.getFilterName();
                    }

                    @Override
                    protected void initCheckedTextView(FilterCheckedTextView checkedTextView) {
                        checkedTextView.setPadding(0, UIUtil.dp(mContext, 15), 0, UIUtil.dp(mContext, 15));
                    }
                })
                .rightAdapter(new DoubleListAdapter<MenuPrivateLessonsBean.DataBean>(null, mContext) {
                    @Override
                    public String provideText(MenuPrivateLessonsBean.DataBean filterRigthBeans) {
                        return filterRigthBeans.getFt_name();
                    }

                    @Override
                    public boolean isSelect(MenuPrivateLessonsBean.DataBean filterRigthBeans) {
                        return filterRigthBeans.isSelect();
                    }
                })
                .onLeftItemClickListener((item, position) -> {
                    filterCode = item.getFilterCode();
                    //去请求右边所显示的门店
                    //getFilterStore();
                    String showSexText;
                    if(position == 0){
                        showSexText = "全部";
                    }else{
                        showSexText = item.getFilterName();
                    }
                    //去更新显示类容
                    onFilterText(POSITION_TYPE,showSexText);
                }).onRightItemClickListener((item, childItem) -> {
                    if (BaseUtil.isValue(childItem)) {
                        if (childItem.isSelect()) {
                            childItem.setSelect(false);
                        } else {
                            childItem.setSelect(true);
                        }
                    }
                }).onRestClickListener((rightList, listView) -> {
                    if (!CommonUtil.isEmpty(rightList)) {
                        for (MenuPrivateLessonsBean.DataBean beans : rightList) {
                            beans.setSelect(false);
                        }
                        listView.notifyDataChanged();
                    }
                }).onInquireClickListener(rightList -> {
                    if (!CommonUtil.isEmpty(rightList)) {
                        ft_ids = "";
                        for (MenuPrivateLessonsBean.DataBean beans : rightList) {
                            if (beans.isSelect()) {
                                if(!BaseUtil.isValue(ft_ids)){
                                    ft_ids += beans.getFt_id();
                                }else{
                                    ft_ids += ","+beans.getFt_id();
                                }
                            }
                        }
                        onFilterDone(region_code,filterCode,os_ids,ft_ids);
                    }else{
                        LemonBubble.showError(mContext, "很抱歉，该地区没有相应的教练！", 2000);
                    }
                });

        List<FilterBGbeans> filterBGbeans = new ArrayList<>();

        FilterBGbeans bGbeans = new FilterBGbeans();
        bGbeans.setFilterCode("");
        bGbeans.setFilterName("全部");
        filterBGbeans.add(bGbeans);

        FilterBGbeans bGbeans1 = new FilterBGbeans();
        bGbeans1.setFilterCode("1");
        bGbeans1.setFilterName("男");
        filterBGbeans.add(bGbeans1);
        FilterBGbeans bGbeans2 = new FilterBGbeans();
        bGbeans2.setFilterCode("2");
        bGbeans2.setFilterName("女");
        filterBGbeans.add(bGbeans2);


        //设置左边固定数据
        typeFilter.setLeftList(filterBGbeans, 0);
        typeFilter.getLeftListView().setBackgroundColor(mContext.getResources().getColor(R.color.color_F5F6FB));
        //设置右边请求数据
        getCourseType();

        return typeFilter;
    }

    /*
    * 选中信息回调
    * */
    private void onFilterDone(String region_code,String filterCode,String os_ids,String ft_ids) {
        if (onFilterDoneListener != null) {
            onFilterDoneListener.onFilterDone(region_code, filterCode, os_ids,ft_ids);
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
    //区县编码
    private String region_code="";
    //性别筛选
    private String filterCode="";

    //分页需要的数据
    protected int curPage = 1;//当前页数
    private int pageSize = 10;//一页条目数
    private boolean isLoadMore = false;//是否开启了分页功能
    protected final int FIRST_PAGE = 1;//表示第一页

    //已下是所选参数的回调
    private String os_ids = "";//多个或者单个门店id；
    private String ft_ids = "";//多个或单个私教类型

    /*
     * 组装参数
     * */
    public CoachDropMenuAdapter setParams(String key, String value){
        if(params == null){
            params = new HashMap<>();
        }
        params.put(key,value);
        return this;
    }

    /*
     * 清空参数集
     * */
    public CoachDropMenuAdapter clearParams(){
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
            //int randomNum = 100;
            //long timeSpan = 1561619095;


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
    * 请求区县
    * */
    private void getArea(){
        //根据城市名称获取下面的区县列表
        clearParams().setParams("city_name",userToken.getRegion_name());
        Controller.myRequest(Constants.GET_OS_ADDRESS_LIST,Controller.TYPE_POST,getParams(), FilterAreaBeans.class,this);
    }

    /*
     * 根据区县code 获取门店列表
     * */
    private void getFilterStore(){
        clearParams().setParams("region_code",region_code).setParams("page","10").setParams("curpage","1");
        Controller.myRequest(Constants.GET_OS_WHERE_LIST,Controller.TYPE_POST,getParams(), FilterStoreBeans.class,this);
    }

    /*
    * 请求私教类型
    * */
    private void getCourseType(){
        //加载私教课类型
        clearParams();
        Controller.myRequest(Constants.GET_FIT_TYPE_LIST_ALL,Controller.TYPE_POST,getParams(), MenuPrivateLessonsBean.class,this);
    }

    @Override
    public void onSuccess(Object data) {
        if(data instanceof FilterAreaBeans){
            FilterAreaBeans filterAreaBeans = (FilterAreaBeans)data;
            region_code = filterAreaBeans.getData().getList().getFather_region_code();

            //初始化选中.
            FilterAreaBeans.DataBean.ListBean.SonListBean sonListBean = new FilterAreaBeans.DataBean.ListBean.SonListBean();
            sonListBean.setRegion_code(filterAreaBeans.getData().getList().getFather_region_code());
            sonListBean.setRegion_name(filterAreaBeans.getData().getList().getFather_region_name());
            filterAreaBeans.getData().getList().getSon_list().add(0,sonListBean);

            doubleFilter.setLeftList(filterAreaBeans.getData().getList().getSon_list(), 0);
            getFilterStore();
        }

        if(data instanceof FilterStoreBeans){
            FilterStoreBeans filterStoreBeans = (FilterStoreBeans)data;
            //设置左边的筛选项
            doubleFilter.setRightList(filterStoreBeans.getData().getList(), -1);
        }

        if(data instanceof MenuPrivateLessonsBean){
            MenuPrivateLessonsBean privateLessonsBean = (MenuPrivateLessonsBean)data;
            typeFilter.setRightList(privateLessonsBean.getData(),-1);
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
