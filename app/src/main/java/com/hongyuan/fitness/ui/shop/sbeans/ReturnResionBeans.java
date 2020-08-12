package com.hongyuan.fitness.ui.shop.sbeans;

import com.hongyuan.fitness.base.BaseBean;

import java.util.List;

public class ReturnResionBeans extends BaseBean {

    /**
     * data : {"list":[{"reason_id":1,"reason_name":"质量问题"},{"reason_id":2,"reason_name":"配件问题"},{"reason_id":3,"reason_name":"少件/漏发"},{"reason_id":4,"reason_name":"与商品描述不符"},{"reason_id":5,"reason_name":"包装/商品残破"},{"reason_id":6,"reason_name":"发票问题"},{"reason_id":7,"reason_name":"其它"}]}
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
             * reason_id : 1
             * reason_name : 质量问题
             */

            private int reason_id;
            private String reason_name;

            public int getReason_id() {
                return reason_id;
            }

            public void setReason_id(int reason_id) {
                this.reason_id = reason_id;
            }

            public String getReason_name() {
                return reason_name;
            }

            public void setReason_name(String reason_name) {
                this.reason_name = reason_name;
            }
        }
    }
}
