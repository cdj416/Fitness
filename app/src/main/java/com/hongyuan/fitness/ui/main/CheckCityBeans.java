package com.hongyuan.fitness.ui.main;

import com.hongyuan.fitness.base.BaseBean;

public class CheckCityBeans extends BaseBean {

    /**
     * data : {"is_open":1}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * is_open : 1
         */

        private int is_open;

        public int getIs_open() {
            return is_open;
        }

        public void setIs_open(int is_open) {
            this.is_open = is_open;
        }
    }
}
