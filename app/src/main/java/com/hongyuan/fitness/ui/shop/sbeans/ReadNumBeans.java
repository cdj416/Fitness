package com.hongyuan.fitness.ui.shop.sbeans;

import com.hongyuan.fitness.base.BaseBean;

public class ReadNumBeans extends BaseBean {

    /**
     * data : {"all":0,"msg_private":0,"msg_review":0}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * all : 0
         * msg_private : 0
         * msg_review : 0
         */

        private int all;
        private int msg_private;
        private int msg_review;

        public int getAll() {
            return all;
        }

        public void setAll(int all) {
            this.all = all;
        }

        public int getMsg_private() {
            return msg_private;
        }

        public void setMsg_private(int msg_private) {
            this.msg_private = msg_private;
        }

        public int getMsg_review() {
            return msg_review;
        }

        public void setMsg_review(int msg_review) {
            this.msg_review = msg_review;
        }
    }
}
