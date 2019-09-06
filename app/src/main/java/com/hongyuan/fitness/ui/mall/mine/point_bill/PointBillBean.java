package com.hongyuan.fitness.ui.mall.mine.point_bill;

import com.hongyuan.fitness.base.BaseBean;

import java.util.List;

public class PointBillBean extends BaseBean {

    /**
     * hasmore : false
     * curpage : 1
     * page_total : 1
     * data : {"list":[{"pl_id":68,"pl_type":"reduce","pl_reason":"积分购物-dfasfdsadfsfds","pl_reason_id":11,"pl_point_num":2,"pl_time":1564105897,"m_id":3,"pl_out_id":97,"pl_date":"2019-07-26 09:51:37"},{"pl_id":67,"pl_type":"reduce","pl_reason":"积分购物-dfasfdsadfsfds","pl_reason_id":11,"pl_point_num":2,"pl_time":1564105515,"m_id":3,"pl_out_id":96,"pl_date":"2019-07-26 09:45:15"},{"pl_id":2,"pl_type":"add","pl_reason":"注册送积分","pl_reason_id":1,"pl_point_num":10,"pl_time":1563765996,"m_id":3,"pl_out_id":0,"pl_date":"2019-07-22 11:26:36"}]}
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
             * pl_id : 68
             * pl_type : reduce
             * pl_reason : 积分购物-dfasfdsadfsfds
             * pl_reason_id : 11
             * pl_point_num : 2
             * pl_time : 1564105897
             * m_id : 3
             * pl_out_id : 97
             * pl_date : 2019-07-26 09:51:37
             */

            private int pl_id;
            private String pl_type;
            private String pl_reason;
            private int pl_reason_id;
            private int pl_point_num;
            private int pl_time;
            private int m_id;
            private int pl_out_id;
            private String pl_date;

            public int getPl_id() {
                return pl_id;
            }

            public void setPl_id(int pl_id) {
                this.pl_id = pl_id;
            }

            public String getPl_type() {
                return pl_type;
            }

            public void setPl_type(String pl_type) {
                this.pl_type = pl_type;
            }

            public String getPl_reason() {
                return pl_reason;
            }

            public void setPl_reason(String pl_reason) {
                this.pl_reason = pl_reason;
            }

            public int getPl_reason_id() {
                return pl_reason_id;
            }

            public void setPl_reason_id(int pl_reason_id) {
                this.pl_reason_id = pl_reason_id;
            }

            public int getPl_point_num() {
                return pl_point_num;
            }

            public void setPl_point_num(int pl_point_num) {
                this.pl_point_num = pl_point_num;
            }

            public int getPl_time() {
                return pl_time;
            }

            public void setPl_time(int pl_time) {
                this.pl_time = pl_time;
            }

            public int getM_id() {
                return m_id;
            }

            public void setM_id(int m_id) {
                this.m_id = m_id;
            }

            public int getPl_out_id() {
                return pl_out_id;
            }

            public void setPl_out_id(int pl_out_id) {
                this.pl_out_id = pl_out_id;
            }

            public String getPl_date() {
                return pl_date;
            }

            public void setPl_date(String pl_date) {
                this.pl_date = pl_date;
            }
        }
    }
}
