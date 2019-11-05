package com.hongyuan.fitness.ui.store.consultant;

import com.hongyuan.fitness.base.BaseBean;

import java.util.List;

public class ConsultantBeans extends BaseBean {


    /**
     * hasmore : false
     * curpage : 1
     * page_total : 1
     * data : {"list":[{"m_id":63,"m_name":"m88888","m_mobile":"18888888888","mi_head":"","pid":0,"cid":0,"did":0,"tj_mid":0,"sale_mid":0,"add_time":1571629990,"province":null,"city":null,"distinct":null,"tj_mobile":"","sale_mobile":"","add_date":"2019-10-21 11:53:10"},{"m_id":62,"m_name":"m21565","m_mobile":"15757121565","mi_head":"","pid":0,"cid":0,"did":0,"tj_mid":0,"sale_mid":0,"add_time":1569308838,"province":null,"city":null,"distinct":null,"tj_mobile":"","sale_mobile":"","add_date":"2019-09-24 15:07:18"},{"m_id":61,"m_name":"m83571","m_mobile":"13868283571","mi_head":"","pid":0,"cid":0,"did":0,"tj_mid":0,"sale_mid":0,"add_time":1569308838,"province":null,"city":null,"distinct":null,"tj_mobile":"","sale_mobile":"","add_date":"2019-09-24 15:07:18"},{"m_id":60,"m_name":"m26984","m_mobile":"13625726984","mi_head":"","pid":0,"cid":0,"did":0,"tj_mid":0,"sale_mid":0,"add_time":1569308838,"province":null,"city":null,"distinct":null,"tj_mobile":"","sale_mobile":"","add_date":"2019-09-24 15:07:18"},{"m_id":59,"m_name":"m03908","m_mobile":"18267203908","mi_head":"","pid":0,"cid":0,"did":0,"tj_mid":0,"sale_mid":0,"add_time":1569308837,"province":null,"city":null,"distinct":null,"tj_mobile":"","sale_mobile":"","add_date":"2019-09-24 15:07:17"}]}
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
             * m_id : 63
             * m_name : m88888
             * m_mobile : 18888888888
             * mi_head :
             * pid : 0
             * cid : 0
             * did : 0
             * tj_mid : 0
             * sale_mid : 0
             * add_time : 1571629990
             * province : null
             * city : null
             * distinct : null
             * tj_mobile :
             * sale_mobile :
             * add_date : 2019-10-21 11:53:10
             */

            private int m_id;
            private String m_name;
            private String m_mobile;
            private String mi_head;
            private int pid;
            private int cid;
            private int did;
            private int tj_mid;
            private int sale_mid;
            private int add_time;
            private Object province;
            private Object city;
            private Object distinct;
            private String tj_mobile;
            private String sale_mobile;
            private String add_date;

            public int getM_id() {
                return m_id;
            }

            public void setM_id(int m_id) {
                this.m_id = m_id;
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

            public String getMi_head() {
                return mi_head;
            }

            public void setMi_head(String mi_head) {
                this.mi_head = mi_head;
            }

            public int getPid() {
                return pid;
            }

            public void setPid(int pid) {
                this.pid = pid;
            }

            public int getCid() {
                return cid;
            }

            public void setCid(int cid) {
                this.cid = cid;
            }

            public int getDid() {
                return did;
            }

            public void setDid(int did) {
                this.did = did;
            }

            public int getTj_mid() {
                return tj_mid;
            }

            public void setTj_mid(int tj_mid) {
                this.tj_mid = tj_mid;
            }

            public int getSale_mid() {
                return sale_mid;
            }

            public void setSale_mid(int sale_mid) {
                this.sale_mid = sale_mid;
            }

            public int getAdd_time() {
                return add_time;
            }

            public void setAdd_time(int add_time) {
                this.add_time = add_time;
            }

            public Object getProvince() {
                return province;
            }

            public void setProvince(Object province) {
                this.province = province;
            }

            public Object getCity() {
                return city;
            }

            public void setCity(Object city) {
                this.city = city;
            }

            public Object getDistinct() {
                return distinct;
            }

            public void setDistinct(Object distinct) {
                this.distinct = distinct;
            }

            public String getTj_mobile() {
                return tj_mobile;
            }

            public void setTj_mobile(String tj_mobile) {
                this.tj_mobile = tj_mobile;
            }

            public String getSale_mobile() {
                return sale_mobile;
            }

            public void setSale_mobile(String sale_mobile) {
                this.sale_mobile = sale_mobile;
            }

            public String getAdd_date() {
                return add_date;
            }

            public void setAdd_date(String add_date) {
                this.add_date = add_date;
            }
        }
    }
}
