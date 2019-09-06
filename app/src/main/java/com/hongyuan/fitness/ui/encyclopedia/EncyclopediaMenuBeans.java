package com.hongyuan.fitness.ui.encyclopedia;

import com.hongyuan.fitness.base.BaseBean;

import java.util.List;

public class EncyclopediaMenuBeans extends BaseBean {

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * baike_categoryid : 3
         * baike_category_name : 减肥
         */

        private int baike_categoryid;
        private String baike_category_name;
        private boolean select;

        public DataBean(int baike_categoryid, String baike_category_name, boolean select) {
            this.baike_categoryid = baike_categoryid;
            this.baike_category_name = baike_category_name;
            this.select = select;
        }

        public boolean isSelect() {
            return select;
        }

        public void setSelect(boolean select) {
            this.select = select;
        }

        public int getBaike_categoryid() {
            return baike_categoryid;
        }

        public void setBaike_categoryid(int baike_categoryid) {
            this.baike_categoryid = baike_categoryid;
        }

        public String getBaike_category_name() {
            return baike_category_name;
        }

        public void setBaike_category_name(String baike_category_name) {
            this.baike_category_name = baike_category_name;
        }
    }
}
