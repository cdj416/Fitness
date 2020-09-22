package com.hongyuan.fitness.ui.shop.sbeans;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.hongyuan.fitness.base.BaseBean;

import java.util.List;

public class NewPoitionBeans extends BaseBean {

    /**
     * data : {"my_point":2952,"g_point":900000,"qd_week":[{"point":3,"is_qd":1,"qd_date":"2020-09-07"},{"point":3,"is_qd":0,"qd_date":"2020-09-08"},{"point":3,"is_qd":0,"qd_date":"2020-09-09"},{"point":3,"is_qd":0,"qd_date":"2020-09-10"},{"point":3,"is_qd":0,"qd_date":"2020-09-11"},{"point":3,"is_qd":0,"qd_date":"2020-09-12"},{"point":13,"is_qd":0,"qd_date":"2020-09-13"}],"qd_info":{"is_today_qd":1,"lx_day":1},"goods":[{"g_id":31,"g_name":"麂皮绒橡胶瑜伽垫2","g_img":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190727/39fa7b39c5dbfbca85e4aa0eee6f6c0efe7e56d5_400x473.jpg","g_point":1,"g_price":"100.00","is_dh":1},{"g_id":27,"g_name":"运动水杯","g_img":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190727/4b2b305ebcc73bdabd1e770061609529eb8e486c_400x400.jpg","g_point":1200,"g_price":"0.00","is_dh":1},{"g_id":53,"g_name":"不锈钢摇摇杯","g_img":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20200529/ff3bfdac47de0c600c2b1b51ebb1c993e482e4a3_600x600.jpg","g_point":1200,"g_price":"0.00","is_dh":1},{"g_id":29,"g_name":"苏打水机套装","g_img":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190727/c2090d1a11003526a78b9e9a5282d6b1e2445985_400x403.jpg","g_point":1690,"g_price":"100.00","is_dh":1},{"g_id":52,"g_name":"气泡机家用便携式苏打水机套装","g_img":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20200529/84d5f39fa2d141edcc09e6723ea099581ef7b444_600x719.jpg","g_point":1690,"g_price":"0.00","is_dh":1},{"g_id":26,"g_name":"超大号纯棉浴巾套装","g_img":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20200603/97df1ca26ffb068c9e2ded5053681eae6ccbe455_447x525.jpg","g_point":1800,"g_price":"0.00","is_dh":1},{"g_id":28,"g_name":"旅行健身包","g_img":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190727/97767ec9b85fd09c96458b98cf7b487f8e6d2905_400x379.jpg","g_point":2000,"g_price":"0.00","is_dh":1}]}
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
         * my_point : 2952
         * g_point : 900000
         * qd_week : [{"point":3,"is_qd":1,"qd_date":"2020-09-07"},{"point":3,"is_qd":0,"qd_date":"2020-09-08"},{"point":3,"is_qd":0,"qd_date":"2020-09-09"},{"point":3,"is_qd":0,"qd_date":"2020-09-10"},{"point":3,"is_qd":0,"qd_date":"2020-09-11"},{"point":3,"is_qd":0,"qd_date":"2020-09-12"},{"point":13,"is_qd":0,"qd_date":"2020-09-13"}]
         * qd_info : {"is_today_qd":1,"lx_day":1}
         * goods : [{"g_id":31,"g_name":"麂皮绒橡胶瑜伽垫2","g_img":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190727/39fa7b39c5dbfbca85e4aa0eee6f6c0efe7e56d5_400x473.jpg","g_point":1,"g_price":"100.00","is_dh":1},{"g_id":27,"g_name":"运动水杯","g_img":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190727/4b2b305ebcc73bdabd1e770061609529eb8e486c_400x400.jpg","g_point":1200,"g_price":"0.00","is_dh":1},{"g_id":53,"g_name":"不锈钢摇摇杯","g_img":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20200529/ff3bfdac47de0c600c2b1b51ebb1c993e482e4a3_600x600.jpg","g_point":1200,"g_price":"0.00","is_dh":1},{"g_id":29,"g_name":"苏打水机套装","g_img":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190727/c2090d1a11003526a78b9e9a5282d6b1e2445985_400x403.jpg","g_point":1690,"g_price":"100.00","is_dh":1},{"g_id":52,"g_name":"气泡机家用便携式苏打水机套装","g_img":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20200529/84d5f39fa2d141edcc09e6723ea099581ef7b444_600x719.jpg","g_point":1690,"g_price":"0.00","is_dh":1},{"g_id":26,"g_name":"超大号纯棉浴巾套装","g_img":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20200603/97df1ca26ffb068c9e2ded5053681eae6ccbe455_447x525.jpg","g_point":1800,"g_price":"0.00","is_dh":1},{"g_id":28,"g_name":"旅行健身包","g_img":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190727/97767ec9b85fd09c96458b98cf7b487f8e6d2905_400x379.jpg","g_point":2000,"g_price":"0.00","is_dh":1}]
         */

        private int my_point;
        private int g_point;
        private String active;
        private QdInfoBean qd_info;
        private List<QdWeekBean> qd_week;
        private List<GoodsBean> goods;

        public String getActive() {
            return active;
        }

        public void setActive(String active) {
            this.active = active;
        }

        public int getMy_point() {
            return my_point;
        }

        public void setMy_point(int my_point) {
            this.my_point = my_point;
        }

        public int getG_point() {
            return g_point;
        }

        public void setG_point(int g_point) {
            this.g_point = g_point;
        }

        public QdInfoBean getQd_info() {
            return qd_info;
        }

        public void setQd_info(QdInfoBean qd_info) {
            this.qd_info = qd_info;
        }

        public List<QdWeekBean> getQd_week() {
            return qd_week;
        }

        public void setQd_week(List<QdWeekBean> qd_week) {
            this.qd_week = qd_week;
        }

        public List<GoodsBean> getGoods() {
            return goods;
        }

        public void setGoods(List<GoodsBean> goods) {
            this.goods = goods;
        }

        public static class QdInfoBean {
            /**
             * is_today_qd : 1
             * lx_day : 1
             */

            private int is_today_qd;
            private int lx_day;

            public int getIs_today_qd() {
                return is_today_qd;
            }

            public void setIs_today_qd(int is_today_qd) {
                this.is_today_qd = is_today_qd;
            }

            public int getLx_day() {
                return lx_day;
            }

            public void setLx_day(int lx_day) {
                this.lx_day = lx_day;
            }
        }

        public static class QdWeekBean {
            /**
             * point : 3
             * is_qd : 1
             * qd_date : 2020-09-07
             */

            private int point;
            private int is_qd;
            private String qd_date;

            public int getPoint() {
                return point;
            }

            public void setPoint(int point) {
                this.point = point;
            }

            public int getIs_qd() {
                return is_qd;
            }

            public void setIs_qd(int is_qd) {
                this.is_qd = is_qd;
            }

            public String getQd_date() {
                return qd_date;
            }

            public void setQd_date(String qd_date) {
                this.qd_date = qd_date;
            }
        }

        public static class GoodsBean implements MultiItemEntity {
            /**
             * g_id : 31
             * g_name : 麂皮绒橡胶瑜伽垫2
             * g_img : http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190727/39fa7b39c5dbfbca85e4aa0eee6f6c0efe7e56d5_400x473.jpg
             * g_point : 1
             * g_price : 100.00
             * is_dh : 1
             */

            private int g_id;
            private String g_name;
            private String g_img;
            private int g_point;
            private String g_price;
            private int is_dh;
            private int itemType;

            public int getG_id() {
                return g_id;
            }

            public void setG_id(int g_id) {
                this.g_id = g_id;
            }

            public String getG_name() {
                return g_name;
            }

            public void setG_name(String g_name) {
                this.g_name = g_name;
            }

            public String getG_img() {
                return g_img;
            }

            public void setG_img(String g_img) {
                this.g_img = g_img;
            }

            public int getG_point() {
                return g_point;
            }

            public void setG_point(int g_point) {
                this.g_point = g_point;
            }

            public String getG_price() {
                return g_price;
            }

            public void setG_price(String g_price) {
                this.g_price = g_price;
            }

            public int getIs_dh() {
                return is_dh;
            }

            public void setIs_dh(int is_dh) {
                this.is_dh = is_dh;
            }

            public void setItemType(int itemType) {
                this.itemType = itemType;
            }

            @Override
            public int getItemType() {
                return itemType;
            }
        }
    }
}
