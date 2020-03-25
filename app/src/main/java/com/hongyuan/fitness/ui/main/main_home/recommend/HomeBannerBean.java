package com.hongyuan.fitness.ui.main.main_home.recommend;

import com.hongyuan.fitness.base.BaseBean;

import java.io.Serializable;
import java.util.List;

public class HomeBannerBean extends BaseBean implements Serializable {


    /**
     * data : {"list":[{"img_id":9,"img_name":"首页幻灯4","it_id":1,"img_src":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190729/946ab5f37831753b479b7ed42207b11849f56eb5_1029x444.jpg","img_href_type":1,"img_href_id":"148979681","img_href":"3","img_href_code":"tm_store","img_state":1,"img_sys":0,"admin_id":1,"add_time":1563420382,"update_time":1577933440,"update_admin":1,"img_sort":4,"it_name":"首页幻灯"},{"img_id":8,"img_name":"首页幻灯3","it_id":1,"img_src":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20200102/3cd3baaa7909d0b252922087623f70fc43d7b021_686x218.jpg","img_href_type":1,"img_href_id":"546778594967","img_href":"45","img_href_code":"tm_product","img_state":1,"img_sys":0,"admin_id":1,"add_time":1563420364,"update_time":1577933426,"update_admin":1,"img_sort":3,"it_name":"首页幻灯"},{"img_id":7,"img_name":"首页幻灯2","it_id":1,"img_src":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20200102/18e7f4c358928e75aeaae811ff74ef3857164134_686x218.jpg","img_href_type":1,"img_href_id":"111299932","img_href":"4","img_href_code":"tb_store","img_state":1,"img_sys":0,"admin_id":1,"add_time":1563420340,"update_time":1577933412,"update_admin":1,"img_sort":2,"it_name":"首页幻灯"},{"img_id":1,"img_name":"首页幻灯5","it_id":1,"img_src":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190718/c7b1e4cadf5b4fd5c5cfda31e44ed53212d28a69_680x300.jpg","img_href_type":1,"img_href_id":"3189016","img_href":"fsdafdsaf2","img_href_code":"jd_product","img_state":1,"img_sys":0,"admin_id":1,"add_time":1559368423,"update_time":1577933452,"update_admin":1,"img_sort":1,"it_name":"首页幻灯"},{"img_id":6,"img_name":"首页幻灯1","it_id":1,"img_src":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20200102/fc4f0ebf3cda21e6f5e14db4911c1cd96cd4a92f_686x218.jpg","img_href_type":1,"img_href_id":"528653845933","img_href":"3","img_href_code":"tb_product","img_state":1,"img_sys":0,"admin_id":1,"add_time":1563420319,"update_time":1577933372,"update_admin":1,"img_sort":1,"it_name":"首页幻灯"},{"img_id":10,"img_name":"商城幻灯1","it_id":1,"img_src":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190729/946ab5f37831753b479b7ed42207b11849f56eb5_1029x444.jpg","img_href_type":1,"img_href_id":"1000075972","img_href":"1","img_href_code":"jd_store","img_state":1,"img_sys":0,"admin_id":1,"add_time":1564393622,"update_time":0,"update_admin":0,"img_sort":1,"it_name":"首页幻灯"}]}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean implements Serializable{
        private List<ListBean> list;

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean implements Serializable{
            /**
             * img_id : 9
             * img_name : 首页幻灯4
             * it_id : 1
             * img_src : http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190729/946ab5f37831753b479b7ed42207b11849f56eb5_1029x444.jpg
             * img_href_type : 1
             * img_href_id : 148979681
             * img_href : 3
             * img_href_code : tm_store
             * img_state : 1
             * img_sys : 0
             * admin_id : 1
             * add_time : 1563420382
             * update_time : 1577933440
             * update_admin : 1
             * img_sort : 4
             * it_name : 首页幻灯
             */

            private int img_id;
            private String img_name;
            private int it_id;
            private String img_src;
            private int img_href_type;
            private String img_href_id;
            private String img_href;
            private String img_href_code;
            private int img_state;
            private int img_sys;
            private int admin_id;
            private int add_time;
            private int update_time;
            private int update_admin;
            private int img_sort;
            private String it_name;

            public int getImg_id() {
                return img_id;
            }

            public void setImg_id(int img_id) {
                this.img_id = img_id;
            }

            public String getImg_name() {
                return img_name;
            }

            public void setImg_name(String img_name) {
                this.img_name = img_name;
            }

            public int getIt_id() {
                return it_id;
            }

            public void setIt_id(int it_id) {
                this.it_id = it_id;
            }

            public String getImg_src() {
                return img_src;
            }

            public void setImg_src(String img_src) {
                this.img_src = img_src;
            }

            public int getImg_href_type() {
                return img_href_type;
            }

            public void setImg_href_type(int img_href_type) {
                this.img_href_type = img_href_type;
            }

            public String getImg_href_id() {
                return img_href_id;
            }

            public void setImg_href_id(String img_href_id) {
                this.img_href_id = img_href_id;
            }

            public String getImg_href() {
                return img_href;
            }

            public void setImg_href(String img_href) {
                this.img_href = img_href;
            }

            public String getImg_href_code() {
                return img_href_code;
            }

            public void setImg_href_code(String img_href_code) {
                this.img_href_code = img_href_code;
            }

            public int getImg_state() {
                return img_state;
            }

            public void setImg_state(int img_state) {
                this.img_state = img_state;
            }

            public int getImg_sys() {
                return img_sys;
            }

            public void setImg_sys(int img_sys) {
                this.img_sys = img_sys;
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

            public int getImg_sort() {
                return img_sort;
            }

            public void setImg_sort(int img_sort) {
                this.img_sort = img_sort;
            }

            public String getIt_name() {
                return it_name;
            }

            public void setIt_name(String it_name) {
                this.it_name = it_name;
            }
        }
    }
}
