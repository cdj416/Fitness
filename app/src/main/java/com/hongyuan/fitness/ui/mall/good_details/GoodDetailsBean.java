package com.hongyuan.fitness.ui.mall.good_details;

import com.hongyuan.fitness.base.BaseBean;

import java.io.Serializable;
import java.util.List;

public class GoodDetailsBean extends BaseBean implements Serializable{

    /**
     * data : {"info":{"g_id":6,"g_name":"dfasfdsadfsfds","g_second_name":"fdsa fdsfdsafdsa","g_add_time":1563872597,"g_state":1,"g_on_sale":1,"g_img":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190718/fe2319b1e3cd155c4c16714eb1f54a18b524d89a_1242x744.jpg","g_desc":"fdsafdsa fsd fdsafdsa","m_id":1,"store_id":42,"pid":0,"cid":0,"did":0,"g_zt_osids":"12,13","first_category_id":1,"second_category_id":5,"g_stock":23,"g_price":"0.01","g_marcket_price":"135.00","g_point":0,"g_update_time":0,"sku":[{"item":[{"sku_name":"M"},{"sku_name":"L"}],"sku_type_name":"尺码","sku_type_id":4},{"item":[{"sku_name":"白色"}],"sku_type_name":"颜色","sku_type_id":5}],"gp":[{"gp_id":16,"gp_img":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190718/fe2319b1e3cd155c4c16714eb1f54a18b524d89a_1242x744.jpg","gp_stock":100,"gp_price":"0.01","gp_marcket_price":"150.00","g_id":6,"gp_sn":"SN2019072315638725975d36cd55dce76","gp_state":1,"gp_sale_num":0,"gp_point":0},{"gp_id":17,"gp_img":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190718/fe2319b1e3cd155c4c16714eb1f54a18b524d89a_1242x744.jpg","gp_stock":50,"gp_price":"0.01","gp_marcket_price":"160.00","g_id":6,"gp_sn":"SN2019072315638725975d36cd55e4c7c","gp_state":1,"gp_sale_num":0,"gp_point":0}]}}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean implements Serializable{
        /**
         * info : {"g_id":6,"g_name":"dfasfdsadfsfds","g_second_name":"fdsa fdsfdsafdsa","g_add_time":1563872597,"g_state":1,"g_on_sale":1,"g_img":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190718/fe2319b1e3cd155c4c16714eb1f54a18b524d89a_1242x744.jpg","g_desc":"fdsafdsa fsd fdsafdsa","m_id":1,"store_id":42,"pid":0,"cid":0,"did":0,"g_zt_osids":"12,13","first_category_id":1,"second_category_id":5,"g_stock":23,"g_price":"0.01","g_marcket_price":"135.00","g_point":0,"g_update_time":0,"sku":[{"item":[{"sku_name":"M"},{"sku_name":"L"}],"sku_type_name":"尺码","sku_type_id":4},{"item":[{"sku_name":"白色"}],"sku_type_name":"颜色","sku_type_id":5}],"gp":[{"gp_id":16,"gp_img":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190718/fe2319b1e3cd155c4c16714eb1f54a18b524d89a_1242x744.jpg","gp_stock":100,"gp_price":"0.01","gp_marcket_price":"150.00","g_id":6,"gp_sn":"SN2019072315638725975d36cd55dce76","gp_state":1,"gp_sale_num":0,"gp_point":0},{"gp_id":17,"gp_img":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190718/fe2319b1e3cd155c4c16714eb1f54a18b524d89a_1242x744.jpg","gp_stock":50,"gp_price":"0.01","gp_marcket_price":"160.00","g_id":6,"gp_sn":"SN2019072315638725975d36cd55e4c7c","gp_state":1,"gp_sale_num":0,"gp_point":0}]}
         */

        private InfoBean info;

        public InfoBean getInfo() {
            return info;
        }

        public void setInfo(InfoBean info) {
            this.info = info;
        }

        public static class InfoBean implements Serializable{
            /**
             * g_id : 6
             * g_name : dfasfdsadfsfds
             * g_second_name : fdsa fdsfdsafdsa
             * g_add_time : 1563872597
             * g_state : 1
             * g_on_sale : 1
             * g_img : http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190718/fe2319b1e3cd155c4c16714eb1f54a18b524d89a_1242x744.jpg
             * g_desc : fdsafdsa fsd fdsafdsa
             * m_id : 1
             * store_id : 42
             * pid : 0
             * cid : 0
             * did : 0
             * g_zt_osids : 12,13
             * first_category_id : 1
             * second_category_id : 5
             * g_stock : 23
             * g_price : 0.01
             * g_marcket_price : 135.00
             * g_point : 0
             * g_update_time : 0
             * sku : [{"item":[{"sku_name":"M"},{"sku_name":"L"}],"sku_type_name":"尺码","sku_type_id":4},{"item":[{"sku_name":"白色"}],"sku_type_name":"颜色","sku_type_id":5}]
             * gp : [{"gp_id":16,"gp_img":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190718/fe2319b1e3cd155c4c16714eb1f54a18b524d89a_1242x744.jpg","gp_stock":100,"gp_price":"0.01","gp_marcket_price":"150.00","g_id":6,"gp_sn":"SN2019072315638725975d36cd55dce76","gp_state":1,"gp_sale_num":0,"gp_point":0},{"gp_id":17,"gp_img":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190718/fe2319b1e3cd155c4c16714eb1f54a18b524d89a_1242x744.jpg","gp_stock":50,"gp_price":"0.01","gp_marcket_price":"160.00","g_id":6,"gp_sn":"SN2019072315638725975d36cd55e4c7c","gp_state":1,"gp_sale_num":0,"gp_point":0}]
             */

            private int g_id;
            private String g_name;
            private String g_second_name;
            private int g_add_time;
            private int g_state;
            private int g_on_sale;
            private String g_img;
            private String g_desc;
            private int m_id;
            private int store_id;
            private int pid;
            private int cid;
            private int did;
            private String g_zt_osids;
            private int first_category_id;
            private int second_category_id;
            private int g_stock;
            private String g_price;
            private String g_marcket_price;
            private int g_point;
            private int g_update_time;
            private List<SkuBean> sku;
            private List<GpBean> gp;

            public int getG_id() {
                return g_id;
            }

            public void setG_id(int g_id) {
                this.g_id = g_id;
            }

            public String getG_name() {
                return g_name;
            }

            public void setG_name(String g_name) {
                this.g_name = g_name;
            }

            public String getG_second_name() {
                return g_second_name;
            }

            public void setG_second_name(String g_second_name) {
                this.g_second_name = g_second_name;
            }

            public int getG_add_time() {
                return g_add_time;
            }

            public void setG_add_time(int g_add_time) {
                this.g_add_time = g_add_time;
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

            public String getG_img() {
                return g_img;
            }

            public void setG_img(String g_img) {
                this.g_img = g_img;
            }

            public String getG_desc() {
                return g_desc;
            }

            public void setG_desc(String g_desc) {
                this.g_desc = g_desc;
            }

            public int getM_id() {
                return m_id;
            }

            public void setM_id(int m_id) {
                this.m_id = m_id;
            }

            public int getStore_id() {
                return store_id;
            }

            public void setStore_id(int store_id) {
                this.store_id = store_id;
            }

            public int getPid() {
                return pid;
            }

            public void setPid(int pid) {
                this.pid = pid;
            }

            public int getCid() {
                return cid;
            }

            public void setCid(int cid) {
                this.cid = cid;
            }

            public int getDid() {
                return did;
            }

            public void setDid(int did) {
                this.did = did;
            }

            public String getG_zt_osids() {
                return g_zt_osids;
            }

            public void setG_zt_osids(String g_zt_osids) {
                this.g_zt_osids = g_zt_osids;
            }

            public int getFirst_category_id() {
                return first_category_id;
            }

            public void setFirst_category_id(int first_category_id) {
                this.first_category_id = first_category_id;
            }

            public int getSecond_category_id() {
                return second_category_id;
            }

            public void setSecond_category_id(int second_category_id) {
                this.second_category_id = second_category_id;
            }

            public int getG_stock() {
                return g_stock;
            }

            public void setG_stock(int g_stock) {
                this.g_stock = g_stock;
            }

            public String getG_price() {
                return g_price;
            }

            public void setG_price(String g_price) {
                this.g_price = g_price;
            }

            public String getG_marcket_price() {
                return g_marcket_price;
            }

            public void setG_marcket_price(String g_marcket_price) {
                this.g_marcket_price = g_marcket_price;
            }

            public int getG_point() {
                return g_point;
            }

            public void setG_point(int g_point) {
                this.g_point = g_point;
            }

            public int getG_update_time() {
                return g_update_time;
            }

            public void setG_update_time(int g_update_time) {
                this.g_update_time = g_update_time;
            }

            public List<SkuBean> getSku() {
                return sku;
            }

            public void setSku(List<SkuBean> sku) {
                this.sku = sku;
            }

            public List<GpBean> getGp() {
                return gp;
            }

            public void setGp(List<GpBean> gp) {
                this.gp = gp;
            }

            public static class SkuBean implements Serializable{
                /**
                 * item : [{"sku_name":"M"},{"sku_name":"L"}]
                 * sku_type_name : 尺码
                 * sku_type_id : 4
                 */

                private String sku_type_name;
                private int sku_type_id;
                private String selectChildName;
                private List<ItemBean> item;

                public String getSelectChildName() {
                    return selectChildName;
                }

                public void setSelectChildName(String selectChildName) {
                    this.selectChildName = selectChildName;
                }

                public String getSku_type_name() {
                    return sku_type_name;
                }

                public void setSku_type_name(String sku_type_name) {
                    this.sku_type_name = sku_type_name;
                }

                public int getSku_type_id() {
                    return sku_type_id;
                }

                public void setSku_type_id(int sku_type_id) {
                    this.sku_type_id = sku_type_id;
                }

                public List<ItemBean> getItem() {
                    return item;
                }

                public void setItem(List<ItemBean> item) {
                    this.item = item;
                }

                public static class ItemBean implements Serializable{
                    /**
                     * sku_name : M
                     */

                    private String sku_name;
                    private boolean select;

                    public boolean isSelect() {
                        return select;
                    }

                    public void setSelect(boolean select) {
                        this.select = select;
                    }

                    public String getSku_name() {
                        return sku_name;
                    }

                    public void setSku_name(String sku_name) {
                        this.sku_name = sku_name;
                    }
                }
            }

            public static class GpBean implements Serializable{
                /**
                 * gp_id : 16
                 * gp_img : http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190718/fe2319b1e3cd155c4c16714eb1f54a18b524d89a_1242x744.jpg
                 * gp_stock : 100
                 * gp_price : 0.01
                 * gp_marcket_price : 150.00
                 * g_id : 6
                 * gp_sn : SN2019072315638725975d36cd55dce76
                 * gp_state : 1
                 * gp_sale_num : 0
                 * gp_point : 0
                 */

                private int gp_id;
                private String gp_img;
                private int gp_stock;
                private String gp_price;
                private String gp_marcket_price;
                private int g_id;
                private String gp_sn;
                private int gp_state;
                private int gp_sale_num;
                private int gp_point;

                public int getGp_id() {
                    return gp_id;
                }

                public void setGp_id(int gp_id) {
                    this.gp_id = gp_id;
                }

                public String getGp_img() {
                    return gp_img;
                }

                public void setGp_img(String gp_img) {
                    this.gp_img = gp_img;
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

                public String getGp_marcket_price() {
                    return gp_marcket_price;
                }

                public void setGp_marcket_price(String gp_marcket_price) {
                    this.gp_marcket_price = gp_marcket_price;
                }

                public int getG_id() {
                    return g_id;
                }

                public void setG_id(int g_id) {
                    this.g_id = g_id;
                }

                public String getGp_sn() {
                    return gp_sn;
                }

                public void setGp_sn(String gp_sn) {
                    this.gp_sn = gp_sn;
                }

                public int getGp_state() {
                    return gp_state;
                }

                public void setGp_state(int gp_state) {
                    this.gp_state = gp_state;
                }

                public int getGp_sale_num() {
                    return gp_sale_num;
                }

                public void setGp_sale_num(int gp_sale_num) {
                    this.gp_sale_num = gp_sale_num;
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
