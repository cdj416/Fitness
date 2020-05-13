package com.hongyuan.fitness.ui.shop.sbeans;

import com.hongyuan.fitness.base.BaseBean;

public class AddressInfoBeans extends BaseBean {

    /**
     * data : {"address_id":6,"name":"摩旅","tel":"166194994","address":"一你仔仔细细","is_default":1,"pid":12,"cid":1201,"did":120102,"m_id":3,"addtime":1587720859,"updatetime":1587720859,"is_del":0,"type_id":1,"province":"河北省","city":"石家庄市","district":"长安区"}
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
         * address_id : 6
         * name : 摩旅
         * tel : 166194994
         * address : 一你仔仔细细
         * is_default : 1
         * pid : 12
         * cid : 1201
         * did : 120102
         * m_id : 3
         * addtime : 1587720859
         * updatetime : 1587720859
         * is_del : 0
         * type_id : 1
         * province : 河北省
         * city : 石家庄市
         * district : 长安区
         */

        private int address_id;
        private String name;
        private String tel;
        private String address;
        private int is_default;
        private int pid;
        private int cid;
        private int did;
        private int m_id;
        private int addtime;
        private int updatetime;
        private int is_del;
        private int type_id;
        private String province;
        private String city;
        private String district;

        public int getAddress_id() {
            return address_id;
        }

        public void setAddress_id(int address_id) {
            this.address_id = address_id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getTel() {
            return tel;
        }

        public void setTel(String tel) {
            this.tel = tel;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public int getIs_default() {
            return is_default;
        }

        public void setIs_default(int is_default) {
            this.is_default = is_default;
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

        public int getM_id() {
            return m_id;
        }

        public void setM_id(int m_id) {
            this.m_id = m_id;
        }

        public int getAddtime() {
            return addtime;
        }

        public void setAddtime(int addtime) {
            this.addtime = addtime;
        }

        public int getUpdatetime() {
            return updatetime;
        }

        public void setUpdatetime(int updatetime) {
            this.updatetime = updatetime;
        }

        public int getIs_del() {
            return is_del;
        }

        public void setIs_del(int is_del) {
            this.is_del = is_del;
        }

        public int getType_id() {
            return type_id;
        }

        public void setType_id(int type_id) {
            this.type_id = type_id;
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

        public String getDistrict() {
            return district;
        }

        public void setDistrict(String district) {
            this.district = district;
        }
    }
}
