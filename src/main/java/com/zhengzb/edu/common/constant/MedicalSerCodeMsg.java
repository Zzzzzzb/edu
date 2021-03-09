package com.zhengzb.edu.common.constant;

public class MedicalSerCodeMsg extends CodeMsg {

    private MedicalSerCodeMsg(String code, String msg) {
        super(code, msg);
    }

    public static final MedicalSerCodeMsg ERROR_10000 = new MedicalSerCodeMsg("10000", "[HIS.HTTP]接口返回空");

    public static final MedicalSerCodeMsg ERROR_10001 = new MedicalSerCodeMsg("10001", "请确认密钥是否正确");
    /**
     * 用户服务
     */
    public static final MedicalSerCodeMsg ERROR_109701 = new MedicalSerCodeMsg("109701", "token已失效，请重新登录");

    public static final MedicalSerCodeMsg ERROR_109702 = new MedicalSerCodeMsg("ERROR_109702", "该用户不存在");

    public static final MedicalSerCodeMsg ERROR_109703 = new MedicalSerCodeMsg("ERROR_109703", "账号ID和账户号不能同时为空");

    public static final MedicalSerCodeMsg ERROR_109704 = new MedicalSerCodeMsg("ERROR_109704", "账户号不为空时账号类型不可为空");

    public static final MedicalSerCodeMsg ERROR_109705 = new MedicalSerCodeMsg("ERROR_109705", "存在多个查询账号");

    public static final MedicalSerCodeMsg ERROR_109706 = new MedicalSerCodeMsg("ERROR_109706", "该账号不存在");



}