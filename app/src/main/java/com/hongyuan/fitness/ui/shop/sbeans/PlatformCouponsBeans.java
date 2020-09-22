package com.hongyuan.fitness.ui.shop.sbeans;

import com.hongyuan.fitness.base.BaseBean;

import java.util.List;

public class PlatformCouponsBeans extends BaseBean {

    /**
     * hasmore : false
     * curpage : 1
     * page_total : 1
     * data : {"list":[{"coupon_id":44,"coupon_name":"撒大声地222","coupon_for":6,"coupon_os_ids":"22","coupon_money":"3213.00","start_time":1589299200,"end_time":1696124800,"coupon_note":"但是","min_money":"3214.00","start_date":"2020/05/13 00:00","end_date":"2023/10/01 09:46","coupon_for_str":"6","is_exp":0,"osl_name":"","os_names":["湖州首玺健身湖东店"]},{"coupon_id":43,"coupon_name":"人发热","coupon_for":5,"coupon_os_ids":"21","coupon_money":"21.00","start_time":1589299200,"end_time":1696124800,"coupon_note":"但是","min_money":"21.00","start_date":"2020/05/13 00:00","end_date":"2023/10/01 09:46","coupon_for_str":"5","is_exp":0,"osl_name":"","os_names":["湖州首玺健身爱山店"]},{"coupon_id":41,"coupon_name":"测试优惠券绑定商品","coupon_for":2,"coupon_os_ids":"24","coupon_money":"12.00","start_time":1589299200,"end_time":1696124800,"coupon_note":"但是","min_money":"200.00","start_date":"2020/05/13 00:00","end_date":"2023/10/01 09:46","coupon_for_str":"会籍卡","is_exp":0,"osl_name":"","os_names":["首玺健身环城西路店"]}]}
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
             * coupon_id : 44
             * coupon_name : 撒大声地222
             * coupon_for : 6
             * coupon_os_ids : 22
             * coupon_money : 3213.00
             * start_time : 1589299200
             * end_time : 1696124800
             * coupon_note : 但是
             * min_money : 3214.00
             * start_date : 2020/05/13 00:00
             * end_date : 2023/10/01 09:46
             * coupon_for_str : 6
             * is_exp : 0
             * osl_name :
             * os_names : ["湖州首玺健身湖东店"]
             */

            private int coupon_id;
            private String coupon_name;
            private int coupon_for;
            private String coupon_os_ids;
            private String coupon_money;
            private int start_time;
            private int end_time;
            private String coupon_note;
            private String min_money;
            private String start_date;
            private String end_date;
            private String coupon_for_str;
            private int is_exp;
            private String osl_name;
            private int is_have;
            private List<String> os_names;

            public int getCoupon_id() {
                return coupon_id;
            }

            public void setCoupon_id(int coupon_id) {
                this.coupon_id = coupon_id;
            }

            public String getCoupon_name() {
                return coupon_name;
            }

            public int getIs_have() {
                return is_have;
            }

            public void setIs_have(int is_have) {
                this.is_have = is_have;
            }

            public void setCoupon_name(String coupon_name) {
                this.coupon_name = coupon_name;
            }

            public int getCoupon_for() {
                return coupon_for;
            }

            public void setCoupon_for(int coupon_for) {
                this.coupon_for = coupon_for;
            }

            public String getCoupon_os_ids() {
                return coupon_os_ids;
            }

            public void setCoupon_os_ids(String coupon_os_ids) {
                this.coupon_os_ids = coupon_os_ids;
            }

            public String getCoupon_money() {
                return coupon_money;
            }

            public void setCoupon_money(String coupon_money) {
                this.coupon_money = coupon_money;
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

            public String getCoupon_note() {
                return coupon_note;
            }

            public void setCoupon_note(String coupon_note) {
                this.coupon_note = coupon_note;
            }

            public String getMin_money() {
                return min_money;
            }

            public void setMin_money(String min_money) {
                this.min_money = min_money;
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

            public String getCoupon_for_str() {
                return coupon_for_str;
            }

            public void setCoupon_for_str(String coupon_for_str) {
                this.coupon_for_str = coupon_for_str;
            }

            public int getIs_exp() {
                return is_exp;
            }

            public void setIs_exp(int is_exp) {
                this.is_exp = is_exp;
            }

            public String getOsl_name() {
                return osl_name;
            }

            public void setOsl_name(String osl_name) {
                this.osl_name = osl_name;
            }

            public List<String> getOs_names() {
                return os_names;
            }

            public void setOs_names(List<String> os_names) {
                this.os_names = os_names;
            }
        }
    }
}
