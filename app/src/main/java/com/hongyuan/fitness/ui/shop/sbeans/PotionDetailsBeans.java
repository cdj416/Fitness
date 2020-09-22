package com.hongyuan.fitness.ui.shop.sbeans;

import com.hongyuan.fitness.base.BaseBean;

import java.util.List;

public class PotionDetailsBeans extends BaseBean {

    /**
     * hasmore : true
     * curpage : 1
     * page_total : 14
     * data : {"list":[{"pl_id":62005,"pl_type":"add","pl_reason":"阅读健康百科","pl_reason_id":24,"pl_point_num":2,"pl_time":1600242637,"m_id":3,"pl_out_id":0,"pl_date":"2020-09-16 15:50:37"},{"pl_id":62004,"pl_type":"add","pl_reason":"阅读健康百科","pl_reason_id":24,"pl_point_num":2,"pl_time":1600242600,"m_id":3,"pl_out_id":0,"pl_date":"2020-09-16 15:50:00"},{"pl_id":62001,"pl_type":"add","pl_reason":"阅读健康百科","pl_reason_id":24,"pl_point_num":2,"pl_time":1600242563,"m_id":3,"pl_out_id":0,"pl_date":"2020-09-16 15:49:23"},{"pl_id":61997,"pl_type":"add","pl_reason":"阅读健康百科","pl_reason_id":24,"pl_point_num":2,"pl_time":1600242553,"m_id":3,"pl_out_id":0,"pl_date":"2020-09-16 15:49:13"},{"pl_id":61991,"pl_type":"add","pl_reason":"阅读健康百科","pl_reason_id":24,"pl_point_num":2,"pl_time":1600242318,"m_id":3,"pl_out_id":0,"pl_date":"2020-09-16 15:45:18"},{"pl_id":61984,"pl_type":"add","pl_reason":"点赞别人","pl_reason_id":3,"pl_point_num":1,"pl_time":1600239618,"m_id":3,"pl_out_id":0,"pl_date":"2020-09-16 15:00:18"},{"pl_id":61982,"pl_type":"add","pl_reason":"点赞别人","pl_reason_id":3,"pl_point_num":1,"pl_time":1600239610,"m_id":3,"pl_out_id":0,"pl_date":"2020-09-16 15:00:10"},{"pl_id":61978,"pl_type":"add","pl_reason":"点赞别人","pl_reason_id":3,"pl_point_num":1,"pl_time":1600238935,"m_id":3,"pl_out_id":0,"pl_date":"2020-09-16 14:48:55"},{"pl_id":61976,"pl_type":"add","pl_reason":"点赞别人","pl_reason_id":3,"pl_point_num":1,"pl_time":1600238418,"m_id":3,"pl_out_id":0,"pl_date":"2020-09-16 14:40:18"},{"pl_id":61974,"pl_type":"add","pl_reason":"点赞别人","pl_reason_id":3,"pl_point_num":1,"pl_time":1600238402,"m_id":3,"pl_out_id":0,"pl_date":"2020-09-16 14:40:02"}]}
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
             * pl_id : 62005
             * pl_type : add
             * pl_reason : 阅读健康百科
             * pl_reason_id : 24
             * pl_point_num : 2
             * pl_time : 1600242637
             * m_id : 3
             * pl_out_id : 0
             * pl_date : 2020-09-16 15:50:37
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
