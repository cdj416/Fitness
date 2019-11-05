package com.hongyuan.fitness.ui.membership_card.card_detail;

import java.io.Serializable;

public class CardAddPersonBeans implements Serializable {

    private String name;
    private String phoneOrDays;
    private int type;
    private String beansId;

    public String getBeansId() {
        return beansId;
    }

    public void setBeansId(String beansId) {
        this.beansId = beansId;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneOrDays() {
        return phoneOrDays;
    }

    public void setPhoneOrDays(String phoneOrDays) {
        this.phoneOrDays = phoneOrDays;
    }
}
