package com.hongyuan.fitness.ui.mall.good_pay;

import java.io.Serializable;

public class PayDataBean implements Serializable {

    private String showPrice;
    private String showPoint;
    private String lavePoint;
    private String o_id;

    //约课需要的
    private ReservationData reservationData;

    public String getLavePoint() {
        return lavePoint;
    }

    public void setLavePoint(String lavePoint) {
        this.lavePoint = lavePoint;
    }

    public String getShowPrice() {
        return showPrice;
    }

    public void setShowPrice(String showPrice) {
        this.showPrice = showPrice;
    }

    public String getShowPoint() {
        return showPoint;
    }

    public void setShowPoint(String showPoint) {
        this.showPoint = showPoint;
    }

    public String getO_id() {
        return o_id;
    }

    public void setO_id(String o_id) {
        this.o_id = o_id;
    }

    public ReservationData getReservationData() {
        return reservationData;
    }

    public void setReservationData(ReservationData reservationData) {
        this.reservationData = reservationData;
    }

    /*
    * 自动约课需要的参数值
    * */
    public static class ReservationData implements Serializable{
        private String start_date;
        private String end_date;
        private String jl_mid;
        private String cp_id;


        public String getStart_date() {
            return start_date;
        }

        public void setStart_date(String start_date) {
            this.start_date = start_date;
        }

        public String getEnd_date() {
            return end_date;
        }

        public void setEnd_date(String end_date) {
            this.end_date = end_date;
        }

        public String getJl_mid() {
            return jl_mid;
        }

        public void setJl_mid(String jl_mid) {
            this.jl_mid = jl_mid;
        }

        public String getCp_id() {
            return cp_id;
        }

        public void setCp_id(String cp_id) {
            this.cp_id = cp_id;
        }
    }
}
