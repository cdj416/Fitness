package com.hongyuan.fitness.ui.shop.sbeans;

import android.util.Log;

import com.chad.library.adapter.base.entity.AbstractExpandableItem;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.hongyuan.fitness.base.BaseBean;
import com.hongyuan.fitness.ui.shop.sadapter.SnewOrdersAdapter;
import com.hongyuan.fitness.util.BaseUtil;
import com.hongyuan.fitness.util.BigDecimalUtils;

import java.util.ArrayList;
import java.util.List;

public class SnewOrderBeans extends BaseBean {

    /**
     * hasmore : false
     * curpage : 1
     * page_total : 1
     * data : {"list":[{"o_id":19279,"o_sn":"GOODS2020051215892684805eba5000c0992","o_pay_time":0,"o_name":"乐拉泰国青柚金柚进口西施蜜柚2个4.5斤新鲜当季孕妇水果顺丰包邮","o_img":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20200301/d6e8d9eed2598af883e92b1f6516a023c539d652_800x467.jpg","o_money":"205.00","o_point":0,"o_time":1589268480,"o_state":1,"o_pay_state":0,"o_type_code":"o_goods","o_out_id":1562,"o_coupon_money":"0.00","store_id":42,"o_deliver_way":1,"add_date":"2020-05-12 15:28:00","pay_date":"1970-01-01 08:00:00","o_pay_state_str":"未支付","o_state_str":"未支付","store_name":"湖州志裕","o_num":2,"o_price":"205.00","op_point":0,"deliver_state":0,"goods_list":[{"g_id":43,"g_img":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20200301/d6e8d9eed2598af883e92b1f6516a023c539d652_800x467.jpg","g_name":"乐拉泰国青柚金柚进口西施蜜柚2个4.5斤新鲜当季孕妇水果顺丰包邮","gp_price":"100.00","gp_point":0,"sku_names":"蓝色;M码","buy_num":1},{"g_id":43,"g_img":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20200301/d6e8d9eed2598af883e92b1f6516a023c539d652_800x467.jpg","g_name":"乐拉泰国青柚金柚进口西施蜜柚2个4.5斤新鲜当季孕妇水果顺丰包邮","gp_price":"105.00","gp_point":0,"sku_names":"白色;L码","buy_num":1}]},{"o_id":19277,"o_sn":"GOODS2020051215892683755eba4f973c194","o_pay_time":0,"o_name":"乐拉泰国青柚金柚进口西施蜜柚2个4.5斤新鲜当季孕妇水果顺丰包邮","o_img":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20200301/d6e8d9eed2598af883e92b1f6516a023c539d652_800x467.jpg","o_money":"185.00","o_point":0,"o_time":1589268375,"o_state":1,"o_pay_state":0,"o_type_code":"o_goods","o_out_id":1560,"o_coupon_money":"20.00","store_id":42,"o_deliver_way":2,"add_date":"2020-05-12 15:26:15","pay_date":"1970-01-01 08:00:00","o_pay_state_str":"未支付","o_state_str":"未支付","store_name":"湖州志裕","o_num":2,"o_price":"185.00","op_point":0,"deliver_state":0,"goods_list":[{"g_id":43,"g_img":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20200301/d6e8d9eed2598af883e92b1f6516a023c539d652_800x467.jpg","g_name":"乐拉泰国青柚金柚进口西施蜜柚2个4.5斤新鲜当季孕妇水果顺丰包邮","gp_price":"100.00","gp_point":0,"sku_names":"蓝色;M码","buy_num":1},{"g_id":43,"g_img":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20200301/d6e8d9eed2598af883e92b1f6516a023c539d652_800x467.jpg","g_name":"乐拉泰国青柚金柚进口西施蜜柚2个4.5斤新鲜当季孕妇水果顺丰包邮","gp_price":"105.00","gp_point":0,"sku_names":"白色;L码","buy_num":1}]}]}
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
        //需要使用的数据
        private List<MultiItemEntity> mList;
        /*
         * 处理下数据
         * */
        public List<MultiItemEntity> getmList(){
            if(list != null){
                mList = new ArrayList<>();

                for(ListBean listBean : list){
                    int goodsNum = 0;
                    String allPrice = "0";
                    int g_id = 0;
                    for(ListBean.GoodsListBean goodsListBean : listBean.getGoods_list()){
                        listBean.addSubItem(goodsListBean);
                        goodsNum += goodsListBean.getBuy_num();
                        allPrice = BigDecimalUtils.add(allPrice,BigDecimalUtils.mul(goodsListBean.getGp_price(),String.valueOf(goodsListBean.getBuy_num()),2),2);
                        g_id = goodsListBean.g_id;
                    }
                    mList.add(listBean);

                    //添加第三种类型数据
                    BottomBean bottomBean = new BottomBean();
                    bottomBean.setBuyNum(goodsNum);
                    bottomBean.setAllPrice(BaseUtil.getNoZoon(allPrice));
                    bottomBean.setStatus(listBean.getO_state());
                    bottomBean.setO_id(listBean.getO_id());
                    bottomBean.setG_id(g_id);
                    mList.add(bottomBean);
                }
            }

            return mList;
        }

        public static class BottomBean implements MultiItemEntity{
            // 订单状态  1是待支付 2已取消 3待发货，4已发货 5已提货 6待评价 8已完成 9是不能申请退款的终极完成
            public static final int STATU_PAY = 1;
            public static final int STATU_CANCEL = 2;
            public static final int STATU_DELIVERY = 3;
            public static final int STATU_SHIPPED = 4;
            public static final int STATU_PICKEDUP = 5;
            public static final int STATU_BE_EVALUATED = 6;
            public static final int STATU_COMPLETED = 8;
            public static final int STATU_ALL_COMPLETED = 9;

            private int buyNum;
            private String allPrice;
            private int status;
            private int o_id;
            private int g_id;

            public int getG_id() {
                return g_id;
            }

            public void setG_id(int g_id) {
                this.g_id = g_id;
            }

            public int getO_id() {
                return o_id;
            }

            public void setO_id(int o_id) {
                this.o_id = o_id;
            }

            public int getBuyNum() {
                return buyNum;
            }

            public void setBuyNum(int buyNum) {
                this.buyNum = buyNum;
            }

            public String getAllPrice() {
                return allPrice;
            }

            public void setAllPrice(String allPrice) {
                this.allPrice = allPrice;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            @Override
            public int getItemType() {
                return 2;
            }
        }

        /*******************************************************************************************/
        private List<ListBean> list;

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean extends AbstractExpandableItem<ListBean.GoodsListBean> implements MultiItemEntity {
            /**
             * o_id : 19279
             * o_sn : GOODS2020051215892684805eba5000c0992
             * o_pay_time : 0
             * o_name : 乐拉泰国青柚金柚进口西施蜜柚2个4.5斤新鲜当季孕妇水果顺丰包邮
             * o_img : http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20200301/d6e8d9eed2598af883e92b1f6516a023c539d652_800x467.jpg
             * o_money : 205.00
             * o_point : 0
             * o_time : 1589268480
             * o_state : 1
             * o_pay_state : 0
             * o_type_code : o_goods
             * o_out_id : 1562
             * o_coupon_money : 0.00
             * store_id : 42
             * o_deliver_way : 1
             * add_date : 2020-05-12 15:28:00
             * pay_date : 1970-01-01 08:00:00
             * o_pay_state_str : 未支付
             * o_state_str : 未支付
             * store_name : 湖州志裕
             * o_num : 2
             * o_price : 205.00
             * op_point : 0
             * deliver_state : 0
             * goods_list : [{"g_id":43,"g_img":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20200301/d6e8d9eed2598af883e92b1f6516a023c539d652_800x467.jpg","g_name":"乐拉泰国青柚金柚进口西施蜜柚2个4.5斤新鲜当季孕妇水果顺丰包邮","gp_price":"100.00","gp_point":0,"sku_names":"蓝色;M码","buy_num":1},{"g_id":43,"g_img":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20200301/d6e8d9eed2598af883e92b1f6516a023c539d652_800x467.jpg","g_name":"乐拉泰国青柚金柚进口西施蜜柚2个4.5斤新鲜当季孕妇水果顺丰包邮","gp_price":"105.00","gp_point":0,"sku_names":"白色;L码","buy_num":1}]
             */

            private int o_id;
            private String o_sn;
            private int o_pay_time;
            private String o_name;
            private String o_img;
            private String o_money;
            private int o_point;
            private int o_time;
            private int o_state;
            private int o_pay_state;
            private String o_type_code;
            private int o_out_id;
            private String o_coupon_money;
            private int store_id;
            private int o_deliver_way;
            private String add_date;
            private String pay_date;
            private String o_pay_state_str;
            private String o_state_str;
            private String store_name;
            private int o_num;
            private String o_price;
            private int op_point;
            private int deliver_state;
            private List<GoodsListBean> goods_list;

            public int getO_id() {
                return o_id;
            }

            public void setO_id(int o_id) {
                this.o_id = o_id;
            }

            public String getO_sn() {
                return o_sn;
            }

            public void setO_sn(String o_sn) {
                this.o_sn = o_sn;
            }

            public int getO_pay_time() {
                return o_pay_time;
            }

            public void setO_pay_time(int o_pay_time) {
                this.o_pay_time = o_pay_time;
            }

            public String getO_name() {
                return o_name;
            }

            public void setO_name(String o_name) {
                this.o_name = o_name;
            }

            public String getO_img() {
                return o_img;
            }

            public void setO_img(String o_img) {
                this.o_img = o_img;
            }

            public String getO_money() {
                return o_money;
            }

            public void setO_money(String o_money) {
                this.o_money = o_money;
            }

            public int getO_point() {
                return o_point;
            }

            public void setO_point(int o_point) {
                this.o_point = o_point;
            }

            public int getO_time() {
                return o_time;
            }

            public void setO_time(int o_time) {
                this.o_time = o_time;
            }

            public int getO_state() {
                return o_state;
            }

            public void setO_state(int o_state) {
                this.o_state = o_state;
            }

            public int getO_pay_state() {
                return o_pay_state;
            }

            public void setO_pay_state(int o_pay_state) {
                this.o_pay_state = o_pay_state;
            }

            public String getO_type_code() {
                return o_type_code;
            }

            public void setO_type_code(String o_type_code) {
                this.o_type_code = o_type_code;
            }

            public int getO_out_id() {
                return o_out_id;
            }

            public void setO_out_id(int o_out_id) {
                this.o_out_id = o_out_id;
            }

            public String getO_coupon_money() {
                return o_coupon_money;
            }

            public void setO_coupon_money(String o_coupon_money) {
                this.o_coupon_money = o_coupon_money;
            }

            public int getStore_id() {
                return store_id;
            }

            public void setStore_id(int store_id) {
                this.store_id = store_id;
            }

            public int getO_deliver_way() {
                return o_deliver_way;
            }

            public void setO_deliver_way(int o_deliver_way) {
                this.o_deliver_way = o_deliver_way;
            }

            public String getAdd_date() {
                return add_date;
            }

            public void setAdd_date(String add_date) {
                this.add_date = add_date;
            }

            public String getPay_date() {
                return pay_date;
            }

            public void setPay_date(String pay_date) {
                this.pay_date = pay_date;
            }

            public String getO_pay_state_str() {
                return o_pay_state_str;
            }

            public void setO_pay_state_str(String o_pay_state_str) {
                this.o_pay_state_str = o_pay_state_str;
            }

            public String getO_state_str() {
                return o_state_str;
            }

            public void setO_state_str(String o_state_str) {
                this.o_state_str = o_state_str;
            }

            public String getStore_name() {
                return store_name;
            }

            public void setStore_name(String store_name) {
                this.store_name = store_name;
            }

            public int getO_num() {
                return o_num;
            }

            public void setO_num(int o_num) {
                this.o_num = o_num;
            }

            public String getO_price() {
                return o_price;
            }

            public void setO_price(String o_price) {
                this.o_price = o_price;
            }

            public int getOp_point() {
                return op_point;
            }

            public void setOp_point(int op_point) {
                this.op_point = op_point;
            }

            public int getDeliver_state() {
                return deliver_state;
            }

            public void setDeliver_state(int deliver_state) {
                this.deliver_state = deliver_state;
            }

            public List<GoodsListBean> getGoods_list() {
                return goods_list;
            }

            public void setGoods_list(List<GoodsListBean> goods_list) {
                this.goods_list = goods_list;
            }

            @Override
            public int getLevel() {
                return 0;
            }

            @Override
            public int getItemType() {
                return 0;
            }

            public static class GoodsListBean implements MultiItemEntity{
                /**
                 * g_id : 43
                 * g_img : http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20200301/d6e8d9eed2598af883e92b1f6516a023c539d652_800x467.jpg
                 * g_name : 乐拉泰国青柚金柚进口西施蜜柚2个4.5斤新鲜当季孕妇水果顺丰包邮
                 * gp_price : 100.00
                 * gp_point : 0
                 * sku_names : 蓝色;M码
                 * buy_num : 1
                 */

                private int g_id;
                private String g_img;
                private String g_name;
                private String gp_price;
                private int gp_point;
                private String sku_names;
                private int buy_num;

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

                @Override
                public int getItemType() {
                    return 1;
                }
            }
        }
    }
}
