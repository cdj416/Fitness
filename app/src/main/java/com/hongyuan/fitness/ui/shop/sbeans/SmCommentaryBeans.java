package com.hongyuan.fitness.ui.shop.sbeans;

import com.hongyuan.fitness.base.BaseBean;

import java.util.List;

public class SmCommentaryBeans extends BaseBean {

    /**
     * hasmore : false
     * curpage : 1
     * page_total : 1
     * data : {"list":[{"msg_id":38399,"is_read":0,"m_name":"妮妮","m_id":3,"m_mobile":"18183185173","mi_head":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20200414/2e4f6772c99ea949617b0cc3c61b52217596ea71_1920x1008.jpeg","msg_content":"你才是神经病","msg_time":"2020-08-23 10:00:09","cr_id":3361,"circle_id":5047,"cr_id_father":3360,"first_cr_id":3359,"praise_num":0,"reply_num":0,"praise_state":0},{"msg_id":38398,"is_read":0,"m_name":"妮妮","m_id":3,"m_mobile":"18183185173","mi_head":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20200414/2e4f6772c99ea949617b0cc3c61b52217596ea71_1920x1008.jpeg","msg_content":"神经病","msg_time":"2020-08-23 09:59:57","cr_id":3360,"circle_id":5047,"cr_id_father":3359,"first_cr_id":3359,"praise_num":0,"reply_num":1,"praise_state":0},{"msg_id":38397,"is_read":0,"m_name":"妮妮","m_id":3,"m_mobile":"18183185173","mi_head":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20200414/2e4f6772c99ea949617b0cc3c61b52217596ea71_1920x1008.jpeg","msg_content":"啦啦","msg_time":"2020-08-23 09:59:37","cr_id":3359,"circle_id":5047,"cr_id_father":0,"first_cr_id":0,"praise_num":0,"reply_num":2,"praise_state":0},{"msg_id":38396,"is_read":0,"m_name":"妮妮","m_id":3,"m_mobile":"18183185173","mi_head":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20200414/2e4f6772c99ea949617b0cc3c61b52217596ea71_1920x1008.jpeg","msg_content":"嘿嘿\n","msg_time":"2020-08-23 09:59:29","cr_id":3358,"circle_id":5047,"cr_id_father":0,"first_cr_id":0,"praise_num":0,"reply_num":0,"praise_state":0},{"msg_id":38395,"is_read":0,"m_name":"妮妮","m_id":3,"m_mobile":"18183185173","mi_head":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20200414/2e4f6772c99ea949617b0cc3c61b52217596ea71_1920x1008.jpeg","msg_content":"我来评论下，哈哈","msg_time":"2020-08-23 09:59:21","cr_id":3357,"circle_id":5047,"cr_id_father":0,"first_cr_id":0,"praise_num":0,"reply_num":0,"praise_state":0}]}
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
             * msg_id : 38399
             * is_read : 0
             * m_name : 妮妮
             * m_id : 3
             * m_mobile : 18183185173
             * mi_head : http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20200414/2e4f6772c99ea949617b0cc3c61b52217596ea71_1920x1008.jpeg
             * msg_content : 你才是神经病
             * msg_time : 2020-08-23 10:00:09
             * cr_id : 3361
             * circle_id : 5047
             * cr_id_father : 3360
             * first_cr_id : 3359
             * praise_num : 0
             * reply_num : 0
             * praise_state : 0
             */

            private int msg_id;
            private int is_read;
            private String m_name;
            private int m_id;
            private String m_mobile;
            private String mi_head;
            private String msg_content;
            private String msg_time;
            private int cr_id;
            private int circle_id;
            private int cr_id_father;
            private int first_cr_id;
            private int praise_num;
            private int reply_num;
            private int praise_state;

            public int getMsg_id() {
                return msg_id;
            }

            public void setMsg_id(int msg_id) {
                this.msg_id = msg_id;
            }

            public int getIs_read() {
                return is_read;
            }

            public void setIs_read(int is_read) {
                this.is_read = is_read;
            }

            public String getM_name() {
                return m_name;
            }

            public void setM_name(String m_name) {
                this.m_name = m_name;
            }

            public int getM_id() {
                return m_id;
            }

            public void setM_id(int m_id) {
                this.m_id = m_id;
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

            public String getMsg_content() {
                return msg_content;
            }

            public void setMsg_content(String msg_content) {
                this.msg_content = msg_content;
            }

            public String getMsg_time() {
                return msg_time;
            }

            public void setMsg_time(String msg_time) {
                this.msg_time = msg_time;
            }

            public int getCr_id() {
                return cr_id;
            }

            public void setCr_id(int cr_id) {
                this.cr_id = cr_id;
            }

            public int getCircle_id() {
                return circle_id;
            }

            public void setCircle_id(int circle_id) {
                this.circle_id = circle_id;
            }

            public int getCr_id_father() {
                return cr_id_father;
            }

            public void setCr_id_father(int cr_id_father) {
                this.cr_id_father = cr_id_father;
            }

            public int getFirst_cr_id() {
                return first_cr_id;
            }

            public void setFirst_cr_id(int first_cr_id) {
                this.first_cr_id = first_cr_id;
            }

            public int getPraise_num() {
                return praise_num;
            }

            public void setPraise_num(int praise_num) {
                this.praise_num = praise_num;
            }

            public int getReply_num() {
                return reply_num;
            }

            public void setReply_num(int reply_num) {
                this.reply_num = reply_num;
            }

            public int getPraise_state() {
                return praise_state;
            }

            public void setPraise_state(int praise_state) {
                this.praise_state = praise_state;
            }
        }
    }
}
