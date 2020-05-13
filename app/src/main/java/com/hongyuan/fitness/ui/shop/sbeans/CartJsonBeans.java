package com.hongyuan.fitness.ui.shop.sbeans;

import java.util.List;

public class CartJsonBeans {

    /**
     * store_id : 42
     * goods_list : [{"gp_id":141,"buy_num":2,"cart_id":4},{"gp_id":142,"buy_num":1,"cart_id":5}]
     */

    private int store_id;
    private List<GoodsListBean> goods_list;

    public int getStore_id() {
        return store_id;
    }

    public void setStore_id(int store_id) {
        this.store_id = store_id;
    }

    public List<GoodsListBean> getGoods_list() {
        return goods_list;
    }

    public void setGoods_list(List<GoodsListBean> goods_list) {
        this.goods_list = goods_list;
    }

    public static class GoodsListBean {
        /**
         * gp_id : 141
         * buy_num : 2
         * cart_id : 4
         */

        private int gp_id;
        private int buy_num;
        private int cart_id;

        public int getGp_id() {
            return gp_id;
        }

        public void setGp_id(int gp_id) {
            this.gp_id = gp_id;
        }

        public int getBuy_num() {
            return buy_num;
        }

        public void setBuy_num(int buy_num) {
            this.buy_num = buy_num;
        }

        public int getCart_id() {
            return cart_id;
        }

        public void setCart_id(int cart_id) {
            this.cart_id = cart_id;
        }
    }
}
