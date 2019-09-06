package com.hongyuan.fitness.ui.mall.good_pay;

import com.hongyuan.fitness.base.BaseBean;

public class AlipayBean extends BaseBean {

    /**
     * data : {"pay":"alipay_sdk=alipay-sdk-php-20161101&app_id=2019062065617630&biz_content=%7B%22body%22%3A%22alipay+APP%E6%94%AF%E4%BB%98%22%2C%22subject%22%3A%22%E6%94%AF%E4%BB%98%E5%AE%9DAPP%E6%94%AF%E4%BB%98%22%2C%22out_trade_no%22%3A%2280-40581564055119%22%2C%22timeout_express%22%3A%2215mm%22%2C%22total_amount%22%3A%220.01%22%2C%22product_code%22%3A%22QUICK_MSECURITY_PAY%22%7D&charset=UTF-8&format=json&method=alipay.trade.app.pay&notify_url=http%3A%2F%2Fwww.hongyuangood.com%2Findex%2Fnotifyali%2Fnotifyurl&sign_type=RSA2&timestamp=2019-07-25+19%3A45%3A19&version=1.0&sign=tw4JW35oAOdJGiPTMEhzWX%2FQTRJc%2FtC71f39rDHjfHU9HI3zISmGJrHxkKp4Ua5O4HCMMtdCXakd0CMmDkyZ%2BGGHsuheJlNYsOxZUpvJd8njbJ9tYbcqeL2IBEVmGk06iIiGDJy3pdZ5bZ79eA7x8qOHcp3h%2BC6JAk6bMY0V7JQcSubHg1GvRbERj%2Bq0kh5DEIHKxu%2FALMDuc6DQQGr4866wgIjUe4KREVOE5EjvU55nVAE0uRin7xIdYll%2FIyS0gZJPDhF%2BNlnQeVeWx8ZDO%2FCPyceqdexSACEjJ%2ByJitRFpoLMDM4ANHMB5oF6fQQgawpdfMM1BW9wPlKhVRnZMA%3D%3D"}
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
         * pay : alipay_sdk=alipay-sdk-php-20161101&app_id=2019062065617630&biz_content=%7B%22body%22%3A%22alipay+APP%E6%94%AF%E4%BB%98%22%2C%22subject%22%3A%22%E6%94%AF%E4%BB%98%E5%AE%9DAPP%E6%94%AF%E4%BB%98%22%2C%22out_trade_no%22%3A%2280-40581564055119%22%2C%22timeout_express%22%3A%2215mm%22%2C%22total_amount%22%3A%220.01%22%2C%22product_code%22%3A%22QUICK_MSECURITY_PAY%22%7D&charset=UTF-8&format=json&method=alipay.trade.app.pay&notify_url=http%3A%2F%2Fwww.hongyuangood.com%2Findex%2Fnotifyali%2Fnotifyurl&sign_type=RSA2&timestamp=2019-07-25+19%3A45%3A19&version=1.0&sign=tw4JW35oAOdJGiPTMEhzWX%2FQTRJc%2FtC71f39rDHjfHU9HI3zISmGJrHxkKp4Ua5O4HCMMtdCXakd0CMmDkyZ%2BGGHsuheJlNYsOxZUpvJd8njbJ9tYbcqeL2IBEVmGk06iIiGDJy3pdZ5bZ79eA7x8qOHcp3h%2BC6JAk6bMY0V7JQcSubHg1GvRbERj%2Bq0kh5DEIHKxu%2FALMDuc6DQQGr4866wgIjUe4KREVOE5EjvU55nVAE0uRin7xIdYll%2FIyS0gZJPDhF%2BNlnQeVeWx8ZDO%2FCPyceqdexSACEjJ%2ByJitRFpoLMDM4ANHMB5oF6fQQgawpdfMM1BW9wPlKhVRnZMA%3D%3D
         */

        private String pay;

        public String getPay() {
            return pay;
        }

        public void setPay(String pay) {
            this.pay = pay;
        }
    }
}
