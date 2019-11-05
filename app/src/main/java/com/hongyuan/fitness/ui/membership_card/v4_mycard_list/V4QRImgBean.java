package com.hongyuan.fitness.ui.membership_card.v4_mycard_list;

import com.hongyuan.fitness.base.BaseBean;

public class V4QRImgBean extends BaseBean {

    /**
     * data : {"img":"http://192.168.1.16/qrcode/11571903914.png"}
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
         * img : http://192.168.1.16/qrcode/11571903914.png
         */

        private String img;

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }
    }
}
