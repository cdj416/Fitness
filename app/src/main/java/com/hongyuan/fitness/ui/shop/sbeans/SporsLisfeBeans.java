package com.hongyuan.fitness.ui.shop.sbeans;

import com.hongyuan.fitness.base.BaseBean;

import java.util.List;

public class SporsLisfeBeans extends BaseBean {

    /**
     * data : {"list":[{"g_id":25,"g_name":"AJ运动鞋","g_img":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190727/7eb7ac27566969d1fd96e81e654011fbfcc05a35_400x256.jpg","g_price":"1099.00","g_point":0,"g_sale_num":0,"g_evaluate_num":0,"pid":35,"cid":3505,"store_name":"fdsafdsa","region_name":"湖州市"},{"g_id":41,"g_name":"测试口罩","g_img":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20200227/d2b2122c49e8f31f34e5ee71e2d1bc212a376184_800x533.jpg","g_price":"0.00","g_point":0,"g_sale_num":0,"g_evaluate_num":0,"pid":42,"cid":4201,"store_name":"fdsafdsa","region_name":"海口市"},{"g_id":44,"g_name":"纯棉运动套装女春秋2020年新款春装卫衣时尚洋气休闲服两件套春季","g_img":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190718/3446465387ccad7043e2560604cc44f8a3c9d167_1242x828.jpg","g_price":"0.01","g_point":0,"g_sale_num":0,"g_evaluate_num":0,"pid":10,"cid":1001,"store_name":"家松","region_name":"北京市"},{"g_id":45,"g_name":"GU极优男装运动风中裤(工装)2020夏季新款时尚潮流休闲短裤325499","g_img":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190718/3446465387ccad7043e2560604cc44f8a3c9d167_1242x828.jpg","g_price":"0.01","g_point":0,"g_sale_num":0,"g_evaluate_num":0,"pid":10,"cid":1001,"store_name":"家松","region_name":"北京市"},{"g_id":46,"g_name":"GU极优男装运动风中裤(工装)2020夏季新款时尚潮流休闲短裤325499","g_img":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190718/3446465387ccad7043e2560604cc44f8a3c9d167_1242x828.jpg","g_price":"0.01","g_point":0,"g_sale_num":0,"g_evaluate_num":0,"pid":10,"cid":1001,"store_name":"家松","region_name":"北京市"},{"g_id":47,"g_name":"【商场同款】速写男装2020春季新品运动束脚工装九分裤9KB321890","g_img":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190718/3446465387ccad7043e2560604cc44f8a3c9d167_1242x828.jpg","g_price":"0.01","g_point":0,"g_sale_num":0,"g_evaluate_num":0,"pid":10,"cid":1001,"store_name":"家松","region_name":"北京市"}]}
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
             * g_id : 25
             * g_name : AJ运动鞋
             * g_img : http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190727/7eb7ac27566969d1fd96e81e654011fbfcc05a35_400x256.jpg
             * g_price : 1099.00
             * g_point : 0
             * g_sale_num : 0
             * g_evaluate_num : 0
             * pid : 35
             * cid : 3505
             * store_name : fdsafdsa
             * region_name : 湖州市
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

            public String getRegion_name() {
                return region_name;
            }

            public void setRegion_name(String region_name) {
                this.region_name = region_name;
            }
        }
    }
}
