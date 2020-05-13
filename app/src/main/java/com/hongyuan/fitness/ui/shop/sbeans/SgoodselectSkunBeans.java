package com.hongyuan.fitness.ui.shop.sbeans;

import com.hongyuan.fitness.base.BaseBean;

public class SgoodselectSkunBeans extends BaseBean {

    /**
     * data : {"item":{"gp_id":142,"gp_stock":200,"gp_price":"105.00","gp_point":0}}
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
         * item : {"gp_id":142,"gp_stock":200,"gp_price":"105.00","gp_point":0}
         */

        private ItemBean item;

        public ItemBean getItem() {
            return item;
        }

        public void setItem(ItemBean item) {
            this.item = item;
        }

        public static class ItemBean {
            /**
             * gp_id : 142
             * gp_stock : 200
             * gp_price : 105.00
             * gp_point : 0
             */

            private int gp_id;
            private int gp_stock;
            private String gp_price;
            private int gp_point;

            public int getGp_id() {
                return gp_id;
            }

            public void setGp_id(int gp_id) {
                this.gp_id = gp_id;
            }

            public int getGp_stock() {
                return gp_stock;
            }

            public void setGp_stock(int gp_stock) {
                this.gp_stock = gp_stock;
            }

            public String getGp_price() {
                return gp_price;
            }

            public void setGp_price(String gp_price) {
                this.gp_price = gp_price;
            }

            public int getGp_point() {
                return gp_point;
            }

            public void setGp_point(int gp_point) {
                this.gp_point = gp_point;
            }
        }
    }
}
