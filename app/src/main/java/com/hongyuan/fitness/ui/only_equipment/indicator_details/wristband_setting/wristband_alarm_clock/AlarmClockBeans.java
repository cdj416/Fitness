package com.hongyuan.fitness.ui.only_equipment.indicator_details.wristband_setting.wristband_alarm_clock;

import com.hongyuan.fitness.base.BaseBean;

import java.io.Serializable;
import java.util.List;

public class AlarmClockBeans extends BaseBean implements Serializable{

    /**
     * data : {"list":[{"id":11,"alarmId":1,"openFlag":0,"hour":13,"minture":30,"mon":1,"tues":1,"wed":1,"thur":1,"fri":1,"sat":1,"sun":1,"alarmName":"测试","m_id":13}]}
     */

    private DataBean data;

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
             * id : 11
             * alarmId : 1
             * openFlag : 0
             * hour : 13
             * minture : 30
             * mon : 1
             * tues : 1
             * wed : 1
             * thur : 1
             * fri : 1
             * sat : 1
             * sun : 1
             * alarmName : 测试
             * m_id : 13
             */

            private int id;
            private int alarmId;
            private int openFlag;
            private int hour;
            private int minture;
            private int mon;
            private int tues;
            private int wed;
            private int thur;
            private int fri;
            private int sat;
            private int sun;
            private String alarmName;
            private int m_id;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getAlarmId() {
                return alarmId;
            }

            public void setAlarmId(int alarmId) {
                this.alarmId = alarmId;
            }

            public int getOpenFlag() {
                return openFlag;
            }

            public void setOpenFlag(int openFlag) {
                this.openFlag = openFlag;
            }

            public int getHour() {
                return hour;
            }

            public void setHour(int hour) {
                this.hour = hour;
            }

            public int getMinture() {
                return minture;
            }

            public void setMinture(int minture) {
                this.minture = minture;
            }

            public int getMon() {
                return mon;
            }

            public void setMon(int mon) {
                this.mon = mon;
            }

            public int getTues() {
                return tues;
            }

            public void setTues(int tues) {
                this.tues = tues;
            }

            public int getWed() {
                return wed;
            }

            public void setWed(int wed) {
                this.wed = wed;
            }

            public int getThur() {
                return thur;
            }

            public void setThur(int thur) {
                this.thur = thur;
            }

            public int getFri() {
                return fri;
            }

            public void setFri(int fri) {
                this.fri = fri;
            }

            public int getSat() {
                return sat;
            }

            public void setSat(int sat) {
                this.sat = sat;
            }

            public int getSun() {
                return sun;
            }

            public void setSun(int sun) {
                this.sun = sun;
            }

            public String getAlarmName() {
                return alarmName;
            }

            public void setAlarmName(String alarmName) {
                this.alarmName = alarmName;
            }

            public int getM_id() {
                return m_id;
            }

            public void setM_id(int m_id) {
                this.m_id = m_id;
            }
        }
    }
}
