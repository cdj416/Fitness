package com.hongyuan.fitness.ui.shop.sbeans;

import com.hongyuan.fitness.base.BaseBean;

import java.util.List;

public class AfterSalesOrderListBeans extends BaseBean {

    /**
     * hasmore : false
     * curpage : 1
     * page_total : 1
     * data : {"list":[{"opg_id":189,"buy_num":1,"gp_point":0,"g_name":"GU极优男装运动风中裤(工装)2020夏季新款时尚潮流休闲短裤325499","g_img":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190718/3446465387ccad7043e2560604cc44f8a3c9d167_1242x828.jpg","is_refund":1,"refund_state":1,"refund_money":"10.00","sku_names":"蓝色;M码","store_name":"家松2","store_id":42,"g_id":45,"refuse_time":0,"agree_time":0,"finished_time":0,"apply_refund_time":1594867474,"deliver_time":0,"confirm_time":0,"refuse_confirm_deliver_time":0,"point":0,"refuse_date":"1970-01-01 08:00:00","agree_date":"1970-01-01 08:00:00","finished_date":"1970-01-01 08:00:00","apply_refund_date":"2020-07-16 10:44:34","deliver_date":"1970-01-01 08:00:00","confirm_date":"1970-01-01 08:00:00","refuse_confirm_deliver_date":"1970-01-01 08:00:00","state_str1":"退款中","state_str2":"等待商家确认"},{"opg_id":190,"buy_num":1,"gp_point":0,"g_name":"测试口罩","g_img":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20200227/d2b2122c49e8f31f34e5ee71e2d1bc212a376184_800x533.jpg","is_refund":1,"refund_state":1,"refund_money":"124.00","sku_names":"40;常规","store_name":"fdsafdsa","store_id":43,"g_id":41,"refuse_time":0,"agree_time":0,"finished_time":0,"apply_refund_time":1594867049,"deliver_time":0,"confirm_time":0,"refuse_confirm_deliver_time":0,"point":0,"refuse_date":"1970-01-01 08:00:00","agree_date":"1970-01-01 08:00:00","finished_date":"1970-01-01 08:00:00","apply_refund_date":"2020-07-16 10:37:29","deliver_date":"1970-01-01 08:00:00","confirm_date":"1970-01-01 08:00:00","refuse_confirm_deliver_date":"1970-01-01 08:00:00","state_str1":"退款中","state_str2":"等待商家确认"},{"opg_id":191,"buy_num":1,"gp_point":0,"g_name":"麂皮绒橡胶瑜伽垫","g_img":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190727/bebc38e971ebbe1803b5415b87e456c3d12be1bb_400x473.jpg","is_refund":1,"refund_state":1,"refund_money":"0.00","sku_names":"","store_name":"fdsafdsa","store_id":43,"g_id":24,"refuse_time":0,"agree_time":0,"finished_time":0,"apply_refund_time":1594866766,"deliver_time":0,"confirm_time":0,"refuse_confirm_deliver_time":0,"point":0,"refuse_date":"1970-01-01 08:00:00","agree_date":"1970-01-01 08:00:00","finished_date":"1970-01-01 08:00:00","apply_refund_date":"2020-07-16 10:32:46","deliver_date":"1970-01-01 08:00:00","confirm_date":"1970-01-01 08:00:00","refuse_confirm_deliver_date":"1970-01-01 08:00:00","state_str1":"退款中","state_str2":"等待商家确认"}]}
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
             * opg_id : 189
             * buy_num : 1
             * gp_point : 0
             * g_name : GU极优男装运动风中裤(工装)2020夏季新款时尚潮流休闲短裤325499
             * g_img : http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190718/3446465387ccad7043e2560604cc44f8a3c9d167_1242x828.jpg
             * is_refund : 1
             * refund_state : 1
             * refund_money : 10.00
             * sku_names : 蓝色;M码
             * store_name : 家松2
             * store_id : 42
             * g_id : 45
             * refuse_time : 0
             * agree_time : 0
             * finished_time : 0
             * apply_refund_time : 1594867474
             * deliver_time : 0
             * confirm_time : 0
             * refuse_confirm_deliver_time : 0
             * point : 0
             * refuse_date : 1970-01-01 08:00:00
             * agree_date : 1970-01-01 08:00:00
             * finished_date : 1970-01-01 08:00:00
             * apply_refund_date : 2020-07-16 10:44:34
             * deliver_date : 1970-01-01 08:00:00
             * confirm_date : 1970-01-01 08:00:00
             * refuse_confirm_deliver_date : 1970-01-01 08:00:00
             * state_str1 : 退款中
             * state_str2 : 等待商家确认
             */

            private int opg_id;
            private int buy_num;
            private int gp_point;
            private String g_name;
            private String g_img;
            private int is_refund;
            private int refund_state;
            private String refund_money;
            private String sku_names;
            private String store_name;
            private int store_id;
            private int g_id;
            private int refuse_time;
            private int agree_time;
            private int finished_time;
            private int apply_refund_time;
            private int deliver_time;
            private int confirm_time;
            private int refuse_confirm_deliver_time;
            private int point;
            private String refuse_date;
            private String agree_date;
            private String finished_date;
            private String apply_refund_date;
            private String deliver_date;
            private String confirm_date;
            private String refuse_confirm_deliver_date;
            private String state_str1;
            private String state_str2;

            public int getOpg_id() {
                return opg_id;
            }

            public void setOpg_id(int opg_id) {
                this.opg_id = opg_id;
            }

            public int getBuy_num() {
                return buy_num;
            }

            public void setBuy_num(int buy_num) {
                this.buy_num = buy_num;
            }

            public int getGp_point() {
                return gp_point;
            }

            public void setGp_point(int gp_point) {
                this.gp_point = gp_point;
            }

            public String getG_name() {
                return g_name;
            }

            public void setG_name(String g_name) {
                this.g_name = g_name;
            }

            public String getG_img() {
                return g_img;
            }

            public void setG_img(String g_img) {
                this.g_img = g_img;
            }

            public int getIs_refund() {
                return is_refund;
            }

            public void setIs_refund(int is_refund) {
                this.is_refund = is_refund;
            }

            public int getRefund_state() {
                return refund_state;
            }

            public void setRefund_state(int refund_state) {
                this.refund_state = refund_state;
            }

            public String getRefund_money() {
                return refund_money;
            }

            public void setRefund_money(String refund_money) {
                this.refund_money = refund_money;
            }

            public String getSku_names() {
                return sku_names;
            }

            public void setSku_names(String sku_names) {
                this.sku_names = sku_names;
            }

            public String getStore_name() {
                return store_name;
            }

            public void setStore_name(String store_name) {
                this.store_name = store_name;
            }

            public int getStore_id() {
                return store_id;
            }

            public void setStore_id(int store_id) {
                this.store_id = store_id;
            }

            public int getG_id() {
                return g_id;
            }

            public void setG_id(int g_id) {
                this.g_id = g_id;
            }

            public int getRefuse_time() {
                return refuse_time;
            }

            public void setRefuse_time(int refuse_time) {
                this.refuse_time = refuse_time;
            }

            public int getAgree_time() {
                return agree_time;
            }

            public void setAgree_time(int agree_time) {
                this.agree_time = agree_time;
            }

            public int getFinished_time() {
                return finished_time;
            }

            public void setFinished_time(int finished_time) {
                this.finished_time = finished_time;
            }

            public int getApply_refund_time() {
                return apply_refund_time;
            }

            public void setApply_refund_time(int apply_refund_time) {
                this.apply_refund_time = apply_refund_time;
            }

            public int getDeliver_time() {
                return deliver_time;
            }

            public void setDeliver_time(int deliver_time) {
                this.deliver_time = deliver_time;
            }

            public int getConfirm_time() {
                return confirm_time;
            }

            public void setConfirm_time(int confirm_time) {
                this.confirm_time = confirm_time;
            }

            public int getRefuse_confirm_deliver_time() {
                return refuse_confirm_deliver_time;
            }

            public void setRefuse_confirm_deliver_time(int refuse_confirm_deliver_time) {
                this.refuse_confirm_deliver_time = refuse_confirm_deliver_time;
            }

            public int getPoint() {
                return point;
            }

            public void setPoint(int point) {
                this.point = point;
            }

            public String getRefuse_date() {
                return refuse_date;
            }

            public void setRefuse_date(String refuse_date) {
                this.refuse_date = refuse_date;
            }

            public String getAgree_date() {
                return agree_date;
            }

            public void setAgree_date(String agree_date) {
                this.agree_date = agree_date;
            }

            public String getFinished_date() {
                return finished_date;
            }

            public void setFinished_date(String finished_date) {
                this.finished_date = finished_date;
            }

            public String getApply_refund_date() {
                return apply_refund_date;
            }

            public void setApply_refund_date(String apply_refund_date) {
                this.apply_refund_date = apply_refund_date;
            }

            public String getDeliver_date() {
                return deliver_date;
            }

            public void setDeliver_date(String deliver_date) {
                this.deliver_date = deliver_date;
            }

            public String getConfirm_date() {
                return confirm_date;
            }

            public void setConfirm_date(String confirm_date) {
                this.confirm_date = confirm_date;
            }

            public String getRefuse_confirm_deliver_date() {
                return refuse_confirm_deliver_date;
            }

            public void setRefuse_confirm_deliver_date(String refuse_confirm_deliver_date) {
                this.refuse_confirm_deliver_date = refuse_confirm_deliver_date;
            }

            public String getState_str1() {
                return state_str1;
            }

            public void setState_str1(String state_str1) {
                this.state_str1 = state_str1;
            }

            public String getState_str2() {
                return state_str2;
            }

            public void setState_str2(String state_str2) {
                this.state_str2 = state_str2;
            }
        }
    }
}
