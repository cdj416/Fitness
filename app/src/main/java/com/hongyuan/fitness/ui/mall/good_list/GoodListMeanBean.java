package com.hongyuan.fitness.ui.mall.good_list;

import com.hongyuan.fitness.base.BaseBean;

import java.util.List;

public class GoodListMeanBean extends BaseBean {

    /**
     * data : {"list":[{"category_id":5,"category_name":"运动衣","parent_id":3,"level":2,"category_img":""}]}
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
             * category_id : 5
             * category_name : 运动衣
             * parent_id : 3
             * level : 2
             * category_img :
             */

            private int category_id;
            private String category_name;
            private int parent_id;
            private int level;
            private String category_img;
            private boolean select;

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

            public boolean isSelect() {
                return select;
            }

            public void setSelect(boolean select) {
                this.select = select;
            }
        }
    }
}
