package com.zhengzb.edu.common.constant;

import lombok.Data;

import java.io.Serializable;

@Data
public class ApiContext implements Serializable {

    private static final long serialVersionUID = 1L;
    private String userId;
}
