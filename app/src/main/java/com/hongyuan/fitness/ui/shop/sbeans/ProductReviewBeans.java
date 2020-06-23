package com.hongyuan.fitness.ui.shop.sbeans;

import com.hongyuan.fitness.base.BaseBean;
import com.hongyuan.fitness.ui.shop.sadapter.SnewOrdersAdapter;
import com.hongyuan.fitness.util.BaseUtil;

import java.util.List;

public class ProductReviewBeans extends BaseBean {

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * gp_id : 49
         * g_id : 23
         * g_img : http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190727/1e0696325d1d51e414b3c40bff2e38a0bd26b726_400x403.jpg
         * g_name : 苏打水机套装
         * sku_names :
         * buy_num : 1
         * gp_price : 169.00
         * gp_point : 0
         */

        private int gp_id;
        private int g_id;
        private String g_img;
        private String g_name;
        private String sku_names;
        private int buy_num;
        private String gp_price;
        private int gp_point;

        //获取显示类型
        public int getShowType() {
            if(BaseUtil.isValue(this.gp_price) && Double.parseDouble(this.gp_price) > 0 && this.gp_point > 0){
                return SnewOrdersAdapter.SHOW_MONEY_POINT;
            }else if(BaseUtil.isValue(this.gp_price) && Double.parseDouble(this.gp_price) > 0 && this.gp_point <= 0 ){
                return SnewOrdersAdapter.SHOW_MONEY;
            }else if((!BaseUtil.isValue(this.gp_price) || Double.parseDouble(this.gp_price) <= 0 ) && this.gp_point > 0){
                return SnewOrdersAdapter.SHOW_POINT;
            }else{
                return SnewOrdersAdapter.SHOW_MONEY_POINT;
            }
        }

        public int getGp_id() {
            return gp_id;
        }

        public void setGp_id(int gp_id) {
            this.gp_id = gp_id;
        }

        public int getG_id() {
            return g_id;
        }

        public void setG_id(int g_id) {
            this.g_id = g_id;
        }

        public String getG_img() {
            return g_img;
        }

        public void setG_img(String g_img) {
            this.g_img = g_img;
        }

        public String getG_name() {
            return g_name;
        }

        public void setG_name(String g_name) {
            this.g_name = g_name;
        }

        public String getSku_names() {
            return sku_names;
        }

        public void setSku_names(String sku_names) {
            this.sku_names = sku_names;
        }

        public int getBuy_num() {
            return buy_num;
        }

        public void setBuy_num(int buy_num) {
            this.buy_num = buy_num;
        }

        public String getGp_price() {
            return gp_price;
        }

        public void setGp_price(String gp_price) {
            this.gp_price = gp_price;
        }

        public int getGp_point() {
            return gp_point;
        }

        public void setGp_point(int gp_point) {
            this.gp_point = gp_point;
        }
    }
}
