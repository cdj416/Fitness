package com.hongyuan.fitness.ui.mall.good_pay;

import com.google.gson.annotations.SerializedName;
import com.hongyuan.fitness.base.BaseBean;

public class WecathPayBean extends BaseBean {

    /**
     * data : {"appid":"wx6bbd7113014b8551","noncestr":"fAfFKST9xDEq5DUgNZEfjUDeiYke5qfR","package":"Sign=WXPay","partnerid":"1512273331","prepayid":"wx252115545326275a7211c40d1339322300","timestamp":1564060553,"sign":"889B7714A73C0FADAD41F3B2882A3E4B"}
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
         * appid : wx6bbd7113014b8551
         * noncestr : fAfFKST9xDEq5DUgNZEfjUDeiYke5qfR
         * package : Sign=WXPay
         * partnerid : 1512273331
         * prepayid : wx252115545326275a7211c40d1339322300
         * timestamp : 1564060553
         * sign : 889B7714A73C0FADAD41F3B2882A3E4B
         */

        private String appid;
        private String noncestr;
        @SerializedName("package")
        private String packageX;
        private String partnerid;
        private String prepayid;
        private long timestamp;
        private String sign;

        public String getAppid() {
            return appid;
        }

        public void setAppid(String appid) {
            this.appid = appid;
        }

        public String getNoncestr() {
            return noncestr;
        }

        public void setNoncestr(String noncestr) {
            this.noncestr = noncestr;
        }

        public String getPackageX() {
            return packageX;
        }

        public void setPackageX(String packageX) {
            this.packageX = packageX;
        }

        public String getPartnerid() {
            return partnerid;
        }

        public void setPartnerid(String partnerid) {
            this.partnerid = partnerid;
        }

        public String getPrepayid() {
            return prepayid;
        }

        public void setPrepayid(String prepayid) {
            this.prepayid = prepayid;
        }

        public long getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(long timestamp) {
            this.timestamp = timestamp;
        }

        public String getSign() {
            return sign;
        }

        public void setSign(String sign) {
            this.sign = sign;
        }
    }
}
