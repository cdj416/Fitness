package com.hongyuan.fitness.util;

import android.app.Activity;
import android.widget.Toast;

import com.hongyuan.fitness.base.Constants;
import com.hongyuan.fitness.ui.mall.good_pay.WecathPayBean;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

public class PayUtil {

    public static void WechatPay(Activity mActivity, WecathPayBean.DataBean data){
        // 在支付之前，如果应用没有注册到微信，应该先调用IWXMsg.registerApp将应用注册到微信
        IWXAPI msgApi = WXAPIFactory.createWXAPI(mActivity, null);
        msgApi.registerApp(Constants.APP_ID);
        PayReq req=new PayReq();
        try {
            req.appId= data.getAppid();
            req.partnerId= data.getPartnerid();
            req.prepayId= data.getPrepayid();
            req.packageValue= data.getPackageX();
            req.nonceStr= data.getNoncestr();
            req.timeStamp= String.valueOf(data.getTimestamp());
            req.sign= data.getSign();

            if (!msgApi.isWXAppInstalled()) {
                Toast.makeText(mActivity,
                        "未安装微信,不能完成支付，请选择其他方式",Toast.LENGTH_SHORT).show();
            } else {
                boolean a=msgApi.sendReq(req);
                System.out.println(a);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
