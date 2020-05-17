package com.hongyuan.fitness.ui.shop.sbeans;

import com.hongyuan.fitness.base.BaseBean;

import java.util.List;

public class SgCollectGoodsBeans extends BaseBean {

    /**
     * hasmore : false
     * curpage : 1
     * page_total : 1
     * data : {"list":[{"collection_id":105,"out_id":22,"collection_time":1588920188,"m_id":3,"collection_code":"product","collection_date":"2020-05-08 14:43:08","info":{"g_id":22,"g_name":"运动包","g_price":"158.00","g_point":0,"g_img":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190727/e45640b4662b02d6f18d89566fc7e2e8fe51ee25_400x379.jpg","g_on_sale":1}},{"collection_id":100,"out_id":20,"collection_time":1588753596,"m_id":3,"collection_code":"product","collection_date":"2020-05-06 16:26:36","info":{"g_id":20,"g_name":"浴巾套装，优质全棉","g_price":"169.00","g_point":0,"g_img":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190727/4342df818371d05dc866ca7da18700c6369b9b46_400x409.jpg","g_on_sale":1}},{"collection_id":95,"out_id":42,"collection_time":1588129944,"m_id":3,"collection_code":"product","collection_date":"2020-04-29 11:12:24","info":{"g_id":42,"g_name":"测试勿买","g_price":"0.00","g_point":0,"g_img":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20200301/d6e8d9eed2598af883e92b1f6516a023c539d652_800x467.jpg","g_on_sale":1}},{"collection_id":94,"out_id":43,"collection_time":1588129936,"m_id":3,"collection_code":"product","collection_date":"2020-04-29 11:12:16","info":{"g_id":43,"g_name":"乐拉泰国青柚金柚进口西施蜜柚2个4.5斤新鲜当季孕妇水果顺丰包邮","g_price":"0.01","g_point":0,"g_img":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20200301/d6e8d9eed2598af883e92b1f6516a023c539d652_800x467.jpg","g_on_sale":1}}]}
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
             * collection_id : 105
             * out_id : 22
             * collection_time : 1588920188
             * m_id : 3
             * collection_code : product
             * collection_date : 2020-05-08 14:43:08
             * info : {"g_id":22,"g_name":"运动包","g_price":"158.00","g_point":0,"g_img":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190727/e45640b4662b02d6f18d89566fc7e2e8fe51ee25_400x379.jpg","g_on_sale":1}
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
                 * g_id : 22
                 * g_name : 运动包
                 * g_price : 158.00
                 * g_point : 0
                 * g_img : http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190727/e45640b4662b02d6f18d89566fc7e2e8fe51ee25_400x379.jpg
                 * g_on_sale : 1
                 */

                private int g_id;
                private String g_name;
                private String g_price;
                private int g_point;
                private String g_img;
                private int g_on_sale;

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
            }
        }
    }
}
