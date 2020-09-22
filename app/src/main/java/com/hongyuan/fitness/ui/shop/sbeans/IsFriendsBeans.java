package com.hongyuan.fitness.ui.shop.sbeans;

import com.hongyuan.fitness.base.BaseBean;

public class IsFriendsBeans extends BaseBean {

    /**
     * data : {"is_friend":1,"msg":"已经关注"}
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
         * is_friend : 1
         * msg : 已经关注
         */

        private int is_friend;
        private String msg;

        public int getIs_friend() {
            return is_friend;
        }

        public void setIs_friend(int is_friend) {
            this.is_friend = is_friend;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }
    }
}
