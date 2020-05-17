package com.hongyuan.fitness.ui.shop.sbeans;

import com.hongyuan.fitness.base.BaseBean;

import java.util.List;

public class IncomeDetailsBeans extends BaseBean {

    /**
     * hasmore : false
     * curpage : 1
     * page_total : 1
     * data : {"list":[{"o_id":19487,"o_sn":"GOODS2020051615896015655ebf651de219d","o_pay_time":0,"o_time":1589601565,"o_state":1,"o_pay_state":0,"o_type_code":"o_goods","o_out_id":1588,"store_id":42,"o_deliver_way":1,"confirm_time":0,"add_date":"2020-05-16 11:59:25","update_date":"2020-05-16 11:59:25","o_pay_state_str":"未支付","o_state_str":"未支付","income_state_str":"待支付","op_income":"1.50"},{"o_id":19381,"o_sn":"GOODS2020051415894487885ebd1054a909e","o_pay_time":0,"o_time":1589448788,"o_state":1,"o_pay_state":0,"o_type_code":"o_goods","o_out_id":1580,"store_id":42,"o_deliver_way":1,"confirm_time":0,"add_date":"2020-05-14 17:33:08","update_date":"2020-05-14 17:33:08","o_pay_state_str":"未支付","o_state_str":"未支付","income_state_str":"待支付","op_income":"1.20"}]}
     */

    private boolean hasmore;
    private int curpage;
    private int page_total;
    private DataBean data;

    public boolean isHasmore() {
        return hasmore;
    }

    public void setHasmore(boolean hasmore) {
        this.hasmore = hasmore;
    }

    public int getCurpage() {
        return curpage;
    }

    public void setCurpage(int curpage) {
        this.curpage = curpage;
    }

    public int getPage_total() {
        return page_total;
    }

    public void setPage_total(int page_total) {
        this.page_total = page_total;
    }

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
             * o_id : 19487
             * o_sn : GOODS2020051615896015655ebf651de219d
             * o_pay_time : 0
             * o_time : 1589601565
             * o_state : 1
             * o_pay_state : 0
             * o_type_code : o_goods
             * o_out_id : 1588
             * store_id : 42
             * o_deliver_way : 1
             * confirm_time : 0
             * add_date : 2020-05-16 11:59:25
             * update_date : 2020-05-16 11:59:25
             * o_pay_state_str : 未支付
             * o_state_str : 未支付
             * income_state_str : 待支付
             * op_income : 1.50
             */

            private int o_id;
            private String o_sn;
            private int o_pay_time;
            private int o_time;
            private int o_state;
            private int o_pay_state;
            private String o_type_code;
            private int o_out_id;
            private int store_id;
            private int o_deliver_way;
            private int confirm_time;
            private String add_date;
            private String update_date;
            private String o_pay_state_str;
            private String o_state_str;
            private String income_state_str;
            private String op_income;

            public int getO_id() {
                return o_id;
            }

            public void setO_id(int o_id) {
                this.o_id = o_id;
            }

            public String getO_sn() {
                return o_sn;
            }

            public void setO_sn(String o_sn) {
                this.o_sn = o_sn;
            }

            public int getO_pay_time() {
                return o_pay_time;
            }

            public void setO_pay_time(int o_pay_time) {
                this.o_pay_time = o_pay_time;
            }

            public int getO_time() {
                return o_time;
            }

            public void setO_time(int o_time) {
                this.o_time = o_time;
            }

            public int getO_state() {
                return o_state;
            }

            public void setO_state(int o_state) {
                this.o_state = o_state;
            }

            public int getO_pay_state() {
                return o_pay_state;
            }

            public void setO_pay_state(int o_pay_state) {
                this.o_pay_state = o_pay_state;
            }

            public String getO_type_code() {
                return o_type_code;
            }

            public void setO_type_code(String o_type_code) {
                this.o_type_code = o_type_code;
            }

            public int getO_out_id() {
                return o_out_id;
            }

            public void setO_out_id(int o_out_id) {
                this.o_out_id = o_out_id;
            }

            public int getStore_id() {
                return store_id;
            }

            public void setStore_id(int store_id) {
                this.store_id = store_id;
            }

            public int getO_deliver_way() {
                return o_deliver_way;
            }

            public void setO_deliver_way(int o_deliver_way) {
                this.o_deliver_way = o_deliver_way;
            }

            public int getConfirm_time() {
                return confirm_time;
            }

            public void setConfirm_time(int confirm_time) {
                this.confirm_time = confirm_time;
            }

            public String getAdd_date() {
                return add_date;
            }

            public void setAdd_date(String add_date) {
                this.add_date = add_date;
            }

            public String getUpdate_date() {
                return update_date;
            }

            public void setUpdate_date(String update_date) {
                this.update_date = update_date;
            }

            public String getO_pay_state_str() {
                return o_pay_state_str;
            }

            public void setO_pay_state_str(String o_pay_state_str) {
                this.o_pay_state_str = o_pay_state_str;
            }

            public String getO_state_str() {
                return o_state_str;
            }

            public void setO_state_str(String o_state_str) {
                this.o_state_str = o_state_str;
            }

            public String getIncome_state_str() {
                return income_state_str;
            }

            public void setIncome_state_str(String income_state_str) {
                this.income_state_str = income_state_str;
            }

            public String getOp_income() {
                return op_income;
            }

            public void setOp_income(String op_income) {
                this.op_income = op_income;
            }
        }
    }
}
