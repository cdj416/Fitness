package com.hongyuan.fitness.ui.shop.sbeans;

import com.hongyuan.fitness.base.BaseBean;

import java.util.List;

public class ShopCommentBeans extends BaseBean {


    /**
     * hasmore : false
     * curpage : 1
     * page_total : 1
     * data : {"all_num":9,"img_num":8,"append_num":3,"good_num":9,"normal_num":0,"bad_num":0,"list":[{"evaluation_id":13,"gp_id":142,"m_id":1,"evaluation_score":"5.0","is_have_img":1,"o_id":19234,"is_append":0,"is_anonymous":0,"evaluation_content":"","evaluation_time":1588569716,"m_name":"m88888","mi_head":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20200103/ae5e6dcc6581dc12efe73b6e6d7c757112e7dc9f_2436x2436.jpg","g_name":"乐拉泰国青柚金柚进口西施蜜柚2个4.5斤新鲜当季孕妇水果顺丰包邮","sku_names":"白色;L码","evaluation_img":[{"img_id":13,"img_url":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20200301/d6e8d9eed2598af883e92b1f6516a023c539d652_800x467.jpg","evaluation_id":13}],"evaluation_date":"2020-05-04 13:21:56","is_review":0,"append":[{"evaluation_id":15,"gp_id":142,"m_id":1,"evaluation_score":"5.0","is_have_img":1,"o_id":19234,"is_append":1,"is_anonymous":0,"evaluation_content":"","evaluation_time":1588571764,"m_name":"m88888","mi_head":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20200103/ae5e6dcc6581dc12efe73b6e6d7c757112e7dc9f_2436x2436.jpg","g_name":"乐拉泰国青柚金柚进口西施蜜柚2个4.5斤新鲜当季孕妇水果顺丰包邮","evaluation_img":[{"img_id":15,"img_url":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20200301/d6e8d9eed2598af883e92b1f6516a023c539d652_800x467.jpg","evaluation_id":15}],"evaluation_date":"2020-05-04 13:21:56"}]},{"evaluation_id":13,"gp_id":142,"m_id":1,"evaluation_score":"5.0","is_have_img":1,"o_id":19234,"is_append":0,"is_anonymous":0,"evaluation_content":"","evaluation_time":1588569716,"m_name":"m88888","mi_head":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20200103/ae5e6dcc6581dc12efe73b6e6d7c757112e7dc9f_2436x2436.jpg","g_name":"乐拉泰国青柚金柚进口西施蜜柚2个4.5斤新鲜当季孕妇水果顺丰包邮","sku_names":"蓝色;M码","evaluation_img":[{"img_id":13,"img_url":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20200301/d6e8d9eed2598af883e92b1f6516a023c539d652_800x467.jpg","evaluation_id":13}],"evaluation_date":"2020-05-04 13:21:56","is_review":0,"append":[{"evaluation_id":15,"gp_id":142,"m_id":1,"evaluation_score":"5.0","is_have_img":1,"o_id":19234,"is_append":1,"is_anonymous":0,"evaluation_content":"","evaluation_time":1588571764,"m_name":"m88888","mi_head":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20200103/ae5e6dcc6581dc12efe73b6e6d7c757112e7dc9f_2436x2436.jpg","g_name":"乐拉泰国青柚金柚进口西施蜜柚2个4.5斤新鲜当季孕妇水果顺丰包邮","evaluation_img":[{"img_id":15,"img_url":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20200301/d6e8d9eed2598af883e92b1f6516a023c539d652_800x467.jpg","evaluation_id":15}],"evaluation_date":"2020-05-04 13:21:56"}]}]}
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
        /**
         * all_num : 9
         * img_num : 8
         * append_num : 3
         * good_num : 9
         * normal_num : 0
         * bad_num : 0
         * list : [{"evaluation_id":13,"gp_id":142,"m_id":1,"evaluation_score":"5.0","is_have_img":1,"o_id":19234,"is_append":0,"is_anonymous":0,"evaluation_content":"","evaluation_time":1588569716,"m_name":"m88888","mi_head":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20200103/ae5e6dcc6581dc12efe73b6e6d7c757112e7dc9f_2436x2436.jpg","g_name":"乐拉泰国青柚金柚进口西施蜜柚2个4.5斤新鲜当季孕妇水果顺丰包邮","sku_names":"白色;L码","evaluation_img":[{"img_id":13,"img_url":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20200301/d6e8d9eed2598af883e92b1f6516a023c539d652_800x467.jpg","evaluation_id":13}],"evaluation_date":"2020-05-04 13:21:56","is_review":0,"append":[{"evaluation_id":15,"gp_id":142,"m_id":1,"evaluation_score":"5.0","is_have_img":1,"o_id":19234,"is_append":1,"is_anonymous":0,"evaluation_content":"","evaluation_time":1588571764,"m_name":"m88888","mi_head":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20200103/ae5e6dcc6581dc12efe73b6e6d7c757112e7dc9f_2436x2436.jpg","g_name":"乐拉泰国青柚金柚进口西施蜜柚2个4.5斤新鲜当季孕妇水果顺丰包邮","evaluation_img":[{"img_id":15,"img_url":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20200301/d6e8d9eed2598af883e92b1f6516a023c539d652_800x467.jpg","evaluation_id":15}],"evaluation_date":"2020-05-04 13:21:56"}]},{"evaluation_id":13,"gp_id":142,"m_id":1,"evaluation_score":"5.0","is_have_img":1,"o_id":19234,"is_append":0,"is_anonymous":0,"evaluation_content":"","evaluation_time":1588569716,"m_name":"m88888","mi_head":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20200103/ae5e6dcc6581dc12efe73b6e6d7c757112e7dc9f_2436x2436.jpg","g_name":"乐拉泰国青柚金柚进口西施蜜柚2个4.5斤新鲜当季孕妇水果顺丰包邮","sku_names":"蓝色;M码","evaluation_img":[{"img_id":13,"img_url":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20200301/d6e8d9eed2598af883e92b1f6516a023c539d652_800x467.jpg","evaluation_id":13}],"evaluation_date":"2020-05-04 13:21:56","is_review":0,"append":[{"evaluation_id":15,"gp_id":142,"m_id":1,"evaluation_score":"5.0","is_have_img":1,"o_id":19234,"is_append":1,"is_anonymous":0,"evaluation_content":"","evaluation_time":1588571764,"m_name":"m88888","mi_head":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20200103/ae5e6dcc6581dc12efe73b6e6d7c757112e7dc9f_2436x2436.jpg","g_name":"乐拉泰国青柚金柚进口西施蜜柚2个4.5斤新鲜当季孕妇水果顺丰包邮","evaluation_img":[{"img_id":15,"img_url":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20200301/d6e8d9eed2598af883e92b1f6516a023c539d652_800x467.jpg","evaluation_id":15}],"evaluation_date":"2020-05-04 13:21:56"}]}]
         */

        private int all_num;
        private int img_num;
        private int append_num;
        private int good_num;
        private int normal_num;
        private int bad_num;
        private List<ListBean> list;

        public int getAll_num() {
            return all_num;
        }

        public void setAll_num(int all_num) {
            this.all_num = all_num;
        }

        public int getImg_num() {
            return img_num;
        }

        public void setImg_num(int img_num) {
            this.img_num = img_num;
        }

        public int getAppend_num() {
            return append_num;
        }

        public void setAppend_num(int append_num) {
            this.append_num = append_num;
        }

        public int getGood_num() {
            return good_num;
        }

        public void setGood_num(int good_num) {
            this.good_num = good_num;
        }

        public int getNormal_num() {
            return normal_num;
        }

        public void setNormal_num(int normal_num) {
            this.normal_num = normal_num;
        }

        public int getBad_num() {
            return bad_num;
        }

        public void setBad_num(int bad_num) {
            this.bad_num = bad_num;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * evaluation_id : 13
             * gp_id : 142
             * m_id : 1
             * evaluation_score : 5.0
             * is_have_img : 1
             * o_id : 19234
             * is_append : 0
             * is_anonymous : 0
             * evaluation_content :
             * evaluation_time : 1588569716
             * m_name : m88888
             * mi_head : http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20200103/ae5e6dcc6581dc12efe73b6e6d7c757112e7dc9f_2436x2436.jpg
             * g_name : 乐拉泰国青柚金柚进口西施蜜柚2个4.5斤新鲜当季孕妇水果顺丰包邮
             * sku_names : 白色;L码
             * evaluation_img : [{"img_id":13,"img_url":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20200301/d6e8d9eed2598af883e92b1f6516a023c539d652_800x467.jpg","evaluation_id":13}]
             * evaluation_date : 2020-05-04 13:21:56
             * is_review : 0
             * append : [{"evaluation_id":15,"gp_id":142,"m_id":1,"evaluation_score":"5.0","is_have_img":1,"o_id":19234,"is_append":1,"is_anonymous":0,"evaluation_content":"","evaluation_time":1588571764,"m_name":"m88888","mi_head":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20200103/ae5e6dcc6581dc12efe73b6e6d7c757112e7dc9f_2436x2436.jpg","g_name":"乐拉泰国青柚金柚进口西施蜜柚2个4.5斤新鲜当季孕妇水果顺丰包邮","evaluation_img":[{"img_id":15,"img_url":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20200301/d6e8d9eed2598af883e92b1f6516a023c539d652_800x467.jpg","evaluation_id":15}],"evaluation_date":"2020-05-04 13:21:56"}]
             */

            private int evaluation_id;
            private int gp_id;
            private int m_id;
            private String evaluation_score;
            private int is_have_img;
            private int o_id;
            private int is_append;
            private int is_anonymous;
            private String evaluation_content;
            private int evaluation_time;
            private String m_name;
            private String mi_head;
            private String g_name;
            private String sku_names;
            private String evaluation_date;
            private int is_review;
            private List<EvaluationImgBean> evaluation_img;
            private List<AppendBean> append;

            public int getEvaluation_id() {
                return evaluation_id;
            }

            public void setEvaluation_id(int evaluation_id) {
                this.evaluation_id = evaluation_id;
            }

            public int getGp_id() {
                return gp_id;
            }

            public void setGp_id(int gp_id) {
                this.gp_id = gp_id;
            }

            public int getM_id() {
                return m_id;
            }

            public void setM_id(int m_id) {
                this.m_id = m_id;
            }

            public String getEvaluation_score() {
                return evaluation_score;
            }

            public void setEvaluation_score(String evaluation_score) {
                this.evaluation_score = evaluation_score;
            }

            public int getIs_have_img() {
                return is_have_img;
            }

            public void setIs_have_img(int is_have_img) {
                this.is_have_img = is_have_img;
            }

            public int getO_id() {
                return o_id;
            }

            public void setO_id(int o_id) {
                this.o_id = o_id;
            }

            public int getIs_append() {
                return is_append;
            }

            public void setIs_append(int is_append) {
                this.is_append = is_append;
            }

            public int getIs_anonymous() {
                return is_anonymous;
            }

            public void setIs_anonymous(int is_anonymous) {
                this.is_anonymous = is_anonymous;
            }

            public String getEvaluation_content() {
                return evaluation_content;
            }

            public void setEvaluation_content(String evaluation_content) {
                this.evaluation_content = evaluation_content;
            }

            public int getEvaluation_time() {
                return evaluation_time;
            }

            public void setEvaluation_time(int evaluation_time) {
                this.evaluation_time = evaluation_time;
            }

            public String getM_name() {
                return m_name;
            }

            public void setM_name(String m_name) {
                this.m_name = m_name;
            }

            public String getMi_head() {
                return mi_head;
            }

            public void setMi_head(String mi_head) {
                this.mi_head = mi_head;
            }

            public String getG_name() {
                return g_name;
            }

            public void setG_name(String g_name) {
                this.g_name = g_name;
            }

            public String getSku_names() {
                return sku_names;
            }

            public void setSku_names(String sku_names) {
                this.sku_names = sku_names;
            }

            public String getEvaluation_date() {
                return evaluation_date;
            }

            public void setEvaluation_date(String evaluation_date) {
                this.evaluation_date = evaluation_date;
            }

            public int getIs_review() {
                return is_review;
            }

            public void setIs_review(int is_review) {
                this.is_review = is_review;
            }

            public List<EvaluationImgBean> getEvaluation_img() {
                return evaluation_img;
            }

            public void setEvaluation_img(List<EvaluationImgBean> evaluation_img) {
                this.evaluation_img = evaluation_img;
            }

            public List<AppendBean> getAppend() {
                return append;
            }

            public void setAppend(List<AppendBean> append) {
                this.append = append;
            }

            public static class EvaluationImgBean {
                /**
                 * img_id : 13
                 * img_url : http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20200301/d6e8d9eed2598af883e92b1f6516a023c539d652_800x467.jpg
                 * evaluation_id : 13
                 */

                private int img_id;
                private String img_url;
                private int evaluation_id;

                public int getImg_id() {
                    return img_id;
                }

                public void setImg_id(int img_id) {
                    this.img_id = img_id;
                }

                public String getImg_url() {
                    return img_url;
                }

                public void setImg_url(String img_url) {
                    this.img_url = img_url;
                }

                public int getEvaluation_id() {
                    return evaluation_id;
                }

                public void setEvaluation_id(int evaluation_id) {
                    this.evaluation_id = evaluation_id;
                }
            }

            public static class AppendBean {
                /**
                 * evaluation_id : 15
                 * gp_id : 142
                 * m_id : 1
                 * evaluation_score : 5.0
                 * is_have_img : 1
                 * o_id : 19234
                 * is_append : 1
                 * is_anonymous : 0
                 * evaluation_content :
                 * evaluation_time : 1588571764
                 * m_name : m88888
                 * mi_head : http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20200103/ae5e6dcc6581dc12efe73b6e6d7c757112e7dc9f_2436x2436.jpg
                 * g_name : 乐拉泰国青柚金柚进口西施蜜柚2个4.5斤新鲜当季孕妇水果顺丰包邮
                 * evaluation_img : [{"img_id":15,"img_url":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20200301/d6e8d9eed2598af883e92b1f6516a023c539d652_800x467.jpg","evaluation_id":15}]
                 * evaluation_date : 2020-05-04 13:21:56
                 */

                private int evaluation_id;
                private int gp_id;
                private int m_id;
                private String evaluation_score;
                private int is_have_img;
                private int o_id;
                private int is_append;
                private int is_anonymous;
                private String evaluation_content;
                private int evaluation_time;
                private String m_name;
                private String mi_head;
                private String g_name;
                private String evaluation_date;
                private List<EvaluationImgBeanX> evaluation_img;

                public int getEvaluation_id() {
                    return evaluation_id;
                }

                public void setEvaluation_id(int evaluation_id) {
                    this.evaluation_id = evaluation_id;
                }

                public int getGp_id() {
                    return gp_id;
                }

                public void setGp_id(int gp_id) {
                    this.gp_id = gp_id;
                }

                public int getM_id() {
                    return m_id;
                }

                public void setM_id(int m_id) {
                    this.m_id = m_id;
                }

                public String getEvaluation_score() {
                    return evaluation_score;
                }

                public void setEvaluation_score(String evaluation_score) {
                    this.evaluation_score = evaluation_score;
                }

                public int getIs_have_img() {
                    return is_have_img;
                }

                public void setIs_have_img(int is_have_img) {
                    this.is_have_img = is_have_img;
                }

                public int getO_id() {
                    return o_id;
                }

                public void setO_id(int o_id) {
                    this.o_id = o_id;
                }

                public int getIs_append() {
                    return is_append;
                }

                public void setIs_append(int is_append) {
                    this.is_append = is_append;
                }

                public int getIs_anonymous() {
                    return is_anonymous;
                }

                public void setIs_anonymous(int is_anonymous) {
                    this.is_anonymous = is_anonymous;
                }

                public String getEvaluation_content() {
                    return evaluation_content;
                }

                public void setEvaluation_content(String evaluation_content) {
                    this.evaluation_content = evaluation_content;
                }

                public int getEvaluation_time() {
                    return evaluation_time;
                }

                public void setEvaluation_time(int evaluation_time) {
                    this.evaluation_time = evaluation_time;
                }

                public String getM_name() {
                    return m_name;
                }

                public void setM_name(String m_name) {
                    this.m_name = m_name;
                }

                public String getMi_head() {
                    return mi_head;
                }

                public void setMi_head(String mi_head) {
                    this.mi_head = mi_head;
                }

                public String getG_name() {
                    return g_name;
                }

                public void setG_name(String g_name) {
                    this.g_name = g_name;
                }

                public String getEvaluation_date() {
                    return evaluation_date;
                }

                public void setEvaluation_date(String evaluation_date) {
                    this.evaluation_date = evaluation_date;
                }

                public List<EvaluationImgBeanX> getEvaluation_img() {
                    return evaluation_img;
                }

                public void setEvaluation_img(List<EvaluationImgBeanX> evaluation_img) {
                    this.evaluation_img = evaluation_img;
                }

                public static class EvaluationImgBeanX {
                    /**
                     * img_id : 15
                     * img_url : http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20200301/d6e8d9eed2598af883e92b1f6516a023c539d652_800x467.jpg
                     * evaluation_id : 15
                     */

                    private int img_id;
                    private String img_url;
                    private int evaluation_id;

                    public int getImg_id() {
                        return img_id;
                    }

                    public void setImg_id(int img_id) {
                        this.img_id = img_id;
                    }

                    public String getImg_url() {
                        return img_url;
                    }

                    public void setImg_url(String img_url) {
                        this.img_url = img_url;
                    }

                    public int getEvaluation_id() {
                        return evaluation_id;
                    }

                    public void setEvaluation_id(int evaluation_id) {
                        this.evaluation_id = evaluation_id;
                    }
                }
            }
        }
    }
}
