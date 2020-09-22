package com.hongyuan.fitness.ui.shop.sbeans;

import com.hongyuan.fitness.base.BaseBean;

import java.util.List;

public class MsgDetailsBeans extends BaseBean {

    /**
     * hasmore : false
     * curpage : 1
     * page_total : 1
     * data : {"list":[{"msg_category_img":"https://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/my/%E7%B3%BB%E7%BB%9F%E9%80%9A%E7%9F%A5%402x.png","msg_category_id":1,"msg_id":5943,"is_read":1,"msg_title":"测试推送","href_id":0,"msg_content":"测试推送","msg_time":1579057018,"href_code":"index","href":"","href_type":1,"msg_date":"2020-01-15 10:56:58"},{"msg_category_img":"https://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/my/%E7%B3%BB%E7%BB%9F%E9%80%9A%E7%9F%A5%402x.png","msg_category_id":1,"msg_id":5944,"is_read":1,"msg_title":"测试推送2","href_id":0,"msg_content":"测试推送2","msg_time":1579057099,"href_code":"index","href":"","href_type":1,"msg_date":"2020-01-15 10:58:19"}]}
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
             * msg_category_img : https://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/my/%E7%B3%BB%E7%BB%9F%E9%80%9A%E7%9F%A5%402x.png
             * msg_category_id : 1
             * msg_id : 5943
             * is_read : 1
             * msg_title : 测试推送
             * href_id : 0
             * msg_content : 测试推送
             * msg_time : 1579057018
             * href_code : index
             * href :
             * href_type : 1
             * msg_date : 2020-01-15 10:56:58
             */

            private String msg_category_img;
            private int msg_category_id;
            private int msg_id;
            private int is_read;
            private String msg_title;
            private int href_id;
            private String msg_content;
            private long msg_time;
            private String href_code;
            private String href;
            private int href_type;
            private String msg_date;

            public String getMsg_category_img() {
                return msg_category_img;
            }

            public void setMsg_category_img(String msg_category_img) {
                this.msg_category_img = msg_category_img;
            }

            public int getMsg_category_id() {
                return msg_category_id;
            }

            public void setMsg_category_id(int msg_category_id) {
                this.msg_category_id = msg_category_id;
            }

            public int getMsg_id() {
                return msg_id;
            }

            public void setMsg_id(int msg_id) {
                this.msg_id = msg_id;
            }

            public int getIs_read() {
                return is_read;
            }

            public void setIs_read(int is_read) {
                this.is_read = is_read;
            }

            public String getMsg_title() {
                return msg_title;
            }

            public void setMsg_title(String msg_title) {
                this.msg_title = msg_title;
            }

            public int getHref_id() {
                return href_id;
            }

            public void setHref_id(int href_id) {
                this.href_id = href_id;
            }

            public String getMsg_content() {
                return msg_content;
            }

            public void setMsg_content(String msg_content) {
                this.msg_content = msg_content;
            }

            public long getMsg_time() {
                return msg_time;
            }

            public void setMsg_time(long msg_time) {
                this.msg_time = msg_time;
            }

            public String getHref_code() {
                return href_code;
            }

            public void setHref_code(String href_code) {
                this.href_code = href_code;
            }

            public String getHref() {
                return href;
            }

            public void setHref(String href) {
                this.href = href;
            }

            public int getHref_type() {
                return href_type;
            }

            public void setHref_type(int href_type) {
                this.href_type = href_type;
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
