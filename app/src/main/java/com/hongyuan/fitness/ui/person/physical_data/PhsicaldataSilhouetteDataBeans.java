package com.hongyuan.fitness.ui.person.physical_data;

import com.hongyuan.fitness.base.BaseBean;

import java.util.List;

public class PhsicaldataSilhouetteDataBeans extends BaseBean {

    /**
     * data : {"list":{"img":[{"id":40,"m_id":13,"add_time":1574870400,"img":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20191205/fe3f000623a6d594bed1a1d10c21c85adf2487c8_796x796.jpg","is_del":2,"add_date":"2019-11-28"}],"img_num":1}}
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
         * list : {"img":[{"id":40,"m_id":13,"add_time":1574870400,"img":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20191205/fe3f000623a6d594bed1a1d10c21c85adf2487c8_796x796.jpg","is_del":2,"add_date":"2019-11-28"}],"img_num":1}
         */

        private ListBean list;

        public ListBean getList() {
            return list;
        }

        public void setList(ListBean list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * img : [{"id":40,"m_id":13,"add_time":1574870400,"img":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20191205/fe3f000623a6d594bed1a1d10c21c85adf2487c8_796x796.jpg","is_del":2,"add_date":"2019-11-28"}]
             * img_num : 1
             */

            private int img_num;
            private List<ImgBean> img;

            public int getImg_num() {
                return img_num;
            }

            public void setImg_num(int img_num) {
                this.img_num = img_num;
            }

            public List<ImgBean> getImg() {
                return img;
            }

            public void setImg(List<ImgBean> img) {
                this.img = img;
            }

            public static class ImgBean {
                /**
                 * id : 40
                 * m_id : 13
                 * add_time : 1574870400
                 * img : http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20191205/fe3f000623a6d594bed1a1d10c21c85adf2487c8_796x796.jpg
                 * is_del : 2
                 * add_date : 2019-11-28
                 */

                private int id;
                private int m_id;
                private int add_time;
                private String img;
                private int is_del;
                private String add_date;

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public int getM_id() {
                    return m_id;
                }

                public void setM_id(int m_id) {
                    this.m_id = m_id;
                }

                public int getAdd_time() {
                    return add_time;
                }

                public void setAdd_time(int add_time) {
                    this.add_time = add_time;
                }

                public String getImg() {
                    return img;
                }

                public void setImg(String img) {
                    this.img = img;
                }

                public int getIs_del() {
                    return is_del;
                }

                public void setIs_del(int is_del) {
                    this.is_del = is_del;
                }

                public String getAdd_date() {
                    return add_date;
                }

                public void setAdd_date(String add_date) {
                    this.add_date = add_date;
                }
            }
        }
    }
}
