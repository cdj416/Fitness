package com.hongyuan.fitness.ui.shop.sbeans;

import com.hongyuan.fitness.base.BaseBean;

public class KeFuBeans extends BaseBean {

    /**
     * data : {"info":{"m_name":"m88888","m_mobile":"18621854159","mi_head":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20200103/ae5e6dcc6581dc12efe73b6e6d7c757112e7dc9f_2436x2436.jpg"}}
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
         * info : {"m_name":"m88888","m_mobile":"18621854159","mi_head":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20200103/ae5e6dcc6581dc12efe73b6e6d7c757112e7dc9f_2436x2436.jpg"}
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
             * m_name : m88888
             * m_mobile : 18621854159
             * mi_head : http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20200103/ae5e6dcc6581dc12efe73b6e6d7c757112e7dc9f_2436x2436.jpg
             */

            private String m_name;
            private String m_mobile;
            private String mi_head;

            public String getM_name() {
                return m_name;
            }

            public void setM_name(String m_name) {
                this.m_name = m_name;
            }

            public String getM_mobile() {
                return m_mobile;
            }

            public void setM_mobile(String m_mobile) {
                this.m_mobile = m_mobile;
            }

            public String getMi_head() {
                return mi_head;
            }

            public void setMi_head(String mi_head) {
                this.mi_head = mi_head;
            }
        }
    }
}
