package com.hongyuan.fitness.ui.shop.sbeans;

import com.hongyuan.fitness.base.BaseBean;

import java.util.List;

public class GroupChatUserBeans extends BaseBean {

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * mi_realname : 大王
         * mi_head : http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20200515/b50700cf80e7a5d543ea3cb98a29aadfb7f29709_1334x1334.jpg
         * m_id : 15535
         */

        private String mi_realname;
        private String mi_head;
        private int m_id;

        public String getMi_realname() {
            return mi_realname;
        }

        public void setMi_realname(String mi_realname) {
            this.mi_realname = mi_realname;
        }

        public String getMi_head() {
            return mi_head;
        }

        public void setMi_head(String mi_head) {
            this.mi_head = mi_head;
        }

        public int getM_id() {
            return m_id;
        }

        public void setM_id(int m_id) {
            this.m_id = m_id;
        }
    }
}
