package com.hongyuan.fitness.ui.mall.home_goods;

import com.hongyuan.fitness.base.BaseBean;

import java.util.List;

public class HomeGoodsBeans extends BaseBean {

    /**
     * data : {"is_tan":1,"list":[{"g_id":19,"g_img":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190726/d51a0a6ba7f2f14c22ca386850266b1d2a30c7a1_299x85.png","g_name":"名称111"},{"g_id":31,"g_img":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20191111/cee28a242c4a03efca590c5b25bc9620807d32e4_621x621.jpg","g_name":"测试001"},{"g_id":43,"g_img":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20200210/d3249bd25e73b1bf9030a1c43b3918644b6ec3a3_80x80.png","g_name":"水果标题"}]}
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
         * is_tan : 1
         * list : [{"g_id":19,"g_img":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190726/d51a0a6ba7f2f14c22ca386850266b1d2a30c7a1_299x85.png","g_name":"名称111"},{"g_id":31,"g_img":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20191111/cee28a242c4a03efca590c5b25bc9620807d32e4_621x621.jpg","g_name":"测试001"},{"g_id":43,"g_img":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20200210/d3249bd25e73b1bf9030a1c43b3918644b6ec3a3_80x80.png","g_name":"水果标题"}]
         */

        private int is_tan;
        private List<ListBean> list;

        public int getIs_tan() {
            return is_tan;
        }

        public void setIs_tan(int is_tan) {
            this.is_tan = is_tan;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * g_id : 19
             * g_img : http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190726/d51a0a6ba7f2f14c22ca386850266b1d2a30c7a1_299x85.png
             * g_name : 名称111
             */

            private int g_id;
            private String g_img;
            private String g_name;
            private boolean receive;

            public boolean isReceive() {
                return receive;
            }

            public void setReceive(boolean receive) {
                this.receive = receive;
            }

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
        }
    }
}
