package com.hongyuan.fitness.ui.membership_card.card_detail;

import com.hongyuan.fitness.base.BaseBean;

public class CardDetailsBean extends BaseBean {


    /**
     * data : {"card_id":1,"card_name":"首玺健身年卡","card_original_price":"10000.00","card_sale_price":"8000.00","ct_id":1,"expire_time":0,"card_days":365,"card_img":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190722/330ac86fb40e0b9538c70c224726bd50424c323f_648x454.jpg","admin_id":1,"add_time":1563774702,"update_time":0,"update_admin":0,"card_note":"范德萨范德萨发送到范德萨范德萨富士达","os_id":12,"card_type":1,"osl_id":0,"ct_name":"年卡","os_name":"首玺健身(旗舰店)","osl_name":null,"card_type_name":"门店卡"}
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
         * card_id : 1
         * card_name : 首玺健身年卡
         * card_original_price : 10000.00
         * card_sale_price : 8000.00
         * ct_id : 1
         * expire_time : 0
         * card_days : 365
         * card_img : http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190722/330ac86fb40e0b9538c70c224726bd50424c323f_648x454.jpg
         * admin_id : 1
         * add_time : 1563774702
         * update_time : 0
         * update_admin : 0
         * card_note : 范德萨范德萨发送到范德萨范德萨富士达
         * os_id : 12
         * card_type : 1
         * osl_id : 0
         * ct_name : 年卡
         * os_name : 首玺健身(旗舰店)
         * osl_name : null
         * card_type_name : 门店卡
         */

        private int card_id;
        private String card_name;
        private String card_original_price;
        private String card_sale_price;
        private int ct_id;
        private int expire_time;
        private int card_days;
        private String card_img;
        private int admin_id;
        private int add_time;
        private int update_time;
        private int update_admin;
        private String card_note;
        private int os_id;
        private int card_type;
        private int osl_id;
        private String ct_name;
        private String os_name;
        private Object osl_name;
        private String card_type_name;

        public int getCard_id() {
            return card_id;
        }

        public void setCard_id(int card_id) {
            this.card_id = card_id;
        }

        public String getCard_name() {
            return card_name;
        }

        public void setCard_name(String card_name) {
            this.card_name = card_name;
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

        public int getCt_id() {
            return ct_id;
        }

        public void setCt_id(int ct_id) {
            this.ct_id = ct_id;
        }

        public int getExpire_time() {
            return expire_time;
        }

        public void setExpire_time(int expire_time) {
            this.expire_time = expire_time;
        }

        public int getCard_days() {
            return card_days;
        }

        public void setCard_days(int card_days) {
            this.card_days = card_days;
        }

        public String getCard_img() {
            return card_img;
        }

        public void setCard_img(String card_img) {
            this.card_img = card_img;
        }

        public int getAdmin_id() {
            return admin_id;
        }

        public void setAdmin_id(int admin_id) {
            this.admin_id = admin_id;
        }

        public int getAdd_time() {
            return add_time;
        }

        public void setAdd_time(int add_time) {
            this.add_time = add_time;
        }

        public int getUpdate_time() {
            return update_time;
        }

        public void setUpdate_time(int update_time) {
            this.update_time = update_time;
        }

        public int getUpdate_admin() {
            return update_admin;
        }

        public void setUpdate_admin(int update_admin) {
            this.update_admin = update_admin;
        }

        public String getCard_note() {
            return card_note;
        }

        public void setCard_note(String card_note) {
            this.card_note = card_note;
        }

        public int getOs_id() {
            return os_id;
        }

        public void setOs_id(int os_id) {
            this.os_id = os_id;
        }

        public int getCard_type() {
            return card_type;
        }

        public void setCard_type(int card_type) {
            this.card_type = card_type;
        }

        public int getOsl_id() {
            return osl_id;
        }

        public void setOsl_id(int osl_id) {
            this.osl_id = osl_id;
        }

        public String getCt_name() {
            return ct_name;
        }

        public void setCt_name(String ct_name) {
            this.ct_name = ct_name;
        }

        public String getOs_name() {
            return os_name;
        }

        public void setOs_name(String os_name) {
            this.os_name = os_name;
        }

        public Object getOsl_name() {
            return osl_name;
        }

        public void setOsl_name(Object osl_name) {
            this.osl_name = osl_name;
        }

        public String getCard_type_name() {
            return card_type_name;
        }

        public void setCard_type_name(String card_type_name) {
            this.card_type_name = card_type_name;
        }
    }
}
