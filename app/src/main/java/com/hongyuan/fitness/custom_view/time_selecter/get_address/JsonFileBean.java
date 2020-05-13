package com.hongyuan.fitness.custom_view.time_selecter.get_address;

import com.hongyuan.fitness.base.BaseBean;
import com.hongyuan.fitness.custom_view.time_selecter.IPickerViewData;

import java.io.Serializable;
import java.util.List;

/**
 * 地址对应的实体类
 */

public class JsonFileBean extends BaseBean implements Serializable {


    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean implements Serializable, IPickerViewData {
        /**
         * region_name : 河北省
         * region_code : 12
         * child : [{"region_code":"1201","region_name":"石家庄市","child":[{"region_code":"120102","region_name":"长安区"},{"region_code":"120103","region_name":"桥东区"},{"region_code":"120104","region_name":"桥西区"},{"region_code":"120105","region_name":"新华区"},{"region_code":"120106","region_name":"井陉矿区"},{"region_code":"120107","region_name":"裕华区"},{"region_code":"120108","region_name":"井陉县"},{"region_code":"120109","region_name":"正定县"},{"region_code":"120110","region_name":"栾城县"},{"region_code":"120111","region_name":"行唐县"},{"region_code":"120112","region_name":"灵寿县"},{"region_code":"120113","region_name":"高邑县"},{"region_code":"120114","region_name":"深泽县"},{"region_code":"120115","region_name":"赞皇县"},{"region_code":"120116","region_name":"无极县"},{"region_code":"120117","region_name":"平山县"},{"region_code":"120118","region_name":"元氏县"},{"region_code":"120119","region_name":"赵县"},{"region_code":"120120","region_name":"辛集市"},{"region_code":"120121","region_name":"藁城市"},{"region_code":"120122","region_name":"晋州市"},{"region_code":"120123","region_name":"新乐市"},{"region_code":"120124","region_name":"鹿泉市"},{"region_code":"120125","region_name":"高新区"}]},{"region_code":"1202","region_name":"秦皇岛市","child":[{"region_code":"120202","region_name":"海港区"},{"region_code":"120203","region_name":"山海关区"},{"region_code":"120204","region_name":"北戴河区"},{"region_code":"120205","region_name":"青龙县"},{"region_code":"120206","region_name":"昌黎县"},{"region_code":"120207","region_name":"抚宁县"},{"region_code":"120208","region_name":"卢龙县"},{"region_code":"120209","region_name":"开发区"}]},{"region_code":"1203","region_name":"唐山市","child":[{"region_code":"120302","region_name":"路南区"},{"region_code":"120303","region_name":"路北区"},{"region_code":"120304","region_name":"古冶区"},{"region_code":"120305","region_name":"开平区"},{"region_code":"120306","region_name":"丰南区"},{"region_code":"120307","region_name":"丰润区"},{"region_code":"120308","region_name":"滦县"},{"region_code":"120309","region_name":"滦南县"},{"region_code":"120310","region_name":"乐亭县"},{"region_code":"120311","region_name":"迁西县"},{"region_code":"120312","region_name":"玉田县"},{"region_code":"120313","region_name":"唐海县"},{"region_code":"120314","region_name":"遵化市"},{"region_code":"120315","region_name":"迁安市"}]},{"region_code":"1204","region_name":"张家口市","child":[{"region_code":"120402","region_name":"桥东区"},{"region_code":"120403","region_name":"桥西区"},{"region_code":"120404","region_name":"宣化区"},{"region_code":"120405","region_name":"下花园区"},{"region_code":"120406","region_name":"宣化县"},{"region_code":"120407","region_name":"张北县"},{"region_code":"120408","region_name":"康保县"},{"region_code":"120409","region_name":"沽源县"},{"region_code":"120410","region_name":"尚义县"},{"region_code":"120411","region_name":"蔚县"},{"region_code":"120412","region_name":"阳原县"},{"region_code":"120413","region_name":"怀安县"},{"region_code":"120414","region_name":"万全县"},{"region_code":"120415","region_name":"怀来县"},{"region_code":"120416","region_name":"涿鹿县"},{"region_code":"120417","region_name":"赤城县"},{"region_code":"120418","region_name":"崇礼县"}]},{"region_code":"1205","region_name":"廊坊市","child":[{"region_code":"120502","region_name":"安次区"},{"region_code":"120503","region_name":"广阳区"},{"region_code":"120504","region_name":"固安县"},{"region_code":"120505","region_name":"永清县"},{"region_code":"120506","region_name":"香河县"},{"region_code":"120507","region_name":"大城县"},{"region_code":"120508","region_name":"文安县"},{"region_code":"120509","region_name":"大厂县"},{"region_code":"120510","region_name":"霸州市"},{"region_code":"120511","region_name":"三河市"},{"region_code":"120512","region_name":"开发区"}]},{"region_code":"1206","region_name":"衡水市","child":[{"region_code":"120602","region_name":"桃城区"},{"region_code":"120603","region_name":"枣强县"},{"region_code":"120604","region_name":"武邑县"},{"region_code":"120605","region_name":"武强县"},{"region_code":"120606","region_name":"饶阳县"},{"region_code":"120607","region_name":"安平县"},{"region_code":"120608","region_name":"故城县"},{"region_code":"120609","region_name":"景县"},{"region_code":"120610","region_name":"阜城县"},{"region_code":"120611","region_name":"冀州市"},{"region_code":"120612","region_name":"深州市"}]},{"region_code":"1207","region_name":"保定市","child":[{"region_code":"120701","region_name":"新市区"},{"region_code":"120702","region_name":"北市区"},{"region_code":"120703","region_name":"南市区"},{"region_code":"120705","region_name":"满城县"},{"region_code":"120706","region_name":"清苑县"},{"region_code":"120707","region_name":"涞水县"},{"region_code":"120708","region_name":"阜平县"},{"region_code":"120709","region_name":"徐水县"},{"region_code":"120710","region_name":"定兴县"},{"region_code":"120711","region_name":"唐县"},{"region_code":"120712","region_name":"高阳县"},{"region_code":"120713","region_name":"容城县"},{"region_code":"120714","region_name":"涞源县"},{"region_code":"120715","region_name":"望都县"},{"region_code":"120716","region_name":"安新县"},{"region_code":"120717","region_name":"易县"},{"region_code":"120718","region_name":"曲阳县"},{"region_code":"120719","region_name":"蠡县"},{"region_code":"120720","region_name":"顺平县"},{"region_code":"120721","region_name":"博野县"},{"region_code":"120722","region_name":"雄县"},{"region_code":"120723","region_name":"涿州市"},{"region_code":"120724","region_name":"定州市"},{"region_code":"120725","region_name":"安国市"},{"region_code":"120726","region_name":"高碑店市"},{"region_code":"120727","region_name":"高新区"}]},{"region_code":"1208","region_name":"承德市","child":[{"region_code":"120802","region_name":"双桥区"},{"region_code":"120803","region_name":"双滦区"},{"region_code":"120804","region_name":"鹰手营子"},{"region_code":"120805","region_name":"承德县"},{"region_code":"120806","region_name":"兴隆县"},{"region_code":"120807","region_name":"平泉县"},{"region_code":"120808","region_name":"滦平县"},{"region_code":"120809","region_name":"隆化县"},{"region_code":"120810","region_name":"丰宁县"},{"region_code":"120811","region_name":"宽城县"},{"region_code":"120812","region_name":"围场县"}]},{"region_code":"1209","region_name":"邢台市","child":[{"region_code":"120902","region_name":"桥东区"},{"region_code":"120903","region_name":"桥西区"},{"region_code":"120904","region_name":"邢台县"},{"region_code":"120905","region_name":"临城县"},{"region_code":"120906","region_name":"内丘县"},{"region_code":"120907","region_name":"柏乡县"},{"region_code":"120908","region_name":"隆尧县"},{"region_code":"120909","region_name":"任县"},{"region_code":"120910","region_name":"南和县"},{"region_code":"120911","region_name":"宁晋县"},{"region_code":"120912","region_name":"巨鹿县"},{"region_code":"120913","region_name":"新河县"},{"region_code":"120914","region_name":"广宗县"},{"region_code":"120915","region_name":"平乡县"},{"region_code":"120916","region_name":"威县"},{"region_code":"120917","region_name":"清河县"},{"region_code":"120918","region_name":"临西县"},{"region_code":"120919","region_name":"南宫市"},{"region_code":"120920","region_name":"沙河市"}]},{"region_code":"1210","region_name":"沧州市","child":[{"region_code":"121002","region_name":"新华区"},{"region_code":"121003","region_name":"运河区"},{"region_code":"121004","region_name":"沧县"},{"region_code":"121005","region_name":"青县"},{"region_code":"121006","region_name":"东光县"},{"region_code":"121007","region_name":"海兴县"},{"region_code":"121008","region_name":"盐山县"},{"region_code":"121009","region_name":"肃宁县"},{"region_code":"121010","region_name":"南皮县"},{"region_code":"121011","region_name":"吴桥县"},{"region_code":"121012","region_name":"献县"},{"region_code":"121013","region_name":"孟村县"},{"region_code":"121014","region_name":"泊头市"},{"region_code":"121015","region_name":"任丘市"},{"region_code":"121016","region_name":"黄骅市"},{"region_code":"121017","region_name":"河间市"},{"region_code":"121018","region_name":"开发区"}]},{"region_code":"1211","region_name":"邯郸市","child":[{"region_code":"121102","region_name":"邯山区"},{"region_code":"121103","region_name":"丛台区"},{"region_code":"121104","region_name":"复兴区"},{"region_code":"121105","region_name":"峰峰矿区"},{"region_code":"121106","region_name":"邯郸县"},{"region_code":"121107","region_name":"临漳县"},{"region_code":"121108","region_name":"成安县"},{"region_code":"121109","region_name":"大名县"},{"region_code":"121110","region_name":"涉县"},{"region_code":"121111","region_name":"磁县"},{"region_code":"121112","region_name":"肥乡县"},{"region_code":"121113","region_name":"永年县"},{"region_code":"121114","region_name":"邱县"},{"region_code":"121115","region_name":"鸡泽县"},{"region_code":"121116","region_name":"广平县"},{"region_code":"121117","region_name":"馆陶县"},{"region_code":"121118","region_name":"魏县"},{"region_code":"121119","region_name":"曲周县"},{"region_code":"121120","region_name":"武安市"}]}]
         */

        private String region_name;
        private String region_code;
        private boolean select;
        private List<ChildBeanX> child;

        public boolean isSelect() {
            return select;
        }

        public void setSelect(boolean select) {
            this.select = select;
        }

        public String getRegion_name() {
            return region_name;
        }

        public void setRegion_name(String region_name) {
            this.region_name = region_name;
        }

        public String getRegion_code() {
            return region_code;
        }

        public void setRegion_code(String region_code) {
            this.region_code = region_code;
        }

        public List<ChildBeanX> getChild() {
            return child;
        }

        public void setChild(List<ChildBeanX> child) {
            this.child = child;
        }

        @Override
        public String getPickerViewText() {
            return this.region_name;
        }

        public static class ChildBeanX implements Serializable,IPickerViewData{
            /**
             * region_code : 1201
             * region_name : 石家庄市
             * child : [{"region_code":"120102","region_name":"长安区"},{"region_code":"120103","region_name":"桥东区"},{"region_code":"120104","region_name":"桥西区"},{"region_code":"120105","region_name":"新华区"},{"region_code":"120106","region_name":"井陉矿区"},{"region_code":"120107","region_name":"裕华区"},{"region_code":"120108","region_name":"井陉县"},{"region_code":"120109","region_name":"正定县"},{"region_code":"120110","region_name":"栾城县"},{"region_code":"120111","region_name":"行唐县"},{"region_code":"120112","region_name":"灵寿县"},{"region_code":"120113","region_name":"高邑县"},{"region_code":"120114","region_name":"深泽县"},{"region_code":"120115","region_name":"赞皇县"},{"region_code":"120116","region_name":"无极县"},{"region_code":"120117","region_name":"平山县"},{"region_code":"120118","region_name":"元氏县"},{"region_code":"120119","region_name":"赵县"},{"region_code":"120120","region_name":"辛集市"},{"region_code":"120121","region_name":"藁城市"},{"region_code":"120122","region_name":"晋州市"},{"region_code":"120123","region_name":"新乐市"},{"region_code":"120124","region_name":"鹿泉市"},{"region_code":"120125","region_name":"高新区"}]
             */

            private String region_code;
            private String region_name;
            private List<ChildBean> child;

            public String getRegion_code() {
                return region_code;
            }

            public void setRegion_code(String region_code) {
                this.region_code = region_code;
            }

            public String getRegion_name() {
                return region_name;
            }

            public void setRegion_name(String region_name) {
                this.region_name = region_name;
            }

            public List<ChildBean> getChild() {
                return child;
            }

            public void setChild(List<ChildBean> child) {
                this.child = child;
            }

            @Override
            public String getPickerViewText() {
                return region_name;
            }

            public static class ChildBean implements Serializable,IPickerViewData{
                /**
                 * region_code : 120102
                 * region_name : 长安区
                 */

                private String region_code;
                private String region_name;

                public String getRegion_code() {
                    return region_code;
                }

                public void setRegion_code(String region_code) {
                    this.region_code = region_code;
                }

                public String getRegion_name() {
                    return region_name;
                }

                public void setRegion_name(String region_name) {
                    this.region_name = region_name;
                }

                @Override
                public String getPickerViewText() {
                    return region_name;
                }
            }
        }
    }
}
