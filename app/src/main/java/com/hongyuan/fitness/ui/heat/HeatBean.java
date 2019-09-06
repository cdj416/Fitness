package com.hongyuan.fitness.ui.heat;

import com.google.gson.annotations.SerializedName;
import com.hongyuan.fitness.base.BaseBean;

import java.util.List;

public class HeatBean extends BaseBean {


    /**
     * data : {"list":{"1":{"list":[{"fe_id":141,"f_id":831,"fe_type":1,"fu_id":0,"fe_num":"100.00","f_type":1,"f_img":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190726/99195ef2412679958ad63e77875bdaf6d28e4f31_1242x1119.jpg","f_name":"光明畅优优酪乳酸牛奶原味","fe_cal":"8500.00","fe_protein":"300.00","fe_fat":"320.00","fe_carbohydrate":"1100.00","fe_sugar":"0.00","fe_na":"0.00","fe_ge":0,"fe_time":1564588800,"fe_date":"2019-08-01"},{"fe_id":142,"f_id":828,"fe_type":1,"fu_id":0,"fe_num":"100.00","f_type":1,"f_img":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190726/0ba0516efcc37ad48ac3cb6f4b08b5bac4b411a5_1242x1539.jpg","f_name":"光明无蔗糖酸奶（桶装）","fe_cal":"6000.00","fe_protein":"280.00","fe_fat":"300.00","fe_carbohydrate":"550.00","fe_sugar":"0.00","fe_na":"0.00","fe_ge":0,"fe_time":1564588800,"fe_date":"2019-08-01"}],"cal":14500},"2":{"list":[{"fe_id":148,"f_id":828,"fe_type":2,"fu_id":0,"fe_num":"100.00","f_type":1,"f_img":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190726/0ba0516efcc37ad48ac3cb6f4b08b5bac4b411a5_1242x1539.jpg","f_name":"光明无蔗糖酸奶（桶装）","fe_cal":"6000.00","fe_protein":"280.00","fe_fat":"300.00","fe_carbohydrate":"550.00","fe_sugar":"0.00","fe_na":"0.00","fe_ge":0,"fe_time":1564588800,"fe_date":"2019-08-01"}],"cal":6000},"3":{"list":[{"fe_id":151,"f_id":802,"fe_type":3,"fu_id":0,"fe_num":"100.00","f_type":1,"f_img":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190726/8d7382909295a6ec576fbc108bd2ce48fb65a6e2_1242x1636.jpg","f_name":"雀巢高钙高铁牛奶","fe_cal":"4800.00","fe_protein":"140.00","fe_fat":"300.00","fe_carbohydrate":"570.00","fe_sugar":"0.00","fe_na":"0.00","fe_ge":0,"fe_time":1564588800,"fe_date":"2019-08-01"}],"cal":4800},"4":{"list":[{"fe_id":158,"f_id":831,"fe_type":4,"fu_id":0,"fe_num":"100.00","f_type":1,"f_img":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190726/99195ef2412679958ad63e77875bdaf6d28e4f31_1242x1119.jpg","f_name":"光明畅优优酪乳酸牛奶原味","fe_cal":"8500.00","fe_protein":"300.00","fe_fat":"320.00","fe_carbohydrate":"1100.00","fe_sugar":"0.00","fe_na":"0.00","fe_ge":0,"fe_time":1564588800,"fe_date":"2019-08-01"}],"cal":8500},"5":{"list":[{"fe_id":161,"f_id":827,"fe_type":5,"fu_id":0,"fe_num":"100.00","f_type":1,"f_img":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190726/f941d5c321435b66d29d4d44b2426255ae4f9a8c_1242x1229.jpg","f_name":"豆瓣辣酱","fe_cal":"7300.00","fe_protein":"360.00","fe_fat":"240.00","fe_carbohydrate":"1290.00","fe_sugar":"0.00","fe_na":"126870.00","fe_ge":0,"fe_time":1564588800,"fe_date":"2019-08-01"}],"cal":7300},"6":{"list":[{"fe_id":162,"f_id":830,"fe_type":6,"fu_id":0,"fe_num":"100.00","f_type":2,"f_img":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190726/a56ca51e488ed865e2841bed63fa866ebc966eb1_1242x1229.jpg","f_name":"晨恩 素豆豉鲮鱼","fe_cal":"338.00","fe_protein":"15000.00","fe_fat":"22000.00","fe_carbohydrate":"20000.00","fe_sugar":"0.00","fe_na":"922.00","fe_ge":0,"fe_time":1564588800,"fe_date":"2019-08-01"}],"cal":338}},"cal_all":41438,"ok_cal_all":"2400","consume_heat":0,"body_info":{"mbi_id":93,"mbi_height":"180.00","mbi_weight":"70","m_id":3,"mbi_birth":649486246,"mbi_sex":1}}
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
         * list : {"1":{"list":[{"fe_id":141,"f_id":831,"fe_type":1,"fu_id":0,"fe_num":"100.00","f_type":1,"f_img":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190726/99195ef2412679958ad63e77875bdaf6d28e4f31_1242x1119.jpg","f_name":"光明畅优优酪乳酸牛奶原味","fe_cal":"8500.00","fe_protein":"300.00","fe_fat":"320.00","fe_carbohydrate":"1100.00","fe_sugar":"0.00","fe_na":"0.00","fe_ge":0,"fe_time":1564588800,"fe_date":"2019-08-01"},{"fe_id":142,"f_id":828,"fe_type":1,"fu_id":0,"fe_num":"100.00","f_type":1,"f_img":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190726/0ba0516efcc37ad48ac3cb6f4b08b5bac4b411a5_1242x1539.jpg","f_name":"光明无蔗糖酸奶（桶装）","fe_cal":"6000.00","fe_protein":"280.00","fe_fat":"300.00","fe_carbohydrate":"550.00","fe_sugar":"0.00","fe_na":"0.00","fe_ge":0,"fe_time":1564588800,"fe_date":"2019-08-01"}],"cal":14500},"2":{"list":[{"fe_id":148,"f_id":828,"fe_type":2,"fu_id":0,"fe_num":"100.00","f_type":1,"f_img":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190726/0ba0516efcc37ad48ac3cb6f4b08b5bac4b411a5_1242x1539.jpg","f_name":"光明无蔗糖酸奶（桶装）","fe_cal":"6000.00","fe_protein":"280.00","fe_fat":"300.00","fe_carbohydrate":"550.00","fe_sugar":"0.00","fe_na":"0.00","fe_ge":0,"fe_time":1564588800,"fe_date":"2019-08-01"}],"cal":6000},"3":{"list":[{"fe_id":151,"f_id":802,"fe_type":3,"fu_id":0,"fe_num":"100.00","f_type":1,"f_img":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190726/8d7382909295a6ec576fbc108bd2ce48fb65a6e2_1242x1636.jpg","f_name":"雀巢高钙高铁牛奶","fe_cal":"4800.00","fe_protein":"140.00","fe_fat":"300.00","fe_carbohydrate":"570.00","fe_sugar":"0.00","fe_na":"0.00","fe_ge":0,"fe_time":1564588800,"fe_date":"2019-08-01"}],"cal":4800},"4":{"list":[{"fe_id":158,"f_id":831,"fe_type":4,"fu_id":0,"fe_num":"100.00","f_type":1,"f_img":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190726/99195ef2412679958ad63e77875bdaf6d28e4f31_1242x1119.jpg","f_name":"光明畅优优酪乳酸牛奶原味","fe_cal":"8500.00","fe_protein":"300.00","fe_fat":"320.00","fe_carbohydrate":"1100.00","fe_sugar":"0.00","fe_na":"0.00","fe_ge":0,"fe_time":1564588800,"fe_date":"2019-08-01"}],"cal":8500},"5":{"list":[{"fe_id":161,"f_id":827,"fe_type":5,"fu_id":0,"fe_num":"100.00","f_type":1,"f_img":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190726/f941d5c321435b66d29d4d44b2426255ae4f9a8c_1242x1229.jpg","f_name":"豆瓣辣酱","fe_cal":"7300.00","fe_protein":"360.00","fe_fat":"240.00","fe_carbohydrate":"1290.00","fe_sugar":"0.00","fe_na":"126870.00","fe_ge":0,"fe_time":1564588800,"fe_date":"2019-08-01"}],"cal":7300},"6":{"list":[{"fe_id":162,"f_id":830,"fe_type":6,"fu_id":0,"fe_num":"100.00","f_type":2,"f_img":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190726/a56ca51e488ed865e2841bed63fa866ebc966eb1_1242x1229.jpg","f_name":"晨恩 素豆豉鲮鱼","fe_cal":"338.00","fe_protein":"15000.00","fe_fat":"22000.00","fe_carbohydrate":"20000.00","fe_sugar":"0.00","fe_na":"922.00","fe_ge":0,"fe_time":1564588800,"fe_date":"2019-08-01"}],"cal":338}}
         * cal_all : 41438
         * ok_cal_all : 2400
         * consume_heat : 0
         * body_info : {"mbi_id":93,"mbi_height":"180.00","mbi_weight":"70","m_id":3,"mbi_birth":649486246,"mbi_sex":1}
         */

        private ListBeanXXXXXX list;
        private double cal_all;
        private String ok_cal_all;
        private double consume_heat;
        private BodyInfoBean body_info;

        public ListBeanXXXXXX getList() {
            return list;
        }

        public void setList(ListBeanXXXXXX list) {
            this.list = list;
        }

        public double getCal_all() {
            return cal_all;
        }

        public void setCal_all(double cal_all) {
            this.cal_all = cal_all;
        }

        public String getOk_cal_all() {
            return ok_cal_all;
        }

        public void setOk_cal_all(String ok_cal_all) {
            this.ok_cal_all = ok_cal_all;
        }

        public double getConsume_heat() {
            return consume_heat;
        }

        public void setConsume_heat(double consume_heat) {
            this.consume_heat = consume_heat;
        }

        public BodyInfoBean getBody_info() {
            return body_info;
        }

        public void setBody_info(BodyInfoBean body_info) {
            this.body_info = body_info;
        }

        public static class ListBeanXXXXXX {
            /**
             * 1 : {"list":[{"fe_id":141,"f_id":831,"fe_type":1,"fu_id":0,"fe_num":"100.00","f_type":1,"f_img":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190726/99195ef2412679958ad63e77875bdaf6d28e4f31_1242x1119.jpg","f_name":"光明畅优优酪乳酸牛奶原味","fe_cal":"8500.00","fe_protein":"300.00","fe_fat":"320.00","fe_carbohydrate":"1100.00","fe_sugar":"0.00","fe_na":"0.00","fe_ge":0,"fe_time":1564588800,"fe_date":"2019-08-01"},{"fe_id":142,"f_id":828,"fe_type":1,"fu_id":0,"fe_num":"100.00","f_type":1,"f_img":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190726/0ba0516efcc37ad48ac3cb6f4b08b5bac4b411a5_1242x1539.jpg","f_name":"光明无蔗糖酸奶（桶装）","fe_cal":"6000.00","fe_protein":"280.00","fe_fat":"300.00","fe_carbohydrate":"550.00","fe_sugar":"0.00","fe_na":"0.00","fe_ge":0,"fe_time":1564588800,"fe_date":"2019-08-01"}],"cal":14500}
             * 2 : {"list":[{"fe_id":148,"f_id":828,"fe_type":2,"fu_id":0,"fe_num":"100.00","f_type":1,"f_img":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190726/0ba0516efcc37ad48ac3cb6f4b08b5bac4b411a5_1242x1539.jpg","f_name":"光明无蔗糖酸奶（桶装）","fe_cal":"6000.00","fe_protein":"280.00","fe_fat":"300.00","fe_carbohydrate":"550.00","fe_sugar":"0.00","fe_na":"0.00","fe_ge":0,"fe_time":1564588800,"fe_date":"2019-08-01"}],"cal":6000}
             * 3 : {"list":[{"fe_id":151,"f_id":802,"fe_type":3,"fu_id":0,"fe_num":"100.00","f_type":1,"f_img":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190726/8d7382909295a6ec576fbc108bd2ce48fb65a6e2_1242x1636.jpg","f_name":"雀巢高钙高铁牛奶","fe_cal":"4800.00","fe_protein":"140.00","fe_fat":"300.00","fe_carbohydrate":"570.00","fe_sugar":"0.00","fe_na":"0.00","fe_ge":0,"fe_time":1564588800,"fe_date":"2019-08-01"}],"cal":4800}
             * 4 : {"list":[{"fe_id":158,"f_id":831,"fe_type":4,"fu_id":0,"fe_num":"100.00","f_type":1,"f_img":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190726/99195ef2412679958ad63e77875bdaf6d28e4f31_1242x1119.jpg","f_name":"光明畅优优酪乳酸牛奶原味","fe_cal":"8500.00","fe_protein":"300.00","fe_fat":"320.00","fe_carbohydrate":"1100.00","fe_sugar":"0.00","fe_na":"0.00","fe_ge":0,"fe_time":1564588800,"fe_date":"2019-08-01"}],"cal":8500}
             * 5 : {"list":[{"fe_id":161,"f_id":827,"fe_type":5,"fu_id":0,"fe_num":"100.00","f_type":1,"f_img":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190726/f941d5c321435b66d29d4d44b2426255ae4f9a8c_1242x1229.jpg","f_name":"豆瓣辣酱","fe_cal":"7300.00","fe_protein":"360.00","fe_fat":"240.00","fe_carbohydrate":"1290.00","fe_sugar":"0.00","fe_na":"126870.00","fe_ge":0,"fe_time":1564588800,"fe_date":"2019-08-01"}],"cal":7300}
             * 6 : {"list":[{"fe_id":162,"f_id":830,"fe_type":6,"fu_id":0,"fe_num":"100.00","f_type":2,"f_img":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190726/a56ca51e488ed865e2841bed63fa866ebc966eb1_1242x1229.jpg","f_name":"晨恩 素豆豉鲮鱼","fe_cal":"338.00","fe_protein":"15000.00","fe_fat":"22000.00","fe_carbohydrate":"20000.00","fe_sugar":"0.00","fe_na":"922.00","fe_ge":0,"fe_time":1564588800,"fe_date":"2019-08-01"}],"cal":338}
             */

            @SerializedName("1")
            private _$1Bean _$1;
            @SerializedName("2")
            private _$2Bean _$2;
            @SerializedName("3")
            private _$3Bean _$3;
            @SerializedName("4")
            private _$4Bean _$4;
            @SerializedName("5")
            private _$5Bean _$5;
            @SerializedName("6")
            private _$6Bean _$6;

            public _$1Bean get_$1() {
                return _$1;
            }

            public void set_$1(_$1Bean _$1) {
                this._$1 = _$1;
            }

            public _$2Bean get_$2() {
                return _$2;
            }

            public void set_$2(_$2Bean _$2) {
                this._$2 = _$2;
            }

            public _$3Bean get_$3() {
                return _$3;
            }

            public void set_$3(_$3Bean _$3) {
                this._$3 = _$3;
            }

            public _$4Bean get_$4() {
                return _$4;
            }

            public void set_$4(_$4Bean _$4) {
                this._$4 = _$4;
            }

            public _$5Bean get_$5() {
                return _$5;
            }

            public void set_$5(_$5Bean _$5) {
                this._$5 = _$5;
            }

            public _$6Bean get_$6() {
                return _$6;
            }

            public void set_$6(_$6Bean _$6) {
                this._$6 = _$6;
            }

            public static class _$1Bean {
                /**
                 * list : [{"fe_id":141,"f_id":831,"fe_type":1,"fu_id":0,"fe_num":"100.00","f_type":1,"f_img":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190726/99195ef2412679958ad63e77875bdaf6d28e4f31_1242x1119.jpg","f_name":"光明畅优优酪乳酸牛奶原味","fe_cal":"8500.00","fe_protein":"300.00","fe_fat":"320.00","fe_carbohydrate":"1100.00","fe_sugar":"0.00","fe_na":"0.00","fe_ge":0,"fe_time":1564588800,"fe_date":"2019-08-01"},{"fe_id":142,"f_id":828,"fe_type":1,"fu_id":0,"fe_num":"100.00","f_type":1,"f_img":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190726/0ba0516efcc37ad48ac3cb6f4b08b5bac4b411a5_1242x1539.jpg","f_name":"光明无蔗糖酸奶（桶装）","fe_cal":"6000.00","fe_protein":"280.00","fe_fat":"300.00","fe_carbohydrate":"550.00","fe_sugar":"0.00","fe_na":"0.00","fe_ge":0,"fe_time":1564588800,"fe_date":"2019-08-01"}]
                 * cal : 14500
                 */

                private double cal;
                private List<ListBean> list;

                public double getCal() {
                    return cal;
                }

                public void setCal(double cal) {
                    this.cal = cal;
                }

                public List<ListBean> getList() {
                    return list;
                }

                public void setList(List<ListBean> list) {
                    this.list = list;
                }

                public static class ListBean {
                    /**
                     * fe_id : 141
                     * f_id : 831
                     * fe_type : 1
                     * fu_id : 0
                     * fe_num : 100.00
                     * f_type : 1
                     * f_img : http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190726/99195ef2412679958ad63e77875bdaf6d28e4f31_1242x1119.jpg
                     * f_name : 光明畅优优酪乳酸牛奶原味
                     * fe_cal : 8500.00
                     * fe_protein : 300.00
                     * fe_fat : 320.00
                     * fe_carbohydrate : 1100.00
                     * fe_sugar : 0.00
                     * fe_na : 0.00
                     * fe_ge : 0
                     * fe_time : 1564588800
                     * fe_date : 2019-08-01
                     */

                    private int fe_id;
                    private int f_id;
                    private int fe_type;
                    private int fu_id;
                    private String fe_num;
                    private int f_type;
                    private String f_img;
                    private String f_name;
                    private String fe_cal;
                    private String fe_protein;
                    private String fe_fat;
                    private String fe_carbohydrate;
                    private String fe_sugar;
                    private String fe_na;
                    private int fe_ge;
                    private int fe_time;
                    private String fe_date;

                    public int getFe_id() {
                        return fe_id;
                    }

                    public void setFe_id(int fe_id) {
                        this.fe_id = fe_id;
                    }

                    public int getF_id() {
                        return f_id;
                    }

                    public void setF_id(int f_id) {
                        this.f_id = f_id;
                    }

                    public int getFe_type() {
                        return fe_type;
                    }

                    public void setFe_type(int fe_type) {
                        this.fe_type = fe_type;
                    }

                    public int getFu_id() {
                        return fu_id;
                    }

                    public void setFu_id(int fu_id) {
                        this.fu_id = fu_id;
                    }

                    public String getFe_num() {
                        return fe_num;
                    }

                    public void setFe_num(String fe_num) {
                        this.fe_num = fe_num;
                    }

                    public int getF_type() {
                        return f_type;
                    }

                    public void setF_type(int f_type) {
                        this.f_type = f_type;
                    }

                    public String getF_img() {
                        return f_img;
                    }

                    public void setF_img(String f_img) {
                        this.f_img = f_img;
                    }

                    public String getF_name() {
                        return f_name;
                    }

                    public void setF_name(String f_name) {
                        this.f_name = f_name;
                    }

                    public String getFe_cal() {
                        return fe_cal;
                    }

                    public void setFe_cal(String fe_cal) {
                        this.fe_cal = fe_cal;
                    }

                    public String getFe_protein() {
                        return fe_protein;
                    }

                    public void setFe_protein(String fe_protein) {
                        this.fe_protein = fe_protein;
                    }

                    public String getFe_fat() {
                        return fe_fat;
                    }

                    public void setFe_fat(String fe_fat) {
                        this.fe_fat = fe_fat;
                    }

                    public String getFe_carbohydrate() {
                        return fe_carbohydrate;
                    }

                    public void setFe_carbohydrate(String fe_carbohydrate) {
                        this.fe_carbohydrate = fe_carbohydrate;
                    }

                    public String getFe_sugar() {
                        return fe_sugar;
                    }

                    public void setFe_sugar(String fe_sugar) {
                        this.fe_sugar = fe_sugar;
                    }

                    public String getFe_na() {
                        return fe_na;
                    }

                    public void setFe_na(String fe_na) {
                        this.fe_na = fe_na;
                    }

                    public int getFe_ge() {
                        return fe_ge;
                    }

                    public void setFe_ge(int fe_ge) {
                        this.fe_ge = fe_ge;
                    }

                    public int getFe_time() {
                        return fe_time;
                    }

                    public void setFe_time(int fe_time) {
                        this.fe_time = fe_time;
                    }

                    public String getFe_date() {
                        return fe_date;
                    }

                    public void setFe_date(String fe_date) {
                        this.fe_date = fe_date;
                    }
                }
            }

            public static class _$2Bean {
                /**
                 * list : [{"fe_id":148,"f_id":828,"fe_type":2,"fu_id":0,"fe_num":"100.00","f_type":1,"f_img":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190726/0ba0516efcc37ad48ac3cb6f4b08b5bac4b411a5_1242x1539.jpg","f_name":"光明无蔗糖酸奶（桶装）","fe_cal":"6000.00","fe_protein":"280.00","fe_fat":"300.00","fe_carbohydrate":"550.00","fe_sugar":"0.00","fe_na":"0.00","fe_ge":0,"fe_time":1564588800,"fe_date":"2019-08-01"}]
                 * cal : 6000
                 */

                private double cal;
                private List<ListBeanX> list;

                public double getCal() {
                    return cal;
                }

                public void setCal(double cal) {
                    this.cal = cal;
                }

                public List<ListBeanX> getList() {
                    return list;
                }

                public void setList(List<ListBeanX> list) {
                    this.list = list;
                }

                public static class ListBeanX {
                    /**
                     * fe_id : 148
                     * f_id : 828
                     * fe_type : 2
                     * fu_id : 0
                     * fe_num : 100.00
                     * f_type : 1
                     * f_img : http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190726/0ba0516efcc37ad48ac3cb6f4b08b5bac4b411a5_1242x1539.jpg
                     * f_name : 光明无蔗糖酸奶（桶装）
                     * fe_cal : 6000.00
                     * fe_protein : 280.00
                     * fe_fat : 300.00
                     * fe_carbohydrate : 550.00
                     * fe_sugar : 0.00
                     * fe_na : 0.00
                     * fe_ge : 0
                     * fe_time : 1564588800
                     * fe_date : 2019-08-01
                     */

                    private int fe_id;
                    private int f_id;
                    private int fe_type;
                    private int fu_id;
                    private String fe_num;
                    private int f_type;
                    private String f_img;
                    private String f_name;
                    private String fe_cal;
                    private String fe_protein;
                    private String fe_fat;
                    private String fe_carbohydrate;
                    private String fe_sugar;
                    private String fe_na;
                    private int fe_ge;
                    private int fe_time;
                    private String fe_date;

                    public int getFe_id() {
                        return fe_id;
                    }

                    public void setFe_id(int fe_id) {
                        this.fe_id = fe_id;
                    }

                    public int getF_id() {
                        return f_id;
                    }

                    public void setF_id(int f_id) {
                        this.f_id = f_id;
                    }

                    public int getFe_type() {
                        return fe_type;
                    }

                    public void setFe_type(int fe_type) {
                        this.fe_type = fe_type;
                    }

                    public int getFu_id() {
                        return fu_id;
                    }

                    public void setFu_id(int fu_id) {
                        this.fu_id = fu_id;
                    }

                    public String getFe_num() {
                        return fe_num;
                    }

                    public void setFe_num(String fe_num) {
                        this.fe_num = fe_num;
                    }

                    public int getF_type() {
                        return f_type;
                    }

                    public void setF_type(int f_type) {
                        this.f_type = f_type;
                    }

                    public String getF_img() {
                        return f_img;
                    }

                    public void setF_img(String f_img) {
                        this.f_img = f_img;
                    }

                    public String getF_name() {
                        return f_name;
                    }

                    public void setF_name(String f_name) {
                        this.f_name = f_name;
                    }

                    public String getFe_cal() {
                        return fe_cal;
                    }

                    public void setFe_cal(String fe_cal) {
                        this.fe_cal = fe_cal;
                    }

                    public String getFe_protein() {
                        return fe_protein;
                    }

                    public void setFe_protein(String fe_protein) {
                        this.fe_protein = fe_protein;
                    }

                    public String getFe_fat() {
                        return fe_fat;
                    }

                    public void setFe_fat(String fe_fat) {
                        this.fe_fat = fe_fat;
                    }

                    public String getFe_carbohydrate() {
                        return fe_carbohydrate;
                    }

                    public void setFe_carbohydrate(String fe_carbohydrate) {
                        this.fe_carbohydrate = fe_carbohydrate;
                    }

                    public String getFe_sugar() {
                        return fe_sugar;
                    }

                    public void setFe_sugar(String fe_sugar) {
                        this.fe_sugar = fe_sugar;
                    }

                    public String getFe_na() {
                        return fe_na;
                    }

                    public void setFe_na(String fe_na) {
                        this.fe_na = fe_na;
                    }

                    public int getFe_ge() {
                        return fe_ge;
                    }

                    public void setFe_ge(int fe_ge) {
                        this.fe_ge = fe_ge;
                    }

                    public int getFe_time() {
                        return fe_time;
                    }

                    public void setFe_time(int fe_time) {
                        this.fe_time = fe_time;
                    }

                    public String getFe_date() {
                        return fe_date;
                    }

                    public void setFe_date(String fe_date) {
                        this.fe_date = fe_date;
                    }
                }
            }

            public static class _$3Bean {
                /**
                 * list : [{"fe_id":151,"f_id":802,"fe_type":3,"fu_id":0,"fe_num":"100.00","f_type":1,"f_img":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190726/8d7382909295a6ec576fbc108bd2ce48fb65a6e2_1242x1636.jpg","f_name":"雀巢高钙高铁牛奶","fe_cal":"4800.00","fe_protein":"140.00","fe_fat":"300.00","fe_carbohydrate":"570.00","fe_sugar":"0.00","fe_na":"0.00","fe_ge":0,"fe_time":1564588800,"fe_date":"2019-08-01"}]
                 * cal : 4800
                 */

                private double cal;
                private List<ListBeanXX> list;

                public double getCal() {
                    return cal;
                }

                public void setCal(double cal) {
                    this.cal = cal;
                }

                public List<ListBeanXX> getList() {
                    return list;
                }

                public void setList(List<ListBeanXX> list) {
                    this.list = list;
                }

                public static class ListBeanXX {
                    /**
                     * fe_id : 151
                     * f_id : 802
                     * fe_type : 3
                     * fu_id : 0
                     * fe_num : 100.00
                     * f_type : 1
                     * f_img : http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190726/8d7382909295a6ec576fbc108bd2ce48fb65a6e2_1242x1636.jpg
                     * f_name : 雀巢高钙高铁牛奶
                     * fe_cal : 4800.00
                     * fe_protein : 140.00
                     * fe_fat : 300.00
                     * fe_carbohydrate : 570.00
                     * fe_sugar : 0.00
                     * fe_na : 0.00
                     * fe_ge : 0
                     * fe_time : 1564588800
                     * fe_date : 2019-08-01
                     */

                    private int fe_id;
                    private int f_id;
                    private int fe_type;
                    private int fu_id;
                    private String fe_num;
                    private int f_type;
                    private String f_img;
                    private String f_name;
                    private String fe_cal;
                    private String fe_protein;
                    private String fe_fat;
                    private String fe_carbohydrate;
                    private String fe_sugar;
                    private String fe_na;
                    private int fe_ge;
                    private int fe_time;
                    private String fe_date;

                    public int getFe_id() {
                        return fe_id;
                    }

                    public void setFe_id(int fe_id) {
                        this.fe_id = fe_id;
                    }

                    public int getF_id() {
                        return f_id;
                    }

                    public void setF_id(int f_id) {
                        this.f_id = f_id;
                    }

                    public int getFe_type() {
                        return fe_type;
                    }

                    public void setFe_type(int fe_type) {
                        this.fe_type = fe_type;
                    }

                    public int getFu_id() {
                        return fu_id;
                    }

                    public void setFu_id(int fu_id) {
                        this.fu_id = fu_id;
                    }

                    public String getFe_num() {
                        return fe_num;
                    }

                    public void setFe_num(String fe_num) {
                        this.fe_num = fe_num;
                    }

                    public int getF_type() {
                        return f_type;
                    }

                    public void setF_type(int f_type) {
                        this.f_type = f_type;
                    }

                    public String getF_img() {
                        return f_img;
                    }

                    public void setF_img(String f_img) {
                        this.f_img = f_img;
                    }

                    public String getF_name() {
                        return f_name;
                    }

                    public void setF_name(String f_name) {
                        this.f_name = f_name;
                    }

                    public String getFe_cal() {
                        return fe_cal;
                    }

                    public void setFe_cal(String fe_cal) {
                        this.fe_cal = fe_cal;
                    }

                    public String getFe_protein() {
                        return fe_protein;
                    }

                    public void setFe_protein(String fe_protein) {
                        this.fe_protein = fe_protein;
                    }

                    public String getFe_fat() {
                        return fe_fat;
                    }

                    public void setFe_fat(String fe_fat) {
                        this.fe_fat = fe_fat;
                    }

                    public String getFe_carbohydrate() {
                        return fe_carbohydrate;
                    }

                    public void setFe_carbohydrate(String fe_carbohydrate) {
                        this.fe_carbohydrate = fe_carbohydrate;
                    }

                    public String getFe_sugar() {
                        return fe_sugar;
                    }

                    public void setFe_sugar(String fe_sugar) {
                        this.fe_sugar = fe_sugar;
                    }

                    public String getFe_na() {
                        return fe_na;
                    }

                    public void setFe_na(String fe_na) {
                        this.fe_na = fe_na;
                    }

                    public int getFe_ge() {
                        return fe_ge;
                    }

                    public void setFe_ge(int fe_ge) {
                        this.fe_ge = fe_ge;
                    }

                    public int getFe_time() {
                        return fe_time;
                    }

                    public void setFe_time(int fe_time) {
                        this.fe_time = fe_time;
                    }

                    public String getFe_date() {
                        return fe_date;
                    }

                    public void setFe_date(String fe_date) {
                        this.fe_date = fe_date;
                    }
                }
            }

            public static class _$4Bean {
                /**
                 * list : [{"fe_id":158,"f_id":831,"fe_type":4,"fu_id":0,"fe_num":"100.00","f_type":1,"f_img":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190726/99195ef2412679958ad63e77875bdaf6d28e4f31_1242x1119.jpg","f_name":"光明畅优优酪乳酸牛奶原味","fe_cal":"8500.00","fe_protein":"300.00","fe_fat":"320.00","fe_carbohydrate":"1100.00","fe_sugar":"0.00","fe_na":"0.00","fe_ge":0,"fe_time":1564588800,"fe_date":"2019-08-01"}]
                 * cal : 8500
                 */

                private double cal;
                private List<ListBeanXXX> list;

                public double getCal() {
                    return cal;
                }

                public void setCal(double cal) {
                    this.cal = cal;
                }

                public List<ListBeanXXX> getList() {
                    return list;
                }

                public void setList(List<ListBeanXXX> list) {
                    this.list = list;
                }

                public static class ListBeanXXX {
                    /**
                     * fe_id : 158
                     * f_id : 831
                     * fe_type : 4
                     * fu_id : 0
                     * fe_num : 100.00
                     * f_type : 1
                     * f_img : http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190726/99195ef2412679958ad63e77875bdaf6d28e4f31_1242x1119.jpg
                     * f_name : 光明畅优优酪乳酸牛奶原味
                     * fe_cal : 8500.00
                     * fe_protein : 300.00
                     * fe_fat : 320.00
                     * fe_carbohydrate : 1100.00
                     * fe_sugar : 0.00
                     * fe_na : 0.00
                     * fe_ge : 0
                     * fe_time : 1564588800
                     * fe_date : 2019-08-01
                     */

                    private int fe_id;
                    private int f_id;
                    private int fe_type;
                    private int fu_id;
                    private String fe_num;
                    private int f_type;
                    private String f_img;
                    private String f_name;
                    private String fe_cal;
                    private String fe_protein;
                    private String fe_fat;
                    private String fe_carbohydrate;
                    private String fe_sugar;
                    private String fe_na;
                    private int fe_ge;
                    private int fe_time;
                    private String fe_date;

                    public int getFe_id() {
                        return fe_id;
                    }

                    public void setFe_id(int fe_id) {
                        this.fe_id = fe_id;
                    }

                    public int getF_id() {
                        return f_id;
                    }

                    public void setF_id(int f_id) {
                        this.f_id = f_id;
                    }

                    public int getFe_type() {
                        return fe_type;
                    }

                    public void setFe_type(int fe_type) {
                        this.fe_type = fe_type;
                    }

                    public int getFu_id() {
                        return fu_id;
                    }

                    public void setFu_id(int fu_id) {
                        this.fu_id = fu_id;
                    }

                    public String getFe_num() {
                        return fe_num;
                    }

                    public void setFe_num(String fe_num) {
                        this.fe_num = fe_num;
                    }

                    public int getF_type() {
                        return f_type;
                    }

                    public void setF_type(int f_type) {
                        this.f_type = f_type;
                    }

                    public String getF_img() {
                        return f_img;
                    }

                    public void setF_img(String f_img) {
                        this.f_img = f_img;
                    }

                    public String getF_name() {
                        return f_name;
                    }

                    public void setF_name(String f_name) {
                        this.f_name = f_name;
                    }

                    public String getFe_cal() {
                        return fe_cal;
                    }

                    public void setFe_cal(String fe_cal) {
                        this.fe_cal = fe_cal;
                    }

                    public String getFe_protein() {
                        return fe_protein;
                    }

                    public void setFe_protein(String fe_protein) {
                        this.fe_protein = fe_protein;
                    }

                    public String getFe_fat() {
                        return fe_fat;
                    }

                    public void setFe_fat(String fe_fat) {
                        this.fe_fat = fe_fat;
                    }

                    public String getFe_carbohydrate() {
                        return fe_carbohydrate;
                    }

                    public void setFe_carbohydrate(String fe_carbohydrate) {
                        this.fe_carbohydrate = fe_carbohydrate;
                    }

                    public String getFe_sugar() {
                        return fe_sugar;
                    }

                    public void setFe_sugar(String fe_sugar) {
                        this.fe_sugar = fe_sugar;
                    }

                    public String getFe_na() {
                        return fe_na;
                    }

                    public void setFe_na(String fe_na) {
                        this.fe_na = fe_na;
                    }

                    public int getFe_ge() {
                        return fe_ge;
                    }

                    public void setFe_ge(int fe_ge) {
                        this.fe_ge = fe_ge;
                    }

                    public int getFe_time() {
                        return fe_time;
                    }

                    public void setFe_time(int fe_time) {
                        this.fe_time = fe_time;
                    }

                    public String getFe_date() {
                        return fe_date;
                    }

                    public void setFe_date(String fe_date) {
                        this.fe_date = fe_date;
                    }
                }
            }

            public static class _$5Bean {
                /**
                 * list : [{"fe_id":161,"f_id":827,"fe_type":5,"fu_id":0,"fe_num":"100.00","f_type":1,"f_img":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190726/f941d5c321435b66d29d4d44b2426255ae4f9a8c_1242x1229.jpg","f_name":"豆瓣辣酱","fe_cal":"7300.00","fe_protein":"360.00","fe_fat":"240.00","fe_carbohydrate":"1290.00","fe_sugar":"0.00","fe_na":"126870.00","fe_ge":0,"fe_time":1564588800,"fe_date":"2019-08-01"}]
                 * cal : 7300
                 */

                private double cal;
                private List<ListBeanXXXX> list;

                public double getCal() {
                    return cal;
                }

                public void setCal(double cal) {
                    this.cal = cal;
                }

                public List<ListBeanXXXX> getList() {
                    return list;
                }

                public void setList(List<ListBeanXXXX> list) {
                    this.list = list;
                }

                public static class ListBeanXXXX {
                    /**
                     * fe_id : 161
                     * f_id : 827
                     * fe_type : 5
                     * fu_id : 0
                     * fe_num : 100.00
                     * f_type : 1
                     * f_img : http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190726/f941d5c321435b66d29d4d44b2426255ae4f9a8c_1242x1229.jpg
                     * f_name : 豆瓣辣酱
                     * fe_cal : 7300.00
                     * fe_protein : 360.00
                     * fe_fat : 240.00
                     * fe_carbohydrate : 1290.00
                     * fe_sugar : 0.00
                     * fe_na : 126870.00
                     * fe_ge : 0
                     * fe_time : 1564588800
                     * fe_date : 2019-08-01
                     */

                    private int fe_id;
                    private int f_id;
                    private int fe_type;
                    private int fu_id;
                    private String fe_num;
                    private int f_type;
                    private String f_img;
                    private String f_name;
                    private String fe_cal;
                    private String fe_protein;
                    private String fe_fat;
                    private String fe_carbohydrate;
                    private String fe_sugar;
                    private String fe_na;
                    private int fe_ge;
                    private int fe_time;
                    private String fe_date;

                    public int getFe_id() {
                        return fe_id;
                    }

                    public void setFe_id(int fe_id) {
                        this.fe_id = fe_id;
                    }

                    public int getF_id() {
                        return f_id;
                    }

                    public void setF_id(int f_id) {
                        this.f_id = f_id;
                    }

                    public int getFe_type() {
                        return fe_type;
                    }

                    public void setFe_type(int fe_type) {
                        this.fe_type = fe_type;
                    }

                    public int getFu_id() {
                        return fu_id;
                    }

                    public void setFu_id(int fu_id) {
                        this.fu_id = fu_id;
                    }

                    public String getFe_num() {
                        return fe_num;
                    }

                    public void setFe_num(String fe_num) {
                        this.fe_num = fe_num;
                    }

                    public int getF_type() {
                        return f_type;
                    }

                    public void setF_type(int f_type) {
                        this.f_type = f_type;
                    }

                    public String getF_img() {
                        return f_img;
                    }

                    public void setF_img(String f_img) {
                        this.f_img = f_img;
                    }

                    public String getF_name() {
                        return f_name;
                    }

                    public void setF_name(String f_name) {
                        this.f_name = f_name;
                    }

                    public String getFe_cal() {
                        return fe_cal;
                    }

                    public void setFe_cal(String fe_cal) {
                        this.fe_cal = fe_cal;
                    }

                    public String getFe_protein() {
                        return fe_protein;
                    }

                    public void setFe_protein(String fe_protein) {
                        this.fe_protein = fe_protein;
                    }

                    public String getFe_fat() {
                        return fe_fat;
                    }

                    public void setFe_fat(String fe_fat) {
                        this.fe_fat = fe_fat;
                    }

                    public String getFe_carbohydrate() {
                        return fe_carbohydrate;
                    }

                    public void setFe_carbohydrate(String fe_carbohydrate) {
                        this.fe_carbohydrate = fe_carbohydrate;
                    }

                    public String getFe_sugar() {
                        return fe_sugar;
                    }

                    public void setFe_sugar(String fe_sugar) {
                        this.fe_sugar = fe_sugar;
                    }

                    public String getFe_na() {
                        return fe_na;
                    }

                    public void setFe_na(String fe_na) {
                        this.fe_na = fe_na;
                    }

                    public int getFe_ge() {
                        return fe_ge;
                    }

                    public void setFe_ge(int fe_ge) {
                        this.fe_ge = fe_ge;
                    }

                    public int getFe_time() {
                        return fe_time;
                    }

                    public void setFe_time(int fe_time) {
                        this.fe_time = fe_time;
                    }

                    public String getFe_date() {
                        return fe_date;
                    }

                    public void setFe_date(String fe_date) {
                        this.fe_date = fe_date;
                    }
                }
            }

            public static class _$6Bean {
                /**
                 * list : [{"fe_id":162,"f_id":830,"fe_type":6,"fu_id":0,"fe_num":"100.00","f_type":2,"f_img":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190726/a56ca51e488ed865e2841bed63fa866ebc966eb1_1242x1229.jpg","f_name":"晨恩 素豆豉鲮鱼","fe_cal":"338.00","fe_protein":"15000.00","fe_fat":"22000.00","fe_carbohydrate":"20000.00","fe_sugar":"0.00","fe_na":"922.00","fe_ge":0,"fe_time":1564588800,"fe_date":"2019-08-01"}]
                 * cal : 338
                 */

                private double cal;
                private List<ListBeanXXXXX> list;

                public double getCal() {
                    return cal;
                }

                public void setCal(double cal) {
                    this.cal = cal;
                }

                public List<ListBeanXXXXX> getList() {
                    return list;
                }

                public void setList(List<ListBeanXXXXX> list) {
                    this.list = list;
                }

                public static class ListBeanXXXXX {
                    /**
                     * fe_id : 162
                     * f_id : 830
                     * fe_type : 6
                     * fu_id : 0
                     * fe_num : 100.00
                     * f_type : 2
                     * f_img : http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190726/a56ca51e488ed865e2841bed63fa866ebc966eb1_1242x1229.jpg
                     * f_name : 晨恩 素豆豉鲮鱼
                     * fe_cal : 338.00
                     * fe_protein : 15000.00
                     * fe_fat : 22000.00
                     * fe_carbohydrate : 20000.00
                     * fe_sugar : 0.00
                     * fe_na : 922.00
                     * fe_ge : 0
                     * fe_time : 1564588800
                     * fe_date : 2019-08-01
                     */

                    private int fe_id;
                    private int f_id;
                    private int fe_type;
                    private int fu_id;
                    private String fe_num;
                    private int f_type;
                    private String f_img;
                    private String f_name;
                    private String fe_cal;
                    private String fe_protein;
                    private String fe_fat;
                    private String fe_carbohydrate;
                    private String fe_sugar;
                    private String fe_na;
                    private int fe_ge;
                    private int fe_time;
                    private String fe_date;

                    public int getFe_id() {
                        return fe_id;
                    }

                    public void setFe_id(int fe_id) {
                        this.fe_id = fe_id;
                    }

                    public int getF_id() {
                        return f_id;
                    }

                    public void setF_id(int f_id) {
                        this.f_id = f_id;
                    }

                    public int getFe_type() {
                        return fe_type;
                    }

                    public void setFe_type(int fe_type) {
                        this.fe_type = fe_type;
                    }

                    public int getFu_id() {
                        return fu_id;
                    }

                    public void setFu_id(int fu_id) {
                        this.fu_id = fu_id;
                    }

                    public String getFe_num() {
                        return fe_num;
                    }

                    public void setFe_num(String fe_num) {
                        this.fe_num = fe_num;
                    }

                    public int getF_type() {
                        return f_type;
                    }

                    public void setF_type(int f_type) {
                        this.f_type = f_type;
                    }

                    public String getF_img() {
                        return f_img;
                    }

                    public void setF_img(String f_img) {
                        this.f_img = f_img;
                    }

                    public String getF_name() {
                        return f_name;
                    }

                    public void setF_name(String f_name) {
                        this.f_name = f_name;
                    }

                    public String getFe_cal() {
                        return fe_cal;
                    }

                    public void setFe_cal(String fe_cal) {
                        this.fe_cal = fe_cal;
                    }

                    public String getFe_protein() {
                        return fe_protein;
                    }

                    public void setFe_protein(String fe_protein) {
                        this.fe_protein = fe_protein;
                    }

                    public String getFe_fat() {
                        return fe_fat;
                    }

                    public void setFe_fat(String fe_fat) {
                        this.fe_fat = fe_fat;
                    }

                    public String getFe_carbohydrate() {
                        return fe_carbohydrate;
                    }

                    public void setFe_carbohydrate(String fe_carbohydrate) {
                        this.fe_carbohydrate = fe_carbohydrate;
                    }

                    public String getFe_sugar() {
                        return fe_sugar;
                    }

                    public void setFe_sugar(String fe_sugar) {
                        this.fe_sugar = fe_sugar;
                    }

                    public String getFe_na() {
                        return fe_na;
                    }

                    public void setFe_na(String fe_na) {
                        this.fe_na = fe_na;
                    }

                    public int getFe_ge() {
                        return fe_ge;
                    }

                    public void setFe_ge(int fe_ge) {
                        this.fe_ge = fe_ge;
                    }

                    public int getFe_time() {
                        return fe_time;
                    }

                    public void setFe_time(int fe_time) {
                        this.fe_time = fe_time;
                    }

                    public String getFe_date() {
                        return fe_date;
                    }

                    public void setFe_date(String fe_date) {
                        this.fe_date = fe_date;
                    }
                }
            }
        }

        public static class BodyInfoBean {
            /**
             * mbi_id : 93
             * mbi_height : 180.00
             * mbi_weight : 70
             * m_id : 3
             * mbi_birth : 649486246
             * mbi_sex : 1
             */

            private int mbi_id;
            private String mbi_height;
            private String mbi_weight;
            private int m_id;
            private int mbi_birth;
            private int mbi_sex;

            public int getMbi_id() {
                return mbi_id;
            }

            public void setMbi_id(int mbi_id) {
                this.mbi_id = mbi_id;
            }

            public String getMbi_height() {
                return mbi_height;
            }

            public void setMbi_height(String mbi_height) {
                this.mbi_height = mbi_height;
            }

            public String getMbi_weight() {
                return mbi_weight;
            }

            public void setMbi_weight(String mbi_weight) {
                this.mbi_weight = mbi_weight;
            }

            public int getM_id() {
                return m_id;
            }

            public void setM_id(int m_id) {
                this.m_id = m_id;
            }

            public int getMbi_birth() {
                return mbi_birth;
            }

            public void setMbi_birth(int mbi_birth) {
                this.mbi_birth = mbi_birth;
            }

            public int getMbi_sex() {
                return mbi_sex;
            }

            public void setMbi_sex(int mbi_sex) {
                this.mbi_sex = mbi_sex;
            }
        }
    }
}
