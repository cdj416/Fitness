package com.hongyuan.fitness.ui.store;

import com.hongyuan.fitness.base.BaseBean;

import java.util.List;

public class StoreDetailBean extends BaseBean {


    /**
     * data : {"os_id":21,"os_name":"湖州首玺健身爱山店","pid":35,"cid":3505,"did":350502,"os_address":"星火百货B座六楼","os_tel":"0572-2556111","os_leader":"王彩环","os_brief":"位于湖州最市中心、人气最足、楼下50米衣裳街（湖城最有名的美食街）。集健身、瑜伽、舞蹈、动感单车、洗浴等为一体的健身俱乐部。","os_img":"","os_lng":"120.107542","os_lat":"30.87196","os_addtime":1564128142,"admin_id":10,"update_time":0,"update_admin":0,"os_logo":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190726/86c57e8ced1df6b98af3a50d7bbe33a0951e23b3_1362x908.jpg","os_banner1":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190726/2597c083b9fb35b635d204404e3bae72fa829379_1440x1080.jpg","os_banner2":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190726/f57f94021ce5196bb68663d8bec3e978409a5a2f_1440x1080.jpg","os_banner3":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190726/cd891ddccaacb6fbec1910d6560e4a59bbdb8d86_2880x2160.jpg","os_banner4":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190726/9db96eddee4366346d47a69b3139546bd522a66e_1440x1080.jpg","os_banner5":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190726/db16e6d4d143fe98decb9ea6b1f165ea86c91c13_1440x1080.jpg","os_teshe":"专业的教练团队是你们的技术保障，优秀的会籍顾问让您健身没有后顾之忧，漂亮专业的前台小姐姐让您进门就从满好心情。","os_start_time":"10:00:00","os_end_time":"22:00:00","os_week":"1,2,3,4,5,6,7","osf_ids":"4,3,2,1","os_area":2500,"os_area_privite":300,"is_del":2,"osl_id":1,"address":"浙江省湖州市吴兴区星火百货B座六楼","banner":["http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190726/2597c083b9fb35b635d204404e3bae72fa829379_1440x1080.jpg","http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190726/f57f94021ce5196bb68663d8bec3e978409a5a2f_1440x1080.jpg","http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190726/cd891ddccaacb6fbec1910d6560e4a59bbdb8d86_2880x2160.jpg","http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190726/9db96eddee4366346d47a69b3139546bd522a66e_1440x1080.jpg","http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190726/db16e6d4d143fe98decb9ea6b1f165ea86c91c13_1440x1080.jpg"],"osf":[{"osf_id":1,"osf_name":"有氧器械区"},{"osf_id":2,"osf_name":"动感单车房"},{"osf_id":3,"osf_name":"多功能器械区"},{"osf_id":4,"osf_name":"高温瑜伽馆"}],"osl_name":"普通店","osl_img":""}
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
         * os_id : 21
         * os_name : 湖州首玺健身爱山店
         * pid : 35
         * cid : 3505
         * did : 350502
         * os_address : 星火百货B座六楼
         * os_tel : 0572-2556111
         * os_leader : 王彩环
         * os_brief : 位于湖州最市中心、人气最足、楼下50米衣裳街（湖城最有名的美食街）。集健身、瑜伽、舞蹈、动感单车、洗浴等为一体的健身俱乐部。
         * os_img :
         * os_lng : 120.107542
         * os_lat : 30.87196
         * os_addtime : 1564128142
         * admin_id : 10
         * update_time : 0
         * update_admin : 0
         * os_logo : http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190726/86c57e8ced1df6b98af3a50d7bbe33a0951e23b3_1362x908.jpg
         * os_banner1 : http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190726/2597c083b9fb35b635d204404e3bae72fa829379_1440x1080.jpg
         * os_banner2 : http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190726/f57f94021ce5196bb68663d8bec3e978409a5a2f_1440x1080.jpg
         * os_banner3 : http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190726/cd891ddccaacb6fbec1910d6560e4a59bbdb8d86_2880x2160.jpg
         * os_banner4 : http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190726/9db96eddee4366346d47a69b3139546bd522a66e_1440x1080.jpg
         * os_banner5 : http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190726/db16e6d4d143fe98decb9ea6b1f165ea86c91c13_1440x1080.jpg
         * os_teshe : 专业的教练团队是你们的技术保障，优秀的会籍顾问让您健身没有后顾之忧，漂亮专业的前台小姐姐让您进门就从满好心情。
         * os_start_time : 10:00:00
         * os_end_time : 22:00:00
         * os_week : 1,2,3,4,5,6,7
         * osf_ids : 4,3,2,1
         * os_area : 2500
         * os_area_privite : 300
         * is_del : 2
         * osl_id : 1
         * address : 浙江省湖州市吴兴区星火百货B座六楼
         * banner : ["http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190726/2597c083b9fb35b635d204404e3bae72fa829379_1440x1080.jpg","http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190726/f57f94021ce5196bb68663d8bec3e978409a5a2f_1440x1080.jpg","http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190726/cd891ddccaacb6fbec1910d6560e4a59bbdb8d86_2880x2160.jpg","http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190726/9db96eddee4366346d47a69b3139546bd522a66e_1440x1080.jpg","http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190726/db16e6d4d143fe98decb9ea6b1f165ea86c91c13_1440x1080.jpg"]
         * osf : [{"osf_id":1,"osf_name":"有氧器械区"},{"osf_id":2,"osf_name":"动感单车房"},{"osf_id":3,"osf_name":"多功能器械区"},{"osf_id":4,"osf_name":"高温瑜伽馆"}]
         * osl_name : 普通店
         * osl_img :
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
        private int is_del;
        private int osl_id;
        private String address;
        private String osl_name;
        private String osl_img;
        private List<String> banner;
        private List<OsfBean> osf;

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

        public int getIs_del() {
            return is_del;
        }

        public void setIs_del(int is_del) {
            this.is_del = is_del;
        }

        public int getOsl_id() {
            return osl_id;
        }

        public void setOsl_id(int osl_id) {
            this.osl_id = osl_id;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
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

        public List<String> getBanner() {
            return banner;
        }

        public void setBanner(List<String> banner) {
            this.banner = banner;
        }

        public List<OsfBean> getOsf() {
            return osf;
        }

        public void setOsf(List<OsfBean> osf) {
            this.osf = osf;
        }

        public static class OsfBean {
            /**
             * osf_id : 1
             * osf_name : 有氧器械区
             */

            private int osf_id;
            private String osf_name;

            public int getOsf_id() {
                return osf_id;
            }

            public void setOsf_id(int osf_id) {
                this.osf_id = osf_id;
            }

            public String getOsf_name() {
                return osf_name;
            }

            public void setOsf_name(String osf_name) {
                this.osf_name = osf_name;
            }
        }
    }
}
