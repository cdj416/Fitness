package com.hongyuan.fitness.ui.shop.sbeans;

import com.hongyuan.fitness.base.BaseBean;

import java.util.List;

public class SmParaiseDetailsBeans extends BaseBean {

    /**
     * hasmore : false
     * curpage : 1
     * page_total : 1
     * data : {"list":[{"m_id":3,"m_mobile":"18183185173","mi_head":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20200414/2e4f6772c99ea949617b0cc3c61b52217596ea71_1920x1008.jpeg","mi_realname":"陈道明","m_name":"妮妮","mi_sex":2},{"m_id":4739,"m_mobile":"13757267593","mi_head":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20200416/372cc639893881858c1c77f8074b059a194901e6_2436x2436.jpg","mi_realname":"朱伟","m_name":"菰城少年","mi_sex":1}]}
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
             * m_id : 3
             * m_mobile : 18183185173
             * mi_head : http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20200414/2e4f6772c99ea949617b0cc3c61b52217596ea71_1920x1008.jpeg
             * mi_realname : 陈道明
             * m_name : 妮妮
             * mi_sex : 2
             */

            private int m_id;
            private String m_mobile;
            private String mi_head;
            private String mi_realname;
            private String m_name;
            private int mi_sex;

            public int getM_id() {
                return m_id;
            }

            public void setM_id(int m_id) {
                this.m_id = m_id;
            }

            public String getM_mobile() {
                return m_mobile;
            }

            public void setM_mobile(String m_mobile) {
                this.m_mobile = m_mobile;
            }

            public String getMi_head() {
                return mi_head;
            }

            public void setMi_head(String mi_head) {
                this.mi_head = mi_head;
            }

            public String getMi_realname() {
                return mi_realname;
            }

            public void setMi_realname(String mi_realname) {
                this.mi_realname = mi_realname;
            }

            public String getM_name() {
                return m_name;
            }

            public void setM_name(String m_name) {
                this.m_name = m_name;
            }

            public int getMi_sex() {
                return mi_sex;
            }

            public void setMi_sex(int mi_sex) {
                this.mi_sex = mi_sex;
            }
        }
    }
}
