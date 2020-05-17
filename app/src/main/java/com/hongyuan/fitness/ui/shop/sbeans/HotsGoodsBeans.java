package com.hongyuan.fitness.ui.shop.sbeans;

import com.hongyuan.fitness.base.BaseBean;

import java.util.List;

public class HotsGoodsBeans extends BaseBean {

    /**
     * data : {"list":[{"g_id":41,"g_name":"测试口罩","g_img":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20200227/d2b2122c49e8f31f34e5ee71e2d1bc212a376184_800x533.jpg","g_price":"0.00","g_point":0,"g_sale_num":0,"g_evaluate_num":0,"pid":42,"cid":4201,"store_name":"fdsafdsa","g_income":"3.00","region_name":"海口市"},{"g_id":42,"g_name":"测试勿买","g_img":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20200301/d6e8d9eed2598af883e92b1f6516a023c539d652_800x467.jpg","g_price":"0.00","g_point":0,"g_sale_num":0,"g_evaluate_num":0,"pid":35,"cid":3505,"store_name":"fdsafdsa","g_income":"4.00","region_name":"湖州市"},{"g_id":43,"g_name":"乐拉泰国青柚金柚进口西施蜜柚2个4.5斤新鲜当季孕妇水果顺丰包邮","g_img":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20200301/d6e8d9eed2598af883e92b1f6516a023c539d652_800x467.jpg","g_price":"0.01","g_point":0,"g_sale_num":0,"g_evaluate_num":0,"pid":10,"cid":1001,"store_name":"家松","g_income":"5.00","region_name":"北京市"}]}
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
             * g_id : 41
             * g_name : 测试口罩
             * g_img : http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20200227/d2b2122c49e8f31f34e5ee71e2d1bc212a376184_800x533.jpg
             * g_price : 0.00
             * g_point : 0
             * g_sale_num : 0
             * g_evaluate_num : 0
             * pid : 42
             * cid : 4201
             * store_name : fdsafdsa
             * g_income : 3.00
             * region_name : 海口市
             */

            private int g_id;
            private String g_name;
            private String g_img;
            private String g_price;
            private int g_point;
            private int g_sale_num;
            private int g_evaluate_num;
            private int pid;
            private int cid;
            private String store_name;
            private String g_income;
            private String region_name;

            public int getG_id() {
                return g_id;
            }

            public void setG_id(int g_id) {
                this.g_id = g_id;
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

            public String getG_price() {
                return g_price;
            }

            public void setG_price(String g_price) {
                this.g_price = g_price;
            }

            public int getG_point() {
                return g_point;
            }

            public void setG_point(int g_point) {
                this.g_point = g_point;
            }

            public int getG_sale_num() {
                return g_sale_num;
            }

            public void setG_sale_num(int g_sale_num) {
                this.g_sale_num = g_sale_num;
            }

            public int getG_evaluate_num() {
                return g_evaluate_num;
            }

            public void setG_evaluate_num(int g_evaluate_num) {
                this.g_evaluate_num = g_evaluate_num;
            }

            public int getPid() {
                return pid;
            }

            public void setPid(int pid) {
                this.pid = pid;
            }

            public int getCid() {
                return cid;
            }

            public void setCid(int cid) {
                this.cid = cid;
            }

            public String getStore_name() {
                return store_name;
            }

            public void setStore_name(String store_name) {
                this.store_name = store_name;
            }

            public String getG_income() {
                return g_income;
            }

            public void setG_income(String g_income) {
                this.g_income = g_income;
            }

            public String getRegion_name() {
                return region_name;
            }

            public void setRegion_name(String region_name) {
                this.region_name = region_name;
            }
        }
    }
}
