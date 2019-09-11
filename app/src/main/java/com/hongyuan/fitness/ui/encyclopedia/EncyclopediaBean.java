package com.hongyuan.fitness.ui.encyclopedia;

import com.hongyuan.fitness.base.BaseBean;

import java.util.List;

public class EncyclopediaBean extends BaseBean {

    /**
     * hasmore : false
     * curpage : 1
     * page_total : 1
     * data : {"list":[{"article_id":10,"article_title":"9.5富文本测试标题1","article_brief":"9.5富文本测试简介19.5富文本测试简介19.5富文本测试简介1","article_img":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190905/ef4c04bb17a56fae1e42c1ba252b2f09deedbcdf_960x1707.jpg","article_video":"","type":1,"state":1,"review_num":0,"praise_num":0,"fx_num":0,"add_time":1567665909,"m_id":1,"article_content":"<p><span style=\"font-size: x-small; color: rgb(157, 157, 157);\">9.5富文本测试详情19.5富文本测试详情1<\/span><\/p><p><span style=\"font-size: x-small; color: rgb(157, 157, 157);\"><br><\/span><\/p><p><img src=\"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190905/e5c9e02cf1dd55e5cfea3bc0a60925303a60ed6b_960x1506.jpg\" style=\"max-width:100%;\"><span style=\"font-size: x-small; color: rgb(157, 157, 157);\"><br><\/span><\/p><p>9.5富文本测试详情1&nbsp;<span style=\"font-size: 14px;\">9.5富文本测试详情1<\/span><span style=\"font-size: 14px;\">9.5富文本测试详情1<\/span><span style=\"font-size: 14px;\">9.5富文本测试详情1<\/span><span style=\"font-size: 14px;\">9.5富文本测试详情1<\/span><span style=\"font-size: 14px;\">9.5富文本测试详情1<\/span><span style=\"font-size: 14px;\">9.5富文本测试详情1<\/span><span style=\"font-size: 14px;\">9.5富文本测试详情1<\/span><span style=\"font-size: 14px;\">9.5富文本测试详情1<\/span><span style=\"font-size: 14px;\">9.5富文本测试详情1<\/span><span style=\"font-size: 14px;\">9.5富文<\/span><span style=\"font-size: 14px;\">9.5富文本测试详情1<\/span><span style=\"font-size: 14px;\">9.5富文本测试详情1<\/span><span style=\"font-size: 14px;\">9.5富文本测试详情1<\/span><span style=\"font-size: 14px;\">本测试详情1<\/span><span style=\"font-size: 14px;\">9.5富文本测试详情1<\/span><span style=\"font-size: 14px;\">&nbsp;<\/span><\/p><p><span style=\"font-size: 14px;\"><br><\/span><\/p><p><span style=\"font-size: 14px;\"><br><\/span><\/p>","baike_categoryid":3,"m_mobile":"18621854159","mi_head":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190820/99960903c5df39754e5e150f10467bc39fe09364_2436x2436.jpg","baike_category_name":"减肥","add_date":"2019-09-05 14:45:09","is_praise":0}]}
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
             * article_id : 10
             * article_title : 9.5富文本测试标题1
             * article_brief : 9.5富文本测试简介19.5富文本测试简介19.5富文本测试简介1
             * article_img : http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190905/ef4c04bb17a56fae1e42c1ba252b2f09deedbcdf_960x1707.jpg
             * article_video :
             * type : 1
             * state : 1
             * review_num : 0
             * praise_num : 0
             * fx_num : 0
             * add_time : 1567665909
             * m_id : 1
             * article_content : <p><span style="font-size: x-small; color: rgb(157, 157, 157);">9.5富文本测试详情19.5富文本测试详情1</span></p><p><span style="font-size: x-small; color: rgb(157, 157, 157);"><br></span></p><p><img src="http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190905/e5c9e02cf1dd55e5cfea3bc0a60925303a60ed6b_960x1506.jpg" style="max-width:100%;"><span style="font-size: x-small; color: rgb(157, 157, 157);"><br></span></p><p>9.5富文本测试详情1&nbsp;<span style="font-size: 14px;">9.5富文本测试详情1</span><span style="font-size: 14px;">9.5富文本测试详情1</span><span style="font-size: 14px;">9.5富文本测试详情1</span><span style="font-size: 14px;">9.5富文本测试详情1</span><span style="font-size: 14px;">9.5富文本测试详情1</span><span style="font-size: 14px;">9.5富文本测试详情1</span><span style="font-size: 14px;">9.5富文本测试详情1</span><span style="font-size: 14px;">9.5富文本测试详情1</span><span style="font-size: 14px;">9.5富文本测试详情1</span><span style="font-size: 14px;">9.5富文</span><span style="font-size: 14px;">9.5富文本测试详情1</span><span style="font-size: 14px;">9.5富文本测试详情1</span><span style="font-size: 14px;">9.5富文本测试详情1</span><span style="font-size: 14px;">本测试详情1</span><span style="font-size: 14px;">9.5富文本测试详情1</span><span style="font-size: 14px;">&nbsp;</span></p><p><span style="font-size: 14px;"><br></span></p><p><span style="font-size: 14px;"><br></span></p>
             * baike_categoryid : 3
             * m_mobile : 18621854159
             * mi_head : http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190820/99960903c5df39754e5e150f10467bc39fe09364_2436x2436.jpg
             * baike_category_name : 减肥
             * add_date : 2019-09-05 14:45:09
             * is_praise : 0
             */

            private int article_id;
            private String article_title;
            private String article_brief;
            private String article_img;
            private String article_video;
            private int type;
            private int state;
            private int review_num;
            private int praise_num;
            private int fx_num;
            private int add_time;
            private int m_id;
            private String article_content;
            private int baike_categoryid;
            private String m_mobile;
            private String mi_head;
            private String baike_category_name;
            private String add_date;
            private int is_praise;
            private int numMark;

            public int getNumMark() {
                return numMark;
            }

            public void setNumMark(int numMark) {
                this.numMark = numMark;
            }

            public int getArticle_id() {
                return article_id;
            }

            public void setArticle_id(int article_id) {
                this.article_id = article_id;
            }

            public String getArticle_title() {
                return article_title;
            }

            public void setArticle_title(String article_title) {
                this.article_title = article_title;
            }

            public String getArticle_brief() {
                return article_brief;
            }

            public void setArticle_brief(String article_brief) {
                this.article_brief = article_brief;
            }

            public String getArticle_img() {
                return article_img;
            }

            public void setArticle_img(String article_img) {
                this.article_img = article_img;
            }

            public String getArticle_video() {
                return article_video;
            }

            public void setArticle_video(String article_video) {
                this.article_video = article_video;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public int getState() {
                return state;
            }

            public void setState(int state) {
                this.state = state;
            }

            public int getReview_num() {
                return review_num;
            }

            public void setReview_num(int review_num) {
                this.review_num = review_num;
            }

            public int getPraise_num() {
                return praise_num;
            }

            public void setPraise_num(int praise_num) {
                this.praise_num = praise_num;
            }

            public int getFx_num() {
                return fx_num;
            }

            public void setFx_num(int fx_num) {
                this.fx_num = fx_num;
            }

            public int getAdd_time() {
                return add_time;
            }

            public void setAdd_time(int add_time) {
                this.add_time = add_time;
            }

            public int getM_id() {
                return m_id;
            }

            public void setM_id(int m_id) {
                this.m_id = m_id;
            }

            public String getArticle_content() {
                return article_content;
            }

            public void setArticle_content(String article_content) {
                this.article_content = article_content;
            }

            public int getBaike_categoryid() {
                return baike_categoryid;
            }

            public void setBaike_categoryid(int baike_categoryid) {
                this.baike_categoryid = baike_categoryid;
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

            public String getBaike_category_name() {
                return baike_category_name;
            }

            public void setBaike_category_name(String baike_category_name) {
                this.baike_category_name = baike_category_name;
            }

            public String getAdd_date() {
                return add_date;
            }

            public void setAdd_date(String add_date) {
                this.add_date = add_date;
            }

            public int getIs_praise() {
                return is_praise;
            }

            public void setIs_praise(int is_praise) {
                this.is_praise = is_praise;
            }
        }
    }
}
