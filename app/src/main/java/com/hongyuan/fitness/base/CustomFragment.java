package com.hongyuan.fitness.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.custom_view.TitleView;
import com.hongyuan.fitness.ui.login.vtwo_login.VtwoLoginActivity;
import com.hongyuan.fitness.ui.login.vtwo_login.vtwo_verification_login.VtwoVerificationLoginActivity;
import com.hongyuan.fitness.util.BaseUtil;
import com.hongyuan.fitness.util.EncryptionUtil;
import com.hongyuan.fitness.util.SharedPreferencesUtil;
import com.scwang.smartrefresh.header.MaterialHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.BallPulseFooter;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import net.lemonsoft.lemonbubble.LemonBubble;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/*
 * 所有Fragment需继承本Fragment（可以方便接下来的快速开发哦^_^）
 * */
public abstract class CustomFragment<T> extends Fragment implements RetrofitListener{

    private boolean isFragmentVisible;
    private boolean isReuseView;
    private boolean isFirstVisible;
    private View rootView;
    private Context mContext;
    private View mView,headLayt;
    protected SmartRefreshLayout refresh;
    private LinearLayout customBg;
    private TitleView mTitle;

    //泛型类
    private Class<T> dataBean;

    //下面是当前基础布局
    private FrameLayout mainView,headView,bottomView;
    //页面效果
    private RelativeLayout load_box;
    private TextView isEmpty,isTvErr;
    public final int SHOW_ERR = 0X1;//显示错误页面
    public final int SHOW_EMPTY = 0X2;//显示空数据页面
    public final int SHOW_DATA = 0X3;//显示数据页面

    //分页需要的数据
    protected int curPage = 1;//当前页数
    private int pageSize = 10;//一页条目数
    public boolean isLoadMore = false;//是否开启了分页功能
    protected final int FIRST_PAGE = 1;//表示第一页

    private Map<String,String> params;
    private Map<String,String> headParams;
    public CustomActivity mActivity;

    //标识木有头部布局或者底部布局
    private final int NO_VIEW = 0;
    //请求成功状态码
    private final int SUCCESS = 1;
    //需要登录的错误码
    protected final int ISLOGIN = 600;

    /*
     * 当创建多个Fragment时，通过传递参数来识别
     * */
    public CustomFragment setArguments(String type) {
        Bundle bundle = new Bundle();
        bundle.putString("_type", type);
        setArguments(bundle);
        return this;
    }

    /*
     * 直接传递Bundle
     * */
    public CustomFragment setMyArguments(Bundle bundle) {
        setArguments(bundle);
        return this;
    }

    /*
     * 获取当前的类型
     * */
    public String getFragType() {
        Bundle bundle = getArguments();
        return bundle.getString("_type", "");
    }

    /*
     * 获取具体key的值
     * */
    public String getFragType(String key) {
        Bundle bundle = getArguments();
        if(bundle != null){
            return bundle.getString(key);
        }
        return "";
    }
    /*
     * 获取具体key的值
     * */
    public boolean getBoolenFrag(String key) {
        Bundle bundle = getArguments();
        if(bundle != null){
            return bundle.getBoolean(key);
        }
        return false;
    }

    /*
     * 获取自定义的key值所对应的Serializable序列化对象
     * */
    public Object getSerializableBeans(String key){
        Bundle bundle = getArguments();
        if(bundle != null){
            return bundle.getSerializable(key);
        }
        return null;
    }


    /*
     * 高版本需要走这个方法
     * */
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
        if(context instanceof CustomActivity){
            mActivity = (CustomActivity)context;
        }
    }

    /*
     * 低版本需要走这个方法
     * */
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mContext = activity;
        if(activity instanceof CustomActivity){
            mActivity = (CustomActivity)activity;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        try {
            Field childFragmentManager = Fragment.class.getDeclaredField("mChildFragmentManager");
            childFragmentManager.setAccessible(true);
            childFragmentManager.set(this, null);
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    //setUserVisibleHint()在Fragment创建时会先被调用一次，传入isVisibleToUser = false
    //如果当前Fragment可见，那么setUserVisibleHint()会再次被调用一次，传入isVisibleToUser = true
    //如果Fragment从可见->不可见，那么setUserVisibleHint()也会被调用，传入isVisibleToUser = false
    //总结：setUserVisibleHint()除了Fragment的可见状态发生变化时会被回调外，在new Fragment()时也会被回调
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        //setUserVisibleHint()有可能在fragment的生命周期外被调用
        if (rootView == null) {
            return;
        }
        if (isFirstVisible && isVisibleToUser) {
            onFragmentFirstVisible();
            isFirstVisible = false;
        }
        if(!isFirstVisible && isVisibleToUser){

        }
        if (isVisibleToUser) {
            onFragmentVisibleChange(true);
            isFragmentVisible = true;
            return;
        }
        if (isFragmentVisible) {
            isFragmentVisible = false;
            onFragmentVisibleChange(false);
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initVariable();
    }

    @Override
    public void onResume() {
        super.onResume();
        onMyResume();
        //当再次显示出来时，需要刷新
        if(!isFirstVisible && isFragmentVisible){
            curPage = FIRST_PAGE;
            refreshData();
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mView == null)
            mView = inflater.inflate(R.layout.fragment_custom, container, false);
        customBg = mView.findViewById(R.id.customBg);
        mTitle = mView.findViewById(R.id.mTitle);
        mainView = mView.findViewById(R.id.mainView);
        headView = mView.findViewById(R.id.headView);
        bottomView = mView.findViewById(R.id.bottomView);
        refresh = mView.findViewById(R.id.refresh);
        //加载主布局
        View childView = LayoutInflater.from(mContext).inflate(getLayoutId(), null);
        mainView.addView(childView);
        if(getHeadLayoutId() != NO_VIEW){
            headLayt = LayoutInflater.from(mContext).inflate(getHeadLayoutId(), null);
            headView.addView(headLayt);
            headView.setVisibility(View.VISIBLE);
            initHeadView(headLayt);
        }

        //加载底部布局
        if(getBottomLayoutId() != 0){
            View bottomChildView = LayoutInflater.from(mContext).inflate(getBottomLayoutId(), null);
            bottomView.addView(bottomChildView);
            bottomView.setVisibility(View.VISIBLE);
        }else{
            bottomView.setVisibility(View.GONE);
        }

        initPrompt(mView);
        setOnRefresh();
        initView(mView);
        return mView;
    }

    /*
     * 初始化加载控件各个设置
     * */
    private void setOnRefresh(){
        //关闭滚动到底部自动加载
        refresh.setEnableAutoLoadMore(false);
        //设置刷新监听
        refresh.setOnRefreshListener(onRefresh());
        //加载更多监听
        refresh.setOnLoadMoreListener(onLoadMore());
        //设置主题颜色
        refresh.setPrimaryColors(0xFFFFFFFF);
        //初始刷新动画
        refresh.setRefreshHeader(new MaterialHeader(mContext).setShowBezierWave(true));
        //初始化加载动画
        refresh.setRefreshFooter(new BallPulseFooter(mContext).setSpinnerStyle(SpinnerStyle.Scale));
        //关闭上拉加载更多
        setEnableLoadMore(false);
        //是否开启自动刷新
        setAutoRefresh(false);
        //是否开启刷新功能
        setEnableRefresh(false);
    }

    /*
     * 设置背景颜色
     * */
    public void setCustomBg(int mColor){
        customBg.setBackgroundColor(getResources().getColor(mColor));
    }

    /*
     * 是否启用越界拖动（仿苹果效果）1.0.4
     * */
    public void setEnableOverScrollDrag(boolean flag){
        if(refresh != null){
            refresh.setEnableOverScrollDrag(flag);
        }
    }

    /*
     * 是否开启上拉加载(默认不开启)
     * */
    public void setEnableLoadMore(boolean flag){
        if(refresh != null){
            isLoadMore = flag;
            refresh.setEnableLoadMore(flag);
        }
    }

    /*
     * flag 是否开启上拉加载(默认不开启)
     * auto 是否开启拉到底部自动加载
     * */
    public void setEnableLoadMore(boolean flag,boolean auto){
        if(refresh != null){
            isLoadMore = flag;
            refresh.setEnableLoadMore(flag);
        }
        if(auto){
            refresh.setEnableAutoLoadMore(auto);
        }
    }

    /*
     * 是否开启自动刷新(默认不开启)
     * */
    public void setAutoRefresh(boolean flag){
        if(flag){
            refresh.autoRefresh();
        }
    }

    /*
     * 是否开启刷新功能（默认不开启）
     * */
    public void setEnableRefresh(boolean flag){
        if(refresh != null){
            refresh.setEnableRefresh(flag);
        }
    }

    /*
     * 设置标题
     * */
    public TitleView getmTitle(){
        mTitle.setVisibility(View.VISIBLE);
        return this.mTitle;
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
            loadMoreData();
        };
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        //如果setUserVisibleHint()在rootView创建前调用时，那么
        //就等到rootView创建完后才回调onFragmentVisibleChange(true)
        //保证onFragmentVisibleChange()的回调发生在rootView创建完成之后，以便支持ui操作

        if (rootView == null) {
            rootView = view;
            if (getUserVisibleHint()) {
                if (isFirstVisible) {
                    onFragmentFirstVisible();
                    isFirstVisible = false;
                }
                onFragmentVisibleChange(true);
                isFragmentVisible = true;
            }
        }
        super.onViewCreated(isReuseView && rootView != null ? rootView : view, savedInstanceState);
    }

    /**
     * 去除setUserVisibleHint()多余的回调场景，保证只有当fragment可见状态发生变化时才回调
     * 回调时机在view创建完后，所以支持ui操作，解决在setUserVisibleHint()里进行ui操作有可能报null异常的问题
     *
     * 可在该回调方法里进行一些ui显示与隐藏
     *
     * @param isVisible true  不可见 -> 可见
     *                  false 可见  -> 不可见
     */
    protected void onFragmentVisibleChange(boolean isVisible) {
        try {
            if (isVisible && isFirstVisible) {
                //不可见状态
            } else {
                //可见状态
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 在fragment首次可见时回调，可用于加载数据，防止每次进入都重复加载数据
     */
    protected void onFragmentFirstVisible() {
        lazyLoad();

        //只执行一次
        lazyOnceLoad();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        initVariable();
    }

    private void initVariable() {
        isFirstVisible = true;
        isFragmentVisible = false;
        rootView = null;
        isReuseView = true;
    }

    public abstract int getLayoutId();
    public abstract void initView(View mView);
    /*
     * 加载头部布局文件
     * */
    protected int getHeadLayoutId(){
        return NO_VIEW;
    }

    /*
     * 初始化头部布局各个控件
     * */
    protected void initHeadView(View headLayt){

    }

    public int getBottomLayoutId(){
        return 0;
    }

    public void refreshData(){

    }
    public void loadMoreData(){

    }
    /*
     * 加载数据
     * */
    protected void lazyLoad(){

    }

    /*
     * 只加载一次
     * */
    protected void lazyOnceLoad(){

    }

    /*
     * 每次都加载
     * */
    protected void onMyResume(){

    }

    /**
     * 跳转页面
     *
     * @param clz    所跳转的目的Activity类
     * @param bundle 跳转所携带的信息
     */
    public void startActivity(Class<?> clz, Bundle bundle) {
        Intent intent = new Intent();
        intent.setClass(getContext(),clz);
        if(bundle != null){
            intent.putExtras(bundle);
        }
        getContext().startActivity(intent);
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
     * 显示页面效果切换
     * */
    public void setPromtView(int type){
        if(type == SHOW_ERR){
            mainView.setVisibility(View.GONE);
            load_box.setVisibility(View.VISIBLE);
            isTvErr.setVisibility(View.VISIBLE);
            isEmpty.setVisibility(View.GONE);
        }else if(type == SHOW_EMPTY){
            mainView.setVisibility(View.GONE);
            load_box.setVisibility(View.VISIBLE);
            isTvErr.setVisibility(View.GONE);
            isEmpty.setVisibility(View.VISIBLE);
        }else{
            load_box.setVisibility(View.GONE);
            mainView.setVisibility(View.VISIBLE);
        }
    }
    /*
     * 组装参数
     * */
    public CustomFragment setParams(String key,String value){
        if(params == null){
            params = new HashMap<>();
        }
        params.put(key,value);
        return this;
    }
    /*
     * 清空参数集
     * */
    public CustomFragment clearParams(){
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
        if(mActivity.userToken != null){
            int randomNum = (int)(Math.random()*100);
            long timeSpan = System.currentTimeMillis();

            StringBuilder ntoken = new StringBuilder();
            ntoken.append(EncryptionUtil.md5Decode(mActivity.userToken.getToken()));
            ntoken.append(EncryptionUtil.md5DecodeTwo(String.valueOf(randomNum)));
            ntoken.append(EncryptionUtil.md5DecodeTwo(String.valueOf(timeSpan)));

            Map<String,String> baseParams = new HashMap<>();
            baseParams.put("client","Android");
            baseParams.put("token",mActivity.userToken.getToken());
            baseParams.put("at_id",String.valueOf(mActivity.userToken.getAt_id()));
            baseParams.put("randomnum",String.valueOf(randomNum));
            baseParams.put("timespan",String.valueOf(timeSpan));
            baseParams.put("ntoken",ntoken.toString());
            //是否开启分页
            if(isLoadMore){
                baseParams.put("curpage",String.valueOf(curPage));
                baseParams.put("page",String.valueOf(pageSize));
            }
            //是否登录过
            if(mActivity.userToken.getM_id() != null){
                baseParams.put("m_id",mActivity.userToken.getM_id());
                baseParams.put("m_mobile",mActivity.userToken.getM_mobile());
            }

            return baseParams;
        }
        return null;
    }

    /*
     * 需要做区分，子类需要实现该方法
     * */
    @Override
    public void onSuccess(int code, Object data) {

    }

    /*
     * 关闭刷新的回调方法
     * */
    @Override
    public void closeRefresh() {
        if(refresh != null){
            refresh.finishRefresh();
            refresh.finishLoadMore();

        }
    }

    /*
     * 请求失败的回调
     * */
    @Override
    public void onError(int err_code,String description) {
        if(err_code == ISLOGIN && description.contains("登录")){
            startActivity(VtwoVerificationLoginActivity.class,null);
        }else{
            LemonBubble.showError(getContext(), description, 2000);
        }

    }

    /*
     * 请求结果区分成功与否（当 SUCCESS == 1）时继续下面操作，否则弹框提示
     * */
    protected boolean isSuccess(Object obj){
        if(obj instanceof BaseBean){
            BaseBean baseBean = (BaseBean)obj;
            if(Integer.valueOf(baseBean.getStatus().getSucceed()) == SUCCESS ){
                return true;
            }else if(baseBean.getStatus().getError_code() == ISLOGIN){
                startActivity(VtwoVerificationLoginActivity.class,null);
            }else{
                LemonBubble.showError(mActivity, baseBean.getStatus().getError_desc(), 2000);
                return false;
            }
        }
        return false;
    }


    /*
     * 判断对象是否有值
     * */
    public boolean isValue(Object obj){
        if(obj == null || TextUtils.isEmpty(obj.toString()) || "null".equals(obj.toString())){
            return false;
        }
        return true;
    }

    /*
     * 成功提示
     * */
    public void showSuccess(String message){
        LemonBubble.showRight(mActivity,message,2000);
    }

    /*
     * 提升加载中
     * */
    public void showLoading(String message){
        LemonBubble.showRoundProgress(this,message);
    }

    /*
     * 获取固定底部顶高
     * */
    public View getFooterHeight(RecyclerView v){
        View convertView = LayoutInflater
                .from(mActivity)
                .inflate(R.layout.view_bottom_height, (ViewGroup) v.getParent(), false);
        return convertView;
    }

    /*
     * 设置泛型类
     * */
    public CustomFragment setMyClass(Class<T> dataBean){
        this.dataBean = dataBean;
        return this;
    }

    /*
     * 获取泛型
     * */
    public Class<T> getMyClass(){
        return this.dataBean;
    }


    /*
     * 存储当前自动刷新时间
     * */
    protected void inAutoRefreshTime(String key){
        String nowTime = String.valueOf(System.currentTimeMillis());
        SharedPreferencesUtil.putBean(mActivity,key,nowTime);
    }

    /*
     * 获取上次自动刷新的时间
     * */
    protected Long getAutoRefreshTime(String key){
        String lastTime = String.valueOf(SharedPreferencesUtil.getBean(mActivity,key));
        if(BaseUtil.isValue(lastTime)){
            return Long.valueOf(lastTime);
        }

        return 0l;
    }

    /*
     * 调用直接拨打电话功能
     * */
    public void callTel(String telNum){
        Intent intent=new Intent(Intent.ACTION_CALL, Uri.parse("tel:"+telNum));
        mActivity.startActivity(intent);
    }

    //用来标识是否是第一次创建

    /*
     * 是否需要自动刷新
     * */
    protected boolean isAutoRefresh(String key){
        long nowTime = System.currentTimeMillis();
        long lastTime = getAutoRefreshTime(key);

        long apartTime = nowTime - lastTime;
        if(apartTime > 3600000 && !isFirstVisible){
            return true;
        }
        return false;
    }

}
