package com.hongyuan.fitness.ui.shop.sbeans;

import com.hongyuan.fitness.base.BaseBean;

import java.util.List;

public class CancelOrderReasonBeans extends BaseBean {

    /**
     * data : {"list":[{"reason_id":1,"name":"我不想买了"},{"reason_id":1,"name":"价格有点贵"},{"reason_id":2,"name":"规格/款式/数量拍错"},{"reason_id":3,"name":"收货地址写错"},{"reason_id":4,"name":"暂时不需要了"},{"reason_id":5,"name":"其它"}]}
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
             * name : 我不想买了
             */

            private int reason_id;
            private String name;

            public int getReason_id() {
                return reason_id;
            }

            public void setReason_id(int reason_id) {
                this.reason_id = reason_id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }
    }
}
