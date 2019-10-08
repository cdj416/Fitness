package com.hongyuan.fitness.ui.person.daily_punch;

import com.hongyuan.fitness.base.BaseBean;

import java.util.List;

public class TaskBeans extends BaseBean {

    /**
     * data : {"list":{"today_num":0,"all_num":"100","have_point":124,"list":[{"code":"add_circle","task_name":"去发现发布一条动态","task_note":"每天最多获取10积分","one_point":"10","level":0,"level_all":"1","state":"0"},{"code":"add_circle_praise","task_name":"去发现点赞一条动态","task_note":"每天最多获取10积分","one_point":"2","level":0,"level_all":"5","state":"0"},{"code":"run","task_name":"去户外使用跑步功能","task_note":"跑步1km米可获10积分","one_point":"10","level":0,"level_all":"1","state":"0"},{"code":"gz","task_name":"关注好友","task_note":"关注好友最低每天可获取10积分","one_point":"2","level":0,"level_all":"5","state":"0"},{"code":"qd","task_name":"每天签到打卡","task_note":"签到10积分，连续签到有额外积分奖励","one_point":"10","level":0,"level_all":"1","state":"0"},{"code":"ka_share","task_name":"每天打卡分享","task_note":"打卡分享每天可以获取20积分","one_point":"20","level":0,"level_all":"1","state":"0"}]}}
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
         * list : {"today_num":0,"all_num":"100","have_point":124,"list":[{"code":"add_circle","task_name":"去发现发布一条动态","task_note":"每天最多获取10积分","one_point":"10","level":0,"level_all":"1","state":"0"},{"code":"add_circle_praise","task_name":"去发现点赞一条动态","task_note":"每天最多获取10积分","one_point":"2","level":0,"level_all":"5","state":"0"},{"code":"run","task_name":"去户外使用跑步功能","task_note":"跑步1km米可获10积分","one_point":"10","level":0,"level_all":"1","state":"0"},{"code":"gz","task_name":"关注好友","task_note":"关注好友最低每天可获取10积分","one_point":"2","level":0,"level_all":"5","state":"0"},{"code":"qd","task_name":"每天签到打卡","task_note":"签到10积分，连续签到有额外积分奖励","one_point":"10","level":0,"level_all":"1","state":"0"},{"code":"ka_share","task_name":"每天打卡分享","task_note":"打卡分享每天可以获取20积分","one_point":"20","level":0,"level_all":"1","state":"0"}]}
         */

        private ListBeanX list;

        public ListBeanX getList() {
            return list;
        }

        public void setList(ListBeanX list) {
            this.list = list;
        }

        public static class ListBeanX {
            /**
             * today_num : 0
             * all_num : 100
             * have_point : 124
             * list : [{"code":"add_circle","task_name":"去发现发布一条动态","task_note":"每天最多获取10积分","one_point":"10","level":0,"level_all":"1","state":"0"},{"code":"add_circle_praise","task_name":"去发现点赞一条动态","task_note":"每天最多获取10积分","one_point":"2","level":0,"level_all":"5","state":"0"},{"code":"run","task_name":"去户外使用跑步功能","task_note":"跑步1km米可获10积分","one_point":"10","level":0,"level_all":"1","state":"0"},{"code":"gz","task_name":"关注好友","task_note":"关注好友最低每天可获取10积分","one_point":"2","level":0,"level_all":"5","state":"0"},{"code":"qd","task_name":"每天签到打卡","task_note":"签到10积分，连续签到有额外积分奖励","one_point":"10","level":0,"level_all":"1","state":"0"},{"code":"ka_share","task_name":"每天打卡分享","task_note":"打卡分享每天可以获取20积分","one_point":"20","level":0,"level_all":"1","state":"0"}]
             */

            private int today_num;
            private String all_num;
            private int have_point;
            private List<ListBean> list;

            public int getToday_num() {
                return today_num;
            }

            public void setToday_num(int today_num) {
                this.today_num = today_num;
            }

            public String getAll_num() {
                return all_num;
            }

            public void setAll_num(String all_num) {
                this.all_num = all_num;
            }

            public int getHave_point() {
                return have_point;
            }

            public void setHave_point(int have_point) {
                this.have_point = have_point;
            }

            public List<ListBean> getList() {
                return list;
            }

            public void setList(List<ListBean> list) {
                this.list = list;
            }

            public static class ListBean {
                /**
                 * code : add_circle
                 * task_name : 去发现发布一条动态
                 * task_note : 每天最多获取10积分
                 * one_point : 10
                 * level : 0
                 * level_all : 1
                 * state : 0
                 */

                private String code;
                private String task_name;
                private String task_note;
                private String one_point;
                private int level;
                private String level_all;
                private String state;

                public String getCode() {
                    return code;
                }

                public void setCode(String code) {
                    this.code = code;
                }

                public String getTask_name() {
                    return task_name;
                }

                public void setTask_name(String task_name) {
                    this.task_name = task_name;
                }

                public String getTask_note() {
                    return task_note;
                }

                public void setTask_note(String task_note) {
                    this.task_note = task_note;
                }

                public String getOne_point() {
                    return one_point;
                }

                public void setOne_point(String one_point) {
                    this.one_point = one_point;
                }

                public int getLevel() {
                    return level;
                }

                public void setLevel(int level) {
                    this.level = level;
                }

                public String getLevel_all() {
                    return level_all;
                }

                public void setLevel_all(String level_all) {
                    this.level_all = level_all;
                }

                public String getState() {
                    return state;
                }

                public void setState(String state) {
                    this.state = state;
                }
            }
        }
    }
}
