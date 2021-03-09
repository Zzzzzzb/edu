package com.zhengzb.edu.common.constant;



public class CodeMsg {
    
    /**
     * 成功
     */
    public static final CodeMsg SUCCESS = new CodeMsg("0000", "操作成功");
    public static final CodeMsg ERROR_SERVER = new CodeMsg("-1", "服务器处理失败");
    public static final CodeMsg SERVER_BUSY = new CodeMsg("500", "系统繁忙，请稍候再试");
    public static final CodeMsg ERROR_TEMPORARY = new CodeMsg("-2", "临时异常");
    public static final CodeMsg SCOKET_TIME_OUT = new CodeMsg("-3", "请求超时");
    public static final CodeMsg ERROR_9998 = new CodeMsg("9998", "授权令牌无效");
    public static final CodeMsg ERROR_9999 = new CodeMsg("9999", "参数校验失败");
    public static final CodeMsg ERROR_10000 = new CodeMsg("10000", "请传入授权令牌");
    public static final CodeMsg ERROR_10001 = new CodeMsg("10001", "授权令牌错误");
    public static final CodeMsg ERROR_10002 = new CodeMsg("10002", "请求报文不能为空");
    public static final CodeMsg ERROR_10003 = new CodeMsg("10003", "错误的加密类型");
    public static final CodeMsg ERROR_10004 = new CodeMsg("10004", "错误的签名方式");
    public static final CodeMsg ERROR_10006 = new CodeMsg("10006", "请确认密钥是否正确");
    public static final CodeMsg ERROR_10007 = new CodeMsg("10007", "%s远程调用返回为空");
    public static final CodeMsg ERROR_10008 = new CodeMsg("10008", "远程调用失败%s");
    public static final CodeMsg ERROR_10009 = new CodeMsg("10009", "未支持的调用方法");
    public static final CodeMsg ERROR_10010 = new CodeMsg("10010", "未支持的请求体类型");
    public static final CodeMsg ERROR_10011 = new CodeMsg("10011", "字段长度过长");
    public static final CodeMsg ERROR_10012 = new CodeMsg("10012", "Sql执行失败");
    protected final String code;
    protected final String msg;
    
    protected CodeMsg(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }
    
    public String getCode() {
        return code;
    }
    
    public CodeMsg setCode(String code) {
        return new CodeMsg(code, this.msg);
    }
    
    public String getMsg() {
        return msg;
    }
    
    public CodeMsg setMsg(String msg) {
        return new CodeMsg(this.code, msg);
    }
}
