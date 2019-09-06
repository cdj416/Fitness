package com.hongyuan.fitness.wxapi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.Constants;
import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

public class WXPayEntryActivity extends Activity implements IWXAPIEventHandler {

	private IWXAPI api;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_class_success);
		api = WXAPIFactory.createWXAPI(this, Constants.APP_ID);
		api.handleIntent(getIntent(), this);
	}

	@Override
	protected void onNewIntent(Intent intent) {
		super.onNewIntent(intent);
		setIntent(intent);
		api.handleIntent(intent, this);
	}

	@Override
	public void onReq(BaseReq req) {
	}

	@Override
	public void onResp(BaseResp baseResp) {
		if (baseResp.getType() == ConstantsAPI.COMMAND_PAY_BY_WX) {
			int code = baseResp.errCode;
			Log.e("cdj","========pay_code==="+code);
			String msg = "";
			switch (code) {
				case 0:
					msg = "支付成功！";

					break;
				case -1:
					msg = "支付失败！";
					finish();
					break;
				case -2:
					msg = "您取消了支付！";
					finish();
					break;
				default:
					msg = "支付失败！";
					finish();
					break;
		}
	}
}
}