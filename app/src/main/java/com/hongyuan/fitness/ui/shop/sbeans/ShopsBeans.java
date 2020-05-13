package com.hongyuan.fitness.ui.shop.sbeans;

import com.hongyuan.fitness.base.BaseBean;

import java.util.List;

public class ShopsBeans extends BaseBean {

    /**
     * hasmore : true
     * curpage : 1
     * page_total : 2
     * data : {"list":[{"store_id":42,"store_name":"湖州志裕","store_logo":"https://gdp.alicdn.com/imgextra/i2/2862021803/O1CN01PwOaGM1PBocqkAOjD_!!2862021803.jpg","g_score":"5.0","goods_list":[{"g_id":43,"g_name":"乐拉泰国青柚金柚进口西施蜜柚2个4.5斤新鲜当季孕妇水果顺丰包邮","g_img":"https://img.alicdn.com/imgextra/i4/3865407743/TB2ppx4cHArBKNjSZFLXXc_dVXa_!!3865407743.jpg_430x430q90.jpg","g_on_sale":1,"g_price":"0.01","g_point":0}],"collection_num":0},{"store_id":43,"store_name":"fdsafdsa","store_logo":"","g_score":"5.0","goods_list":[{"g_id":20,"g_name":"浴巾套装，优质全棉","g_img":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190727/4342df818371d05dc866ca7da18700c6369b9b46_400x409.jpg","g_on_sale":1,"g_price":"169.00","g_point":0},{"g_id":21,"g_name":"运动水杯，保温高档不锈钢","g_img":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190727/2517b0f0b7dd3974b9b5d7bccb6736fc7e522c23_400x400.jpg","g_on_sale":1,"g_price":"59.00","g_point":0},{"g_id":22,"g_name":"运动包","g_img":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190727/e45640b4662b02d6f18d89566fc7e2e8fe51ee25_400x379.jpg","g_on_sale":1,"g_price":"158.00","g_point":0}],"collection_num":0}]}
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
             * store_id : 42
             * store_name : 湖州志裕
             * store_logo : https://gdp.alicdn.com/imgextra/i2/2862021803/O1CN01PwOaGM1PBocqkAOjD_!!2862021803.jpg
             * g_score : 5.0
             * goods_list : [{"g_id":43,"g_name":"乐拉泰国青柚金柚进口西施蜜柚2个4.5斤新鲜当季孕妇水果顺丰包邮","g_img":"https://img.alicdn.com/imgextra/i4/3865407743/TB2ppx4cHArBKNjSZFLXXc_dVXa_!!3865407743.jpg_430x430q90.jpg","g_on_sale":1,"g_price":"0.01","g_point":0}]
             * collection_num : 0
             */

            private int store_id;
            private String store_name;
            private String store_logo;
            private String g_score;
            private int collection_num;
            private List<GoodsListBean> goods_list;

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

            public String getG_score() {
                return g_score;
            }

            public void setG_score(String g_score) {
                this.g_score = g_score;
            }

            public int getCollection_num() {
                return collection_num;
            }

            public void setCollection_num(int collection_num) {
                this.collection_num = collection_num;
            }

            public List<GoodsListBean> getGoods_list() {
                return goods_list;
            }

            public void setGoods_list(List<GoodsListBean> goods_list) {
                this.goods_list = goods_list;
            }

            public static class GoodsListBean {
                /**
                 * g_id : 43
                 * g_name : 乐拉泰国青柚金柚进口西施蜜柚2个4.5斤新鲜当季孕妇水果顺丰包邮
                 * g_img : https://img.alicdn.com/imgextra/i4/3865407743/TB2ppx4cHArBKNjSZFLXXc_dVXa_!!3865407743.jpg_430x430q90.jpg
                 * g_on_sale : 1
                 * g_price : 0.01
                 * g_point : 0
                 */

                private int g_id;
                private String g_name;
                private String g_img;
                private int g_on_sale;
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

                public int getG_on_sale() {
                    return g_on_sale;
                }

                public void setG_on_sale(int g_on_sale) {
                    this.g_on_sale = g_on_sale;
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
}
