package com.hongyuan.fitness.ui.shop.sbeans;

import com.chad.library.adapter.base.entity.AbstractExpandableItem;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.hongyuan.fitness.base.BaseBean;

import java.util.ArrayList;
import java.util.List;

public class ScartBeans extends BaseBean {


    /**
     * data : {"list":[{"store_id":42,"store_name":"湖州志裕","have_coupon":1,"goods_list":[{"cart_id":72,"gp_id":141,"buy_num":2,"g_id":43,"g_img":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20200301/d6e8d9eed2598af883e92b1f6516a023c539d652_800x467.jpg","g_name":"乐拉泰国青柚金柚进口西施蜜柚2个4.5斤新鲜当季孕妇水果顺丰包邮","g_state":1,"g_on_sale":1,"gp_stock":100,"gp_price":"100.00","gp_point":0,"gp_limit":20,"is_valid":1,"sku_names":"蓝色;M码"}]},{"store_id":43,"store_name":"fdsafdsa","have_coupon":0,"goods_list":[{"cart_id":83,"gp_id":48,"buy_num":1,"g_id":22,"g_img":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190727/e45640b4662b02d6f18d89566fc7e2e8fe51ee25_400x379.jpg","g_name":"运动包","g_state":1,"g_on_sale":1,"gp_stock":10,"gp_price":"158.00","gp_point":0,"gp_limit":1,"is_valid":1,"sku_names":""},{"cart_id":84,"gp_id":47,"buy_num":2,"g_id":21,"g_img":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190727/2517b0f0b7dd3974b9b5d7bccb6736fc7e522c23_400x400.jpg","g_name":"运动水杯，保温高档不锈钢","g_state":1,"g_on_sale":1,"gp_stock":10,"gp_price":"59.00","gp_point":0,"gp_limit":0,"is_valid":1,"sku_names":""},{"cart_id":85,"gp_id":49,"buy_num":1,"g_id":23,"g_img":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190727/1e0696325d1d51e414b3c40bff2e38a0bd26b726_400x403.jpg","g_name":"苏打水机套装","g_state":1,"g_on_sale":1,"gp_stock":6,"gp_price":"169.00","gp_point":0,"gp_limit":0,"is_valid":1,"sku_names":""},{"cart_id":86,"gp_id":50,"buy_num":2,"g_id":24,"g_img":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190727/bebc38e971ebbe1803b5415b87e456c3d12be1bb_400x473.jpg","g_name":"麂皮绒橡胶瑜伽垫","g_state":1,"g_on_sale":1,"gp_stock":10,"gp_price":"199.00","gp_point":0,"gp_limit":0,"is_valid":1,"sku_names":""}]}]}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        //购物车使用的数据集
        private List<MultiItemEntity> mList;
        /*
         * 处理下数据
         * */
        public List<MultiItemEntity> getmList(){
            if(mList == null){
                mList = new ArrayList<>();

                for(ScartBeans.DataBean.ListBean dataBean : list){
                    for(ScartBeans.DataBean.ListBean.GoodsListBean listBean : dataBean.getGoods_list()){
                        dataBean.addSubItem(listBean);
                    }
                    mList.add(dataBean);
                }
            }

            return mList;
        }

        private List<ListBean> list;

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean extends AbstractExpandableItem<ListBean.GoodsListBean> implements MultiItemEntity{
            /**
             * store_id : 42
             * store_name : 湖州志裕
             * have_coupon : 1
             * goods_list : [{"cart_id":72,"gp_id":141,"buy_num":2,"g_id":43,"g_img":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20200301/d6e8d9eed2598af883e92b1f6516a023c539d652_800x467.jpg","g_name":"乐拉泰国青柚金柚进口西施蜜柚2个4.5斤新鲜当季孕妇水果顺丰包邮","g_state":1,"g_on_sale":1,"gp_stock":100,"gp_price":"100.00","gp_point":0,"gp_limit":20,"is_valid":1,"sku_names":"蓝色;M码"}]
             */

            private int store_id;
            private String store_name;
            private int have_coupon;
            private boolean selectAll;
            private List<GoodsListBean> goods_list;

            public boolean isSelectAll() {
                return selectAll;
            }

            public void setSelectAll(boolean selectAll) {
                this.selectAll = selectAll;
            }

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

            public int getHave_coupon() {
                return have_coupon;
            }

            public void setHave_coupon(int have_coupon) {
                this.have_coupon = have_coupon;
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

            public static class GoodsListBean implements MultiItemEntity{
                /**
                 * cart_id : 72
                 * gp_id : 141
                 * buy_num : 2
                 * g_id : 43
                 * g_img : http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20200301/d6e8d9eed2598af883e92b1f6516a023c539d652_800x467.jpg
                 * g_name : 乐拉泰国青柚金柚进口西施蜜柚2个4.5斤新鲜当季孕妇水果顺丰包邮
                 * g_state : 1
                 * g_on_sale : 1
                 * gp_stock : 100
                 * gp_price : 100.00
                 * gp_point : 0
                 * gp_limit : 20
                 * is_valid : 1
                 * sku_names : 蓝色;M码
                 */

                private int cart_id;
                private int gp_id;
                private int buy_num;
                private int g_id;
                private String g_img;
                private String g_name;
                private int g_state;
                private int g_on_sale;
                private int gp_stock;
                private String gp_price;
                private int gp_point;
                private int gp_limit;
                private int is_valid;
                private String sku_names;
                private boolean select;

                public boolean isSelect() {
                    return select;
                }

                public void setSelect(boolean select) {
                    this.select = select;
                }

                public int getCart_id() {
                    return cart_id;
                }

                public void setCart_id(int cart_id) {
                    this.cart_id = cart_id;
                }

                public int getGp_id() {
                    return gp_id;
                }

                public void setGp_id(int gp_id) {
                    this.gp_id = gp_id;
                }

                public int getBuy_num() {
                    return buy_num;
                }

                public void setBuy_num(int buy_num) {
                    this.buy_num = buy_num;
                }

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

                public int getG_state() {
                    return g_state;
                }

                public void setG_state(int g_state) {
                    this.g_state = g_state;
                }

                public int getG_on_sale() {
                    return g_on_sale;
                }

                public void setG_on_sale(int g_on_sale) {
                    this.g_on_sale = g_on_sale;
                }

                public int getGp_stock() {
                    return gp_stock;
                }

                public void setGp_stock(int gp_stock) {
                    this.gp_stock = gp_stock;
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

                public int getGp_limit() {
                    return gp_limit;
                }

                public void setGp_limit(int gp_limit) {
                    this.gp_limit = gp_limit;
                }

                public int getIs_valid() {
                    return is_valid;
                }

                public void setIs_valid(int is_valid) {
                    this.is_valid = is_valid;
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
