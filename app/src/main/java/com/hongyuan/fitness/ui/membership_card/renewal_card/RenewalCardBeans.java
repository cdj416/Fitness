package com.hongyuan.fitness.ui.membership_card.renewal_card;

import com.hongyuan.fitness.base.BaseBean;

import java.util.List;

public class RenewalCardBeans extends BaseBean {

    /**
     * data : {"list":[{"card_name":"通用普通卡","card_id":2,"card_original_price":"10000.00","card_sale_price":"5000.00","card_days":400,"card_note":"送30天","ct_id":1,"ct_name":"年卡"},{"card_name":"通用旗舰卡","card_id":3,"card_original_price":"20000.00","card_sale_price":"7000.00","card_days":450,"card_note":"送50天，优惠1000","ct_id":1,"ct_name":"年卡"},{"card_name":"通用顶级卡","card_id":4,"card_original_price":"30000.00","card_sale_price":"9000.00","card_days":500,"card_note":"优惠3000，送30天","ct_id":1,"ct_name":"年卡"}]}
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
             * card_name : 通用普通卡
             * card_id : 2
             * card_original_price : 10000.00
             * card_sale_price : 5000.00
             * card_days : 400
             * card_note : 送30天
             * ct_id : 1
             * ct_name : 年卡
             */

            private String card_name;
            private int card_id;
            private String card_original_price;
            private String card_sale_price;
            private int card_days;
            private String card_note;
            private int ct_id;
            private String ct_name;

            public String getCard_name() {
                return card_name;
            }

            public void setCard_name(String card_name) {
                this.card_name = card_name;
            }

            public int getCard_id() {
                return card_id;
            }

            public void setCard_id(int card_id) {
                this.card_id = card_id;
            }

            public String getCard_original_price() {
                return card_original_price;
            }

            public void setCard_original_price(String card_original_price) {
                this.card_original_price = card_original_price;
            }

            public String getCard_sale_price() {
                return card_sale_price;
            }

            public void setCard_sale_price(String card_sale_price) {
                this.card_sale_price = card_sale_price;
            }

            public int getCard_days() {
                return card_days;
            }

            public void setCard_days(int card_days) {
                this.card_days = card_days;
            }

            public String getCard_note() {
                return card_note;
            }

            public void setCard_note(String card_note) {
                this.card_note = card_note;
            }

            public int getCt_id() {
                return ct_id;
            }

            public void setCt_id(int ct_id) {
                this.ct_id = ct_id;
            }

            public String getCt_name() {
                return ct_name;
            }

            public void setCt_name(String ct_name) {
                this.ct_name = ct_name;
            }
        }
    }
}
