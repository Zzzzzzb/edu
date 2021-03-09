package com.zhengzb.edu.http.handler;


import com.zhengzb.edu.common.result.Result;



public interface TxnHandler<REQ, RESP> {

    /**
     * 交易请求
     *
     * @param req
     * @return
     */
    Result<RESP> doTxn(REQ req);

}
