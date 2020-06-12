package com.hongyuan.fitness.ui.shop.sbeans;

import com.hongyuan.fitness.base.BaseBean;

import java.util.List;

public class LikeGoodsBeans extends BaseBean {

    /**
     * hasmore : false
     * curpage : 1
     * page_total : 1
     * data : {"list":[{"g_id":47,"g_name":"孙俪娘娘同款草帽子出口日本外贸夏季可折叠防UV防晒帽子女遮阳帽","g_img":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20200525/a18ba537f2e8a96b31ee8f02f3df5ec3b082275b_600x600.jpg","g_price":"75.00","g_point":0,"store_name":"佳淞"},{"g_id":46,"g_name":"帽子夏季日系双面拼色大沿春秋渔夫帽女 遮阳帽防晒帽子韩可折叠","g_img":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20200525/942e80e0badea8f60dd20d172788ed23dfb4ea69_600x600.jpg","g_price":"29.00","g_point":0,"store_name":"佳淞"},{"g_id":45,"g_name":"双面磨毛裸感瑜伽五分裤女 高腰提臀健身裤 紧身瑜伽中裤","g_img":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20200521/477005e9e69172c74ade65f1a9470fac82fd4fc6_600x600.jpg","g_price":"69.00","g_point":0,"store_name":"佳淞"},{"g_id":44,"g_name":"新款性感美背瑜伽背心 运动健身背心 假两件运动瑜伽文胸背心","g_img":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20200520/6817cb01ed80ed5557e6fa912fd53bedc47e103f_600x600.jpg","g_price":"85.00","g_point":0,"store_name":"佳淞"},{"g_id":43,"g_name":"ins网红新款瑜伽服背心 运动跑步防震背心 健身吊带背心女","g_img":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20200520/9106a560cec9e853e4c999ff6e34c6cbad41ca3c_400x400.jpg","g_price":"78.00","g_point":0,"store_name":"佳淞"}]}
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
             * g_id : 47
             * g_name : 孙俪娘娘同款草帽子出口日本外贸夏季可折叠防UV防晒帽子女遮阳帽
             * g_img : http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20200525/a18ba537f2e8a96b31ee8f02f3df5ec3b082275b_600x600.jpg
             * g_price : 75.00
             * g_point : 0
             * store_name : 佳淞
             */

            private int g_id;
            private String g_name;
            private String g_img;
            private String g_price;
            private int g_point;
            private String store_name;

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

            public String getStore_name() {
                return store_name;
            }

            public void setStore_name(String store_name) {
                this.store_name = store_name;
            }
        }
    }
}
