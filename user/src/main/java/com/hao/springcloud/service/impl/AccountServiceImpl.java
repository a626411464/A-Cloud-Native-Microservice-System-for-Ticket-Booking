package com.hao.springcloud.service.impl;

import com.hao.springcloud.dao.AccountDao;
import com.hao.springcloud.domain.Account;
import com.hao.springcloud.service.AccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class AccountServiceImpl implements AccountService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AccountServiceImpl.class);


    @Resource
    AccountDao accountDao;

    /**
     * 扣减账户余额
     */
    @Override
    public void decrease(Long userId, BigDecimal money) {
        LOGGER.info("------->account-service中扣减账户余额开始");
        //模拟超时异常，全局事务回滚
        //暂停几秒钟线程
//        try { TimeUnit.SECONDS.sleep(20); } catch (InterruptedException e) { e.printStackTrace(); }
        accountDao.decrease(userId,money);
        LOGGER.info("------->account-service中扣减账户余额结束");
    }

    @Override
    public List<Account> findAll() {
        return accountDao.findAll();
    }

    @Override
    public void increase(Long userId, BigDecimal money) {
        LOGGER.info("------->account-service中恢复账户余额开始");
        //模拟超时异常，全局事务回滚
        //暂停几秒钟线程
//        try { TimeUnit.SECONDS.sleep(20); } catch (InterruptedException e) { e.printStackTrace(); }
        accountDao.increase(userId,money);
        LOGGER.info("------->account-service中恢复账户余额结束");
    }
}
