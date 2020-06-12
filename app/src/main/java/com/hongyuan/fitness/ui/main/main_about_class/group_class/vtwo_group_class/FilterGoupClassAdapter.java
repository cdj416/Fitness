package com.hongyuan.fitness.ui.main.main_about_class.group_class.vtwo_group_class;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.Constants;
import com.hongyuan.fitness.base.Controller;
import com.hongyuan.fitness.base.RetrofitListener;
import com.hongyuan.fitness.custom_view.filter_view.adapter.DoubleListAdapter;
import com.hongyuan.fitness.custom_view.filter_view.adapter.MenuAdapter;
import com.hongyuan.fitness.custom_view.filter_view.adapter.SimpleTextAdapter;
import com.hongyuan.fitness.custom_view.filter_view.adapter.SingleReclerAdapter;
import com.hongyuan.fitness.custom_view.filter_view.typeview.DoubleListView;
import com.hongyuan.fitness.custom_view.filter_view.typeview.SingleReclerView;
import com.hongyuan.fitness.custom_view.filter_view.util.CommonUtil;
import com.hongyuan.fitness.custom_view.filter_view.util.UIUtil;
import com.hongyuan.fitness.custom_view.filter_view.view.FilterCheckedTextView;
import com.hongyuan.fitness.ui.about_class.coach.coach_list.FilterAreaBeans;
import com.hongyuan.fitness.ui.about_class.coach.coach_list.FilterStoreBeans;
import com.hongyuan.fitness.ui.main.TokenSingleBean;
import com.hongyuan.fitness.util.BaseUtil;
import com.hongyuan.fitness.util.EncryptionUtil;

import net.lemonsoft.lemonbubble.LemonBubble;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FilterGoupClassAdapter implements MenuAdapter, RetrofitListener {

    private final int POSITION_STORE = 0;
    private final int POSITION_TYPE = 1;
    private final int POSITION_TIME = 2;

    private final Context mContext;
    private OnFilterDoneListener onFilterDoneListener;
    private OnFilterContentListener contentListener;
    private String[] titles;

    private TokenSingleBean userToken;

    //区县门店列表
    private DoubleListView<FilterAreaBeans.DataBean.ListBean.SonListBean, FilterStoreBeans.DataBean.ListBean> doubleFilter;
    //私教课类型
    private SingleReclerView<FilterGoupClassTypeBeans.DataBean> singleListView;

    public interface OnFilterDoneListener {
        void onFilterDone(String si_ids, String os_ids, String start_date,String end_date);
    }

    public interface OnFilterContentListener {
        void onFilterContent(int position, String changeText);
    }


    public FilterGoupClassAdapter(Context context, String[] titles, OnFilterDoneListener onFilterDoneListener,OnFilterContentListener contentListener){
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
                view = createSingleTypeListView();
                break;
            case 2:
                view = createOpenTimeView();
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
                        showStoreText = userToken.getRegion_name()+"全城";
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

                        onFilterDone(si_ids,os_ids,start_date,end_date);
                    }else{
                        LemonBubble.showError(mContext, "很抱歉，该地区没有相应的课程！", 2000);
                    }
                });

        doubleFilter.getLeftListView().setBackgroundColor(mContext.getResources().getColor(R.color.color_F5F6FB));
        //获取左边的区县数据
        getArea();

        return doubleFilter;
    }

    /*
    * 设置单例的视图
    * */
    private View createSingleTypeListView() {
        singleListView = new SingleReclerView<FilterGoupClassTypeBeans.DataBean>(mContext)
                .setMyAdapter(new SingleReclerAdapter<FilterGoupClassTypeBeans.DataBean>() {
                    @Override
                    public String provideText(FilterGoupClassTypeBeans.DataBean t) {
                        return t.getSi_name();
                    }

                    @Override
                    public boolean isSelect(FilterGoupClassTypeBeans.DataBean t) {
                        if(t.isSelect()){
                            return true;
                        }else{
                            return false;
                        }
                    }
                }).setItemClickListener((position,adapter) -> {
                    String showTypeText;
                    if(!CommonUtil.isEmpty(goupClassTypeBeans.getData())){
                        for(FilterGoupClassTypeBeans.DataBean beans : goupClassTypeBeans.getData()){
                            beans.setSelect(false);
                        }
                        goupClassTypeBeans.getData().get(position).setSelect(true);
                        adapter.setNewData(goupClassTypeBeans.getData());
                        if(position == 0){
                            si_ids = "";
                            showTypeText = "全部";
                        }else{
                            si_ids = String.valueOf(goupClassTypeBeans.getData().get(position).getSi_id());
                            showTypeText = goupClassTypeBeans.getData().get(position).getSi_name();
                        }
                        //去更新数据
                        onFilterDone(si_ids,os_ids,start_date,end_date);

                        //去更新显示类容
                        onFilterText(POSITION_TYPE,showTypeText);
                    }

                });

        //获取课程类型
        getCourseType();
        return singleListView;
    }
    /*
    * 设置单例的视图
    * */
    private View createOpenTimeView() {
        List<FilterGoupClassTime> mList = getTimeData();
        SingleReclerView<FilterGoupClassTime> singleTimeListView = new SingleReclerView<FilterGoupClassTime>(mContext)
                .setMyAdapter(new SingleReclerAdapter<FilterGoupClassTime>() {
                    @Override
                    public String provideText(FilterGoupClassTime t) {
                        if(t.getStartTime().equals("全天")){
                            return t.getStartTime();
                        }
                        return t.getStartTime()+" - "+t.getEndTime();
                    }

                    @Override
                    public boolean isSelect(FilterGoupClassTime t) {
                        if(t.isSelect()){
                            return true;
                        }else{
                            return false;
                        }
                    }
                }).setItemClickListener((position,adapter) -> {
                    String showTiemText;
                    for(FilterGoupClassTime beans : mList){
                        beans.setSelect(false);
                    }
                    mList.get(position).setSelect(true);
                    adapter.setNewData(mList);

                    if(position == 0){
                        start_date = "";
                        end_date = "";
                        showTiemText = "全天";
                    }else{
                        start_date = mList.get(position).getStartTime();
                        end_date = mList.get(position).getEndTime();
                        showTiemText = start_date+"-"+end_date;
                    }
                    //去更新数据
                    onFilterDone(si_ids,os_ids,start_date,end_date);

                    //去更新显示类容
                    onFilterText(POSITION_TIME,showTiemText);
                });

        singleTimeListView.setList(mList);

        return singleTimeListView;
    }

    /*
    * 组装时间数据
    * */
    private List<FilterGoupClassTime> getTimeData(){
        List<FilterGoupClassTime> list = new ArrayList<>();

        list.add(new FilterGoupClassTime("全天","全天",true));
        list.add(new FilterGoupClassTime("9:00","12:00",false));
        list.add(new FilterGoupClassTime("12:00","15:00",false));
        list.add(new FilterGoupClassTime("15:00","18:00",false));
        list.add(new FilterGoupClassTime("18:00","22:00",false));

        return list;
    }

    /*
    * 选中信息回调
    * */
    private void onFilterDone(String si_ids,String os_ids,String start_date,String end_date) {
        if (onFilterDoneListener != null) {
            onFilterDoneListener.onFilterDone(si_ids, os_ids, start_date,end_date);
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

    /*
     * 改变标题数据显示
     * */
    public void changTitles(String[] titles){
        this.titles = titles;
        //从新获取左边区县数据
        getArea();
        //从新获取私教类型
        getCourseType();

        onFilterText(0,userToken.getRegion_name()+"全城");
    }


    /***********************************************已下去请求获取数据*********************************/

    private Map<String,String> params;
    //区县编码
    private String region_code="";

    //分页需要的数据
    protected int curPage = 1;//当前页数
    private int pageSize = 10;//一页条目数
    private boolean isLoadMore = false;//是否开启了分页功能
    protected final int FIRST_PAGE = 1;//表示第一页

    //已下是所选参数的回调
    private String os_ids = "";//多个或者单个门店id；
    private String si_ids = "";//多个或单个私教类型
    private String start_date = "";
    private String end_date = "";

    private FilterGoupClassTypeBeans goupClassTypeBeans;

    /*
     * 组装参数
     * */
    public FilterGoupClassAdapter setParams(String key, String value){
        if(params == null){
            params = new HashMap<>();
        }
        params.put(key,value);
        return this;
    }

    /*
     * 清空参数集
     * */
    public FilterGoupClassAdapter clearParams(){
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
            baseParams.put("city_code", BaseUtil.isValue(userToken.getRegion_code()) ? userToken.getRegion_code() : "3505");

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
        Controller.myRequest(Constants.GET_SI_LIST,Controller.TYPE_POST,getParams(), FilterGoupClassTypeBeans.class,this);
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

        if(data instanceof FilterGoupClassTypeBeans){
            goupClassTypeBeans = (FilterGoupClassTypeBeans)data;
            FilterGoupClassTypeBeans.DataBean typeBean = new FilterGoupClassTypeBeans.DataBean();
            typeBean.setSi_name("全部");
            typeBean.setSi_id(0);
            typeBean.setSelect(true);
            goupClassTypeBeans.getData().add(0,typeBean);
            singleListView.setList(goupClassTypeBeans.getData());
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
