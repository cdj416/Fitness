package com.hongyuan.fitness.ui.person.mine_point.point_rules;

public class RulesBean {
    private String taskName;
    private String reward;
    private String maxReward;

    public RulesBean(String taskName, String reward, String maxReward) {
        this.taskName = taskName;
        this.reward = reward;
        this.maxReward = maxReward;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getReward() {
        return reward;
    }

    public void setReward(String reward) {
        this.reward = reward;
    }

    public String getMaxReward() {
        return maxReward;
    }

    public void setMaxReward(String maxReward) {
        this.maxReward = maxReward;
    }
}
