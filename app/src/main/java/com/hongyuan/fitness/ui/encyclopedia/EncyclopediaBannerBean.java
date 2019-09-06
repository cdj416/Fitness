package com.hongyuan.fitness.ui.encyclopedia;

import com.hongyuan.fitness.base.BaseBean;

import java.util.List;

public class EncyclopediaBannerBean extends BaseBean {


    /**
     * hasmore : false
     * curpage : 1
     * page_total : 1
     * data : {"list":[{"baike_id":52,"m_id":7,"baike_categoryid":1,"baike_name":"百科视频","baike_content":"小鸡的东西我自己都没了。我说过去的事情就这样一直一直陪伴着你回来我的心里永远不会发生这种问题","baike_type":2,"baike_state":1,"add_time":1562122922,"update_admin":1,"update_time":1562137963,"praise_num":1,"review_num":2,"lat":"","lng":"","os_id":0,"ft_id":7,"is_tj":1,"baike_img":"http://88php.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190703/c9e0d0ff1cc8cd3c8c27be38f42428e522b601ed_828x1472.jpg","m_mobile":"15220219931","mi_head":"","ft_name":"康复","bi":[{"bi_id":16,"file_src":"http://88php.oss-cn-hangzhou.aliyuncs.com/uplaod/video/20190703/7133c226705877104e42c15dbf883ada933c22aa.mp4","baike_id":52,"baike_type":2}],"add_date":"2019-07-03 11:02:02"}]}
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
             * baike_id : 52
             * m_id : 7
             * baike_categoryid : 1
             * baike_name : 百科视频
             * baike_content : 小鸡的东西我自己都没了。我说过去的事情就这样一直一直陪伴着你回来我的心里永远不会发生这种问题
             * baike_type : 2
             * baike_state : 1
             * add_time : 1562122922
             * update_admin : 1
             * update_time : 1562137963
             * praise_num : 1
             * review_num : 2
             * lat :
             * lng :
             * os_id : 0
             * ft_id : 7
             * is_tj : 1
             * baike_img : http://88php.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190703/c9e0d0ff1cc8cd3c8c27be38f42428e522b601ed_828x1472.jpg
             * m_mobile : 15220219931
             * mi_head :
             * ft_name : 康复
             * bi : [{"bi_id":16,"file_src":"http://88php.oss-cn-hangzhou.aliyuncs.com/uplaod/video/20190703/7133c226705877104e42c15dbf883ada933c22aa.mp4","baike_id":52,"baike_type":2}]
             * add_date : 2019-07-03 11:02:02
             */

            private int baike_id;
            private int m_id;
            private int baike_categoryid;
            private String baike_name;
            private String baike_content;
            private int baike_type;
            private int baike_state;
            private int add_time;
            private int update_admin;
            private int update_time;
            private int praise_num;
            private int review_num;
            private String lat;
            private String lng;
            private int os_id;
            private int ft_id;
            private int is_tj;
            private String baike_img;
            private String m_mobile;
            private String mi_head;
            private String ft_name;
            private String add_date;
            private List<BiBean> bi;

            public int getBaike_id() {
                return baike_id;
            }

            public void setBaike_id(int baike_id) {
                this.baike_id = baike_id;
            }

            public int getM_id() {
                return m_id;
            }

            public void setM_id(int m_id) {
                this.m_id = m_id;
            }

            public int getBaike_categoryid() {
                return baike_categoryid;
            }

            public void setBaike_categoryid(int baike_categoryid) {
                this.baike_categoryid = baike_categoryid;
            }

            public String getBaike_name() {
                return baike_name;
            }

            public void setBaike_name(String baike_name) {
                this.baike_name = baike_name;
            }

            public String getBaike_content() {
                return baike_content;
            }

            public void setBaike_content(String baike_content) {
                this.baike_content = baike_content;
            }

            public int getBaike_type() {
                return baike_type;
            }

            public void setBaike_type(int baike_type) {
                this.baike_type = baike_type;
            }

            public int getBaike_state() {
                return baike_state;
            }

            public void setBaike_state(int baike_state) {
                this.baike_state = baike_state;
            }

            public int getAdd_time() {
                return add_time;
            }

            public void setAdd_time(int add_time) {
                this.add_time = add_time;
            }

            public int getUpdate_admin() {
                return update_admin;
            }

            public void setUpdate_admin(int update_admin) {
                this.update_admin = update_admin;
            }

            public int getUpdate_time() {
                return update_time;
            }

            public void setUpdate_time(int update_time) {
                this.update_time = update_time;
            }

            public int getPraise_num() {
                return praise_num;
            }

            public void setPraise_num(int praise_num) {
                this.praise_num = praise_num;
            }

            public int getReview_num() {
                return review_num;
            }

            public void setReview_num(int review_num) {
                this.review_num = review_num;
            }

            public String getLat() {
                return lat;
            }

            public void setLat(String lat) {
                this.lat = lat;
            }

            public String getLng() {
                return lng;
            }

            public void setLng(String lng) {
                this.lng = lng;
            }

            public int getOs_id() {
                return os_id;
            }

            public void setOs_id(int os_id) {
                this.os_id = os_id;
            }

            public int getFt_id() {
                return ft_id;
            }

            public void setFt_id(int ft_id) {
                this.ft_id = ft_id;
            }

            public int getIs_tj() {
                return is_tj;
            }

            public void setIs_tj(int is_tj) {
                this.is_tj = is_tj;
            }

            public String getBaike_img() {
                return baike_img;
            }

            public void setBaike_img(String baike_img) {
                this.baike_img = baike_img;
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

            public String getFt_name() {
                return ft_name;
            }

            public void setFt_name(String ft_name) {
                this.ft_name = ft_name;
            }

            public String getAdd_date() {
                return add_date;
            }

            public void setAdd_date(String add_date) {
                this.add_date = add_date;
            }

            public List<BiBean> getBi() {
                return bi;
            }

            public void setBi(List<BiBean> bi) {
                this.bi = bi;
            }

            public static class BiBean {
                /**
                 * bi_id : 16
                 * file_src : http://88php.oss-cn-hangzhou.aliyuncs.com/uplaod/video/20190703/7133c226705877104e42c15dbf883ada933c22aa.mp4
                 * baike_id : 52
                 * baike_type : 2
                 */

                private int bi_id;
                private String file_src;
                private int baike_id;
                private int baike_type;

                public int getBi_id() {
                    return bi_id;
                }

                public void setBi_id(int bi_id) {
                    this.bi_id = bi_id;
                }

                public String getFile_src() {
                    return file_src;
                }

                public void setFile_src(String file_src) {
                    this.file_src = file_src;
                }

                public int getBaike_id() {
                    return baike_id;
                }

                public void setBaike_id(int baike_id) {
                    this.baike_id = baike_id;
                }

                public int getBaike_type() {
                    return baike_type;
                }

                public void setBaike_type(int baike_type) {
                    this.baike_type = baike_type;
                }
            }
        }
    }
}
