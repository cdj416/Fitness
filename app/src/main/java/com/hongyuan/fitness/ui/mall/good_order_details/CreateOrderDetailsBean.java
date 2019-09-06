package com.hongyuan.fitness.ui.mall.good_order_details;

import java.io.Serializable;

public class CreateOrderDetailsBean implements Serializable {

    //已下是请求接口需要的参数值
    private String gp_id;
    private String op_num;
    private String store_id;
    private String op_quhuo_osid;

    //需要显示的规格字符串
    private String norm;
    private String quhuostoreName;

    public String getQuhuostoreName() {
        return quhuostoreName;
    }

    public void setQuhuostoreName(String quhuostoreName) {
        this.quhuostoreName = quhuostoreName;
    }

    public String getNorm() {
        return norm;
    }

    public void setNorm(String norm) {
        this.norm = norm;
    }

    public String getGp_id() {
        return gp_id;
    }

    public void setGp_id(String gp_id) {
        this.gp_id = gp_id;
    }

    public String getOp_num() {
        return op_num;
    }

    public void setOp_num(String op_num) {
        this.op_num = op_num;
    }

    public String getStore_id() {
        return store_id;
    }

    public void setStore_id(String store_id) {
        this.store_id = store_id;
    }

    public String getOp_quhuo_osid() {
        return op_quhuo_osid;
    }

    public void setOp_quhuo_osid(String op_quhuo_osid) {
        this.op_quhuo_osid = op_quhuo_osid;
    }
}
