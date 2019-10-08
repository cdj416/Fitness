package com.hongyuan.fitness.ui.person.my_collection.collection_baike;

import com.hongyuan.fitness.base.BaseBean;

import java.util.List;

public class CollectionBaikeBeans extends BaseBean {

    /**
     * hasmore : false
     * curpage : 1
     * page_total : 1
     * data : {"list":[{"collection_id":56,"out_id":11,"collection_time":1569490861,"m_id":13,"collection_code":"baike","article_title":"212标题","article_img":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190911/ec89d14ace654f78e7ba93175aa337c0ba049dfb_960x1506.jpg","review_num":1,"article_id":11,"type":1,"add_date":"2019-09-11 10:13:55","is_collection":"1"},{"collection_id":55,"out_id":12,"collection_time":1569490854,"m_id":13,"collection_code":"baike","article_title":"09-11测试标题1 重新编辑","article_img":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190911/cac7d4692f6432a645cd5fe4bb1962b5636c6650_960x1707.jpg","review_num":9,"article_id":12,"type":2,"add_date":"2019-09-11 10:38:51","is_collection":"1"},{"collection_id":54,"out_id":14,"collection_time":1569490850,"m_id":13,"collection_code":"baike","article_title":"大地","article_img":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190912/83e0c8f65edc40ca1a99641aa526db06a29c4d1b_960x1707.jpg","review_num":0,"article_id":14,"type":2,"add_date":"2019-09-12 10:27:55","is_collection":"1"},{"collection_id":53,"out_id":13,"collection_time":1569490840,"m_id":13,"collection_code":"baike","article_title":"标题","article_img":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190912/1cc8ccbf767986f76afac1995a9c53c9af837568_960x1707.jpg","review_num":14,"article_id":13,"type":1,"add_date":"2019-09-12 09:11:41","is_collection":"1"}]}
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
             * collection_id : 56
             * out_id : 11
             * collection_time : 1569490861
             * m_id : 13
             * collection_code : baike
             * article_title : 212标题
             * article_img : http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190911/ec89d14ace654f78e7ba93175aa337c0ba049dfb_960x1506.jpg
             * review_num : 1
             * article_id : 11
             * type : 1
             * add_date : 2019-09-11 10:13:55
             * is_collection : 1
             */

            private int collection_id;
            private int out_id;
            private int collection_time;
            private int m_id;
            private String collection_code;
            private String article_title;
            private String article_img;
            private int review_num;
            private int article_id;
            private int type;
            private String add_date;
            private String is_collection;

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

            public String getArticle_title() {
                return article_title;
            }

            public void setArticle_title(String article_title) {
                this.article_title = article_title;
            }

            public String getArticle_img() {
                return article_img;
            }

            public void setArticle_img(String article_img) {
                this.article_img = article_img;
            }

            public int getReview_num() {
                return review_num;
            }

            public void setReview_num(int review_num) {
                this.review_num = review_num;
            }

            public int getArticle_id() {
                return article_id;
            }

            public void setArticle_id(int article_id) {
                this.article_id = article_id;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public String getAdd_date() {
                return add_date;
            }

            public void setAdd_date(String add_date) {
                this.add_date = add_date;
            }

            public String getIs_collection() {
                return is_collection;
            }

            public void setIs_collection(String is_collection) {
                this.is_collection = is_collection;
            }
        }
    }
}
