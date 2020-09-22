package com.hongyuan.fitness.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.core.app.ActivityCompat;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.util.Pair;
import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.Constants;
import com.hongyuan.fitness.base.ConstantsCode;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.ui.about_class.PriviteClassActivity;
import com.hongyuan.fitness.ui.about_class.coach.coach_homepage.CoachHomePageActivity;
import com.hongyuan.fitness.ui.about_class.group_class.group_details.MissionDetailActivity;
import com.hongyuan.fitness.ui.about_class.privite_class.course_details.CourseDetailsActivity;
import com.hongyuan.fitness.ui.encyclopedia.EncyclopediaActivity;
import com.hongyuan.fitness.ui.find.circle.edit_post.EditPostActivity;
import com.hongyuan.fitness.ui.find.circle.post_details.PostDetailsActivity;
import com.hongyuan.fitness.ui.login.vtwo_login.vtwo_verification_login.VtwoVerificationLoginActivity;
import com.hongyuan.fitness.ui.main.MainActivity;
import com.hongyuan.fitness.ui.main.TokenSingleBean;
import com.hongyuan.fitness.ui.mall.mine.mine_order.order_details.MineOrderDetailsActivity;
import com.hongyuan.fitness.ui.only_equipment.indicator_details.IndicatorDetailsActivity;
import com.hongyuan.fitness.ui.only_equipment.smart_basic_information.SmartBasicInformationActivity;
import com.hongyuan.fitness.ui.out_door.RunActivity;
import com.hongyuan.fitness.ui.person.edit_information.EditInformationActivity;
import com.hongyuan.fitness.ui.person.fix.SixPriviteCourseActivity;
import com.hongyuan.fitness.ui.person.my_promote.PromotionCodeActivity;
import com.hongyuan.fitness.ui.person.newedition.activity.GroupCourseOrdersActivity;
import com.hongyuan.fitness.ui.person.person_message.PersonMessageActivity;
import com.hongyuan.fitness.ui.person.physical_data.PhysicalDataActivity;
import com.hongyuan.fitness.ui.person.push_share.PushShareActivity;
import com.hongyuan.fitness.ui.scan.ScanActivity;
import com.hongyuan.fitness.ui.shop.sactivity.MsgDetailsActivity;
import com.hongyuan.fitness.ui.shop.sactivity.NewPoitionActivity;
import com.hongyuan.fitness.ui.shop.sactivity.SgoodsDetailActivity;
import com.hongyuan.fitness.ui.shop.sactivity.ShopMessageActivity;
import com.hongyuan.fitness.ui.store.StoreDetailActivity;
import com.hongyuan.fitness.ui.store.punch.PunchActivity;
import com.hongyuan.fitness.ui.store.store_page_list.StoreActivity;
import com.hongyuan.fitness.ui.video.MyPlayActivity;
import com.hongyuan.fitness.ui.webview.WebViewActivity;

import org.greenrobot.eventbus.EventBus;

public class JumpUtils {

    public static final int TYPE_APP = 1;
    public static final int TYPE_H5 = 2;

    public static class JumpBeans{
        private int img_href_type;//跳转类型
        private String href_code;//跳转约定码
        private String href_id;//原生跳转约定参数
        private String img_href;//H5连接
        private String h5Title;//h5标题
        private String h5Back;//h5返回类型

        public int getImg_href_type() {
            return img_href_type;
        }

        public void setImg_href_type(int img_href_type) {
            this.img_href_type = img_href_type;
        }

        public String getHref_code() {
            return href_code;
        }

        public void setHref_code(String href_code) {
            this.href_code = href_code;
        }

        public String getHref_id() {
            return href_id;
        }

        public void setHref_id(String href_id) {
            this.href_id = href_id;
        }

        public String getImg_href() {
            return img_href;
        }

        public void setImg_href(String img_href) {
            this.img_href = img_href;
        }

        public String getH5Title() {
            return h5Title;
        }

        public void setH5Title(String h5Title) {
            this.h5Title = h5Title;
        }

        public String getH5Back() {
            return h5Back;
        }

        public void setH5Back(String h5Back) {
            this.h5Back = h5Back;
        }
    }

    /**
     * 跳转到视频播放
     *
     * @param activity
     * @param view
     */
    public static void goToVideoPlayer(Activity activity, View view, Bundle bundle) {
        Intent intent = new Intent(activity, MyPlayActivity.class);
        intent.putExtra(MyPlayActivity.TRANSITION, true);
        intent.putExtras(bundle);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            Pair pair = new Pair<>(view, MyPlayActivity.IMG_TRANSITION);
            ActivityOptionsCompat activityOptions = ActivityOptionsCompat.makeSceneTransitionAnimation(
                    activity, pair);
            ActivityCompat.startActivity(activity, intent, activityOptions.toBundle());
        } else {
            activity.startActivity(intent);
            activity.overridePendingTransition(R.anim.abc_fade_in, R.anim.abc_fade_out);
        }
    }



    /*
    *
    * 跳转约定
    * */
    public static void goAtherPage(CustomActivity mContext,JumpBeans jumpBeans){
        Bundle bundle = new Bundle();

        if(jumpBeans.getImg_href_type() == TYPE_APP){
            switch (jumpBeans.href_code){
                case "cs_info"://团课详情
                    bundle.putString("cs_id",jumpBeans.getHref_id());
                    mContext.startActivity(MissionDetailActivity.class,bundle);
                    break;
                case "cs_qd"://团课扫码签到
                    //通知扫码签到
                    EventBus.getDefault().post(ConstantsCode.EB_SUPER_COURSE_XY_QD,"");
                    mContext.finish();
                    break;
                case "cp_info"://私教课详情
                    bundle.putString("cp_id",jumpBeans.getHref_id());
                    mContext.startActivity(CourseDetailsActivity.class,bundle);
                    break;
                case "os_qd"://进店打卡
                    bundle.putString("os_id",jumpBeans.getHref_id());
                    bundle.putString("bg_img","");
                    mContext.startActivity(PunchActivity.class,bundle);
                    break;
                case "os_info"://健身房首页详情
                    bundle.putString("os_id",jumpBeans.getHref_id());
                    mContext.startActivity(StoreDetailActivity.class,bundle);
                    break;
                case "goods_list"://商品列表
                    //bundle.putInt("position",0);
                    //mContext.startActivity(GoodActivity.class,bundle);
                    EventBus.getDefault().post(ConstantsCode.EB_START_COURSE,"2");
                    break;
                case "goods_info"://商品详情
                    bundle.putString("g_id",jumpBeans.getHref_id());
                    mContext.startActivity(SgoodsDetailActivity.class,bundle);
                    break;
                case "os_list"://门店列表
                    break;
                case "circle_list"://圈子帖子列表
                    bundle.putInt("showNum",1);
                    mContext.startActivity(MainActivity.class,bundle);
                    break;
                case "circle_info"://帖子详情
                    bundle.putString("circle_id",jumpBeans.getHref_id());
                    mContext.startActivity(PostDetailsActivity.class,bundle);
                    break;
                case "coach_list"://教练列表
                    break;
                case "coach_info"://教练主页
                    bundle.putString("coach_mid",jumpBeans.getHref_id());
                    mContext.startActivity(CoachHomePageActivity.class,bundle);
                    break;
                case "msg_list"://消息列表
                    //bundle.putString("coach_mid",href_id);
                    mContext.startActivity(ShopMessageActivity.class,null);
                    break;
                case "msg_info"://消息详情
                    break;
                case "order_list"://订单列表
                    //mContext.startActivity(MineOrderActivity.class,null);
                    break;
                case "order_info"://订单详情
                    bundle.putString("o_id",jumpBeans.getHref_id());
                    mContext.startActivity(MineOrderDetailsActivity.class,bundle);
                    break;
                case "smart_equipment"://体脂称扫描
                    bundle.putString("url",jumpBeans.getHref_id());
                    mContext.startActivity(SmartBasicInformationActivity.class,bundle);
                    break;

                case "msg_private"://私信详情
                    bundle.putString("msg_category_id",jumpBeans.getHref_id());
                    mContext.startActivity(MsgDetailsActivity.class,bundle);
                    break;

                case "msg_review"://消息评论
                    bundle.putInt("position",1);
                    mContext.startActivity(ShopMessageActivity.class,bundle);
                    break;

                case "msg_praise"://消息点赞
                    bundle.putInt("position",2);
                    mContext.startActivity(ShopMessageActivity.class,bundle);
                    break;

                case "msg_fans"://消息粉丝
                    bundle.putInt("position",3);
                    mContext.startActivity(ShopMessageActivity.class,bundle);
                    break;

                case "circle"://个人主页
                    mContext.startActivity(PersonMessageActivity.class,null);
                    break;
                case "cpa"://去我的私教课程
                    mContext.startActivity(SixPriviteCourseActivity.class,null);
                    break;
                case "super"://去我的团课中心
                    mContext.startActivity(GroupCourseOrdersActivity.class,null);
                    break;

                case "jd_product"://京东商品
                    String url = "openapp.jdmobile://virtual?params=%7B%22sourceValue%22:%220_productDetail_97%22,%22des%22:%22productDetail%22,%22skuId%22:%22"+jumpBeans.getHref_id()+"%22,%22category%22:%22jump%22,%22sourceType%22:%22PCUBE_CHANNEL%22%7D";
                    goAtherApp(mContext,url);
                    break;
                case "jd_store"://京东店铺
                    String url1 = "openapp.jdmobile://virtual?params=%7B%22sourceValue%22:%220_jshopMain_97%22,%22des%22:%22jshopMain%22,%22shopId%22:%22"+jumpBeans.getHref_id()+"%22,%22category%22:%22jump%22,%22sourceType%22:%22PCUBE_CHANNEL%22%7D";
                    goAtherApp(mContext,url1);
                    break;
                case "tb_product"://淘宝商品
                    goAtherApp(mContext,mContext.getResources().getString(R.string.taobao_app_goods,jumpBeans.getHref_id()));
                    break;
                case "tb_store"://淘宝店铺
                    goAtherApp(mContext,mContext.getResources().getString(R.string.taobao_app_shop,jumpBeans.getHref_id()));
                    break;
                case "tm_product"://天猫商品
                    goAtherApp(mContext,mContext.getResources().getString(R.string.tmall_app_goods,jumpBeans.getHref_id()));
                    break;
                case "tm_store"://天猫店铺
                    goAtherApp(mContext,mContext.getResources().getString(R.string.tmall_app_shop,jumpBeans.getHref_id()));
                    break;

            }
        }else if(jumpBeans.getImg_href_type() == TYPE_H5 && BaseUtil.isValue(jumpBeans.getImg_href())){
            if(TokenSingleBean.getInstance().getIslogin() == 2){
                bundle.putString("url",jumpBeans.getImg_href()+ TokenSingleBean.getInstance().getWebAllParams(jumpBeans.getImg_href()));
            }else{
                bundle.putString("url",jumpBeans.getImg_href());
            }

            if(BaseUtil.isValue(jumpBeans.getH5Title())){
                bundle.putString("title",jumpBeans.getH5Title());
            }
            if(BaseUtil.isValue(jumpBeans.getH5Back())){
                bundle.putString("backType",jumpBeans.getH5Back());
            }
            mContext.startActivity(WebViewActivity.class,bundle);
            if(mContext instanceof ScanActivity){
                mContext.finish();
            }
        }else{
            CustomDialog.showMessage(mContext,"未知去向！");
        }

    }


    //各个应用相应的包名
    private static String[] packageNames = {"com.taobao.taobao","com.jingdong.app.mall","com.tmall.wireless"};

    /*
    * 跳转各种原生app
    * */
    private static void goAtherApp(Context mContext,String url){
        if(url.contains("openapp.jdmobile://") || url.contains("taobao://") || url.contains("tmall://")){
            if(url.contains("openapp.jdmobile://")){
                if(!checkPackage(mContext,packageNames[1])){
                    CustomDialog.showMessage(mContext,"抱歉，未安装京东APP！");
                    return;
                }
            }
            if(url.contains("taobao://")){
                if(!checkPackage(mContext,packageNames[0])){
                    CustomDialog.showMessage(mContext,"抱歉，未安装淘宝APP！");
                    return;
                }
            }
            if(url.contains("tmall://")){
                if(!checkPackage(mContext,packageNames[2])){
                    CustomDialog.showMessage(mContext,"抱歉，未安装天猫APP！");
                    return;
                }
            }
        }else{
            CustomDialog.showMessage(mContext,"抱歉，未知连接！");
            return;
        }


        Intent intent = new Intent();
        intent.setAction("android.intent.action.VIEW");
        Uri uri = Uri.parse(url);
        intent.setData(uri);
        mContext.startActivity(intent);
    }

    /*
    * 检测是否按照天猫淘宝等应用
    * */
    private static boolean checkPackage(Context mContext,String packageName)
    {
        if (packageName == null || "".equals(packageName))
            return false;
        try
        {
            mContext.getPackageManager().getApplicationInfo(packageName, PackageManager
                    .GET_UNINSTALLED_PACKAGES);
            return true;
        }
        catch (PackageManager.NameNotFoundException e)
        {
            return false;
        }
    }

    /*
    * 积分跳转约定
    * */
    public static void goAtherPage(CustomActivity mActivity,int pt_code){
        Bundle bundle = new Bundle();
        switch (pt_code){
            case 1://首次注册登录
                break;
            case 7://每日签到
                mActivity.startActivity(NewPoitionActivity.class);
                break;
            case 8://每日分享到朋友圈
                mActivity.startActivity(PushShareActivity.class);
                break;
            case 14://完善资料
                mActivity.startActivity(EditInformationActivity.class);
                break;
            case 2://发动态
                mActivity.startActivity(EditPostActivity.class);
                break;
            case 3://点赞别人
            case 4://发表评论
            case 6://关注别人
            case 15://动态推荐
                //通过EventBus去通知MainActivity显示第二页
                EventBus.getDefault().post(ConstantsCode.EB_START_COURSE,"1");
                mActivity.startActivity(MainActivity.class,null);
                break;
            case 5://户外步行/跑步
                mActivity.startActivity(RunActivity.class,null);
                break;
            case 29://邀请好友下载APP
                mActivity.startActivity(PromotionCodeActivity.class,null);
                break;
            case 17://线上购买健身卡
                mActivity.startActivity(StoreActivity.class,null);
                break;
            case 16://线上预约课程
            case 20://用户上课
            case 21://课程评价
                mActivity.startActivity(SixPriviteCourseActivity.class,null);
                break;
            case 22://线上买私教
                mActivity.startActivity(PriviteClassActivity.class,null);
                break;
            case 18://线上预约场馆
                bundle.putString("url", Constants.WEB_ADDRESS+"/"+TokenSingleBean.getInstance().getWebAllParams(""));
                bundle.putString("title","场馆");
                if(BaseUtil.isValue(TokenSingleBean.getInstance().getM_id())){
                    mActivity.startActivity(WebViewActivity.class,bundle);
                }else{
                    mActivity.startActivity(VtwoVerificationLoginActivity.class,null);
                }
                break;
            case 19://线上发起运动
                if(BaseUtil.isValue(TokenSingleBean.getInstance().getM_id())){
                    bundle.putString("url", Constants.WEB_ADDRESS+ "/Asportslist"+TokenSingleBean.getInstance().getWebAllParams(""));
                    bundle.putString("title","发起运动");
                    mActivity.startActivity(WebViewActivity.class,bundle);
                }else{
                    mActivity.startActivity(VtwoVerificationLoginActivity.class,null);
                }
                break;
            case 23://报名运动
                bundle.putString("url", Constants.WEB_ADDRESS+ "/Asportslist"+TokenSingleBean.getInstance().getWebAllParams(""));
                bundle.putString("title","参加运动");
                mActivity.startActivity(WebViewActivity.class,bundle);
                break;
            case 30://报名赛事
                bundle.putString("url", Constants.WEB_ADDRESS+"/event"+TokenSingleBean.getInstance().getWebAllParams(""));
                bundle.putString("title","赛事");
                mActivity.startActivity(WebViewActivity.class,bundle);
                break;
            case 24://阅读健康百科
                mActivity.startActivity(EncyclopediaActivity.class,null);
                break;
            case 25://首次添加身体数据
                mActivity.startActivity(PhysicalDataActivity.class,null);
                break;
            case 26://绑定智能设备
                bundle.putInt("showPosition",0);
                mActivity.startActivity(IndicatorDetailsActivity.class,bundle);
                break;
            case 28://发表的动态被评论
                bundle.putInt("position",1);
                mActivity.startActivity(ShopMessageActivity.class,bundle);
                break;
            case 27://发表的动态被点赞
                bundle.putInt("position",2);
                mActivity.startActivity(ShopMessageActivity.class,bundle);
                break;
        }
    }

}
