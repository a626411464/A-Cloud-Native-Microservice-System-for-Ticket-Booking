package com.hao.springcloud.controller;

import com.hao.springcloud.domain.CommonResult;
import com.hao.springcloud.service.AccountService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.math.BigDecimal;

@RestController
public class AccountController {

    @Resource
    AccountService accountService;

    /**
     * 扣减账户余额
     */
    @RequestMapping("/account/decrease")
    public CommonResult decrease(@RequestParam("userId") Long userId, @RequestParam("money") BigDecimal money){
        System.out.println(userId);
        System.out.println(money);
        accountService.decrease(userId,money);
        return new CommonResult(200,"扣减账户余额成功！");
    }

    @RequestMapping("/account/increase")
    public CommonResult increase(@RequestParam("userId") Long userId, @RequestParam("money") BigDecimal money){
        accountService.increase(userId,money);
        return new CommonResult(200,"扣减账户余额成功！");
    }


    @RequestMapping("/account/findAll")
    public CommonResult findAll(){
        return new CommonResult(200,"查询成功",accountService.findAll());
    }
}