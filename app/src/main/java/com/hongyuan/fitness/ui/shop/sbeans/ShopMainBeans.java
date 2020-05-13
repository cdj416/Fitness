package com.hongyuan.fitness.ui.shop.sbeans;

import com.hongyuan.fitness.base.BaseBean;

import java.util.List;

public class ShopMainBeans extends BaseBean {

    /**
     * data : {"goods_selected":[{"g_id":43,"g_name":"乐拉泰国青柚金柚进口西施蜜柚2个4.5斤新鲜当季孕妇水果顺丰包邮","g_img":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20200301/d6e8d9eed2598af883e92b1f6516a023c539d652_800x467.jpg","g_price":"0.01","g_point":0}],"goods_new":[{"g_id":41,"g_name":"测试口罩","g_img":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20200227/d2b2122c49e8f31f34e5ee71e2d1bc212a376184_800x533.jpg","g_price":"0.00","g_point":0}],"goods_hot":[{"g_id":42,"g_name":"测试勿买","g_img":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20200301/d6e8d9eed2598af883e92b1f6516a023c539d652_800x467.jpg","g_price":"0.00","g_point":0}],"store_selected":[{"store_id":42,"store_name":"湖州志裕","store_logo":"https://gdp.alicdn.com/imgextra/i2/2862021803/O1CN01PwOaGM1PBocqkAOjD_!!2862021803.jpg"}]}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private List<GoodsSelectedBean> goods_selected;
        private List<GoodsNewBean> goods_new;
        private List<GoodsHotBean> goods_hot;
        private List<StoreSelectedBean> store_selected;

        public List<GoodsSelectedBean> getGoods_selected() {
            return goods_selected;
        }

        public void setGoods_selected(List<GoodsSelectedBean> goods_selected) {
            this.goods_selected = goods_selected;
        }

        public List<GoodsNewBean> getGoods_new() {
            return goods_new;
        }

        public void setGoods_new(List<GoodsNewBean> goods_new) {
            this.goods_new = goods_new;
        }

        public List<GoodsHotBean> getGoods_hot() {
            return goods_hot;
        }

        public void setGoods_hot(List<GoodsHotBean> goods_hot) {
            this.goods_hot = goods_hot;
        }

        public List<StoreSelectedBean> getStore_selected() {
            return store_selected;
        }

        public void setStore_selected(List<StoreSelectedBean> store_selected) {
            this.store_selected = store_selected;
        }

        public static class GoodsSelectedBean {
            /**
             * g_id : 43
             * g_name : 乐拉泰国青柚金柚进口西施蜜柚2个4.5斤新鲜当季孕妇水果顺丰包邮
             * g_img : http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20200301/d6e8d9eed2598af883e92b1f6516a023c539d652_800x467.jpg
             * g_price : 0.01
             * g_point : 0
             */

            private int g_id;
            private String g_name;
            private String g_img;
            private String g_price;
            private int g_point;

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
        }

        public static class GoodsNewBean {
            /**
             * g_id : 41
             * g_name : 测试口罩
             * g_img : http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20200227/d2b2122c49e8f31f34e5ee71e2d1bc212a376184_800x533.jpg
             * g_price : 0.00
             * g_point : 0
             */

            private int g_id;
            private String g_name;
            private String g_img;
            private String g_price;
            private int g_point;

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
        }

        public static class GoodsHotBean {
            /**
             * g_id : 42
             * g_name : 测试勿买
             * g_img : http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20200301/d6e8d9eed2598af883e92b1f6516a023c539d652_800x467.jpg
             * g_price : 0.00
             * g_point : 0
             */

            private int g_id;
            private String g_name;
            private String g_img;
            private String g_price;
            private int g_point;

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
        }

        public static class StoreSelectedBean {
            /**
             * store_id : 42
             * store_name : 湖州志裕
             * store_logo : https://gdp.alicdn.com/imgextra/i2/2862021803/O1CN01PwOaGM1PBocqkAOjD_!!2862021803.jpg
             */

            private int store_id;
            private String store_name;
            private String store_logo;

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

            public String getStore_logo() {
                return store_logo;
            }

            public void setStore_logo(String store_logo) {
                this.store_logo = store_logo;
            }
        }
    }
}
