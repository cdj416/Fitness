package com.hongyuan.fitness.ui.person.my_coupon;

import com.hongyuan.fitness.base.BaseBean;

import java.io.Serializable;
import java.util.List;

public class CouponListBeans extends BaseBean implements Serializable {


    /**
     * hasmore : false
     * curpage : 1
     * page_total : 1
     * data : {"list":[{"coupon_id":1,"coupon_name":"法师打发的撒范德萨发","coupon_for":"2,3","coupon_os_ids":"26,24","coupon_osl_id":0,"coupon_type":2,"coupon_money":"12.00","start_time":1569600000,"end_time":1571241600,"add_time":1569668833,"add_admin":1,"update_time":1569721763,"update_admin":1,"is_del":2,"coupon_note":"给第三方刚发的刚发的刚发的给对方给对方地方","min_money":"23.00","is_open":1,"amount_num":0,"is_index":2,"start_date":"2019/09/28 00:00","end_date":"2019/10/17 00:00","coupon_for_str":"私教课,商品","is_exp":0,"osl_name":"","os_names":["首玺健身环城西路店","首玺健身金色水岸店"],"is_have":0}]}
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

    public static class DataBean implements Serializable {
        private List<ListBean> list;

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean implements Serializable {
            /**
             * coupon_id : 1
             * coupon_name : 法师打发的撒范德萨发
             * coupon_for : 2,3
             * coupon_os_ids : 26,24
             * coupon_osl_id : 0
             * coupon_type : 2
             * coupon_money : 12.00
             * start_time : 1569600000
             * end_time : 1571241600
             * add_time : 1569668833
             * add_admin : 1
             * update_time : 1569721763
             * update_admin : 1
             * is_del : 2
             * coupon_note : 给第三方刚发的刚发的刚发的给对方给对方地方
             * min_money : 23.00
             * is_open : 1
             * amount_num : 0
             * is_index : 2
             * start_date : 2019/09/28 00:00
             * end_date : 2019/10/17 00:00
             * coupon_for_str : 私教课,商品
             * is_exp : 0
             * osl_name :
             * os_names : ["首玺健身环城西路店","首玺健身金色水岸店"]
             * is_have : 0
             */
            private int cm_id;
            private int coupon_id;
            private String coupon_name;
            private String coupon_for;
            private String coupon_os_ids;
            private int coupon_osl_id;
            private int coupon_type;
            private String coupon_money;
            private int start_time;
            private long end_time;
            private int add_time;
            private int add_admin;
            private int update_time;
            private int update_admin;
            private int is_del;
            private String coupon_note;
            private String min_money;
            private int is_open;
            private int amount_num;
            private int is_index;
            private String start_date;
            private String end_date;
            private String coupon_for_str;
            private int is_exp;
            private String osl_name;
            private int is_have;
            private List<String> os_names;
            private boolean isReceive;
            private boolean select;
            private int is_use;
            private String store_name;
            private int store_id;
            private int is_verification;
            private String verification_code;

            public int getIs_verification() {
                return is_verification;
            }

            public void setIs_verification(int is_verification) {
                this.is_verification = is_verification;
            }

            public String getVerification_code() {
                return verification_code;
            }

            public void setVerification_code(String verification_code) {
                this.verification_code = verification_code;
            }

            public int getStore_id() {
                return store_id;
            }

            public void setStore_id(int store_id) {
                this.store_id = store_id;
            }

            public String getStore_name() {
                return store_name;
            }

            public void setStore_name(String store_name) {
                this.store_name = store_name;
            }

            public int getIs_use() {
                return is_use;
            }

            public void setIs_use(int is_use) {
                this.is_use = is_use;
            }

            public int getCm_id() {
                return cm_id;
            }

            public void setCm_id(int cm_id) {
                this.cm_id = cm_id;
            }

            public boolean isSelect() {
                return select;
            }

            public void setSelect(boolean select) {
                this.select = select;
            }

            public boolean isReceive() {
                return isReceive;
            }

            public void setReceive(boolean receive) {
                isReceive = receive;
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

            public String getCoupon_for() {
                return coupon_for;
            }

            public void setCoupon_for(String coupon_for) {
                this.coupon_for = coupon_for;
            }

            public String getCoupon_os_ids() {
                return coupon_os_ids;
            }

            public void setCoupon_os_ids(String coupon_os_ids) {
                this.coupon_os_ids = coupon_os_ids;
            }

            public int getCoupon_osl_id() {
                return coupon_osl_id;
            }

            public void setCoupon_osl_id(int coupon_osl_id) {
                this.coupon_osl_id = coupon_osl_id;
            }

            public int getCoupon_type() {
                return coupon_type;
            }

            public void setCoupon_type(int coupon_type) {
                this.coupon_type = coupon_type;
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

            public long getEnd_time() {
                return end_time;
            }

            public void setEnd_time(long end_time) {
                this.end_time = end_time;
            }

            public int getAdd_time() {
                return add_time;
            }

            public void setAdd_time(int add_time) {
                this.add_time = add_time;
            }

            public int getAdd_admin() {
                return add_admin;
            }

            public void setAdd_admin(int add_admin) {
                this.add_admin = add_admin;
            }

            public int getUpdate_time() {
                return update_time;
            }

            public void setUpdate_time(int update_time) {
                this.update_time = update_time;
            }

            public int getUpdate_admin() {
                return update_admin;
            }

            public void setUpdate_admin(int update_admin) {
                this.update_admin = update_admin;
            }

            public int getIs_del() {
                return is_del;
            }

            public void setIs_del(int is_del) {
                this.is_del = is_del;
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

            public int getIs_open() {
                return is_open;
            }

            public void setIs_open(int is_open) {
                this.is_open = is_open;
            }

            public int getAmount_num() {
                return amount_num;
            }

            public void setAmount_num(int amount_num) {
                this.amount_num = amount_num;
            }

            public int getIs_index() {
                return is_index;
            }

            public void setIs_index(int is_index) {
                this.is_index = is_index;
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

            public int getIs_have() {
                return is_have;
            }

            public void setIs_have(int is_have) {
                this.is_have = is_have;
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
