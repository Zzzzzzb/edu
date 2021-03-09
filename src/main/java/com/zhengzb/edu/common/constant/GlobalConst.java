package com.zhengzb.edu.common.constant;


public class GlobalConst {
    /**
     * 0返回测试数据 1返回调用数据
     */
    public static final String isTest = "0";

    /**
     * 1需要加密
     */
    public static final String enableSign = "1";

    /**
     * 1需要验签
     */
    public static final String enableVerifySign = "1";


    /**
     * Dubbo 各个服务平台版本号
     */
    public static final String DUBBO_VERSION_MIDDLE = "1.0.0";
    public static final String DUBBO_VERSION_MEDICAL = "1.0.0";
    public static final String DUBBO_VERSION_UNIPAY = "1.0.0";

    /**
     * 可退费时间范围 day
     */
    public static final Integer REFUND_DAYS_RANGE = 60;
    /*
     * 门诊号结算标识
     * */
    public static final String OUT_PATIOENT_SETTLE_TYPE = "1";

    /*
     * 单据号结算标识
     * */
    public static final String DOUCENTMENT_SETTLE_TYPE = "2";

    /**
     * 科室类型
     */
    public static class DepartType {
        /**
         * 挂号科室
         */
        public static final String REGISTER_DEPART = "1";
        /**
         * 非挂号科室
         */
        public static final String NOT_REGISTER_DEPART = "2";
    }

    /**
     * 业务类型
     */
    public static class BussinessType {
        /**
         * 预缴金充值
         */
        public static final String BUSSINESSTYPE_PREPAYMENT_RECHARGE = "0";
        /**
         * 预缴金退款
         */
        public static final String BUSSINESSTYPE_PREPAYMENT_REFUND = "1";
        /**
         * 挂号结算
         */
        public static final String BUSSINESSTYPE_APPOINT = "2";
        /**
         * 费用结算
         */
        public static final String BUSSINESSTYPE_SETTLE = "3";
    }

    /**
     * 登录类型
     */
    enum  LoginType {

        /** 微信公众号 */
        WX_GZH,

        /** 微信小程序 */
        WX_XCX,

        /** 支付宝服务窗 */
        ALIPAY_FUWU

    }

    /**
     * 登录方式
     */
    enum LoginWay {

        /** OAuth2.0 */
        OAUTH2,

        /** 账号密码 */
        PASSWORD
    }

    /**
     * 登录方式Bean的名称
     */
    static interface  UserLoginWay {

        /**
         * 微信公众号Oauth2登录
         */
        static final String WX_GZH_OAUTH2 = "WX_GZH_OAUTH2";
        /**
         * 微信公众号PASSWORD登录
         */
        static final  String WX_GZH_PASSWORD = "WX_GZH_PASSWORD";
        /**
         * 微信小程序Oauth2登录
         */
        static final  String WX_XCX_OAUTH2 = "WX_XCX_OAUTH2";
        /**
         * 微信小程序PASSWORD登录
         */
        static final  String WX_XCX_PASSWORD = "WX_XCX_PASSWORD";
        /**
         * 支付宝服务窗Oauth2登录
         */
        static final  String ALIPAY_FUWU_OAUTH2 = "ALIPAY_FUWU_OAUTH2";
        /**
         * 支付宝服务窗PASSWORD登录
         */
        static final String ALIPAY_FUWU_PASSWORD = "ALIPAY_FUWU_PASSWORD";

    }

    public static class Redis {

        /**
         * 项目名key
         */
        public static final String  REDIS_COMMON_KEY = "medical:";
        /**
         * 服务名-微信公众号
         */
        public static final String SERVER_WX = REDIS_COMMON_KEY + "medical-ser-director-wx:";

        /**
         * 订单锁
         */
        public static final  String ORDER_LOCK = REDIS_COMMON_KEY + "lock:";
        /**
         * 医院地址-geo类型
         */
        public static final  String MODEL_HOSPITAL_ADDRESS = SERVER_WX + "hospitaladdress:";
        /**
         * 记录每天活跃人数
         */
        public static final  String ACTIVE_PERSON_DAY = SERVER_WX + "active:";
        /**
         * 用于记录用户状态
         */
        public static final  String USER_ID = SERVER_WX + "userid:";
        /**
         * 预支付订单
         */
        public static final  String PRE_ORDER_PAY = SERVER_WX + "pay:";
        /**
         * 公众号支付参数
         */
        public static final String PUBLIC_ACCOUNTS_PAY_PARAMS= SERVER_WX + "publicAccountsPayParams:";
        /**
         * 医保支付参数
         */
        public static final  String CHS_PAY_PARAMS= SERVER_WX + "chsPayParams:";

    }

    public static class RedisDelayQueue {
        public static final String PRE_PAY = "pre_pay";
    }

    public static class Model {
        /** 结算模块 */
        public static final String SETTLE = "Settle";
        /** 充值模块 */
        public static final  String RECHARGE = "Recharge";
        /** 预约挂号模块 */
        public static final  String APPOINT = "Appoint";
    }

    /**
     * 支付类型
     */
    public static class  payType {
        /** 自费支付 */
        public static final String SLEF_PAY = "slefpay";
        /** 医保支付 */
        public static final  String CHS_PAY = "chspay";
    }

    public static class Ecard {

        /**
         * 绑定医保电子凭证服务地址
         */
        public static final String BIND_ECARD_URL = "/mims-ehic/ecard/bind_ybdzpz";
        /**
         * 解绑医保电子凭证服务地址
         */
        public static final   String UNBIND_ECARD_URL = "/mims-ehic/ecard/unbind_ybdzpz ";
        /**
         * 医保签到
         */
        public static final String SIGNIN = "/mims-mspp/common/signin/QD";
        /**
         * 医保签退
         */
        public static final String SIGNOUT = "/mims-mspp/common/signout/QT";

    }

    /**
     * 卡类型
     */
    enum CardType {

        /** 社(医)保卡 */
        SBK("1"),

        /** 院内就诊卡 */
        YNK("2"),

        /** 区域就诊卡 */
        QYK("3"),

        /** 居民健康卡 */
        JMJKK("4"),

        /** 电子健康卡 */
        DZJKK("5"),

        /** 居民身份证 */
        SFZ("6"),

        /** 电子医保凭证 */
        DZYBPZ("7"),

        /** 电子社保卡 */
        DZSBK("8");

        String value;

        CardType(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }

    /**
     * 预下单业务类型
     */
    enum BussType {

        /** 充值 */
        Recharge("0"),

        /** 退款 */
        Refund("1"),

        /** 预约挂号 */
        Appointment("2"),

        /** 结算 */
        Settlement("3");

        private final String value;

        BussType(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }

    }

    /**
     * 生成ID类型
     */
    enum GenerateType {
        /** 支付订单号 */
        PAY,

        /** 退款订单号 */
        REFUND
    }

    /**
     * 对账标志
     */
    public static class SttlStatus {
        /** 未对账 */
        public static final String UNSTTL = "0";

        /** 已对账 */
        public static final  String STTLED = "1";
    }

    /**
     * 订单状态
     */
    public  static class OrderStatus {
        // 0:待支付  1:支付完成 2:支付失败 3:部分退款 4:全额退款 5:交易关闭

        /** 待支付 */
        public static final  String WAIT_PAY = "1";

        /** 支付完成 */
        public static final  String PAY_SUCCESS = "2";

        /** 支付失败 */
        public static final  String PAY_FAILED = "3";

        /** 部分退款 */
        public static final String REFUND_PART = "4";

        /** 全额退款 */
        public static final String REFUND_ALL = "5";

        /** 交易关闭 */
        public static final String CLOSE_ORDER = "6";

        /** 待退款 */
        public static final  String WAIT_REFUND = "7";

        /** 退款失败 */
        public static final  String CLOSE_REFUND = "8";
    }

    /**
     * 有效标志
     */
    public  static class ValidStatus {

        /** 无效 */
        public static final  String INVALID = "0";

        /** 有效 */
        public static final  String VALID = "1";
    }

    /**
     * 撤销标志
     */
    public  static class RevokeStatus {

        /** 未撤销 */
        public static final  String UNREVOKE = "0";

        /** 业务系统撤销 */
        public static final  String BUSINESS_REVOKE = "1";

        /** 患者主动撤销 */
        public static final  String PATIENT_REVOKE = "2";
    }

    /**
     * 打印标志
     */
    public  static class PrintFlag {

        /** 未打印 */
        public static final  String UNPRINT = "0";

        /** 打印成功 */
        public static final   String PRINTED = "1";
    }

    /**
     * 业务处理标志
     */
    public  static class ProcessStatus {

        /** 待处理 */
        public static final  String WAIT_PROCESS = "0";

        /** 处理成功 */
        public static final  String PROCESS_SUCCESS = "1";

        /** 处理失败 */
        public static final   String PROCESS_FAILED = "2";
    }

    public  static class Pattern {
        public static final   String IDCARD = "(^[1-9]\\d{5}(18|19|([23]\\d))\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}[0-9Xx]$)|(^[1-9]\\d{5}\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{2}$)";
        //yyyy-MM-dd HH:mm:ss 年-月-日 时:分:秒
        public static final   String YYYY_MM_DD_HH_mm_ss = "^(\\d{4})-([0-1]\\d)-([0-3]\\d)\\s([0-5]\\d):([0-5]\\d):([0-5]\\d)$";
        //带横杠 yyyy-MM-dd 年-月-日
        public static final  String YYYY_MM_DD ="([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})-(((0[13578]|1[02])-(0[1-9]|[12][0-9]|3[01]))|((0[469]|11)-(0[1-9]|[12][0-9]|30))|(02-(0[1-9]|[1][0-9]|2[0-8])))";
        //不带横杠 yyyyMMdd 年月日
        public static final  String YYYYMMDD = "^[12][890]\\d{2}((0[1-9])|10|11|12)(([012][0-9])|30|31)$";
        //时分秒
        public static final  String HHMMSS ="([0-1]?[0-9]|2[0-3])([0-5][0-9])([0-5][0-9])$";


    }



}
