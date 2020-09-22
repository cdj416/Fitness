package com.hongyuan.fitness.ui.shop.sbeans;

import com.chad.library.adapter.base.entity.AbstractExpandableItem;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.hongyuan.fitness.base.BaseBean;
import com.hongyuan.fitness.ui.shop.sadapter.SorderDetailsAdapter;
import com.hongyuan.fitness.util.BaseUtil;
import com.hongyuan.fitness.util.BigDecimalUtils;

import java.util.ArrayList;
import java.util.List;

public class SorderDetailBeans extends BaseBean {

    /**
     * data : {"list":[{"store_id":42,"store_name":"湖州志裕","is_zt":1,"goods_list":[{"is_valid":1,"g_id":43,"cart_id":92,"buy_num":1,"g_img":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20200301/d6e8d9eed2598af883e92b1f6516a023c539d652_800x467.jpg","g_name":"乐拉泰国青柚金柚进口西施蜜柚2个4.5斤新鲜当季孕妇水果顺丰包邮","gp_id":141,"gp_price":"100.00","gp_ponit":0,"sku_names":"蓝色;M码"},{"is_valid":1,"g_id":43,"cart_id":93,"buy_num":1,"g_img":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20200301/d6e8d9eed2598af883e92b1f6516a023c539d652_800x467.jpg","g_name":"乐拉泰国青柚金柚进口西施蜜柚2个4.5斤新鲜当季孕妇水果顺丰包邮","gp_id":142,"gp_price":"105.00","gp_ponit":0,"sku_names":"白色;L码"}],"deliver_fee":0,"buy_all_num":2,"all_price":205,"all_point":0,"best_coupon":{"cm_id":1095,"coupon_money":"10.00"}},{"store_id":43,"store_name":"fdsafdsa","is_zt":0,"goods_list":[{"is_valid":1,"g_id":21,"cart_id":94,"buy_num":1,"g_img":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190727/2517b0f0b7dd3974b9b5d7bccb6736fc7e522c23_400x400.jpg","g_name":"运动水杯，保温高档不锈钢","gp_id":47,"gp_price":"59.00","gp_ponit":0,"sku_names":""},{"is_valid":1,"g_id":22,"cart_id":95,"buy_num":1,"g_img":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190727/e45640b4662b02d6f18d89566fc7e2e8fe51ee25_400x379.jpg","g_name":"运动包","gp_id":48,"gp_price":"158.00","gp_ponit":0,"sku_names":""},{"is_valid":1,"g_id":23,"cart_id":96,"buy_num":1,"g_img":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190727/1e0696325d1d51e414b3c40bff2e38a0bd26b726_400x403.jpg","g_name":"苏打水机套装","gp_id":49,"gp_price":"169.00","gp_ponit":0,"sku_names":""},{"is_valid":1,"g_id":24,"cart_id":97,"buy_num":1,"g_img":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190727/bebc38e971ebbe1803b5415b87e456c3d12be1bb_400x473.jpg","g_name":"麂皮绒橡胶瑜伽垫","gp_id":50,"gp_price":"199.00","gp_ponit":0,"sku_names":""}],"deliver_fee":0,"buy_all_num":4,"all_price":585,"all_point":0,"best_coupon":{}}]}
     */

    private DataBean data;

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
            if(mList == null){
                mList = new ArrayList<>();

                for(SorderDetailBeans.DataBean.ListBean dataBean : list){
                    int allNum = 0;
                    String allPrice = "0";//加上快递费的价格
                    String useAllPrice = "0";//计算后显示的价格

                    String gids = "";//所有商品id

                    for(SorderDetailBeans.DataBean.ListBean.GoodsListBean listBean : dataBean.getGoods_list()){
                        dataBean.addSubItem(listBean);
                        allNum += listBean.getBuy_num();
                        allPrice = BigDecimalUtils.add(allPrice,BigDecimalUtils.mul(listBean.getGp_price(),String.valueOf(listBean.getBuy_num()),2),2);

                        gids += "," + listBean.getG_id();
                    }
                    mList.add(dataBean);
                    //加上快递费用
                    useAllPrice = BigDecimalUtils.add(allPrice,String.valueOf(dataBean.getDeliver_fee()),2);

                    //减去红包费用
                    if(BaseUtil.isValue(dataBean.getBest_coupon().getCoupon_money())){
                        useAllPrice = BaseUtil.getNoZoon(BigDecimalUtils.sub(useAllPrice,dataBean.getBest_coupon().getCoupon_money(),2));
                    }

                    //添加第三种类型数据
                    BottomBean bottomBean = new BottomBean();
                    bottomBean.setDeliveryType(dataBean.getIs_zt());
                    bottomBean.setDelivery(BaseUtil.getNoZoon(dataBean.getDeliver_fee()));
                    bottomBean.setAddress("");
                    bottomBean.setNote("");
                    bottomBean.setStoreCoupon(BaseUtil.getNoZoon(dataBean.getBest_coupon().getCoupon_money()));
                    bottomBean.setAllNum(String.valueOf(allNum));
                    bottomBean.setAllPrice(BaseUtil.getNoZoon(allPrice));
                    bottomBean.setUseAllprice(BaseUtil.getNoZoon(useAllPrice));
                    bottomBean.setAllPoint(dataBean.getAll_point());
                    bottomBean.setDeliverWay(1);
                    bottomBean.setCmId(dataBean.getBest_coupon().getCm_id());

                    bottomBean.setStoreId(String.valueOf(dataBean.getStore_id()));
                    bottomBean.setGids(gids.substring(1));
                    mList.add(bottomBean);
                }
            }

            return mList;
        }

        /*
         * 获取商品的总积分
         * */
        public String getAllPoint(){
            String allPoint = "0";
            for(SorderDetailBeans.DataBean.ListBean dataBean : list){
                allPoint = BigDecimalUtils.add(allPoint,String.valueOf(dataBean.getAll_point()),2);
            }

            return BaseUtil.getNoZoon(allPoint);
        }

        /*
        * 是否有无效商品
        * */
        public boolean isInvalid(){
            for(SorderDetailBeans.DataBean.ListBean dataBean : list){
                for(SorderDetailBeans.DataBean.ListBean.GoodsListBean listBean : dataBean.getGoods_list()){
                    if(listBean.getIs_valid() == 0){
                        return true;
                    }
                }
            }

            return false;
        }

        public static class BottomBean implements MultiItemEntity{
            private int deliveryType;//是否支持自提
            private int deliverWay;//当前用户选择快递还是自提方式，1快递，2自提。
            private int ztAddressId;
            private String delivery;
            private String storeCoupon;
            private String note;
            private String address;
            private String allNum;
            private String allPrice;//总价(加上快递费之后的价格)
            private String storeId;
            private String useAllprice;//计算后显示的价格
            private int allPoint;//总积分
            private int cmId;//优惠券id
            private String gids;//商品id

            public String getGids() {
                return gids;
            }

            public void setGids(String gids) {
                this.gids = gids;
            }

            public int getAllPoint() {
                return allPoint;
            }

            public void setAllPoint(int allPoint) {
                this.allPoint = allPoint;
            }

            public int getCmId() {
                return cmId;
            }

            public void setCmId(int cmId) {
                this.cmId = cmId;
            }

            public int getZtAddressId() {
                return ztAddressId;
            }

            public void setZtAddressId(int ztAddressId) {
                this.ztAddressId = ztAddressId;
            }

            public int getDeliverWay() {
                return deliverWay;
            }

            public void setDeliverWay(int deliverWay) {
                this.deliverWay = deliverWay;
            }

            public String getUseAllprice() {
                return useAllprice;
            }

            public void setUseAllprice(String useAllprice) {
                this.useAllprice = useAllprice;
            }

            public String getStoreId() {
                return storeId;
            }

            public void setStoreId(String storeId) {
                this.storeId = storeId;
            }

            public String getAllNum() {
                return allNum;
            }

            public void setAllNum(String allNum) {
                this.allNum = allNum;
            }

            public String getAllPrice() {
                return allPrice;
            }

            public void setAllPrice(String allPrice) {
                this.allPrice = allPrice;
            }

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
            }

            public int getDeliveryType() {
                return deliveryType;
            }

            public void setDeliveryType(int deliveryType) {
                this.deliveryType = deliveryType;
            }

            public String getDelivery() {
                return delivery;
            }

            public void setDelivery(String delivery) {
                this.delivery = delivery;
            }

            public String getStoreCoupon() {
                return storeCoupon;
            }

            public void setStoreCoupon(String storeCoupon) {
                this.storeCoupon = storeCoupon;
            }

            public String getNote() {
                return note;
            }

            public void setNote(String note) {
                this.note = note;
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
             * store_id : 42
             * store_name : 湖州志裕
             * is_zt : 1
             * goods_list : [{"is_valid":1,"g_id":43,"cart_id":92,"buy_num":1,"g_img":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20200301/d6e8d9eed2598af883e92b1f6516a023c539d652_800x467.jpg","g_name":"乐拉泰国青柚金柚进口西施蜜柚2个4.5斤新鲜当季孕妇水果顺丰包邮","gp_id":141,"gp_price":"100.00","gp_ponit":0,"sku_names":"蓝色;M码"},{"is_valid":1,"g_id":43,"cart_id":93,"buy_num":1,"g_img":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20200301/d6e8d9eed2598af883e92b1f6516a023c539d652_800x467.jpg","g_name":"乐拉泰国青柚金柚进口西施蜜柚2个4.5斤新鲜当季孕妇水果顺丰包邮","gp_id":142,"gp_price":"105.00","gp_ponit":0,"sku_names":"白色;L码"}]
             * deliver_fee : 0
             * buy_all_num : 2
             * all_price : 205
             * all_point : 0
             * best_coupon : {"cm_id":1095,"coupon_money":"10.00"}
             */

            private int store_id;
            private String store_name;
            private int is_zt;
            private int deliver_fee;
            private int buy_all_num;
            private double all_price;
            private int all_point;
            private BestCouponBean best_coupon;
            private List<GoodsListBean> goods_list;

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

            public int getIs_zt() {
                return is_zt;
            }

            public void setIs_zt(int is_zt) {
                this.is_zt = is_zt;
            }

            public int getDeliver_fee() {
                return deliver_fee;
            }

            public void setDeliver_fee(int deliver_fee) {
                this.deliver_fee = deliver_fee;
            }

            public int getBuy_all_num() {
                return buy_all_num;
            }

            public void setBuy_all_num(int buy_all_num) {
                this.buy_all_num = buy_all_num;
            }

            public double getAll_price() {
                return all_price;
            }

            public void setAll_price(double all_price) {
                this.all_price = all_price;
            }

            public int getAll_point() {
                return all_point;
            }

            public void setAll_point(int all_point) {
                this.all_point = all_point;
            }

            public BestCouponBean getBest_coupon() {
                return best_coupon;
            }

            public void setBest_coupon(BestCouponBean best_coupon) {
                this.best_coupon = best_coupon;
            }

            public List<GoodsListBean> getGoods_list() {
                return goods_list;
            }

            public void setGoods_list(List<GoodsListBean> goods_list) {
                this.goods_list = goods_list;
            }

            @Override
            public int getItemType() {
                return 0;
            }

            @Override
            public int getLevel() {
                return 0;
            }

            public static class BestCouponBean {
                /**
                 * cm_id : 1095
                 * coupon_money : 10.00
                 */

                private int cm_id;
                private String coupon_money;

                public int getCm_id() {
                    return cm_id;
                }

                public void setCm_id(int cm_id) {
                    this.cm_id = cm_id;
                }

                public String getCoupon_money() {
                    return coupon_money;
                }

                public void setCoupon_money(String coupon_money) {
                    this.coupon_money = coupon_money;
                }
            }

            public static class GoodsListBean implements MultiItemEntity{
                /**
                 * is_valid : 1
                 * g_id : 43
                 * cart_id : 92
                 * buy_num : 1
                 * g_img : http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20200301/d6e8d9eed2598af883e92b1f6516a023c539d652_800x467.jpg
                 * g_name : 乐拉泰国青柚金柚进口西施蜜柚2个4.5斤新鲜当季孕妇水果顺丰包邮
                 * gp_id : 141
                 * gp_price : 100.00
                 * gp_ponit : 0
                 * sku_names : 蓝色;M码
                 */

                private int is_valid;
                private int g_id;
                private int cart_id;
                private int buy_num;
                private String g_img;
                private String g_name;
                private int gp_id;
                private String gp_price;
                private int gp_point;
                private String sku_names;


                //获取显示类型
                public int getShowType() {
                    if(BaseUtil.isValue(this.gp_price) && Double.parseDouble(this.gp_price) > 0 && this.gp_point > 0){
                        return SorderDetailsAdapter.SHOW_MONEY_POINT;
                    }else if(BaseUtil.isValue(this.gp_price) && Double.parseDouble(this.gp_price) > 0 && this.gp_point <= 0 ){
                        return SorderDetailsAdapter.SHOW_MONEY;
                    }else if((!BaseUtil.isValue(this.gp_price) || Double.parseDouble(this.gp_price) <= 0 ) && this.gp_point > 0){
                        return SorderDetailsAdapter.SHOW_POINT;
                    }else{
                        return SorderDetailsAdapter.SHOW_MONEY_POINT;
                    }
                }

                public int getGp_point() {
                    return gp_point;
                }

                public void setGp_point(int gp_point) {
                    this.gp_point = gp_point;
                }

                public int getIs_valid() {
                    return is_valid;
                }

                public void setIs_valid(int is_valid) {
                    this.is_valid = is_valid;
                }

                public int getG_id() {
                    return g_id;
                }

                public void setG_id(int g_id) {
                    this.g_id = g_id;
                }

                public int getCart_id() {
                    return cart_id;
                }

                public void setCart_id(int cart_id) {
                    this.cart_id = cart_id;
                }

                public int getBuy_num() {
                    return buy_num;
                }

                public void setBuy_num(int buy_num) {
                    this.buy_num = buy_num;
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

                public int getGp_id() {
                    return gp_id;
                }

                public void setGp_id(int gp_id) {
                    this.gp_id = gp_id;
                }

                public String getGp_price() {
                    return gp_price;
                }

                public void setGp_price(String gp_price) {
                    this.gp_price = gp_price;
                }

                public String getSku_names() {
                    return sku_names;
                }

                public void setSku_names(String sku_names) {
                    this.sku_names = sku_names;
                }

                @Override
                public int getItemType() {
                    return 1;
                }
            }
        }
    }
}
