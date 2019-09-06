package com.hongyuan.fitness.ui.store.more_store;

import com.hongyuan.fitness.base.BaseBean;

import java.io.Serializable;
import java.util.List;

public class StoreBean extends BaseBean implements Serializable{


    /**
     * hasmore : true
     * curpage : 1
     * page_total : 7
     * data : {"list":[{"os_id":17,"os_name":"爱山首玺健身会所","pid":35,"cid":3505,"did":350502,"os_address":"红旗路爱山广场7号楼星火百货B座6层","os_tel":"0572-2556111","os_leader":"范德萨","os_brief":"防守打法递四方速递阿范德萨范德萨","os_img":"http://88php.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190621/eb877e6b3ffee5eb1078633fb6e1f3434fc5cb52_600x338.jpg","os_lng":"120.10768","os_lat":"30.87206","os_addtime":1561119010,"admin_id":1,"update_time":0,"update_admin":0,"os_logo":"http://88php.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190621/1569ea775d80ebde3e4f2255db056e2e80875884_800x600.jpg","os_banner1":"http://88php.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190621/a3d2d6d6cc824b38481291dab6cecbdeb995659b_648x454.jpg","os_banner2":"","os_banner3":"http://88php.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190621/ebb260a59b667f763610415846d53faa095f1fbb_648x454.jpg","os_banner4":"http://88php.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190621/3e88b35788640b9808f6af541e6d1b433de5c107_800x600.jpg","os_banner5":"http://88php.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190621/44794df26868748542f77c78079ae5514471dd21_600x338.jpg","os_teshe":" 范德萨范德萨范德萨范德萨范德萨","os_start_time":"09:00:00","os_end_time":"22:00:00","os_week":"1,2,3,4,5,6,7","osf_ids":"4,3,2","os_area":1233,"os_area_privite":333,"osl_id":1,"distance_um":1243,"address":"浙江省湖州市吴兴区红旗路爱山广场7号楼星火百货B座6层","juli":"1.2km","osl_name":"普通店","osl_img":"https://88php.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190703/oslevel3.png"}]}
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

    public static class DataBean implements Serializable {
        private List<ListBean> list;

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean implements Serializable {
            /**
             * os_id : 17
             * os_name : 爱山首玺健身会所
             * pid : 35
             * cid : 3505
             * did : 350502
             * os_address : 红旗路爱山广场7号楼星火百货B座6层
             * os_tel : 0572-2556111
             * os_leader : 范德萨
             * os_brief : 防守打法递四方速递阿范德萨范德萨
             * os_img : http://88php.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190621/eb877e6b3ffee5eb1078633fb6e1f3434fc5cb52_600x338.jpg
             * os_lng : 120.10768
             * os_lat : 30.87206
             * os_addtime : 1561119010
             * admin_id : 1
             * update_time : 0
             * update_admin : 0
             * os_logo : http://88php.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190621/1569ea775d80ebde3e4f2255db056e2e80875884_800x600.jpg
             * os_banner1 : http://88php.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190621/a3d2d6d6cc824b38481291dab6cecbdeb995659b_648x454.jpg
             * os_banner2 :
             * os_banner3 : http://88php.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190621/ebb260a59b667f763610415846d53faa095f1fbb_648x454.jpg
             * os_banner4 : http://88php.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190621/3e88b35788640b9808f6af541e6d1b433de5c107_800x600.jpg
             * os_banner5 : http://88php.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190621/44794df26868748542f77c78079ae5514471dd21_600x338.jpg
             * os_teshe :  范德萨范德萨范德萨范德萨范德萨
             * os_start_time : 09:00:00
             * os_end_time : 22:00:00
             * os_week : 1,2,3,4,5,6,7
             * osf_ids : 4,3,2
             * os_area : 1233
             * os_area_privite : 333
             * osl_id : 1
             * distance_um : 1243
             * address : 浙江省湖州市吴兴区红旗路爱山广场7号楼星火百货B座6层
             * juli : 1.2km
             * osl_name : 普通店
             * osl_img : https://88php.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190703/oslevel3.png
             */

            private int os_id;
            private String os_name;
            private int pid;
            private int cid;
            private int did;
            private String os_address;
            private String os_tel;
            private String os_leader;
            private String os_brief;
            private String os_img;
            private String os_lng;
            private String os_lat;
            private int os_addtime;
            private int admin_id;
            private int update_time;
            private int update_admin;
            private String os_logo;
            private String os_banner1;
            private String os_banner2;
            private String os_banner3;
            private String os_banner4;
            private String os_banner5;
            private String os_teshe;
            private String os_start_time;
            private String os_end_time;
            private String os_week;
            private String osf_ids;
            private int os_area;
            private int os_area_privite;
            private int osl_id;
            private int distance_um;
            private String address;
            private String juli;
            private String osl_name;
            private String osl_img;

            public int getOs_id() {
                return os_id;
            }

            public void setOs_id(int os_id) {
                this.os_id = os_id;
            }

            public String getOs_name() {
                return os_name;
            }

            public void setOs_name(String os_name) {
                this.os_name = os_name;
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

            public String getOs_address() {
                return os_address;
            }

            public void setOs_address(String os_address) {
                this.os_address = os_address;
            }

            public String getOs_tel() {
                return os_tel;
            }

            public void setOs_tel(String os_tel) {
                this.os_tel = os_tel;
            }

            public String getOs_leader() {
                return os_leader;
            }

            public void setOs_leader(String os_leader) {
                this.os_leader = os_leader;
            }

            public String getOs_brief() {
                return os_brief;
            }

            public void setOs_brief(String os_brief) {
                this.os_brief = os_brief;
            }

            public String getOs_img() {
                return os_img;
            }

            public void setOs_img(String os_img) {
                this.os_img = os_img;
            }

            public String getOs_lng() {
                return os_lng;
            }

            public void setOs_lng(String os_lng) {
                this.os_lng = os_lng;
            }

            public String getOs_lat() {
                return os_lat;
            }

            public void setOs_lat(String os_lat) {
                this.os_lat = os_lat;
            }

            public int getOs_addtime() {
                return os_addtime;
            }

            public void setOs_addtime(int os_addtime) {
                this.os_addtime = os_addtime;
            }

            public int getAdmin_id() {
                return admin_id;
            }

            public void setAdmin_id(int admin_id) {
                this.admin_id = admin_id;
            }

            public int getUpdate_time() {
                return update_time;
            }

            public void setUpdate_time(int update_time) {
                this.update_time = update_time;
            }

            public int getUpdate_admin() {
                return update_admin;
            }

            public void setUpdate_admin(int update_admin) {
                this.update_admin = update_admin;
            }

            public String getOs_logo() {
                return os_logo;
            }

            public void setOs_logo(String os_logo) {
                this.os_logo = os_logo;
            }

            public String getOs_banner1() {
                return os_banner1;
            }

            public void setOs_banner1(String os_banner1) {
                this.os_banner1 = os_banner1;
            }

            public String getOs_banner2() {
                return os_banner2;
            }

            public void setOs_banner2(String os_banner2) {
                this.os_banner2 = os_banner2;
            }

            public String getOs_banner3() {
                return os_banner3;
            }

            public void setOs_banner3(String os_banner3) {
                this.os_banner3 = os_banner3;
            }

            public String getOs_banner4() {
                return os_banner4;
            }

            public void setOs_banner4(String os_banner4) {
                this.os_banner4 = os_banner4;
            }

            public String getOs_banner5() {
                return os_banner5;
            }

            public void setOs_banner5(String os_banner5) {
                this.os_banner5 = os_banner5;
            }

            public String getOs_teshe() {
                return os_teshe;
            }

            public void setOs_teshe(String os_teshe) {
                this.os_teshe = os_teshe;
            }

            public String getOs_start_time() {
                return os_start_time;
            }

            public void setOs_start_time(String os_start_time) {
                this.os_start_time = os_start_time;
            }

            public String getOs_end_time() {
                return os_end_time;
            }

            public void setOs_end_time(String os_end_time) {
                this.os_end_time = os_end_time;
            }

            public String getOs_week() {
                return os_week;
            }

            public void setOs_week(String os_week) {
                this.os_week = os_week;
            }

            public String getOsf_ids() {
                return osf_ids;
            }

            public void setOsf_ids(String osf_ids) {
                this.osf_ids = osf_ids;
            }

            public int getOs_area() {
                return os_area;
            }

            public void setOs_area(int os_area) {
                this.os_area = os_area;
            }

            public int getOs_area_privite() {
                return os_area_privite;
            }

            public void setOs_area_privite(int os_area_privite) {
                this.os_area_privite = os_area_privite;
            }

            public int getOsl_id() {
                return osl_id;
            }

            public void setOsl_id(int osl_id) {
                this.osl_id = osl_id;
            }

            public int getDistance_um() {
                return distance_um;
            }

            public void setDistance_um(int distance_um) {
                this.distance_um = distance_um;
            }

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
            }

            public String getJuli() {
                return juli;
            }

            public void setJuli(String juli) {
                this.juli = juli;
            }

            public String getOsl_name() {
                return osl_name;
            }

            public void setOsl_name(String osl_name) {
                this.osl_name = osl_name;
            }

            public String getOsl_img() {
                return osl_img;
            }

            public void setOsl_img(String osl_img) {
                this.osl_img = osl_img;
            }
        }
    }
}
