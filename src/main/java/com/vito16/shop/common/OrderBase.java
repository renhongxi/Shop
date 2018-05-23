package com.vito16.shop.common;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;


@Setter
@Getter
public class OrderBase implements Serializable {

    /**
     * 请求流水号
     */
    private String reqId;


}
