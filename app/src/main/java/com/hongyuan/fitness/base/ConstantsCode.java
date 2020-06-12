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

    //修改密码
    public final static int DO_FIND_PASSWORD = 0x049;


    /*================================三版需要的=======================================*/
    //团课--开启团课提醒
    public final static int ADD_REMIND_CS = 0x028;
    //团课--关闭团课提醒
    public final static int DEL_REMIND_CS = 0x029;
    //收藏--添加收藏
    public final static int ADD_COLLECTION = 0x030;
    //收藏--添加收藏
    public final static int DEL_COLLECTION = 0x031;

    /*================================四版需要的=======================================*/
    //收藏--添加收藏
    public final static int GET_COUPON = 0x032;
    //圈子--删除圈子帖子
    public final static int DEL_CIRCLE = 0x033;
    //用户上传识别的人脸
    public final static int UPDATE_MEMBER_FACE = 0x034;

    /*================================五版需要的=======================================*/
    //用户上传识别的人脸
    public final static int ADD_BODY_FAT = 0x035;
    //删除体脂数据
    public final static int DEL_BODY_FAT = 0x036;
    //身体数据--添加身体数据
    public final static int ADD_BODY_DATA = 0x037;
    //身体数据--添加身体图片
    public final static int ADD_BODY_IMG = 0x038;
    //身体数据--删除身体图片
    public final static int DEL_BODY_IMG = 0x039;
    //计划--添加个人计划
    public final static int ADD_PLAN = 0x040;
    //手环--添加或者编辑闹钟设置
    public final static int ADD_ALARM = 0x041;
    //手环--删除闹钟设置
    public final static int DEL_ALARM = 0x042;
    //手环--闹钟开启或关闭
    public final static int UPDATE_FLAG = 0x043;
    //手环--批量导入睡眠数据
    public final static int ADD_SLEEP_DAY = 0x044;
    //手环--添加心率
    public final static int ADD_HEAART_RATE_DAY = 0x045;
    //手环--添加每天运动总数据
    public final static int ADD_SPORT_DAY = 0x046;
    //手环--添加每一项的运动数据(健身)
    public final static int ADD_EXERCISE_DAY = 0x047;
    //会员-修改会员基本信息
    public final static int UPDATE_M_INFO = 0x048;


    /*================================六版需要的=======================================*/
    //申请入驻
    public final static int APPLY_IN = 0x049;
    //修改申请
    public final static int EDIT_APPLY = 0x050;
    //添加收货地址
    public final static int ADD_RECEIVING_ADDRESS = 0x051;
    //添修改收货地址
    public final static int EDIT_RECEIVING_ADDRESS = 0x052;
    //删除收货地址
    public final static int DETL_RECEIVING_ADDRESS = 0x053;
    //添加商品到购
    public final static int ADD_SHOPPING_CART = 0x054;
    //清空搜索历史记录
    public final static int DEL_VISIT_HABIT = 0x055;
    //商城第一个banner
    public final static int FIRST_BANNER = 0x056;
    //商城第二个banner
    public final static int SECOND_BANNER = 0x057;
    //商品分类获取第三级分类
    public final static int GET_THIRD_CATEGORY_BY_FIRST_ID = 0x058;
    //删除购物车里面的商品
    public final static int DEL_SHOPPING_CART = 0x059;
    //获取购物车数据
    public final static int GET_SHOPPING_CART = 0x060;
    //确认订单
    public final static int CONFIRM_ORDER = 0x061;
    //添加商品评论
    public final static int ADD_GOODS_EVALUTION = 0x062;
    //学员取消上课
    public final static int CANCEL_COURSE_PRIVITE_APPOINTMENT_NEW = 0x063;
    //运动群聊组id添加
    public final static int GYM_SPORT_GROUP_CHAT_ADD = 0x064;
    //检查超级管理员
    public final static int CHECK_ADMIN_NAME = 0x064;
    //添加每次跑步或步行记录
    public final static int ME_ADD_EXERCISE_TIME = 0x065;
    //分享打卡
    public final static int KA_SHARE = 0x066;

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
    //以获取手环数据时使用
    public final static int EB_WRISTBAND_SHOW_CONTENT = 8;
    //领取商品后数据的刷新
    public final static int EB_CHAGEE_RECEVER_GOODS = 9;
    //搜索商品列表
    public final static int GET_GOODS_LIST_SIX = 10;
    //关闭搜索列表商品页面
    public final static int EB_CLOSE_SERARCH = 11;
    //tuan可扫码签到
    public final static int EB_SUPER_COURSE_XY_QD = 12;
    //首页数据刷新
    public final static int EB_HOME_REFRESH = 1301;
    //刷新定位城市
    public final static int EB_HOME_LOCATION = 1302;

}
