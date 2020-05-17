package com.hongyuan.fitness.ui.main;

import com.hongyuan.fitness.base.BaseBean;

import java.util.List;

public class OpenCitysBeans extends BaseBean {

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

        public static class ListBean {
            /**
             * region_code : 3505
             * region_name : 湖州市
             */

            private String region_code;
            private String region_name;

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
        }
    }
}
