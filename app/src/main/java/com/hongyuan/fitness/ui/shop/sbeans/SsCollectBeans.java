package com.hongyuan.fitness.ui.shop.sbeans;

import com.hongyuan.fitness.base.BaseBean;

import java.util.List;

public class SsCollectBeans extends BaseBean {

    /**
     * hasmore : false
     * curpage : 1
     * page_total : 1
     * data : {"list":[{"collection_id":107,"out_id":43,"collection_time":1589363238,"m_id":3,"collection_code":"store","collection_date":"2020-05-13 17:47:18","info":{"store_id":43,"store_name":"fdsafdsa","store_logo":"","g_score":"5.0","collection_num":0,"goods_list":[{"g_id":20,"g_img":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190727/4342df818371d05dc866ca7da18700c6369b9b46_400x409.jpg","g_name":"浴巾套装，优质全棉","g_price":"169.00","g_point":0},{"g_id":21,"g_img":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190727/2517b0f0b7dd3974b9b5d7bccb6736fc7e522c23_400x400.jpg","g_name":"运动水杯，保温高档不锈钢","g_price":"59.00","g_point":0},{"g_id":22,"g_img":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190727/e45640b4662b02d6f18d89566fc7e2e8fe51ee25_400x379.jpg","g_name":"运动包","g_price":"158.00","g_point":0}]}}]}
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
             * collection_id : 107
             * out_id : 43
             * collection_time : 1589363238
             * m_id : 3
             * collection_code : store
             * collection_date : 2020-05-13 17:47:18
             * info : {"store_id":43,"store_name":"fdsafdsa","store_logo":"","g_score":"5.0","collection_num":0,"goods_list":[{"g_id":20,"g_img":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190727/4342df818371d05dc866ca7da18700c6369b9b46_400x409.jpg","g_name":"浴巾套装，优质全棉","g_price":"169.00","g_point":0},{"g_id":21,"g_img":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190727/2517b0f0b7dd3974b9b5d7bccb6736fc7e522c23_400x400.jpg","g_name":"运动水杯，保温高档不锈钢","g_price":"59.00","g_point":0},{"g_id":22,"g_img":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190727/e45640b4662b02d6f18d89566fc7e2e8fe51ee25_400x379.jpg","g_name":"运动包","g_price":"158.00","g_point":0}]}
             */

            private int collection_id;
            private int out_id;
            private int collection_time;
            private int m_id;
            private String collection_code;
            private String collection_date;
            private InfoBean info;

            public int getCollection_id() {
                return collection_id;
            }

            public void setCollection_id(int collection_id) {
                this.collection_id = collection_id;
            }

            public int getOut_id() {
                return out_id;
            }

            public void setOut_id(int out_id) {
                this.out_id = out_id;
            }

            public int getCollection_time() {
                return collection_time;
            }

            public void setCollection_time(int collection_time) {
                this.collection_time = collection_time;
            }

            public int getM_id() {
                return m_id;
            }

            public void setM_id(int m_id) {
                this.m_id = m_id;
            }

            public String getCollection_code() {
                return collection_code;
            }

            public void setCollection_code(String collection_code) {
                this.collection_code = collection_code;
            }

            public String getCollection_date() {
                return collection_date;
            }

            public void setCollection_date(String collection_date) {
                this.collection_date = collection_date;
            }

            public InfoBean getInfo() {
                return info;
            }

            public void setInfo(InfoBean info) {
                this.info = info;
            }

            public static class InfoBean {
                /**
                 * store_id : 43
                 * store_name : fdsafdsa
                 * store_logo :
                 * g_score : 5.0
                 * collection_num : 0
                 * goods_list : [{"g_id":20,"g_img":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190727/4342df818371d05dc866ca7da18700c6369b9b46_400x409.jpg","g_name":"浴巾套装，优质全棉","g_price":"169.00","g_point":0},{"g_id":21,"g_img":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190727/2517b0f0b7dd3974b9b5d7bccb6736fc7e522c23_400x400.jpg","g_name":"运动水杯，保温高档不锈钢","g_price":"59.00","g_point":0},{"g_id":22,"g_img":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190727/e45640b4662b02d6f18d89566fc7e2e8fe51ee25_400x379.jpg","g_name":"运动包","g_price":"158.00","g_point":0}]
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
                     * g_id : 20
                     * g_img : http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190727/4342df818371d05dc866ca7da18700c6369b9b46_400x409.jpg
                     * g_name : 浴巾套装，优质全棉
                     * g_price : 169.00
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
    }
}
