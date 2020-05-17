package com.hongyuan.fitness.ui.shop.sbeans;

import com.hongyuan.fitness.base.BaseBean;

import java.util.List;

public class SccouponBeans extends BaseBean {

    /**
     * hasmore : false
     * curpage : 1
     * page_total : 1
     * data : {"list":[{"coupon_id":5,"coupon_name":"六一亲子优惠券","coupon_money":"10.00","store_id":42,"min_money":"10.00","coupon_note":"可以全店使用","amount_num":0,"start_time":1588003200,"end_time":1598457600,"store_name":"湖州志裕","goods_list":[{"g_id":43,"g_name":"乐拉泰国青柚金柚进口西施蜜柚2个4.5斤新鲜当季孕妇水果顺丰包邮","g_img":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20200301/d6e8d9eed2598af883e92b1f6516a023c539d652_800x467.jpg","g_on_sale":1,"g_price":"0.01","g_point":0},{"g_id":44,"g_name":"纯棉运动套装女春秋2020年新款春装卫衣时尚洋气休闲服两件套春季","g_img":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190718/3446465387ccad7043e2560604cc44f8a3c9d167_1242x828.jpg","g_on_sale":1,"g_price":"0.01","g_point":0},{"g_id":45,"g_name":"GU极优男装运动风中裤(工装)2020夏季新款时尚潮流休闲短裤325499","g_img":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190718/3446465387ccad7043e2560604cc44f8a3c9d167_1242x828.jpg","g_on_sale":1,"g_price":"0.01","g_point":0}],"start_date":"2020-04-28 00:00:00","end_date":"2020-08-27 00:00:00","is_lq":0},{"coupon_id":3,"coupon_name":"店铺通用红包","coupon_money":"20.00","store_id":42,"min_money":"20.00","coupon_note":"本店大促，红包多多","amount_num":0,"start_time":1588003200,"end_time":1598457600,"store_name":"湖州志裕","goods_list":[{"g_id":43,"g_name":"乐拉泰国青柚金柚进口西施蜜柚2个4.5斤新鲜当季孕妇水果顺丰包邮","g_img":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20200301/d6e8d9eed2598af883e92b1f6516a023c539d652_800x467.jpg","g_on_sale":1,"g_price":"0.01","g_point":0},{"g_id":44,"g_name":"纯棉运动套装女春秋2020年新款春装卫衣时尚洋气休闲服两件套春季","g_img":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190718/3446465387ccad7043e2560604cc44f8a3c9d167_1242x828.jpg","g_on_sale":1,"g_price":"0.01","g_point":0},{"g_id":45,"g_name":"GU极优男装运动风中裤(工装)2020夏季新款时尚潮流休闲短裤325499","g_img":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190718/3446465387ccad7043e2560604cc44f8a3c9d167_1242x828.jpg","g_on_sale":1,"g_price":"0.01","g_point":0}],"start_date":"2020-04-28 00:00:00","end_date":"2020-08-27 00:00:00","is_lq":1}]}
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
             * coupon_id : 5
             * coupon_name : 六一亲子优惠券
             * coupon_money : 10.00
             * store_id : 42
             * min_money : 10.00
             * coupon_note : 可以全店使用
             * amount_num : 0
             * start_time : 1588003200
             * end_time : 1598457600
             * store_name : 湖州志裕
             * goods_list : [{"g_id":43,"g_name":"乐拉泰国青柚金柚进口西施蜜柚2个4.5斤新鲜当季孕妇水果顺丰包邮","g_img":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20200301/d6e8d9eed2598af883e92b1f6516a023c539d652_800x467.jpg","g_on_sale":1,"g_price":"0.01","g_point":0},{"g_id":44,"g_name":"纯棉运动套装女春秋2020年新款春装卫衣时尚洋气休闲服两件套春季","g_img":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190718/3446465387ccad7043e2560604cc44f8a3c9d167_1242x828.jpg","g_on_sale":1,"g_price":"0.01","g_point":0},{"g_id":45,"g_name":"GU极优男装运动风中裤(工装)2020夏季新款时尚潮流休闲短裤325499","g_img":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190718/3446465387ccad7043e2560604cc44f8a3c9d167_1242x828.jpg","g_on_sale":1,"g_price":"0.01","g_point":0}]
             * start_date : 2020-04-28 00:00:00
             * end_date : 2020-08-27 00:00:00
             * is_lq : 0
             */

            private int coupon_id;
            private String coupon_name;
            private String coupon_money;
            private int store_id;
            private String min_money;
            private String coupon_note;
            private int amount_num;
            private int start_time;
            private int end_time;
            private String store_name;
            private String start_date;
            private String end_date;
            private int is_lq;
            private List<GoodsListBean> goods_list;

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

            public int getStore_id() {
                return store_id;
            }

            public void setStore_id(int store_id) {
                this.store_id = store_id;
            }

            public String getMin_money() {
                return min_money;
            }

            public void setMin_money(String min_money) {
                this.min_money = min_money;
            }

            public String getCoupon_note() {
                return coupon_note;
            }

            public void setCoupon_note(String coupon_note) {
                this.coupon_note = coupon_note;
            }

            public int getAmount_num() {
                return amount_num;
            }

            public void setAmount_num(int amount_num) {
                this.amount_num = amount_num;
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

            public String getStore_name() {
                return store_name;
            }

            public void setStore_name(String store_name) {
                this.store_name = store_name;
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

            public int getIs_lq() {
                return is_lq;
            }

            public void setIs_lq(int is_lq) {
                this.is_lq = is_lq;
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
                 * g_img : http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20200301/d6e8d9eed2598af883e92b1f6516a023c539d652_800x467.jpg
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
