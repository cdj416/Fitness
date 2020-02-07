package com.hongyuan.fitness.ui.main.main_about_class.private_lessons.vtwo_private_lessons;

import com.hongyuan.fitness.base.BaseBean;

import java.util.List;

public class VtwoPrivateLessonsBeans extends BaseBean {


    /**
     * hasmore : false
     * curpage : 1
     * page_total : 1
     * data : {"list":[{"cp_id":1,"cp_name":"嗯哼","m_id":3,"ft_id":7,"cp_duration":"9点","cp_info":"想干嘛干嘛去","cp_people":"老少皆宜","cp_suggest":"没介意","cp_note":"","cp_price":"0.01","cp_img":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190805/5dfcb8afa85d19ffb9a0124d810cd574306ba534_1706x2208.jpg","cp_num":1,"os_id":16,"ft_name":"康复","os_name":"首玺健身（环城西路店）","mi_realname":"陈道明","coach_nickname":"小明","coach_head":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190722/3749235a38c65966c07dbcc49d7855af6c726aff_800x600.jpg","distance_um":1778,"last_kong_date":"2019-08-14 18:30"},{"cp_id":3,"cp_name":"tpmpmpm","m_id":3,"ft_id":4,"cp_duration":"12:00","cp_info":"小朋友婆婆","cp_people":"79799","cp_suggest":"小朋友婆婆","cp_note":"","cp_price":"0.10","cp_img":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190805/4e913d513e05120b1611f1a194f239667d0fe1b0_1242x867.jpg","cp_num":1,"os_id":16,"ft_name":"POS","os_name":"首玺健身（环城西路店）","mi_realname":"陈道明","coach_nickname":"小明","coach_head":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190722/3749235a38c65966c07dbcc49d7855af6c726aff_800x600.jpg","distance_um":1778,"last_kong_date":"2019-08-14 18:30"},{"cp_id":5,"cp_name":"万岁万岁万万岁我","m_id":3,"ft_id":7,"cp_duration":"9:30","cp_info":"所以说我后悔莫","cp_people":"另一种以","cp_suggest":"进攻你","cp_note":"","cp_price":"0.01","cp_img":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190806/a33378b1dbdd65f40d53df880c3256349912f8a4_1242x699.jpg","cp_num":1,"os_id":16,"ft_name":"康复","os_name":"首玺健身（环城西路店）","mi_realname":"陈道明","coach_nickname":"小明","coach_head":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190722/3749235a38c65966c07dbcc49d7855af6c726aff_800x600.jpg","distance_um":1778,"last_kong_date":"2019-08-14 18:30"},{"cp_id":2,"cp_name":"啦啦","m_id":3,"ft_id":6,"cp_duration":"9:00","cp_info":"进攻 Mr","cp_people":"这泼猴","cp_suggest":"哈哈额","cp_note":"","cp_price":"0.01","cp_img":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190805/21e7d3e7fa354e8f2d76b59c865a476cf807b6f6_1242x699.jpg","cp_num":1,"os_id":16,"ft_name":"拉伸","os_name":"首玺健身（环城西路店）","mi_realname":"陈道明","coach_nickname":"小明","coach_head":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190722/3749235a38c65966c07dbcc49d7855af6c726aff_800x600.jpg","distance_um":1778,"last_kong_date":"2019-08-14 18:30"},{"cp_id":4,"cp_name":"希望xpy","m_id":3,"ft_id":3,"cp_duration":"979797","cp_info":"希望呜呜呜","cp_people":"879797","cp_suggest":"明和难以置信","cp_note":"","cp_price":"0.01","cp_img":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190806/d909b24145cf253a1d1d50b1a56d2371976c0906_1654x2208.jpg","cp_num":1,"os_id":16,"ft_name":"塑形","os_name":"首玺健身（环城西路店）","mi_realname":"陈道明","coach_nickname":"小明","coach_head":"http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190722/3749235a38c65966c07dbcc49d7855af6c726aff_800x600.jpg","distance_um":1778,"last_kong_date":"2019-08-14 18:30"}]}
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
             * cp_id : 1
             * cp_name : 嗯哼
             * m_id : 3
             * ft_id : 7
             * cp_duration : 9点
             * cp_info : 想干嘛干嘛去
             * cp_people : 老少皆宜
             * cp_suggest : 没介意
             * cp_note :
             * cp_price : 0.01
             * cp_img : http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190805/5dfcb8afa85d19ffb9a0124d810cd574306ba534_1706x2208.jpg
             * cp_num : 1
             * os_id : 16
             * ft_name : 康复
             * os_name : 首玺健身（环城西路店）
             * mi_realname : 陈道明
             * coach_nickname : 小明
             * coach_head : http://hongyuangood.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20190722/3749235a38c65966c07dbcc49d7855af6c726aff_800x600.jpg
             * distance_um : 1778
             * last_kong_date : 2019-08-14 18:30
             */

            private int cp_id;
            private String cp_name;
            private int m_id;
            private int ft_id;
            private String cp_duration;
            private String cp_info;
            private String cp_people;
            private String cp_suggest;
            private String cp_note;
            private String cp_price;
            private String cp_img;
            private int cp_num;
            private int os_id;
            private String ft_name;
            private String os_name;
            private String mi_realname;
            private String coach_nickname;
            private String coach_head;
            private int distance_um;
            private String last_kong_date;

            public int getCp_id() {
                return cp_id;
            }

            public void setCp_id(int cp_id) {
                this.cp_id = cp_id;
            }

            public String getCp_name() {
                return cp_name;
            }

            public void setCp_name(String cp_name) {
                this.cp_name = cp_name;
            }

            public int getM_id() {
                return m_id;
            }

            public void setM_id(int m_id) {
                this.m_id = m_id;
            }

            public int getFt_id() {
                return ft_id;
            }

            public void setFt_id(int ft_id) {
                this.ft_id = ft_id;
            }

            public String getCp_duration() {
                return cp_duration;
            }

            public void setCp_duration(String cp_duration) {
                this.cp_duration = cp_duration;
            }

            public String getCp_info() {
                return cp_info;
            }

            public void setCp_info(String cp_info) {
                this.cp_info = cp_info;
            }

            public String getCp_people() {
                return cp_people;
            }

            public void setCp_people(String cp_people) {
                this.cp_people = cp_people;
            }

            public String getCp_suggest() {
                return cp_suggest;
            }

            public void setCp_suggest(String cp_suggest) {
                this.cp_suggest = cp_suggest;
            }

            public String getCp_note() {
                return cp_note;
            }

            public void setCp_note(String cp_note) {
                this.cp_note = cp_note;
            }

            public String getCp_price() {
                return cp_price;
            }

            public void setCp_price(String cp_price) {
                this.cp_price = cp_price;
            }

            public String getCp_img() {
                //涉及到图片的地方都需要处理下，节省流量
                return cp_img+"?x-oss-process=image/resize,h_200,w_200";
            }

            public void setCp_img(String cp_img) {
                this.cp_img = cp_img;
            }

            public int getCp_num() {
                return cp_num;
            }

            public void setCp_num(int cp_num) {
                this.cp_num = cp_num;
            }

            public int getOs_id() {
                return os_id;
            }

            public void setOs_id(int os_id) {
                this.os_id = os_id;
            }

            public String getFt_name() {
                return ft_name;
            }

            public void setFt_name(String ft_name) {
                this.ft_name = ft_name;
            }

            public String getOs_name() {
                return os_name;
            }

            public void setOs_name(String os_name) {
                this.os_name = os_name;
            }

            public String getMi_realname() {
                return mi_realname;
            }

            public void setMi_realname(String mi_realname) {
                this.mi_realname = mi_realname;
            }

            public String getCoach_nickname() {
                return coach_nickname;
            }

            public void setCoach_nickname(String coach_nickname) {
                this.coach_nickname = coach_nickname;
            }

            public String getCoach_head() {
                return coach_head;
            }

            public void setCoach_head(String coach_head) {
                this.coach_head = coach_head;
            }

            public int getDistance_um() {
                return distance_um;
            }

            public void setDistance_um(int distance_um) {
                this.distance_um = distance_um;
            }

            public String getLast_kong_date() {
                return last_kong_date;
            }

            public void setLast_kong_date(String last_kong_date) {
                this.last_kong_date = last_kong_date;
            }
        }
    }
}
