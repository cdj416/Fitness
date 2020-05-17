package com.hongyuan.fitness.ui.shop.sbeans;

import com.hongyuan.fitness.base.BaseBean;

import java.util.List;

public class LogisticsBeans extends BaseBean {

    /**
     * data : {"list":[{"time":"2020-05-04 18:52:57","context":"客户签收人: 凭取件码 已签收  感谢使用圆通速递，期待再次为您服务 如有疑问请联系：15356676953，投诉电话：0571-28177166"},{"time":"2020-05-04 15:50:35","context":"西溪璞园西门保安亭旁-蜂站蜂站已发出自提通知，请上门自提，联系电话13033615952"},{"time":"2020-05-04 15:50:35","context":"圆通合作点蜂站快件已到达西溪璞园西门保安亭旁-蜂站驿站联系电话13033615952"},{"time":"2020-05-04 14:19:57","context":"【浙江省杭州市新天目山路公司】 派件中  派件人: 俞乐 电话 15356676953  如有疑问，请联系：0571-28177166"},{"time":"2020-05-04 14:17:06","context":"【浙江省杭州市新天目山路公司】 已收入"},{"time":"2020-05-04 08:16:26","context":"【杭州石桥城配中心】 已发出 下一站 【浙江省杭州市新天目山路公司】"},{"time":"2020-05-04 07:54:54","context":"【杭州石桥城配中心公司】 已收入"},{"time":"2020-05-03 21:15:45","context":"【淮安转运中心】 已发出 下一站 【杭州石桥城配中心公司】"},{"time":"2020-05-03 20:53:01","context":"【淮安转运中心公司】 已收入"},{"time":"2020-05-03 14:16:27","context":"【江苏省淮安市清河公司】 已收件 取件人: 王巍 (15189596723)"}]}
     */

    private DataBean data;

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
             * time : 2020-05-04 18:52:57
             * context : 客户签收人: 凭取件码 已签收  感谢使用圆通速递，期待再次为您服务 如有疑问请联系：15356676953，投诉电话：0571-28177166
             */

            private String time;
            private String context;

            public String getTime() {
                return time;
            }

            public void setTime(String time) {
                this.time = time;
            }

            public String getContext() {
                return context;
            }

            public void setContext(String context) {
                this.context = context;
            }
        }
    }
}
