package com.hongyuan.fitness.ui.main;

import com.chad.library.adapter.base.entity.AbstractExpandableItem;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.hongyuan.fitness.base.BaseBean;
import com.hongyuan.fitness.util.BaseUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AllCitysBeans extends BaseBean {

    /**
     * data : {"list":[{"region_code":"3505","region_name":"湖州市"}]}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private List<ListBean> list;

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean implements MultiItemEntity{
            /**
             * region_code : 3505
             * region_name : 湖州市
             */

            private String region_code;
            private String region_name;
            private String letter;

            public ListBean() {
            }

            public ListBean(String region_code, String region_name) {
                this.region_code = region_code;
                this.region_name = region_name;
            }

            public String getLetter() {
                return letter;
            }

            public void setLetter(String letter) {
                this.letter = letter;
            }

            public String getRegion_code() {
                return region_code;
            }

            public void setRegion_code(String region_code) {
                this.region_code = region_code;
            }

            public String getRegion_name() {
                return region_name;
            }

            public void setRegion_name(String region_name) {
                this.region_name = region_name;
            }

            @Override
            public int getItemType() {
                return 1;
            }
        }

        /******************************************数据从组*********************************************/


        public static class HeardBeans extends AbstractExpandableItem<ListBean> implements MultiItemEntity{

            private String title;

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            @Override
            public int getLevel() {
                return 0;
            }

            @Override
            public int getItemType() {
                return 0;
            }
        }


        private List<MultiItemEntity> mList = new ArrayList<>();

        public   List<MultiItemEntity> getmList(){
            //按照字母排序
            //Collections.sort(list, (city, t1) -> city.getRegion_name().compareTo(t1.getRegion_name()));

            //插入头部数据
            for(ListBean bean : list){
                //String letter = PinyinUtils.getFirstLetter(PinyinUtils.getPinYinFirstLetter(bean.getRegion_name()));

                HeardBeans heardBeans = isHave(bean.getLetter());
                if(BaseUtil.isValue(heardBeans)){
                    heardBeans.addSubItem(bean);
                }else{
                    heardBeans = new HeardBeans();
                    heardBeans.setTitle(bean.getLetter());
                    heardBeans.addSubItem(bean);
                    mList.add(heardBeans);
                }
            }

            //用之前先排序
            Collections.sort(mList, (o1, o2) -> {
                if(o1 instanceof HeardBeans){
                    if(o2 instanceof ListBean){
                        return ((HeardBeans) o1).getTitle().compareTo(((ListBean) o2).getLetter());
                    }else{
                        return ((HeardBeans) o1).getTitle().compareTo(((HeardBeans)o2).getTitle());
                    }
                }else{
                    if(o2 instanceof ListBean){
                        return ((ListBean) o1).getLetter().compareTo(((ListBean) o2).getLetter());
                    }else{
                        return ((ListBean) o1).getLetter().compareTo(((HeardBeans)o2).getTitle());
                    }
                }
            });
            return mList;
        }

        //根据首字母查询头部实体类
        private HeardBeans isHave(String letter){
            for(MultiItemEntity entity : mList){
                if(entity instanceof HeardBeans){
                    HeardBeans heardBeans = (HeardBeans)entity;
                    if(letter.equals(heardBeans.getTitle())){
                        return heardBeans;
                    }
                }
            }

            return null;
        }


    }
}
