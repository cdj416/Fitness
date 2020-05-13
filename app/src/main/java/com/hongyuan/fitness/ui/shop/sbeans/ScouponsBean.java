package com.hongyuan.fitness.ui.shop.sbeans;

import com.hongyuan.fitness.base.BaseBean;

import java.util.List;

public class ScouponsBean extends BaseBean {

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * coupon_id : 3
         * coupon_name : 店铺通用红包
         * coupon_money : 20.00
         * store_id : 42
         * min_money : 20.00
         * coupon_note : 本店大促，红包多多
         * amount_num : 0
         * start_time : 1588003200
         * end_time : 1598457600
         * start_date : 2020-04-28 00:00:00
         * end_date : 2020-08-27 00:00:00
         */

        private int coupon_id;
        private String coupon_name;
        private String coupon_money;
        private int store_id;
        private String min_money;
        private String coupon_note;
        private int amount_num;
        private int start_time;
        private int end_time;
        private String start_date;
        private String end_date;

        public int getCoupon_id() {
            return coupon_id;
        }

        public void setCoupon_id(int coupon_id) {
            this.coupon_id = coupon_id;
        }

        public String getCoupon_name() {
            return coupon_name;
        }

        public void setCoupon_name(String coupon_name) {
            this.coupon_name = coupon_name;
        }

        public String getCoupon_money() {
            return coupon_money;
        }

        public void setCoupon_money(String coupon_money) {
            this.coupon_money = coupon_money;
        }

        public int getStore_id() {
            return store_id;
        }

        public void setStore_id(int store_id) {
            this.store_id = store_id;
        }

        public String getMin_money() {
            return min_money;
        }

        public void setMin_money(String min_money) {
            this.min_money = min_money;
        }

        public String getCoupon_note() {
            return coupon_note;
        }

        public void setCoupon_note(String coupon_note) {
            this.coupon_note = coupon_note;
        }

        public int getAmount_num() {
            return amount_num;
        }

        public void setAmount_num(int amount_num) {
            this.amount_num = amount_num;
        }

        public int getStart_time() {
            return start_time;
        }

        public void setStart_time(int start_time) {
            this.start_time = start_time;
        }

        public int getEnd_time() {
            return end_time;
        }

        public void setEnd_time(int end_time) {
            this.end_time = end_time;
        }

        public String getStart_date() {
            return start_date;
        }

        public void setStart_date(String start_date) {
            this.start_date = start_date;
        }

        public String getEnd_date() {
            return end_date;
        }

        public void setEnd_date(String end_date) {
            this.end_date = end_date;
        }
    }
}
