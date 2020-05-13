package com.hongyuan.fitness.ui.shop.sbeans;

import com.hongyuan.fitness.base.BaseBean;

public class AdminNumUse extends BaseBean {

    /**
     * data : {"state":1,"msg":"管理员账号可以使用","code":"2005","data":""}
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
         * state : 1
         * msg : 管理员账号可以使用
         * code : 2005
         * data :
         */

        private int state;
        private String msg;
        private String code;
        private String data;

        public int getState() {
            return state;
        }

        public void setState(int state) {
            this.state = state;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getData() {
            return data;
        }

        public void setData(String data) {
            this.data = data;
        }
    }
}
