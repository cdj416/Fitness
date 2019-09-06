package com.hongyuan.fitness.ui.main.main_home.recommend;

import com.hongyuan.fitness.base.BaseBean;

import java.util.List;

public class HomeBoutiqueGroupBean extends BaseBean {

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * cs_name : 跑步机超课
         * cs_id : 1
         * cs_img : http://88php.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190620/9cfc42dc3a89089458a5932eb2c0cb74a4ec5670_600x338.jpg
         * si_name : 跑步机
         * distance_um : 2535
         */

        private String cs_name;
        private int cs_id;
        private String cs_img;
        private String si_name;
        private int distance_um;

        public String getCs_name() {
            return cs_name;
        }

        public void setCs_name(String cs_name) {
            this.cs_name = cs_name;
        }

        public int getCs_id() {
            return cs_id;
        }

        public void setCs_id(int cs_id) {
            this.cs_id = cs_id;
        }

        public String getCs_img() {
            return cs_img;
        }

        public void setCs_img(String cs_img) {
            this.cs_img = cs_img;
        }

        public String getSi_name() {
            return si_name;
        }

        public void setSi_name(String si_name) {
            this.si_name = si_name;
        }

        public int getDistance_um() {
            return distance_um;
        }

        public void setDistance_um(int distance_um) {
            this.distance_um = distance_um;
        }
    }
}
