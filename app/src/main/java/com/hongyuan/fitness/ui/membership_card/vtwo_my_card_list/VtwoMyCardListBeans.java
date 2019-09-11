package com.hongyuan.fitness.ui.membership_card.vtwo_my_card_list;

import com.hongyuan.fitness.base.BaseBean;

import java.util.ArrayList;
import java.util.List;

public class VtwoMyCardListBeans extends BaseBean {

    /**
     * data : {"list":{"cardlist_os":[{"my_card_id":1,"m_id":3,"os_id":24,"my_card_type":1,"open_time":1565193600,"my_card_time":1565094254,"last_time":1596807775,"my_card_days":0,"osl_id":0,"os_name":"首玺健身环城西路店","open_date":"2019-08-08 00:00:00","last_date":"2020-08-07 21:42:55","my_card_date":"2019-08-06 20:24:14"}],"cardlist_ty":[{"my_card_id":5,"m_id":3,"os_id":0,"my_card_type":2,"open_time":1566292012,"my_card_time":0,"last_time":0,"my_card_days":500,"osl_id":1,"osl_name":"普通店","open_date":"2019-08-20 17:06:52","last_date":0,"my_card_date":"1970-01-01 08:00:00"}]}}
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
         * list : {"cardlist_os":[{"my_card_id":1,"m_id":3,"os_id":24,"my_card_type":1,"open_time":1565193600,"my_card_time":1565094254,"last_time":1596807775,"my_card_days":0,"osl_id":0,"os_name":"首玺健身环城西路店","open_date":"2019-08-08 00:00:00","last_date":"2020-08-07 21:42:55","my_card_date":"2019-08-06 20:24:14"}],"cardlist_ty":[{"my_card_id":5,"m_id":3,"os_id":0,"my_card_type":2,"open_time":1566292012,"my_card_time":0,"last_time":0,"my_card_days":500,"osl_id":1,"osl_name":"普通店","open_date":"2019-08-20 17:06:52","last_date":0,"my_card_date":"1970-01-01 08:00:00"}]}
         */

        private ListBean list;

        public ListBean getList() {
            return list;
        }

        public void setList(ListBean list) {
            this.list = list;
        }

        public static class ListBean {
            private List<CardlistOsBean> cardlist_os;
            private List<CardlistTyBean> cardlist_ty;


            private List<VtwoMycardCommonBeans> commonBeansList;


            public List<VtwoMycardCommonBeans> getUseList(){
                if(commonBeansList == null){
                    commonBeansList = new ArrayList<>();
                }
                commonBeansList.clear();
                if(cardlist_os != null && cardlist_os.size() > 0){
                    for(CardlistOsBean osBean : cardlist_os){
                        VtwoMycardCommonBeans commonBeans = new VtwoMycardCommonBeans();
                        commonBeans.setLast_date(osBean.getLast_date());
                        commonBeans.setLast_time(osBean.getLast_time());
                        commonBeans.setM_id(osBean.getM_id());
                        commonBeans.setMy_card_date(osBean.getMy_card_date());
                        commonBeans.setMy_card_days(osBean.getMy_card_days());
                        commonBeans.setMy_card_id(osBean.getMy_card_id());
                        commonBeans.setMy_card_time(osBean.getMy_card_time());
                        commonBeans.setMy_card_type(osBean.getMy_card_type());
                        commonBeans.setOpen_date(osBean.getOpen_date());
                        commonBeans.setOpen_time(osBean.getOpen_time());
                        commonBeans.setOs_id(osBean.getOs_id());
                        commonBeans.setOs_name(osBean.getOs_name());
                        commonBeans.setOsl_id(osBean.getOsl_id());

                        commonBeansList.add(commonBeans);
                    }
                }
                if(cardlist_ty != null && cardlist_ty.size() > 0){
                    for(CardlistTyBean tyBean : cardlist_ty){
                        VtwoMycardCommonBeans commonBeans = new VtwoMycardCommonBeans();
                        commonBeans.setLast_date(String.valueOf(tyBean.getLast_date()));
                        commonBeans.setLast_time(tyBean.getLast_time());
                        commonBeans.setM_id(tyBean.getM_id());
                        commonBeans.setMy_card_date(tyBean.getMy_card_date());
                        commonBeans.setMy_card_days(tyBean.getMy_card_days());
                        commonBeans.setMy_card_id(tyBean.getMy_card_id());
                        commonBeans.setMy_card_time(tyBean.getMy_card_time());
                        commonBeans.setMy_card_type(tyBean.getMy_card_type());
                        commonBeans.setOpen_date(tyBean.getOpen_date());
                        commonBeans.setOpen_time(tyBean.getOpen_time());
                        commonBeans.setOs_id(tyBean.getOs_id());
                        commonBeans.setOsl_id(tyBean.getOsl_id());

                        commonBeansList.add(commonBeans);
                    }
                }
                return commonBeansList;
            }

            public List<CardlistOsBean> getCardlist_os() {
                return cardlist_os;
            }

            public void setCardlist_os(List<CardlistOsBean> cardlist_os) {
                this.cardlist_os = cardlist_os;
            }

            public List<CardlistTyBean> getCardlist_ty() {
                return cardlist_ty;
            }

            public void setCardlist_ty(List<CardlistTyBean> cardlist_ty) {
                this.cardlist_ty = cardlist_ty;
            }

            public static class CardlistOsBean {
                /**
                 * my_card_id : 1
                 * m_id : 3
                 * os_id : 24
                 * my_card_type : 1
                 * open_time : 1565193600
                 * my_card_time : 1565094254
                 * last_time : 1596807775
                 * my_card_days : 0
                 * osl_id : 0
                 * os_name : 首玺健身环城西路店
                 * open_date : 2019-08-08 00:00:00
                 * last_date : 2020-08-07 21:42:55
                 * my_card_date : 2019-08-06 20:24:14
                 */

                private int my_card_id;
                private int m_id;
                private int os_id;
                private int my_card_type;
                private int open_time;
                private int my_card_time;
                private int last_time;
                private int my_card_days;
                private int osl_id;
                private String os_name;
                private String open_date;
                private String last_date;
                private String my_card_date;

                public int getMy_card_id() {
                    return my_card_id;
                }

                public void setMy_card_id(int my_card_id) {
                    this.my_card_id = my_card_id;
                }

                public int getM_id() {
                    return m_id;
                }

                public void setM_id(int m_id) {
                    this.m_id = m_id;
                }

                public int getOs_id() {
                    return os_id;
                }

                public void setOs_id(int os_id) {
                    this.os_id = os_id;
                }

                public int getMy_card_type() {
                    return my_card_type;
                }

                public void setMy_card_type(int my_card_type) {
                    this.my_card_type = my_card_type;
                }

                public int getOpen_time() {
                    return open_time;
                }

                public void setOpen_time(int open_time) {
                    this.open_time = open_time;
                }

                public int getMy_card_time() {
                    return my_card_time;
                }

                public void setMy_card_time(int my_card_time) {
                    this.my_card_time = my_card_time;
                }

                public int getLast_time() {
                    return last_time;
                }

                public void setLast_time(int last_time) {
                    this.last_time = last_time;
                }

                public int getMy_card_days() {
                    return my_card_days;
                }

                public void setMy_card_days(int my_card_days) {
                    this.my_card_days = my_card_days;
                }

                public int getOsl_id() {
                    return osl_id;
                }

                public void setOsl_id(int osl_id) {
                    this.osl_id = osl_id;
                }

                public String getOs_name() {
                    return os_name;
                }

                public void setOs_name(String os_name) {
                    this.os_name = os_name;
                }

                public String getOpen_date() {
                    return open_date;
                }

                public void setOpen_date(String open_date) {
                    this.open_date = open_date;
                }

                public String getLast_date() {
                    return last_date;
                }

                public void setLast_date(String last_date) {
                    this.last_date = last_date;
                }

                public String getMy_card_date() {
                    return my_card_date;
                }

                public void setMy_card_date(String my_card_date) {
                    this.my_card_date = my_card_date;
                }
            }

            public static class CardlistTyBean {
                /**
                 * my_card_id : 5
                 * m_id : 3
                 * os_id : 0
                 * my_card_type : 2
                 * open_time : 1566292012
                 * my_card_time : 0
                 * last_time : 0
                 * my_card_days : 500
                 * osl_id : 1
                 * osl_name : 普通店
                 * open_date : 2019-08-20 17:06:52
                 * last_date : 0
                 * my_card_date : 1970-01-01 08:00:00
                 */

                private int my_card_id;
                private int m_id;
                private int os_id;
                private int my_card_type;
                private int open_time;
                private int my_card_time;
                private int last_time;
                private int my_card_days;
                private int osl_id;
                private String osl_name;
                private String open_date;
                private String last_date;
                private String my_card_date;

                public int getMy_card_id() {
                    return my_card_id;
                }

                public void setMy_card_id(int my_card_id) {
                    this.my_card_id = my_card_id;
                }

                public int getM_id() {
                    return m_id;
                }

                public void setM_id(int m_id) {
                    this.m_id = m_id;
                }

                public int getOs_id() {
                    return os_id;
                }

                public void setOs_id(int os_id) {
                    this.os_id = os_id;
                }

                public int getMy_card_type() {
                    return my_card_type;
                }

                public void setMy_card_type(int my_card_type) {
                    this.my_card_type = my_card_type;
                }

                public int getOpen_time() {
                    return open_time;
                }

                public void setOpen_time(int open_time) {
                    this.open_time = open_time;
                }

                public int getMy_card_time() {
                    return my_card_time;
                }

                public void setMy_card_time(int my_card_time) {
                    this.my_card_time = my_card_time;
                }

                public int getLast_time() {
                    return last_time;
                }

                public void setLast_time(int last_time) {
                    this.last_time = last_time;
                }

                public int getMy_card_days() {
                    return my_card_days;
                }

                public void setMy_card_days(int my_card_days) {
                    this.my_card_days = my_card_days;
                }

                public int getOsl_id() {
                    return osl_id;
                }

                public void setOsl_id(int osl_id) {
                    this.osl_id = osl_id;
                }

                public String getOsl_name() {
                    return osl_name;
                }

                public void setOsl_name(String osl_name) {
                    this.osl_name = osl_name;
                }

                public String getOpen_date() {
                    return open_date;
                }

                public void setOpen_date(String open_date) {
                    this.open_date = open_date;
                }

                public String getLast_date() {
                    return last_date;
                }

                public void setLast_date(String last_date) {
                    this.last_date = last_date;
                }

                public String getMy_card_date() {
                    return my_card_date;
                }

                public void setMy_card_date(String my_card_date) {
                    this.my_card_date = my_card_date;
                }
            }
        }
    }
}
