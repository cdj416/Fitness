package com.hongyuan.fitness.ui.person.physical_data;

import com.hongyuan.fitness.base.BaseBean;

public class PhysicalBeans extends BaseBean {

    /**
     * data : {"info":{"weight":"","height":"","bmi":"","xw":"","yw":"","tw":"","dtw":"","xtw":"","sbw":"","bfp":""}}
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
         * info : {"weight":"","height":"","bmi":"","xw":"","yw":"","tw":"","dtw":"","xtw":"","sbw":"","bfp":""}
         */

        private InfoBean info;

        public InfoBean getInfo() {
            return info;
        }

        public void setInfo(InfoBean info) {
            this.info = info;
        }

        public static class InfoBean {
            /**
             * weight :
             * height :
             * bmi :
             * xw :
             * yw :
             * tw :
             * dtw :
             * xtw :
             * sbw :
             * bfp :
             */

            private String weight;
            private String height;
            private String bmi;
            private String xw;
            private String yw;
            private String tw;
            private String dtw;
            private String xtw;
            private String sbw;
            private String bfp;

            public String getWeight() {
                return weight;
            }

            public void setWeight(String weight) {
                this.weight = weight;
            }

            public String getHeight() {
                return height;
            }

            public void setHeight(String height) {
                this.height = height;
            }

            public String getBmi() {
                return bmi;
            }

            public void setBmi(String bmi) {
                this.bmi = bmi;
            }

            public String getXw() {
                return xw;
            }

            public void setXw(String xw) {
                this.xw = xw;
            }

            public String getYw() {
                return yw;
            }

            public void setYw(String yw) {
                this.yw = yw;
            }

            public String getTw() {
                return tw;
            }

            public void setTw(String tw) {
                this.tw = tw;
            }

            public String getDtw() {
                return dtw;
            }

            public void setDtw(String dtw) {
                this.dtw = dtw;
            }

            public String getXtw() {
                return xtw;
            }

            public void setXtw(String xtw) {
                this.xtw = xtw;
            }

            public String getSbw() {
                return sbw;
            }

            public void setSbw(String sbw) {
                this.sbw = sbw;
            }

            public String getBfp() {
                return bfp;
            }

            public void setBfp(String bfp) {
                this.bfp = bfp;
            }
        }
    }
}
