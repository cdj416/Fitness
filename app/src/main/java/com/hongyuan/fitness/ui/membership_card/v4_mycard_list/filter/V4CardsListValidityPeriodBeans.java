package com.hongyuan.fitness.ui.membership_card.v4_mycard_list.filter;

import com.hongyuan.fitness.base.BaseBean;

import java.util.List;

public class V4CardsListValidityPeriodBeans extends BaseBean {

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * ct_id : 1
         * ct_name : 年卡
         */



        private int ct_id;
        private String ct_name;
        private boolean select;

        public DataBean(int ct_id, String ct_name, boolean select) {
            this.ct_id = ct_id;
            this.ct_name = ct_name;
            this.select = select;
        }

        public boolean isSelect() {
            return select;
        }

        public void setSelect(boolean select) {
            this.select = select;
        }

        public int getCt_id() {
            return ct_id;
        }

        public void setCt_id(int ct_id) {
            this.ct_id = ct_id;
        }

        public String getCt_name() {
            return ct_name;
        }

        public void setCt_name(String ct_name) {
            this.ct_name = ct_name;
        }
    }
}
