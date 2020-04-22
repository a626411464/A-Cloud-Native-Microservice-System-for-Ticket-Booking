package com.hao.springcloud.service;

import com.hao.springcloud.domain.Account;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.util.List;

public interface AccountService {

    /**
     * 扣减账户余额
     * @param userId 用户id
     * @param money 金额
     */
    void decrease(@RequestParam("userId") Long userId, @RequestParam("money") BigDecimal money);
    List<Account> findAll();

    void increase(Long userId, BigDecimal money);
}
