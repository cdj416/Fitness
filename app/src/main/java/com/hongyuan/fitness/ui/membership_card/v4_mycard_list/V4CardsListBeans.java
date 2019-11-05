package com.hongyuan.fitness.ui.membership_card.v4_mycard_list;

import com.hongyuan.fitness.base.BaseBean;

import java.util.List;

public class V4CardsListBeans extends BaseBean {


    /**
     * hasmore : false
     * curpage : 1
     * page_total : 1
     * data : {"list":[{"card_id":10,"card_name":"环城西路店+湖东店多店卡","card_original_price":"2600.00","card_sale_price":"2000.00","ct_id":1,"expire_time":0,"card_days":400,"card_img":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20191021/37032738b3ff1412c5bdc80125cf4fe818102d31_654x348.jpg","admin_id":1,"add_time":1571723734,"update_time":0,"update_admin":0,"card_note":"多送35天","os_id":"24,22","card_type":1,"osl_id":0,"cc_id":2,"is_del":2,"is_ty":2,"ct_name":"年卡","osl_name":null,"osl_img":null,"cc_name":"门店基本卡","cc_img":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20191021/37032738b3ff1412c5bdc80125cf4fe818102d31_654x348.jpg","card_type_name":"门店卡","c_img":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20191021/37032738b3ff1412c5bdc80125cf4fe818102d31_654x348.jpg","c_name":"门店基本卡","os_names":"湖州首玺健身管理有限公司湖东店,首玺健身环城西路店"},{"card_id":9,"card_name":"环城西路店家庭卡","card_original_price":"3000.00","card_sale_price":"2530.00","ct_id":1,"expire_time":0,"card_days":400,"card_img":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20191021/5e9dd5f616847a35eefc29b252030e08d55d4900_654x348.jpg","admin_id":1,"add_time":1571723665,"update_time":0,"update_admin":0,"card_note":"范德萨发四大 富士达","os_id":"24","card_type":1,"osl_id":0,"cc_id":3,"is_del":2,"is_ty":2,"ct_name":"年卡","osl_name":null,"osl_img":null,"cc_name":"家庭卡","cc_img":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20191021/5e9dd5f616847a35eefc29b252030e08d55d4900_654x348.jpg","card_type_name":"门店卡","c_img":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20191021/5e9dd5f616847a35eefc29b252030e08d55d4900_654x348.jpg","c_name":"家庭卡","os_names":"首玺健身环城西路店"},{"card_id":7,"card_name":"宏丰门店情侣卡2","card_original_price":"5000.00","card_sale_price":"3000.00","ct_id":1,"expire_time":0,"card_days":400,"card_img":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20191015/d7e12bf6b7a1a6d4e01f36d9a3d0130b26e788d6_648x454.jpg","admin_id":1,"add_time":1571386210,"update_time":1571388609,"update_admin":1,"card_note":"法师打发的撒 ","os_id":"26,24","card_type":1,"osl_id":0,"cc_id":1,"is_del":2,"is_ty":2,"ct_name":"年卡","osl_name":null,"osl_img":null,"cc_name":"情侣卡","cc_img":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20191021/a70eb506855647da17faff846a19313070375e87_654x348.jpg","card_type_name":"门店卡","c_img":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20191021/a70eb506855647da17faff846a19313070375e87_654x348.jpg","c_name":"情侣卡","os_names":"首玺健身环城西路店,首玺健身金色水岸店"},{"card_id":6,"card_name":"宏丰门店情侣卡1","card_original_price":"5000.00","card_sale_price":"3000.00","ct_id":1,"expire_time":0,"card_days":400,"card_img":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20191015/d7e12bf6b7a1a6d4e01f36d9a3d0130b26e788d6_648x454.jpg","admin_id":1,"add_time":1571130408,"update_time":1571388540,"update_admin":1,"card_note":"法师打发的撒 ","os_id":"26,24","card_type":1,"osl_id":0,"cc_id":1,"is_del":2,"is_ty":2,"ct_name":"年卡","osl_name":null,"osl_img":null,"cc_name":"情侣卡","cc_img":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20191021/a70eb506855647da17faff846a19313070375e87_654x348.jpg","card_type_name":"门店卡","c_img":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20191021/a70eb506855647da17faff846a19313070375e87_654x348.jpg","c_name":"情侣卡","os_names":"首玺健身环城西路店,首玺健身金色水岸店"}]}
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
             * card_id : 10
             * card_name : 环城西路店+湖东店多店卡
             * card_original_price : 2600.00
             * card_sale_price : 2000.00
             * ct_id : 1
             * expire_time : 0
             * card_days : 400
             * card_img : http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20191021/37032738b3ff1412c5bdc80125cf4fe818102d31_654x348.jpg
             * admin_id : 1
             * add_time : 1571723734
             * update_time : 0
             * update_admin : 0
             * card_note : 多送35天
             * os_id : 24,22
             * card_type : 1
             * osl_id : 0
             * cc_id : 2
             * is_del : 2
             * is_ty : 2
             * ct_name : 年卡
             * osl_name : null
             * osl_img : null
             * cc_name : 门店基本卡
             * cc_img : http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20191021/37032738b3ff1412c5bdc80125cf4fe818102d31_654x348.jpg
             * card_type_name : 门店卡
             * c_img : http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20191021/37032738b3ff1412c5bdc80125cf4fe818102d31_654x348.jpg
             * c_name : 门店基本卡
             * os_names : 湖州首玺健身管理有限公司湖东店,首玺健身环城西路店
             */

            private int card_id;
            private String card_name;
            private String card_original_price;
            private String card_sale_price;
            private int ct_id;
            private int expire_time;
            private int card_days;
            private String card_img;
            private int admin_id;
            private int add_time;
            private int update_time;
            private int update_admin;
            private String card_note;
            private String os_id;
            private int card_type;
            private int osl_id;
            private int cc_id;
            private int is_del;
            private int is_ty;
            private String ct_name;
            private Object osl_name;
            private Object osl_img;
            private String cc_name;
            private String cc_img;
            private String card_type_name;
            private String c_img;
            private String c_name;
            private String os_names;

            public int getCard_id() {
                return card_id;
            }

            public void setCard_id(int card_id) {
                this.card_id = card_id;
            }

            public String getCard_name() {
                return card_name;
            }

            public void setCard_name(String card_name) {
                this.card_name = card_name;
            }

            public String getCard_original_price() {
                return card_original_price;
            }

            public void setCard_original_price(String card_original_price) {
                this.card_original_price = card_original_price;
            }

            public String getCard_sale_price() {
                return card_sale_price;
            }

            public void setCard_sale_price(String card_sale_price) {
                this.card_sale_price = card_sale_price;
            }

            public int getCt_id() {
                return ct_id;
            }

            public void setCt_id(int ct_id) {
                this.ct_id = ct_id;
            }

            public int getExpire_time() {
                return expire_time;
            }

            public void setExpire_time(int expire_time) {
                this.expire_time = expire_time;
            }

            public int getCard_days() {
                return card_days;
            }

            public void setCard_days(int card_days) {
                this.card_days = card_days;
            }

            public String getCard_img() {
                return card_img;
            }

            public void setCard_img(String card_img) {
                this.card_img = card_img;
            }

            public int getAdmin_id() {
                return admin_id;
            }

            public void setAdmin_id(int admin_id) {
                this.admin_id = admin_id;
            }

            public int getAdd_time() {
                return add_time;
            }

            public void setAdd_time(int add_time) {
                this.add_time = add_time;
            }

            public int getUpdate_time() {
                return update_time;
            }

            public void setUpdate_time(int update_time) {
                this.update_time = update_time;
            }

            public int getUpdate_admin() {
                return update_admin;
            }

            public void setUpdate_admin(int update_admin) {
                this.update_admin = update_admin;
            }

            public String getCard_note() {
                return card_note;
            }

            public void setCard_note(String card_note) {
                this.card_note = card_note;
            }

            public String getOs_id() {
                return os_id;
            }

            public void setOs_id(String os_id) {
                this.os_id = os_id;
            }

            public int getCard_type() {
                return card_type;
            }

            public void setCard_type(int card_type) {
                this.card_type = card_type;
            }

            public int getOsl_id() {
                return osl_id;
            }

            public void setOsl_id(int osl_id) {
                this.osl_id = osl_id;
            }

            public int getCc_id() {
                return cc_id;
            }

            public void setCc_id(int cc_id) {
                this.cc_id = cc_id;
            }

            public int getIs_del() {
                return is_del;
            }

            public void setIs_del(int is_del) {
                this.is_del = is_del;
            }

            public int getIs_ty() {
                return is_ty;
            }

            public void setIs_ty(int is_ty) {
                this.is_ty = is_ty;
            }

            public String getCt_name() {
                return ct_name;
            }

            public void setCt_name(String ct_name) {
                this.ct_name = ct_name;
            }

            public Object getOsl_name() {
                return osl_name;
            }

            public void setOsl_name(Object osl_name) {
                this.osl_name = osl_name;
            }

            public Object getOsl_img() {
                return osl_img;
            }

            public void setOsl_img(Object osl_img) {
                this.osl_img = osl_img;
            }

            public String getCc_name() {
                return cc_name;
            }

            public void setCc_name(String cc_name) {
                this.cc_name = cc_name;
            }

            public String getCc_img() {
                return cc_img;
            }

            public void setCc_img(String cc_img) {
                this.cc_img = cc_img;
            }

            public String getCard_type_name() {
                return card_type_name;
            }

            public void setCard_type_name(String card_type_name) {
                this.card_type_name = card_type_name;
            }

            public String getC_img() {
                return c_img;
            }

            public void setC_img(String c_img) {
                this.c_img = c_img;
            }

            public String getC_name() {
                return c_name;
            }

            public void setC_name(String c_name) {
                this.c_name = c_name;
            }

            public String getOs_names() {
                return os_names;
            }

            public void setOs_names(String os_names) {
                this.os_names = os_names;
            }
        }
    }
}
