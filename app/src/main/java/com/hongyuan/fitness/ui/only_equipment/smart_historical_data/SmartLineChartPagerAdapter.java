package com.hongyuan.fitness.ui.only_equipment.smart_historical_data;

import android.os.Bundle;
import android.os.Parcelable;
import android.widget.Button;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.hongyuan.fitness.base.CustomFragment;
import com.hongyuan.fitness.ui.main.TitleBean;

import java.util.ArrayList;
import java.util.List;

public class SmartLineChartPagerAdapter extends FragmentPagerAdapter {

    private List<CustomFragment> fragments;
    private List<TitleBean> beans;

    //传递的数据
    private List<BodyFatListNewBeans.DataBean.ListBean> showData;

    public SmartLineChartPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public CustomFragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return beans.get(position).getTitleName();
    }

    @Override
    public int getCount() {
        if(fragments != null){
            return fragments.size();
        }
        return 0;
    }

    /*
     * 初始化数据
     * */
    public void setData(List<BodyFatListNewBeans.DataBean.ListBean> showData) {
        this.showData = showData;

        if(beans == null){
            beans = new ArrayList<>();
        }
        if (fragments == null) {
            fragments = new ArrayList<>();
        }
        fragments.clear();
        beans.clear();
        beans.add(new TitleBean("体重",0));
        beans.add(new TitleBean("骨骼肌率",1));
        beans.add(new TitleBean("脂肪重量",2));
        beans.add(new TitleBean("体水分",3));
        beans.add(new TitleBean("去脂体重",4));
        beans.add(new TitleBean("内脏脂肪等级",5));
        beans.add(new TitleBean("蛋白质量",6));
        beans.add(new TitleBean("BMI",7));
        beans.add(new TitleBean("肥胖度",8));
        beans.add(new TitleBean("基础代谢",9));
        beans.add(new TitleBean("体脂率",10));
        beans.add(new TitleBean("脂肪控制",11));
        beans.add(new TitleBean("肌肉控制",12));

        fragments.add(new SmartLineChartFragment().setArguments("0").setMyArguments(getData(0)));
        fragments.add(new SmartLineChartFragment().setArguments("1").setMyArguments(getData(1)));
        fragments.add(new SmartLineChartFragment().setArguments("2").setMyArguments(getData(2)));
        fragments.add(new SmartLineChartFragment().setArguments("3").setMyArguments(getData(3)));
        fragments.add(new SmartLineChartFragment().setArguments("4").setMyArguments(getData(4)));
        fragments.add(new SmartLineChartFragment().setArguments("5").setMyArguments(getData(5)));
        fragments.add(new SmartLineChartFragment().setArguments("6").setMyArguments(getData(6)));
        fragments.add(new SmartLineChartFragment().setArguments("7").setMyArguments(getData(7)));
        fragments.add(new SmartLineChartFragment().setArguments("8").setMyArguments(getData(8)));
        fragments.add(new SmartLineChartFragment().setArguments("9").setMyArguments(getData(9)));
        fragments.add(new SmartLineChartFragment().setArguments("10").setMyArguments(getData(10)));
        fragments.add(new SmartLineChartFragment().setArguments("11").setMyArguments(getData(11)));
        fragments.add(new SmartLineChartFragment().setArguments("12").setMyArguments(getData(12)));

        notifyDataSetChanged();
    }

    /*
    * 组装需要的数据源
    * */
    private Bundle getData(int type){
        Bundle bundle = new Bundle();

        List<SmartHistoricalUseData> mList = new ArrayList<>();
        SmartHistoricalUseData useData;

        for(BodyFatListNewBeans.DataBean.ListBean listBean : showData ){
            switch (type){
                case 0:
                    useData = new SmartHistoricalUseData();
                    useData.setAdd_date(listBean.getAdd_date());
                    useData.setShowText(listBean.getWeight());
                    useData.setUnit("kg");
                    mList.add(useData);
                    break;
                case 1:
                    useData = new SmartHistoricalUseData();
                    useData.setAdd_date(listBean.getAdd_date());
                    useData.setShowText(listBean.getSkeletal_muscle_rate());
                    useData.setUnit("%");
                    mList.add(useData);
                    break;
                case 2:
                    useData = new SmartHistoricalUseData();
                    useData.setAdd_date(listBean.getAdd_date());
                    useData.setShowText(listBean.getFat_weight());
                    useData.setUnit("kg");
                    mList.add(useData);
                    break;
                case 3:
                    useData = new SmartHistoricalUseData();
                    useData.setAdd_date(listBean.getAdd_date());
                    useData.setShowText(listBean.getBody_water_per());
                    useData.setUnit("%");
                    mList.add(useData);
                    break;
                case 4:
                    useData = new SmartHistoricalUseData();
                    useData.setAdd_date(listBean.getAdd_date());
                    useData.setShowText(listBean.getFfm());
                    useData.setUnit("kg");
                    mList.add(useData);
                    break;
                case 5:
                    useData = new SmartHistoricalUseData();
                    useData.setAdd_date(listBean.getAdd_date());
                    useData.setShowText(listBean.getVisceral_fat_grade_1());
                    useData.setUnit("");
                    mList.add(useData);
                    break;
                case 6:
                    useData = new SmartHistoricalUseData();
                    useData.setAdd_date(listBean.getAdd_date());
                    useData.setShowText(listBean.getProtein_quality());
                    useData.setUnit("kg");
                    mList.add(useData);
                    break;
                case 7:
                    useData = new SmartHistoricalUseData();
                    useData.setAdd_date(listBean.getAdd_date());
                    useData.setShowText(listBean.getBmi());
                    useData.setUnit("");
                    mList.add(useData);
                    break;
                case 8:
                    useData = new SmartHistoricalUseData();
                    useData.setAdd_date(listBean.getAdd_date());
                    useData.setShowText(listBean.getObesity());
                    useData.setUnit("%");
                    mList.add(useData);
                    break;
                case 9:
                    useData = new SmartHistoricalUseData();
                    useData.setAdd_date(listBean.getAdd_date());
                    useData.setShowText(String.valueOf(listBean.getBasal_metabolism()));
                    useData.setUnit("kcal");
                    mList.add(useData);
                    break;
                case 10:
                    useData = new SmartHistoricalUseData();
                    useData.setAdd_date(listBean.getAdd_date());
                    useData.setShowText(String.valueOf(listBean.getBody_fat_per()));
                    useData.setUnit("%");
                    mList.add(useData);
                    break;
                case 11:
                    useData = new SmartHistoricalUseData();
                    useData.setAdd_date(listBean.getAdd_date());
                    useData.setShowText(String.valueOf(listBean.getFat_control()));
                    useData.setUnit("kg");
                    mList.add(useData);
                    break;
                case 12:
                    useData = new SmartHistoricalUseData();
                    useData.setAdd_date(listBean.getAdd_date());
                    useData.setShowText(String.valueOf(listBean.getMuscle_control()));
                    useData.setUnit("kg");
                    mList.add(useData);
                    break;
            }
        }

        bundle.putParcelableArrayList("useData", (ArrayList<? extends Parcelable>) mList);

        return bundle;
    }
}
