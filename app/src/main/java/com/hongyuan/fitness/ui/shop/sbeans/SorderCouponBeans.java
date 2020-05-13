package com.hongyuan.fitness.ui.shop.sbeans;

import com.hongyuan.fitness.base.BaseBean;

import java.util.List;

public class SorderCouponBeans extends BaseBean {

    /**
     * data : {"list":[{"cm_id":1095,"coupon_id":4,"coupon_name":"五一节大促红包","coupon_money":"10.00","min_money":"10","coupon_note":"本店大促，红包多多","start_time":1588003200,"end_time":1598457600,"start_date":"2020-04-28 00:00:00","end_date":"2020-08-27 00:00:00"}]}
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
             * cm_id : 1095
             * coupon_id : 4
             * coupon_name : 五一节大促红包
             * coupon_money : 10.00
             * min_money : 10
             * coupon_note : 本店大促，红包多多
             * start_time : 1588003200
             * end_time : 1598457600
             * start_date : 2020-04-28 00:00:00
             * end_date : 2020-08-27 00:00:00
             */

            private int cm_id;
            private int coupon_id;
            private String coupon_name;
            private String coupon_money;
            private String min_money;
            private String coupon_note;
            private int start_time;
            private int end_time;
            private String start_date;
            private String end_date;
            private boolean select;

            public boolean isSelect() {
                return select;
            }

            public void setSelect(boolean select) {
                this.select = select;
            }

            public int getCm_id() {
                return cm_id;
            }

            public void setCm_id(int cm_id) {
                this.cm_id = cm_id;
            }

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
}
