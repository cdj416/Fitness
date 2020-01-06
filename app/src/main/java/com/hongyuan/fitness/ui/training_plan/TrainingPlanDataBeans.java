package com.hongyuan.fitness.ui.training_plan;

import java.io.Serializable;

public class TrainingPlanDataBeans implements Serializable {

    private String sex;
    private String bothirdDay;
    private String height;
    private String weight;
    private String planMb;
    private String planMbName;
    private String planWeight;
    private String planDay;

    public String getPlanMbName() {
        return planMbName;
    }

    public void setPlanMbName(String planMbName) {
        this.planMbName = planMbName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getBothirdDay() {
        return bothirdDay;
    }

    public void setBothirdDay(String bothirdDay) {
        this.bothirdDay = bothirdDay;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getPlanMb() {
        return planMb;
    }

    public void setPlanMb(String planMb) {
        this.planMb = planMb;
    }

    public String getPlanWeight() {
        return planWeight;
    }

    public void setPlanWeight(String planWeight) {
        this.planWeight = planWeight;
    }

    public String getPlanDay() {
        return planDay;
    }

    public void setPlanDay(String planDay) {
        this.planDay = planDay;
    }
}
