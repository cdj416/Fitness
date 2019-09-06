package com.hongyuan.fitness.ui.heat.heat_detail;

import com.hongyuan.fitness.base.BaseBean;

import java.util.List;

public class HeatDetailBean  extends BaseBean {


    /**
     * data : {"f_id":2,"f_name":"面条","f_img":"http://88php.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190704/4d99ea4076285bc55b73a055c34dcf2383719b32_648x454.jpg","fc_id":3,"f_type":2,"f_cal":"12.00","f_protein":"345.00","f_fat":"433.00","f_carbohydrate":"48.00","f_sugar":"44.00","f_na":"45.00","fc_name":"主食","f_type_name":"固体","fu":[{"fu_id":11,"fu_name":"小碗","f_id":2,"fu_num":"300.00"},{"fu_id":12,"fu_name":"中碗","f_id":2,"fu_num":"500.00"}]}
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
         * f_id : 2
         * f_name : 面条
         * f_img : http://88php.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190704/4d99ea4076285bc55b73a055c34dcf2383719b32_648x454.jpg
         * fc_id : 3
         * f_type : 2
         * f_cal : 12.00
         * f_protein : 345.00
         * f_fat : 433.00
         * f_carbohydrate : 48.00
         * f_sugar : 44.00
         * f_na : 45.00
         * fc_name : 主食
         * f_type_name : 固体
         * fu : [{"fu_id":11,"fu_name":"小碗","f_id":2,"fu_num":"300.00"},{"fu_id":12,"fu_name":"中碗","f_id":2,"fu_num":"500.00"}]
         */

        private int f_id;
        private String f_name;
        private String f_img;
        private int fc_id;
        private int f_type;
        private String f_cal;
        private String f_protein;
        private String f_fat;
        private String f_carbohydrate;
        private String f_sugar;
        private String f_na;
        private String fc_name;
        private String f_type_name;
        private List<FuBean> fu;

        public int getF_id() {
            return f_id;
        }

        public void setF_id(int f_id) {
            this.f_id = f_id;
        }

        public String getF_name() {
            return f_name;
        }

        public void setF_name(String f_name) {
            this.f_name = f_name;
        }

        public String getF_img() {
            return f_img;
        }

        public void setF_img(String f_img) {
            this.f_img = f_img;
        }

        public int getFc_id() {
            return fc_id;
        }

        public void setFc_id(int fc_id) {
            this.fc_id = fc_id;
        }

        public int getF_type() {
            return f_type;
        }

        public void setF_type(int f_type) {
            this.f_type = f_type;
        }

        public String getF_cal() {
            return f_cal;
        }

        public void setF_cal(String f_cal) {
            this.f_cal = f_cal;
        }

        public String getF_protein() {
            return f_protein;
        }

        public void setF_protein(String f_protein) {
            this.f_protein = f_protein;
        }

        public String getF_fat() {
            return f_fat;
        }

        public void setF_fat(String f_fat) {
            this.f_fat = f_fat;
        }

        public String getF_carbohydrate() {
            return f_carbohydrate;
        }

        public void setF_carbohydrate(String f_carbohydrate) {
            this.f_carbohydrate = f_carbohydrate;
        }

        public String getF_sugar() {
            return f_sugar;
        }

        public void setF_sugar(String f_sugar) {
            this.f_sugar = f_sugar;
        }

        public String getF_na() {
            return f_na;
        }

        public void setF_na(String f_na) {
            this.f_na = f_na;
        }

        public String getFc_name() {
            return fc_name;
        }

        public void setFc_name(String fc_name) {
            this.fc_name = fc_name;
        }

        public String getF_type_name() {
            return f_type_name;
        }

        public void setF_type_name(String f_type_name) {
            this.f_type_name = f_type_name;
        }

        public List<FuBean> getFu() {
            return fu;
        }

        public void setFu(List<FuBean> fu) {
            this.fu = fu;
        }

        public static class FuBean {
            /**
             * fu_id : 11
             * fu_name : 小碗
             * f_id : 2
             * fu_num : 300.00
             */

            private int fu_id;
            private String fu_name;
            private int f_id;
            private String fu_num;

            public int getFu_id() {
                return fu_id;
            }

            public void setFu_id(int fu_id) {
                this.fu_id = fu_id;
            }

            public String getFu_name() {
                return fu_name;
            }

            public void setFu_name(String fu_name) {
                this.fu_name = fu_name;
            }

            public int getF_id() {
                return f_id;
            }

            public void setF_id(int f_id) {
                this.f_id = f_id;
            }

            public String getFu_num() {
                return fu_num;
            }

            public void setFu_num(String fu_num) {
                this.fu_num = fu_num;
            }
        }
    }
}
