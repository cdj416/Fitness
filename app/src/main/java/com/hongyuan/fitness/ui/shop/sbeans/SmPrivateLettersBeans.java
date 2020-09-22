package com.hongyuan.fitness.ui.shop.sbeans;

import com.hongyuan.fitness.base.BaseBean;

import java.util.List;

public class SmPrivateLettersBeans extends BaseBean {

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * msg_category_id : 1
         * msg_category_img : https://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/my/%E7%B3%BB%E7%BB%9F%E9%80%9A%E7%9F%A5%402x.png
         * title : 系统通知
         * msg_content : 测试推送
         * msg_time : 1579057018
         * msg_date : 2020-01-15 10:56:58
         * count : 0
         */

        private int msg_category_id;
        private String msg_category_img;
        private String title;
        private String msg_content;
        private long msg_time;
        private String msg_date;
        private int count;

        public int getMsg_category_id() {
            return msg_category_id;
        }

        public void setMsg_category_id(int msg_category_id) {
            this.msg_category_id = msg_category_id;
        }

        public String getMsg_category_img() {
            return msg_category_img;
        }

        public void setMsg_category_img(String msg_category_img) {
            this.msg_category_img = msg_category_img;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
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

        public String getMsg_date() {
            return msg_date;
        }

        public void setMsg_date(String msg_date) {
            this.msg_date = msg_date;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }
    }
}
