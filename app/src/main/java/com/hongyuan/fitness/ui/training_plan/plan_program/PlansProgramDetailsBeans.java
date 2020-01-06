package com.hongyuan.fitness.ui.training_plan.plan_program;
import com.hongyuan.fitness.base.BaseBean;

import java.io.Serializable;
import java.util.List;

public class PlansProgramDetailsBeans extends BaseBean implements Serializable {

    /**
     * data : {"info":{"content_id":12,"plan_id":22,"jl_id":13,"week_num":5,"add_time":1576476465,"content_state":8,"day_num":38,"plan_day":4,"add_date":"2019-12-16 14:07:45","m_name":"小陈","mi_head":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190925/c5455b02fc8634cf663c35bb551424112a119e58_1334x1334.jpg","ft_str":"塑形/POS/康复","os_name":"首玺健身金色水岸店","item":[{"day":1,"content":[{"items_type_name":"热身运动","color":"#3DA1E7","items_type":[{"item_name":null,"item_content":"好咯公公婆婆哦是","item_img":null,"color":"#3DA1E7"}]},{"items_type_name":"无氧运动","color":"#FF9345","items_type":[{"item_name":null,"item_content":"吉隆坡轰轰轰","item_img":null,"color":"#FF9345"}]},{"items_type_name":"有氧运动","color":"#22C883","items_type":[{"item_name":null,"item_content":"灵敏送朋友","item_img":null,"color":"#22C883"}]}]},{"day":2,"content":[{"items_type_name":"热身运动","color":"#3DA1E7","items_type":[{"item_name":null,"item_content":"null","item_img":null,"color":"#3DA1E7"}]},{"items_type_name":"无氧运动","color":"#FF9345","items_type":[{"item_name":null,"item_content":"null","item_img":null,"color":"#FF9345"}]},{"items_type_name":"有氧运动","color":"#22C883","items_type":[{"item_name":null,"item_content":"灵敏红","item_img":null,"color":"#22C883"},{"item_name":null,"item_content":"null","item_img":null,"color":"#22C883"}]}]},{"day":3,"content":[{"items_type_name":"热身运动","color":"#3DA1E7","items_type":[{"item_name":null,"item_content":"null","item_img":null,"color":"#3DA1E7"}]},{"items_type_name":"无氧运动","color":"#FF9345","items_type":[{"item_name":null,"item_content":"null","item_img":null,"color":"#FF9345"}]},{"items_type_name":"有氧运动","color":"#22C883","items_type":[{"item_name":null,"item_content":"null","item_img":null,"color":"#22C883"}]}]},{"day":4,"content":[{"items_type_name":"热身运动","color":"#3DA1E7","items_type":[{"item_name":null,"item_content":"null","item_img":null,"color":"#3DA1E7"}]},{"items_type_name":"无氧运动","color":"#FF9345","items_type":[{"item_name":null,"item_content":"null","item_img":null,"color":"#FF9345"}]},{"items_type_name":"有氧运动","color":"#22C883","items_type":[{"item_name":null,"item_content":"null","item_img":null,"color":"#22C883"}]}]}]}}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean implements Serializable {
        /**
         * info : {"content_id":12,"plan_id":22,"jl_id":13,"week_num":5,"add_time":1576476465,"content_state":8,"day_num":38,"plan_day":4,"add_date":"2019-12-16 14:07:45","m_name":"小陈","mi_head":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190925/c5455b02fc8634cf663c35bb551424112a119e58_1334x1334.jpg","ft_str":"塑形/POS/康复","os_name":"首玺健身金色水岸店","item":[{"day":1,"content":[{"items_type_name":"热身运动","color":"#3DA1E7","items_type":[{"item_name":null,"item_content":"好咯公公婆婆哦是","item_img":null,"color":"#3DA1E7"}]},{"items_type_name":"无氧运动","color":"#FF9345","items_type":[{"item_name":null,"item_content":"吉隆坡轰轰轰","item_img":null,"color":"#FF9345"}]},{"items_type_name":"有氧运动","color":"#22C883","items_type":[{"item_name":null,"item_content":"灵敏送朋友","item_img":null,"color":"#22C883"}]}]},{"day":2,"content":[{"items_type_name":"热身运动","color":"#3DA1E7","items_type":[{"item_name":null,"item_content":"null","item_img":null,"color":"#3DA1E7"}]},{"items_type_name":"无氧运动","color":"#FF9345","items_type":[{"item_name":null,"item_content":"null","item_img":null,"color":"#FF9345"}]},{"items_type_name":"有氧运动","color":"#22C883","items_type":[{"item_name":null,"item_content":"灵敏红","item_img":null,"color":"#22C883"},{"item_name":null,"item_content":"null","item_img":null,"color":"#22C883"}]}]},{"day":3,"content":[{"items_type_name":"热身运动","color":"#3DA1E7","items_type":[{"item_name":null,"item_content":"null","item_img":null,"color":"#3DA1E7"}]},{"items_type_name":"无氧运动","color":"#FF9345","items_type":[{"item_name":null,"item_content":"null","item_img":null,"color":"#FF9345"}]},{"items_type_name":"有氧运动","color":"#22C883","items_type":[{"item_name":null,"item_content":"null","item_img":null,"color":"#22C883"}]}]},{"day":4,"content":[{"items_type_name":"热身运动","color":"#3DA1E7","items_type":[{"item_name":null,"item_content":"null","item_img":null,"color":"#3DA1E7"}]},{"items_type_name":"无氧运动","color":"#FF9345","items_type":[{"item_name":null,"item_content":"null","item_img":null,"color":"#FF9345"}]},{"items_type_name":"有氧运动","color":"#22C883","items_type":[{"item_name":null,"item_content":"null","item_img":null,"color":"#22C883"}]}]}]}
         */

        private InfoBean info;

        public InfoBean getInfo() {
            return info;
        }

        public void setInfo(InfoBean info) {
            this.info = info;
        }

        public static class InfoBean implements Serializable {
            /**
             * content_id : 12
             * plan_id : 22
             * jl_id : 13
             * week_num : 5
             * add_time : 1576476465
             * content_state : 8
             * day_num : 38
             * plan_day : 4
             * add_date : 2019-12-16 14:07:45
             * m_name : 小陈
             * mi_head : http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190925/c5455b02fc8634cf663c35bb551424112a119e58_1334x1334.jpg
             * ft_str : 塑形/POS/康复
             * os_name : 首玺健身金色水岸店
             * item : [{"day":1,"content":[{"items_type_name":"热身运动","color":"#3DA1E7","items_type":[{"item_name":null,"item_content":"好咯公公婆婆哦是","item_img":null,"color":"#3DA1E7"}]},{"items_type_name":"无氧运动","color":"#FF9345","items_type":[{"item_name":null,"item_content":"吉隆坡轰轰轰","item_img":null,"color":"#FF9345"}]},{"items_type_name":"有氧运动","color":"#22C883","items_type":[{"item_name":null,"item_content":"灵敏送朋友","item_img":null,"color":"#22C883"}]}]},{"day":2,"content":[{"items_type_name":"热身运动","color":"#3DA1E7","items_type":[{"item_name":null,"item_content":"null","item_img":null,"color":"#3DA1E7"}]},{"items_type_name":"无氧运动","color":"#FF9345","items_type":[{"item_name":null,"item_content":"null","item_img":null,"color":"#FF9345"}]},{"items_type_name":"有氧运动","color":"#22C883","items_type":[{"item_name":null,"item_content":"灵敏红","item_img":null,"color":"#22C883"},{"item_name":null,"item_content":"null","item_img":null,"color":"#22C883"}]}]},{"day":3,"content":[{"items_type_name":"热身运动","color":"#3DA1E7","items_type":[{"item_name":null,"item_content":"null","item_img":null,"color":"#3DA1E7"}]},{"items_type_name":"无氧运动","color":"#FF9345","items_type":[{"item_name":null,"item_content":"null","item_img":null,"color":"#FF9345"}]},{"items_type_name":"有氧运动","color":"#22C883","items_type":[{"item_name":null,"item_content":"null","item_img":null,"color":"#22C883"}]}]},{"day":4,"content":[{"items_type_name":"热身运动","color":"#3DA1E7","items_type":[{"item_name":null,"item_content":"null","item_img":null,"color":"#3DA1E7"}]},{"items_type_name":"无氧运动","color":"#FF9345","items_type":[{"item_name":null,"item_content":"null","item_img":null,"color":"#FF9345"}]},{"items_type_name":"有氧运动","color":"#22C883","items_type":[{"item_name":null,"item_content":"null","item_img":null,"color":"#22C883"}]}]}]
             */

            private int content_id;
            private int plan_id;
            private int jl_id;
            private int week_num;
            private int add_time;
            private int content_state;
            private int day_num;
            private int plan_day;
            private String add_date;
            private String m_name;
            private String mi_head;
            private String ft_str;
            private String os_name;
            private List<ItemBean> item;

            public int getContent_id() {
                return content_id;
            }

            public void setContent_id(int content_id) {
                this.content_id = content_id;
            }

            public int getPlan_id() {
                return plan_id;
            }

            public void setPlan_id(int plan_id) {
                this.plan_id = plan_id;
            }

            public int getJl_id() {
                return jl_id;
            }

            public void setJl_id(int jl_id) {
                this.jl_id = jl_id;
            }

            public int getWeek_num() {
                return week_num;
            }

            public void setWeek_num(int week_num) {
                this.week_num = week_num;
            }

            public int getAdd_time() {
                return add_time;
            }

            public void setAdd_time(int add_time) {
                this.add_time = add_time;
            }

            public int getContent_state() {
                return content_state;
            }

            public void setContent_state(int content_state) {
                this.content_state = content_state;
            }

            public int getDay_num() {
                return day_num;
            }

            public void setDay_num(int day_num) {
                this.day_num = day_num;
            }

            public int getPlan_day() {
                return plan_day;
            }

            public void setPlan_day(int plan_day) {
                this.plan_day = plan_day;
            }

            public String getAdd_date() {
                return add_date;
            }

            public void setAdd_date(String add_date) {
                this.add_date = add_date;
            }

            public String getM_name() {
                return m_name;
            }

            public void setM_name(String m_name) {
                this.m_name = m_name;
            }

            public String getMi_head() {
                return mi_head;
            }

            public void setMi_head(String mi_head) {
                this.mi_head = mi_head;
            }

            public String getFt_str() {
                return ft_str;
            }

            public void setFt_str(String ft_str) {
                this.ft_str = ft_str;
            }

            public String getOs_name() {
                return os_name;
            }

            public void setOs_name(String os_name) {
                this.os_name = os_name;
            }

            public List<ItemBean> getItem() {
                return item;
            }

            public void setItem(List<ItemBean> item) {
                this.item = item;
            }

            public static class ItemBean implements Serializable {
                /**
                 * day : 1
                 * content : [{"items_type_name":"热身运动","color":"#3DA1E7","items_type":[{"item_name":null,"item_content":"好咯公公婆婆哦是","item_img":null,"color":"#3DA1E7"}]},{"items_type_name":"无氧运动","color":"#FF9345","items_type":[{"item_name":null,"item_content":"吉隆坡轰轰轰","item_img":null,"color":"#FF9345"}]},{"items_type_name":"有氧运动","color":"#22C883","items_type":[{"item_name":null,"item_content":"灵敏送朋友","item_img":null,"color":"#22C883"}]}]
                 */

                private int day;
                private List<ContentBean> content;

                public int getDay() {
                    return day;
                }

                public void setDay(int day) {
                    this.day = day;
                }

                public List<ContentBean> getContent() {
                    return content;
                }

                public void setContent(List<ContentBean> content) {
                    this.content = content;
                }

                public static class ContentBean implements Serializable {
                    /**
                     * items_type_name : 热身运动
                     * color : #3DA1E7
                     * items_type : [{"item_name":null,"item_content":"好咯公公婆婆哦是","item_img":null,"color":"#3DA1E7"}]
                     */

                    private String items_type_name;
                    private String color;
                    private List<ItemsTypeBean> items_type;

                    public String getItems_type_name() {
                        return items_type_name;
                    }

                    public void setItems_type_name(String items_type_name) {
                        this.items_type_name = items_type_name;
                    }

                    public String getColor() {
                        return color;
                    }

                    public void setColor(String color) {
                        this.color = color;
                    }

                    public List<ItemsTypeBean> getItems_type() {
                        return items_type;
                    }

                    public void setItems_type(List<ItemsTypeBean> items_type) {
                        this.items_type = items_type;
                    }

                    public static class ItemsTypeBean implements Serializable {
                        /**
                         * item_name : null
                         * item_content : 好咯公公婆婆哦是
                         * item_img : null
                         * color : #3DA1E7
                         */

                        private Object item_name;
                        private String item_content;
                        private Object item_img;
                        private String color;

                        public Object getItem_name() {
                            return item_name;
                        }

                        public void setItem_name(Object item_name) {
                            this.item_name = item_name;
                        }

                        public String getItem_content() {
                            return item_content;
                        }

                        public void setItem_content(String item_content) {
                            this.item_content = item_content;
                        }

                        public Object getItem_img() {
                            return item_img;
                        }

                        public void setItem_img(Object item_img) {
                            this.item_img = item_img;
                        }

                        public String getColor() {
                            return color;
                        }

                        public void setColor(String color) {
                            this.color = color;
                        }
                    }
                }
            }
        }
    }
}
