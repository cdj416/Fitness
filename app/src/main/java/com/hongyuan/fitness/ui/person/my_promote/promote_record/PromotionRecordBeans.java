package com.hongyuan.fitness.ui.person.my_promote.promote_record;

import com.hongyuan.fitness.base.BaseBean;

import java.util.List;

public class PromotionRecordBeans extends BaseBean {

    /**
     * hasmore : false
     * curpage : 1
     * page_total : 0
     * data : {"list":[{"m_name":"m88888","m_mobile":"186****4159","mi_head":""}],"mytg":{"m_name":"m88888","m_mobile":"186****4159","mi_head":""},"num":0}
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
        /**
         * list : [{"m_name":"m88888","m_mobile":"186****4159","mi_head":""}]
         * mytg : {"m_name":"m88888","m_mobile":"186****4159","mi_head":""}
         * num : 0
         */

        private MytgBean mytg;
        private int num;
        private List<ListBean> list;

        public MytgBean getMytg() {
            return mytg;
        }

        public void setMytg(MytgBean mytg) {
            this.mytg = mytg;
        }

        public int getNum() {
            return num;
        }

        public void setNum(int num) {
            this.num = num;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class MytgBean {
            /**
             * m_name : m88888
             * m_mobile : 186****4159
             * mi_head :
             */

            private String m_name;
            private String m_mobile;
            private String mi_head;

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

            public String getMi_head() {
                return mi_head;
            }

            public void setMi_head(String mi_head) {
                this.mi_head = mi_head;
            }
        }

        public static class ListBean {
            /**
             * m_name : m88888
             * m_mobile : 186****4159
             * mi_head :
             */

            private String m_name;
            private String m_mobile;
            private String mi_head;

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

            public String getMi_head() {
                return mi_head;
            }

            public void setMi_head(String mi_head) {
                this.mi_head = mi_head;
            }
        }
    }
}
