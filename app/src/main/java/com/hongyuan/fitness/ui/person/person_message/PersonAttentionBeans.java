package com.hongyuan.fitness.ui.person.person_message;

import java.io.Serializable;

public class PersonAttentionBeans implements Serializable {

    private int m_id;
    private String mi_head;
    private String m_mobile;
    private String m_name;
    private int is_friend;

    public int getM_id() {
        return m_id;
    }

    public void setM_id(int m_id) {
        this.m_id = m_id;
    }

    public String getMi_head() {
        return mi_head;
    }

    public void setMi_head(String mi_head) {
        this.mi_head = mi_head;
    }

    public String getM_mobile() {
        return m_mobile;
    }

    public void setM_mobile(String m_mobile) {
        this.m_mobile = m_mobile;
    }

    public String getM_name() {
        return m_name;
    }

    public void setM_name(String m_name) {
        this.m_name = m_name;
    }

    public int getIs_friend() {
        return is_friend;
    }

    public void setIs_friend(int is_friend) {
        this.is_friend = is_friend;
    }
}
