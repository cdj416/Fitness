package com.hongyuan.fitness.ui.about_class.coach.coach_list;

import com.hongyuan.fitness.base.BaseBean;

import java.util.List;

public class FilterAreaBeans extends BaseBean {

    /**
     * data : {"list":{"son_list":[{"region_name":"吴兴区","region_code":"350502"},{"region_name":"南浔区","region_code":"350503"},{"region_name":"德清县","region_code":"350504"},{"region_name":"长兴县","region_code":"350505"},{"region_name":"安吉县","region_code":"350506"},{"region_name":"开发区","region_code":"350507"}],"father_region_code":"3505","father_region_name":"全城"}}
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
         * list : {"son_list":[{"region_name":"吴兴区","region_code":"350502"},{"region_name":"南浔区","region_code":"350503"},{"region_name":"德清县","region_code":"350504"},{"region_name":"长兴县","region_code":"350505"},{"region_name":"安吉县","region_code":"350506"},{"region_name":"开发区","region_code":"350507"}],"father_region_code":"3505","father_region_name":"全城"}
         */

        private ListBean list;

        public ListBean getList() {
            return list;
        }

        public void setList(ListBean list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * son_list : [{"region_name":"吴兴区","region_code":"350502"},{"region_name":"南浔区","region_code":"350503"},{"region_name":"德清县","region_code":"350504"},{"region_name":"长兴县","region_code":"350505"},{"region_name":"安吉县","region_code":"350506"},{"region_name":"开发区","region_code":"350507"}]
             * father_region_code : 3505
             * father_region_name : 全城
             */

            private String father_region_code;
            private String father_region_name;
            private List<SonListBean> son_list;

            public String getFather_region_code() {
                return father_region_code;
            }

            public void setFather_region_code(String father_region_code) {
                this.father_region_code = father_region_code;
            }

            public String getFather_region_name() {
                return father_region_name;
            }

            public void setFather_region_name(String father_region_name) {
                this.father_region_name = father_region_name;
            }

            public List<SonListBean> getSon_list() {
                return son_list;
            }

            public void setSon_list(List<SonListBean> son_list) {
                this.son_list = son_list;
            }

            public static class SonListBean {
                /**
                 * region_name : 吴兴区
                 * region_code : 350502
                 */

                private String region_name;
                private String region_code;

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
            }
        }
    }
}
