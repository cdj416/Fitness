package com.hongyuan.fitness.custom_view.select_address;

import com.hongyuan.fitness.base.BaseBean;

import java.util.List;

public class SelectAddressLeftBeans extends BaseBean {

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * region_id : 1
         * region_name : 北京市
         * region_code : 10
         * father_id : 0
         */

        private int region_id;
        private String region_name;
        private String region_code;
        private int father_id;
        private boolean select;

        public boolean isSelect() {
            return select;
        }

        public void setSelect(boolean select) {
            this.select = select;
        }

        public int getRegion_id() {
            return region_id;
        }

        public void setRegion_id(int region_id) {
            this.region_id = region_id;
        }

        public String getRegion_name() {
            return region_name;
        }

        public void setRegion_name(String region_name) {
            this.region_name = region_name;
        }

        public String getRegion_code() {
            return region_code;
        }

        public void setRegion_code(String region_code) {
            this.region_code = region_code;
        }

        public int getFather_id() {
            return father_id;
        }

        public void setFather_id(int father_id) {
            this.father_id = father_id;
        }
    }
}
