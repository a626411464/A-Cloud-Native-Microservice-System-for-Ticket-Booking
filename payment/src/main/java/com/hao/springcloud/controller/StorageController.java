package com.hao.springcloud.controller;

import com.hao.springcloud.domain.CommonResult;
import com.hao.springcloud.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StorageController {

    @Autowired
    private StorageService storageService;

    /**
     * 扣减库存
     */
    @RequestMapping("/storage/decrease")
    public CommonResult decrease(Long ticketTypeId, Integer count) {
        storageService.decrease(ticketTypeId, count);
        return new CommonResult(200,"扣减库存成功！");
    }
    @RequestMapping("/storage/increase")
    public CommonResult increase(Long ticketTypeId, Integer count) {
        storageService.increase(ticketTypeId, count);
        return new CommonResult(200,"恢复库存成功！");
    }

    @RequestMapping("/storage/getprice")
    public Integer getprice(Long ticketTypeId) {
        System.out.println(ticketTypeId);
        return storageService.getPrice(ticketTypeId);
    }




    @RequestMapping("/storage/findAll")
    public CommonResult findAll() {
        return new CommonResult(200,"库存查询成功！",storageService.findAll());
    }
}