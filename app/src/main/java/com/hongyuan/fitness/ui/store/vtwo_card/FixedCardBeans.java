package com.hongyuan.fitness.ui.store.vtwo_card;

public class FixedCardBeans {

    private int osl_id;
    private String os_name;
    private int cardType;

    public int getCardType() {
        return cardType;
    }

    public void setCardType(int cardType) {
        this.cardType = cardType;
    }

    public int getOsl_id() {
        return osl_id;
    }

    public void setOsl_id(int osl_id) {
        this.osl_id = osl_id;
    }

    public String getOs_name() {
        return os_name;
    }

    public void setOs_name(String os_name) {
        this.os_name = os_name;
    }

    public FixedCardBeans(int osl_id, String os_name, int cardType) {
        this.osl_id = osl_id;
        this.os_name = os_name;
        this.cardType = cardType;
    }
}
