package com.hongyuan.fitness.ui.find.circle.topic_participant;

import com.hongyuan.fitness.base.BaseBean;

import java.util.List;

public class TopicParticpantBeans extends BaseBean {

    /**
     * hasmore : false
     * curpage : 1
     * page_total : 1
     * data : {"list":[{"m_id":2,"mi_head":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190919/72bb4c54c7af946c80276ac0a8d2895c654ad0f6_2208x2208.jpg","m_mobile":"15220219931","m_name":"呼呼","is_friend":1},{"m_id":13,"mi_head":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190918/604e8db72a0562e17534d50a637fd75110e5cd41_1179x977.png","m_mobile":"18183185173","m_name":"嗯哼哼","is_friend":0}]}
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
             * m_id : 2
             * mi_head : http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190919/72bb4c54c7af946c80276ac0a8d2895c654ad0f6_2208x2208.jpg
             * m_mobile : 15220219931
             * m_name : 呼呼
             * is_friend : 1
             */

            private int m_id;
            private String mi_head;
            private String m_mobile;
            private String m_name;
            private int is_friend;

            public int getM_id() {
                return m_id;
            }

            public void setM_id(int m_id) {
                this.m_id = m_id;
            }

            public String getMi_head() {
                return mi_head;
            }

            public void setMi_head(String mi_head) {
                this.mi_head = mi_head;
            }

            public String getM_mobile() {
                return m_mobile;
            }

            public void setM_mobile(String m_mobile) {
                this.m_mobile = m_mobile;
            }

            public String getM_name() {
                return m_name;
            }

            public void setM_name(String m_name) {
                this.m_name = m_name;
            }

            public int getIs_friend() {
                return is_friend;
            }

            public void setIs_friend(int is_friend) {
                this.is_friend = is_friend;
            }
        }
    }
}
