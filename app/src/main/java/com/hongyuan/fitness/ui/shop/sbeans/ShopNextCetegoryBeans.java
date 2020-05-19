package com.hongyuan.fitness.ui.shop.sbeans;

import com.hongyuan.fitness.base.BaseBean;

import java.util.List;

public class ShopNextCetegoryBeans extends BaseBean {

    /**
     * data : {"list":[{"category_id":18,"category_name":"低热量简餐","category_img":""},{"category_id":19,"category_name":"创意轻食","category_img":""}]}
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
             * category_id : 18
             * category_name : 低热量简餐
             * category_img :
             */

            private int category_id;
            private String category_name;
            private String category_img;
            private boolean select;

            public boolean isSelect() {
                return select;
            }

            public void setSelect(boolean select) {
                this.select = select;
            }

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
