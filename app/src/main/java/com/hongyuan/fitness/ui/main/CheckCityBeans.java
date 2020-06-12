package com.hongyuan.fitness.ui.main;

import com.hongyuan.fitness.base.BaseBean;

public class CheckCityBeans extends BaseBean {


    /**
     * data : {"is_open":1,"region_code":"3505"}
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
         * region_code : 3505
         */

        private int is_open;
        private String region_code;

        public int getIs_open() {
            return is_open;
        }

        public void setIs_open(int is_open) {
            this.is_open = is_open;
        }

        public String getRegion_code() {
            return region_code;
        }

        public void setRegion_code(String region_code) {
            this.region_code = region_code;
        }
    }
}
