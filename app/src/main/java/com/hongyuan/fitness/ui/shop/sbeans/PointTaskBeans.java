package com.hongyuan.fitness.ui.shop.sbeans;

import com.hongyuan.fitness.base.BaseBean;

import java.util.List;

public class PointTaskBeans extends BaseBean {

    /**
     * data : {"news_list":[{"pt_code":14,"pl_id":null,"pt_name":"完善资料","pt_img":"http://m.qpic.cn/psc?/V547pe0o4B24UD1qmcuP1BtcMJ1ddrPr/ruAMsa53pVQWN7FLK88i5g5ZjVVQIYx5m7311D31e3MjwisLEE5NY8.PsJIPcLIU7YiAJYNg3*DY*F9GSLsZBnfL6g*R*qTBPh63z13dOUc!/mnull&bo=ugPAAAAAAAADB1s!&rf=photolist&t=5","point_num_up":50,"point_num":50},{"pt_code":29,"pl_id":null,"pt_name":"邀请好友下载APP","pt_img":"http://m.qpic.cn/psc?/V547pe0o4B24UD1qmcuP1BtcMJ1ddrPr/ruAMsa53pVQWN7FLK88i5mMMi7XTPTp2..I3DENok8PUqfv2D9MZJOABsctgItQhLu2AU2Q3SYRN1AY7NhplgpK8i2QmVFqeDeIW.4oLYkA!/mnull&bo=ugPAAAAAAAADB1s!&rf=photolist&t=5","point_num_up":0,"point_num":100},{"pt_code":25,"pl_id":null,"pt_name":"首次添加身体数据","pt_img":"http://m.qpic.cn/psc?/V547pe0o4B24UD1qmcuP1BtcMJ1ddrPr/ruAMsa53pVQWN7FLK88i5mMMi7XTPTp2..I3DENok8O8ZA3OyNvTofRfPJ8qYw7qyTt3EkTU0Vmq98E7xCc94N6hL7mtG0730*YkuAsAaN8!/mnull&bo=ugPAAAAAAAADB1s!&rf=photolist&t=5","point_num_up":50,"point_num":50},{"pt_code":26,"pl_id":null,"pt_name":"绑定智能设备","pt_img":"http://m.qpic.cn/psc?/V547pe0o4B24UD1qmcuP1BtcMJ1ddrPr/ruAMsa53pVQWN7FLK88i5g5ZjVVQIYx5m7311D31e3MjwisLEE5NY8.PsJIPcLIU7YiAJYNg3*DY*F9GSLsZBnfL6g*R*qTBPh63z13dOUc!/mnull&bo=ugPAAAAAAAADB1s!&rf=photolist&t=5","point_num_up":30,"point_num":30}],"day_list":[{"pt_code":1,"pt_name":"首次注册登录","all_num":null,"all_count":null,"point_num_up":100,"point_num":100,"pt_rule":"每日上限100积分","state_all_num":1,"state_num":1,"is_state":1},{"pt_code":7,"pt_name":"每日签到","all_num":null,"all_count":null,"point_num_up":13,"point_num":3,"pt_rule":"每天获得3个积分，满7天额外获得10个积分","state_all_num":4,"state_num":0,"is_state":0},{"pt_code":8,"pt_name":"每日分享到朋友圈","all_num":null,"all_count":null,"point_num_up":10,"point_num":10,"pt_rule":"每日上限10积分","state_all_num":1,"state_num":0,"is_state":0},{"pt_code":2,"pt_name":"发动态","all_num":"5","all_count":"5","point_num_up":20,"point_num":5,"pt_rule":"每日上限20积分","state_all_num":4,"state_num":"5","is_state":0}]}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private List<NewsListBean> news_list;
        private List<DayListBean> day_list;

        public List<NewsListBean> getNews_list() {
            return news_list;
        }

        public void setNews_list(List<NewsListBean> news_list) {
            this.news_list = news_list;
        }

        public List<DayListBean> getDay_list() {
            return day_list;
        }

        public void setDay_list(List<DayListBean> day_list) {
            this.day_list = day_list;
        }

        public static class NewsListBean {
            /**
             * pt_code : 14
             * pl_id : null
             * pt_name : 完善资料
             * pt_img : http://m.qpic.cn/psc?/V547pe0o4B24UD1qmcuP1BtcMJ1ddrPr/ruAMsa53pVQWN7FLK88i5g5ZjVVQIYx5m7311D31e3MjwisLEE5NY8.PsJIPcLIU7YiAJYNg3*DY*F9GSLsZBnfL6g*R*qTBPh63z13dOUc!/mnull&bo=ugPAAAAAAAADB1s!&rf=photolist&t=5
             * point_num_up : 50
             * point_num : 50
             */

            private int pt_code;
            private Object pl_id;
            private String pt_name;
            private String pt_img;
            private int point_num_up;
            private int point_num;

            public int getPt_code() {
                return pt_code;
            }

            public void setPt_code(int pt_code) {
                this.pt_code = pt_code;
            }

            public Object getPl_id() {
                return pl_id;
            }

            public void setPl_id(Object pl_id) {
                this.pl_id = pl_id;
            }

            public String getPt_name() {
                return pt_name;
            }

            public void setPt_name(String pt_name) {
                this.pt_name = pt_name;
            }

            public String getPt_img() {
                return pt_img;
            }

            public void setPt_img(String pt_img) {
                this.pt_img = pt_img;
            }

            public int getPoint_num_up() {
                return point_num_up;
            }

            public void setPoint_num_up(int point_num_up) {
                this.point_num_up = point_num_up;
            }

            public int getPoint_num() {
                return point_num;
            }

            public void setPoint_num(int point_num) {
                this.point_num = point_num;
            }
        }

        public static class DayListBean {
            /**
             * pt_code : 1
             * pt_name : 首次注册登录
             * all_num : null
             * all_count : null
             * point_num_up : 100
             * point_num : 100
             * pt_rule : 每日上限100积分
             * state_all_num : 1
             * state_num : 1
             * is_state : 1
             */

            private int pt_code;
            private String pt_name;
            private Object all_num;
            private Object all_count;
            private int point_num_up;
            private int point_num;
            private String pt_rule;
            private int state_all_num;
            private int state_num;
            private int is_state;

            public int getPt_code() {
                return pt_code;
            }

            public void setPt_code(int pt_code) {
                this.pt_code = pt_code;
            }

            public String getPt_name() {
                return pt_name;
            }

            public void setPt_name(String pt_name) {
                this.pt_name = pt_name;
            }

            public Object getAll_num() {
                return all_num;
            }

            public void setAll_num(Object all_num) {
                this.all_num = all_num;
            }

            public Object getAll_count() {
                return all_count;
            }

            public void setAll_count(Object all_count) {
                this.all_count = all_count;
            }

            public int getPoint_num_up() {
                return point_num_up;
            }

            public void setPoint_num_up(int point_num_up) {
                this.point_num_up = point_num_up;
            }

            public int getPoint_num() {
                return point_num;
            }

            public void setPoint_num(int point_num) {
                this.point_num = point_num;
            }

            public String getPt_rule() {
                return pt_rule;
            }

            public void setPt_rule(String pt_rule) {
                this.pt_rule = pt_rule;
            }

            public int getState_all_num() {
                return state_all_num;
            }

            public void setState_all_num(int state_all_num) {
                this.state_all_num = state_all_num;
            }

            public int getState_num() {
                return state_num;
            }

            public void setState_num(int state_num) {
                this.state_num = state_num;
            }

            public int getIs_state() {
                return is_state;
            }

            public void setIs_state(int is_state) {
                this.is_state = is_state;
            }
        }
    }
}
