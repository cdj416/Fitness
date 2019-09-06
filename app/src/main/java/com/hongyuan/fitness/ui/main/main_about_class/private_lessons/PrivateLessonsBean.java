package com.hongyuan.fitness.ui.main.main_about_class.private_lessons;

import com.hongyuan.fitness.base.BaseBean;

import java.io.Serializable;
import java.util.List;

public class PrivateLessonsBean extends BaseBean implements Serializable {


    /**
     * hasmore : false
     * curpage : 1
     * page_total : 1
     * data : {"list":[{"mi_id":1,"m_id":1,"mi_contract_mobile":"12345678988","mi_email":"","mi_wx":"","mi_qq":"","mi_level":1,"mi_birth":1563353726,"pid":0,"cid":0,"did":0,"mi_address":"","mi_gold":0,"mi_head":"","mi_sex":1,"mi_realname":"范德萨","coach_level":3,"coach_sfz":"2345678989","coach_os_id":12,"coach_desc":"个地方撒广东佛山给对方","coach_skill":"股份的时光地方广东佛山","coach_sfz_img1":"http://88php.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190619/a9acf6918a5615896f4230bcdc10600398cacef8_648x454.jpg","coach_sfz_img2":"http://88php.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190619/99827f7c9c726e948cd5dee3cb27bfb36b9d1775_600x338.jpg","coach_head":"http://88php.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190619/5c8eb939feef3892cef99f739ffb6508a12e0b55_800x600.jpg","coach_banner1":"http://88php.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190619/08c358aa2226c98b9f0542465a0330e4670bdb8b_648x454.jpg","coach_banner2":"http://88php.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190619/753c76ae6138dd1898e699e7011763f55c86566a_800x600.jpg","role_id":4,"coach_ft_ids":"7,6,5,4,3,2,1","coach_experience":"fdsafds afds fdsaa fdsa ","cc_ids":"3,2","coach_nickname":"孟morri","saler_os_ids":"","sl_id":0,"mi_point":0,"coach_s":"5.00","coach_review_count":0,"ft_names":"减脂/增肌","min_cp_price":1000,"total_course":2,"last_kong_date":"2019-07-19 19:30"},{"mi_id":7,"m_id":7,"mi_contract_mobile":"18888888888","mi_email":"","mi_wx":"","mi_qq":"","mi_level":1,"mi_birth":1563353389,"pid":35,"cid":3505,"did":350502,"mi_address":"衣裳街88号","mi_gold":0,"mi_head":"http://88php.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190715/d87b91a654a886a35f1ab625400489b5850b5a5f_2208x2208.jpg","mi_sex":0,"mi_realname":"发发发","coach_level":3,"coach_sfz":"","coach_os_id":12,"coach_desc":"范德萨范德萨范德萨范德萨 范德萨范德萨范德萨发 范德萨范德萨","coach_skill":"范德萨范德萨范德萨范德萨范德萨","coach_sfz_img1":"","coach_sfz_img2":"","coach_head":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190717/a455f72302f2f2cd76d7433131bff2ecc9640c6f_800x600.jpg","coach_banner1":"/uploads/coach_banner/20190613/bdd6fbf1538bb176fca57d93a6edebaa.jpg","coach_banner2":"/uploads/coach_banner/20190613/cfa817bbcc8b106b9980309d4c495145.jpg","role_id":2,"coach_ft_ids":"7,6,4,3,2,1","coach_experience":"范德萨范德萨范德萨范德萨范德萨范德萨范德萨","cc_ids":"4,3,1","coach_nickname":"肖","saler_os_ids":"","sl_id":0,"mi_point":0,"coach_s":"4.25","coach_review_count":4,"ft_names":"减脂/增肌","min_cp_price":0.01,"total_course":5,"last_kong_date":"2019-07-19 19:30"}]}
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

        public static class ListBean implements Serializable {
            /**
             * mi_id : 1
             * m_id : 1
             * mi_contract_mobile : 12345678988
             * mi_email :
             * mi_wx :
             * mi_qq :
             * mi_level : 1
             * mi_birth : 1563353726
             * pid : 0
             * cid : 0
             * did : 0
             * mi_address :
             * mi_gold : 0
             * mi_head :
             * mi_sex : 1
             * mi_realname : 范德萨
             * coach_level : 3
             * coach_sfz : 2345678989
             * coach_os_id : 12
             * coach_desc : 个地方撒广东佛山给对方
             * coach_skill : 股份的时光地方广东佛山
             * coach_sfz_img1 : http://88php.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190619/a9acf6918a5615896f4230bcdc10600398cacef8_648x454.jpg
             * coach_sfz_img2 : http://88php.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190619/99827f7c9c726e948cd5dee3cb27bfb36b9d1775_600x338.jpg
             * coach_head : http://88php.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190619/5c8eb939feef3892cef99f739ffb6508a12e0b55_800x600.jpg
             * coach_banner1 : http://88php.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190619/08c358aa2226c98b9f0542465a0330e4670bdb8b_648x454.jpg
             * coach_banner2 : http://88php.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190619/753c76ae6138dd1898e699e7011763f55c86566a_800x600.jpg
             * role_id : 4
             * coach_ft_ids : 7,6,5,4,3,2,1
             * coach_experience : fdsafds afds fdsaa fdsa
             * cc_ids : 3,2
             * coach_nickname : 孟morri
             * saler_os_ids :
             * sl_id : 0
             * mi_point : 0
             * coach_s : 5.00
             * coach_review_count : 0
             * ft_names : 减脂/增肌
             * min_cp_price : 1000
             * total_course : 2
             * last_kong_date : 2019-07-19 19:30
             */

            private int mi_id;
            private int m_id;
            private String mi_contract_mobile;
            private String mi_email;
            private String mi_wx;
            private String mi_qq;
            private int mi_level;
            private int mi_birth;
            private int pid;
            private int cid;
            private int did;
            private String mi_address;
            private int mi_gold;
            private String mi_head;
            private int mi_sex;
            private String mi_realname;
            private int coach_level;
            private String coach_sfz;
            private int coach_os_id;
            private String coach_desc;
            private String coach_skill;
            private String coach_sfz_img1;
            private String coach_sfz_img2;
            private String coach_head;
            private String coach_banner1;
            private String coach_banner2;
            private int role_id;
            private String coach_ft_ids;
            private String coach_experience;
            private String cc_ids;
            private String coach_nickname;
            private String saler_os_ids;
            private int sl_id;
            private int mi_point;
            private String coach_s;
            private int coach_review_count;
            private String ft_names;
            private double min_cp_price;
            private int total_course;
            private String last_kong_date;

            public int getMi_id() {
                return mi_id;
            }

            public void setMi_id(int mi_id) {
                this.mi_id = mi_id;
            }

            public int getM_id() {
                return m_id;
            }

            public void setM_id(int m_id) {
                this.m_id = m_id;
            }

            public String getMi_contract_mobile() {
                return mi_contract_mobile;
            }

            public void setMi_contract_mobile(String mi_contract_mobile) {
                this.mi_contract_mobile = mi_contract_mobile;
            }

            public String getMi_email() {
                return mi_email;
            }

            public void setMi_email(String mi_email) {
                this.mi_email = mi_email;
            }

            public String getMi_wx() {
                return mi_wx;
            }

            public void setMi_wx(String mi_wx) {
                this.mi_wx = mi_wx;
            }

            public String getMi_qq() {
                return mi_qq;
            }

            public void setMi_qq(String mi_qq) {
                this.mi_qq = mi_qq;
            }

            public int getMi_level() {
                return mi_level;
            }

            public void setMi_level(int mi_level) {
                this.mi_level = mi_level;
            }

            public int getMi_birth() {
                return mi_birth;
            }

            public void setMi_birth(int mi_birth) {
                this.mi_birth = mi_birth;
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

            public String getMi_address() {
                return mi_address;
            }

            public void setMi_address(String mi_address) {
                this.mi_address = mi_address;
            }

            public int getMi_gold() {
                return mi_gold;
            }

            public void setMi_gold(int mi_gold) {
                this.mi_gold = mi_gold;
            }

            public String getMi_head() {
                return mi_head;
            }

            public void setMi_head(String mi_head) {
                this.mi_head = mi_head;
            }

            public int getMi_sex() {
                return mi_sex;
            }

            public void setMi_sex(int mi_sex) {
                this.mi_sex = mi_sex;
            }

            public String getMi_realname() {
                return mi_realname;
            }

            public void setMi_realname(String mi_realname) {
                this.mi_realname = mi_realname;
            }

            public int getCoach_level() {
                return coach_level;
            }

            public void setCoach_level(int coach_level) {
                this.coach_level = coach_level;
            }

            public String getCoach_sfz() {
                return coach_sfz;
            }

            public void setCoach_sfz(String coach_sfz) {
                this.coach_sfz = coach_sfz;
            }

            public int getCoach_os_id() {
                return coach_os_id;
            }

            public void setCoach_os_id(int coach_os_id) {
                this.coach_os_id = coach_os_id;
            }

            public String getCoach_desc() {
                return coach_desc;
            }

            public void setCoach_desc(String coach_desc) {
                this.coach_desc = coach_desc;
            }

            public String getCoach_skill() {
                return coach_skill;
            }

            public void setCoach_skill(String coach_skill) {
                this.coach_skill = coach_skill;
            }

            public String getCoach_sfz_img1() {
                return coach_sfz_img1;
            }

            public void setCoach_sfz_img1(String coach_sfz_img1) {
                this.coach_sfz_img1 = coach_sfz_img1;
            }

            public String getCoach_sfz_img2() {
                return coach_sfz_img2;
            }

            public void setCoach_sfz_img2(String coach_sfz_img2) {
                this.coach_sfz_img2 = coach_sfz_img2;
            }

            public String getCoach_head() {
                return coach_head;
            }

            public void setCoach_head(String coach_head) {
                this.coach_head = coach_head;
            }

            public String getCoach_banner1() {
                return coach_banner1;
            }

            public void setCoach_banner1(String coach_banner1) {
                this.coach_banner1 = coach_banner1;
            }

            public String getCoach_banner2() {
                return coach_banner2;
            }

            public void setCoach_banner2(String coach_banner2) {
                this.coach_banner2 = coach_banner2;
            }

            public int getRole_id() {
                return role_id;
            }

            public void setRole_id(int role_id) {
                this.role_id = role_id;
            }

            public String getCoach_ft_ids() {
                return coach_ft_ids;
            }

            public void setCoach_ft_ids(String coach_ft_ids) {
                this.coach_ft_ids = coach_ft_ids;
            }

            public String getCoach_experience() {
                return coach_experience;
            }

            public void setCoach_experience(String coach_experience) {
                this.coach_experience = coach_experience;
            }

            public String getCc_ids() {
                return cc_ids;
            }

            public void setCc_ids(String cc_ids) {
                this.cc_ids = cc_ids;
            }

            public String getCoach_nickname() {
                return coach_nickname;
            }

            public void setCoach_nickname(String coach_nickname) {
                this.coach_nickname = coach_nickname;
            }

            public String getSaler_os_ids() {
                return saler_os_ids;
            }

            public void setSaler_os_ids(String saler_os_ids) {
                this.saler_os_ids = saler_os_ids;
            }

            public int getSl_id() {
                return sl_id;
            }

            public void setSl_id(int sl_id) {
                this.sl_id = sl_id;
            }

            public int getMi_point() {
                return mi_point;
            }

            public void setMi_point(int mi_point) {
                this.mi_point = mi_point;
            }

            public String getCoach_s() {
                return coach_s;
            }

            public void setCoach_s(String coach_s) {
                this.coach_s = coach_s;
            }

            public int getCoach_review_count() {
                return coach_review_count;
            }

            public void setCoach_review_count(int coach_review_count) {
                this.coach_review_count = coach_review_count;
            }

            public String getFt_names() {
                return ft_names;
            }

            public void setFt_names(String ft_names) {
                this.ft_names = ft_names;
            }

            public double getMin_cp_price() {
                return min_cp_price;
            }

            public void setMin_cp_price(double min_cp_price) {
                this.min_cp_price = min_cp_price;
            }

            public int getTotal_course() {
                return total_course;
            }

            public void setTotal_course(int total_course) {
                this.total_course = total_course;
            }

            public String getLast_kong_date() {
                return last_kong_date;
            }

            public void setLast_kong_date(String last_kong_date) {
                this.last_kong_date = last_kong_date;
            }
        }
    }
}
