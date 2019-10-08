package com.hongyuan.fitness.ui.find.circle.circle_detail;

import com.hongyuan.fitness.base.BaseBean;

import java.util.List;

public class CircleDetailsBeans extends BaseBean {


    /**
     * data : {"category_id":6,"category_name":"锻炼","parent_id":2,"category_img":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190910/f65ff616e52600e44eba3783619a7c36faaf8d9c_200x142.jpg","category_note":"让我们在减肥的路上努力前行，总有一天会变美化的自己","count":5,"head":["http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190919/72bb4c54c7af946c80276ac0a8d2895c654ad0f6_2208x2208.jpg","http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190918/604e8db72a0562e17534d50a637fd75110e5cd41_1179x977.png"]}
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
         * category_id : 6
         * category_name : 锻炼
         * parent_id : 2
         * category_img : http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190910/f65ff616e52600e44eba3783619a7c36faaf8d9c_200x142.jpg
         * category_note : 让我们在减肥的路上努力前行，总有一天会变美化的自己
         * count : 5
         * head : ["http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190919/72bb4c54c7af946c80276ac0a8d2895c654ad0f6_2208x2208.jpg","http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190918/604e8db72a0562e17534d50a637fd75110e5cd41_1179x977.png"]
         */

        private int category_id;
        private String category_name;
        private int parent_id;
        private String category_img;
        private String category_note;
        private int count;
        private List<String> head;

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

        public String getCategory_img() {
            return category_img;
        }

        public void setCategory_img(String category_img) {
            this.category_img = category_img;
        }

        public String getCategory_note() {
            return category_note;
        }

        public void setCategory_note(String category_note) {
            this.category_note = category_note;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public List<String> getHead() {
            return head;
        }

        public void setHead(List<String> head) {
            this.head = head;
        }
    }
}
