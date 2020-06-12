package com.hongyuan.fitness.ui.main.main_home.recommend.vthour;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.BaseBean;
import com.hongyuan.fitness.base.Constants;
import com.hongyuan.fitness.base.ConstantsCode;
import com.hongyuan.fitness.base.SingleClick;
import com.hongyuan.fitness.custom_view.CustomRecyclerView;
import com.hongyuan.fitness.ui.about_class.coach.coach_list.CoachListActivity;
import com.hongyuan.fitness.ui.about_class.group_class.group_details.MissionDetailActivity;
import com.hongyuan.fitness.ui.about_class.privite_class.course_details.CourseDetailsActivity;
import com.hongyuan.fitness.ui.find.circle.circle_detail.CircleDetailsActivity;
import com.hongyuan.fitness.ui.find.circle.post_details.PostDetailsActivity;
import com.hongyuan.fitness.ui.login.vtwo_login.vtwo_verification_login.VtwoVerificationLoginActivity;
import com.hongyuan.fitness.ui.main.MainActivity;
import com.hongyuan.fitness.ui.main.TokenSingleBean;
import com.hongyuan.fitness.ui.main.main_home.recommend.vthird_home.V3HomeFindContentAdapter;
import com.hongyuan.fitness.ui.main.main_home.recommend.vthird_home.V3HomeGoodsAdapter;
import com.hongyuan.fitness.ui.main.main_home.recommend.vthird_home.V3HomeStoreAdapter;
import com.hongyuan.fitness.ui.shop.sactivity.SgoodsDetailActivity;
import com.hongyuan.fitness.ui.store.StoreDetailActivity;
import com.hongyuan.fitness.ui.store.store_page_list.StoreActivity;
import com.hongyuan.fitness.ui.webview.WebViewActivity;
import com.hongyuan.fitness.util.BaseUtil;
import org.greenrobot.eventbus.EventBus;

import java.util.List;

public class V4HomeRecyclerItemView extends LinearLayout {
    private MainActivity mActivity;

    private TextView typeTitle,desTv,typeRight;
    private CustomRecyclerView mTRecycler;
    private RelativeLayout titleBox;

    private int styleType;

    /*
    * 需要回调请求的接口
    * */
    public interface Commback{
        void commback(int position, String datas);
    }


    public V4HomeRecyclerItemView(Context context) {
        super(context);
        if(context instanceof MainActivity){
            mActivity = (MainActivity)context;
        }
        initLayoutView();
    }

    public V4HomeRecyclerItemView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.homeItem);
        styleType = a.getInt(R.styleable.homeItem_vthourth_type,0);

        if(context instanceof MainActivity){
            mActivity = (MainActivity)context;
        }
        initLayoutView();
    }

    public V4HomeRecyclerItemView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        if(context instanceof MainActivity){
            mActivity = (MainActivity)context;
        }
        initLayoutView();
    }

    public void initLayoutView() {
        View view = View.inflate(getContext(), R.layout.view_vthourth_home_page_item, this);
        mTRecycler = view.findViewById(R.id.mTRecycler);
        typeTitle = view.findViewById(R.id.typeTitle);
        desTv = view.findViewById(R.id.desTv);
        typeRight = view.findViewById(R.id.typeRight);
        titleBox = view.findViewById(R.id.titleBox);



        //附近场馆
        if(styleType == 0){
            LinearLayoutManager manager = new LinearLayoutManager(getContext());
            manager.setOrientation(LinearLayoutManager.HORIZONTAL);
            mTRecycler.setLayoutManager(manager);

            typeTitle.setText("附近场馆");
            typeRight.setText("更多");

            typeRight.setOnClickListener(v -> {
                mActivity.startActivity(StoreActivity.class,null);
            });

        }
        //和朋友一起运动
        if(styleType == 1){
            LinearLayoutManager manager = new LinearLayoutManager(getContext());
            manager.setOrientation(LinearLayoutManager.HORIZONTAL);
            mTRecycler.setLayoutManager(manager);

            typeTitle.setText("和朋友一起运动");
            typeRight.setText("更多");

            typeRight.setOnClickListener(v -> {
                Bundle bundle = new Bundle();
                bundle.putString("url", Constants.WEB_ADDRESS+"/Asportslist"+TokenSingleBean.getInstance().getWebAllParams(""));
                bundle.putString("title","约运动");
                if(BaseUtil.isValue(TokenSingleBean.getInstance().getM_id())){
                    mActivity.startActivity(WebViewActivity.class,bundle);
                }else{
                    mActivity.startActivity(VtwoVerificationLoginActivity.class,null);
                }
            });
        }
        //减脂塑形
        if(styleType == 2){
            LinearLayoutManager manager = new LinearLayoutManager(getContext());
            manager.setOrientation(LinearLayoutManager.HORIZONTAL);
            mTRecycler.setLayoutManager(manager);

            typeTitle.setText("减脂塑形");
            typeRight.setText("更多");

            typeRight.setOnClickListener(v -> {
                //通过EventBus去通知MainActivity显示第三页
                EventBus.getDefault().post(ConstantsCode.EB_START_COURSE,"2");
                //需要显示私教课
                EventBus.getDefault().post(ConstantsCode.EB_SHOW_PRIVITE,"0");
            });
        }
        //力量增肌
        if(styleType == 3){
            LinearLayoutManager manager = new LinearLayoutManager(getContext());
            manager.setOrientation(LinearLayoutManager.HORIZONTAL);
            mTRecycler.setLayoutManager(manager);

            typeTitle.setText("力量增肌");
            typeRight.setText("更多");

            typeRight.setOnClickListener(v -> {
                //通过EventBus去通知MainActivity显示第三页
                EventBus.getDefault().post(ConstantsCode.EB_START_COURSE,"2");
                //需要显示私教课
                EventBus.getDefault().post(ConstantsCode.EB_SHOW_PRIVITE,"0");
            });
        }
        //精品团课
        if(styleType == 4){
            LinearLayoutManager manager = new LinearLayoutManager(getContext());
            manager.setOrientation(LinearLayoutManager.HORIZONTAL);
            mTRecycler.setLayoutManager(manager);

            typeTitle.setText("精品团课");
            typeRight.setText("更多");
            desTv.setVisibility(VISIBLE);

            typeRight.setOnClickListener(v -> {
                //通过EventBus去通知MainActivity显示第三页
                EventBus.getDefault().post(ConstantsCode.EB_START_COURSE,"2");
                //需要显示团课
                EventBus.getDefault().post(ConstantsCode.EB_SHOW_PRIVITE,"1");
            });
        }
        //精品培训课
        if(styleType == 5){
            LinearLayoutManager manager = new LinearLayoutManager(getContext());
            manager.setOrientation(LinearLayoutManager.HORIZONTAL);
            mTRecycler.setLayoutManager(manager);

            typeTitle.setText("精品培训课");
            typeRight.setText("更多");

            typeRight.setOnClickListener(v -> {
                Bundle bundle = new Bundle();
                bundle.putString("url", Constants.WEB_ADDRESS+"/train"+TokenSingleBean.getInstance().getWebAllParams(""));
                bundle.putString("title","培训课");
                if(BaseUtil.isValue(TokenSingleBean.getInstance().getM_id())){
                    mActivity.startActivity(WebViewActivity.class,bundle);
                }else{
                    mActivity.startActivity(VtwoVerificationLoginActivity.class,null);
                }
            });
        }
        //随动商城
        if(styleType == 6){
            LinearLayoutManager manager = new LinearLayoutManager(getContext());
            manager.setOrientation(LinearLayoutManager.HORIZONTAL);
            mTRecycler.setLayoutManager(manager);

            typeTitle.setText("随动商城");
            typeRight.setText("更多");

            typeRight.setOnClickListener(v -> {
                //通过EventBus去通知MainActivity显示第四页
                EventBus.getDefault().post(ConstantsCode.EB_START_COURSE,"3");
            });
        }

        //热门话题
        if(styleType == 7){
            GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 2);
            mTRecycler.setLayoutManager(layoutManager);

            typeTitle.setText("社区热门话题");
            typeRight.setText("更多");
            typeRight.setOnClickListener(v -> {
                //通过EventBus去通知MainActivity显示第二页
                EventBus.getDefault().post(ConstantsCode.EB_START_COURSE,"1");
            });
        }

        //随动发现
        if(styleType == 8){
            titleBox.setVisibility(GONE);
            GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 2);
            mTRecycler.setLayoutManager(layoutManager);
        }

    }

/***************************************************************************************************/

    /*
     * 设置门店数据
     * */
    public void setStore(List<V4HomeBeans.DataBean.OfflineStoreBean> mList){
        V3HomeStoreAdapter adapter = new V3HomeStoreAdapter();
        mTRecycler.setAdapter(adapter);

        adapter.setNewData(mList);

        adapter.setOnItemChildClickListener((adapter1, view, position) -> {
            Bundle bundle = new Bundle();
            bundle.putString("os_id",String.valueOf(mList.get(position).getOs_id()));
            mActivity.startActivity(StoreDetailActivity.class,bundle);
        });
    }

    /*
    * 设置和朋友一起运动
    * */
    public void setYueYundong(List<HomeIndexBeans.DataBean.SportBean> mList){
        V4YueYundongAdapter adapter = new V4YueYundongAdapter();
        mTRecycler.setAdapter(adapter);

        adapter.setNewData(mList);

        adapter.setOnItemChildClickListener((adapter1, view, position) -> {
            Bundle bundle = new Bundle();
            bundle.putString("url", Constants.WEB_ADDRESS+"/apply_reserve"+TokenSingleBean.getInstance().getWebAllParams("")+"&gs_id="+mList.get(position).getGs_id());
            bundle.putString("title","报名预约");
            if(BaseUtil.isValue(TokenSingleBean.getInstance().getM_id())){
                mActivity.startActivity(WebViewActivity.class,bundle);
            }else{
                mActivity.startActivity(VtwoVerificationLoginActivity.class,null);
            }

        });
    }

    /*
    * 设置减脂塑形
    * */
    public void setJianzhi(List<V4HomeBeans.DataBean.Cp1Bean> mList){
        V4PulAdapter adapter = new V4PulAdapter<V4HomeBeans.DataBean.Cp1Bean>() {
            @Override
            public String getImg(V4HomeBeans.DataBean.Cp1Bean item) {
                return item.getCp_img();
            }

            @Override
            public String getName(V4HomeBeans.DataBean.Cp1Bean item) {
                return item.getCp_name();
            }

            @Override
            public String getDes(V4HomeBeans.DataBean.Cp1Bean item) {
                return "教练："+item.getMi_realname();
            }
        };
        mTRecycler.setAdapter(adapter);

        adapter.setNewData(mList);

        adapter.setOnItemChildClickListener((adapter1, view, position) -> {
            Bundle bundle = new Bundle();
            bundle.putString("cp_id",String.valueOf(mList.get(position).getCp_id()));
            mActivity.startActivity(CourseDetailsActivity.class,bundle);
        });
    }

    /*
    * 力量增肌
    * */
    public void setLiLiang(List<V4HomeBeans.DataBean.Cp2Bean> mList){
        V4PulAdapter adapter = new V4PulAdapter<V4HomeBeans.DataBean.Cp2Bean>() {
            @Override
            public String getImg(V4HomeBeans.DataBean.Cp2Bean item) {
                return item.getCp_img();
            }

            @Override
            public String getName(V4HomeBeans.DataBean.Cp2Bean item) {
                return item.getCp_name();
            }

            @Override
            public String getDes(V4HomeBeans.DataBean.Cp2Bean item) {
                return "教练："+item.getMi_realname();
            }
        };
        mTRecycler.setAdapter(adapter);

        adapter.setNewData(mList);

        adapter.setOnItemChildClickListener((adapter1, view, position) -> {
            Bundle bundle = new Bundle();
            bundle.putString("cp_id",String.valueOf(mList.get(position).getCp_id()));
            mActivity.startActivity(CourseDetailsActivity.class,bundle);
        });
    }

    /*
    * 精品团课
    * */
    public void setGroup(List<V4HomeBeans.DataBean.SuperCourseBean> mList){
        V4PulAdapter adapter = new V4PulAdapter<V4HomeBeans.DataBean.SuperCourseBean>() {
            @Override
            public String getImg(V4HomeBeans.DataBean.SuperCourseBean item) {
                return item.getCs_img();
            }

            @Override
            public String getName(V4HomeBeans.DataBean.SuperCourseBean item) {
                return item.getCs_name();
            }

            @Override
            public String getDes(V4HomeBeans.DataBean.SuperCourseBean item) {
                return item.getCs_count()+"人参课";
            }
        };
        mTRecycler.setAdapter(adapter);

        adapter.setNewData(mList);

        adapter.setOnItemChildClickListener((adapter1, view, position) -> {
            Bundle bundle = new Bundle();
            bundle.putString("cs_id",String.valueOf(mList.get(position).getCs_id()));
            mActivity.startActivity(MissionDetailActivity.class,bundle);
        });
    }

    /*
    * 精品培训课
    * */
    public void setPeiXun(List<HomeIndexBeans.DataBean.CourseTrainBean> mList){
        V4PulAdapter adapter = new V4PulAdapter<HomeIndexBeans.DataBean.CourseTrainBean>() {
            @Override
            public String getImg(HomeIndexBeans.DataBean.CourseTrainBean item) {
                return item.getCt_img();
            }

            @Override
            public String getName(HomeIndexBeans.DataBean.CourseTrainBean item) {
                return item.getCt_name();
            }

            @Override
            public String getDes(HomeIndexBeans.DataBean.CourseTrainBean item) {
                return item.getOs_name();
            }
        };
        mTRecycler.setAdapter(adapter);

        adapter.setNewData(mList);

        adapter.setOnItemChildClickListener((adapter1, view, position) -> {
            Bundle bundle = new Bundle();
            bundle.putString("url", Constants.WEB_ADDRESS+"/train_detail"+TokenSingleBean.getInstance().getWebAllParams("")+"&ct_id="+mList.get(position).getCt_id());
            bundle.putString("title","培训课详情");
            if(BaseUtil.isValue(TokenSingleBean.getInstance().getM_id())){
                mActivity.startActivity(WebViewActivity.class,bundle);
            }else{
                mActivity.startActivity(VtwoVerificationLoginActivity.class,null);
            }
        });
    }

    /*
    * 商品
    * */
    public void setGoods(List<V4HomeBeans.DataBean.GoodsBean> mList){
        V3HomeGoodsAdapter adapter = new V3HomeGoodsAdapter();
        mTRecycler.setAdapter(adapter);

        adapter.setNewData(mList);
        adapter.setOnItemChildClickListener((adapter1, view, position) -> {
            Bundle bundle = new Bundle();
            bundle.putString("g_id",String.valueOf(mList.get(position).getG_id()));
            mActivity.startActivity(SgoodsDetailActivity.class,bundle);
        });
    }

    /*
     * 热门话题
     * */
    public void setTopic(List<V4HomeBeans.DataBean.CircleCategoryBean> mList){
        V4TopicAdapter adapter = new V4TopicAdapter<V4HomeBeans.DataBean.CircleCategoryBean>() {
            @Override
            public String getImg(V4HomeBeans.DataBean.CircleCategoryBean item) {
                return item.getCategory_img();
            }

            @Override
            public String getName(V4HomeBeans.DataBean.CircleCategoryBean item) {
                return item.getCategory_name();
            }

            @Override
            public String getDes(V4HomeBeans.DataBean.CircleCategoryBean item) {
                return item.getCategory_note();
            }

            @Override
            public int getNums(V4HomeBeans.DataBean.CircleCategoryBean item) {
                return item.getCount();
            }
        };
        mTRecycler.setAdapter(adapter);

        adapter.setNewData(mList);

        adapter.setOnItemChildClickListener((adapter1, view, position) -> {
            Bundle bundle = new Bundle();
            bundle.putString("circle_categoryid",String.valueOf(mList.get(position).getCategory_id()));
            mActivity.startActivity(CircleDetailsActivity.class,bundle);
        });
    }

    /*
     * 设置发现数据
     * */
    public void setFind(List<V4HomeBeans.DataBean.CircleBean> mList,Commback commback){
        V3HomeFindContentAdapter adapter = new V3HomeFindContentAdapter();
        mTRecycler.setAdapter(adapter);

        adapter.setNewData(mList);

        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @SingleClick
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                if(view.getId() == R.id.jumpDetails){
                    Bundle bundle = new Bundle();
                    bundle.putString("circle_id",String.valueOf(mList.get(position).getCircle_id()));
                    mActivity.startActivity(PostDetailsActivity.class,bundle);
                }
                if(view.getId() == R.id.attention){
                    //帖子点赞/取消点赞
                    commback.commback(position,String.valueOf(mList.get(position).getCircle_id()));
                }
            }
        });
    }

    /*
     * 获取底部---查看更多视图
     * */
    private View getFooterItem(RecyclerView v){
        View convertView = LayoutInflater
                .from(mActivity)
                .inflate(R.layout.view_bottom_look_moretext, (ViewGroup) v.getParent(), false);
        TextView lookMore = convertView.findViewById(R.id.lookMore);
        lookMore.setOnClickListener(v1 -> {
            if(styleType == 2){
                //通过EventBus去通知MainActivity显示第三页
                EventBus.getDefault().post(ConstantsCode.EB_START_COURSE,"2");
                //需要显示团课
                EventBus.getDefault().post(ConstantsCode.EB_SHOW_PRIVITE,"1");
            }
            if(styleType == 1){
                //通过EventBus去通知MainActivity显示第三页
                EventBus.getDefault().post(ConstantsCode.EB_START_COURSE,"2");
                //需要显示私教课
                EventBus.getDefault().post(ConstantsCode.EB_SHOW_PRIVITE,"0");
            }
            if(styleType == 0){
                mActivity.startActivity(CoachListActivity.class,null);
            }
        });
        return convertView;
    }


}
