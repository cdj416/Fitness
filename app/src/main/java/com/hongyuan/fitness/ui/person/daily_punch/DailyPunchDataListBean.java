package com.hongyuan.fitness.ui.person.daily_punch;

import com.hongyuan.fitness.base.BaseBean;

import java.util.List;

public class DailyPunchDataListBean extends BaseBean {

    /**
     * data : {"list":[{"now_time":1561910400,"now_date":"2019-07-01","week":"1","is_cur_date":0,"is_qd":0},{"now_time":1561996800,"now_date":"2019-07-02","week":"2","is_cur_date":0,"is_qd":0},{"now_time":1562083200,"now_date":"2019-07-03","week":"3","is_cur_date":0,"is_qd":0},{"now_time":1562169600,"now_date":"2019-07-04","week":"4","is_cur_date":0,"is_qd":0},{"now_time":1562256000,"now_date":"2019-07-05","week":"5","is_cur_date":0,"is_qd":0},{"now_time":1562342400,"now_date":"2019-07-06","week":"6","is_cur_date":0,"is_qd":0},{"now_time":1562428800,"now_date":"2019-07-07","week":"0","is_cur_date":0,"is_qd":0},{"now_time":1562515200,"now_date":"2019-07-08","week":"1","is_cur_date":0,"is_qd":0},{"now_time":1562601600,"now_date":"2019-07-09","week":"2","is_cur_date":0,"is_qd":0},{"now_time":1562688000,"now_date":"2019-07-10","week":"3","is_cur_date":0,"is_qd":0},{"now_time":1562774400,"now_date":"2019-07-11","week":"4","is_cur_date":0,"is_qd":0},{"now_time":1562860800,"now_date":"2019-07-12","week":"5","is_cur_date":0,"is_qd":0},{"now_time":1562947200,"now_date":"2019-07-13","week":"6","is_cur_date":0,"is_qd":0},{"now_time":1563033600,"now_date":"2019-07-14","week":"0","is_cur_date":0,"is_qd":0},{"now_time":1563120000,"now_date":"2019-07-15","week":"1","is_cur_date":0,"is_qd":0},{"now_time":1563206400,"now_date":"2019-07-16","week":"2","is_cur_date":0,"is_qd":0},{"now_time":1563292800,"now_date":"2019-07-17","week":"3","is_cur_date":0,"is_qd":0},{"now_time":1563379200,"now_date":"2019-07-18","week":"4","is_cur_date":0,"is_qd":0},{"now_time":1563465600,"now_date":"2019-07-19","week":"5","is_cur_date":0,"is_qd":0},{"now_time":1563552000,"now_date":"2019-07-20","week":"6","is_cur_date":0,"is_qd":0},{"now_time":1563638400,"now_date":"2019-07-21","week":"0","is_cur_date":0,"is_qd":0},{"now_time":1563724800,"now_date":"2019-07-22","week":"1","is_cur_date":0,"is_qd":0},{"now_time":1563811200,"now_date":"2019-07-23","week":"2","is_cur_date":0,"is_qd":0},{"now_time":1563897600,"now_date":"2019-07-24","week":"3","is_cur_date":0,"is_qd":0},{"now_time":1563984000,"now_date":"2019-07-25","week":"4","is_cur_date":0,"is_qd":0},{"now_time":1564070400,"now_date":"2019-07-26","week":"5","is_cur_date":0,"is_qd":0},{"now_time":1564156800,"now_date":"2019-07-27","week":"6","is_cur_date":0,"is_qd":0},{"now_time":1564243200,"now_date":"2019-07-28","week":"0","is_cur_date":0,"is_qd":0},{"now_time":1564329600,"now_date":"2019-07-29","week":"1","is_cur_date":1,"is_qd":1},{"now_time":1564416000,"now_date":"2019-07-30","week":"2","is_cur_date":0,"is_qd":0},{"now_time":1564502400,"now_date":"2019-07-31","week":"3","is_cur_date":0,"is_qd":0}]}
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
             * now_time : 1561910400
             * now_date : 2019-07-01
             * week : 1
             * is_cur_date : 0
             * is_qd : 0
             */

            private int now_time;
            private String now_date;
            private String week;
            private int is_cur_date;
            private int is_qd;

            public int getNow_time() {
                return now_time;
            }

            public void setNow_time(int now_time) {
                this.now_time = now_time;
            }

            public String getNow_date() {
                return now_date;
            }

            public void setNow_date(String now_date) {
                this.now_date = now_date;
            }

            public String getWeek() {
                return week;
            }

            public void setWeek(String week) {
                this.week = week;
            }

            public int getIs_cur_date() {
                return is_cur_date;
            }

            public void setIs_cur_date(int is_cur_date) {
                this.is_cur_date = is_cur_date;
            }

            public int getIs_qd() {
                return is_qd;
            }

            public void setIs_qd(int is_qd) {
                this.is_qd = is_qd;
            }
        }
    }
}
