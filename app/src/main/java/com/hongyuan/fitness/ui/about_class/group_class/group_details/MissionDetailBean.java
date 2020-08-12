package com.hongyuan.fitness.ui.about_class.group_class.group_details;

import com.hongyuan.fitness.base.BaseBean;

import java.io.Serializable;
import java.util.List;

public class MissionDetailBean extends BaseBean implements Serializable{


    /**
     * data : {"os_name":"首玺健身环城西路店","os_tel":"0572-2927111","cs_id":2,"os_id":24,"cs_start_time":1565226000,"cs_state":1,"cs_brief":"范德萨发的","add_time":1565091588,"cs_end_time":1565229600,"cs_name":"游泳团课","si_name":"跑步机","cs_max_num":2,"cs_min_num":1,"cs_img":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190806/02f7911161a2917fb89cf19ddc5cecd253b46407_800x600.jpg","pid":35,"cid":3505,"did":350502,"os_address":"环城西路288-336号金航大厦四楼","cs_time":["2019-08-08 09:00:00","2019-08-08 10:00:00"],"state_name":"已报名","to_state":"4","ocs_id":1,"address":"浙江省湖州市吴兴区环城西路288-336号金航大厦四楼","member_ocs":[{"add_time":1565094269,"m_name":"哈哈","mi_head":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190820/0d47d7ed38f213b679f4c6abfaaf26670b3ae695_1228x1008.png","add_date":"2019-08-06 20:24:29"},{"add_time":1566294713,"m_name":"唧唧","mi_head":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190726/76df48ff0b7b0ab73fdb04090db122554f428334_2208x2208.jpg","add_date":"2019-08-20 17:51:53"}],"sign_up_num":2,"add_date":"2019-08-06 19:39:48","cs_start_date":"2019-08-08 09:00:00","cs_end_date":"2019-08-08 10:00:00"}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean implements Serializable{
        /**
         * os_name : 首玺健身环城西路店
         * os_tel : 0572-2927111
         * cs_id : 2
         * os_id : 24
         * cs_start_time : 1565226000
         * cs_state : 1
         * cs_brief : 范德萨发的
         * add_time : 1565091588
         * cs_end_time : 1565229600
         * cs_name : 游泳团课
         * si_name : 跑步机
         * cs_max_num : 2
         * cs_min_num : 1
         * cs_img : http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190806/02f7911161a2917fb89cf19ddc5cecd253b46407_800x600.jpg
         * pid : 35
         * cid : 3505
         * did : 350502
         * os_address : 环城西路288-336号金航大厦四楼
         * cs_time : ["2019-08-08 09:00:00","2019-08-08 10:00:00"]
         * state_name : 已报名
         * to_state : 4
         * ocs_id : 1
         * address : 浙江省湖州市吴兴区环城西路288-336号金航大厦四楼
         * member_ocs : [{"add_time":1565094269,"m_name":"哈哈","mi_head":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190820/0d47d7ed38f213b679f4c6abfaaf26670b3ae695_1228x1008.png","add_date":"2019-08-06 20:24:29"},{"add_time":1566294713,"m_name":"唧唧","mi_head":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190726/76df48ff0b7b0ab73fdb04090db122554f428334_2208x2208.jpg","add_date":"2019-08-20 17:51:53"}]
         * sign_up_num : 2
         * add_date : 2019-08-06 19:39:48
         * cs_start_date : 2019-08-08 09:00:00
         * cs_end_date : 2019-08-08 10:00:00
         */

        private String os_name;
        private String os_tel;
        private int cs_id;
        private int os_id;
        private long cs_start_time;
        private int cs_state;
        private String cs_brief;
        private long add_time;
        private long cs_end_time;
        private String cs_name;
        private String si_name;
        private int cs_max_num;
        private int cs_min_num;
        private String cs_img;
        private int pid;
        private int cid;
        private int did;
        private String os_address;
        private String state_name;
        private String to_state;
        private int ocs_id;
        private String address;
        private int sign_up_num;
        private String add_date;
        private String cs_start_date;
        private String cs_end_date;
        private String cs_jy;
        private String is_hy;
        private long bm_time;
        private String is_remind;
        private int is_select_num;
        private long now_time;
        private String is_qd;
        private List<String> cs_time;
        private List<MemberOcsBean> member_ocs;

        public String getIs_qd() {
            return is_qd;
        }

        public void setIs_qd(String is_qd) {
            this.is_qd = is_qd;
        }

        public long getNow_time() {
            return now_time;
        }

        public void setNow_time(long now_time) {
            this.now_time = now_time;
        }

        public int getIs_select_num() {
            return is_select_num;
        }

        public void setIs_select_num(int is_select_num) {
            this.is_select_num = is_select_num;
        }

        public String getIs_remind() {
            return is_remind;
        }

        public void setIs_remind(String is_remind) {
            this.is_remind = is_remind;
        }

        public long getBm_time() {
            return bm_time;
        }

        public void setBm_time(long bm_time) {
            this.bm_time = bm_time;
        }

        public String getCs_jy() {
            return cs_jy;
        }

        public void setCs_jy(String cs_jy) {
            this.cs_jy = cs_jy;
        }

        public String getIs_hy() {
            return is_hy;
        }

        public void setIs_hy(String is_hy) {
            this.is_hy = is_hy;
        }

        public String getOs_name() {
            return os_name;
        }

        public void setOs_name(String os_name) {
            this.os_name = os_name;
        }

        public String getOs_tel() {
            return os_tel;
        }

        public void setOs_tel(String os_tel) {
            this.os_tel = os_tel;
        }

        public int getCs_id() {
            return cs_id;
        }

        public void setCs_id(int cs_id) {
            this.cs_id = cs_id;
        }

        public int getOs_id() {
            return os_id;
        }

        public void setOs_id(int os_id) {
            this.os_id = os_id;
        }

        public long getCs_start_time() {
            return cs_start_time;
        }

        public void setCs_start_time(long cs_start_time) {
            this.cs_start_time = cs_start_time;
        }

        public int getCs_state() {
            return cs_state;
        }

        public void setCs_state(int cs_state) {
            this.cs_state = cs_state;
        }

        public String getCs_brief() {
            return cs_brief;
        }

        public void setCs_brief(String cs_brief) {
            this.cs_brief = cs_brief;
        }

        public long getAdd_time() {
            return add_time;
        }

        public void setAdd_time(long add_time) {
            this.add_time = add_time;
        }

        public long getCs_end_time() {
            return cs_end_time;
        }

        public void setCs_end_time(long cs_end_time) {
            this.cs_end_time = cs_end_time;
        }

        public String getCs_name() {
            return cs_name;
        }

        public void setCs_name(String cs_name) {
            this.cs_name = cs_name;
        }

        public String getSi_name() {
            return si_name;
        }

        public void setSi_name(String si_name) {
            this.si_name = si_name;
        }

        public int getCs_max_num() {
            return cs_max_num;
        }

        public void setCs_max_num(int cs_max_num) {
            this.cs_max_num = cs_max_num;
        }

        public int getCs_min_num() {
            return cs_min_num;
        }

        public void setCs_min_num(int cs_min_num) {
            this.cs_min_num = cs_min_num;
        }

        public String getCs_img() {
            return cs_img;
        }

        public void setCs_img(String cs_img) {
            this.cs_img = cs_img;
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

        public String getState_name() {
            return state_name;
        }

        public void setState_name(String state_name) {
            this.state_name = state_name;
        }

        public String getTo_state() {
            return to_state;
        }

        public void setTo_state(String to_state) {
            this.to_state = to_state;
        }

        public int getOcs_id() {
            return ocs_id;
        }

        public void setOcs_id(int ocs_id) {
            this.ocs_id = ocs_id;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public int getSign_up_num() {
            return sign_up_num;
        }

        public void setSign_up_num(int sign_up_num) {
            this.sign_up_num = sign_up_num;
        }

        public String getAdd_date() {
            return add_date;
        }

        public void setAdd_date(String add_date) {
            this.add_date = add_date;
        }

        public String getCs_start_date() {
            return cs_start_date;
        }

        public void setCs_start_date(String cs_start_date) {
            this.cs_start_date = cs_start_date;
        }

        public String getCs_end_date() {
            return cs_end_date;
        }

        public void setCs_end_date(String cs_end_date) {
            this.cs_end_date = cs_end_date;
        }

        public List<String> getCs_time() {
            return cs_time;
        }

        public void setCs_time(List<String> cs_time) {
            this.cs_time = cs_time;
        }

        public List<MemberOcsBean> getMember_ocs() {
            return member_ocs;
        }

        public void setMember_ocs(List<MemberOcsBean> member_ocs) {
            this.member_ocs = member_ocs;
        }

        public static class MemberOcsBean implements Serializable{
            /**
             * add_time : 1565094269
             * m_name : 哈哈
             * mi_head : http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190820/0d47d7ed38f213b679f4c6abfaaf26670b3ae695_1228x1008.png
             * add_date : 2019-08-06 20:24:29
             */

            private int add_time;
            private String m_name;
            private String mi_head;
            private String add_date;

            public int getAdd_time() {
                return add_time;
            }

            public void setAdd_time(int add_time) {
                this.add_time = add_time;
            }

            public String getM_name() {
                return m_name;
            }

            public void setM_name(String m_name) {
                this.m_name = m_name;
            }

            public String getMi_head() {
                return mi_head;
            }

            public void setMi_head(String mi_head) {
                this.mi_head = mi_head;
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
