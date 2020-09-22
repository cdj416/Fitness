package com.hongyuan.fitness.ui.shop.sbeans;

import com.hongyuan.fitness.base.BaseBean;

import java.util.List;

public class MyTaskBeans extends BaseBean {

    /**
     * data : {"list":[{"pt_code":1,"pt_name":"首次注册登录","all_num":null,"all_count":0,"point_num_up":100,"point_num":100,"pt_rule":"每日上限100积分","state_all_num":1,"state_num":1,"is_state":1},{"pt_code":7,"pt_name":"每日签到","all_num":null,"all_count":0,"point_num_up":13,"point_num":3,"pt_rule":"每天获得3个积分，满7天额外获得10个积分","state_all_num":4,"state_num":0,"is_state":0},{"pt_code":8,"pt_name":"每日分享到朋友圈","all_num":null,"all_count":0,"point_num_up":10,"point_num":10,"pt_rule":"每日上限10积分","state_all_num":1,"state_num":0,"is_state":0},{"pt_code":2,"pt_name":"发动态","all_num":null,"all_count":0,"point_num_up":20,"point_num":5,"pt_rule":"每日上限20积分","state_all_num":4,"state_num":0,"is_state":0},{"pt_code":3,"pt_name":"点赞别人","all_num":null,"all_count":0,"point_num_up":10,"point_num":1,"pt_rule":"每日上限10积分","state_all_num":10,"state_num":0,"is_state":0},{"pt_code":4,"pt_name":"发表评论","all_num":null,"all_count":0,"point_num_up":10,"point_num":2,"pt_rule":"每日上限10积分","state_all_num":5,"state_num":0,"is_state":0},{"pt_code":6,"pt_name":"关注别人","all_num":null,"all_count":0,"point_num_up":10,"point_num":2,"pt_rule":"每日上限10积分","state_all_num":5,"state_num":0,"is_state":0},{"pt_code":5,"pt_name":"户外步行/跑步","all_num":null,"all_count":0,"point_num_up":10,"point_num":10,"pt_rule":"每日上限10积分","state_all_num":1,"state_num":0,"is_state":0},{"pt_code":17,"pt_name":"线上购买健身卡","all_num":null,"all_count":0,"point_num_up":0,"point_num":100,"pt_rule":"无上限","state_all_num":0,"state_num":0,"is_state":0},{"pt_code":16,"pt_name":"线上预约课程","all_num":null,"all_count":0,"point_num_up":40,"point_num":20,"pt_rule":"每日上限40积分","state_all_num":2,"state_num":0,"is_state":0},{"pt_code":22,"pt_name":"线上买私教","all_num":null,"all_count":0,"point_num_up":0,"point_num":60,"pt_rule":"无上限","state_all_num":0,"state_num":0,"is_state":0},{"pt_code":18,"pt_name":"线上预约场馆","all_num":null,"all_count":0,"point_num_up":0,"point_num":20,"pt_rule":"无上限","state_all_num":0,"state_num":0,"is_state":0},{"pt_code":19,"pt_name":"线上发起运动","all_num":null,"all_count":0,"point_num_up":40,"point_num":20,"pt_rule":"每日上限40积分","state_all_num":2,"state_num":0,"is_state":0},{"pt_code":20,"pt_name":"用户上课","all_num":null,"all_count":0,"point_num_up":40,"point_num":20,"pt_rule":"每日上限40积分","state_all_num":2,"state_num":0,"is_state":0},{"pt_code":23,"pt_name":"在线报名","all_num":null,"all_count":0,"point_num_up":20,"point_num":10,"pt_rule":"每日上限20积分","state_all_num":2,"state_num":0,"is_state":0},{"pt_code":24,"pt_name":"阅读健康百科","all_num":null,"all_count":0,"point_num_up":10,"point_num":2,"pt_rule":"每日上限10积分","state_all_num":5,"state_num":0,"is_state":0},{"pt_code":27,"pt_name":"发表的动态被点赞","all_num":null,"all_count":0,"point_num_up":10,"point_num":1,"pt_rule":"每日上限10积分","state_all_num":10,"state_num":0,"is_state":0},{"pt_code":28,"pt_name":"发表的动态被评论","all_num":null,"all_count":0,"point_num_up":20,"point_num":2,"pt_rule":"每日上限20积分","state_all_num":10,"state_num":0,"is_state":0},{"pt_code":32,"pt_name":"积分抽奖","all_num":null,"all_count":0,"point_num_up":0,"point_num":20,"pt_rule":"无上限","state_all_num":0,"state_num":0,"is_state":0},{"pt_code":33,"pt_name":"积分中奖","all_num":null,"all_count":0,"point_num_up":0,"point_num":0,"pt_rule":"无上限","state_all_num":0,"state_num":0,"is_state":0}]}
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
             * pt_code : 1
             * pt_name : 首次注册登录
             * all_num : null
             * all_count : 0
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
            private int all_count;
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

            public int getAll_count() {
                return all_count;
            }

            public void setAll_count(int all_count) {
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
