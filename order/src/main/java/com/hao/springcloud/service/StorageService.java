package com.hao.springcloud.service;

import com.hao.springcloud.domain.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value="seata-storage-service")
public interface StorageService
{
    @PostMapping(value="/storage/decrease")
    CommonResult decrease(@RequestParam("ticketTypeId") Long ticketTypeId, @RequestParam("count") Integer count);
    @PostMapping(value="/storage/increase")
    CommonResult increase(@RequestParam("ticketTypeId") Long ticketTypeId, @RequestParam("count") Integer count);

    @PostMapping(value="/storage/decrease")
    CommonResult decreaseTest(@RequestParam("ticketTypeId") Long ticketTypeId, @RequestParam("count") Integer count);
}