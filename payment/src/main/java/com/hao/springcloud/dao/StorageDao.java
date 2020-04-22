package com.hao.springcloud.dao;

import com.hao.springcloud.domain.Storage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface StorageDao {

    //扣减库存
    void decrease(@Param("ticketTypeId") Long ticketTypeId, @Param("count") Integer count);

    List<Storage> findAll();

    void increase(@Param("ticketTypeId") Long ticketTypeId, @Param("count") Integer count);
}
