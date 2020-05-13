package com.hongyuan.fitness.ui.shop.sbeans;

import java.util.List;

public class OrderJsonBeans {


    /**
     * goods_list : [{"gp_id":141,"buy_num":2,"cart_id":4}]
     * deliver_way : 2
     * zt_address_id : 1
     * store_id : 42
     * cm_id : 0
     * op_note : 尽快发货
     */

    private int deliver_way;
    private int zt_address_id;
    private int store_id;
    private int cm_id;
    private String op_note;
    private List<GoodsListBean> goods_list;

    public int getDeliver_way() {
        return deliver_way;
    }

    public void setDeliver_way(int deliver_way) {
        this.deliver_way = deliver_way;
    }

    public int getZt_address_id() {
        return zt_address_id;
    }

    public void setZt_address_id(int zt_address_id) {
        this.zt_address_id = zt_address_id;
    }

    public int getStore_id() {
        return store_id;
    }

    public void setStore_id(int store_id) {
        this.store_id = store_id;
    }

    public int getCm_id() {
        return cm_id;
    }

    public void setCm_id(int cm_id) {
        this.cm_id = cm_id;
    }

    public String getOp_note() {
        return op_note;
    }

    public void setOp_note(String op_note) {
        this.op_note = op_note;
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
