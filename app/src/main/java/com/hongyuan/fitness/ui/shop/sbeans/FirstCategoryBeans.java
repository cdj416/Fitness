package com.hongyuan.fitness.ui.shop.sbeans;

import com.hongyuan.fitness.base.BaseBean;

import java.io.Serializable;
import java.util.List;

public class FirstCategoryBeans extends BaseBean implements Serializable {

    /**
     * data : {"list":[{"category_id":1,"category_name":"轻食代餐","parent_id":0,"level":1,"category_img":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190823/ffc37796c6e69e063f49ab4578e4b896848a2580_132x132.png","state":1,"banner_img":"","is_tj":0,"tj_type":0},{"category_id":2,"category_name":"运动装备","parent_id":0,"level":1,"category_img":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190823/88755edf9b6893629da4ab086898473ea9fba64a_132x132.png","state":1,"banner_img":"","is_tj":0,"tj_type":0},{"category_id":3,"category_name":"男女服饰","parent_id":0,"level":1,"category_img":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190823/18f59ae0846e326e95a41c2b37696b7a954c62b5_132x132.png","state":1,"banner_img":"","is_tj":0,"tj_type":0},{"category_id":4,"category_name":"智能设备","parent_id":0,"level":1,"category_img":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190823/25a8e3a565d004d80b6ddd8d81f4d26dd18fd4d2_132x132.png","state":1,"banner_img":"","is_tj":0,"tj_type":0}]}
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

        public static class ListBean implements Serializable {
            /**
             * category_id : 1
             * category_name : 轻食代餐
             * parent_id : 0
             * level : 1
             * category_img : http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190823/ffc37796c6e69e063f49ab4578e4b896848a2580_132x132.png
             * state : 1
             * banner_img :
             * is_tj : 0
             * tj_type : 0
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
            private boolean select;

            public boolean isSelect() {
                return select;
            }

            public void setSelect(boolean select) {
                this.select = select;
            }

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
        }
    }
}
