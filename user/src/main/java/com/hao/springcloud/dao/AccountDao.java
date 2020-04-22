package com.hao.springcloud.dao;

import com.hao.springcloud.domain.Account;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;

@Mapper
public interface AccountDao {

    /**
     * 扣减账户余额
     */
    void decrease(@Param("userId") Long userId, @Param("money") BigDecimal money);
    List<Account> findAll();

    void increase(@Param("userId") Long userId, @Param("money") BigDecimal money);
}
