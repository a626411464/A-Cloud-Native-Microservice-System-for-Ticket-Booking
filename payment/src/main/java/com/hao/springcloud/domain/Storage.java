package com.hao.springcloud.domain;

import lombok.Data;

import java.io.Serializable;

@Data
public class Storage implements Serializable {

    private Long id;

    /**
     * 产品id
     */
    private Long ticketTypeId;

    /**
     * 总库存
     */
    private Integer total;

    /**
     * 已用库存
     */
    private Integer used;

    /**
     * 剩余库存
     */
    private Integer residue;
    /**
    *  车票价格
     */
    private Integer price;
}
