package com.hongyuan.fitness.ui.shop.sbeans;

import com.hongyuan.fitness.base.BaseBean;

public class CheckInMeBeans extends BaseBean {

    /**
     * data : {"info1":{"apply_type":1,"is_tj":1,"is_pass":0},"info2":{"apply_type":2,"is_tj":0,"is_pass":0},"info3":{"apply_type":3,"is_tj":0,"is_pass":0}}
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
         * info1 : {"apply_type":1,"is_tj":1,"is_pass":0}
         * info2 : {"apply_type":2,"is_tj":0,"is_pass":0}
         * info3 : {"apply_type":3,"is_tj":0,"is_pass":0}
         */

        private Info1Bean info1;
        private Info2Bean info2;
        private Info3Bean info3;

        public Info1Bean getInfo1() {
            return info1;
        }

        public void setInfo1(Info1Bean info1) {
            this.info1 = info1;
        }

        public Info2Bean getInfo2() {
            return info2;
        }

        public void setInfo2(Info2Bean info2) {
            this.info2 = info2;
        }

        public Info3Bean getInfo3() {
            return info3;
        }

        public void setInfo3(Info3Bean info3) {
            this.info3 = info3;
        }

        public static class Info1Bean {
            /**
             * apply_type : 1
             * is_tj : 1
             * is_pass : 0
             */

            private int apply_type;
            private int is_tj;
            private int is_pass;

            public int getApply_type() {
                return apply_type;
            }

            public void setApply_type(int apply_type) {
                this.apply_type = apply_type;
            }

            public int getIs_tj() {
                return is_tj;
            }

            public void setIs_tj(int is_tj) {
                this.is_tj = is_tj;
            }

            public int getIs_pass() {
                return is_pass;
            }

            public void setIs_pass(int is_pass) {
                this.is_pass = is_pass;
            }
        }

        public static class Info2Bean {
            /**
             * apply_type : 2
             * is_tj : 0
             * is_pass : 0
             */

            private int apply_type;
            private int is_tj;
            private int is_pass;

            public int getApply_type() {
                return apply_type;
            }

            public void setApply_type(int apply_type) {
                this.apply_type = apply_type;
            }

            public int getIs_tj() {
                return is_tj;
            }

            public void setIs_tj(int is_tj) {
                this.is_tj = is_tj;
            }

            public int getIs_pass() {
                return is_pass;
            }

            public void setIs_pass(int is_pass) {
                this.is_pass = is_pass;
            }
        }

        public static class Info3Bean {
            /**
             * apply_type : 3
             * is_tj : 0
             * is_pass : 0
             */

            private int apply_type;
            private int is_tj;
            private int is_pass;

            public int getApply_type() {
                return apply_type;
            }

            public void setApply_type(int apply_type) {
                this.apply_type = apply_type;
            }

            public int getIs_tj() {
                return is_tj;
            }

            public void setIs_tj(int is_tj) {
                this.is_tj = is_tj;
            }

            public int getIs_pass() {
                return is_pass;
            }

            public void setIs_pass(int is_pass) {
                this.is_pass = is_pass;
            }
        }
    }
}
