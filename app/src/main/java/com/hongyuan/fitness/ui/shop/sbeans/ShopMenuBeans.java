package com.hongyuan.fitness.ui.shop.sbeans;

import com.hongyuan.fitness.base.BaseBean;

import java.util.List;

public class ShopMenuBeans extends BaseBean {

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * category_id : 5
         * category_name : 男装
         * second : [{"category_id":7,"category_name":"户外风衣","category_img":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20200519/0cd8c5c8f600d553c0dbc2aaa54afb2cb307bedd_132x132.png"},{"category_id":10,"category_name":"运动鞋","category_img":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20200519/c7a182f33283cdfb26a500dcdc479b45f574bb48_132x132.png"},{"category_id":11,"category_name":"运动杯","category_img":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20200519/7e7c6e7e66824f6ba0676afade0c2416b1bd9a0e_132x132.png"},{"category_id":12,"category_name":"箱包","category_img":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20200519/c2b9fd6e4e55db8aec00971da26fb49a2b6bbb0e_132x132.png"},{"category_id":13,"category_name":"毛巾","category_img":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20200519/9cee491a172d8e6660a6eddb2b4c36e802668abc_132x132.png"}]
         */

        private int category_id;
        private String category_name;
        private List<SecondBean> second;

        public int getCategory_id() {
            return category_id;
        }

        public void setCategory_id(int category_id) {
            this.category_id = category_id;
        }

        public String getCategory_name() {
            return category_name;
        }

        public void setCategory_name(String category_name) {
            this.category_name = category_name;
        }

        public List<SecondBean> getSecond() {
            return second;
        }

        public void setSecond(List<SecondBean> second) {
            this.second = second;
        }

        public static class SecondBean {
            /**
             * category_id : 7
             * category_name : 户外风衣
             * category_img : http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20200519/0cd8c5c8f600d553c0dbc2aaa54afb2cb307bedd_132x132.png
             */

            private int category_id;
            private String category_name;
            private String category_img;

            public int getCategory_id() {
                return category_id;
            }

            public void setCategory_id(int category_id) {
                this.category_id = category_id;
            }

            public String getCategory_name() {
                return category_name;
            }

            public void setCategory_name(String category_name) {
                this.category_name = category_name;
            }

            public String getCategory_img() {
                return category_img;
            }

            public void setCategory_img(String category_img) {
                this.category_img = category_img;
            }
        }
    }
}
