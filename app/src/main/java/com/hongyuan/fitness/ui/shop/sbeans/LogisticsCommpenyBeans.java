package com.hongyuan.fitness.ui.shop.sbeans;

import com.hongyuan.fitness.base.BaseBean;

import java.util.List;

public class LogisticsCommpenyBeans extends BaseBean {

    /**
     * data : {"list":[{"id":1,"name":"百世快递","code":"ht"},{"id":2,"name":"京东快递","code":"jd"},{"id":3,"name":"申通快递","code":"sto"},{"id":4,"name":"EMS","code":"ems"},{"id":5,"name":"中通快递","code":"zt"},{"id":6,"name":"顺丰速运","code":"sf"},{"id":7,"name":"圆通","code":"yt"},{"id":8,"name":"韵达快递","code":"yd"}]}
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
             * id : 1
             * name : 百世快递
             * code : ht
             */

            private int id;
            private String name;
            private String code;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getCode() {
                return code;
            }

            public void setCode(String code) {
                this.code = code;
            }
        }
    }
}
