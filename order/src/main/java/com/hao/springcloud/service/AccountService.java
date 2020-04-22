package com.hao.springcloud.service;

import com.hao.springcloud.domain.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

@FeignClient(value = "seata-account-service")
public interface AccountService
{
    @PostMapping(value = "/account/decrease")
    CommonResult decrease(@RequestParam("userId") Long userId, @RequestParam("money") BigDecimal money);
    @PostMapping(value = "/account/increase")
    CommonResult increase(@RequestParam("userId")Long userId, @RequestParam("money")  BigDecimal money);

    @PostMapping(value = "/account/decrease")
    CommonResult decreaseTest(@RequestParam("userId") Long userId, @RequestParam("money") BigDecimal money);


}