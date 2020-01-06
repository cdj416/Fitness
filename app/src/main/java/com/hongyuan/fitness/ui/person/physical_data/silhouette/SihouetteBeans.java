package com.hongyuan.fitness.ui.person.physical_data.silhouette;

import com.hongyuan.fitness.base.BaseBean;

import java.util.List;

public class SihouetteBeans extends BaseBean {

    /**
     * hasmore : false
     * curpage : 1
     * page_total : 1
     * data : {"list":[{"img_date":"2003-05-30","imgs":[{"id":"42","img":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20191205/b909e623add00547ce3f0ea3d2ac2411ec239b05_479x800.jpg","add_date":"2003-05-30"}]},{"img_date":"2019-06-04","imgs":[{"id":"49","img":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20191206/34bc337123941ea5e0f15899b2d467622aea5980_533x800.jpg","add_date":"2019-06-04"},{"id":"46","img":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20191205/882f728aae6f1edad49eb20e100daf3fd0059741_1080x720.jpg","add_date":"2019-06-04"},{"id":"45","img":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20191205/ff34cab087528a31fabd861c07e3c430d94e403b_479x800.jpg","add_date":"2019-06-04"},{"id":"43","img":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20191205/df8aeae3d2d3825e262a12ebd5f8d00367e9a3e0_581x800.jpg","add_date":"2019-06-04"}]},{"img_date":"2019-11-28","imgs":[{"id":"41","img":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20191205/6f247274f8c9dfaafc48e634077f01fc26c60b5d_4032x3024.jpg","add_date":"2019-11-28"},{"id":"40","img":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20191205/fe3f000623a6d594bed1a1d10c21c85adf2487c8_796x796.jpg","add_date":"2019-11-28"}]},{"img_date":"2019-12-05","imgs":[{"id":"44","img":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20191205/0b1b5d10e91c8eea03932da54bef4b66d79fd5fd_1560x2080.JPEG","add_date":"2019-12-05"},{"id":"47","img":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20191205/9720d3e2c37c24825370ab28e06145d5dcbc66ec_536x956.jpg","add_date":"2019-12-05"},{"id":"48","img":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20191205/75fe8dea11b1f5b754557d83afed97a2eecc8a75_1080x1920.jpg","add_date":"2019-12-05"}]}]}
     */

    private boolean hasmore;
    private int curpage;
    private int page_total;
    private DataBean data;

    public boolean isHasmore() {
        return hasmore;
    }

    public void setHasmore(boolean hasmore) {
        this.hasmore = hasmore;
    }

    public int getCurpage() {
        return curpage;
    }

    public void setCurpage(int curpage) {
        this.curpage = curpage;
    }

    public int getPage_total() {
        return page_total;
    }

    public void setPage_total(int page_total) {
        this.page_total = page_total;
    }

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
             * img_date : 2003-05-30
             * imgs : [{"id":"42","img":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20191205/b909e623add00547ce3f0ea3d2ac2411ec239b05_479x800.jpg","add_date":"2003-05-30"}]
             */

            private String img_date;
            private List<ImgsBean> imgs;

            public String getImg_date() {
                return img_date;
            }

            public void setImg_date(String img_date) {
                this.img_date = img_date;
            }

            public List<ImgsBean> getImgs() {
                return imgs;
            }

            public void setImgs(List<ImgsBean> imgs) {
                this.imgs = imgs;
            }

            public static class ImgsBean {
                /**
                 * id : 42
                 * img : http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20191205/b909e623add00547ce3f0ea3d2ac2411ec239b05_479x800.jpg
                 * add_date : 2003-05-30
                 */

                private String id;
                private String img;
                private String add_date;

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public String getImg() {
                    return img;
                }

                public void setImg(String img) {
                    this.img = img;
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
