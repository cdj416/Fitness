package com.hongyuan.fitness.ui.shop.sbeans;

import com.hongyuan.fitness.base.BaseBean;

public class CreateOrderBeans extends BaseBean {


    /**
     * data : {"o_ids":{"money_o_ids":"19385","point_o_ids":"","free_o_ids":""}}
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
         * o_ids : {"money_o_ids":"19385","point_o_ids":"","free_o_ids":""}
         */

        private OIdsBean o_ids;

        public OIdsBean getO_ids() {
            return o_ids;
        }

        public void setO_ids(OIdsBean o_ids) {
            this.o_ids = o_ids;
        }

        public static class OIdsBean {
            /**
             * money_o_ids : 19385
             * point_o_ids :
             * free_o_ids :
             */

            private String money_o_ids;
            private String point_o_ids;
            private String free_o_ids;

            public String getMoney_o_ids() {
                return money_o_ids;
            }

            public void setMoney_o_ids(String money_o_ids) {
                this.money_o_ids = money_o_ids;
            }

            public String getPoint_o_ids() {
                return point_o_ids;
            }

            public void setPoint_o_ids(String point_o_ids) {
                this.point_o_ids = point_o_ids;
            }

            public String getFree_o_ids() {
                return free_o_ids;
            }

            public void setFree_o_ids(String free_o_ids) {
                this.free_o_ids = free_o_ids;
            }
        }
    }
}
