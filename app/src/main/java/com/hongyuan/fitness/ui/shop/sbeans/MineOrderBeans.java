package com.hongyuan.fitness.ui.shop.sbeans;

import com.hongyuan.fitness.base.BaseBean;

import java.util.List;

public class MineOrderBeans extends BaseBean {

    /**
     * hasmore : true
     * curpage : 1
     * page_total : 19
     * data : {"list":[{"o_id":98,"o_sn":"GOODS2019072615641074175d3a6299e2694","o_pay_time":0,"o_name":"dfasfdsadfsfds","o_img":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190718/fe2319b1e3cd155c4c16714eb1f54a18b524d89a_1242x744.jpg","o_money":"0.01","o_point":0,"o_time":1564107417,"o_state":0,"o_pay_state":2,"o_type_code":"o_goods","o_out_id":83,"add_date":"2019-07-26 10:16:57","pay_date":"1970-01-01 08:00:00","o_num":1,"o_price":"0.01","op_point":0,"sku":["M","白色"]},{"o_id":97,"o_sn":"GOODS2019072615641058945d3a5ca6cb62b","o_pay_time":0,"o_name":"dfasfdsadfsfds","o_img":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190718/fe2319b1e3cd155c4c16714eb1f54a18b524d89a_1242x744.jpg","o_money":"0.00","o_point":2,"o_time":1564105894,"o_state":0,"o_pay_state":2,"o_type_code":"o_goods","o_out_id":82,"add_date":"2019-07-26 09:51:34","pay_date":"1970-01-01 08:00:00","o_num":1,"o_price":"0.00","op_point":2,"sku":["M","白色"]}]}
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
             * o_id : 98
             * o_sn : GOODS2019072615641074175d3a6299e2694
             * o_pay_time : 0
             * o_name : dfasfdsadfsfds
             * o_img : http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190718/fe2319b1e3cd155c4c16714eb1f54a18b524d89a_1242x744.jpg
             * o_money : 0.01
             * o_point : 0
             * o_time : 1564107417
             * o_state : 0
             * o_pay_state : 2
             * o_type_code : o_goods
             * o_out_id : 83
             * add_date : 2019-07-26 10:16:57
             * pay_date : 1970-01-01 08:00:00
             * o_num : 1
             * o_price : 0.01
             * op_point : 0
             * sku : ["M","白色"]
             */

            private int o_id;
            private String o_sn;
            private int o_pay_time;
            private String o_name;
            private String o_img;
            private String o_money;
            private int o_point;
            private int o_time;
            private int o_state;
            private int o_pay_state;
            private String o_type_code;
            private int o_out_id;
            private String add_date;
            private String pay_date;
            private int o_num;
            private String o_price;
            private int op_point;
            private List<String> sku;
            private String o_coupon_money;

            public String getO_coupon_money() {
                return o_coupon_money;
            }

            public void setO_coupon_money(String o_coupon_money) {
                this.o_coupon_money = o_coupon_money;
            }

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

            public String getO_name() {
                return o_name;
            }

            public void setO_name(String o_name) {
                this.o_name = o_name;
            }

            public String getO_img() {
                return o_img;
            }

            public void setO_img(String o_img) {
                this.o_img = o_img;
            }

            public String getO_money() {
                return o_money;
            }

            public void setO_money(String o_money) {
                this.o_money = o_money;
            }

            public int getO_point() {
                return o_point;
            }

            public void setO_point(int o_point) {
                this.o_point = o_point;
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

            public String getAdd_date() {
                return add_date;
            }

            public void setAdd_date(String add_date) {
                this.add_date = add_date;
            }

            public String getPay_date() {
                return pay_date;
            }

            public void setPay_date(String pay_date) {
                this.pay_date = pay_date;
            }

            public int getO_num() {
                return o_num;
            }

            public void setO_num(int o_num) {
                this.o_num = o_num;
            }

            public String getO_price() {
                return o_price;
            }

            public void setO_price(String o_price) {
                this.o_price = o_price;
            }

            public int getOp_point() {
                return op_point;
            }

            public void setOp_point(int op_point) {
                this.op_point = op_point;
            }

            public List<String> getSku() {
                return sku;
            }

            public void setSku(List<String> sku) {
                this.sku = sku;
            }
        }
    }
}
