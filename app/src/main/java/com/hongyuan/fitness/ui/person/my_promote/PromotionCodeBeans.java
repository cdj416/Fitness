package com.hongyuan.fitness.ui.person.my_promote;

import com.hongyuan.fitness.base.BaseBean;

public class PromotionCodeBeans extends BaseBean {

    /**
     * data : {"info":{"m_name":"小陈","m_mobile":"18183185173","mi_head":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190925/c5455b02fc8634cf663c35bb551424112a119e58_1334x1334.jpg","role_id":2,"coach_os_id":26,"saler_os_ids":"","txt":"首玺健身金色水岸店私教","oss_url":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20191222/88dc4fa985fdbf5ac76bc12e765547e9452e319e.png"}}
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
         * info : {"m_name":"小陈","m_mobile":"18183185173","mi_head":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190925/c5455b02fc8634cf663c35bb551424112a119e58_1334x1334.jpg","role_id":2,"coach_os_id":26,"saler_os_ids":"","txt":"首玺健身金色水岸店私教","oss_url":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20191222/88dc4fa985fdbf5ac76bc12e765547e9452e319e.png"}
         */

        private InfoBean info;

        public InfoBean getInfo() {
            return info;
        }

        public void setInfo(InfoBean info) {
            this.info = info;
        }

        public static class InfoBean {
            /**
             * m_name : 小陈
             * m_mobile : 18183185173
             * mi_head : http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190925/c5455b02fc8634cf663c35bb551424112a119e58_1334x1334.jpg
             * role_id : 2
             * coach_os_id : 26
             * saler_os_ids :
             * txt : 首玺健身金色水岸店私教
             * oss_url : http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20191222/88dc4fa985fdbf5ac76bc12e765547e9452e319e.png
             */

            private String m_name;
            private String m_mobile;
            private String mi_head;
            private int role_id;
            private int coach_os_id;
            private String saler_os_ids;
            private String txt;
            private String oss_url;

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

            public int getRole_id() {
                return role_id;
            }

            public void setRole_id(int role_id) {
                this.role_id = role_id;
            }

            public int getCoach_os_id() {
                return coach_os_id;
            }

            public void setCoach_os_id(int coach_os_id) {
                this.coach_os_id = coach_os_id;
            }

            public String getSaler_os_ids() {
                return saler_os_ids;
            }

            public void setSaler_os_ids(String saler_os_ids) {
                this.saler_os_ids = saler_os_ids;
            }

            public String getTxt() {
                return txt;
            }

            public void setTxt(String txt) {
                this.txt = txt;
            }

            public String getOss_url() {
                return oss_url;
            }

            public void setOss_url(String oss_url) {
                this.oss_url = oss_url;
            }
        }
    }
}
