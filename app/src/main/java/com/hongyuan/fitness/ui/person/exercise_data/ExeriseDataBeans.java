package com.hongyuan.fitness.ui.person.exercise_data;

import com.hongyuan.fitness.base.BaseBean;

public class ExeriseDataBeans extends BaseBean {

    /**
     * data : {"info":{"walk_num":0,"walk_distance":null,"walk_exerciseTime":null,"walk_exerciseType":1,"running_num":0,"running_distance":0,"running_exerciseTime":0,"running_exerciseType":2,"fitness_num":0,"fitness_distance":0,"fitness_exerciseTime":0,"fitness_exerciseType":3,"ball_num":0,"ball_distance":0,"ball_exerciseTime":0,"ball_exerciseType":4,"swim_num":0,"swim_distance":0,"swim_exerciseTime":0,"swim_exerciseType":5}}
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
         * info : {"walk_num":0,"walk_distance":null,"walk_exerciseTime":null,"walk_exerciseType":1,"running_num":0,"running_distance":0,"running_exerciseTime":0,"running_exerciseType":2,"fitness_num":0,"fitness_distance":0,"fitness_exerciseTime":0,"fitness_exerciseType":3,"ball_num":0,"ball_distance":0,"ball_exerciseTime":0,"ball_exerciseType":4,"swim_num":0,"swim_distance":0,"swim_exerciseTime":0,"swim_exerciseType":5}
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
             * walk_num : 0
             * walk_distance : null
             * walk_exerciseTime : null
             * walk_exerciseType : 1
             * running_num : 0
             * running_distance : 0
             * running_exerciseTime : 0
             * running_exerciseType : 2
             * fitness_num : 0
             * fitness_distance : 0
             * fitness_exerciseTime : 0
             * fitness_exerciseType : 3
             * ball_num : 0
             * ball_distance : 0
             * ball_exerciseTime : 0
             * ball_exerciseType : 4
             * swim_num : 0
             * swim_distance : 0
             * swim_exerciseTime : 0
             * swim_exerciseType : 5
             */

            private int walk_num;
            private Object walk_distance;
            private Object walk_exerciseTime;
            private int walk_exerciseType;
            private int running_num;
            private int running_distance;
            private int running_exerciseTime;
            private int running_exerciseType;
            private int fitness_num;
            private int fitness_distance;
            private int fitness_exerciseTime;
            private int fitness_exerciseType;
            private int ball_num;
            private int ball_distance;
            private int ball_exerciseTime;
            private int ball_exerciseType;
            private int swim_num;
            private int swim_distance;
            private int swim_exerciseTime;
            private int swim_exerciseType;

            public int getWalk_num() {
                return walk_num;
            }

            public void setWalk_num(int walk_num) {
                this.walk_num = walk_num;
            }

            public Object getWalk_distance() {
                if(walk_distance == null){
                    return 0;
                }else{
                    return walk_distance;
                }

            }

            public void setWalk_distance(Object walk_distance) {
                this.walk_distance = walk_distance;
            }

            public Object getWalk_exerciseTime() {
                return walk_exerciseTime;
            }

            public void setWalk_exerciseTime(Object walk_exerciseTime) {
                this.walk_exerciseTime = walk_exerciseTime;
            }

            public int getWalk_exerciseType() {
                return walk_exerciseType;
            }

            public void setWalk_exerciseType(int walk_exerciseType) {
                this.walk_exerciseType = walk_exerciseType;
            }

            public int getRunning_num() {
                return running_num;
            }

            public void setRunning_num(int running_num) {
                this.running_num = running_num;
            }

            public int getRunning_distance() {
                return running_distance;
            }

            public void setRunning_distance(int running_distance) {
                this.running_distance = running_distance;
            }

            public int getRunning_exerciseTime() {
                return running_exerciseTime;
            }

            public void setRunning_exerciseTime(int running_exerciseTime) {
                this.running_exerciseTime = running_exerciseTime;
            }

            public int getRunning_exerciseType() {
                return running_exerciseType;
            }

            public void setRunning_exerciseType(int running_exerciseType) {
                this.running_exerciseType = running_exerciseType;
            }

            public int getFitness_num() {
                return fitness_num;
            }

            public void setFitness_num(int fitness_num) {
                this.fitness_num = fitness_num;
            }

            public int getFitness_distance() {
                return fitness_distance;
            }

            public void setFitness_distance(int fitness_distance) {
                this.fitness_distance = fitness_distance;
            }

            public int getFitness_exerciseTime() {
                return fitness_exerciseTime;
            }

            public void setFitness_exerciseTime(int fitness_exerciseTime) {
                this.fitness_exerciseTime = fitness_exerciseTime;
            }

            public int getFitness_exerciseType() {
                return fitness_exerciseType;
            }

            public void setFitness_exerciseType(int fitness_exerciseType) {
                this.fitness_exerciseType = fitness_exerciseType;
            }

            public int getBall_num() {
                return ball_num;
            }

            public void setBall_num(int ball_num) {
                this.ball_num = ball_num;
            }

            public int getBall_distance() {
                return ball_distance;
            }

            public void setBall_distance(int ball_distance) {
                this.ball_distance = ball_distance;
            }

            public int getBall_exerciseTime() {
                return ball_exerciseTime;
            }

            public void setBall_exerciseTime(int ball_exerciseTime) {
                this.ball_exerciseTime = ball_exerciseTime;
            }

            public int getBall_exerciseType() {
                return ball_exerciseType;
            }

            public void setBall_exerciseType(int ball_exerciseType) {
                this.ball_exerciseType = ball_exerciseType;
            }

            public int getSwim_num() {
                return swim_num;
            }

            public void setSwim_num(int swim_num) {
                this.swim_num = swim_num;
            }

            public int getSwim_distance() {
                return swim_distance;
            }

            public void setSwim_distance(int swim_distance) {
                this.swim_distance = swim_distance;
            }

            public int getSwim_exerciseTime() {
                return swim_exerciseTime;
            }

            public void setSwim_exerciseTime(int swim_exerciseTime) {
                this.swim_exerciseTime = swim_exerciseTime;
            }

            public int getSwim_exerciseType() {
                return swim_exerciseType;
            }

            public void setSwim_exerciseType(int swim_exerciseType) {
                this.swim_exerciseType = swim_exerciseType;
            }
        }
    }
}
