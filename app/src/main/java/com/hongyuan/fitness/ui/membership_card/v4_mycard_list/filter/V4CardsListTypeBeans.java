package com.hongyuan.fitness.ui.membership_card.v4_mycard_list.filter;

import com.hongyuan.fitness.base.BaseBean;

import java.util.List;

public class V4CardsListTypeBeans extends BaseBean {

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * cc_id : 3
         * cc_name : 家庭卡
         * cc_img : http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20191021/5e9dd5f616847a35eefc29b252030e08d55d4900_654x348.jpg
         * cc_adult_num : 3
         * cc_children_num : 1
         * cc_note : 会员权益
         购买会籍卡的用户即可获得随动健身平台会员，可进入指定门店 使用健身设施（包含临时更衣柜，不含其他单独收费项目)
         会员规则
         1、会籍卡仅限于指定门店使用
         2、会员卡仅限于用户本人使用，不得以任何形式授权或转授他人 使用
         3、平台通用会籍卡即买即开卡，门店会籍卡需在30天之内进店开 卡，30天后不到店默认为自动开卡。
         4、会员进店需扫码进入，以识别您的会员身份
         */

        private int cc_id;
        private String cc_name;
        private String cc_img;
        private int cc_adult_num;
        private int cc_children_num;
        private String cc_note;
        private boolean select;

        public DataBean(int cc_id, String cc_name, String cc_img, int cc_adult_num, int cc_children_num, String cc_note, boolean select) {
            this.cc_id = cc_id;
            this.cc_name = cc_name;
            this.cc_img = cc_img;
            this.cc_adult_num = cc_adult_num;
            this.cc_children_num = cc_children_num;
            this.cc_note = cc_note;
            this.select = select;
        }

        public boolean isSelect() {
            return select;
        }

        public void setSelect(boolean select) {
            this.select = select;
        }

        public int getCc_id() {
            return cc_id;
        }

        public void setCc_id(int cc_id) {
            this.cc_id = cc_id;
        }

        public String getCc_name() {
            return cc_name;
        }

        public void setCc_name(String cc_name) {
            this.cc_name = cc_name;
        }

        public String getCc_img() {
            return cc_img;
        }

        public void setCc_img(String cc_img) {
            this.cc_img = cc_img;
        }

        public int getCc_adult_num() {
            return cc_adult_num;
        }

        public void setCc_adult_num(int cc_adult_num) {
            this.cc_adult_num = cc_adult_num;
        }

        public int getCc_children_num() {
            return cc_children_num;
        }

        public void setCc_children_num(int cc_children_num) {
            this.cc_children_num = cc_children_num;
        }

        public String getCc_note() {
            return cc_note;
        }

        public void setCc_note(String cc_note) {
            this.cc_note = cc_note;
        }
    }
}
