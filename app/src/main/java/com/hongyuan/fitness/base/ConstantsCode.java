package com.hongyuan.fitness.base;

public class ConstantsCode {

    /*================================已下是接口使用的=======================================*/

    //圈子帖子点赞
    public final static int  ADD_CIRCLE_PRAISE = 0x001;
    //圈子帖子点赞
    public final static int  CANCEL_CIRCLE_PRAISE = 0x002;
    //食物热量--记录食物热量
    public final static int  ADD_FOOD_EATING = 0x003;
    //食物热量--编辑食物热量记录
    public final static int  EDIT_FOOD_EATING = 0x004;
    //食物热量--删除食物热量摄取记录
    public final static int  DEL_FOOD_EATING = 0x005;
    //用户端--取消订单
    public final static int CANCLE_ORDER = 0x006;
    //检测手机号是否注册
    public final static int CHECK_REGI_MOBILE = 0x007;
    //获取短信码token
    public final static int GET_MESSAGE_TOKEN = 0x008;
    //获取短信验证码
    public final static int SEND_REGI_MSG = 0x009;
    //进店打卡
    public final static int MEMBER_COME_STORE_DO_CARD = 0x010;
    //离店打卡
    public final static int MEMBER_OFF_STORE_DO_CARD = 0x011;
    //会员--检查是否存在身体指数
    public final static int CHECK_MEMBER_BOBY_INDEX = 0x012;
    //发现-关注
    public final static int ADD_FRIEND = 0x013;
    //获取门店的的教练
    public final static int GET_OS_COACH_LIST = 0x014;
    //读取门店的团课列表
    public final static int GET_COURSE_SUPER_LIST = 0x015;
    //会员签到--签到
    public final static int ADD_QD = 0x016;
    //待上课-私教课签到
    public final static int PRIVITE_COURSE_QD = 0x017;
    //团课--学员签到签退
    public final static int SUPER_COURSE_XY_QD = 0x018;
    //消息--更改为已读状态
    public final static int UPDATE_MSG_READ = 0x019;
    //查询市级数据
    public final static int GET_ADDRESS_LIST = 0x020;
    //修改性别
    public final static int UPDATE_MEMBER_SEX = 0x021;
    //会员--修改生日
    public final static int UPDATE_MEMBER_BIRTH = 0x022;
    //会员--修改用户省市
    public final static int UPDATE_MEMBER_AREA = 0x023;
    //会员--修改昵称
    public final static int UPDATE_MEMBER_NAME = 0x024;
    //会员--修改个性签名
    public final static int UPDATE_MEMBER_SIGN = 0x025;
    //课程--取消团课报名
    public final static int CANCEL_COURSE_SUPER_ORDER = 0x026;
    //课程--取消私教课报名
    public final static int CANCEL_COURSE_PRIVITE_APPOINTMENT = 0x027;


    /*================================三版需要的=======================================*/
    //团课--开启团课提醒
    public final static int ADD_REMIND_CS = 0x028;
    //团课--关闭团课提醒
    public final static int DEL_REMIND_CS = 0x029;
    //收藏--添加收藏
    public final static int ADD_COLLECTION = 0x030;
    //收藏--添加收藏
    public final static int DEL_COLLECTION = 0x031;

    /*================================已下EventBus使用的=======================================*/
    //食物搜索
    public final static int EB_FOOD_SEARCH = 1;
    //食物添加成功
    public final static int EB_ADD_FOOD_SUSSESS = 2;
    //刷新发现的三个fragment数据
    public final static int EB_ADD_CIRCLE = 3;
    //商城商品搜索
    public final static int EB_SEARCH_GOODS = 4;
    //首页需要刷新下数据并且显示在第一个页面
    public final static int EB_START_MAIN = 5;
    //需要跳转到课程页面
    public final static int EB_START_COURSE = 6;
    //需要显示私教还是团课
    public final static int EB_SHOW_PRIVITE = 7;
}
