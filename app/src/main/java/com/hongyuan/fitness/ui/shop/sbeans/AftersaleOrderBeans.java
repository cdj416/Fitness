package com.hongyuan.fitness.ui.shop.sbeans;

import com.hongyuan.fitness.base.BaseBean;

import java.util.List;

public class AftersaleOrderBeans extends BaseBean {

    /**
     * data : {"info":{"confirm_date":0,"state_str":"等待付款","deliver_address":{"consignee":"她婆婆无事献殷勤","deliver_mobile":"54649949","pname":"黑龙江省","cname":"哈尔滨市","dname":"道里区","address":"行行"},"store":{"store_id":42,"store_name":"湖州志裕","store_person_tel":""},"goods_list":[{"g_id":43,"g_img":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20200301/d6e8d9eed2598af883e92b1f6516a023c539d652_800x467.jpg","g_name":"乐拉泰国青柚金柚进口西施蜜柚2个4.5斤新鲜当季孕妇水果顺丰包邮","sku_names":"蓝色;M码","buy_num":2,"gp_price":"100.00","gp_point":0},{"g_id":43,"g_img":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20200301/d6e8d9eed2598af883e92b1f6516a023c539d652_800x467.jpg","g_name":"乐拉泰国青柚金柚进口西施蜜柚2个4.5斤新鲜当季孕妇水果顺丰包邮","sku_names":"白色;L码","buy_num":1,"gp_price":"105.00","gp_point":0}],"all_product_price":"305.00","deliver_fee":"0.00","coupon_money":"10.00","need_money":"295.00","o_point":0,"note":"嘻嘻嘻庸人自扰之","order_sn":"GOODS2020051215892665125eba48503a219","add_date":"2020-05-12 14:55:12","deliver_way":1,"state":1}}
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
         * info : {"confirm_date":0,"state_str":"等待付款","deliver_address":{"consignee":"她婆婆无事献殷勤","deliver_mobile":"54649949","pname":"黑龙江省","cname":"哈尔滨市","dname":"道里区","address":"行行"},"store":{"store_id":42,"store_name":"湖州志裕","store_person_tel":""},"goods_list":[{"g_id":43,"g_img":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20200301/d6e8d9eed2598af883e92b1f6516a023c539d652_800x467.jpg","g_name":"乐拉泰国青柚金柚进口西施蜜柚2个4.5斤新鲜当季孕妇水果顺丰包邮","sku_names":"蓝色;M码","buy_num":2,"gp_price":"100.00","gp_point":0},{"g_id":43,"g_img":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20200301/d6e8d9eed2598af883e92b1f6516a023c539d652_800x467.jpg","g_name":"乐拉泰国青柚金柚进口西施蜜柚2个4.5斤新鲜当季孕妇水果顺丰包邮","sku_names":"白色;L码","buy_num":1,"gp_price":"105.00","gp_point":0}],"all_product_price":"305.00","deliver_fee":"0.00","coupon_money":"10.00","need_money":"295.00","o_point":0,"note":"嘻嘻嘻庸人自扰之","order_sn":"GOODS2020051215892665125eba48503a219","add_date":"2020-05-12 14:55:12","deliver_way":1,"state":1}
         */

        private InfoBean info;

        public InfoBean getInfo() {
            return info;
        }

        public void setInfo(InfoBean info) {
            this.info = info;
        }

        public static class InfoBean {
            /**
             * confirm_date : 0
             * state_str : 等待付款
             * deliver_address : {"consignee":"她婆婆无事献殷勤","deliver_mobile":"54649949","pname":"黑龙江省","cname":"哈尔滨市","dname":"道里区","address":"行行"}
             * store : {"store_id":42,"store_name":"湖州志裕","store_person_tel":""}
             * goods_list : [{"g_id":43,"g_img":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20200301/d6e8d9eed2598af883e92b1f6516a023c539d652_800x467.jpg","g_name":"乐拉泰国青柚金柚进口西施蜜柚2个4.5斤新鲜当季孕妇水果顺丰包邮","sku_names":"蓝色;M码","buy_num":2,"gp_price":"100.00","gp_point":0},{"g_id":43,"g_img":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20200301/d6e8d9eed2598af883e92b1f6516a023c539d652_800x467.jpg","g_name":"乐拉泰国青柚金柚进口西施蜜柚2个4.5斤新鲜当季孕妇水果顺丰包邮","sku_names":"白色;L码","buy_num":1,"gp_price":"105.00","gp_point":0}]
             * all_product_price : 305.00
             * deliver_fee : 0.00
             * coupon_money : 10.00
             * need_money : 295.00
             * o_point : 0
             * note : 嘻嘻嘻庸人自扰之
             * order_sn : GOODS2020051215892665125eba48503a219
             * add_date : 2020-05-12 14:55:12
             * deliver_way : 1
             * state : 1
             */

            private String confirm_date;
            private String state_str;
            private DeliverAddressBean deliver_address;
            private StoreBean store;
            private String all_product_price;
            private String deliver_fee;
            private String coupon_money;
            private String need_money;
            private int o_point;
            private String note;
            private String order_sn;
            private String add_date;
            private String pay_date;
            private int deliver_way;
            private int state;
            private String pay_way;
            private List<GoodsListBean> goods_list;
            private deliverInfoBeans deliver_info;

            public deliverInfoBeans getDeliver_info() {
                return deliver_info;
            }

            public void setDeliver_info(deliverInfoBeans deliver_info) {
                this.deliver_info = deliver_info;
            }

            public String getPay_way() {
                return pay_way;
            }

            public void setPay_way(String pay_way) {
                this.pay_way = pay_way;
            }

            public String getPay_date() {
                return pay_date;
            }

            public void setPay_date(String pay_date) {
                this.pay_date = pay_date;
            }

            public String getConfirm_date() {
                return confirm_date;
            }

            public void setConfirm_date(String confirm_date) {
                this.confirm_date = confirm_date;
            }

            public String getState_str() {
                return state_str;
            }

            public void setState_str(String state_str) {
                this.state_str = state_str;
            }

            public DeliverAddressBean getDeliver_address() {
                return deliver_address;
            }

            public void setDeliver_address(DeliverAddressBean deliver_address) {
                this.deliver_address = deliver_address;
            }

            public StoreBean getStore() {
                return store;
            }

            public void setStore(StoreBean store) {
                this.store = store;
            }

            public String getAll_product_price() {
                return all_product_price;
            }

            public void setAll_product_price(String all_product_price) {
                this.all_product_price = all_product_price;
            }

            public String getDeliver_fee() {
                return deliver_fee;
            }

            public void setDeliver_fee(String deliver_fee) {
                this.deliver_fee = deliver_fee;
            }

            public String getCoupon_money() {
                return coupon_money;
            }

            public void setCoupon_money(String coupon_money) {
                this.coupon_money = coupon_money;
            }

            public String getNeed_money() {
                return need_money;
            }

            public void setNeed_money(String need_money) {
                this.need_money = need_money;
            }

            public int getO_point() {
                return o_point;
            }

            public void setO_point(int o_point) {
                this.o_point = o_point;
            }

            public String getNote() {
                return note;
            }

            public void setNote(String note) {
                this.note = note;
            }

            public String getOrder_sn() {
                return order_sn;
            }

            public void setOrder_sn(String order_sn) {
                this.order_sn = order_sn;
            }

            public String getAdd_date() {
                return add_date;
            }

            public void setAdd_date(String add_date) {
                this.add_date = add_date;
            }

            public int getDeliver_way() {
                return deliver_way;
            }

            public void setDeliver_way(int deliver_way) {
                this.deliver_way = deliver_way;
            }

            public int getState() {
                return state;
            }

            public void setState(int state) {
                this.state = state;
            }

            public List<GoodsListBean> getGoods_list() {
                return goods_list;
            }

            public void setGoods_list(List<GoodsListBean> goods_list) {
                this.goods_list = goods_list;
            }

            public static class DeliverAddressBean {
                /**
                 * consignee : 她婆婆无事献殷勤
                 * deliver_mobile : 54649949
                 * pname : 黑龙江省
                 * cname : 哈尔滨市
                 * dname : 道里区
                 * address : 行行
                 */

                private String consignee;
                private String deliver_mobile;
                private String pname;
                private String cname;
                private String dname;
                private String address;

                public String getConsignee() {
                    return consignee;
                }

                public void setConsignee(String consignee) {
                    this.consignee = consignee;
                }

                public String getDeliver_mobile() {
                    return deliver_mobile;
                }

                public void setDeliver_mobile(String deliver_mobile) {
                    this.deliver_mobile = deliver_mobile;
                }

                public String getPname() {
                    return pname;
                }

                public void setPname(String pname) {
                    this.pname = pname;
                }

                public String getCname() {
                    return cname;
                }

                public void setCname(String cname) {
                    this.cname = cname;
                }

                public String getDname() {
                    return dname;
                }

                public void setDname(String dname) {
                    this.dname = dname;
                }

                public String getAddress() {
                    return address;
                }

                public void setAddress(String address) {
                    this.address = address;
                }
            }

            public static class deliverInfoBeans{

                /**
                 * deliver_company : 圆通快递
                 * deliver_company_code : yt
                 * deliver_num : YT4494003930938
                 * deliver_date : 1970-01-01 08:00:00
                 * info : {"time":"2020-05-04 18:52:57","context":"客户签收人: 凭取件码 已签收  感谢使用圆通速递，期待再次为您服务 如有疑问请联系：15356676953，投诉电话：0571-28177166"}
                 */

                private String deliver_company;
                private String deliver_company_code;
                private String deliver_num;
                private String deliver_date;
                private DeInfoBean info;

                public String getDeliver_company() {
                    return deliver_company;
                }

                public void setDeliver_company(String deliver_company) {
                    this.deliver_company = deliver_company;
                }

                public String getDeliver_company_code() {
                    return deliver_company_code;
                }

                public void setDeliver_company_code(String deliver_company_code) {
                    this.deliver_company_code = deliver_company_code;
                }

                public String getDeliver_num() {
                    return deliver_num;
                }

                public void setDeliver_num(String deliver_num) {
                    this.deliver_num = deliver_num;
                }

                public String getDeliver_date() {
                    return deliver_date;
                }

                public void setDeliver_date(String deliver_date) {
                    this.deliver_date = deliver_date;
                }

                public DeInfoBean getInfo() {
                    return info;
                }

                public void setInfo(DeInfoBean info) {
                    this.info = info;
                }

                public static class DeInfoBean {
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

            public static class StoreBean {
                /**
                 * store_id : 42
                 * store_name : 湖州志裕
                 * store_person_tel :
                 */

                private int store_id;
                private String store_name;
                private String store_person_tel;

                public int getStore_id() {
                    return store_id;
                }

                public void setStore_id(int store_id) {
                    this.store_id = store_id;
                }

                public String getStore_name() {
                    return store_name;
                }

                public void setStore_name(String store_name) {
                    this.store_name = store_name;
                }

                public String getStore_person_tel() {
                    return store_person_tel;
                }

                public void setStore_person_tel(String store_person_tel) {
                    this.store_person_tel = store_person_tel;
                }
            }

            public static class GoodsListBean {
                /**
                 * g_id : 43
                 * g_img : http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20200301/d6e8d9eed2598af883e92b1f6516a023c539d652_800x467.jpg
                 * g_name : 乐拉泰国青柚金柚进口西施蜜柚2个4.5斤新鲜当季孕妇水果顺丰包邮
                 * sku_names : 蓝色;M码
                 * buy_num : 2
                 * gp_price : 100.00
                 * gp_point : 0
                 */

                private int g_id;
                private String g_img;
                private String g_name;
                private String sku_names;
                private int buy_num;
                private String gp_price;
                private int gp_point;

                public int getG_id() {
                    return g_id;
                }

                public void setG_id(int g_id) {
                    this.g_id = g_id;
                }

                public String getG_img() {
                    return g_img;
                }

                public void setG_img(String g_img) {
                    this.g_img = g_img;
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

                public int getBuy_num() {
                    return buy_num;
                }

                public void setBuy_num(int buy_num) {
                    this.buy_num = buy_num;
                }

                public String getGp_price() {
                    return gp_price;
                }

                public void setGp_price(String gp_price) {
                    this.gp_price = gp_price;
                }

                public int getGp_point() {
                    return gp_point;
                }

                public void setGp_point(int gp_point) {
                    this.gp_point = gp_point;
                }
            }
        }
    }
}
