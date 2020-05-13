package com.hongyuan.fitness.ui.shop.sbeans;

import com.hongyuan.fitness.base.BaseBean;

import java.util.List;

public class PickUpAddress extends BaseBean {

    /**
     * data : {"list":[{"zt_address_id":1,"pid":10,"cid":1001,"did":0,"pname":"北京市","cname":"朝阳区","dname":"","address":"街道2003","mobile":"18888888888","person":"吴总","lat":"1000","lng":"1000","zt_name":"北京朝阳点","m_id":1,"store_id":42,"is_del":0,"distance_um":14491783}]}
     */

    private DataBean data;

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
             * zt_address_id : 1
             * pid : 10
             * cid : 1001
             * did : 0
             * pname : 北京市
             * cname : 朝阳区
             * dname :
             * address : 街道2003
             * mobile : 18888888888
             * person : 吴总
             * lat : 1000
             * lng : 1000
             * zt_name : 北京朝阳点
             * m_id : 1
             * store_id : 42
             * is_del : 0
             * distance_um : 14491783
             */

            private int zt_address_id;
            private int pid;
            private int cid;
            private int did;
            private String pname;
            private String cname;
            private String dname;
            private String address;
            private String mobile;
            private String person;
            private String lat;
            private String lng;
            private String zt_name;
            private int m_id;
            private int store_id;
            private int is_del;
            private long distance_um;
            private boolean select;

            public boolean isSelect() {
                return select;
            }

            public void setSelect(boolean select) {
                this.select = select;
            }

            public int getZt_address_id() {
                return zt_address_id;
            }

            public void setZt_address_id(int zt_address_id) {
                this.zt_address_id = zt_address_id;
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

            public String getPname() {
                return pname;
            }

            public void setPname(String pname) {
                this.pname = pname;
            }

            public String getCname() {
                return cname;
            }

            public void setCname(String cname) {
                this.cname = cname;
            }

            public String getDname() {
                return dname;
            }

            public void setDname(String dname) {
                this.dname = dname;
            }

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
            }

            public String getMobile() {
                return mobile;
            }

            public void setMobile(String mobile) {
                this.mobile = mobile;
            }

            public String getPerson() {
                return person;
            }

            public void setPerson(String person) {
                this.person = person;
            }

            public String getLat() {
                return lat;
            }

            public void setLat(String lat) {
                this.lat = lat;
            }

            public String getLng() {
                return lng;
            }

            public void setLng(String lng) {
                this.lng = lng;
            }

            public String getZt_name() {
                return zt_name;
            }

            public void setZt_name(String zt_name) {
                this.zt_name = zt_name;
            }

            public int getM_id() {
                return m_id;
            }

            public void setM_id(int m_id) {
                this.m_id = m_id;
            }

            public int getStore_id() {
                return store_id;
            }

            public void setStore_id(int store_id) {
                this.store_id = store_id;
            }

            public int getIs_del() {
                return is_del;
            }

            public void setIs_del(int is_del) {
                this.is_del = is_del;
            }

            public long getDistance_um() {
                return distance_um;
            }

            public void setDistance_um(long distance_um) {
                this.distance_um = distance_um;
            }
        }
    }
}
