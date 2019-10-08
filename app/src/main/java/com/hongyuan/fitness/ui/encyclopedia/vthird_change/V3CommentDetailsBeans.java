package com.hongyuan.fitness.ui.encyclopedia.vthird_change;

import com.hongyuan.fitness.base.BaseBean;

import java.util.List;

public class V3CommentDetailsBeans extends BaseBean {

    /**
     * hasmore : false
     * curpage : 1
     * page_total : 1
     * data : {"list":[{"ar_id":81,"article_id":13,"ar_id_father":78,"m_id":13,"to_mid":13,"ar_content":"麻麻","ar_state":1,"add_time":1569401859,"update_admin":0,"update_time":0,"reply_num":0,"praise_num":0,"first_ar_id":78,"m_name":"小陈","m_mobile":"18183185173","mi_head":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190925/c5455b02fc8634cf663c35bb551424112a119e58_1334x1334.jpg","article_title":"标题","add_date":"2019-09-25 16:57:39","is_praise":0,"to_m_name":"小陈"},{"ar_id":80,"article_id":13,"ar_id_father":79,"m_id":13,"to_mid":13,"ar_content":"卡卡","ar_state":1,"add_time":1569401852,"update_admin":0,"update_time":0,"reply_num":0,"praise_num":0,"first_ar_id":78,"m_name":"小陈","m_mobile":"18183185173","mi_head":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190925/c5455b02fc8634cf663c35bb551424112a119e58_1334x1334.jpg","article_title":"标题","add_date":"2019-09-25 16:57:32","is_praise":0,"to_m_name":"小陈"},{"ar_id":79,"article_id":13,"ar_id_father":78,"m_id":13,"to_mid":13,"ar_content":"哈哈","ar_state":1,"add_time":1569401847,"update_admin":0,"update_time":0,"reply_num":1,"praise_num":0,"first_ar_id":78,"m_name":"小陈","m_mobile":"18183185173","mi_head":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190925/c5455b02fc8634cf663c35bb551424112a119e58_1334x1334.jpg","article_title":"标题","add_date":"2019-09-25 16:57:27","is_praise":0,"to_m_name":"小陈"}]}
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
             * ar_id : 81
             * article_id : 13
             * ar_id_father : 78
             * m_id : 13
             * to_mid : 13
             * ar_content : 麻麻
             * ar_state : 1
             * add_time : 1569401859
             * update_admin : 0
             * update_time : 0
             * reply_num : 0
             * praise_num : 0
             * first_ar_id : 78
             * m_name : 小陈
             * m_mobile : 18183185173
             * mi_head : http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190925/c5455b02fc8634cf663c35bb551424112a119e58_1334x1334.jpg
             * article_title : 标题
             * add_date : 2019-09-25 16:57:39
             * is_praise : 0
             * to_m_name : 小陈
             */

            private int ar_id;
            private int article_id;
            private int ar_id_father;
            private int m_id;
            private int to_mid;
            private String ar_content;
            private int ar_state;
            private int add_time;
            private int update_admin;
            private int update_time;
            private int reply_num;
            private int praise_num;
            private int first_ar_id;
            private String m_name;
            private String m_mobile;
            private String mi_head;
            private String article_title;
            private String add_date;
            private int is_praise;
            private String to_m_name;

            public int getAr_id() {
                return ar_id;
            }

            public void setAr_id(int ar_id) {
                this.ar_id = ar_id;
            }

            public int getArticle_id() {
                return article_id;
            }

            public void setArticle_id(int article_id) {
                this.article_id = article_id;
            }

            public int getAr_id_father() {
                return ar_id_father;
            }

            public void setAr_id_father(int ar_id_father) {
                this.ar_id_father = ar_id_father;
            }

            public int getM_id() {
                return m_id;
            }

            public void setM_id(int m_id) {
                this.m_id = m_id;
            }

            public int getTo_mid() {
                return to_mid;
            }

            public void setTo_mid(int to_mid) {
                this.to_mid = to_mid;
            }

            public String getAr_content() {
                return ar_content;
            }

            public void setAr_content(String ar_content) {
                this.ar_content = ar_content;
            }

            public int getAr_state() {
                return ar_state;
            }

            public void setAr_state(int ar_state) {
                this.ar_state = ar_state;
            }

            public int getAdd_time() {
                return add_time;
            }

            public void setAdd_time(int add_time) {
                this.add_time = add_time;
            }

            public int getUpdate_admin() {
                return update_admin;
            }

            public void setUpdate_admin(int update_admin) {
                this.update_admin = update_admin;
            }

            public int getUpdate_time() {
                return update_time;
            }

            public void setUpdate_time(int update_time) {
                this.update_time = update_time;
            }

            public int getReply_num() {
                return reply_num;
            }

            public void setReply_num(int reply_num) {
                this.reply_num = reply_num;
            }

            public int getPraise_num() {
                return praise_num;
            }

            public void setPraise_num(int praise_num) {
                this.praise_num = praise_num;
            }

            public int getFirst_ar_id() {
                return first_ar_id;
            }

            public void setFirst_ar_id(int first_ar_id) {
                this.first_ar_id = first_ar_id;
            }

            public String getM_name() {
                return m_name;
            }

            public void setM_name(String m_name) {
                this.m_name = m_name;
            }

            public String getM_mobile() {
                return m_mobile;
            }

            public void setM_mobile(String m_mobile) {
                this.m_mobile = m_mobile;
            }

            public String getMi_head() {
                return mi_head;
            }

            public void setMi_head(String mi_head) {
                this.mi_head = mi_head;
            }

            public String getArticle_title() {
                return article_title;
            }

            public void setArticle_title(String article_title) {
                this.article_title = article_title;
            }

            public String getAdd_date() {
                return add_date;
            }

            public void setAdd_date(String add_date) {
                this.add_date = add_date;
            }

            public int getIs_praise() {
                return is_praise;
            }

            public void setIs_praise(int is_praise) {
                this.is_praise = is_praise;
            }

            public String getTo_m_name() {
                return to_m_name;
            }

            public void setTo_m_name(String to_m_name) {
                this.to_m_name = to_m_name;
            }
        }
    }
}
