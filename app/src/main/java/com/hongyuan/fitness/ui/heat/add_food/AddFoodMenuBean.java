package com.hongyuan.fitness.ui.heat.add_food;

import com.hongyuan.fitness.base.BaseBean;

import java.util.List;

public class AddFoodMenuBean extends BaseBean {

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {


        /**
         * fc_id : 3
         * fc_name : 主食
         */

        public DataBean() {
        }

        public DataBean(int fc_id, String fc_name) {
            this.fc_id = fc_id;
            this.fc_name = fc_name;
        }

        private int fc_id;
        private String fc_name;

        public int getFc_id() {
            return fc_id;
        }

        public void setFc_id(int fc_id) {
            this.fc_id = fc_id;
        }

        public String getFc_name() {
            return fc_name;
        }

        public void setFc_name(String fc_name) {
            this.fc_name = fc_name;
        }
    }
}
