package com.hongyuan.fitness.ui.shop.sbeans;

import com.hongyuan.fitness.base.BaseBean;

import java.util.ArrayList;
import java.util.List;

public class TuiJianCetegoryBeans extends BaseBean {

    /**
     * data : {"zc":[{"category_id":8,"category_name":"速干衣裤","parent_id":6,"level":3,"category_img":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20200519/bd41aa1b35d7c14f05c258ebdd9e3b392e990a5b_132x132.png","state":1,"banner_img":"","is_tj":1,"tj_type":1,"category_code":""},{"category_id":10,"category_name":"运动鞋","parent_id":5,"level":3,"category_img":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20200519/c7a182f33283cdfb26a500dcdc479b45f574bb48_132x132.png","state":1,"banner_img":"","is_tj":1,"tj_type":1,"category_code":""},{"category_id":12,"category_name":"箱包","parent_id":5,"level":3,"category_img":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20200519/c2b9fd6e4e55db8aec00971da26fb49a2b6bbb0e_132x132.png","state":1,"banner_img":"","is_tj":1,"tj_type":1,"category_code":""}],"rm":[{"category_id":7,"category_name":"户外风衣","parent_id":5,"level":3,"category_img":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20200519/0cd8c5c8f600d553c0dbc2aaa54afb2cb307bedd_132x132.png","state":1,"banner_img":"","is_tj":1,"tj_type":2,"category_code":""},{"category_id":13,"category_name":"毛巾","parent_id":5,"level":3,"category_img":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20200519/9cee491a172d8e6660a6eddb2b4c36e802668abc_132x132.png","state":1,"banner_img":"","is_tj":1,"tj_type":2,"category_code":""}]}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private List<ShopMenuBeans.DataBean> tuiList = new ArrayList<>();


        public List<ShopMenuBeans.DataBean> getTuiList(){
            ShopMenuBeans.DataBean sdBean = new ShopMenuBeans.DataBean();
            sdBean.setCategory_id(-1);
            sdBean.setCategory_name("专场分类");
            List<ShopMenuBeans.DataBean.SecondBean> zcBeanList = new ArrayList<>();
            for(ZcBean zcBean : zc){
                ShopMenuBeans.DataBean.SecondBean secondBean = new ShopMenuBeans.DataBean.SecondBean();
                secondBean.setCategory_id(zcBean.getCategory_id());
                secondBean.setCategory_img(zcBean.getCategory_img());
                secondBean.setCategory_name(zcBean.getCategory_name());
                zcBeanList.add(secondBean);
            }
            sdBean.setSecond(zcBeanList);
            tuiList.add(sdBean);


            ShopMenuBeans.DataBean rmBean = new ShopMenuBeans.DataBean();
            rmBean.setCategory_id(-2);
            rmBean.setCategory_name("热门推荐");
            List<ShopMenuBeans.DataBean.SecondBean> rmBeanList = new ArrayList<>();
            for(RmBean bean : rm){
                ShopMenuBeans.DataBean.SecondBean secondBean = new ShopMenuBeans.DataBean.SecondBean();
                secondBean.setCategory_id(bean.getCategory_id());
                secondBean.setCategory_img(bean.getCategory_img());
                secondBean.setCategory_name(bean.getCategory_name());
                rmBeanList.add(secondBean);
            }
            rmBean.setSecond(rmBeanList);
            tuiList.add(rmBean);


            return tuiList;

        }



        /***************************************************组装数据*********************************/

        private List<ZcBean> zc;
        private List<RmBean> rm;

        public List<ZcBean> getZc() {
            return zc;
        }

        public void setZc(List<ZcBean> zc) {
            this.zc = zc;
        }

        public List<RmBean> getRm() {
            return rm;
        }

        public void setRm(List<RmBean> rm) {
            this.rm = rm;
        }

        public static class ZcBean {
            /**
             * category_id : 8
             * category_name : 速干衣裤
             * parent_id : 6
             * level : 3
             * category_img : http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20200519/bd41aa1b35d7c14f05c258ebdd9e3b392e990a5b_132x132.png
             * state : 1
             * banner_img :
             * is_tj : 1
             * tj_type : 1
             * category_code :
             */

            private int category_id;
            private String category_name;
            private int parent_id;
            private int level;
            private String category_img;
            private int state;
            private String banner_img;
            private int is_tj;
            private int tj_type;
            private String category_code;

            public int getCategory_id() {
                return category_id;
            }

            public void setCategory_id(int category_id) {
                this.category_id = category_id;
            }

            public String getCategory_name() {
                return category_name;
            }

            public void setCategory_name(String category_name) {
                this.category_name = category_name;
            }

            public int getParent_id() {
                return parent_id;
            }

            public void setParent_id(int parent_id) {
                this.parent_id = parent_id;
            }

            public int getLevel() {
                return level;
            }

            public void setLevel(int level) {
                this.level = level;
            }

            public String getCategory_img() {
                return category_img;
            }

            public void setCategory_img(String category_img) {
                this.category_img = category_img;
            }

            public int getState() {
                return state;
            }

            public void setState(int state) {
                this.state = state;
            }

            public String getBanner_img() {
                return banner_img;
            }

            public void setBanner_img(String banner_img) {
                this.banner_img = banner_img;
            }

            public int getIs_tj() {
                return is_tj;
            }

            public void setIs_tj(int is_tj) {
                this.is_tj = is_tj;
            }

            public int getTj_type() {
                return tj_type;
            }

            public void setTj_type(int tj_type) {
                this.tj_type = tj_type;
            }

            public String getCategory_code() {
                return category_code;
            }

            public void setCategory_code(String category_code) {
                this.category_code = category_code;
            }
        }

        public static class RmBean {
            /**
             * category_id : 7
             * category_name : 户外风衣
             * parent_id : 5
             * level : 3
             * category_img : http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20200519/0cd8c5c8f600d553c0dbc2aaa54afb2cb307bedd_132x132.png
             * state : 1
             * banner_img :
             * is_tj : 1
             * tj_type : 2
             * category_code :
             */

            private int category_id;
            private String category_name;
            private int parent_id;
            private int level;
            private String category_img;
            private int state;
            private String banner_img;
            private int is_tj;
            private int tj_type;
            private String category_code;

            public int getCategory_id() {
                return category_id;
            }

            public void setCategory_id(int category_id) {
                this.category_id = category_id;
            }

            public String getCategory_name() {
                return category_name;
            }

            public void setCategory_name(String category_name) {
                this.category_name = category_name;
            }

            public int getParent_id() {
                return parent_id;
            }

            public void setParent_id(int parent_id) {
                this.parent_id = parent_id;
            }

            public int getLevel() {
                return level;
            }

            public void setLevel(int level) {
                this.level = level;
            }

            public String getCategory_img() {
                return category_img;
            }

            public void setCategory_img(String category_img) {
                this.category_img = category_img;
            }

            public int getState() {
                return state;
            }

            public void setState(int state) {
                this.state = state;
            }

            public String getBanner_img() {
                return banner_img;
            }

            public void setBanner_img(String banner_img) {
                this.banner_img = banner_img;
            }

            public int getIs_tj() {
                return is_tj;
            }

            public void setIs_tj(int is_tj) {
                this.is_tj = is_tj;
            }

            public int getTj_type() {
                return tj_type;
            }

            public void setTj_type(int tj_type) {
                this.tj_type = tj_type;
            }

            public String getCategory_code() {
                return category_code;
            }

            public void setCategory_code(String category_code) {
                this.category_code = category_code;
            }
        }
    }
}
