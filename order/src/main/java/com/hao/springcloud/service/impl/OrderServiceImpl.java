package com.hao.springcloud.service.impl;

import com.hao.springcloud.dao.OrderDao;
import com.hao.springcloud.domain.Order;
import com.hao.springcloud.service.AccountService;
import com.hao.springcloud.service.OrderService;
import com.hao.springcloud.service.StorageService;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


@Service
@Slf4j
public class OrderServiceImpl implements OrderService
{
    @Resource
    private OrderDao orderDao;
    @Resource
    private StorageService storageService;
    @Resource
    private AccountService accountService;

    /**
     * 创建订单->调用库存服务扣减库存->调用账户服务扣减账户余额->修改订单状态
     * 简单说：下订单->扣库存->减余额->改状态
     * @return
     */
    @Override
    @GlobalTransactional(name = "fsp-create-order",rollbackFor = Exception.class)
    public Order create(Order order)
    {
        log.info("----->开始新建订单");
        //1 新建订单
        //2 获取订单的价格
        //获取车票的价格;
        System.out.println(order.getTicketTypeId());
        order.setMoney(storageService.getPrice(order.getTicketTypeId()));
        orderDao.create(order);
       return orderDao.orderfindByid(order.getId());
    }
    @Override
    @GlobalTransactional(name = "fsp-create-order",rollbackFor = Exception.class)
    public String continued(Order order) {
        log.info("----->订单微服务开始调用库存，做扣减Count");
        storageService.decrease(order.getTicketTypeId(),order.getCount());
        log.info("----->订单微服务开始调用库存，做扣减end");
        //4 扣减账户
        log.info("----->订单微服务开始调用账户，做扣减Money");
        accountService.decrease(order.getUserId(),order.getMoney());
        log.info("----->订单微服务开始调用账户，做扣减end");
        //5 修改订单状态，从零到1,1代表已经完成
        log.info("----->修改订单状态开始");
        orderDao.update(order.getUserId(),0);
        log.info("----->修改订单状态结束");
        return "交易成功";
    }


    //测试分布式回滚



    @Override
    public List<Order> findAll() {
        return orderDao.findAll();

    }
    @Override
    public int confirm(Long orderId) {
        Order order=orderDao.orderfindByid(orderId);
        return order.getStatus();
    }
    @Override
    public Order orderfindByid(Long orderId) {
        return orderDao.orderfindByid(orderId);
    }

    @Override
    public Order cannel(Order order) {
        orderDao.cannel(order.getId());
        return orderDao.orderfindByid(order.getId());
    }
    @Override
    @GlobalTransactional(name = "fsp-create-order",rollbackFor = Exception.class)
    public void refund(long orderId) {
        //获取该订单的全部信息
        Order order=orderDao.orderfindByid(orderId);
        //为用户返回money
        log.info("----->订单微服务开始调用账户，做扣减Money");
        accountService.increase(order.getUserId(),order.getMoney());
        log.info("----->订单微服务开始调用账户，做增加end");
        log.info("----->订单微服务开始调用库存，增加Count");
        storageService.increase(order.getTicketTypeId(),order.getCount());
        log.info("----->订单微服务开始调用库存，增加end");
        // 修改订单状态，修改订单状态 3表示已退款
        log.info("----->修改订单状态开始");
        orderDao.refund(orderId);
        log.info("----->修改订单状态结束");
    }

    @Override
    @GlobalTransactional(name = "fsp-create-order",rollbackFor = Exception.class)
    public String breakOrder(Order order) {
        storageService.decreaseTest(order.getTicketTypeId(),order.getCount());
        accountService.decreaseTest(order.getUserId(),order.getMoney());
        orderDao.update(order.getUserId(),0);
        return "交易成功";

    }
}

