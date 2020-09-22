package com.hongyuan.fitness.ui.shop.sbeans;

import com.hongyuan.fitness.base.BaseBean;

import java.util.List;

public class SmFanBeans extends BaseBean {

    /**
     * hasmore : false
     * curpage : 1
     * page_total : 1
     * data : {"list":[{"msg_id":38402,"m_name":"妮妮","m_mobile":"18183185173","m_id":3,"mi_head":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20200414/2e4f6772c99ea949617b0cc3c61b52217596ea71_1920x1008.jpeg","msg_time":"2020-08-23 11:23:52","is_read":0}]}
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
             * msg_id : 38402
             * m_name : 妮妮
             * m_mobile : 18183185173
             * m_id : 3
             * mi_head : http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20200414/2e4f6772c99ea949617b0cc3c61b52217596ea71_1920x1008.jpeg
             * msg_time : 2020-08-23 11:23:52
             * is_read : 0
             */

            private int msg_id;
            private String m_name;
            private String m_mobile;
            private int m_id;
            private String mi_head;
            private String msg_time;
            private int is_read;

            public int getMsg_id() {
                return msg_id;
            }

            public void setMsg_id(int msg_id) {
                this.msg_id = msg_id;
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

            public int getM_id() {
                return m_id;
            }

            public void setM_id(int m_id) {
                this.m_id = m_id;
            }

            public String getMi_head() {
                return mi_head;
            }

            public void setMi_head(String mi_head) {
                this.mi_head = mi_head;
            }

            public String getMsg_time() {
                return msg_time;
            }

            public void setMsg_time(String msg_time) {
                this.msg_time = msg_time;
            }

            public int getIs_read() {
                return is_read;
            }

            public void setIs_read(int is_read) {
                this.is_read = is_read;
            }
        }
    }
}
