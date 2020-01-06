package com.hongyuan.fitness.ui.only_equipment.indicator_details.wristband_setting;

import com.hongyuan.fitness.base.BaseBean;

public class WristbandSettingBeans extends BaseBean {

    /**
     * data : {"info":{"id":2,"m_id":13,"call_notice":0,"call_delay_notice":0,"site_long_notice":0,"site_long_duration":0,"site_long_starttime":"","site_long_endtime":"","noon_break":0,"msg_notice":0,"wx_notice":0,"qq_notice":0,"sport_target":0,"bright_screen":0,"heart_rate":0,"heart_rate_area":0,"beart_rate_max":0,"heart_rate_nosport_s":0,"heart_rate_nosport_e":0,"heart_rate_warmup_s":0,"heart_rate_warmup_e":0,"heart_rate_fatborning_s":0,"heart_rate_fatborning_e":0,"heart_rate_ae_s":0,"heart_rate_ae_e":0,"heart_rate_ne_s":0,"heart_rate_ne_e":0,"limit_s":0,"limit_e":0,"alarm_notice":0}}
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
         * info : {"id":2,"m_id":13,"call_notice":0,"call_delay_notice":0,"site_long_notice":0,"site_long_duration":0,"site_long_starttime":"","site_long_endtime":"","noon_break":0,"msg_notice":0,"wx_notice":0,"qq_notice":0,"sport_target":0,"bright_screen":0,"heart_rate":0,"heart_rate_area":0,"beart_rate_max":0,"heart_rate_nosport_s":0,"heart_rate_nosport_e":0,"heart_rate_warmup_s":0,"heart_rate_warmup_e":0,"heart_rate_fatborning_s":0,"heart_rate_fatborning_e":0,"heart_rate_ae_s":0,"heart_rate_ae_e":0,"heart_rate_ne_s":0,"heart_rate_ne_e":0,"limit_s":0,"limit_e":0,"alarm_notice":0}
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
             * id : 2
             * m_id : 13
             * call_notice : 0
             * call_delay_notice : 0
             * site_long_notice : 0
             * site_long_duration : 0
             * site_long_starttime :
             * site_long_endtime :
             * noon_break : 0
             * msg_notice : 0
             * wx_notice : 0
             * qq_notice : 0
             * sport_target : 0
             * bright_screen : 0
             * heart_rate : 0
             * heart_rate_area : 0
             * beart_rate_max : 0
             * heart_rate_nosport_s : 0
             * heart_rate_nosport_e : 0
             * heart_rate_warmup_s : 0
             * heart_rate_warmup_e : 0
             * heart_rate_fatborning_s : 0
             * heart_rate_fatborning_e : 0
             * heart_rate_ae_s : 0
             * heart_rate_ae_e : 0
             * heart_rate_ne_s : 0
             * heart_rate_ne_e : 0
             * limit_s : 0
             * limit_e : 0
             * alarm_notice : 0
             */

            private int id;
            private int m_id;
            private int call_notice;
            private int call_delay_notice;
            private int site_long_notice;
            private int site_long_duration;
            private String site_long_starttime;
            private String site_long_endtime;
            private int noon_break;
            private int msg_notice;
            private int wx_notice;
            private int qq_notice;
            private int sport_target;
            private int bright_screen;
            private int heart_rate;
            private int heart_rate_area;
            private int beart_rate_max;
            private int heart_rate_nosport_s;
            private int heart_rate_nosport_e;
            private int heart_rate_warmup_s;
            private int heart_rate_warmup_e;
            private int heart_rate_fatborning_s;
            private int heart_rate_fatborning_e;
            private int heart_rate_ae_s;
            private int heart_rate_ae_e;
            private int heart_rate_ne_s;
            private int heart_rate_ne_e;
            private int limit_s;
            private int limit_e;
            private int alarm_notice;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getM_id() {
                return m_id;
            }

            public void setM_id(int m_id) {
                this.m_id = m_id;
            }

            public int getCall_notice() {
                return call_notice;
            }

            public void setCall_notice(int call_notice) {
                this.call_notice = call_notice;
            }

            public int getCall_delay_notice() {
                return call_delay_notice;
            }

            public void setCall_delay_notice(int call_delay_notice) {
                this.call_delay_notice = call_delay_notice;
            }

            public int getSite_long_notice() {
                return site_long_notice;
            }

            public void setSite_long_notice(int site_long_notice) {
                this.site_long_notice = site_long_notice;
            }

            public int getSite_long_duration() {
                return site_long_duration;
            }

            public void setSite_long_duration(int site_long_duration) {
                this.site_long_duration = site_long_duration;
            }

            public String getSite_long_starttime() {
                return site_long_starttime;
            }

            public void setSite_long_starttime(String site_long_starttime) {
                this.site_long_starttime = site_long_starttime;
            }

            public String getSite_long_endtime() {
                return site_long_endtime;
            }

            public void setSite_long_endtime(String site_long_endtime) {
                this.site_long_endtime = site_long_endtime;
            }

            public int getNoon_break() {
                return noon_break;
            }

            public void setNoon_break(int noon_break) {
                this.noon_break = noon_break;
            }

            public int getMsg_notice() {
                return msg_notice;
            }

            public void setMsg_notice(int msg_notice) {
                this.msg_notice = msg_notice;
            }

            public int getWx_notice() {
                return wx_notice;
            }

            public void setWx_notice(int wx_notice) {
                this.wx_notice = wx_notice;
            }

            public int getQq_notice() {
                return qq_notice;
            }

            public void setQq_notice(int qq_notice) {
                this.qq_notice = qq_notice;
            }

            public int getSport_target() {
                return sport_target;
            }

            public void setSport_target(int sport_target) {
                this.sport_target = sport_target;
            }

            public int getBright_screen() {
                return bright_screen;
            }

            public void setBright_screen(int bright_screen) {
                this.bright_screen = bright_screen;
            }

            public int getHeart_rate() {
                return heart_rate;
            }

            public void setHeart_rate(int heart_rate) {
                this.heart_rate = heart_rate;
            }

            public int getHeart_rate_area() {
                return heart_rate_area;
            }

            public void setHeart_rate_area(int heart_rate_area) {
                this.heart_rate_area = heart_rate_area;
            }

            public int getBeart_rate_max() {
                return beart_rate_max;
            }

            public void setBeart_rate_max(int beart_rate_max) {
                this.beart_rate_max = beart_rate_max;
            }

            public int getHeart_rate_nosport_s() {
                return heart_rate_nosport_s;
            }

            public void setHeart_rate_nosport_s(int heart_rate_nosport_s) {
                this.heart_rate_nosport_s = heart_rate_nosport_s;
            }

            public int getHeart_rate_nosport_e() {
                return heart_rate_nosport_e;
            }

            public void setHeart_rate_nosport_e(int heart_rate_nosport_e) {
                this.heart_rate_nosport_e = heart_rate_nosport_e;
            }

            public int getHeart_rate_warmup_s() {
                return heart_rate_warmup_s;
            }

            public void setHeart_rate_warmup_s(int heart_rate_warmup_s) {
                this.heart_rate_warmup_s = heart_rate_warmup_s;
            }

            public int getHeart_rate_warmup_e() {
                return heart_rate_warmup_e;
            }

            public void setHeart_rate_warmup_e(int heart_rate_warmup_e) {
                this.heart_rate_warmup_e = heart_rate_warmup_e;
            }

            public int getHeart_rate_fatborning_s() {
                return heart_rate_fatborning_s;
            }

            public void setHeart_rate_fatborning_s(int heart_rate_fatborning_s) {
                this.heart_rate_fatborning_s = heart_rate_fatborning_s;
            }

            public int getHeart_rate_fatborning_e() {
                return heart_rate_fatborning_e;
            }

            public void setHeart_rate_fatborning_e(int heart_rate_fatborning_e) {
                this.heart_rate_fatborning_e = heart_rate_fatborning_e;
            }

            public int getHeart_rate_ae_s() {
                return heart_rate_ae_s;
            }

            public void setHeart_rate_ae_s(int heart_rate_ae_s) {
                this.heart_rate_ae_s = heart_rate_ae_s;
            }

            public int getHeart_rate_ae_e() {
                return heart_rate_ae_e;
            }

            public void setHeart_rate_ae_e(int heart_rate_ae_e) {
                this.heart_rate_ae_e = heart_rate_ae_e;
            }

            public int getHeart_rate_ne_s() {
                return heart_rate_ne_s;
            }

            public void setHeart_rate_ne_s(int heart_rate_ne_s) {
                this.heart_rate_ne_s = heart_rate_ne_s;
            }

            public int getHeart_rate_ne_e() {
                return heart_rate_ne_e;
            }

            public void setHeart_rate_ne_e(int heart_rate_ne_e) {
                this.heart_rate_ne_e = heart_rate_ne_e;
            }

            public int getLimit_s() {
                return limit_s;
            }

            public void setLimit_s(int limit_s) {
                this.limit_s = limit_s;
            }

            public int getLimit_e() {
                return limit_e;
            }

            public void setLimit_e(int limit_e) {
                this.limit_e = limit_e;
            }

            public int getAlarm_notice() {
                return alarm_notice;
            }

            public void setAlarm_notice(int alarm_notice) {
                this.alarm_notice = alarm_notice;
            }
        }
    }
}
