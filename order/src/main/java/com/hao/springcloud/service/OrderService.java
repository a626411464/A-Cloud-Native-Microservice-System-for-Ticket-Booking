package com.hao.springcloud.service;

import com.hao.springcloud.domain.Order;

import java.util.List;

public interface OrderService
{
    Order create(Order order);
    List<Order> findAll();

    int confirm(Long orderId);

    String continued(Order order);

    Order orderfindByid(Long orderId);

    Order cannel(Order order);

    void refund(long orderId);

     String breakOrder(Order order);
}