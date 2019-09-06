package com.hongyuan.fitness.ui.person.mine_message.child_message;

import com.hongyuan.fitness.base.BaseBean;

import java.util.List;

public class MineChildMessageBeans extends BaseBean {

    /**
     * hasmore : false
     * curpage : 1
     * page_total : 1
     * data : {"list":[{"msg_id":8,"msg_title":"您的朋友圈有人评论，快去看看吧","msg_content":"有用户评论了您的朋友圈，进去回复吧","to_mobile":"18183185173","is_public":2,"msg_category_id":2,"msg_time":1564733891,"msg_img":"","is_read":1,"href_type":1,"href":"","href_code":"circle_info","href_id":10,"is_push":1,"admin_id":0,"msg_date":"2019-08-02 16:18:11"},{"msg_id":7,"msg_title":"您的朋友圈有人评论，快去看看吧","msg_content":"有用户评论了您的朋友圈，进去回复吧","to_mobile":"18183185173","is_public":2,"msg_category_id":2,"msg_time":1564733848,"msg_img":"","is_read":0,"href_type":1,"href":"","href_code":"circle_info","href_id":11,"is_push":1,"admin_id":0,"msg_date":"2019-08-02 16:17:28"},{"msg_id":6,"msg_title":"您的朋友圈有人点赞，快去看看吧","msg_content":"有用户点赞了您的朋友圈，进去回复吧","to_mobile":"18183185173","is_public":2,"msg_category_id":2,"msg_time":1564733723,"msg_img":"","is_read":0,"href_type":1,"href":"","href_code":"circle_info","href_id":11,"is_push":1,"admin_id":0,"msg_date":"2019-08-02 16:15:23"},{"msg_id":5,"msg_title":"您的朋友圈有人点赞，快去看看吧","msg_content":"有用户点赞了您的朋友圈，进去回复吧","to_mobile":"18183185173","is_public":2,"msg_category_id":2,"msg_time":1564733536,"msg_img":"","is_read":0,"href_type":1,"href":"","href_code":"circle_info","href_id":11,"is_push":1,"admin_id":0,"msg_date":"2019-08-02 16:12:16"},{"msg_id":4,"msg_title":"您的朋友圈有人点赞，快去看看吧","msg_content":"有用户点赞了您的朋友圈，进去回复吧","to_mobile":"18183185173","is_public":2,"msg_category_id":2,"msg_time":1564733496,"msg_img":"","is_read":1,"href_type":1,"href":"","href_code":"circle_info","href_id":11,"is_push":1,"admin_id":0,"msg_date":"2019-08-02 16:11:36"},{"msg_id":3,"msg_title":"您的朋友圈有人点赞，快去看看吧","msg_content":"有用户点赞了您的朋友圈，进去回复吧","to_mobile":"18183185173","is_public":2,"msg_category_id":2,"msg_time":1564733471,"msg_img":"","is_read":0,"href_type":1,"href":"","href_code":"circle_info","href_id":11,"is_push":1,"admin_id":0,"msg_date":"2019-08-02 16:11:11"},{"msg_id":2,"msg_title":"您的朋友圈有人点赞，快去看看吧","msg_content":"有用户点赞了您的朋友圈，进去回复吧","to_mobile":"18183185173","is_public":2,"msg_category_id":2,"msg_time":1564733451,"msg_img":"","is_read":0,"href_type":1,"href":"","href_code":"circle_info","href_id":11,"is_push":1,"admin_id":0,"msg_date":"2019-08-02 16:10:51"}]}
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
             * msg_id : 8
             * msg_title : 您的朋友圈有人评论，快去看看吧
             * msg_content : 有用户评论了您的朋友圈，进去回复吧
             * to_mobile : 18183185173
             * is_public : 2
             * msg_category_id : 2
             * msg_time : 1564733891
             * msg_img :
             * is_read : 1
             * href_type : 1
             * href :
             * href_code : circle_info
             * href_id : 10
             * is_push : 1
             * admin_id : 0
             * msg_date : 2019-08-02 16:18:11
             */

            private int msg_id;
            private String msg_title;
            private String msg_content;
            private String to_mobile;
            private int is_public;
            private int msg_category_id;
            private int msg_time;
            private String msg_img;
            private int is_read;
            private int href_type;
            private String href;
            private String href_code;
            private int href_id;
            private int is_push;
            private int admin_id;
            private String msg_date;

            public int getMsg_id() {
                return msg_id;
            }

            public void setMsg_id(int msg_id) {
                this.msg_id = msg_id;
            }

            public String getMsg_title() {
                return msg_title;
            }

            public void setMsg_title(String msg_title) {
                this.msg_title = msg_title;
            }

            public String getMsg_content() {
                return msg_content;
            }

            public void setMsg_content(String msg_content) {
                this.msg_content = msg_content;
            }

            public String getTo_mobile() {
                return to_mobile;
            }

            public void setTo_mobile(String to_mobile) {
                this.to_mobile = to_mobile;
            }

            public int getIs_public() {
                return is_public;
            }

            public void setIs_public(int is_public) {
                this.is_public = is_public;
            }

            public int getMsg_category_id() {
                return msg_category_id;
            }

            public void setMsg_category_id(int msg_category_id) {
                this.msg_category_id = msg_category_id;
            }

            public int getMsg_time() {
                return msg_time;
            }

            public void setMsg_time(int msg_time) {
                this.msg_time = msg_time;
            }

            public String getMsg_img() {
                return msg_img;
            }

            public void setMsg_img(String msg_img) {
                this.msg_img = msg_img;
            }

            public int getIs_read() {
                return is_read;
            }

            public void setIs_read(int is_read) {
                this.is_read = is_read;
            }

            public int getHref_type() {
                return href_type;
            }

            public void setHref_type(int href_type) {
                this.href_type = href_type;
            }

            public String getHref() {
                return href;
            }

            public void setHref(String href) {
                this.href = href;
            }

            public String getHref_code() {
                return href_code;
            }

            public void setHref_code(String href_code) {
                this.href_code = href_code;
            }

            public int getHref_id() {
                return href_id;
            }

            public void setHref_id(int href_id) {
                this.href_id = href_id;
            }

            public int getIs_push() {
                return is_push;
            }

            public void setIs_push(int is_push) {
                this.is_push = is_push;
            }

            public int getAdmin_id() {
                return admin_id;
            }

            public void setAdmin_id(int admin_id) {
                this.admin_id = admin_id;
            }

            public String getMsg_date() {
                return msg_date;
            }

            public void setMsg_date(String msg_date) {
                this.msg_date = msg_date;
            }
        }
    }
}
