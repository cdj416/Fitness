package com.hongyuan.fitness.base;

public class Constants {

    //测试地址
    //public static final String ADRESS = "http://192.168.0.104";
    //正式地址
    public static final String ADRESS = "http://www.hongyuangood.com";

    /*==================================================================================*/
    //微信支付需要的appId
    public static final String APP_ID = "wx96322a2480850d96";


    /*==================================================================================*/
    //上传多文件
    public static final String  UPFILE_OSS_MORE = ADRESS + "/api/index/upfile_oss_more";
    //单文件上传
    public static final String  UPFILE_OSS = ADRESS + "/api/index/upfile_oss";

    //token的获取
    public static final String GET_TOKEN = ADRESS + "/api/index/api_token";
    //获取短信码token
    public static final String GET_MESSAGE_TOKEN = ADRESS + "/api/index/get_token";
    //获取短信验证码
    public static final String SEND_REGI_MSG = ADRESS + "/api/index/send_regi_msg";
    //注册用户
    public static final String MEMBER_REGISTER = ADRESS + "/api/index/memberregister";
    //登录接口
    public static final String MEMBER_LOGIN = ADRESS + "/api/index/memberlogin";
    //登录接口
    public static final String DO_FIND_PASSWORD = ADRESS + "/api/index/do_find_password";
    //短信验证码登录
    public static final String MEMBERLOGIN_DXM = ADRESS + "/api/index/memberlogin_dxm";
    //检测手机号是否注册
    public static final String CHECK_REGI_MOBILE = ADRESS + "/api/index/check_regi_mobile";

    //读取购买过的门店或者距离最近的门店
    public static final String GET_DEFAULT_OS = ADRESS + "/api/offlinestorea/get_default_os";
    //获取课程类型
    public static final String GET_FIT_TYPE_LIST = ADRESS + "/api/coursemember/get_fit_type_list";
    //获取所有课程类型(在首页课程使用)
    public static final String GET_FIT_TYPE_LIST_ALL = ADRESS + "/api/coursea/get_fit_type_list";
    //获取门店的的教练
    public static final String GET_OS_COACH_LIST = ADRESS + "/api/offlinestorea/get_os_coach_list";
    //读取教练的私教课
    public static final String GET_COACH_COURSE_PRIVITE = ADRESS + "/api/coursea/get_coach_course_privite";
    //用户端--教练主页信息
    public static final String GET_COACH_INDEX_INFO = ADRESS + "/api/coachhome/get_coach_index_info";
    //私教课详情
    public static final String GET_COURSE_PRIVITE_INFO = ADRESS + "/api/coursea/get_course_privite_info";
    //学员选课的时间坐标
    public static final String GET_PLAN_DATE = ADRESS + "/api/coachhome/get_plan_date";
    //教练某天的时间安排
    public static final String GET_COACH_TIMEPLAN_DAY_LIST = ADRESS + "/api/coachmember/get_coach_timeplan_day_list";
    //获取团课详情
    public static final String GET_COURSE_SUPER_INFO = ADRESS + "/api/coursemember/get_course_super_info";
    //报名团课
    public static final String SIGN_UP_COURSE_SUPER = ADRESS + "/api/coursemember/sign_up_course_super";
    //我报名的团课（学员）
    public static final String GET_MEMBER_SIGN_UP_COURSE_SUPER_LIST = ADRESS + "/api/coursemember/get_member_sign_up_course_super_list";
    //购买私教课生成订单
    public static final String BUY_PRIVITE_COURSE = ADRESS + "/api/coursemember/buy_privite_course";
    //私教--私教教练最近有空时间
    public static final String GET_COACH_LAST_KONG_TIME = ADRESS + "/api/coachhome/get_coach_last_kong_time";
    //用户端--读取教练相册列表图片
    public static final String GET_COACH_PHOTO_LIST = ADRESS + "/api/coachhome/get_coach_photo_list";
    //私教课--我已购买的私教课列表
    public static final String GET_MY_COURSE_PRIVITE_LIST = ADRESS + "/api/coursemember/get_my_course_privite_list";
    //学员已买私教课教练列表(已弃用)
    public static final String GET_MEMBER_ORDER_COURSE_PRIVITE_COACH = ADRESS + "/api/coursemember/get_member_order_course_privite_coach";
    //学员已买私教课教练列表
    public static final String GET_MY_COURSE_PRIVITE_COACH = ADRESS + "/api/coursemember/get_my_course_privite_coach";
    //约课--私教课详情
    public static final String GET_COURSE_PRIVITE_INFO_RESERVE = ADRESS + "/api/coursemember/get_course_privite_info";
    //学员约私教课
    public static final String ADD_COURSE_PRIVITE_APPOINTMENT = ADRESS + "/api/coursemember/add_course_privite_appointment";
    //学员教练私教课签到签退
    public static final String PRIVITE_COURSE_QD = ADRESS + "/api/coursemember/privite_course_qd";
    //团课--学员签到签退
    public static final String SUPER_COURSE_XY_QD = ADRESS + "/api/coursemember/super_course_xy_qd";
    // 私教--评论私教课
    public static final String ADD_COACH_REVIEW = ADRESS + "/api/member/add_coach_review";
    // 私教--评论私教课列表
    public static final String GET_COACH_REVIEW_LIST = ADRESS + "/api/member/get_coach_review_list";
    // 私教--评论私教课列表--有图片
    public static final String GET_COACH_REVIEW_IMG_LIST = ADRESS + "/api/member/get_coach_review_img_list";
    // 私教--私教课评论数
    public static final String GET_COACH_REVIEW_SUM = ADRESS + "/api/coachhome/get_coach_review_sum";

    //推荐百科
    public static final String GET_BAIKE_LIST_TJ = ADRESS + "/api/baikehome/get_baike_list_tj";
    //百科列表
    public static final String GET_BAIKE_LIST = ADRESS + "/api/baikehome/get_baike_list";
    //读取百科详情
    public static final String GET_BAIKE_INFO = ADRESS + "/api/baikehome/get_baike_info";
    //百科评论列表
    public static final String GET_BAIKE_REVIEWLIST = ADRESS + "/api/baikehome/get_baike_reviewList";
    //添加百科评论
    public static final String ADD_BAIKE_REVIEW = ADRESS + "/api/baikemember/add_baike_review";
    //百科评论子评论列表
    public static final String GET_BAIKE_REVIEWLIST_SON = ADRESS + "/api/baikehome/get_baike_reviewList_son";
    //点赞百科评论
    public static final String ADD_BAIKE_REVIEW_PRAISE = ADRESS + "/api/baikemember/add_baike_review_praise";
    //取消百科评论点赞
    public static final String CANCEL_BAIKE_REVIEW_PRAISE = ADRESS + "/api/baikemember/cancel_baike_review_praise";
    //点赞百科
    public static final String ADD_BAIKE_PRAISE = ADRESS + "/api/baikemember/add_baike_praise";
    //取消百科点赞
    public static final String CANCEL_BAIKE_PRAISE = ADRESS + "/api/baikemember/cancel_baike_praise";
    //添加百科
    public static final String ADD_BAIKE = ADRESS + "/api/baikemember/add_baike";

    //读取圈子帖子列表
    public static final String GET_CIRCLE_LIST = ADRESS + "/api/circlehome/get_circle_list";
    //发布圈子的帖子
    public static final String  ADD_CIRCLE = ADRESS + "/api/circle/add_circle";
    //圈子帖子详情
    public static final String  GET_CIRCLE_INFO = ADRESS + "/api/circlehome/get_circle_info";
    //读取圈子帖子评论列表
    public static final String  GET_RIRCLE_REVIEWLIST = ADRESS + "/api/circlehome/get_circle_reviewList";
    //圈子帖子评论
    public static final String  ADD_CIRCLE_REVIEW = ADRESS + "/api/circle/add_circle_review";
    //圈子帖子评论子评论列表
    public static final String  GET_CIRCLE_REVIEWLIST_SON = ADRESS + "/api/circlehome/get_circle_reviewList_son";
    //圈子帖子评论点赞
    public static final String  ADD_CIRCLE_REVIEW_PRAISE = ADRESS + "/api/circle/add_circle_review_praise";
    //圈子帖子评论取消点赞
    public static final String  CANCEL_CIRCLE_REVIEW_PRAISE = ADRESS + "/api/circle/cancel_circle_review_praise";
    //圈子帖子关注
    public static final String  ADD_FRIEND = ADRESS + "/api/member/add_friend";
    //圈子帖子点赞
    public static final String  ADD_CIRCLE_PRAISE = ADRESS + "/api/circle/add_circle_praise";
    //取消圈子帖子点赞
    public static final String  CANCEL_CIRCLE_PRAISE = ADRESS + "/api/circle/cancel_circle_praise";
    //我的好友列表
    public static final String  GET_MY_FRIENDS = ADRESS + "/api/member/get_my_friends";

    //食物热量--读取摄取列表
    public static final String  GET_FOOD_EATING_LIST = ADRESS + "/api/foodmember/get_food_eating_list";
    //食物热量--读取食物分类
    public static final String  GET_FOOD_CATEGORY_LIST = ADRESS + "/api/foodmember/get_food_category_list";
    //食物热量--读取食物列表
    public static final String  GET_FOOD_LIST = ADRESS + "/api/foodmember/get_food_list";
    //食物热量--记录食物热量
    public static final String  ADD_FOOD_EATING = ADRESS + "/api/foodmember/add_food_eating";
    //食物热量--读取食物详情
    public static final String  GET_FOOD_INFO = ADRESS + "/api/foodmember/get_food_info";
    //食物热量--编辑食物热量记录
    public static final String  EDIT_FOOD_EATING = ADRESS + "/api/foodmember/edit_food_eating";
    //食物热量--删除食物热量摄取记录
    public static final String  DEL_FOOD_EATING = ADRESS + "/api/foodmember/del_food_eating";

    //获取更多门店
    public static final String GET_OFFLINE_STORE_LIST_JULI = ADRESS + "/api/Offlinestorea/get_offline_store_list_juli";
    //读取门店详细信息
    public static final String GET_OFFLINE_STORE_INFO = ADRESS + "/api/offlinestorea/get_offline_store_info";
    //读取门店的会员卡列表
    public static final String GET_CARD_LIST = ADRESS + "/api/cardhome/get_card_list";
    //读取门店的团课列表
    public static final String GET_COURSE_SUPER_LIST = ADRESS + "/api/coursemember/get_course_super_list";
    //会员卡详情
    public static final String GET_CARD_INFO = ADRESS + "/api/cardhome/get_card_info";
    //会员卡添加购买订单
    public static final String ADD_CARD_ORDER = ADRESS + "/api/cardmember/add_card_order";
    //我的会员卡订单
    public static final String GET_MY_ORDER_CARD_LIST = ADRESS + "/api/cardmember/get_my_order_card_list";
    //我的会员卡订单
    public static final String GET_MY_CARD_LIST = ADRESS + "/api/cardmember/get_my_card_list";
    //会员--修改名称
    public static final String UPDATE_MEMBER_NAME = ADRESS + "/api/member/update_member_name";


    //首页-获取团课
    public static final String GET_INDEX_COURSE_SUPER = ADRESS + "/api/offlinestorea/get_index_course_super";
    //首页--读取首页教练
    public static final String GET_INDEX_COACH = ADRESS + "/api/offlinestorea/get_index_coach";
    //首页--首页图片(通用读取图片)
    public static final String GET_IMG_LIST = ADRESS + "/api/appsethome/get_img_list";

    //用户端--商品列表
    public static final String GET_GOODS_LIST = ADRESS + "/api/goodshome/get_goods_list";
    //用户端--读取商品一级分类
    public static final String GET_FIRST_CATEGORY = ADRESS + "/api/goodshome/get_first_category";
    //用户端--读取商品的二级分类
    public static final String GET_SECOND_CATEGORY = ADRESS + "/api/goodshome/get_second_category";
    //用户端--商品详情
    public static final String GET_GOODS_DETAIL = ADRESS + "/api/goodshome/get_goods_detail";
    //用户端--点击sku（该接口已废弃）
    public static final String CLICK_SKU = ADRESS + "/api/goodshome/click_sku";
    //用户端--选中sku
    public static final String SELECT_SKU = ADRESS + "/api/goodshome/select_sku";
    //用户端--读取用户积分
    public static final String GET_MEMBER_POINT = ADRESS + "/api/member/get_member_point";
    //用户端--购买商品下订单
    public static final String ADD_GOODS_ORDER = ADRESS + "/api/goodsmember/add_goods_order";
    //积分--读取我的积分日志列表
    public static final String GET_POINT_LOG_LIST = ADRESS + "/api/pointmember/get_point_log_list";
    //用户端--订单列表
    public static final String GET_ORDER_LIST = ADRESS + "/api/goodsmember/get_order_list";
    //用户端--订单详情
    public static final String GET_ORDER_INFO = ADRESS + "/api/goodsmember/get_order_info";
    //用户端--取消订单
    public static final String CANCLE_ORDER = ADRESS + "/api/goodsmember/cancle_order";

    //个人中心-学员已约课程列表（待评价）
    public static final String GET_MEMBER_APPOINTMENT_COURSE_PRIVITE_LIST = ADRESS + "/api/coursemember/get_member_appointment_course_privite_list";
    //会员--修改用户头像
    public static final String UPDATE_MEMBER_HEAD = ADRESS + "/api/member/update_member_head";
    //用户端--用户首页数据
    public static final String GET_MEMBER_INDEX_INFO = ADRESS + "/api/member/get_member_index_info";
    //会员签到--会员当月签到列表
    public static final String GET_MONTH_QD = ADRESS + "/api/member/get_month_qd";
    //会员签到--检查是否已经签到
    public static final String CHECK_IS_QD = ADRESS + "/api/member/check_is_qd";
    //会员签到--签到
    public static final String ADD_QD = ADRESS + "/api/member/add_qd";
    //会员--判断是进店还是离店操作
    public static final String CHECK_COME_OR_OFF_STORE = ADRESS + "/api/member/check_come_or_off_store";
    //用户--进店
    public static final String MEMBER_COME_STORE_DO_CARD = ADRESS + "/api/member/member_come_store_do_card";
    //会员--离店
    public static final String MEMBER_OFF_STORE_DO_CARD = ADRESS + "/api/member/member_off_store_do_card";
    //会员--检查是否存在身体指数
    public static final String CHECK_MEMBER_BOBY_INDEX = ADRESS + "/api/member/check_member_body_index";
    //会员--添加身体指数
    public static final String ADD_MEMBER_BODY_INDEX = ADRESS + "/api/member/add_member_body_index";

    //消息--读取消息分类列表
    public static final String GET_MSG_CATEGORY_LIST = ADRESS + "/api/msgmember/get_msg_category_list";
    //消息--读取消息列表(子项里面的)
    public static final String GET_MSG_LIST = ADRESS + "/api/msgmember/get_msg_list";
    //消息--更改为已读状态
    public static final String UPDATE_MSG_READ = ADRESS + "/api/msgmember/update_msg_read";

    //获取每天跑步等数据列表
    public static final String GET_EXERCISE_DAY_LIST = ADRESS + "/api/exercise/get_exercise_day_list";
    // 户外--读取个人跑步里程
    public static final String GET_RUN_DATA = ADRESS + "/api/exercise/get_run_data";

    // APP--检查app版本
    public static final String CHECK_APP_VERSION = ADRESS + "/api/appsethome/check_app_version";

    //支付-APP 支付宝支付
    public static final String UNIFIEDORDER = ADRESS + "/api/alipay/unifiedorder";
    //支付-安卓 APP微信支付
    public static final String GETPREPAYORDER = ADRESS + "/api/pay/getPrePayOrder";
    //用户端--纯积分支付
    public static final String POINT_PAY = ADRESS + "/api/pointmember/point_pay";


    /**************************************二期版本********************************************************/
    //课程--私教课列表
    public static final String GET_COURSE_PRIVITE_LIST = ADRESS + "/api/offlinestorea/get_course_privite_list";
    //教练--教练列表
    public static final String GET_COACH_LIST = ADRESS + "/api/offlinestorea/get_coach_list";
    //根据城市名称获取下面的区县列表
    public static final String GET_OS_ADDRESS_LIST = ADRESS + "/api/offlinestorea/get_os_address_list";
    //根据区县code 获取门店列表
    public static final String GET_OS_WHERE_LIST = ADRESS + "/api/offlinestorea/get_os_where_list";
    //课程--团课列表
    public static final String GET_TWO_COURSE_SUPER_LIST = ADRESS + "/api/offlinestorea/get_course_super_list";
    //课程--团课单项类型列表
    public static final String GET_SI_LIST = ADRESS + "/api/coursea/get_si_list";
    //门店--门店含有的会籍卡列表
    public static final String GET_OS_CARD_LIST = ADRESS + "/api/cardmember/get_os_card_list";
    //圈子--圈子用户首页
    public static final String CIRCLE_MEMBER_INDEX = ADRESS + "/api/circle/circle_member_index";
    //圈子--圈子用户首页帖子列表
    public static final String GET_MEMBER_CIRCLE_LIST = ADRESS + "/api/circle/get_member_circle_list";
    //通用--读取省市区
    public static final String GET_ADDRESS_LIST = ADRESS + "/api/offlinestorea/get_address_list";
    //会员--修改性别
    public static final String UPDATE_MEMBER_SEX = ADRESS + "/api/member/update_member_sex";
    //会员--修改生日
    public static final String UPDATE_MEMBER_BIRTH = ADRESS + "/api/member/update_member_birth";
    //会员--修改用户省市
    public static final String UPDATE_MEMBER_AREA = ADRESS + "/api/member/update_member_area";
    //会员--修改个性签名
    public static final String UPDATE_MEMBER_SIGN = ADRESS + "/api/member/update_member_sign";
    //会籍卡--我的会籍卡列表
    public static final String GET_MY_CARD_LIST_ALL = ADRESS + "/api/cardmember/get_my_card_list_all";
    //会籍卡--通卡适用门店列表
    public static final String GET_SUIT_OS_LIST = ADRESS + "/api/cardmember/get_suit_os_list";
    //会籍卡--续卡读取续卡充值卡列表
    public static final String GET_XU_CARD_LIST = ADRESS + "/api/cardmember/get_xu_card_list";
    //会籍卡--会籍卡详情
    public static final String GET_MY_CARD_INFO = ADRESS + "/api/cardmember/get_my_card_info";
    //课程--取消团课报名
    public static final String CANCEL_COURSE_SUPER_ORDER = ADRESS + "/api/coursemember/cancel_course_super_order";
    //课程--取消私教课报名
    public static final String CANCEL_COURSE_PRIVITE_APPOINTMENT = ADRESS + "/api/coursemember/cancel_course_privite_appointment";
    //购买私教--根据数量返回价格
    public static final String GET_PRICE_BY_NUM = ADRESS + "/api/coursemember/get_price_by_num";
    //百科--读取百科的分类列表
    public static final String GET_BAIKE_CATEGORY_LIST = ADRESS + "/api/baikehome/get_baike_category_list";
    //关注我的用户
    public static final String GET_GZ_MY = ADRESS + "/api/member/get_gz_my";


}
