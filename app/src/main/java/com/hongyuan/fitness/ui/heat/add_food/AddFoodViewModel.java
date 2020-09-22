package com.hongyuan.fitness.ui.heat.add_food;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;
import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.BaseBean;
import com.hongyuan.fitness.base.Constants;
import com.hongyuan.fitness.base.ConstantsCode;
import com.hongyuan.fitness.base.Controller;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.base.CustomViewModel;
import com.hongyuan.fitness.custom_view.AddFoodView;
import com.hongyuan.fitness.databinding.ActivityAddFoodBinding;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

public class AddFoodViewModel extends CustomViewModel implements AddFoodView.SelectData{

    private ActivityAddFoodBinding binding;
    private AddFoodViewPagerAdapter meunAdapter;

    private int selectNum = 0;
    private String fe_date;
    private String fe_type;
    private String eatingStr = "";

    public AddFoodViewModel(CustomActivity mActivity, ActivityAddFoodBinding binding) {
        super(mActivity);
        this.binding = binding;
        initView();
        lazyLoad();
    }

    @Override
    protected void initView() {
        fe_date = getBundle().getString("fe_date");
        fe_type = getBundle().getString("fe_type");

        List<AddFoodMenuBean.DataBean> mList = new ArrayList<>();
        mList.add(new AddFoodMenuBean.DataBean(0,"常见"));
        meunAdapter = new AddFoodViewPagerAdapter(mActivity,mActivity.getSupportFragmentManager(),mList,this);
        binding.mViewPager.setAdapter(meunAdapter);
        binding.layoutMenu.setupWithViewPager(binding.mViewPager);
        binding.mViewPager.setCurrentItem(0);

        mActivity.getMainTitle().getRightView().setOnClickListener(v ->
            addFood()
        );

        binding.searchEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                EventBus.getDefault().post(ConstantsCode.EB_FOOD_SEARCH,s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    /*
     * 自定义菜单栏样式
     * */
    private void setTabViw(AddFoodMenuBean bean){
        //设置自定义tab
        for (int i = 0; i < bean.getData().size(); i++){
            TabLayout.Tab tab = binding.layoutMenu.getTabAt(i);
            if (tab != null) {
                tab.setCustomView(meunAdapter.getTabView(i));
            }
        }
        //设置第一页为选中状态时的tab文字颜色为红色
        View view=binding.layoutMenu.getTabAt(0).getCustomView();
        TextView textView= view.findViewById(R.id.name);
        LinearLayout linearLayout = view.findViewById(R.id.box);
        textView.setTextColor(0xffffffff);
        linearLayout.setBackgroundResource(R.drawable.shape_radius16_ef5b48);
    }

    /*
     * tabview监听
     * */
    private void getTabListener(){
        //监听事件
        binding.layoutMenu.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                //选中了tab的逻辑
                View view=tab.getCustomView();
                TextView textView= view.findViewById(R.id.name);
                LinearLayout linearLayout = view.findViewById(R.id.box);
                textView.setTextColor(0xffffffff);
                linearLayout.setBackgroundResource(R.drawable.shape_radius16_ef5b48);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                //未选中tab的逻辑
                View view=tab.getCustomView();
                TextView textView= view.findViewById(R.id.name);
                LinearLayout linearLayout = view.findViewById(R.id.box);
                textView.setTextColor(0xff5F5F5F);
                linearLayout.setBackgroundResource(R.drawable.shape_radius16_ffffff_stroke_979797);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                //再次选中tab的逻辑
            }
        });
    }

    /*
    * 记录热量
    * */
    private void addFood(){
        clearParams().setParams("eating_str",eatingStr);
        Controller.myRequest(ConstantsCode.ADD_FOOD_EATING,Constants.ADD_FOOD_EATING,Controller.TYPE_POST,getParams(), BaseBean.class,this);
    }

    @Override
    protected void lazyLoad() {
        Controller.myRequest(Constants.GET_FOOD_CATEGORY_LIST,Controller.TYPE_POST,getParams(), AddFoodMenuBean.class,this);
    }

    @Override
    public void onSuccess(Object data) {
        super.onSuccess(data);

        if(data instanceof AddFoodMenuBean && isSuccess(data)){
            AddFoodMenuBean menuBean = (AddFoodMenuBean)data;
            AddFoodMenuBean.DataBean firstData = new AddFoodMenuBean.DataBean();
            firstData.setFc_id(0);
            firstData.setFc_name("常见");
            menuBean.getData().add(0,firstData);
            meunAdapter.setData(menuBean.getData());
            binding.mViewPager.setOffscreenPageLimit(menuBean.getData().size());


            //初始化自定义样式
            setTabViw(menuBean);
            getTabListener();
        }
    }

    @Override
    public void onSuccess(int code, Object data) {
        super.onSuccess(code,data);

        if(code == ConstantsCode.ADD_FOOD_EATING){
            mActivity.showSuccess("添加成功！");
            EventBus.getDefault().post(ConstantsCode.EB_ADD_FOOD_SUSSESS,"添加成功");
        }
    }

    /*
    * 选择结果回调
    * */
    @Override
    public void retrunEatingStr(String f_id,String fu_id,String fe_num,String selNum) {

        selectNum++;
        View mView = LayoutInflater.from(mActivity).inflate(R.layout.item_title_right_view, null);
        TextView tv = mView.findViewById(R.id.showNum);
        tv.setText(String.valueOf(selectNum));

        mActivity.getMainTitle().addRightContentView(mView);
        //String eating_str = data.getF_id()+","+fuId+","+fe_num;
        //拼接参数
        if(selectNum == 1){
            eatingStr += f_id+","+fu_id+","+fe_num+","+fe_type+","+fe_date+","+selNum;
        }else{
            eatingStr += ";"+f_id+","+fu_id+","+fe_num+ ","+fe_type+","+fe_date+","+selNum;
        }

    }
}
