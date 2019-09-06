package com.hongyuan.fitness.ui.encyclopedia.encyclopedia_detail;

import com.hongyuan.fitness.base.BaseBean;

import java.io.Serializable;
import java.util.List;

public class EncyclopediaDetailCommentBean extends BaseBean implements Serializable {


    /**
     * hasmore : false
     * curpage : 1
     * page_total : 1
     * data : {"list":[{"br_id":69,"baike_id":52,"br_id_father":0,"m_id":7,"to_mid":0,"br_content":"不能容忍的是","br_state":0,"add_time":1562125229,"update_admin":0,"update_time":0,"reply_num":4,"praise_num":0,"first_br_id":0,"m_name":"小鸡","m_mobile":"15220219931","mi_head":"http://88php.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190620/9cfc42dc3a89089458a5932eb2c0cb74a4ec5670_600x338.jpg","baike_content":"小鸡的东西我自己都没了。我说过去的事情就这样一直一直陪伴着你回来我的心里永远不会发生这种问题","add_date":"2019-07-03 11:40:29","is_praise":0,"list_s":[{"br_id":93,"baike_id":52,"br_id_father":69,"m_id":7,"to_mid":7,"br_content":"打雷下雨模型","br_state":0,"add_time":1562989386,"update_admin":0,"update_time":0,"reply_num":0,"praise_num":0,"first_br_id":69,"m_name":"小鸡","m_mobile":"15220219931","mi_head":"http://88php.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190620/9cfc42dc3a89089458a5932eb2c0cb74a4ec5670_600x338.jpg","baike_content":"小鸡的东西我自己都没了。我说过去的事情就这样一直一直陪伴着你回来我的心里永远不会发生这种问题","add_date":"2019-07-13 11:43:06","is_praise":0,"to_m_name":"小鸡"},{"br_id":76,"baike_id":52,"br_id_father":69,"m_id":7,"to_mid":7,"br_content":"一次","br_state":0,"add_time":1562140789,"update_admin":0,"update_time":0,"reply_num":0,"praise_num":0,"first_br_id":69,"m_name":"小鸡","m_mobile":"15220219931","mi_head":"http://88php.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190620/9cfc42dc3a89089458a5932eb2c0cb74a4ec5670_600x338.jpg","baike_content":"小鸡的东西我自己都没了。我说过去的事情就这样一直一直陪伴着你回来我的心里永远不会发生这种问题","add_date":"2019-07-03 15:59:49","is_praise":0,"to_m_name":"小鸡"}]}]}
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

    public static class DataBean implements Serializable {
        private List<ListBean> list;

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean implements Serializable {
            /**
             * br_id : 69
             * baike_id : 52
             * br_id_father : 0
             * m_id : 7
             * to_mid : 0
             * br_content : 不能容忍的是
             * br_state : 0
             * add_time : 1562125229
             * update_admin : 0
             * update_time : 0
             * reply_num : 4
             * praise_num : 0
             * first_br_id : 0
             * m_name : 小鸡
             * m_mobile : 15220219931
             * mi_head : http://88php.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190620/9cfc42dc3a89089458a5932eb2c0cb74a4ec5670_600x338.jpg
             * baike_content : 小鸡的东西我自己都没了。我说过去的事情就这样一直一直陪伴着你回来我的心里永远不会发生这种问题
             * add_date : 2019-07-03 11:40:29
             * is_praise : 0
             * list_s : [{"br_id":93,"baike_id":52,"br_id_father":69,"m_id":7,"to_mid":7,"br_content":"打雷下雨模型","br_state":0,"add_time":1562989386,"update_admin":0,"update_time":0,"reply_num":0,"praise_num":0,"first_br_id":69,"m_name":"小鸡","m_mobile":"15220219931","mi_head":"http://88php.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190620/9cfc42dc3a89089458a5932eb2c0cb74a4ec5670_600x338.jpg","baike_content":"小鸡的东西我自己都没了。我说过去的事情就这样一直一直陪伴着你回来我的心里永远不会发生这种问题","add_date":"2019-07-13 11:43:06","is_praise":0,"to_m_name":"小鸡"},{"br_id":76,"baike_id":52,"br_id_father":69,"m_id":7,"to_mid":7,"br_content":"一次","br_state":0,"add_time":1562140789,"update_admin":0,"update_time":0,"reply_num":0,"praise_num":0,"first_br_id":69,"m_name":"小鸡","m_mobile":"15220219931","mi_head":"http://88php.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190620/9cfc42dc3a89089458a5932eb2c0cb74a4ec5670_600x338.jpg","baike_content":"小鸡的东西我自己都没了。我说过去的事情就这样一直一直陪伴着你回来我的心里永远不会发生这种问题","add_date":"2019-07-03 15:59:49","is_praise":0,"to_m_name":"小鸡"}]
             */

            private int br_id;
            private int baike_id;
            private int br_id_father;
            private int m_id;
            private int to_mid;
            private String br_content;
            private int br_state;
            private int add_time;
            private int update_admin;
            private int update_time;
            private int reply_num;
            private int praise_num;
            private int first_br_id;
            private String m_name;
            private String m_mobile;
            private String mi_head;
            private String baike_content;
            private String add_date;
            private int is_praise;
            private List<ListSBean> list_s;

            public int getBr_id() {
                return br_id;
            }

            public void setBr_id(int br_id) {
                this.br_id = br_id;
            }

            public int getBaike_id() {
                return baike_id;
            }

            public void setBaike_id(int baike_id) {
                this.baike_id = baike_id;
            }

            public int getBr_id_father() {
                return br_id_father;
            }

            public void setBr_id_father(int br_id_father) {
                this.br_id_father = br_id_father;
            }

            public int getM_id() {
                return m_id;
            }

            public void setM_id(int m_id) {
                this.m_id = m_id;
            }

            public int getTo_mid() {
                return to_mid;
            }

            public void setTo_mid(int to_mid) {
                this.to_mid = to_mid;
            }

            public String getBr_content() {
                return br_content;
            }

            public void setBr_content(String br_content) {
                this.br_content = br_content;
            }

            public int getBr_state() {
                return br_state;
            }

            public void setBr_state(int br_state) {
                this.br_state = br_state;
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

            public int getReply_num() {
                return reply_num;
            }

            public void setReply_num(int reply_num) {
                this.reply_num = reply_num;
            }

            public int getPraise_num() {
                return praise_num;
            }

            public void setPraise_num(int praise_num) {
                this.praise_num = praise_num;
            }

            public int getFirst_br_id() {
                return first_br_id;
            }

            public void setFirst_br_id(int first_br_id) {
                this.first_br_id = first_br_id;
            }

            public String getM_name() {
                return m_name;
            }

            public void setM_name(String m_name) {
                this.m_name = m_name;
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

            public String getBaike_content() {
                return baike_content;
            }

            public void setBaike_content(String baike_content) {
                this.baike_content = baike_content;
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

            public List<ListSBean> getList_s() {
                return list_s;
            }

            public void setList_s(List<ListSBean> list_s) {
                this.list_s = list_s;
            }

            public static class ListSBean implements Serializable {
                /**
                 * br_id : 93
                 * baike_id : 52
                 * br_id_father : 69
                 * m_id : 7
                 * to_mid : 7
                 * br_content : 打雷下雨模型
                 * br_state : 0
                 * add_time : 1562989386
                 * update_admin : 0
                 * update_time : 0
                 * reply_num : 0
                 * praise_num : 0
                 * first_br_id : 69
                 * m_name : 小鸡
                 * m_mobile : 15220219931
                 * mi_head : http://88php.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190620/9cfc42dc3a89089458a5932eb2c0cb74a4ec5670_600x338.jpg
                 * baike_content : 小鸡的东西我自己都没了。我说过去的事情就这样一直一直陪伴着你回来我的心里永远不会发生这种问题
                 * add_date : 2019-07-13 11:43:06
                 * is_praise : 0
                 * to_m_name : 小鸡
                 */

                private int br_id;
                private int baike_id;
                private int br_id_father;
                private int m_id;
                private int to_mid;
                private String br_content;
                private int br_state;
                private int add_time;
                private int update_admin;
                private int update_time;
                private int reply_num;
                private int praise_num;
                private int first_br_id;
                private String m_name;
                private String m_mobile;
                private String mi_head;
                private String baike_content;
                private String add_date;
                private int is_praise;
                private String to_m_name;

                public int getBr_id() {
                    return br_id;
                }

                public void setBr_id(int br_id) {
                    this.br_id = br_id;
                }

                public int getBaike_id() {
                    return baike_id;
                }

                public void setBaike_id(int baike_id) {
                    this.baike_id = baike_id;
                }

                public int getBr_id_father() {
                    return br_id_father;
                }

                public void setBr_id_father(int br_id_father) {
                    this.br_id_father = br_id_father;
                }

                public int getM_id() {
                    return m_id;
                }

                public void setM_id(int m_id) {
                    this.m_id = m_id;
                }

                public int getTo_mid() {
                    return to_mid;
                }

                public void setTo_mid(int to_mid) {
                    this.to_mid = to_mid;
                }

                public String getBr_content() {
                    return br_content;
                }

                public void setBr_content(String br_content) {
                    this.br_content = br_content;
                }

                public int getBr_state() {
                    return br_state;
                }

                public void setBr_state(int br_state) {
                    this.br_state = br_state;
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

                public int getReply_num() {
                    return reply_num;
                }

                public void setReply_num(int reply_num) {
                    this.reply_num = reply_num;
                }

                public int getPraise_num() {
                    return praise_num;
                }

                public void setPraise_num(int praise_num) {
                    this.praise_num = praise_num;
                }

                public int getFirst_br_id() {
                    return first_br_id;
                }

                public void setFirst_br_id(int first_br_id) {
                    this.first_br_id = first_br_id;
                }

                public String getM_name() {
                    return m_name;
                }

                public void setM_name(String m_name) {
                    this.m_name = m_name;
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

                public String getBaike_content() {
                    return baike_content;
                }

                public void setBaike_content(String baike_content) {
                    this.baike_content = baike_content;
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

                public String getTo_m_name() {
                    return to_m_name;
                }

                public void setTo_m_name(String to_m_name) {
                    this.to_m_name = to_m_name;
                }
            }
        }
    }
}
