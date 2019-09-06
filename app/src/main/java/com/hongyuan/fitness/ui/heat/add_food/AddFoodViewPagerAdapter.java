package com.hongyuan.fitness.ui.heat.add_food;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.CustomFragment;
import com.hongyuan.fitness.custom_view.AddFoodView;

import java.util.ArrayList;
import java.util.List;

public class AddFoodViewPagerAdapter extends FragmentPagerAdapter {

    private List<CustomFragment> fragments;
    private List<AddFoodMenuBean.DataBean> beans;
    private Context mContext;
    private AddFoodView.SelectData selectData;

    public AddFoodViewPagerAdapter(Context mContext, FragmentManager fm, List<AddFoodMenuBean.DataBean> beans, AddFoodView.SelectData selectData) {
        super(fm);
        this.beans = beans;
        this.mContext = mContext;
        this.selectData = selectData;
        setData(beans);
    }

    @Override
    public CustomFragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return beans.get(position).getFc_name();
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    /*
     * 初始化数据
     * */
    public void setData(List<AddFoodMenuBean.DataBean> beans) {
        if(beans == null){
            return;
        }
        if (fragments == null) {
            fragments = new ArrayList<>();
        }
        this.beans.clear();
        this.beans = beans;
        for (int i = 0 ; i < beans.size() ; i++){
            fragments.add(new AddFoodFragment().setSelectData(selectData).setArguments(String.valueOf(beans.get(i).getFc_id())));
        }

        notifyDataSetChanged();
    }

    public View getTabView(int position) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_menu_private_lessons, null);
        TextView textView=view.findViewById(R.id.name);
        textView.setText(beans.get(position).getFc_name());
        return view;
    }
}
