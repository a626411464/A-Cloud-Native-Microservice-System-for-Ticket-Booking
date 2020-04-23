package com.hao.springcloud.service;

import com.hao.springcloud.domain.Storage;

import java.util.List;

public interface StorageService {
    /**
     * 扣减库存
     */
    void decrease(Long ticketTypeId, Integer count);

    List<Storage> findAll();

    int getPrice(Long ticketTypeId);


    void increase(Long ticketTypeId, Integer count);
}

