package com.hongyuan.fitness.base;

public class Constants {

    //测试地址
    public static final String ADRESS = "https://www.1667799.com";
    //预发环境
    //public static final String ADRESS = "http://test.1667799.com";
    //正式地址
    //public static final String ADRESS = "http://www.hongyuangood.com";

    //webUrl需要的地址
    public static final String WEB_ADDRESS = "https://www.1667799.com/#";

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
    public static final String GETPREPAYORDER = ADRESS + "/api/payandroid/getPrePayOrder";
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

    /**************************************三期版本********************************************************/
    //新百科--文章分类
    public static final String V3_GET_BAIKE_CATEGORY_LIST = ADRESS + "/api/articlehome/get_baike_category_list";
    //新百科--用户端文章列表
    public static final String V3_GET_ARTICLE_LIST = ADRESS + "/api/articlehome/get_article_list";
    //积分--打卡分享图片
    public static final String SHARE_IMGS = ADRESS + "/api/member/share_imgs";
    //积分--获取积分任务列表
    public static final String GETRWLIST = ADRESS + "/api/member/getRWList";
    //积分--打卡分享
    public static final String KA_SHARE = ADRESS + "/api/member/ka_share";
    //圈子--读取主题分类
    public static final String GET_CIRCLE_CATEGORY_LIST = ADRESS + "/api/circlehome/get_circle_category_list";
    //圈子--话题分类详情
    public static final String GET_CIRCLE_CATEGORY_INFO = ADRESS + "/api/circlehome/get_circle_category_info";
    //圈子--读取参加主题的用户列表
    public static final String GET_CIRCLE_MEMBER_LIST = ADRESS + "/api/circlehome/get_circle_member_list";
    //会员--其他会员主页
    public static final String CIRCLE_OTHER_MEMBER_INDEX = ADRESS + "/api/circle/circle_other_member_index";
    //会员--其他会员主页帖子列表
    public static final String GET_OTHER_MEMBER_CIRCLE_LIST = ADRESS + "/api/circle/get_other_member_circle_list";
    //新百科--推荐热门文章列表
    public static final String GET_ARTICLE_LIST_TJ = ADRESS + "/api/articlehome/get_article_list_tj";
    //新百科--文章详情
    public static final String GET_ARTICLE_INFO = ADRESS + "/api/articlehome/get_article_info";
    //新百科--评论列表
    public static final String GET_ARTICLE_REVIEWLIST = ADRESS + "/api/articlehome/get_article_reviewList";
    //新百科--添加文章评论
    public static final String ADD_ARTICLE_REVIEW = ADRESS + "/api/articlemember/add_article_review";
    //新百科--点赞文章
    public static final String ADD_ARTICLE_PRAISE = ADRESS + "/api/articlemember/add_article_praise";
    //新百科--取消文章点赞
    public static final String CANCEL_ARTICLE_PRAISE = ADRESS + "/api/articlemember/cancel_article_praise";
    //新百科--点赞文章评论
    public static final String ADD_ARTICLE_REVIEW_PRAISE = ADRESS + "/api/articlemember/add_article_review_praise";
    //新百科--取消评论点赞
    public static final String CANCEL_ARTICLE_REVIEW_PRAISE = ADRESS + "/api/articlemember/cancel_article_review_praise";
    //新百科--评论具体的列表
    public static final String GET_ARTICLE_REVIEWLIST_SON = ADRESS + "/api/articlehome/get_article_reviewList_son";
    //团课--开启团课提醒
    public static final String ADD_REMIND_CS = ADRESS + "/api/coursemember/add_remind_cs";
    //团课--删除团课提醒
    public static final String DEL_REMIND_CS = ADRESS + "/api/coursemember/del_remind_cs";
    //收藏--我的收藏列表
    public static final String GET_COLLECTION_LIST = ADRESS + "/api/member/get_collection_list";
    //收藏--添加收藏
    public static final String ADD_COLLECTION = ADRESS + "/api/member/add_collection";
    //收藏--删除我的收藏
    public static final String DEL_COLLECTION = ADRESS + "/api/member/del_collection";


    /**************************************四期版本********************************************************/
    //优惠券--能领取的所有优惠券
    public static final String ALL_COUPON_LIST = ADRESS + "/api/couponmember/all_coupon_list";
    //优惠券--领取优惠券
    public static final String GET_COUPON = ADRESS + "/api/couponmember/get_coupon";
    //优惠券--我的优惠券
    public static final String MY_COUPON_LIST = ADRESS + "/api/couponmember/my_coupon_list";
    //优惠券--首页弹出的优惠券列表
    public static final String INDEX_COUPON_LIST = ADRESS + "/api/couponindex/index_coupon_list";
    //优惠券--去使用的优惠券列表
    public static final String USE_COUPON_LIST = ADRESS + "/api/couponmember/use_coupon_list";
    //进店扫码--生成二维码
    public static final String SHOW_CODE = ADRESS + "/api/cardmember/show_code";
    //进店扫码--检查扫码是否完成
    public static final String check_do_card = ADRESS + "/api/cardmember/check_do_card";
    //会籍卡--读取卡的年限比如月卡年卡
    public static final String GET_CARD_TYPE_LIST = ADRESS + "/api/cardhome/get_card_type_list";
    //会籍卡--卡分类列表比如情侣卡等
    public static final String GET_CARD_CATEGORY_LIST = ADRESS + "/api/cardhome/get_card_category_list";
    //销售--销售顾问列表
    public static final String GET_SALER_LIST = ADRESS + "/api/salerindex/get_saler_list";
    //会籍卡--得到进店打卡的会籍卡列表
    public static final String GET_COME_CARD = ADRESS + "/api/cardmember/get_come_card";
    //圈子--删除圈子帖子
    public static final String DEL_CIRCLE = ADRESS + "/api/circle/del_circle";
    //个人资料--用户修改人脸图片
    public static final String UPDATE_MEMBER_FACE = ADRESS + "/api/member/update_member_face";

    /**************************************五期版本********************************************************/
    // 体脂--添加体脂数据
    public static final String ADD_BODY_FAT = ADRESS + "/api/body/add_body_fat";
    // 体脂--读取最新一条体脂数据
    public static final String GET_NEW_BODY_FAT_INFO = ADRESS + "/api/body/get_new_body_fat_info";
    // 体脂--体脂数据列表
    public static final String GET_BODY_FAT_LIST = ADRESS + "/api/body/get_body_fat_list";
    // 体脂--删除体脂数据
    public static final String DEL_BODY_FAT = ADRESS + "/api/body/del_body_fat";
    // 体脂--读取最新的十条数据
    public static final String GET_BODY_FAT_LIST_NEW = ADRESS + "/api/body/get_body_fat_list_new";
    // 身体数据--获取最新的身体数据
    public static final String GET_BODY_DATA_INFO = ADRESS + "/api/body/get_body_data_info";
    // 身体数据--获取身体数据列表
    public static final String GET_BODY_DATA_LIST = ADRESS + "/api/body/get_body_data_list";
    // 身体数据--添加身体数据
    public static final String ADD_BODY_DATA = ADRESS + "/api/body/add_body_data";
    // 身体数据--最新4张图片
    public static final String GET_BODY_IMG = ADRESS + "/api/body/get_body_img";
    // 身体数据--添加身体图片
    public static final String ADD_BODY_IMG = ADRESS + "/api/body/add_body_img";
    // 身体数据--身体图片列表
    public static final String GET_BODY_IMG_LIST = ADRESS + "/api/body/get_body_img_list";
    // 身体数据--删除身体图片
    public static final String DEL_BODY_IMG = ADRESS + "/api/body/del_body_img";
    // 计划--添加个人计划
    public static final String ADD_PLAN = ADRESS + "/api/body/add_plan";
    // 计划--计划目标
    public static final String GET_PLAN_MB = ADRESS + "/api/body/get_plan_mb";
    // 计划--读取计划信息
    public static final String GET_PLAN_INFO = ADRESS + "/api/body/get_plan_info";
    // 计划--用户计划状态
    public static final String GET_PLAN_STATE = ADRESS + "/api/body/get_plan_state";
    // 计划--教练已完成计划列表
    public static final String GET_PLAN_CONTENT_LIST = ADRESS + "/api/body/get_plan_content_list";
    // 计划--教练计划详情
    public static final String GET_PLAN_CONTENT_INFO = ADRESS + "/api/body/get_plan_content_info";
    // 推广--进入推广码
    public static final String TGINFO = ADRESS + "/api/member/tginfo";
    // 推广--我的推广下级列表
    public static final String GET_TG_MEMBER_LIST = ADRESS + "/api/member/get_tg_member_list";
    // 手环--读取闹钟设置列表
    public static final String GET_ALARM_LIST = ADRESS + "/api/sh/get_alarm_list";
    // 手环--添加或者编辑闹钟设置
    public static final String ADD_ALARM = ADRESS + "/api/sh/add_alarm";
    // 手环--获取alarmId
    public static final String GET_LAST_ID = ADRESS + "/api/sh/get_last_id";
    // 手环--删除闹钟设置
    public static final String DEL_ALARM = ADRESS + "/api/sh/del_alarm";
    // 手环--闹钟开启或关闭
    public static final String UPDATE_FLAG = ADRESS + "/api/sh/update_flag";
    // 手环--添加手环配置信息
    public static final String ADD_SH_CONFIG = ADRESS + "/api/sh/add_sh_config";
    // 手环--读取手环配置信息
    public static final String GET_SH_CONFIG_INFO = ADRESS + "/api/sh/get_sh_config_info";
    // 手环--批量导入睡眠数据
    public static final String ADD_SLEEP_DAY = ADRESS + "/api/sh/add_sleep_day";
    // 手环--添加心率
    public static final String ADD_HEAART_RATE_DAY = ADRESS + "/api/sh/add_heart_rate_day";
    // 手环--添加每天运动总数据
    public static final String ADD_SPORT_DAY = ADRESS + "/api/sh/add_sport_day";
    // 手环--添加每一项的运动数据(健身)
    public static final String ADD_EXERCISE_DAY = ADRESS + "/api/sh/add_exercise_day";
    // 手环--各项运动数据总览(包括单项)
    public static final String GET_EXERCISE_ITEM_INFO = ADRESS + "/api/sh/get_exercise_item_info";
    // 手环--读取运动列表
    public static final String GET_SPORT_LIST = ADRESS + "/api/sh/get_sport_list";
    // 手环--读取睡眠列表
    public static final String GET_SLEEP_LIST = ADRESS + "/api/sh/get_sleep_list";

    /**************************************五期版本********************************************************/
    // 首页--弹出商品
    public static final String GET_FREE_GODDS = ADRESS + "/api/goodshome/get_free_goods";
    //会员--读取会员基本信息
    public static final String GET_M_INFO = ADRESS + "/api/member/get_m_info";
    //会员-修改会员基本信息
    public static final String UPDATE_M_INFO = ADRESS + "/api/member/update_m_info";


    /**************************************六期版本********************************************************/
    //申请入驻
    public static final String APPLY_IN = ADRESS + "/api/goodsmember/apply_in";
    //检查超级管理员账号是否可用
    public static final String CHECK_ADMIN_NAME = ADRESS + "/api/goodsmember/check_admin_name";
    //申请入驻状态列表
    public static final String GET_APPLY_STATE = ADRESS + "/api/goodsmember/get_apply_state";
    //申请详情
    public static final String GET_APPLY_INFO = ADRESS + "/api/goodsmember/get_apply_info";
    //修改申请
    public static final String EDIT_APPLY = ADRESS + "/api/goodsmember/edit_apply";
    //添加收货地址
    public static final String ADD_RECEIVING_ADDRESS = ADRESS + "/api/goodsmember/add_receiving_address";
    //收货地址列表
    public static final String GET_RECEIVING_ADDRESS_LIST = ADRESS + "/api/goodsmember/get_receiving_address_list";
    //收货地址详情
    public static final String GET_RECEIVING_ADDRESS_INFO = ADRESS + "/api/goodsmember/get_receiving_address_info";
    //修改收货地址
    public static final String EDIT_RECEIVING_ADDRESS = ADRESS + "/api/goodsmember/edit_receiving_address";
    //删除收货地址
    public static final String DETL_RECEIVING_ADDRESS = ADRESS + "/api/goodsmember/del_receiving_address";
    //默认收货地址详情
    public static final String GET_DEFAULT_RECEIVING_ADDRESS_INFO = ADRESS + "/api/goodsmember/get_default_receiving_address_info";
    //搜索商品列表
    public static final String GET_GOODS_LIST_SIX = ADRESS + "/api/goodshome/get_goods_list";
    //搜索店铺列表
    public static final String GET_STORE_LIST = ADRESS + "/api/goodshome/get_store_list";
    //商品详情
    public static final String GET_GOODS_DETAIL_SIX = ADRESS + "/api/goodshome/get_goods_detail";
    //商品详情可领取的红包列表
    public static final String GET_GOODS_COUPON_LIST = ADRESS + "/api/goodsmember/get_goods_coupon_list";
    //添加商品到购物车
    public static final String ADD_SHOPPING_CART = ADRESS + "/api/goodsmember/add_shopping_cart";
    //用户搜索历史
    public static final String GET_VISIT_HABIT = ADRESS + "/api/goodshome/get_visit_habit";
    //大家都在搜
    public static final String GET_HOT_WORD = ADRESS + "/api/goodshome/get_hot_word";
    //清空搜索历史记录
    public static final String DEL_VISIT_HABIT = ADRESS + "/api/goodsmember/del_visit_habit";
    //商品详情的店铺信息/推荐商品
    public static final String GET_GOODS_STORE_INFO = ADRESS + "/api/goodshome/get_goods_store_info";
    //商城首页商品信息
    public static final String GET_BUSINESS_INDEX = ADRESS + "/api/goodshome/get_business_Index";
    //猜你喜欢商品列表
    public static final String GET_HABIT_GODDS_LIST = ADRESS + "/api/goodshome/get_habit_goods_list";
    //商品分类获取第三级分类
    public static final String GET_THIRD_CATEGORY_BY_FIRST_ID = ADRESS + "/api/goodshome/get_third_category_by_first_id";
    //商品分类-点击一级菜单的子分类
    public static final String GET_CATEGORY_BY_FIRST_ID = ADRESS + "/api/goodshome/get_category_by_first_id";
    //店铺主页
    public static final String GET_STORE_INFO = ADRESS + "/api/goodshome/get_store_info";
    //店铺主页红包列表
    public static final String GET_STORE_COUPON_LIST = ADRESS + "/api/goodshome/get_store_coupon_list";
    //店铺所有商品
    public static final String GET_STORE_GOODS_LIST = ADRESS + "/api/goodshome/get_store_goods_list";
    //读取我的购物车
    public static final String GET_SHOPPING_CART = ADRESS + "/api/goodsmember/get_shopping_cart";
    //删除购物车
    public static final String DEL_SHOPPING_CART = ADRESS + "/api/goodsmember/del_shopping_cart";
    //订单结算详情信息
    public static final String GET_COMFIRM_ORDER_INFO = ADRESS + "/api/goodsmember/get_comfirm_order_info";
    //店铺自提地址列表
    public static final String GET_GOODS_ZT_ADDRESS = ADRESS + "/api/goodshome/get_goods_zt_address";
    //订单结算我的优惠券列表
    public static final String GET_ORDER_CONFIRM_COUPON_LIST = ADRESS + "/api/goodsmember/get_order_confirm_coupon_list";
    //生成订单
    public static final String ADD_GOODS_ORDER_NEW = ADRESS + "/api/goodsmember/add_goods_order_new";
    //取消订单原因
    public static final String GET_CANCEL_REASON_LIST = ADRESS + "/api/goodshome/get_cancel_reason_list";
    //确认订单
    public static final String CONFIRM_ORDER = ADRESS + "/api/goodsmember/confirm_order";
    //读取订单的所有商品
    public static final String GET_ORDER_GOODS = ADRESS + "/api/goodsmember/get_order_goods";
    //添加商品评论
    public static final String ADD_GOODS_EVALUTION = ADRESS + "/api/goodsmember/add_goods_evaluation";
    //查询详细物流信息
    public static final String GET_DELIVER_INFO = ADRESS + "/api/goodsmember/get_deliver_info";
    //领券中心
    public static final String GET_CATEGORY_COUPON_LIST = ADRESS + "/api/goodshome/get_category_coupon_list";
    //商品的评价列表
    public static final String GET_GOODS_EVALUATION = ADRESS + "/api/goodshome/get_goods_evaluation";
    //运动生活热卖推荐
    public static final String GET_SPORT_GOODS_LIST = ADRESS + "/api/goodshome/get_sport_goods_list";
    //运动生活二级分类列表
    public static final String GET_SPORT_GOODS_CATEGORY = ADRESS + "/api/goodshome/get_sport_goods_category";
    //推广商品商家
    public static final String GET_FX_STORE_LIST = ADRESS + "/api/goodshome/get_fx_store_list";
    //推广商品列表
    public static final String GET_FX_GOODS_LIST = ADRESS + "/api/goodshome/get_fx_goods_list";
    //热门推广商品
    public static final String GET_HOT_FX_GOODS_LIST = ADRESS + "/api/goodshome/get_hot_fx_goods_list";
    //收益首页数据
    public static final String GET_INDEX_INCOME_DATA = ADRESS + "/api/income/get_index_income_data";
    //收益报表
    public static final String GET_INCOME_FORMS_DATA = ADRESS + "/api/income/get_income_forms_data";
    //结算订单明细
    public static final String GET_SETTLEMENT_LIST = ADRESS + "/api/income/get_settlement_list";
    //推广订单
    public static final String GET_INCOME_ORDER_LIST = ADRESS + "/api/income/get_order_list";


    //我的私教课单独一条
    public static final String GET_MEMBER_APPOINTMENT_COURSE_PRIVITE_ONE = ADRESS + "/api/coursemember/get_member_appointment_course_privite_one";
    //我的私教课单独一条
    public static final String GET_CANCEL_REASON = ADRESS + "/api/coursemember/get_cancel_reason";
    //学员取消预约
    public static final String CANCEL_COURSE_PRIVITE_APPOINTMENT_NEW = ADRESS + "/api/coursemember/cancel_course_privite_appointment_new";
    //首页场馆\团课\私教课\商品\发现
    public static final String GET_INDEX_OS_PC_BUSINESS_CIRCLE = ADRESS + "/api/appindex/get_index_os_pc_business_circle";
    //app首页  精品培训课 和朋友一起约运动
    public static final String INDEX = ADRESS + "/api/gymhome/index";
    //检查定位城市是否开通
    public static final String CHECK_CUR_CITY_IS_OPEN = ADRESS + "/api/goodshome/check_cur_city_is_open";
    //获取热门开通城市
    public static final String GET_OPEN_HOT_CITYS = ADRESS + "/api/goodshome/get_open_hot_citys";
    //开通城市的列表
    public static final String GET_OPEN_CITYS = ADRESS + "/api/goodshome/get_open_citys";
    //运动群聊组id添加
    public static final String GYM_SPORT_GROUP_CHAT_ADD = ADRESS + "/api/gymmember/gym_sport_group_chat_add";
    //运动群聊组会员列表获取
    public static final String SPORT_CHAT_MEMBER_LIST = ADRESS + "/api/gymmember/sport_chat_member_list";



}
