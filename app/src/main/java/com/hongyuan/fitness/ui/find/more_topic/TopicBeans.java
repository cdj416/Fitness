package com.hongyuan.fitness.ui.find.more_topic;

import com.hongyuan.fitness.base.BaseBean;
import com.hongyuan.fitness.ui.find.topic.SlectTopicLeftBeans;

import java.util.List;

public class TopicBeans extends BaseBean {

    /**
     * hasmore : false
     * curpage : 1
     * page_total : 1
     * data : {"list":[{"category_id":2,"category_name":"娱乐","parent_id":0,"category_img":"","category_note":"","count":0,"parent_name":"顶级分类"},{"category_id":1,"category_name":"减肥","parent_id":0,"category_img":"","category_note":"","count":20,"parent_name":"顶级分类"}]}
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
             * category_id : 2
             * category_name : 娱乐
             * parent_id : 0
             * category_img :
             * category_note :
             * count : 0
             * parent_name : 顶级分类
             */

            private int category_id;
            private String category_name;
            private int parent_id;
            private String category_img;
            private String category_note;
            private int count;
            private String parent_name;
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

            public int getParent_id() {
                return parent_id;
            }

            public void setParent_id(int parent_id) {
                this.parent_id = parent_id;
            }

            public String getCategory_img() {
                return category_img;
            }

            public void setCategory_img(String category_img) {
                this.category_img = category_img;
            }

            public String getCategory_note() {
                return category_note;
            }

            public void setCategory_note(String category_note) {
                this.category_note = category_note;
            }

            public int getCount() {
                return count;
            }

            public void setCount(int count) {
                this.count = count;
            }

            public String getParent_name() {
                return parent_name;
            }

            public void setParent_name(String parent_name) {
                this.parent_name = parent_name;
            }
        }
    }
}
