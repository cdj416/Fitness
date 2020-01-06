package com.hongyuan.fitness.wxapi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.gif.GifDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.Constants;
import com.hongyuan.fitness.ui.about_class.class_success.SuccessClassActivity;
import com.hongyuan.fitness.ui.main.MainActivity;
import com.hongyuan.fitness.ui.mall.good_pay.GoodsPayViewModel;
import com.hongyuan.fitness.ui.mall.mine.mine_order.MineOrderActivity;
import com.hongyuan.fitness.ui.promt_success.V3SuccessActivity;
import com.hongyuan.fitness.util.BaseUtil;
import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

public class WXPayEntryActivity extends Activity implements IWXAPIEventHandler {

	private IWXAPI api;

	private ImageView successImg;
	private TextView goHome;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_class_success);
		api = WXAPIFactory.createWXAPI(this, Constants.APP_ID);
		api.handleIntent(getIntent(), this);

		successImg = findViewById(R.id.successImg);
		goHome = findViewById(R.id.goHome);
		goHome.setOnClickListener(v -> {
			Intent intent = new Intent(WXPayEntryActivity.this,MainActivity.class);
			startActivity(intent);
		});
		//Glide.with(mActivity).load(R.drawable.success_gif_img).into(binding.successImg);

		RequestOptions options = new RequestOptions().skipMemoryCache(true);//配置

		Glide.with(this).asGif()
				.apply(options)//应用配置
				.load(R.drawable.success_gif_img)
				.listener(new RequestListener<GifDrawable>() {//添加监听，设置播放次数
					@Override
					public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<GifDrawable> target, boolean isFirstResource) {
						return false;
					}

					@Override
					public boolean onResourceReady(GifDrawable resource, Object model, Target<GifDrawable> target, DataSource dataSource, boolean isFirstResource) {
						if (resource instanceof GifDrawable) {
							resource.setLoopCount(1);//只播放一次
						}
						return false;
					}
				})
				.into(successImg);
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
					goSuccess();
					break;
				case -1:
					msg = "支付失败！";
					Intent intent = new Intent(this, MineOrderActivity.class);
					startActivity(intent);
					break;
				case -2:
					msg = "您取消了支付！";
					Intent intent1 = new Intent(this, MineOrderActivity.class);
					startActivity(intent1);
					break;
				default:
					msg = "支付失败！";
					Intent intent2 = new Intent(this, MineOrderActivity.class);
					startActivity(intent2);
					break;
			}
		}
	}

	/*
	 * 三版跳转
	 * */
	private void goSuccess(){
		if(BaseUtil.isValue(GoodsPayViewModel.successBeans)){
			Intent intent = new Intent(this,V3SuccessActivity.class);
			startActivity(intent);
			finish();
		}else{
			Bundle bundle = new Bundle();
			bundle.putString("titleName","支付结果");
			bundle.putString("successText","支付成功！");
			bundle.putString("buttonText","完成");
			Intent intent = new Intent(this,V3SuccessActivity.class);
			intent.putExtra("bundle",bundle);
			startActivity(intent);
			finish();
		}

	}


}