package com.hongyuan.fitness.ui.main.main_home.recommend.vtwo_home;

import com.hongyuan.fitness.base.BaseBean;

import java.util.List;

public class GymCategoryBeans extends BaseBean {

    /**
     * data : {"list":[{"gc_name":"游泳","gc_img_day":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20200724/8c000bbcc943eea7863e3c97a59ec71a5f6eee5d_20x20.png","gc_img_night":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20200724/333d4774567d31945bf2313fde7a1da7c56adb04_20x20.png","gc_id":5,"gc_img":""},{"gc_name":"健身","gc_img_day":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20200724/972f6f8e282feca8163c7b5d5e4343953b1b55b9_20x20.png","gc_img_night":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20200724/aa02181c9d01599c766da0347e10fd030f7b7718_20x20.png","gc_id":1,"gc_img":""},{"gc_name":"壁球","gc_img_day":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20200724/b500722428acb80f821f0709fed2be280cefcaaa_20x20.png","gc_img_night":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20200724/c49cd45c6494820cd2aec8f34990f54d81085308_20x20.png","gc_id":8,"gc_img":""},{"gc_name":"高尔夫","gc_img_day":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20200724/30bf2460ccb80b20773be65cc79671b983042703_20x20.png","gc_img_night":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20200724/a36bc002989df4122cce728d67006ed1034e984e_20x20.png","gc_id":9,"gc_img":""}]}
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
             * gc_name : 游泳
             * gc_img_day : http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20200724/8c000bbcc943eea7863e3c97a59ec71a5f6eee5d_20x20.png
             * gc_img_night : http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20200724/333d4774567d31945bf2313fde7a1da7c56adb04_20x20.png
             * gc_id : 5
             * gc_img :
             */

            private String gc_name;
            private String gc_img_day;
            private String gc_img_night;
            private int gc_id;
            private String gc_img;

            public String getGc_name() {
                return gc_name;
            }

            public void setGc_name(String gc_name) {
                this.gc_name = gc_name;
            }

            public String getGc_img_day() {
                return gc_img_day;
            }

            public void setGc_img_day(String gc_img_day) {
                this.gc_img_day = gc_img_day;
            }

            public String getGc_img_night() {
                return gc_img_night;
            }

            public void setGc_img_night(String gc_img_night) {
                this.gc_img_night = gc_img_night;
            }

            public int getGc_id() {
                return gc_id;
            }

            public void setGc_id(int gc_id) {
                this.gc_id = gc_id;
            }

            public String getGc_img() {
                return gc_img;
            }

            public void setGc_img(String gc_img) {
                this.gc_img = gc_img;
            }
        }
    }
}
