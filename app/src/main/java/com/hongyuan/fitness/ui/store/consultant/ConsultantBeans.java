package com.hongyuan.fitness.ui.store.consultant;

import com.hongyuan.fitness.base.BaseBean;

import java.util.List;

public class ConsultantBeans extends BaseBean {


    /**
     * hasmore : false
     * curpage : 1
     * page_total : 1
     * data : {"list":[{"m_id":5133,"m_name":"沈路","m_mobile":"17858759016","mi_head":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20200103/cdc12f8534c4fbabf8048ab8395cb94344ca68d7_240x240.jpg","pid":35,"cid":3505,"did":350502,"tj_mid":0,"sale_mid":0,"add_time":1575005291,"mi_realname":"沈路","province":"浙江省","city":"湖州市","distinct":"吴兴区","tj_mobile":"","sale_mobile":"","add_date":"2019-11-29 13:28:11"},{"m_id":120,"m_name":"林峰","m_mobile":"18367253038","mi_head":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20200301/b70ec7877a497a4b0ef0c1305c6857331905ab9d_828x828.jpg","pid":35,"cid":3505,"did":350502,"tj_mid":0,"sale_mid":0,"add_time":1567349495,"mi_realname":"卜川平","province":"浙江省","city":"湖州市","distinct":"吴兴区","tj_mobile":"","sale_mobile":"","add_date":"2019-09-01 22:51:35"},{"m_id":86,"m_name":"叶臣武","m_mobile":"15857234115","mi_head":"","pid":35,"cid":3505,"did":0,"tj_mid":0,"sale_mid":0,"add_time":1565342576,"mi_realname":"叶臣武","province":"浙江省","city":"湖州市","distinct":null,"tj_mobile":"","sale_mobile":"","add_date":"2019-08-09 17:22:56"},{"m_id":35,"m_name":"费森佳就是费费","m_mobile":"15268290191","mi_head":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20200106/81f4d51e9422223e843305b4a50cfbf85fe29fb3_2436x2436.jpg","pid":35,"cid":3505,"did":350502,"tj_mid":0,"sale_mid":0,"add_time":1564367379,"mi_realname":"费森佳","province":"浙江省","city":"湖州市","distinct":"吴兴区","tj_mobile":"","sale_mobile":"","add_date":"2019-07-29 10:29:39"}]}
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
             * m_id : 5133
             * m_name : 沈路
             * m_mobile : 17858759016
             * mi_head : http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20200103/cdc12f8534c4fbabf8048ab8395cb94344ca68d7_240x240.jpg
             * pid : 35
             * cid : 3505
             * did : 350502
             * tj_mid : 0
             * sale_mid : 0
             * add_time : 1575005291
             * mi_realname : 沈路
             * province : 浙江省
             * city : 湖州市
             * distinct : 吴兴区
             * tj_mobile :
             * sale_mobile :
             * add_date : 2019-11-29 13:28:11
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
            private String mi_realname;
            private String province;
            private String city;
            private String distinct;
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

            public String getMi_realname() {
                return mi_realname;
            }

            public void setMi_realname(String mi_realname) {
                this.mi_realname = mi_realname;
            }

            public String getProvince() {
                return province;
            }

            public void setProvince(String province) {
                this.province = province;
            }

            public String getCity() {
                return city;
            }

            public void setCity(String city) {
                this.city = city;
            }

            public String getDistinct() {
                return distinct;
            }

            public void setDistinct(String distinct) {
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
