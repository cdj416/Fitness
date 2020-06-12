package com.hongyuan.fitness.ui.shop.sbeans;

import com.hongyuan.fitness.base.BaseBean;

import java.util.List;

public class MallMenuBeans extends BaseBean {

    /**
     * data : {"list":[{"category_id":1,"category_name":"轻食代餐","parent_id":0,"level":1,"category_img":"https://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/my/%E8%BD%BB%E9%A3%9F.png"},{"category_id":2,"category_name":"运动装备","parent_id":0,"level":1,"category_img":"https://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/my/%E5%99%A8%E6%9D%90.png"},{"category_id":3,"category_name":"男女服饰","parent_id":0,"level":1,"category_img":"https://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/my/%E6%9C%8D%E9%A5%B0.png"},{"category_id":4,"category_name":"智能设备","parent_id":0,"level":1,"category_img":"https://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/my/%E7%94%B5%E5%AD%90%E8%AE%BE%E5%A4%87.png"}]}
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
             * category_id : 1
             * category_name : 轻食代餐
             * parent_id : 0
             * level : 1
             * category_img : https://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/my/%E8%BD%BB%E9%A3%9F.png
             */

            private int category_id;
            private String category_name;
            private int parent_id;
            private int level;
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

            public int getParent_id() {
                return parent_id;
            }

            public void setParent_id(int parent_id) {
                this.parent_id = parent_id;
            }

            public int getLevel() {
                return level;
            }

            public void setLevel(int level) {
                this.level = level;
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
