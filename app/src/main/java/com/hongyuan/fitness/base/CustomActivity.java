package com.hongyuan.fitness.base;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.drawable.AnimationDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.app.SkinAppCompatDelegateImpl;
import androidx.recyclerview.widget.RecyclerView;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.custom_view.StatusBarHeightView;
import com.hongyuan.fitness.custom_view.TitleView;
import com.hongyuan.fitness.ui.login.TokenBean;
import com.hongyuan.fitness.ui.main.TokenSingleBean;
import com.hongyuan.fitness.util.BaseUtil;
import com.hongyuan.fitness.util.HiddenAnimUtils;
import com.hongyuan.fitness.util.HourMeterUtil;
import com.hongyuan.fitness.util.SharedPreferencesUtil;
import com.hongyuan.fitness.util.SkinConstants;
import com.hongyuan.fitness.util.StatusBarUtil;
import com.scwang.smartrefresh.header.MaterialHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.BallPulseFooter;
import com.umeng.analytics.MobclickAgent;

import net.lemonsoft.lemonbubble.LemonBubble;

import io.reactivex.annotations.Nullable;
import me.goldze.mvvmhabit.base.AppManager;

/*
* 所有activity需继承本activity（可以方便接下来的快速开发哦^_^）
* */
public abstract class CustomActivity extends AppCompatActivity implements HourMeterUtil.TimeCallBack {
    private Handler handler = new Handler();
    //加载器
    public View mView,bottomChildView;
    //父类中的标题栏
    private TitleView mainTitle;
    //父类中的刷新控件
    public SmartRefreshLayout refresh;
    //子类中的主要布局内容
    private FrameLayout mainView,bottomView;
    //全局使用的信息
    public TokenSingleBean userToken;
    //状态栏高度
    public StatusBarHeightView barHeight,barHeightDialog;

    //存储token需要的key值常亮
    protected final String TOKEN_SESSION = "token_session";
    //页面效果
    private RelativeLayout load_box;
    private TextView isEmpty,isTvErr;
    public static final int SHOW_ERR = 0X1;//显示错误页面
    public static final int SHOW_EMPTY = 0X2;//显示空数据页面
    public static final int SHOW_DATA = 0X3;//显示数据页面

    //已下是标题样式
    public final int TYPE_BAR1 = 0X1;
    public final int TYPE_BAR2 = 0X2;
    public final int TYPE_BAR3 = 0X3;
    public final int TYPE_BAR4 = 0X4;
    public final int TYPE_BAR5 = 0X5;
    public final int TYPE_BAR6 = 0X6;
    public final int TYPE_BAR7 = 0X7;
    public final int TYPE_BAR8 = 0X8;
    public final int TYPE_BAR9 = 0X9;

    //跳转处理需要的对象
    private Class<?> clz;
    private Bundle bundle;

    //加载中弹框
    private Dialog loadingDialog = null;
    private AnimationDrawable animationDrawable = null;
    private HourMeterUtil hourUtil;

    //需要下一个activity回传值的跳转码
    private final static int ORDINARY_ACTIVITY_RESULT_CODE = 0x1;

    //主题样式
    public String skin;

    //积分提时框
    public FrameLayout showHavePotionBox;
    private TextView showText;
    //顶部提示消息展开收缩动画工具类
    private HiddenAnimUtils potionAnimUtils;

    //换肤需要重载的方法
    @NonNull
    @Override
    public AppCompatDelegate getDelegate() {
        return SkinAppCompatDelegateImpl.get(this, this);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 加入Activity到堆栈
        AppManager.getAppManager().addActivity(this);

        //查询主题样式
        skin = SharedPreferencesUtil.getString(this, SkinConstants.SKIN);

        //初始化token，全局使用
        userToken = TokenSingleBean.getInstance();
        //禁止使用横屏
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED);
        //加载父布局
        setContentView(R.layout.activity_custom);

        barHeight = findViewById(R.id.barHeight);
        mainTitle = findViewById(R.id.mainTitle);
        mainView = findViewById(R.id.mainView);
        bottomView = findViewById(R.id.bottomView);
        refresh = findViewById(R.id.refresh);
        showHavePotionBox = findViewById(R.id.showHavePotionBox);
        showText = findViewById(R.id.showText);
        barHeightDialog = findViewById(R.id.barHeightDialog);

        //加载主布局
        mView = LayoutInflater.from(this).inflate(getLayoutId(), null);
        mainView.addView(mView);

        //加载底部布局
        if(getBottomLayoutId() != 0){
            bottomChildView = LayoutInflater.from(this).inflate(getBottomLayoutId(), null);
            bottomView.addView(bottomChildView);
            bottomView.setVisibility(View.VISIBLE);

            //加载底部布局控件
            initBottomView(bottomChildView);
        }else{
            bottomView.setVisibility(View.GONE);
        }

        //初始化头部消息动画对象
        potionAnimUtils = HiddenAnimUtils.newInstance(this,showHavePotionBox,null,32,0,false);

        //计时回调
        hourUtil = new HourMeterUtil();
        hourUtil.setTimeCallBack(this);

        //加载提示布局
        initPrompt();
        //初始化刷新控件
        setOnRefresh();
        //加载子布局视图
        initView();
        //setImmersive();
    }

    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }

    /*
    * 沉浸式（默认白色主题）
    * */
    public void setImmersive(){
        //当FitsSystemWindows设置 true 时，会在屏幕最上方预留出状态栏高度的 padding
        StatusBarUtil.setRootViewFitsSystemWindows(this,false);
        StatusBarUtil.setTranslucentStatus(this);
    }

    /*
    * 设置状态栏文字颜色（灰色）
    * */
    public void setsetImmersive(int color){
        //true往下移，flase会被标题栏遮住（还可以使用相应的view来实现）
        StatusBarUtil.setRootViewFitsSystemWindows(this,true);
        StatusBarUtil.setTranslucentStatus(this);
        if (!StatusBarUtil.setStatusBarDarkTheme(this, true)) {
            //如果不支持设置深色风格 为了兼容总不能让状态栏白白的看不清, 于是设置一个状态栏颜色为半透明,
            //这样半透明+白=灰, 状态栏的文字能看得清
            StatusBarUtil.setStatusBarColor(this,color);
        }
    }

    /*
    * 设置主题样式
    * typeBar:1表示红色从左到又渐变，2表示白色主题
    * */
    public void setTypeBar(int typeBar,String titleName){
        if(typeBar == 1){
            barHeight.setVisibility(View.VISIBLE);
            mainTitle.setVisibility(View.VISIBLE);
            mainTitle.getBgView().setBackgroundResource(R.drawable.shape_gradient_v_ef_f0);
            mainTitle.setCenterTextColor(titleName,0xFFFFFFFF);
            mainTitle.hideLine();
            mainTitle.setLeftImage(R.mipmap.white_left_img);
            setImmersive();
        }else if(typeBar == 2){
            mainTitle.setCenterTextColor(titleName,0xFF000000);
            mainTitle.hideLine();
            setsetImmersive(0x55000000);
        }else{
            barHeight.setVisibility(View.GONE);
        }
    }
    /*
    * 运动里面的主题
    * */
    public void setTypeBar(String titleName,int gradientId){
        barHeight.setVisibility(View.VISIBLE);
        mainTitle.setVisibility(View.VISIBLE);
        mainTitle.getBgView().setBackgroundResource(gradientId);
        barHeight.setBackgroundResource(gradientId);
        mainTitle.setCenterTextColor(titleName,0xFFFFFFFF);
        mainTitle.hideLine();
        mainTitle.setLeftImage(R.mipmap.white_common);
        setImmersive();
    }

    /*
    * 通用标题样式
    * barType:标题和bar的样式
    * drawableId：使用沉溺式显示的北京样式
    * titleName：标题显示内容
    *
    * TYPE_BAR1：没标题，沉浸式，有顶高,状态栏字体灰色
    * TYPE_BAR2:有标题，沉浸式，有顶高，状态栏字体白色，标题字体颜色白色，没有返回键,
    *           有右边title,（该样式仅限个人中心使用）
    * TYPE_BAR3:有标题，非沉浸式，黑色字体，无标题底线,白色背景
    * TYPE_BAR4：有标题，沉浸式，有顶高，白色字体，自定义背景
    * TYPE_BAR5:有标题，非沉浸式，黑色字体，有标题底线，白色背景
    * TYPE_BAR6:没标题，沉浸式，无顶高，状态栏字体灰色
    * TYPE_BAR7:没标题，沉浸式，无顶高，状态栏字体白色色
    * TYPE_BAR8:没标题，沉浸式，有顶高，状态栏字体白色色，自定义顶高背景颜色
    * TYPE_BAR9：有标题，沉浸式，有顶高，白色字体，自定义背景,有标题底线
    * */
    public void setTitleBar(int barType,int drawableId,String titleName){
        if(barType == TYPE_BAR1){
            //隐藏标题
            hideTitle(true);
            //沉浸式
            setImmersive();
            //显示顶高
            barHeight.setVisibility(View.VISIBLE);
            barHeight.setBackgroundResource(drawableId);
            StatusBarUtil.setCommonUI(this,true);
        }
        if(barType == TYPE_BAR2){
            //隐藏标题
            hideTitle(true);
            //沉浸式
            setImmersive();
            //隐藏顶高
            barHeight.setVisibility(View.GONE);
            //StatusBarUtil.setCommonUI(this,false);
        }
        if(barType == TYPE_BAR3){
            setTitle(titleName);
            setsetImmersive(0x55000000);
        }
        if(barType == TYPE_BAR4){
            //沉浸式
            setImmersive();
            mainTitle.setCenterTextColor(titleName,0xFFFFFFFF);
            mainTitle.setLeftImage(R.mipmap.theme_left_img_black);
            mainTitle.getBgView().setBackgroundResource(drawableId);
            mainTitle.hideLine();
            barHeight.setVisibility(View.VISIBLE);
            barHeight.setBackgroundResource(drawableId);
        }
        if(barType == TYPE_BAR5){
            mainTitle.setCentreText(titleName);
            setsetImmersive(0x55000000);
        }
        if(barType == TYPE_BAR6){
            //隐藏标题
            hideTitle(true);
            //沉浸式
            setImmersive();
            StatusBarUtil.setCommonUI(this,true);
        }
        if(barType == TYPE_BAR7){
            //隐藏标题
            hideTitle(true);
            //沉浸式
            setImmersive();
        }

        if(barType == TYPE_BAR8){
            //隐藏标题
            hideTitle(true);
            //沉浸式
            setImmersive();
            //显示顶高
            barHeight.setVisibility(View.VISIBLE);
            barHeight.setBackgroundResource(drawableId);
        }

        if(barType == TYPE_BAR9){
            //沉浸式
            setImmersive();
            mainTitle.setCenterTextColor(titleName,0xFFFFFFFF);
            mainTitle.setLeftImage(R.mipmap.theme_left_img_black);
            mainTitle.getBgView().setBackgroundResource(drawableId);
            mainTitle.showLine();
            barHeight.setVisibility(View.VISIBLE);
            barHeight.setBackgroundResource(drawableId);
        }
    }


    /*
    * 个人中心设置沉浸式
    * */
    public void setPersonImmer(){
        StatusBarUtil.setTranslucentStatus(this);
        StatusBarUtil.setRootViewFitsSystemWindows(this,false);
    }

    /*
     * 加载页面显示效果
     * */
    private void initPrompt(){
        load_box = findViewById(R.id.load_box);
        isEmpty = findViewById(R.id.isEmpty);
        isTvErr = findViewById(R.id.isErr);
    }

    /*
     * 初始化加载控件各个设置
     * */
    private void setOnRefresh(){
        //关闭滚动到底部自动加载
        refresh.setEnableAutoLoadMore(false);
        //设置主题颜色
        refresh.setPrimaryColors(0xFFF2F2F2);
        //初始刷新动画
        refresh.setRefreshHeader(new MaterialHeader(this).setShowBezierWave(true));
        //初始化加载动画
        refresh.setRefreshFooter(new BallPulseFooter(this).setSpinnerStyle(SpinnerStyle.Scale));
        //关闭上拉加载更多
        refresh.setEnableLoadMore(false);
        //是否开启自动刷新
        refresh.setEnableRefresh(false);
        //是否开启刷新功能
        refresh.setEnableRefresh(false);
    }

    /*
     * 设置标题
     * */
    protected void setTitle(String title){
        mainTitle.setCentreText(title);
        mainTitle.hideLine();
    }
    /*
     * 获取标题控件
     * */
    public TitleView getMainTitle(){
        return this.mainTitle;
    }

    /*
    * 隐藏标题
    * */
    protected void hideTitle(boolean flag){
        if(flag){
            mainTitle.setVisibility(View.GONE);
        }else{
            mainTitle.setVisibility(View.VISIBLE);
        }
    }

    /*
    * 隐藏左边返回键
    * */
    protected void hideTitleLeft(){
        mainTitle.hideLeft();
    }

    /*
     * 加载布局文件
     * */
    protected abstract int getLayoutId();

    /*
    * 加载底部button布局
    * */
    public int getBottomLayoutId(){
        return 0;
    }

    /*
     * 加载布局控件
     * */
    protected abstract void initView();

    /*
    * 加载底部布局控件
    * */
    protected void initBottomView(View bottomChildView){

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
     * 获取固定底部顶高
     * */
    public View getFooterHeight(RecyclerView v){
        View convertView = LayoutInflater
                .from(this)
                .inflate(R.layout.view_bottom_90height, (ViewGroup) v.getParent(), false);
        return convertView;
    }
    /*
     * 获取固定底部顶高
     * */
    public View getFooter16Height(RecyclerView v){
        View convertView = LayoutInflater
                .from(this)
                .inflate(R.layout.view_bottom_12height, (ViewGroup) v.getParent(), false);
        return convertView;
    }

    /*
     * 关闭刷新的回调方法
     * */
    public void closeRefresh() {
        if(refresh != null){
            refresh.finishRefresh();
            refresh.finishLoadMore();
        }
    }

    /*
    * 有值回传的跳转
    * */
    public void startForResult(Class<?> clz,Bundle bundle){
        Intent intent = new Intent();
        intent.setClass(this,clz);
        if(bundle != null){
            intent.putExtra("bundle",bundle);
        }
        startActivityForResult(intent,ORDINARY_ACTIVITY_RESULT_CODE);
    }

    /*
     * 回传上一个页面
     * */
    public void setResult(Bundle bundle){
        Intent intent = new Intent();
        intent.putExtra("bundle", bundle);
        setResult(RESULT_OK, intent);
        finish();
    }

    /*
    * 从写onActivityResult获取跳转传递的值
    * */
    @Override
    protected void onActivityResult(int requestCode, int resultCode,Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == ORDINARY_ACTIVITY_RESULT_CODE && resultCode == RESULT_OK){
            assert data != null;
            forResult(data.getBundleExtra("bundle"));
        }
    }

    /*
    * 回传需要从写的函数
    * @param bundle 跳转所携带的信息
    * */
    protected void forResult(Bundle bundle){

    }

    /**
     * 跳转页面
     */
    public void startActivity(Class<?> clz) {
        Intent intent = new Intent();
        intent.setClass(this,clz);
        if(bundle != null){
            intent.putExtra("bundle",bundle);
        }
        startActivity(intent);
    }

    /**
     * 跳转页面
     *
     * @param clz    所跳转的目的Activity类
     * @param bundle 跳转所携带的信息
     */
    public void startActivity(Class<?> clz, Bundle bundle) {
        Intent intent = new Intent();
        intent.setClass(this,clz);
        if(bundle != null){
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    /*
    * 获取跳转过来的序列化对象数据
    * */
    public Object getIntentData(String key){
        return getIntent().getSerializableExtra(key);
    }

    /*
    * 成功提示弹框，并过三秒关掉当前activity
    * */
    public void showSuccess(String message){
        if(!isFinishing()) {
            LemonBubble.showRight(this,message,2000);
        }
        handler.postDelayed(runnableClose, 2000);
    }
    /*
    * 成功提示弹框，并过三秒关掉当前activity
    * */
    public void showSuccess(String message,Class<?> clz, Bundle bundle){
        this.clz = clz;
        this.bundle = bundle;
        if(!isFinishing()) {
            LemonBubble.showRight(this,message,2000);
        }
        handler.postDelayed(runnableClose, 2000);
    }

    /*
    * 错误提示框，并过三门关闭当前activity
    * */
    public void showErr(String message){
        if(!isFinishing()) {
            LemonBubble.showError(this, message, 2000);
        }
        handler.postDelayed(runnableClose, 2500);
    }

    /*
    * 关闭当前页面
    * */
    Runnable runnableClose = new Runnable() {
        @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
        @Override
        public void run() {
            if(clz != null){
                startActivity(clz);
            }
            finish();
        }
    };

    /*
     * 提示加载中
     * */
    public void showLemonLoading(String message){
        if(!isFinishing()){
            LemonBubble.showRoundProgress(this,message);
        }
    }

    /*
     * 成功提示
     * */
    public void showLemonSuccess(String message){
        if(!isFinishing()){
            LemonBubble.showRight(this,message,2000);
        }
    }

    /*
     * 错误提示
     * */
    public void showLemonErr(String message){
        if(!isFinishing()){
            LemonBubble.showError(this, message, 2000);
        }
    }

    /*
    * 关闭提示
    * */
    public void hidLEmon(){
        LemonBubble.hide();
    }

    /*
    * 显示加载弹框
    * */
    public void showLoading(){
        //开始计时
        if(hourUtil != null){
            hourUtil.startCount();
        }

        if(loadingDialog == null){
            loadingDialog = new Dialog(this, R.style.MessageTheme);
            loadingDialog.setCanceledOnTouchOutside(false);
            View view = View.inflate(this, R.layout.view_base_loading,null);
            loadingDialog.setContentView(view);
            Window window = loadingDialog.getWindow();
            window.setGravity(Gravity.CENTER);
            window.setWindowAnimations(R.style.main_menu_animStyle);
            window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
            ImageView loadImg = view.findViewById(R.id.loadImg);
            animationDrawable = (AnimationDrawable) loadImg.getBackground();
        }
        if(!isFinishing()){
            loadingDialog.show();
            animationDrawable.start();
        }
    }

    /*
     * 获取传递参数的bundle
     * */
    public Bundle getBundle(){
        return getIntent().getExtras();
    }

    /*
    * 关闭加载弹框
    * */
    public void closeLoading(){
        if(!isFinishing() && loadingDialog != null && loadingDialog.isShowing()){
            loadingDialog.dismiss();
            hourUtil.stopCount();
        }
    }

    @Override
    public void onTime(int passedTime) {
        if(passedTime%30 == 0){
            closeLoading();
        }
    }

    @Override
    protected void onDestroy() {
        try {
            //日了狗，不知道啥原因老报这句错误，暂时抛出异常，后续再详查
            super.onDestroy();
        }catch (Exception e){
            e.printStackTrace();
        }

        // 结束Activity&从堆栈中移除
        AppManager.getAppManager().finishActivity(this);
    }


    /*
    * 当用户信息被清楚后从新获取
    * */
    public void getNewUserToken(){
        //token信息
        TokenBean.DataBean tokenBean = (TokenBean.DataBean) SharedPreferencesUtil.getBean(this,TOKEN_SESSION);
        if(BaseUtil.isValue(tokenBean) && BaseUtil.isValue(tokenBean.getM_id())) {
            userToken.setAt_id(tokenBean.getAt_id());
            userToken.setAt_name(tokenBean.getAt_name());
            userToken.setAt_pwd(tokenBean.getAt_pwd());
            userToken.setToken(tokenBean.getToken());
            userToken.setM_id(tokenBean.getM_id());
            userToken.setM_mobile(tokenBean.getM_mobile());
            userToken.setRandomnum("90");
            userToken.setNtoken("79ce633ae10a10c2bba676a7fbf5db3b1a862df26f6943997cef90233877a4fe1f23a61d89941a31f62e6ddd6fad9025");
            userToken.setTimespan("1591924480353");
        }
    }

    /*
    * 显示获得的积分
    * */
    public void showPotion(String numPotion){
        if(barHeight.getVisibility() == View.VISIBLE){
            barHeightDialog.setVisibility(View.VISIBLE);
        }else{
            barHeightDialog.setVisibility(View.GONE);
        }

        potionAnimUtils.toggle(true);

        HourMeterUtil hourMeterUtil = new HourMeterUtil();
        showText.setText(numPotion);
        hourMeterUtil.startCount();
        hourMeterUtil.setTimeCallBack(passedTime -> {
            if(passedTime >= 2){
                potionAnimUtils.toggle(false);
                hourMeterUtil.stopCount();
            }
        });
    }
}
