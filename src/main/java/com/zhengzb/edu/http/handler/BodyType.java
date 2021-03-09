package com.zhengzb.edu.http.handler;



public enum BodyType {

    /** RequestBody--json数据提交 */
    JSON,
    /** FormBody--表单数据提交 */
    Form,
    /** MultipartBody--文件上传 */
    Multipart;
}
