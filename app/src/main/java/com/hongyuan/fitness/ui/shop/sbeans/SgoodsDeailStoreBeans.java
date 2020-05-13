package com.hongyuan.fitness.ui.shop.sbeans;

import com.hongyuan.fitness.base.BaseBean;

import java.util.List;

public class SgoodsDeailStoreBeans extends BaseBean {


    /**
     * data : {"store_id":42,"store_name":"湖州志裕","store_logo":"https://gdp.alicdn.com/imgextra/i2/2862021803/O1CN01PwOaGM1PBocqkAOjD_!!2862021803.jpg","all_goods":1,"all_collection":1,"goods_list":[{"g_id":43,"g_img":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20200301/d6e8d9eed2598af883e92b1f6516a023c539d652_800x467.jpg","g_name":"乐拉泰国青柚金柚进口西施蜜柚2个4.5斤新鲜当季孕妇水果顺丰包邮","g_price":"0.01","g_point":0}],"tj_goods_list":[{"g_id":43,"g_img":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20200301/d6e8d9eed2598af883e92b1f6516a023c539d652_800x467.jpg","g_name":"乐拉泰国青柚金柚进口西施蜜柚2个4.5斤新鲜当季孕妇水果顺丰包邮","g_price":"0.01","g_point":0}],"is_collection":0,"coupon_info":{"coupon_id":3,"coupon_name":"店铺通用红包","coupon_money":"20.00","min_money":"20.00"}}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * store_id : 42
         * store_name : 湖州志裕
         * store_logo : https://gdp.alicdn.com/imgextra/i2/2862021803/O1CN01PwOaGM1PBocqkAOjD_!!2862021803.jpg
         * all_goods : 1
         * all_collection : 1
         * goods_list : [{"g_id":43,"g_img":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20200301/d6e8d9eed2598af883e92b1f6516a023c539d652_800x467.jpg","g_name":"乐拉泰国青柚金柚进口西施蜜柚2个4.5斤新鲜当季孕妇水果顺丰包邮","g_price":"0.01","g_point":0}]
         * tj_goods_list : [{"g_id":43,"g_img":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20200301/d6e8d9eed2598af883e92b1f6516a023c539d652_800x467.jpg","g_name":"乐拉泰国青柚金柚进口西施蜜柚2个4.5斤新鲜当季孕妇水果顺丰包邮","g_price":"0.01","g_point":0}]
         * is_collection : 0
         * coupon_info : {"coupon_id":3,"coupon_name":"店铺通用红包","coupon_money":"20.00","min_money":"20.00"}
         */

        private int store_id;
        private String store_name;
        private String store_logo;
        private int all_goods;
        private int all_collection;
        private int is_collection;
        private CouponInfoBean coupon_info;
        private List<GoodsListBean> goods_list;
        private List<TjGoodsListBean> tj_goods_list;

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

        public int getAll_goods() {
            return all_goods;
        }

        public void setAll_goods(int all_goods) {
            this.all_goods = all_goods;
        }

        public int getAll_collection() {
            return all_collection;
        }

        public void setAll_collection(int all_collection) {
            this.all_collection = all_collection;
        }

        public int getIs_collection() {
            return is_collection;
        }

        public void setIs_collection(int is_collection) {
            this.is_collection = is_collection;
        }

        public CouponInfoBean getCoupon_info() {
            return coupon_info;
        }

        public void setCoupon_info(CouponInfoBean coupon_info) {
            this.coupon_info = coupon_info;
        }

        public List<GoodsListBean> getGoods_list() {
            return goods_list;
        }

        public void setGoods_list(List<GoodsListBean> goods_list) {
            this.goods_list = goods_list;
        }

        public List<TjGoodsListBean> getTj_goods_list() {
            return tj_goods_list;
        }

        public void setTj_goods_list(List<TjGoodsListBean> tj_goods_list) {
            this.tj_goods_list = tj_goods_list;
        }

        public static class CouponInfoBean {
            /**
             * coupon_id : 3
             * coupon_name : 店铺通用红包
             * coupon_money : 20.00
             * min_money : 20.00
             */

            private int coupon_id;
            private String coupon_name;
            private String coupon_money;
            private String min_money;

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
        }

        public static class GoodsListBean {
            /**
             * g_id : 43
             * g_img : http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20200301/d6e8d9eed2598af883e92b1f6516a023c539d652_800x467.jpg
             * g_name : 乐拉泰国青柚金柚进口西施蜜柚2个4.5斤新鲜当季孕妇水果顺丰包邮
             * g_price : 0.01
             * g_point : 0
             */

            private int g_id;
            private String g_img;
            private String g_name;
            private String g_price;
            private int g_point;

            public int getG_id() {
                return g_id;
            }

            public void setG_id(int g_id) {
                this.g_id = g_id;
            }

            public String getG_img() {
                return g_img;
            }

            public void setG_img(String g_img) {
                this.g_img = g_img;
            }

            public String getG_name() {
                return g_name;
            }

            public void setG_name(String g_name) {
                this.g_name = g_name;
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

        public static class TjGoodsListBean {
            /**
             * g_id : 43
             * g_img : http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20200301/d6e8d9eed2598af883e92b1f6516a023c539d652_800x467.jpg
             * g_name : 乐拉泰国青柚金柚进口西施蜜柚2个4.5斤新鲜当季孕妇水果顺丰包邮
             * g_price : 0.01
             * g_point : 0
             */

            private int g_id;
            private String g_img;
            private String g_name;
            private String g_price;
            private int g_point;

            public int getG_id() {
                return g_id;
            }

            public void setG_id(int g_id) {
                this.g_id = g_id;
            }

            public String getG_img() {
                return g_img;
            }

            public void setG_img(String g_img) {
                this.g_img = g_img;
            }

            public String getG_name() {
                return g_name;
            }

            public void setG_name(String g_name) {
                this.g_name = g_name;
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
    }
}
