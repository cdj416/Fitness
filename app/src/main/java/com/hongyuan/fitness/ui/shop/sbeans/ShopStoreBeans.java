package com.hongyuan.fitness.ui.shop.sbeans;

import com.hongyuan.fitness.base.BaseBean;

public class ShopStoreBeans extends BaseBean {

    /**
     * data : {"store_id":42,"store_name":"湖州志裕","store_logo":"https://gdp.alicdn.com/imgextra/i2/2862021803/O1CN01PwOaGM1PBocqkAOjD_!!2862021803.jpg","g_score":"4.4","store_background_img":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20200301/d6e8d9eed2598af883e92b1f6516a023c539d652_800x467.jpg","store_content":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190718/3446465387ccad7043e2560604cc44f8a3c9d167_1242x828.jpg,http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20200210/475c145ee9bfe51bccc4ce99ac40ddffcbaf929f_800x533.jpg,http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190727/1e0696325d1d51e414b3c40bff2e38a0bd26b726_400x403.jpg","collection_num":0,"is_collection":0}
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
         * store_id : 42
         * store_name : 湖州志裕
         * store_logo : https://gdp.alicdn.com/imgextra/i2/2862021803/O1CN01PwOaGM1PBocqkAOjD_!!2862021803.jpg
         * g_score : 4.4
         * store_background_img : http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20200301/d6e8d9eed2598af883e92b1f6516a023c539d652_800x467.jpg
         * store_content : http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190718/3446465387ccad7043e2560604cc44f8a3c9d167_1242x828.jpg,http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20200210/475c145ee9bfe51bccc4ce99ac40ddffcbaf929f_800x533.jpg,http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190727/1e0696325d1d51e414b3c40bff2e38a0bd26b726_400x403.jpg
         * collection_num : 0
         * is_collection : 0
         */

        private int store_id;
        private String store_name;
        private String store_logo;
        private String g_score;
        private String store_background_img;
        private String store_content;
        private int collection_num;
        private int is_collection;

        public int getStore_id() {
            return store_id;
        }

        public void setStore_id(int store_id) {
            this.store_id = store_id;
        }

        public String getStore_name() {
            return store_name;
        }

        public void setStore_name(String store_name) {
            this.store_name = store_name;
        }

        public String getStore_logo() {
            return store_logo;
        }

        public void setStore_logo(String store_logo) {
            this.store_logo = store_logo;
        }

        public String getG_score() {
            return g_score;
        }

        public void setG_score(String g_score) {
            this.g_score = g_score;
        }

        public String getStore_background_img() {
            return store_background_img;
        }

        public void setStore_background_img(String store_background_img) {
            this.store_background_img = store_background_img;
        }

        public String getStore_content() {
            return store_content;
        }

        public void setStore_content(String store_content) {
            this.store_content = store_content;
        }

        public int getCollection_num() {
            return collection_num;
        }

        public void setCollection_num(int collection_num) {
            this.collection_num = collection_num;
        }

        public int getIs_collection() {
            return is_collection;
        }

        public void setIs_collection(int is_collection) {
            this.is_collection = is_collection;
        }
    }
}
