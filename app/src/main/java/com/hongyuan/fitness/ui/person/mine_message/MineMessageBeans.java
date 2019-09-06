package com.hongyuan.fitness.ui.person.mine_message;

import com.hongyuan.fitness.base.BaseBean;

import java.util.List;

public class MineMessageBeans extends BaseBean {

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
         * msg_category_name : 系统通知
         * msg_category_img : https://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/my/%E7%B3%BB%E7%BB%9F%E9%80%9A%E7%9F%A5%402x.png
         */

        private int msg_category_id;
        private String msg_category_name;
        private String msg_category_img;

        public int getMsg_category_id() {
            return msg_category_id;
        }

        public void setMsg_category_id(int msg_category_id) {
            this.msg_category_id = msg_category_id;
        }

        public String getMsg_category_name() {
            return msg_category_name;
        }

        public void setMsg_category_name(String msg_category_name) {
            this.msg_category_name = msg_category_name;
        }

        public String getMsg_category_img() {
            return msg_category_img;
        }

        public void setMsg_category_img(String msg_category_img) {
            this.msg_category_img = msg_category_img;
        }
    }
}
